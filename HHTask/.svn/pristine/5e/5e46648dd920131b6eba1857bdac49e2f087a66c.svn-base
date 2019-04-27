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
		<div id="gongneng">
			<div align="center">
				<form action="interviewLogAction_update.action" method="post"
					onsubmit="return update()">
					<table class="table" style="width: 92%" align="center">
						<tr>
							<td colspan="9" align="center">
								<h4>
									修改面试登记表
									<input type="hidden" name="interviewLog.id"
										value="<s:property value="interviewLog.id"/>">
								</h4>
								<h3>
									<font color="red">${errorMessage}</font>
								</h3>
							</td>
						</tr>
						<tr>
							<td align="center">
								应聘职位
							</td>
							<td>
								<s:property value="interviewLog.job" />
							</td>
							<td align="center">
								应聘部门
							</td>
							<td colspan="2">
								<s:property value="interviewLog.interviewDept" />
							</td>
							<td align="center">
								学历
							</td>
							<td colspan="1" align="center">
								${interviewLog.gereneducation}
							</td>
							<td align="center">
								身份证号码
							</td>
							<td align="center">
								<s:property value="interviewLog.cardID" />
							</td>
						</tr>
						<tr>
							<td align="center">
								姓名
							</td>
							<td>
								<s:property value="interviewLog.name" />
							</td>
							<td align="center">
								性别
							</td>
							<td>
								<s:property value="interviewLog.sex" />
							</td>
							<td align="center">
								出生年月
							</td>
							<td>
								<s:property value="interviewLog.birthday" />
							</td>
							<td align="center">
								婚否
							</td>
							<td>
								<s:property value="interviewLog.marriage" />
							</td>
							<td align="center">
								身高:
								<s:property value="interviewLog.height" />
								&nbsp; cm
							</td>
						</tr>
						<tr>
							<td align="center">
								户籍地址
							</td>
							<td colspan="3">
								<s:property value="interviewLog.census" />
							</td>
							<td align="center">
								户籍性质
							</td>
							<td colspan="3" align="center">
								<s:if
									test="interviewLog.censusStatus!=null&&interviewLog.censusStatus=='非农'.toString()">
									<input type="radio" name="interviewLog.censusStatus"
										id="censusStatus" value="农">
								农&nbsp;
								<input type="radio" name="interviewLog.censusStatus"
										id="censusStatus" value="非农" checked="checked">
								非农&nbsp;
							</s:if>
								<s:else>
									<input type="radio" name="interviewLog.censusStatus"
										id="censusStatus" value="农" checked="checked">
								农&nbsp;
								<input type="radio" name="interviewLog.censusStatus"
										id="censusStatus" value="非农">
								非农&nbsp;
							</s:else>

							</td>
							<td align="center">
								工龄:
								<s:property value="interviewLog.workAge" />
								&nbsp;
							</td>
						</tr>
						<tr>
							<td align="center">
								毕业学院
							</td>
							<td colspan="3">
								<s:property value="interviewLog.finishSchool" />
							</td>
							<td align="center">
								毕业时间
							</td>
							<td>
								<s:property value="interviewLog.finishTime" />
							</td>
							<td align="center">
								专业
							</td>
							<td>
								<s:property value="interviewLog.specialty" />
							</td>
							<td align="center">
								专业成果:
								<s:property value="interviewLog.specialtyResult" />
							</td>
						</tr>
						<tr>
							<td align="center">
								通讯地址
							</td>
							<td colspan="3">
								<s:property value="interviewLog.contactAddress" />
							</td>
							<td align="center">
								邮编
							</td>
							<td>
								<s:property value="interviewLog.zipCode" />
							</td>
							<td align="center">
								联系方式
							</td>
							<td colspan="2">
								<input class="horizontalLine" type="text"
									name="interviewLog.tel" id="tel"
									value="<s:property value="interviewLog.tel"/>">
									邮箱账号
									<s:property value="interviewLog.email"/>
							</td>
						</tr>
						<tr>
							<td align="center">
								原工作单位
							</td>
							<td colspan="3">
								<s:property value="interviewLog.beforeWorkAdd" />
							</td>
							<td align="center">
								原从事工种
							</td>
							<td>
								<s:property value="interviewLog.beforWork" />
							</td>
							<td align="center">
								现住址
							</td>
							<td colspan="2">
								<s:property value="interviewLog.nowAddress" />
							</td>
						</tr>
						<tr>
							<th colspan="9">
								家庭主要成员及主要社会关系
							</th>
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

						<!-- ******************************************* -->
						<s:iterator value="familiesList" id="families" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center">
							</s:if>
							<s:else>
								<tr align="center">
							</s:else>
							<td>
								${families.familyName}
							</td>
							<td colspan="2">
								${families.familyRelation}
							</td>
							<td>
								${families.familySex}
							</td>
							<td>
								${families.familyAge}
							</td>
							<td colspan="3">
								${families.familyAddress}
							</td>
							<td>
								${families.familyTel}
							</td>
						</s:iterator>
						<tr>
							<td align="center">
								主要经历
							</td>
							<td colspan="8" align="left">
								${interviewLog.experience}
							</td>
						</tr>
						<tr>
							<td align="center">
								专业技能及特长
							</td>
							<td colspan="8" align="left">
								${interviewLog.strongPoint}
							</td>
						</tr>
						<tr>
							<td align="center">
								薪资要求
							</td>
							<td colspan="2" align="left">
								${interviewLog.wantPay}
							</td>
							<td align="center" style="width: 101px; height: 20px;">
								其它要求
							</td>
							<td colspan="5" align="left">
								${interviewLog.otherWant}
							</td>
						</tr>
						<tr>
							<td colspan="9" align="center">
								<h4>
									以下由招聘单位填写（以优、良、普通、差或其他描述评价）
								</h4>
							</td>
						</tr>
						<tr>
							<td rowspan="2" align="center">
								评价结果
							</td>
							<td align="center">
								专业知识
							</td>
							<td align="center">
								<input name="interviewLog.specialtyScore" id="specialtyScore"
									value="${interviewLog.specialtyScore}" />
							</td>
							<td align="center" style="width: 101px; height: 20px;">
								相关工作经验
							</td>
							<td align="center">
								<input name="interviewLog.experienceScore" id="experienceScore"
									value="${interviewLog.experienceScore}" />
							</td>
							<td align="center">
								职业取向
							</td>
							<td align="center">
								<input name="interviewLog.job_direction" id="job_direction"
									value="${interviewLog.job_direction}" />
							</td>
							<td align="center">
								性格爱好
								<input name="interviewLog.nature_hobby" id="nature_hobby"
									value="${interviewLog.nature_hobby}" />
							</td>
							<td align="center">
								才智
								<input name="interviewLog.intelligence" id="intelligence"
									value="${interviewLog.intelligence}" />
							</td>
						</tr>
						<tr>
							<td align="center">
								职业态度
							</td>
							<td align="center">
								<input name="interviewLog.job_attitude" id="job_attitude"
									value="${interviewLog.job_attitude}" />
							</td>
							<td align="center" style="width: 101px; height: 20px;">
								分析能力
							</td>
							<td align="center">
								<input name="interviewLog.analytical_skills"
									id="analytical_skills"
									value="${interviewLog.analytical_skills}" />
							</td>
							<td align="center">
								语言表达能力
							</td>
							<td align="center">
								<input name="interviewLog.voice_ability" id="voice_ability"
									value="${interviewLog.voice_ability}" />
							</td>
							<td align="center">
								沟通能力
								<input name="interviewLog.communication_skills"
									id="communication_skills"
									value="${interviewLog.communication_skills}" />
							</td>
							<td align="center">
								学历
								<input name="interviewLog.education" id="education"
									value="${interviewLog.education}" />
							</td>
						</tr>
						<tr>
							<td align="center">
								主考评语
							</td>
							<td colspan="8" align="left">
								<textarea rows="3" cols="100%"
									name="interviewLog.examiner_remark" id="examiner_remark">${interviewLog.examiner_remark}</textarea>
							</td>
						</tr>
						<tr>
							<td rowspan="3" align="center">
								用人部门意见
							</td>
							<td colspan="3" align="center">
								<s:if
									test="interviewLog.enroll_result!=null&&interviewLog.enroll_result=='试用'">
									<input type="radio" name="interviewLog.enroll_result"
										id="enroll_result" value="试用" checked="checked"
										onclick="onluyong1()">
								试用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="interviewLog.enroll_result"
										id="enroll_result1" value="不录用" onclick="onluyong1()">
								不录用
							</s:if>
								<%--<s:elseif test="interviewLog.enroll_result==试用">
									<input type="radio" name="interviewLog.enroll_result"
										id="enroll_result" value="试用">
								试用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="interviewLog.enroll_result"
										id="enroll_result" value="不录用">
								不录用
							</s:elseif>
								--%>
								<s:else>
									<input type="radio" name="interviewLog.enroll_result"
										id="enroll_result" value="试用" onclick="onluyong1()">
								试用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="interviewLog.enroll_result"
										id="enroll_result1" value="不录用" checked="checked"
										onclick="onluyong1()">
								不录用
							</s:else>
							</td>
							<td rowspan="3" colspan="1" align="center">
								工厂意见
							</td>
							<td rowspan="3" colspan="4" align="left">
								<textarea rows="3" cols="90%"
									name="interviewLog.factory_opinion" id="factory_opinion">${interviewLog.factory_opinion}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								拟安排岗位：
								<input type="text" name="interviewLog.ni_post" id="ni_post"
									style="width: 50%; height: 20px;"
									value="<s:property value="interviewLog.ni_post"/>" />
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								拟定试用期工资：
								<input type="text" name="interviewLog.ni_salary" id="ni_salary"
									value="<s:property value="interviewLog.ni_salary"/>"
									onblur="mustBeNumber('ni_salary')">
								元
							</td>
						</tr>
						
					<s:if test="ccTag!='show'">
						<tr>
							<td colspan="9" align="center">
								<input type="submit" value="保存" id="baocun"
									style="width: 80px; height: 30px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 80px; height: 30px;">
							</td>
						</tr>
					</s:if>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update() {
	if (!validateText("specialtyScore", "专业知识")) {
		return false;
	}
	if (!validateText("experienceScore", "相关工作经验")) {
		return false;
	}
	if (!validateText("job_direction", "职业取向")) {
		return false;
	}
	if (!validateText("nature_hobby", "性格爱好")) {
		return false;
	}
	if (!validateText("intelligence", "才智")) {
		return false;
	}
	if (!validateText("job_attitude", "职业态度")) {
		return false;
	}
	if (!validateText("analytical_skills", "分析能力")) {
		return false;
	}
	if (!validateText("voice_ability", "语言表达能力")) {
		return false;
	}
	if (!validateText("communication_skills", "沟通技巧")) {
		return false;
	}
	if (!validateText("education", "学历")) {
		return false;
	}
	if (!validateText("examiner_remark", "主考评语")) {
		return false;
	}
	if ($("#enroll_result").val() == '试用') {
		if (!validateText("ni_post", "拟安排岗位")) {
			return false;
		}
		if (!validateText("ni_salary", "拟定试用期工资")) {
			return false;
		}
	}
}function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function onluyong(){
	var text = ($("#enroll_result").val());
	//var text2 = document.getElementsByName("interviewLog.enroll_result");
	//alert($("input[name='interviewLog.enroll_result']:checked").val());
	if(text == "试用"){
		$("#baocun").val("申请");
	}else{
		$("#baocun").val("保存");
	}
}
function onluyong1(){
	var text = $("input[name='interviewLog.enroll_result']:checked").val();//获取单选按钮选中的值
	if(text == "不录用"){
		$("#baocun").val("保存");
	}else{
		$("#baocun").val("申请");
	}
}
$(function(){
	onluyong1();
});
</script>
	</body>
</html>
