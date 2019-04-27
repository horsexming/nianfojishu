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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>

			<div align="center">
				<form action="osaAction!findOSAppList.action?tag=${tag}"
					method="post">
					<table width="95%" class="table">
						<tr>
							<td>
								申报部门：
								<select name="osa.dept" style="width: 50px;" id="dept"
									onMouseOver="createDept('dept','osaAction!selectItem.action?tag=dept')">
									<option value="">
										申报部门
									</option>
									<option value="${osa.dept}" selected="selected">
										${osa.dept}
									</option>
									<OPTION></OPTION>
								</select>
							</td>
							<td>
								客户名称：
								<select name="osa.customer" style="width: 50px;" id="customer"
									onMouseOver="createDept('customer','osaAction!selectItem.action?tag=customer')">
									<option value="">
										选择客户
									</option>
									<option value="${osa.customer}" selected="selected">
										${osa.customer}
									</option>
							</td>
							<td>
								零件号：
								<select name="osa.markID" style=" width: 50px;" id="markID"
									onMouseOver="createDept('markID','osaAction!selectItem.action?tag=markID')">
									<option value="">
										选择零件号
									</option>
									<option value="${osa.markID}" selected="selected">
										${osa.markID}
									</option>
							</td>
							<td>
								工序号：
								<select name="osa.processNO" style="width: 50px;" id="processNO"
									onMouseOver="createDept('processNO','osaAction!selectItem.action?tag=processNO')">
									<option value="">
										选择工序号
									</option>
									<option value="${osa.processNO}" selected="selected">
										${osa.processNO}
									</option>
							</td>
							<td>
								是否加急：
								<select type="text" name="osa.isJiaji">
									<option value="">
										选择
									</option>
									<option value="${osa.isJiaji}" selected="selected">
										${osa.isJiaji}
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
							</td>
							<td>
								申请时限：
								<select name="osa.timeLimit">
									<OPTION value="">
										选择时限
									</OPTION>
									<option value="${osa.timeLimit}" selected="selected">
										${osa.timeLimit}
									</option>
									<OPTION value="长期">
										长期
									</OPTION>
									<OPTION value="短期">
										短期
									</OPTION>
								</select>
							</td>

						</tr>
						<tr>
							<td>
								申请单号：
								<select name="osa.osaNO" style="width: 50px;" id="osaNO"
									onMouseOver="createDept('osaNO','osaAction!selectItem.action?tag=osaNO')">
									<option value="">
										选择申请单号
									</option>
									<option value="${osa.osaNO}" selected="selected">
										${osa.osaNO}
									</option>
							</td>

							<td>
								条码：
								<input type="text" name="osa.number" size="5" />
							</td>
							<td>
								申报人：
								<input type="text" name="osa.username" size="5" />
							</td>
							<td>
								状态：
								<select name="osa.status">
									<option value="">
										选择状态
									</option>
									<option value="${osa.status}" selected="selected">
										${osa.status}
									</option>
									<OPTION value="申请">
										申请
									</OPTION>
									<OPTION value="审批">
										审批
									</OPTION>
								</select>

							</td>
							<td>
								日期从：
								<input class="Wdate" type="text" name="startDate"
									value="${ startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							<td>
								到：
								<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" class="input" value="查找" />

							</td>
							<s:if test="tag=='dept1'">
								<a href="System/SOP/OSApp/osa_add1.jsp">添加产品基本信息</a>
							</s:if>

						</tr>
					</table>
				</form>
				<br>
				<table class="table" style="width: 100%;">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							外委申请编号
						</th>
						<th align="center">
							外委人
						</th>
						<th align="center">
							部门
						</th>
						<th align="center">
							客户名称
						</th>
						<th align="center">
							零件号
						</th>
						<th align="center">
							外委工序
						</th>
						<th align="center">
							工序名
						</th>
						<th align="center">
							交付数量
						</th>
						<th align="center">
							状态
						</th>

						<th align="center">
							交付时间
						</th>
						<th align="center">
							是否加急
						</th>
						<th align="center">
							时限
						</th>
						<th align="center">
							设备故障
						</th>
						<th align="center">
							外委单价
						</th>
						<th align="center">
							自制单价
						</th>
						<th align="center">
							外委限量
						</th>
						<th align="center">
							录入状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<tr>
						<th colspan="19"
							style="background-color: red; color: #ffffff; height: 30px;">
							待录入数据
						</th>
					</tr>
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="osaV">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${osaV.osaNO}
							</td>
							<td>
								${osaV.username}
							</td>
							<td>
								${osaV.dept}
							</td>
							<td>
								${osaV.customer}
							</td>

							<td>
								${osaV.markID }
							</td>
							<td>
								${osaV.processNO}
							</td>
							<td>
								${osaV.processName}
							</td>
							<td>
								${osaV.deliveryCount}
							</td>
							<td>
								${osaV.status}
							</td>

							<td>
								${osaV.deliveryDate}
							</td>
							<td>
								${osaV.isJiaji}
							</td>
							<td>
								${osaV.timeLimit}
							</td>
							<td>
								${osaV.machineFail}
							</td>
							<td>
								${osaV.osOneHeji}
							</td>
							<td>
								${osaV.selfOneHeji}
							</td>
							<td>
								${osaV.addChengMinBalanceCount}
							</td>
							<td>
								${osaV.executeStatus}
							</td>

							<td width="35px;">
								<s:if test="tag=='dept1'">
									<s:if
										test="#osaV.executeStatus=='产品录入'||#osaV.executeStatus!='原因录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update1">修改</a>
									</s:if>
								</s:if>
								<s:if test="tag=='dept2'">
									<s:if
										test="#osaV.executeStatus=='周期录入'||#osaV.executeStatus=='产品录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update2">产品周期</a>
									</s:if>
								</s:if>
								<s:if test="tag=='dept3'">
									<s:if test="#osaV.executeStatus=='原因录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update3">申报原因</a>
									</s:if>
								</s:if>
								<s:if test="tag=='dept4'">
									<s:if test="#osaV.executeStatus=='成本核算录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update4">成本核算</a>
									</s:if>
								</s:if>
								<s:if test="tag=='dept5'">
									<s:if test="#osaV.executeStatus=='自制新增成本录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update5">自制新增成本</a>
									</s:if>
								</s:if>
								<s:if test="tag=='manager'">
									<s:if test="#osaV.executeStatus=='评审完成'">
										<a target="_showwaiwei"
											href="osaAction!getOSAById.action?id=${id}&crudTag=print">查看</a>
										<br />
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update">修改</a>
									</s:if>
								</s:if>
								<s:if test="tag=='self'">
									<s:if test="#osaV.executeStatus=='评审完成'">
										<a target="_showwaiwei"
											href="osaAction!getOSAById.action?id=${id}&crudTag=print">查看</a>
										<br />
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update">修改</a>
									</s:if>
								</s:if>

							</td>
						</tr>
						</s:iterator>
					</s:if>
					<tr>
						<th colspan="19"
							style="background-color: green; color: #ffffff; height: 30px;">
							录入历史数据
						</th>
					</tr>
					<s:if test="{listAll.size()>0}">
						<s:iterator value="listAll" status="se" id="osaVAll">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${osaVAll.osaNO}
							</td>
							<td>
								${osaVAll.username}
							</td>
							<td>
								${osaVAll.dept}
							</td>
							<td>
								${osaVAll.customer}
							</td>

							<td>
								${osaVAll.markID }
							</td>
							<td>
								${osaVAll.processNO}
							</td>
							<td>
								${osaVAll.processName}
							</td>
							<td>
								${osaVAll.deliveryCount}
							</td>
							<td>
								${osaVAll.status}
							</td>

							<td>
								${osaVAll.deliveryDate}
							</td>
							<td>
								${osaVAll.isJiaji}
							</td>
							<td>
								${osaVAll.timeLimit}
							</td>
							<td>
								${osaVAll.machineFail}
							</td>
							<td>
								${osaVAll.osOneHeji}
							</td>
							<td>
								${osaVAll.selfOneHeji}
							</td>
							<td>
								${osaVAll.addChengMinBalanceCount}
							</td>
							<td>
								${osaVAll.executeStatus}
							</td>

							<td width="35px;">
								<s:if test="tag=='dept1'">
									<s:if
										test="#osaVAll.executeStatus=='产品录入'||#osaVAll.executeStatus!='原因录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update1">修改</a>
									</s:if>
								</s:if>
								<s:if test="tag=='dept2'">
									<s:if
										test="#osaVAll.executeStatus=='周期录入'||#osaVAll.executeStatus=='产品录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update2">产品周期</a>
									</s:if>
								</s:if>
								<s:if test="tag=='dept3'">
									<s:if test="#osaVAll.executeStatus=='原因录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update3">申报原因</a>
									</s:if>
								</s:if>
								<s:if test="tag=='dept4'">
									<s:if test="#osaVAll.executeStatus=='成本核算录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update4">成本核算</a>
									</s:if>
								</s:if>
								<s:if test="tag=='dept5'">
									<s:if test="#osaVAll.executeStatus=='自制新增成本录入'">
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update5">自制新增成本</a>
									</s:if>
								</s:if>
								<s:if test="tag=='manager'">
									<s:if test="#osaVAll.executeStatus=='评审完成'">
										<a target="_showwaiwei"
											href="osaAction!getOSAById.action?id=${id}&crudTag=print">查看</a>
										<br />
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update">修改</a>
									</s:if>
								</s:if>
								<s:if test="tag=='self'">
									<s:if test="#osaVAll.executeStatus=='评审完成'">
										<a target="_showwaiwei"
											href="osaAction!getOSAById.action?id=${id}&crudTag=print">查看</a>
										<br />
										<a
											href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update">修改</a>
									</s:if>
								</s:if>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="19" align="right">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="17" style="font-size: 15px; color: red;">
								对不起，没有查到相关的外委申请单信息
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
