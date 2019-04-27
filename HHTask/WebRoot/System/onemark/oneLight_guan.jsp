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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.showdiv {
	border: solid 1px #000000;
	width: 90px;
	height: 90px;
	float: left;
	margin-left: 20px;
	margin-top: 50px;
	text-align: center;
	border-radius: 90%;
	cursor: pointer;
	font-size: 14px;
	font-weight: bolder;
	background-color: gray;
	color: #ffffff;
}

.showdiv2 {
	border: solid 1px #000000;
	width: 90px;
	height: 90px;
	float: left;
	margin-left: 20px;
	margin-top: 50px;
	text-align: center;
	border-radius: 90%;
	cursor: pointer;
	font-size: 14px;
	font-weight: bolder;
	background-color: olive;
	color: #ffffff;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<s:iterator id="light" value="lightList"
					status="pageStatus">
					<s:if test="#pageStatus.index%8==0">
						<div style="clear: both;"></div>
					</s:if>
					<s:if test="#light.lightStatus=='打开'">
							<div class="showdiv2"
								onclick="openOrClose('${light.id}','${light.lightZhiLing}')">
								<br />
								${light.lightNum}
								<br />
								${light.lightStatus}
							</div>
						</s:if>
						<s:if test="#light.lightStatus=='关闭'">
							<div class="showdiv"
								onclick="openOrClose('${light.id}','${light.lightZhiLing}')">
								<br />
								${light.lightNum}
								<br />
								${light.lightStatus}
							</div>
						</s:if>
				</s:iterator>
			</div>
			<div style="float: right;width: 100%;" align="center">
				<s:if test="errorMessage==null">
					<span style="width: 100%;">
						第
						<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
						页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
							styleClass="page" theme="number" />
					</span>
				</s:if>
				<s:else>
					<span style="color: red;width: 100%;font-size:22px">
						${errorMessage}
					</span>
				</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function openOrClose(id, status) {
	window.location.href = "OneLightAction_openLight.action?light.id=" + id
			+ "&light.lightZhiLing=" + status + "&tag=${tag}";
}
</script>
	</body>
</html>
