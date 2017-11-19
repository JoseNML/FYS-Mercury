/*
 * 
 */
package hva.fys.mercury;

/**
 *
 * @author Mitchell 
 */
public class TableHandler {
    private int registratieNrColumn;
    private String typeBagageColumn;
    private String merkColumn;
    private String vluchtColumn;
    private int bagageNrColumn;
    private String locatieColumn;
    private String kleur1Column;
    private String kleur2Column;
    private String maatColumn;
    private String gewichtColumn;
    private String infoPassagierColumn;
    private String overigeColumn;
    
    public TableHandler(){}
    
    public TableHandler(int reg, String type, String merk, String vlucht, int bag, String loc,
            String kleur1, String kleur2, String maat, String gewicht, String info, String overige){
        this.registratieNrColumn = reg;
        this.typeBagageColumn = type;
        this.merkColumn = merk;
        this.vluchtColumn = vlucht;
        this.bagageNrColumn = bag;
        this.locatieColumn = loc;
        this.kleur1Column = kleur1;
        this.kleur2Column = kleur2;
        this.maatColumn = maat;
        this.gewichtColumn = gewicht;
        this.infoPassagierColumn = info;
        this.overigeColumn = overige;
    }
    
    /**
     * @return the registratieNrColumn
     */
    public int getRegistratieNrColumn() {
        return registratieNrColumn;
    }

    /**
     * @param registratieNrColumn the registratieNrColumn to set
     */
    public void setRegistratieNrColumn(int registratieNrColumn) {
        this.registratieNrColumn = registratieNrColumn;
    }

    /**
     * @return the typeBagageColumn
     */
    public String getTypeBagageColumn() {
        return typeBagageColumn;
    }

    /**
     * @param typeBagageColumn the typeBagageColumn to set
     */
    public void setTypeBagageColumn(String typeBagageColumn) {
        this.typeBagageColumn = typeBagageColumn;
    }

    /**
     * @return the merkColumn
     */
    public String getMerkColumn() {
        return merkColumn;
    }

    /**
     * @param merkColumn the merkColumn to set
     */
    public void setMerkColumn(String merkColumn) {
        this.merkColumn = merkColumn;
    }

    /**
     * @return the vluchtColumnString
     */
    public String getVluchtColumn() {
        return vluchtColumn;
    }

    /**
     * @param vluchtColumn the vluchtColumn to set
     */
    public void setVluchtColumn(String vluchtColum) {
        this.vluchtColumn = vluchtColumn;
    }

    /**
     * @return the bagageNrColumn
     */
    public int getBagageNrColumn() {
        return bagageNrColumn;
    }

    /**
     * @param bagageNrColumn the bagageNrColumn to set
     */
    public void setBagageNrColumn(int bagageNrColumn) {
        this.bagageNrColumn = bagageNrColumn;
    }

    /**
     * @return the locatieColumn
     */
    public String getLocatieColumn() {
        return locatieColumn;
    }

    /**
     * @param locatieColumn the locatieColumn to set
     */
    public void setLocatieColumn(String locatieColumn) {
        this.locatieColumn = locatieColumn;
    }

    /**
     * @return the kleur1Column
     */
    public String getKleur1Column() {
        return kleur1Column;
    }

    /**
     * @param kleur1Column the kleur1Column to set
     */
    public void setKleur1Column(String kleur1Column) {
        this.kleur1Column = kleur1Column;
    }

    /**
     * @return the kleur2Column
     */
    public String getKleur2Column() {
        return kleur2Column;
    }

    /**
     * @param kleur2Column the kleur2Column to set
     */
    public void setKleur2Column(String kleur2Column) {
        this.kleur2Column = kleur2Column;
    }

    /**
     * @return the maatColumn
     */
    public String getMaatColumn() {
        return maatColumn;
    }

    /**
     * @param maatColumn the maatColumn to set
     */
    public void setMaatColumn(String maatColumn) {
        this.maatColumn = maatColumn;
    }

    /**
     * @return the gewichtColumn
     */
    public String getGewichtColumn() {
        return gewichtColumn;
    }

    /**
     * @param gewichtColumn the gewichtColumn to set
     */
    public void setGewichtColumn(String gewichtColumn) {
        this.gewichtColumn = gewichtColumn;
    }

    /**
     * @return the infoPassagierColumn
     */
    public String getInfoPassagierColumn() {
        return infoPassagierColumn;
    }

    /**
     * @param infoPassagierColumn the infoPassagierColumn to set
     */
    public void setInfoPassagierColumn(String infoPassagierColumn) {
        this.infoPassagierColumn = infoPassagierColumn;
    }

    /**
     * @return the overigeColumn
     */
    public String getOverigeColumn() {
        return overigeColumn;
    }

    /**
     * @param overigeColumn the overigeColumn to set
     */
    public void setOverigeColumn(String overigeColumn) {
        this.overigeColumn = overigeColumn;
    }

}
