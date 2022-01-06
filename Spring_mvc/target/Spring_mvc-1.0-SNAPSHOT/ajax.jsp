<%--
  Created by IntelliJ IDEA.
  User: lqs
  Date: 2022/1/6
  Time: 上午9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
    <script>
        var userlist = [];
        userlist.push({username: "lqs", age: 12});
        userlist.push({username: "liqisong", age: 20})

        $.ajax({
            method: "post",
            url: "${pageContext.request.contextPath}/get/index5",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(userlist),
            success: function (e) {
                console.log(e[0].username)
            }
        })

    </script>
</head>
<body>

</body>
</html>
