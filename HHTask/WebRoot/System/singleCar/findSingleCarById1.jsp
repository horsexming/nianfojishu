<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<center>
					<div id="printDiv">
						<h5>
							${companyInfo.name}
						</h5>
						<table align="center" class="table" id="complexselectedlist">
							<tr>
								<th colspan="4">
									申请用车单
								</th>
							</tr>
							<tr>
								<th style="width: 30%;">
									用车部门
								</th>
								<th align="left">
									${singleCar.var_dept}
								</th>
								<th style="width: 30%;">
									用车时间
								</th>
								<th align="left">
									${singleCar.car_date}
								</th>
							</tr>
							<tr>
								<th style="width: 30%;">
									用车事由
								</th>
								<th align="left">
									${singleCar.car_content}
								</th>
								<th style="width: 30%;">
									人数及吨位
								</th>
								<th align="left">
									${singleCar.car_amount}
								</th>
							</tr>
							<tr>
								<th>
									到达地点
								</th>
								<th colspan="3" align="left">
									${singleCar.car_place}
								</th>
							</tr>
							<tr>
								<th style="width: 30%;">
									收费标准
								</th>
								<th align="left">
									${singleCar.charges}
								</th>
								<th style="width: 30%;">
									进出库时间
								</th>
								<th align="left">
									${singleCar.comeoroutdate}
								</th>
							</tr>
							<tr>
								<th style="width: 30%;">
									出车前里程表
								</th>
								<th align="left">
									${singleCar.beforeodometer}
								</th>
								<th style="width: 30%;">
									回车后里程表
								</th>
								<th align="left">
									${singleCar.endodometer}
								</th>
							</tr>

							<tr>
								<th style="width: 30%;">
									公里数
								</th>
								<th align="left">
									${singleCar.kilometers}
								</th>
								<th style="width: 30%;">
									驾驶员姓名
								</th>
								<th align="left">
									${singleCar.pilotname}
								</th>
							</tr>
							<tr>
								<th style="width: 30%;">
									车号
								</th>
								<th align="left">
									${singleCar.car_number}
								</th>
								<th style="width: 30%;">
									用车单位领导
								</th>
								<th align="left">
									${singleCar.unit_leading}
								</th>
							</tr>
							<tr>
							<th>
							早出晚归
							</th>
							<th align="left">
								 	${singleCar.zcwg}
							</th>
							
						
							<th>
							周末及厂休
							</th>
							<th align="left">
								 	${singleCar.zmcx}
							</th>
							</tr>
						<tr>
							<tr>
							
								<th>
									出车类型
								</th>
								<th align="left">
									${singleCar.singlecarType}
								</th>
								<th>
									备注
								</th>
								<th align="left">
									${singleCar.remark}
								</th>
							</tr>
							<tr>
								<th>
									带车人
								</th>
								<th align="left" colspan="3">
									${singleCar.car_usename}
								</th>
<%--								<th>--%>
<%--									派车人--%>
<%--								</th>--%>
<%--								<th align="left">--%>
<%--									<span id="aaa"></span> &nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									<span id="bbb"></span>&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									<span id="ccc"></span>--%>
<%--								</th>--%>
							</tr>
							<tr>
									<th>
									已审批人
									</th>
									<th colspan="3" align="left">
									<span id="aaa"></span> &nbsp;&nbsp;&nbsp;&nbsp;
									<span id="bbb"></span>&nbsp;&nbsp;&nbsp;&nbsp;
									<span id="ccc"></span>
									</th>
							</tr>
						</table>
					</div>
					<table class="table" style="width: 100%">
						<tr>
							<td align="center">
								<input type="button" style="width: 60px; font-size: 18px;"
									value="返回" onclick="javascript:history.go(-1);">
								<%--								<s:if test="'同意'==singleCar.status">--%>
								<%--									<input style="width: 80px; font-size: 18px;"--%>
								<%--										onclick="pagePrint('printDiv','sy')" type="button" value="打印">--%>
								<%--								</s:if>--%>
								<%--								<s:else>--%>
								<%--									<span style="font-size: 20; color: red; font-weight: bold">等待审核人通过后，方可打印借款！</span>--%>
								<%--								</s:else>--%>
							</td>
						</tr>
					</table>
				</center>
			</div>
			<br>
			<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$(function(){
			var id = ${singleCar.id};
			$.ajax( {
				type : "POST",
				url : "singleCarAction_findExecutionNode.action",
				data : {
					id : id
				},
				dataType : "json",
				success : function(data) {
					$("#aaa").append("<img src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"' height='50px' width='120px' align='middle'></img>");
					$("#bbb").append(data.data1[0].auditDateTime);
					$("#ccc").append("<img src='<%=request.getContextPath()%>"+data.data2[1].signature_address+"' height='50px' width='120px' align='middle'></img>");
				}
			});
	})
		
</script>

</html>
