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
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在付款申请进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
			</div>

			<div align="center">
					<h3>
						用车管理
					</h3>
				<form action="AskForLeaveAction!tobackCarList.action"	 method="post">
				<input type="hidden" name="pageStatus" value="${pageStatus}">
				<table class="table">
					<tr>
						<td align="center">
						用车部门：
							<select name="singleCar.var_dept"  id="dept">
									<option value="">--请选择部门--</option>
								</select>
						</td>
						<td align="center">
							用车人：
							<input type="text" name="singleCar.car_usename">
						</td>
					</tr>
					<tr>
						<td  colspan="2" align="center">
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询" class="input" />
					</tr>
				</table>

				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							用车单位
						</td>
						<td align="center">
							车号
						</td>
							<td align="center">
							用车事由
						</td>
							<td align="center">
							到达地点
						</td>
						<td align="center">
							用车人
						</td>
						<td align="center">
							出车类型
						</td>
							<td align="center">
							审批状态
						</td>
						<td align="center">
							出车前里程
						</td>
						<td align="center">
							用车时间
						</td>
						<td align="center">
							处理状态
						</td>
						<td align="center">
							使用状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="singleCarList" id="pageList" status="pageStatus">
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
							${pageList.var_dept}
						</td>
						<td>
							${pageList.car_number}
						</td>
						<td>
							${pageList.car_content}
						</td>
						<td>
							${pageList.car_place}
						</td>
						<td>
						 	${pageList.car_usename}
						</td>
						<td>
						 	${pageList.singlecarType}
						</td>
						<td>
						 	${pageList.status}
						</td>
						<td>
						 	${pageList.beforeodometer}公里
						</td>
						<td>
							${pageList.car_date}
						</td>
						<td>
							${pageList.processingstatus}
						</td>
						<td>
							${pageList.useStatus}
						</td>
						<td>
						<s:if test="#pageList.status=='同意'&&#pageList.useStatus=='使用中'">
						<input type="button" value="归还" onclick="backCar(${pageList.id})"/>
						</s:if>
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>

				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript" src="javascript/DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript">
		$(function() {
					createDept('dept');
				})
		function backCar(id){
			window.location.href="AskForLeaveAction!tobackCar.action?id="+id;
		}
</script>
	</body>
</html>
