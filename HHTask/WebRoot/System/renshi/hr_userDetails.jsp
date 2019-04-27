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
$(function() {
	$.ajax( {
		url : "UsersAction!findAllBanci.action",
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.name + "|" + this.id
										+ "'>" + this.name + "</option>")
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
$(function() {
	
	$.ajax( {
		type : "post",
		url : "UsersAction!findRank.action",
		data : {
			pageStatus : "ajax"
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.code + "'>"
										+ this.name + "("+this.code+")"+"</option>")
								.appendTo("#post");
					});
		}
	});
});
function banciguan() {
	var name = $("#banciselect").val();
	if ("${user.banci_name}" == name) {
		$("#banci_id").val(user.banci_id);
		$("#banci_name").val(user.banci_name);
	} else {
		var usersData = name.split("|");
		var banciid = usersData[1];
		var banciname = usersData[0];
		$("#banci_id").val(banciid);
		$("#banci_name").val(banciname);
	}
	//alert("${user.banci_name}");
}
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
	var joined = document.getElementById("joined");//入职时间
	var tryDays = document.getElementById("tryDays");//试用期
	var phoneNumber = document.getElementById("phoneNumber");//手机号
	var presentAddress = document.getElementById("presentAddress");//现住址
	var banci_name = document.getElementById("banci_name");//班次名
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
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 30px;"
				align="left">
				<div style="float: left; width: 30%" align="left">

				</div>
				<s:if test="pageStatus!='show'">
					<div style="float: left; width: 68%;" align="right">
						<s:if test="user.password.resume!=null&&user.password.resume!=''">
							<a
								<%--							href="DownAction.action?fileName=${user.password.resume}&directory=/upload/user/"><font--%>
							href="FileViewAction.action?FilePath=/upload/user/${user.password.resume}"><font
								color="#ffffff">下载简历 </font> </a>
							<a
								href="UsersAction!findUserById.action?pageStatus=1&&id=${user.id}"><font
								color="#ffffff">重新上传简历 </font> </a>
						</s:if>
						<s:else>
							<a
								href="UsersAction!findUserById.action?pageStatus=1&&id=${user.id}"><font
								color="#ffffff">上传简历 </font> </a>
						</s:else>
						<s:if
							test='user.password.userStatus=="完成"||user.password.userStatus=="薪资处理"'>
							<s:if
								test='user.password.contract!=null && user.password.contract!=""'>
								<a
									<%--								href="DownAction.action?fileName=${user.password.contract}&directory=/upload/user/"><font--%>
								href="FileViewAction.action?FilePath=/upload/user/${user.password.contract}"><font
									color="#ffffff">下载合同</font> </a>
								<a
									href="UsersAction!findUserById.action?id=${user.id}&pageStatus=2">
									<font color="#ffffff">重新上传合同</font> </a>
								<a href="ContractAction!findContractByUid.action?id=${user.id}"
									target="print"><font color="#ffffff">系统合同</font> </a>
							</s:if>
							<s:else>
								<a
									href="UsersAction!findUserById.action?id=${user.id}&pageStatus=2">
									<font color="#ffffff">上传合同</font> </a>
								<a href="ContractAction!findContractByUid.action?id=${user.id}"
									target="print"><font color="#ffffff">系统合同</font> </a>
							</s:else>
						</s:if>
						<a href="JoinTrainAction!findJoinTrainByUid.action?id=${user.id}"
							target="_blank"><font color="#ffffff">培训记录 </font> </a>
						<a href="UsersAction!wageSignedAndPrint.action?id=${user.id}"
							target="_blank"><font color="#ffffff">薪资调整协议书 </font> </a>
						<a href="UsersAction!findWorkrecords2.action?id=${user.id}"
							target="_blank"><font color="#ffffff">工作记录 </font> </a>
						<a href="FingerprintMgAction_findOneFing.action?id=${user.id}"><font
							color="#ffffff">指纹管理 </font> </a>
						<a href="faceAction!gotoUsersFaceList.action?userId=${user.id}"><font
							color="#ffffff">人脸识别 </font> </a>
						<a href="dimissionLogAction_toadd.action?id=${user.id}"><font
							color="#ffffff">离职申请单 </font> </a>
						<a href="dimission_XieYiAction_toUsersAdd.action?id=${user.id}"><font
							color="#ffffff">离职协议 </font> </a>
					</div>
			</div>
			</s:if>
			<div align="center">
				<div align="center">
					<font color="red">${successMesssage}</font>
					<font color="red">${errorMessage}</font>
				</div>
				<div id="updateUser">
					<%--					<div>--%>
					<%--						<s:if test='user.onWork=="离职中"'>--%>
					<%--										员工${user.name}即将离职,为其处理两件事：<br />--%>
					<%--							<br />--%>
					<%--							<form action="UsersAction!upload.action" method="post"--%>
					<%--								enctype="multipart/form-data">--%>
					<%--								<font color="red" size="4px">1、上传离职协议单</font>--%>
					<%--								<br />--%>
					<%--								<input type="hidden" name="id" value="${user.id}">--%>
					<%--								<input type="file" name="picture">--%>
					<%--								<input type="submit" value="上传"--%>
					<%--									style="width: 60px; height: 30px">--%>
					<%--								<br />--%>
					<%--							</form>--%>
					<%--							<hr />--%>
					<%--							<font color="red" size="4px">2、<a--%>
					<%--								href="WageStandardAction!findWSByCode.action?code=${user.code}&pageStatus=leave&userId=${user.id}">离职工资处理</a>--%>
					<%--							</font>--%>
					<%--							<br />--%>
					<%--							<hr />--%>
					<%--							<font color="red" size="4px">3、<a--%>
					<%--								href="InformAction!start.action?leaveInform.code=${user.code}">离职通知单</a>--%>
					<%--							</font>--%>
					<%----%>
					<%--							<br />--%>
					<%--							<br />--%>
					<%--						</s:if>--%>
					<%--					</div>--%>
					<form action="UsersAction!updateUser.action" method="post"
						onsubmit="return chackForm()" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${user.id}">
						<input type="hidden" name="user.id" value="${user.id}">
						<input type="hidden" name="user.password.password"
							value="${user.password.password}">
						<input type="hidden" name="user.password.picture"
							value="${user.password.picture}">
						<input type="hidden" name="user.password.resume"
							value="${user.password.resume}">
						<input type="hidden" name="user.password.userStatus"
							value="${user.password.userStatus}">
						<input type="hidden" name="user.password.deptNumber"
							value="${user.password.deptNumber}">
						<input type="hidden" name="user.password.contract"
							value="${user.password.contract}">
						<table width="100%" border="0" class="table" bgcolor="#F0F0F0">
							<tr>
								<th colspan="6" align="center">
									<font color="red">${user.name}</font>的信息维护 (入职流程:
									<s:if test="pageStatus!='show'">
										<a href="UsersAction!findUserById.action?id=${user.id}"
											target="_blank"> ${user.password.userStatus}</a>
									</s:if>
									<s:else>
										${user.password.userStatus}
									</s:else>
									)
								</th>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									个人资料
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									姓名:
								</th>
								<td>
									<input id="userName" name="user.name" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.name}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									性别:
								</th>
								<td>
									<s:if test='user.sex=="男"'>
										<input type="radio" name="user.sex" value="男"
											checked="checked">
									男
									<input type="radio" name="user.sex" value="女">
									女
									</s:if>
									<s:else>
										<input type="radio" name="user.sex" value="男">
									男
									<input type="radio" name="user.sex" value="女" checked="checked">
									女
								</s:else>
									<font color="red"> *</font>
								</td>
								<th rowspan="3" align="center">
									照片
								</th>
								<td rowspan="3">
									<s:if test='user.sex =="男"'>
										<img alt="${user.name}"
											src="upload/user/${user.password.picture}" width="120px;"
											style="border: solid 1px #000000; height: 130px;"
											onerror="this.src='images/man.jpg'">
									</s:if>
									<s:else>
										<img alt="${user.name}"
											src="upload/user/${user.password.picture}" width="120px;"
											style="border: solid 1px #000000; height: 130px;"
											onerror="this.src='images/woman.jpg'">
									</s:else>
								</td>
							</tr>
							<tr>

								<th align="right">
									民族:
								</th>
								<td>
									<select id="nation" name="user.nation" style="width: 150px;">
										<option value="${user.nation}">
											${user.nation}
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
								<th align="right">
									籍贯:
								</th>
								<td>
									<input id="birthplace" name="user.birthplace"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.birthplace}">
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									户籍:
								</th>
								<td>
									<input id="residence" name="user.residence"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.residence}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									出生年月:
								</th>
								<td>
									<input class="Wdate" type="text" name="user.bothday"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										value="${user.bothday}" />
								</td>
							</tr>
							<tr>
								<th align="right">
									学历:
								</th>
								<td>
									<select id="education" name="user.education"
										style="width: 150px;">
										<option value="${user.education}">
											${user.education}
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
								</th>
								<td>
									<input id="uid"
										onkeyup="document.getElementById('check').href='http://qq.ip138.com/idsearch/index.asp?action=idcard&userid='+this.value;"
										name="user.uid" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine');document.getElementById('check').href='http://qq.ip138.com/idsearch/index.asp?action=idcard&userid='+this.value;"
										maxlength="18" value="${user.uid}">
									<font color="red"> *<a id="check" target="_blank"
										href="http://qq.ip138.com/idsearch/index.asp?action=idcard&userid=${user.uid}">(验)</a>
									</font>
								</td>
								<th align="right">
									重传照片:
								</th>
								<td>
									<input type="file" name="picture">
								</td>
							</tr>
							<tr>
								<th align="right">
									员工性质:
								</th>
								<td>
									<s:if test='user.password.staffNature=="正式"'>
										<input type="radio" name="user.password.staffNature"
											value="正式" checked="checked">
									正式
									<input title="劳务派遣人员,无需签订合同" type="radio"
											name="user.password.staffNature" value="劳务">
									劳务
									</s:if>
									<s:else>
										<input type="radio" name="user.password.staffNature"
											value="正式">
									正式
									<input title="劳务派遣人员,无需签订合同" type="radio"
											name="user.password.staffNature" value="劳务" checked="checked">
									劳务
								</s:else>
									<font color="red"> *</font>
								</td>
								<th align="right">
									户籍性质:
								</th>
								<td>
									<s:if test='user.password.censusNature=="农业"'>
										<input type="radio" name="user.password.censusNature"
											value="农业" checked="checked">
									农业
									<input type="radio" name="user.password.censusNature"
											value="非农">
									非农</s:if>
									<s:else>
										<input type="radio" name="user.password.censusNature"
											value="农业">
									农业
									<input type="radio" name="user.password.censusNature"
											value="非农" checked="checked">
									非农
									</s:else>
									<font color="red">*</font>
								</td>
								<th align="right">
									婚姻状况:
									<br />
									marital status:
								</th>
								<td>
									<input type="text" name="user.password.maritalStatus" 
									value="${user.password.maritalStatus}"
									class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								<s:if test="bool">
									<th align="right">
										用户性质:
										<br />
										(考勤机专用)
									</th>
									<td>
										<select name="user.user_privilege" class="horizontalLine">
											<s:if
												test="user.user_privilege==null||user.user_privilege==''||user.user_privilege=='USER'">
												<option value="USER" title="普通用户" selected="selected">
													用户
												</option>
												<option value="MANAGER" title="有管理考勤机的权限">
													管理员
												</option>
											</s:if>
											<s:else>
												<option value="USER" title="普通用户">
													用户
												</option>
												<option value="MANAGER" title="有管理考勤机的权限"
													selected="selected">
													管理员
												</option>
											</s:else>
										</select>
									</td>
								</s:if>
							</tr>
							<tr>
								<th align="right">
									毕业院校:
									<br />
									Graduation Institutions:
								</th>
								<td>
									<input type="text" name="user.password.graduationInstitutions"
										value="${user.password.graduationInstitutions}"
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
										value="${user.password.speciality}"
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
										value="${user.password.dateOfGraduation}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
							</tr>
							<tr> 
								<th align="right">
									政治面貌:
									<br />
									political aspects:
								</th>
								<td colspan="10">
									<input type="text" name="user.password.politicalAspects"
									value="${user.password.politicalAspects}" 
									class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									工作岗位信息
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									卡号:
								</th>
								<td>
									<input id="cardId" name="user.cardId" class="horizontalLine"
										value="${user.cardId}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									工号:
								</th>
								<td>
									<input id="code" name="user.code" class="horizontalLine"
										readonly="readonly" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.code}" />
									<font color="red"> *</font>
								</td>
								<th align="right">
									部门:
								</th>
								<td>
									<%--									<input type="hidden" value="${user.dept}" name="user.dept"/>--%>
<%--									<input type="text"--%>
<%--										value="${user.dept}(${user.password.deptNumber})"--%>
<%--										class="horizontalLine" readonly="readonly" />--%>
									<select id="dept" name="user.dept" id="dept"
										style="width: 150px;">
										<option value="${user.dept}">
											${user.dept}(${user.password.deptNumber})
										</option>
									</select>
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									职务:
								</th>
								<td>
									<input name="user.duty" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.duty}">
								</td>
								<th align="right">
									职级:
								</th>
								<td>
									<%--																		<input name="user.post" class="horizontalLine"--%>
									<%--																			onfocus="chageClass(this,'')"--%>
									<%--																			onblur="chageClass(this,'horizontalLine')"--%>
									<%--																			value="${user.post}">--%>
									<!-- 										<input name="user.post" value="${user.post}"  -->
									<!-- 										readonly="readonly" class="horizontalLine" /> -->
									<select id="post" name="user.post">
										<option selected="selected">
											${user.post}
										</option>
									</select>
								</td>

								<th align="right">
									入职时间:
								</th>
								<td>
									<input id="joined" class="Wdate" type="text" name="user.joined"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										value="${user.joined}" />
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									借物品:
								</th>
								<td>
									<input type="radio" name="user.power" value="允许"
										checked="checked">
									是
									<input type="radio" name="user.power" value="否">
									否
								</td>
								<th align="right">
									状态:
								</th>
								<td>
									<s:if test="tag== 'kc'">
										<SELECT name="user.onWork" style="width: 150px">
											<option value="${user.onWork}">
												${user.onWork}
											</option>
											<option value="直接离职">
												已离职
											</option>
											<option value="实习">
												实习
											</option>
											<option value="试用">
												试用
											</option>
											<option value="在职">
												在职(正式)
											</option>
											<option value="退休">
												退休
											</option>
											<option value="内退">
												内退
											</option>
											<option value="病休">
												病休
											</option>
										</SELECT>
									</s:if>
									<s:else>
										<input type="text" value="${user.onWork}" name="user.onWork"
											readonly="readonly" class="horizontalLine" />
									</s:else>
								</td>
								<th align="right">
									试用期:
								</th>
								<td>
									<input id="tryDays" class="Wdate" type="text"
										name="user.tryDays"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										value="${user.tryDays}" />
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
										onblur="chageClass(this,'horizontalLine')"
										value="${user.jobtitle}">
								</td>
								<th align="right">
									职称级别:
									<br />
								</th>
								<td>
									<input name="user.leveltitles" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.leveltitles}">
								</td>
								<th align="right">
									职称取得时间:
									<br />
								</th>
								<td>
									<input name="user.password.jobtitleGetTime" class="Wdate"
										value="${user.password.jobtitleGetTime}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
							</tr>
							<tr>
								<th align="right">
									是否属于内部人员:
									<br />
								</th>
								<td colspan="1">
									<s:if test='user.internal=="是"'>
										<input type="radio" name="user.internal" value="是"
											checked="checked">
									是
									<input type="radio" name="user.internal" value="否">
									否
									</s:if>
									<s:else>
										<input type="radio" name="user.internal" value="是">
									是
									<input type="radio" name="user.internal" value="否"
											checked="checked">
									否
								</s:else>
									<font color="red"> *</font>
								</td>
								<th align="right">
									人员级别:
									<br />
								</th>
								<td>
									<input name="user.dutyLevel" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.dutyLevel}">
								</td>
								<th align="right">
									备注:
									<br />
								</th>
								<td colspan="2">
									<input name="user.more" class="horizontalLine"
										style="width: 200px" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.more}">
								</td>



							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									联系方式
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									邮箱:
								</th>
								<td>
									<input id="mailBox" name="user.password.mailBox"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.mailBox}">
								</td>
								<th align="right">
									电话:
								</th>
								<td>
									<input onkeyup="this.value=this.value.replace(/\D/g,'')"
										name="user.password.telephone" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.telephone}">
								</td>
								<th align="right">
									手机:
								</th>
								<td>
									<input id="phoneNumber"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										name="user.password.phoneNumber" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.phoneNumber}">
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									现住址:
								</th>
								<td colspan="3">
									<input id="presentAddress" name="user.password.presentAddress"
										style="width: 455px" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.presentAddress}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									班次:
									<input id="whiteCard" type="hidden" name="user.whiteCard"
										value="${user.whiteCard}" />
									<input id="parkAdmin" type="hidden" name="user.parkAdmin"
										value="${user.parkAdmin}" />
									<input id="banci_id" type="hidden" name="user.banci_id"
										value="${user.banci_id}" />
									<input id="banci_name" type="hidden" name="user.banci_name"
										value="${user.banci_name}" />
								</th>
								<td colspan="1">
									<SELECT onclick="banciguan()" id="banciselect"
										class="horizontalLine" style="width: 153px;">
										<option value="${user.banci_name}|${user.banci_id}">
											${user.banci_name}
										</option>
										<option></option>
										<%--										<s:iterator value="banciList" id="banci" status="pageStatus">--%>
										<%--											<option value="${banci.name}|${banci.id}">--%>
										<%--												${banci.name}--%>
										<%--											</option>--%>
										<%--										</s:iterator>--%>
									</SELECT>
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									劳资信息
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									员工性质:
								</th>
								<td>
									<select name="userNature">
										<option value="${user.userNature}">
											${user.userNature}
										</option>
										<option value="在岗">
											在岗
										</option>
										<option value="劳务派遣">
											劳务派遣
										</option>
										<option value="离退休人员">
											离退休人员
										</option>
										<option value="外籍、港澳台人员">
											外籍、港澳台人员
										</option>
										<option value="非全日制、兼职、第二职业者">
											非全日制、兼职、第二职业者
										</option>
									</select>
								</td>
								<th align="right">
									所属银行:
								</th>
								<td>
									<input name="user.bank" class="horizontalLine"
										value="${user.bank}">
								</td>
								<th align="right">
									银行卡号:
								</th>
								<td>
									<input name="user.bankCards" class="horizontalLine"
										value="${user.bankCards}">
								</td>
							</tr>
							<tr>
								<th align="right">
									社保账号:
								</th>
								<td>
									<input name="user.password.ssAccount" class="horizontalLine"
										value="${user.password.ssAccount}">	
								</td>
								<th align="right">
									公积金账号:
								</th>
								<td>
									<input name="user.password.gjjAccount" class="horizontalLine"
										value="${user.password.gjjAccount}">
								</td>
								<th align="right">
								</th>
								<td>
								</td>
							</tr>
							<s:if test="pageStatus!='show'">
								<tr>
									<td colspan="6" align="center">
										<br />
										<br />
										<s:if
											test='(user.onWork!="离职"&&user.onWork!="离职中")||tag=="kc"'>
											<input type="submit" value="修改"
												style="width: 80px; height: 50px;" />
											<input type="reset" value="重置"
												style="width: 80px; height: 50px;" />
										</s:if>
										<br />
										<br />
									</td>
								</tr>
							</s:if>
						</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
