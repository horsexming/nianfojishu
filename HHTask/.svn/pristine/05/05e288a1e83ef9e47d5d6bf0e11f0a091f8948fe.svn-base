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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
<table class="table" style="width: 50%;">
					<tr><th colspan="2">招标信息</th></tr>
					<tr><th align="right">招标题目:</th>
						<td>${zhaobiao.title}</td>
					</tr>
					<tr>
						<th align="right">招标负责人:</th>
						<td>${zhaobiao.fuze}</td>
					</tr>
					<tr>
						<th align="right">负责人电话:</th>
						<td>${zhaobiao.phone}</td>
					</tr>
					<tr>
						<th align="right">开始时间:</th>
						<td>${zhaobiao.moban}</td>
					</tr>
					<tr>
						<th align="right">结束时间:</th>
						<td>${zhaobiao.kongxian}</td>
					</tr>
					<tr>
						<th align="right">招商简介:</th>
						<td colspan="2" height="100px">${zhaobiao.loc}</td>
					</tr>
				</table>
				<!--               -->
				<br />
				<br />
				<table class="table" style="width: 100%">
					<tr bgcolor="#c0dcf2" height="50px">
						<td>
							ID
						</td>
						<td>
							使用模版
						</td>
						<td>
							数量/单位
						</td>
						<td>
							财务预算
						</td>
						<td>
							规格要求
						</td>
						<td>
							物料名称
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="list" id="zhaobiaoXi" status="pageIndex">
						<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${zhaobiaoXi.zhmoban.name}
							</td>
							<td>
								${zhaobiaoXi.t2}/${zhaobiaoXi.t3}
							</td>
							<td>
								部门：${zhaobiaoXi.deptMonthBudget.budgetMonth}&nbsp;&nbsp;&nbsp;   
							            月份：${zhaobiaoXi.deptMonthBudget.userDept}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							             预算金额：${zhaobiaoXi.deptMonthBudget.name}( ${zhaobiaoXi.deptMonthBudget.accountMoney})
							</td>
							<td>
								${zhaobiaoXi.t5}
							</td>
							<td>
								${zhaobiaoXi.t6}
								<input type="hidden" value="${zhaobiaoXi.id}" id="zhaobiaoXiid" />
							</td>
							<td colspan="6" align="center">
								<a onclick="javascript:fun3('${zhaobiaoXi.zhmoban.name}');"><font
									style="color: 0red;">查看可以对此次招标信息投标的商家</font> </a>
							</td>
						</tr>
					</s:iterator>
				</table>
				<br/><br/>
				<div id="t1"></div>


			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function fun3(name) {
	$.ajax( {
				type : "POST",
				url : "zhaobiaoAction!listtoubiaoyonghu.action",
				data : {
					pagemoban : name
				},
				dataType : "json",
				success : function(object) {
					$("#t1").empty();//清空
				var reshtml = "<table class='table' id='t2'><tr bgcolor='#c0dcf2' height='50px'><td>公司名称</td><td>公司联系电话</td><td>联系人</td><td>联系人电话</td><td>注册时间</td><td>公司地址</td></tr>";
				var th = "";
				$(object).each(
						function() {
							th += "<tr ><th>" + this.name + "</th><th>"
									+ this.cfax + "</th><th>" + this.cperson
									+ "</th><th>" + this.cmobile + "</th><th>"
									+ this.time + "</th><th>" + this.companydz
									+ "</th></tr>"
						});

				reshtml = reshtml + th + "</table>";
				$(reshtml).appendTo("#t1");
			}

			});
}
	function f1(){
	    var taid=document.getElementById("t2");
	    t2.style.display = "none";
	}
</script>
	</body>
</html>
