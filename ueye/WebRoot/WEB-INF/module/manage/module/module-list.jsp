<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ueye" uri="ueye" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>模块管理</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><center>
		
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<tr>
				<th  class="th" colspan="4" align="right">
					<div align=right style="margin-right:10px;">
					<a href="${pageContext.request.contextPath}/module!forward">
						模块添加
					</a>
					</div>
				</th>
			</tr>
			<tr >
				<th class="th">模块名称</th>
				<th class="th">添加子节点</th>
				<th class="th">修 改</th>
				<th class="th">删 除</th>
			</tr>
			<ueye:cmsForEach var="module" items="moduleList">
			<tr>
				<td align="left" height="25">
					<table>
						<tr>							
							<c:forEach var="row" items="${module.rows}">
			 					<td width="18px"></td>
			 				</c:forEach>
							<td>
												
							<c:if test="${!module.leaf}">						
									<c:if test="${module.expanded}">
										<a href="${pageContext.request.contextPath}/module/${module.id }/expand">
											<img border='0' width="16" height="13" title="折 叠" src="${pageContext.request.contextPath}/images/folder_open.gif"/>
										</a>
									</c:if>
									<c:if test="${!module.expanded}">
										<a href="${pageContext.request.contextPath}/module/${module.id }/expand">
											<img border='0' width="16" height="13" title="展 开" src="${pageContext.request.contextPath}/images/folder.gif"/>
										</a>
									</c:if>
							</c:if>
					
							<c:if test="${module.leaf}">
								<img border='0' title="叶子节点" src="${pageContext.request.contextPath}/images/blank.gif"/>
							</c:if>
					
							</td>
							<td colspan="${10-module.level}" style="font-size:10pt;" title="链接地址: ${module.action }">					
								${module.label}
							</td>
						</tr>
					</table>
				</td>
				<td> 
					<c:if test="${module.leaf}">
						<img style='filter:gray;' border='0' title='此节点为叶子节点，请修改后在添加' src='${pageContext.request.contextPath}/images/insert.gif'/>
					</c:if>
					<c:if test="${!module.leaf}">
						<a href='${pageContext.request.contextPath}/module/new?id=${module.id}'><img border='0' title='添 加' src='${pageContext.request.contextPath}/images/insert.gif'/></a>
					</c:if>
				</td>
				<td class="td">
					<a href='${pageContext.request.contextPath}/module/${module.id}'><img border='0' title='修 改' src='${pageContext.request.contextPath}/images/edit.gif'/></a>					
				</td>
				<td class="td">
					<a href='${pageContext.request.contextPath}/module/${module.id}?_method=delete'><img border='0' title='删 除' src='${pageContext.request.contextPath}/images/delete.gif'/></a>
				</td>				
			</tr>	
			</ueye:cmsForEach>
		</table>
		</center>	 
	</body>
	
	
</html>
