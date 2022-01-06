<%--
  Created by IntelliJ IDEA.
  User: lqs
  Date: 2022/1/6
  Time: 上午9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/get/index4">
        <input type="text" name="listUser[0].username"><br>
        <input type="text" name="listUser[0].age"><br>
        <input type="text" name="listUser[1].username"><br>
        <input type="text" name="listUser[1].age"><br>
        <input type="submit" value="submit">

    </form>
</body>
</html>
