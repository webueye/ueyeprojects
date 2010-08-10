<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<% response.setStatus(200); %>
<html>
	<body>
		<div align="center" style="margin-top: 100px;">
			<font color="red">
				 <%=request.getAttribute("javax.servlet.error.message")%>
			</font>
		</div>
	</body>
</html>
