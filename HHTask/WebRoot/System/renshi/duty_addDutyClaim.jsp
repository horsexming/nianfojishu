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
					<a href="DutyClaimAction!findAllDutyClaim.action"
						style="color: #ffffff">查看职位胜任信息
						<br/>
						View Jobs competent information
						</a>
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新
						<br/>
						Refresh</a>
				</div>
			</div>
			
			<div align="center">
				<form id="addForm" action="DutyClaimAction!addDutyClaim.action"
					method="post" onsubmit="return chageDutyForm()">
					<input name="dutyClaim.claimStatus" value="标准" type="hidden" />
					<input name="dutyClaim.floor" value="1" type="hidden" />
					<div style="color: red">
						<h3>
							${successMessage}
						</h3>
					</div>
					<table class="table">
						<tr>
							<th colspan="2" align="center">
								<br />
								<h1>
									添加职位胜任要求
									<br/>
									Add Jobs competency requirements
								</h1>
							</th>
						</tr>
						<tr>
							<th>
								部门:
								<br/>
								Department:
							</th>
							<td>
								<select id="dept" name="dutyClaim.deptClaim"
									style="width: 200px;" onmouseover="createDept('dept')">
								</select>
							</td>
						</tr>
						<tr>
							<th>
								职位:
								<br/>
								Jobs:
							</th>
							<td>
								<select id="duty" name="dutyClaim.duty" style="width: 200px;">
									<option value="请选择职位">
										请选择职位
									</option>
									<s:iterator id="pageDuty" value="list">
										<option value="${pageDuty}">
											${pageDuty}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								学历要求:
								<br/>
								Education:
								<br/>
								
							</th>
							<td>
								<select id="eduClaim" name="dutyClaim.eduClaim"
									style="width: 200px;">
									<option value="请选择学历">
										请选择学历
									</option>
									<option value="小学以上">
										小学以上
									</option>
									<option value="初中以上">
										初中以上
									</option>
									<option value="高中以上">
										高中以上
									</option>
									<option value="中专以上">
										中专以上
									</option>
									<option value="大专以上">
										大专以上
									</option>
									<option value="本科以上">
										本科以上
									</option>
									<option value="硕士以上">
										硕士以上
									</option>
									<option value="博士以上">
										博士以上
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								专业要求:
								<br/>
								Professional requirements:
							</th>
							<td>
								<input name="dutyClaim.speClaim" style="width: 400PX;" />
							</td>
						</tr>
						<tr>
							<th>
								经验要求:
								<br/>
								Experience Requirements:
							</th>
							<td>
								<input name="dutyClaim.expClaim" style="width: 400PX;" />
							</td>
						</tr>
						<tr>
							<th>
								技能要求:
								<br/>
								Skills required:
							</th>
							<td>
								1、
								<input name="dutyClaim.skillClaim" style="width: 400PX;" />
								<input type="hidden" name="dutyClaim.skillClaim"
									style="width: 10PX;" value="|" />
								<input type="button" id="fileButton_1"
									onclick="uploadFile(this,1,'dutyClaim.skillClaim')"
									value="添加技能要求(Adding skill requirements)">
								<div id="fileDiv_1" style="display: none;">

								</div>
							</td>
						</tr>
						<tr>
							<th>
								素质要求:
								<br/>
								Qualifications:
							</th>
							<td>
								1、
								<input name="dutyClaim.quaClaim" style="width: 400PX;" />
								<input type="hidden" name="dutyClaim.quaClaim"
									style="width: 10PX;" value="|" />
								<input type="button" id="fileButton_2"
									onclick="uploadFile(this,2,'dutyClaim.quaClaim')"
									value="添加素质要求(Adding quality requirements)">
								<div id="fileDiv_2" style="display: none;">

								</div>
							</td>
						</tr>
						<tr>
							<th>
								上岗要求:
								<br/>
								Induction requirements:
							</th>
							<td>
								<input name="dutyClaim.posClaim" style="width: 400PX;" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" style="width: 80px; height: 50px;"
									value="添加(Add)" />
								<input type="reset" style="width: 80px; height: 50px;"
									value="重置(Reset)" />
							</td>
						</tr>
					</table>
				</form>
				<form id="updateForm"
					action="DutyClaimAction!updateDutyClaim.action" method="post" style="display: none;">
					<input name="dutyClaim.claimStatus"
						value="${dutyClaim.claimStatus}" type="hidden" />
					<input name="dutyClaim.floor" value="${dutyClaim.floor}"
						type="hidden" />
					<input name="id" value="${dutyClaim.id}" type="hidden" />
					<input name="dutyClaim.id" value="${dutyClaim.id}" type="hidden" />
					<div style="color: red">
						<h3>
							${successMessage}
						</h3>
					</div>
					<table class="table">
						<tr>
							<th colspan="2" align="center">
								<br />
								<h1>
									修改职位胜任要求
									<br/>
									Modify job competency requirements
								</h1>
							</th>
						</tr>
						<tr>
							<th>
								部门:
								<br/>
								Department:
							</th>
							<td>
								<select id="dept2" name="dutyClaim.deptClaim"
									style="width: 200px;" onmouseover="createDept('dept2')">
									<option value="${dutyClaim.deptClaim}">
										${dutyClaim.deptClaim}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								职位:
								<br/>
								Position:
							</th>
							<td>
								<select id="duty" name="dutyClaim.duty" style="width: 200px;">
									<option value="${dutyClaim.duty}">
										${dutyClaim.duty}
									</option>
									<s:iterator id="pageDuty" value="list">
										<option value="${pageDuty}">
											${pageDuty}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								学历要求:
								<br/>
								Education:
							</th>
							<td>
								<select id="eduClaim" name="dutyClaim.eduClaim"
									style="width: 200px;">
									<option value="${dutyClaim.eduClaim}">
										${dutyClaim.eduClaim}
									</option>
									<option value="小学以上">
										小学以上
									</option>
									<option value="初中以上">
										初中以上
									</option>
									<option value="高中以上">
										高中以上
									</option>
									<option value="中专以上">
										中专以上
									</option>
									<option value="大专以上">
										大专以上
									</option>
									<option value="本科以上">
										本科以上
									</option>
									<option value="硕士以上">
										硕士以上
									</option>
									<option value="博士以上">
										博士以上
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								专业要求:
								<br/>
								Professional requirements:
							</th>
							<td>
								<input name="dutyClaim.speClaim" style="width: 400PX;"
									value="${dutyClaim.speClaim}" />
							</td>
						</tr>
						<tr>
							<th>
								经验要求:
								<br/>
								Experience Requirements:
							</th>
							<td>
								<input name="dutyClaim.expClaim" style="width: 400PX;"
									value="${dutyClaim.expClaim}" />
							</td>
						</tr>
						<tr>
							<th>
								技能要求:
								<br/>
								Skills required:
							</th>
							<td>
								<s:generator val="dutyClaim.skillClaim" separator="|" id="iter1">
								</s:generator>
								<s:iterator status="st" value="#request.iter1" id="name">
									<span title="要求技能${st.index+1}:${name}"> ${st.index+1}</span>、
								<input name="dutyClaim.skillClaim" style="width: 400PX;"
										value="${name}" />
									<input type="hidden" name="dutyClaim.skillClaim"
										style="width: 10PX;" value="|" />
									<s:if test="#st.first">
										<input type="button" id="fileButton_1"
											onclick="uploadFile(this,3,'dutyClaim.skillClaim')"
											value="添加技能要求(Adding skill requirements)">
									</s:if>
									<br />
								</s:iterator>

								<div id="fileDiv_3" style="display: none;">

								</div>
							</td>
						</tr>
						<tr>
							<th>
								素质要求:
								<br/>
								Qualifications:
							</th>
							<td>
								<s:generator val="dutyClaim.quaClaim" separator="|" id="iter1">
								</s:generator>
								<s:iterator status="st" value="#request.iter1" id="name">
									<span title="要求技能${st.index+1}:${name}"> ${st.index+1}</span>、
								<input name="dutyClaim.quaClaim" style="width: 400PX;"
										value="${name}" />
									<input type="hidden" name="dutyClaim.quaClaim"
										style="width: 10PX;" value="|" />
									<s:if test="#st.first">
										<input type="button" id="fileButton_1"
											onclick="uploadFile(this,4,'dutyClaim.quaClaim')"
											value="添加素质要求(Adding quality requirements)">
									</s:if>
									<br />
								</s:iterator>

								<div id="fileDiv_4" style="display: none;">

								</div>
							</td>
						</tr>
						<tr>
							<th>
								上岗要求:
								<br/>
								Induction requirements:
							</th>
							<td>
								<input name="dutyClaim.posClaim" style="width: 400PX;" value="${dutyClaim.posClaim}"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" style="width: 80px; height: 50px;"
									value="修改(Modify)" />
								<input type="reset" style="width: 80px; height: 50px;"
									value="重置(Reset)" />
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
var fileDivHTML = "";
var count = 0;
var count1 = "${dutyClaim.skillClaim}" == "" ? 1 : 2;
var count2 = "${dutyClaim.skillClaim}" == "" ? 1 : 2;
var num = 0;
function uploadFile(obj, few, name) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	fileDiv.style.display = "block";
	if (few == 1) {
		count1++;
		num = count1;
	} else {
		count2++;
		num = count2;
	}
	fileDivHTML = "<div id='file" + count + "'>" + num + "、 <input name='"
			+ name + "' style='width: 400PX;'>" + "<input type='hidden' name='"
			+ name
			+ "' style='width: 10PX;' value='|' /><a href='javascript:delFile("
			+ count + "," + few + ")'>删除(Delete)</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (few == 1) {
		count1--;
		num = count1;
	} else {
		count2--;
		num = count2;
	}
	if (num <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "添加技能要求";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}

//检验表单
function chageDutyForm() {
	var duty = $("#duty");//职位
	var eduClaim = $("#eduClaim");//学历
	var dept = $("#dept");//部门
	if (dept.val() == "") {
		alert("请选择部门");
		duty.focus();
		return false;
	} else if (duty.val() == "请选择职位") {
		alert("请选择职位");
		duty.focus();
		return false;
	} else if (eduClaim.val() == "请选择学历") {
		alert("请选择学历");
		eduClaim.focus();
		return false;
	}
	return true;
}

//布局调控
$(function() {
	var dutyClaim = "${dutyClaim}";
	if (dutyClaim != "") {
		$("#addForm").hide();
		$("#updateForm").slideDown("slow");
	}
});
</script>
	</body>
</html>
