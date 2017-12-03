package hva.fys.mercury.DAO;

import hva.fys.mercury.MainApp;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

public class DatabaseManager {

    private static final String DB_DEFAULT_DATABASE = "sys";
    private static final String DB_DEFAULT_SERVER_URL = "localhost:3306";
    private static final String DB_DEFAULT_ACCOUNT = "root";
    private static final String DB_DEFAULT_PASSWORD = MainApp.DATABASE_PASSWORD;

    private final static String DB_DRIVER_URL = "com.mysql.jdbc.Driver";
    private final static String DB_DRIVER_PREFIX = "jdbc:mysql://";
    private final static String DB_DRIVER_PARAMETERS = "?useSSL=false";

    private Connection connection = null;

    // set for verbose logging of all queries
    private boolean verbose = true;

    // remembers the first error message on the connection 
    private String errorMessage = null;

    public DatabaseManager() {
        this(DB_DEFAULT_DATABASE, DB_DEFAULT_SERVER_URL, DB_DEFAULT_ACCOUNT, DB_DEFAULT_PASSWORD);
    }

    public DatabaseManager(String dbName) {
        this(dbName, DB_DEFAULT_SERVER_URL, DB_DEFAULT_ACCOUNT, DB_DEFAULT_PASSWORD);
    }

    public DatabaseManager(String dbName, String account, String password) {
        this(dbName, DB_DEFAULT_SERVER_URL, account, password);
    }

    public DatabaseManager(String dbName, String serverURL, String account, String password) {
        try {
            // verify that a proper JDBC driver has been installed and linked
            if (!selectDriver(DB_DRIVER_URL)) {
                return;
            }

            if (password == null) {
                password = "";
            }

            // establish a connection to a named database on a specified server	
            String connStr = DB_DRIVER_PREFIX + serverURL + "/" + dbName + DB_DRIVER_PARAMETERS;
            log("Connecting " + connStr);
            this.connection = DriverManager.getConnection(connStr, account, password);

        } catch (SQLException eSQL) {
            error(eSQL);
            this.close();
        }
    }

    public final void close() {

        if (this.connection == null) {
            // db has been closed earlier already
            return;
        }
        try {
            this.connection.close();
            this.connection = null;
            this.log("Data base has been closed");
        } catch (SQLException eSQL) {
            error(eSQL);
        }
    }

    private Boolean selectDriver(String driverName) {
        try {
            Class.forName(driverName);
            // Put all non-prefered drivers to the end, such that driver selection hits the first
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver d = drivers.nextElement();
                if (!d.getClass().getName().equals(driverName)) {   // move the driver to the end of the list
                    DriverManager.deregisterDriver(d);
                    DriverManager.registerDriver(d);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            error(ex);
            return false;
        }
        return true;
    }

    /**
     * *
     * Executes a DDL, DML or DCL query that does not yield a result set
     *
     * @param sql the full sql text of the query.
     * @return the number of rows that have been impacted, -1 on error
     */
    public int executeUpdateQuery(String sql) {
        try {
            Statement s = this.connection.createStatement();
            log(sql);
            int n = s.executeUpdate(sql);
            s.close();
            return (n);
        } catch (SQLException ex) {
            // handle exception
            error(ex);
            return -1;
        }
    }

    /**
     * *
     * Executes an SQL query that yields a ResultSet with the outcome of the
     * query. This outcome may be a single row with a single column in case of a
     * scalar outcome.
     *
     * @param sql the full sql text of the query.
     * @return a ResultSet object that can iterate along all rows
     * @throws SQLException
     */
    public ResultSet executeResultSetQuery(String sql) throws SQLException {
        Statement s = this.connection.createStatement();
        log(sql);
        ResultSet rs = s.executeQuery(sql);
        // cannot close the statement, because that also closes the resultset
        return rs;
    }

    /**
     * *
     * Executes query that is expected to return a single String value
     *
     * @param sql the full sql text of the query.
     * @return the string result, null if no result or error
     */
    public String executeStringQuery(String sql) {
        String result = null;
        try {
            Statement s = this.connection.createStatement();
            log(sql);
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                result = rs.getString(1);
            }
            // close both statement and resultset
            s.close();
        } catch (SQLException ex) {
            error(ex);
        }

        return result;
    }

    /**
     * *
     * Executes query that is expected to return a list of String values
     *
     * @param sql the full sql text of the query.
     * @return the string result, null if no result or error
     */
    public String executeStringListQuery(String sql) {
        String result = null;
        try {
            Statement s = this.connection.createStatement();
            log(sql);
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                result = rs.getString(1);
            }
            // close both statement and resultset
            s.close();
        } catch (SQLException ex) {
            error(ex);
        }

        return result;
    }

    /**
     * *
     * echoes a message on the system console, if run in verbose mode
     *
     * @param message
     */
    public void log(String message) {
        if (isVerbose()) {
            System.out.println("DatabaseManager: " + message);
        }
    }

    /**
     * *
     * echoes an exception and its stack trace on the system console. remembers
     * the message of the first error that occurs for later reference. closes
     * the connection such that no further operations are possible.
     *
     * @param e
     */
    public final void error(Exception e) {
        String msg = "DatabaseManager-" + e.getClass().getName() + ": " + e.getMessage();

        // capture the message of the first error of the connection
        if (this.errorMessage == null) {
            this.errorMessage = msg;
        }
        System.out.println(msg);
        e.printStackTrace();

        // if an error occurred, close the connection to prevent further operations
        this.close();
    }

    /**
     * *
     * builds a sample database with sample content
     *
     * @param dbName name of the sample database.
     * 
     * @author: David Britt
     */
     public static void createDatabase(String dbName) {

        System.out.println("Creating the " + dbName + " database...");

        // use the sys schema for creating another db
        DatabaseManager sysJDBC = new DatabaseManager("sys");
        //original:
//        sysJDBC.executeUpdateQuery("CREATE DATABASE IF NOT EXISTS " + dbName);

        sysJDBC.executeUpdateQuery("DROP DATABASE IF EXISTS " + dbName);
        sysJDBC.executeUpdateQuery("CREATE DATABASE " + dbName);

        sysJDBC.close();

        // create or truncate Airport table in the Airline database
        System.out.println("Creating the " + dbName + " Table...");
        DatabaseManager myJDBC = new DatabaseManager(dbName);

        //Luchthaven table
        myJDBC.executeUpdateQuery("DROP TABLE IF EXISTS `LuchtHaven`;");
        myJDBC.executeUpdateQuery(
                "CREATE TABLE IF NOT EXISTS `LuchtHaven` ("
                + "`IATA_Code` varchar(3) NOT NULL,"
                + "`LuchtHavenNaam` varchar(45)  DEFAULT NULL,"
                + "`Land` varchar(45)  DEFAULT NULL,"
                + "`TimeZone` int(2)  DEFAULT NULL,"
                + "`DaylightSaving` varchar(45)  DEFAULT NULL,"
                + "PRIMARY KEY (`IATA_Code`)"
                + ");"
        );

        //Vluchten
        myJDBC.executeUpdateQuery("DROP TABLE IF EXISTS `Vluchten`;");
        myJDBC.executeUpdateQuery(
                "CREATE TABLE `Vluchten` ("
                + "`VluchtenID` int(4) NOT NULL,"
                + "`Airline` varchar(45) DEFAULT NULL,"
                + "`VluchtNummer` varchar(45) DEFAULT NULL,"
                + "`VanLocatie` varchar(45) DEFAULT NULL,"
                + "`NaarLocatie` varchar(45) DEFAULT NULL,"
                + "PRIMARY KEY (`VluchtenID`)"
                + ");"
        );

        //Locaties
        myJDBC.executeUpdateQuery("DROP TABLE IF EXISTS `Locaties`;");
        myJDBC.executeUpdateQuery(
                "CREATE TABLE `Locaties` ("
                + "`LocatieID` int(4) NOT NULL,"
                + "`Engels` varchar(45) DEFAULT NULL,"
                + "`Nederlands` varchar(45) DEFAULT NULL,"
                + "`Spaans` varchar(45) DEFAULT NULL,"
                + "`Turks` varchar(45) DEFAULT NULL,"
                + "PRIMARY KEY (`LocatieID`)"
                + ");"
        );

        //BagageType
        myJDBC.executeUpdateQuery("DROP TABLE IF EXISTS `BagageTypes`;");
        myJDBC.executeUpdateQuery(
                "CREATE TABLE `BagageTypes` ("
                + "`BagageTypeID` int(4) NOT NULL,"
                + "`Engels` varchar(45) DEFAULT NULL,"
                + "`Nederlands` varchar(45) DEFAULT NULL,"
                + "`Spaans` varchar(45) DEFAULT NULL,"
                + "`Turks` varchar(45) DEFAULT NULL,"
                + "PRIMARY KEY (`BagageTypeID`)"
                + ");"
        );

        //Kleuren
        myJDBC.executeUpdateQuery("DROP TABLE IF EXISTS `Kleuren`;");
        myJDBC.executeUpdateQuery(
                "CREATE TABLE `Kleuren` ("
                + "`RalCode` int(4) NOT NULL,"
                + "`Engels` varchar(45) DEFAULT NULL,"
                + "`Nederlands` varchar(45) DEFAULT NULL,"
                + "`Spaans` varchar(45) DEFAULT NULL,"
                + "`Turks` varchar(45) DEFAULT NULL,"
                + "PRIMARY KEY (`RalCode`)"
                + ");"
        );

        //Reizigers Table
        myJDBC.executeUpdateQuery("DROP TABLE IF EXISTS `Reizigers`;");
        myJDBC.executeUpdateQuery(
                "CREATE TABLE `Reizigers` ("
                + "`ReizigerID` int(11) NOT NULL,"
                + "`VoorNaam` varchar(45)  DEFAULT NULL,"
                + "`AchterNaam` varchar(45)  DEFAULT NULL,"
                + "`WoonPlaats` varchar(45)  DEFAULT NULL,"
                + "`Adres` varchar(45)  DEFAULT NULL,"
                + "`Land` varchar(45)  DEFAULT NULL,"
                + "`Telefoon` varchar(45)  DEFAULT NULL,"
                + "`Email` varchar(45)  DEFAULT NULL,"
                + "`IATA_Code` varchar(3) DEFAULT NULL,"
                + "`BagageRegistratieNummer` int(11) DEFAULT NULL,"
                + "PRIMARY KEY (`ReizigerID`),"
                + "FOREIGN KEY (`IATA_Code`) REFERENCES LuchtHaven (`IATA_Code`)"
                + ");"
        );

        //Bagage Table
        myJDBC.executeUpdateQuery("DROP TABLE IF EXISTS `Bagage`;");
        myJDBC.executeUpdateQuery(
                "CREATE TABLE IF NOT EXISTS `Bagage` ("
                + "`BagageRegistratieNummer` int(11) NOT NULL,"
                + "`IATA_Code` varchar(3) DEFAULT NULL,"
                + "`DateFound` varchar(45)  DEFAULT NULL,"
                + "`TimeFound` varchar(45)  DEFAULT NULL,"
                + "`BagageType` varchar(45)  DEFAULT NULL,"
                + "`BrandMerk` varchar(45)  DEFAULT NULL,"
                + "`ArrivedWithFlight` varchar(45)  DEFAULT NULL,"
                + "`BagageLabel` varchar(45)  DEFAULT NULL,"
                + "`LocatieGevonden` varchar(45)  DEFAULT NULL,"
                + "`MainColor` varchar(45)  DEFAULT NULL,"
                + "`SecondColor` varchar(45)  DEFAULT NULL,"
                + "`Grootte` varchar(45)  DEFAULT NULL,"
                /*
                 Double check gewicht, mischien beter om int(3) van te maken?       
                 */
                + "`Gewicht` varchar(45)  DEFAULT NULL,"
                + "`Reiziger` varchar(45)  DEFAULT NULL,"
                + "`OverigeEigenschappen` varchar(45)  DEFAULT NULL,"
                //                + "`ReizigerID` int(11)  DEFAULT NULL,"
                //                + "`Status` varchar(45)  DEFAULT NULL,"
                + "PRIMARY KEY (`BagageRegistratieNummer`),"
                //                + "FOREIGN KEY (`ReizigerID`) REFERENCES Reizigers(`ReizigerID`),"
                + "FOREIGN KEY (`IATA_Code`) REFERENCES LuchtHaven(`IATA_Code`)"
                /*
                    Figure out why these foreign keys don't work
                    My theory at the moment is that you can not set a FK to a none PK entity.
                 */
                //                + "FOREIGN KEY (`BagageType`) REFERENCES BagageTypes(`Engels`),"
                //                + "FOREIGN KEY (`ArrivedWithFlight`) REFERENCES Vluchten(`VluchtNummer`),"
                //                + "FOREIGN KEY (`LocatieGevonden`) REFERENCES Locatie(`Engels`),"
                //                + "FOREIGN KEY (`MainColor`) REFERENCES Kleuren(`Engels`),"
                //                + "FOREIGN KEY (`SecondColor`) REFERENCES Kleuren(`Engels`),"

                + ");"
        );

        //Gebruiker Table
        myJDBC.executeUpdateQuery("DROP TABLE IF EXISTS `Gebruikers`;");
        myJDBC.executeUpdateQuery(
                "CREATE TABLE `Gebruikers` ("
                + "`EmployeeID` int(11) NOT NULL,"
                + "`Initials` varchar(45)  DEFAULT NULL,"
                + "`FirstName` varchar(45)  DEFAULT NULL,"
                + "`MiddleName` varchar(45)  DEFAULT NULL,"
                + "`SurName` varchar(45)  DEFAULT NULL,"
                + "`BirthDate` varchar(45)  DEFAULT NULL,"
                + "`StartEmploymentDate` varchar(45)  DEFAULT NULL,"
                + "`WorkEmail` varchar(45)  DEFAULT NULL,"
                + "`WorkingLocation` varchar(45)  DEFAULT NULL,"
                + "`StatusEmployment` varchar(45)  DEFAULT NULL,"
                + "`EndDateEmployment` varchar(45)  DEFAULT NULL,"
                + "`PersonalEmail` varchar(45)  DEFAULT NULL,"
                + "`MobilePhoneNumber` varchar(45)  DEFAULT NULL,"
                + "`HomePhoneNumber` varchar(45)  DEFAULT NULL,"
                + "`DepartmentEmployment` varchar(45)  DEFAULT NULL,"
                + "`HomeAdress` varchar(45)  DEFAULT NULL,"
                + "PRIMARY KEY (`EmployeeID`)"
                + ");"
        );

        myJDBC.executeUpdateQuery("SHOW ENGINE INNODB STATUS;");

        //close the connection with the database 
        myJDBC.close();
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}