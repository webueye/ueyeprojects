<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>模块修改</title>
		<jsp:include page="../../../common/resource.jsp"/>

</head>

	<body class="body"><center>
	
	<form name="module" action="${pageContext.request.contextPath}/module/${module.id }" method="post">
		<s:hidden name="_method" value="put" />
		<table class="table" width="70%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th >父模块名称:</th>
				<td class="ltd">
					<input size="12" type="hidden" id="module.parent.id" name="module.parent.id" value="${module.parent.id }"/>
					<input size="12" type="text" disabled="disabled" id="module.parent.label" name="module.parent.label" value="${module.parent.label }" style="width:150px"/>
					<span style="display:none" id="parentDiv">
						<select id="changeParent" onChange="treeNode.parentDivHide()">
							<option value="default">--请选择--</option>
						</select>
					</span>					
					&nbsp;&nbsp;
					<img onClick="treeNode.readData()" title="修改父节点" border="0" alt="修改" src="${pageContext.request.contextPath}/images/edit.gif">
										
				</td>
    		</tr>
			<tr class="th">
				<th >模块名称:</th>
				<td class="ltd">
					<input type="hidden" name="module.id" value="${module.id }"/>
					<input type="hidden" name="parentId" value="${module.parent.id }"/>
					<input size="12" type="text" name="module.label" value="${module.label }" style="width:150px"/>
				</td>
			</tr>
			<tr class="th">
				<th>模块地址:</th>
				<td class="ltd">
					<input size="12" type="text" name="module.action" value="${module.action }" style="width:150px"/>
				</td>
			</tr>
			<tr class="th">
				<th>模块序号:</th>
				<td class="ltd">
					<input size="12" type="text" name="module.orderValue" value="${module.orderValue }" style="width:150px"/>
				</td>
			</tr>
			<tr class="th">
				<th>叶子节点:</th>
				<td class="ltd">
					<select name="module.leaf" onChange="common.componetEnable(this.value,texp)" style="width:150px">
						<option value="true">叶子节点</option>
						<s:if test="!module.leaf">
							<option value="false" selected="selected">非叶子节点</option>
						</s:if>
						<s:else>
							<option value="false">非叶子节点 </option>
						</s:else>
					</select>
				</td>				
			</tr>
			<tr class="th" id="texp" style="display:table-row">
				<th>是否展开:</th>
				<td class="ltd">
					<script type="text/javascript">
						common.componetEnable('${module.leaf}',texp);
					</script>
                    <select name="module.expanded" title="默认情况下此节点是否展开" style="width:150px;z-index:1;">
						<option value="false">不展开</option>
						<s:if test="module.expanded">
							<option value="true" selected="selected">展 &nbsp;开</option>
						</s:if>
						<s:else>
							<option value="true">展 开</option>
						</s:else>
					</select>
				</td>
			</tr>			
			<tr class="th">
				<th>模块图片:</th>
				<td class="ltd">
					<img id="mIcon" name="mIcon" onClick="common.promptLocking(bgCompName,disCompName)" src='${pageContext.request.contextPath}/images/${module.icon }' title="修改模块图片" width="16" height="16"/>
					<input type="hidden" id="module.icon" name="module.icon" value='${module.icon }'/>
				</td>
			</tr>
			<tr class="th">
				<th colspan="2">
					<input class="button" type="submit" value="修 改"/>&nbsp;
					<input class="button" type="reset" value="重 写"/>&nbsp;
					<input class="button" type="button" value="返 回" onClick="history.back()"/>
				</th>
			</tr>		
		</table>
	</form>	
    
    <!--********************************************************************-->
     
     <div id="bgCompName"  class="bgCompDiv"></div>    
     <div id="disCompName" class="disCompDiv" >
        <table width="450" border="0" cellpadding="0" cellspacing="0" >
            <tr>
                <td class="promptTHBG" height="27" height="27" valign="middle">
                    [&nbsp;图&nbsp;片&nbsp;上&nbsp;传&nbsp;]                
                </td>
            </tr>
            <tr>
              <td height="130" align="center">
              	<img id="loading" src="${pageContext.request.contextPath}/images/loading.gif" style="display:none;"/>
                <input type="file" id="moduleIcon" name="moduleIcon"/>   <br/><br/>
                <input type="button" class="button" value=" 上 传 " onclick="return treeNode.moduleIconUpload(mIcon,'module.icon')">&nbsp;&nbsp;
                <input type="button" class="button" value=" 取 消 " onClick="common.promptUnLocking(bgCompName,disCompName)"></td>
            </tr>
        </table>
      </div>    
    <!--********************************************************************-->
    </center>
</body>
		
</html>
