<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>用户组角色设置</title>
		<jsp:include page="../../../common/resource.jsp"/>
		<style type="text/css">
		<!--
			table{
				font-size:12pt;
				color:red;
				text-align:left;
				LINE-HEIGHT: 25px;
				background-color:#E1E1E1;
			}
		-->
		</style>
	</head>

	<body class="body"><br/><br/><br/><center>
		<form action="${pageContext.request.contextPath}/user-group-account/${id }" method="post">
		<input type="hidden" name="_method" value="put"/>
		<input type="hidden" id="chooseValues" name="chooseValues" size="100" value=""/>
		<div style="#AAEE88;width:550px;height:300px;">
			<s:optiontransferselect
			    size="15"		
				doubleSize="15"				
				cssStyle="width:150px;background:#E1E1E1;height:250px"
				doubleCssStyle="width:150px;background:#E1E1E1;height:250px"			
			    id="accounts"
			    name="accounts" 
			    list="sourceMap"
			    doubleList="targetMap"
			    doubleId="userGroupAccounts"  
			    doubleName="userGroupAccounts"               
				leftUpLabel="上 移"  
			    rightUpLabel="上 移" 
			    leftTitle="可选用户"
			    leftDownLabel="下 移"
			    rightTitle="已选用户"  
			    rightDownLabel="下 移"      
			    selectAllLabel="选择所有"  
			    addToLeftLabel="向左移动"
			    addToRightLabel="向右移动"
			    addAllToLeftLabel="全部向左移动"
			    addAllToRightLabel="全部向右移动"   
			    allowSelectAll="false"   
			    allowUpDownOnLeft="false"
			    allowUpDownOnRight="false" 
			    ondblclick="moveSelectedOptions(document.getElementById('accounts'), document.getElementById('userGroupAccounts'), false, '');"
			    doubleOndblclick="moveSelectedOptions(document.getElementById('userGroupAccounts'), document.getElementById('accounts'), false, '');"
			   /> <br/>
	 		<input type="submit" value="提交" onclick="account.choose('userGroupAccounts','chooseValues')"/>
	 		<input type="button" value="返回" onclick="history.back()"/>
		</div>
		</form>
		</center>
	</body>
	
</html>

