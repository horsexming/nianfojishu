<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<meta name="viewport" content="width=device-width, initial-scale=1" />		
<%--		<script type="text/javascript" src="js/time.js">--%>
<%--		</script>--%>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.c_1 {
	border: solid 1px #ADADAD;
	border-radius: 50px;
	width: 100px;
	height: 100px;
	cursor: hand;
	z-index: 0;
}

 body {
	background-color: #28284a;
}
</style>
	</head>
	<body >
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
			</div>
			<div align="center">
				<center>
				<br/>
				<div style="width: 100%; height: auto; color: #fff; ">
					<p   style="font-size: 45px; float: left; width: 39%; height: 40px; ">
						<b>上海红湖访客管理系统</b>
					</p>
					<p style="float: left; width: 30%;">
						<span style="font-size:20px;  " >当前时间:</span><span id="shijan" style="font-size:20px;"></span>
					</p>
					<p style="float: left; width: 30%; height: 40px;">
						<img alt="" src="<%=basePath %>/img/pebs.jpg">
						&nbsp;
					</p>
				</div>
		<div style="width: 100%;">
		<div style="float: left;;height: 100%;width: 20px;">
						&nbsp;
					</div>
<%--				<div style="float: left; width: 8% ;height:100px; background-color: #3d4495; margin-top: 25px;" >--%>
<%--					--%>
<%--				</div>--%>
					<table  width="47%;"  style="float:left; font-weight:bold; color: #FFFF77;">
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr align="left" height="50px" bgcolor="#288ecc" style="font-size: 30px; color: #ffffff;">
							<th width="18%" rowspan="2" bgcolor="#3d4495" align="center" id="jin_th" >
								<div>
									<strong><font color="#8ec232" size="5px;" style="font-family: 微软雅黑;" >进门</font></strong>
								</div>
								<div style="height: 10%">
									&nbsp;
								</div>
								<div>
									<img alt="" src="<%=basePath %>/img/jinmen.png">
								</div>
							</th>
							<th width="25%" >
								来访人
							</th>
							<th width="25%">
								被访人
							</th>
							<th width="32%">
								来访编号/状态
							</th>
						</tr>
						
							<s:iterator id="unpricetest" value="visitList" status="statussdf">
							<s:if test='#unpricetest.visit_laiStatus=="进门中"'>
						<tr align="center" bgcolor="#3d4495"style="font-size: 30px; color: #ffffff;" height="50px">
								<td>
									<input type="hidden" name="jishu1"/>
									${unpricetest.visitsName}
								</td>
								<td>
									${unpricetest.shouFangName}
								</td>
								<td>
									<b>
									<s:if test="#unpricetest.visit_laiStatus == '进门中'">
										<font color="#aea24c">${unpricetest.visitsCode}/待出</font>	
									</s:if>
									<s:else>
										<font color="red">${unpricetest.visitsCode}/禁入</font>	
									</s:else>
									</b>
								</td>
							</tr>
							</s:if>
							</s:iterator>
							<tr bgcolor="#3d4495"  height="50px" style=" font-size: 20px; color: #ffffff;">
								<th colspan="3" align="left">
									共计:<span id="jinmenspan1" style="color:#E8E86C; font-size: 30px;font-family;">0</span>车次
								</th>
							</tr>
					</table>
					<div style="float: left;;height: 100%;width: 20px;">
						&nbsp;
					</div>
					<table    width="47%;" style="float:left;  font-weight:bold; color:#EE0030;">
					<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr align="left"  height="50px" bgcolor="#288ecc" style="font-size: 30px;color: #ffffff;">
							<th width="18%" rowspan="2" bgcolor="#3d4495" align="center" id="chu_th">
								<div>
									<strong><font color="#8ec232" size="5px;" style="font-family: 微软雅黑;" >出门</font></strong>
								</div>
								<div style="height: 10%">
									&nbsp;
								</div>
								<div>
									<img alt="" src="<%=basePath %>/img/chumen.png">
								</div>	
							</th>
							<th width="25%">
								来访人
							</th>
							<th width="25%">
								被访人
							</th>
							<th width="32%">
								来访编号/状态
							</th>
						</tr>
						<s:iterator id="unpricetest" value="visitList" status="statussdf">
						<s:if test='#unpricetest.visit_laiStatus=="出门中"'>
						<tr align="center" height="50px" bgcolor="#3d4495" style="font-size: 30px; color: #ffffff;">
							<td>
								<input type="hidden" name="jishu2"/>
								${unpricetest.visitsName}
							</td>
							<td>
								${unpricetest.shouFangName} 
							</td>
							<td>
								<b>
									<s:if test="#unpricetest.visit_laiStatus != '已出门'">
										<font color="#aea24c">${unpricetest.visitsCode}/待出</font>	
									</s:if>
									<s:elseif test="#unpricetest.visit_laiStatus == '已出门'">
										<font color="##8aaa83">${unpricetest.visitsCode}/已出</font>	
									</s:elseif>
								</b>
							</td>
						</tr>
						</s:if>
					</s:iterator>
						<tr bgcolor="#3d4495" height="50px" style="font-size: 20px;color: #ffffff;">
								<th colspan="3" align="left">
									共计:<span id="jinmenspan2" style="color:#E8E86C; font-size: 30px;font-family;">0</span>车次
								</th>
							</tr>
					</table>
<%--				</div>--%>
			</div>
				</center>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
			//重新加载页面
			function reLoad() {
				window.location.reload();
			}
			//页面定时刷新(1分钟/次)
			setTimeout(reLoad, 3000 * 60);
			$(function(){
				getshijan();
				
				var jishu1 =document.getElementsByName("jishu1");
				if(jishu1!=null && jishu1.length>0){
					$("#jinmenspan1").html(jishu1.length);
						document.getElementById("jin_th").setAttribute("rowspan",2+jishu1.length);
				}
				var jishu2 =document.getElementsByName("jishu2");
				if(jishu2!=null && jishu2.length>0){
					$("#jinmenspan2").html(jishu2.length);
					document.getElementById("chu_th").setAttribute("rowspan",2+jishu2.length);
				}
			})
			function getshijan(){
				var  date = new Date();
				var month = date.getMonth();//月份
				var day = date.getDate();// 日
				var hours = date.getHours();// 小时
				var minutes = date.getMinutes();//分
				var seconds = date.getSeconds();//秒
				if(seconds<10){
					seconds="0"+seconds;
				}
				if(minutes<10){
					minutes = "0"+minutes;
				}
				if(hours<10){
					hours = "0"+hours;
				}
				if(day<10){
					day = "0"+day;
				}
				month+=1;
				if(month<10){
					month = "0"+month;
				}
				var shijian = date.getFullYear()+"."+month+"."+day+" "+hours+":"+minutes;
				$("#shijan").html(shijian);
			}
			setInterval("getshijan()", 1000 );
		</script>
	</body>
</html>
