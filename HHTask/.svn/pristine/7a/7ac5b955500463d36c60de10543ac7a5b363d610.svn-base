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
		<style type="text/css">
a {
	cursor: pointer;
}

.table {
	border: 0px solid #999;
	width: 756px;
	border-collapse: collapse;
}

.table th,.table td {
	border-width: 1px;
	padding: 0px;
}

.subTable {
	text-align: center;
	border-collapse: collapse;
	width: 100%;
	border-width: 1px;
	border-style: hidden;
}
</style>
	</head>
	<body style="text-align: center;">
		<!-- 弹出层开始 -->
		<div id="bodyDiv" align="center" class="transDiv" style="left: 0px;"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 999; width: 1024px; display: none;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; height: 400px;">
					<iframe id="uploadIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 100%; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<!-- 弹出层结束 -->
		<div align="center">
			<!-- A4页面开始 -->
			<div id="printDiv" style="width: 794px; border: 1px solid #000000;">
				<!-- 页边距内开始 -->
				<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
				<div
					style="width: 756px; border: 1px solid #000000; position: relative; top: 0px; bottom: 100px;">
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}" />
					<input type="hidden" id="processTableId" />
					<table id="processDataListTable" width="width:750px;" class="table"
						cellpadding="0" cellspacing="0">
						<!-- 1 -->
						<tr align="center">
							<td align="center" colspan="9">
								<table class="subTable">
									<tr>
										<td rowspan="2" style="width: 13%;">
											<img style="width: 80px;"
												src="<%=basePath%>${companyInfo.shhhjpg}" />
										</td>
										<td style="width: 20%; font-size: 25px;" rowspan="2">
											工艺程序图表
										</td>
										<td style="width: 13%">
											型别
										</td>
										<td style="width: 20%">
											件号
										</td>
										<td style="width: 20%">
											件名
										</td>
									</tr>
									<tr>
										<td>
											${gongyiGuicheng.xingbie}
										</td>
										<td>
											${gongyiGuicheng.jianNumb}
										</td>
										<td>
											${gongyiGuicheng.jianName}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- 2 -->
						<tr align="center">
							<th style="width: 5%">
								工序号
							</th>
							<th style="width: 15%">
								工序名称
							</th>
							<th style="width: 10%;">
								状态
							</th>
							<th style="width: 10%;">
								操作
							</th>
						</tr>
						<!-- 34 -->
						<s:iterator id="p" value="processDataList" status="st">
							<tr align="center" style="line-height: 30px;">
								<td>
									${p.gongxuNo}
								</td>
								<td>
									${p.gongxuName}
								</td>
								<td>
									${p.editStatus}
								</td>
								<td>
									<a target="_detail"
										href="gongyiGuichengAction!findGGAffByggIDAndProId.action?gongyiGuichengAffix.gongyiGuichengId=${gongyiGuicheng.id}&gongyiGuichengAffix.processDataId=${p.id}&gongyiGuichengAffix.affixType=tupian&gongyiGuichengAffix.weizhi=gxsmlq">
										查看图纸 </a>
								</td>
							</tr>
						</s:iterator>
						<!-- 35 -->
					</table>
				</div>
				<!-- 页边距内结束-->
			</div>
			<!-- A4页面结束  -->
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
