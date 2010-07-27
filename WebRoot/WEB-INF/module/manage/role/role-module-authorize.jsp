<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ueye" uri="ueye" %>
<html>
	<head>
		<title>角色模块授权</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><br/><center>
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th colspan="2" align="left"><strong>&nbsp;&nbsp;模块列表</strong></th>
		  	</tr>
		</table><br/>
		<form action="${pageContext.request.contextPath}/role-module" method="post">
		<input type="hidden" name="id" value="${id }"/>
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<ueye:cmsForEach var="module" items="roleModuleList">
				<tr class="th">
					<td align="left">	
						<table bgcolor="#EFF3FF" border="0" cellpadding="0" cellspacing="0">
							<tr class="ltd">
							<c:forEach var="row" items="${module.rows}">
			 					<td bgcolor="#EFF3FF" width="18px"></td>
			 				</c:forEach>
								<td bgcolor="#EFF3FF" align="right">
									<c:if test="${!module.leaf}">					
										<c:if test="${module.expanded}">
											<input type="checkbox" ${module.checked } id="${module.parent.id }_${module.id }" value="${module.id }" name="function" onclick="module.cascadeCheck(true,this,true)"/><a href="${pageContext.request.contextPath}/role-module/${module.id }/expand?role.id=${id}"><img border="0" alt="折 叠" src="${pageContext.request.contextPath}/images/minus.jpg"></a>
										</c:if>
										<c:if test="${!module.expanded}">
											<input type="checkbox" ${module.checked } id="${module.parent.id }_${module.id }" value="${module.id }" name="function" onclick="module.cascadeCheck(true,this,true)"/><a href="${pageContext.request.contextPath}/role-module/${module.id }/expand?role.id=${id}"><img border="0" alt="展 开" src="${pageContext.request.contextPath}/images/plus.jpg"></a>
										</c:if>
									</c:if>
									<c:if test="${module.leaf}">
										<input type="checkbox" ${module.checked } id="${module.parent.id }_${module.id }" value="${module.id }" name="function" onclick="module.cascadeCheck(true,this,true)"/><img border='0' title="叶子节点" src="${pageContext.request.contextPath}/images/leaf.gif"/>
									</c:if>
								</td>
								<td colspan="${10-module.level}" bgcolor="#EFF3FF" align="left">					
									&nbsp;${module.label}
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</ueye:cmsForEach>
			<tr class="th">
				<td>
					<input type="submit" class="button" value=" 提 交 "/>&nbsp;&nbsp;
					<input type="button" class="button" value=" 返 回 " onclick="window.location='${pageContext.request.contextPath}/role'"/>
				</td>
			</tr>
		</table> 
		</form>
		</center>
	</body>	
	
</html>
