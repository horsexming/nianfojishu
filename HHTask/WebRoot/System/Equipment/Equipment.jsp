<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			
			
			<div align="center">

				<div align="center">
					<s:if test="pageStatus=='guzhang'">
						<form action="EquipmentAction!findAllber.action" method="post"
							style="margin: 0px;">
							<table width="100%">
								<tr>
									<td style="font-size: 18px; color: black;" align="right">
										请扫描维修条码：
									</td>
									<td>
										<input id="barcodeID" type="text" name="maintenance.barcode"
											style="font-size: 20PX" onblur="javascript:this.select();"
											value="${maintenance.barcode}" />

									</td>
								</tr>


							</table>
						</form>

					</s:if>
					<s:if test="pageStatus==null">
						<form action="EquipmentAction!findAlll.action" method="post"
							style="margin: 0px;">
							<table width="100%">
								<tr>
									<th colspan="6">
										报修查询管理
									</th>
								</tr>


							</table>
						</form>
					</s:if>
					
					<table class="table">
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
							<th align="center">
								确认时间
							</th>
							<th align="center" width="100px">
								操作
							</th>
						</tr>

						<s:if test="pageStatus==null">
							<tr bgcolor="red">
								<th colspan="15" align="center">
									<font color="#ffffff">故障信息</font>
								</th>
							</tr>
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
								<td height="50px">

									<a
										href="EquipmentAction!initupmaintenance.action?id=${pageMaintenance.id}">修改</a>/
									<a
										href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>/
									<a
										href="EquipmentAction!delSubmitt.action?id=${pageMaintenance.id}"
										onClick="return window.confirm('确认要删除吗？')">删除</a>
								</td>
								<s:if test="#pageStatus.last">

								</s:if>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="15" align="center">
									<font color="#ffffff">故障指派信息</font>
								</th>
							</tr>
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

									<a
										href="EquipmentAction!initupmaintenance.action?id=${pageMaintenance.id}">修改</a>/
									<a
										href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>/
									<a
										href="EquipmentAction!delSubmitt.action?id=${pageMaintenance.id} "
										onClick="return window.confirm('确认要删除吗？')">删除</a>
								</td>
								<s:if test="#pageStatus.last">
									</tr>
								</s:if>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="15" align="center">
									<font color="#ffffff">维修中信息</font>
								</th>
							</tr>
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
									<a
										href="EquipmentAction!findByclintMangagement.action?id=
									<s:property value="id" />">明细</a>

								</td>
								<s:if test="#pageStatus.last">

								</s:if>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="15" align="center">
									<font color="#ffffff">修复待验证信息</font>
								</th>
							</tr>
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
									${pageMaintenance.faultReason}
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
									<a
										href="EquipmentAction!upremarkMaintenance.action?id=${pageMaintenance.id}">确认修复</a>/
									<a
										href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>

								</td>
								<s:if test="#pageStatus.last">

								</s:if>

							</s:iterator>
							<tr bgcolor="green">
								<th colspan="15" align="center">
									<font color="#ffffff">正常信息</font>
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
									${pageMaintenance.faultReason}
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



									<a
										href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>

								</td>
								<s:if test="#pageStatus.last">

								</s:if>
							</s:iterator>
						</s:if>
						<s:else>

							<s:iterator value="repairList" id="pageMaintenance"
								status="repairPage">
								<s:if test="#repairPage.first">
									<s:if test='pageStatus=="guzhang"'>
										<tr bgcolor="green">
											<th colspan="15" align="center">
												<font color="#ffffff">开始维修信息</font>
											</th>
										</tr>
									</s:if>
									<s:else>
										<tr bgcolor="green">
											<th colspan="15" align="center">
												<font color="#ffffff">指派维修人信息</font>
											</th>
										</tr>
									</s:else>
								</s:if>
								<s:if test="#repairPage.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>

								<td>
									<s:if test="#repairPage.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#repairPage.index+1" />
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

									<s:if test='status=="故障"'>
										<a
											href="EquipmentAction!condition.action?id=${pageMaintenance.id}&pageStatus=${pageStatus}"
											onClick="return window.confirm('确认开始维修吗？')">开始维修</a>/
						
									<a
											href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>
									</s:if>
									<s:else>
										<a
											href="EquipmentAction!remarkMaintenance.action?id=${pageMaintenance.id}&pageStatus=${pageStatus}"
											onClick="return window.confirm('确认指派维修人吗？')">指派维修人</a>/
									<a
											href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>
									</s:else>


								</td>
							</s:iterator>

							<s:iterator value="repairList1" id="pageMaintenance"
								status="repairStatus">
								<s:if test="#repairStatus.first">
									<s:if test="errorMessage=='zhipai'">
										<tr bgcolor="green">
											<th colspan="15" align="center">
												<font color="#ffffff">修改维修人信息</font>
											</th>
										</tr>
									</s:if>
									<s:else>
										<tr bgcolor="green">
											<th colspan="15" align="center">
												<font color="#ffffff">维修中信息</font>
											</th>
										</tr>
									</s:else>
								</s:if>
								<s:if test="#repairStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:if test="#repairStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#repairStatus.index+1" />
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

									<s:if test="errorMessage=='zhipai'">
										<a
											href="EquipmentAction!remarkMaintenance.action?id=${pageMaintenance.id}">修改维修人</a>
									</s:if>
									<s:else>
										<s:if test='status=="维修中"'>
											<a
												href="EquipmentAction!remarkMaintenance.action?id=${pageMaintenance.id}&pageStatus=${pageStatus}">维修确认</a>/
										</s:if>
										<s:else>
											<a
												href="RequisitionAction!updateSubmit.action?id=${pageRequisition.id}&pageStatus=${pageStatus}"
												onClick="return window.confirm('确认通过吗？')">通过</a>/
											<a
												href="RequisitionAction!updateSubmit.action?id=${pageRequisition.id}&pageStatus=back${pageStatus}"
												onClick="return window.confirm('你确定要打回？')">打回</a>
										</s:else>
										<a
											href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>
									</s:else>
								</td>
							</s:iterator>
						</s:else>
						<tr>
							<td colspan="15" align="right">
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
			<script type="text/javascript">
onload = function() {
	document.getElementById("barcodeID").select();

}
</script>

		</div>
	</body>
</html>
