<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>CatPage</title>
</head>
<p>
<p>Hello, meow! <p>
<table border="5" cellspacing="0" cellpadding="20">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Age</td>
        <td>Password</td>
        <td>City</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getAge()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getCity()}</td>
        </tr>
    </c:forEach>
</table>
</p>
<form action="${pageContext.request.contextPath}/add" method="post">
    Name: <input name="name" type="text" minlength="1" />
    Age: <input name="age" type="number" min=1 />
    Password: <input name="password" type="password" min=1 />
    City: <input name="city" type="text" />
    <input type="submit" value="addDB" />
</form>
</p>
<br><br>
<form action="${pageContext.request.contextPath}/update" method="post">
    Id: <input name="id" type=number minlength="1" />
    <br></br>
    Old name: <input name="oldname" type="text" minlength="1" />
    New name: <input name="name" type="text" minlength="1" />
    Old password:<input name="oldpassword" type="password" min=1 />
    New password: <input name="password" type="password" min=1 />
    <br></br>
    New age: <input name="age" type="number" min=1 />
    New city: <input name="city" type="text" />
    <input type="submit" value="updateDB" />
</form>
</p>
<br><br>
<form action="${pageContext.request.contextPath}/delete" method="post">
    Id: <input name="id" type=number minlength="1" />
    <input type="submit" value="delete" />
</form>
</body>
</html>
