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
			style="filter: Alpha(Opacity =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
		</div>


		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">


			<div align="center">
				<div align="center">
				<font style="font-size: 22px;font-weight: bolder;">
					设备管理</font>
<%--				${sessionScope.Users}--%>
					<s:if test="tag==null || tag!='pingmu'">
						<form action="EquipmentAction!findAlll.action" method="post"
							style="margin: 0px;">
							<input type="hidden" name="pageStatus" value="findByCon" />
							<table class="table">
								<tr>
									<th colspan="6">
										报修查询管理
									</th>
								</tr>
								<tr>
									<td align="right">
										工区：
									</td>
									<td>
										<input type="text" style="width: 150px;"
											name="maintenance.workArea">
									</td>
									<td align="right">
										报修人：
									</td>
									<td>
										<input type="text" style="width: 150px;"
											name="maintenance.alermMan">
									</td>
									<td align="right">
										修理人:
									</td>
									<td>
										<input type="text" style="width: 150px;"
											name="maintenance.repairMan">
									</td>
								</tr>
								<tr>

									<td align="right">
										所在班组: &nbsp;&nbsp;
									</td>
									<td>
										<select id="selectDept" style="width: 155px;"
											name="maintenance.classGroup">
											<option></option>
										</select>

									</td>
									<td align="right">
										状态:
									</td>
									<td>
										<select style="width: 150px;" name="maintenance.status">
											<option></option>
											<option value="故障">
												故障
											</option>
											<option value="故障指派">
												故障指派
											</option>
											<option value="维修中">
												维修中
											</option>
											<option value="修复待验证">
												修复待验证
											</option>
											<option value="正常">
												正常
											</option>

										</select>
									</td>
									<td align="right">
										维修编号:
									</td>
									<td>
										<input type="text" style="width: 150px;"
											name="maintenance.barcode">
									</td>
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
								</tr>
								<tr align="center">
									<td colspan="8">
										<input type="submit" value="查询"
											style="width: 100px; height: 60px">
										<input type="button" value="导出Excel"
											style="width: 100px; height: 60px;"
											onclick="exportExcel(this.form);todisabledone(this)" data="downData" />
										<a
											href="EquipmentAction!findAlll.action?maintenance.name=&pageStatus=findallMaintenance">查询所有</a>
									</td>
								</tr>
							</table>
						</form>
					</s:if>
					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">
						<tr bgcolor="#c0dcf2" height="50px">

							<th align="center">
								编号
							</th>
							<th align="center">
								工区
							</th>
							<th align="center">
								工位
							</th>
							<th align="center">
								设备编码
							</th>
							<th align="center">
								设备类型
							</th>
							<th align="center">
								设备名称
							</th>

							<th align="center">
								报修时间
							</th>
							<th align="center">
								所在班组
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								修理反馈
							</th>
							<th align="center">
								修理人
							</th>
							<th align="center">
								报修人
							</th>
							<th align="center">
								修复时间
							</th>
							<th align="center" width="100px">
								确认时间
							</th>
							<th align="center">
								维修时长（小时）
							</th>
							<s:if test="tag==null || tag!='pingmu'">
								<th align="center">
									操作
								</th>
							</s:if>
						</tr>

						<s:if test="pageStatus!='findByCon'">
							<tr bgcolor="black">
								<th colspan="20" align="center">
									<font color="#ffffff">故障指派信息</font>
								</th>
							</tr>
						</s:if>
						<s:iterator value="repairList3" id="pageMaintenance"
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
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>

							<td>
								${pageMaintenance.workArea}
							</td>
							<td>
								${pageMaintenance.workPosition}
							</td>
							<td>
								${pageMaintenance.no}

							</td>

							<td>
								${pageMaintenance.type}
							</td>
							<td>
								${pageMaintenance.name}
							</td>
							<td>
								<fmt:formatDate value="${pageMaintenance.alarmTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								${pageMaintenance.classGroup}
							</td>
							<td>
								${pageMaintenance.status}
							</td>
							<td>
								${pageMaintenance.repairDetail}
							</td>
							<td>
								${pageMaintenance.repairMan}
							</td>
							<td>
								${pageMaintenance.alermMan}
							</td>
							<td>
								${pageMaintenance.timetorepair}
							</td>
							<td>
								${pageMaintenance.countdowntime}

							</td>
							<td>
								${pageMaintenance.updateTime}

							</td>
							<s:if test="tag==null || tag!='pingmu'">
								<td>
									<a
										href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>
	
								</td>
							</s:if>
							<s:if test="#pageStatus.last">

							</s:if>
						</s:iterator>
						<s:if test="pageStatus!='findByCon'">
							<tr bgcolor="red">
								<th colspan="20" align="center">
									<font color="#ffffff">故障信息</font>
								</th>
							</tr>
						</s:if>
						<s:iterator value="repairList1" id="pageMaintenance"
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
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>

							<td>
								${pageMaintenance.workArea}
							</td>
							<td>
								${pageMaintenance.workPosition}
							</td>
							<td>
								${pageMaintenance.no}

							</td>

							<td>
								${pageMaintenance.type}
							</td>
							<td>
								${pageMaintenance.name}
							</td>
							<td>
								<fmt:formatDate value="${pageMaintenance.alarmTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								${pageMaintenance.classGroup}
							</td>
							<td>
								${pageMaintenance.status}
							</td>
							<td>
								${pageMaintenance.repairDetail}
							</td>
							<td>
								${pageMaintenance.repairMan}
							</td>
							<td>
								${pageMaintenance.alermMan}
							</td>
							<td>
								${pageMaintenance.timetorepair}
							</td>
							<td>
								${pageMaintenance.countdowntime}

							</td>
							<td>
								${pageMaintenance.updateTime}

							</td>
							<s:if test="tag==null || tag!='pingmu'">
							<td>

								<a
									href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>

							</td>
							</s:if>
							<s:if test="#pageStatus.last">
								</tr>
							</s:if>
						</s:iterator>
						<s:if test="pageStatus!='findByCon'">
							<tr bgcolor="#0000C6">
								<th colspan="20" align="center">
									<font color="#ffffff">维修中信息</font>
								</th>
							</tr>
						</s:if>
						<s:iterator value="repairList2" id="pageMaintenance"
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
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>

							<td>
								${pageMaintenance.workArea}
							</td>
							<td>
								${pageMaintenance.workPosition}
							</td>
							<td>
								${pageMaintenance.no}

							</td>

							<td>
								${pageMaintenance.type}
							</td>
							<td>
								${pageMaintenance.name}
							</td>
							<td>
								<fmt:formatDate value="${pageMaintenance.alarmTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								${pageMaintenance.classGroup}
							</td>
							<td>
								${pageMaintenance.status}
							</td>
							<td>
								${pageMaintenance.repairDetail}
							</td>
							<td>
								${pageMaintenance.repairMan}
							</td>
							<td>
								${pageMaintenance.alermMan}
							</td>
							<td>
								${pageMaintenance.timetorepair}
							</td>
							<td>
								${pageMaintenance.countdowntime}

							</td>
							<td>
								${pageMaintenance.updateTime}

							</td>
							<s:if test="tag==null || tag!='pingmu'">
							<td>


								<a
									href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>

							</td>
							</s:if>
							<s:if test="#pageStatus.last">
								</tr>
							</s:if>

						</s:iterator>
						<s:if test="pageStatus!='findByCon'">
							<tr bgcolor="#336666">
								<th colspan="20" align="center">
									<font color="#ffffff">修复待验证信息</font>
								</th>
							</tr>
						</s:if>
						<s:iterator value="repairList4" id="pageMaintenance"
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
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>

							<td>
								${pageMaintenance.workArea}
							</td>
							<td>
								${pageMaintenance.workPosition}
							</td>
							<td>
								${pageMaintenance.no}

							</td>

							<td>
								${pageMaintenance.type}
							</td>
							<td>
								${pageMaintenance.name}
							</td>
							<td>
								<fmt:formatDate value="${pageMaintenance.alarmTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								${pageMaintenance.classGroup}
							</td>
							<td>
								${pageMaintenance.status}
							</td>
							<td>
								${pageMaintenance.repairDetail}
							</td>
							<td>
								${pageMaintenance.repairMan}
							</td>
							<td>
								${pageMaintenance.alermMan}
							</td>
							<td>
								${pageMaintenance.timetorepair}
							</td>
							<td style="color: red; font-size: 18px; font-weight: bolder;">
								<%--
								${pageMaintenance.countdowntime}分钟
							--%>
							</td>
							<td>
								${pageMaintenance.updateTime}

							</td>
							<s:if test="tag==null || tag!='pingmu'">
							<td>
								<a
									href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>

							</td>
							</s:if>
							<s:if test="#pageStatus.last">
								</tr>
							</s:if>
						</s:iterator>
						<tr bgcolor="green">
							<th colspan="20" align="center">
								<s:if test="pageStatus=='findByCon'">
									<font color="#ffffff">查询信息</font>
								</s:if>
								<s:else>
									<font color="#ffffff">正常信息</font>
								</s:else>

							</th>
						</tr>
						<s:iterator value="repairList" id="pageMaintenance"
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
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>

							<td>
								${pageMaintenance.workArea}
							</td>
							<td>
								${pageMaintenance.workPosition}
							</td>
							<td>
								${pageMaintenance.no}

							</td>

							<td>
								${pageMaintenance.type}
							</td>
							<td>
								${pageMaintenance.name}
							</td>
							<td>
								<fmt:formatDate value="${pageMaintenance.alarmTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								${pageMaintenance.classGroup}
							</td>
							<td>
								${pageMaintenance.status}
							</td>
							<td>
								${pageMaintenance.repairDetail}
							</td>
							<td>
								${pageMaintenance.repairMan}
							</td>
							<td>
								${pageMaintenance.alermMan}
							</td>
							<td>
								${pageMaintenance.timetorepair}
							</td>
							<td>
								${pageMaintenance.persontime}

							</td>
							<td>
								${pageMaintenance.updateTime}

							</td>
							<s:if test="tag==null || tag!='pingmu'">
							<td>


								<a
									href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>

							</td>
							</s:if>
							<s:if test="#pageStatus.last">

							</s:if>
						</s:iterator>
						<tr>
							<td colspan="20" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
					<br>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
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
						objform.action="EquipmentAction!macrepairexcel.action";
						objform.submit();
						objform.action="EquipmentAction!findAlll.action?maintenance.name=&pageStatus=findallMaintenance";
			}
			</SCRIPT>
		</div>
	</body>
</html>
