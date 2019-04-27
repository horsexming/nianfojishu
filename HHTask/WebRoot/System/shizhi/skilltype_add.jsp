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
						href="<%=path%>/System/shizhi/skilltype_add.jsp"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					添加技能分类<br/>
					（add skillscore）
				</h3>
				<form action="skillTypeAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								技能类别<br/>（skill type）
							</th>
							<td>
								<input type="text" name="skillType.name" id="ssname" />
							</td>
						</tr>
						<tr>
							<th align="right">
								技能最小分值<br/>（skill min score）：
							</th>
							<td>
								<input type="text" name="skillType.minscore" id="minscore" />
							</td>
						</tr>
						<tr>
							<th align="right">
								技能最大分值<br/>（skill max score）：
							</th>
							<td>
								<input type="text" name="skillType.maxscore" id="maxscore" />
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
	var stname = document.getElementById("stname").value;
	var minscore = document.getElementById("minscore").value;
	var maxscore = document.getElementById("maxscore").value;
	if (ssname == "") {
		alert("请输入难度名称!");
		return false;
	}
	if(minscore != ""&isNaN(minscore)){
	  alert("难度最大分值栏请输入数字!");
		return false;
	}
	if(maxscore != ""&isNaN(maxscore)){
	  alert("难度最大分值栏请输入数字!");
		return false;
	}
}
</script>
	</body>
</html>
