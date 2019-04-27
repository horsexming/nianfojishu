<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<div id="bodyDiv" align="center"
			style="filter: Alpha(Opacity =                                                                                                                                                                                                                                 75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
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
					<s:if test="pageStatus==null">
						<form action="EquipmentChangesAction!findAll.action" method="post"
							style="margin: 0px;">
							<table width="100%" border="1">
								<tr>
									<th colspan="8">
										变动设备管理
									</th>
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

						<s:if test="pageStatus==null">
							<tr bgcolor="green">
								<th colspan="12" align="center">
									<font color="#ffffff">审批信息</font>
								</th>
							</tr>
							<s:iterator value="equipmentChangesList1"
								id="pageEquipmentChanges" status="pageStatus">
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

								<td>
									<a
										href="EquipmentChangesAction!initupmaintenance.action?id=${pageEquipmentChanges.id}">修改</a>/
									<a
										href="EquipmentChangesAction!delSubmit.action?id=${pageEquipmentChanges.id}"
										onClick="return window.confirm('确认要删除选中的信息吗？')">删除</a>/

									<a
										href="EquipmentChangesAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="12" align="center">
									<font color="#ffffff">打回信息</font>
								</th>
							</tr>
							<s:iterator value="equipmentChangesList2"
								id="pageEquipmentChanges" status="pageStatus">
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
								<td>

									<a
										href="EquipmentChangesAction!initupmaintenance.action?id=${pageEquipmentChanges.id}">修改</a>/
									<a
										href="EquipmentChangesAction!delSubmit.action?id=${pageEquipmentChanges.id}"
										onClick="return window.confirm('确认要删除选中的信息吗？')">删除</a>/
									<a
										href="EquipmentChangesAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="12" align="center">
									<font color="#ffffff">批准信息</font>
								</th>
							</tr>
							<s:iterator value="equipmentChangesList3"
								id="pageEquipmentChanges" status="pageStatus">
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
								<td>
									<a
										href="EquipmentChangesAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="12" align="center">
									<font color="#ffffff">同意信息</font>
								</th>
							</tr>
							<s:iterator value="equipmentChangesList4"
								id="pageEquipmentChanges" status="pageStatus">
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
								<td>
									<a
										href="EquipmentChangesAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="12" align="center">
									<font color="#ffffff">完成信息</font>
								</th>
							</tr>
							<s:iterator value="equipmentChangesList"
								id="pageEquipmentChanges" status="pageStatus">
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
								<td>
									<a
										href="EquipmentChangesAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<s:iterator value="equipmentChangesList"
								id="pageEquipmentChanges" status="pageStatus">
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
								<td>
									<s:if test='#pageEquipmentChanges.status=="同意"'>
										<a
											href="EquipmentChangesAction!updateSubmit.action?id=${pageEquipmentChanges.id}&pageStatus=${pageStatus}"
											onClick="return window.confirm('确认完成吗？')">完成</a>
									</s:if>
									<s:else>

										<a
											href="EquipmentChangesAction!updateSubmit.action?id=${pageEquipmentChanges.id}&pageStatus=${pageStatus}"
											onClick="return window.confirm('确认通过吗？')">通过</a>/
									<a
											href="EquipmentChangesAction!updateSubmit.action?id=${pageEquipmentChanges.id}&pageStatus=back${pageStatus}"
											onClick="return window.confirm('你确定要打回？')">打回</a>
									</s:else>
									/
									<a
										href="EquipmentChangesAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>

							</s:iterator>
						</s:else>
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
								<td colspan="12" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
					<br>
				</div>
				<%@include file="/util/foot.jsp"%>
				</center>
				<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
