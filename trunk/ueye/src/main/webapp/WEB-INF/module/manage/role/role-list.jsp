<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>角色列表</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><br/><center>
		<table class="table" width="90%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th colspan="5" >
					<div align=right style="margin-right:10px;">
					<a href="${pageContext.request.contextPath}/role/new">
						添加角色
					</a>
					</div>
				</th>
			</tr>
			<tr class="th">
				<th>角色名称</th>
				<th colspan="2">授权</th>
				<th>编 辑</th>
				<th>删 除</th>
			</tr>
		<s:iterator id="role" value="roleList">
			<tr>
				<td class="td">${role.roleName }</td>
				<td class="td"><a href="${pageContext.request.contextPath}/role-module/${role.id }">模块授权</a></td>
				<td class="td">
					<a href="${pageContext.request.contextPath}/role-function/${role.id }">
					功能授权
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/role/${role.id }">
						<img border="0" src="${pageContext.request.contextPath}/images/edit.gif"/>
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/role/${role.id }/destroy">
						<img border="0" src="${pageContext.request.contextPath}/images/delete.gif"/>
					</a>
				</td>
			</tr>
		</s:iterator>
		<c:if test="${page.pageCount>0 }">	
			<tr>
				<td colspan="5" class="page">
					<div align=right style="width:100%">
						<jsp:include page="../../../common/page.jsp">
							<jsp:param name="actionURL" value="role"/>
						</jsp:include>
					</div>
				</td>
			</tr>
		</c:if>
		</table>	
		</center>	 
	</body>
	
	
</html>
