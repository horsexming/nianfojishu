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
			style="filter: Alpha(Opacity =                                                                                                                                                                                                                 75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
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
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
				<div id="addProductPrice"
					style="background-color: #ffffff; width: 100%;">
					<form action="RequisitionAction!addSubmit.action" method="post"
						onsubmit="return check()">
						<table border="1" width="100%" class="table">
							<tr>
								<td colspan="20" align="center"
									style="font-family: 微软雅黑; font-weight: bold;">
									添加申请单

								</td>
							</tr>
							<tr>
								<td align="right">
									申请人:
								</td>
								<td align="left">
									<select name="requisition.name" style="width: 155px">
										<option>
											${Users.name}
										</option>
									</select>
								</td>
								<td align="right">
									部门:
								</td>
								<td align="left">
									<select name="requisition.department" style="width: 155px">
										<option>
											${Users.dept}
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">
									查询信息时段从:
								</td>
								<td>

									<input class="Wdate" type="text" name="requisition.startdate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />

									到
									<input class="Wdate" type="text" name="requisition.enddate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<td align="right">
									查询内容:
								</td>
								<td>

									<input type="text" style="width: 250px; height: 80px;"
										name="requisition.content" />
								</td>
								</td>
								<td align="right">
									事由:
								</td>
								<td>

									<input type="text" style="width: 250px; height: 80px;"
										name="requisition.subjectmatter" />

								</td>
							</tr>

							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="添加"
										style="width: 100px; height: 50px;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 100px; height: 50px;">

								</td>


							</tr>
						</table>
					</form>
				</div>
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
						<form action="RequisitionAction!findAll.action" method="post"
							style="margin: 0px;">
							<table width="100%" border="1">
								<tr>
									<th colspan="6">
										申请单查询审批管理
									</th>
								</tr>
								<tr>
									<td align="center">
										申请时间:
										<input class="Wdate" type="text" name="requisition.itdate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd ',skin:'whyGreen'})" />

									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<input value="查询" type="submit"
											style="width: 100px; height: 50px">
										<s:if test="pageStatus==null">
											<input type="button" onclick="chageDiv('block');"
												value="添加申请单" style="width: 100px; height: 50px" />
										</s:if>
									</td>
								</tr>
							</table>
						</form>
					</s:if>
					
					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								申请人
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								申请时间
							</th>
							<th align="center">
								审批状态
							</th>
							<th align="center">
								备注内容
							</th>
							<th align="center">
								操作
							</th>
						</tr>

						<s:if test="pageStatus==null">
							<tr bgcolor="green">
								<th colspan="9" align="center">
									<font color="#ffffff">申请单待审批信息</font>
								</th>
							</tr>
							<s:iterator value="requisitionList1" id="pageRequisition"
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
									${pageRequisition.name}
								</td>
								<td>
									${pageRequisition.department}

								</td>

								<td>
									${pageRequisition.itdate}
								</td>
								<td>
									${pageRequisition.manager}
								</td>
								<td>
								</td>
								<td>
									<a
										href="RequisitionAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="9" align="center">
									<font color="#ffffff">申请单打回信息</font>
								</th>
							</tr>
							<s:iterator value="requisitionList2" id="pageRequisition"
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
									${pageRequisition.name}
								</td>
								<td>
									${pageRequisition.department}

								</td>
								<td>
									${pageRequisition.itdate}
								</td>
								<td>
									${pageRequisition.manager}
								</td>
								<td>
								</td>
								<td>
									<a
										href="RequisitionAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
									<a
										href="RequisitionAction!initUpRequisition.action?id=${pageRequisition.id}"">/修改</a>/
									<a
										href="RequisitionAction!delSubmit.action?id=${pageRequisition.id}"
										onClick="return window.confirm('确认要删除选中的信息吗？')">删除</a>

								</td>
								</tr>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="9" align="center">
									<font color="#ffffff">申请单审批同意(执行中)信息</font>
								</th>
							</tr>
							<s:iterator value="requisitionList3" id="pageRequisition"
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
									${pageRequisition.name}
								</td>
								<td>
									${pageRequisition.department}

								</td>
								<td>
									${pageRequisition.itdate}
								</td>
								<td>
									${pageRequisition.manager}
								</td>
								<td>
								</td>
								<td>
									<a
										href="RequisitionAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="9" align="center">
									<font color="#ffffff">申请单完成信息</font>
								</th>
							</tr>
							<s:iterator value="requisitionList" id="pageRequisition"
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
									${pageRequisition.name}
								</td>
								<td>
									${pageRequisition.department}

								</td>
								<td>
									${pageRequisition.itdate}
								</td>
								<td>
									${pageRequisition.manager}
								</td>
								<td>
									${pageRequisition.replyremarks}
								</td>
								<td>
									<a
										href="RequisitionAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
								</td>
								</tr>

							</s:iterator>
						</s:if>
						<s:else>
							<s:iterator value="requisitionList1" id="pageRequisition"
								status="pageStatus">
								<s:if test="#pageStatus.first">
									<tr bgcolor="green">
										<th colspan="9" align="center">
											<font color="#ffffff">申请单执行中信息</font>
										</th>
									</tr>
								</s:if>
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
									${pageRequisition.name}
								</td>
								<td>
									${pageRequisition.department}

								</td>
								<td>
									${pageRequisition.itdate}
								</td>
								<td>
									${pageRequisition.manager}
								</td>
								<td>
								</td>
								<td>
									<s:if test='manager=="任务执行中"'>
										<a
											href="RequisitionAction!remarkRequisition.action?id=${pageRequisition.id}&pageStatus=${pageStatus}">操作</a>
									</s:if>
									<s:else>

										<a
											href="RequisitionAction!updateSubmit.action?id=${pageRequisition.id}&pageStatus=${pageStatus}"
											onClick="return window.confirm('确认通过吗？')">通过</a>/
									<a
											href="RequisitionAction!updateSubmit.action?id=${pageRequisition.id}&pageStatus=back${pageStatus}"
											onClick="return window.confirm('你确定要打回？')">打回</a>
									</s:else>
									/
									<a
										href="RequisitionAction!findByclientManagement.action?id=<s:property value="id" />&pageStatus=${pageStatus}">明细</a>

								</td>
							</s:iterator>
							<s:iterator value="requisitionList" id="pageRequisition"
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
									${pageRequisition.name}
								</td>
								<td>
									${pageRequisition.department}

								</td>
								<td>
									${pageRequisition.itdate}
								</td>
								<td>
									${pageRequisition.manager}
								</td>
								<td>
								</td>
								<td>
									<s:if test='manager=="任务执行中"'>

									</s:if>
									<s:else>

										<a
											href="RequisitionAction!updateSubmit.action?id=${pageRequisition.id}&pageStatus=${pageStatus}"
											onClick="return window.confirm('确认通过吗？')">通过</a>/
									<a
											href="RequisitionAction!updateSubmit.action?id=${pageRequisition.id}&pageStatus=back${pageStatus}"
											onClick="return window.confirm('你确定要打回？')">打回</a>
									</s:else>
									/
									<a
										href="RequisitionAction!findByclientManagement.action?id=<s:property value="id" />&pageStatus=${pageStatus}">明细</a>

								</td>
								</tr>

							</s:iterator>
						</s:else>
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
