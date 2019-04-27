<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<meta http-equiv="Access-Control-Allow-Origin" content="*">
		<script src="<%=basePath%>/javascript/jquery/jquery-3.2.1.js"> </script> 
		<script type="text/javascript"
 			src="<%=basePath%>/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
		<style type="text/css">
		
			#contentDiv{
				overflow: visible;
			}
			h1{
				color: white;
				font-size: 4.25em;
			}
			h2,h3,h4,h5,h6{
				color: white;
			}
			*{
				font-family: 黑体;
			}
			.container, body, html{
				width: 98%;
				height: 100%;
				margin: auto;
				padding: auto;
			}
			#header{
				width: 100%;
				height: 30%;
			}
			#body{
				width: 100%;
				height: 60%;
			}
			#footer{
				width: 100%;
				height: 10%;
			}
			
			
			.custom-variables {
			  --some-color:white;
			  --some-keyword: italic;
			  --some-size: 3.25em;
/* 			  --some-complex-value: 1px 1px 2px whitesmoke, 0 0 1em slategray, 0 0 0.2em slategray; */
			  color: var(--some-color);
			  font-size: var(--some-size);
/* 			  font-style: var(--some-keyword); */
			  text-shadow: var(--some-complex-value);
			  cursor: pointer;
			  padding: 80px 20px 0px 20px;;
			  margin-bottom: 0px;
			}
			.custom-variables-eng{
				--some-color:white;
			  --some-keyword: italic;
			  --some-size: 1.6em;
/* 			  --some-complex-value: 1px 1px 2px whitesmoke, 0 0 1em slategray, 0 0 0.2em slategray; */
			  color: var(--some-color);
			  font-size: var(--some-size);
/* 			  font-style: var(--some-keyword); */
			  text-shadow: var(--some-complex-value);
			  cursor: pointer;
			  padding: 0px 20px 45px 20px;
			}
			body{
				background-color: #97BCE4;
				padding-right: 0px!important;
			}
			.itemClass,.modal-content{
				/* 选项*/
 			    border: 2px solid #ccc; 
				border-radius:25px;
				background-color: #529DD5;
				width: 100%;
			  	height: 360px;
			  	-webkit-border-radius: 30px;
				-moz-border-radius: 30px;
				-webkit-box-shadow: rgb(20,255,255) 0px 0px 15px;
				-moz-box-shadow: rgb(20,255,255) 0px 0px 15px;
				box-shadow: rgb(20,255,255) 0px 0px 15px;
			}
			
			ul{
				list-style: decimal;
				color: gray;
				margin-left: 65px;
				color: #E1EAF7;
			}
			.btn{
				background-color: #529DD5;
			  	-webkit-border-radius: 10px;
				-moz-border-radius: 10px;
				-webkit-box-shadow: rgb(20,255,255) 0px 0px 15px;
				-moz-box-shadow: rgb(20,255,255) 0px 0px 15px;
				box-shadow: rgb(20,255,255) 0px 0px 15px;
			}
		</style>
	</head>
	<body>
		<div class="modal fade" id="toPrintModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">提醒消息</h4>
		            </div>
		            <div class="modal-body">
		            	<div class="row">
							<h3 class="text-center">请在下方扫描二维码</h3>
						</div>
						<div class="row">
							<form method="post" action="<%=basePath%>/visitorAction!toPrintVoucher.action"
								autocomplete="off" id="voucherForm">
								<input type="text" name="param" id="voucher" onkeyup="enterVoucher(this)" style="width: 0px;">
							</form>
						</div>
						<div class="row">
							<div class="alert alert-warning">
							    	<p>注：请使用手机短信里的二维码在这里扫描，打印完毕后请前往门禁处认证</p>
							    	<p>如果没有收到短信请和被访人联系，谢谢。</p>
							</div>
						</div>
		            </div>
		            <div class="modal-footer" id="returnBtn">
		                <button class="btn btn-default" data-dismiss="modal">关闭(<span id="time"></span>)</button>
		            </div>
		        </div>
		    </div>
		</div>
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="showModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">提醒消息</h4>
		            </div>
		            <div class="modal-body">
		            	<h3 style="color: red" id="showMessage"></h3>
		            </div>
		            <div class="modal-footer" id="returnBtn">
		                <button class="btn btn-default" data-dismiss="modal">关闭</button>
		            </div>
		        </div>
		    </div>
		</div>
		
		
		<div class="container">
			<div id="header">
				<div class="row" style="height: 40px;">
	            </div>
				<div class="row clearfix">
					<img src="<%=basePath%>/${companyInfo.logo}" height="70px;" alt="<%=basePath%>/${companyInfo.logo}">
					<br>
					<h1 align="center">
						欢迎使用E-CHECK访客系统
					</h1>
					<h2 align="center">Welcome to E-check visitor registration system</h2>
	            </div>
	            <div class="row" style="height: 40px;">
	            	<s:if test="errorMessage!=null && errorMessage!=''">
	            		<div class="col-lg-1"></div>
	            		<div class="col-lg-10">
		            		<div class="alert alert-danger" role="alert" id="errorMessage">
								<p class="m-0" align="center">${errorMessage}</p>
							</div>
	            		</div>
	            		<div class="col-lg-1"></div>
	            	</s:if>
	            </div>
			</div>
			<div id="body">
	            <div class="row ">
	            	<div class="col-lg-4"><!-- tanchuApply() -->
	                   	<div class="itemClass" onclick="location.href='<%=basePath%>/visitorAction!judgeIdentity.action'">
	                   		<p class="custom-variables text-center" >访客登记</p>
	                   		<p class="custom-variables-eng text-center" >Registrated</p>
	                   		<div class="Jumbotron">
		                   		<ul>
		                   			<li>身份信息采集</li>
		                   			<li>来访信息登记</li>
		                   			<li>等待被访人确认</li>
		                   		</ul>
	                   		</div>
	                    </div>
	                </div>
	                <div class="col-lg-4">
	                   	<div class="itemClass" onclick="tanchuToPrint()">
	                  		<p class="custom-variables text-center">访单打印</p>
	                  		<p class="custom-variables-eng text-center" >Print Certification</p>
	                  		<div class="Jumbotron">
		                  		<ul>
		                  			<li>短信接收</li>
		                  			<li>扫描手机二维码</li>
		                  			<li>打印访单</li>
		                  		</ul>
		                  	</div>
	                    </div>
	                </div>
	                <div class="col-lg-4">
	                   	<div class="itemClass" onclick="location.href='<%=basePath%>/visitorAction!toVisitorCheck.action'">
	                  		<p class="custom-variables text-center">进出识别</p>
	                  		<p class="custom-variables-eng text-center" >I/O Verification</p>
	                  		<div class="Jumbotron">
		                  		<ul>
		                  			<li>身份证认证</li>
		                  			<li>指纹认证</li>
		                  			<li>访单认证</li>
		                  		</ul>
		                  	</div>
	                    </div>
	                </div>
				</div>
			</div>
			<div id="footer">
				<br>
				<br>
				<br>
				<div class="row">
					<p class="text-right">上海零参科技有限公司提供技术支持</p>
				</div>
			</div>
		</div>
	</body>	
	<script type="text/javascript" >
	var pageTimer = {};//定义所有的定时器
	var portStatus = false;
	$(function(){
		
		setTimeout(function(){
			$("#errorMessage").hide('slow');
		},4000);
		
		//model 关闭事件
		$('#identityModal').on('hide.bs.modal', function () {
			clearInterval(pageTimer["apply"]);
		});
		$('#showModal').on('hide.bs.modal', function () {
			clearInterval(pageTimer["message"]);
			clearInterval(pageTimer["check"]);
		});
		$('#toPrintModal').on('hide.bs.modal', function () {
			clearInterval(pageTimer["print"]);
			clearInterval(pageTimer["message"]);
			$("#voucher").val("");
		});
		
		//防止页面后退
		if (window.history && window.history.pushState) {
		　　	$(window).on('popstate', function () {
			　　window.history.pushState('forward', null, '#');
			　　window.history.forward(1);
		　　	});
	　　	}
	　　	window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
	　　	window.history.forward(1);
	　　	
	});
	
	function tanchuApply(){
		var numberIndex = 0;//请求次数
		$("#second").text(30);
		$('#identityModal').modal('show');
		pageTimer["apply"] = setInterval(dijian,1000);
		function dijian(){
			numberIndex ++;
			var numberVal = $("#second").text();
			var number = parseInt(numberVal)-1;
			if(number<=0){
				clearInterval(pageTimer["apply"]);
				$("#showMessage").html("获取身份失败，请关闭窗口后重新放置卡片");
				$("#showModal").modal('show');
				$("#returnBtn").html("<button class='btn btn-default' data-dismiss='modal'>关闭(<span id='second1'>10</span>)</button>");
				jishi(false);
				$('#identityModal').modal('hide')
			}
			$("#second").text(number);
			console.log("second:"+number);

			getIdentityCard(numberIndex,"apply");
		}
	}
	
	function tanchuCheck(){
		$('#identityModal').modal('show');
		$("#second").text(30);
		pageTimer["check"] = setInterval(dijian,1000);
		var numberIndex = 0;
		
		function dijian(){
			numberIndex++;
			var numberVal = $("#second").text();
			var number = parseInt(numberVal)-1;
			if(number<=0){
				clearInterval(pageTimer["check"]);
				$('#identityModal').modal('hide');
			}
			$("#second").text(number);
			console.log("second:"+number);
			
			getIdentityCard(numberIndex,"check")
		}
	}
	
	function jishi(idName){
		pageTimer["message"] = setInterval(dijian,1000);
		function dijian(){
			var numberVal = $("#second1").text();
			var number = parseInt(numberVal)-1;
			if(number<=0){
				clearInterval(pageTimer["message"]);
				$('#showModal').modal('hide');
				$('#identityModal').modal('hide');
			}
			$("#second1").text(number);
			console.log("second1:"+number);
		}
	}
	
	function getIdentityCard(numberIndex,pattern){
		if(numberIndex%5==0){
			return false;//两秒执行一次获取操作，目的因为跨域访问不能使用同步
		}
		//ajax获取身份信息
		$.ajax({
			type:"POST",
			url:"http://localhost:8888/termb/readcontent?number="+numberIndex,
			dataType:"jsonp",
			success:function(data){
				if(data!=null){
					if(data.status==2){
						//寻卡失败，继续寻卡
						$("#identityCard").val("");
						$("#name").val("");
					}else if(data.status=1){
						clearInterval(pageTimer["apply"]);
						
						if(pattern=="apply"){
							clearInterval(pageTimer["apply"]);
							setTimeout(function(){
								$("#errorMessage").hide('slow');
							},4000);
							getIdentityImg(data.identtiy,data.name);//获得图片后上传到服务器
							//$("#applyForm").submit();
						}else if(pattern=="check"){
							var identity= data.identtiy;
							if(identity!=null && identity!=""){
								$.ajax({
									type:"post",
									url:"<%=basePath%>/visitorAction!verifyInAndOut.action",
									data:{
										"param":identity,
										"pageStatus":"idCard"
									},
									async : false,
									dataType:"json",
									success:function(data){
										$("#showMessage").html(data);
										$("#showModal").modal('show');
										if(data!=null&& data.indexOf("成功")>0){
											//30秒后跳转到主页（或者点击返回到主页）
											$("#returnBtn").html("<button class='btn btn-default' onclick='location.href=\"<%=basePath%>/visitorAction!toVisitorIndex.action\"'>返回到主页(<span id='second1'>10</span>)</button>");
											jishi(true);
										}else{
											$("#returnBtn").html("<button class='btn btn-default' data-dismiss='modal'>关闭(<span id='second1'>10</span>)</button>");
											jishi(false);
										}
									},
									error:function(e){
									    alert("系统异常，请联系管理员");
									}
								});
							    return true;
							}else{
								return false;
							}
						}
					}else if(data.status!=1){
						clearInterval(pageTimer["apply"]);
						$("#showMessage").html(data.info+"[获取身份失败，请重新放置卡片]");
						$("#showModal").modal('show');
						$("#returnBtn").html("<button class='btn btn-default' data-dismiss='modal'>关闭(<span id='second1'>10</span>)</button>");
						jishi(false);
						$('#identityModal').modal('hide');
					}
				}else{
					clearInterval(pageTimer["apply"]);
					$("#showMessage").html("本地项目异常，请联系管理员");
					$("#showModal").modal('show');
					$("#returnBtn").html("<button class='btn btn-default' data-dismiss='modal'>关闭(<span id='second1'>10</span>)</button>");
					jishi(false);
					$('#identityModal').modal('hide');
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				clearInterval(pageTimer["apply"]);
				$("#showMessage").html("系统获取身份证异常："+XMLHttpRequest.status+"  "+XMLHttpRequest.readyState+"  "+textStatus);
				$("#showModal").modal('show');
				$("#returnBtn").html("<button class='btn btn-default' data-dismiss='modal'>关闭(<span id='second1'>10</span>)</button>");
				jishi(false);
// 				$('#identityModal').modal('hide');
		    }
		});
		return false;
	}
	
	
	function getIdentityImg(identityCard,name){
		setTimeout(function(){
			console.log("两秒后执行获取图片");
		},2000);
		var image = new Image();
		
		image.src = "http://localhost:8888/termb/getPicture?callback=123456798"; //D:Apache24/htdocs/xp.jpg
		image.setAttribute("crossOrigin",'Anonymous');
		image.onload= function(){
			 var canvas = document.createElement("canvas"), //创建canvas元素
             width=image.width, //确保canvas的尺寸和图片一样
             height=image.height;
	         canvas.width=width;
	         canvas.height=height;
	         canvas.getContext("2d").drawImage(image,0,0,width,height); //将图片绘制到canvas中
	         var dataURL=canvas.toDataURL('image/jpeg',1); //转换图片为dataURL
			 console.log(dataURL);
	         
			$.ajax({
				type:"POST",
				url:"<%=basePath%>/visitorAction!uploadByIdentity.action",
				dataType:"json",
				data:{
					image:dataURL,
					"visitorIdentity.identityCard":identityCard,
					"visitorIdentity.name":name
				},
				async : false,
				success:function(data){
					if(data!=null && data.success==true&& data.message=="success"){
						location.href="<%=basePath%>/visitorAction!judgeIdentity.action?visitorIdentity.id="+data.data;
					}else{
						clearInterval(pageTimer["apply"]);
						$("#showMessage").html("上传身份证异常："+"结果："+data.success+"，消息："+data.message);
						$("#showModal").modal('show');
						$("#returnBtn").html("<button class='btn btn-default' data-dismiss='modal'>关闭(<span id='second1'>10</span>)</button>");
						jishi(false);
						$('#identityModal').modal('hide');	
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					clearInterval(pageTimer["apply"]);
					$("#showMessage").html("上传身份证异常："+XMLHttpRequest.status+"  "+XMLHttpRequest.readyState+"  "+textStatus);
					$("#showModal").modal('show');
					$("#returnBtn").html("<button class='btn btn-default' data-dismiss='modal'>关闭(<span id='second1'>10</span>)</button>");
					jishi(false);
					$('#identityModal').modal('hide');
			    }
			});
		}
		
		//上传到服务器
		//uploadByIdentity();
		
	}
	
	//弹出获取二维码页面
	function tanchuToPrint(){
		$('#toPrintModal').modal('show');
		$("#time").text(30);
		pageTimer["print"] = setInterval(dijian,1000);
		var numberIndex = 0;
		$("#voucher").val("");
		function dijian(){
			numberIndex++;
			var numberVal = $("#time").text();
			var number = parseInt(numberVal)-1;
			if(number<=0){
				clearInterval(pageTimer["print"]);
				$('#toPrintModal').modal('hide');
			}
			$("#time").text(number);
			console.log("time:"+number);
			$("#voucher").focus();
			
			//getIdentityCard(numberIndex,"check")
		}
	}
	
	function enterVoucher(obj){
		var val = obj.value;
		if(val!=null && val!="" && val.length==64){
// 			$.ajax({
// 				type:"post",
// 				url:"<%=basePath%>/visitorAction!findFollowVisitorVoucher.action",
// 				data:{
// 					"param":val
// 				},
// 				dataType:"json",
// 				success:function(data){
// 					for(var i=0;i<data.length;i++){
// 						var html = "<%=basePath%>/visitorAction!toPrintVoucher.action?param="+data[i];
// 						var print = window.open(html,"print","width=100,height=200");
// 						alert(print);
// // 						print.print();
// // 						print.close();
// 					}
					
// 				},error:function(){
// 					alert("系统异常");
// 				}
// 			});
			$("#voucherForm").submit();
		}
	}
	</script>
</html>
