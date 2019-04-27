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
	<body onload="createDept('interviewDept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<form action="interviewLogAction_add.action" method="post"
					onsubmit="return adddata()">
					<table class="table" align="center" style="width: 92%;"
						id="tablebod">
						<tbody>
							<tr>
								<td colspan="9" align="center">
									<h4>
										添加面试登记表
									</h4>
									<h3>
										<font color="red">${successMessage}</font>
									</h3>
									<h3>
										<font color="red">${errorMessage}</font>
									</h3>
								</td>
							</tr>
							<tr>
								<td align="center">
									应聘职位
								</td>
								<td align="left">
									<input name="interviewLog.job" id="job" value=""
										style="width: 110px;">
								</td>
								<td align="center">
									应聘部门
								</td>
								<td colspan="2" align="left">
									<SELECT name="interviewLog.interviewDept" id="interviewDept">
										<option value="">
										</option>
									</SELECT>
								</td>
								<td align="center">
									学历
								</td>
								<td colspan="1" align="left">
									<select id="gereneducation" name="interviewLog.gereneducation"
										style="width: 150px;">
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
								</td>
								<td align="center">
									身份证号码
								</td>
								<td align="left">
									<input name="interviewLog.cardID" id="cardID" value=""
										style="width: 100%; height: 20px;">
								</td>
							</tr>
							<tr>
								<td align="center">
									姓名
								</td>
								<td align="left">
									<input name="interviewLog.name" id="name" value=""
										style="width: 110px;">
								</td>
								<td align="center">
									性别
								</td>
								<td align="left">
									<select name="interviewLog.sex" id="sex">
										<option value="">
											请选择性别
										</option>
										<option value="男">
											男
										</option>
										<option value="女">
											女
										</option>
									</select>
								</td>
								<td align="center">
									出生年月
								</td>
								<td align="center">
									<input class="Wdate" type="text" name="interviewLog.birthday"
										id="birthday" value=""
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
								<td align="center">
									婚否
								</td>
								<td align="left">
									<select name="interviewLog.marriage" id="marriage">
										<option value="">
											请选择婚姻状况
										</option>
										<option value="未婚">
											未婚
										</option>
										<option value="已婚">
											已婚
										</option>
									</select>
								</td>
								<td align="left">
									身高
									<input name="interviewLog.height" id="height" value=""
										onblur="mustBeNumber('height')">
									cm
								</td>
							</tr>
							<tr>
								<td align="center">
									户籍地址
								</td>
								<td colspan="3" align="left">
									<input name="interviewLog.census" id="census"
										style="width: 100%; height: 20px;" value="">
								</td>
								<td align="center">
									户籍性质
								</td>
								<td colspan="3" align="center">
									<input type="radio" name="interviewLog.censusStatus"
										id="censusStatus" value="农" checked="checked">
									<label for="censusStatus">
									农&nbsp;
									</label>
									<input type="radio" name="interviewLog.censusStatus"
										id="censusStatus1" value="非农">
									<label for="censusStatus1">
									非农&nbsp;
									</label>
								</td>
								<td align="left">
									工龄
									<input type="text" name="interviewLog.workAge" id="workAge"
										value="">
								</td>
							</tr>
							<tr>
								<td align="center">
									毕业学院
								</td>
								<td colspan="3">
									<input type="text" name="interviewLog.finishSchool"
										id="finishSchool" style="width: 100%; height: 20px;" value="">
								</td>
								<td align="center">
									毕业时间
								</td>
								<td align="left">
									<input class="Wdate" type="text" name="interviewLog.finishTime"
										id="finishTime" value=""
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
								<td align="center">
									专业
								</td>
								<td align="left">
									<input type="text" name="interviewLog.specialty" id="specialty"
										value="">
								</td>
								<td align="left">
									专业成果
									<input type="text" name="interviewLog.specialtyResult"
										id="specialtyResult" value="">
								</td>
							</tr>
							<tr>
								<td align="center">
									通讯地址
								</td>
								<td colspan="3" align="left">
									<input type="text" name="interviewLog.contactAddress"
										id="contactAddress" style="width: 100%; height: 20px;"
										value="">
								</td>
								<td align="center">
									邮编
								</td>
								<td align="left">
									<input type="text" name="interviewLog.zipCode" id="zipCode"
										value="">
								</td>
								<td align="center">
									手机号码
								</td>
								<td colspan="2" align="left">
									<input type="text" name="interviewLog.tel" id="tel"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										maxlength="11" value="" onblur="mustBeNumber('tel')">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									邮箱账号&nbsp;<input type="text" name="interviewLog.email" id="email"
										value="">
								</td>
							</tr>
							<tr>
								<td align="center">
									原工作单位
								</td>
								<td colspan="3" align="left">
									<input type="text" name="interviewLog.beforeWorkAdd"
										id="beforeWorkAdd" style="width: 100%; height: 20px;" value="">
								</td>
								<td align="center">
									原从事工种
								</td>
								<td align="left">
									<input type="text" name="interviewLog.beforWork" id="beforWork"
										value="">
								</td>
								<td align="center">
									现住址
								</td>
								<td colspan="2" align="left">
									<input type="text" name="interviewLog.nowAddress"
										id="nowAddress" style="width: 100%; height: 20px;" value="">
								</td>
							</tr>
							<tr>
								<td colspan="9" align="center">
									家庭主要成员及主要社会关系
								</td>
							</tr>
							<tr>
								<td align="center">
									姓名
								</td>
								<td colspan="2" align="center">
									与本人关系或称呼
								</td>
								<td align="center">
									性别
								</td>
								<td align="center">
									年龄
								</td>
								<td colspan="3" align="center">
									工作单位或地址
								</td>
								<td align="center">
									联系方式
								</td>
							</tr>

							<tr>
								<td align="center">
									<input type="text" name="familiesList[0].familyName"
										id="familyName0" value="" style="width: 98px; height: 20px;">
								</td>
								<td colspan="2" align="left">
									<input type="text" name="familiesList[0].familyRelation"
										id="familyRelation0" style="width: 100%; height: 20px;"
										value="">
								</td>
								<td align="left">
									<select name="familiesList[0].familySex" id="familySex0">
										<option value="">
											请选择性别
										</option>
										<option value="男">
											男
										</option>
										<option value="女">
											女
										</option>
									</select>
								</td>
								<td align="center">
									<input type="text" name="familiesList[0].familyAge"
										id="familyAge0" value="" style="width: 99px; height: 20px;"
										onblur="mustBeNumber('familyAge0')">
								</td>
								<td colspan="3" align="left">
									<input type="text" name="familiesList[0].familyAddress"
										id="familyAddress0" style="width: 100%; height: 20px;"
										value="">
								</td>
								<td align="left">
									<input type="text" name="familiesList[0].familyTel"
										id="familyTel0" style="width: 100%; height: 20px;"
										onblur="mustBeNumber('familyTel0')">
								</td>
							</tr>

							<!-- ******************************************* -->
							<tr>
								<td align="right" colspan="5" style="border-right-width: 0px;">
									<input type="button" id="tijiao" onclick="addFamily(this,1)"
										value="添加" />
								</td>
								<td align="left" colspan="4" style="border-left-width: 0px;">
									<input type="button" onclick="delFamily(this)" id="shanchu"
										style="display: none;" value="删除" />
								</td>
							</tr>
							<tr>
								<td align="center">
									主要经历
								</td>
								<td colspan="8" align="left">
									<textarea rows="4" cols="100%" name="interviewLog.experience"
										id="experience"></textarea>
								</td>
							</tr>
							<tr>
								<td align="center">
									专业技能及特长
								</td>
								<td colspan="8" align="left">
									<textarea rows="4" cols="100%" name="interviewLog.strongPoint"
										id="strongPoint"></textarea>
								</td>
							</tr>
							<tr>
								<td align="center">
									薪资要求
								</td>
								<td colspan="2" align="left">
									<input type="text" name="interviewLog.wantPay" id="wantPay"
										onblur="mustBeNumber('wantPay')">
								</td>
								<td align="center">
									其它要求
								</td>
								<td colspan="5" align="left">
									<textarea rows="1" cols="100%" name="interviewLog.otherWant"
										id="otherWant"></textarea>
								</td>
							</tr>

							<tr>
								<td colspan="9" align="center">
									<input type="submit" value="添加"
										style="width: 80px; height: 35px;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 80px; height: 35px;">
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var lineCount = 1;
var begAddLineNum = 1;//家人信息
var size = 1;//家属信息条数
var index = 1;//家属信息下标
function addFamily(obj, few) {
	if (size >= 6) {
		alert("家属信息条数达到上限");
		return false;
	}
	var trindex = 8 + size;
	var fname = "familyName" + index;
	var relation = "familyRelation" + index;
	var sex = "familySex" + index;
	var age = "familyAge" + index;
	var address = "familyAddress" + index;
	var tel = "familyTel" + index;
	$("#tablebod>tbody>tr")
			.eq(trindex)
			.after(
					"<tr id='addtr"
							+ index
							+ "'><td colspan='1' align='center'><input type='text' style='width:98px' name='familiesList["
							+ index
							+ "].familyName' id="
							+ fname
							+ "></td><td colspan='2'><input type='text' style='width:100%' name='familiesList["
							+ index
							+ "].familyRelation' id="
							+ relation
							+ "></td><td colspan='1'><select name='familiesList["
							+ index
							+ "].familySex' id="
							+ sex
							+ "><option value=''>请选择性别</option><option value='男'>男</option><option value='女'>女</option></select></td><td colspan='1' align='center'><input type='text' style='width:99px' name='familiesList["
							+ index
							+ "].familyAge' id="
							+ age
							+ " onblur=\"mustBeNumber('"
							+ age
							+ "')\"></td><td colspan='3'><input type='text' style='width:100%' name='familiesList["
							+ index
							+ "].familyAddress' id="
							+ address
							+ "></td><td colspan='1'><input type='text' style='width:100%' name='familiesList["
							+ index + "].familyTel' id=" + tel
							+ " onblur=\"mustBeNumber('" + tel
							+ "')\"></td></tr>");
	size++;
	index++;
	document.getElementById("shanchu").style.display = "block";

}
//删除家属信息
function delFamily() {
	tablebod.deleteRow(index + 8);
	size--;
	index--;
	if (size < 2) {
		document.getElementById("shanchu").style.display = "none";
	}
}

function adddata() {
	var job = document.getElementById("job").value;
	var name = document.getElementById("name").value;
	if (job == "" || job == null) {
		document.getElementById("job").focus();
		alert("求职职位不能为空");
		return false;
	}
	if (!validateText("interviewDept", "求职部门")) {
		return false;
	}
	if (name == "" || name == null) {
		document.getElementById("name").focus();
		alert("姓名不能为空");
		return false;
	}

	if (!validateText("gereneducation", "学历")) {
		return false;
	}
	if (!validateText("cardID", "身份证号码")) {
		return false;
	}
	if (!validateText("birthday", "生日")) {
		return false;
	}
	if (!validateText("sex", "性别")) {
		return false;
	}
	if (!validateText("marriage", "婚否")) {
		return false;
	}
	if (!validateText("height", "身高")) {
		return false;
	}
	if (!validateText("census", "户籍地址")) {
		return false;
	}
	if (!validateText("specialty", "专业")) {
		return false;
	}
	if (!validateText("contactAddress", "通讯地址")) {
		return false;
	}
	if (!validateText("tel", "手机号码")) {
		return false;
	}
	if (!validateText("email", "邮箱账号")) {
		return false;
	}
	if (!validateText("nowAddress", "现住址")) {
		return false;
	}
	if (!validateText("familyName0", "家属1姓名")) {
		return false;
	}
	if (!validateText("familyRelation0", "家属1与本人关系或称呼")) {
		return false;
	}
	if (!validateText("familySex0", "家属1性别")) {
		return false;
	}
	if (!validateText("familyAge0", "家属1年龄")) {
		return false;
	}
	if (!validateText("familyAddress0", "家属1住址或工作单位")) {
		return false;
	}
	if (!validateText("familyTel0", "家属1联系方式")) {
		return false;
	}

	for ( var i = 1; i < size; i++) {
		n = i + 1;

		var fname = "familyName" + i;
		var relation = "familyRelation" + i;
		var sex = "familySex" + i;
		var age = "familyAge" + i;
		var address = "familyAddress" + i;
		var tel = "familyTel" + i;
		if (!validateText(fname, "家属" + n + "姓名")) {
			return false;
		}
		if (!validateText(relation, "家属" + n + "与本人关系或称呼")) {
			return false;
		}
		if (!validateText(sex, "家属" + n + "性别")) {
			return false;
		}
		if (!validateText(age, "家属" + n + "年龄")) {
			return false;
		}
		if (!validateText(address, "家属" + n + "住址或工作单位")) {
			return false;
		}
		if (!validateText(tel, "家属" + n + "联系方式")) {
			return false;
		}
	}

	if (!validateText("wantPay", "薪资要求")) {
		return false;
	}
<%--if (!validateText("otherWant", "其他要求")) {
		return false;
	}--%>

}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		$("#" + id).focus();
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
