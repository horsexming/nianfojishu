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
<STYLE type="text/css">
#fullbg1 {
	background-color: gray;
	left: 0;
	opacity: 0.5;
	position: absolute;
	top: 0;
	z-index: 3;
	filter: alpha(opacity =     50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
}

#dialog1 {
	background-color: #fff;
	border: 5px solid rgba(0, 0, 0, 0.4);
	left: 50%;
	margin: -200px 0 0 -200px;
	padding: 1px;
	position: fixed !important; /* 浮动对话框 */
	position: absolute;
	top: 45%;
	width: 400px;
	height: 200px;
	z-index: 5;
	border-radius: 5px;
	display: none;
}

#xiugaiIframe1 {
	background-color: #fff;
	height: 165px;
	line-height: 24px;
	width: 400px;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<div id="errormsg_div">
			
			</div>
			<div id="explain_div">
				<ul style="text-align: left; font-weight: bold; margin-left: 25px;">
					<li>1.此功能只用于解决图号间出现跳层问题；</li>
					<li>2.请不要填写未出现跳层情况的图号，以免出现重复现象;</li>
					<li>3.请严格按照规范填写</li>
					<li>4.处理跳层期间会导致系统编码申请功能暂时失效</li>
				</ul>
			</div>
			<hr/>
					<table class="table" style="width: 78%;">
						<tr>
							<th align="right"> 
								跳层前最后一个编码
							</th>
							<td>
								<input type="text" value="" id="firstChartNO" />
							</td>
						</tr>
						<tr>
							<th align="right"> 
								跳层后第一个编码
							</th>
							<td>
								<input type="text" value=""  id="secondChartNO" />
							</td>
						</tr>
					</table>
					<input type="button" value="提交" class="input"  onclick="submint()" id="sub"/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function submint(){
	var firstChartNO = $("#firstChartNO").val();
	var secondChartNO = $("#secondChartNO").val();
	if(firstChartNO == ''){
		$("#errormsg_div").html("<span type='color:red;'><b>请填写跳层前最后一个编码</b> </span>");
		return false;
	}else if(secondChartNO == ""){
		$("#errormsg_div").html("<span type='color:red;'><b>请填写跳层后第一个编码</b> </span>");
		return false;
	}
	$("#sub").attr("disabled","disabled")
$("body").append("<div id='fullbg1'></div>");
		$("body")
				.append(
						"<div id='dialog1' class='loginbox'>"
								+ "<iframe id='xiugaiIframe1' src='<%=basePath%>System/ChartNo/cq_zhemu.jsp' "
								+ "marginwidth='0' marginheight='0' hspace='0' vspace='0' "
								+ "frameborder='0' scrolling='yes'"
								+ " style='width: 100%;margin: 0px; padding: 0px;'>"
								+ "</iframe></div>")

		var sWidth, sHeight;
		//sWidth=document.body.offsetWidth;//得出当前屏幕的宽
		sWidth = document.body.clientWidth;//BODY对象宽度

		//sHeight=screen.height; //得到当前屏幕的高
		//sHeight=document.body.clientHeight;//BODY对象高度
		if (window.innerHeight && window.scrollMaxY) {
			sHeight = window.innerHeight + window.scrollMaxY;
		} else if (document.body.scrollHeight > document.body.offsetHeight) {
			sHeight = document.body.scrollHeight;
		} else {
			sHeight = document.body.offsetHeight;
		} //以上得到整个屏幕的高
		//		var bw = $("body").width();
		//		var bh = window.screen.availHeight;
		$("#fullbg1").css( {
			height : sHeight,
			width : sWidth,
			display : "block"
		});
		$("#dialog1").show();
		$.ajax( {
		type : "POST",
		url : "ChartNOSQAction_dealWithJumpLayer.action",
		data : {
				firstChartNO:firstChartNO,
				secondChartNO:secondChartNO,
			},
		dataType : "json",
		success : function(data) {
			if("true" == data){
				$("#errormsg_div").html("<span style='color:red;'><b>处理跳层问题成功!~</b> </span>");
			}else{
				$("#errormsg_div").html(data);
			}
				$("#firstChartNO").val("");
				$("#secondChartNO").val("");
				$("#sub").removeAttr("disabled");
				$("#dialog1").hide();
				$("#fullbg1").hide();
		}
	})
}


</SCRIPT>
	</body>
</html>
