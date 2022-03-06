<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/equipment.css">
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
</head>
<% request.setCharacterEncoding("utf-8"); %>
<body>
<%-- <form action="${pageContext.request.contextPath }/user/login.do" method="post">
	userName:<input type="text" name="userName" value="${user.userName }"/><br/>
	password:<input type="password" name="password" value="${user.password }"><br/>
	<input type="submit" value="login"/><font color="red">${errorMsg }</font>
</form> --%>

<div class="grad">

			<div class="title">成都工业职业技术学院</div>
            <div class="title">信息工程分院(新华三产业学院)——SSM框架实训项目</div>
			<div class="logindiv">
				<form class="bs-example bs-example-form" role="form" action="${pageContext.request.contextPath }/user/login" method="post">
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span class="glyphicon glyphicon-user"></span></span>
						<input type="text" class="form-control" name="username" placeholder="用户名" value="${user.username }">
					</div>
					<br>
					<div class="input-group input-group-lg">
						<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
						<input type="password" class="form-control" name="password" placeholder="请输入密码" value="${user.password }">
					</div>
					<br>
					<div style="color:red">${errorMsg} <p>${msg}</p> </div>
					<!-- <div class="hint" id="hint">
							用户名或密码错误
					</div> -->
					<div class="input-group input-group-lg" style="margin: 0 auto;width:100%;">
						<button type="submit" class="buttonlarg btn btn-primary btn-lg" style="width:100%;">登陆</button>
					</div>
				</form>
			</div>
	        <footer class="navbar-fixed-bottom text-center">
		        <div class="container">
		          信息工程学院移动应用开发团队&reg;2020-2022 版权所有
		         </div>
		    </footer>


</div>
</body>
</html>