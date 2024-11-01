
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
        <title>Home Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Hello ${sessionScope.user.userName}!</h1>
    <div>

        <div style="width: 300px">
            <c:if test="${sessionScope.user.role == 'user'}">
                <button onclick="window.location.href = 'change_info_account'; " class="btn btn-primary btn-block">Change Info Account</button>
            </c:if>
        </div>


        <div style="margin-top: 20px; width: 300px">
            <button onclick="window.location.href = 'logout'; " class="btn btn-primary btn-block">Logout</button>
        </div>

        <div style="margin-top: 20px; width: 300px">
            <button onclick="window.location.href = 'list_product'; " class="btn btn-primary btn-block">List All Product</button>
        </div>


    </div>
    </body>
</html>
