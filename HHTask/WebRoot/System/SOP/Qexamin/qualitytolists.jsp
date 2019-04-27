<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						style="width: 100%; height: 510px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; background: url('<%=basePath%>images/title.jpg') no-repeat;"
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>
			
			<div align="center">
				
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							客户名称
						</th>
						<th align="center">
							产品名称
						</th>
						<th align="center">
							产品图号
						</th>
						<th align="center">
							检测模板编号
						</th>
						<th align="center">
							审批状态
						</th>						
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="qualityto" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout=outBgcolor(this, '');>
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
							</s:if>
							<s:else>
								<font color="#c0dcf2"></font>
							</s:else>
							<s:property value="#pageStatus.index+1" />

						</td>
						<td>
							${qualityto.kehu}
						</td>
						<td>
							${qualityto.productname}
						</td>
						<td>
							${qualityto.bianhao}
						</td>
						<td>
							${qualityto.jcbh}
						</td>
						<td>
							${qualityto.statu}
						</td>
						
						
						<td  width="15%">
						<s:if test='%{"已完成".equals(#qualityto.statu)}'>
							<input type="button" value="审核" 
							onclick="window.location.href='QualityccAction!updateQualitytoos.action?qualityto.Id=${qualityto.id}' ">
						</s:if>
							<input type="button" value="查看" onclick = "add(${qualityto.id})">
							<input type="button" 	
									onclick="if(window.confirm('确实要删除该记录吗?')){window.location.href=
									'QualityccAction!deleteQualityto.action?qualityto.Id=${qualityto.id}&cpage=${cpage}'}" value="删除 ">
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function add(a){
			
			var url=encodeURI(encodeURI("QualityccAction!lookQualitytat.action?qualityto.Id="+a));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
}
		</script>
	</body>
</html>
