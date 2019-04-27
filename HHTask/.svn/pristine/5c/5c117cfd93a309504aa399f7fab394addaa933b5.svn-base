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
	<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
				<table style="width: 100%">
					<tr>
						<td>
							
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				</center>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		
		
	
			<div align="center">
			<form action="markIdAction!listtianxiejiepai.action" method="post"
					theme="simple">
				<table class="table" style="width: 100%;border: 1ex;" >
					<tr  bgcolor="#c0dcf2">
						<th align="cleft" >
							ID
						</th>
							<th align="cleft" >
							工序号
						</th>
						<th align="cleft" >
							工序名称
						</th>
						<th align="cleft" >
							类型
						</th>
						<th align="cleft" >
							是否完成
						</th>
						<th align="cleft" >
							操作<input type="hidden" value="${gysMarkIdjiepai.id}" id="gysMarkIdjiepai.id">
						</th>
					
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr  align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<td>
								${pageIndex.index+1}
							</td>
								<td>
							${zhUser1.processNO}
							</td>
							<td>
								${zhUser1.processName}
							</td>
							<td>
								${zhUser1.productStyle}
							</td>
							<td>
									<s:if test='#zhUser1.capacity==null'>
									<font style="color: red;">未完成</font>
									</s:if>
									<s:else>
									完成
									</s:else>
							</td>
							<td>
									<a onclick="toUpdatezhaobiao(${zhUser1.id})">填写节拍</a>
							</td>
						
						</tr>
							</s:iterator>
						</table>
						
						</form>
						
				
		</div>
		
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			
	function toUpdatezhaobiao(id){
		//var eid=document.getElementById("gysMarkIdjiepai.id").value();
		var eid=${gysMarkIdjiepai.id};
			var url=encodeURI(encodeURI("markIdAction!tojiepai.action?pIdZijian.id="+id+"&gysMarkIdjiepai.id="+eid));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
		function chakan(id){
			var url=encodeURI(encodeURI("zhaobiaoAction!chakan.action?gysjiepai.id="+id));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	</SCRIPT>
	</body>
</html>
