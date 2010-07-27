<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>用户添加</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><center>
	<form name="account" action="${pageContext.request.contextPath}/account" method="post">
		<s:include value="../../../common/msg.jsp"/>
		<table class="table" width="80%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th class="rth">用户名称:</th>
				<td class="ltd">
					<input type="text" name="account.username" value="${account.username}" autocomplete="off" style="width:160px"/>
				</td>
			</tr>
			<tr class="th">
				<th class="rth">用户密码:</th>
				<td class="ltd">
					<input type="password" name="account.password" autocomplete="off" style="width:160px"/>
				</td>
			</tr>
			<tr class="th">
				<th class="rth">电子邮箱:</th>
				<td class="ltd">
					<input type="text" name="account.email" value="${account.email}" style="width:160px"/>
				</td>
			</tr>
			<tr class="th">
				<th class="rth">用户状态:</th>
				<td class="ltd">
					<input type="radio" checked="checked" value="false" id="account.accountState" name="account.accountState"/> 激活
					<input type="radio" value="true" id="account.accountState" name="account.accountState"/> 锁定
				</td>				
			</tr>
			<tr class="th">
				<th class="rth">管理员:</th>
				<td class="ltd">
					<input type="checkbox" value="true" id="account.admin" name="account.admin"/> 超级管理员
				</td>				
			</tr>
			<tr class="th">
				<th colspan="2">
					<input class="button" type="submit" value="添 加"/>
					<input class="button" type="reset" value="重 写"/>
					<input class="button" type="button" value="返 回" onclick="history.back()"/>
				</th>
			</tr>		
		</table>
	</form>	 
	</center>
	</body>
		
</html>
