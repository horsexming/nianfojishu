<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>生产力生态平衡系统</title>
		<%@include file="/util/inc.jsp"%>
		<script type="text/javascript">
function chageDivBorder(obj) {
	obj.style.border = "4px solid #4984e8";
}
function redDivBorder(obj) {
	obj.style.border = "solid 4px #ffffff";
}
</script>

	</head>
	<body bgcolor="#ffffff">
		<center>
			<div id="all" style="width: 980px;">
				<%@include file="/util/seachUnil.jsp"%>
				<div id="main" style="margin-top: 10px;">
					<div id="jianjie" align="left"
						style="width: 100%; border: solid 1px #0170b8;">
						<div id="xitong"
							style="margin-top: 0px; width: 100%; border: solid 1px #0170b8"
							align="left">
							<div id="xitong"
								style="width: 100%; font-weight: bold; padding-left: 40px; padding-top: 5px; padding-bottom: 5px; ">
								<font color="#ffffff">生产力生态平衡系统</font>
							</div>
							<div style="padding-bottom: 10px;">
								<table style="margin-left: 80px;">
									<s:iterator id="mf" value="#session.topModuleList"
										status="pageId">
										<s:if test="#pageId.index % 4 == 0">
											<tr>
										</s:if>
										<td style="width: 200px" align="left">
											<div
												style="border: solid 4px #ffffff; width: 194px; height: 144px; cursor: pointer; margin: 1px;"
												onmouseover="chageDivBorder(this)"
												onmouseout="redDivBorder(this)"
												onclick="goPageByUrl('${mf.id}','${mf.targetNewPage}')"
												title="${mf.functionName}: ${mf.functionIntro}">
												<div align="left"
													style="border: solid 1px #fff; width: 192px; height: 142px;">
													<div align="left"
														style="border: solid 1px #0362A2; width: 190px; height: 140px; margin: 0px; padding: 0px; background-color:${mf.bgColor}">
														<s:if test="#mf.bgColor!=''">
															<img alt="${mf.functionName}" width="190px"
																height="140px" style="background-color:${mf.bgColor}"
																src="<%=basePath%>upload/file/sysImages/${mf.imageName}">
														</s:if>
														<s:else>
															<img alt="${mf.functionName}" width="190px"
																height="140px"
																src="<%=basePath%>upload/file/sysImages/${mf.imageName}">
														</s:else>
													</div>
												</div>
											</div>
											<div
												style="padding-left: 10px; margin-bottom: 20px; font: 9px;"
												align="left">
											</div>
										</td>
										<s:if test="(#pageId.index+1) % 4 == 0">
											</tr>
										</s:if>
									</s:iterator>
								</table>
							</div>
						</div>
					</div>
					<%@include file="/util/foot.jsp"%>
		</center>
	</body>


</html>
