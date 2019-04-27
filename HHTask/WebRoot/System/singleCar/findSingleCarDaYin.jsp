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

			<div align="center" id="tongzhi">
					
				<form action="singleCarAction_findExamListC.action?"	 method="post">
				<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="center">
				
				
			<font size="5px"> ${singleCarAll.firsttime}
			—${singleCarAll.finishtime}
						驾    驶    员   车    辆   公   里   及  补   助  统   计</font>
				<input type="hidden" name="id" value="${singleCarAll.id}">
					
					</div>
				
				<table width="100%" border="0" style="border-collapse: collapse; "class="table">
					<tr  height="50px">
					    
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
					</tr>
					<tr  height="50px">
					    
						<td align="center">
							${singleCarAll.pilotname}
						</td>
						
						<td align="center">
							${singleCarAll.car_number}
						</td>
							<td align="center">
							${singleCarAll.hjkilometers}
						</td>
							<td align="center">
							${singleCarAll.hjmoney}
						</td>
						<td align="center">
							${singleCarAll.ctkilometers}
						</td>
						<td align="center">
							${singleCarAll.ctmoney}
						</td>
							<td align="center">
							${singleCarAll.zcwgcs}
						</td>
						<td align="center">
							${singleCarAll.zcwgmoney}
						</td>
						<td align="center">
							${singleCarAll.zccs}
						</td>
						<td align="center">
							${singleCarAll.zcmoney}
						</td>
						<td align="center" >
							${singleCarAll.allmoney}
						</td>
						<td align="center">
							${singleCarAll.permoney}
						</td>
					</tr>
					</table>
				</form>
				<table>
				<tr><td></td></tr>
			    <tr><td></td></tr>
			    <tr><td></td></tr>
			    <tr><td></td></tr>
			</table>
				  <s:if test="singleCarAll.status=='同意'">
				<table width="100%" border="0">
				<tr>
				  <td align="left">填表：徐海燕 </td> 
				  <td>总经办： 
         <td><img style='max-height: 45px;'
					width='120px' align="middle" height='45px'
		src='<%=request.getContextPath()%><s:property value="singleCarAll.qianmingList[0]"/>'></img>
          </td>
			
				  <td>总经理审批：
           <td><img style='max-height: 45px;'
					width='120px' align="middle" height='45px'
		src='<%=request.getContextPath()%><s:property value="singleCarAll.qianmingList[1]"/>'></td>
</tr>
				  </td>
				  <tr></tr>
				</table>
					 </s:if>
                 
			<br>
			</div>
			<div>
			<s:if test="singleCarAll.status=='同意'">
				<input type="submit" value="打印" onclick="pagePrint('tongzhi','sy')"
					style="width: 80px; height: 40px;">
					</s:if>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	
	</body>
	
</html>
