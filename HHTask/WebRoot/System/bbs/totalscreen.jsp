<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${name}</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">


		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script
			src="${pageContext.request.contextPath}/javascript/Swiper-2.7.6/idangerous.swiper.js">

</script>
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/javascript/Swiper-2.7.6/idangerous.swiper.css">


		<style>
body {
	background: #ffffff;
}

* {
	margin: 0;
	padding: 0;
}

.swiper-container {
	width: 100%;
	height: 100%;
}

.blue-slide {
	background: #4390EE;
}

.red-slide {
	background: #CA4040;
}

.orange-slide {
	background: #FF8604;
}

.swiper-slide {
	line-height: 300px;
	color: #fff;
	font-size: 36px;
	text-align: center;
}

.pagination {
	position: absolute;
	z-index: 20;
	bottom: 10px;
	width: 100%;
	text-align: center;
}

.swiper-pagination-switch {
	display: inline-block;
	width: 8px;
	height: 8px;
	border-radius: 8px;
	background: #555;
	margin: 0 5px;
	opacity: 0.8;
	border: 1px solid #fff;
	cursor: pointer;
}

.swiper-active-switch {
	background: #fff;
}
</style>
	</head>

	<body>
		<div class="header_bt" style="height: 30px; background-color: #1b1430">
			<div
				style="float: left; width: 7%; height: 30px; font-size: 26px; color: white">
				<strong>公告：</strong>
			</div>
			<div style="float: left; width: 93%; height: 30px; font-size: 26px;">
				<marquee direction="left" scrollamount="10"
					onmouseout="this.start()" onmouseover="this.stop()">
					<font color="YELLOW" id="show"></font>
				</marquee>
			</div>
		</div>
		<div class="swiper-container" style="height: 92%">
			<div class="swiper-wrapper">
				<div class="swiper-slide black-slide">
				</div>
			</div>
			<div id="urls">
				<div id="urllist">
					<s:iterator id="url" value="o">
						<input type="hidden" name="showURL" value="${url}">
					</s:iterator>
				</div>
			</div>
		</div>
		<div align="right"
			style="background: #1b1430; color: #fff; font-size: 26px;">
			<b>${companyenglishname}</b>
			<button id="btn1"
					style="width: 80px; height: 40px; color: #ffffff; background-color: blue; font-weight: bolder;">
				上一页
			</button>
			<button id="btn2"
				style="width: 80px; height: 40px; color: #ffffff; background-color: blue; font-weight: bolder;">
				下一页
			</button>
		</div>

		<%----%>
		<%--		<div>--%>
		<%--			<s:iterator id="url" value="o">--%>
		<%--				<input name="showURL" value="${url}">--%>
		<%--			</s:iterator>--%>
		<%--		</div>--%>


		<script type="text/javascript">
var mySwiper = new Swiper('.swiper-container', {
	loop : true,
	paginationClickable : true
});
$(function() {
	//公告
	$.ajax( {
		url : "NoticeAction!show.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(useradsfa) {
			$("#show").empty();//清空
		var message = "";
		$(useradsfa).each(function(i, n) {
			message += n.content + "&nbsp;&nbsp;&nbsp;&nbsp;";
		});
		$("#show").html(message);
	}
	});
});

var z = 0;
var interval;
var id_of_settimeout;

//供子页面跳转
//跳转都是维护input[name='showURL'] 这个数组 ，这段可以抽象出来
function next() {
	var size = 0;
	var src = new Array();
	$("input[name='showURL']").each(function(i, element) {
		src.push($(this).val());
		size = i;
	});
	if (src[z] != null) {
		var url = "<iframe id='iframe1'style='width: 100%; height: 100%; margin: 0px; padding: 0px;'src="
				+ src[z] + '?screen.id=${screen.id}&id=${screen.id}></iframe>';
		if (src[z].indexOf("?") > 0) {
			url = "<iframe id='iframe1'style='width: 100%; height: 100%; margin: 0px; padding: 0px;'src="
					+ src[z]
					+ '&screen.id=${screen.id}&id=${screen.id}></iframe>'
		}
		var newSlide = mySwiper.getSlide(1).html(url);
		if (interval != null) {
			clearsetInterval();
		}
		auto2nextpage();
	}
	z++;
	if (z > size) {
		z = 0;
	}
}

//首次
next();

function auto2nextpage(time) {
	if (time == null) {
		time = 60;
	} else {
		time += 60;
	}
<%--	alert(time);--%>
	interval = setInterval(next, 1000 * time);
}

function clearsetInterval() {
<%--	alert(interval);--%>
	clearInterval(interval);
}


// function previous() {
// 	mySwiper.swipePrev();
// }
// $('#btn1').click(function(){
// 	mySwiper.swipePrev();
// })

//上一页
$('#btn1').click(function(){
    var size=0;
    var src= new Array();
    $("input[name='showURL']").each(function(i,element){
        src.push($(this).val());
        size=i;
    })

	z=z-1;
	//此为最后一页
	if(z<0){z=size-1;}
	//此为第一页
	else if(z==0){z=size;}
	else if(z>0){
        z=z-1;
	}

    var url="<iframe id='iframe1'style='width: 100%; height: 100%; margin: 0px; padding: 0px;'src="
        +src[z]+'?screen.id=${screen.id}&id=${screen.id}></iframe>'
    if(src[z].indexOf("?")>0){
        url="<iframe id='iframe1'style='width: 100%; height: 100%; margin: 0px; padding: 0px;'src="
            +src[z]+'&screen.id=${screen.id}&id=${screen.id}></iframe>'
    }
    var newSlide = mySwiper.getSlide(1).html(url);
    z++;
    if(z>size){z=0;}

})

//下一页
$('#btn2').click(function(){
	var size=0;
	var src= new Array();
	$("input[name='showURL']").each(function(i,element){
		src.push($(this).val());
		size=i;
	})
	
	var url="<iframe id='iframe1'style='width: 100%; height: 100%; margin: 0px; padding: 0px;'src=" 
		+src[z]+'?screen.id=${screen.id}&id=${screen.id}></iframe>'
	if(src[z].indexOf("?")>0){
		url="<iframe id='iframe1'style='width: 100%; height: 100%; margin: 0px; padding: 0px;'src=" 
		+src[z]+'&screen.id=${screen.id}&id=${screen.id}></iframe>'
	}
	var newSlide = mySwiper.getSlide(1).html(url);
		z++;
	if(z>size){z=0;}
})




// 公告,内容列表刷新 1h
$(function () {

    var totalinterval = setInterval(RefreshNotice, 1000 * 3600);
});

function RefreshNotice() {
    $.ajax({
        url: "NoticeAction!show.action",
        type: 'post',
        dataType: 'json',
        cache: false,//防止数据缓存
        success: function (useradsfa) {
            $("#show").empty();//清空
            var message = "";
            $(useradsfa).each(function (i, n) {
                message += n.content + "&nbsp;&nbsp;&nbsp;&nbsp;";
            });
            $("#show").html(message);
        }
    });
    $.ajax({
        url: "ScreenContentAction_findScreenContent.action?id=${screen.id}",
        type: 'post',
        dataType: 'json',
        cache: false,//防止数据缓存
        success: function (urllist) {
            $("#urllist").remove();
            $("#urls").append("<div id='urllist'></div>");
            $(urllist).each(function (i, n) {
                $("#urllist").append("<input type='hidden' name='showURL' value=" + urllist[i] + ">");
            });
        }
    });
};
// 公告,内容列表刷新 end



</script>
	</body>
</html>
