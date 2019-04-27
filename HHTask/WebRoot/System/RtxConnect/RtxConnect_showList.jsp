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
								<span id="title">您正进行RTX设置添加</span>
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
			<h2 style="">RTX连接配置信息</h2>
			<div align="center" >
				<a href="javascript:;" onclick="tanchu('')" >添加</a>
			</div>
				<table class="table">
					<tr  align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>rtx服务器地址</th>
						<th>rxt端口</th>
						<th>rxt默认账户</th>
						<th>rxt登录密码</th>
						<th>JDBC驱动</th>
						<th>服务器和数据库rtxdb地址</th>
						<th>pebURL</th>
						<th>数据库用户名</th>
						<th>数据库密码</th>
						<th>发送邮件邮箱</th>
						<th>jdp用户名</th>
						<th>jdp密码</th>
						<th>操作</th>
					</tr>
					<s:iterator value="rtxConnectList" id="pageRtxConnet" status="pageStatus">
						<tr align="center">
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
									<s:property value="#pageStatus.index+1" />
								</td>
								<td>
									${pageRtxConnet.rtxIp}
								</td>
								<td>
									${pageRtxConnet.rtxPort}
								</td>
								<td>
									${pageRtxConnet.sender}
								</td>
								<td>
									${pageRtxConnet.pwd}
								</td>
								<td align="left">
									${pageRtxConnet.driverName}
								</td>
								<td align="left">
									${pageRtxConnet.dbURL}
								</td>
								<td align="left">
									${pageRtxConnet.pebsURL}
								</td>
								<td>
									${pageRtxConnet.userName}
								</td>
								<td>
									${pageRtxConnet.userPwd}
								</td>
								<td>
									${pageRtxConnet.spareMail}
								</td>
								<td>
									${pageRtxConnet.jdpUserName}
								</td>
								<td>
									${pageRtxConnet.jdpPassWord}
								</td>
								<td>
									<a href="javascript:;" onclick="tanchu('${pageRtxConnet.id}')" >修改</a>/
									<a href="rtxMsgAction_delRtxConnect action?id=${pageRtxConnet.id}" onclick="return confirm('删除后会导致RTX无法发送消息，确定还要删除吗？')">删除</a>
								</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function tanchu(num) {
	if(num!=""){
		document.getElementById("xiugaiIframe").src = "rtxMsgAction_getRtxConnectById.action?id="+num;
	}else{
		document.getElementById("xiugaiIframe").src = "<%=basePath%>/System/RtxConnect/RtxConnect_add.jsp";
	}
	chageDiv('block')
}

</SCRIPT>
	</body>
</html>