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
				<div id="test" style="position: absolute;right: 5px;top: 10px;">
					<canvas id="myCanvas" width="200" height="100"></canvas>
				</div>
				<form action="procardTemplateSbAction_updateProcessTz.action" enctype="multipart/form-data" method="post">  
				<table class="table">
				<s:if test="pageStatus!='noUpload'&&pageStatus!='view'">
				<tr>
				 <td align="left"><h3>工艺规程文件</h3>
				 </td>
				 <td align="left">
				 <input type="button" id="fileButton_1"
											onclick="uploadFile(this,1)" value="上传附件">

										<div id="fileDiv_1" style="display: none;">

										</div>
				 </td>
				 <td align="left" colspan="3">
				 <input type="hidden" name="id" value="${id}">
				 <select  name="processTemplateFile.type" id="type">
				   <option>工艺规范</option>
				   <option>SOP文件</option>
				   <option>SIP文件</option>
				   <option>视频文件</option>
				   <option>3D文件</option>
				   <option>3D模型</option>
				   <option>缺陷图纸</option>
				   <option>成型图</option>
				   <option>NC数冲</option>
				   <option>镭射</option>
				   <option>标签文件</option>
				   <option>其它</option>
				 </select>
				 <input type="submit" value="上传" style="width: 50px;height: 20px;">
				 </td>
				</tr>
				</s:if>
				<tr id="spTr">
				 <td colspan="5" align="left">
				 <h3>视频</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="spFile">
				 <s:if test="#spFile.type=='视频文件'">
				  <tr>
				   <td align="left"> <video src="<%=path%>/upload/file/processTz/${spFile.month}/<s:property value="#spFile.fileName"/>" controls="controls">您的浏览器不支持 video 标签。</video> </td>
				   <td align="left"><s:property value="#spFile.oldfileName"/></td>
				   <td align="left"><s:property value="#spFile.addTime"/></td>
				   <td align="left"><s:property value="#spFile.userName"/></td>				   
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${spFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#spFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${spFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${spFile.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				</s:iterator>
				<tr id="qxTr">
				 <td colspan="5" align="left">
				 <h3>缺陷图纸</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="qxFile">
				 	<s:if test="#qxFile.type=='缺陷图纸'">
						<tr>
							<td align="left">
								<a
<%--									href="<%=path%>/upload/file/processTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>">--%>
									href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/processTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>&Rename=<s:property value="#qxFile.oldfileName"/>">
									<img
										src="<%=path%>/upload/file/processTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>"
										style="width: 80px; height: 80px;" />
								</a>
							</td>
							<td align="left">
								${oldfileName}
							</td>
							<td>
								<%--<a
									href="DownAction.action?fileName=${qxFile.fileName}&directory=/upload/file/processTz/${qxFile.month}/">下载</a>&nbsp;&nbsp;
								--%><s:if test="pageStatus!='view'&&#qxFile.canChange=='yes'">
								 <input type="button" onclick="deletetz(${qxFile.id},${param.id})" value="删除"/>
<%--								<a--%>
<%--									href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${qxFile.id}&&id=${param.id}&&pageStatus=${pageStatus}"--%>
<%--									onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
								</s:if>
								</td>
							</td>
						</tr>
					</s:if>
				</s:iterator>
				<tr id="3DTr">
				 <td colspan="5" align="left">
				 <h3>3D文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="file3D">
				 <s:if test="#file3D.type=='3D文件'">
				  <tr>
				   <td align="left"> <a href="<%=path%>/upload/file/processTz/${file3D.month}/<s:property value="#file3D.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${file3D.month}/<s:property value="#file3D.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left"><s:property value="#file3D.oldfileName"/></td>
				   <td align="left"><s:property value="#file3D.addTime"/></td>
				   <td align="left"><s:property value="#file3D.userName"/></td>
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${file3D.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#file3D.canChange=='yes'">
				   <input type="button" onclick="deletetz(${file3D.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${file3D.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
					</s:if>
					</td>
				  </tr>
				 </s:if>
				</s:iterator>
				<tr id="gfTr">
				 <td colspan="5" align="left">
				 <h3>工艺规范</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="tzFile">
				 <s:if test="#tzFile.type=='工艺规范'">
				  <tr>
<%--				   <td align="left"><a  target="_showPri" href="<%=path%>/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a  target="_showPri" href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>&Rename=<s:property value="#tzFile.oldfileName"/>"> <img src="<%=path%>/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left">${oldfileName}</td>
				   <td align="left"><s:property value="#tzFile.addTime"/></td>
				   <td align="left"><s:property value="#tzFile.userName"/></td>
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${tzFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#tzFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${tzFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${tzFile.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				<tr id="sopTr">
				 <td colspan="5" align="left">
				 <h3>SOP文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="sopFile">
				 <s:if test="#sopFile.type=='SOP文件'">
				  <tr>
				   <td align="left"><a  target="_showPri" href="<%=path%>/upload/file/processTz/${sopFile.month}/<s:property value="#sopFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${sopFile.month}/<s:property value="#sopFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left">${oldfileName}</td>
				   <td align="left"><s:property value="#sopFile.addTime"/></td>
				   <td align="left"><s:property value="#sopFile.userName"/></td>
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${sopFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#sopFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${sopFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${sopFile.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				<tr id="sapTr">
				 <td colspan="5" align="left">
				 <h3>SIP文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="sapFile">
				 <s:if test="#sapFile.type=='SIP文件'">
				  <tr>
				   <td align="left"><a  target="_showPri" href="<%=path%>/upload/file/processTz/${sapFile.month}/<s:property value="#sapFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${sapFile.month}/<s:property value="#sapFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left">${oldfileName}</td>
				   <td align="left"><s:property value="#sapFile.addTime"/></td>
				   <td align="left"><s:property value="#sapFile.userName"/></td>
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${sapFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#sapFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${sapFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${sapFile.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				 <tr id="mxTr">
				 <td colspan="5" align="left">
				 <h3>3D模型</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="modelFile">
				 <s:if test="#modelFile.type=='3D模型'">
				  <tr>
				   <td align="left"><a href="<%=path%>/upload/file/processTz/${modelFile.month}/<s:property value="#modelFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${modelFile.month}/<s:property value="#modelFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left"><s:property value="#modelFile.oldfileName"/></td>
				   <td align="left"><s:property value="#modelFile.addTime"/></td>
				   <td align="left"><s:property value="#modelFile.userName"/></td>
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${modelFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#modelFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${modelFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				 <tr id="cxTr">
				 <td colspan="5" align="left">
				 <h3>成型图</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="cxFile">
				 <s:if test="#cxFile.type=='成型图'">
				  <tr>
<%--				   <td align="left"><a href="<%=path%>/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>&Rename=<s:property value="#cxFile.oldfileName"/>"> <img src="<%=path%>/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left"><s:property value="#cxFile.oldfileName"/></td>
				   <td align="left"><s:property value="#cxFile.addTime"/></td>
				   <td align="left"><s:property value="#cxFile.userName"/></td>
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${cxFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#cxFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${cxFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				 <tr id="ncTr">
				 <td colspan="5" align="left">
				 <h3>NC数冲</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="ncFile">
				 <s:if test="#ncFile.type=='NC数冲'">
				  <tr>
<%--				   <td align="left"><a href="<%=path%>/upload/file/processTz/${ncFile.month}/<s:property value="#ncFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${ncFile.month}/<s:property value="#ncFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/processTz/${ncFile.month}/<s:property value="#ncFile.fileName"/>&Rename=<s:property value="#ncFile.oldfileName"/>"> <img src="<%=path%>/upload/file/processTz/${ncFile.month}/<s:property value="#ncFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left"><s:property value="#ncFile.oldfileName"/></td>
				   <td align="left"><s:property value="#ncFile.addTime"/></td>
				   <td align="left"><s:property value="#ncFile.userName"/></td>
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${ncFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#ncFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${ncFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				 <tr id="lsTr">
				 <td colspan="5" align="left">
				 <h3>镭射</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="lsFile">
				 <s:if test="#lsFile.type=='镭射'">
				  <tr>
<%--				   <td align="left"><a href="<%=path%>/upload/file/processTz/${lsFile.month}/<s:property value="#lsFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${lsFile.month}/<s:property value="#lsFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/processTz/${lsFile.month}/<s:property value="#lsFile.fileName"/>&Rename=<s:property value="#lsFile.oldfileName"/>"> <img src="<%=path%>/upload/file/processTz/${lsFile.month}/<s:property value="#lsFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left"><s:property value="#lsFile.oldfileName"/></td>
				   <td align="left"><s:property value="#lsFile.addTime"/></td>
				   <td align="left"><s:property value="#lsFile.userName"/></td>
				   <td><a href="procardTemplateSbAction_DownloadProcessTz.action?id=${lsFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#lsFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${lsFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				  <tr id="bqwjTr">
				 <td colspan="5" align="left">
				 <h3>标签文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="bqwjFile">
				 <s:if test="#bqwjFile.type=='标签文件'">
				  <tr>
<%--				   <td align="left"><a href="<%=path%>/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a href="<%=basePath%>FileViewAction.action?FilePath=/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>&Rename=<s:property value="#bqwjFile.oldfileName"/>"><s:property value="#bqwjFile.fileName"/></a> </td>
				   <td align="left"><s:property value="#bqwjFile.oldfileName"/></td>
				   <td align="left"><s:property value="#bqwjFile.addTime"/></td>
				   <td align="left"><s:property value="#bqwjFile.userName"/></td>
				   <td><a href="procardTemplateGyAction_DownloadProcessTz.action?id=${bqwjFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#bqwjFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${bqwjFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				  <tr id="qtwjTr">
				 <td colspan="5" align="left">
				 <h3>其它文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="qtwjFile">
				 <s:if test="#qtwjFile.type=='标签文件'">
				  <tr>
<%--				   <td align="left"><a href="<%=path%>/upload/file/processTz/${qtwjFile.month}/<s:property value="#qtwjFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${qtwjFile.month}/<s:property value="#qtwjFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a href="<%=basePath%>FileViewAction.action?FilePath=/upload/file/processTz/${qtwjFile.month}/<s:property value="#qtwjFile.fileName"/>&Rename=<s:property value="#qtwjFile.oldfileName"/>"><s:property value="#qtwjFile.fileName"/></a> </td>
				   <td align="left"><s:property value="#qtwjFile.oldfileName"/></td>
				   <td align="left"><s:property value="#qtwjFile.addTime"/></td>
				   <td align="left"><s:property value="#qtwjFile.userName"/></td>
				   <td><a href="procardTemplateGyAction_DownloadProcessTz.action?id=${qtwjFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'&&#qtwjFile.canChange=='yes'">
				    <input type="button" onclick="deletetz(${qtwjFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				 	</td>
				  </tr>
				 </s:if>
				 </s:iterator>
				</table>
				 </form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		var fileDivHTML = "";
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + "," + few + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}
function deletetz(fileId,id){
	if(window.confirm("确认要删除该条记录?")){
		window.location.href="procardTemplateSbAction_deletesbBomTz.action?processTemplateFile.id="+fileId+"&pageStatus=${pageStatus}&id="+id
	}
	
}
window.onscroll=function(){ 
	var oDiv=document.getElementById("test"); 
	oDiv.style.top=document.body.scrollTop + 10;  //控制上下位置
} 
$(function(){
	var c=document.getElementById("myCanvas");
	var ctx=c.getContext("2d");
 	ctx.strokeStyle = 'red';
	ctx.font="30px Arial";
	ctx.strokeText("${Users.code} ${Users.name}",10,50);
})
		</script>
	</body>
</html>
