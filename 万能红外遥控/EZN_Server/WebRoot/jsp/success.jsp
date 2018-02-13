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
	<form action="query.action" method="post">
		<table align="center">
			<tr>
				<td style="vertical-align: middle;">USERNAME
					<input type="text" style="width: 300px;height: 30px;font-size: 16px" name="queryText"  value="${searchText }"/>
					<input type="submit" value="Query" style="width: 100px;height: 30px;font-size: 16px"/>
					<input type="button" value="Add" style="width: 100px;height: 30px;font-size: 16px" 
						onclick="window.location.href='add.action?param=0'"/>
				</td>
			</tr>
		</table>
	</form>
	
	<table align="center" border="1" cellpadding="0" cellspacing="0" bordercolor="#3366cc">
		<tr align="center" bgcolor="#3399cc" height="26px">
			<td width="100">NO.</td>
			<td width="160">UNAME</td>
			<td width="70">SEX</td>
			<td width="150">TELPHONE</td>
			<td width="300">ADDRESS</td>
			<td width="120">EDIT/DELETE</td>
		</tr>
		
		<c:forEach var="user" items="${users }">
			<tr align="center"  height="24px">
				<td width="100">${user.id}</td>
				<td width="160">${user.uname}</td>
				<td width="70">${user.sex }</td>
				<td width="150">${user.telphone }</td>
				<td width="300">${user.address}</td>
				<td width="120">
					<a href="edit.action?param=0&id=${user.id}">编辑</a>&nbsp;&nbsp;
					<a href="delete.action?id=${user.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
