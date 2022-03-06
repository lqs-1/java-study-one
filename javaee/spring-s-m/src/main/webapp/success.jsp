<%--
  Created by IntelliJ IDEA.
  User: lqs
  Date: 2022/3/4
  Time: 下午3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


${user.username}    登录成功 <a href="${pageContext.request.contextPath }/user/logout">注销</a>

</body>
</html>
