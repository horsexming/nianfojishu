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
		
		<%@include file="/util/sonTop.jsp"%>
		
		
		
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
	
<center>
					<table class="table">
					<tr ><td colspan="8">采购明细</td></tr>
					<tr bgcolor="#c0dcf2">
						<td>
							编号:
						</td>
						<td>
							物料名称:
						</td>
						<td>
							采购数量:
						</td>
						
						<td>
							单位:
						</td>
						<td>
							规格要求:
						</td>
						
						<td>
							到货期限:
						</td>
					
					</tr>

					<s:iterator value="list" id="pageList" status="pageStatus">
						<tr >
							<td>
							   ${pageStatus.index+1}
							   
							</td>
							<td>${pageList.markId }
							</td>
							<td>${pageList.shiji}
							</td>
						<td>${pageList.danwei}
							</td>
						<td>${pageList.guige}
							</td>
							<td>${pageList.genertorDate}
							</td>
							<td>
							<!-- <a onclick="DingdanAction!torukudan.action?dingdanzhuanhua.id=${pageList.id}">申请入库单</a>
								<td>
							操作
						</td>
							 <s:if test='#pageList.status==null'>
							<a onclick="torukudan(${pageList.id})">申请入库单</a>
							</s:if>
							</td> -->
						
						</tr>
					</s:iterator>
					<tr>
					</table>
					</center>
			</div>
		<%@include file="/util/foot.jsp"%>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
		function torukudan(id){
	var url=encodeURI(encodeURI("DingdanAction!torukudanchaxun.action?zhtoubiao.id="+id));
	$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	
	</script>


</html>
