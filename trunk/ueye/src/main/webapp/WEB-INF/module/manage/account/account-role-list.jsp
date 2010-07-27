<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>用户所拥有的角色列表</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><center>
	<form>
		
		<table class="table" width="70%" cellSpacing="1" cellPadding="4">
			<tr>
				<th colspan="4"  class="th">
					<div align=right style="margin-right:10px;">
						<a href="${pageContext.request.contextPath}/account-role/${id }/edit">
							编辑列表
						</a>
					</div>
				</th>
			</tr>
			<tr class="th">
				<th>选择</th>
				<th >角色名称</th>
				<th >角色描述</th>
				<th>删 除</th>
			</tr>
		<c:forEach var="role" items="${accountRoleList}">
			<tr>
				<td class="td"><input type="checkbox" value=""/></td>
				<td class="td">
					${role.roleName }
				</td>
				<td class="td">
					${role.description }
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/account-role/${role.id }?account.id=${id }&_method=delete">
						<img border="0" src="${pageContext.request.contextPath}/images/delete.gif"/>
					</a>
				</td>
			</tr>
		</c:forEach>
			<tr class="th">
				<th  align="center" colspan="4" >
					<input type="button" value=" 返 回 " class="button" onclick="window.location='${pageContext.request.contextPath}/account'"/>
				</th>
			</tr>	
		</table>
		</form>	
		</center>	 
	</body>
	
	
</html>
