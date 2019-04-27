<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
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
	<body onload="createDept('sqDept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">选择档案</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" style="background-color: #fff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					存取档案申请
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="DanganAction_add.action" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table" id="tablebod">
						<tbody> 
						<tr style="width: 100%">
						<th colspan="2">
							部门
						</th>
						<td align="center" colspan="2">
							<select id="sqDept" name="dangAn.sqDept"
								style="width: 155px;">
								<option value="">
									--请选择部门--
								</option>
							</select>
						</td>
						<th colspan="2">
							姓名
						</th>
						<td align="center" colspan="2">
							<select id="name" style="width: 155px;" onclick="deptNotNull()">
								<option></option>
							</select>
							<input id="bfName" name="dangAn.sqName" type="hidden">
							<input id="sqCode" name="dangAn.sqCode" type="hidden">
						</td>
						</tr>
							<tr>
								<th>
									存档室名称
								</th>
								<td align="center">
									<select name="dangAn.cdAceName" id="cdAceName"
										style="width: 156px;"
										onMouseOver="createDept('cdAceName','DanganAction_findSelectName.action')">
										<option value="">
											请选择存档室
										</option>
									</select>
								</td>
								<th>
									存档室门禁编号
								</th>
								<td align="center">
									<select name="dangAn.cdAceNum" id="cdAceNum"
										onclick="daNameNull()" style="width: 156px;">
									</select>
								</td>
								<th>
									申请人手机号
								</th>
								<td align="center">
									<input type="text" name="dangAn.sqTel" id="sqTel" />
								</td>
								<th>
									存档日期
								</th>
								<td align="center" >
									<input class="Wdate" type="text" name="dangAn.shenqingdate"
										id="shenqingdate" value=""
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
							</tr>
							<tr id="addtr1">
								<th colspan="8">
									存取档信息
								</th>
							</tr>
							<tr>
							<th align='center'>存档类型 1： </th>
							<td align='center'>
								<select name="archiveUnarchiverApltList[1].cqType" id = "cqType1">
									<option value=''>请选择存取档类型</option>
									<option value = '存档'>存档</option>
									<option value = '取档'>取档</option>
								</select>
							</td>
							<th >档案名称</th>
							<td align='center'>
								<input type="text" title="点击选择档案" readonly="readonly" onclick="selectPrice(1)" name="archiveUnarchiverApltList[1].daName" id ="daName1"/>
							</td>
							<th >档案编号</th>
							<td align='center'>
								<input type="text" title="点击选择档案" readonly="readonly" onclick="selectPrice(1)" name="archiveUnarchiverApltList[1].daNum" id ="daNum1"/>
							</td>
							<th >档案位置</th>
							<td align='center'>
								<input type="text" title="点击选择档案" readonly="readonly" onclick="selectPrice(1)" name="archiveUnarchiverApltList[1].daGuihao" id="daGuihao1">
								<input type="hidden" name="archiveUnarchiverApltList[1].fileName" id="fileName1">
								<input type="hidden" name="archiveUnarchiverApltList[1].daGuiId" id="daGuiId1">
							</td>
							</tr>
							<tr>
								<td align="right" colspan="4" style="border-right-width: 0px;">
									<input type="button" id="tijia" onclick="addDangan(this,1)"
										value="添加档案" style="width: 110px; height: 25px;" />
								</td>
								<td align="left" colspan="4" style="border-left-width: 0px;">
									<input type="button" onclick="delFamily(this)" id="shanchu"
										style="display: none; width: 110px; height: 25px;"
										value="删除档案" />
								</td>
							</tr>
							<tr>
								<td colspan="8" align="center">
									<input type="submit" value="申  请(Application)"
										style="width: 130px; height: 40px;" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var size = 1;//存档信息条数
var index = 2;//存档信息下标
function addDangan(obj, few) {
	if (size >= 20) {
		alert("存档信息条数达到上限");
		return false;
	}
	var trindex = 1 + index;
	var cqType = "cqType" + index;//存取类型
	var daName = "daName" + index;//档案名称
	var daNum = "daNum" + index;//档案编号
	var daGuiId = "daGuiId" + index;//档案位置（在哪个档案柜里）
	var daGuihao = "daGuihao" + index;//档案位置（在哪个档案柜里）
	var fileName = "fileName" + index;//档案文件地址
	$("#tablebod>tbody>tr")
			.eq(trindex)
			.after(
					"<tr id='addtr"
							+ index
							+ "'><th colspan='1' align='center'>存档类型  "+index+"： </th><td colspan='1' align='center'><select name='archiveUnarchiverApltList["
							+ index
							+ "].cqType' id="
							+ cqType
							+ "><option value=''>请选择存取档类型</option><option value = '存档'>存档</option><option value = '取档'>取档</option></select></td>" +
							"<th >档案名称</th><td align='center'><input type='text' title='点击选择档案' readonly='readonly' onclick='selectPrice("
							+ index
							+ ")' name='archiveUnarchiverApltList["
							+ index
							+ "].daName' id="
							+ daName
							+ "></td><th >档案编号</th><td align = 'center'><input type='text' title='点击选择档案' onclick='selectPrice("
							+ index
							+ ")' readonly='readonly' name='archiveUnarchiverApltList["
							+ index
							+ "].daNum' id="
							+ daNum
							+ "></td><th align = 'center'>位置： </th><td align = 'center'><input type='text' title='点击选择档案' onclick='selectPrice("
							+ index
							+ ")' readonly='readonly' name='archiveUnarchiverApltList["
							+ index
							+ "].daGuihao' id="
							+ daGuihao
							+ "><input type='hidden' name='archiveUnarchiverApltList["
							+ index
							+ "].fileName' id="
							+ fileName
							+ "><input type='hidden' name='archiveUnarchiverApltList["
							+ index + "].daGuiId' id=" + daGuiId
							+ "></td></tr>");
	size++;
	index++;
	document.getElementById("shanchu").style.display = "block";

}
//删除存档信息
function delFamily() {
	tablebod.deleteRow(index+1);
	size--;
	index--;
	if (size == 1) {
		document.getElementById("shanchu").style.display = "none";
	}
}

function validate() {
	if (!validateText("cdAceName", "存档室名称")) {
		return false;
	}
	if (!validateText("cdAceNum", "存档室门禁编号")) {
		return false;
	}
	if (!validateText("sqTel", "申请人手机号")) {
		return false;
	}
	if (!validateText("shenqingdate", "申请存档日期")) {
		return false;
	}

	if (!validateText("cqType1", "存取档案1类型")) {
		return false;
	}
	if (!validateText("daName1", "档案1名称")) {
		return false;
	}
	for ( var i = 2; i <= size; i++) {
		var cqType = "cqType" + i;
		if (!validateText(cqType, "存取档案" + i + "类型")) {
			return false;
		}
		var daName = "daName" + i;
		if (!validateText(daName, "档案" + i + "名称")) {
			return false;
		}
	} 
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

$(function(){
	$("#cdAceName").bind("change",function(){
		if ($("#cdAceName").val() != "") {
			//显示所有档案设备名称
			$.ajax({
				url : 'DanganAction_findacEJson.action',
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					aceName : $("#cdAceName").val()//要传过去的值
				},
				success : function(allAce) {
					$("#cdAceNum").empty();//清空数据
					$("<option value=''>--请选择档案室编号--</option>").appendTo("#cdAceNum");
					$(allAce).each(function(){
						$("<option value='"+this.equipmentNum+"'>"+this.equipmentNum+"</option>").appendTo("#cdAceNum");
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		} else {
			$("#cdAceNum").empty();//清空数据
		}
	});
});
function daNameNull() {
	if ($("#cdAceName").val() == "" || $("#cdAceName").val() == "") {
		alert("存档室名称不能为空！");
		return false;
	}
}

//查找档案信息弹出层
function selectPrice(num) {
	//alert(type);"http://task.shhhes.com"
	var url = "PriceAction!findPriceByCondition.action?statue=dangan&num_1="+num;
	$("#showProcess").attr("src",
			url);
	chageDiv('block');
}

//根据部门显示人员
$(function(){
	//显示部门对应的员工信息
	$("#sqDept").bind(
			"change",
			function() {
				if ($("#sqDept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#sqDept").val()
						},
						success : function(useradsfa) {
							$("#name").empty();//清空
							$("#sqCode").val("");
							$("<option></option>").appendTo("#name");
							$(useradsfa).each(
									function() {
										$(
												"<option value='"
															+ this.code + "|"
															+ this.name + "'>"
															+ this.name
															+ "</option>")
												.appendTo("#name")
									});
								$("#name").bind("change", function() {
								var name = $("#name").val();
								var usersData = name.split("|");
								var ncode = usersData[0];
								var bfName = usersData[1];
								$("#sqCode").val(ncode);
								$("#bfName").val(bfName);
							});
							
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#name").empty();//清空
				$("#ncode").val("");
				}
		});
});

function deptNotNull() {
	if ($("#sqDept").val() == "" || $("#sqDept").val() == "") {
		alert("被访人部门不能为空！");
		return false;
	}
}
</script>
	</body>
</html>
