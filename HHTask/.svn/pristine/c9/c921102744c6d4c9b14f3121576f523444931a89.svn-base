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
			<div align="center">
				<form action="nianXiuAction!updatekq.action" method="post"
					onsubmit="return adddata()">
					<table class="table" style="width: 70%">
						<tr>
							<th align="center" colspan="2">
								个人 ${kaoQin.yuefen}考勤调整
							</th>
						</tr>
						<tr>
							<th align="right">
								卡号:
							</th>
							<td>
								${kaoQin.carId}
							</td>
						</tr>
						<tr>
							<th align="right">
								姓名:
							</th>
							<td>
								${kaoQin.name}
							</td>
						</tr>
						<tr>
							<th align="right">
								部门:<input type="hidden" name="kaoQin.id"
									value="${kaoQin.id}" />
							</th>
							<td>
								${kaoQin.dept}
							</td>
						</tr>
						<tr>
							<th align="right">
								出勤天数:
							</th>
							<td>
								<input type="text" id="chuqintianshu" name="kaoQin.chuqintianshu"
									value="${kaoQin.chuqintianshu}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								应该出勤天数:
							</th>
							<td>
								${kaoQin.yingchuqin}
							</td>
						</tr>
						<tr>
							<th align="right">
								事假:
							</th>
							<td>
								<input type="text" id="shijia" name="kaoQin.shijia"
									value="${kaoQin.shijia}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								矿工:
							</th>
							<td>
								<input type="text" id="kuanggong" name="kaoQin.kuanggong"
									value="${kaoQin.kuanggong}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								病假:
							</th>
							<td>
								<input type="text" id="bingjia" name="kaoQin.bingjia"
									value="${kaoQin.bingjia}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								年休:
							</th>
							<td>
								<input type="text" id="nianxiujia" name="kaoQin.nianxiujia"
									value="${kaoQin.nianxiujia}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								调休.换休:
							</th>
							<td>
								<input type="text" id="tiaoxiu" name="kaoQin.tiaoxiu"
									value="${kaoQin.tiaoxiu}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								公休:
							</th>
							<td>
								<input type="text" id="gongxiu" name="kaoQin.gongxiu"
									value="${kaoQin.gongxiu}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								产假/陪护假:
							</th>
							<td>
								<input type="text" id="chanjia" name="kaoQin.chanjia"
									value="${kaoQin.chanjia}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								婚/丧假:
							</th>
							<td>
								<input type="text" id="huncangjia" name="kaoQin.huncangjia"
									value="${kaoQin.huncangjia}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								迟到:
							</th>
							<td>
								<input type="text" id="lateTime" name="kaoQin.lateTime"
									value="${kaoQin.lateTime}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								早退:
							</th>
							<td>
								<input type="text" id="earlyTime" name="kaoQin.earlyTime"
									value="${kaoQin.earlyTime}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								其他:
							</th>
							<td>
								<input type="text" id="qita" name="kaoQin.qita"
									value="${kaoQin.qita}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								加班.小时:
							</th>
							<td>
								<input type="text" id="jiaban" name="kaoQin.jiaban"
									value="${kaoQin.jiaban}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								夜班:
							</th>
							<td>
								<input type="text" id="yeban" name="kaoQin.yeban"
									value="${kaoQin.yeban}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								公出:
							</th>
							<td>
								<input type="text" id="gongchu" name="kaoQin.gongchu"
									value="${kaoQin.gongchu}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								月份:
							</th>
							<td>
								${kaoQin.yuefen}
							</td>
						</tr>
						<tr>
							<th align="right">
								备注:
							</th>
							<td>
								<input type="text" id="beizhu" name="kaoQin.beizhu"
									value="${kaoQin.beizhu}" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								说明:
							</th>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<textarea rows="4" cols="100%" name="kaoQin.shuoming"
									id="shuoming">${kaoQin.shuoming}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="修改" style="width: 58px;height: 30px;"/> &nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置" style="width: 58px;height: 30px;"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function adddata()() {
	if (!validateText("chuqintianshu", "出勤天数")) {
		return false;
	}
	if (!validateText("shijia", "事假")) {
		return false;
	}
	if (!validateText("kuanggong", "旷工")) {
		return false;
	}
	if (!validateText("bingjia", "病假")) {
		return false;
	}
	if (!validateText("nianxiujia", "年休假")) {
		return false;
	}
	if (!validateText("tiaoxiu", "调休")) {
		return false;
	}
	if (!validateText("gongxiu", "公休")) {
		return false;
	}
	if (!validateText("chanjia", "产假/陪护假")) {
		return false;
	}
	if (!validateText("huncangjia", "婚/丧假")) {
		return false;
	}
	if (!validateText("qita", "其他")) {
		return false;
	}
	if (!validateText("lateTime", "迟到")) {
		return false;
	}
	if (!validateText("earlyTime", "早退")) {
		return false;
	}
	if (!validateText("jiaban", "加班")) {
		return false;
	}
	if (!validateText("yeban", "夜班")) {
		return false;
	}
	if (!validateText("gongchu", "公出")) {
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
	</SCRIPT>
	</body>
</html>
