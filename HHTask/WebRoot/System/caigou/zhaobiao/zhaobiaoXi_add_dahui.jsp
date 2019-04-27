<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
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
	<body   onload="getDept('dept');">
	<center>
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
				<br />
				<br />
				<form action="zhaobiaoAction!addzhaobiaoXidahuito.action" method="post"
					theme="simple">
					<table class="table" style="width:65%;" >
					
						<tr><th align="right">部门：</th>
							<td>
								<select id="dept" style="width: 100px;"
									name="deptMonthBudget.name"
									onchange="javascript:ff1(this.value);"></select>
							</td></tr>
						<tr><th align="right">预算月份：</th><td>
								<select id="yuefen" style="width: 100px;" onchange="getKemu(this.value);" ></select>
									</td></tr>
						<tr><th align="right">预算科目：</th><td>
								<select id="kemu"  name="zhaobiaoXi.t9" style="width: 100px;" ></select>
								<tr><th align="right">物料名称</th><td>
								<input type="text" id="zhaobiaoXi.t6" name="zhaobiaoXi.t6"  >
						
						
							</td></tr>
								
							</td></tr>
						<tr><th align="right">模板：</th><td>
							
									<select id="t9" style="width: 100px;" name="zhaobiaoXi.t1"></select>
								</td></tr>
						<tr><th align="right">数量：</th><td>
						
									<input type="text" name="zhaobiaoXi.t2" id="t2"
									style="width: 80px;"  "/>
								</td></tr>
						<tr><th align="right">单位：</th><td>
								<SELECT name="zhaobiaoXi.t3" style="width: 100px;">
									<option value="件">件</option>
									<option value="包">包</option>
									<option value="瓶">瓶</option>
									<option value="本">本</option>
									<option value="捆">捆</option>
									<option value="斤">斤</option>
									<option value="箱">箱</option>
									<option value="桶">桶</option>
									<option value="套">套</option>
									<option value="只">只</option>
									<option value="支">支</option>
									<option value="kg">kg</option>
									<option value="张">张</option>
									<option value="部">	部</option>
									<option value="条">条</option>
									<option value="副">副</option>
									<option value="根">根</option>
									<option value="个">个</option>
									<option value="双">
										双
									</option>
									<option value="对">
										对
									</option>
									<option value="卷">
										卷
									</option>
									<option value="米">
										米
									</option>
									<option value="尺">
										尺
									</option>
									<option value="平方米">
										平方米
									</option>
									<option value="立方米">
										立方米
									</option>
									<option value="公升">
										公升
									</option>
									<option value="把">
										把
									</option>
									<option value="打">
										打
									</option>
									<option value="吨">
										吨
									</option>
									<option value="盒">
										盒
									</option>
									<option value="块">
										块
									</option>
									<option value="台">
										台
									</option>
									<option value="升">
										升
									</option>
								</SELECT>
						
						
							</td></tr>
							
							
							<tr><th align="right">交付时间：</th><td>
							<input class="Wdate" type="text" id="zhaobiaoXi.t8"
									name="zhaobiaoXi.t8"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td></tr>
						<tr><th align="right">规格要求：	<input type="hidden" id="zhaobiao.id" name="zhaobiao.id" value="${zhaobiao.id}"  ></th><td>
							<input type="text" id="zhaobiaoXi.t5" name="zhaobiaoXi.t5"  style="width: 250px; height: 50px" >
							</td></tr>
						
							<tr><td  colspan="2" align="center"><input type="submit" value="保存" class="input" />
							<input type="button" name="Submit2" value="返回"  class="input"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td></tr>
					</table>
					
					
					</form>
				</center>
		
	
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	})
			function getDept(id){
				$.ajax( {
					url : 'zhaobiaoAction!findAllDept1.action',
					dataType : 'json',
					cache : false,//防止数据缓存
					success : function(allDdept) {
						$("#dept").empty();
						$("<option></option>").appendTo("#dept");
						$(allDdept).each(
								function() {
									$(
											"<option value='" + this + "'>"
													+ this + "</option>").appendTo(
											"#dept");
								});
					}
				});
				//------------------------------------------------------
				$.ajax( {
					url : 'zhaobiaoAction!listzhmoban.action',
					dataType : 'json',
					cache : false,//防止数据缓存
					success : function(allDdept) {
						$("#t9").empty();
						$("<option></option>").appendTo("#t9");
						$(allDdept).each(
								function() {
									$(
											"<option value='" + this.id + "'>"
													+ this.name + "</option>").appendTo(
											"#t9");
								});
					}
				});
				
				
				
		}
	
	
	
		
			function ff1(deptValue){
		         $.ajax( {
					type : "POST",
					url : "zhaobiaoAction!listDept.action",
					data:{pageStatus:deptValue},
					dataType : "json",
					success : function(object) {
						$("#yuefen").empty();
						$("<option></option>").appendTo("#yuefen");
						$(object).each(
								function() {
									$(
											"<option value='" + this + "'>"
													+ this + "</option>").appendTo(
											"#yuefen");
								});
					}
				});
		}
		function getKemu(yuefen){
		   var dept=document.getElementById("dept").value;
		     $.ajax( {
					type : "POST",
					url : "zhaobiaoAction!listMoth.action",
					data:{pageStatus:yuefen,pagename:dept},
					dataType : "json",
					success : function(obj) {
						$("#kemu").empty();
						$("<option></option>").appendTo("#kemu");
							$(obj).each(
									function() {
										$(
												"<option value='" + this.id + "'>"
														+ this.name+"("+this.accountMoney+")"+ "</option>").appendTo(
												"#kemu");
									});
					}
				});
		}
		
		
</script>
	</body>
</html>
