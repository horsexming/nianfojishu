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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
				</div>
			</div>

			<div align="center">
				<h3>
					<font color="red">${nonCoreReceivables.chengzufang}</font>添加应收明细信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="NoncoreReceAction!addDetail.action?tag=${tag}" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th style="width: 264px;">
								费用科目：
							</th>
							<th align="center">
								<SELECT id="kemu" style="width: 152px;" name="nonCoreReceivablesDetail.kemu" onclick="shuid()" >
									<option value="">--请选择科目--</option>
									<s:if test='nonCoreReceivables.receiveType=="设备租赁"'>
										<option value="设备租赁" selected="selected">设备租赁</option>
									</s:if>
									<s:elseif test='nonCoreReceivables.receiveType=="其他"'>
										<option value="其他" selected="selected">其他</option>
									</s:elseif>
									<s:else>
										<option value="地产租赁" selected="selected">地产租赁</option>
										<s:if test='nonCoreReceivables.isNeeddaitijiaofei=="是"'>
											<option value="水费">水费</option>
											<option value="电费">电费</option>
										</s:if>
									</s:else>
									<option value="废旧物资处理">废旧物资处理</option>
									<option value="其他">其他</option>
								</SELECT>
							</th>
							<th>
								费用截止日：
							</th>
							<td align="center">
								<input id="jiluTime" type="text" name="nonCoreReceivablesDetail.jiluTime" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
								<input type="hidden" name="nonCoreReceivables.id" value="${id}"/>
							</td>
						</tr>
						<tr id="biao" style="display: none;">
							<th>
								上次读表数：
							</th>
							<td align="center">
								<input id="lastbiaoshu" type="text" name="nonCoreReceivablesDetail.lastbiaoshu" onblur="mustBeNumber('lastbiaoshu')"/>
							</td>
							<th>
								本次读表数：
							</th>
							<td align="center">
								<input id="thisbiaoshu" type="text" name="nonCoreReceivablesDetail.thisbiaoshu" onblur="mustBeNumber('thisbiaoshu')"/>
							</td>
						</tr>
						<tr>
							<th>
								收款状态：
							</th>
							<td align="center">
								<SELECT id="zhuangtai" style="width: 152px;" name="nonCoreReceivablesDetail.zhuangtai">
<%--									<option value="">--请选择收款状态--</option>--%>
									<option value="未收" selected="selected">未收</option>
<%--									<option value="已收">已收</option>--%>
								</SELECT>
							</td>
							<th>
								缴费附件：
							</th>
							<td align="center" colspan="1">
								<input id="photoPath" style="width: 158px;" type="file" name="fujian1"/>
							</td>
						</tr>
						<tr id="zu">
							<th>
								应收金额：
							</th>
							<td align="center">
								<input id="yingfuJine" type="text" name="nonCoreReceivablesDetail.yingfuJine" value="${nonCoreReceivables.zujin}" onblur="mustBeNumber('yingfuJine')"/>
							</td>
							<th colspan="2">
							</th>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 80px; height: 30px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("kemu", "科目")) {
		return false;
	}
	if (!validateText("jiluTime", "费用截止日")) {
		return false;
	}
	if($("#kemu").val()=='水费'||$("#kemu").val()=='电费'){
		if (!validateText("lastbiaoshu", "上次读表数")) {
			return false;
		}
		if (!validateText("thisbiaoshu", "本次读表数")) {
			return false;
		}
	}else{
		if (!validateText("yingfuJine", "应收金额")) {
			return false;
		}
	}
	if (!validateText("zhuangtai", "收款状态")) {
		return false;
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
function shuid(){
	if($("#kemu").val()=='水费'||$("#kemu").val()=='电费'){
		$("#biao").show();
		$("#lastbiaoshu").removeAttr("disabled");
		$("#thisbiaoshu").removeAttr("disabled");
		$("#zu").hide();
		$("#yingfuJine").attr("disabled", "disabled");
	}else{
		$("#zu").show();
		$("#yingfuJine").removeAttr("disabled");
		$("#biao").hide();
		$("#lastbiaoshu").attr("disabled", "disabled");
		$("#thisbiaoshu").attr("disabled", "disabled");
	}
}
</script>
	</body>
</html>
