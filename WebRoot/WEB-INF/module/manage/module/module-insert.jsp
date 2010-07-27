<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>模块添加</title>		
		<jsp:include page="../../../common/resource.jsp"/>
	</head>

	<body class="body" onload="treeNode.readData()"><center>
	<form name="module" action="${pageContext.request.contextPath}/module" method="post">
		<s:hidden name="_method" value="put" />
		<table class="table" width="70%" cellSpacing="1" cellPadding="4">
			<tr class="th">
				<th align="center" colspan="2">模块添加</th>
			</tr>
			<tr class="th">
				<th align="right">父模块名称:</th>
				<td class="ltd">
					<select id="changeParent" name="module.parent.id"  style="width:150px">
						<option value="-1">请选择</option>
					</select>
				</td>
			</tr>
			<tr class="th">
				<th align="right">模块名称:</th>
				<td class="ltd">
					<input type="text" size="12" name="module.label" style="width:150px"/>
				</td>
			</tr>
			<tr class="th">
				<th align="right">模块地址:</th>
				<td class="ltd">
					<input type="text" size="12" name="module.action" style="width:150px"/>
				</td>
			</tr>
			<tr class="th">
				<th align="right">叶子节点:</th>
				<td class="ltd">
					<select name="module.leaf" onChange="common.componetEnable(this.value,texp)" style="width:150px">
						<option value="false"> 非叶子节点</option>
						<option value="true"> 叶子节点</option>					
					</select>
				</td>				
			</tr>
			<tr class="th" id="texp" style="display:table-row">
				<th align="right">是否展开:</th>
				<td class="ltd">
                    <select name="module.expanded" title="默认情况下此节点是否展开" style="width:150px">
						<option value="false"> 不展开</option>
						<option value="true"> 展 &nbsp;开</option>
					</select>
				</td>
			</tr>
			<tr class="th">
				<th align="right">模块图片:</th>
				<td class="ltd">
					<img id="mIcon" name="mIcon" onClick="common.promptLocking(bgCompName,disCompName)" src='${pageContext.request.contextPath}/images/folder_16_pad.gif' title="上传模块图片" width="16" height="16"/>
					<input type="hidden" id="module.icon" name="module.icon" value='folder_16_pad.gif'/>
				</td>
			</tr>
			<tr class="th">
				<th colspan="2">
					<input class="button" id="moduleInsertButton" disabled="disabled" type="submit" value="添 加"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="button" type="reset" value="重 写"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="button" type="button" value="返 回" onclick="history.back()"/>
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
