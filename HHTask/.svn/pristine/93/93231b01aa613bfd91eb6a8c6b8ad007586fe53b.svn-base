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
		<style type="text/css">
.users {
	position: absolute;
	top: 100px;
	left: -4%;
	width: 23%;
	height: 200px;
	margin-left: 5%;
	margin-top: 100px;
	font-size: large;
	font-family: 黑体;
}

.users ul {
	list-style: inside;
}

.users ul li {
	margin-top: 5px;
	border-bottom: 1px solid #666 dotted;
	list-style-type: disc;
}

.b_0 {
	position: relative;
	float: left;
	width: 18px;
	top: 50px;
}

.b_1 {
	width: 22%;
	float: left;
}

.b_2 {
	width: 36%;
	float: left;
}

.b_3 {
	width: 39%;
	float: left;
}

.z {
	width: 71%;
	height: auto;
	float: left;
	border-left: solid 1px #000000;
}

.a {
	position: relative;
	width: 100%;
	border-bottom: dotted 1px #000000;
	overflow: hidden;
	padding: 10 0 20 0px;
}

.c_2 {
	width: 100%;
	position: relative;
	font-size: large;
	position: relative;
	padding-top: 80px;
}

.c_2_span {
	text-decoration: underline;
	width: 80%;
	top: 70px;
}

.c_1 {
	position: relative;
	border: solid 1px #ADADAD;
	border-radius: 35px;
	width: 70px;
	height: 70px;
	background-color: #0071bf;
	cursor: hand;
	z-index: 0;
}

.c_3 {
	position: relative;
	width: 260px;
	height: 111px;
	border: solid 1px #ADADADk;
	background-color: #0071bf;;
}

.c_1_font {
	color: #FFFFFF;
	font-size: x-large;
}

.c_3_font {
	color: #FFFFFF;
}

.b_0_hr {
	float: right;
	width: 100%;
	height: 2px;
	border: none;
	background-color: #909090;
}

.c1_span {
	position: relative;
	top: 12px;
}
</style>
		<SCRIPT type="text/javascript">
window.onscroll=function(){ 
	var oDiv=document.getElementById("userxin"); 
	oDiv.style.top=document.body.scrollTop + 100;  //控制上下位置
//oDiv.style.left = document.body.scrollLeft + 300; //控制横向位置

} 
<%--window.parent.onscroll=function(){ --%>
<%--	var oDiv=document.getElementById("userxin"); --%>
<%--	var h = window.parent.document.body.scrollTop;--%>
<%--	if(h==0){--%>
<%--		alert('到顶了')--%>
<%--		oDiv.style.top = 120;--%>
<%--	}--%>
<%--	alert(h)--%>
<%--	oDiv.style.top=h - 200;  //控制上下位置--%>
<%----%>
<%--} --%>

</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div style="width: 23%; height: 100%; float: left;">
					<div style="" class="users" id="userxin" align="left">
						<ul>
							<li>
								姓名:${ck.userName}
							</li>
							<li>
								部门:${ck.dept}
							</li>
							<li>
								学历:${ck.education}
							</li>
							<li>
								职位:${ck.job}
							</li>
							<li>
								籍贯:${ck.birthplace}
							</li>
							<li>
								手机号:${ck.phoneNumber}
							</li>
							<li>
								身份证号:${ck.cardId}
							</li>
							<li>
								出生年月:${ck.bothday}
							</li>
							<s:if test="ck!=null && ck.userId">
								<li>
									<a href="javascript:;"
										onclick="tanchu('UsersAction!findUserByIdForDetails.action?id=${user.id}&pageStatus=show'
							,'员工基本资料查看')">员工基本资料</a>
								</li>
							</s:if>
						</ul>

					</div>
				</div>
				<div class="z" align="left">
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1" style="">
							<br />
							<s:if test="ck == null">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="zhuandao('interviewLogAction_toadd.action')">
									<span><br /> <font class="c_1_font">面试</font> </span>
								</div>
							</s:if>
							<s:else>
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">面试</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">面试时间:${ck.mianshiTime} </span>
							</div>
						</div>
						<div class="b_3" style="">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if test="minashiId!=null && minashiId>0">
									<s:if test="ck.status == '面试'">
										<a href="javascript:;"
											onclick="tanchu('interviewLogAction_toupdate.action?interviewLog.id=${minashiId}'
							,'面试登记单查看')"><font
											class="c_3_font">面试登记单</font> </a>
										<br>
									</s:if>
									<s:else>
										<a href="javascript:;"
											onclick="tanchu('interviewLogAction_toupdate.action?ccTag=show&interviewLog.id=${minashiId}'
							,'面试登记单查看')"><font
											class="c_3_font">面试登记单</font> </a>
										<br>
									</s:else>
									<a href="javascript:;"
										onclick="tanchu('interviewQuizzesAction_selectId.action?ccTag=show&interviewLog.id=${minashiId}'
							,'面试测试单查看')"><font
										class="c_3_font">面试测试单</font> </a>
									<br>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未添加过面试登记单。</STRONG> </font>

								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if test="statue != 'person' && ck.status == '待入职'">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="tanchu('interviewLogAction!to_add_ruzhi.action?interviewLog.id=${minashiId}','入职')">
									<span><br /> <font class="c_1_font">入职</font> </span>
								</div>
							</s:if>
							<s:elseif test="ck.ruzhiTime!=null">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">入职</font> </span>
								</div>
							</s:elseif>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090">
									<span><br /> <font class="c_1_font">入职</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">入职时间:${ck.ruzhiTime} </span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null && (ck.ruzhiTime != null || ck.userId != null)">
									<a href="javascript:;"
										onclick="tanchu('UsersAction!findUserByIdForDetails.action?id=${user.id}&pageStatus=show'
							,'员工基本资料查看')"><font
										class="c_3_font">员工基本资料</font> </a>
									<br>
									<a href="javascript:;"
										onclick="tanchu('ContractAction!findContractByUid.action?id=${user.id}'
							,'系统合同查看')"><font
										class="c_3_font">系统合同</font> </a>
									<br>
									<%--									<s:if--%>
									<%--										test="user.password.resume!=null&&user.password.resume!=''">--%>
									<%--										<a--%>
									<%--											href="DownAction.action?fileName=${user.password.resume}&directory=/upload/user/"><font--%>
									<%--											color="#ffffff">下载简历 </font> </a>--%>
									<%--										<br>--%>
									<%--										<a--%>
									<%--											href="UsersAction!findUserById.action?pageStatus=1&&id=${user.id}"><font--%>
									<%--											color="#ffffff">重新上传简历 </font> </a>--%>
									<%--										<br>--%>
									<%--									</s:if>--%>
									<%--									<s:else>--%>
									<a
										href="UsersAction!findUserById.action?pageStatus=1&id=${user.id}&tag=sc"><font
										color="#ffffff">学历证书</font> </a>
									<br>
									<%--									</s:else>--%>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>

								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if
								test="statue != 'person' && (ck.status == '试用' || ck.status == '实习') && (ck.zzkhTime ==null || zzkh.percentageScore<60)">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onclick="tanchu('BecomingAction_initBecomingkhdf.action?id=${user.id}','转正考核')"
									onmouseout="changout(this)">
									<span class="c1_span"> <font class="c_1_font">转正考核</font>
									</span>
								</div>
							</s:if>
							<s:elseif
								test="ck.zzkhTime!=null || ck.status =='在职'
							||ck.status =='内退'||ck.status =='退休'||ck.status =='病休'
							||ck.status =='离职'">

								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span class="c1_span"> <font class="c_1_font">转正考核</font>
									</span>
								</div>
							</s:elseif>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090">
									<span class="c1_span"> <font class="c_1_font">转正考核</font>
									</span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">考核时间:${ck.zzkhTime} </span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null && (ck.ruzhiTime != null || ck.userId != null)">
									<s:if test="ck.zzkhTime!=null">
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}转正考核得分为:<font color="red" size="5"><fmt:formatNumber
														value="${zzkh.percentageScore}" pattern="0.00"></fmt:formatNumber>
											</font> <s:if test="zzkh!=null && zzkh.percentageScore>60">可转正</s:if>
												<s:else>不可转正</s:else>。</STRONG> </font>
									</s:if>
									<s:elseif test="ck.status=='在职'">
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}已转正。</STRONG> </font>
									</s:elseif>
									<s:else>
										<font class="c_3_font"><br />
											<br />${ck.userName}尚未进行转正考核 </font>
									</s:else>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>
								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if
								test="statue != 'person' && ck.status == '试用' && zzkh!=null && zzkh.percentageScore>60  ">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onclick="tanchu('UsersAction!findUserByIdForDetails.action?id=${user.id}&tag=zz','转正')"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">转正</font> </span>
								</div>
							</s:if>
							<s:elseif
								test="ck.zhuanzhengTime!=null || ck.status =='在职'
							||ck.status =='内退'||ck.status =='退休'||ck.status =='病休'
							||ck.status =='离职'">

								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">转正</font> </span>
								</div>
							</s:elseif>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090">
									<span><br /> <font class="c_1_font">转正</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">转正时间:${ck.zhuanzhengTime} </span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null && (ck.ruzhiTime != null || ck.userId != null)">
									<s:if test="ck.zhuanzhengTime!=null">
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}于<font id="zz_font">${ck.zhuanzhengTime}</font>转正。</STRONG>
										</font>
									</s:if>
									<s:elseif test="ck.status=='试用'">
										<s:if test="tianshu!=null && tianshu>0">
											<font class="c_3_font"> <br /> <br /> <STRONG>
													${ck.userName}还剩<font color="red" size="5">${tianshu}</font>天试用期到期。</STRONG>
											</font>
										</s:if>
										<s:elseif test="tianshu!=null && 0>=tianshu">
											<font class="c_3_font"> <br /> <br /> <STRONG>
													${ck.userName}试用期已到期。</STRONG> </font>
										</s:elseif>
										<s:else>
											<font class="c_3_font"> <br /> <br /> <STRONG>
													${ck.userName}尚未转正。</STRONG> </font>
										</s:else>
									</s:elseif>
									<s:elseif test="ck.status=='在职'">
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}已转正。</STRONG> </font>
									</s:elseif>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>
								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if test="ck.jixiaoTime!=null">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">绩效</font> </span>
								</div>
							</s:if>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090">
									<span><br /> <font class="c_1_font">绩效</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">考核时间:${ck.jixiaoTime}</span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null && (ck.ruzhiTime != null || ck.userId != null)">
									<s:if test="ck.jixiaoTime!=null && ck.jixiaoTime!=''">
										<table class="table">
											<tr>
												<th>
													序号
												</th>
												<th>
													月份
												</th>
												<th>
													成绩
												</th>
											</tr>
											<s:iterator value="jxList" id="pageList" status="pageStatus">
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
													<s:property value="#pageStatus.index+1" />
												</td>
												<td>
													${pageList.asstMouth}
												</td>
												<td>
													${pageList.percentageScore}
												</td>
												</tr>
											</s:iterator>
										</table>
										<s:if test="jxsize >=3">
											<input type="button" value="更多"
												onclick="tanchu('AssScoreAction!findAssScoreByCondition.action?assScore.userId=${pageList.userId}&status=person','绩效查看')" />

										</s:if>
									</s:if>
									<s:else>
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}尚未获得过绩效。</STRONG> </font>
									</s:else>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>
								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if
								test="statue != 'person' && ck!=null && (ck.status == '试用' || ck.status == '在职')">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="tanchu('CareertrackAction_tiaozhuan.action?id=${ck.id}&tag=hr_rewardPunish_add','添加特别奖金')">
									<span><br /> <font class="c_1_font">奖惩</font> </span>
								</div>
							</s:if>
							<s:elseif test="ck.jiangchengTime!=null && ck.jiangchengTime!=''">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">奖惩</font> </span>
								</div>
							</s:elseif>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090;">
									<span><br /> <font class="c_1_font">奖惩</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">奖惩时间:${ck.jiangchengTime}</span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null && (ck.ruzhiTime != null || ck.userId != null)">
									<s:if
										test="ck.jiangchengTime!=null && ck.jiangchengTime!='' &&jcList!=null ">
										<table class="table">
											<tr>
												<th>
													序号
												</th>
												<th>
													月份
												</th>
												<th>
													金额
												</th>
											</tr>
											<s:iterator value="jcList" id="pageList" status="pageStatus">
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
													<s:property value="#pageStatus.index+1" />
												</td>
												<td>
													<fmt:formatDate value="${pageList.date}"
														pattern="yyyy-MM-dd" />
												</td>
												<td>
													${pageList.money}
												</td>
												</tr>
											</s:iterator>
										</table>
										<s:if test="jcsize >=3">
											<input type="button" value="更多"
												onclick="tanchu('rewardPunishAction!findAllRewardPunish.action?rewardPunish.userId=${pageList.userId}&status=person','个人奖惩查看')" />
										</s:if>
									</s:if>
									<s:else>
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}尚未获得过奖惩。</STRONG> </font>
									</s:else>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>
								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if
								test="statue != 'person' && ck!=null && (ck.status == '试用' || ck.status == '在职')">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="tanchu('CareertrackAction_tiaozhuan.action?id=${ck.id}&tag=transferadd','调动申请')">
									<span><br /> <font class="c_1_font">调动</font> </span>
								</div>
							</s:if>
							<s:elseif test="ck.diaodongTime!=null">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">调动</font> </span>
								</div>
							</s:elseif>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090">
									<span><br /> <font class="c_1_font">调动</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">调动时间:${ck.diaodongTime} </span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null &&(ck.ruzhiTime != null || ck.userId != null)">
									<s:if test="ck.diaodongTime!=null">
										<s:if test="transfer!=null">
											<font class="c_3_font"> <br /> <STRONG>
													${ck.userName}于<font id="dd_font"></font>由${transfer.beforeDept}部调往${transfer.dept}部。</STRONG>
											</font>
											<br>
											<input type="button" value="明细"
												onclick="tanchu('TransferAction_showtransferbyId.action?id=${transfer.id}','${ck.userName}的调动明细查看')" />
										</s:if>
									</s:if>
									<s:else>
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}尚未发生过调动。</STRONG> </font>
									</s:else>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>
								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if test="statue != 'person' && ck!=null && ck.status == '在职'">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="tanchu('CareertrackAction_tiaozhuan.action?id=${ck.id}&tag=Promotion_add','人员晋升申请')">
									<span><br /> <font class="c_1_font">晋升</font> </span>
								</div>
							</s:if>
							<s:elseif test="ck.jinshengTime!=null">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">晋升</font> </span>
								</div>
							</s:elseif>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090">
									<span><br /> <font class="c_1_font">晋升</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">晋升时间:${ck.jinshengTime} </span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null && (ck.ruzhiTime != null || ck.userId != null)">
									<s:if test="ck.jinshengTime!=null">
										<s:if test="promotion!=null">
											<font class="c_3_font"> <br /> <STRONG>
													${ck.userName}于<font id="js_font"></font>职级晋升为${promotion.rank}职级。</STRONG>
											</font>
											<br>
											<input type="button" value="明细"
												onclick="tanchu('PromotionAction_findpnById.action?id=${promotion.id}&status=show','${ck.userName}的晋升明细查看')" />
										</s:if>
										<s:else>
											<font class="c_3_font"> <br /> <br /> <STRONG>
													${ck.userName}的晋升信息已被删除。</STRONG> </font>
										</s:else>
									</s:if>
									<s:else>
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}尚未发生过晋升。</STRONG> </font>
									</s:else>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>
								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if test="statue != 'person' && ck!=null && ck.status == '在职'">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="tanchu('CareertrackAction_tiaozhuan.action?id=${ck.id}&tag=Promotion_add','人员晋升申请')">
									<span><br /> <font class="c_1_font">降职</font> </span>
								</div>
							</s:if>
							<s:elseif test="ck.jinshengTime!=null">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">降职</font> </span>
								</div>
							</s:elseif>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090">
									<span><br /> <font class="c_1_font">降职</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">降职时间:${ck.jinshengTime} </span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null && (ck.ruzhiTime != null || ck.userId != null)">
									<s:if test="ck.jinshengTime!=null">
										<s:if test="promotion!=null">
											<font class="c_3_font"> <br /> <STRONG>
													${ck.userName}于<font id="js_font"></font>职级降职为${promotion.rank}职级。</STRONG>
											</font>
											<br>
											<input type="button" value="明细"
												onclick="tanchu('PromotionAction_findpnById.action?id=${promotion.id}&status=show','${ck.userName}的降职明细查看')" />
										</s:if>
										<s:else>
											<font class="c_3_font"> <br /> <br /> <STRONG>
													${ck.userName}的降职信息已被删除。</STRONG> </font>
										</s:else>
									</s:if>
									<s:else>
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}尚未发生过降职。</STRONG> </font>
									</s:else>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>
								</s:else>
							</div>
						</div>
					</div>
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
							<s:if test="statue == 'person'">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="tanchu('dimissionLogAction_toadd.action','添加离职申请单')">
									<span><br /> <font class="c_1_font">离职</font> </span>
								</div>
							</s:if>
							<s:elseif test="ck.lizhiTime!=null">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">离职</font> </span>
								</div>
							</s:elseif>
							<s:else>
								<div align="center" class="c_1"
									style="background-color: #909090;">
									<span><br /> <font class="c_1_font">离职</font> </span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">离职时间:${ck.lizhiTime} </span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<s:if
									test="ck!=null && (ck.ruzhiTime != null || ck.userId != null)">
									<s:if test="lzsq.id!=null && lzsq.id >0">
										<a href="javascript:;"
											onclick="tanchu('dimissionLogAction_toselect.action?dimissionLog.id=${lzsq.id}&tag=all&id=${lzsq.epId}','离职申请单查看')"><font
											color="#ffffff">离职申请单 </font> </a>
										<br>

										<s:if test="lzjj.id !=null && lzjj.id>0">
											<a href="javascript:;"
												onclick="tanchu('dimission_HandoverAction_toselect.action?dimissionHandover.id=${lzjj.id}&tag=all&id=${lzjj.epId}','离职交接单查看')"><font
												color="#ffffff">离职交接单 </font> </a>
											<br>
										</s:if>
										<s:else>
											<a href="javascript:;" onclick="alert('该员工尚未添加离职交接单')"><font
												color="#ffffff">离职交接单 </font> </a>
											<br>
										</s:else>
										<s:if test="lzgzId!=null && lzgzId>0">
											<a href="javascript:;"
												onclick="tanchu('dimission_ZhengYiAction_toselect.action?dimissionZhengYi.id=${lzgzId}','离职工资单查看')"><font
												color="#ffffff">离职工资单 </font> </a>
											<br>
										</s:if>
										<s:else>
											<a href="javascript:;" onclick="alert('该员工尚未添加离职工资单')"><font
												color="#ffffff">离职工资单 </font> </a>
											<br>
										</s:else>
										<s:if test="lztzId!=null && lztzId>0">
											<a href="javascript:;"
												onclick="tanchu('dimission_XieYiAction_toselectNotice.action?dimissionNotice.id=${lztzId}','离职通知单查看')"><font
												color="#ffffff">离职通知单 </font> </a>
											<br>
										</s:if>
										<s:else>
											<a href="javascript:;" onclick="alert('该员工尚未添加离职通知单')"><font
												color="#ffffff">离职通知单 </font> </a>
											<br>
										</s:else>
										<s:if test="zzhtId!=null && zzhtId>0">
											<a href="javascript:;"
												onclick="tanchu('dimission_XieYiAction_toselect.action?dimissionXieYi.id=${zzhtId}','劳动协议终止查看')"><font
												color="#ffffff">劳动协议终止 </font> </a>
										</s:if>
										<s:else>
											<a href="javascript:;" onclick="alert('该员工尚未添加劳动协议终止')"><font
												color="#ffffff">劳动协议终止 </font> </a>
										</s:else>
									</s:if>
									<s:else>
										<font class="c_3_font"> <br /> <br /> <STRONG>
												${ck.userName}尚未添加离职申请单。</STRONG> </font>
									</s:else>
								</s:if>
								<s:else>
									<font class="c_3_font"> <br /> <br /> <STRONG>
											${ck.userName}尚未入职。</STRONG> </font>
								</s:else>
							</div>
						</div>
					</div>

				</div>

			</div>
		</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function tanchu(src,title){
	$("#xiugaiIframe").attr("src",src);
	chageDiv('block',title);
}
function changover(obj){
	$(obj).css({
		background: "#909090",
	})
	
}
function changout(obj){
	$(obj).css({
		background: "#0071bf",
	})
	}
function zhuandao(src){
		window.open(src)
		//tanchu('interviewLogAction_toadd.action','添加面试单')
		
}

$(function(){
	var zhuanzhengTime = "${ck.zhuanzhengTime}".substring(0,10);
	var diaodongTime =  "${ck.diaodongTime}".substring(0,10);
	var	jinshengTime =	"${ck.jinshengTime}".substring(0,10);
	var zzarray = zhuanzhengTime.split("-");
	$("#zz_font").html(zzarray[0]+"年"+zzarray[1]+"月"+zzarray[2]+"日");
	var ddarray = diaodongTime.split("-");
	$("#dd_font").html(ddarray[0]+"年"+ddarray[1]+"月"+ddarray[2]+"日");
	var jsarray = jinshengTime.split("-");
	$("#js_font").html(jsarray[0]+"年"+jsarray[1]+"月"+jsarray[2]+"日");
})
</SCRIPT>
	</body>
</html>
