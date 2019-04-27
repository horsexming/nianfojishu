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
		<center>
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
						href="tryMakeAction_add.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					添加项目试制零件<br/>
					（add project try make part）
					<h3><font color="red">${errorMessage}</font></h3>
				</h3>
				<form action="tryMakeAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								项目<br/>（project）
							</th>
							<td>
							   <SELECT name="proTryMakeScore.id" id="projectid" onchange="getParts()">
							       <option value="0">
							       ----未选择----
							       </option>
							      <s:iterator value="ptmList" id="ptm">
							       <option value="<s:property value="#ptm.id"/>">
							       <s:property value="#ptm.proName"/>::<s:property value="#ptm.month"/>
							       </option> 
							      </s:iterator>
							   </SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								零件<br/>（part）：
							</th>
							<td>
								<SELECT name="procardTemplate.id" id="quotedPriceid">
							       <option value="0">
							       ----请先选择项目----
							       </option>
							   </SELECT>
							</td>
						</tr>
						
						<tr>
							<th align="right">
								备注<br/>（difficult score）：
							</th>
							<td>
								<input type="text" name="tryMake.remark" id="remark" />
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
	var projectid = document.getElementById("projectid").value;
	var quotedPriceid = document.getElementById("quotedPriceid").value;
	if (projectid== 0) {
		alert("请输选择项目");
		return false;
	}
	if (quotedPriceid==0) {
		alert("请输选择零件!");
		return false;
	}
}
function getParts() {
	var projectid=$("#projectid").val();
	if(projectid==""||projectid==0){
		return ;
	}
	$.ajax({
		type : "POST",
		url : "tryMakeAction_getParts.action",
		dataType : "json",
		data : {
		 'proTryMakeScore.id' : projectid
		},
		success : function(data){
			if(data.success){
				$("#quotedPriceid").empty();
				$("<option value='0'>--请选择--</option>").appendTo("#quotedPriceid");
				$(data.data).each(
					function() {
						$("<option value='" + this.id + "'>"
										+ this.markId
										+ "</option>").appendTo(
								"#quotedPriceid");
					});
			}else{
				alert(data.message);
			}
		}
	});
}
</script>
	</body>
</html>
