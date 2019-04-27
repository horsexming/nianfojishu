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
		<STYLE type="text/css">
		.close{
			opacity:0.5;
			width: 80px;
		}
		.open{
			opacity:1;
			width: 80px;
		}
		/* green */
.green {
	height: 25px;
	width: 80px;
	margin-bottom:5px;
<%--	color: #e8f0de;--%>
	color: #000;
	border: solid 1px #538312;
	background: #64991e;
	background: -webkit-gradient(linear, left top, left bottom, from(#7db72f), to(#4e7d0e));
	background: -moz-linear-gradient(top,  #7db72f,  #4e7d0e);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#7db72f', endColorstr='#4e7d0e');
}
.green1 {
	height: 25px;
	width: 80px;
	margin-bottom:5px;
	color: #000;
	border: solid 1px #538312;
<%--	background: #64991e;--%>
<%--	background: -webkit-gradient(linear, left top, left bottom, from(#7db72f), to(#4e7d0e));--%>
	background: -moz-linear-gradient(top,  #7db72f,  #4e7d0e);
<%--	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#7db72f', endColorstr='#4e7d0e');--%>
}
.green:hover {
	background: #538018;
	background: -webkit-gradient(linear, left top, left bottom, from(#6b9d28), to(#436b0c));
	background: -moz-linear-gradient(top,  #6b9d28,  #436b0c);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#6b9d28', endColorstr='#436b0c');
}
.green:active {
	color: #a9c08c;
	background: -webkit-gradient(linear, left top, left bottom, from(#4e7d0e), to(#7db72f));
	background: -moz-linear-gradient(top,  #4e7d0e,  #7db72f);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#4e7d0e', endColorstr='#7db72f');
}

		</STYLE>
		
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
				<div>
					<div style="float: left; width: 20%;height: 200px;">
						<h3>开关量状态</h3>
						<input type="button" value="监控模式" class="button green1" id="monitoringModeOpen" disabled="disabled" onclick="colsePort(2,this)"/><br/>
						<input type="button" id="ajMalfunctionOpen" value="A机故障" class="button green1" disabled="disabled" onclick="colsePort(3,this)"/><br/>
						<input type="button" id="bjMalfunctionOpen" value="B机故障" class="button green1" disabled="disabled" onclick="colsePort(4,this)"/><br/>
						<input type="button" id="workMode1Open" value="工作模式1" class="button green1" disabled="disabled" onclick="colsePort(5,this)"/><br/>
						<input type="button" id="workMode2Open" value="工作模式2" class="button green1" disabled="disabled" onclick="colsePort(6,this)"/><br/>
						<input type="button" id="ajResetOpen" value="A机复位" class="button green1" disabled="disabled" onclick="colsePort(7,this)"/><br/>
						<input type="button" id="bjResetOpen" value="B机复位" class="button green1" disabled="disabled" onclick="colsePort(8,this)"/>
					</div>
					<div style="float: left; width: 20%;height: 200px;">
						<h3>模拟数据数据</h3>
						<textarea rows="6" cols="40" id="jiankongSend"></textarea>
						<input type="button" value="发送" onclick="sendJiankong()"/>
					</div>
				</div>
			
			
<%--				<div id="state"></div>--%>
<%--				<div id="logs"></div>--%>
<%--				<div id="show0" align="center">--%>
<%--					串口号：--%>
<%--					<SELECT id="chuankou" style="width: 80px; height: 21px;">--%>
<%--					</SELECT>--%>
<%--					波特率：--%>
<%--					<SELECT id="botelv" style="width: 80px; height: 21px;">--%>
<%--						<option value="110">--%>
<%--							110--%>
<%--						</option>--%>
<%--						<option value="300">--%>
<%--							300--%>
<%--						</option>--%>
<%--						<option value="600">--%>
<%--							600--%>
<%--						</option>--%>
<%--						<option value="1200">--%>
<%--							1200--%>
<%--						</option>--%>
<%--						<option value="2400">--%>
<%--							2400--%>
<%--						</option>--%>
<%--						<option value="4800">--%>
<%--							4800--%>
<%--						</option>--%>
<%--						<option value="9600" selected="selected">--%>
<%--							9600--%>
<%--						</option>--%>
<%--						<option value="14400">--%>
<%--							14400--%>
<%--						</option>--%>
<%--						<option value="19200">--%>
<%--							19200--%>
<%--						</option>--%>
<%--						<option value="38400">--%>
<%--							38400--%>
<%--						</option>--%>
<%--						<option value="56000">--%>
<%--							56000--%>
<%--						</option>--%>
<%--						<option value="57600">--%>
<%--							57600--%>
<%--						</option>--%>
<%--						<option value="115200">--%>
<%--							115200--%>
<%--						</option>--%>
<%--					</SELECT>--%>
<%--					<input id="open1" type="button" value="打开串口" onclick="openSp()" />--%>
<%--					<input id="close1" type="button" value="关闭串口" onclick="closeSp()" />--%>
<%--										style="display: none;" --%>
<%--				</div>--%>
<%--				<div id="show" align="center">--%>
<%--					接收数据：--%>
<%--					<textarea rows="6" cols="50" id="receiveInfor"></textarea><br/>--%>
<%--					<input type="checkbox" name="receivetype"--%>
<%--						id="receiveType" value="Hex" onchange="types('receive')">--%>
<%--					<label for="censusStatus1">--%>
<%--					按十六进制显示&nbsp;<input type="button" value="清空" onclick="qinkongreceive()"/>--%>
<%--					</label>--%>
<%--				</div>--%>
<%--				<div id="show1" align="center">--%>
<%--					发送数据：--%>
<%--					<textarea rows="6" cols="50" id="sendInfor"></textarea>--%>
<%--				</div>--%>
<%--				<div id="show1" align="center">--%>
<%--					<input type="checkbox" name="sendtype"--%>
<%--						id="sendtype1" value="Hex" onchange="types('send')">--%>
<%--					<label for="censusStatus1">--%>
<%--					按十六进制发送&nbsp;--%>
<%--					</label>--%>
<%--					<input type="button" value="发送" onclick="sendMe()"/>--%>
<%--					<input type="button" value="清空" onclick="qinkongsend()"/>--%>
<%--				</div>--%>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var bool = false;

function qinkongsend(){
	$("#sendInfor").val("");
}
function qinkongreceive(){
	$("#receiveInfor").val("");
}
function openSp() {
	$.ajax( {
		url : "AccessEquipmentAction_openSp.action",
		type : 'post',
		data : {
			"commName" : $("#chuankou").val(),
			"baudrate" : $("#botelv").val()
		},
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
function closeSp() {
	$.ajax( {
		url : "AccessEquipmentAction_closeSp.action",
		type : 'post',
		data : {
			"commName" : $("#chuankou").val(),
			"baudrate" : $("#botelv").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				$("#close1").hide();
				$("#open1").show();
				bool = false;
			} else {
				$("#open1").hide();
				$("#close1").show();
			}
			alert(data.message);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function sendMe() {
	$.ajax( {
		url : "AccessEquipmentAction_sendMess.action",
		type : 'post',
		data : {
			"sendInfor" : $("#sendInfor").val()
		},
		dataType : 'json',
		success : function(data) {
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function types(types) {
	var str = types+"type";
	var types1 = document.getElementsByName(str);
	var sendtype = "";
	for ( var i = 0; i < types1.length; i++) {
		if (types1[i].checked == true) {
			sendtype = types1[i].value;
			break;
		}
	}
	$.ajax( {
		url : "AccessEquipmentAction_chengeType.action",
		type : 'post',
		data : {
			"types" : types,
			"leitype" : sendtype
		},
		dataType : 'json',
		success : function(data) {
			//alert(data);
		},
		error : function() {
			alert("服务器异常!");
		}
	})
}

$(function() {
	$.ajax( {
		url : "AccessEquipmentAction_huoquCHuankou.action",
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$(data).each(
					function() {
						$("<option value='" + this + "'>" + this + "</option>")
								.appendTo("#chuankou")
					});
		},
		error : function() {
			alert("服务器异常!");
		}
	})
});
$(function() {
	longPolling();
});
function selectPort(){
	$.ajax( {
		url : "DiJianAction_selectPortStatus.action",
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data[1]){
<%--				$("#monitoringModeOpen").removeClass("close");--%>
<%--				$("#monitoringModeOpen").className("open");--%>
				$("#monitoringModeOpen").removeAttr("disabled");
				document.getElementById("monitoringModeOpen").className = "green";
			}else{
				$("#monitoringModeOpen").attr("disabled", "disabled");
				document.getElementById("monitoringModeOpen").className = "green1";
			}
			if(data[2]){
				$("#ajMalfunctionOpen").removeAttr("disabled");
				document.getElementById("ajMalfunctionOpen").className = "green";
			}else{
				$("#ajMalfunctionOpen").attr("disabled", "disabled");
				document.getElementById("ajMalfunctionOpen").className = "green1";
			}
			if(data[3]){
				$("#bjMalfunctionOpen").removeAttr("disabled");
				document.getElementById("bjMalfunctionOpen").className = "green";
			}else{
				$("#bjMalfunctionOpen").attr("disabled", "disabled");
				document.getElementById("bjMalfunctionOpen").className = "green1";
			}
			if(data[4]){
				$("#workMode1Open").removeAttr("disabled");
				document.getElementById("workMode1Open").className = "green";
			}else{
				$("#workMode1Open").attr("disabled", "disabled");
				document.getElementById("workMode1Open").className = "green1";
			}
			if(data[5]){
				$("#workMode2Open").removeAttr("disabled");
				document.getElementById("workMode2Open").className = "green";
			}else{
				$("#workMode2Open").attr("disabled", "disabled");
				document.getElementById("workMode2Open").className = "green1";
			}
			if(data[6]){
				$("#ajResetOpen").removeAttr("disabled");
				document.getElementById("ajResetOpen").className = "green";
			}else{
				$("#ajResetOpen").attr("disabled", "disabled");
				document.getElementById("ajResetOpen").className = "green1";
			}
			if(data[7]){
				$("#bjResetOpen").removeAttr("disabled");
				document.getElementById("bjResetOpen").className = "green";
			}else{
				$("#bjResetOpen").attr("disabled", "disabled");
				document.getElementById("bjResetOpen").className = "green1";
			}
<%--			if(data[0]){--%>
<%--				document.getElementById("energizeOpen").className = "open";--%>
<%--			}else{--%>
<%--				document.getElementById("energizeOpen").className = "close";--%>
<%--			}--%>
			
		},
		error : function() {
			alert("服务器异常!");
		}
	})
}
//发送监控模拟信号
function sendJiankong(){
	$.ajax( {
		url : "DiJianAction_sendJiankong.action",
		type : 'post',
		data : {
			"jiankongSend" : $("#jiankongSend").val()
		},
		dataType : 'json',
		success : function(data) {
		
		},
		error : function() {
			alert("服务器异常!");
		}
	})
}

function colsePort(id,obj){
	$.ajax( {
		url : "DiJianAction_colsePort.action",
		type : 'post',
		data : {
			"id" : id
		},
		dataType : 'json',
		success : function(data) {
			obj.className = "green1";
			obj.disabled = true;
		},
		error : function() {
			alert("服务器异常!");
		}
	})
}

function longPolling() {
	selectPort();
	if (bool) {
		//打开串口的时候监听对应串口接收的信息
		$.ajax( {
			url : 'AccessEquipmentAction_dijian.action',
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
				//document.getElementById("receiveInfor").innerHTML=shuo;
<%--				$("#state").append(--%>
<%--						"[state: " + textStatus + ", data: { " + data--%>
<%--								+ "} ]<br/>");--%>
				if (textStatus == "success") { // 请求成功
					longPolling();
				}
			}
		});
	} else {
		setTimeout("longPolling()", 2000);
	}
}<%--           function longPolling11() {--%>
<%--                	$("#state").append("[state: " + " ]<br/>");--%>
<%--                	if(bool){--%>
<%--                		//打开串口的时候监听对应串口接收的信息--%>
<%--	                    $.ajax({--%>
<%--	                        url : 'AccessEquipmentAction_dijian.action',--%>
<%--	                        data: {"commName": $("#chuankou").val()},--%>
<%--	                        dataType: "json",--%>
<%--	                        timeout: 10000,--%>
<%--	                        error: function (XMLHttpRequest, textStatus, errorThrown) {--%>
<%--	                            $("#state").append("[state: " + textStatus + ", error: " + errorThrown + " ]<br/>");--%>
<%--	                            if (textStatus == "timeout") { // 请求超时--%>
<%--	                                longPolling(); // 递归调用--%>
<%--	                                // 其他错误，如网络错误等--%>
<%--	                                } else {--%>
<%--	                                    longPolling();--%>
<%--	                                }--%>
<%--	                            },--%>
<%--	                        success: function (data, textStatus) {--%>
<%--	                            $("#state").append("[state: " + textStatus + ", data: { " + data + "} ]<br/>");--%>
<%--	                            if (textStatus == "success") { // 请求成功--%>
<%--	                                longPolling();--%>
<%--	                            }--%>
<%--	                        }--%>
<%--	                    });--%>
<%--                	}else{--%>
<%--                		setTimeout("longPolling11();",1000);--%>
<%--                	}--%>
<%--                }--%>
<%--			3、长连接iframe方式--%>
<%--			$(function () {--%>
<%--                window.setInterval(function () {--%>
<%--                    var url = "AccessEquipmentAction_dijian.action?timed=" + new Date().getTime();--%>
<%--                    var $iframe = $('<iframe id="frame" name="polling" style="display: none;" src="' + url + '"></iframe>');--%>
<%--                    $("body").append($iframe);--%>
<%--                --%>
<%--                    $iframe.load(function () {--%>
<%--                        $("#logs").append("[data: " + $($iframe.get(0).contentDocument).find("body").text() + " ]<br/>");--%>
<%--                        $iframe.remove();--%>
<%--                    });--%>
<%--                }, 5000);--%>
<%--                --%>
<%--            });--%>

        </script>
	</body>
</html>
