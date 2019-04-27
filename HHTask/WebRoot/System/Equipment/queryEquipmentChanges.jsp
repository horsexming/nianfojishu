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
			style="filter: Alpha(Opacity =                                                                                                                                                                                                                                                                                                                                                                                                                 75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
			</div>
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
					<form action="EquipmentChangesAction!findAAll.action" method="post"
						style="margin: 0px;">

						<table class="table">
							<tr>
								<th colspan="8">
									变动设备管理
								</th>
							</tr>
							<tr>
								<td align="right">
									设备类型：
								</td>
								<td>
									<input type="text" style="width: 150px;"
										name="equipmentChanges.devicetype">
								</td>
								<td align="right">
									设备编号：
								</td>
								<td>
									<input type="text" style="width: 150px;"
										name="equipmentChanges.devicenumber">
								</td>
								<td align="right">
									设备名称:
								</td>
								<td>
									<input type="text" style="width: 150px;"
										name="equipmentChanges.equipmentName">
								</td>
							</tr>
							<tr>

								<td align="right">
									所在班组:
								</td>
								<td>
									<select id="selectDept" style="width: 155px;"
										name="equipmentChanges.dapt">
										<option></option>
									</select>

								</td>
								<td align="right">
									状态:
								</td>
								<td>
									<select style="width: 150px;" name="equipmentChanges.status">
										<option></option>
										<option value="审批">
											审批
										</option>
										<option value="批准">
											批准
										</option>
										<option value="同意">
											同意
										</option>
										<option value="完成">
											完成
										</option>
									</select>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									起始日期：
								</td>
								<td>
									<input class="Wdate" name="date1" id="startdate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

								</td>
								<td align="right">
									结束日期：
								</td>
								<td>
									<input class="Wdate" name="date2" id="enddate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

								</td>
								<td></td>
								<td></td>
							</tr>
							<tr align="center">
								<td colspan="8" rowspan="5">
									<input type="submit" value="查询"
										style="width: 100px; height: 60px">
									<input type="button" value="导出Excel"
										style="width: 100px; height: 60px;"
										onclick="exportExcel(this.form);todisabledone(this)" data="downData" />
							</tr>

						</table>
						<table width="100%" border="0" style="border-collapse: collapse;"
							class="table">

							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									编号
								</th>
								<th align="center">
									新工区
								</th>
								<th align="center">
									新工位
								</th>
								<th align="center">
									设备编号
								</th>
								<th align="center">
									设备名称
								</th>
								<th align="center">
									申请人
								</th>
								<th align="center">
									申请时间
								</th>
								<th align="center">
									所在班组
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									旧工区
								</th>
								<th align="center">
									旧工位
								</th>
								<th align="center">
									操作
								</th>
							</tr>
							<s:iterator value="equipmenList" id="pageEquipmentChanges"
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
										<font></font>
									</s:if>
									<s:else>
										<font color="red"></font>
									</s:else>
									<s:property value="#pageStatus.index+1" />

								</td>
								<td>
									${pageEquipmentChanges.newworkarea}
								</td>
								<td>
									${pageEquipmentChanges.newstation}
								</td>

								<td>
									${pageEquipmentChanges.devicenumber}
								</td>

								<td>
									${pageEquipmentChanges.equipmentName}
								</td>
								<td>
									${pageEquipmentChanges.name}
								</td>
								<td>
									${pageEquipmentChanges.changesdate}
								</td>
								<td>
									${pageEquipmentChanges.dapt}
								</td>
								<td>
									${pageEquipmentChanges.status}
								</td>
								<td>
									${pageEquipmentChanges.oldworkarea}
								</td>
								<td>
									${pageEquipmentChanges.oldstation}

								</td>

								<td align="center">
									<a
										href="EquipmentChangesAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
							</s:iterator>
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
									</td>
								</s:else>

							</tr>

						</table>
					</form>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<SCRIPT type="text/javascript">
				function exportExcel(objform) {
				        var startdate=document.getElementById("startdate");
				        var enddate=document.getElementById("enddate");
				        if(startdate.value==""){
				          alert("请输入开始时间");
				           return false;
				        }
				        else if(enddate.value==""){
				        alert("请输入结束时间");
				        return false;
				        }
						objform.action="EquipmentChangesAction!macrepairexcel.action";
						objform.submit();
						objform.action="EquipmentChangesAction!findAll.action?equipmentChanges.name=";
			}
			</SCRIPT>
	</body>
</html>