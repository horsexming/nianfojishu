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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在设备点检内容进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					<font size="4">添加设备点检内容</font>
				</h3>
				
				<FORM ACTION="DJNRAction_add.action" METHOD="POST"
					ONSUBMIT="return check()">
					<TABLE WIDTH="75%" CLASS="TABLE">
						<TR>
							<TD ALIGN="RIGHT">
								点检内容:
								<INPUT TYPE="TEXT" NAME="djnr.nr" ID="nr">
							</TD>
							<TD ALIGN="LEFT">
								<INPUT TYPE="SUBMIT" ID="SUB" VALUE="添加"
									STYLE="WIDTH: 75PX; HEIGHT: 30PX" />&nbsp;&nbsp;&nbsp;&nbsp;
								
								<INPUT TYPE="RESET" VALUE="重置" STYLE="WIDTH: 75PX; HEIGHT: 30PX" />
							</TD>
						</TR>
					</TABLE>
				</FORM>
				<hr/>
				<h3>
				<font size="4">设备点检内容</font>
					
				</h3>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<td>
							序号
						</td>
						<td>
							点检内容
						</td>
						<td>
							操作
						</td>
					</tr>

					<s:iterator id="djnrtest" value="djnrList" status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${djnrtest.nr}
						</td>
						<td>
							<a href="javascript:;" onclick="showupdate(${djnrtest.id})">修改</a>/
							<a href="DJNRAction_del.action?djnr.id=${djnrtest.id}"
								onclick="return confirm('确定要删除吗?')">删除</a>

						</td>
					</s:iterator>
					</tr>
					<tr>
						<td colspan="3" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function showupdate(id) {
	$("#xiugaiIframe")
			.attr("src", "DJNRAction_initupdate.action?djnr.id=" + id);
	chageDiv('block');
}
function check() {
	var nr = document.getElementById("nr");
	if (nr != null && nr.value == "") {
		alert('点检内容不能为空!');
		nr.focus();
		return false;
	}
	document.getElementById("sub").disabled = "disabled";
	return true;

}
</script>
	</body>
</html>
