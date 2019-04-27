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
							<table class="table">
								<tr>
									<th align="right">
										订单编号:
										<br />
										(内部):
									</th>
									<td>
										<input name="procard.orderNumber"
											value="${procard.orderNumber}" />
									</td>
									<th align="right">
										件号:
										<br />
										Part No. :
									</th>
									<td>
										<input name="procard.markId" value="${procard.markId}" />
									</td>
									<th align="right">
										业务件号:
										<br />
										Part No. :
									</th>
									<td>
										<input name="procard.ywMarkId" value="${procard.ywMarkId}" />
									</td>
								</tr>
								<tr>
									<th colspan="6">
										<input type="hidden" value="${tag}" name="tag" />
										<input type="submit" value="查询" class="input" />
									</th>
								</tr>
							</table>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<s:if test="pageStatus == 'nocaigou' ">
									<th>
										<input type="checkbox" onclick="chageAllCheck(this)" />
									</th>
								</s:if>
								<th align="center">
									序号
									<br />
									No.
								</th>
								<th align="center">
									订单编号
									<br />
									(内部)
								</th>
								<th align="center">
									件号
									<br />
									Part No.
								</th>
								<th align="center">
									业务件号
									<br />
									Part No.
								</th>
								<th align="center">
									名称
									<br />
									Name
								</th>
								<th align="center">
									版本
								</th>
								<th align="center">
									供料属性
								</th>
								<th align="center">
									卡片类型
									<br />
									Card Type
								</th>
								<th align="center">
									产品类型
									<br />
									Product Type
								</th>
								<th align="center">
									批次
									<br />
									Batch
								</th>
								<th align="center">
									计划下达时间
								</th>
								<th align="center">
									负责人
								</th>
								<th align="center">
									产品开始时间
								</th>
								<th align="center">
									入库时间
								</th>

								<th align="center">
									数量
									<br />
									(
									<font color="red">${nums}</font>)
								</th>
								<th align="center">
									待入库数
								</th>
								<th align="center">
									入库数
								</th>
								<th align="center">
									已领数量
								</th>
								<th align="center">
									状态
								</th>
								<th align="center" colspan="2">
									操作
								</th>
							</tr>
							<s:iterator value="procardList" id="pageProcard"
								status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 50px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="left">
									${pageProcard.orderNumber}
								</td>
								<td align="left">
									<a title="查看工序"
										href="ProcardAction!findProcardByRunCard.action?id=${pageProcard.id}&pageStatus=history&viewStatus=${viewStatus}">${pageProcard.markId}</a>
								</td>
								<td align="left">
									${pageProcard.ywMarkId}
								</td>
								<td align="left"
									style="max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageProcard.proName}</font>
									<ul class="qs_ul">
										<li>
											${pageProcard.proName}
										</li>
									</ul>
								</td>
								<td align="left">
									${pageProcard.banBenNumber}
								</td>
								<td align="left">
									${pageProcard.kgliao}
								</td>
								<td>
									${pageProcard.procardStyle}
								</td>
								<td>
									${pageProcard.productStyle}
								</td>
								<td>
									${pageProcard.selfCard}
								</td>
								<td align="center">
									${pageProcard.procardTime}
								</td>
								<td>
									${pageProcard.zhikaren}
								</td>
								<td>
									${pageProcard.jihuoDate}
								</td>
								<td>
									${pageProcard.needFinalDate}
								</td>
								<td>
									${pageProcard.filnalCount}
								</td>
								<td align="right">
									<s:if test="#pageProcard.procardStyle=='总成'">
										<s:if test="#pageProcard.rukuCount==null">0</s:if>
										<s:elseif test="#pageProcard.hasRuku!=null">${pageProcard.rukuCount-pageProcard.hasRuku}</s:elseif>
										<s:else>
									${pageProcard.rukuCount} 
									</s:else>
									</s:if>
								</td>
								<td align="right">
									<s:if test="#pageProcard.procardStyle=='总成'">
										<s:if test="#pageProcard.hasRuku==null">0</s:if>
										<s:else>${pageProcard.hasRuku}</s:else>
									</s:if>
								</td>
								<td align="right">
									<s:if
										test="#pageProcard.hascount==null || #pageProcard.klNumber==0">
									0
									</s:if>
									<s:else>
									${pageProcard.klNumber-pageProcard.hascount}
									</s:else>
								</td>
								<td>
									${pageProcard.status}
								</td>
								<td colspan="2" align="left">
									<a href="<%=basePath %>/System/oderCancel/Procard_zzps.jsp?id=${pageProcard.id}">自制件评审</a>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="20" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="18" align="center" style="color: red">
								</s:else>
								</td>
							</tr>
						</table>
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
