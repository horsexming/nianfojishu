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
	<body>
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
							<span id="title">选择发票</span>
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
					财务档案存取申请
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="DanganAction_addFp.action" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table" id="tablebod">
						<tbody>
							<tr>
								<th>
									申请人手机号
								</th>
								<td align="center" colspan="1">
									<input type="text" name="dangAn.sqTel" id="sqTel" />
								</td>
								<th>
									申请数量
								</th>
								<td align="center" colspan="1">
									<input type="text" name="dangAn.num" id="num" />
								</td>
								<th>
									开票金额
								</th>
								<td align="center" colspan="1">
									<input type="text" name="dangAn.money" id="money" />
								</td>
								<th>
									操作日期
								</th>
								<td align="center" colspan="1">
									<input class="Wdate" type="text" name="dangAn.shenqingdate"
										id="shenqingdate" value=""
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
							</tr>
							<tr>
								<th>原因：</th>
								<td colspan="7"><input type="text" style="width: 90%" name="dangAn.quDangReason" id="sqTel" /></td>
							</tr>
							<tr>
								<th colspan="8">
									存取票信息
								</th>
							</tr>
							
							<tr>
								<td align="right" colspan="4" style="border-right-width: 0px;">
									<input type="button" id="tijia" onclick="addDangan(this,1)"
										value="添加" style="width: 80px; height: 25px;" />
								</td>
								<td align="left" colspan="4" style="border-left-width: 0px;">
									<input type="button" onclick="delFamily(this)" id="shanchu"
										style="display: none; width: 80px; height: 25px;"
										value="删除" />
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
var size = 0;//存档信息条数
var index = 1;//存档信息下标
function addDangan(obj, few) {
	if (size >= 20) {
		alert("存取发票信息条数达到上限");
		return false;
	}
	var trindex = 1 + size;
	var cqType = "cqType" + index;//存取类型
	var daName = "daName" + index;//档案名称
	var daNum = "daNum" + index;//档案编号
	var daGuiId = "daGuiId" + index;//档案位置（在哪个档案柜里）
	var daGuihao = "daGuihao" + index;//档案位置（在哪个档案柜里）
	var fileName = "fileName" + index;//档案文件地址
	var priceID = "priceID" + index;//档案文件地址
	$("#tablebod>tbody>tr")
			.eq(trindex)
			.after(
					"<tr id='addtr"
							+ index
							+ "'><th colspan='1' align='center'>存取类型： </th><td colspan='1' align='center'><select name='archiveUnarchiverApltList["
							+ index
							+ "].cqType' id="
							+ cqType
							+ "><option value=''>请选择存取类型</option><option value = '存档'>存档</option><option value = '取档'>取档</option></select></td><th >发票名称</th><td align='center'><input type='text' title='点击选择发票' readonly='readonly' onclick='selectcwPrice("
							+ index
							+ ")' name='archiveUnarchiverApltList["
							+ index
							+ "].daName' id="
							+ daName
							+ "></td><th >发票编号</th><td align = 'center'><input type='text' title='点击选择发票' onclick='selectcwPrice("
							+ index
							+ ")' readonly='readonly' name='archiveUnarchiverApltList["
							+ index
							+ "].daNum' id="
							+ daNum
							+ "></td><th align = 'center'>位置： </th><td align = 'center'><input type='text' title='点击选择发票' onclick='selectcwPrice("
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
							+ "><input type='hidden' name='archiveUnarchiverApltList["
							+ index + "].daId' id=" + priceID
							+ "></td></tr>");
	size++;
	index++;
	document.getElementById("shanchu").style.display = "block";

}
//删除存档信息
function delFamily() {
	tablebod.deleteRow(index);
	size--;
	index--;
	if (size < 1) {
		document.getElementById("shanchu").style.display = "none";
	}
}

function validate() {
	if (!validateText("num", "申请发票数量")) {
		return false;
	}
	if (!validateText("shenqingdate", "申请存档日期")) {
		return false;
	}

	//if (!validateText("cqType0", "存取档1方式")) {
	//	return false;
	//}

	for ( var i = 1; i <= size; i++) {
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


//查找档案信息弹出层
function selectcwPrice(num) {
	//alert(type);"http://task.shhhes.com"
	var url = "CwCertificateAction_showList.action?tag=cw1&num_1="+num;
	$("#showProcess").attr("src",
			url);
	chageDiv('block');
}
</script>
	</body>
</html>
