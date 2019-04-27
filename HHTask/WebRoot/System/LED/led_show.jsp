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
		<div id="gongneng">

			<div align="center">
				<h3>
					LED管理
					<br />
					(LED Management)
				</h3>
				<form action="lEDAction_showList.action" method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								<input type="hidden" name="pageStatus"
									value="<s:property value='pageStatus'/>" />
								工位（name）：
								<input type="text" name="lED.stations"
									value="<s:property value="lED.stations"/>" />
							</td>
							<td align="center">
								ip（number）：
								<input type="text" name="lED.ip"
									value="<s:property value="lED.ip"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="hidden" name="pageStatus"
									value="<s:property value='pageStatus'/>" />
								名称（name）：
								<input type="text" name="lED.name"
									value="<s:property value="lED.name"/>" />
							</td>
							<td align="center">
								编号（number）：
								<input type="text" name="lED.number"
									value="<s:property value="lED.number"/>" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加LED(add)" onclick="add()" />
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
							名称
							<br />
							(name)
						</td>
						<td align="center">
							编号
							<br />
							（number）
						</td>
						<td align="center">
							IP
							<br />
							（ip）
						</td>
						<td align="center">
							端口
							<br />
							(port)
						</td>
						<td align="center">
							域名
							<br />
							(domainName)
						</td>
						<td align="center">
							穿戴标准
							<br />
							(dress)
						</td>
						<td align="center">
							工位
							<br />
							（stations）
						</td>
						<td align="center">
							宽
							<br />
							（width）
						</td>
						<td align="center">
							高
							<br />
							（higth）
						</td>
						<td align="center">
							字体大小
							<br />
							（fontSize）
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
					<s:iterator value="lEDList" id="lEDPage" status="pageStatus">
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
							${lEDPage.name }
						</td>
						<td>
							${lEDPage.number}
						</td>
						<td>
							${lEDPage.ip}
						</td>
						<td>
							${lEDPage.port}
						</td>
						<td>
							${lEDPage.domainName}
						</td>
						<td>
							${lEDPage.dress}
						</td>
						<td>
							${lEDPage.stations}
						</td>
						<td>
							${lEDPage.width}
						</td>
						<td>
							${lEDPage.higth}
						</td>
						<td>
							${lEDPage.fontSize}
						</td>
						<td>
							${lEDPage.sendStatus}
						</td>
						<td colspan="2">
							<a
								href="http://192.168.18.170:6161/SendMsServer/lEDAction_sendGongWeiMs.action?lED.id=${lEDPage.id }">推送工位</a>
							<input type="button" value="查看日志(view)"
								style="width: 60px; height: 30px;" onclick="view(${lEDPage.id})" />
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${lEDPage.id})" />
							<input type="button" style="width: 60px; height: 30px;"
								value="复制LED(Copy)" onclick="copy(${lEDPage.id})" />
							<s:if test="pageStatus=='manage'">
								<input type="button" value="删除(delete)"
									style="width: 60px; height: 30px;"
									onclick="todelete(${lEDPage.id })" />
							</s:if>
						</td>

					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="14" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="14" align="center" style="color: red">
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
function view(id) {
	var pageStatus = '<s:property value='
pageStatus'/>';
	window.location.href = "lEDAction_lEDLogView.action?lED.id=" + id+"&pageStatus="+pageStatus;
}
function update(id) {
	var pageStatus='<s:property value='pageStatus'/>';
	window.location.href = "lEDAction_toupdate.action?lED.id=" + id+"&pageStatus="+pageStatus;
}
function todelete(id) {
	if(window.confirm('确定删除吗?')){
	var pageStatus='<s:property value='pageStatus'/>';
	window.location.href = "lEDAction_delete.action?lED.id=" + id+"&pageStatus="+pageStatus;
	}
}
function add() {
	var pageStatus='<s:property value='pageStatus'/>';
	window.location.href = "<%=path%>/System/LED/led_add.jsp?pageStatus="+pageStatus;
}
function copy(id) {
	window.location.href = "lEDAction_copyAdd.action?lED.id="+id+"&pageStatus=${pageStatus}";
}
</script>
	</body>
</html>

