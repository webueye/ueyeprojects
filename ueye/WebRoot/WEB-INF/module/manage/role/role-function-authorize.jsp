<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ueye" uri="ueye" %>
<html>
	<head>
		<title>角色功能授权</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><br/><center>
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th colspan="2" align="left"><strong>&nbsp;模&nbsp;块&nbsp;功&nbsp;能&nbsp;列&nbsp;表</strong></th>
		  	</tr>
		</table><br/>
		<form action="${pageContext.request.contextPath}/role-function" method="post">
		<input type="hidden" name="id" value="${id }"/>
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<ueye:cmsForEach var="module" items="roleFunctionList">
				<tr class="th">
					<td align="left">	
						<table bgcolor="#EFF3FF" border="0" cellpadding="0" cellspacing="0">
							<tr class="ltd">
							<c:forEach var="row" items="${module.rows}">
			 					<td bgcolor="#EFF3FF" width="18px" height="20"></td>
			 				</c:forEach>
								<td bgcolor="#EFF3FF" align="right" height="20"> 
									<c:if test="${!module.leaf}">					
										<c:if test="${module.expanded}">
											<a href="${pageContext.request.contextPath}/role-function/${module.id }/expand?role.id=${id}"><img border="0" alt="折 叠" src="${pageContext.request.contextPath}/images/minus.jpg"></a>
										</c:if>
										<c:if test="${!module.expanded}">
											<a href="${pageContext.request.contextPath}/role-function/${module.id }/expand?role.id=${id}"><img border="0" alt="展 开" src="${pageContext.request.contextPath}/images/plus.jpg"></a>
										</c:if>
									</c:if>
									<c:if test="${module.leaf}">
										<img border='0' title="叶子节点" src="${pageContext.request.contextPath}/images/leaf.gif"/>
									</c:if>
								</td>
								<td colspan="${10-module.level}" bgcolor="#EFF3FF" align="left" height="20">					
									&nbsp;${module.label}
								</td>								
							</tr>
						</table>
					</td>
					<td class="ltd" height="20" width="60%">
						<c:forEach var="function" items="${module.functions}">
							<input type="checkbox" id="moduleFunction" name="moduleFunction" value="${module.id}_${function.id}" ${function.checked }/>${function.functionName }&nbsp;
						</c:forEach>
					</td>		
				</tr>
			</ueye:cmsForEach>
			<tr class="th" >
				<td colspan="2">
					<input type="submit" class="button" value=" 提 交 "/>&nbsp;&nbsp;
					<input type="button" class="button" value=" 返 回 " onclick="window.location='${pageContext.request.contextPath}/role'"/>
				</td>
			</tr>
		</table> 
		</form>
		</center>
	</body>	
	
</html>
