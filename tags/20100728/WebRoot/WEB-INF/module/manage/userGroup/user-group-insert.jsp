<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>创建用户组</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><br/><center>
	<form name="userGroup" action="${pageContext.request.contextPath}/user-group" method="post">
		<div>
			<font color="red">
				<s:actionmessage/>				
			</font>
		</div>
		<table class="table" width="70%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th class="rth">用户组名称:</th>
				<td class="ltd">
					<input type="text" size="15" name="userGroup.groupName" value="${userGroup.groupName }"/>
				</td>
			</tr>
			<tr class="th">
				<th class="rth">用户组描述:</th>
				<td class="ltd">
					<input type="text" size="15" name="userGroup.groupDesc" value="${userGroup.groupDesc }"/>
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
