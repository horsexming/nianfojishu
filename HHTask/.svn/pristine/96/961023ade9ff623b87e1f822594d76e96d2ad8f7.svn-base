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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>进度纪要文件</h3>
				<div align="left"><font size="4">纪要内容:${qpjyd.jyContext}</font>
				<br/>
				</div>
				<div align="left"><font size="4">执行方案:${qpjyd.zxLog}</font>
				</div>
				<div align="left"><font size="4">审批意见:${qpjyd.spMsg}</font>
				</div>
				<s:if test="#session.Users.id==qpjyd.addUserId">
				<div align="center">
					<form action="QuotedPrice_uploadJymsFile.action"  enctype="multipart/form-data" method="post">
						<input type="hidden" value="${qpjyd.id}" name="id">
						描述文件:&nbsp;&nbsp;<input type="file" value="选择文件" id="accessory" name="accessory">&nbsp;&nbsp;
						<input type="submit" value="上传">
					</form>
				</div>
				</s:if>
				<table class="table">
					<tr>
						<td align="center">文件</td>
						<td align="center">文件名称</td>
						<td align="center">上传日期</td>
						<td align="center">操作</td>
					</tr>
					<tr>
						<td align="center" colspan="4" bgcolor="red">描述文件</td>
					</tr>
					<s:iterator value="qpjydFileList" id="pagejyFile">
					<s:if test="#pagejyFile.type=='描述文件'">
					<tr>
						<td align="center"><a
									href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/jy/<s:property value="#pagejyFile.fileName"/>">
									<img
										src="<%=path%>/upload/file/jy/<s:property value="#pagejyFile.fileName"/>"
										style="width: 80px; height: 80px;" />
								</a></td>
						<td align="center"><s:property value="#pagejyFile.oldFileName"/></td>
						<td align="center"><s:property value="#pagejyFile.addTime"/></td>
						<td align="center">
						<a href="QuotedPrice_DownloadjydFile.action?id=<s:property value="#pagejyFile.id"/>">下载</a>
						<s:if test="#session.Users.id==#pagejyFile.userId">
						<a href="javascript:void(0);" onclick="deletejyFile(<s:property value="#pagejyFile.id"/>)">删除</a>
						</s:if>
						</td>
					</tr>
					</s:if>
					</s:iterator>
					<tr>
						<td align="center" colspan="4" bgcolor="red">执行文件</td>
					</tr>
					<s:iterator value="qpjydFileList" id="pagejyFile">
					<s:if test="#pagejyFile.type=='执行文件'">
					<tr>
						<td align="center"><a
									href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/jy/<s:property value="#pagejyFile.fileName"/>">
									<img
										src="<%=path%>/upload/file/jy/<s:property value="#pagejyFile.fileName"/>"
										style="width: 80px; height: 80px;" />
								</a></td>
						<td align="center"><s:property value="#pagejyFile.oldFileName"/></td>
						<td align="center"><s:property value="#pagejyFile.addTime"/></td>
						<td align="center">
						<a href="QuotedPrice_DownloadjydFile.action?id=<s:property value="#pagejyFile.id"/>">下载</a>
						<s:if test="#session.Users.id==#pagejyFile.userId">
						<a href="javascript:void(0);" onclick="deletejyFile(<s:property value="#pagejyFile.id"/>)">删除</a>
						</s:if>
						</td>
					</tr>
					</s:if>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function deletejyFile(id){
	if(confirm("是否确定删除?")){
		window.location.href="QuotedPrice_deletejyFile.action?data=1&id="+id;
	}
}
</SCRIPT>
	</body>
</html>
