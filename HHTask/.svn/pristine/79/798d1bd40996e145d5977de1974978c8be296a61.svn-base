<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<style type="text/css">
		
			a{
				cursor: hand;
			}
		</style>
	</head>
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					系统设置管理
				</h3>
				<form action="SetupCheckAction_findAllbyName.action" method="post">
					<table class="table" align="center">
						<tr>
							<th>
								审核方:
							</th>
							<td align="center">
								<select name="setupCheck.auditParty" id="auditParty"
									value="${setupCheck.auditParty}">
									<option value="${setupCheck.auditParty}">
										${setupCheck.auditParty}
									</option>
									<option value="内部审核">
										内部审核
									</option>
									<option value="内部稽查">
										内部稽查
									</option>
									<option value="第三方审核">
										第三方审核
									</option>
									<option value="客户审核">
										客户审核
									</option>
									<option value="客户稽查">
										客户稽查
									</option>
									<option value="三按两遵守">
										三按两遵守
									</option>
									<option value="5S稽查">
										5S稽查
									</option>
									<option value="错混料稽查">
										错混料稽查
									</option>
									<option value="直发直提稽查">
										直发直提稽查
									</option>
									<option value="PCN稽查">
										PCN稽查
									</option>
									<option value="ECN稽查">
										ECN稽查
									</option>
								</select>
							</td>
							<th>
								审核类别
							</th>
							<td align="center">
								<select name="setupCheck.checkType" id="checkType">
									<option value="${setupCheck.checkType}">
										${setupCheck.checkType}
									</option>
									<option value="QSA">
										QSA
									</option>
									<option value="QPA">
										QPA
									</option>
									<option value="CSR">
										CSR
									</option>
									<option value="OHSAS">
										OHSAS
									</option>
									<option value="三防">
										三防
									</option>
									<option value="三标联合">
										三标联合
									</option>
									<option value="其他审核">
										其他审核
									</option>
								</select>
							</td>
							<th>
								审核日期
							</th>
							<td align="center">
								<input name="setupCheck.checkDate"
									value="${setupCheck.checkDate}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>
								完成日期
							</th>
							<td align="center">
								<input name="setupCheck.allTime" value="${setupCheck.allTime}"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th>
								所属部门
							</th>
							<td align="center">
								<SELECT id="dept" name="setupCheck.department">
									<option value="${setupCheck.department}">
										${setupCheck.department}
									</option>
								</SELECT>
							</td>
							<th>
								总负责人
							</th>
							<td align="center">
								<input id="bfName" name="setupCheck.uploadPerson"
									value="${setupCheck.uploadPerson}">
							</td>

						</tr>
						<tr>
						<th>
								总负责人
							</th>
							<td align="center">
								<input id="bfName" name="setupCheck.uploadPerson1"
									value="${setupCheck.uploadPerson1}">
							</td>
							<th>
								状态
							</th>
							<td align="center">
								<input id="status" name="setupCheck.stauts"
									value="${setupCheck.stauts}">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" style="width: 100px; height: 35px;"
									value="查询(select)" />
								<input type="reset" style="width: 100px; height: 35px;"
									value="重置" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">

					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							审核方
						</td>
						<td align="center">
							审核类型
						</td>
						<td align="center">
							审核日期
						</td>
						<td align="center">
							负责部门
						</td>
						<td align="center">
							总负责人
						</td>
						<td align="center">
							第二负责人
						</td>
						<td align="center">
							完成日期
						</td>
						<td align="center">
							问题描述
						</td>
						<td align="center">
							状态
						</td>
						
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="setupCheckList" id="list" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list.auditParty}
						</td>
						<td align="center">
							${list.checkType}
						</td>
						<td align="center">
							${list.checkDate}
						</td>
						<td align="center">
							${list.department}
						</td>
						<td align="center">
							${list.uploadPerson}
						</td>
						<td align="center">
							${list.uploadPerson1}
						</td>
						<td align="center">
							${list.allTime}
						</td>
						<td align="center">
							${list.description}
						</td>
						<td align="center">
							${list.stauts}
						</td>
						<td colspan="2">
							<a onclick="toDetail(${list.id},'${cpage}')">措施详情</a>/
							<s:if test="#list.shortFile!=null">
<%--							<a href="<%=path%>/upload/file/detail/${list.shortFile}">详情文件查看</a>--%>
							<a href="FileViewAction.action?FilePath=/upload/file/detail/${list.shortFile}">详情文件查看</a>
							</s:if><s:else><FONT color="red">无文件</FONT></s:else>
						</td>
					</s:iterator>

					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font>/
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
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id, cpage) {
	window.location.href = "SetupCheckAction_toUpdate.action?setupCheck.id="
			+ id + "&cpage=" + cpage + "&tag=" + "self";
}
function todelete(id, cpage) {
	window.location.href = "SetupCheckAction_delete.action?setupCheck.id=" + id
			+ "&cpage=" + cpage + "&tag=" + "self";
}
function addWay(id, cpage) {
	window.location.href = "SetupCheckAction_toAddWay.action?setupCheck.id="
			+ id + "&cpage=" + cpage + "&tag=" + "self";
}
function toFile(id, cpage) {
	window.location.href = "SetupCheckAction_toUpdateFile.action?setupCheck.id="
			+ id + "&cpage=" + cpage + "&tag=" + "self";
}
function toDetail(id, cpage) {
	window.location.href = "SetupCheckAction_toDetail.action?setupCheck.id="
			+ id + "&cpage=" + cpage + "&tag=" + "self"+"&nopass="+"nopass"+"&nooperate="+"nooperate";
}

</script>
	</body>
</html>
