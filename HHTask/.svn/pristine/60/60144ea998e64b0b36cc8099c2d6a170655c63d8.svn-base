<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<script type="text/javascript">
function chageDivBorder(obj) {
	obj.style.border = "4px solid #4984e8";
}
function redDivBorder(obj) {
	obj.style.border = "solid 4px #fff";
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<font color="#ffffff">${moduleFunction.functionName}功能管理</font>
			</div>
			
			<div style="padding-bottom: 10px; background-color: #fff;">
				<table style="margin-left: 20px;">
					<s:iterator id="mf" value="allModuleList" status="pageId">
						<s:if test="#pageId.index % 4 == 0">
							<tr>
						</s:if>
						<td style="width: 200px" align="left">
							<div
								style="border: solid 4px #fff; width: 194px; height: 124px; cursor: pointer; margin: 1px; font-weight: bolder;"
								onmouseover="chageDivBorder(this)"
								onmouseout="redDivBorder(this)"
								onclick="goPageByUrl('${mf.id}','${mf.targetNewPage}');"
								title="${mf.functionName}: ${mf.functionIntro}">
								<div align="left"
									style="border: solid 1px #fff; width: 192px; height: 122px;">
									<div align="left"
										style="border: solid 1px #0362A2; width: 190px; height: 120px; background-color: ${mf.bgColor}">
										<s:if test="mf.imageName!=''">
											<img alt="${mf.functionName}" width="150px" height="120px"
												src="<%=basePath%>upload/file/sysImages/${mf.imageName}">
										</s:if>
										<s:else>
											<div
												style="font-weight: bold; padding-left: 10px; padding-top: 10px; color: #ffffff;">
												${mf.functionName}
											</div>
											<hr>
											<div style="color: #000; font-size: 12px;">
												&nbsp;&nbsp;&nbsp;&nbsp;${mf.functionIntro}
											</div>
										</s:else>
									</div>
								</div>
							</div>
							<div
								style="font-size: 4px; padding-left: 10px; margin-bottom: 20px; color: #666666;"
								align="left">
							</div>
						</td>
						<s:if test="#id.index % 4 == 0">
							</tr>
						</s:if>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
