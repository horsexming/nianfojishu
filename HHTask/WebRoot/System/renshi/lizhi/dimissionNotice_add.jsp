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
				<%-- 
				style="width: 756px; height: 1086px; border: 0px solid #000000;">
				--%>
				<form action="dimission_XieYiAction_addNotice.action" method="post"
					onsubmit="return dimiss_ZYAdd()">
					<table class="table" align="center">
						<tr>
							<th align="center" colspan="6" style="font-size: 20px; height: 70px;">
								添加离职通知单
								<input type="hidden" name="dimissionLog.id"
									value="${dimissionLog.id}" />
								<font color="red">${errorMessage}</font>
							</th>
						</tr>
						<tr>
							<th colspan="6" align="right">
								编号：${number}<input type="hidden" name="dimissionNotice.noticeNumber" value="${number}"/>&nbsp;&nbsp;&nbsp;&nbsp;
							</th>
						</tr>
						<tr>
							<th>
								主送
							</th>
							<th>
								财务部
							</th>
							<th>
								抄送：
							</th>
							<td>
								<input type="text" name="dimissionNotice.chaosong"/>
							</td>
							<th>
								存档：
							</th>
							<th>
								人力资源
							</th>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<th>
								${dimissionLog.name}
								<input type="hidden" name="dimissionNotice.name" value="${dimissionLog.name}"/>
							</th>
							<th>
								部门/班组
							</th>
							<th>
								${dimissionLog.dept}
								<input type="hidden" name="dimissionNotice.dept" value="${dimissionLog.dept}"/>
							</th>
							<th>
								工号
							</th>
							<th>
								${dimissionLog.code}
								<input type="hidden" name="dimissionNotice.code" value="${dimissionLog.code}"/>
							</th>
						</tr>
						<tr>
							<th>
								离职原因
							</th>
							<th colspan="5">
								<input type="text" name="dimissionNotice.lizhi" style="width: 100%" id="lizhi"/>
							</th>
						</tr>
						<tr>
							<th>
								办理手续日期
							</th>
							<th>
								<input class="Wdate" type="text" name="dimissionNotice.banli_time"
									id="banli_time"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</th>
							<th>
								止薪日期
							</th>
							<th>
								<input class="Wdate" type="text" name="dimissionNotice.zhixin_time"
									id="zhixin_time"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</th>
							<th>
								停缴社保、公积金日期
							</th>
							<th>
								<input class="Wdate" type="text" name="dimissionNotice.shebao_time"
									id="shebao_time"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</th>
						</tr>
						<tr>
							<th>
								离职当月工资
							</th>
							<td colspan="5">
								<input type="checkbox" name="dimissionNotice.gongzijiesuan" value="按考勤出勤日期结算" />
								按考勤出勤日期结算;
								<input type="checkbox" name="dimissionNotice.gongzijiesuan" value="另行结算" />
								另行结算;
								<input type="checkbox" name="dimissionNotice.gongzijiesuan" value="已结清" />
								已结清
							</td>
						</tr>
						<tr>
							<th colspan="6" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此通知
							</th>
						</tr>
						<tr>
							<th colspan="6" align="right">
								经办人：${Users.name}<input type="hidden" name="dimissionNotice.banliren" value="${Users.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</th>
						</tr>
						<tr>
							<th colspan="6" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;签收：
							</th>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="提交" 
									id="btnlizhi" style="width: 80px; height: 40px;">
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
	if (!validateText("lizhi", "离职原因")) {
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
