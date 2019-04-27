<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<STYLE type="text/css">
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}

/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div>
			<div align="center" style="border: 1px solid #00000;">
				<div id="rootTemplateDiv">
					<div>
						<div align="center">
							<h3>
								<font color="red">${successMessage}</font>
								<font color="red">${errorMessage}</font>
							</h3>
						</div>
						<form action="ProcardAction!findAllProcards.action" method="post"
							id="form">
							<input type="hidden" name="viewStatus" value="${viewStatus}"
								id="viewStatus" />
							<input type="hidden" name="pageStatus" value="${pageStatus}"
								id="pageStatus" />
							<input type="hidden" name="operation" value="${operation}"
								id="operation" />
							<table class="table">
								<tr>
									<th align="right">
										订单编号:
										<br />
										(内部):
									</th>
									<td>
										<input name="procard.orderNumber"
											value="${procarddc.orderNumber}" />
									</td>
									<th align="right">
										件号:
										<br />
										Part No. :
									</th>
									<td>
										<input name="procard.markId" value="${procarddc.markId}" />
									</td>
									<th align="right">
										业务件号:
										<br />
										Part No. :
									</th>
									<td>
										<input name="procard.ywMarkId" value="${procarddc.ywMarkId}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
										<br />
										Card Type :
									</th>
									<td>
										<select id="procardStyle" name="procarddc.procardStyle"
											style="width: 155px;">
											<option>
												${procard.procardStyle}
											</option>
											<option></option>
											<option>
												总成
											</option>
											<option>
												外购
											</option>
											<option>
												自制
											</option>
										</select>
									</td>
									<th align="right">
										产品类型:
										<br />
										Product Type :
									</th>
									<td>
										<select name="procard.productStyle" style="width: 155px;">
											<option>
												${procarddc.productStyle}
											</option>
											<option></option>
											<option>
												试制
											</option>
											<option>
												批产
											</option>
										</select>
									</td>
									<th align="right">
										状态:
										<br />
										State :
									</th>
									<td>
										<select name="procard.status" style="width: 155px"
											id="procard_status">
											<!--											<option>-->
											<!--												${procard.status}-->
											<!--											</option>-->
											<option></option>
											<!--											<s:if test="pageStatus != 'ruku'">-->
											<!--												<option>-->
											<!--													初始-->
											<!--												</option>-->
											<!--												<option>-->
											<!--													已发卡-->
											<!--												</option>-->
											<!--												<option>-->
											<!--													已发料-->
											<!--												</option>-->
											<!--												<option>-->
											<!--													领工序-->
											<!--												</option>-->
											<!--											</s:if>-->
											<!--											<option>-->
											<!--												完成-->
											<!--											</option>-->
											<!--											<option>-->
											<!--												入库-->
											<!--											</option>-->
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
										<br />
										Name :
									</th>
									<td>
										<input name="procard.proName" value="${procarddc.proName}" />
									</td>
									<th align="right">
										版本:
									</th>
									<td>
										<input name="procard.banBenNumber"
											value="${procarddc.banBenNumber}" />
									</td>
									<th align="right">
										供料属性:
									</th>
									<td>
										<select name="procard.kgliao" id="kgliao2"
											style="width: 155px;">
											<option>
												${procarddc.kgliao}
											</option>
											<option></option>
											<option value="TK">
												自购(TK)
											</option>
											<option value="TK AVL">
												指定供应商(TK AVL)
											</option>
											<option value="CS">
												客供(CS)
											</option>
											<option value="TK Price">
												完全指定(TK Price)
											</option>
										</select>
									</td>
								</tr>
								<%--<tr>
									<th align="right">
										计划单号:
										<br />
										Single number plan :
									</th>
									<td>
										<input name="procard.planOrderNum"
											value="${procard.planOrderNum}" />
									</td>
									<th align="right">
										批次:
										<br />
										Batch :
									</th>
									<td>
										<input name="procard.selfCard" value="${procard.selfCard}" />
									</td>
								</tr>
								--%>
								<tr>
									<th align="right">
										批次:
										<br />
										Batch :
									</th>
									<td>
										<input name="procard.selfCard" value="${procarddc.selfCard}" />
									</td>
									<th align="right">
										起始时间:
										<br />
										start time :
									</th>
									<td>
										<input type="text" class="Wdate" name="startDate"
											value="${startDate}" id="startDate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
									</td>
									<th align="right">
										结束时间:
										<br />
										end time :
									</th>
									<td>
										<input type="text" class="Wdate" name="endDate"
											value="${endDate}" id="endDate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
									</td>
								</tr>
								<tr>
									<th colspan="6">
										<input type="hidden" value="${tag}" name="tag" />
										<input type="reset" value="清空" class="input" />
										<input type="button" value="订单周期导出" class="input" style="width: 100px;"
											onclick="getscjdExcel();todisabledone(this)" data="downData" />
									</th>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<SCRIPT type="text/javascript">
			$(function() {
			 	var pageStatus=document.getElementById("pageStatus").value;
			 	var status = null;
			 	$("#processName").empty();
				if(pageStatus!='ruku'){
					status=new Array("初始","已发卡","已发料","领工序","设变锁定","完成","入库");
				}else{
					status=new Array("完成","入库");
				}
				for(var i=0;i<status.length;i++){
 						$("<option value='" + status[i] + "'>"
										+ status[i] + "</option>")
								.appendTo("#procard_status");
					}
				duoxuaSelect("procard_status",'${procard.status}');	
			});
		function getExcel(){
		//var startDate=document.getElementById("startDate").value;
		 var procardStyle=document.getElementById("procardStyle").value;
		 if(procardStyle==""||procardStyle==null){
			 document.getElementById("procardStyle").focus();
			 alert("卡片类型不能为空");
			 return false;
		 }
		 var form=document.getElementById("form");
		 form.action="ProcardAction!geExcel.action?cpage=${cpage}";
		 form.submit();
		 form.action="ProcardAction!findAllProcards.action";
		}
		function getscjdExcel(){
			var form=document.getElementById("form");
		 form.action="ProcardAction!getscjdExcel.action";
		 form.submit();
		}
		//导出单个总成bom
		function getExcel_1(id){
		 var form=document.getElementById("form");
		 form.action="ProcardAction!geExcel_1.action?cpage=${cpage}&id="+id;
		 form.submit();
		 form.action="ProcardAction!findAllProcards.action";
		}
		function getExcel_2(id){
		 var form=document.getElementById("form");
		 form.action="ProcardAction!geExcel_2.action";
		 form.submit();
		 form.action="ProcardAction!findAllProcards.action";
		}
		function uploadPic(id){
		 form.action="procardTemplateGyAction_findPICforProduct.action?cpage=${cpage}&procard.id="+id;
		 form.submit();
		 form.action="ProcardAction!findAllProcards.action";
		}
		function blquery(){
		 	$("#pageStatus").val('buliao');
		 	 var form=document.getElementById("form");
			 form.action="ProcardAction!findAllProcards.action";
		 	 form.submit();
		}
		function allquery(){
		 	 var form=document.getElementById("form");
			 form.action="ProcardAction!findAllProcards.action";
		 	 form.submit();
		}
		//导出单个总成  生产零件
		function exportProcard(procardId){
			 var form=document.getElementById("form");
			 form.action="ProcardAction!exportProcard.action?cpage=${cpage}&id="+procardId;
			 form.submit();
			 form.action="ProcardAction!findAllProcards.action";
		}
//前往超损补料页面
function tocsbl(id){
	window.location.href = 'ProcardAction!tocsbl.action?id='+id;
}
function check(){
	var bool = false;
	var processIds = document.getElementsByName("processIds");
	if(processIds!=null && processIds.length>0){
		for(var i=0;i<processIds.length;i++){
			if(processIds[i].checked){
				bool = true;
				break;
			}
		}
	}
	if(!bool){
		alert("请至少选择一条数据~");
		$("#sub").removeAttr("disabled")
	}
	return bool;
}
		</SCRIPT>
	</body>
</html>
