<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<br/>
			<h2 style="font-size: x-large;">修改SPC 群数组大小</h2>
			<form action="SpcControlAction_updatespcGroups.action" method="post">
				<table class="table" style="width: 50%;">
					<tr>
						<th>
							群数组大小（n）
						</th>
						<th>
							A2
						</th>
						<th>
							D4
						</th>
						<th>
							D3
						</th>
					</tr>
					<s:iterator value="spcgroupsList" id="pagesg" status="statussds">
								<tr>
									<th class="th_1">
										<input type="text" value="${pagesg.gropsSize}" style="width: 50px;" name="spcgroupsList[${statussds.index}].gropsSize" />
										<input type="hidden" value="${pagesg.id}" name="spcgroupsList[${statussds.index}].id"  style="width: 50px;" />
									</th>
									<th>
										<input type="text" value="${pagesg.a2}" name="spcgroupsList[${statussds.index}].a2"  style="width: 50px;" />
									</th>
									<th>
										<input type="text" value="${pagesg.d4}" name="spcgroupsList[${statussds.index}].d4" style="width: 50px;"  />
									</th>
									<th>
										<input type="text" value="${pagesg.d3}" name="spcgroupsList[${statussds.index}].d3"  style="width: 50px;" />
									</th>
								</tr>
					</s:iterator>
				</table>
				<input type="submit" value="修改" class="input"/>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if('${errorMessage}' == 'true'){
		alert('修改成功!');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
</SCRIPT>
	</body>
</html>
