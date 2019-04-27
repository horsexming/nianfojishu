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
		<center>
			<div style="width: 100%; height: 100%">
				<table class="table">
					<s:if test="tag=='lj'">
						<tr id="3DTr">
					 <td colspan="4" align="left">
					 <h3>3D文件</h3>
					 </td>
					</tr>
					<s:iterator value="list" id="file3D">
					 <s:if test="#file3D.type=='3D文件'">
					  <tr>
				 	  <td align="left"> <a href="<%=path%>/upload/file/processTz/${file3D.month}/<s:property value="#file3D.fileName"/>"><s:property value="#file3D.fileName"/></a> </td>
				 	  <td align="left"><s:property value="#file3D.oldfileName"/></td>
				 	  <td align="left"><s:property value="#file3D.addTime"/></td>
				 	  <td align="left"><s:property value="#file3D.userName"/></td>
<%--				 	  <td><a href="DownAction.action?fileName=${file3D.fileName}&directory=/upload/file/processTz/${file3D.month}/">下载</a>&nbsp;&nbsp;--%>
				 	 </tr>
				 </s:if>
				</s:iterator>
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
					<tr id="gfTr">
						<td colspan="4" align="left">
							<h3>
								工艺规范
							</h3>
						</td>
					</tr>
					<s:iterator value="list" id="tzFile">
						<s:if test="#tzFile.type=='工艺规范'">
							<tr>
								<td align="left">
									<a target="_showP"
										<%--										href="<%=path%>/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>">--%>
										href="FileViewAction.action?wmark=${procard.orderNumber}&FilePath=/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>">
										<%--										<img--%> <%--											src="<%=path%>/upload/file/processTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"--%>
										<%--											style="width: 80px; height: 80px;" /> --%>
										${tzFile.fileName} </a>
								</td>
								<td align="left"><s:property value="#tzFile.oldfileName"/></td>
				 	 			 <td align="left"><s:property value="#tzFile.addTime"/></td>
				 	 			 <td align="left"><s:property value="#tzFile.userName"/></td>
							</tr>
						</s:if>
					</s:iterator>
					<tr id="cxTr">
						<td colspan="4" align="left">
							<h3>
								成型图
							</h3>
						</td>
					</tr>
					<s:iterator value="list" id="cxFile">
						<s:if test="#cxFile.type=='成型图'">
							<tr>
								<td align="left">
									<a target="_showP"
										<%--										href="<%=path%>/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>">--%>
										href="FileViewAction.action?wmark=${procard.orderNumber}&FilePath=/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>">
										<%--										<img--%> <%--											src="<%=path%>/upload/file/processTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"--%>
										<%--											style="width: 80px; height: 80px;" /> --%>
										${cxFile.fileName} </a>
								</td>
								<td align="left"><s:property value="#cxFile.oldfileName"/></td>
				 				 <td align="left"><s:property value="#cxFile.addTime"/></td>
				 				 <td align="left"><s:property value="#cxFile.userName"/></td>
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
				   <td align="left"><a href="<%=basePath%>FileViewAction.action?FilePath=/upload/file/processTz/${ncFile.month}/<s:property value="#ncFile.fileName"/>"><s:property value="#ncFile.fileName"/></a> </td>
				   <td align="left"><s:property value="#ncFile.oldfileName"/></td>
				   <td align="left"><s:property value="#ncFile.addTime"/></td>
				   <td align="left"><s:property value="#ncFile.userName"/></td>
					 <td><a href="DownAction.action?fileName=${ncFile.fileName}&directory=/upload/file/processTz/${ncFile.month}/">下载</a>&nbsp;&nbsp;
<!--				   <td><a href="<%=basePath%>FileViewAction.action?FilePath=/upload/file/processTz/${ncFile.month}/${lsFile.fileName}">下载</a>&nbsp;&nbsp;-->
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
				   <td align="left"><a href="<%=basePath%>FileViewAction.action?FilePath=/upload/file/processTz/${lsFile.month}/<s:property value="#lsFile.fileName"/>"><s:property value="#lsFile.fileName"/></a> </td>
				   <td align="left"><s:property value="#lsFile.oldfileName"/></td>
				   <td align="left"><s:property value="#lsFile.addTime"/></td>
				   <td align="left"><s:property value="#lsFile.userName"/></td>
					 <td><a href="DownAction.action?fileName=${lsFile.fileName}&directory=/upload/file/processTz/${lsFile.month}/">下载</a>&nbsp;&nbsp;
<!--				   <td><a href="<%=basePath%>FileViewAction.action?FilePath=/upload/file/processTz/${lsFile.month}/${cxFile.fileName}">下载</a>&nbsp;&nbsp;-->
				  </tr>
				 </s:if>
				 </s:iterator>
					<tr id="bqwjTr">
						<td colspan="4" align="left">
							<h3>
								标签文件
							</h3>
						</td>
					</tr>
					<s:iterator value="list" id="bqwjFile">
						<s:if test="#bqwjFile.type=='标签文件'">
							<tr>
								<td align="left">
									<a target="_showP"
										<%--										href="<%=path%>/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>">--%>
										href="FileViewAction.action?wmark=${procard.orderNumber}&FilePath=/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>">
										<%--										<img--%> <%--											src="<%=path%>/upload/file/processTz/${bqwjFile.month}/<s:property value="#bqwjFile.fileName"/>"--%>
										<%--											style="width: 80px; height: 80px;" /> --%>
										${bqwjFile.fileName} </a>
								</td>
								<td align="left"><s:property value="#bqwjFile.oldfileName"/></td>
				 				 <td align="left"><s:property value="#bqwjFile.addTime"/></td>
				 				 <td align="left"><s:property value="#bqwjFile.userName"/></td>
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
					<tr id="otherTr">
				 <td colspan="5" align="left">
				 <h3>其他文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="otherFile">
				 <s:if test="#otherFile.type=='其他文件'">
				  <tr>
<%--				   <td align="left"><a href="<%=path%>/upload/file/processTz/${otherFile.month}/<s:property value="#otherFile.fileName"/>"> <img src="<%=path%>/upload/file/processTz/${otherFile.month}/<s:property value="#otherFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a href="<%=basePath%>FileViewAction.action?FilePath=/upload/file/processTz/${otherFile.month}/<s:property value="#otherFile.fileName"/>"><s:property value="#otherFile.fileName"/></a> </td>
				   <td align="left"><s:property value="#otherFile.oldfileName"/></td>
				   <td align="left"><s:property value="#otherFile.addTime"/></td>
				   <td align="left"><s:property value="#otherFile.userName"/></td>
					 <td><a href="DownAction.action?fileName=${otherFile.fileName}&directory=/upload/file/processTz/${otherFile.month}/">下载</a>&nbsp;&nbsp;
<!--				   <td><a href="<%=basePath%>FileViewAction.action?FilePath=/upload/file/processTz/${otherFile.month}/${cxFile.fileName}">下载</a>&nbsp;&nbsp;-->
				  </tr>
				 </s:if>
				 </s:iterator>
				</table>
			</div>
		</center>
		<SCRIPT type="text/javascript">
		</SCRIPT>
	</body>
</html>
