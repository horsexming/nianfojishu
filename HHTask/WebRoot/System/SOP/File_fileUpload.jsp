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
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="File_save.action"  method="post" enctype="multipart/form-data" >
					<input type="hidden" name="file.fid" value="${file.fid}">
					<input type="hidden" name="file.mid" value="${file.mid}">
					<table class="table" style="width: 50%">
						<tr>
							<th colspan="2">设置规格要求</th>
						</tr>
						<tr>
							<td align="right">信息标题:</td>
							<td><input name="file.title" type="text" /></td>
						</tr>
						<tr>
							<td align="right">类别:</td>
							<td>
								<select name="file.classs" >
									<option value="数模">数模</option>
									<option value="文档">文档</option>
									<option value="图纸">图纸</option>
									<option value="新类别">新类别</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">描述:</td>
							<td><textarea name="file.des"  rows="5" cols="20"></textarea> </td>
						</tr>
						<tr>
							<td align="right">文档上传:</td>
							<td><input type="button" onclick="addFileUpload();" value="添加" /> </td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="fileUploadDiv"></div>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="提交" >
								<input type="reset" value="清空" >
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
			<div>
				<table class="table">
					<tr>
						<th>信息标题</th>
						<th>信息类别</th>
						<th>文字信息</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
					
					<s:iterator id="z" value="files">
						<tr>
							<td>${z.title}</td>
							<td>${z.classs}</td>
							<td>${z.des}</td>
							<td>${z.creattime}</td>
							<td>
								<a href="upload/guige/${z.url}">查看文件</a>
								<a href="File_delete.action?file.mid=${z.mid}&file.fid=${z.fid}&file.id=${z.id}" >删除</a> 
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<script type="text/javascript">
			function addFileUpload(){
				var fileInputs = document.getElementsByName("imgpath");
				var fileDiv = document.getElementById("fileUploadDiv");
				var fileUploadInput = document.createElement("input");
				var link =  document.createElement("a");
				var br =  document.createElement("br");
				
				fileUploadInput.setAttribute("type","file");
				fileUploadInput.setAttribute("name","imgpath");
				fileUploadInput.setAttribute("id","file" + (fileInputs.length == null?0: fileInputs.length));
				link.setAttribute("onclick","javascript:deleteFileUpload(" + (fileInputs.length == null?0: fileInputs.length) + ");");
				link.innerHTML = '删除';
				
				fileDiv.appendChild(fileUploadInput);
				fileDiv.appendChild(link);
				fileDiv.appendChild(br);
			}
			
			function deleteFileUpload(index, link){
				var fileDiv = document.getElementById("fileUploadDiv");
				var fileUploadInputs = fileDiv.getElementsByTagName("input");
				var links = fileDiv.getElementsByTagName("a");
				var brs = fileDiv.getElementsByTagName("br");
				fileDiv.removeChild(fileUploadInputs[fileUploadInputs.length-1]);
				fileDiv.removeChild(links[links.length-1]);
				fileDiv.removeChild(brs[brs.length-1]);
			}
			
			
		</script>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
