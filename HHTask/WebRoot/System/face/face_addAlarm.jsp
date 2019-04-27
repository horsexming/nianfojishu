<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>人脸列表</title>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
			#pictureFace{
				text-align: center;
				width: 80%;
				height: 500px;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h1 align="center" style="font-size: 25px;">添加人脸识别报警人员</h1>
				<br><br>
				<div  align="center" id="uploadDiv" style="width: 100%;">
					<h1 align="center" style="font-size: 20px;">选择图片上传</h1>
					<form action="${pageContext.request.contextPath}/faceAction!uploadAlarmFacePicture.action" 
							enctype="multipart/form-data" method="post" id="pictureForm">
						<br><br><br>
						<input type="hidden" name="pageStatus" value="alarm">
						编码：<input type="text" name="faceUsers.code"  placeholder="不填自动生成"><br>
						姓名：<input type="text" name="faceUsers.userName" ><br>
						<input type="hidden" name="pageStatus" value="alarm">
						<input type="hidden" name="faceUsers.groupName" value="alarm">
						<input type="file" name="picture" id="picture"><br>
						<br><br><br>
						<input type="button" value="提交" class="input" onclick="submitPicture()">
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		$(function(){
			$("#pageStatus").tinyselect();
		});
// 		$(function(){
// 			setInterval(loop,1000);
// 			function loop(){
// 				var canvas = document.getElementById("pictureCanvas");
// 				var picContext = canvas.getContext('2d');
// 				var image = new Image();
// 	    		image.src = "${pageContext.request.contextPath}/upload/file/dll/face/picture/face.jpg";
// 	    		image.onload= function(){
// 	    			var width = image.width/2;
// 	    			var height = image.height/2;
// 	    			picContext.clearRect(0,0, width , height);
// 					picContext.drawImage(image, 0, 0, width, height);
// 	    		}
// 			}
// 		});
		function submitPicture(){
			var picture = document.getElementById("picture");
			if (picture.value != '') {
				idx = picture.value.lastIndexOf(".");
				if (idx != -1) {
					ext = picture.value.substr(idx + 1).toUpperCase();
					if ( ext != 'JPG' && ext != 'JPEG' && ext != 'PNG' && ext!='bmp') {
						alert("只能上传.jpg,.jpeg,.png,.bmp类型的文件!");
						return false;
					}else{
						$("#pictureForm").submit();
					}
				} else {
					alert("只能上传.jpg,.jpeg,.png,.bmp类型的文件!");
					return false;
				}
			}else{
				alert("请选择文件");
			}
		}
		
		function pictureClickSubmit(){
			$("#pictureForm").submit();
		}
		function changeCamera(){
			var pageStatus = $("#pageStatus").val();
			location.href="${pageContext.request.contextPath}/faceAction!toAddFaceUsers.action?userId=${userId}&pageStatus="+pageStatus;
		}
		
// 		var dataURL=canvas.toDataURL('image/jpeg',1);
		</script>
	</body>
</html>
