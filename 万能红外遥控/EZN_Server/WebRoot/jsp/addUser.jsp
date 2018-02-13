<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>user list page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<form action="add.action" method="post">
		<table width="500" align="center">
			<caption>ADD USER INFORMATION</caption>
			<tr height="26px">
				<td width="150px" align="right">UNAME</td>
				<td width="350px">
					<input type="hidden" name="param" value="1"/>
					<input type="text"  name="user.uname" value="${user.uname }"/>
				</td>
			</tr>
			<tr height="26px">
				<td align="right">PASSWORD</td>
				<td><input type="password"  name="user.password" value="${user.password }"/></td>
			</tr>
			<tr height="26px">
				<td align="right">SEX</td>
				<td>
					<select name="user.sex">
						<option value="male">male</option>
						<option value="female">female</option>
					</select>
				</td>
			</tr>
			<tr height="26px">
				<td align="right">TELPHONE</td>
				<td><input type="text"  name="user.telphone" value="${user.telphone }"/></td>
			</tr>
			<tr height="26px">
				<td align="right">ADDRESS</td>
				<td><input type="text"  name="user.address" value="${user.address }"/></td>
			</tr>
			<tr height="26px">
				<td colspan="2"  align="center" width="500">
					<input type="submit" value="Save"/>
					<input type="button" value="Back" onclick="window.history.back(-1)"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
