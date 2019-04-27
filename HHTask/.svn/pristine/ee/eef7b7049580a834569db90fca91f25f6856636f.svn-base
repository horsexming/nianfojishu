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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
				</div>
			</div>

			<div align="center">
				<h3>
					查看应收总信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="NoncoreReceAction!update.action?tag=${tag}" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th>
								租贷类型：
							</th>
							<th align="left">
								${nonCoreReceivables.receiveType}
							</th>
							<th>
								缴租周期：
							</th>
							<td align="left">
								${nonCoreReceivables.jiaozuzhouqi}
							</td>
						</tr>
						<tr>
							<th>
								合同编号：
							</th>
							<td align="left">
								${nonCoreReceivables.hetongbianhao}
							</td>
							<th>
								合同附件：
							</th>
							<td align="left">
<%--								<a href="<%=path%>/upload/file/feiZY/${nonCoreReceivables.hetongfujian}">查看附件</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/feiZY/${nonCoreReceivables.hetongfujian}">查看附件</a>
							</td>
						</tr>
						<tr>
							<th>
								承租方：
							</th>
							<td align="left">
								${nonCoreReceivables.chengzufang}
							</td>
							<th>
								承租地址：
							</th>
							<td align="left">
								${nonCoreReceivables.chengzudizhi}
							</td>
						</tr>
						<tr>
							<th>
								经办人：
							</th>
							<td align="left">
								${nonCoreReceivables.jignbanren}
							</td>
							<th>
								开始有效期：
							</th>
							<td align="left">
								${nonCoreReceivables.youxiaoqiStart}
							</td>
						</tr>
						<tr>
							<th>
								租金：
							</th>
							<td align="left">
								${nonCoreReceivables.zujin} (单周期内租金)
							</td>
							<th>保证金：</th>
							<td align="left" >
								${nonCoreReceivables.baozhegnjin}
							</td>
						</tr>
						<tr>
							<th>
								是否需代缴费用(水电、管理)：
							</th>
							<td align="left">
								${nonCoreReceivables.isNeeddaitijiaofei}
							</td>
							<th>
								截止有效期：
							</th>
							<td align="left">
								${nonCoreReceivables.youxiaoqi}
							</td>
						</tr>
						<tr>
							<th>
								摘要：
							</th>
							<td align="left" colspan="3">
								${nonCoreReceivables.zhaiyao}
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
