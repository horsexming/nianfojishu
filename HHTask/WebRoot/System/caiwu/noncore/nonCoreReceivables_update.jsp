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
					修改应收总信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="NoncoreReceAction!update.action?tag=${tag}" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th>
								租贷类型：
							</th>
							<th align="left">
								<SELECT id="receiveType" style="width: 152px;" name="nonCoreReceivables.receiveType">
									<option value="${nonCoreReceivables.receiveType}">${nonCoreReceivables.receiveType}</option>
									<option value="地产租赁">地产租赁</option>
									<option value="设备租赁">设备租赁</option>
								</SELECT>
							</th>
							<th>
								缴租周期：
							</th>
							<td align="left">
								<input id="jiaozuzhouqi" type="text" name="nonCoreReceivables.jiaozuzhouqi" value="${nonCoreReceivables.jiaozuzhouqi}"/>
								<input type="hidden" name="nonCoreReceivables.id" value="${nonCoreReceivables.id}"/>月
							</td>
						</tr>
						<tr>
							<th>
								合同编号：
							</th>
							<td align="left">
								<input id="hetongbianhao" type="text" name="nonCoreReceivables.hetongbianhao" value="${nonCoreReceivables.hetongbianhao}"/>
							</td>
							<th>
								合同附件：
							</th>
							<td align="left">
								<input id="hetongfujian" style="width: 158px;" type="file" name="fujian"
								onclick="return window.confirm('上传新文件后将删除旧文件，是否继续?')"/>
<%--								<a href="<%=path%>/upload/file/feiZY/${nonCoreReceivables.hetongfujian}">查看附件</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/feiZY/${nonCoreReceivables.hetongfujian}">查看附件</a>
								<input type="hidden" name="nonCoreReceivables.hetongfujian"
									value="${nonCoreReceivables.hetongfujian}" />
							</td>
						</tr>
						<tr>
							<th>
								承租方：
							</th>
							<td align="left">
								<input id="chengzufang" type="text" name="nonCoreReceivables.chengzufang" value="${nonCoreReceivables.chengzufang}"/>
							</td>
							<th>
								承租地址：
							</th>
							<td align="left">
								<input id="chengzudizhi" type="text" name="nonCoreReceivables.chengzudizhi" value="${nonCoreReceivables.chengzudizhi}"/>
							</td>
						</tr>
						<tr>
							<th>
								经办人：
							</th>
							<td align="left">
								<input id="jignbanren" type="text" name="nonCoreReceivables.jignbanren" value="${nonCoreReceivables.jignbanren}"/>
							</td>
							<th>
								开始有效期：
							</th>
							<td align="left">
								<input class="Wdate" id="youxiaoqistart" type="text" name="nonCoreReceivables.youxiaoqiStart"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" value="${nonCoreReceivables.youxiaoqiStart}"/>
							</td>
						</tr>
						<tr>
							<th>
								租金：
							</th>
							<td align="left">
								<input id="zujin" type="text" name="nonCoreReceivables.zujin" value="${nonCoreReceivables.zujin}"/>(单周期内租金)
							</td>
							<th>保证金：</th>
							<td align="left" >
								<input id="baozhegnjin" type="text" name="nonCoreReceivables.baozhegnjin" value="${nonCoreReceivables.baozhegnjin}"/>
							</td>
						</tr>
						<tr>
							<th>
								是否需代缴费用(水电、管理)：
							</th>
							<td align="left">
							<s:if test='nonCoreReceivables.isNeeddaitijiaofei=="是"'>
								<input type="radio" name="nonCoreReceivables.isNeeddaitijiaofei"
									id="isNeeddaitijiaofei" value="是" checked="checked">
								是&nbsp;
								<input type="radio" name="nonCoreReceivables.isNeeddaitijiaofei"
									id="isNeeddaitijiaofei" value="否">
								否&nbsp;
							</s:if>
							<s:else>
								<input type="radio" name="nonCoreReceivables.isNeeddaitijiaofei"
									id="isNeeddaitijiaofei" value="是">
								是&nbsp;
								<input type="radio" name="nonCoreReceivables.isNeeddaitijiaofei"
									id="isNeeddaitijiaofei" value="否" checked="checked">
								否&nbsp;
							</s:else>
							</td>
							<th>
								截止有效期：
							</th>
							<td align="left">
								<input class="Wdate" id="youxiaoqi" type="text" name="nonCoreReceivables.youxiaoqi"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" value="${nonCoreReceivables.youxiaoqi}"/>
							</td>
						</tr>
						<tr>
							<th>
								状态：
							</th>
							<td align="left">
								<SELECT id="typeStatus" name="nonCoreReceivables.typeStatus">
								<s:if test="nonCoreReceivables.typeStatus==null||nonCoreReceivables.typeStatus==''">
									<option value="有效" selected="selected">有效</option>
								</s:if>
								<s:else>
									<option value="${nonCoreReceivables.typeStatus}" selected="selected">${nonCoreReceivables.typeStatus}</option>
								</s:else>
									<option value="失效">失效</option>
									<option value="有效">有效</option>
								</SELECT>
							</td>
							<th colspan="2"></th>
						</tr>
						<tr>
							<th>
								摘要：
							</th>
							<td align="left" colspan="3">
								<textarea rows="2" cols="100%" name="nonCoreReceivables.zhaiyao"
										id="zhaiyao">${nonCoreReceivables.zhaiyao}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="修改(update)"
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
	if (!validateText("receiveType", "租贷类型")) {
		return false;
	}
	if (!validateText("jiaozuzhouqi", "租贷周期")) {
		return false;
	}
	if (!validateText("hetongbianhao", "合同编号")) {
		return false;
	}
	if (!validateText("chengzufang", "承租方")) {
		return false;
	}
	if (!validateText("chengzudizhi", "承租地址")) {
		return false;
	}
	if (!validateText("jignbanren", "经办人")) {
		return false;
	}
	if (!validateText("youxiaoqi", "合同截止有效期")) {
		return false;
	}
	if (!validateText("zujin", "租金")) {
		return false;
	}
	if (!validateText("baozhegnjin", "保证金")) {
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
</script>
	</body>
</html>
