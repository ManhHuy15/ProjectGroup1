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
public Users getUserByUserName(String email) {
    try (Connection conn = new DBContext().getConnection()) {
        String sql = "SELECT * FROM Users WHERE User_Name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Users user = new Users();
            user.setUserId(rs.getInt("User_ID"));
            user.setUserName(rs.getString("User_Name"));
            user.setPassword(rs.getString("Password"));
            user.setPhone(rs.getString("Phone"));
            user.setAddress(rs.getString("Address"));
            user.setEmail(rs.getString("Email"));
            
            // Chuyển đổi từ java.sql.Date sang LocalDate
            java.sql.Date dobSqlDate = rs.getDate("DOB");
            if (dobSqlDate != null) {
                user.setDob(dobSqlDate.toLocalDate());
            } else {
                user.setDob(null); // hoặc xử lý giá trị null nếu cần
            }
            
            user.setRole(rs.getString("Role"));
            return user;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

// Method to get user by email and password
public Users getUserByUserNameAndPassword(String userName, String password) {
    try (Connection conn = new DBContext().getConnection()) {
        String sql = "SELECT * FROM Users WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);  // Password should be hashed
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Users user = new Users();
            user.setUserId(rs.getInt("User_ID"));
            user.setUserName(rs.getString("User_Name"));
            user.setPassword(rs.getString("Password"));
            user.setPhone(rs.getString("Phone"));
            user.setAddress(rs.getString("Address"));
            user.setEmail(rs.getString("Email"));
            
            // Chuyển đổi từ java.sql.Date sang LocalDate
            java.sql.Date dobSqlDate = rs.getDate("DOB");
            if (dobSqlDate != null) {
                user.setDob(dobSqlDate.toLocalDate());
            } else {
                user.setDob(null); // hoặc xử lý giá trị null nếu cần
            }
            
            user.setRole(rs.getString("Role"));
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
            String sql = "INSERT INTO Users (User_Name, Password, Phone, Address, Email, DOB, Role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword()); // Hash the password
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getEmail());

            // Convert LocalDate to java.sql.Date
            if (user.getDob() != null) {
                ps.setDate(6, java.sql.Date.valueOf(user.getDob()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            ps.setString(7, user.getRole());
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


}

