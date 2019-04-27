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
			<br />
			<br />
			<div align="center">
				请先选择文件,再点击"开始导入".
				<s:if test="pageStatus == 'sop'">
					<form action="procardTemplateGyAction_daoRuHwSZBom.action" method="post"
					enctype="multipart/form-data" onsubmit="return validate();">
				</s:if>
				<s:else>
					<form action="procardTemplateGyAction_daoRuBom.action" method="post"
					enctype="multipart/form-data" onsubmit="return validate();">
				</s:else>
					<input type="file" name="bomTree">
					<input id="scbtn" type="submit" value="开始导入"
						style="width: 80px; height: 40px;">
					<br />
					<label id="lb" style="display: none; color: red">
						正在导入中,请耐心等待......
					</label>
				</form>
			</div>
			<br />
			<div align="center">
				<input type="button" value="导入模板下载" class="input"
					style="width: 100px;height: 30px;" onclick="downLoadMb()">
				<input type="button" value="导入案列下载" class="input"
					style="width: 100px;height: 30px;" onclick="downLoadMb2()">
			</div>
			<br />
			<div align="center">
				<font color="red">注：1.导入文件版本为excle2003版本</font>
			</div>
			<div align="center">
				<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.导入文件后缀名为.xls</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<s:if test="successMessage!=null">
			<div align="left" style="color: red;">
				${successMessage}
			</div>
		</s:if>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function validate(){
			$("#scbtn").attr("disabled","disabled");
			$("#lb").show();
		}
		function downLoadMb(){
	window.location.href="DownAction.action?fileName=BOMmoban.xls&directory=/upload/file/download/";
<%--	window.location.href="FileViewAction.action?FilePath=/upload/file/download/BOMmoban.xls&Refresh=true";--%>
}
		function downLoadMb2(){
	window.location.href="DownAction.action?fileName=ASBOM.xls&directory=/upload/file/download/";
<%--	window.location.href="FileViewAction.action?FilePath=/upload/file/download/ASBOM.xls&Refresh=true";--%>
}
		</SCRIPT>
	</body>
</html>
