<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<s:head theme="ajax" debug="true" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				
				
				<div align="left" style="margin-left: 10px; float: left; border:  solid 0px red; width: 13%">
					<form action="Templet_listVerify.action" method="post">
						<s:if test="rootTemplet != null && rootTemplet.id == #pt.id">
								<option value="${pt.id}" selected="selected">${pt.name}</option>
							</s:if><s:else>
								<option value="${pt.id}">${pt.partsNumber}${pt.specification}</option>
						</s:else>
						<select name="templet.id" style="width: 130px; border:8px  solid #000000;" >
							<s:iterator value="listVerify" id="v">
								<option value="${v.id}">${v.partsNumber}${v.specification}</option>
							</s:iterator>
						</select>
						<br />
						<input type="submit" value="查看">
					</form>
					
					
				</div>
				
				<div align="left" style="margin-left: 30px; float: left; border:  solid 0px red; width: 30%">
					<s:if test="rootTemplet != null">
						<s:tree rootNode="root" nodeIdProperty="id" nodeTitleProperty="name" childCollectionProperty="childrens" showRootGrid="true" showGrid="true" id="deptNumber">
						</s:tree>
					</s:if>
				</div>
				
				<div id="message" align="right"
				style="margin-right: 50px; float: right;border:  solid 0px red;width: 40%;display:none ; ">
					<form name="from2" action="" method="post" style="margin: 0 px; padding: 0 px; ">
						<input type="hidden" id="templetId" name="templet.id">
						<input type="hidden" id="rootTemplet" name="temp" >
						<input id="parentId" type="hidden" name="templet.parentId" class="search_input" />
						<input id="belongLayer" type="hidden"
										name="templet.belongLayer" >
						<table style="border-collapse: collapse;border:1px solid #999;" border="1" >
							<tr >
							      <th colspan="2">
							        <span id="templetName"></span>
							      </th>
							</tr>
							<tr>
								<th align="right">
									产&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
								</th>
								<td>
									<input id="name" type="text" name="templet.name" disabled="disabled" style="width: 200px" onblur="checkTrim(this);">
								</td>
							</tr>
							<s:if test="#session.templetBoss == true">
								<tr >
									<th align="right" height="27 px">
										负责人:
									</th>
									<td>
										<input id="resPerson" type="text" name="templet.resPerson" style="width: 200px;" onblur="checkTrim(this);" disabled="disabled">
									</td>
								</tr>
							</s:if>
							<tr>
								<th align="right">
									件号:
								</th>
								<td>
									<input id="partsNumber" type="text" name="templet.partsNumber" style="width: 200px" onblur="checkTrim(this);" disabled="disabled">
								</td>
							</tr>
							<tr>
								<th align="right">
									权值:
								</th>
								<td>
									<input id="advPosition" type="text" name="templet.advPosition" onblur="checkTrim(this);" style="width: 200px" onkeyup="if(isNaN(value))execCommand('undo')" onchange="if(isNaN(value))execCommand('undo')" disabled="disabled">
								</td>
							</tr>
							<tr>
								<th align="right">
									车型:
								</th>
								<td>
									<input id="models" type="text" name="templet.models" onblur="checkTrim(this);" style="width: 200px" disabled="disabled">
								</td>
							</tr>
							<tr>
								<th align="right">
									说明:
								</th>
								<td>
									<input id="directions" type="text" name="templet.directions" onblur="checkTrim(this);" style="width: 200px" disabled="disabled">
								</td>
							</tr>
							<tr>
								<th align="right">
									规格:
								</th>
								<td>
									<input id="specification" type="text" onblur="checkTrim(this);" style="width: 200px" name="templet.specification" disabled="disabled">
								</td>
							</tr>
							<tr>
								<th align="right">
									类别:
								</th>
								<td>
									<select id="category" name="templet.category" style="width: 200px" disabled="disabled">
										<s:iterator value='{"板料","包装物","工装","管料","外购件","组合件","总成","辅件"}' id="hhh">
											<option value="${hhh}">
												${hhh}
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							
							
							<tr>
								<th align="right">
									牌号:
								</th>
								<td>
									<input id="trademark" type="text" name="templet.specification" value="${templet.specification}" disabled="disabled" style="width: 200px">
								</td>
							</tr>
							<tr>
								<th align="right">
									执行标准:
								</th>
								<td>
									<input id="standards" type="text" name="templet.standards" value="${templet.standards}" disabled="disabled" style="width: 200px">
								</td>
							</tr>
							<tr>
								<th align="right">
									厚度:
								</th>
								<td>
									<input id="thickness" type="text" name="templet.thickness" value="${templet.thickness}" disabled="disabled" style="width: 140px">±
									<input id="thicknessT" type="text" name="templet.thicknessT" value="${templet.thicknessT}" disabled="disabled" style="width: 50px">
								</td>
							</tr>
							<tr>
								<th align="right">
									宽度:
								</th>
								<td>
									<input id="width" type="text" name="templet.width" value="${templet.width}" disabled="disabled"  style="width: 140px">±
									<input id="widthT" type="text" name="templet.widthT" value="${templet.widthT}"  disabled="disabled"  style="width: 50px">
								</td>
							</tr>
							<tr>
								<th align="right">
									长度:
								</th>
								<td>
									<input id="length" type="text" name="templet.length" value="${templet.length}" disabled="disabled"  style="width: 140px">±
									<input id="lengthT" type="text" name="templet.lengthT" value="${templet.lengthT}" disabled="disabled"  style="width: 50px">
								</td>
							</tr>
							<tr>
								<th align="right">
									直径:
								</th>
								<td>
									<input id="diameter" type="text" name="templet.diameter" value="${templet.diameter}" disabled="disabled"  style="width: 200px">
								</td>
							</tr>
							
							
							
							<tr>
								<th align="right">
									单位:
								</th>
								<td>
									<select id="unit" name="templet.unit" style="width: 200px" onkeypress="writeSelect(this)" title="可输入" onkeydown="if(event.keyCode == 8){this.options[0].text ='';}" disabled="disabled">
										<option value="件">件</option>
										<s:iterator value='{"件","套","台","只","kg","支"}' id="hhh">
											<option value="${hhh}">
												${hhh}
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<div id="buts">
										<input id="yesButton" type="button" value="同意" onclick="changeTemplet('yes',this.form)" style="width: 70px; height: 50px;" />
										<input id="noButton" type="button" value="驳回" onclick="changeTemplet('no',this.form)" style="width: 70px; height: 50px;" />
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
				
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
<script type="text/javascript">
//tree加载操作
dojo.addOnLoad(function() {
	var t = dojo.widget.byId('deptNumber');
	var s = t.selector;
	dojo.event.connect(s, 'select', 'treeNodeSelected');
	expandObj(t);
});


//选择事件
function treeNodeSelected(arg) {
	sendRequest('Templet_findTempletByIdForTree.action?templet.id=' + arg.source.widgetId, forInput);
	var messageDiv = document.getElementById("message");//信息层
	var deptNameSpan = document.getElementById("templetName");//部门名称
	var deptIdText = document.getElementById("templetId");//隐藏Id
	deptNameSpan.innerHTML = arg.source.title;//名称
	deptIdText.value = arg.source.widgetId;//id
	messageDiv.style.display = "block";//显示信息层
}

//展开全部
function expandObj(obj) {
	if (obj) {
		if (obj.children) {
			for ( var i = 0; i < obj.children.length; i++) {
				var childObj = obj.children[i];
				if (childObj) {
					try {
						childObj.expand();
					} catch (e) {
					}
					if (childObj.children) {
						expandObj(childObj);
					}
				}
			}
		}
	}
	
}

//为部门名称和编号赋值
function forInput() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var templetString = message.split("|");
			document.getElementById("templetId").value = templetString[0];
			document.getElementById("parentId").value = templetString[1];//父id
			document.getElementById("name").value = templetString[2];//部门编号
			document.getElementById("partsNumber").value = templetString[3];//部门编号
			document.getElementById("advPosition").value = templetString[4];//部门编号
			document.getElementById("models").value = templetString[5];//部门编号
			document.getElementById("directions").value = templetString[6];//部门编号
			document.getElementById("specification").value = templetString[7];//部门编号
			var categorySelect = document.getElementById("category");//类型下拉框
			for ( var i = 0; i < categorySelect.length; i++) {
				if (templetString[8] == categorySelect.options[i].value) {
					categorySelect.options[i].selected = "selected";
				}
			}
			document.getElementById("unit")[0].innerHTML= templetString[9];//单位
			document.getElementById("unit")[0].value= templetString[9];//单位
			if(typeof (document.getElementById("resPerson")) ==  "undefined"){
				document.getElementById("resPerson").value = templetString[10];//单位
			}
			document.getElementById("belongLayer").value = templetString[11];//层次
			
			var ss = document.getElementById("productTemplet");
			
			if(typeof (document.getElementById("rootTemplet")) ==  "undefined"){
				document.getElementById("rootTemplet").value = ss.options[ss.selectedIndex].value;
			}
			
			if(templetString[0] == document.getElementById("rootTemplet").value){
				document.getElementById("resPerson").style.display="inline";
			} 
			if(/\(审核中\)$/.test(document.getElementById("templetName").innerHTML)){
				document.getElementById("buts").style.display = "inline";
			} else {
				document.getElementById("buts").style.display = "none";
			}
			document.getElementById("trademark").value = templetString[12];//谁维护谁杯具，等着我重写这部分功能吧，我自己也吐血了，杀一个程序员不用枪，只需改三次需求即可
			document.getElementById("thickness").value = templetString[13];//早知道我用JSON传了
			document.getElementById("thicknessT").value = templetString[14];
			document.getElementById("width").value = templetString[15];
			document.getElementById("widthT").value = templetString[16];
			document.getElementById("length").value = templetString[17];
			document.getElementById("lengthT").value = templetString[18];
			document.getElementById("diameter").value = templetString[19];
			document.getElementById("standards").value = templetString[20];
			
			
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
function changeTemplet(status, form) {
	if(!checkAll()){
		return ;
	}
	
	if (status == "yes") {
		form.action = "Templet_agree.action";
	} else if (status == "no") {
		form.action = "Templet_overrule.action";
	} 
	form.submit();
}
function checkAll(){
	var tname = document.getElementById("name");
	tname.value = tname.value.replace(/\s+/g,"");
	if(tname.value.length < 1){
		alert("请输入名称");
		return false;
	}
	
	var partsNumber = document.getElementById("partsNumber");
	partsNumber.value = partsNumber.value.replace(/^\s+|\s+$/g, "");
	return true;
}

</script>
</html>
