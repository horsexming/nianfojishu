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
	<body onload="createDept('dept')">
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
							<span id="title">您正在对积分项进行操作</span>
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
				<div style="margin: 10px;">
					<input class="input" onclick="window.history.back();" type="button"
						value="返回" style="height: 38px; width: 56px; float: left;">
				</div>
				<br />
				<s:if test="statue!='person'">
					<form action="IntegralAction_findIntegral.action" method="post">
						<table>
							<tr>
								<th align="right">
									部门:
								</th>
								<td>
									<select name="integral.integrdept" id="dept"
										style="width: 155px;" onchange="showzpname()">
										<option value=""></option>
									</select>
								</td>
								<th align="right">
									姓名:
								</th>
								<td>
									<select name="" id="users" style="width: 155px;"
										onclick="sel()">
										<option value=""></option>
									</select>
									<input type="hidden" name="integral.integralName" id="userName"
										value="" />
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="submit" value="查询"
										style="width: 75px; height: 30px;" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value="添加"
										style="width: 75px; height: 30px;" onclick="tanchu()" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input class="input" onclick="window.history.back();"
										type="button" value="返回" />
								</td>
							</tr>
						</table>
					</form>
				</s:if>
				<s:else>
					<h2>
						个人积分信息
					</h2>
				</s:else>
				<br />
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<td>
							序号
						</td>
						<td>
							积分
						</td>
						<td>
							积分所属人部门
						</td>
						<td>
							积分所属人姓名
						</td>
						<td>
							积分所属人员工号
						</td>
						<td>
							本月累计消费积分
						</td>
						<td>
							积分来源项
						</td>
						<td>
							消费记录
						</td>
						<s:if test="statue != 'person'">
							<td>
								操作
							</td>
						</s:if>
					</tr>
					<s:iterator value="inList" id="inlist" status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')" width="80%">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')" width="80%">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${inlist.totalIntegral}
						</td>
						<td>
							${inlist.integrdept}
						</td>
						<td>
							${inlist.integralName}
						</td>
						<td>
							${inlist.integrcode}
						</td>
						<td>
							<s:if test="#inlist.xfmonth!=null && month == #inlist.xfmonth">
								${inlist.sumxf}
							</s:if>
						</td>
						<td>
							<a href="javascript:;"
								onclick="tanchu('laiyuan','${inlist.integrcode}')">积分来源项</a>
						</td>
						<td>
							<a href="javascript:;"
								onclick="tanchu('xiaofei','${inlist.integrcode}','${statue}')">消费记录</a>
						</td>
						<s:if test="statue != 'person'">
							<td>

								<a href="javascript:;" onclick="tanchu('tiao1','${inlist.id}')">消费</a>/
								<a href="javascript:;" onclick="tanchu('tiao2','${inlist.id}')">增加积分</a>/
								<a onclick="return confirm('确定要删除吗?') "
									href='IntegralAction_delIntegral.action?integral.id=${inlist.id}'>删除</a>

							</td>
						</s:if>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="9" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var select;
function showzpname() {
	deptname = document.getElementById("dept").value;
	select = document.getElementById("users");
	if (deptname != "") {
		$.ajax( {
			type : "POST",
			url : "UsersAction!findUsersByDept.action",
			cache : false,//防止数据缓存
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				deptName : deptname
			},
			dataType : "json",
			success : function(useradsfa) {
				$("#users").empty();//清空
				$("<option></option>").appendTo("#users");
				$(useradsfa).each(
						function() {
							$(
									"<option value='" + this.code + "|"
											+ this.name + "|" + this.id + "|"
											+ this.cardId + "'>" + this.name
											+ "</option>").appendTo("#users")
						});
				$("#users").bind("change", function() {
					var user = $("#users").val();
					var userCodeName = user.split("|");
					if (userCodeName != "") {
						$("#code").val(userCodeName[0]);
						$("#userName").val(userCodeName[1]);
						$("#userId").val(userCodeName[2]);
						$("#cardId").val(userCodeName[3]);
					} else {
						$("#code").val("");
						$("#userName").val("");
					}
				})

			},
			error : function() {
				alert("服务器异常!");
			}
		});
	} else {
		$("#users").empty();//清空
	}
}
function tanchu(obj1, obj2, statue) {
	if (obj1 == null || obj1 == undefined) {
		document.getElementById("xiugaiIframe").src = "IntegralAction_tiaozhuan.action";
	} else if (obj1 == "laiyuan") {
		document.getElementById("xiugaiIframe").src = "IntegralAction_showListIs.action?is.in_code="
				+ obj2;
	} else if (obj1 == "xiaofei") {
		document.getElementById("xiugaiIframe").src = "IntegralAction_showListXf.action?xf.in_code="
				+ obj2 + "&statue=" + statue;
	} else if (obj1 == 'tiao1') {
		document.getElementById("xiugaiIframe").src = "IntegralAction_tiaozhuan1.action?statue=xiaofei&integral.id="
				+ obj2;
	} else if (obj1 == 'tiao2') {
		document.getElementById("xiugaiIframe").src = "IntegralAction_tiaozhuan1.action?statue=zengjia&integral.id="
				+ obj2;
	} else if (obj1 == 'test') {
		document.getElementById("xiugaiIframe").src = "IntegralAction_tiaozhuan2.action";
	}

	chageDiv('block')
}
function sel() {
	var dept = document.getElementById("dept").value;
	if (dept == "") {
		alert("请先选择部门")
	}
}
</script>
	</body>
</html>
