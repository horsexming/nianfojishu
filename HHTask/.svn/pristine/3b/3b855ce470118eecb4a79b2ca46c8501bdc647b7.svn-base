<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			.table{
				border:0px solid #999;
				border-width: 1px;
				border-collapse:collapse;
			}
			.table th,.table td {
				border-width: 1px;
				padding: 0px;
			}
			
			.subTable{
				text-align: center;
				border-collapse:collapse;
				width: 100%;
				border-width:1px; 
				border-style:hidden;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					
				</div>
			</div>
			
			<div align="center">
				<form action="processDataAction!updateProcessData.action" method="post" enctype="multipart/form-data" style="">
					<input type="hidden" id="id" name="processData.id" value="${processData.id}"/>
					<table border="0" width="100%" class="table" style="text-align: center;">
						<tr><td>设备</td><td><input type="text" id="shebei" name="processData.shebei" value="${processData.shebei}"/></td></tr>
						<tr><td>基准</td><td><input type="text" id="jizhun" name="processData.jizhun" value="${processData.jizhun}"/></td></tr>
						<tr><td>夹\模具</td><td><input type="text" id="jiaOrMoju" name="processData.jiaOrMoju" value="${processData.jiaOrMoju}"/></td></tr>
						<tr><td>索引号</td><td><input type="text" id="suoyinNumb" name="processData.suoyinNumb" value="${processData.suoyinNumb}"/></td></tr>
						<tr><td>更改单号</td><td><input type="text" id="danNumb" name="processData.danNumb" value="${processData.danNumb}"/></td></tr>
						<tr><td>签名</td><td><input type="text" id="qianming" name="processData.qianming" value="${processData.qianming}"/></td></tr>
						<tr><td>日期</td><td>
							<input id="qianmingDate" class="Wdate" type="text" name="processData.qianmingDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" value="<s:date name="processData.qianmingDate"  format="yyyy-MM-dd"/>" />
						</td></tr>
						<tr>
							<td>工序图片</td>
							<td><input type="file" name="fileList"/></td>
						</tr>
						<tr>
							<td>视频</td>
							<td><input type="file" name="fileList"/></td>
						</tr>
						<tr>
							<td>工序图片预览</td>
							<td><img src="${pageContext.request.contextPath}/${processData.processImg}" style="width: 100px;height: 100px;"/></td>
						</tr>
						<tr>
							<td>视频预览</td>
							<td>
								<video loop="true" id="example_video_1" class="video-js vjs-default-skin" controls="controls" preload="none" width="100px;" height="100px;" poster="${pageContext.request.contextPath }/images/mk/oceans-clip.png" data-setup="{}">
						    		<source src="${pageContext.request.contextPath}/${processData.processVideo}" type='video/${fn:substring(processData.processVideo,fn:length(processData.processVideo)-3,fn:length(processData.processVideo))}' />
						    		<track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
						    		<track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
						  		</video>
					  		</td>
						</tr>
						
						<tr>
							<td align="center" colspan="2" >
								<input type="submit" value="修改"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function(){
	
});
</script>
