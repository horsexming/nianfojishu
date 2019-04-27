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
		<style type="text/css">
.users {
	position: absolute;
	top: 100px;
	left: -4%;
	width: 23%;
	height: 200px;
	margin-left: 5%;
	margin-top: 100px;
	font-size: large;
	font-family: 黑体;
}
.users ul {
 list-style:inside; 

}
.users ul li{
			margin-top:5px;
			 border-bottom: 1px solid #666 dotted;
			 list-style-type:disc;
	 }
.b_0 {
	position: relative;
	float: left;
	width: 18px;
	top: 50px;
}
.b_1 {
	width: 22%;
	float: left;
}

.b_2 {
	width: 36%;
	float: left;
}

.b_3 {
	width: 39%;
	float: left;
}

.z {
	width: 71%;
	height: auto;
	float: left;
	border-left: solid 1px #000000;
}

.a {
	position: relative;
	width: 100%;
	border-bottom: dotted 1px #000000;
	overflow: hidden;
	padding: 10 0 20 0px;
}

.c_2 {
	width: 100%;
	position: relative;
	font-size: large;
	position: relative;
	padding-top: 80px;
}

.c_2_span {
	text-decoration: underline;
	width: 80%;
	top: 70px;
}

.c_1 {
	position: relative;
	border: solid 1px #ADADAD;
	border-radius: 35px;
	width: 70px;
	height: 70px;
	background-color: #0071bf;
	cursor: hand;
	z-index: 0;
}

.c_3 {
	position: relative;
	width: 260px;
	height: 111px;
	border: solid 1px #ADADADk ;
	background-color: #0071bf;;     
}
 .c_1_font{
 color: #FFFFFF;
 font-size: x-large;
 
 }                                                                                
.c_3_font {
	color: #FFFFFF;
}

.b_0_hr {
	float: right;
	width: 100%;
	height: 2px;
	border: none;
	background-color: #909090;
}
.c1_span{
	position: relative;
	top: 12px;

}
</style>
		<SCRIPT type="text/javascript">
window.onscroll=function(){ 
	var oDiv=document.getElementById("userxin"); 
	oDiv.style.top=document.body.scrollTop + 100;  //控制上下位置
//oDiv.style.left = document.body.scrollLeft + 300; //控制横向位置

} 
<%--window.parent.onscroll=function(){ --%>
<%--	var oDiv=document.getElementById("userxin"); --%>
<%--	var h = window.parent.document.body.scrollTop;--%>
<%--	if(h==0){--%>
<%--		alert('到顶了')--%>
<%--		oDiv.style.top = 120;--%>
<%--	}--%>
<%--	alert(h)--%>
<%--	oDiv.style.top=h - 200;  //控制上下位置--%>
<%----%>
<%--} --%>

</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
<%--			<div style="width: 100%;height: 2%;" align="center">--%>
<%--				<br/>--%>
<%--				<input type="button" value="返回" onclick="history.go(-1)" class="input">--%>
<%--			</div>--%>
				<div style="width: 10%; height: 100%; float: left;">
					<div style="" class="users" id="userxin" align="left">
					<ul  >
						
					</ul>
				
					</div>
				</div>
				<div class="z" align="left">
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1" style="">
							<br />
							<s:if test="price != null">
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="zhuandao('PriceAction!findPriceById.action?id=${price.id}&statue=mingxi')">
									<span><br /> <font class="c_1_font"  >价格</font>
									</span>
								</div>
							</s:if>
							<s:else>
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)">
									<span><br /> <font class="c_1_font">价格</font>
									</span>
								</div>
							</s:else>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">件号:${price.partNumber} </span>
							</div>
						</div>
						<div class="b_3" style="">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<ul>
									<li>件号:${price.partNumber}</li>
									<li>名称:${price.name}</li>
									<li>含税价:${price.hsPrice }&nbsp;&nbsp; 不含税价:${price.bhsPrice }&nbsp;&nbsp; 税率:${price.taxprice}</li>
								</ul>
							</div>
						</div>
					</div>
					<s:if test="barContract!=null">
					<div class="a">
						<div class="b_0">

							<hr class="b_0_hr" />
						</div>
						<div align="left" class="b_1">
							<br />
								<div align="center" class="c_1" onmousemove="changover(this)"
									onmouseout="changout(this)"
									onclick="zhuandao('bargainAction_salBarContract.action?barContract.id=${barContract.id}&status=print')">
									<span><br /> <font class="c_1_font">合同</font>
									</span>
								</div>
						</div>
						<div align="left" class="b_2" style="">
							<div align="left" class="c_2">
								<span class="c_2_span">合同编号:${barContract.contract_num} </span>
							</div>
						</div>
						<div class="b_3">
							<div class="c_3" align="center" onmousemove="changover(this)"
								onmouseout="changout(this)">
								<a href="">查看合同</a>
							</div>
						</div>
					</div>
				</s:if>
			</div>
		</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function tanchu(src,title){
	$("#xiugaiIframe").attr("src",src);
	chageDiv('block',title);
}
function changover(obj){
	$(obj).css({
		background: "#909090",
	})
	
}
function changout(obj){
	$(obj).css({
		background: "#0071bf",
	})
	}
function zhuandao(src){
		window.open(src)
		
}

$(function(){
	var zhuanzhengTime = "${ck.zhuanzhengTime}".substring(0,10);
	var diaodongTime =  "${ck.diaodongTime}".substring(0,10);
	var	jinshengTime =	"${ck.jinshengTime}".substring(0,10);
	var zzarray = zhuanzhengTime.split("-");
	$("#zz_font").html(zzarray[0]+"年"+zzarray[1]+"月"+zzarray[2]+"日");
	var ddarray = diaodongTime.split("-");
	$("#dd_font").html(ddarray[0]+"年"+ddarray[1]+"月"+ddarray[2]+"日");
	var jsarray = jinshengTime.split("-");
	$("#js_font").html(jsarray[0]+"年"+jsarray[1]+"月"+jsarray[2]+"日");
})


</SCRIPT>
	</body>
</html>
