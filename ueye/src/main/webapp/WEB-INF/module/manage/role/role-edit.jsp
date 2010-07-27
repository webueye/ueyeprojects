<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>角色编辑</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><br/><center>
	<form name="role" action="${pageContext.request.contextPath}/role/${role.id }" method="post">
		<s:hidden name="_method" value="put" />
		<table class="table" width="70%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th class="rth">角色名称:</th>
				<td class="ltd">
					<input type="hidden" name="role.id" value="${role.id }"/>
					<input type="text" size="15" name="role.roleName" value="${role.roleName }" readonly="readonly"/>
				</td>
			</tr>
			<tr class="th">
				<th class="rth">角色描述:</th>
				<td class="ltd">
					<input type="text" size="15" name="role.description" value="${role.description }"/>
				</td>				
			</tr>
			<tr class="th">
				<th colspan="2">
					<input class="button" type="submit" value="修 改"/>
					<input class="button" type="reset" value="重 写"/>
					<input class="button" type="button" value="返 回" onclick="history.back()"/>
				</th>
			</tr>		
		</table>
	</form>
	</center>	 
	</body>
		
</html>
