<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>用户设置</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body">&nbsp;&nbsp;<br/><center>
	<form>
		
		<table class="table" width="70%" cellSpacing="1" cellPadding="4">
			<tr>
				<th colspan="5" class="th">
					<div align=right style="margin-right:10px;">
						<a href="${pageContext.request.contextPath}/user-group-account/${id }/edit">
							编辑列表
						</a>
					</div>
				</th>
			</tr>
			<tr class="th">
				<th>选择</th>
				<th>用户名称</th>
				<th>用户邮箱</th>
				<th>用户状态</th>
				<th>删 除</th>
			</tr>
		<c:forEach var="account" items="${page.data}">
			<tr>
				<td class="td"><input type="checkbox" value=""/></td>
				<td class="td">${account.username }</td>
				<td class="td">${account.email }</td>
				<td class="td">${account.accountState?'已锁定':'已激活' }</td>				
				<td>
					<a href="${pageContext.request.contextPath}/user-group-account/${account.id }?userGroup.id=${id }&_method=delete">
						<img border="0" src="${pageContext.request.contextPath}/images/delete.gif"/>
					</a>
				</td>
			</tr>
		</c:forEach>
			<tr class="th">
				<th  align="center" colspan="5" >
					<input type="button" value=" 返 回 " class="button" onclick="window.location='${pageContext.request.contextPath}/user-group'"/>
				</th>
			</tr>	
		</table>
		</form>	
		</center>	 
	</body>
	
	
</html>
