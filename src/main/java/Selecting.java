import java.sql.*;

public class Selecting {

    private DB db = new DB();
    private String query;
    private Statement statement = db.getConnection().createStatement();
    private ResultSet resultSet;

    public Selecting(DB db, String query, Statement statement, ResultSet resultSet) throws SQLException {
        this.db = db;
        this.query = query;
        this.statement = statement;
        this.resultSet = resultSet;
    }

    public Selecting() throws SQLException {

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public ResultSet setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
        return resultSet;
    }

}
