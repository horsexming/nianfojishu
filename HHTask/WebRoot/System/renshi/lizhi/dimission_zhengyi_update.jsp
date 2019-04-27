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
		<div align="center" id="gongneng">
			<div align="center"
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				<form action="dimission_ZhengYiAction_update.action" method="post"
					onsubmit="return dimiss_ZYAdd()">
					<table class="table" align="center">
						<tr>
							<th align="center" colspan="8" style="size: 20pt;">
								修改离职工资单<input type="hidden" name="dimissionZhengYi.id"
									id="log_number" value="${dimissionZhengYi.id}" />
							</th>
						</tr>
						<tr>
							<th colspan="4" align="left" style="border-right-width: 0px;">
								&nbsp;&nbsp;&nbsp;&nbsp;离职申请单编号：${dimissionZhengYi.log_number}
								<input type="hidden" name="dimissionZhengYi.log_number"
									id="log_number" value="${dimissionZhengYi.log_number}" />
							</th>
							<th colspan="4" align="right" style="border-left-width: 0px;">
								离职交接单编号：${dimissionZhengYi.hand_number} &nbsp;&nbsp;&nbsp;&nbsp;
								<input type="hidden" name="dimissionZhengYi.hand_number"
									id="hand_number" value="${dimissionZhengYi.hand_number}" />
							</th>
						</tr>
						<tr>
							<td align="center">
								姓名
							</td>
							<td align="center" style="width: 102px;">
								<input type="text" style="width: 100px;"
									name="dimissionZhengYi.name" id="name"
									value="${dimissionZhengYi.name}" readonly="readonly" />
							</td>
							<td align="center">
								入职日期
							</td>
							<td align="center" colspan="2">
								<input type="text" name="dimissionZhengYi.ruzhiTime"
									id="ruzhiTime" value="${dimissionZhengYi.ruzhiTime}"
									readonly="readonly">
							</td>
							<td align="center">
								离职日期
							</td>
							<td align="center" colspan="2">
								<input type="text" name="dimissionZhengYi.liTime" id="liTime"
									value="${dimissionZhengYi.liTime}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td align="center">
								员工卡号
							</td>
							<td align="center" style="width: 102px;">
								<input type="text" style="width: 100px;"
									name="dimissionZhengYi.code" id="code"
									value="${dimissionZhengYi.code}" readonly="readonly" />
							</td>
							<td align="center">
								离职原因
							</td>
							<td align="center" colspan="5">
								<textarea rows="2" cols="85%"
									name="dimissionZhengYi.dimi_Reason" id="dimi_Reason"
									readonly="readonly">${dimissionZhengYi.dimi_Reason}</textarea>
							</td>
						</tr>
						<%--
						以下内容由人事填写：
						--%>
						<tr>
							<td align="center" colspan="2">
								工资结算截止日期
							</td>
							<td align="center" colspan="2">
								<input class="Wdate" type="text" name="dimissionZhengYi.jz_Time"
									id="jz_Time" value="${dimissionZhengYi.jz_Time}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td align="center" colspan="2">
								当月计薪日
							</td>
							<td align="center" colspan="2">
								<input class="Wdate" type="text"
									name="dimissionZhengYi.jxr_Time" id="jxr_Time"
									value="${dimissionZhengYi.jxr_Time}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								生产奖金或绩效分配数
							</td>
							<td colspan="2">
								<input type="text" name="dimissionZhengYi.jx_Money"
									id="jx_Money" value="${dimissionZhengYi.jx_Money}" onblur="mustBeNumber('jx_Money')"/>
								元
							</td>
							<td align="center" colspan="2">
								应发岗位工资
							</td>
							<td colspan="2">
								<input type="text" name="dimissionZhengYi.gw_Money"
									id="gw_Money" value="${dimissionZhengYi.gw_Money}" onblur="mustBeNumber('gw_Money')" />
								元
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								应发奖金绩效
							</td>
							<td colspan="2">
								<input type="text" name="dimissionZhengYi.jiangj_Money"
									id="jiangj_Money" value="${dimissionZhengYi.jiangj_Money}" onblur="mustBeNumber('jiangj_Money')" />
								元
							</td>
							<td align="center" colspan="2">
								餐费
							</td>
							<td colspan="2" align="left">
								<input type="text" name="dimissionZhengYi.canfei"
									id="canfei" value="${dimissionZhengYi.canfei}" onblur="mustBeNumber('canfei')"/>
								元 
								<a href="AttendanceAction!showRechage.action" target="_canfei">餐费查询</a>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								合计离职工资
							</td>
							<td colspan="6">
								<input type="text" name="dimissionZhengYi.add_up_number"
									id="add_up_number" value="${dimissionZhengYi.add_up_number}" onblur="mustBeNumber('add_up_number')"/>
								元
							</td>
						</tr>

						<tr>
							<td align="center">
								离职补偿金额
							</td>
							<td align="center" style="width: 102px;">
								<input type="text" style="width: 100px"
									name="dimissionZhengYi.bc_Money" id="bc_Money"
									value="${dimissionZhengYi.bc_Money}" onblur="mustBeNumber('bc_Money')" />
							</td>
							<td align="center">
								大写
							</td>
							<td align="center" colspan="2">
								<input type="text" style="width: 100%"
									name="dimissionZhengYi.daxie" id="daxie"
									value="${dimissionZhengYi.daxie}" />
							</td>
							<td align="center">
								合计应发
							</td>
							<td align="center" colspan="2">
								<input type="text" style="width: 100%"
									name="dimissionZhengYi.addup_yf" id="addup_yf"
									value="${dimissionZhengYi.addup_yf}" />
							</td>
						</tr>
						<tr>
							<td align="center">
								补贴
							</td>
							<td colspan="2">
								<input type="text" name="dimissionZhengYi.butie" id="butie"
									value="${dimissionZhengYi.butie}" onblur="mustBeNumber('butie')" />
								元
							</td>
							<td align="center">
								补贴说明
							</td>
							<td colspan="4">
								<input type="text" style="width: 100%;"
									name="dimissionZhengYi.butie_shuoming" id="butie_shuoming"
									value="${dimissionZhengYi.butie_shuoming}" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								社保、公积金缴费截止日期
							</td>
							<td align="center" colspan="2">
								<input class="Wdate" type="text"
									name="dimissionZhengYi.sbjf_jzTime" id="sbjf_jzTime"
									value="${dimissionZhengYi.sbjf_jzTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td align="center" colspan="2">
								是否需要补足最低工资标准
							</td>
							<s:if
								test="dimissionZhengYi.buzu_min=='是'&&dimissionZhengYi.buzu_min!=null">
								<td align="center" colspan="2">
									<input type="radio" name="dimissionZhengYi.buzu_min"
										id="buzu_min" value="是" checked="checked" />
									是
									<input type="radio" name="dimissionZhengYi.buzu_min"
										id="buzu_min" value="否" />
									否
								</td>
							</s:if>
							<s:else>
								<td align="center" colspan="2">
									<input type="radio" name="dimissionZhengYi.buzu_min"
										id="buzu_min" value="是" />
									是
									<input type="radio" name="dimissionZhengYi.buzu_min"
										id="buzu_min" value="否" checked="checked" />
									否
								</td>
							</s:else>
						</tr>

						<tr>
							<th colspan="8">
								以下扣款项
							</th>
						</tr>
						<tr>
							<td align="center">
								养老
							</td>
							<td align="center">
								医疗
							</td>
							<td align="center">
								失业
							</td>
							<td align="center">
								公积金
							</td>
							<td align="center">
								房租
							</td>
							<td align="center">
								其他扣款
							</td>
							<td align="center" colspan="2">
								实发金额合计
							</td>
						</tr>
						<tr>

							<td align="center" style="width: 98px;">
								<input type="text" style="width: 90px;"
									name="dimissionZhengYi.pension" id="pension"
									value="${dimissionZhengYi.pension}" onblur="mustBeNumber('pension')" />
							</td>
							<td align="center" style="width: 102px;">
								<input type="text" style="width: 100px;"
									name="dimissionZhengYi.yiliao" id="yiliao"
									value="${dimissionZhengYi.yiliao}" onblur="mustBeNumber('yiliao')"/>
							</td>
							<td align="center">
								<input type="text" style="width: 90px;"
									name="dimissionZhengYi.shiye" id="shiye"
									value="${dimissionZhengYi.shiye}" onblur="mustBeNumber('shiye')" />
							</td>
							<td align="center">
								<input type="text" style="width: 90px;"
									name="dimissionZhengYi.fund" id="fund"
									value="${dimissionZhengYi.fund}" onblur="mustBeNumber('fund')" />
							</td>
							<td align="center">
								<input type="text" style="width: 90px;"
									name="dimissionZhengYi.rent" id="rent"
									value="${dimissionZhengYi.rent}" onblur="mustBeNumber('rent')" />
							</td>
							<td align="center">
								<input type="text" style="width: 90px;"
									name="dimissionZhengYi.other" id="other"
									value="${dimissionZhengYi.other}" onblur="mustBeNumber('other')" />
							</td>
							<td align="center" colspan="2">
								<input type="text" style="width: 98%;"
									name="dimissionZhengYi.real_money" id="real_money"
									value="${dimissionZhengYi.real_money}" onblur="mustBeNumber('real_money')" />
							</td>

						</tr>
						<tr>
							<td align="center">
								备注
							</td>
							<td colspan="7">
								<textarea rows="3" cols="100%" name="dimissionZhengYi.remark"
									id="remark">${dimissionZhengYi.remark}</textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="8">
								<input type="submit" value="修改"
									style="width: 80px; height: 40px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 80px; height: 40px;">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function dimiss_ZYAdd() {

	if (!validateText("jz_Time", "工资结算截止日期")) {
		return false;
	}
	if (!validateText("jxr_Time", "当月计薪日")) {
		return false;
	}
	if (!validateText("jx_Money", "生产奖金或绩效分配数")) {
		return false;
	}
	if (!validateText("gw_Money", "应发岗位工资")) {
		return false;
	}
	if (!validateText("jiangj_Money", "应发奖金绩效")) {
		return false;
	}
	if (!validateText("add_up_number", "合计离职工资")) {
		return false;
	}

	if (!validateText("bc_Money", "离职补偿金额")) {
		return false;
	}
	if (!validateText("daxie", "大写金额")) {
		return false;
	}
	if (!validateText("addup_yf", "合计应发")) {
		return false;
	}
	if (!validateText("butie", "补贴")) {
		return false;
	}
	if (!validateText("butie_shuoming", "补贴说明")) {
		return false;
	}
	if (!validateText("sbjf_jzTime", "社保缴费截止日期")) {
		return false;
	}

	if (!validateText("pension", "养老金")) {
		return false;
	}
	if (!validateText("yiliao", "医疗金")) {
		return false;
	}
	if (!validateText("shiye", "失业金")) {
		return false;
	}
	if (!validateText("fund", "公积金")) {
		return false;
	}
	if (!validateText("rent", "房租")) {
		return false;
	}
	if (!validateText("other", "其他")) {
		return false;
	}
	if (!validateText("real_money", "实际金额合计")) {
		return false;
	}
	if (!validateText("remark", "备注")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $("#" + id).val();
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
		</script>
	</body>
</html>
