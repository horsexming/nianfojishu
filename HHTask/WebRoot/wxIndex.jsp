<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>生产力生态平衡系统——我的五行</title>
		<link type="text/css" rel="stylesheet" href="css/wx.css" />
	</head>
	<SCRIPT LANGUAGE="JavaScript">	 
/*设置客户端的高和宽*/  
window.onload=function(){
setimg();
setgoldlink();
setwoodlink();
setwaterlink();
setfirelink();
setgroundlink();
}
window.onresize=function(){
setimg();
setgoldlink();
setwoodlink()
setfirelink();
setwaterlink();
setgroundlink();
}



function setimg(){   
    var divId=document.getElementById('centerimg');    
    var rr=new getClientBounds();  
    divId.style.display='block'; 
    divId.style.left=(0.98*rr.width-divId.clientWidth)/2+document.body.scrollLeft+"px";  
      
      
}
function setgoldlink(){
    var goldleft;
    var gold=document.getElementById('gold');    
    var rr=new getClientBounds();  
    gold.style.display='block'; 
    gold.style.left=(0.98*rr.width-gold.clientWidth)/2+document.body.scrollLeft+"px"; 
    var goldleft=(0.98*rr.width-gold.clientWidth)/2+document.body.scrollLeft;
   return {width:goldleft};
}
function setwoodlink(){
    var w=new setgoldlink();
    var wood=document.getElementById('wood'); 
    var gold=document.getElementById('gold');       
    wood.style.display='block';
    wood.style.left = (w.width+230)+"px";
    //var rr=new getClientBounds(); 
    //wood.style.left=(1.4*rr.width-wood.clientWidth)/2+document.body.scrollLeft 
}
function setwaterlink(){
     var w=new setgoldlink();
    var water=document.getElementById('water'); 
    var gold=document.getElementById('gold');       
    water.style.display='block';
    water.style.left = (w.width+130)+"px";   
   // var rr=new getClientBounds();   
   // water.style.left=(1.25*rr.width-water.clientWidth)/2+document.body.scrollLeft+"px"; 
}
function setfirelink(){
    var w=new setgoldlink();
    var fire=document.getElementById('fire');
    var gold=document.getElementById('gold');       
    fire.style.display='block';
    fire.style.left = (w.width-180)+"px";   
   // var rr=new getClientBounds(); 
   // fire.style.left=(0.65*rr.width-ground.clientWidth)/2+document.body.scrollLeft+"px"; 
}
function setgroundlink(){
    var w=new setgoldlink();
    var ground=document.getElementById('ground');   
    var gold=document.getElementById('gold');       
    ground.style.display='block';
    ground.style.left = (w.width-260)+"px"; 
    //var rr=new getClientBounds(); 
    //ground.style.left=(0.55*rr.width-ground.clientWidth)/2+document.body.scrollLeft+"px"; 
}

/*设置客户端的高和宽*/
function getClientBounds(){
    var clientWidth;
    var clientHeight;
    
    clientWidth = document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth : document.body.clientWidth;
    clientHeight = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight :   document.body.clientHeight;
        
    return {width: clientWidth, height: clientHeight};
}
 

</script>
	<body id="body">
		<div class="honghu">
			<A target="_block" href="ModuleFunctionAction!findMfByUser.action"><img
					style="position: absolute; left: 600px; top: 240px;" id="centerimg"
					src="images/wxx.gif" width="195" height="192" /> </A>
		</div>
		<a target="_block" id="gold" style="position: absolute;"
			href="ModuleFunctionAction!findMfByIdForJump.action?id=560"
			class="gold"></a>
		<a target="_block" id="wood" style="position: absolute;"
			href="ModuleFunctionAction!findMfByIdForJump.action?id=561"
			class="wood"></a>
		<a target="_block" id="water" style="position: absolute;"
			href="ModuleFunctionAction!findMfByIdForJump.action?id=562"
			class="water"></a>
		<a target="_block" id="fire" style="position: absolute;"
			href="ModuleFunctionAction!findMfByIdForJump.action?id=563"
			class="fire"></a>
		<a target="_block" id="ground" style="position: absolute;"
			href="ModuleFunctionAction!findMfByIdForJump.action?id=564"
			class="ground"></a>
	</body>
</html>
