<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.entity.bp.Templet"%>
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
		<script type="text/javascript" src="javascript/jquery-1.7.2.min.js"></script>
		
		<script type="text/javascript">
document.onkeydown = banBackSpace;
$(function(){
	//给搜索按钮的表单提交为ajax
	$("#searchButton").click(function(){
		var params = $('#searchByMdelsFrom select').serialize(); //序列化表单的值，与prototype中的form.serialize()相同 
		$.ajax({ 
			url : 'Templet!searchByModel.action', //后台处理程序 
			type:'post', //数据发送方式 
			dataType:'json', //接受数据格式 
			data:params, //要传递的数据 
			success : function(msg){
				var obj = msg;
				if(obj.success){
					initSelect(obj.data);
				} else {
					alert(obj.message);
				}
			}  
		}); 
	});
});

//初始化select
function initSelect(data){
	$('#productTemplet').html('');
	for(var i = 0; i<data.length; i++){
		document.getElementById("productTemplet").options.add(new Option(data[i].partsNumber,data[i].id));
	}
}
//给所有的文本框去除首尾空格
function checkTrim(ele) {
	ele.value = ele.value.replace(/^\s+|\s+$/g, "");
}

function showTemplet() {
	document.form1.action = "Templet_list.action";
	document.form1.submit();
}

//显示添加最顶层。
function showAddRoot() {
	document.getElementById("message").style.display = "inline";
	document.getElementById("updateButton").style.display = "none";
	document.getElementById("deleteButton").style.display = "inline";
	document.getElementById("addButton").style.display = "none";
	document.getElementById("addRootButton").style.display = "inline";
	document.getElementById("resPerson").style.display = "inline";

}
</script>
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
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div>
				<div>
					<form id="searchByMdelsFrom" action="" method="post">
						根据车型查找：
						<select name="templet.models">
							<s:iterator value="models" id="m">
								<option value="${m}">${m}</option>
							</s:iterator>
						</select>
						<input id="searchButton" type="button" value="查找">
						
						
					</form>
				</div>
				<div style="clear: both;" align="center">
					<font color="red">${successMessage}</font>
					<font color="red">${errorMessage}</font>
				</div>
				<br />
				<div align="left"
					style="margin-left: 10px; float: left; border:  solid 0px red; width: 13%">
					<form id="listForm" name="form1" action="" method="post">
						<select id="productTemplet" size="10" name="rootTemplet.id"
							style="width: 130px; height: 300px;  border: 8px  solid #000000;" onchange="showTemplet();">
							<s:iterator value="productTemplets" id="pt" status="st">
								<s:if test="rootTemplet != null && rootTemplet.id == #pt.id">
									<option value="${pt.id}" selected="selected">
										${pt.partsNumber}
									</option>
								</s:if>
								<s:else>
									<option value="${pt.id}">
										${pt.partsNumber}
									</option>
								</s:else>
							</s:iterator>
						</select>
						<br />
						<br />
						<div align="center">
							
							<s:if test="#session.templetBoss == true">
								<input onclick="showAddRoot();" type="button" value="添加">
							</s:if>
						</div>
					</form> 
				</div>
				<div align="left"
					style="margin-left: 30px; float: left; border:  solid 0px red; width: 30%">
					<s:if test="rootTemplet != null">
						<s:tree rootNode="root" nodeIdProperty="id"
							nodeTitleProperty="name" childCollectionProperty="childrens"
							showRootGrid="true" showGrid="true" id="deptNumber">
						</s:tree>
					</s:if>
				</div>

				<div id="message" align="right"
					style="margin-right: 50px; float: right; border:  solid 0px red; width: 40%; display: none;">
					<form name="from2" action="" method="post"
						style="margin: 0 px; padding: 0 px;">
						<input type="hidden" id="templetId" name="templet.id">
						<input type="hidden" id="rootTemplet" name="temp">
						<input id="parentId" type="hidden" name="templet.parentId"
							class="search_input" />
						<input id="belongLayer" type="hidden" name="templet.belongLayer">
						<table style="border-collapse: collapse; border: 1px solid #999;"
							border="1">
							<tr>
								<th colspan="2">
									<span id="templetName"></span>
								</th>
							</tr>
							<tr>
								<th align="right">
									产名:
								</th>
								<td>
									<input id="name" type="text" name="templet.name"
										style="width: 200px" onblur="checkTrim(this);">
								</td>
							</tr>
							
							<tr>
								<th align="right">
									件号:
								</th>
								<td>
									<input id="partsNumber" type="text" name="templet.partsNumber"
										style="width: 200px" onblur="checkTrim(this);">
								</td>
							</tr>
							<tr>
								<th align="right">
									类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:
								</th>
								<td>
									<select id="category" name="templet.category" onchange="showCategory(this.value);"
										style="width: 200px">
										<s:iterator
											value='{"板料","管料","卷料","外购件","工装","组合件","总成","辅件","外委"}'
											id="hhh">
											<option value="${hhh}">
												${hhh}
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									数量:
								</th>
								<td>
									<input id="advPosition" type="text" name="templet.advPosition"
										onblur="checkTrim(this);" style="width: 200px"
										onkeyup="if(isNaN(value))execCommand('undo')"
										onchange="if(isNaN(value))execCommand('undo')">
								</td>
							</tr>
							<tr id="trademark_tr">
								<th align="right">
									牌号:
								</th>
								<td>
									<input id="trademark" type="text" name="templet.trademark" onkeyup="generateSpecification();"
										onblur="checkTrim(this);" style="width: 200px">
								</td>
							</tr>
							<tr id="standards_tr">
								<th align="right">
									执行标准:
								</th>
								<td>
									<input id="standards" type="text" name="templet.standards" onblur="checkTrim(this);" onkeyup="generateSpecification();" style="width: 200px">
								</td>
							</tr>
							<tr id="thickness_tr">
								<th align="right">
									厚度:
								</th>
								<td>
									<input id="thickness" type="text" name="templet.thickness" onblur="checkTrim(this);" style="width: 140px" onkeyup="generateSpecification();">±
									<input id="thicknessT" type="text" name="templet.thicknessT" onblur="checkTrim(this);"  onkeyup="generateSpecification();" style="width: 50px">
								</td>
							</tr>
							<tr id="width_tr">
								<th align="right">
									宽度:
								</th>
								<td>
									<input id="width" type="text" name="templet.width" onblur="checkTrim(this);" onkeyup="generateSpecification();" style="width: 140px">±
									<input id="widthT" type="text" name="templet.widthT" onblur="checkTrim(this);" onkeyup="generateSpecification();" style="width: 50px">
								</td>
							</tr>
							<tr id="length_tr">
								<th align="right">
									长度:
								</th>
								<td>
									<input id="length" type="text" name="templet.length" onblur="checkTrim(this);" onkeyup="generateSpecification();" style="width: 140px">±
									<input id="lengthT" type="text" name="templet.lengthT" onblur="checkTrim(this);" onkeyup="generateSpecification();" style="width: 50px">
								</td>
							</tr>
							<tr id="diameter_tr">
								<th align="right">
									直径:
								</th>
								<td>
									<input id="diameter" type="text" name="templet.diameter" onblur="checkTrim(this);" onkeyup="generateSpecification();" style="width: 200px">
								</td>
							</tr>
							<tr id="specification_tr">
								<th align="right">
									规格:
								</th>
								<td>
									<input id="specification" type="text" onblur="checkTrim(this);" readonly="readonly"
										style="width: 200px" name="templet.specification">
								</td>
							</tr>
							<tr>
								<th align="right">
									车型:
								</th>
								<td>
									<input id="models" type="text" name="templet.models"
										onblur="checkTrim(this);" style="width: 200px">
								</td>
							</tr>
							<tr>
								<th align="right">
									说明:
								</th>
								<td>
									<input id="directions" type="text" name="templet.directions"
										onblur="checkTrim(this);" style="width: 200px">
								</td>
							</tr>
							<s:if test="#session.templetBoss == true">
								<tr>
									<th align="right" height="27 px">
										负责人:
									</th>
									<td>
										<input id="resPerson" type="text" name="templet.resPerson"
											style="width: 200px;" onblur="checkTrim(this);">
									</td>
								</tr>
							</s:if>
							<tr>
								<th align="right">
									单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位:
								</th>
								<td>
									<select id="unit" name="templet.unit" style="width: 200px"
										onkeypress="writeSelect(this)" title="可输入"
										onkeydown="if(event.keyCode == 8){this.options[0].text ='';}">
										<option value="件">
											件
										</option>
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
									<input id="updateButton" type="button" value="修改内容"
										onclick="changeTemplet('update',this.form)"
										style="width: 70px; height: 50px;" />
									<input id="addButton" type="button" value="添加下层"
										onclick="changeTemplet('add',this.form)"
										style="width: 70px; height: 50px;" />
									<input id="addRootButton" type="button" value="确认"
										onclick="changeTemplet('addRoot',this.form)"
										style="width: 60px; height: 50px; display: none" />
									<input id="deleteButton" type="button" value="删除"
										onclick="changeTemplet('del',this.form)"
										style="width: 60px; height: 50px;" />
									<input type="reset" value="清空"
										style="width: 60px; height: 50px;">
								</td>
							</tr>
						</table>
					</form>
				</div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
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
	sendRequest(
			'Templet_findTempletByIdForTree.action?templet.id=' + arg.source.widgetId,
			forInput);
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

function generateSpecification(){
	var cateValue = document.getElementById("category").options[document.getElementById("category").options.selectedIndex].value;
	if(cateValue == "板料"){
		var trademark = document.getElementById("trademark").value;
		var standards = document.getElementById("standards").value;
		var thickness = document.getElementById("thickness").value;
		var width = document.getElementById("width").value;
		var widthT = document.getElementById("widthT").value;
		var length = document.getElementById("length").value;
		var lengthT = document.getElementById("lengthT").value;
		var specification = document.getElementById("specification");
		specification.value =  trademark + " " + standards + " " + thickness + "*" + width + "±" + widthT + "*" + length + "±" + lengthT;
	} else if(cateValue == "管料"){
		var trademark = document.getElementById("trademark").value;
		var standards = document.getElementById("standards").value;
		var diameter = document.getElementById("diameter").value;
		var thickness = document.getElementById("thickness").value;
		var thicknessT = document.getElementById("thicknessT").value;
		var length = document.getElementById("length").value;
		var lengthT = document.getElementById("lengthT").value;
		var specification = document.getElementById("specification");
		specification.value =  trademark + " " + standards + " " + diameter + "*" + thickness + "±" + thicknessT + "*" + length + "±" + lengthT;
	} else if(cateValue == "卷料"){
		var trademark = document.getElementById("trademark").value;
		var standards = document.getElementById("standards").value;
		var thickness = document.getElementById("thickness").value;
		var thicknessT = document.getElementById("thicknessT").value;
		var width = document.getElementById("width").value;
		var widthT = document.getElementById("widthT").value;
		var specification = document.getElementById("specification");
		specification.value =  trademark + " " + standards + " " + thickness + "±" + thicknessT + "*" + width + "±" + widthT + "*C";
	}
}

function showCategory(cate){
	if(cate == "板料"){
		showTr(false);
		document.getElementById("trademark").style.display = "inline";
		document.getElementById("standards").style.display = "inline";
		document.getElementById("thickness").style.display = "inline";
		document.getElementById("thicknessT").style.display = "none";
		document.getElementById("diameter").style.display = "none";
		document.getElementById("width").style.display = "inline";
		document.getElementById("widthT").style.display = "inline";
		document.getElementById("length").style.display = "inline";
		document.getElementById("lengthT").style.display = "inline";
		document.getElementById("specification").readOnly = true;
	} else if(cate == "管料"){
		showTr(false);
		document.getElementById("trademark").style.display = "inline";
		document.getElementById("standards").style.display = "inline";
		document.getElementById("thickness").style.display = "inline";
		document.getElementById("thicknessT").style.display = "inline";
		document.getElementById("diameter").style.display = "inline";
		document.getElementById("width").style.display = "none";
		document.getElementById("widthT").style.display = "none";
		document.getElementById("length").style.display = "inline";
		document.getElementById("lengthT").style.display = "inline";
		document.getElementById("specification").readOnly = true;
	} else if(cate == "卷料"){
		showTr(false);
		document.getElementById("trademark").style.display = "inline";
		document.getElementById("standards").style.display = "inline";
		document.getElementById("thickness").style.display = "inline";
		document.getElementById("thicknessT").style.display = "inline";
		document.getElementById("diameter").style.display = "none";
		document.getElementById("width").style.display = "inline";
		document.getElementById("widthT").style.display = "inline";
		document.getElementById("length").style.display = "none";
		document.getElementById("lengthT").style.display = "none";
		document.getElementById("specification").readOnly = true;
	} else if(cate == "外购件"){
		showTr(false);
		document.getElementById("trademark").style.display = "none";
		document.getElementById("standards").style.display = "none";
		document.getElementById("thickness").style.display = "none";
		document.getElementById("thicknessT").style.display = "none";
		document.getElementById("diameter").style.display = "none";
		document.getElementById("width").style.display = "none";
		document.getElementById("widthT").style.display = "none";
		document.getElementById("length").style.display = "none";
		document.getElementById("lengthT").style.display = "none";
		document.getElementById("specification").readOnly = false;
	} else {
		document.getElementById("trademark").style.display = "inline";
		document.getElementById("standards").style.display = "inline";
		document.getElementById("thickness").style.display = "inline";
		document.getElementById("thicknessT").style.display = "inline";
		document.getElementById("diameter").style.display = "inline";
		document.getElementById("width").style.display = "inline";
		document.getElementById("widthT").style.display = "inline";
		document.getElementById("length").style.display = "inline";
		document.getElementById("lengthT").style.display = "inline";
		document.getElementById("specification").readOnly = false;
		showTr(true);
	}
}

function showTr(bool){
	if(bool){
		$("#trademark_tr").hide();
		$("#standards_tr").hide();
		$("#width_tr").hide();
		$("#length_tr").hide();
		$("#trademark_tr").hide();
		$("#diameter_tr").hide();
		$("#specification_tr").hide();
		$("#thickness_tr").hide();
	} else {
		$("#trademark_tr").show();
		$("#standards_tr").show();
		$("#width_tr").show();
		$("#length_tr").show();
		$("#trademark_tr").show();
		$("#diameter_tr").show();
		$("#specification_tr").show();
		$("#thickness_tr").show();
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
			showCategory(templetString[8]);
			document.getElementById("unit")[0].innerHTML= templetString[9];//单位
			document.getElementById("unit")[0].value= templetString[9];//单位
			document.getElementById("resPerson").value = templetString[10];//单位
			document.getElementById("belongLayer").value = templetString[11];//层次

			var ss = document.getElementById("productTemplet");
			document.getElementById("rootTemplet").value = ss.options[ss.selectedIndex].value;
			if (templetString[0] == document.getElementById("rootTemplet").value) {
				document.getElementById("resPerson").style.display = "inline";
				document.getElementById("models").style.display = "inline";
			} else {
				document.getElementById("resPerson").style.display = "none";
				document.getElementById("models").style.display = "none";
			}

			if (document.getElementById("parentId").value == 0) {
				document.getElementById("deleteButton").style.display = "inline";
				document.getElementById("resPerson").style.display = "inline";
			} else {
				document.getElementById("deleteButton").style.display = "inline";
				document.getElementById("resPerson").style.display = "none";
			}
			document.getElementById("trademark").value = templetString[12];//谁维护谁杯具，等着我重写这部分功能吧，我自己也吐血了，杀一个程序员不用枪，只需改三次需求即可
			document.getElementById("thickness").value = templetString[13];
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
	if (!checkAll()) {
		return;
	}
	if (status == "update") {
		form.action = "Templet_update.action";
	} else if (status == "add") {
		form.action = "Templet_add.action";
	} else if (status == "del") {
		form.action = "Templet_delete.action";
	} else if (status == "addRoot") {
		form.action = "Templet_addRoot.action";
	}
	form.submit();
}
function checkAll() {
	var tname = document.getElementById("name");
	tname.value = tname.value.replace(/\s+/g, "");
	if (tname.value.length < 1) {
		alert("请输入名称");
		return false;
	}

	var partsNumber = document.getElementById("partsNumber");
	partsNumber.value = partsNumber.value.replace(/^\s+|\s+$/g, "");

	return true;
}



</script>


</html>
