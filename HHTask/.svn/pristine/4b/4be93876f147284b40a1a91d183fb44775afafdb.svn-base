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
						href="processPRNScoreAction_toadd.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					添加工序PRN评分<br/>
					（add process PRN score）
				</h3>
				<form action="processPRNScoreAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								工序<br/>（process）
							</th>
							<td>
							
							  <SELECT name="processPRNScore.processName" id="pname" >	
							    <option value="未选择">----未选择----</option>					  
							    <s:iterator value="processList" var="processName">
							     <option value="<s:property value='#processName'/>">
							      <s:property value="#processName"/>
							     </option>
							    </s:iterator>
							  </SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								评分内容1<br/>（content1）：
							</th>
							<td>
								<input type="text" name="processPRNScore.content1" id="content1" />
							</td>
						</tr>
						<tr>
							<th align="right">
								评分内容2<br/>（content2）：
							</th>
							<td>
								<input type="text" name="processPRNScore.content2" id="content2" />
							</td>
						</tr>
						<tr>
							<th align="right">
								评分内容3<br/>（content3）：
							</th>
							<td>
								<input type="text" name="processPRNScore.content3" id="content3" />
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
			function isLinked() {
function validate() {
	var pname = document.getElementById("pname").value;
	var content1 = document.getElementById("content1").value;
	var content2 = document.getElementById("content2").value;
	var content3 = document.getElementById("content3").value;
	if (pname==""||pname == "未选择") {
		alert("请选择工序!");
		return false;
	}
	if (content1 == "") {
		alert("请输入评分内容1!");
		return false;
	}else if(isNaN(content1)){
	  alert("评分内容1栏请输入数字!");
		return false;
	}
	if (content2 == "") {
		alert("请输入评分内容2!");
		return false;
	}else if(isNaN(content2)){
	  alert("评分内容2请输入数字!");
		return false;
	}
	if (content3 == "") {
		alert("请输入评分内容3!");
		return false;
	}else if(isNaN(content3)){
	  alert("评分内容3栏请输入数字!");
		return false;
	}
}
</script>
	</body>
</html>
