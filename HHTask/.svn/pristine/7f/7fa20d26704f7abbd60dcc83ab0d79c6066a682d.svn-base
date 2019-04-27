<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<h5>
					${companyInfo.name}
				</h5>
				<!-- singleCarAction_addSingleCar.action --> 
				<form id=testFrom  action="singleCarAction_addSingleCar.action?tag=<s:property value="#parameters.tag"/>" method="post"  onsubmit="return check()" >
					<table style="width: 98%;" class="table">
						<tr>
							<th colspan="4"  >
									申请用车单
							</th>
							</tr>
							<tr>
							<th style="width: 15%;">
									用车部门
							</th>
							<th align="left">
									<select name="singleCar.var_dept"  id="dept">
									<option value="">--请选择部门--</option>
									</select>
							</th>
							<th style="width: 15%;">
									用车时间
							</th>
							<th align="left">
									<input type="text" id="car_date" class="Wdate" name="singleCar.car_date"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</th>
							</tr>
							<tr>
								<th style="width: 15%;">
									用车事由
								</th>
								<th align="left">
									<input type="text" id="car_content" name="singleCar.car_content" value="">								
								</th>
								<th style="width: 15%;">
									人数及吨位
								</th>
								<th align="left">
									<input type="text" name="singleCar.car_amount" value=""   onkeyup="this.value=this.value.replace(/[^\d]/g,'')">								
								</th>
							</tr>
							<tr>
							<th>
								到达地点
							</th>
							<th colspan="3" align="left">
								<input type="text"  id="car_place" name="singleCar.car_place"  size="70%">
							</th>
							</tr>
							<tr>
							<th style="width: 15%;">
							收费标准
							</th>
							<th align="left">
								<input type="text" name="singleCar.charges"  onkeyup="if(isNaN(value))execCommand('undo')">
							</th>
							<th style="width: 15%;">
							进出库时间
							</th>
							<th align="left">
								<input type="text" class="Wdate"
								name="singleCar.comeoroutdate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</th>
							</tr>
							<tr>
							<th style="width: 15%;">
							出车前里程表
							</th>
							<th align="left">
								<input type="text" name="singleCar.beforeodometer" onkeyup="if(isNaN(value))execCommand('undo')">
							</th>
							<th style="width: 15%;">
							回车后里程表
							</th>
							<th align="left">
								 	<input type="text" name="singleCar.endodometer" onkeyup="if(isNaN(value))execCommand('undo')">
							</th>
							</tr>
							
							<tr>
							<th style="width: 15%;">
							公里数
							</th>
							<th align="left">
								<input type="text" name="singleCar.kilometers" onkeyup="if(isNaN(value))execCommand('undo')">
							</th>
							<th style="width: 15%;">
							驾驶员姓名
							</th>
							<th align="left">
								 	<input type="text" name="singleCar.pilotname">
							</th>
							</tr>
							<tr>
							<th style="width: 15%;">
							车号
							</th>
							<th align="left">
								<input type="text" name="singleCar.car_number">
							</th>
							<th style="width: 15%;">
							用车单位领导
							</th>
							<th align="left">
								 	<input type="text" name="singleCar.unit_leading">
							</th>
							</tr>
							<tr>
							<th style="width: 15%;">
							早出晚归
							</th>
							<th align="left">
								<input type="text" name="singleCar.zcwg">
							</th>
							<th style="width: 15%;">
							周末及厂休
							</th>
							<th align="left">
								 	<input type="text" name="singleCar.zmcx">
							</th>
							</tr>
						<tr>
							<th>
							出车类型
							</th>
							<th align="left">
								 <input type="radio"  id="aaa" name="singleCar.singlecarType" value="省内"  checked="checked"/>
									<label for="aaa">
										省内
									</label>
									<input type="radio"  id="bbb"  name="singleCar.singlecarType"  value="省外" />
									<label for="bbb">
										省外
									</label>
							</th>
							<th>
								备注
							</th>
							<th align="left">
								  <input type="text" name="singleCar.remark">
							</th>
						</tr>
						<tr id="button1">
								<td colspan="6" align="center">
									<input id="reviewButton" name="reviewButton" type="submit" value="提交"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
									<input type="reset" value="取消"
										style="width: 60px; height: 40px;" align="top">
								</td>
							</tr>
					
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$(document).ready(function() {
	createDept("dept");
	var successMessage = "${successMessage}";
	if (successMessage != "") {
		alert(successMessage);
	}
})

//提交前校验
function check(){
	var dept = document.getElementById("dept");//用车部门
	var car_date = document.getElementById("car_date");//用车时间 
	var car_content = document.getElementById("car_content");//用车事由
	var car_place = document.getElementById("car_place");//到达地点
	if(dept.value==""){
  			alert("请选择用车部门!");
  			dept.focus();
  			return false;
  		}
		if(car_date.value==""){
  			alert("请填写用时间!");
  			car_date.focus();
  			return false;
  		}	if(car_content.value==""){
  			alert("请填写用车事由!");
  			car_content.focus();
  			return false;
  		}
  			if(car_place.value==""){
  			alert("请填写到达地点!");
  			car_place.focus();
  			return false;
  		}
	
	
}
	
</script>

</html>
