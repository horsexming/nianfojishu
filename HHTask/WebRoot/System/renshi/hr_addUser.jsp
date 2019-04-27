<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<script type="text/javascript">
//表单检查
function chackForm() {
	var userName = document.getElementById("userName");//名称
	var nation = document.getElementById("nation");//民族	
	var birthplace = document.getElementById("birthplace");//籍贯
	var residence = document.getElementById("residence");//户籍
	var education = document.getElementById("education");//学历
	var uid = document.getElementById("uid");//身份证号
	var cardId = document.getElementById("cardId");//卡号
	var code = document.getElementById("code");//工号
	var dept = document.getElementById("dept");//部门
	var post = document.getElementById("post");//部门
	var joined = document.getElementById("joined");//入职时间
	var tryDays = document.getElementById("tryDays");//试用期
	var phoneNumber = document.getElementById("phoneNumber");//手机号
	var presentAddress = document.getElementById("presentAddress");//现住址
	if (userName.value == "") {
		alert("姓名不能为空!");
		userName.focus();
		return false;
	} else if (nation.value == "") {
		alert("民族不能为空!");
		nation.focus();
		return false;

	} else if (birthplace.value == "") {
		alert("籍贯不能为空!");
		birthplace.focus();
		return false;
	} else if (residence.value == "") {
		alert("户籍不能为空!");
		residence.focus();
		return false;
	} else if (education.value == "") {
		alert("学历不能为空!");
		education.focus();
		return false;
	} else if (uid.value == "") {
		alert("身份证号不能为空!");
		uid.focus();
		return false;
	} else if (cardId.value == "") {
		alert("卡号不能为空!");
		cardId.focus();
		return false;
	} else if (code.value == "") {
		alert("工号不能为空!");
		code.focus();
		return false;
	} else if (dept.value == "") {
		alert("请选择部门!");
		dept.focus();
		return false;
	} else if (post.value == "") {
		alert("请选择职称!");
		post.focus();
		return false;
	} else if (joined.value == "") {
		alert("入职时间不能为空!")
		joined.focus();
		return false;
	} else if (tryDays.value == "") {
		alert("试用期不能为空!")
		tryDays.focus();
		return false;
	} else if (phoneNumber.value == "") {
		alert("手机号不能为空!");
		phoneNumber.focus();
		return false;
	} else if (presentAddress.value == "") {
		alert("现住址不能为空!");
		presentAddress.focus();
		return false;
	} else {
		return true;
	}
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng">
				<!-- 入职流程导航栏 -->
				<div align="center" style="width: 100%;">
					<div class="style1"
						style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 添加员工(Adding staff) </font>
					</div>
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 上传简历(Upload Resume)</font>
					</div>
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 签订合同 (Contract)</font>
					</div>
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 薪资信息(Salary Information) </font>
					</div>
				</div>

				<div align="center">
					<br />
					<form action="AddUserAction!addUser.action" method="post"
						enctype="multipart/form-data">	
						<table>
							<tr>
								<th>
									人员导入:
								</th>
								<th>
									<input type="file" name="addusers">
								</th>
								<td>
									<input type="submit" value="导入">
									<a href="<%=basePath%>/upload/file/download/usersTemplate.xls">导入模版下载</a>
									<a href="FileViewAction.action?FilePath=/upload/file/download/usersTemplate.xls&Refresh=true">/预览</a>
								</td>
							</tr>
						</table>
					</form>
					<form action="UsersAction!addUser.action" method="post"
						onsubmit="return chackForm()" enctype="multipart/form-data">
						<table width="100%" border="0" class="table">
							<tr>
								<th colspan="6" align="center">
									新员工基本信息登记
									<br />
									Basic information on the registration of new employees
									<input type="hidden" name="interviewLog.id"
										value="${interviewLog.id}" />
								</th>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									个人资料
									<br />
									Personal Information
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									姓名:
									<br />
									Name:
								</th>
								<td>
									<input id="userName" name="user.name" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${interviewLog.name}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									性别:
									<br />
									Gender:
								</th>
								<td>
									<s:if test="interviewLog.sex!=null">
										<input type="text" name="user.sex" value="${interviewLog.sex}">
									</s:if>
									<s:else>
										<input type="radio" name="user.sex" value="男"
											checked="checked">
									男(man)
									<input type="radio" name="user.sex" value="女">
									女(Women)
									</s:else>
									<font color="red"> *</font>
								</td>
								<th align="right">
									民族:
									<br />
									National:
								</th>
								<td>
									<select id="nation" name="user.nation" style="width: 150px;">
										<option value="">
											请选择民族
										</option>
										<option value="汉族">
											汉族
										</option>
										<option value="蒙古族">
											蒙古族
										</option>
										<option value="鄂温克族">
											鄂温克族
										</option>
										<option value="达斡尔族">
											达斡尔族
										</option>
										<option value="赫哲族">
											赫哲族
										</option>
										<option value="朝鲜族">
											朝鲜族
										</option>
										<option value="满族">
											满族
										</option>
										<option value="纳西族">
											纳西族
										</option>
										<option value="藏族">
											藏族
										</option>
										<option value="僳僳族">
											僳僳族
										</option>
										<option value="彝族">
											彝族
										</option>
										<option value="普米族">
											普米族
										</option>
										<option value="白族">
											白族
										</option>
										<option value="独龙族">
											独龙族
										</option>
										<option value="怒族">
											怒族
										</option>
										<option value="阿昌族">
											阿昌族
										</option>
										<option value="景颇族">
											景颇族
										</option>
										<option value="德昂族">
											德昂族
										</option>
										<option value="佤族">
											佤族
										</option>
										<option value="拉祜族">
											拉祜族
										</option>
										<option value="布朗族">
											布朗族
										</option>
										<option value="基诺族">
											基诺族
										</option>
										<option value="傣族">
											傣族
										</option>
										<option value="哈尼族">
											哈尼族
										</option>
										<option value="门巴族">
											门巴族
										</option>
										<option value="珞巴族">
											珞巴族
										</option>
										<option value="塔吉克族">
											塔吉克族
										</option>
										<option value="柯尔克孜族">
											柯尔克孜族
										</option>
										<option value="哈萨克族">
											哈萨克族
										</option>
										<option value="乌孜别克族">
											乌孜别克族
										</option>
										<option value="塔塔尔族">
											塔塔尔族
										</option>
										<option value="俄罗斯族">
											俄罗斯族
										</option>
										<option value="锡伯族">
											锡伯族
										</option>
										<option value="维吾尔族">
											维吾尔族
										</option>
										<option value="裕固族">
											裕固族
										</option>
										<option value="土族">
											土族
										</option>
										<option value="撒拉族">
											撒拉族
										</option>
										<option value="保安族">
											保安族
										</option>
										<option value="东乡族">
											东乡族
										</option>
										<option value="回族">
											回族
										</option>
										<option value="畲族">
											畲族
										</option>
										<option value="黎族">
											黎族
										</option>
										<option value="京族">
											京族
										</option>
										<option value="毛南族">
											毛南族
										</option>
										<option value="壮族">
											壮族
										</option>
										<option value="仫佬族">
											仫佬族
										</option>
										<option value="瑶族">
											瑶族
										</option>
										<option value="侗族">
											侗族
										</option>
										<option value="苗族">
											苗族
										</option>
										<option value="水族">
											水族
										</option>
										<option value="布依族">
											布依族
										</option>
										<option value="仡佬族">
											仡佬族
										</option>
										<option value="土家族">
											土家族
										</option>
										<option value="羌族">
											羌族
										</option>
										<option value="高山族">
											高山族
										</option>
									</select>
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									籍贯:
									<br />
									Birthplace:
								</th>
								<td>
									<input id="birthplace" name="user.birthplace"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
									<font color="red"> *</font>
								</td>
								<th align="right">
									户籍:
									<br />
									Residence:
								</th>
								<td>
									<input id="residence" name="user.residence"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
									<font color="red"> *</font>
								</td>
								<th align="right">
									出生年月:
									<br />
									Date of Birth:
								</th>
								<td>
									<input class="Wdate" type="text" name="user.bothday"
										value="${interviewLog.birthday}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<th align="right">
									学历:
									<br />
									Education:
								</th>
								<td>
									<select id="education" name="user.education"
										style="width: 150px;">
										<option value="${interviewLog.gereneducation}">
											${interviewLog.gereneducation}
										</option>
										<option value="">
											请选择学历
										</option>
										<option value="小学">
											小学
										</option>
										<option value="初中">
											初中
										</option>
										<option value="高中">
											高中
										</option>
										<option value="中专">
											中专
										</option>
										<option value="大专">
											大专
										</option>
										<option value="本科">
											本科
										</option>
										<option value="硕士">
											硕士
										</option>
										<option value="博士">
											博士
										</option>
									</select>
									<font color="red"> *</font>
								</td>
								<th align="right">
									身份证号:
									<br />
									ID number:
								</th>
								<td>
									<input id="uid"
										onkeyup="document.getElementById('check').href='http://qq.ip138.com/idsearch/index.asp?action=idcard&userid='+this.value;"
										name="user.uid" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine');document.getElementById('check').href='http://qq.ip138.com/idsearch/index.asp?action=idcard&userid='+this.value;"
										maxlength="18" value="${interviewLog.cardID}">
									<font color="red"> *<a id="check" target="_blank"
										href="http://qq.ip138.com/idsearch/index.asp?action=idcard">(验)</a>
									</font>
								</td>
								<th align="right">
									员工性质:
									<br />
									Employees nature:
								</th>
								<td>
									<input type="radio" name="user.password.staffNature" value="正式"
										checked="checked">
									正式(Official)
									<input title="劳务派遣人员,无需签订合同" type="radio"
										name="user.password.staffNature" value="劳务">
									劳务(Labor)
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									户籍性质:
									<br />
									Household nature:
								</th>
								<td>
									<s:if test="interviewLog.censusStatus!=null">
										<input type="text" name="user.password.censusNature"
											value="${interviewLog.censusStatus}" />
									</s:if>
									<s:else>
										<input type="radio" name="user.password.censusNature"
											value="农业" checked="checked">
										农业(Agriculture)
										<input type="radio" name="user.password.censusNature"
												value="非农">
										非农(Nonagricultural)
										</s:else>
									<font color="red"> *</font>
								</td>
								<th align="right">
									婚姻状况:
									<br />
									marital status:
								</th>
								<td>
									<input type="text" name="user.password.maritalStatus" 
									class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								<th align="right">
									政治面貌:
									<br />
									political aspects:
								</th>
								<td>
									<input type="text" name="user.password.politicalAspects" 
									class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
							</tr>
							<tr>
								<th align="right">
									毕业院校:
									<br />
									Graduation Institutions:
								</th>
								<td>
									<input type="text" name="user.password.graduationInstitutions"
									 class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								<th align="right">
									所学专业:
									<br />
									speciality:
								</th>
								<td>
									<input type="text" name="user.password.speciality"
									 class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								<th align="right">
									毕业日期:
									<br />
									date of graduation:
								</th>
								<td>
									<input type="text" name="user.password.dateOfGraduation" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
							</tr>
							<tr>
								<th align="right">
									上传照片:
									<br />
									Upload Photos:
								</th>
								<td colspan="8">
									<input type="file" name="picture">
								</td>
								<s:if test="bool">
									<th align="right">
										用户性质:
										<br />
										(考勤机专用)
									</th>
									<td>
										<select name="user.user_privilege" class="horizontalLine">
											<option value="USER" title="普通用户" selected="selected">用户</option>
											<option value="MANAGER" title="有管理考勤机的权限">管理员</option>
										</select>
									</td>
								</s:if>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									工作岗位信息
									<br />
									Job information
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									卡号:
									<br />
									Card number:
								</th>
								<td>
									<input id="cardId" name="user.cardId" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
									<font color="red"> *</font>
								</td>
								<th align="right">
									工号:
									<br />
									Card number:
								</th>
								<td>
									<input id="code" name="user.code" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
									<font color="red"> *</font>
								</td>
								<th align="right">
									部门:
									<br />
									Department:
								</th>
								<td>
									<select id="dept" name="user.dept" id="dept"
										onmouseover="createDept('dept')" style="width: 150px;">
										<option value="${interviewLog.interviewDept}">
											${interviewLog.interviewDept}
										</option>
										<option value="">
											请选择部门
										</option>
									</select>
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									职务:
									<br />
									Title:
								</th>
								<td>
									<input name="user.duty" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								<th align="right">
									职级:
									<br />
									Job title:
								</th>
								<td>
									<%--									<input name="user.post" class="horizontalLine"--%>
									<%--										onfocus="chageClass(this,'')"--%>
									<%--										onblur="chageClass(this,'horizontalLine')">--%>
									<select id="post" name="user.post">
										<option title="普通员工">
											GP
										</option>
										<option title="部门主管">
											DM
										</option>
										<option title="副总经理">
											DGM
										</option>
										<option title="劳务员">
											SW
										</option>
										<option title="总经理">
											GM
										</option>
									</select>
									<font color="red"> *</font>
								</td>

								<th align="right">
									入职时间:
									<br />
									Entry time:
								</th>
								<td>
									<input id="joined" class="Wdate" type="text" name="user.joined"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									借物品:
									<br />
									Borrow items:
								</th>
								<td>
									<input type="radio" name="user.power" value="允许"
										checked="checked">
									是(YES)
									<input type="radio" name="user.power" value="否">
									否(NO)
								</td>
								<th align="right">
									状态:
									<br />
									Status:
								</th>
								<td>
									<SELECT name="user.onWork" style="width: 150px">
									<s:if test="interviewLog.enroll_result!=null">
										<option value="${interviewLog.enroll_result}">
											${interviewLog.enroll_result}
										</option>
									</s:if>
										<option value="试用">
											试用
										</option>
										<option value="实习">
											实习
										</option>
										<option value="内退">
											内退
										</option>
									</SELECT>
								</td>
								<th align="right">
									试用期:
									<br />
									Trial period:
								</th>
								<td>
									<input id="tryDays" class="Wdate" type="text"
										name="user.tryDays"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									职称:
									<br />
								</th>
								<td>
									<input name="user.jobtitle" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								<th align="right">
									职称级别:
									<br />
								</th>
								<td>
									<input name="user.leveltitles" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								<th align="right">
									职称取得时间:
									<br />
								</th>
								<td>
									<input name="user.password.jobtitleGetTime" class="Wdate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
							</tr>
							<tr>
								<th align="right">
									是否属于内部人员:
									<br />
								</th>
								<td colspan="1">
									<input type="radio" name="user.internal" value="是"
										checked="checked">
									是
									<input type="radio" name="user.internal" value="否">
									否
									<font color="red"> *</font>
								</td>
								<th align="right">
									备注:
									<br />
									Remarks:
								</th>
								<td colspan="6">
									<input name="user.more" class="horizontalLine"
										style="width: 760px" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									联系方式:
									<br />
									Contact:
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									邮箱:
									<br />
									E-mail:
								</th>
								<td>
									<input id="mailBox" name="user.password.mailBox"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine');isEmail(this.value)">
								</td>
								<th align="right">
									电话:
									<br />
									Phone:
								</th>
								<td>
									<input onkeyup="this.value=this.value.replace(/\D/g,'')"
										name="user.password.telephone" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								<th align="right">
									手机:
									<br />
									Cell phone:
								</th>
								<td>
									<input id="phoneNumber"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										value="${interviewLog.tel}" name="user.password.phoneNumber"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" maxlength="11">
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									现住址:
									<br />
									Present address:
								</th>
								<td colspan="3">
									<input id="presentAddress" name="user.password.presentAddress"
										value="${interviewLog.nowAddress}" style="width: 455px"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
									<font color="red"> *</font>
								</td>
								<th align="right">
									班次:
									<input id="banci_id" type="hidden" name="user.banci_id"
										value="" />
									<input id="banci_name" type="hidden" name="user.banci_name"
										value="" />
								</th>
								<td colspan="1">
									<SELECT id="banciselect"
										class="horizontalLine" style="width: 153px;">
										<option value=""></option>
									</SELECT>
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<br />
									<br />
									<input type="submit" value="添加(Add)"
										style="width: 80px; height: 50px;" />
									<input type="reset" value="重置(Reset)"
										style="width: 80px; height: 50px;" />
									<br />
									<br />
								</td>
							</tr>
						</table>
					</form>
					<div>
						<font color="red">${successMessage}</font>
						<font color="red">${errorMessage}</font>
					</div>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	<script type="text/javascript">
$(document).ready(function() {
	$.ajax( {
		url : "UsersAction!getMaxCode.action",
		type : "POST",
		dataType : "json",
		success : function(data) {
			$("#code").val(data);
		}
	})
	
	$.ajax({
		url : "UsersAction!findAllBanci.action",
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$(data).each(
					function() {
						$("<option value='" + this.name + "|"
											+ this.id + "'>"
											+ this.name
											+ "</option>")
								.appendTo("#banciselect")
					});
				$("#banciselect").bind("change", function() {
				var name = $("#banciselect").val();
				var usersData = name.split("|");
				var bfName = usersData[0];
				var ncode = usersData[1];
				$("#banci_id").val(ncode);
				$("#banci_name").val(bfName);
			});
		},
		error : function() {
			alert("服务器异常!");
		}
	})
});
</script>

</html>
