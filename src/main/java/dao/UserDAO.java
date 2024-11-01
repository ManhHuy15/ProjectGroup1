/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Users;


/**
 *
 * @author Tobi
 */
public class UserDAO {

   // Method to get user by email
public Users getUserByUserNameAndEmail(String username, String email) {
      String sql = "SELECT user_id, username, email, phone_number, address, created_at, role " +
                 "FROM assignmentPRJ301.dbo.Users " +
                 "WHERE username = ? or email = ? ";

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, username);
        ps.setString(2, email);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                java.sql.Date dobSqlDate = rs.getDate("created_at");
                user.setDob(dobSqlDate != null ? dobSqlDate.toLocalDate() : null);
                
                user.setRole(rs.getString("role"));

                return user;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return null;
}

// Method to get user by email and password
    public Users getUserByUserNameAndPassword(String userName, String password) {
        try (Connection conn = new DBContext().getConnection()) {
            String sql = "SELECT [user_id]\n" +
                    "      ,[username]\n" +
                    "      ,[password_hash]\n" +
                    "      ,[email]\n" +
                    "      ,[phone_number]\n" +
                    "      ,[address]\n" +
                    "      ,[role]\n" +
                    "      ,[created_at]\n" +
                    "  FROM [assignmentPRJ301].[dbo].[Users]" +
                    "WHERE username = ? AND password_hash = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);  // Password should be hashed
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password_hash"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                java.sql.Date dobSqlDate = rs.getDate("created_at");
                user.setDob(dobSqlDate != null ? dobSqlDate.toLocalDate() : null);
                
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


// Method to add a new user
public void addUser(Users user) {
        try (Connection conn = new DBContext().getConnection()) {
            String sql = "INSERT INTO [dbo].[Users]\n" +
                        "           ([username]\n" +
                        "           ,[password_hash]\n" +
                        "           ,[email]\n" +
                        "           ,[phone_number]\n" +
                        "           ,[address]\n" +
                        "           ,[role]\n" +
                        "           ,[created_at])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
           
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword()); 
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getAddress());
        ps.setString(6, user.getRole());
        ps.setDate(7, java.sql.Date.valueOf(user.getDob()));

        ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            throw new RuntimeException("Error adding user to the database: " + e.getMessage());
        }
    }

 public List<Users> getAllCustomers() {
    List<Users> users = new ArrayList<>();
    String sql = "SELECT * FROM Users WHERE Role != 'admin'"; 

    try (Connection conn = new DBContext().getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Users user = new Users();
            user.setUserId(rs.getInt("UserId")); 
            user.setUserName(rs.getString("User_Name"));
            user.setPassword(rs.getString("Password")); 
            user.setPhone(rs.getString("Phone"));
            user.setAddress(rs.getString("Address"));
            user.setEmail(rs.getString("Email"));

            // Kiểm tra nếu DOB không null trước khi chuyển đổi
            if (rs.getDate("DOB") != null) {
                user.setDob(rs.getDate("DOB").toLocalDate());
            } else {
                user.setDob(null); // Nếu DOB là null
            }

            user.setRole(rs.getString("Role")); // Nếu cần
            users.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return users;
}


    // Phương thức chỉnh sửa thông tin người dùng
    public Users getUserById(int userId) {
    Users user = null;
    String sql = "SELECT * FROM Users WHERE User_Id = ?"; // Giả sử có cột UserId trong bảng Users

    try (Connection conn = new DBContext().getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            user = new Users();
            user.setUserId(rs.getInt("User_Id"));
            user.setUserName(rs.getString("User_Name"));
            user.setPassword(rs.getString("Password")); // Lưu ý xử lý mật khẩu một cách an toàn
            user.setPhone(rs.getString("Phone"));
            user.setAddress(rs.getString("Address"));
            user.setEmail(rs.getString("Email"));
            user.setDob(rs.getDate("DOB").toLocalDate());
            user.setRole(rs.getString("Role")); // Nếu cần
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return user;
}

public boolean updateUsers(Users user) {
    String sql = "UPDATE Users SET User_Name = ?, Password = ?, Phone = ?, Address = ?, Email = ?, DOB = ? WHERE UserId = ?";
    
    try (Connection conn = new DBContext().getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword()); // Chú ý: xử lý mật khẩu an toàn
        pstmt.setString(3, user.getPhone());
        pstmt.setString(4, user.getAddress());
        pstmt.setString(5, user.getEmail());
        pstmt.setDate(6, java.sql.Date.valueOf(user.getDob())); // Chuyển đổi LocalDate sang java.sql.Date
        pstmt.setInt(7, user.getUserId());

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public boolean deleteUserById(int userId) {
    String sql = "DELETE FROM Users WHERE UserId = ?";
    try (Connection conn = new DBContext().getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, userId);
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0; // Return true if at least one row was deleted
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean updateUser(Users user) {
        String sql = "UPDATE [dbo].[Users]\n" +
                "   SET [password_hash] = ?\n" +
                "      ,[email] = ?\n" +
                "      ,[phone_number] = ?\n" +
                "      ,[address] = ?\n" +
                " WHERE Users.user_id = ?";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getAddress());
            stmt.setInt(5, user.getUserId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returns false if an error occurred
        }
    }


}

