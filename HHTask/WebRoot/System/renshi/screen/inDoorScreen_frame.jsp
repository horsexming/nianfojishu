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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
			*{
				padding: 0;
				margin: 0;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<iframe id="iframe" src="inDoorScreenAction!toScreenIndex.action?id=${id}" width="100%" height="100%" scrolling="no" frameborder=0></iframe>
			</div>
		</div>
		<div>
			<div id="bdtts_div_id">
				<audio id="tts_autio_id" autoplay="autoplay"> <source
					id="tts_source_id" src="" type="audio/mpeg"> <embed
					id="tts_embed_id" height="0" width="0" src=""></audio>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			$(function(){
				setInterval(function(){
					console.log("30分钟刷新页面");
					$('#iframe').attr('src', $('#iframe').attr('src'));
				},1000*60*30);
				
			});
			
			
			var pagesId = 0;
			var sta = true;
			$(function() {
				setInterval(tofind,1500);
				setTimeout(function(){
					console.log("750毫秒后执行点击下一步");
				},750);
				setInterval(tofindLeave,1500);
				
				setTimeout(function(){
					console.log("750毫秒后执行点击下一步");
				},750);
				setInterval(tofindFaceAlarm,1500);
				
				setTimeout(function(){
					console.log("750毫秒后执行点击下一步");
				},750);
				setInterval(tofindAlertMessageAlarm,1500);
			});

			function tofind() {
				if (sta) {
					sta = false;
					$.ajax( {
						type : "POST",
						url : "AttendanceTowAction_findSxtKq.action",
						dataType : "json",
						async : false,
						data : {
							id : pagesId
						},
						success : function(msg) {
							var username="";
							for(var i=0;i<msg.length;i++){
								username+=msg[i].name;
							}
							if(username!=""){
								pagesId = msg[0].id;
								if(username!="maxid"){
									doTTS(username+ "欢迎来办公");
									setTimeout(function(){
										console.log("12秒后执行点击下一步");
									},1200);
									$('#iframe').attr('src', $('#iframe').attr('src'));
								}
							}
							sta = true;
						}
					});
				}
			}

			/*离开语音提示*/
			var pagesIdLeave = 0;
			var staLeave = true;
			function tofindLeave() {
				if (staLeave) {
					staLeave = false;
					$.ajax( {
						type : "POST",
						url : "AttendanceTowAction_findSxtKqLeave.action",
						dataType : "json",
						async : false,
						data : {
							id : pagesIdLeave
						},
						success : function(msg) {
							var username="";
							for(var i=0;i<msg.length;i++){
								username+=msg[i].name;
							}
							if(username!=""){
								pagesIdLeave = msg[0].id;
								if(username!="maxid"){
									doTTS(username+ "离开");
									$('#iframe').attr('src', $('#iframe').attr('src'));
								}
							}
							staLeave = true;
						}
					});
				}
			}
			
			/*报警使用*/
			var pagesIdAlarm = 0;
			var staAlarm = true;
			function tofindFaceAlarm() {
				if (staAlarm) {
					staAlarm = false;
					$.ajax( {
						type : "POST",
						url : "inDoorScreenAction!findSxtFaceAlarm.action",
						dataType : "json",
						async : false,
						data : {
							id : pagesIdAlarm
						},
						success : function(msg) {
							var username="";
							for(var i=0;i<msg.length;i++){
								username+=msg[i].userName;
							}
							if(username!="" && pagesIdAlarm!=0){
								pagesIdAlarm = msg[0].id;
								if(username!="maxid"){
									doTTS(username+ "非法闯入");
								}
							}
							staAlarm = true;
// 						},error:function(){
// 							alert("非法闯入语音加载失败");
						}
					});
				}
			}
			
			
			/*报警使用*/
			var pagesIdAlertMessageAlarm = 0;
			var staAlertMessageAlarm = true;
			function tofindAlertMessageAlarm() {
				if (staAlertMessageAlarm) {
					staAlertMessageAlarm = false;
					$.ajax( {
						type : "POST",
						url : "inDoorScreenAction!findSxtFaceAlertMessageAlarm.action",
						dataType : "json",
						async : false,
						data : {
							id : pagesIdAlertMessageAlarm
						},
						success : function(msg) {
							if(msg!=null && msg.length>0){
								if(pagesIdAlertMessageAlarm>0){
									doTTS("不明人员进入");
								}
								pagesIdAlertMessageAlarm = msg[0];
							}
							staAlertMessageAlarm = true;
// 						},error:function(){
// 							alert("非法闯入语音加载失败");
						}
					});
				}
			}
			
			
			function doTTS(ttsText) {
				var ttsDiv = document.getElementById('bdtts_div_id');
				var ttsAudio = document.getElementById('tts_autio_id');
				//var ttsText = document.getElementById('ttsText').value;

				// 文字转语音
				ttsDiv.removeChild(ttsAudio);
				var au1 = '<audio id="tts_autio_id" autoplay="autoplay">';
				var sss = '<source id="tts_source_id" src="http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&per=3&spd=5&text=' + ttsText + '" type="audio/mpeg">';
				var eee = '<embed id="tts_embed_id" height="0" width="0" src="">';
				var au2 = '</audio>';
				ttsDiv.innerHTML = au1 + sss + eee + au2;

				ttsAudio = document.getElementById('tts_autio_id');

				ttsAudio.play();
			}
		</script>
	</body>
</html>
