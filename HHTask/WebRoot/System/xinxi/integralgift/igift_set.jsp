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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font id="font_zi" color="red" size="5">${errorMessage}</font>
				<h2>
					抽奖时间设置
				</h2>
				<form action="IntegralGiftAction_addigSet.action" method="post"  onsubmit="return check()">
					<table>
						<tr>
							<th>
								抽奖开始时间:
							</th>
							<td>
								<input id="starttime1" class="Wdate" type="text"
											name="igSet.beginTime" id="beginTime"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
											 />
							</td>
						</tr>
						<tr>
							<th>
								抽奖结束时间:
							</th>
							<td>
								<input id="starttime1" class="Wdate" type="text"
											name="igSet.endTime" id="endTime"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
											 />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="设置" id="sub"class="input"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
function check(){
	var endTime = $("#endTime").val();
	var beginTime = $("#beginTime").val();
	if(beginTime!=null && beginTime == ""){
		$("#font_zi").html("请填写抽奖开始时间");
		$("#beginTime").focus();
		return  false;
	}else if(endTime!=null && endTime == ""){
		$("#font_zi").html("请填写抽奖结束时间");
		$("#endTime").focus();
		return false;
	}
	document.getElementById("sub").disabled ="disabled";
	return true;
}
	
	
	</script>	
	</body>
</html>
