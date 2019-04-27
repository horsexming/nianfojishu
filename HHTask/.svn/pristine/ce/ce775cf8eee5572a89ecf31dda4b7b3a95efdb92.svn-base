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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>

			<div align="center">
				<form action="FailureStAction!addFailureSt.action" method="post"
					onsubmit="return check();">
					<table class="Table">
						<tr>
							<th colspan="6">
								每周一次提交不合格统计
							</th>
						</tr>
						<tr>
							<th align="right">
								日期:
							</th>
							<td align="left">
								<input class="Wdate" type="text" name="failureSt.dateTime"
									onClick="WdatePicker({dateFmt:'yyyy年MM月dd日',skin:'whyGreen'})" />
							</td>
							<th align="right">
								零件号:
							</th>
							<td align="left">
								<input name="failureSt.markId" />
							</td>
							<th align="right">
								客户:
							</th>
							<td align="left">
								<select name="failureSt.Client" style="width: 155px;">
									<option value="华为技术有限公司">
										华为技术有限公司
									</option>
									<option value="湘华五金科技">
										东莞市湘华五金科技有限公司
									</option>
									<option value="深圳市华阳通">
										深圳市华阳通机电有限公司
									</option>
									<option value="华荣通信">
										东莞市华荣通信技术有限公司
									</option>
									<option value="鸿富锦精密">
										鸿富锦精密有限公司
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								质量缺陷:
							</th>
							<td align="left">
								<select name="failureSt.buhegeType">
									<option value="SJ">
										塑胶件(SJ)
									</option>
									<option value="WJ">
										五金件(WJ)
									</option>
									<option value="BJ">
										钣金件(BJ)
									</option>
									<option value="YC">
										原材料(YC)
									</option>
									<option value="JG">
										紧固件(JG)
									</option>
									<option value="BC">
										包材类(BC)
									</option>
									<option value="DD">
										电镀类(DD)
									</option>
									<option value="DZ">
										电子类(DZ)
									</option>
									<option value="PT">
										喷涂件(PT)
									</option>
									<option value="FL">
										辅料(FL)
									</option>
									<option value="ZJ">
										整机(ZJ)
									</option>
								</select>
								<select id="buhegeType" name="failureSt.buhegeId">
								</select>
								<select name="failureSt.buhegeTypeClass">
									<option value="CR">
										致命缺陷(CR)
									</option>
									<option value="MA">
										主要缺陷(MA)
									</option>
									<option value="MI">
										次要缺陷(MI)
									</option>
								</select>
							</td>
							<th align="right">
								批次数量:
							</th>
							<td align="left">
								<input name="failureSt.submitCount" value="" />
							</td>
							<th align="right">
								不良数量:
							</th>
							<td align="left">
								<input name="failureSt.failureCount" value="" />
							</td>
						</tr>
						<tr>
							<th align="right">
								工位:
							</th>
							<td align="left">
								<input name="failureSt.gongwei" value="" id="gongwei" />
								<font color="red">*</font>
							</td>
							<th align="right">
								目标值PPM:
							</th>
							<td align="left">
								<input name="failureSt.targetPPM" value="3500" id="targetPPM"
									onkeyup="getshuliang();" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="添加" class="input" />
								<input type="reset" value="重置" class="input" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<script type="text/javascript">
function getshuliang() {
	var weldingDefects = document.getElementById("weldingDefects").value;
	var strikeSize = document.getElementById("strikeSize").value;
	var flangeFlatness = document.getElementById("flangeFlatness").value;
	var tfb = document.getElementById("tfb").value;
	var airtight = document.getElementById("airtight").value;
	var exterior = document.getElementById("exterior").value;
	var other = document.getElementById("other").value;
	var failureCount = parseFloat(weldingDefects) + parseFloat(strikeSize)
			+ parseFloat(flangeFlatness) + parseFloat(tfb)
			+ parseFloat(airtight) + parseFloat(exterior) + parseFloat(other);
	document.getElementById("failureCount").value = failureCount;
}

function check() {
	if ($("#gongwei").val() == "") {
		alert("工位必须填写!");
		return false;
	}
}
function getbhgType() {
	$.ajax( {
		type : "POST",
		url : "BuHeGePinAction_findAllbuhegepinlist.action",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data == "error") {
				alert("出错了哦!")
			} else if (data != null) {
				$("#buhegeType").empty();
				$("#buhegespan").html("&nbsp;&nbsp;&nbsp;&nbsp;缺陷类型:");
				$(data).each(
						function() {
							$("#buhegeType").append(
									'<option value=' + this.id + '>'
											+ this.code + this.type
											+ '</option>');
						})
				$("#buhegeType_cp").show();
				$("#buhegeType").show();
				$("#buhegeType_class").show();
			}
		}
	})
}

$(function() {
	getbhgType();
})
</script>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
