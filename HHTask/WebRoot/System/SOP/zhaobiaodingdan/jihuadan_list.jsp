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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>
			
			<div align="center">
				<h3>
					计划单
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
							编号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							牌号
						</td>
						<td align="center">
							物料名称
						</td>
						<td align="center">
							数量
						</td>
						<td align="center">
							单位
						</td>
						<td align="center">
							规格要求
						</td>
						<td align="center">
							计划年月
						</td>
						<td align="center">
							计划编号
						</td>
						<td align="center">
							修改
						</td>
					</tr>
					<s:iterator value="list" id="zhaobiao1" status="pageIndex">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${pageIndex.index+1}
						</th>
						<th>
							${zhaobiao1.markId}
						</th>
						<th>
							${zhaobiao1.paihao}
						</th>
						<th>
							${zhaobiao1.name}
						</th>
						<th>
							${zhaobiao1.shuliang}
						</th>

						<th>
							${zhaobiao1.danwei}
						</th>
						<th>
							${zhaobiao1.guige}
						</th>
						<th>
							${zhaobiao1.genertorDate}
						</th>
						<th>
							${zhaobiao1.biaohao}
						</th>
						<th>
								<a onclick="toUpdatezhaobiao(${zhaobiao1.id})">修改</a>
								
						</th>
						
					</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage != null">
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:if>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			function update(id){
				window.location="internalOrder_initProduct.action?id="+id+"&customeId="+${id};
			}
		</SCRIPT>
	</body>
</html>
