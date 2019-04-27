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
							<span id="title">您正在查看活动详情</span>
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
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>名称</th>
						<th>期号</th>
						<th>随机码</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>状态</th>
						<th>详情</th>
					</tr>
				<s:iterator id="pagelist" value="igSetList" status="pagestatus">
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
						${pagelist.name}
					</td>
					<td >
						${pagelist.qihao}
					</td>
					<td>${pagelist.randomnum}</td>
					<td>${pagelist.beginTime}</td>
					<td>${pagelist.endTime}</td>
					<td>${pagelist.status}</td>
					<td>
						<a href="javascript:;" onclick="tanchu('${pagelist.randomnum}',${pagelist.id})">详情</a>
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
function tanchu(str,id){
		document.getElementById("xiugaiIframe").src="IntegralGiftAction_findidgiftList.action?str="+str+"&id="+id;
		chageDiv('block')
}


</script>
	</body>
</html>
