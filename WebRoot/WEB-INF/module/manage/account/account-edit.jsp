<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>用户修改</title>
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body"><center>
	<form name="account" action="${pageContext.request.contextPath}/account/${account.id }" method="post">
		<s:hidden name="_method" value="put" />
		<s:include value="../../../common/msg.jsp"/>
		<table class="table" width="70%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th class="rth">用户名称:</th>
				<td class="ltd">
					<input type="hidden" name="account.id" value="${account.id}"/>
					<input type="text" name="account.username" value="${account.username }" readonly="readonly" style="width:160px"/>
				</td>
			</tr>
			<tr class="th">
				<th class="rth">电子邮箱:</th>
				<td class="ltd">
					<input type="text" name="account.email" value="${account.email}" autocomplete="off" style="width:160px"/>
				</td>
			</tr>
			<tr class="th">
				<th class="rth">密码修改:</th>
				<td class="ltd">
					<select name="modifyPassword" onChange="common.componetEnable(this.value, texp)" style="width:160px">
						<option value="true">不修改密码</option>
						<option value="false">修改此密码</option>					
					</select>
				</td>
			</tr>
			<tr class="th" id="texp" style="display:none">
				<th class="rth">新 密 码:</th>
				<td class="ltd">
					<input type="hidden" name="originalPassword" value="${account.password}" style="width:160px"/>
					<input type="password" id="disElemId" name="account.password" style="width:160px"/>
				</td>
			</tr>
			<tr class="th">
				<th class="rth">用户状态:</th>
				<td class="ltd">
					<s:if test="account.accountState">
						<input type="radio" value="false" name="account.accountState"/> 激活
						<input type="radio" checked="checked" value="true" name="account.accountState"/> 锁定
					</s:if>
					<s:else>
						<input type="radio" checked="checked"  value="false" name="account.accountState"/> 激活
						<input type="radio" value="true" name="account.accountState"/> 锁定
					</s:else>
				</td>				
			</tr>
			<tr class="th">
				<th class="rth">管理员:</th>
				<td class="ltd"> 
					<input type="checkbox" value="true" ${account.admin?'checked':''} id="account.admin" name="account.admin"/> 超级管理员
				</td>				
			</tr>
			<tr class="th">
				<th colspan="2">
					<input class="button" type="submit" value="修 改"/>
					<input class="button" type="reset" value="重 写"/>
					<input class="button" type="button" value="返 回" onclick="history.back()"/>
				</th>
			</tr>		
		</table>
	</form>	
	</center>
	
	<script type="text/javascript">
		function handleTransfer(self, disElem){
			alert(self.value);
			if(self.value){
				$("#"+self.id).hide();
				$("#"+disElem).show();
			}
			else{
				$("#"+disElem).hide();
			}
		}
	</script>
	
	</body>
		
</html>
