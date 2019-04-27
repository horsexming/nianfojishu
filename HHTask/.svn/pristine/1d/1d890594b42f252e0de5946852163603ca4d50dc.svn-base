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
							<span id="title">您正在对礼品添加进行操作</span>
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
				<form action="IntegralGiftAction_showgiftList.action" method="post">
					<strong>礼品名称:</strong>
					<input type="text" value="" name="gift.name"/>
					<input type="submit" value="查询" class="input"/>
					<input type="button" value="添加" class="input" onclick="tanchu()"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>图片</th>
						<th>礼品名称</th>
						<th>礼品数量</th>
						<th>单件所需积分</th>
						<th>是否参与抽奖</th>
						<th>礼品价值</th>
						<th>添加人</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				<s:iterator id="pagelist" value="giftList" status="pagestatus">
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
						<s:property value="#pagestatus.index+1" />
					</td>
					<td>
						<img alt="${pagelist.name}" src="<%=basePath%>upload/file/Gift/${pagelist.picture}" 
						 	onmousemove="showimg(${pagelist.id})"  style="width: 25px;height: 25px">
						 <div id="div_${pagelist.id}" style="display: none;">
						 	<img alt="${pagelist.name}" 
						 	src="<%=basePath%>upload/file/Gift/${pagelist.picture}" onmouseout="hidimg(${pagelist.id})">
						 </div>
					</td>
					<td >
						${pagelist.name}
					</td>
					<td>${pagelist.num}</td>
					<td>${pagelist.xy_Integral}</td>
					<td>${pagelist.isLuckdraw}</td>
					<td>${pagelist.gift_price}</td>
					<td>${pagelist.addusers}</td>
					<td>${pagelist.addTime}</td>
					<td>
						<a href="javascritp:;" onclick="tanchu(${pagelist.id})">兑换礼品</a>/
						<s:if test="#pagelist.isLuckdraw == 'no'">
							<a href="IntegralGiftAction_Luckdraw.action?id=${pagelist.id}&cpage=${cpage}" >参与抽奖</a>/
						</s:if>
						<a href="javascritp:;" onclick="tanchu(${pagelist.id},'xiugai')">修改</a>/
						<a href="IntegralGiftAction_delgift.action?gift.id=${pagelist.id}&cpage=${cpage}" onclick="confirm('确定要删除吗?')">删除</a>
					</td>
					</tr>
   				 </s:iterator>
   				 <tr>
				<td colspan="11" align="right">
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
<script type="text/javascript">
function tanchu(num,status){
	if(num!=null && num!=''){
		if(status == 'xiugai'){
			document.getElementById("xiugaiIframe").src="IntegralGiftAction_toupdategift.action?id="+num;
		}else{
			document.getElementById("xiugaiIframe").src="IntegralGiftAction_initaddintegralgift.action?id="+num;
		}
		
	}else{
		document.getElementById("xiugaiIframe").src="<%=basePath%>/System/xinxi/integralgift/gift_add.jsp";
	}
		chageDiv('block')
}

function showimg(num){
	$("#div_"+num).show();
}
function hidimg(num){
	$("#div_"+num).hide();
}
</script>
	</body>
</html>
