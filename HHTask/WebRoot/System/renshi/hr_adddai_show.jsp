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
	<body onload="createDept('interviewDept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px;" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<a href="interviewLogAction_showList1.action"
							style="color: #ffffff">刷新<br />(reflesh)</a>
					</div>
				</div>

				<div align="center">
					<h3>
						面试登记单管理
						<br />
						(Interview registration form management)
					</h3>
					<form action="interviewLogAction_showList1.action" method="post">
						<table class="table" align="center">
							<tr>
								<td align="center">
									姓名（project name）：
									<input type="text" name="interviewLog.name" />
								</td>
								<td align="center">
									应聘部门（candidates dept）：
									<select name="interviewLog.interviewDept" id="interviewDept">
										<option></option>
									</select>
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
							<th colspan="12" align="center">

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
								性别
								<br />
								(sex)
							</td>
							<td align="center" colspan="2">
								毕业学校
								<br />
								（finish school）
							</td>
							<td align="center">
								专业
								<br />
								（specialty）
							</td>
							<td align="center" colspan="2">
								通讯地址
								<br />
								（contact address）
							</td>
							<td align="center">
								联系方式
								<br />
								（tel）
							</td>
							<td align="center">
								工作经验
								<br />
								（experience Score）
							</td>
							<td align="center">
								录取结果
								<br />
								（enroll result）
							</td>
							<td align="center" colspan="2">
								操作
								<br />
								(Operation)
							</td>
						</tr>

						<s:iterator value="interviewLogList" id="interl"
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
								${interl.name}
							</td>
							<td>
								${interl.sex}
							</td>
							<td align="center" colspan="2">
								${interl.finishSchool}
							</td>
							<td>
								${interl.specialty}
							</td>
							<td align="center" colspan="2">
								${interl.contactAddress}
							</td>
							<td>
								${interl.tel}
							</td>
							<td>
								${interl.experienceScore}
							</td>
							<td>
								${interl.enroll_result}
							</td>
							<td colspan="2">
								<s:if 
									test="#interl.inter_status=='yes'&&#interl.inter_status!=null">
									<a
										href="interviewLogAction!to_add_ruzhi.action?interviewLog.id=${interl.id}">入职</a>
										<a
										href="interviewQuizzesAction_selectId.action?interviewLog.id=${interl.id}">/查看测试单</a>
								</s:if>

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
								<td colspan="12" align="center" style="color: red">
									${errorMessage}

								</td>
							</s:else>
						</tr>

						<s:if test="successMessage=='addsuccess'">
							<tr>
								<td colspan="12" align="center" style="color: red">
									添加成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='updatesuccess'">
							<tr>
								<td colspan="12" align="center" style="color: red">
									修改成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='deletesuccess'">
							<tr>
								<td colspan="12" align="center" style="color: red">
									删除成功
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
