import java.sql.ResultSet;
import java.sql.SQLException;

class Reading {

    private Equipment equipment = new Equipment();
    private Selecting selecting = new Selecting();
    private ResultSet resultSet;

    public Reading(Equipment equipment, Selecting selecting, ResultSet resultSet) throws SQLException {
        this.equipment = equipment;
        this.selecting = selecting;
        this.resultSet = resultSet;
    }

    public Reading() throws SQLException {

    }

    public void select() {
        selecting.setQuery("select * from allequipment");
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Selecting getSelecting() {
        return selecting;
    }

    public void setSelecting(Selecting selecting) {
        this.selecting = selecting;
    }

}


