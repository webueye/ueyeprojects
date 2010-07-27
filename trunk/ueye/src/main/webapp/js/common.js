var basePath="/ueye";
String.prototype.trim = trim;
/*****************************************
* 函数功能: 去掉字符串左右边的空格包括Tab,用于封装在String对象
******************************************/
function trim() {
	return this.replace(/(^[\s,\t]*)|([\s,\t]*$)/g, "");
}

function getObject(value){
	return document.getElementById(value);
}

/*******************************************
 * 读取 module 的值、父 module 的显示与隐藏、图片的上传、加载的小图片
 *******************************************/
treeNode={
	readData:function(){
		$.ajax({
			type:'POST',
			url:basePath+'/json!module',
			dataType:'json',
			success:function(data){
				if(data){
					$("#parentDiv").show();
					var changeParent=document.getElementById('changeParent');
					var moduleLength=(data.length>0);
					if(!moduleLength){
						document.getElementById('moduleInsertButton').disabled=false;
						return;
					}
					if(moduleLength)
						changeParent.length=0;
					else
						changeParent.length=1;
					for(var i=0;i<data.length;i++){
						var op=new Option(data[i].label,data[i].id);
						op.innerHTML=data[i].label;
						changeParent.appendChild(op);
					}
					document.getElementById('moduleInsertButton').disabled=false;
				}
			}
		});
	},
	parentDivHide:function(){
		var changeP=document.getElementById("changeParent");
		document.getElementById("module.parent.id").value=$("#changeParent").val();
		document.getElementById("module.parent.label").value=(changeP.options[changeP.selectedIndex].text).trim();
		//$("#parentDiv").hide();
	},
	moduleIconUpload:function(icon,treeNodeIcon){
		treeNode.loading();//加载图片
		$.ajaxFileUpload({ 
			url:basePath+'/json!uploadModuleIcon',
			secureuri:false, 
			fileElementId:'moduleIcon',
			dataType:!$.browser.mozilla?'text':'json',//浏览器兼容性
			success: function (data, status){
				icon.src=basePath+'/images/'+data;
				document.getElementById(treeNodeIcon).value=data;
				common.promptUnLocking(bgCompName,disCompName);
			}, 
			error: function (data, status, e){ 
				alert("data:"+data+"  status:"+status+"  e:"+e); 
			} 
		});
		return false; 
	},
	loading:function(){
		$("#loading").ajaxStart(
			function(){
				$(this).show(); 
			}).ajaxComplete(
				function(){ 
					$(this).hide(); 
				}
		); 
	}
}

/***********************************************
 * JS 控制树节点显示与隐藏
 ***********************************************/
nodeTree = {
	control:function(self,divId){
		nodeTree.displayNode(self,divId);				
	},
	displayNode : function(self,divId) {
		var node=$("div[id^="+divId+"]");
		if(node==null)return;		
		for(var i=0;i<node.size();i++){
			node.get(i).style.display='block';			
		}
		self.onclick=function(){nodeTree.hideNode(self,divId);}
	},
	hideNode:function(self,divId){
		var node=$("div[id^="+divId+"]");		
		for(var i=0;i<node.size();i++){					
			node.get(i).style.display='none';
			var parentAndChild=node.get(i).id.split("_");
			if(parentAndChild!=null&&parentAndChild.length==2){
				nodeTree.hideNode(self,parentAndChild[1]);
			}
		}
		self.onclick=function(){nodeTree.displayNode(self,divId);}
	},
	num:0
}

/*****************************************************
 *  弹窗背景变灰、判断一个 object 是否为空、某一组件的显示与隐藏
 *****************************************************/
common={
	promptLocking:function(bgCompName,disCompName){			
		if($.browser.msie){
			var disCompIframe=document.createElement("IFRAME");			
			disCompIframe.className="disCompIframe";
			disCompIframe.style.display="block";
			bgCompName.appendChild(disCompIframe);	
		}
		bgCompName.style.display="block";
		bgCompName.style.width=document.body.clientWidth;
		bgCompName.style.height=document.body.clientHeight;
		disCompName.style.display="block";	
		if(!$.browser.msie){
			disCompName.className="disCompDivFire";
		}
	},
	promptUnLocking:function(bgCompName,disCompName){
		bgCompName.style.display="none";
		disCompName.style.display="none";
	},
	isNull:function(obj){
		if(obj==null)return;
		return obj;
	},
	componetEnable:function(vl,compName){
		if('true' == vl)
			compName.style.display='none';
		else if('false' == vl){
			if($.browser.msie)
				compName.style.display="block";
			else if($.browser.mozilla)
				compName.style.display="table-row";
		}
	}
}

/*****************************************************
 *  模块授权时级联选择节点
 *****************************************************/
module={
	cascadeCheck:function(up,selfObj,down){
		var moduleId=selfObj.getAttribute("id").split("_");
		if(moduleId.length==2){
			if(selfObj.checked==true)
				module.upCheck(moduleId[0]);
			if(down)
				module.downCheck(moduleId[1],selfObj);
		}
		
	},
	upCheck:function(parentId){
		var moduleId=$("input[id$=_"+parentId+"]");
		if(moduleId.length>0){
			moduleId[0].checked=true;
			module.cascadeCheck(true,moduleId[0],false);
		}
		
	},
	downCheck:function(selfId,selfObj){
		var moduleId=$("input[id^="+selfId+"_]");
		for(var i=0;i<moduleId.length;i++){
			if(selfObj.checked==true)
				moduleId[i].checked=true;
			else
				moduleId[i].checked=false;
			module.cascadeCheck(false,moduleId[i],true);
		}
	}
}

/*****************************************************
 *  用户角色编辑
 *****************************************************/
account={
	choose:function(target,setValue){
		var select=document.getElementById(target);
		var chooseValueArray=document.getElementById(setValue);
		chooseValueArray.value="";
		if(select.options.length==0){
			chooseValueArray.value="";
			return;
		}
		for(var i=0;i<select.options.length;i++){
			if (select.options[i].value != '') {
				if(i==(select.options-1)){
					chooseValueArray.value+=(select.options[i].value);
				}
				else
					chooseValueArray.value+=(select.options[i].value+",");
			}
		}
	},
	valueConvert:function(){
		
	}
}

/*****************************************************
 * 添加功能
 *****************************************************/
ueyeFunction={
	deleteFlag:false,
	pop:function(bgCompName,disCompName,id,idValue,label,labelValue){
		getObject(id).value=idValue;
		getObject(label).value=labelValue;
		common.promptLocking(bgCompName,disCompName);		
	},
 	insertFunction:function(){
 		var name=getObject("funModel.functionName").value;
 		var code=getObject("funModel.functionCode").value;
 		if(name==''||name.trim()==''){
 			alert('功能名称不能为空且不能为空字符...');
 			return false;
 		}
 		if(code==''||code.trim()==''){
 			alert('功能代码不能为空且不能为空字符...');
 			return false;
 		}
 		return true;
 	},
 	functionList:function(bgCompName,disCompName,id,idValue,label,labelValue,header){
 		jQuery.ajax({
			type:'get',
			url:basePath+'/function/'+idValue,
			dataType:'json',
			success:function(data){
				if(data&&data.length>0){					
					getObject("functionListTable").appendChild(ueyeFunction.mergeData(data));
			 		ueyeFunction.pop(bgCompName,disCompName,id,idValue,label,labelValue);
			 		getObject(header).innerHTML=labelValue+'功能列表';
				}
				else
					alert("没有功能数据 。。。");
			}
		});
 	},
 	mergeData:function(data){
 		var tbody=document.createElement("tbody");
		for(var i=0;i<data.length;i++){
			var tr=document.createElement("tr");
	 		ueyeFunction.createTableContent(tr,data[i]);
			tbody.appendChild(tr);
		}
		return tbody;
 	},
 	setAttr:function(tr,td,value){
 		td.setAttribute("width","130");
 		td.setAttribute("className","td");
 		td.setAttribute("class","td");
 		td.innerHTML=value;
 		tr.appendChild(td);
 	},
 	createTableContent:function(tr,data){
 		//名称
 		var nameTD=document.createElement("td");
 		ueyeFunction.setAttr(tr,nameTD,data.functionName);
 		//code
 		var codeTD=document.createElement("td");
 		ueyeFunction.setAttr(tr,codeTD,data.functionCode);
 		//描述
 		var descTD=document.createElement("td");
 		ueyeFunction.setAttr(tr,descTD,data.functionDesc);
 		//链接
 		var linkTD=document.createElement("td");
 		var link="<a href='#' onClick='ueyeFunction.deleteFunction("+data.id+","+data.moduleId+")'> 删 除 </a>";
 		ueyeFunction.setAttr(tr,linkTD,link);
 	},
 	closeFunctionList:function(bgCompNameFunction,disCompNameFunction){
 		common.promptUnLocking(bgCompNameFunction,disCompNameFunction);
 		jQuery("#functionListTable").empty();
 		if(ueyeFunction.deleteFlag){
 			window.location=basePath+'/function';
 		}
 	},
 	deleteFunction:function(id,moduleId){
 		jQuery.ajax({
			type:'get',
			url:basePath+'/function/'+id+'/destroy?module.id='+moduleId,
			dataType:'json',
			success:function(data){
				ueyeFunction.deleteFlag=true;
				jQuery("#functionListTable").empty();
				if(data&&data.length>0){
					getObject("functionListTable").appendChild(ueyeFunction.mergeData(data));
				}
				else{
					alert("功能数据已被清空完了 。。。");
					window.location=basePath+'/function';
				}
			},
			error: function (data, status, e){ 
				alert("data:"+data+"  status:"+status+"  e:"+e); 
			} 
		});
 	}
}



































