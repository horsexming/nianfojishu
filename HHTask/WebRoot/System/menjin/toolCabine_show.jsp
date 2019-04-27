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
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					工具柜信息查询
				</h3>
				<form action="ToolCabineAction_showList.action" method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								柜子编号(1~26)：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="toolCabine.cabOpenOrder" />
							</td>
							<th align="center" style="width: 25%">
								柜子IP：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="toolCabine.cabAceIp" />
							</td>
						</tr>
						<tr>
							<th align="center" style="width: 25%">
								现存物品名称：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="toolCabine.nowArticleName" />
							</td>
							<th align="center" style="width: 25%">
								现存物品规格：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="toolCabine.nowArticleFormat" />
							</td>
						</tr>
						<tr align="center">
							<td align="center" colspan="4">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
								<input type="button" value="添加" onclick="toadd()"
									style="width: 100px; height: 25px;"/>
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
							柜子标识
						</td>
						<td align="center">
							柜子编号
						</td>
						<td align="center">
							柜子IP
						</td>
						<td align="center">
							柜子规定物品名称
						</td>
						<td align="center">
							柜子规定物品规格
						</td>
						<td align="center">
							当前存放物品名称
						</td>
						<td align="center">
							当前存放物品规格
						</td>
						<td align="center">
							当前存放物品数量
						</td>
						<td align="center">
							柜子状态
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="toolCabineList" id="toolCabines"
						status="pageStatus">
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
							${toolCabines.cabOpenOrder}
						</td>
						<td align="center">
							${toolCabines.cabNumber}
						</td>
						<td align="center">
							${toolCabines.cabAceIp}
						</td>
						<td align="center">
							${toolCabines.articleName}
						</td>
						<td align="center">
							${toolCabines.articleFormat}
						</td>
						<td align="center">
							${toolCabines.nowArticleName}
						</td>
						<td align="center">
							${toolCabines.nowArticleFormat}
						</td>
						<td align="center">
							${toolCabines.nowNumber}
						</td>
						<td align="center">
							${toolCabines.cabStatus}
						</td>
						<td align="center" colspan="2">
							<a href="ToolCabineAction_toupdate.action?toolCabine.id=${toolCabines.id}">修改</a>
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
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toadd(){
	window.location.href = "ToolCabineAction_toadd.action";
}
</script>
	</body>
</html>
