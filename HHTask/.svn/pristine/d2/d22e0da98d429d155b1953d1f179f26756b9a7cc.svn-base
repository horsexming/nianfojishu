<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js"></script>
<style type="text/css">
html, body, img {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}

img {
	height: auto;
}
</style>
</head>
<body>
	<img src="${pageContext.request.contextPath}/upload/file/dll/face/picture/${param.pageStatus}"
		alt="摄像头图片" id="picture" onclick="clickImg()">
</body>
<script type="text/javascript">
	setTimeout(loop, 5000);
	function loop() {
		location.reload();
	}
	function clickImg() {
		//location.href = "${pageContext.request.contextPath}/faceAction!uploadFacePicture.action";
		parent.pictureClickSubmit();
	}

// 	function getBase64Image(img) {
// 		var canvas = document.createElement("canvas");
// 		canvas.width = img.width;
// 		canvas.height = img.height;
// 		var ctx = canvas.getContext("2d");
// 		ctx.drawImage(img, 0, 0, img.width, img.height);
// 		var dataURL = canvas.toDataURL("image/png");
// 		return dataURL
// 		// return dataURL.replace("data:image/png;base64,", "");
// 	}

// 	function main() {
// 		var img = document.createElement('img');
// 		img.src = './images/Game of Thrones.jpg'; //此处自己替换本地图片的地址
// 		img.onload = function() {
// 			var data = getBase64Image(img);
// 			var img1 = document.createElement('img');
// 			img1.src = data;
// 			document.body.appendChild(img1);
// 			console.log(data);
// 		}
// 	}
</script>
</html>
