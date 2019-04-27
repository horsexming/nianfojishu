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
								<span id="title">开模申请单信息</span>
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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="MouldApplyOrderAction_findmaoList.action" method="post" >
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="" name="md.markId"/>
							</td>
							<th align="right">
								模具号
							</th>
							<td>
								<input type="text" value="" name="md.mojuNo"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								客户
							</th>
							<td>
								<input type="text" value="" name="md.mao.addUserName"/>
							</td>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="" name="md.mao.ywMarkId"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>申请单号</th>
						<th>客户名称</th>
						<th>业务件号</th>
						<th>制作方式</th>
						<th>件号</th>
						<th>零件名称</th>
						<th>模具号</th>
						<th>单台用量</th>
						<th>查看附件</th>
						<th>操作</th>
					</tr>
					<s:iterator value="mdList" id="pagemd" status="statussdf">
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
								
								<td>${pagemd.mao.planNumber}</td>
								<td>${pagemd.mao.kehu}</td>
								<td>${pagemd.mao.ywMarkId}</td>
								<td>${pagemd.mao.maketype}</td>
								<td>${pagemd.markId}</td>
								<td>${pagemd.proName}</td>
								<td>${pagemd.mojuNo}</td>
								<td>${pagemd.yongliang}</td>
								<td> <a href="FileViewAction.action?FilePath=/upload/file/MouldDetail/${pagemd.fileName}">查看附件</a></td>
								<td>
								</td>
						</tr>
					</s:iterator>
					<tr>
								<td colspan="30" align="right">
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
<SCRIPT type="text/javascript">
function showmingxi(num) {
		document.getElementById("xiugaiIframe").src = "MouldApplyOrderAction_findMoaAndmdList.action?id="
				+ num+"&pageStatus=mingxi";
	chageDiv('block')
}

</SCRIPT>
	</body>
</html>
