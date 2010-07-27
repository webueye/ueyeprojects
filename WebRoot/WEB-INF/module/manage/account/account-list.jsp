<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>用户管理</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><center>
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th colspan="6" >
					<div align=right style="margin-right:10px;">
					<a href="${pageContext.request.contextPath}/account/new">
						添加用户
					</a>
					</div>
				</th>
			</tr>
			<tr class="th">
				<th>用户名称</th>
				<th>电子邮件</th>
				<th>用户状态</th>
				<th>角色设置</th>
				<th>组设置</th>
				<!--  
				<th width="10%">密码修改</th>
				-->
				<th>编 辑</th>
			</tr>
		<s:iterator id="account" value="accountList">
			<tr>
				<td class="td">${account.username }</td>
				<td class="td">${account.email }</td>
				<td class="td">
					<a href="${pageContext.request.contextPath}/account/${account.id}/state">
						${account.accountState?'已锁定':'已激活'}
					</a>
				</td>
				<td class="td">
					<a href="${pageContext.request.contextPath}/account-role?id=${account.id }">
						角色设置
					</a>
				</td>
				<td class="td">
					<a href="${pageContext.request.contextPath}/account-group?id=${account.id }">
						组设置
					</a>
				</td>
				<!-- 
				<td class="td">
					<a href="${pageContext.request.contextPath}/account/${account.id }">
						密码修改
					</a>
				</td>
				-->
				<td class="td">
					<a href="${pageContext.request.contextPath}/account/${account.id }" title="修改">
						<img border="0" src="${pageContext.request.contextPath}/images/edit.gif"/></a>
					|<a href="${pageContext.request.contextPath}/account/${account.id }?_method=delete" title="删除">
						<img border="0" src="${pageContext.request.contextPath}/images/delete.gif"/>
					</a>
				</td>
			</tr>
		</s:iterator>	
		</table>
		
		</center>		 
	</body>
	
	
</html>
