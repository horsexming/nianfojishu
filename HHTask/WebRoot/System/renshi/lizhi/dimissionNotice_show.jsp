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
		<div align="center" id="gongneng">
			<div align="center" style="border: 0px solid #000000;">
				<%-- 
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				--%>
				<div align="center">
					<h3>
						离职通知单管理
						<br />
						(Dimission application form management)
					</h3>

					<form action="dimission_XieYiAction_showListNotice.action" method="post">
						<table class="table" align="center">
							<tr>
								<td align="center">
									姓名（name）：
									<input type="text" name="dimissionNotice.name" />
								</td>
								<td align="center">
									编号（number）：
									<input type="text" name="dimissionNotice.noticeNumber" />
								</td>
							</tr>
							<tr>
								<td align="center">
									部门（dept）：
									<select name="dimissionNotice.dept" id="dept">
										<option></option>
									</select>
								</td>
								<td align="center">
									员工号（code）：
									<input type="text" name="dimissionNotice.code" id="code" />
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
								编号
								<br />
								（number）
							</td>
							<td align="center">
								止薪日期
								<br />
								(duty)
							</td>
							<td align="center">
								停缴社保日期
								<br />
								(year)
							</td>
							<td align="center" colspan="2">
								操作
								<br />
								(Operation)
							</td>
						</tr>

						<s:iterator value="dimissionNotices" id="dimisl"
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
								${dimisl.noticeNumber}
							</td>
							<td>
								${dimisl.zhixin_time}
							</td>
							<td>
								${dimisl.shebao_time}
							</td>
							<td colspan="2">
								<a
									href="dimission_XieYiAction_toselectNotice.action?dimissionNotice.id=${dimisl.id}">预览</a>
							<a
									href="dimission_XieYiAction_toupdateNotice.action?dimissionNotice.id=${dimisl.id}">/修改</a>
							<a
									href="dimission_XieYiAction_deleteNotice.action?dimissionNotice.id=${dimisl.id}">/删除</a>
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
					</table>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
