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
						<a href="dimissionLogAction_showList_zhuguan.action"
							style="color: #ffffff">刷新<br />(reflesh)</a>
					</div>
				</div>

				<div align="center">
					<h3>
						待确认离职申请单管理
						<br />
						(Dimission application form management)
					</h3>
					<!-- 内容 -->
					<form action="dimissionLogAction_showList_zhuguan.action"
						method="post">
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
								<td align="center">
									员工卡号（code）：
									<input type="text" name="dimissionLog.code" id="code" />
								</td>
							</tr>

							<tr>
								<td align="center" colspan="3">
									<input type="submit" style="width: 100px; height: 40px;"
										value="查询(select)" />
								</td>
							</tr>
						</table>
					</form>
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

						<s:iterator value="dimissionLogzhuguan" id="dimisl"
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
								<s:if
									test="#dimisl.add_dimissTime_status=='待填写'&&#dimisl.add_dimissTime_status!=null">
									<a
										href="dimissionLogAction_toupdateZhuguan.action?dimissionLog.id=${dimisl.id}&tag=${tag}">/<font color="red">添加离职时间</font></a>
								</s:if>
								<s:if test="#dimisl.add_dimissTime_status=='已填写'&&dimisl.add_dimissTime_status!=null">
									/已添加
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
									请求失败，不存在该申请单
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='updateNot'">
							<tr>
								<td colspan="10" align="center" style="color: red">
									修改失败，不存在该申请单
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='deleteNot'">
							<tr>
								<td colspan="10" align="center" style="color: red">
									删除失败，不存在该申请单
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
		function add(){
		window.location.href = "dimissionLogAction_toadd.action";
		}
		function update(id){
		window.location.href = "dimissionLogAction_toupdate.action?dimissionLog.id=" + id;
		}
		function selete(id){
		window.location.href = "dimissionLogAction_toselete.action?dimissionLog.id=" + id;
		}
		function todelete(id,cpage){
		window.location.href = "dimissionLogAction_delete.action?dimissionLog.id=" + id +"&cpage="+cpage;
		}
		</SCRIPT>
	</body>
</html>
