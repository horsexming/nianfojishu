<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<%@include file="/util/sonHead.jsp"%>
	<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<%-- 	<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script>  --%>
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
		#l-map{height:80%;width:100%;}
		#r-result{width:100%;}
		#searchResultPanel{
			border:1px solid #C0C0C0;
			position:absolute;
			left:10%
		}
	</style>
	<script charset="utf-8" src="http://api.map.baidu.com/api?v=1.3"></script>
	<title>
<!-- 		关键字输入提示词条 -->
	</title>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="r-result" class="row">
		<div class="col-xs-4">
			<input type="text" id="suggestId" placeholder="位置检索"
									class="search_text form-control typeahead" accesskey="s"
									tabindex="9" autocomplete="off" x-webkit-speech=""
									x-webkit-grammar="builtin:search" />	
		</div>
		<div style="height: 30px;line-height: 50px;" class="col-xs-4">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="showMessage" style="color: red;display: none;font-size: 20px;line-height: 30px;height: 30px;">位置已选择</font>
		</div>
		<div class="col-xs-4">
			<input type="button" value="确定位置" onclick="confirmLocation()" style="height: 30px;width: 70px;">
		</div>
	</div>
	<div id="searchResultPanel" >
	</div>
	<div id="l-map"></div>
	<div><p>注：点击地图的某一点，出现位置已选择，点击[确定位置]后，选择公出位置完成</div>
</body>
</html>
<script type="text/javascript">
	var chooseLat; //选择的纬度
	var chooseLng; //选择的经度

	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}
	var map = new BMap.Map("l-map");
	map.centerAndZoom("上海",12);                   // 初始化地图,设置城市和地图级别。
	
	//根据IP获取定位
	function myFun(result){
		var cityName = result.name;
		map.setCenter(cityName);
		console.log(cityName);
		//alert("当前定位城市:"+cityName);
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
	
	
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	function showInfo(e){
		//document.getElementById('').style.display='block';
		$("#showMessage").show();
		$("#showMessage").text("位置已选择"+e.currentTarget.cityName);
// 		alert(e.point.lng + ", " + e.point.lat);
		chooseLat = e.point.lat;
		chooseLng = e.point.lng;
	}
	// 添加带有定位的导航控件
	  var navigationControl = new BMap.NavigationControl({
	    // 靠左上角位置
	    anchor: BMAP_ANCHOR_TOP_LEFT,
	    // LARGE类型
	    type: BMAP_NAVIGATION_CONTROL_LARGE,
	    // 启用显示定位
	    enableGeolocation: true
	  });
	  map.addControl(navigationControl);
	map.addEventListener("click", showInfo);
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	
	
	//确定位置
	function confirmLocation(){
		
		if(chooseLat!=null && chooseLng!=null){
			//子窗口给父窗口元素赋值
			window.parent.chageDiv('none');
			$("#lat",window.parent.document).attr("value",chooseLat);
			$("#lng",window.parent.document).attr("value",chooseLng);
			$("#showMapMessage",window.parent.document).text("已选择");
		}else{
			alert("请选择公出位置");
		}
	}
</script>


