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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form
					action="AttendancePersonInformationAction!selectAllAtPersonInforPage.action"
					method="post">
					<table class="table">
					    <tr>
					        <td colspan="3" align="center" style="font-size: 18px;font-weight:bold;">考勤人员信息维护</td>
					    </tr>
						<tr>
							<td>
								姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
								<input type="text"
									name="person.name" value="${person.name}"/>
							</td>
							<td>
								卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								<input type="text" name="person.cardNo" value="${person.cardNo}"/>
							</td>
							<td rowspan="2" align="center">
								<input type="submit" class="input" value="查询" />
								<input type="reset" class="input" value="重置" />
							</td>
							</tr>
						    <tr>
							<td>
								部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:
								<SELECT name="department.name" id="department"
									onMouseOver="createDept('department','AttendancePersonInformationAction!findSTPDept.action?tag=department')">
								<option value="">选择部门</option>
								<option value="${department.name}" checked="checked">${department.name}</option>
								</SELECT>
							</td>
							<td>
								班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次:
								<SELECT name="attendancePersonInformation.classNo" style="width:150px;">
								<option value="A00001">白班</option>
								<option value="A00002">夜班</option>
								</SELECT>
							</td>
						</tr>
						
					</table>
				</form>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							姓名
						</th>
						<th>
							部门
						</th>
						<th>
							卡号
						</th>
						<th>
							班次
						</th>
						<th>
							添加时间
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="p"
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${p[0]}
						</td>
						<td>
							${p[1]}
						</td>
						<td>
							${p[2]}
						</td>
						<td>
							${p[3]}
						</td>
						<td>
							${p[4]}
						</td>
						<td>
							
							<a
								href="AttendancePersonInformationAction!getKQPersonId.action?id=${p[5]}"><font
								color="red">修改</font>
							</a> 
						
							<a onClick="return confirm('确定要删除该条记录吗？')"
								href="AttendancePersonInformationAction!deleteInfor.action?id=${p[5]}"><font
								color="red">删除</font> </a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="7" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="17" align="center" style="color: red">
						</s:else>
						</td>
					</tr>
				</table>
				<div align="left">
				</div>
			</div>
			<div align="left">
				<br>
			</div>
		</div>
		<div align="left">
			<%@include file="/util/foot.jsp"%>
			<center></center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		</div>
	</body>
</html>
