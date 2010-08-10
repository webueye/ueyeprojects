<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<% response.setStatus(200); %>
<html>
	
	<body>
		
		<div align="center" style="margin-top: 20px;">
			<div>
		      	<div style="padding:140px 0 0 30px">
		        	<p>您访问的地址不存在，请确认您输入的 URL 地址 ......</p>
		      	</div>
		    </div>
		
		    <div style="padding:20px 0 0 400px">
		      	<a href="javascript:void(0)" onclick="history.back()"><img src='${pageContext.request.contextPath}/images/discover.gif' border='0'></a>
		    </div>
		
		</div>
		
	</body>
	
</html>

