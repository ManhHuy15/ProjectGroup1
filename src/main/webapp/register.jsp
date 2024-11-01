<!DOCTYPE html>
<html>
<head>
    <title>Register Page</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Register</h2>
        <form action="register" method="post" class="mx-auto"  style="max-width: 400px; border-radius: 15px; box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2); padding: 20px;">
            <div class="form-group">
                <label for="userName">Username:</label>
                <input type="text" class="form-control" id="userName" name="userName" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" id="address" name="address">
            </div>

            <div class="form-group">
                <label for="dateOfBirth">Date of Birth:</label>
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
            </div>

            <!-- Display error message if present -->
            <div style="color: red;">
                <c:if test="${not empty error}">
                    <p>${error}</p>
                </c:if>
            </div>

            <!-- Display success message if present -->
            <div style="color: green;">
                <c:if test="${not empty success}">
                    <p>${success}</p>
                </c:if>
            </div>

            <button type="submit" class="btn btn-primary btn-block">Register</button>
            <p class="text-center mt-3">
                Already have an account? <a href="login">Login here</a>
            </p>
        </form>
    </div>
         
      <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
