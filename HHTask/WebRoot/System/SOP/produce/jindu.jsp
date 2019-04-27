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
			#baifen {
		position: absolute;
		left: 125px;
		}

#jindu_div {
	position: relative;
}
		
		</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
					<div style="width: 350px; height: 20px; border: 1px solid;"
						id="jindu_div">
						<div
							style="width: 0px; height: 20px; background-color: green; display: none; float: left;"
							id="color_div">
						</div>
						<span id="baifen" style=""></span>
					</div>
					<span id="miaoshu"></span>
			</div>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
var index = 0;
var jindu_width = $("#jindu_div").css("width");
var sum = parseInt(jindu_width.split('px')[0]);
var time0;
var jindu_sum=0 ;
var jindu_cl=0;
function aa(URL0,URL1){
	$.ajax( {
		type : "POST",
		url : URL0,
		data : {},
		dataType : "json",
		async : false,
		success : function(data) {
			if(data!=null && data.length==2){
			jindu_sum = data[0];
			jindu_cl = data[1];
			if(jindu_sum!=null && jindu_sum>0
					&& jindu_cl!=null && jindu_cl>0){
			index = jindu_cl/jindu_sum;
	var color_width =sum*index;
	if(color_width>0){
		$("#color_div").css({
			width:color_width+'px',
			display: 'block'
	})
	index =Math.round(index*1000)/10
	$("#baifen").html(index+"%");
	}
	
	if(index>0 && index%100==0){
			$.ajax( {
		type : "POST",
		url : URL1,
		async : false,
		dataType : "json",
		success : function(data) {
			jindu_sum = 0;
			jindu_cl=0;
			index =0;
		}
	})
	clearInterval(time0);
	}
	$("#miaoshu").html('共<b style="color:red;">'+jindu_sum+'</b>条数据，已处理<b style="color:red;">'+jindu_cl+'</b>条数据。还余<b style="color:red;">'+(jindu_sum-jindu_cl)+'</b>条。');
	}
		}	
		}
	})
}

$(function(){
	time0 =setInterval("aa('ProcardTemplateAction!test.action','ProcardTemplateAction!removeSession.action')", 1000);
})


</SCRIPT>
	</body>
</html>
