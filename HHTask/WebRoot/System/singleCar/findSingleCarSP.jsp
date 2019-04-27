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
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeDiv.gif" width="30"
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
					
				<form action="singleCarAction_findExamListC.action?"	 method="post">
				<table class="table">
					<tr>
					<td align="center">驾驶员：<input id="pilotname1" type="text" name="pilotname" value="${pilotname}"/></td>
					<td align="center">车牌号：<input id="car_number1" type="text" name="car_number" value="${car_number}"/></td>
					</tr>
					<tr>
					 
					 
						<td align="center">
						开始时间：
							<input class="Wdate" id="firsttime1" type="text" name="firsttime" value="${singleCarAll.firsttime}"
											onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
						</td>
						<td align="center">
						结束时间：
							<input class="Wdate" id="finishtime1" type="text" name="finishtime" value="${singleCarAll.finishtime}"
											onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td  colspan="3" align="center">
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询 " class="input" />
								
					</tr>
				</table>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
					    <td align="center">
							序号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							车号
						</td>
							<td align="center">
							合计公里
						</td>
							<td align="center">
							合计金额
						</td>
						<td align="center">
							长途公里
						</td>
						<td align="center">
							长途金额
						</td>
							<td align="center">
							早出晚归次数
						</td>
						<td align="center">
							早出晚归金额
						</td>
						<td align="center">
							周末及厂休次数
						</td>
						<td align="center">
							周末及厂休金额
						</td>
						<td align="center">
							总金额
						</td>
						<td align="center">
							领款人
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					
					<tr>
							<s:iterator value="maps" id="pageList" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="center">
									<s:if test="#pageStatus.index%2==1">
									</s:if>
									<s:else>
										<font color="#c0dcf2"></font>
									</s:else>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="center">
							${pageList.pilotname}
						</td>
						
						<td align="center">
							${pageList.car_number}
						</td>
							<td align="center">
							${pageList.hjkilometers}
						</td>
							<td align="center">
							${pageList.hjmoney}
						</td>
						<td align="center">
							${pageList.ctkilometers}
						</td>
						<td align="center">
							${pageList.ctmoney}
						</td>
							<td align="center">
							${pageList.zcwgcs}
						</td>
						<td align="center">
							${pageList.zcwgmoney}
						</td>
						<td align="center">
							${pageList.zccs}
						</td>
						<td align="center">
							${pageList.zcmoney}
						</td>
						<td align="center" >
							${pageList.allmoney}
						</td>
						<td align="center">
							${pageList.permoney}
						</td>
						<td align="center">
							${pageList.status}
						</td>
						
					    <td align="center">
					    <input type="button" value="审批动态"
								style="width: 60px; height: 30px;"
								onclick="showshenpi(${pageList.epId})" />
					   <s:if test="#pageList.status=='同意' ">
					 <a href="singleCarAction_findSingleCarAllById.action?singleCarAll.id=${pageList.id}">打印</a>
						</s:if>
						</td>
						</s:iterator>
					</tr>
					
					
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
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
						
					</tr>
				</table>
				<table>
				</table>
                 
				</form>
			</div>
			<br>
			
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function showshenpi(id) {
	window.location.href = "CircuitRunAction_findAduitPage.action?id=" + id;
}
	</script>
	</body>
	
</html>
