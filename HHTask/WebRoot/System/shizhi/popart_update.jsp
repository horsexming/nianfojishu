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
						href="projectOrderAction_update.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改项目需求单零件<br/>
					（update project order part）
				</h3>
				<form action="projectOrderPartAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								项目名称
							<br />
							(project name)
							</th>
							<td>
							<input type="text" name="projectOrderPart.proName" id="proName"
							 value="<s:property value="projectOrderPart.proName"/>" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								月份
							<br />
							(month)
							</th>
							<td>
							<input type="text" name="projectOrderPart.month" id="month"
							 value="<s:property value="projectOrderPart.month"/>" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								零件号
							<br />
							（part number）：
							</th>
							<td>
							<input type="text" name="projectOrderPart.markId" id="markId"
							 value="<s:property value="projectOrderPart.markId"/>" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								零件数量
							<br />
							（part count）：
							</th>
							<td>
							<input type="hidden" name="projectOrderPart.id" id="partNum"
							 value="<s:property value="projectOrderPart.id"/>" />
							<input type="text" name="projectOrderPart.partNum" id="partNum"
							 value="<s:property value="projectOrderPart.partNum"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								说明
							<br />
							（remark）：
							</th>
							<td>
							<input type="text" name="projectOrderPart.remark" id="remark" 
							value="<s:property value="projectOrderPart.remark"/>"/>
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
	var proName = document.getElementById("proName").value;
	var markId = document.getElementById("markId").value;
	if(proName==""||proName==0){
		alert("请选择项目名称");
		return false;
	}
	if(markId==""||markId==0){
		alert("请选择零件号");
		return false;
	}
	
	
}
</script>
	</body>
</html>
