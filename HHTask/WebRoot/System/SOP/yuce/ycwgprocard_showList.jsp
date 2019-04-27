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
<%-- 遮慕层  --%>
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
<%----%>
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	left:10px;
	width: auto;
	background: #CCC;
	color: green;
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
				<form action="orderManager_findycwgProcardList.action" method="post">
					<table class="table">
						<tr>
							<th>
								件号
							</th>
							<th>
								<input type="text" value="${ycwgProcard.markId }" name="ycwgProcard.markId"/>
							</th>
							<th>
								名称
							</th>
							<th>
								<input type="text" value="${ycwgProcard.proName }" name="ycwgProcard.proName"/>
							</th>
						</tr>
					</table>
					<input type="hidden" value="${status }" name="status"/>
					<input type="hidden" value="${ycwgProcard.ycProductId}" name="ycwgProcard.ycProductId"/>
					<input type="submit" value="查询" class="input" />
					<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData" class="input">
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							件号
						</th>
						<th>
							名称
						</th>
						<th>
							规格
						</th>
						<th>
							版本
						</th>
						<th>
							单位
						</th>
						<th>
							供料属性
						</th>
						<th>
							图号
						</th>
						<th>
							物料类别
						</th>
						<th>
							需求数量
						</th>
						<th>
							净需求数量
						</th>
						<th>
							对应总成数
						</th>
						<th>
							添加时间
						</th>
						<th>
							分配周
						</th>
						<th>
							备注
						</th>
						<th>
							添加人
						</th>
						<th>
							LT等级
						</th>
						<s:iterator id="pageList" value="ycwgProcardList"
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
								<td>
									${pageList.markId}
								</td>
								<td style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageList.proName}</font>
										<ul class="qs_ul">
											<li>
											${pageList.proName}
											</li>
									</ul>
								</td>
								<td style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageList.specification}</font>
										<ul class="qs_ul">
											<li>
											${pageList.specification}
											</li>
									</ul>
								</td>
								<td>
									${pageList.banben}
								</td>
								<td>
									${pageList.unit}
								</td>
								<td>
									${pageList.kgliao}
								</td>
								<td style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageList.tuhao}</font>
										<ul class="qs_ul">
											<li>
											${pageList.tuhao}
											</li>
									</ul>
								</td>
								<td>
									${pageList.wgType}
								</td>
								<td>
									${pageList.xqNum}
								</td>
								<td>
									${pageList.sjxqNum}
								</td>
								<td>
									${pageList.zcNum}
								</td>
								<td>
									${pageList.addTime}
								</td>
								<td style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageList.fpweek}</font>
										<ul class="qs_ul">
											<li>
											${pageList.fpweek}
											</li>
									</ul>
								</td>
								<td style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageList.more}</font>
										<ul class="qs_ul">
											<li>
											${pageList.more}
											</li>
									</ul>
								</td>
								<td>
									${pageList.addUsersName}
								</td>
								<td>
									${pageList.ltdengji}
								</td>
						</s:iterator>
					</tr>
					<tr>
								<td colspan="30" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

								</td>
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
function tanchu(num,num0){
	document.getElementById("xiugaiIframe").src = "<%=basePath%>/System/SOP/yuce/ycweek_add.jsp?id="+ num+"&num="+num0;
	chageDiv('block')
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
		$.ajax( {
		type : "POST",
		url : "orderManager_addYcWaiGouProcrd.action",
		data : {
			id : num	
		},
		dataType : "json",
		success : function(data) {
			if (data =='true') {
				$("#dialog1").hide();
				$("#fullbg1").hide();
			}else{
				$("#msg_div").html(data);
			}
		}
	})
}
function exportExcel(obj){//zhaobiaoAction!listAll.action
		obj.action = "orderManager_exportExcelYcWg.action";
	 	obj.submit();
	  	obj.action = "orderManager_findycwgProcardList.action";
	 	}
</SCRIPT>
	</body>
</html>
