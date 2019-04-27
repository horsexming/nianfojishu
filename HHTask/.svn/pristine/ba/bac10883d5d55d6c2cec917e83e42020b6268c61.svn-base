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
						<a href="dimission_XieYiAction_showList.action"
							style="color: #ffffff">刷新<br />(reflesh)</a>
					</div>
				</div>

				<div align="center">
					<h3>
						劳动终止协议管理
						<br />
						(Labor management termination agreement)
					</h3>
					<form action="dimission_XieYiAction_showList.action" method="post">
						<table class="table" align="center">
							<tr>
								<td align="center" style="border-right-width: 0px;">
									姓名（name）：
									<input type="text" name="dimissionZhengYi.name" />
								</td>
								<td style="border-left-width: 0px;" align="left" colspan="2">
									<input type="submit" style="width: 100px; height: 40px;"
										value="查询(select)" />
								</td>
							</tr>
						</table>
					</form>
					<!-- 内容 -->
					<table  class="table">
						<tr>
							<th colspan="5" align="center">

							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
								<br />
								(num)
							</td>
							<td align="center">
								乙方姓名
								<br />
								(name)
							</td>
							<td align="center">
								乙方电话
								<br />
								(entry time)
							</td>
							<td align="center">
								协议编号
								<br />
								(Agreement Number)
							</td>
							<td align="center" colspan="2">
								操作
								<br />
								(Operation)
							</td>
						</tr>

						<s:iterator value="dimissionXieYis" id="dimisl"
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
								${dimisl._Bfang}
							</td>
							<td>
								${dimisl._Btel}
							</td>
							<td>
								${dimisl.xieyi_number}
							</td>
							<td colspan="2">
								<a  
									href="dimission_XieYiAction_toselect.action?dimissionXieYi.id=${dimisl.id}">预览</a>
									<s:if test="#dimisl.xieyi_Status!=null">
								<a href="CircuitRunAction_findAduitPage.action?id=${dimisl.epId}">/审批动态</a>
									</s:if>
								<s:if test="#dimisl.xieyi_Status=='未审核'||#dimisl.xieyi_Status=='打回'&&#dimisl.xieyi_Status!=null">
									<%--<a
										href="dimission_XieYiAction_toupdate.action?dimissionXieYi.id=${dimisl.id}">/修改</a>
									--%><a onclick="return window.confirm('您将删除数据，是否继续?')"
										href="dimission_XieYiAction_delete.action?dimissionXieYi.id=${dimisl.id}&cpage=${cpage}">/删除</a>
								</s:if>
							</td>

						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="6" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="6" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>
						</tr>
						<s:if test="successMessage=='addsuccess'">
							<tr>
								<td colspan="6" align="center" style="color: red">
									添加成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='updatesuccess'">
							<tr>
								<td colspan="6" align="center" style="color: red">
									修改成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='deletesuccess'">
							<tr>
								<td colspan="6" align="center" style="color: red">
									删除成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='seleteNot'">
							<tr>
								<td colspan="6" align="center" style="color: red">
									请求失败，不存在该离职协议
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='deleteNot'">
							<tr>
								<td colspan="6" align="center" style="color: red">
									删除失败，该离职协议不存在与之对应的离职申请
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
