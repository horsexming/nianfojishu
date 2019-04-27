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
				<form action="dimissionLogAction_add.action?tages=${tages}" method="post"
					onsubmit="return dimissAdd()">
					<table class="table" align="center">
						<tr>
							<th align="center" colspan="6" style="size: 20pt;">
								<img alt="loge" src="<%=path%>/images/zhaobiao.jpg"
									style="width: 30px; height: 30px;">
								${companyInfo.name}员工离职申请单
								<br />
								<input type="hidden" name="tag" value="code" />
								<font color="#ff0000">${errorMessage}</font>
							</th>
						</tr>
						<tr>
							<td colspan="6" align="right">
								<h3>
									合同编号：${contract.contractNumber} &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="hidden" name="dimissionLog.contract_number"
										id="contract_number" value="${contract.contractNumber}" />
								</h3>
							</td>
						</tr>
						<tr>
							<th>
								申请人姓名
							</th>
							<th>
								部门
							</th>
							<th>
								岗位
							</th>
							<th colspan="2">
								本厂工作年限
							</th>
							<th>
								工号
							</th>
						</tr>
						<tr>
							<s:if test="tages=='dai'">
								<td align="center">
									<input type="hidden" name="dimissionLog.codeId" id="codeId"
										value="${us.id}" readonly="readonly" />
									<input type="text" name="dimissionLog.name" id="name"
										value="${us.name}" readonly="readonly" />
								</td>
								<td align="center">
									<input type="text" name="dimissionLog.dept" id="dept"
										value="${us.dept}" readonly="readonly" />
								</td>
								<td align="center">
									<input type="text" name="dimissionLog.job" id="job"
										value="${us.duty}" readonly="readonly" />
								</td>
								<td align="center" colspan="2">
									<input type="text" name="dimissionLog.year_term" id="year_term"
										value="${year_term}"/>
								</td>
								<td align="center">
									<input type="text" name="dimissionLog.code" id="code"
										value="${us.code}" readonly="readonly" />
								</td>
							</s:if>
							<s:else>
								<td align="center">
									<input type="hidden" name="dimissionLog.codeId" id="codeId"
										value="${Users.id}" readonly="readonly" />
									<input type="text" name="dimissionLog.name" id="name"
										value="${Users.name}" readonly="readonly" />
								</td>
								<td align="center">
									<input type="text" name="dimissionLog.dept" id="dept"
										value="${Users.dept}" readonly="readonly" />
								</td>
								<td align="center">
									<input type="text" name="dimissionLog.job" id="job"
										value="${Users.duty}" readonly="readonly" />
								</td>
								<td align="center" colspan="2">
									<input type="text" name="dimissionLog.year_term" id="year_term"
										value="${year_term}"/>
								</td>
								<td align="center">
									<input type="text" name="dimissionLog.code" id="code"
										value="${Users.code}" readonly="readonly" />
								</td>
							</s:else>
						</tr>
						<tr>
							<th>
								离职原因
							</th>
							<td colspan="5">
								<textarea rows="5" cols="100%"
									name="dimissionLog.dimission_Reason" id="dimission_Reason"></textarea>
							</td>
						</tr>

						<tr>
							<th>
								离职后去向
							</th>
							<td align="center" colspan="5">
								<input type="text" name="dimissionLog.dimission_laterGo"
									id="dimission_laterGo" style="width: 100%;" />
							</td>
							<%--<th>
								申请离职时间
							</th>
							<td align="center" colspan="2">
								<input class="Wdate" type="text" name="dimissionLog.app_time"
									id="app_time"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})">
							</td>
						--%>
						</tr>
						<%--<tr>
							<th>
								是否有遗留问题或劳动争议
							</th>
							<td colspan="5">
								<select name="dimissionLog.naowuzhengyi" id="naowuzhengyi"
									class="zhengyi" onclick="zhengyi(${naowuzhengyi})">
									<option value="否 ">
										否
									</option>
									<option value="是">
										是
									</option>
								</select>
								<textarea rows="3" cols="90%" title="请填写争议内容"
									name="dimissionLog.zhengyi_content" id="zhengyi_content"
									style="display: none"></textarea>
							</td>
						</tr>
						--%>
						<tr>
							<th colspan="6">
								请确认以下条款
							</th>
						</tr>
						<tr>
							<s:iterator value="provisionlist" id="provil" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="left">
								</s:if>
								<s:else>
									<tr align="left">
								</s:else>
								<td align="left" colspan="6">
									&nbsp;&nbsp;&nbsp;&nbsp;${provil.content}
								</td>
							</s:iterator>
						</tr>
						<s:if test="tages=='dai'">
							<tr>
								<td align="center" colspan="6">
									确定
									<input type="checkbox" name="dimissionLog.confirm" id="confirm" checked="checked"
										value="同意" onclick="if(this.checked){enable()}else{disable()}" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="6">
									<input type="submit" value="申请"
										id="btnlizhi" style="width: 80px; height: 40px;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 80px; height: 40px;">
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td align="center" colspan="6">
									本人确定
									<input type="checkbox" name="dimissionLog.confirm" id="confirm"
										value="同意" onclick="if(this.checked){enable()}else{disable()}" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="6">
									<input type="submit" value="申请" disabled="disabled"
										id="btnlizhi" style="width: 80px; height: 40px;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 80px; height: 40px;">
								</td>
							</tr>
						</s:else>

					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function disable() {
	document.getElementById("btnlizhi").disabled = true
}
function enable() {
	document.getElementById("btnlizhi").disabled = false
}

function dimissAdd() {

	if (!validateText("job", "岗位")) {
		return false;
	}
	if (!validateText("year_term", "本厂工作年限")) {
		return false;
	}
	if (!validateText("dimission_Reason", "离职原因")) {
		return false;
	}
	if (!validateText("dimission_laterGo", "离职后去向")) {
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
function zhengyi(obj){
	if($("#naowuzhengyi").val()=='是'){
		$("#zhengyi_content").show();
	}else{
		$("#zhengyi_content").hide();
	};
}
		</script>
	</body>
</html>
