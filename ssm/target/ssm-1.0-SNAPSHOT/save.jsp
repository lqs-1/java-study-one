
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/user/save">
    <input type="text" name="id"><br>
    <input type="text" name="name"><br>
    <input type="text" name="money"><br>
    <input type="submit" value="保存"><br>
</form>

</body>
</html>
