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
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/javascript/layer/theme/default/layer.css">
		<%--<script type="text/javascript"--%>
		<%--			src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js">--%>
		<%--			</script>--%>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/layer/layer.js">
</script>
	</head>
	<body>
		<h3></h3>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<h4>
				文件受控申请
				</h4>

					<table class="table">
						<s:if test="systemFile.source!=null">
							<tr>
								<th align="right" style="width: 15%">文件来源</th>
								<td align="left" style="width: 35%">${systemFile.source}</td>
								<th align="right" style="width: 15%">文件类别</th>
								<td align="left" style="width: 35%">${systemFile.category }</td>
							</tr>
						</s:if>
						
						<tr>
							<s:if test="systemFile.fileType!=null">
								<th align="right" style="width: 15%">
									文件类型
								</th>
								<td style="width: 35%">
									${systemFile.fileType}
									<input type="hidden" value="${systemFile.fileType}" id="fileType">
								</td>
							</s:if>
							<th align="right" style="width: 15%">
								文件等级
							</th>
							<td style="width: 35%">
								${systemFile.fileLevel}
							</td>
						</tr>
						<tr>
							<th align="right">
								文件名称
							</th>
							<td colspan="3">
								<textarea rows="1" cols="80" name="systemFile.fileName" readonly="readonly">${systemFile.fileName}</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								文件编号
							</th>
							<td>
								${systemFile.fileNo}
							</td>
							<s:if test="systemFile.fileType=='合同类'">
								<th align="right">
									项目编号
								</th>
								<td>
									${systemFile.fileType}
								</td>
							</s:if>
							<s:else>
								<th align="right">
									文件版本号
								</th>
								<td>
									${systemFile.banben}
								</td>
							</s:else>
							
						</tr>
						<tr>
							<th align="right">
								所属部门
							</th>
							<td colspan="1">
										${Users.dept}
							</td>
							<th align="right">
								产品编码
							</th>
							<td>
								${systemFile.cpCode}
							</td>
						</tr>
						<tr>
						<th align="right">
								文件查看
							</th>
							<td colspan="3">
<%--								<s:if test="systemFile.fileUrl!=null">--%>
<%--									<a--%>
<%--										href="FileViewAction.action?FilePath=/upload/file/sysFile/${systemFile.fileUrl}"><FONT--%>
<%--										color="red">${systemFile.fileUrl}</FONT> </a>--%>
<%--									<input type="hidden" value="${systemFile.fileUrl}" id="fileUrl">--%>
<%--									<a href="${pageContext.request.contextPath}/upload/file/sysFile/${systemFile.fileUrl}" style="display:none;" id="download"><FONT--%>
<%--										color="red">下载</FONT> </a>--%>
<%--								</s:if>--%>
								<div id="showFiles">
									<input type="hidden" id="fileInput" value="${systemFile.fileUrl}">
									<input type="hidden" id="fileNameInput" value="${systemFile.otherName }" />
								</div>
							</td>
						</tr>
						<tr>
							<th align="right">
								文件描述
							</th>
							<td colspan="3">
								<textarea rows="8" cols="80" name="systemFile.description" readonly="readonly">${systemFile.description}</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								推送人员
								<br />
								<br />
							</th>
							<td colspan="3">
								<textarea id="tishiPerson" name="systemFile.personToLook"
									rows="5" cols="80" readonly="readonly">${systemFile.personToLook}</textarea>
							</td>
						</tr>
					</table>
					<s:if test="systemFileList!=null">
						<table class="table">
							<tr>
								<th colspan="6" align="center">文件历史版本记录</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>版本号</th>
								<th>升版日期</th>
								<th>状态</th>
								<th>文件</th>
								<th>操作</th>
							</tr>
								<s:iterator value="systemFileList" id="sf" status="pageStatus">
									<tr>
										<td >
											<s:if test="#pageStatus.index%2==1">
												<font>
											</s:if>
											<s:else>
												<font color="#c0dcf2">
											</s:else>
											<s:property value="#pageStatus.index+1" />
											</font>
										</td>
										<td>
											${sf.banben }
										</td>
										<td>
											${sf.uploadDate }
										</td>
										<td>
											<s:if test="'作废'==#sf.status">
												${sf.status}
											</s:if>
											<s:else>
												<a href="CircuitRunAction_findAduitPage.action?id=${sf.epId}">${sf.status}</a>
											</s:else>
										</td>
										<td>
											<a href="FileViewAction.action?FilePath=/upload/file/sysFile/${sf.fileUrl}">
											<FONT color="red">${sf.fileUrl}</FONT>
											</a>
										</td>
										<td>
											<a href="${pageContext.request.contextPath}/systemFileAction_xiangxi.action?id=${sf.id}">详细</a>
										</td>
									</tr>
								</s:iterator>
								<tr>
									<s:if test="errorMessage==null">
										<td colspan="13" align="right">
											第
											<font color="red"><s:property value="cpage" /> </font> /
											<s:property value="total" />
											页
											<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
												styleClass="page" theme="number" />
									</s:if>
									<s:else>
										<td colspan="13" align="center" style="color: red">
											${errorMessage}
									</s:else>
									</td>
								</tr>
					</table>	
					</s:if>
					
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		$(function(){
			//显示查看
			var fileInput = $("#fileInput").val();
			if(fileInput!=null && fileInput!=""){
				var str = "";
				var fileType =$("#fileType").val(); 
				if("合同类"== fileType){
					var suffix = fileInput.split(",");
					var fileName = $("#fileNameInput").val().split(",");
					for(var i=0;i<suffix.length;i++){
						//var subSuffix = suffix[i].substring(suffix[i].indexOf("."));
						if(fileName[i]==""){
							fileName[i]=suffix[i];
						}
						str+="<a href='${pageContext.request.contextPath}/FileViewAction.action?"+
						"FilePath=/upload/file/sysFile/"+suffix[i]+"'>"+fileName[i]+"</a><br>";
<%--						if(subSuffix.indexOf("jpg")<0 && subSuffix.indexOf("pdf")<0 && subSuffix.indexOf("jpg")<0){--%>
<%--							str+="&nbsp;<a href='${pageContext.request.contextPath}/"+--%>
<%--							"upload/file/sysFile/"+suffix[i]+"'>下载</a>&nbsp;&nbsp;&nbsp;<br>";--%>
<%--						}else{--%>
<%--							str+="<a href='${pageContext.request.contextPath}/FileViewAction.action?"+--%>
<%--							"FilePath=/upload/file/sysFile/"+suffix[i]+"'>"+fileName[i]+"</a>";--%>
<%--						}--%>
					}
				}else{
					var suffix = fileInput.substring(fileInput.indexOf("."));
					//alert(suffix);
					
<%--					if(suffix.indexOf("jpg")<0 && suffix.indexOf("pdf")<0 && suffix.indexOf("jpg")<0){--%>
<%--						str+="&nbsp;<a href='${pageContext.request.contextPath}/upload/file/sysFile/"+fileInput+"'>下载</a>";--%>
<%--					}else{--%>
						str+="<a href='${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/sysFile/"+fileInput+"'>"+fileInput+"</a><br>";
<%--					}--%>
				}
				$("#showFiles").append(str);
			}
			
		});

	</script>
	</body>
</html>
