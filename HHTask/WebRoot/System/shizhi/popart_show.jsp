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
					<a href="projectOrderPartAction_showList.action" style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					项目需求单零件管理
					<br />
					(project order part Management)
				</h3>
				<form action="projectOrderPartAction_showList.action" method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								项目名称（project name）：
								<input type="text" name="projectOrderPart.proName"
									value="<s:property value="projectOrderPart.proName"/>" />
							</td>
							<td align="center">
								件号（part number）：
								<input type="text" name="projectOrderPart.markId"
									value="<s:property value="projectOrderPart.markId"/>" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加零件(add)" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							项目名称
							<br />
							(project name)
						</td>
						<td align="center">
							月份
							<br />
							(month)
						</td>
						<td align="center">
							零件号
							<br />
							（part number）
						</td>
						<td align="center">
							零件数量
							<br />
							（part count）
						</td>
						<td align="center">
							类型
							<br />
							（type）
						</td>
						<td align="center">
							说明
							<br />
							（remark）
						</td>
						
						<td align="center" colspan="2">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<s:iterator value="poPartList" id="poPart" status="pageStatus">
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
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${poPart.proName}
						</td>
						<td>
							${poPart.month}
						</td>
						<td>
							${poPart.markId}
						</td>
						<td>
							${poPart.partNum}
						</td>
						<td>
							${poPart.type}
						</td>
						<td>
							${poPart.remark}
						</td>
						
						

						<td colspan="2">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${poPart.id},${cpage})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${poPart.id},${cpage})" />
						</td>

					</s:iterator>
					</tr>
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
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id,cpage) {
	window.location.href = "projectOrderPartAction_toupdate.action?projectOrderPart.id="
			+ id+"&cpage="+cpage;
}
function todelete(id,cpage) {
	window.location.href = "projectOrderPartAction_delete.action?projectOrderPart.id="
			+ id+"&cpage="+cpage;
}
function add() {
	window.location.href = "projectOrderPartAction_toadd.action";
}
</script>
	</body>
</html>
