<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	
	<head>				
		<title>Ueye System Manager</title>
		<script language="JavaScript" type="text/javascript">
		  <!--
		    if (window.self != window.top) {
		      window.open(".", "_top");
		    }
		  // -->
		</script>		
	</head>

<body background="${pageContext.request.contextPath}/images/LoginBackgroundTile.gif">

<center>

<!-- Login -->

<form method="POST" action='${pageContext.request.contextPath }/login' name="loginForm">

  	<table border="0" cellspacing="5" background="${pageContext.request.contextPath}/images/LoginBackgroundTile.gif">
  		<tr>
		    <td height="183">
		       <div align="center">
		       		<img src="${pageContext.request.contextPath}/images/Login.jpg" alt="Ueye Content Manage" width="490" height="228">
		       </div>
		    </td>
    	</tr>

	    <tr>
	    	<td background="images/LoginBackgroundTile.gif">
	     		<table width="100%" border="0" cellspacing="2" cellpadding="5">
	     			<tr>
	     				<td colspan="2" align="center">
	     					<font color="#FFFFFF"><s:actionmessage/></font>
	     				</td>
	     			</tr>
	     			<tr>
				      <th align="right">
				        	<font color="#FFFFFF">用户名：</font>
				      </th>
				      <td align="left">
				        	<input type="text" name="account.username" style="width:120px" id="username"/>
				      </td>
    				</tr> <p>
				    <tr>
				      <th align="right">
				       	 	<font color="#FFFFFF">密&nbsp; 码：</font>
				      </th>
				      <td align="left">
				        	<input type="password" name="account.password" style="width:120px" id="password"/>
				      </td>
				    </tr>

				    <tr>
				      <td width="50%" valign="top"> <div align="right"></div> </td>
				      <td width="55%" valign="top">&nbsp;</td>
				     </tr>
				    <tr>
				       <td width="50%" valign="top" align="right">
			               <input type="submit" value=' 登 陆 ' class="button"/>
				       </td>
				       <td width="55%" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;
				          &nbsp;&nbsp;<input type="reset" value=' 重 置 ' class="button"/>
				       </td>
				     </tr>
				  </table><p> &nbsp;
		 	</td>
		  </tr>
	 </table>
</form>

<script language="JavaScript" type="text/javascript">
  <!--
    document.forms["loginForm"].elements["username"].focus()
  // -->
</script>

</body>


</html>
