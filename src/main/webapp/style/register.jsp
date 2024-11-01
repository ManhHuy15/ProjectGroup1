<!DOCTYPE html>
<html>
<head>
    <title>Register Page</title>
</head>
<body>
    <h2>Register</h2>
    <form action="register" method="post">
        <label for="userName">Username:</label>
        <input type="text" id="userName" name="userName" required>
        <br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required>
        <br><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address">
        <br><br>

        <label for="dateOfBirth">Date of Birth (YYYY-MM-DD):</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" required>
        <br><br>
        
        <input type="submit" value="Register">
    </form>

    <!-- Hi?n th? thông báo l?i n?u có -->
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <!-- Hi?n th? thông báo thành công n?u có -->
    <c:if test="${not empty success}">
        <p style="color:green;">${success}</p>
    </c:if>
</body>
</html>
