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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<style type="text/css">
body {
	background-color: #f2f2f2;
}

.div_a {
	height: 30px;
	width: 98%;
	margin-top:25px;
	font-family: 仿宋体;
	font-size: 15;
}

.div_b {
	height: 100%;
	width: 97%;
	margin-top:25px;
	border: 1px solid #000;
	border-right: none;
}

.div_a:hover {
	webkit-transform: scale(1.1);
	-moz-transform: scale(1.1);
	transform: scale(1.1);
	-webkit-border-radius: 8px;
	-moz-border-radius: 8px;
	border-radius: 8px;
	-webkit-box-shadow: 0 -1px 10px rgba(0, 0, 0, .5);
	-moz-box-shadow: 0 -1px 10px rgba(0, 0, 0, .5);
	box-shadow: 0 -1px 10px rgba(0, 0, 0, .5);
}

.div_c {
	float: left;
	width: 33%;
	height: 100%;
	border-right: 1px solid #000;
	font-family: 仿宋体;
	z-index: 1;
}

.div_c:hover {
	webkit-transform: scale(1.1);
	-moz-transform: scale(1.1);
	transform: scale(1.1);
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: 0 -1px 5px rgba(0, 0, 0, .5);
	-moz-box-shadow: 0 -1px 5px rgba(0, 0, 0, .5);
	box-shadow: 0 -1px 5px rgba(0, 0, 0, .5);
}

.div_c1 {
	position: relative;
	height: 20%;
}

.div_c2 {
	position: relative;
	height: 79%;
}

.duhuan {
	float: right;
	position: absolute;
	top: 15px;
	right: 150px;
}


.button {
	display: inline-block;
	zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */ *
	display: inline;
	vertical-align: baseline;
	margin: 0 2px;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/ 100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}

.button:hover {
	text-decoration: none;
}

.button:active {
	position: relative;
	top: 1px;
}
</style>
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
				<div style="width: 50%; height: 5px;"></div>
				<h2 style="font-size: 25">
					红心<font color="red">♥</font>园地
				</h2>
				<div style="width: 50%; height: 5px;"></div>
				<div class="div_a">
					<div style="width: 50%; height: 5px;"></div>
					<div style="width: 50%;">
						姓名:${integral.integralName}&nbsp;&nbsp;&nbsp;&nbsp;
						部门:${integral.integrdept}&nbsp;&nbsp;&nbsp;&nbsp;红心
						<font color="red">♥</font>:${integral.totalIntegral}&nbsp;&nbsp;&nbsp;&nbsp;
						当月累计消费:${integral.sumxf}
					</div>
				</div>
				
				<s:if test="bool">
					<div  style="float: right; position: absolute; top: 15px; right: 20px;">
					<input type="button" value="我要抽奖" class="button" id="cj_button" disabled="disabled"
						onclick="window.open('IntegralGiftAction_initchoujiang.action')"  />
				</div>
				<div 
					style="float: right; position: absolute; top: 15px; left: 150px;">
					<input type="button" value="报名夺宝" class="button" id= "bm_button"
						onclick="tanchu('<%=basePath %>IntegralGiftAction_findAlligSetList.action?status=baoming','报名夺宝')" />
				</div>
				</s:if>
				<s:else>
				<div align="left">
					<ul style="color: #DF3A01 ;margin:0;padding:0;" >
					<li><span style="display: inline-block;text-align: left;">三个月内有迟到、早退、缺勤、等考勤记录者不能参与抽奖活动</span></li>
					<li><span style="display: inline-block;text-align: left;">三个月内请假天数超过应当享受的年休天数总和的不能参与抽奖活动</span></li>
					<li><span style="display: inline-block;text-align: left;">违反公司规定违纪现象被发现者不能参与抽奖活动</span></li>
					<li><span style="display: inline-block;text-align: left;">一切解释权归上海红湖人力资源部所有</span></li>
				</ul>
				</div>
				
					
				<div  style="float: right; position: absolute; top: 15px; right: 20px;">
					<input type="button" value="我要抽奖" class="button"  disabled="disabled"/>
				</div>
				<div 
					style="float: right; position: absolute; top: 15px; left: 150px;">
					<input type="button" value="报名夺宝" class="button" disabled="disabled"/>
				</div>
				</s:else>
				<div class="duhuan" style="">
					<input type="button" value="我要兑换" class="button"
						onclick="tanchu('IntegralGiftAction_initaddintegralgift1.action','兑换礼品')">
				</div>
				<div class="choujiang"
					style="float: right; position: absolute; top: 15px; right: 280px;">
					<input type="button" value="兑换码兑换" class="button"
						onclick="tanchu('<%=basePath %>System/xinxi/integralgift/gift_dhnum.jsp','兑换礼品')" />
				</div>
				<div 
					style="float: right; position: absolute; top: 15px; left: 280px;">
					<input type="button" value="赠送红心" class="button"
						onclick="tanchu('<%=basePath %>System/xinxi/integralgift/integral_jy.jsp?totalIntegral=${integral.totalIntegral}','赠送积分')" />
				</div>
<%--				<div --%>
<%--					style="float: right; position: absolute; top: 15px; left: 20px;">--%>
<%--					<input type="button" value="帮人报名" class="button"--%>
<%--						onclick="tanchu('<%=basePath %>System/xinxi/integralgift/indianaGift_bm.jsp','帮人报名')" />--%>
<%--				</div>--%>
				<div class="div_b">
					<div class="div_c">
						<div>
							<div style="float: left; width: 20%;">
								&nbsp;
							</div>
							<div style="float: left; width: 60%;">
								<STRONG>个人红心兑换情况</STRONG>
							</div>
							<div align="right" style="float: left; width: 20%;">
								<a href="javascript:;"
									onclick="tanchu('IntegralAction_showListXf.action?xf.in_code=${integral.integrcode}&statue=person','个人积分消费情况')">更多</a>
							</div>
						</div>
						<table class="table">
							<tr style="background-color: #ece9d8">
								<th>
									序号
								</th>
								<th>
									消费红心
									<font color="red">♥</font>
								</th>
								<th>
									消费内容
								</th>
								<th>
									消费时间
								</th>
							</tr>
							<s:iterator value="xfList" id="pagelist1" status="pagestatus1">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')"
										style="height: 26.5px;">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')" style="height: 26.5px;">
								</s:else>
								<th>
									${pagestatus1.index+1}
								</th>
								<th>
									${pagelist1.xiaofeijifen}
								</th>
								<th>
									${pagelist1.neirong}
								</th>
								<th>
									${pagelist1.xiaofeitime}
								</th>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="div_c">
						<div class="div_c1">
							<div>
								<div style="float: left; width: 20%;">
									&nbsp;
								</div>
								<div style="float: left; width: 60%;">
									<STRONG>个人红心<font color="red">♥</font>兑换情况</STRONG>
								</div>
								<div align="right" style="float: left; width: 20%;">
									<a href="javascript:;"
										onclick="tanchu('IntegralGiftAction_showigList.action?integralgift.userId=${integral.userId}&flag=duihuan&status=person','个人积分兑换情况')">更多</a>
								</div>
							</div>
							<table class="table">
								<tr style="background-color: #ece9d8">
									<th>
										序号
									</th>
									<th>
										姓名
									</th>
									<th>
										礼品名称
									</th>
									<th>
										消耗红心
										<font color="red">♥</font>
									</th>
								</tr>
								<s:iterator value="dhuserList" id="pagelist2"
									status="pagestatus2">
									<s:if test="#statussdf.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')"
											style="height: 26.5px;">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')" style="height: 26.5px;">
									</s:else>
									<th>
										${pagestatus2.index+1}
									</th>
									<th>
										${pagelist2.userName}
									</th>
									<th>
										${pagelist2.giftNmae}
									</th>
									<th>
										${pagelist2.xaIntegral}
									</th>
								</s:iterator>
							</table>
						</div>
						<%--						<div class="div_c2">--%>
						<%--							<div>--%>
						<%--								<div style="float: left; width: 20%;">--%>
						<%--									&nbsp;--%>
						<%--								</div>--%>
						<%--								<div style="float: left; width: 60%;">--%>
						<%--									<STRONG>所有人红心<font color="red">♡</font>兑换情况</STRONG>--%>
						<%--								</div>--%>
						<%--								<div align="right" style="float: left; width: 20%;">--%>
						<%--									<a href="javascript:;" onclick="tanchu('IntegralGiftAction_initaddintegralgift1.action','兑换礼品')">我要兑换</a>--%>
						<%--								</div>--%>
						<%--							</div>--%>
						<%--							<table class="table">--%>
						<%--								<tr style="background-color: #ece9d8">--%>
						<%--									<th>--%>
						<%--										序号--%>
						<%--									</th>--%>
						<%--									<th>--%>
						<%--										姓名--%>
						<%--									</th>--%>
						<%--									<th>--%>
						<%--										礼品名称--%>
						<%--									</th>--%>
						<%--									<th>--%>
						<%--										消耗红心<font color="red">♡</font>--%>
						<%--									</th>--%>
						<%--								</tr>--%>
						<%--								<s:iterator value="dhAllList" id="pagelist3"--%>
						<%--									status="pagestatus3">--%>
						<%--									<s:if test="#statussdf.index%2==1">--%>
						<%--										<tr align="center" bgcolor="#e6f3fb"--%>
						<%--											onmouseover="chageBgcolor(this)"--%>
						<%--											onmouseout="outBgcolor(this,'#e6f3fb')"--%>
						<%--											style="height: 26.5px;">--%>
						<%--									</s:if>--%>
						<%--									<s:else>--%>
						<%--										<tr align="center" onmouseover="chageBgcolor(this)"--%>
						<%--											onmouseout="outBgcolor(this,'')" style="height: 26.5px;">--%>
						<%--									</s:else>--%>
						<%--									<th>--%>
						<%--										${pagestatus3.index+1}--%>
						<%--									</th>--%>
						<%--									<th>--%>
						<%--										${pagelist3.userName}--%>
						<%--									</th>--%>
						<%--									<th>--%>
						<%--										${pagelist3.giftNmae}--%>
						<%--									</th>--%>
						<%--									<th>--%>
						<%--										${pagelist3.xaIntegral}--%>
						<%--									</th>--%>
						<%--								</s:iterator>--%>
						<%--							</table>--%>
						<%--						</div>--%>
					</div>
					<div class="div_c">
						<div class="div_c1">
							<div>
								<div style="float: left; width: 20%;">
									&nbsp;
								</div>
								<div style="float: left; width: 60%;">
									<STRONG>个人红心<font color="red">♥</font>中奖情况</STRONG>
								</div>
								<div align="right" style="float: left; width: 20%;">
									<a href="javascript:;"
										onclick="tanchu('IntegralGiftAction_showigList.action?integralgift.userId=${integral.userId}&flag=choujiang&status=person','个人积分抽奖情况')">更多</a>
								</div>
							</div>
							<table class="table">
								<tr style="background-color: #ece9d8">
									<th>
										序号
									</th>
									<th>
										姓名
									</th>
									<th>
										礼品名称
									</th>
									<th>
										消耗红心
										<font color="red">♥</font>
									</th>
								</tr>
								<s:iterator value="zjuserList" id="pagelist4"
									status="pagestatus4">
									<s:if test="#statussdf.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')"
											onmouseout="hidimg('div',${pagelist4.id})">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')"
											onmouseout="hidimg('div',${pagelist4.id})">
									</s:else>
									<th>
										${pagestatus4.index+1}
									</th>
									<th>
										${pagelist4.userName}
									</th>
									<th>
										${pagelist4.giftNmae}
									</th>
									<th>
										${pagelist4.xaIntegral}
									</th>
								</s:iterator>
							</table>
						</div>
						<div class="div_c2">
							<div>
								<div style="float: left; width: 20%;">
									&nbsp;
								</div>
								<div style="float: left; width: 60%;">
									<STRONG>所有人红心<font color="red">♥</font>中奖情况</STRONG>
								</div>
								<div align="right" style="float: left; width: 20%;">
									<a href="javascript:;"
										onclick="window.open('IntegralGiftAction_initchoujiang.action')">我要抽奖</a>
								</div>
							</div>
							<table class="table">
								<tr style="background-color: #ece9d8">
									<th>
										序号
									</th>
									<th>
										姓名
									</th>
									<th>
										礼品名称
									</th>
									<th>
										消耗红心
										<font color="red">♥</font>
									</th>
								</tr>
								<s:iterator value="zjAllList" id="pagelist5"
									status="pagestatus5">
									<s:if test="#statussdf.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')" style="height: 28px;">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')" style="height: 28px;">
									</s:else>
									<th>
										${pagestatus5.index+1}
									</th>
									<th>
										${pagelist5.userName}
									</th>
									<th>
										${pagelist5.giftNmae}
									</th>
									<th>
										${pagelist5.xaIntegral}
									</th>
								</s:iterator>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function tanchu(src, title) {
	$("#xiugaiIframe").attr("src", src);
	chageDiv('block', title);
}
function showimg(name, num) {
	$("#" + name + "_" + num).show();
}
function hidimg(name, num) {
	$("#" + name + "_" + num).hide();
}


$(function(){
	$.ajax( {
		type : "POST",
		url : "IntegralGiftAction_iscjTime.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if (data === "error") {
				alert("啊哦，出错了")
			}else if(data){
				$("#cj_button").removeAttr("disabled");
			}else{
				$("#bm_button").removeAttr("onclick");
				$("#bm_button").attr("disabled","disabled");
				$("#cj_button").removeAttr("onclick");
			}
		}
	})
})
</script>
	</body>
</html>
