<%--
    Document   : home
    Created on : Nov 1, 2024, 5:05:07 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Change Info Page</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Change Info Account ${sessionScope.user.userName}</h2>

        <form action="change_info_account" method="post" style="max-width: 400px; border-radius: 15px; box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2); padding: 20px;" onsubmit="return validatePassword()">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" name="email" required value="${sessionScope.user.email}">
            </div>

            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" class="form-control" id="phoneNumber" name="phone" required value="${sessionScope.user.phone}">
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" id="address" name="address" required value="${sessionScope.user.address}">
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="repeatPassword">Repeat Password:</label>
                <input type="password" class="form-control" id="repeatPassword" name="repeatPassword" required>
            </div>

            <div id="passwordError" style="color: red; display: none;">
                Passwords do not match.
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

        <script>
            function validatePassword() {
                const password = document.getElementById("password").value;
                const repeatPassword = document.getElementById("repeatPassword").value;

                if (password !== repeatPassword) {
                    document.getElementById("passwordError").style.display = "block";
                    return false;
                }

                document.getElementById("passwordError").style.display = "none";
                return true;
            }
        </script>

    </div>

</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function validatePassword() {
        const password = document.getElementById("password").value;
        const repeatPassword = document.getElementById("repeatPassword").value;

        if (password !== repeatPassword) {
            document.getElementById("passwordError").style.display = "block";
            return false;
        }

        document.getElementById("passwordError").style.display = "none";
        return true;
    }
</script>
</html>
