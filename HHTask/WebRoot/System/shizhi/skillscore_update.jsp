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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改技能系数<br/>
					（update skillscore）
				</h3>
				<form action="skillScoreAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								技能级别<br/>（skill lv）
							</th>
							<td>
							<input type="hidden" name="skillScore.id"  value="<s:property value="skillScore.id"/>" />
								<input type="text" name="skillScore.name" id="ssname" value="<s:property value="skillScore.name"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								技能分值<br/>（skill score）：
							</th>
							<td>
								<input type="text" name="skillScore.score" id="ssscore" value="<s:property value="skillScore.score"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								总体技能分值或系数<br/>（total skill score）：
							</th>
							<td>
								<input type="text" name="skillScore.totalscore" id="totalscore" value="<s:property value="skillScore.totalscore"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								强度或复杂系数<br/>（difficult score）：
							</th>
							<td>
								<input type="text" name="skillScore.difficultScore" id="difficultScore" value="<s:property value="skillScore.difficultScore"/>"/>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var ssname = document.getElementById("ssname").value;
	var ssscore = document.getElementById("ssscore").value;
	var totalscore = document.getElementById("totalscore").value;
	var difficultScore = document.getElementById("difficultScore").value;
	if (ssname == "") {
		alert("请输入难度名称!");
		return false;
	}
	if (ssscore == "") {
		alert("请输入难度分值!");
		return false;
	}else if(isNaN(ssscore)){
	  alert("难度分值栏请输入数字!");
		return false;
	}
	if (totalscore == "") {
		alert("请输入总体技能分值或系数!");
		return false;
	}else if(isNaN(totalscore)){
	  alert("总体技能分值或系数栏请输入数字!");
		return false;
	}
	if (difficultScore == "") {
		alert("请输入强度或复杂系数!");
		return false;
	}else if(isNaN(difficultScore)){
	  alert("强度或复杂系数栏请输入数字!");
		return false;
	}

}
</script>
	</body>
</html>
