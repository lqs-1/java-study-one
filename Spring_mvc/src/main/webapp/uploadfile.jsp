
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>uploadfile</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/upload/index1" method="post" enctype="multipart/form-data">
        名称:<input type="text" name="username"><br>
        文件:<input type="file" name="upload"><br>
        <input type="submit" value="submit">
    </form>

</body>
</html>
