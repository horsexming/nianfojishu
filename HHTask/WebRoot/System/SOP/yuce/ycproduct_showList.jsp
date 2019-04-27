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
.dhlabel{
border-top:1px solid #000;
border-bottom: 1px solid #000;
border-left: 1px solid #000;
border-right: 1px solid #000;
margin-left: 5px;
margin-right: 5px;
padding: 3px 5px;
white-space: nowrap;
}
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
			<div id="msg_div" style="color: red">
				
			</div>
			<div align="center">
			<s:if test="status=='All'">
			<label style="background-color: gray;" class="dhlabel" >所有</label>
			</s:if>
			<s:else>
			<label style="background-color: #5cb85c;" onclick="toShowWW('All');" class="dhlabel" ><font color="white">所有</font></label>
			</s:else>
			<s:if test="status=='wfp'">
			<label style="background-color: gray;" class="dhlabel"  >未分配</label>
			</s:if>
			<s:else>
			<label style="background-color: #5cb85c;" onclick="toShowWW('wfp');" class="dhlabel" ><font color="white">未分配</font></label>
			</s:else>
			<s:if test="status=='dsq'">
				<label style="background-color: gray;" class="dhlabel"  >待申请</label>
			</s:if>
			<s:else>
			<label style="background-color: #5cb85c;" onclick="toShowWW('dsq');" class="dhlabel" ><font color="white">待申请</font></label>
			</s:else>
			<s:if test="status=='cgz'">
				<label style="background-color: gray;" class="dhlabel"  >采购中</label>
			</s:if>
			<s:else>
				<label style="background-color: #5cb85c;" onclick="toShowWW('cgz');" class="dhlabel" ><font color="white">采购中</font></label>
			</s:else>
			<s:if test="status=='ywc'">
				<label style="background-color: gray;" class="dhlabel"  >完成</label>
			</s:if>
			<s:else>
				<label style="background-color: #5cb85c;" onclick="toShowWW('ywc');" class="dhlabel" ><font color="white">完成</font></label>
			</s:else>
				<form action="orderManager_findAllYcProduct.action" method="post">
					<table class="table">
						<tr>
							<th>
								订单号(内部)
							</th>
							<th>
								<input type="text" value="${ycProduct.orderNo }" name="ycProduct.orderNo"/>
							</th>
							<th>
								订单号(外部)
							</th>
							<th>
								<input type="text" value="${ycProduct.outOrderNo }" name="ycProduct.outOrderNo"/>
							</th>
						</tr>
						<tr>
							<th>
								件号	
							</th>
							<th>
								<input type="text" value="${ycProduct.markId}" name="ycProduct.markId"/>
							</th>
							<th>
								业务件号
							</th>
							<th>
								<input type="text" value="${ycProduct.ywmarkId}" name="ycProduct.ywmarkId"/>
							</th>
						</tr>
					</table>
					<input type="hidden" value="${status }" name="status"/>
					<input type="submit" value="查询" class="input" />
					<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData" class="input"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							订单号(内部)
						</th>
						<th>
							订单号(外部)
						</th>
						<th>
							件号
						</th>
						<th>
							业务件号
						</th>
						<th>
							下单数量
						</th>
						<th>
							预分配数量
						</th>
						<th>
							已采购数量
						</th>
						<th>
							差异量
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
						<s:iterator id="pageList" value="ycProductList"
								status="statussdf">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>${pageList.orderNo}</td>
								<td>${pageList.outOrderNo}</td>
								<td>${pageList.markId}</td>
								<td>${pageList.ywmarkId}</td>
								<td align="right">${pageList.num}</td>
								<td align="right">${pageList.yfpNum}</td>
								<td align="right">${pageList.ycgNum}</td>
								<td align="right">${pageList.cxNum}</td>
								<td>${pageList.status}</td>
								<td>
									<s:if test='#pageList.status == "未分配"'>
										<a href="javascript:;" onclick="tanchu('${pageList.id}')">预分配</a>	
									</s:if>
									<s:if test='#pageList.status == "待申请" '>
										<a href="javascript:;" onclick="tanchu('${pageList.id}')">预分配</a>	
										<a href="javascript:;" onclick="sqcg('${pageList.id}')" >申请采购</a>
									</s:if>
									<s:if test='#pageList.status == "采购中" || #pageList.status == "完成"'>
										<a href="javascript:;" onclick="showmx('${pageList.id}')">明细</a>
									</s:if>
								</td>
						</s:iterator>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function toShowWW(status){
	window.location.href="orderManager_findAllYcProduct.action?status="+status;
}
function tanchu(num){
	document.getElementById("xiugaiIframe").src = "orderManager_findycWeek.action?id="+num;
	chageDiv('block')
}
function showmx(num){
	document.getElementById("xiugaiIframe").src = "orderManager_findycwgProcardList.action?ycwgProcard.ycProductId="+num;
	chageDiv('block')
}

function issqcg(num){
	var bool = false;
		$.ajax( {
		type : "POST",
		url : "orderManager_issqcg.action",
		data : {
			id : num	
		},
		async:false,
		dataType : "json",
		success : function(data) {
			if(data == 'true'){
				bool =true;
			}else{
				$("#msg_div").html(data);
			}
		}
	})
	return bool;
}

function sqcg(num){
		//弹出遮罩层开始
		$("body").append("<div id='fullbg1'></div>");
		$("body")
				.append(
						"<div id='dialog1' class='loginbox'>"
								+ "<iframe id='xiugaiIframe1' src='<%=basePath%>/System/SOP/yuce/zhemu.jsp' "
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
		//弹出遮罩层结束
		if(issqcg(num)){
		$.ajax( {
		type : "POST",
		url : "orderManager_addYcWaiGouProcrd.action",
		data : {
			id : num	
		},
		dataType : "json",
		success : function(data) {
				$("#dialog1").hide();
				$("#fullbg1").hide();
			if(data.success){
				if(data.message == "true"){
					bool = true
				}else{
					$("#msg_div").css({
						color:'black'
					})
					$("#msg_div").html("<b>已申请成功以下为提醒信息:</b><br>"+data.message);
				}
			}else{
					$("#msg_div").css({
						color:'red'
					})
					$("#msg_div").html("<b>申请失败以下为错误信息:</b><br>"+data.message);
			}
		}
	})
	}else{
			$("#dialog1").hide();
			$("#fullbg1").hide();
	}
}

 function exportExcel(obj){//zhaobiaoAction!listAll.action
		obj.action = "orderManager_exportExcelYc.action";
	 	obj.submit();
	  	obj.action = "orderManager_findAllYcProduct.action?status=${status}";
	 	}
</SCRIPT>
	</body>
</html>
