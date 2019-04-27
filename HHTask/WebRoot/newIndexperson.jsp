<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--     <base href="<%=basePath%>"> --%>
<title>PEBS生产力生态平衡系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=10; IE=9; IE=8; IE=7; IE=EDGE">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/util/sonHead.jsp"%>

<link rel="stylesheet"
	href="<%=basePath%>javascript/typeahead.js/sousuo.css" />

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>javascript/bootstrap-datetimepicker/bootstrap.min.js"></script>
<!-- 日历 -->
<script src="<%=basePath%>javascript/edsk/calendar.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>javascript/edsk/calendar.css">
<!-- 柱状图 -->
 <script type="text/javascript"
	src="<%=basePath%>javascript/edsk/simplex.js"></script>


<script type="text/javascript"
	src="<%=basePath%>javascript/edsk/echarts.min.js"></script>
 <script type="text/javascript"
	src="<%=basePath%>javascript/edsk/echarts-gl.min.js"></script> 
	

<script type="text/javascript"
	src="<%=basePath%>javascript/edsk/ecStat.min.js"></script>
<!-- 饼图 -->
<script type="text/javascript"
	src="<%=basePath%>javascript/edsk/dataTool.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>javascript/edsk/china.js"></script>
<script type="text/javascript"
	src="<%=basePath%>javascript/edsk/world.js"></script>
<script type="text/javascript"
	src="<%=basePath%>javascript/edsk/bmap.min.js"></script>
<!-- 堆积柱状图 -->
<!-- 搜索框 -->
<script type="text/javascript"
	src="<%=basePath%>javascript/typeahead.js/typeahead.bundle.min.js"></script>
<script language="javascript"
	src="<%=basePath%>javascript/typeahead.js/typeahead.bundle.js"></script>


<link href="<%=basePath%>css/prettify.css" type="text/css">
<script src="<%=basePath%>js/prettify.js"></script>
<script type="text/javascript"
	src="<%=basePath%>javascript/edsk/bootstrap-contextmenu.js"></script>
<script type="text/javascript" src="<%=basePath%>javascript/edsk/echarts.js"></script>
<script type="text/javascript" src="<%=basePath%>javascript/edsk/respond.min.js"></script>
<style type="text/css">

html{
height:100%;
}
body {
 margin:0 auto;
	width: 100%;
	min-height:100%;
}

#aox1 {
	width: 0;
	border-left: 27px solid transparent;
	border-right: 27px solid transparent;
	border-bottom: 10px solid #00417F;
}

#aox2 {
	width: 54px;
	height: 35px;
	background-color: #00417F;
}

#aox3 {
	width: 0;
	border-top: 10px solid #00417F;
	border-left: 27px solid transparent;
	border-right: 27px solid transparent;
}

#box1 {
	width: 0;
	border-left: 27px solid transparent;
	border-right: 27px solid transparent;
	border-bottom: 10px solid #CFD1DE;
}

#box2 {
	width: 54px;
	height: 35px;
	background-color: #CFD1DE;
}

#box3 {
	width: 0;
	border-top: 10px solid #CFD1DE;
	border-left: 27px solid transparent;
	border-right: 27px solid transparent;
}

.xuanfu:hover {
	background-color: #f5f5f5;
	box-shadow: 2px 2px 2px #EAEBEE, 2px -2px 2px #EAEBEE, -2px 2px 2px
		#EAEBEE, -2px -2px 2px #EAEBEE;
}

#Marquee {
	height: 25px;
	overflow: hidden;
}

#Marquee div {
	height: 58px;
}

.tip {
	position: relative;
	background-color: #337AB7;
	margin: 20px auto;
	width: 246px;
	height: 43px;
	line-height: 43px;
	color: #FFFFFF;
	text-align: center;
	border-radius: 10px;
	font-family: sans-serif;
	display: none;
}

.tip:after {
	content: '';
	position: absolute;
	width: 0;
	height: 0;
	border: 8px solid;
}

.top:after {
	border-bottom-color: #FFFFFF;
	left: 50%;
	bottom: 100%;
	margin-left: -15px;
}

.right:after {
	border-left-color: #FFFFFF;
	left: 100%;
	top: 50%;
	margin-top: -7px;
}

.bottom:after {
	border-top-color: #FFFFFF;
	top: 100%;
	left: 50%;
	margin-left: -15px;
}

.left:after {
	border-right-color: #FFFFFF;
	top: 50%;
	right: 100%;
	margin-top: -9px;
}

.zhodai {
	-ms-transform: scale(1, 1);
	-moz-transform: scale(1, 1);
	-webkit-transform: scale(1, 1);
	-o-transform: scale(1, 1);
	transform: scale(1, 1);
}

.zhodai:hover {
	-ms-transform: scale(1.2, 1.2);
	-moz-transform: scale(1.2, 1.2);
	-webkit-transform: scale(1.2, 1.2);
	-o-transform: scale(1.2, 1.2);
	transform: scale(1.2, 1.2);
}

.addliu {
	-ms-transform: scale(1, 1);
	-moz-transform: scale(1, 1);
	-webkit-transform: scale(1, 1);
	-o-transform: scale(1, 1);
	transform: scale(1, 1);
}

.addliu:hover {
	-ms-transform: scale(1.2, 1.2);
	-moz-transform: scale(1.2, 1.2);
	-webkit-transform: scale(1.2, 1.2);
	-o-transform: scale(1.2, 1.2);
	transform: scale(1.2, 1.2);
}
/*图片遮罩层*/
.demo {
	padding: 2em 0;
}

.box {
	text-align: center;
	overflow: hidden;
	position: relative;
	s
}

.box:before {
	content: "";
	width: 0;
	height: 100%;
	background: #000;
	padding: 14px 18px;
	position: absolute;
	top: 0;
	left: 50%;
	opacity: 0;
	transition: all 500ms cubic-bezier(0.47, 0, 0.745, 0.715) 0s;
}

.box:hover:before {
	width: 100%;
	left: 0;
	opacity: 0.5;
}

.box img {
	width: 50%;
	height: 120px;
	/*     height: auto; */
}

.box .box-content {
	width: 100%;
	padding: 14px 18px;
	color: #fff;
	position: absolute;
	top: 38%;
	left: 0;
}

.box .title {
	line-height: 30px;
	text-transform: uppercase;
	margin: 0;
	opacity: 0;
	transition: all 0.5s ease 0s;
}

.box .post {
	font-size: 15px;
	text-transform: capitalize;
	opacity: 0;
	transition: all 0.5s ease 0s;
}

.box:hover .title, .box:hover .post {
	opacity: 1;
	transition-delay: 0.7s;
}

.box .icon {
	padding: 0;
	margin: 0;
	list-style: none;
	margin-top: 15px;
}

.box .icon li {
	display: inline-block;
}

.box .icon li a {
	display: block;
	width: 40px;
	height: 40px;
	line-height: 40px;
	border-radius: 50%;
	background: #f74e55;
	font-size: 20px;
	font-weight: 700;
	color: #fff;
	margin-right: 5px;
	opacity: 0;
	transform: translateY(50px);
	transition: all 0.5s ease 0s;
}

.box:hover .icon li a {
	opacity: 1;
	transform: translateY(0px);
	transition-delay: 0.5s;
}
/*  .box:hover .icon li:last-child a{
    transition-delay: 0.8s;
  }*/
@media only screen and (max-width:990px) {
	.box {
		margin-bottom: 30px;
	}
}

.div2:before { /*做一个书签效果*/
	position: absolute; /*必须*/
	top: 50px;
	left: 20px;
	z-index: 1;
	height: 0;
	padding-right: 10px;
	font-weight: bold;
	line-height: 0;
	color: #000;
	border: 15px solid #ee7600;
	border-right-color: transparent; /*右边框透明，变成空缺的角*/
	content: '使用中';
	box-shadow: 0 5px 5px -5px #000;
	margin-top: -40px;
}

.div2:after { /*书签的夹角*/
	content: '';
	position: absolute;
	top: 80px;
	left: 20px;
	border: 4px solid #89540c;
	border-left-color: transparent;
	border-bottom-color: transparent;
	margin-top: -40px;
}
</style>



</head>

<body>
<!-- class="container"  -->
<div class="container" style="width: 100%;background-color: #F7F8FC;height: 100%;">
	
	<input type="text" value="${Users.id}" style="display: none;"
		class="userid">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-4 column" align="left"
					style="height: 10%;width: 33.3%;">
					<img src="<%=basePath%>/img/desk/desk.png" id="edsk">
				</div>
				<!--中间  -->
				<div class="col-md-4 column" style="height: 10%;width: 33.3%;">

					<div style="float: left;width: 100%;">
						<div style="float: right;">
							<font style="font-size: 60px;text-align:center;" color="#E4E5E9">"</font>
						</div>
						<div style="float: left;">
							<font style="font-size: 60px;text-align:center;" color="#E4E5E9">"</font>
						</div>
						<div>
							<p style="color: #E4E5E9;text-align:center;">关键目标:</p>
							<div id="Marquee"></div>

						</div>

					</div>

				</div>
				<div class="col-md-4 column" style="height: 10%;width: 33.3%;"
					align="right">

					<!-- 头像 -->
					<div
						style="float: left;margin-left: 200px;border-radius:50%;overflow:hidden;">
						<s:if test='#session.Users.sex =="男"'>
							<img src="upload/user/${Users.password.picture}" height="60px"
								width="60px" onerror="this.src='images/man.jpg'">
						</s:if>
						<s:else>
							<img src="upload/user/${Users.password.picture}" height="60px"
								width="60px" onerror="this.src='images/woman.jpg'">
						</s:else>

					</div>
					<!-- 基础工作情况 -->
					<div>
						<div>
							<font style="font-size:20px;" class="name">${Users.name}</font>${Users.duty}</div>
						<div>
							<font style="font-size:15px;">积分:</font>206
						</div>
						<div id="huors">
							<font style="font-size:15px;">工作累计(小时):</font>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- 中部内容 -->
	<div class="row clearfix">
		<div class="col-md-6 column"
			style="height:40%;width:48%;margin:5px auto;border:2px solid #EAEBEE;box-shadow:3px 3px 3px #EAEBEE,3px -3px 3px #EAEBEE,-3px 3px 3px #EAEBEE,-2px -3px 3px #EAEBEE;margin-left: 15px;background-color: #FFFFFF;">

			<div style="margin-top: 20px;">
				<div id="zhucaidan"></div>
				<div>
					<!-- 				<div data-toggle="modal" data-target="#myModal"
							>
							<div id="box1"></div>
							<div id="box2" align="center"
								style="color:#FFFFFF;font-size:20px;">+</div>
							<div id="box3"></div>
						</div>  -->

					<div data-toggle="modal" data-target="#myModal" title="收藏夹"
						class='addliu'
						style='float: left;width:60px;height:60px;border:1px solid 2C97DE;background-color:#2C97DE;border-radius:10px;text-align:center;line-height:60px;font-size:20px;margin-left: 30px;color:#FFFFFF;background-color:#CFD1DE;'>+</div>
				</div>
			</div>


			<div align="right" style="margin-top: 140px;">
				<!--  -->
				<div style="float: left;margin-left: 140px;" id="duihuakuang">

				</div>



				<div id="context" data-toggle="context" data-target="#context-menu" >
					<%-- 	<img src="<%=basePath%>/img/desk/xiaor.png" width="26%"
							height="50%" /> --%>
				</div>


				<div id="context-menu">
					<ul class="dropdown-menu" role="menu">
						<li class="moregen"><a tabindex="-1" class="chak"
							onclick="jixwuku()";>查看更多吉祥物</a></li>
						<li class="divider"></li>
						<li class="quxiao"><a tabindex="-1" class="ququ">取消</a></li>
					</ul>
				</div>


			</div>
		</div>
		<div class="col-md-6 column"
			style="height:40%;width:48%;margin:5px auto;border:2px solid #EAEBEE;box-shadow:3px 3px 3px #EAEBEE,3px -3px 3px #EAEBEE,-3px 3px 3px #EAEBEE,-3px -3px 3px #EAEBEE;margin-left: 26px;background-color: #FFFFFF;">

			<!-- 天气 -->
			<div style="float: left;" id="weather">
				<iframe name="weather_inc" src="" width="255" height="220"
					frameborder="0" marginwidth="0" marginheight="0" scrolling="no"
					class="tianqi"></iframe>
			</div>
			<!-- 隐藏内容显示消息 -->
			<div
				style="float: left;margin:20px auto;height:80%;width: 45%; overflow:auto;border: none;display: none;"
				id="taskadd">
				<img alt="返回" src="<%=basePath%>/img/desk/back.png" align="right"
					class="back"><br /> <br />

			</div>

			<!-- 日历 -->
			<div align="right">
				<div id="ca" align="center"></div>
			</div>

		</div>
	</div>
	<!-- 下部内容		 -->
	<div class="row clearfix">
		<div class="col-md-6 column"
			style="height:43%;width:48%;margin:20px auto;border:2px solid #EAEBEE;box-shadow:3px 3px 3px #EAEBEE,3px -3px 3px #EAEBEE,-3px 3px 3px #EAEBEE,-3px -3px 3px #EAEBEE;margin-left:15px;background-color: #FFFFFF; ">
			<div id="container2" style="height: 100%;width: 100%;"></div>
		</div>
		<div class="col-md-6 column">
			<div class="row clearfix" style="margin-left: 10px;">
				<div class="col-md-6 column"
					style="height:43%;width:45%;margin:20px auto;border:2px solid #EAEBEE;box-shadow:3px 3px 3px #EAEBEE,3px -3px 3px #EAEBEE,-3px 3px 3px #EAEBEE,-3px -3px 3px #EAEBEE;background-color: #FFFFFF;">
					<!--[if IE]> 
					<div id="chart" style="height: 100%;width: 50%;"></div>
					<![endif]-->
					<div id="chart" style="height: 100%;"></div>
				</div>
				<div class="col-md-6 column"
					style="height:43%;width:45%;margin:20px auto;border:2px solid #EAEBEE;box-shadow:3px 3px 3px #EAEBEE,3px -3px 3px #EAEBEE,-3px 3px 3px #EAEBEE,-3px -3px 3px #EAEBEE;margin-left: 60px;background-color: #FFFFFF;">
					<!--[if IE]> 
					<div id="duiji" style="height: 100%;width: 50%"></div>
					<![endif]-->
					<div id="duiji" style="height: 100%;width: 100%"></div>
				</div>
			</div>
		</div>
	</div>




	<script type="text/javascript">
	
		var useid=$(".userid").val();
		if(useid !=null && useid !=""){
		
		
 	  $(".moregen").hover(function(){
    	$(".moregen").css({"cursor":"pointer"});      
    	$(".chak").css({"background-color":"#0084C7"});      
    },function(){
    $(".chak").css({"background-color":"#FFFFFF"}); 
          }); 
          
	  $(".quxiao").hover(function(){
    	$(".quxiao").css({"cursor":"pointer"}); 
    	$(".ququ").css({"background-color":"#0084C7"});      
    },function(){
    $(".ququ").css({"background-color":"#FFFFFF"});      
          });




	$.ajax({
		//查看
			url : 'AlertMessagesAction!lookimng.action',
			dataType : 'json',
			cache : false,//防止数据缓存
			success : function(lsitdesk) {
			var seu=lsitdesk.success
			var message=lsitdesk.message;
			var data1 =lsitdesk.data1;
			var data2 =lsitdesk.data2;
			if(message=='返回状态'){
			if(seu==true){
			
			//有图片的时候
			if(data1.length>0){
			var img="";
			$(data1).each(function(){	
				if(data2.imgid==this.id){
				img=this.img;
				if(!!window.ActiveXObject || "ActiveXObject" in window){
					$("<img  src='<%=basePath%>/upload/file/img/"+this.img+"'  width='26%' height='100px;'  />").appendTo("#context");
				}else{
			$("<img  src='<%=basePath%>/upload/file/img/"+this.img+"'  width='26%' height='100px;'  />").appendTo("#context");
			}
			
				}
			});
			}
			
			if(img !=""){
			if(!!window.ActiveXObject || "ActiveXObject" in window){
			$("<img  src='<%=basePath%>/upload/file/img/"+img+"'  width='26%' height='100px;'   />").appendTo("#context");
			}else{
			$("<img  src='<%=basePath%>/upload/file/img/"+img+"'  width='26%'  height='50%'  />").appendTo("#context");
			}
			
			}else{
			/* alert(1); */
			if(!!window.ActiveXObject || "ActiveXObject" in window){
			      $("<img  src='<%=basePath%>/img/desk/xiaor.png' width='26%' height='100px;'  />").appendTo("#context");
			   }else{
			   	$("<img  src='<%=basePath%>/img/desk/xiaor.png' width='26%' height='50%'  />").appendTo("#context");
			   }
						
		
			
			}
			
			}
			
			}else{
			alert("服务器没有吉祥物图片可查看")
			}
			
			},
			error : function() {		
				alert("吉祥物图片获取失败");

			}
	});

	
	//点击任务查看吉祥物库
	function jixwuku(){
	//查看
	$.ajax({
	url : 'AlertMessagesAction!lookimng.action',
			dataType : 'json',
			
			cache : false,//防止数据缓存
			success : function(lsitdesk) {
			 var seu=lsitdesk.success
			var message=lsitdesk.message;
			var data1 =lsitdesk.data1;
			var data2 =lsitdesk.data2;
			if(message=='返回状态'){
			if(seu==true){
			
			if(data1.length>0){
			$('#jxwModal').modal();
			$(data1).each(function(){	
				if(data2.imgid==this.id){
			$("<div class='col-md-3'>"+
			 "<div class='box' ><img  src='upload/file/img/"+this.img+"'  />"+
			 "<div class='div2'></div><div class='box-content'>"+
			 "<span class='title'> <a href='javascripit:vodi(0)' onclick='updatename("+this.id+")'  class='btn btn-danger'>更换吉祥物</a> </span>"+
			 "<p class='post'>P E B S</p> </div></div>").appendTo('#jianxwutup');
				}else{		
			$("<div class='col-md-3'>"+
			 "<div class='box' ><img  src='upload/file/img/"+this.img+"'  />"+
			 "<div class='box-content'>"+
			 "<span class='title'> <a href='javascripit:vodi(0)' onclick='updatename("+this.id+")'  class='btn btn-danger'>更换吉祥物</a> </span>"+
			 "<p class='post'>P E B S</p> </div></div>").appendTo('#jianxwutup');
			}

			 
			});
			
			}
			
			}
			
			}else{
			alert("服务器没有吉祥物图片可查看")
			}
			
			},
			error : function() {		
				alert("吉祥物图片获取失败");

			}
	
	});
	}
	
	
	function updatename(id){
	$.ajax({
	url:"AlertMessagesAction!updatexianshiimgid.action",
	dataType : 'json',
	data:{
			'id':id,
		},
		
	cache : false,//防止数据缓存
	success : function(jieguo) {
	/* alert(jieguo); */
	},
	error : function() {		
		alert("切换吉祥物显示失败");

		}
	});
	
	}
	
	
	
	var NowImg=1;//表示当前显示的div
	var MaxImg=0;//表示div的个数 
	var str ="no";
	$.ajax({
			url : 'AlertMessagesAction!findAlertMessagesajax.action',
			dataType : 'json',
			data:{
				'alertMessages.readStatus':str,
			},
			cache : false,//防止数据缓存
			success : function(alertMessagesList) {
			 MaxImg=alertMessagesList.length;
				var co=0;
			$(alertMessagesList).each(function(){
				co=parseInt(co)+1;
				$("<div class='tip right' id='tixing"+co+"' style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'>"+co+"`<a   href='"+this.functionUrl+"'  alt='"+this.content+"'   style='color:#FFFFFF;'>"+this.content+"</a></div>").appendTo('#duihuakuang');
				})

			},
			error : function() {		
				alert("提醒消息获取失败");

			}
			
	});
	
	
function show(MaxImg,NowImg){
		if(MaxImg>0){
		for(var i=1;i<=MaxImg;i++){
				if(NowImg==i)
				document.getElementById("tixing"+NowImg).style.display='block';//当前显示的div
				else
				document.getElementById("tixing"+i).style.display='none';
			}
		}
}	

 var set1 = setInterval(function(){
	show(MaxImg,NowImg)
	if(NowImg==MaxImg)//判断当前div是否是最后一个，如果是则从第一个重新轮回显示
		NowImg=1;
		else
		NowImg++;//设置下一个显示的图片
},5000); 
	
	

	
	
	
$.ajax({
url : 'FavoriteAction_showajax.action',
			dataType : 'json',
			cache : false,//防止数据缓存
			success : function(listFavorites) {
			var co=0;
			//循环
			$(listFavorites).each(function(){
			co=parseInt(co)+1;
			
			 if(co==1) {
//<input type='text' style='border-radius:18px;width: 33px;height: 20px;background-color: #FFFFFF;border:1px  solid #DBDBDB;outline:none;  margin-top: -10px;margin-left: 35px;color: #00417F;text-align:center;' readonly='readonly' value='999'>
//<input type='text' style='border-radius:18px;width: 33px;height: 20px;background-color: #FFFFFF;border:1px  solid #DBDBDB;outline:none;  margin-top: -10px;margin-left: 35px;color: #00417F;text-align:center;' readonly='readonly' value='999'>	
			/* $("	<div style='float: left;margin-left:20px;' class='zhodai'   onclick='changeiframe("+this.mfid+")' align='center'>"+
			"<div id='aox1'></div>"+
			"<div id='aox2' align='center' style='color:#FFFFFF;font-size:10px;'>"+this.name+"</div>"+
			"<div id='aox3'></div>"+
			"</div>").appendTo('#zhucaidan'); */
			var name=this.name;
			if(name.length>4){
			
			if(name!=""|| name!=null){
			name = name.replace("查询", ""); 
			name = name.replace("添加", ""); 
			name = name.replace("查看", "");
			name = name.replace("管理", "");
			name = name.replace("记录", "");
			name = name.replace("规范", "");
			}
			}
			name=name.substr(0, 4);
			//name
			$("<div    title='"+this.name+"'  class='zhodai' style='float: left;width:60px;height:60px;border:1px solid 2C97DE;background-color:#2C97DE;border-radius:10px;text-align:center;line-height:60px;font-size:13px;margin-left: 30px;color:#F7F8FC' align='center' onclick='changeiframe("+this.mfid+")'>"+name+"</div>").appendTo('#zhucaidan');
			}  else if(co==2 || co==3|| co==4|| co==5||co==6 ){
			var name=this.name;
			if(name.length>5){
			if(name!=""|| name!=null){
			name = name.replace("查询", ""); 
			name = name.replace("添加", ""); 
			name = name.replace("查看", "");
			name = name.replace("修改", "");
			name = name.replace("管理", "");
			}
			}
			name=name.substr(0, 4);
			$("<div   title='"+this.name+"'  class='zhodai' style='float: left;width:60px;height:60px;border:1px solid 2C97DE;background-color:#2C97DE;border-radius:10px;text-align:center;line-height:60px;font-size:13px;margin-left: 30px;color:#F7F8FC' onclick='changeiframe("+this.mfid+")'>"+name+"</div>").appendTo('#zhucaidan');

			}else if(co>6){
			
			}   
 
			});
			},error : function() {		
				alert("收藏夹数据获取失败");
			}
}); 





$(function(){

		var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        //当前日期 年月日
        var taday = year + seperator1 + "01" +seperator1 + "01";
	
	
	$.ajax({
	url : 'WageAction!gonzi.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(wageList) {
		//月份
		var riqi=[];
		//工资
		var gongzi=[];
		//借款
		var jiekuan=[];
		$(wageList).each(function(){
		riqi.push($(this).attr('mouth').substring(5, $(this).attr('mouth').length));
		gongzi.push($(this).attr('shifagongzi'));
		jiekuan.push($(this).attr('zxfjkc'));
		});
	
	

var dom = document.getElementById("duiji");
if(!!window.ActiveXObject || "ActiveXObject" in window){
$('#duiji').width($('#duiji').width());
var heirht= $('#duiji').height();
heirht=heirht/2;
 $('#duiji').height(heirht);
    }
 var myChart = echarts.init(dom); 



 

var app = {};
ddzzt = null;
app.title = '堆叠柱状图';
	
					ddzzt = {
						tooltip : {
							trigger : 'axis',
							axisPointer : { // 坐标轴指示器，坐标轴触发有效
								type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
							},
						},
						legend : {
							data : [ '负债', '自有资产' ]
						},
						grid : {
							left : '3%',
							right : '4%',
							bottom : '3%',
							containLabel : true
						},
						xAxis : [
							{
								type : 'category',
								data : riqi,
							}
						],
						yAxis : [
							{
								type : 'value'
							}
						],
						series : [
	
							{
								name : '自有资产',
								type : 'bar',
								stack : '广告',
								barWidth : 27,
								data : gongzi,
	 							itemStyle : {
									normal : {
										color : '#33888F',
									}
								}
							},
	
							{
								name : '负债',
								type : 'bar',
								stack : '广告',
								barWidth : 27,
								data : jiekuan,
							}

						]
					};
	
					if (ddzzt && typeof ddzzt === "object") {
						myChart.setOption(ddzzt, true);
					}
	
	
	
		myChart.on('click', function (params) {
		if(params.componentType =="xAxis"){ 
		var mouth= params.name
		mouth=mouth.replace("月", "")
		//详细
		$.ajax({
		url : 'WageAction!gonzixiangxi.action',
		dataType : 'json',
		data : {
				shihuamouth: mouth,
			},
		cache : false,//防止数据缓存
		success : function(wage) {
		if(wage !=null){
		$('#xiangxiModal').modal();
		$("#tougengai").text("个人财务:"+wage.mouth);
			$("<table style='border-collapse: collapse; border-spacing: 0;width:100%;'><tr><td>序号</td><td>姓名</td><td>部门</td><td>应发月份</td><td>状态</td><td>自有资产</td><td>负债</td></tr><tr><td>1</td><td>"+wage.userName+"</td><td>"+wage.dept+"</td><td class='mouth'>"+wage.mouth+"</td><td>"+wage.wageStatue+"</td><td>"+wage.shifagongzi+"</td><td>"+wage.zxfjkc+"</td><td><input type='button' onclick='clickon()' value='详细数据'></td></tr></table>").appendTo("#xaingxishuju");
		}
		}
		
		});
		
		}else{ 
		var mouth= params.name
		mouth=mouth.replace("月", "")
		$.ajax({
		url : 'WageAction!gonzixiangxi.action',
		dataType : 'json',
		data : {
				shihuamouth: mouth,
			},
		cache : false,//防止数据缓存
		success : function(wage) {
		if(wage !=null){
		$('#xiangxiModal').modal();
		$("#tougengai").text("个人财务:"+wage.mouth);
		$("<table style='border-collapse: collapse; border-spacing: 0;width:100%;'><tr><td>序号</td><td>姓名</td><td>部门</td><td>应发月份</td><td>状态</td><td>自有资产</td><td>负债</td></tr><tr><td>1</td><td>"+wage.userName+"</td><td>"+wage.dept+"</td><td class='mouth'>"+wage.mouth+"</td><td>"+wage.wageStatue+"</td><td>"+wage.shifagongzi+"</td><td>"+wage.zxfjkc+"</td><td><input type='button' onclick='clickon()' value='详细数据'/></td></tr></table>").appendTo("#xaingxishuju");
		}
		}
		});


			} 
       });  
       
       
       
			},
		error : function() {
							alert("数据异常")
							}	
	});







//第二个饼状图
 		$.ajax({
		url : 'CalendarAction!time.action',
			dataType : 'json',
			data : {
				id: useid,
			},
			cache : false,//防止数据缓存
			success : function(users) {
			//休息时长
			var overtime=24-parseInt(users.successHours);
	//工作时间users.successHours
	var huor=users.successHours+"";
	var str = huor.substring(0,huor.indexOf(".") + 3);
	var oDiv=document.getElementById("huors");
    var txt =document.createTextNode(huor);
	oDiv.appendChild(txt);
	
	
	var dom = document.getElementById("chart");
	if(!!window.ActiveXObject || "ActiveXObject" in window){
$('#chart').width($('#chart').width());
var heirht= $('#chart').height();
heirht=heirht/2;
 $('#chart').height(heirht);
    }
var myChart = echarts.init(dom);
var app = {};
fenxi = null;
fenxi = {
    title : {
        text: '工效分析',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['有效时长','离岗时长','休息时长']
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:users.successHours, name:'有效时长',itemStyle:{color:'#6AB0B8'}},
                {value:overtime, name:'离岗时长',itemStyle:{color:'#334B5C'}},
                {value:0, name:'休息时长',itemStyle:{color:'#FDA844'}},
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

if (fenxi && typeof fenxi === "object") {

    myChart.setOption(fenxi, true);
    

}

			},
				error : function() {
				alert("工效分析数据回掉异常");
						}

					}); 

var daydya = year + "/" +month+"/" +strDate ;
	//名称
	var category=[];
	//页面进行时
	var timeing=[];
	//标准时间
	var baizhun=[];
	//名称
	var  mingc=[];
	//超时时间
	var timemore=[];
	var ids =[];
	//第一个柱状图
	 $.ajax({
	 url : 'CircuitRunAction!gonzuoliu.action',
			dataType : 'json',
			data : {
				addUserId: useid,
			},
			cache : false,//防止数据缓存
			success : function(calendarList) {
			 var panduan= calendarList.success;
			 if(panduan==true){
			 
		
		
			var biao=calendarList.message
			var calendar =	calendarList.data
			
			//循环
			$(calendar).each(function(){
			//名称
			mingc.push( $(this).attr('name'));
			 
			//当前时间
			var ay=new Date(daydya);

			//去除时分秒
			var newDate=/\d{4}-\d{1,2}-\d{1,2}/g.exec($(this).attr('addDateTime'));
			

			newDate=newDate[0].replace(/-/g,'/');
			
			var addtime=new Date(newDate);
			 var time=ay - addtime;
			 
			 //进行时长
			var minute1 =   parseInt(time / (1000 * 60 * 60 * 24));

			var  minute = Math.abs(minute1);
			
			//id
			ids.push($(this).attr('id'));
			//添加页面进行
			timeing.push(minute);
			//标准时间添加
			baizhun.push(biao);
			//标准时间 and 进行时间
			   if(parseInt(minute)<parseInt(biao)){   
			   //添加超时
			   timemore.push(0);
			   }else{
			   var timeo= parseFloat(minute-biao);
			 	timemore.push(timeo);
			   }
			});
				 }
				 gonliu();
				 
function gonliu(){		
var dom = document.getElementById("container2");
if(!!window.ActiveXObject || "ActiveXObject" in window){
$('#container2').width($('#container2').width());
var heirht= $('#container2').height();
 heirht=heirht/2;
 $('#container2').height(heirht);
    }
var myChart = echarts.init(dom);
var app = {};
option = null;

option = {
    title: {
        text: '正在进行工作流分析',
        textStyle: {
        fontSize: 13
        }
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['节点总超时长', '进行时长','标准时长']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data:  mingc   /* ['报销','项目1','招聘','领用']  */
    },
    series: [
    
        {
            name: '节点总超时长',
            type: 'bar',
            data: timemore,  /* [50, 1, 80, 30] */
            itemStyle:{
                                    normal:{
                                        color:'#FDA844'
                                    }
                                },
        },
        {
            name: '进行时长',
            type: 'bar',
            data: timeing,    /* [120, 72, 170, 120] */
                        itemStyle:{
                                    normal:{
                                        color:'#003A7E'
                                    }
                                },
        },
        {
            name: '标准时长',
            type: 'bar',
            data: baizhun,   /* [90, 130, 90, 90] */
                        itemStyle:{
                                    normal:{
                                        color:'#89C4EB'
                                    }
                                },
        }
    ]
};

if (option && typeof option === "object") {
    myChart.setOption(option, true);
						}
						myChart.on('click', function(params) {
							if (params.componentType == "xAxis") {
								alert("单击了" + params.value + "x轴标签, 你点击的是第" + (Number(params.event.target.anid.split("_")[1]) + 1) + "个X标签；当前对应的id为：" + ids[params.event.target.anid.split("_")[1]]);
							} else {
									var id = ids[params.dataIndex];
	
								$.ajax({
									url : 'CircuitRunAction!xiangxi.action',
									dataType : 'json',
									data : {
										id : id
									},
	
									cache : false, //防止数据缓存
									success : function(circuitRun) {
									if(circuitRun==null){
									alert("无数据可查询");
									}else{

									$('#xiangxiModal').modal();
									$("#tougengai").text("工作流分析");
									$("<table  border='1' cellspacing='0' cellpadding='0'  style='width:100%;'>"+
									"<tr><td align='center'>类型</td><td align='center'>具体详情</td><td align='center'>状态</td>"+"<td align='center'>添加时间</td><td align='center'>发布人</td><td align='center'>部门</td></tr>"+
									"<tr><td align='center'>"+circuitRun.name+"</td><td align='center'>"+circuitRun.message+"</td><td align='center'>"+circuitRun.allStatus+"</td><td align='center'>"+circuitRun.addDateTime+"</td><td align='center'>"+circuitRun.addUserName+"</td><td align='center'>"+circuitRun.addUserDept+"</td></tr>"+
									"</table>").appendTo("#xaingxishuju");									
									}
										
									},
									error : function() {
										alert("数据异常")
									}
								});
	
								}
						});
	
						}
			
			},
			error : function() {
				alert("工作流数据回掉异常");
						}
 });


	

	    //鼠标悬停事件

          	    //鼠标悬停事件
    $("#zhucaidan").on('mouseover','.zhodai',function(){
    	$(".zhodai").css({
    	"cursor":"pointer",
    	});

    
    	$("#zhucaidan").on('mouseout','.zhodai',function(){
    	});
    	


    });  
                      	    //鼠标悬停事件 */
    $(".addliu").hover(function(){
    	$(".addliu").css({"cursor":"pointer"});      
    },function(){
          });




    
    //edesk主头像点击事件
       $("#edsk").hover(function(){
    	$("#edsk").css({"cursor":"pointer"});      
    },function(){
          });
          
               $("#errin").hover(function(){
    	$("#errin").css({"cursor":"pointer"});      
    },function(){
          });
   

   $("#edsk").click(function(){
   window.location.href="ModuleFunctionAction!findMfByUser.action";
   });
   

	
	//日历
var caldata={};
var calCircuitData={};
var calProjectData={};
$.ajax({
		url : 'AlertMessagesAction!findAlertMessages4calendar.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(doc) {
			caldata=doc;
			}
		});
	
   

		

		year=parseInt(year)+1;
		var entime=year + seperator1 + "01" + seperator1 + "01";

$.ajax( {

			url : 'CalendarAction!findCalendargo.action',
			dataType : 'json',
			data : {
				start: taday,//start.format(),taday
			    end:entime    //entime
			},
			
			cache : false,//防止数据缓存
			success : function(calendarList) {
			var events = [];
			var da = [];	
														$(calendarList).each(
																function() {
																//开始时间
																			var newDate=/\d{4}-\d{1,2}-\d{1,2}/g.exec($(this).attr('start'));
																			var dayy=newDate[0];
																			var newDate=/\d{4}-\d{1,2}-\d{1,2}/g.exec($(this).attr('endDate'));
																			var entime=newDate[0];
																			
																			var dayall= getDayAll(dayy,entime);
																			
																			//谁提醒
																			var addUserName=$(this).attr('addUserName');
																			 var userName= $(this).attr('userName');
																			 var title=  $(this).attr('title');
																			
																	//开始时间和结束时间所有的日期
																	for(var i=0;dayall.length>i;i++){
																	da.push({																	
																		date:dayall[i],
																		value : addUserName+'  提醒:'+(userName==null?'自己':userName)+'\n'+title+"\n更多请单击",
																	})
																	}	
																			
																			
												//结束时间
																	da.push({
																		date:entime,
																		value : $(this).attr('addUserName')+'  提醒:'+($(this).attr('userName')==null?'自己':$(this).attr('userName'))+'\n'+$(
																								this).attr('title')+"\n更多请单击",
																	})
										
										//开始时间
/* 																	da.push({
																		date:dayy,
																		value : $(this).attr('addUserName')+'  提醒:'+($(this).attr('userName')==null?'自己':$(this).attr('userName'))+'\n'+$(
																								this).attr('title')+"\n更多请单击",
																	}) */
																	
																	
																				});
								
															 
														$("#ca").calendar({
															data:da,
														  onSelected: function (view, date, data) {
														  

														  //把天气隐藏了

														  
														  if($("#taskadd").is(":hidden")){
														  

														  
															//Wed Mar 20 2019 00:00:00 GMT+0800 (中国标准时间)
															var data1=date;
															 var date2= data1.toString();
															 //去除所有空格
															var time=date2.replace(/\s*/g,"")
															//月份
															//1
															var month= time.substring(3, 6);
															if(month=="Jan"){
															month="01"
															} else if(month=="Feb"){
															month="02"
															} else if(month=="Mar"){
															month="03"
															} else if(month=="Apr"){
															month="04"
															}else if(month=="May"){
															month="05"
															}else if(month=="Jun"){
															month="06"
															}else if(month=="Jul"){
															month="07"
															}else if(month=="Aug"){
															month="08"
															}else if(month=="Sep"){
															month="09"
															}else if(month=="Oct"){
															month="10"
															}else if(month=="Nov"){
															month="11"
															}else if(month=="Dec"){
															month="12"
															}
																	//年
															if(!!window.ActiveXObject || "ActiveXObject" in window){
															var datt=time.substring(time.length-4,time.length);
															}else{
															var datt=time.substring(6,12);
															}
															//天
															if(!!window.ActiveXObject || "ActiveXObject" in window){
															var  day=time.substring(6,8);
															}else{
															var  day=datt.substring(0,2);
															}
															
															
															//开始时间
															if(!!window.ActiveXObject || "ActiveXObject" in window){
																var cktime=datt+"-"+month+"-"+day;
															}else{

															var yearq=datt.substring(2,6);
															var cktime=yearq+"-"+month+"-"+day;
															}
														

															$.ajax( {
															url : 'CalendarAction!mingxi.action',
															dataType : 'json',
															data : {
																start: cktime
															},
															cache : false,//防止数据缓存
															success : function(list) {
															
															if(list.length==0){
															alert(cktime+"近期无任务查询")
															}else{
														$("#weather").css({display: "none"});
														  $("#taskadd").css({display: "block"});
															var count=0;
															//list集合
															$(list).each(function(){
																count=count+1;
															$("<div class='xuanfu' style='padding: 0;'  align='left'><span  style='font-size: 14px;border:none;vertical-align:bottom;'  align='left' >"+count+"：&nbsp;&nbsp;&nbsp;</span><span style='font-size: 14px;border:none;vertical-align:bottom;' title='任务开始时间："+this.start+"--任务结束时间："+this.endDate+"' >"+this.title+"</span></div><hr  style='height:1px;border:none;border-top:1px dashed #ECEDEE; margin: 10px auto;'/>").appendTo("#taskadd");
															});
															}
															},
															error : function() {
															alert("数据异常")
															}											
															});	

													
	
														}
	
	
														
      													 }
														});			
													},
														
													error : function() {
														$('#ca').calendar({
															
														 });
													},
													color : 'red', // a non-ajax option
													textColor : 'black' // a non-ajax option
												});
																							
												
												//返回天气
												$(".back").click(function(){
												$("#weather").css({display: "block"});
												  $("#taskadd").css({display: "none"});
												  
												  $(".xuanfu").remove();
												  $("hr").remove()
												  
												});	
										
												 }); 
														//滚动条
												var name=$(".name").text();
												$.ajax({
												url : 'CalendarAction!Calendarajax.action',
												dataType : 'json',
												data : {
												userName: name,
														},
												cache : false,//防止数据缓存
												
												success : function(tt) {
													var count=0;
												if(tt.length>0){
												$(tt).each(function(){
												count=count+1;
													$("<div style='text-align:center;'>"+count+":"+this.title+"</div>").appendTo("#Marquee");
												});	
												}else{
													$("<div style='text-align:center;'>1:今天没有任务</div>").appendTo("#Marquee");
												}
												}
												,error : function() {
												alert("数据异常:Abnormal data");
												}



											
												});	
	/* var Mar2 = document.getElementById("Marquee2");  */
	 var Mar = document.getElementById("Marquee"); 
	 var child_div=Mar.getElementsByTagName("div") 
	/*  var child_div2=Mar2.getElementsByTagName("div"); */
	 var picH = 60;//移动高度 
	 var scrollstep=3;//移动步幅,越大越快 
	 var scrolltime=20;//移动频度(毫秒)越大越慢 
	var stoptime=3000;//间断时间(毫秒) 
	 var tmpH = 0; 
	 Mar.innerHTML += Mar.innerHTML; 
	 function start(){ 
	     if(tmpH < picH){ 
	         tmpH += scrollstep; 
	         if(tmpH > picH )tmpH = picH ; 
	         Mar.scrollTop = tmpH; 
	         setTimeout(start,scrolltime); 
	     }else{ 
	        tmpH = 0;
	         Mar.appendChild(child_div[0]);
	        /*  Mar2.appendChild(child_div2[0]); */
	         Mar.scrollTop = 0; 
	         /* Mar2.scrollTop = 0;  */
	         setTimeout(start,stoptime); 
	    } 
	 }  

onload=function(){setTimeout(start,stoptime)}; 	

		
		function daysBetween(DateOne,DateTwo)
	{
	var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));
	var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);
	var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));
	
	var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));
	var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);
	var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));
	
	var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);
	return Math.abs(cha);
	}
	
	
	    Date.prototype.format=function (){
        var s='';
        s+=this.getFullYear()+'-';          // 获取年份。
        s+=(this.getMonth()+1)+"-";         // 获取月份。
        s+= this.getDate();                 // 获取日。
        return(s);                          // 返回日期。
    };

//详细数据
function clickon(){

		var mouth= $(".mouth").text();

		mouth=mouth.substring(5, 7);

		$.ajax({
		url : 'WageAction!gonzixiangxi.action',
		dataType : 'json',
		data : {
				shihuamouth: mouth,
			},
		cache : false,//防止数据缓存
		success : function(wage) {
		if(wage !=null){
		$("#tougengai").text("个人财务详细数据:"+wage.mouth);
		$("#xaingxishuju").html("");
		$("<table  border='1' cellspacing='0' cellpadding='0'  style='width:100%;'>"
		+"<tr><td align='center' colspan='2'>上海零参科技有限公司</td>"
		+"<tr><td align='center'>应发工资</td><td align='center'>"+wage.yingfagongzi+"</td>"
		+"</tr><tr><td align='center'>岗位工资</td><td align='center'>"+wage.gangweigongzi+"</td></tr>"
		+"</tr><tr><td align='center'>保密津贴</td><td align='center'>"+wage.baomijintie+"</td></tr>"
		+"</tr><tr><td align='center'>绩效考核工资</td><td align='center'>"+wage.jixiaokaohegongzi+"</td></tr>"
		+"</tr><tr><td align='center'>技能工资</td><td align='center'>"+wage.jinenggongzi+"</td></tr>"
		+"</tr><tr><td align='center'> 特殊补贴</td><td align='center'>"+wage.gonglinggongzi+"</td></tr>"
		+"</tr><tr><td align='center'>奖金</td><td align='center'>"+wage.jiangjin+"</td></tr>"
		+"</tr><tr><td align='center'>加班费</td><td align='center'>"+wage.jiabanfei+"</td></tr>"
		+"</tr><tr><td align='center'>其他</td><td align='center'>"+wage.other+"</td></tr>"
		+"</tr><tr><td align='center'>病事旷等</td><td align='center'>"+wage.bingshikangdeng+"</td></tr>"
		+"</tr><tr><td align='center'>养老保险</td><td align='center'>"+wage.tongchoujin+"</td></tr>"
		+"</tr><tr><td align='center'>医疗保险</td><td align='center'>"+wage.yiliaobaoxian+"</td></tr>"
		+"</tr><tr><td align='center'>失业保险</td><td align='center'>"+wage.shiyebaoxian+"</td></tr>"
		+"</tr><tr><td align='center'>公积金</td><td align='center'>"+wage.gongjijin+"</td></tr>"
		+"</tr><tr><td align='center'>午餐费</td><td align='center'>"+wage.wucanfei+"</td></tr>"
		+"</tr><tr><td align='center'>补差</td><td align='center'>"+wage.buchagongzi+"</td></tr>"
		+"</tr><tr><td align='center'>房租费</td><td align='center'>"+wage.fangzufei+"</td></tr>"
		+"</tr><tr><td align='center'>实发工资</td><td align='center'>"+wage.shifagongzi+"</td></tr>"
		+"</table>").appendTo("#xaingxishuju");
		}
		},
		error: function(){
		alert("获取个人详细数据失败");
		}
		});
}
	

    //按日查询
    function getDayAll(begin,end){
        var dateAllArr = new Array();
        var ab = begin.split("-");
        var ae = end.split("-");
        var db = new Date();
        db.setUTCFullYear(ab[0], ab[1]-1, ab[2]);
        var de = new Date();
        de.setUTCFullYear(ae[0], ae[1]-1, ae[2]);
        var unixDb=db.getTime();
        var unixDe=de.getTime();
        for(var k=unixDb;k<=unixDe;){
            dateAllArr.push((new Date(parseInt(k))).format().toString());
            k=k+24*60*60*1000;
        }
        return dateAllArr;
    }

    //按周查询
    function getWeekAll(begin,end){
        var dateAllArr = new Array();
        var ab = begin.split("-");
        var ae = end.split("-");
        var db = new Date();
        db.setUTCFullYear(ab[0], ab[1]-1, ab[2]);
        var de = new Date();
        de.setUTCFullYear(ae[0], ae[1]-1, ae[2]);
        var unixDb=db.getTime();
        var unixDe=de.getTime();
        for(var k=unixDb;k<=unixDe;){
            dateAllArr.push((new Date(parseInt(k))).format().toString());
            k=k+7*24*60*60*1000;
        }
        return dateAllArr;
    }

    function getMonthAll(begin,end) {
        var d1 = begin;
        var d2 = end;
        var dateArry = new Array();
        var s1 = d1.split("-");
        var s2 = d2.split("-");
        var mCount = 0;
        if (parseInt(s1[0]) < parseInt(s2[0])) {
            mCount = (parseInt(s2[0]) - parseInt(s1[0])) * 12 + parseInt(s2[1]) - parseInt(s1[1])+1;
        } else {
            mCount = parseInt(s2[1]) - parseInt(s1[1])+1;
        }
        if (mCount > 0) {
            var startM = parseInt(s1[1]);
            var startY = parseInt(s1[0]);
            for (var i = 0; i < mCount; i++) {
                if (startM < 12) {
                    dateArry[i] = startY + "-" + (startM>9 ? startM : "0" + startM);
                    startM += 1;
                } else {
                    dateArry[i] = startY + "-" + (startM > 9 ? startM : "0" + startM);
                    startM = 1;
                    startY += 1;
                }
            }
        }
        return dateArry;
    }	
    
        function getYearAll(begin,end) {
        var d1 = begin;
        var d2 = end;
        var dateArry = new Array();
        var s1 = d1.split("-");
        var s2 = d2.split("-");
        var mYearCount = parseInt(s2[0]) - parseInt(s1[0])+1;
        var startY = parseInt(s1[0]);
        for (var i = 0; i < mYearCount;i++) {
            dateArry[i] = startY;
            startY += 1;
        }
        return dateArry;
    }
	       function MoveTitle(Element,Text,Time,Class){
        var ElementJQ=$(Element);
        //创建提示框
        var CreateTitle=function(event){
            if(ElementJQ.TitleTime){
                clearTimeout(ElementJQ.TitleTime);
                ElementJQ.TitleTime=setTimeout(function(){
                    ElementJQ.TitleDiv=document.createElement("div");
                    if(Class){
                        $(ElementJQ.TitleDiv).addClass(Class); 
                    }
                    $(ElementJQ.TitleDiv).css({
                        position:"fixed",
                        left:event.clientX+16+"px",//16是鼠标的宽度
                        top:event.clientY+16+"px",//16是鼠标的高度
                    });
                    $(ElementJQ.TitleDiv).html(Text);
                    document.body.appendChild(ElementJQ.TitleDiv);
                },Time)
            }else{
                ElementJQ.TitleTime=setTimeout(function(){
                    ElementJQ.TitleDiv=document.createElement("div");
                    if(Class){
                        $(ElementJQ.TitleDiv).addClass(Class); 
                    }
                    $(ElementJQ.TitleDiv).css({
                        position:"fixed",
                        left:event.clientX+16+"px",//16是鼠标的宽度
                        top:event.clientY+16+"px",//16是鼠标的宽度
                    });
                    $(ElementJQ.TitleDiv).html(Text);
                    document.body.appendChild(ElementJQ.TitleDiv);
                },Time)
            }
        }
        ElementJQ.on("mousemove",function(e){
        clearTimeout(ElementJQ.TitleTime);
            ElementJQ.TitleTime=null;
            if(ElementJQ.TitleDiv){
                document.body.removeChild(ElementJQ.TitleDiv);
                ElementJQ.TitleDiv=null;
            }
            CreateTitle(e);
        });
        ElementJQ.on("mouseover",function(e){
            CreateTitle(e);
        });
        ElementJQ.on("mouseout",function(){
            clearTimeout(ElementJQ.TitleTime);
            ElementJQ.TitleTime=null;
            if(ElementJQ.TitleDiv){
                document.body.removeChild(ElementJQ.TitleDiv);
                ElementJQ.TitleDiv=null;
            }
             
        });
    }
    
$(".tianqi").attr("src","http://i.tianqi.com/index.php?c=code&id=55");
}
else{
alert("请刷新！，重新登录")
}	    
	</script>

	<!-- 弹出 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 100%;">
			<div class="modal-content">
				<div class="modal-header">
					<div style="float: left;">
						<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						<h4 class="modal-title" id="myModalLabel">收藏夹功能管理</h4>
					</div>
					<div class="search" id="remote">
						<form class="searchFun"
							action="ModuleFunctionAction!searchModuleFunction.action"
							method="post" style="margin: 0px; padding-top: 10px;"
							target="workMain">
							<input type="text" name="moduleFunction.functionName"
								style="width: 180px; height: 26px"
								class="search_text form-control typeahead" accesskey="s"
								tabindex="9" autocomplete="off" x-webkit-speech=""
								x-webkit-grammar="builtin:search" />
						</form>
					</div>
					<div align="right" style="margin-top: -40px;">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>

				</div>
				<div class="modal-body" style="height: 70%;overflow-y:auto;">

					<div align="left">
						<div align="center">
							<font color="red">${errorMessage}</font>
						</div>
						<div id="favorites"></div>


					</div>
				</div>
				<br>
			</div>

		</div>


		<!-- <button type="button" class="btn btn-primary">提交更改</button> -->
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal -->
</div>




<!-- 弹出 -->
<div class="modal fade" id="llModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 100%;">
		<div class="modal-content">
			<div class="modal-header">
				<div style="float: left;">
					<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
					<h4 class="modal-title" id="titi"></h4>
				</div>
				<div class="search" id="remote">
					<form class="searchFun"
						action="ModuleFunctionAction!searchModuleFunction.action"
						method="post" style="margin: 0px; padding-top: 10px;"
						target="workMain">
						<input type="text" name="moduleFunction.functionName"
							style="width: 180px; height: 26px"
							class="search_text form-control typeahead" accesskey="s"
							tabindex="9" autocomplete="off" x-webkit-speech=""
							x-webkit-grammar="builtin:search" />
					</form>
				</div>
				<div align="right" style="margin-top: -40px;">
					<button type="button" class="btn btn-default" id="bengyem"
						onclick="addtofavorite()">收藏本页面</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>

			</div>
			<div class="modal-body" style="height: 73%;overflow-y:auto;">

				<div align="left">
					<div align="center">
						<font color="red">${errorMessage}</font>
					</div>
					<div id="gongneng"></div>

				</div>
			</div>
			<br>
		</div>

	</div>


	<!-- <button type="button" class="btn btn-primary">提交更改</button> -->
</div>
<!-- /.modal-content -->
</div>
<!-- /.modal -->
</div>



<div class="modal fade" id="jxwModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 100%;">
		<div class="modal-content">
			<div class="modal-header">
				<div style="float: left;">
					<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
					<h4 class="modal-title" id="baioti">吉祥物查看</h4>
				</div>
				<div align="right">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>

			</div>
			<div class="modal-body" style="height: 73%;overflow-y:auto;">

				<div align="left">
					<div align="center">
						<font color="red">${errorMessage}</font>
					</div>
					<div id="jianxwutup"></div>

				</div>
			</div>
			<br>
		</div>

	</div>


</div>
</div>
</div>

<!-- 柱状图，饼图详细数据 -->
<div class="modal fade" id="xiangxiModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 100%;">
		<div class="modal-content">
			<div class="modal-header">
				<div style="float: left;">
					<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
					<h4 class="modal-title" id="tougengai"></h4>
				</div>
				<div align="right">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>

			</div>
			<div class="modal-body" style="height: 73%;overflow-y:auto;">

				<div align="left">
					<div align="center">
						<font color="red">${errorMessage}</font>
					</div>
					<div id="xaingxishuju"></div>

				</div>
			</div>
			<br>
		</div>

	</div>


</div>
</div>
</div>




<script type="text/javascript">
	var id;
	var functionName;
	var functionLink;

	function addtofavorite() {
		var favorite = {
			"favorite.Mfid" : id,
			"favorite.name" : functionName,
			"favorite.path" : functionLink
		};
		$.ajax({
			url : 'FavoriteAction_addFavorite.action',
			type : 'post',
			dataType : 'json',
			data : favorite,
			cache : false,
			success : function(obj) {
				if (obj == true) {
					alert("收藏成功");
					$("#llModal").modal('hide');
				} else {
					alert("收藏失败");
					$("#llModal").modal('hide');
				}
			},
			error : function() {
				alert("收藏失败");
			}
		});
	}


	var ModuleName = new Bloodhound({
		datumTokenizer : Bloodhound.tokenizers.obj.whitespace('value'),
		queryTokenizer : Bloodhound.tokenizers.whitespace,
		prefetch : '',
		remote : {
			url : 'ModuleFunctionAction!searchModuleFunction2.action',
			prepare : function(query, settings) {
				settings.url += '?moduleFunction.functionName=' + encodeURI(encodeURI(query));
				/*  alert(encodeURI(encodeURI(query))); */
				return settings;
			}
		}
	});

	$(function() {
		//搜索框
		$('.more').on("click", function() {
			$(".searchFun").submit();
		});

		$('#remote .typeahead').typeahead(null, {
			name : 'names',
			display : 'modulename',
			source : ModuleName,
			limit : '10',
		});

		$('#remote .typeahead').bind('typeahead:selected', function(obj, datum, name) {
			var eValue = eval('datum.' + "moduleid");
			$.ajax({
				url : 'ModuleFunctionAction!chageifanme.action',
				dataType : 'json',
				data : {
					id : eValue,
				},
				cache : false, //防止数据缓存
				success : function(moduleFunction) {
					if (moduleFunction == "此功能为原有功能，服务器已更改") {
						alert("此功能为原有功能，服务器已更改");
					} else {
						$("#gongneng").html("");
						$('#llModal').modal();
						$("#titi").text(moduleFunction.functionName);
						$("<iframe src='" + moduleFunction.functionLink + "' width='100%' height='100%'  id='external-frame' frameborder='0'    onload='setIframeHeight(this)' scrolling='no' style='overflow:hidden;'></iframe>").appendTo("#gongneng");
						id = moduleFunction.id;
						functionName = moduleFunction.functionName;
						functionLink = moduleFunction.moduleFunction;

					}

				},
				error : function() {
					alert("请登录");
				}
			});



		});

	});


	//点击加号即可展示收藏夹
	$("#myModal").on("show.bs.modal", function() {
		$.ajax({
			url : 'FavoriteAction_showajax.action',
			dataType : 'json',
			cache : false, //防止数据缓存
			success : function(listFavorites) {
				var co = 0;
				//循环
				$(listFavorites).each(function() {
					co = parseInt(co) + 1;
					$("<div style='width:48%;float: left;border-bottom: solid 1px #0170b8; margin: 10px 10px 0px 10px; padding-top: 5px; background: url('<%=basePath%>title.jpg');'>"
						+ "<div style='font-weight: bold; padding-left: 10px; width: 100%; float: left;'>" + co
						+ "、<input type='button' style='color:#fff;width:200px;height:50px;background-color:" + this.bgcolor
						+ "' value='" + this.name + "' onclick='changeiframe(" + this.mfid + ")'>"
						+ "<a href='javascript:void(0)'    class='btn btn-default' data-dismiss='modal'    style='float: right'  onclick='delte(" + this.id + ")' > 移出</a><a href='javascript:void(0)'   onclick='update(" + this.id + ")' class='btn btn-default' data-dismiss='modal'   onclick='(" + this.id + ")'   style='float: right'  > 移动至桌面</a></div></div>").appendTo('#favorites');
				});
			},
			error : function() {
				alert("收藏夹数据获取失败");
			}
		});
	});




	$("#myModal").on("hide.bs.modal", function() {
		//关闭
		$("#favorites").html("");

	});

	$("#llModal").on("hide.bs.modal", function() {
		//关闭
		$("#gongneng").html("");
		$("#titi").text("");

	});


	$("#xiangxiModal").on("hide.bs.modal", function() {
		//关闭
		$("#xaingxishuju").html("");
		$("#tougengai").text("");

	});
	//关闭
	$("#jxwModal").on("hide.bs.modal", function() {
		$("#jianxwutup").html("");
	});

	function addmore() {
		var s = "<div class='more tt-suggestion tt-selectable '>更多</div>";
		$('.tt-menu').append(s);
	}
	addmore();



	function changeiframe(id) {
		$.ajax({
			url : 'ModuleFunctionAction!chageifanme.action',
			dataType : 'json',
			data : {
				id : id,
			},
			cache : false, //防止数据缓存
			success : function(moduleFunction) {

				if (moduleFunction.functionLink == "此功能为原有功能，服务器已更改") {
					alert("此功能为原有功能，服务器已更改");
				} else {
					$('#llModal').modal();
					if (moduleFunction.functionName != null) {
						$("#titi").text("");
						$("#titi").text(moduleFunction.functionName);
					}
					$("<iframe src='" + moduleFunction.functionLink + "' width='100%' height='100%'  id='external-frame' frameborder='0'    onload='setIframeHeight(this)' scrolling='no' style='overflow:hidden;'></iframe>").appendTo("#gongneng");
				}

			},
			error : function() {
				alert("请登录");
			}
		});

	}

	function setIframeHeight(iframe) {
		if (iframe) {
			var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
			if (iframeWin.document.body) {
				iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
			}
		}
	}
	;

	window.onload = function() {
		setIframeHeight(document.getElementById('external-frame'));
	};


	function delte(id) {
		//删除
		$.ajax({
			url : 'FavoriteAction_deletescj.action',
			dataType : 'json',
			data : {
				id : id,
			},
			cache : false, //防止数据缓存
			success : function(listFavorites) {},
			error : function() {
				alert("收藏夹数据获取失败");
			}
		});


	}

	function update(id) {
		$.ajax({
			url : 'FavoriteAction_update.action',
			dataType : 'json',
			data : {
				id : id,
			},
			cache : false, //防止数据缓存
			success : function(bool) {},
			error : function() {
				alert("调到主菜单失败");
			}
		});
	}
</script>


</body>
</html>
