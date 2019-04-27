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
	<script type="text/javascript" 
	src="${pageContext.request.contextPath}/javascript/layer/layer.js">
	</script>
</head>
<body onload="createDept('dept','DeptNumberAction!finAllDeptNumberForSetlect.action')">
<%----%>
	<h3></h3>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng"
		style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">

		<div align="center">
			<h4>技术部文件受控明细</h4>
			<br>
			<br>
			<s:if test="systemFile.fileUrl!=null">
				<div align="center" style="float: left;width: 35%;margin-left: 10px;">
				<table class="table">
					<tr>
						<th colspan="10" align="center">文件历史版本记录</th>
					</tr>
					<tr>
						<th>编号</th>
						<th>版本号</th>
						<th>升版日期</th>
						<th>申请人</th>
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
									${sf.person }
								</td>
								<td>
									${sf.status}
								</td>
								<td>
									<a href="FileViewAction.action?FilePath=/upload/file/sysFile/${sf.fileUrl}">
										<s:if test="#sf.otherName!=null">
											<FONT color="red">${sf.otherName}</FONT>
										</s:if>
										<s:else>
											<FONT color="red">${sf.fileUrl}</FONT>
										</s:else>
									</a>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/systemFileAction_getSystemFileDetail.action?tag=${tag }&id=${sf.id }&cpage=${cpage}&total=${total}">详细</a>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="13" align="right">
<%--								共 <s:property value="total" /> 页 --%>
<%--								第 <s:property value="cpage" /> 页--%>
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
					<s:if test="userName==systemFile.person">
						<s:if test="systemFile.status=='未审批'||systemFile.status=='打回'">
							<input type="button" value="修改" 
								onclick="update(${systemFile.id})" class="input">
						</s:if>
						<input type="button" value="升版" onclick="toUpGrade()" class="input">
						<input type="button" value="作废" onclick="toCancellation()" class="input">
						<input type="hidden" value="${systemFile.status}" id="fileStatus">
					</s:if>
				</div>
			</s:if>
			<form id="submitFrom" method="post" >
				<s:if test="systemFile.fileUrl==null || tags=='reload'">
					<%-- 上传文件 --%>
					<s:if test="systemFile.fileUrl==null">
						<table class="table" >
					</s:if>
					<s:else>
						<table class="table" style="float: left;width: 57%;margin-right: 10px;">
					</s:else>
						<tr>
							<th align="right">文件来源</th>
							<td>
								<select name="systemFile.source" id="fileSource">
									<option value="${systemFile.source }">${systemFile.source }</option>
								</select>
							</td>
							<th align="right">文件类别</th>
							<td>
								<input type="hidden" name="systemFile.id" value="${systemFile.id}" id="fileId" /> 
								<select name="systemFile.category" id="fileCategory" >
									<option value="${systemFile.category}">${systemFile.category}</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">文件名称</th>
							<td>
	<%--							<textarea rows="1" cols="80" name="systemFile.fileName">${systemFile.fileName}</textarea>--%>
								<input type="text" name="systemFile.fileName" value="${systemFile.fileName }" 
								 style="width:200px;height: 30px;">
							</td>
							
							<th align="right">文件等级</th>
							<td>
								<select name="systemFile.fileLevel" id="fileLevel" >
									<option value="${systemFile.fileLevel}">${systemFile.fileLevel}</option>
									
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">文件编号</th>
							<td>
								<input name="systemFile.fileNo" value="${systemFile.fileNo}" readonly="readonly"  style="width:200px;height: 30px;"/>
							</td>
							<th align="right">文件版本号</th>
							<td>
								<input type="text" name="systemFile.banben" value="${systemFile.banben }" readonly="readonly" style="width:200px;height: 30px;">
							</td>
						</tr>
						<tr>
							<th align="right">所属部门</th>
							<td colspan="1"><SELECT id="dept"
								name="systemFile.department" >
								<option value="${systemFile.department }">${systemFile.department }</option>
							</SELECT></td>
							<s:if test="systemFile.proCode!=null">
								<th align="right">项目编号</th>
								<td><input type="text" name="systemFile.proCode"
									value="${systemFile.proCode}" readonly="readonly" 
									style="width:200px;height: 30px;"></td>
								
							</s:if>
						</tr>
						<tr>
							<th align="right">文件描述</th>
							<td colspan="3"><textarea rows="8" cols="80"
									name="systemFile.description">${systemFile.description}</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">上传文件</th>
							<td colspan="3">
								<input type="file" name="sys" id="sys" />
								<s:if test="systemFile.fileUrl!=null">
									<a href="FileViewAction.action?FilePath=/upload/file/sysFile/${systemFile.fileUrl}">
										<s:if test="#sf.otherName!=null">
											<FONT color="red">${systemFile.otherName}</FONT>
										</s:if>
										<s:else>
											<FONT color="red">${systemFile.fileUrl}</FONT>
										</s:else>
										 
									</a>
								</s:if>
							</td>
						</tr>
						<tr>
							<th align="right">
								文件管控审核人
							</th>
							<td colspan="3">
							<div id="freeDeptDiv">
								<hr />
								<font color="red">文件管控审核人:</font>
								<input type="button" value="增加" onclick="addFreeDept('','')"
									style="width: 60px; height: 30px">
								<ul id="freeDeptUl0">
									<li id="freeDeptli0">
										<SELECT id="zrdept0" name="approvalId"
											onchange="changefreeDept('','',0)"></SELECT>
										<SELECT id="zrpeople0" name="ids"></SELECT>
										<input type="button" value="删除" onclick="deleteFreeDept('','',0)"
											style="width: 60px; height: 30px">
									</li>
								</ul>
							</div>
						</td>
						</tr>
						<tr>
							<th align="right">
								文件管控批准人
							</th>
							<td colspan="3">
							<div id="freeDeptDivpz">
								<hr />
								<font color="red">文件管控批准人:</font>
								<input type="button" value="增加" onclick="addFreeDept('pz','')"
									style="width: 60px; height: 30px">
								<ul id="freeDeptUlpz0">
									<li id="freeDeptlipz0">
										<SELECT id="zrdeptpz0"
											onchange="changefreeDept('pz','',0)"></SELECT>
										<SELECT id="zrpeoplepz0" name="pzId"></SELECT>
										<input type="button" value="删除" onclick="deleteFreeDept('pz','',0)"
											style="width: 60px; height: 30px">
									</li>
								</ul>
							</div>
						</td>
						</tr>
						<tr>
							<th align="right">
								推送人员
								<br />
								<br />
								<input id="test2" type="button" value="选择推送人员">
							</th>
							<td colspan="3">
								<input id="fid" name="systemFile.personToLookId"
									value="${systemFile.personToLookId}" readonly="readonly"
									type="hidden" />
								<textarea id="tishiPerson" name="systemFile.personToLook"
									rows="5" cols="80" readonly="readonly">${systemFile.personToLook}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="hidden" value="${param.pageStatus}" name="pageStatus" /> 
								<input type="button" value="提交" onclick="submitBtn()" class="input">
							</td>
						</tr>
					</table>
				</s:if>
				<s:else>
					<%-- 查看详情 --%>
					<div style="float: left;width: 57%;margin-right: 10px;">
						<table class="table" >
							<tr>
								<th align="right" style="width: 15%">文件来源</th>
								<td style="width: 35%">
									${systemFile.source }
								</td>
								<th align="right"style="width: 15%">文件类别</th>
								<td style="width: 35%">${systemFile.category}</td>
							</tr>
							<tr>
								<th align="right">文件名称</th>
								<td>${systemFile.fileName }</td>
								
								<th align="right">文件等级</th>
								<td>${systemFile.fileLevel}</td>
							</tr>
							<tr>
								<th align="right">文件编号</th>
								<td>
									${systemFile.fileNo}
									<input type="hidden" value="${systemFile.fileNo}" id="fileNo">
								</td>
								<th align="right">文件版本号</th>
								<td>${systemFile.banben }</td>
							</tr>
							<tr>
								<th align="right">所属部门</th>
								<td colspan="1">${systemFile.department}</td>
								<s:if test="systemFile.proCode!=null">
									<th align="right">项目编号</th>
									<td>${systemFile.proCode}</td>
								</s:if>
								<s:else>
									<th></th>
									<td></td>
								</s:else>
							</tr>
							<tr>
								<th align="right">上传日期</th>
								<td align="left">${systemFile.uploadDate }</td>
								<th align="right">当前状态</th>
								<td align="left">${systemFile.status }</td>
								
							</tr>
							<tr>
								<th align="right">文件描述</th>
								<td colspan="3">
									<div style="width:550px;height:110px">
										${systemFile.description}
									</div>
								</td>
							</tr>
							<tr>
								<th align="right">文件</th>
								<td colspan="3">
									<a href="FileViewAction.action?FilePath=/upload/file/sysFile/${systemFile.fileUrl}">
										<s:if test="#sf.otherName!=null">
											<FONT color="red">${systemFile.otherName}</FONT>
										</s:if>
										<s:else>
											<FONT color="red">${systemFile.fileUrl}</FONT>
										</s:else>
									</a>
								</td>
							</tr>
							<tr>
								<th align="right">
									推送人员
								</th>
								<td colspan="3">
									<div style="width:550px;height:110px;overflow:auto" >
										${systemFile.personToLook}
									</div>
								</td>
							</tr>
							<!-- <tr>
								<th>
									审核人
								</th>
								<td>
									
								</td>
							</tr>
							<tr>
								<th>
									批准人
								</th>
								<td>
								
								</td>
							</tr> -->
						</table>
						<table class="table" >
							<tr>
								<th>
									序号
								</th>
								<th style="width: 40px;">
									审批
									<br />
									顺序
								</th>
								<th>
									姓名
								</th>
								<th>
									状态
								</th>
								<th>
									审批时间
								</th>
								<th>
									审批意见
								</th>
							</tr>
							<s:iterator value="circuitRunList" id="pageCircuitRun" status="pageIndex">
								<tr>
									<td align="center">
										${pageIndex.index+1}
									</td>
									<td align="center">
										<s:if test="#pageCircuitRun.auditLevel==1">
											审核人
										</s:if>
										<s:else>
											批准人
										</s:else>
									</td>
									<td align="center">
										${pageCircuitRun.auditUserName}
									</td>
									<td align="center">
										${pageCircuitRun.auditStatus}
									</td>
									<td align="center">
										${pageCircuitRun.auditDateTime}
									</td>
									<td align="center">
										${pageCircuitRun.auditOpinion}
									</td>
								</tr>
							</s:iterator>
							<s:if test="executionNode!=null">
								<tr>
									<td colspan="10" align="center">
										<div id="showMess" align="center"
											style="display: none; font-size: 20px; font-weight: bolder; color: red;"></div>
										<br />
										<table class="table" id="asdfgh">
											<tr>
												<th colspan="2">审批</th>
											</tr>
											<tr>
												<th align="right">
													审批:
												</th>
												<td>
													<input id="auditTrue" type="radio" 
														name="auditStauts" value="true" checked="checked" />
													<label for="auditTrue">
														同意
													</label>
													<input id="auditFalse" type="radio" 
														name="auditStauts" value="false" />
													<label for="auditFalse">
														打回
													</label>
												</td>
											</tr>
											<tr>
												<th align="right">
													审批意见:
												</th>
												<td align="center">
													<textarea  rows="5" cols="25"
														style="width: 100%" id="auditOpinion"></textarea>
												</td>
											</tr>
											<tr>
												<td colspan="2" align="center">
													<input type="button" value="审批"
													onclick="examBtn()"  class="input" id="ok" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</s:if>
						</table>
					</div>
				</s:else>
			</form>
			<form id="upGradeForm" method="post">
				<table class="table" style="float: left;width: 57%;margin-right: 10px;">
					<tr>
						<th align="right" style="width: 15%">文件来源</th>
						<td style="width: 35%">
							${systemFile.source }
							<input type="hidden" value="${systemFile.source}" name="systemFile.source"/>
						</td>
						<th align="right"style="width: 15%">文件类别</th>
						<td style="width: 35%">
							${systemFile.category}
							<input type="hidden" value="${systemFile.category }" name="systemFile.category">
						</td>
					</tr>
					<tr>
						<th align="right">文件名称</th>
						<td>
							${systemFile.fileName }
							<input type="hidden" value="${systemFile.fileName}" name="systemFile.fileName">
						</td>
						
						<th align="right">文件等级</th>
						<td>
							${systemFile.fileLevel}
							<input type="hidden" value="${systemFile.fileLevel}" name="systemFile.fileLevel">
						</td>
					</tr>
					<tr>
						<th align="right">文件编号</th>
						<td>
							${systemFile.fileNo}
							<input type="hidden" value="${systemFile.fileNo}" name="systemFile.fileNo" >
						</td>
						<th align="right">文件版本号</th>
						<td>${newBanben}<input type="hidden" name="systemFile.banben" 
							value="${newBanben}" id="newBanben"></td>
					</tr>
					<tr>
						<th align="right">所属部门</th>
						<td>
							${systemFile.department}
							<input type="hidden" value="${systemFile.department}" name="systemFile.department">
						</td>
						<s:if test="systemFile.proCode!=null">
							<th align="right">项目编号</th>
							<td>
								${systemFile.proCode}
								<input type="hidden" value="${systemFile.proCode }" name="systemFile.proCode">
							</td>
						</s:if>
						<s:else>
							<th></th>
							<td></td>
						</s:else>
					</tr>
					<tr>
						<th align="right">文件描述</th>
						<td colspan="3"><textarea rows="8" cols="80" id="upgradeDesc"
								name="systemFile.description">${systemFile.description}</textarea>
						</td>
					</tr>
					<tr>
						<th align="right">上传文件</th>
						<td colspan="3">
							<input type="file" name="sys" id="sys" />
						</td>
					</tr>
					<tr>
						<th align="right">
							文件管控审核人
						</th>
						<td colspan="3">
						<div id="freeDeptDivUpGrade">
							<hr />
							<font color="red">文件管控审核人:</font>
							<input type="button" value="增加" onclick="addFreeDept('','UpGrade')"
								style="width: 60px; height: 30px">
							<ul id="freeDeptUlUpGrade0">
								<li id="freeDeptliUpGrade0">
									<SELECT id="zrdeptUpGrade0" name="approvalId"
										onchange="changefreeDept('','UpGrade',0)"></SELECT>
									<SELECT id="zrpeopleUpGrade0" name="ids"></SELECT>
									<input type="button" value="删除" onclick="deleteFreeDept('','UpGrade',0)"
										style="width: 60px; height: 30px">
								</li>
							</ul>
						</div>
					</td>
					</tr>
					<tr>
						<th align="right">
							文件管控批准人
						</th>
						<td colspan="3">
						<div id="freeDeptDivpzUpGrade">
							<hr />
							<font color="red">文件管控批准人:</font>
							<input type="button" value="增加" onclick="addFreeDept('pz','UpGrade')"
								style="width: 60px; height: 30px">
							<ul id="freeDeptUlpzUpGrade0">
								<li id="freeDeptlipzUpGrade0">
									<SELECT id="zrdeptpzUpGrade0"
										onchange="changefreeDept('pz','UpGrade',0)"></SELECT>
									<SELECT id="zrpeoplepzUpGrade0" name="pzId"></SELECT>
									<input type="button" value="删除" onclick="deleteFreeDept('pz','UpGrade',0)"
										style="width: 60px; height: 30px">
								</li>
							</ul>
						</div>
					</td>
					</tr>
				
					
					
					<tr>
						<th align="right">
							推送人员
							<br />
							<br />
							<input id="test3" type="button" value="选择推送人员">
						</th>
						<td colspan="3">
							<input id="fid3" name="systemFile.personToLookId" value="${systemFile.personToLookId }" readonly="readonly" type="hidden" />
							<textarea id="tishiPerson3" name="systemFile.personToLook"
								rows="5" cols="80" readonly="readonly">${systemFile.personToLook}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="hidden" value="${systemFile.id}" name="systemFile.id">
							<input type="hidden" value="${param.pageStatus}" name="pageStatus" /> 
							<input type="button" value="升版" onclick="upGrade()" class="input">
						</td>
					</tr>
				</table>
			</form>
			<form id="cancellationForm" method="post">
				<table class="table" style="float: left;width: 57%;margin-right: 10px;">
					<tr>
						<th align="right" style="width: 15%">文件来源</th>
						<td style="width: 35%">
							${systemFile.source }
							<input type="hidden" value="${systemFile.source}" name="systemFile.source"/>
						</td>
						<th align="right"style="width: 15%">文件类别</th>
						<td style="width: 35%">
							${systemFile.category}
							<input type="hidden" value="${systemFile.category }" name="systemFile.category">
						</td>
					</tr>
					<tr>
						<th align="right">文件名称</th>
						<td>
							${systemFile.fileName }
							<input type="hidden" value="${systemFile.fileName}" name="systemFile.fileName">
						</td>
						
						<th align="right">文件等级</th>
						<td>
							${systemFile.fileLevel}
							<input type="hidden" value="${systemFile.fileLevel}" name="systemFile.fileLevel">
						</td>
					</tr>
					<tr>
						<th align="right">文件编号</th>
						<td>
							${systemFile.fileNo}
							<input type="hidden" value="${systemFile.fileNo}" name="systemFile.fileNo" >
						</td>
						<th align="right">文件版本号</th>
						<td>${newBanben }<input type="hidden" name="systemFile.banben" value="${newBanben}" id="newBanben"></td>
					</tr>
					<tr>
						<th align="right">所属部门</th>
						<td>
							${systemFile.department}
							<input type="hidden" value="${systemFile.department }" name="systemFile.department">
						</td>
						<s:if test="systemFile.proCode!=null">
							<th align="right">项目编号</th>
							<td>
								${systemFile.proCode}
								<input type="hidden" value="${systemFile.proCode }" name="systemFile.proCode">
							</td>
						</s:if>
						<s:else>
							<th></th>
							<td></td>
						</s:else>
					</tr>
					<tr>
						<th align="right">作废描述</th>
						<td colspan="3"><textarea rows="8" cols="80" id="canDesc"
								name="systemFile.description">${systemFile.canRemark}</textarea>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							文件作废审核人
						</th>
						<td colspan="3">
						<div id="freeDeptDivCancellation">
							<hr />
							<font color="red">文件作废审核人:</font>
							<input type="button" value="增加" onclick="addFreeDept('','Cancellation')"
								style="width: 60px; height: 30px">
							<ul id="freeDeptUlCancellation0">
								<li id="freeDeptliCancellation0">
									<SELECT id="zrdeptCancellation0" name="approvalId"
										onchange="changefreeDept('','Cancellation',0)"></SELECT>
									<SELECT id="zrpeopleCancellation0" name="ids"></SELECT>
									<input type="button" value="删除" onclick="deleteFreeDept('','Cancellation',0)"
										style="width: 60px; height: 30px">
								</li>
							</ul>
						</div>
					</td>
					</tr>
					<tr>
						<th align="right">
							文件作废批准人
						</th>
						<td colspan="3">
						<div id="freeDeptDivpzCancellation">
							<hr />
							<font color="red">文件作废批准人:</font>
							<input type="button" value="增加" onclick="addFreeDept('pz','Cancellation')"
								style="width: 60px; height: 30px">
							<ul id="freeDeptUlpzCancellation0">
								<li id="freeDeptlipzCancellation0">
									<SELECT id="zrdeptpzCancellation0"
										onchange="changefreeDept('pz','Cancellation',0)"></SELECT>
									<SELECT id="zrpeoplepzCancellation0" name="pzId"></SELECT>
									<input type="button" value="删除" onclick="deleteFreeDept('pz','Cancellation',0)"
										style="width: 60px; height: 30px">
								</li>
							</ul>
						</div>
					</td>
					</tr>
					
					<tr>
						<th align="right">
							推送人员
							<br />
							<br />
							<input id="test4" type="button" value="选择推送人员">
						</th>
						<td colspan="3">
							<input id="fid4" name="systemFile.personToLookId" value="${systemFile.personToLookId}" readonly="readonly" type="hidden" />
							<textarea id="tishiPerson4" name="systemFile.personToLook"
								rows="5" cols="80" readonly="readonly">${systemFile.personToLook}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="hidden" value="${systemFile.id}" name="systemFile.id">
							<input type="hidden" value="${param.pageStatus}" name="pageStatus" /> 
							<input type="button" value="作废" onclick="cancellation()" class="input">
						</td>
					</tr>
				</table>
			</form>
		<br>
		<br>
		<br>
		<br>
		</div>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
	var depts;
	var deptIndexpz=0;
	var deptIndex = 0;
	$(function(){
		getDept();
		$("#upGradeForm").hide();
		$("#cancellationForm").hide();
		$("#fileSource").tinyselect();
		$("#fileCategory").tinyselect();
		$("#fileLevel").tinyselect();
		
		setDept('','',deptIndex);
		setDept('pz','',deptIndex);
		setDept('','UpGrade',deptIndex);
		setDept('','Cancellation',deptIndex);
		setDept('pz','UpGrade',deptIndex);
		setDept('pz','Cancellation',deptIndex);
		
		$('#test4').on('click', function(){
			layer.open({
			  type: 2,
			  title: '选择推送人员',
			  area: ['450px', '800px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath%>/System/systemfile/checkPerson4.jsp'
			});
		});
		$('#test3').on('click', function(){
			layer.open({
			  type: 2,
			  title: '选择推送人员',
			  area: ['450px', '800px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath%>/System/systemfile/checkPerson3.jsp'
			});
		});
		$('#test2').on('click', function(){
			layer.open({
			  type: 2,
			  title: '选择推送人员',
			  area: ['450px', '800px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath%>/System/systemfile/checkPerson.jsp'
			});
		});
		$('#test1').on('click', function(){
			layer.open({
			  type: 2,
			  title: '文件管控审批流程',
			  area: ['900px', '700px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath%>/CircuitCustomize_findAuditNodeByCcId.action?id=632'
			});
		});
		
	});
	function getDept(){
		$.ajax( {
			type : "post",
			url : "GzstoreAction_getdept.action",
			dataType : "json",
			async : false, 
			success : function(data) {
				depts = data;
			}
		});
	}
	function changefreeDept(flag,obj,i) {
		var deptId = $("#zrdept"+flag+obj + i).val();
		if (deptId > 0) {
			$.ajax( {
				type : "post",
				url : "AskForLeaveAction!getDeptUsers.action",
				dataType : "json",
				data : {
					id : deptId
				},
				success : function(data) {
					//填充部门信息
				var selectbox = $("#freeDeptUl"+flag+obj + i + " .tinyselect");
				if (selectbox.length > 1) {
					var len = selectbox.length - 1;
					for ( var n = len; n >= 1; n--) {
						$(selectbox[n]).remove();
					}
				}
				$("#zrpeople"+flag+obj + i).empty();
				$(data).each(
						function() {
							var html = "<option value='" + this.userId + "'>"
									+ this.userName + "</option>";
							$(html).appendTo("#zrpeople"+flag+obj + i);
						});
				$("#zrpeople"+flag+obj + i).tinyselect();

			}
			});
		}
	}
	
	//设置部门
	function setDept(flag,obj,i) {
		$(depts).each(function() {
			var html = "";
			if (this.dept == "${Users.dept}") {
				html = "<option selected='selected' value='"
						+ this.id + "'>" + this.dept + "</option>";
			} else {
				html = "<option value='" + this.id + "'>"
						+ this.dept + "</option>";
			}
			$(html).appendTo("#zrdept"+flag+obj + i);
		});
		changefreeDept(flag,obj,i);
		$("#zrdept"+flag+obj + i).tinyselect();
	}
	
	//根据部门获取人员
	function changefreeDept(flag,obj,i) {
		var deptId = $("#zrdept"+flag+obj + i).val();
		if (deptId > 0) {
			$.ajax( {
				type : "post",
				url : "GzstoreAction_getusers.action",
				dataType : "json",
				data : {
					id : deptId
				},
				success : function(data) {
					//填充部门信息
				var selectbox = $("#freeDeptUl"+flag + obj+i + " .tinyselect");
				if (selectbox.length > 1) {
					var len = selectbox.length - 1;
					for ( var n = len; n >= 1; n--) {
						$(selectbox[n]).remove();
					}
				}
				$("#zrpeople"+flag +obj+ i).empty();
				$(data).each(
						function() {
							var html = "<option value='" + this.id + "'>"
									+ this.name + "</option>";
							$(html).appendTo("#zrpeople"+flag +obj+ i);
						});
				$("#zrpeople"+flag+obj + i).tinyselect();

			}
			});
		}
	}
	function addFreeDept(flag,obj) {
		deptIndex++;
		var name;
		if(flag=="pz"){
			name="pzId";
		}else{
			name="ids";
		}
		var html = "<ul id='freeDeptUl"+flag+obj + deptIndex + "'>" + "<li id='freeDeptli"+flag+obj
				+ deptIndex + "'>" + "<SELECT id='zrdept"+flag+obj + deptIndex
				+ "' name='approvalId' onchange='changefreeDept(\""+flag+"\",\""+obj+"\"," + deptIndex
				+ ")'></SELECT>" + "<SELECT id='zrpeople"+flag+obj + deptIndex
				+ "' name='"+name+"'></SELECT>"
				+ "<input type='button' value='删除' onclick='deleteFreeDept(\""+flag+"\",\""+obj+"\","
				+ deptIndex + ")' style='width: 60px;height: 30px'>" + "</li></ul>";
		$(html).appendTo("#freeDeptDiv"+flag+obj);
		setDept(flag,obj,deptIndex);
	}
	function deleteFreeDept(flag,obj,index) {
		$("#freeDeptUl"+flag +obj+ index).remove();
	}
	
	function validate() {
		var file = document.getElementById("sys");
		if (file.value == "") {
			alert("上传文件不能为空！");
			return false;
		}
		return true;
	}
	
	
	function submitBtn(){
		if(!validate()){
			return ;		
		}
		var form = new FormData(document.getElementById("submitFrom"));
	   	$.ajax({
	             url:"${pageContext.request.contextPath}/systemFileAction_submitFile.action?tags=submit",
	             type:"post",
	             data:form,
	             processData:false,
	             contentType:false,
	             async : false, 
	             success:function(data){
	            	if(data==null){
	            		alert("上传出错。");
	            	}else{
		            	var object = eval('('+data+')');
	            		if(object==null){
		            		alert("上传出错。");
	            		}else{
	            			alert("上传成功");
	            			window.location.reload();
	            		}
	            	}
	             },error:function(err){
	           	  	alert(err);
	             }
	   	});
	}
	
	//前往文件升版
	function toUpGrade(){
		var fileStatus = $("#fileStatus").val();
		if(fileStatus=="打回"){
			alert("历史版本被打回，不能升版操作，谢谢。");
			return;
		}
		alert("新文件的版本为："+$("#newBanben").val()+",请上传文件。");
		$("#submitFrom").hide();
		$("#cancellationForm").hide();
		$("#upGradeForm").show();
		deptIndex=0;
	}
	//提交升版文件
	function upGrade(){
		if(!validate()){
			return ;		
		}
		var upgradeDesc = $("#upgradeDesc").val();
		if(upgradeDesc==null || upgradeDesc==""){
			alert("请填写升版描述");
			return;
		}
		var form = new FormData(document.getElementById("upGradeForm"));
	   	$.ajax({
             url:"${pageContext.request.contextPath}/systemFileAction_submitFile.action?tags=upgrade",
             type:"post",
             data:form,
             processData:false,
             contentType:false,
             async : false, 
             success:function(data){
            	if(data==null || data==""){
            		alert("上传出错。");
            	}else{
	            	var object = eval('('+data+')');
            		if(object==null){
	            		alert("上传出错。");
            		}else{
            			alert("升版申请已提交，请等待审批。");
            			window.location.reload();
            		}
            	}
             },error:function(err){
           	  	alert(err);
             }
	   	});
	}
	
	//前往文件作废
	function toCancellation(){
		var fileStatus = $("#fileStatus").val();
		if("作废"==fileStatus){
			alert("当前文件已作废，请选择最新文件，谢谢");
			return ;
		}
		$("#submitFrom").hide();
		$("#upGradeForm").hide();
		$("#cancellationForm").show();
		
		deptIndex=0;
		
	}
	//文件作废
	function cancellation(){
		var canDesc = $("#canDesc").val();
		if(canDesc==null || canDesc==""){
			alert("请填写作废描述");
			return ;
		}
		
		var fileNo = $("#fileNo").val();
		if(confirm("确定要作废文件编号为 "+fileNo+" 的记录吗？")){
			var form = new FormData(document.getElementById("cancellationForm"));
			$.ajax({
		        url:"${pageContext.request.contextPath}/systemFileAction_submitFile.action?tags=cancellation",
		        type:"post",
		        data:form,
	            processData:false,
	            contentType:false,
	            async : false, 
	            success:function(data){
		       	if(data==null || data==""){
		       		alert("作废失败");
		       	}else{
		           	var object = eval('('+data+')');
		       		if(object==null){
		           		alert("作废失败。");
		       		}else{
		       			alert("文件作废已提交，请等待审批");
		       			window.location.reload();
		       		}
		       	}
		        },error:function(err){
		      	  	alert(err);
		        }
		  	});
		}
	}
	
	function update(id){
		location.href="${pageContext.request.contextPath}/systemFileAction_"+
		"getSystemFileDetail.action?tag=jsb&id="+id+"&tags=reload";
	}
	
	function examBtn(){
		var ch =$("input:radio[name='auditStauts']:checked").val();
		var yijian = $("#auditOpinion").val();
		var epId = "${systemFile.epId}";
		if(epId==""){
			epId="${systemFile.canEpId}";
		}
		$.ajax({
			type : "POST",
			url : "CircuitRunAction_updateAudit.action",
			data :{
				"executionNode.id":"${executionNode.id}",
				"auditStauts":ch,
				"executionNode.auditOpinion":yijian,
				"nextMessage":true,
				"isMessage":true,
				"id":epId
			},
			dataType : "json",
			success : function(soure) {
				$("#asdfgh").hide();
				$("#showMess").show();
				$("#showMess").html(soure.message);
				var circuitRunName = '${circuitRun.name}';
				var allStatus = soure.data;
			}
		});
	}
	</script>
</body>
</html>
