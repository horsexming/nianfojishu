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
	<title>进度纪要填报</title>
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/uploadify/uploadify.css">
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				进度纪要填报
			</div>
		</div>
		<form  action="QuotedPrice_jytb.action"  enctype="multipart/form-data" method="post">
		<input value="${qpjyd.id}" name="qpjyd.id" type="hidden">
		<table class="table">
			<tr>
			<th width="30%">内容:</th>
			<td align="left">${qpjyd.jyContext}</td>
			</tr>
			<tr>
			<th width="30%">执行方案：
			</th>
				<td><textarea id="zxLog" rows="4" cols="100" name="qpjyd.zxLog">${qpjyd.zxLog}</textarea>
				</td>
			</tr>
			<tr>
			<th width="30%">
			文件:
			</th>
			<td>
			<input type="button" id="fileButton_1"
											onclick="uploadFile(this,1)" value="上传附件">
										<div id="fileDiv_1" style="display: none;">

										</div>
			</td>
			</tr>
			<tr>
			<td align="center" colspan="2"><input id="sureTbn" type="submit" value="确定" onclick="javascript:$('#accessory').uploadify('upload','*')">
			</td>
			</tr>
		</table>
		</form>
		<div align="center">
		<table class="table">
					<tr><td align="center" colspan="4">已上传的执行文件</td>
					</tr>
					<tr>
						<td align="center">文件</td>
						<td align="center">文件名称</td>
						<td align="center">上传日期</td>
						<td align="center">操作</td>
					</tr>
					<s:iterator value="qpjydFileList" id="pagejyFile">
					<s:if test="#pagejyFile.type=='执行文件'">
					<tr>
						<td align="center"><a
									href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/jy/<s:property value="#pagejyFile.fileName"/>">
									<img
										src="<%=path%>/upload/file/jy/<s:property value="#pagejyFile.fileName"/>"
										style="width: 80px; height: 80px;" />
								</a></td>
						<td align="center"><s:property value="#pagejyFile.oldFileName"/></td>
						<td align="center"><s:property value="#pagejyFile.addTime"/></td>
						<td align="center">
						<a href="QuotedPrice_DownloadjydFile.action?id=<s:property value="#pagejyFile.id"/>">下载</a>
						<s:if test="#session.Users.id==#pagejyFile.userId">
						<a href="javascript:void(0);" onclick="deletejyFile(<s:property value="#pagejyFile.id"/>)">删除</a>
						</s:if>
						</td>
					</tr>
					</s:if>
					</s:iterator>
				</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/uploadify/jquery.uploadify.min.js"></script>
<SCRIPT type="text/javascript">
$(function() {
    $('#accessory').uploadify({
  		'method'   : 'post',
        'swf'      : '${pageContext.request.contextPath}/javascript/uploadify/uploadify.swf',
        'uploader' : 'QuotedPrice_jytb.action;jsessionid=<%=session.getId()%>',//请求路径  
	    'auto' 	   : false, //是否自动上传 
	    'debug'    : false,
	    'fileObjName' : 'accessory',//和input的name属性值保持一致就好，Struts2就能处理了
	    'buttonText'     : '上传附件',//按钮上的文字
	    'onUploadStart' : function(file) {
        	$("#sureTbn").attr("disabled","disabled");
	   		 var zxLog=document.getElementById('zxLog').value;
	   		 var qpjydId=${qpjyd.id};
	  		 var obj={'qpjyd.id' :qpjydId,'qpjyd.zxLog':zxLog};
            $("#accessory").uploadify("settings", "formData", obj);
        },
        'onUploadSuccess' : function (file, data, response){
        	$("#sureTbn").removeAttr("disabled");
        	alert(data);
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
function deletejyFile(id){
	if(confirm("是否确定删除?")){
		window.location.href="QuotedPrice_deletejyFile.action?data=2&id="+id;
	}
}
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
</SCRIPT>
	</body>
</html>
