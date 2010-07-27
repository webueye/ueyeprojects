<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ueye" uri="ueye" %>

<html>
	<head>
		<title>功能列表</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><center>
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th colspan="2" align="left"><strong>&nbsp;&nbsp;功能列表</strong></th>
		  	</tr>
		</table><br/>
		<form id="insertFunctionForm" name="insertFunctionForm" action="${pageContext.request.contextPath }/function" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="table" width="100%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th align="center">模块列表</th>
		  		<th align="center">功能列表</th>
		  		<th align="center">功能设置</th>
		  	</tr>
			<ueye:cmsForEach var="module" items="functionList" >
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
											<a href="${pageContext.request.contextPath}/function/${module.id }/expand?role.id=${id}"><img border="0" alt="折 叠" src="${pageContext.request.contextPath}/images/minus.jpg"></a>
										</c:if>
										<c:if test="${!module.expanded}">
											<a href="${pageContext.request.contextPath}/function/${module.id }/expand?role.id=${id}"><img border="0" alt="展 开" src="${pageContext.request.contextPath}/images/plus.jpg"></a>
										</c:if>
									</c:if>
									<c:if test="${module.leaf}">
										<img border='0' title="叶子节点" src="${pageContext.request.contextPath}/images/leaf.gif"/>
									</c:if>
								</td>
								<td colspan="${10-module.level}" bgcolor="#EFF3FF" align="left">					
									&nbsp;${module.label}
								</td>
							</tr>
						</table>
					</td>
					<td class="ltd">
						<c:forEach var="function" items="${module.functions}">
							<input type="checkbox" name=""/>${function.functionName }&nbsp;
						</c:forEach>
					</td>
					<td>
						<a href="#" onClick="ueyeFunction.pop(bgCompName,disCompName,'module.id','${module.id }','label','${module.label }')">
							功能设置
						</a>
						<a href="#" onClick="ueyeFunction.functionList(bgCompNameFunction,disCompNameFunction,'module.id','${module.id }','label','${module.label }','header')">
							详细设置
						</a>
					</td>
				</tr>
			</ueye:cmsForEach>
			
		</table> 
		
		
	
	<!--********************************************************************-->
     
     <div id="bgCompName"  class="bgCompDiv"></div>    
     <div id="disCompName" class="disCompDiv" >
        <table width="450" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
                <th height="27" height="27" valign="middle" colspan="2" class="th">
                    [&nbsp;添&nbsp;加&nbsp;功&nbsp;能&nbsp;]                
                </th>
            </tr>
            <tr>
            	<td>&nbsp;</td><td>&nbsp;</td>
            </tr>
            <tr>
            	<td class="rtd">模块名称：</td>
            	<td class="ltd">
            		<input type="hidden" id="module.id" name="module.id" value="0"/>
            		<input type="text" id="label" name="label" value="" readonly="readonly"/>
            	</td>
            </tr>
            <tr>
            	<td class="rtd">功能名称：</td>
            	<td class="ltd"><input type="text" id="funModel.functionName" name="funModel.functionName"/></td>
            </tr>
            <tr>
            	<td class="rtd">功能代码：</td>
            	<td class="ltd"><input type="text" id="funModel.functionCode" name="funModel.functionCode"/></td>
            </tr>
            <tr>
            	<td class="rtd">功能描述：</td>
            	<td class="ltd"><input type="text" id="funModel.functionDesc" name="funModel.functionDesc"/></td>
            </tr>
            <tr>
              <td align="center" colspan="2">
              	<img id="loading" src="${pageContext.request.contextPath}/images/loading.gif" style="display:none;"/><br/>
                <input type="submit" class="button" value=" 提 交 " onclick="return ueyeFunction.insertFunction()">&nbsp;&nbsp;
                <input type="button" class="button" value=" 取 消 " onClick="common.promptUnLocking(bgCompName,disCompName)"><br/><br/>
              </td>
            </tr>
        </table>
      </div>   
      </form>
    <!--********************************************************************-->
	<!-- ######################### 功能列表  ######################## -->	
	
	 <div id="bgCompNameFunction"  class="bgCompDiv"></div>    
     <div id="disCompNameFunction" class="disCompDiv" >
        <table width="480" border="0" cellpadding="0" cellspacing="0">            
            <tr>
                <th class="th"></th>
                <th height="27" valign="middle" colspan="2" class="th">
                    <div id="header" style="font-size:18px">[&nbsp;功&nbsp;能&nbsp;列&nbsp;表&nbsp;]</div>                
                </th>
                <th class="th">
                	<div align=right style="margin-right:5px;">
						<a href="#" onClick="ueyeFunction.closeFunctionList(bgCompNameFunction,disCompNameFunction)">
							<img border="0" src="${pageContext.request.contextPath }/images/delete.gif"/>
						</a>
					</div>
                </th>
            </tr>
            <tr id="trHead">
            	<th class="td" width="130">功能名称</th>
            	<th class="td" width="130">功能代码</th>
            	<th class="td" width="130">功能描述</th>
            	<th class="td" width="120"> 操 作 </th>
            </tr>
        </table>
        <table id="functionListTable" width="480" border="0" cellpadding="0" cellspacing="0">
        	
        </table>
        <table width="480" border="0" cellpadding="0" cellspacing="0">
        	<tr><td>&nbsp;</td></tr>
        </table>
      </div>   
	
		
		</center>
	</body>	
	
</html>
