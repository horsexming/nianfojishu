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
				<table class="table" style="width: 92%" align="center">
					<tr>
						<td colspan="9" align="center">
							<h4>
								查看面试登记表
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
							${interviewLog.censusStatus}
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
							<s:property value="interviewLog.tel" />
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
							${interviewLog.specialtyScore}
						</td>
						<td align="center" style="width: 101px; height: 20px;">
							相关工作经验
						</td>
						<td align="center">
							${interviewLog.experienceScore}
						</td>
						<td align="center">
							职业取向
						</td>
						<td align="center">
							${interviewLog.job_direction}
						</td>
						<td align="center">
							性格爱好&nbsp;&nbsp; ${interviewLog.nature_hobby}
						</td>
						<td align="center">
							才智&nbsp;&nbsp; ${interviewLog.intelligence}
						</td>
					</tr>
					<tr>
						<td align="center">
							职业态度
						</td>
						<td align="center">
							${interviewLog.job_attitude}
						</td>
						<td align="center" style="width: 101px; height: 20px;">
							分析能力
						</td>
						<td align="center">
							${interviewLog.analytical_skills}
						</td>
						<td align="center">
							语言表达能力
						</td>
						<td align="center">
							${interviewLog.voice_ability}
						</td>
						<td align="center">
							沟通能力&nbsp;&nbsp; ${interviewLog.communication_skills}
						</td>
						<td align="center">
							学历&nbsp;&nbsp; ${interviewLog.education}
						</td>
					</tr>
					<tr>
						<td align="center">
							主考评语
						</td>
						<td colspan="8" align="left">
							${interviewLog.examiner_remark}
						</td>
					</tr>
					<tr>
						<td rowspan="3" align="center">
							用人部门意见
						</td>
						<td colspan="3" align="center">
							${interviewLog.enroll_result}
						</td>
						<td rowspan="3" colspan="1" align="center">
							工厂意见
						</td>
						<td rowspan="3" colspan="4" align="left">
							${interviewLog.factory_opinion}
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							拟安排岗位：
							<s:property value="interviewLog.ni_post" />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							拟定试用期工资：
							<s:property value="interviewLog.ni_salary" />
							&nbsp; 元
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>
