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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px;" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<a href="dimissionLogAction_showList_dai.action"
							style="color: #ffffff">刷新<br />(reflesh)</a>
					</div>
				</div>

				<div align="center">
					<h3>
						离职工资单添加
						<br />
						(Dimission application form management)
					</h3>
					<form action="dimissionLogAction_showList_dai.action" method="post">
						<table class="table" align="center">
							<tr>
								<td align="center">
									姓名（name）：
									<input type="text" name="dimissionLog.name" />
								</td>
								<td align="center">
									性别（sex）：
									<input type="text" name="dimissionLog.sex" />
								</td>
							</tr>
							<tr>
								<td align="center">
									部门（dept）：
									<select name="dimissionLog.dept" id="dept">
										<option></option>
									</select>
								</td>
								<td align="center">
									员工卡号（code）：
									<input type="text" name="dimissionLog.code" id="code" />
								</td>
							</tr>

							<tr>
								<td align="center" colspan="2">
									<input type="submit" style="width: 100px; height: 40px;"
										value="查询(select)" />
								</td>
							</tr>
						</table>
					</form>
					<!-- 内容 -->
					<table  class="table">
						<tr>
							<th colspan="10" align="center">

							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
								<br />
								(num)
							</td>
							<td align="center">
								姓名
								<br />
								(name)
							</td>
							<td align="center">
								部门
								<br />
								(dept)
							</td>
							<td align="center">
								工号
								<br />
								(code)
							</td>
							<td align="center">
								岗位
								<br />
								(duty)
							</td>
							<td align="center">
								工龄
								<br />
								(year)
							</td>
							<td align="center">
								申请单编号
								<br />
								（requisition number）
							</td>
							<td align="center">
								状态
								<br />
								（status）
							</td>
							<td align="center" colspan="2">
								操作
								<br />
								(Operation)
							</td>
						</tr>

						<s:iterator value="dimissionLogList" id="dimisl"
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
									<font color="#000000"></font>
								</s:if>
								<s:else>
									<font color="#ff0000"></font>
								</s:else>
								<s:property value="#pageStatus.index+1" />

							</td>
							<td>
								${dimisl.name}
							</td>
							<td>
								${dimisl.dept}
							</td>
							<td>
								${dimisl.code}
							</td>
							<td>
								${dimisl.job}
							</td>
							<td>
								${dimisl.year_term}
							</td>
							<td>
								${dimisl.shenqing_number}
							</td>
							<td>
								${dimisl.lzsq_status}
							</td>
							<td colspan="2">
								<a
									href="dimissionLogAction_toselect.action?dimissionLog.id=${dimisl.id}&tag=${tag}">预览</a>
								<s:if test="#dimisl.epId!=null">
									<a href="CircuitRunAction_findAduitPage.action?id=${dimisl.epId}">/审批动态</a>
								</s:if>
								<s:if
									test="#dimisl.zhengyi_Status!=null&&#dimisl.zhengyi_Status=='待填写'&&#dimisl.lzsq_status!='打回'">
									<a
										href="dimission_ZhengYiAction_toadd.action?dimissionLog.id=${dimisl.id}">/<font
										color="#ff0000">待添加离职工资单</font>
									</a>
								</s:if>
							</td>

						</s:iterator>

						<tr>
							<s:if test="errorMessage==null">
								<td colspan="10" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="10" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>
						</tr>
						<s:if test="successMessage=='addsuccess'">
							<tr>
								<td colspan="10" align="center" style="color: red">
									添加成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='updatesuccess'">
							<tr>
								<td colspan="10" align="center" style="color: red">
									修改成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='deletesuccess'">
							<tr>
								<td colspan="10" align="center" style="color: red">
									删除成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='seleteNot'">
							<tr>
								<td colspan="10" align="center" style="color: red">
									请求失败，不存在该交接单
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='updateNot'">
							<tr>
								<td colspan="10" align="center" style="color: red">
									修改失败，不存在该交接单
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='deleteNot'">
							<tr>
								<td colspan="10" align="center" style="color: red">
									删除失败，不存在该交接单
								</td>
							</tr>
						</s:if>
					</table>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		</SCRIPT>
	</body>
</html>
