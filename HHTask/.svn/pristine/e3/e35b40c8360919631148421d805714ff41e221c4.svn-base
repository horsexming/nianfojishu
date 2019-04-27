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
		<title></title>
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
							<span id="title"></span>
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
				<form action="TbBarcodeLockNoticeAction_findAlltbln.action"
					method="post">
					<table class="table">
						<tr>
							<th>
								条码
							</th>
							<td>
								<input type="text" value="" name="tbln.barcode" />
							</td>
							<th>
								料号
							</th>
							<td>
								<input type="text" value="" name="tbln.matCode" />
							</td>
						</tr>
						<tr>
							<th>
								隔离单号
							</th>
							<td>
								<input type="text" value="" name="tbln.lockNo" />
							</td>
							<th>
								锁定日期
							</th>
							<td>
								<input type="text" value="" name="tbln.lockDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									class="Wdate" />
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" name="" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							条码
						</th>
						<th>
							料号
						</th>
						<th>
							品名
						</th>
						<th>
							锁定状态
						</th>
						<th>
							锁定原因
						</th>
						<th>
							解锁原因
						</th>
						<th>
							锁定人
						</th>
						<th>
							锁定日期
						</th>
						<th>
							隔离单号
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="pagetbln" value="tblnList" status="statussdf">
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
							${pagetbln.barcode}
						</td>
						<td>
							${pagetbln.matCode}
						</td>
						<td>
							${pagetbln.matName}
						</td>
						<td>
							${pagetbln.locked}
						</td>
						<td>
							${pagetbln.lockReason}
						</td>
						<td>
							${pagetbln.unlockReason}
						</td>
						<td>
							${pagetbln.lockUser}
						</td>
						<td>
							${pagetbln.lockDate}
						</td>
						<td>
							${pagetbln.lockNo}
						</td>
						<td>
							${pagetbln.status}
						</td>
						<td>
							<s:if test='#pagetbln.fileName!=null && #pagetbln.fileName!=""'>
								<a
									href="FileViewAction.action?FilePath=/upload/file/TbBarcodeLockNotice/${pagetbln.fileName}&Refresh=true">查看附件</a>/
									</s:if>
							<a href="javascript:;" onclick="tanchu('${pagetbln.id}')">修改</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="18" align="right">
							共
							<s:property value="total" />
							页 第
							<s:property value="cpage" />
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
function tanchu(num) {
	document.getElementById("xiugaiIframe").src = "TbBarcodeLockNoticeAction_findtblnById.action?id="
			+ num;
	chageDiv('block')
}
</script>

	</body>
</html>
