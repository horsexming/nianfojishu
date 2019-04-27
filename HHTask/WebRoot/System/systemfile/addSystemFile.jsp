<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
</head>
<body onload="createDept('dept','DeptNumberAction!finAllDeptNumberForSetlect.action?belongLayer=2')" >
<h3></h3>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
				
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
		<c:choose>
			 <c:when test="${param.pageStatus=='gjb'|| pageStatus=='gjb'}">
				 <h4>${systemFile.id==null?'添加':'修改'}国军标文件</h4>
			</c:when>
			<c:otherwise>
				<h4>${systemFile.id==null?'添加':'修改'}体系文件</h4>
			</c:otherwise>
		</c:choose>
		
			<form action="systemFileAction_${systemFile.id==null?'add':'update'}.action" enctype="multipart/form-data" method="post" onsubmit="return validate()">
				<table class="table">
					<tr>
					  <th align="right">文件类型 
					  </th>
					  <td>
					  <input type="hidden" name="systemFile.id"
								value="${systemFile.id}" id="id"/>
					  	<select name="systemFile.fileType" id="fileType" value="${systemFile.fileType}">
					  	<c:choose>
					  		<c:when test="${param.pageStatus=='gb'|| pageStatus=='gb'}">
								<option value="国标">国标</option>
							</c:when>
							<c:when test="${param.pageStatus=='jb' || pageStatus=='jb'}">
								<option value="军标">军标</option>
							</c:when>
							<c:when test="${param.pageStatus=='gjb' || pageStatus=='gjb'}">
								<option value="国标(gb)">国标</option>
								<option value="军标">军标</option>
								<option value="国军标">国军标</option>
								<option value="项目文件">项目文件</option>
							</c:when>
							<c:otherwise>
								<option value="体系文件">体系文件</option>
								<option value="外来文件">外来文件</option>
								<option value="法律法规">法律法规</option>
								<option value="认证证书">认证证书</option>
								<option value="国标">国标</option>
							</c:otherwise>
						</c:choose>
    					</select>
					  </td>
					  <th align="right">文件等级
					  </th>
					  <td>
					  	<select name="systemFile.fileLevel" id="fileLevel" >
					  	<c:choose>
					  		<c:when test="${param.pageStatus=='gjb'|| pageStatus=='gjb'}">
					  			<option value="国标">国标</option>
								<option value="军标">军标</option>
								<option value="国军标">国军标</option>
					  			<option value="技术规范">技术规范</option>
								<option value="法律法规">法律法规</option>
								<option value="作业SOP">作业SOP</option>
								<option value="检验SIP">检验SIP</option>
								<option value="培训资料">培训资料</option>
								<option value="其他文件">其他文件</option>
							</c:when>
							<c:otherwise>
					  		<option value="${systemFile.fileLevel}">${systemFile.fileLevel}</option>
    						<option value="一级文件">一级文件</option>
							<option value="二级文件">二级文件</option>
							<option value="三级文件">三级文件</option>
							<option value="四级表单">四级表单</option>
							<option value="技术规范">技术规范</option>
							<option value="法律法规">法律法规</option>
							<option value="作业SOP">作业SOP</option>
							<option value="检验SIP">检验SIP</option>
							<option value="外来文件">外来文件</option>
							<option value="培训资料">培训资料</option>
							<option value="其他文件">其他文件</option>
							</c:otherwise>
						</c:choose>
    					</select>
					  </td>
					</tr>
					<tr>
					  <th align="right">文件编号
					  </th>
					  <td>
					  	<input name="systemFile.fileNo" type="fileNo" value="${systemFile.fileNo}"/>
					  </td>
					  <th align="right">文件名称
					  </th>
					  <td>
					   <textarea rows="1" cols="80" name="systemFile.fileName">${systemFile.fileName}</textarea>
					  </td>
					</tr>
					<tr>
					  <th align="right">所属部门
					  </th>
					  <td colspan="1">
					  	<SELECT id="dept" name="systemFile.department" value="${systemFile.department}">
					  	</SELECT>
					  </td>
					  <th align="right">产品编码
					  </th>
					  <td>
					  	<input type="text" name="systemFile.cpCode" value="${systemFile.cpCode}">
					  </td>
					</tr>
					<tr>
					    <th align="right">文件描述
					    </th>
					    <td colspan="3">
					    <textarea rows="8" cols="80" name="systemFile.description">${systemFile.description}</textarea>
					    </td>
					</tr>
					<tr>
						<th align="right">${systemFile.id==null?'上传文件':'重新上传'}
					 	</th>
<%--					 		<td colspan="3"><input type="file" name="sys" id="sys"/><s:if test="systemFile.fileUrl!=null"><a href="<%=path%>/upload/file/sysFile/${systemFile.fileUrl}"><FONT color="red">${systemFile.fileUrl}</FONT></a></s:if></td>--%>
					 		<td colspan="3"><input type="file" name="sys" id="sys"/><s:if test="systemFile.fileUrl!=null"><a href="FileViewAction.action?FilePath=/upload/file/sysFile/${systemFile.fileUrl}"><FONT color="red">${systemFile.fileUrl}</FONT></a></s:if></td>
						</tr>
					<tr>
					<c:if test="${param.pageStatus=='gjb'|| pageStatus=='gjb'}">
						<input type="hidden" value="保密" name="systemFile.baomi" />
					</c:if>
					 <td colspan="4" align="center">
					 	<input type="hidden" value="${param.pageStatus}" name="pageStatus"/>
					   <input type="submit" value="提交">
					 </td>
					</tr>
				</table>
			</form>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
	function validate(){
		var file =document.getElementById("sys");
		var id = document.getElementById("id");
		if(id.value==""){
    		if(file.value==""){
       	 		alert("上传文件不能为空！");
       	 		return false;
        	}
    	}
		return true;
	}
	</script>
</body>
</html>
