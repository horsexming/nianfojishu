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
	<body onload="createDept('selectDept')">
		<div id="bodyDiv" align="center"
			style="filter: Alpha(Opacity =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
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
					<form action="RepairAction!findAll.action" method="post"
						style="margin: 0px;">
						<input type="hidden" name="pageStatus" value="findByCon" />
						<table class="table" border="0">
							<tr>
								<th colspan="6">
									报修查询管理
								</th>
							</tr>
							<tr>
								<td align="right">
									工号：
								</td>
								<td>
									<input type="text" style="width: 150px;"
										name="repair.jobnumber">
								</td>
								<td align="right">
									报修人：
								</td>
								<td>
									<input type="text" style="width: 150px;" name="repair.name">
								</td>
								<td align="right">
									修理人:
								</td>
								<td>
									<input type="text" style="width: 150px;"
										name="repair.repairers">
								</td>
							</tr>
							<tr>
								<td align="right">
									类别: &nbsp;&nbsp;
								</td>
								<td>
									<select style="width: 150px;" name="repair.category">
										<option></option>
										<option value="信息系统类">
											信息系统类
										</option>
										<option value="PC电脑打印机类">
											PC电脑打印机类
										</option>
										<option value="综合公共信息类">
											综合公共信息类
										</option>
										<option value="网络通讯类">
											网络通讯类
										</option>

									</select>
								</td>
								<td align="right">
									部门: &nbsp;&nbsp;
								</td>
								<td>
									<select id="selectDept" style="width: 155px;"
										name="repair.department">
										<option></option>
									</select>

								</td>
								<td align="right">
									状态:
								</td>
								<td>
									<select style="width: 150px;" name="repair.status">
										<option></option>
										<option value="待确定">
											待确定
										</option>
										<option value="待指派">
											待指派
										</option>
										<option value="维修中">
											维修中
										</option>
										<option value="修复完成">
											修复完成
										</option>

									</select>
								</td>
							</tr>
							<tr align="center">
								<td colspan="8" rowspan="5">
									<input type="submit" value="查询"
										style="width: 100px; height: 60px">

									<a
										href="RepairAction!findAll.action?repair.name=&pageStatus=findAll">查询所有</a>
								</td>
							</tr>
						</table>
					</form>
					
					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								报修设备
							</th>
							<th align="center">
								报修时间
							</th>
							<th align="center">
								类别
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								修理反馈
							</th>
							<th align="center">
								报修人
							</th>
							<th align="center">
								修复时间
							</th>
							<th align="center">
								确认倒计时
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:if test="pageStatus!='findByCon'">
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">待指派信息</font>
								</th>
							</tr>
						</s:if>
						<s:iterator value="repairList3" id="pageRepair"
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
								${pageRepair.jobnumber}
							</td>
							<td>
								${pageRepair.name}
							</td>
							<td>
								${pageRepair.department}

							</td>

							<td>
								${pageRepair.devicename}
							</td>
							<td>
								${pageRepair.repairtime}
							</td>
							<td>
								${pageRepair.category}
							</td>
							<td>
								${pageRepair.status}
							</td>
							<td>
								${pageRepair.repairfeedback}
							</td>
							<td>
								${pageRepair.personalnominee}
							</td>
							<td>
								${pageRepair.timetorepair}
							</td>
							<td>
								${pageRepair.countdowntime}
							</td>
							<td>


								<a
									href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>

							</td>
							<s:if test="#pageStatus.last">

							</s:if>
						</s:iterator>
						<s:if test="pageStatus!='findByCon'">
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">待确定信息</font>

								</th>
							</tr>
						</s:if>
						<s:iterator value="repairList1" id="pageRepair"
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
								${pageRepair.jobnumber}
							</td>
							<td>
								${pageRepair.name}
							</td>
							<td>
								${pageRepair.department}

							</td>

							<td>
								${pageRepair.devicename}
							</td>
							<td>
								${pageRepair.repairtime}
							</td>
							<td>
								${pageRepair.category}
							</td>
							<td>
								${pageRepair.status}
							</td>
							<td>
								${pageRepair.repairfeedback}
							</td>
							<td>
								${pageRepair.personalnominee}
							</td>
							<td>
								${pageRepair.timetorepair}
							</td>
							<td>
								${pageRepair.countdowntime}
							</td>
							<td>


								<a
									href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>

							</td>
							<s:if test="#pageStatus.last">
								</tr>
							</s:if>
						</s:iterator>
						<s:if test="pageStatus!='findByCon'">
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">维修中信息</font>
								</th>
							</tr>
						</s:if>
						<s:iterator value="repairList2" id="pageRepair"
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
								${pageRepair.jobnumber}
							</td>
							<td>
								${pageRepair.name}
							</td>
							<td>
								${pageRepair.department}

							</td>

							<td>
								${pageRepair.devicename}
							</td>
							<td>
								${pageRepair.repairtime}
							</td>
							<td>
								${pageRepair.category}
							</td>
							<td>
								${pageRepair.status}
							</td>
							<td>
								${pageRepair.repairfeedback}
							</td>
							<td>
								${pageRepair.personalnominee}
							</td>
							<td>
								${pageRepair.timetorepair}
							</td>
							<td>
								${pageRepair.countdowntime}
							</td>
							<td>


								<a
									href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>

							</td>
							<s:if test="#pageStatus.last">
								</tr>
							</s:if>

						</s:iterator>
						<s:if test="pageStatus!='findByCon'">
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">修复确认信息</font>
								</th>
							</tr>
						</s:if>
						<s:iterator value="repairList4" id="pageRepair"
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
								${pageRepair.jobnumber}
							</td>
							<td>
								${pageRepair.name}
							</td>
							<td>
								${pageRepair.department}

							</td>

							<td>
								${pageRepair.devicename}
							</td>
							<td>
								${pageRepair.repairtime}
							</td>
							<td>
								${pageRepair.category}
							</td>
							<td>
								${pageRepair.status}
							</td>
							<td>
								${pageRepair.repairfeedback}
							</td>
							<td>
								${pageRepair.personalnominee}
							</td>
							<td>
								${pageRepair.timetorepair}
							</td>
							<td style="color: red; font-size: 18px; font-weight: bolder;">
								${pageRepair.countdowntime}分钟
							</td>
							<td>


								<a
									href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>

							</td>
							<s:if test="#pageStatus.last">
								</tr>
							</s:if>
						</s:iterator>
						<tr bgcolor="green">
							<th colspan="13" align="center">
								<s:if test="pageStatus=='findByCon'">
									<font color="#ffffff">查询信息</font>
								</s:if>
								<s:else>
									<font color="#ffffff">修复完成信息</font>
								</s:else>

							</th>
						</tr>
						<s:iterator value="repairList" id="pageRepair" status="pageStatus">
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
								${pageRepair.jobnumber}
							</td>
							<td>
								${pageRepair.name}
							</td>
							<td>
								${pageRepair.department}

							</td>

							<td>
								${pageRepair.devicename}
							</td>
							<td>
								${pageRepair.repairtime}
							</td>
							<td>
								${pageRepair.category}
							</td>
							<td>
								${pageRepair.status}
							</td>
							<td>
								${pageRepair.repairfeedback}
							</td>
							<td>
								${pageRepair.personalnominee}
							</td>
							<td>
								${pageRepair.repairpersontime}
							</td>
							<td>
								${pageRepair.countdowntime}
							</td>
							<td>


								<a
									href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>

							</td>
							<s:if test="#pageStatus.last">

							</s:if>
						</s:iterator>
						<tr>
							<td colspan="13" align="right">
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
		</div>
	</body>
</html>
