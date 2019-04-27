<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'openck.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="/util/sonHead.jsp"%>
  </head>
  
  <body>
  <%@include file="/util/sonTop.jsp"%>
  <div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
				</div>
			</div>
			
			<div align="center">
				<div id="state"></div>
				<div id="logs"></div>
				<div id="show0" align="center">
					串口号：
					<SELECT id="chuankou" style="width: 80px; height: 21px;">
						<s:iterator  id="comhao" value="list" status="stauts"> 
						<option value="${comhao}">${comhao}</option>
						</s:iterator> 
					</SELECT> 
					波特率：
					<SELECT id="botelv" style="width: 80px; height: 21px;">
						<option value="9600" selected="selected">
							9600
						</option>
						<option value="14400">
							14400
						</option>
						<option value="38400">
							38400
						</option>
						<option value="57600">
							57600
						</option>
						<option value="115200">
							115200
						</option>
					</SELECT>
					
					<input id="open1" type="button" value="打开串口" onclick="openSp()" />
					<input id="close1" type="button" value="关闭串口" onclick="closeSp()" />
					
	
				<div id="show" align="center">
					接收数据：
					<textarea rows="6" cols="50" id="receiveInfor">${systemjiehsou}</textarea>
				</div>
	
				
				<div id="show1" align="center">
					发送数据：
					<textarea rows="6" cols="50" class="fasonshuju">${data1}</textarea>
				</div>
				
				
				
				<div id="show1" align="center">
					<input type="button" value="发送" onclick="fasong()" />
					<input type="button" value="清空" />
				</div>
				
					<div id="cunboxniu"  style="margin-left: 900px; margin-top: -220px;" ><input type="checkbox"  class="cun" onclick="cunchu()" />存入用户特征值</div>
					<div id="duibitry"  style="margin-left: 900px;" ><input type="checkbox"  class="duibi" onclick="duibi()" />1:N对比</div>
				<div id="canshu" style="margin-left: 900px;">
				<div><p>参数P1</p><input type="text" class="p1"/></div>
				<div><p>参数P2</p><input type="text" class="p2" /></div>
				<div><p>参数P3</p><input type="text" class="p3" /></div>
				</div>
				</div>
			</div>
		</div>

		
  <%@include file="/util/foot.jsp"%>
  		<script type="text/javascript">
  		
  		

  		
  		
 //打开串口点击事件
function openSp(){

var bool = false;
		$.ajax({
			url : "FingerprintMgAction_zhiwenopne.action",
			type : 'post',
			data : {
			"commName" : $("#chuankou").val(),
			"baudrate" : $("#botelv").val()  },
			dataType : 'json',
			success : function(data) {
				if (data.success) {
				$("#open1").hide();
				$("#close1").show();
				bool = true;
			} else {
				$("#close1").hide();
				$("#open1").show();
			}
			alert(data.message);
			},
			error : function() {
				alert("服务器异常!");
			}
		});
}












	//关闭按钮点击事件
	function closeSp(){
	$.ajax({
			url : "FingerprintMgAction_zhiwenclose.action",
			type : 'post',
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				bool = false;
			}

	
});
}

function cunchu(){
$(".fasonshuju").val("F5 41 00 C4 00 00 85 F5");
}





	//发送数据
	function fasong(){
	var data=$(".fasonshuju").val();
	alert(data)
	
	if($("input[type='checkbox']").is(':checked')==true){
	var p1=  $(".p1").val();
	var p2= $(".p2").val();
	var p3= $(".p3").val();
	if(p1.length<2){
	p1=0+p1;
	}
	if(p2.length<2){
	p2=0+p2;
	}
	if(p3.length<2){
	p3=0+p3;
	}
	var p4=p1+" "+p2+" "+p3;
	window.location.href="FingerprintMgAction_sendData.action?data1="+data+"&&p4="+p4;
	}else{
	window.location.href="FingerprintMgAction_sendData.action?data1="+data;
	}
	
	}




$(function() {
	longPolling();
});








function longPolling() {
	if (bool) {
		//打开串口的时候监听对应串口接收的信息
		$.ajax( {
			url : 'AccessEquipmentAction_fanghui.action',
			data : {
				"commName" : $("#chuankou").val()
			},
			dataType : "json",
			timeout : 10000,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#state").append("[state: " + textStatus + ", error: " + errorThrown
								+ " ]<br/>");
				if (textStatus == "timeout") { // 请求超时
					longPolling(); // 递归调用
					// 其他错误，如网络错误等
				} else {
					longPolling();
				}
			},
			success : function(data, textStatus) {
				var receive=$("#receiveInfor").val();
				//shouming=shouming.replace(/\s+/g," "); 
				if(data!=null&&""!=data){
					var aaa=data.split("|");
					var shuo=receive;
					for(var i=0;i<aaa.length;i++){
						if(i<aaa.length-1){
					    	shuo=shuo+aaa[i]+"\n";
						}else{
							shuo=shuo+aaa[i];
						}
					}
					$("#receiveInfor").val(shuo);
				}

				if (textStatus == "success") { // 请求成功
					longPolling();
				}
			}
			
		});
			}
		}
        </script>
  
  
  </body>
</html>
