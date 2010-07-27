<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>ueye</title>
	</head>

<frameset rows="88,*" cols="*" frameborder="no" border="2" bordercolor="#999999" framespacing="0">
  <frame src="${pageContext.request.contextPath}/main/header.html" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset rows="*" cols="164,*" framespacing="0" frameborder="no" border="0">
    <frame src="${pageContext.request.contextPath}/main/left.jsp" bordercolor="#999999" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="菜单" />
    <frame src="${pageContext.request.contextPath}/main/main.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />
  </frameset>
</frameset>
<noframes><body>
</body>
</noframes>
</html>
