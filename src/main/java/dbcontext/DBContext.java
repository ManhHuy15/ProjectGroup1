package dbcontext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected Connection connection;

    public DBContext() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Project_PRJ301_Group5_Booking_Room_Hotel;trustServerCertificate=true";
            String username = "sa";
            String password = "123";

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

    public static void main(String[] args) { // Check connect of sql with netbeans
        DBContext dbContext = new DBContext();
        if (dbContext.isConnected()) {
            System.out.println("Connected Successfully!");
        } else {
            System.out.println("Connected Not Successfully!");
        }
    }
}