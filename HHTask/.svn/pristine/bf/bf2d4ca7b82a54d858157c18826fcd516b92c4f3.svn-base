<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
					<div style="float: left; width: 48%" align="right">
						<a href="System/renshi/hr_addUser.jsp" style="color: #ffffff">添加员工</a>
					</div>
				</div>
				<!-- 入职流程导航栏 -->
				<div align="center" style="border: blue solid 1px; width: 100%;">
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 添加员工 </font>
					</div>
					<div class="style1"
						style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 上传学历证书</font>
					</div>
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 签订合同 </font>
					</div>
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 薪资信息 </font>
					</div>
				</div>
				<div style="position:absolute;	top: 50px; right: 30%;" >
					<br />
					
											
									  
					<form action="UsersAction!uploadResume.action" method="post"
						enctype="multipart/form-data">
					<font  size="5">上传员工</font>	<font size="5" color="red">${user.name}</font> <font size="5">学历证书:</font>
						<input type="hidden" name="id" value="${user.id}">
						<input type="hidden" name="pageStatus" value="resume">
						<input type="file" name="picture">
						<input type="hidden" name="tag" value="${tag}"/>
						<input type="submit" value="上传" style="width: 60px; height: 30px">
						<br />
						<br />
						<s:if test="pageStatus==null||pageStatus==''">
							<a onclick="return window.confirm('将跳过上传简历,确定跳过?')"
								href="UsersAction!skipUploadResume.action?id=${user.id}">跳过上传简历,以后再上传?</a>
						</s:if>
					</form>
					<div id="xiazaidiv">
					
					</div>
				</div>
				
				<div align="center">
					<div>
						<font color="red">${successMessage}</font>
						<font color="red">${errorMessage}</font>
					</div>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
<SCRIPT type="text/javascript">
var resume = "${user.password.resume}";
$(function(){
	var arraystr =  resume.split("#");
	if(arraystr!=null && arraystr.length>0){
		for(var i=0;i<arraystr.length;i++){
			if(arraystr[i]!=''){
			$("#xiazaidiv").append('<a href=javascript:; onclick=xiazai1("'+arraystr[i]+'")>下载学历证书'+(i+1)+'</a>' +
							'&nbsp;&nbsp;&nbsp;&nbsp;<a href=javascript:; onclick=del("'+arraystr[i]+'")>删除</a><br/>');
			}
		}
	}
})
function xiazai1(obj){
	var fileName1 = encodeURI(encodeURI(obj));
<%--	location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1+"&directory=/upload/user/";--%>
	location.href="<%=request.getContextPath()%>/FileViewAction.action?FilePath=/upload/user/"+fileName1;
}

function del(fileName1){
	var fileName1 = encodeURI(encodeURI(fileName1));
	window.location.href = "UsersAction!updateResume.action?id=${user.id}&pageStatus=1&pictureFileName="+fileName1;
}

</SCRIPT>
	</body>
















</html>
