<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>

	<form action="queryid.action" method="post">
		name<input type="text" name="queryname"><input type="submit"
			value="ok">
	</form>
	
	<table align="center" border="0" cellpadding="0" cellspacing="1" bordercolor="#3366cc">
		<tr align="center" bgcolor="#3399cc" height="26px">
			<td width="100">id列</td>
		</tr>

		<c:forEach var="userinfo" items="${users }">
			<tr align="center" height="24px">
					<td width="100">${userinfo.id}</td>
			</tr>
		</c:forEach>

	</table>



</body>
</html>
