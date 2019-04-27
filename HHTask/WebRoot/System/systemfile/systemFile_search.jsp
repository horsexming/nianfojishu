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
	<style type="text/css">
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
	max-width: 500px;
	overflow: auto;
}
	</style>
	</head>
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
					<h3>
						技术部文件受控管理
					</h3>
				<s:if test="tag=='no'">
					<form action="systemFileAction_findAllByUser.action" method="post">
				</s:if>
				<s:elseif test="tag=='all'">
					<form action="systemFileAction_findAllByshenpi.action"
						method="post">
				</s:elseif>
				<s:elseif test="tag=='jsb'">
					<form action="systemFileAction_findCodeManager.action"
						method="post">
				</s:elseif>
				<s:else>
					<form action="systemFileAction_findAllByupload.action"
						method="post">
				</s:else>
				<table class="table" align="center">
					<tr>
						<th>
							文件类别：
						</th>
						<td align="center">
							<select name="systemFile.category" id="fileType" >
								<option value=""></option>
								<option value="${systemFile.category}" selected="selected">${systemFile.category}</option>
							</select>
						</td>
						<th>
							文件编号：
						</th>
						<td align="center">
							<input type="text" name="systemFile.fileNo"
								value="${systemFile.fileNo}" />
						</td>
						<th>
							文件名称：
						</th>
						<td align="center">

							<input type="text" name="systemFile.fileName"
								value="${systemFile.fileName}" />
						</td>
					</tr>
					<tr>
						<th>
							文件等级：
						</th>
						<td align="center">
							<select name="systemFile.fileLevel" id="fileLevel">
								<option value="${systemFile.fileLevel }">${systemFile.fileLevel }</option>
							</select>
						</td>
						<th>
							部门归属：
						</th>
						<td align="center">


							<SELECT id="dept" name="systemFile.department" >
								<option></option>
								<option value="${systemFile.department}" selected="selected">${systemFile.department}</option>
							</SELECT>

						</td>
						<th>
							文件来源：
						</th>
						<td align="center">
							<select name="systemFile.source" id="fileSource">
								<option></option>
								<option value="${systemFile.source}" selected="selected">${systemFile.source }</option>
							</select>
						</td>
<%--						<th>--%>
<%--							上传人员：--%>
<%--						</th>--%>
<%--						<td align="center">--%>
<%--							<input type="text" name="systemFile.person"--%>
<%--								value="${systemFile.person}" />--%>
<%--						</td>--%>

					</tr>
					<tr>
						<td align="center" colspan="6">
							<input type="hidden" value="${pageStatus}" name="pageStatus" />
							<input type="hidden" value="${tag }" name="tag">
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询" />
							<input type="reset" style="width: 100px; height: 40px;"
								value="重置" />
						</td>
					</tr>
				</table>
				</form>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							文件类别
						</td>
						<td align="center">
							文件编号
						</td>
						<td align="center">
							文件名称
						</td>
						<td align="center">
							产品编号
						</td>
						<td align="center">
							文件等级
						</td>
						<td align="center">
							部门归属
						</td>
						<td align="center">
							文件描述
						</td>
						<td align="center">
							版本号
						</td>
						<td align="center">
							上传人员
						</td>
						<td align="center">
							上传时间
						</td>
						<td align="center">
							审核状态
						</td>
						<td align="center" colspan="2">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<tr>
						<td align="center" colspan="15" bgcolor="red">
							未提交文件
						</td>
					</tr>
					<s:iterator value="unSystemFileList" id="sFile" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="center">
							${sFile.category}
						</td>
						<td align="left">
							${sFile.fileNo}
						</td>
						<td align="left">
							${sFile.fileName}
						</td>
						<td align="center">
							${sFile.cpCode}
						</td>
						<td align="center">
							${sFile.fileLevel}
						</td>
						<td align="center">
							${sFile.department}
						</td>
						<td align="left" style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							
							<font size="1">${sFile.description}</font>
							<ul class="qs_ul">
								<li>
									${sFile.description}
								</li>
							</ul>
						</td>
						</td>
						<td align="center">
							${sFile.banben}
						</td>
						<td align="center">
							${sFile.person}
						</td>
						<td align="center">
							${sFile.uploadDate}
						</td>
						<td align="center">
						</td>
						<td colspan="2">
							<input type="button" value="上传文件" onclick="location.href='${pageContext.request.contextPath}/systemFileAction_getSystemFileDetail.action?tag=${tag }&id=${sFile.id }&pageStatus=${pageStatus }'">
						</td>

					</s:iterator>
					</tr>
					<tr>
						<td align="center" colspan="15" bgcolor="green">
							已提交文件
						</td>
					</tr>
					<s:iterator value="systemFileList" id="sFile" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="center">
							${sFile.category}
						</td>
						<td align="left">
							${sFile.fileNo}
						</td>
						<td align="left">
							${sFile.fileName}
						</td>
						<td align="center">
							${sFile.cpCode}
						</td>
						<td align="center">
							${sFile.fileLevel}
						</td>
						<td align="center">
							${sFile.department}
						</td>
						<td align="left" style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							
							<font size="1">${sFile.description}</font>
							<ul class="qs_ul">
								<li>
									${sFile.description}
								</li>
							</ul>
						</td>
						<td align="center">
							${sFile.banben}
						</td>
						<td align="center">
							${sFile.person}
						</td>
						<td align="center">
							${sFile.uploadDate}
						</td>
						<td align="center">
							<s:if test="'作废'==#sFile.status">
								${sFile.status}
							</s:if>
							<s:else>
								${sFile.status}
							</s:else>
						</td>
						<td colspan="2">
							<s:if test="'作废'==#sFile.status">
								<input type="button" value="详细信息" 
									onclick="location.href='${pageContext.request.contextPath}/systemFileAction_xiangxi.action?tag=${tag }&id=${sFile.id }&pageStaus=${pageStatus }'">
							</s:if>
							<s:else>
								<input type="button" value="详细信息" 
									onclick="location.href='${pageContext.request.contextPath}/systemFileAction_getSystemFileDetail.action?tag=${tag }&id=${sFile.id }&pageStatus=${pageStatus }'">
							</s:else>
						</td>

					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="15" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
<%--function update(id,cpage,tag) {--%>
<%--	window.location.href = "systemFileAction_toAdd.action?systemFile.id=" + id+"&cpage="+cpage+"&pageStatus="+tag;--%>
<%--}--%>
$(function() {
	 var tag = "dengji";
	$.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data:{
			tag:tag
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.typeName+"'>" + this.typeName
										+ "</option>").appendTo("#fileLevel");
					});
			$("#fileLevel").tinyselect();
		}
	});
});

$(function() {
	var tag = "category";
	$.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data:{
			tag:tag
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.code+"'>" + this.typeName
										+ "</option>").appendTo("#fileType");
					});
			$("#fileType").tinyselect();
		}
	});
	
	$.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data : {
			tag : "laiyuan"
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.code + "'>"
										+ this.typeName + "</option>")
								.appendTo("#fileSource");
					});
			$("#fileSource").tinyselect();
		}
	});
});
</script>
	</body>
</html>
