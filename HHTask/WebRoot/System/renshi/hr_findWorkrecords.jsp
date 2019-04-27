<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px;" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
					</div>
				</div>

				<div align="center">
					<form action="UsersAction!findWorkrecords2.action?id=<s:property value="id"/>" method="post">
<%--						<table class="table">--%>
<%--							<tr>--%>
<%--								<td align="center">--%>
<%--									件号：--%>
<%--									<div id="showAll"--%>
<%--										style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">--%>
<%--									</div>--%>
<%--									<input type="text" id="shortname" onkeyup="getAllNames()"--%>
<%--										style="height: 20px" onFocus="init()"--%>
<%--										onBlur="hidediv('showAll')" name="markId" />--%>
<%--								</td>--%>
<%--									<td align="center">--%>
<%--									工序：--%>
<%--									<div id="showAll1"--%>
<%--										style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">--%>
<%--									</div>--%>
<%--									<input type="text" id="shortname1" onkeyup="getAllNames1(<s:property value="id"/>)"--%>
<%--										style="height: 20px" onFocus="init1()"--%>
<%--										onBlur="hidediv('showAll1')" name="processName" />--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<tr>--%>
<%--								<td colspan="4" align="center">--%>
<%--									<input type="submit" style="width: 100px; height: 40px;"--%>
<%--										value="查询" class="input" />--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</table>--%>

						<table class="table" style="width: 100%;">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									工号
								</th>
								<th align="center">
									姓名
								</th>
								<th align="center">
									总提交数量
								</th>
								<th align="center">
									不合格数量
								</th>
								<th align="center">
									工序数
								</th>
								<th align="center">
									总时长(h)
								</th>
								<th align="center">
									明细
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="osaV">
									<s:if test="#se.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#se.index+1" />
									</td>
									<td>
										${osaV[0]}
									</td>
									<td>
										${osaV[1]}
									</td>
									<td>
										${osaV[2]}
									</td>
									<td>
										${osaV[3]}
									</td>
									<div id="showAll3"
										style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
									</div>
									<td id="shortname3" style="background: #a0a3a5;"  onmouseover="init3(${osaV[0]})"  style="height: 20px" 
										onmouseout="hidediv('showAll3')">
										${osaV[4]}
									</td>
									<td>
										${osaV[5]}
									</td>
									<td>
										<a href="ProcessCollectAction_showpcList.action?pc.usercodes=${osaV[0]}&tag=1">明细查看</a>
									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="11" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td colspan="17" style="font-size: 15px; color: red;">
										对不起，没有查到相关信息
									</td>
								</tr>
							</s:else>
						</table>
					</form>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
		<script type="text/javascript">
		//控制件号的下拉位置
function init3(code) {
	count_seach++;
	var shortname = document.getElementById("shortname3");
	var showAll = document.getElementById("showAll3");
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
	mouse(code);
}
		//显示工序
		function mouse(code){
			$.ajax( {
				type : "POST",
				url : "UsersAction!getproNameByCode.action",
				dataType : "json",
				data : {
					code : code
				},
				success : function(data) {
					$("#showAll3").empty();
					$(data).each(function(i) {
										$("#showAll3").append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' align='left'>"
																+ data[i]
																+ "</div>");
									});
				}
			})
		}
		
//控制件号的下拉位置
function init() {
	count_seach++;
	var shortname = document.getElementById("shortname");
	var showAll = document.getElementById("showAll");
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
}
//给件号文本赋值
function selectdiv(obj, shortnameId, showAllId) {
	var shortname = document.getElementById(shortnameId == null ? "shortname"
			: shortnameId);
	//shortname.value=$(obj).find("span").html();
	shortname.value = $(obj).html();
	var showAll = document.getElementById(showAllId == null ? "showAll"
			: showAllId);
	showAll.style.visibility = "hidden";
}
//查询所有件号
function getAllNames() {
	$
			.ajax( {
				type : "POST",
				url : "UsersAction!getProcardAllNames.action",
				dataType : "json",
				data : {
					markId : $("#shortname").val()
				},
				success : function(data) {
					$("#showAll").empty();
					$(data)
							.each(
									function(i) {
										$("#showAll")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)' align='left'>"
																+ data[i].markId
																+ "</div>");
									});
				}
			});
}

//控制工位的下拉位置
function init1() {
	count_seach++;
	var shortname = document.getElementById("shortname1");
	var showAll = document.getElementById("showAll1");
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
}
//给工位文本赋值
function selectdiv1(obj, shortnameId, showAllId) {
	var shortname = document.getElementById(shortnameId == null ? "shortname1"
			: shortnameId);
	shortname.value = $(obj).html();
	var showAll = document.getElementById(showAllId == null ? "showAll1"
			: showAllId);
	showAll.style.visibility = "hidden";
}
//ajax获取所有工序
function getAllNames1(id) {
	$.ajax( {
				type : "POST",
				url : "UsersAction!getprocessNONames.action",
				dataType : "json",
				data : {
					processName : $("#shortname1").val(),
					id:id
				},
				success : function(data) {
					$("#showAll1").empty();
					$(data)
							.each(
									function(i) {
										$("#showAll1")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv1(this)' align='left'>"
																+ data[i].processNO
																+ "("+data[i].processName+")"+"</div>");
									});
				}
			});
}
</script>
	</body>
</html>
