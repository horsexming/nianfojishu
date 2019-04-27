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


		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/uploadify/uploadify.css">
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="test" style="position: absolute; right: 5px; top: 10px;">
					<canvas id="myCanvas" width="200" height="100"></canvas>
				</div>
				<form action="procardTemplateSbAction_updateProcardTz.action"
					enctype="multipart/form-data" method="post">
					<table class="table">
						<s:if test="pageStatus!='noUpload'&&pageStatus!='view'">
							<tr>
								<td align="left">
									<h3>
										工艺规程文件
									</h3>
								</td>
								<td align="left">
									<input type="file" value="上传" id="file_upload"
										name="file_upload">
									<!-- <input type="button" id="fileButton_1"
										onclick="uploadFile(this,1)" value="上传附件"> -->
									<div id="fileDiv_1" style="display: none;">
									</div>
<%--									下发到工序：--%>
<%--									<input type="radio" checked="checked" name="ytRadio" id="noxf" value="yes">--%>
<%--									是--%>
<%--									<input type="radio" name="ytRadio" id="yesxf" value='no'--%>
<%--										${procardTemplate.procardStyle== "外购"?'checked=checked':""}>--%>
<%--									否--%>
								</td>
								<td align="left" colspan="3">
									<input type="hidden" name="id" value="${id}" id="id">
									<input type="hidden" name="pageStatus" value="card">
									<select name="processTemplateFile.type" id="type" onchange="changetzType()">
										<option>
											工艺规范
										</option>
										<option>
											视频文件
										</option>
										<option>
											3D文件
										</option>
										<option>
											3D模型
										</option>
										<option>
											SOP文件
										</option>
										<option>
											SIP文件
										</option>
										<option>
											成型图
										</option>
										<option>
											NC数冲
										</option>
										<option>
											镭射
										</option>
										<option>
											标签文件
										</option>
										<option>
											其它文件
										</option>
									</select>
									<!-- <input type="submit" value="上传"
										style="width: 50px; height: 20px;"> -->
									<%--									<a href="javascript:$('#file_upload').uploadify('upload','*')">上传</a>--%>
									<input type="button" value="上传附件"
										onclick="javascript:$('#file_upload').uploadify('upload','*')" />

								</td>
							</tr>
						</s:if>
						<tr id="spTr">
							<td colspan="5" align="left">
								<h3>
									视频
									<font color="red">生产工序操作的视频</font>
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="spFile">
							<s:if test="#spFile.type=='视频文件'">
								<tr>
									<td align="left">
										<video
											src="<%=path%>/upload/file/processTz/${spFile.month}/<s:property value="#spFile.fileName"/>"
											controls="controls">
										您的浏览器不支持 video 标签。
										</video>
									</td>
									<td align="left">
										<s:property value="#spFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#spFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#spFile.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${spFile.id}">下载</a>
											<s:if test="pageStatus!='view'&&#spFile.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${spFile.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${spFile.fileName.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="3DTr">
							<td colspan="5" align="left">
								<h3>
									3D文件
									<font color="red">格式为.3dxml的专用格式，可在系统中直接浏览</font>
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="file3D">
							<s:if test="#file3D.type=='3D文件'">
								<tr>
									<td align="left">
										<s:if test="#file3D.fileName.indexOf('.3dxml')>0 ">
											<a target="_show3D"
												href="<%=path%>/show3Dfile.jsp?filePath=<%=path%>/upload/file/processTz/${file3D.month}/${file3D.fileName}"><img
													src="<%=path%>/upload/file/processTz/${file3D.month}/<s:property value="#file3D.fileName"/>"
													style="width: 80px; height: 80px;" />
											</a>
										</s:if>
										<s:else>
											<a
												href="<%=path%>/FileViewAction.action?FilePath=/upload/file/processTz/${file3D.month}/<s:property value="#file3D.fileName"/>&Rename=<s:property value="#file3D.oldfileName"/>">
<%--												<img--%>
<%--													src="<%=path%>/upload/file/processTz/${file3D.month}/<s:property value="#file3D.fileName"/>"--%>
<%--													style="width: 80px; height: 80px;" /> --%>
													<s:property value="#file3D.fileName"/>
											</a>
										</s:else>
									</td>
									<td align="left">
										<s:property value="#file3D.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#file3D.addTime" />
									</td>
									<td align="left">
										<s:property value="#file3D.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${file3D.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#file3D.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${file3D.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${file3D.id}&&id=${param.id}&&pageStatus=card" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="gfTr">
							<td colspan="5" align="left">
								<h3>
									工艺规范
									<font color="red">工序图纸、图片格式。用于推送至采购、生产、检验各个部门。</font>
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="tzFile">
							<s:if test="#tzFile.type=='工艺规范'">
								<tr>
									<td align="left">
										<a target="_showPri"
											<%--											href="<%=path%>/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>">--%>
											href="<%=path%>/FileViewAction.action?FilePath=/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>&Rename=<s:property value="#tzFile.oldfileName"/>">

<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" />--%>
												<s:property value="#tzFile.fileName"/>
												 </a>
									</td>
									<td align="left">
										<s:property value="#tzFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#tzFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#tzFile.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${tzFile.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#tzFile.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${tzFile.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${tzFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="sopTr">
							<td colspan="5" align="left">
								<h3>
									SOP文件
									<font color="red">产品加工操作指导书，最好转为图片格式</font>
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="sopFile">
							<s:if test="#sopFile.type=='SOP文件'">
								<tr>
									<td align="left">
										<a target="_showPri"
											href="<%=path%>/upload/file/processTz/${sopFile.month}/<s:property value="#sopFile.fileName"/>">
<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${sopFile.month}/<s:property value="#sopFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" /> --%>
												<s:property value="#sopFile.fileName"/>
												</a>
									</td>
									<td align="left">
										<s:property value="#sopFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#sopFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#sopFile.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${sopFile.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#sopFile.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${sopFile.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${tzFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="sapTr">
							<td colspan="5" align="left">
								<h3>
									SIP文件
									<font color="red">质量要求、要点等。最好转为图片格式</font>
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="sapFile">
							<s:if test="#sapFile.type=='SIP文件'">
								<tr>
									<td align="left">
										<a target="_showPri"
											href="<%=path%>/upload/file/processTz/${sapFile.month}/<s:property value="#sapFile.fileName"/>">
<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${sapFile.month}/<s:property value="#sapFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" /> --%>
												<s:property value="#sapFile.fileName"/>
												</a>
									</td>
									<td align="left">
										<s:property value="#sapFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#sapFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#sapFile.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${sapFile.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#sapFile.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${sapFile.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${sapFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>

						<tr id="mxTr">
							<td colspan="5" align="left">
								<h3>
									3D模型
									<font color="red">产品的模型文件，作为内部产品质料统一管理、下载等作用 如 Pro/e CAD
										等产品设计源文件</font>
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="modelFile">
							<s:if test="#modelFile.type=='3D模型'">
								<tr>
									<td align="left">
										<a
											href="<%=path%>/upload/file/processTz/${modelFile.month}/<s:property value="#modelFile.fileName"/>">
<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${modelFile.month}/<s:property value="#modelFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" /> --%>
												<s:property value="#modelFile.fileName"/></a>
									</td>
									<td align="left">
										<s:property value="#modelFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#modelFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#modelFile.userName" />
									</td>
									<td>
										<%--										<a--%>
										<%--											href="procardTemplateSbAction_DownloadProcessTz.action?id=${modelFile.id}">下载</a>&nbsp;&nbsp;--%>
										<%--										<s:if test="pageStatus!='view'">--%>
										<%--											<input type="button"--%>
										<%--												onclick="deletetz(${modelFile.id},${param.id})" value="删除" />--%>
<%--										<a--%>
<%--											href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}"--%>
<%--											onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
									</td>
									<%--										</s:if>--%>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="cxTr">
							<td colspan="5" align="left">
								<h3>
									成型图
									<font color="red">产品生产出来后的模型图或拍照 图片格式</font>
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="cxFile">
							<s:if test="#cxFile.type=='成型图'">
								<tr>
									<td align="left">
										<a target="_showPri"
											<%--											href="<%=path%>/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>">--%>
											href="<%=path%>/FileViewAction.action?FilePath=/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>&Rename=<s:property value="#cxFile.oldfileName"/>">
<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" /> --%>
												
												<s:property value="#cxFile.fileName"/></a>
									</td>
									<td align="left">
										<s:property value="#cxFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#cxFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#cxFile.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${cxFile.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#cxFile.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${cxFile.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="ncTr">
							<td colspan="5" align="left">
								<h3>
									NC数冲
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="ncFile">
							<s:if test="#ncFile.type=='NC数冲'">
								<tr>
									<td align="left">
										<a target="_showPri"
											<%--											href="<%=path%>/upload/file/processTz/${ncFile.month}/<s:property value="#ncFile.fileName"/>">--%>
											href="<%=path%>/FileViewAction.action?FilePath=/upload/file/processTz/${ncFile.month}/<s:property value="#ncFile.fileName"/>&Rename=<s:property value="#ncFile.oldfileName"/>">
<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${ncFile.month}/<s:property value="#ncFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" /> --%>
												
												<s:property value="#ncFile.fileName"/></a>
									</td>
									<td align="left">
										<s:property value="#ncFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#ncFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#ncFile.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${ncFile.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#ncFile.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${ncFile.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="lsTr">
							<td colspan="5" align="left">
								<h3>
									镭射
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="lsFile">
							<s:if test="#lsFile.type=='镭射'">
								<tr>
									<td align="left">
										<a target="_showPri"
											<%--											href="<%=path%>/upload/file/processTz/${lsFile.month}/<s:property value="#lsFile.fileName"/>">--%>
											href="<%=path%>/FileViewAction.action?FilePath=/upload/file/processTz/${lsFile.month}/<s:property value="#lsFile.fileName"/>&Rename=<s:property value="#lsFile.oldfileName"/>">
<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${lsFile.month}/<s:property value="#lsFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" /> --%>
												
												<s:property value="#lsFile.fileName"/></a>
									</td>
									<td align="left">
										<s:property value="#lsFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#lsFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#lsFile.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${lsFile.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#lsFile.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${lsFile.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="bqwjTr">
							<td colspan="5" align="left">
								<h3>
									标签文件
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="bqwjFile">
							<s:if test="#bqwjFile.type=='标签文件'">
								<tr>
									<td align="left">
										<a target="_showPri"
											<%--											href="<%=path%>/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>">--%>
											href="<%=path%>/FileViewAction.action?FilePath=/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>&Rename=<s:property value="#bqwjFile.oldfileName"/>">
<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" /> --%>
												
												<s:property value="#bqwjFile.fileName"/></a>
									</td>
									<td align="left">
										<s:property value="#bqwjFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#bqwjFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#bqwjFile.userName" />
									</td>
									<td>
										<s:if test="tag==null||tag!='noxz'">
											<a
												href="procardTemplateSbAction_DownloadProcessTz.action?id=${bqwjFile.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#bqwjFile.canChange=='yes'">
												<input type="button"
													onclick="deletetz(${bqwjFile.id},${param.id})" value="删除" />
												<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
											</s:if>
										</s:if>
								</tr>
							</s:if>
						</s:iterator>
						<tr id="qtTr">
							<td colspan="5" align="left">
								<h3>
									其它文件
								</h3>
							</td>
						</tr>
						<s:iterator value="list" id="qtFile">
							<s:if test="#qtFile.type=='其它文件'">
								<tr>
									<td align="left">
										<a
											href="<%=path%>/upload/file/processTz/${qtFile.month}/<s:property value="#qtFile.fileName"/>">
											<%--											href="<%=path%>/FileViewAction.action?FilePath=/upload/file/processTz/${qtFile.month}/<s:property value="#qtFile.fileName"/>">--%>

<%--											<img--%>
<%--												src="<%=path%>/upload/file/processTz/${qtFile.month}/<s:property value="#qtFile.fileName"/>"--%>
<%--												style="width: 80px; height: 80px;" /> --%>
												
												<s:property value="#qtFile.fileName"/></a>
									</td>
									<td align="left">
										<s:property value="#qtFile.oldfileName" />
									</td>
									<td align="left">
										<s:property value="#qtFile.addTime" />
									</td>
									<td align="left">
										<s:property value="#qtFile.userName" />
									</td>
									<td>
										<a
											href="procardTemplateSbAction_DownloadProcessTz.action?id=${qtFile.id}">下载</a>&nbsp;&nbsp;
										<s:if test="pageStatus!='view'&&#qtFile.canChange=='yes'">
											<input type="button"
												onclick="deletetz(${qtFile.id},${param.id})" value="删除" />
											<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
										</s:if>
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
window.onscroll = function() {
	var oDiv = document.getElementById("test");
	oDiv.style.top = document.body.scrollTop + 10; //控制上下位置
}
$(function() {
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	ctx.strokeStyle = 'red';
	ctx.font = "30px Arial";
	ctx.strokeText("${Users.code} ${Users.name}", 10, 50);
})
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
function deletetz(fileId, id) {
	if (window.confirm("确认要删除该条记录?")) {
		window.location.href = "procardTemplateSbAction_deletesbBomTz.action?processTemplateFile.id="
				+ fileId + "&pageStatus=card&id=" + id + "&type=${tag}"
	}

}
</script>

		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.9.1.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/uploadify/jquery.uploadify.min.js">
</script>
		<script type="text/javascript">
$(function() {
	$('#file_upload')
			.uploadify(
					{

						'method' : 'post',
						'swf' : '${pageContext.request.contextPath}/javascript/uploadify/uploadify.swf',
						'uploader' : 'procardTemplateSbAction_updateProcardTz.action;jsessionid=<%=session.getId()%>',//请求路径  
						'auto' : false, //是否自动上传 
						'debug' : false,
						'fileObjName' : 'file_upload',//和input的name属性值保持一致就好，Struts2就能处理了
						'buttonText' : '选择附件',//按钮上的文字
						'onUploadStart' : function(file) {
							var typeval = document.getElementById('type').value;
							var idval = document.getElementById('id').value;
							var obj = {
								'processTemplateFile.type' : typeval,
								'id' : idval,
								tag : '${tag}'
							};
							$("#file_upload").uploadify("settings", "formData",
									obj);
						}

					/*'onUploadStart' : function(file) {
					    
					    $("#file_upload").uploadify("settings","formData",obj );
					}*/
					/*
					'onFallback':function(){    
					        alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");    
					    }, 
					    'onUploadSuccess' : function(file, data, response){//单个文件上传成功触发  
					                           //data就是action中返回来的数据  
					    },'onQueueComplete' : function(){//所有文件上传完成  
					    	alert("文件上传成功!");
					   		}  
					 */
					// Put your options here
					});
});
function changetzType(){
	var type =$("#type").val();
	if(type=="工艺规范"){
		var procardStyle="${procardTemplate.procardStyle}";
		if(procardStyle!="外购"){
		 $(":radio[name='ytRadio'][value='yes']").prop("checked", "checked");
		}
	}
}
</script>
	</body>
</html>
