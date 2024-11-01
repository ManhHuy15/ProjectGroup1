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
import model.Products;

/**
 *
 * @author Tobi
 */
public class ProductsDAO {

    // Method to retrieve all products
public List<Products> getAllProducts() {
    List<Products> products = new ArrayList<>();
    String query = "select * from Products";
    
    try (Connection con = new DBContext().getConnection(); 
         PreparedStatement ps = con.prepareStatement(query); 
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Products product = new Products();
            product.setProductId(rs.getInt("product_id"));

            product.setProductName(rs.getString("product_name"));
            product.setPrice(rs.getDouble("price"));
            product.setQuantity(rs.getInt("stock"));
            product.setDescription(rs.getString("description"));
            product.setCreatedDate(rs.getDate("created_at"));
            products.add(product);
        }
    } catch (SQLException e) {
        // Xử lý lỗi, ví dụ: log vào file hoặc ném lại ngoại lệ
        e.printStackTrace(); // Hoặc ghi lại vào log
    }
    return products;
}


    // Method to add a new product
    public boolean addProducts(Products product) {
        String sql = "INSERT INTO Products (Brand, Product_Name, Price, Quantity, Size, Description, Image, Rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        DBContext dbContext = new DBContext();

        try ( Connection conn = dbContext.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getBrand());
            pstmt.setString(2, product.getProductName());
            pstmt.setDouble(3, product.getPrice());  // Use setDouble for price
            pstmt.setInt(4, product.getQuantity());
            pstmt.setString(5, product.getSize());
            pstmt.setString(6, product.getDescription());
            pstmt.setString(7, product.getImage());
            if (product.getRate() != null) {
                pstmt.setDouble(8, product.getRate()); // Sử dụng setDouble nếu không null
            } else {
                pstmt.setNull(8, java.sql.Types.DOUBLE); // Sử dụng setNull nếu null
            }

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
