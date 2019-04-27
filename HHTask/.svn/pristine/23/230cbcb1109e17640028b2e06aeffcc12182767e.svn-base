<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'huoqu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		<style type="text/css">
		body{
		text-align: center;
		font-size: 12px;
		margin: 0px;
		padding: 0px;
		}
		.clear{
		margin: 0px 0px 0px 0px;
		padding: 0px 0px 0px 0px;
		}
		.cssCenter{
		margin-top: 0px;
		margin-bottom: 0px;
		margin-left: auto;
		margin-right: auto;
		}
		</style>
  </head>
  <script type="text/javascript">
var ComAxCtrl=null;

/**
* 检测是否允许ActiveX控件运行
* @param true-允许ActiveX控件运行；false-不允许ActiveX控件运行
*/
 function isActiveXEnabled() {
//xmlhttp对象
var kXmlHttp = null;  
//如果是IE浏览器
if ($.browser.msie!=null && $.browser.msie == true) {
//微软IE支持的xmlhttp对象
var aVersionhs = ["MSXML2.XMLHttp.5.0",
                 "MSXML2.XMLHttp.4.0",
                 "MSXML2.XMLHttp.3.0",
                 "MSXML2.XMLHttp",
                 "Microsoft.XMLHttp"];
//IE创建方式
for (var i=0; i<aVersionhs.length; i++) {      
try {
kXmlHttp = new ActiveXObject(aVersionhs[i]);
return true;
} catch(e) { 

}      
}
} else {
try {
//非微软IE支持的xmlhttp对象
if (typeof XMLHttpRequest != "undefined") {
kXmlHttp = new XMLHttpRequest();
return true;
}     
} catch(e) {  

}
} 
   return false;
}

/**
* 判断ActiveX控件是否已经注册并加载到浏览器
* @return true-已经注册，并且加载；false-未注册控件或未能成功加载ActiveX控件。
*/
function isActiveXRegistered () {
try {
new ActiveXObject("MRCOMAX.MRCOMAXCtrl.1");
return true;
} catch (e) {

}
return false;
}

function initss(){

alert("isActiveXEnabled=="+isActiveXEnabled());
alert("isActiveXRegistered=="+isActiveXRegistered());

document.getElementById("fingerAGM").value = "";
document.getElementById("info").value = "";
var flag = false;
var flag1 = false;
if(ComAxCtrl==null){
flag = true;
}
if(flag){
ComAxCtrl = document.getElementById("ComAxCtrl");
ComAxCtrl.attachEvent("OnCommRecv",OnCommRecv);
flag1 = true;
}else{
if(!ComAxCtrl.IsCommOpen()){
flag1 = true;
}
}
if(flag1){
var result = ComAxCtrl.CommOpen(1,"9600,n,8,1");
if(result==0){
alert("无法找到设备！");
return false;
}
}
if(!ComAxCtrl.IsCommOpen()){
alert("设备未打开！");
return false;
}

//发送命令
document.getElementById("info").value="";
var cR = ComAxCtrl.CommSend(stringToHex("TPLT"));

} 
function OnCommRecv(data){
hexToString(data);
} 



function stringToHex(str){
　　　　 var val="";
　　　　 for(var i = 0; i < str.length; i++){
　　　　　　 if(val == "")
　　　　　　　　 val = "0X"+str.charCodeAt(i).toString(16);
　　　　　　 else
　　　　　　　　 val += ",0X" + str.charCodeAt(i).toString(16);
　　　　 }
　　　　 return val;
　　 }
function hexToString(str){
　　var val="";
　　var arr = str.split(" ");
　　for(var i = 0; i<arr.length;i++){
　　 val += String.fromCharCode("0x"+arr[i]);
　　}
　　var info = document.getElementById("info").value+val;
document.getElementById("info").value = info;
if(info.length==280){
//接收完数据
}
}
</script>
  
  
  
  <body>											
 
 <object id="ComAxCtrl" classid="clsid:1D82E7E4-CDEE-4894-92C2-A3E605D4F84E" codebase="<%=basePath %>ocx/ComAxCtrl.ocx"  style="width:1px; height:1px;"></object>
  <input type="hidden" id="fingerAGM" value="" />
<button onclick="initss()">读取数据</button>
<textarea rows="20" cols="50" id="info"></textarea>
 
  </body>



</html>
