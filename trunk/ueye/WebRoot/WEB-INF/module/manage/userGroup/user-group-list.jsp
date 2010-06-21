<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>用户组列表</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><br/><center>
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th colspan="6" >
					<div align=right style="margin-right:10px;">
					<a href="${pageContext.request.contextPath}/user-group/new">
						添加用户组
					</a>
					</div>
				</th>
			</tr>
			<tr class="th">
				<th>用户组名称</th>
				<th>用户组描述</th>
				<th>角色设置</th>
				<th>用户设置</th>
				<th>编 辑</th>
				<th>删 除</th>
			</tr>
		<s:iterator id="userGroup" value="page.data">
			<tr>
				<td class="td">${userGroup.groupName }</td>
				<td class="td">${userGroup.groupDesc }</td>
				<td class="td">
					<a href="${pageContext.request.contextPath}/user-group-role?id=${userGroup.id }">
						角色设置
					</a>
				</td>
				<td class="td">
					<a href="${pageContext.request.contextPath}/user-group-account?id=${userGroup.id }">
						用户设置
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/user-group/${userGroup.id }/edit">
						<img border="0" title="修改用户组" src="${pageContext.request.contextPath}/images/edit.gif"/>
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/user-group/${userGroup.id }?_method=delete">
						<img border="0" title="删除用户组" src="${pageContext.request.contextPath}/images/delete.gif"/>
					</a>
				</td>
			</tr>
		</s:iterator>	
		</table>	
		</center>	 
	</body>
	
	
</html>
