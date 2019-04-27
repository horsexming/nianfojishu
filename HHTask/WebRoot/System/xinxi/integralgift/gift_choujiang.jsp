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
			<link type="text/css" rel="styleSheet" href="css/index3.css" />
		<link href="<%=basePath%>css/choujiang.css" rel="stylesheet"
			type="text/css">
			
			<script type="text/javascript" src="js/time.js">
</script>
		<script type="text/javascript" src="js/leftnav.js">
</script>
		<script type="text/javascript" src="js/jqPaginator.js">
</script>
	</head>
	<body style="background: #e62d2d; overflow-x: hidden;">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<!-- width="400" behavior=alternate direction=left align=middle -->
				<div class="notice">
				<div class="header_bt">
					<div class="noticeleft">
						<strong><img src="img/notice_07.png" height="22"
								width="22"> </strong>
					</div>
					<div class="marquee">
						<marquee direction="left" scrollamount="5"
							onmouseout="this.start()" onmouseover="this.stop()">
							<font color="#737373" id="show"></font>
						</marquee>
					</div>
				</div>
			</div>
				<div id="pictrue_div">
					<img src="<%=basePath%>img/choujiang_images/2.png" id="sorry-img"
						style="display: none;" />
					<img src="<%=basePath%>img/choujiang_images/lipin.png" id="lipin"
						style="display: none;" />
				</div>
				<div class="banner">
					<div class="turnplate"
						style="background-image:url(<%=basePath%>img/choujiang_images/turnplate-bg.png);background-size:100% 100%;">
						<canvas class="item" id="wheelcanvas" width="422px" height="422px"></canvas>
						<img class="pointer"
							src="<%=basePath%>img/choujiang_images/turnplate-pointer.png" />
					</div>
				</div>

				<div style="display: none">
					<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
		: " http://");
document
		.write(unescape("%3Cscript src='"
				+ _bdhmProtocol
				+ "hm.baidu.com/h.js%3F6f798e51a1cd93937ee8293eece39b1a' type='text/javascript'%3E%3C/script%3E"));
</script>
					<script type="text/javascript">
var cnzz_protocol = (("https:" == document.location.protocol) ? " https://"
		: " http://");
document
		.write(unescape("%3Cspan id='cnzz_stat_icon_5718743'%3E%3C/span%3E%3Cscript src='"
				+ cnzz_protocol
				+ "s9.cnzz.com/stat.php%3Fid%3D5718743%26show%3Dpic2' type='text/javascript'%3E%3C/script%3E"));</script>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var turnplate={
		restaraunts:[],				//大转盘奖品名称
		colors:[],					//大转盘奖品区块对应背景颜色
		outsideRadius:192,			//大转盘外圆的半径
		textRadius:155,				//大转盘奖品位置距离圆心的距离
		insideRadius:68,			//大转盘内圆的半径
		startAngle:0,				//开始角度
		
		bRotate:false				//false:停止;ture:旋转
};
var name = '${name}';

$(document).ready(function(){
	//动态添加大转盘的奖品与奖品区域背景颜色
	var array = name.split("_");
	var color = "";
	if(array!=null && array.length>0){
		for(var i=0;i<array.length; i++){
			if(i==0){
				color += "#FFF4D6";
			}else if(i%2 == 0 && i!=0){
				color += "_#FFF4D6";
			}else{
				color += "_#FFFFFF";
			}
		}
	}
	turnplate.restaraunts = array;
	turnplate.colors = color.split("_");
	var rotateTimeOut = function (){
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:2160,
			duration:8000,
			callback:function (){
				alert('网络超时，请检查您的网络设置！');
			}
		});
	};

	//旋转转盘 item:奖品位置; txt：提示语;
	var rotateFn = function (item, txt){
		var bool = true;
		var msg = choujiang(txt);
		if(msg == "ycw"){
			txt = "谢谢参与";
			item = 8;
		}else if(msg.indexOf("抱歉")>=0){
			bool = false;
		}
		else {
			var array =	msg.split("&");
			if(array!=null && array.length == 2){
				msg = array[1];
			}
		}
		var angles = item * (360 / turnplate.restaraunts.length) - (360 / (turnplate.restaraunts.length*2));
		if(angles<270){
			angles = 270 - angles; 
		}else{
			angles = 360 - angles + 270;
		}
		
			
		
		$('#wheelcanvas').stopRotate();
		if(bool){
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:angles+1800,
			duration:8000,
			callback:function (){
			if(txt.indexOf("谢谢参与")>=0){
				alert("谢谢参与")
			}else{
				alert("恭喜获得"+txt+"。您的兑换码为"+msg+"请牢记。请于三日内兑换，过期无效。")
			}
				turnplate.bRotate = !turnplate.bRotate;
			}
		});
			}else{
				alert(msg)
			}
	};

	$('.pointer').click(function (){
		if(turnplate.bRotate)return;
		turnplate.bRotate = !turnplate.bRotate;
		//获取随机数(奖品个数范围内)
		var item = rnd(1,turnplate.restaraunts.length);
		//奖品数量等于10,指针落在对应奖品区域的中心角度[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
		rotateFn(item, turnplate.restaraunts[item-1]);
		/* switch (item) {
			case 1:
				rotateFn(252, turnplate.restaraunts[0]);
				break;
			case 2:
				rotateFn(216, turnplate.restaraunts[1]);
				break;
			case 3:
				rotateFn(180, turnplate.restaraunts[2]);
				break;
			case 4:
				rotateFn(144, turnplate.restaraunts[3]);
				break;
			case 5:
				rotateFn(108, turnplate.restaraunts[4]);
				break;
			case 6:
				rotateFn(72, turnplate.restaraunts[5]);
				break;
			case 7:
				rotateFn(36, turnplate.restaraunts[6]);
				break;
			case 8:
				rotateFn(360, turnplate.restaraunts[7]);
				break;
			case 9:
				rotateFn(324, turnplate.restaraunts[8]);
				break;
			case 10:
				rotateFn(288, turnplate.restaraunts[9]);
				break;
		} */
	});
});

function rnd(n, m){
	var random = Math.floor(Math.random()*(m-n+1)+n);
	return random;
	
}


//页面所有元素加载完毕后执行drawRouletteWheel()方法对转盘进行渲染
window.onload=function(){
	drawRouletteWheel();
};

function drawRouletteWheel() {    
  var canvas = document.getElementById("wheelcanvas");    
  if (canvas.getContext) {
	  //根据奖品个数计算圆周角度
	  var arc = Math.PI / (turnplate.restaraunts.length/2);
	  var ctx = canvas.getContext("2d");
	  //在给定矩形内清空一个矩形
	  ctx.clearRect(0,0,422,422);
	  //strokeStyle 属性设置或返回用于笔触的颜色、渐变或模式  
	  ctx.strokeStyle = "#FFBE04";
	  //font 属性设置或返回画布上文本内容的当前字体属性
	  ctx.font = '16px Microsoft YaHei';      
	  for(var i = 0; i < turnplate.restaraunts.length; i++) {       
		  var angle = turnplate.startAngle + i * arc;
		  ctx.fillStyle = turnplate.colors[i];
		  ctx.beginPath();
		  //arc(x,y,r,起始角,结束角,绘制方向) 方法创建弧/曲线（用于创建圆或部分圆）    
		  ctx.arc(211, 211, turnplate.outsideRadius, angle, angle + arc, false);    
		  ctx.arc(211, 211, turnplate.insideRadius, angle + arc, angle, true);
		  ctx.stroke();  
		  ctx.fill();
		  //锁画布(为了保存之前的画布状态)
		  ctx.save();   
		  
		  //----绘制奖品开始----
		  ctx.fillStyle = "#E5302F";
		  var text = turnplate.restaraunts[i];
		  var line_height = 17;
		  //translate方法重新映射画布上的 (0,0) 位置
		  ctx.translate(211 + Math.cos(angle + arc / 2) * turnplate.textRadius, 211 + Math.sin(angle + arc / 2) * turnplate.textRadius);
		  
		  //rotate方法旋转当前的绘图
		  ctx.rotate(angle + arc / 2 + Math.PI / 2);
		  
		  /** 下面代码根据奖品类型、奖品名称长度渲染不同效果，如字体、颜色、图片效果。(具体根据实际情况改变) **/
		  if(text.indexOf("M")>0){//流量包
			  var texts = text.split("M");
			  for(var j = 0; j<texts.length; j++){
				  ctx.font = j == 0?'bold 20px Microsoft YaHei':'16px Microsoft YaHei';
				  if(j == 0){
					  ctx.fillText(texts[j]+"M", -ctx.measureText(texts[j]+"M").width / 2, j * line_height);
				  }else{
					  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
				  }
			  }
		  }else if( text.length>6){//奖品名称长度超过一定范围 
			  text = text.substring(0,6)+"||"+text.substring(6);
			  var texts = text.split("||");
			  for(var j = 0; j<texts.length; j++){
				  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
				  
			  }
		  }else{
			  //在画布上绘制填色的文本。文本的默认颜色是黑色
			  //measureText()方法返回包含一个对象，该对象包含以像素计的指定字体宽度
			  ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
		  }
		  
		  //添加对应图标
		 if(text.indexOf("谢谢参与")>=0){
			   var img= document.getElementById("sorry-img");
			 if(text.length>6){
				  img.onload=function(){  
				  ctx.drawImage(img,-15,30,25,25);      
			  }; 
				  ctx.drawImage(img,-15,30,25,25);  
			  }else{
				    img.onload=function(){  
				  ctx.drawImage(img,-15,10,25,25);      
			  }; 
				  ctx.drawImage(img,-15,10,25,25);
			  }
		  }else{
			   var img= document.getElementById("lipin");
			 if(text.length>6){
				  img.onload=function(){  
				  ctx.drawImage(img,-15,30,25,25);      
			  }; 
				  ctx.drawImage(img,-15,30,25,25);  
			  }else{
				    img.onload=function(){  
				  ctx.drawImage(img,-15,10,25,25);      
			  }; 
				  ctx.drawImage(img,-15,10,25,25);
			  } 
		  } 
		  //把当前画布返回（调整）到上一个save()状态之前 
		  ctx.restore();
		  //----绘制奖品结束----
	  }     
  } 
}


function choujiang(txt){
	var msg = "";
	if(txt != '谢谢参与'){
	$.ajax( {
		type : "POST",
		url : "IntegralGiftAction_choujiang.action",
		async : false,
		data : {
			name:txt
		},
		dataType : "json",
		success : function(data) {
			msg = data;
		}
	})
}
	return msg;
}
function showWork() {
	$("#person").hide();
	$("#showMf").show();
}
$(function(){
	$.ajax( {
		type : "POST",
		url : "IntegralGiftAction_findzjIgift.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if (data == "error") {
				alert("啊哦,出错了啊!")
			}else if(data != null && data != ''){
				$("#show").html(data);
			}
		}
	})
	
})

// 判断抽奖人积分是否足够;

<%--function scroll(obj) {--%>
<%--		var tmp = (obj.scrollLeft)++;--%>
<%--		//当滚动条到达右边顶端时--%>
<%--		if (obj.scrollLeft==tmp) obj.innerHTML += obj.innerHTML;--%>
<%--		//当滚动条滚动了初始内容的宽度时滚动条回到最左端--%>
<%--		if (obj.scrollLeft>=obj.firstChild.offsetWidth) obj.scrollLeft=0;--%>
<%--	}--%>
<%--	setInterval("scroll(document.getElementById('scrollobj'))",10);--%>
</SCRIPT>
	</body>
</html>
