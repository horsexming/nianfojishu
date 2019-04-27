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
					修改项目试制零件修改<br/>
					（update project try make part）
				</h3>
				<form action="tryMakeAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								项目<br/>（project）
							</th>
							<td>
							<input type="hidden" value="<s:property value="tryMake.id"/>" name="tryMake.id">
							  <input type="text" value="<s:property value="proTryMakeScore.proName"/>"  readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								零件号<br/>（part）：
							</th>
							<td>
								<input type="text" value="<s:property value="procardTemplate.markId"/>"  readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								项目阶段<br/>（total skill score）：
							</th>
							<td>
							
								<SELECT name="tryMake.projectStatu" onclick="selectStatus(<s:property value="tryMake.id"/>)" id="status">
								<option value="<s:property value="tryMake.projectStatu"/>">
								<s:property value="tryMake.projectStatu"/>
							  </option>
										
										</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								备注<br/>（difficult score）：
							</th>
							<td>
								<input type="text" name='tryMake.remark' value="<s:property value="tryMake.remark"/>" id="remark" />
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
function selectStatus(id) {
	$.ajax( {
		type : "POST",
		url : "tryMakeAction_ischangeStatus.action?tryMake.id="+id,
		dataType : 'json',
		success : function(data) {
			if(data.success){
				$("#status").empty();
				$("	<option> 立项 </option> <option> 跟踪 </option> <option> 首建样品 </option> " +
						"<option> 小批量 </option> <option> 大批量 </option> <option> 关闭 </option>").appendTo(
								"#status");
			}else{
				alert(data.message);
			}
		}
	});

}
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
</script>
	</body>
</html>
