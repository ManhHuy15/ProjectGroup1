<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Page</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div>

    <h2 style="display: flex; justify-content: center">List Product</h2>

    <table class="table table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity in Stock</th>
            <th>Description</th>
            <th>Create Time</th>

            <td></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.products}" var="p">
            <tr>
                <td>${p.productId}</td>
                <td>${p.productName}</td>
                <td>${p.price}</td>
                <td>${p.quantity}</td>
                <td>${p.description}</td>
                <td>${p.createdDate}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>


</div>
</body>
</html>