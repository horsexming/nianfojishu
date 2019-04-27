<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<body onload="createDept('selectDept')">
		<div id="bodyDiv" align="center"
			style="filter: Alpha(Opacity = 75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>

				</div>
			</div>
			
			
			<div align="center">

				<div align="center">
					<form action="EquipmentAction!findas.action" method="post"
						style="margin: 0px;">
						<table align="center">
							<tr>
								<th colspan="8" style="color: red;">
									设备完好率
								</th>
							</tr>
							<tr>
					<!--  	<td align="right">
									状态：
								</td>
								<td>
									<select style="width: 150px;" name="status">
										<option></option>
										<option value="故障">
											故障
										</option>
										<option value="维修中">
											维修中
										</option>
										<option value="正常">
											正常
										</option>
									</select>
								</td> -->	 
								<td align="right">
									起始月份：
								</td>
								<td>
									<input class="Wdate" name="month1"
										onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />

								</td>
								<td align="right">
									结束月份：
								</td>
								<td>
									<input class="Wdate" name="month2"
										onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />

								</td>

								<td>

									<input type="submit" value="查询"
										style="width: 60px; height: 40px">

								</td>
							</tr>
						</table>
					</form>

					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">
						<tr bgcolor="#c0dcf2" height="30px">
							<th align="center">
								编号
							</th>
							<th align="center">
								月份
							</th>
							<!--  <th align="center">
							      月设备维修总时间/(小时)
							</th>
							<th align="center">
								月设备正常工作总时间/(小时)
							</th>
							<th align="center">
								设备总时间/(小时)
							</th>-->
							<th align="center">
								设备完好率
							</th>

						</tr>
						<s:iterator value="econditionList" id="pageecondition"
							status="pageStatus">
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
									<font color="#000000">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>

							<td style="color: red; font-size: 20px; font-weight: bolder;">
								${pageecondition.month}
							</td>

							<!--  <td style="color: red; font-size: 20px; font-weight: bolder;">
								${pageecondition.status}
							</td>
							<td style="color: red; font-size: 20px; font-weight: bolder;">
								${pageecondition.quantity}
							</td>
							<td style="color: red; font-size: 20px; font-weight: bolder;">
								${pageecondition.total}-->
							</td>
							<td style="color: green; font-size: 20px; font-weight: bolder;">

								<fmt:formatNumber value="${pageecondition.rate}" type="percent"
									pattern="#0.00" />%
							</td>

						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
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
								</td>
							</s:else>

						</tr>
					</table>

				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		</div>
	</body>
</html>
