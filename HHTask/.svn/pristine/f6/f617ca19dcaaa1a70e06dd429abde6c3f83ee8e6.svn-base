<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%; margin-top: ">
					<tr>
						<td>
							您正在添加人员:
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; height: 100px">
					<div id="addDutyPeople" style="color: #ffffff;">
						<form id="addPeopleForm" action="DutyClaimAction!addPeople.action"
							method="post">
							<input name="id" value="${dutyClaim.id}" type="hidden" />
							<input id="userName" name="dutyClaim.userName" type="hidden" />
							<input id="userId" name="dutyClaim.userId" type="hidden" />
							<input id="userId" name="dutyClaim.deptClaim"
								value="${dutyClaim.deptClaim}" type="hidden" />
							<input id="skillClaim" name="dutyClaim.skillClaim" type="hidden" />
							<input id="quaClaim" name="dutyClaim.quaClaim" type="hidden" />
							<table class="table">
								<tr>
									<th colspan="2" align="center">
										<br />
										<h1>
											添加${dutyClaim.duty} 职位胜任要求人员
										</h1>
									</th>
								</tr>
								<tr>
									<th align="right">
										人员类型:
									</th>
									<td>
										<select id="duty" name="dutyClaim.claimStatus"
											style="width: 200px;">
											<option value="现有人员">
												现有人员
											</option>
											<option value="备选人员">
												备选人员
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right" title="要求部门:${dutyClaim.deptClaim}">
										人员信息:
									</th>
									<td style="width: 85%;">
										&nbsp;部门
										<select name="dutyClaim.dept" id="dept" style="width: 100px;">
											<option></option>
										</select>
										名称
										<select id="users" style="width: 100px;">
										</select>
										工号
										<input id="code" name="dutyClaim.code" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right" title="要求职位:${dutyClaim.duty}">
										对应职位:
									</th>
									<td>
										&nbsp;
										<input type="radio" name="dutyClaim.duty" checked="checked"
											value="√">
										√
										<input type="radio" name="dutyClaim.duty" value="X">
										X
										<input name="dutyClaim.duty" style="width: 400PX;" />
									</td>
								</tr>
								<tr>
									<th align="right" title="要求学历:${dutyClaim.eduClaim}">
										学历要求:
									</th>
									<td>
										&nbsp;
										<input type="radio" name="dutyClaim.eduClaim"
											checked="checked" value="√">
										√
										<input type="radio" name="dutyClaim.eduClaim" value="X">
										X
										<input name="dutyClaim.eduClaim" style="width: 400PX;" />
									</td>
								</tr>
								<tr>
									<th align="right" title="要求专业:${dutyClaim.speClaim}">
										专业要求:
									</th>
									<td>
										&nbsp;
										<input type="radio" name="dutyClaim.speClaim"
											checked="checked" value="√">
										√
										<input type="radio" name="dutyClaim.speClaim">
										X
										<input name="dutyClaim.speClaim" style="width: 400PX;" />
									</td>
								</tr>
								<tr>
									<th align="right" title="经验要求:${dutyClaim.expClaim}">
										经验要求:
									</th>
									<td>
										&nbsp;
										<input type="radio" name="dutyClaim.expClaim"
											checked="checked" value="√">
										√
										<input type="radio" name="dutyClaim.expClaim" value="X">
										X
										<input name="dutyClaim.expClaim" style="width: 400PX;" />
									</td>
								</tr>
								<tr>
									<th align="right">
										技能要求:
									</th>
									<td>
										<s:generator val="dutyClaim.skillClaim" separator="\\|"
											id="iter1">
										</s:generator>
										<s:iterator status="st" value="#request.iter1" id="name">
											<span title="要求技能${st.index+1}:${name}"> ${st.index+1}</span>、<input
												type="radio" name="skillClaim_${st.index}" checked="checked"
												value="√">
									√
									<input type="radio" name="skillClaim_${st.index}" value="X">
									X
									<input name="skillClaim_${st.index}" style="width: 400PX;" />
											<hr />
										</s:iterator>

									</td>
								</tr>
								<tr>
									<th align="right">
										素质要求:
									</th>
									<td>
										<s:generator val="dutyClaim.quaClaim" separator=",|,"
											id="iter1">
										</s:generator>
										<s:iterator status="st" value="#request.iter1" id="name">
											<span title="要求素质${st.index+1}:${name}">${st.index+1}、</span>
											<input type="radio" name="quaClaim_${st.index}"
												checked="checked" value="√">
									√
									<input type="radio" name="quaClaim_${st.index}" value="X">
									X
									<input name="quaClaim_${st.index}" style="width: 400PX;" />
											<hr />
										</s:iterator>
									</td>
								</tr>
								<tr>
									<th align="right" title="上岗要求:${dutyClaim.posClaim}">
										上岗要求:
									</th>
									<td>
										<input type="radio" name="dutyClaim.posClaim"
											checked="checked" value="√">
										√
										<input type="radio" name="dutyClaim.posClaim" value="X">
										X
										<input name="dutyClaim.posClaim" style="width: 400PX;" />
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" style="width: 80px; height: 50px;"
											value="添加" />
										<input type="reset" style="width: 80px; height: 50px;"
											value="重置" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
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
						View Jobs competent information</a>
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新
						<br/>
						Refresh</a>
				</div>
			</div>
			
			<div align="center">
				<div id="showAllDuty">
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
								<br/>
								No.
							</th>
							<th align="center">
								部门
								<br/>
								Department
							</th>
							<th align="center">
								职位
								<br/>
								Jobs
							</th>
							<th align="center">
								学历要求
								<br/>
								Academic requirements
							</th>
							<th align="center">
								专业要求
								<br/>
								Professional requirements
							</th>
							<th align="center">
								经验要求
								<br/>
								Experience Requirements
							</th>
							<th align="center">
								操作
								<br/>
								Operation
							</th>
						</tr>
						<s:iterator value="dutyClaimList" id="pageDutyClaim"
							status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageDutyClaim.deptClaim}
							</td>
							<td>
								${pageDutyClaim.duty}
							</td>
							<td>
								${pageDutyClaim.eduClaim}
							</td>
							<td>
								${fn:substring(pageDutyClaim.speClaim, 0, 10)}
							</td>
							<td>
								${fn:substring(pageDutyClaim.expClaim, 0, 10)}
							</td>
							<td>
								<a
									href="DutyClaimAction!delDutyClaim.action?id=${pageDutyClaim.id}"
									onclick="return window.confirm('确定要删除该职位信息吗?')">删除(Delete)</a>/
								<a
									href="DutyClaimAction!findDutyForUpdate.action?id=${pageDutyClaim.id}">修改(Modify)</a>/
								<a
									href="DutyClaimAction!findDutyClaimDetails.action?id=${pageDutyClaim.id}">明细(Details)</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
				</div>
				<div id="showOneDuty" style="display: none;">
					<input type="button" onclick="chageDiv('block')" value="添加人员(Add people)"
						style="width: 80px; height: 40px;">
					<table class="table">
						<tr>
							<th colspan="2" align="center">
								<h3>
									${dutyClaim.duty} 职位胜任要求明细
									<br/>
									Jobs competency requirements detailed
								</h3>
							</th>
						</tr>
						<tr>
							<th align="right">
								所属部门:
								<br/>
								Department:
							</th>
							<td style="width: 85%;">
								&nbsp;${dutyClaim.deptClaim}
							</td>
						</tr>
						<tr>
							<th align="right">
								对应职位:
								<br/>
								Corresponding positions:
							</th>
							<td>
								&nbsp;${dutyClaim.duty}
							</td>
						</tr>
						<tr>
							<th align="right">
								学历要求:
								<br/>
								Education:
							</th>
							<td>
								&nbsp;${dutyClaim.eduClaim}
							</td>
						</tr>
						<tr>
							<th align="right">
								专业要求:
								<br/>
								Professional requirements:
							</th>
							<td>
								&nbsp;${dutyClaim.speClaim}
							</td>
						</tr>
						<tr>
							<th align="right">
								经验要求:
								<br/>
								Experience Requirements:
							</th>
							<td>
								&nbsp;${dutyClaim.expClaim}
							</td>
						</tr>
						<tr>
							<th align="right">
								技能要求:
								<br/>
							Skills required:
							</th>
							<td>
								<s:generator val="dutyClaim.skillClaim" separator="|" id="iter1">
								</s:generator>
								<s:iterator status="st" value="#request.iter1" id="name">
									${st.index+1}、<s:property value="name" />
									<hr />
								</s:iterator>
							</td>
						</tr>
						<tr>
							<th align="right">
								素质要求:
								<br/>
								Qualifications:
							</th>
							<td>
								<s:generator val="dutyClaim.quaClaim" separator="|" id="iter1">
								</s:generator>
								<s:iterator status="st" value="#request.iter1" id="name">
									${st.index+1}、<s:property value="name" />
									<hr />
								</s:iterator>
							</td>
						</tr>
						<tr>
							<th align="right">
								上岗要求:
								<br/>
								Induction requirements:
							</th>
							<td>
								${dutyClaim.posClaim}
							</td>
						</tr>
					</table>
					<div>
						<s:iterator id="nowPeople" value="dutyClaimList"
							status="nowPeopleStatus">
							<s:if test="#nowPeopleStatus.first">
								<h2>
									现有人员
									<br/>
									Existing staff
								</h2>
							</s:if>
							<table class="table">
								<tr>
									<th align="right">
										人员类型:
										<br/>
										Person Type:
									</th>
									<td>
										${nowPeople.claimStatus}
									</td>
								</tr>
								<tr>
									<th align="right" title="要求部门:${dutyClaim.deptClaim}">
										人员信息:
										<br/>
									Personnel information:
									</th>
									<td style="width: 85%;">
										&nbsp;部门:${nowPeople.dept} 名称:
										<b>${nowPeople.userName}</b> 工号:${nowPeople.code}
										<a
											href="DutyClaimAction!delDutyClaim.action?id=${nowPeople.id}"
											onclick="return window.confirm('确定要删除该职位信息吗?')">删除(Delete)</a>
									</td>
								</tr>
								<tr>
									<th align="right" title="要求职位:${dutyClaim.duty}">
										对应职位:
										<br/>
										Corresponding positions:
									</th>
									<td>
										&nbsp;${nowPeople.duty}
									</td>
								</tr>
								<tr>
									<th align="right" title="要求学历:${dutyClaim.eduClaim}">
										学历要求:
										<br/>
										Education:
									</th>
									<td>
										&nbsp;${nowPeople.eduClaim}
									</td>
								</tr>
								<tr>
									<th align="right" title="要求专业:${dutyClaim.speClaim}">
										专业要求:
										<br/>
										Professional requirements:
									</th>
									<td>
										&nbsp;${nowPeople.speClaim}
									</td>
								</tr>
								<tr>
									<th align="right" title="经验要求:${dutyClaim.expClaim}">
										经验要求:
										<br/>
										Experience Requirements:
									</th>
									<td>
										&nbsp;${nowPeople.expClaim}
									</td>
								</tr>
								<tr>
									<th align="right">
										技能要求:
										<br/>
										Skills required:
									</th>
									<td>
										<c:forTokens items="${nowPeople.skillClaim}" begin="0"
											delims="|" var="aValue" varStatus="statusfor">
         ${statusfor.index+1}、${aValue}<hr />
										</c:forTokens>
									</td>
								</tr>
								<tr>
									<th align="right">
										素质要求:
										<br/>
										Qualifications:
									</th>
									<td>
										<c:forTokens items="${nowPeople.quaClaim}" begin="0"
											delims="|" var="aValue" varStatus="statusfor">
         ${statusfor.index+1}、${aValue}<hr />
										</c:forTokens>

									</td>
								</tr>
								<tr>
									<th align="right" title="上岗要求:${dutyClaim.posClaim}">
										上岗要求:
										<br/>
										Induction requirements:
									</th>
									<td>
										${nowPeople.posClaim}
									</td>
								</tr>
							</table>
							<br />
						</s:iterator>
						<s:iterator id="beiPeople" value="list" status="beiPeopleStatus">
							<s:if test="#beiPeopleStatus.first">
								<h2>
									备选人员
									<br/>
									Alternative staff
								</h2>
							</s:if>
							<table class="table">
								<tr>
									<th align="right">
										人员类型:
										<br/>
										Person Type:
									</th>
									<td>
										${beiPeople.claimStatus}
									</td>
								</tr>
								<tr>
									<th align="right" title="要求部门:${dutyClaim.deptClaim}">
										人员信息:
										<br/>
										Personnel information:
									</th>
									<td style="width: 85%;">
										&nbsp;部门:${beiPeople.dept} 名称:
										<b>${beiPeople.userName}</b> 工号:${beiPeople.code}
										<a
											href="DutyClaimAction!delDutyClaim.action?id=${beiPeople.id}"
											onclick="return window.confirm('确定要删除该职位信息吗?')">删除(Delete)</a>
									</td>
								</tr>
								<tr>
									<th align="right" title="要求职位:${dutyClaim.duty}">
										对应职位:
										<br/>
										Corresponding positions:
									</th>
									<td>
										&nbsp;${beiPeople.duty}
									</td>
								</tr>
								<tr>
									<th align="right" title="要求学历:${dutyClaim.eduClaim}">
										学历要求:
										<br/>
										Education:
									</th>
									<td>
										&nbsp;${beiPeople.eduClaim}
									</td>
								</tr>
								<tr>
									<th align="right" title="要求专业:${dutyClaim.speClaim}">
										专业要求:
										<br/>
										Professional requirements:
									</th>
									<td>
										&nbsp;${beiPeople.speClaim}
									</td>
								</tr>
								<tr>
									<th align="right" title="经验要求:${dutyClaim.expClaim}">
										经验要求:
										<br/>
										Experience Requirements:
									</th>
									<td>
										&nbsp;${beiPeople.expClaim}
									</td>
								</tr>
								<tr>
									<th align="right">
										技能要求:
										<BR/>
										Skills required:		
									</th>
									<td>
										<c:forTokens items="${beiPeople.skillClaim}" begin="0"
											delims="|" var="aValue" varStatus="statusfor">
         ${statusfor.index+1}、${aValue}<hr />
										</c:forTokens>
									</td>
								</tr>
								<tr>
									<th align="right">
										素质要求:
										<br/>
										Qualifications:
									</th>
									<td>
										<c:forTokens items="${beiPeople.quaClaim}" begin="0"
											delims="|" var="aValue" varStatus="statusfor">
         ${statusfor.index+1}、${aValue}<hr />
										</c:forTokens>

									</td>
								</tr>
								<tr>
									<th align="right" title="上岗要求:${dutyClaim.posClaim}">
										上岗要求:
										<br/>
										Induction requirements:
									</th>
									<td>
										${beiPeople.posClaim}
									</td>
								</tr>
							</table>
							<br />
						</s:iterator>
					</div>
				</div>
				<br />
				<br />
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//布局调控
$(function() {
	var dutyClaim = "${dutyClaim}";
	if (dutyClaim != "") {
		$("#showAllDuty").hide();
		$("#showOneDuty").slideDown("slow");
	}
});
//表单检查
$(function() {
	$("#addPeopleForm")
			.bind(
					"submit",
					function() {
						if ($("#dept").val() == "") {
							alert("请选择部门");
							$("#dept").focus();
							return false;
						} else if ($("#users").val() == "") {
							alert("请选择人员");
							$("#users").focus();
							return false;
						}

						//技能要求
						var oldSkillClaim = "${dutyClaim.skillClaim}";
						var oldSkillClaim2 = oldSkillClaim.split("|");
						var skillClaim = "";
						for ( var i = 0; i < oldSkillClaim2.length - 1; i++) {
							var radioVal = $(
									":radio[name='skillClaim_" + i
											+ "']:checked").val();
							var textVal = $(
									":text[name='skillClaim_" + i + "']").val();
							skillClaim += radioVal + "," + textVal + "|";

						}

						//素质要求
						var oldQuaClaim = "${dutyClaim.quaClaim}";
						var oldQuaClaim2 = oldQuaClaim.split("|");
						var quaClaim = "";
						for ( var i = 0; i < oldSkillClaim2.length - 1; i++) {
							var radioVal = $(
									":radio[name='quaClaim_" + i + "']:checked")
									.val();
							var textVal = $(":text[name='quaClaim_" + i + "']")
									.val();
							quaClaim += radioVal + "," + textVal + "|";

						}
						$("#skillClaim").val(skillClaim);
						$("#quaClaim").val(quaClaim);

						return true;
					})
});

//查询所有的部门
$.ajax( {
	url : 'DeptNumberAction!findAllDept.action',
	dataType : 'json',
	cache : false,//防止数据缓存
	success : function(allDdept) {
		$("#dept").empty();
		$("<option></option>").appendTo("#dept");
		$(allDdept).each(
				function() {
					$(
							"<option value='" + this.dept + "'>" + this.dept
									+ "</option>").appendTo("#dept");
				});
	}

});
//级联查询出部门所对应的所有人员
$("#dept")
		.bind(
				"change",
				function() {
					if ($("#dept").val() != "") {
						$
								.ajax( {
									url : "UsersAction!findUsersByDept.action",
									type : 'post',
									dataType : 'json',
									cache : false,//防止数据缓存
									contentType : "application/x-www-form-urlencoded; charset=utf-8",
									data : {
										deptName : $("#dept").val()
									},
									success : function(useradsfa) {
										$("#users").empty();//清空
										$("<option></option>").appendTo(
												"#users");
										$(useradsfa)
												.each(
														function() {
															$(
																	"<option value='"
																			+ this.id
																			+ "|"
																			+ this.code
																			+ "|"
																			+ this.name
																			+ "'>"
																			+ this.name
																			+ "</option>")
																	.appendTo(
																			"#users")
														});
										$("#users")
												.bind(
														"change",
														function() {
															var user = $(
																	"#users")
																	.val();
															var userCodeName = user
																	.split("|");
															if (userCodeName != "") {
																$("#userId")
																		.val(
																				userCodeName[0]);
																$("#code")
																		.val(
																				userCodeName[1]);
																$("#userName")
																		.val(
																				userCodeName[2]);
															} else {
																$("#code").val(
																		"");
																$("#userName")
																		.val("");
															}
														})
									},
									error : function() {
										alert("服务器异常!");
									}
								});
					}

				});
</script>
	</body>
</html>
