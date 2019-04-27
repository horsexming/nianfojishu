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
			<div align="center">
				<h3>返修单</h3>
				<br>
				<div align="left">
				单号:${preProduct.fxNumber}&nbsp;&nbsp;&nbsp;
				件号:${preProduct.markId}&nbsp;&nbsp;&nbsp;
				批次:${preProduct.selfCard}&nbsp;&nbsp;&nbsp;
				版本号:${preProduct.banbenNumber}&nbsp;&nbsp;&nbsp;
				数量:${preProduct.fxCount}&nbsp;&nbsp;&nbsp;
				单位:${preProduct.unit}&nbsp;&nbsp;&nbsp;				
				</div>
				<table class="table">
				<tr>
				<th>工序号
				</th>
				<th>工序名称
				</th>
				<th>可领数量
				</th>
				<th>已领数量
				</th>
				<th>提交数量
				</th>
				<th>领取时间
				</th>
				<th>提交时间
				</th>
				<th>领取人
				</th>
				<th>状态
				</th>
				</tr>
				<tr>
				<td align="center">${processInfor.processNO}
				</td>
				<td align="center">${processInfor.processName}
				</td>
				<td align="center">${processInfor.totalCount}
				</td>
				<td align="center">${processInfor.applyCount}
				</td>
				<td align="center">${processInfor.submmitCount}
				</td>
				<td align="center">${processInfor.firstApplyDate}
				</td>
				<td align="center">${processInfor.submitDate}
				</td>
				<td align="center">${processInfor.usernames}
				</td>
				<td align="center">${processInfor.status}
				</td>
				</tr>
				</table>
			</div>
			<div>
			<table class="table">
			<form action="procardTemplateGyAction_updatefxtz.action" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="id" value="${preProduct.id}">
			<tr>
			<td align="center">上传图纸
			</td>
			<td align="center" colspan="2"><input name="gygf" type="file" value="上传文件">  
			</td>
			<td align="center">
			<input type="submit" value="上传">
			</td>
			</tr>
			</form>
			<tr>
				<th>文件
				</th>
				<th>上传时间/上传人
				</th>
				<th>名称
				</th>
				<th>操作
				</th>
			</tr>
			<s:iterator value="prpFileList" id="pagefile">
			<tr>
			<td><a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/fxtz/<s:property value="#pagefile.fileName"/>">
				<img src="<%=path%>/upload/file/fxtz/<s:property value="#pagefile.fileName"/>"
										style="width: 80px; height: 80px;" />
				</a>
			</td>
			<td align="center">
			<s:property value="#pagefile.addTime"/>/<s:property value="#pagefile.addUser"/>
			</td>
			<td align="center">
			<s:property value="#pagefile.oldFileName"/>
			</td>
			<td align="center">
				<a href="procardTemplateGyAction_deletefxtz.action?id=${pagefile.id}">删除</a>
				<a href="DownAction.action?fileName=<s:property value="#pagefile.fileName"/>&directory=/upload/file/fxtz/">下载</a>
			</td>
			</tr>
			</s:iterator>
			</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
