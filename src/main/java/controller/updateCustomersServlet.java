/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import model.Users;

/**
 *
 * @author Tobi
 */
@WebServlet(name="updateCustomers", urlPatterns={"/updateCustomers"})
public class updateCustomersServlet extends HttpServlet {
   
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id")); // Lấy ID người dùng từ tham số
        UserDAO userDAO = new UserDAO();
        Users user = userDAO.getUserById(userId); // Lấy thông tin người dùng từ DAO

        if (user != null) {
            request.setAttribute("user", user); // Đưa thông tin người dùng vào request
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response); // Forward tới JSP chỉnh sửa
        } else {
            request.setAttribute("error", "Người dùng không tồn tại.");
            request.getRequestDispatcher("manageCustomers.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String password = request.getParameter("password"); 
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob"); 

        Users user = new Users();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password); 
        user.setPhone(phone);
        user.setAddress(address);
        user.setEmail(email);
        user.setDob(LocalDate.parse(dob)); 
        UserDAO userDAO = new UserDAO();
        boolean isUpdated = userDAO.updateUsers(user); // Gọi phương thức update

        if (isUpdated) {
            response.sendRedirect("manageCustomers.jsp"); // Chuyển hướng về trang quản lý nếu thành công
        } else {
            request.setAttribute("error", "Cập nhật không thành công!");
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response); // Trả về JSP chỉnh sửa nếu thất bại
        }
    }
}

