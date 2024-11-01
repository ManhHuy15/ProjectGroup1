package dbcontext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    protected Connection connection;

    public DBContext() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=assignmentPRJ301;trustServerCertificate=true";
            String username = "kttanh";
            String password = "2003";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public boolean isConnected() { // Check connect of sql with netbeans
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) { // Check connect of sql with netbeans
        DBContext dbContext = new DBContext();
        if (dbContext.isConnected()) {
            System.out.println("Connected Successfully!");
        } else {
            System.out.println("Connected Not Successfully!");
        }
    }
}