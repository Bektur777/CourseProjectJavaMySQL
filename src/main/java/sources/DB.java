package sources;

import java.sql. *;

public class DB {

    private String URL = "jdbc:mysql://localhost:3306/mydb";
    private String USERNAME = "root";
    private String PASSWORD = "admin123";

    private Connection connection;

    public DB () throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

}
