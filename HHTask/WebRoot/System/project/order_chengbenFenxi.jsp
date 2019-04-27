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
.showdiv {
	left: 50px;
	border: solid 1px #000000;
	width: 100px;
	height: 100px;
	float: left;
	margin-left: 100px;
	margin-top: 30px;
	text-align: center;
	border-radius: 50%;
	cursor: pointer;
}
</style>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 100%; display: none;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">成本分析</span>
						</td>
						<td align="right">
							<s:if test="viewStatus=='zjl'">
								<a href="javascript:history.go(-1);">返回</a>
							</s:if>
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 4000px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table" id="showTable">
					<tr bgcolor="#c0dcf2" height="50px">
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
							类型
						</th>
						<th>
							批次
						</th>
						<th>
							数量
						</th>
						<th>
							生产状态
						</th>
						<s:if test="flag=='rg'">
							<th>
								人工成本
							</th>
						</s:if>
						<s:if test="flag=='sb'">
							<th>
								设备折旧费
							</th>
						</s:if>
						<s:if test="flag=='ny'">
							<th>
								能源消耗费
							</th>
						</s:if>
						<s:if test="flag=='cl'">
							<th>
								材料费
							</th>
						</s:if>
						<s:if test="flag=='wg'">
							<th>
								外购费
							</th>
						</s:if>
						<s:if test="flag=='fl'">
							<th>
								辅料费
							</th>
						</s:if>
						<s:if test="flag=='gl'">
							<th>
								管理费
							</th>
						</s:if>
						<s:if test="flag=='clv'">
							<th>
								差旅费
							</th>
						</s:if>
						<s:if test="flag=='cf'">
							<th>
								餐费
							</th>
						</s:if>
						<%--<th>
							总费用
						</th>
						--%>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="pageProcard" value="list" status="pageIndex">
						<tr data="no" id="prcard${pageIndex.index}" align="center"
							onmouseover="chageBgcolor(this)" style="height: 30px;"
							onmouseout="outBgcolor(this,'')"
							onclick="clickBgcolor(this);showPartS(${pageIndex.index},'${pageProcard.rootId}')">
							<td>
								<s:property value="#pageIndex.index+1" />
							</td>
							<td>
								${pageProcard.markId}
							</td>
							<td>
								${pageProcard.proName}
							</td>
							<td>
								${pageProcard.procardStyle}
							</td>
							<td>
								${pageProcard.selfCard}
							</td>
							<td>
								${pageProcard.filnalCount}
							</td>
							<td>
								${pageProcard.status}
							</td>
							<s:if test="flag=='rg'">
								<td>
									${pageProcard.rengongfei}
								</td>
							</s:if>
							<s:if test="flag=='sb'">
								<td>
									${pageProcard.shebeiZjFei}
								</td>
							</s:if>
							<s:if test="flag=='ny'">
								<td>
									${pageProcard.nyxhFei}
								</td>
							</s:if>
							<s:if test="flag=='cl'">
								<td>
									${pageProcard.clFei}
								</td>
							</s:if>
							<s:if test="flag=='wg'">
								<td>
									${pageProcard.wgFei}
								</td>
							</s:if>
							<s:if test="flag=='fl'">
								<td>
									${pageProcard.flFei}
								</td>
							</s:if>
							<s:if test="flag=='gl'">
								<td>
									${pageProcard.glFei}
								</td>
							</s:if>
							<s:if test="flag=='clv'">
								<td>
									${pageProcard.clvFei}
								</td>
							</s:if>
							<s:if test="flag=='cf'">
								<td >
									${pageProcard.canFei}
								</td>
							</s:if>
							<%--<td>
								${pageProcard.allMoney}
							</td>
							--%>
							<td>
								<a
									href="ProcardAction!findProcardView.action?pageStatus=history&viewStatus=zjl&id=${pageProcard.id}">生产进度</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//展示历史模版
var num = 0;
var size=<s:property value="list.size()"/>;
function showPartS(index, rootId) {
	for ( var i = 0; i < num; i++) {
		$("#showWs").remove();
	}
	if($("#prcard"+index).attr("data")=='yes'){
		$("#prcard"+index).attr("data",'no');
		return false;
	}else{
		for(var j=0;j<size;j++){
			if(j==index){
				$("#prcard"+index).attr("data",'yes');
			}else{
		     $("#prcard"+j).attr("data",'no');
			}
		}
	}
	
	$
			.ajax( {
				type : "POST",
				url : "orderManager_orderPartsChengBen.action",
				data : {
				 id : rootId
				},
				dataType : "json",
				success : function(msg) {
					var html = "";
					$(msg)
							.each(
									function(i, n) {
										num++;
										var cb="";
										var f="${flag}";
										if(f=="rg"){
											cb=n.rengongfei
										}else if(f=="sb"){
											cb=n.shebeiZjFei
										}else if(f=="ny"){
											cb=n.nyxhFei
										}else if(f=="cl"){
											cb=n.clFei
										}else if(f=="wg"){
											cb=n.wgFei
										}else if(f=="fl"){
											cb=n.flFei
										}else if(f=="gl"){
											cb=n.glFei
										}else if(f=="clv"){
											cb=n.clvFei
										}else if(f=="cf"){
											cb=n.canFei
										}
										if(cb==null){
											cb=0;
										}
											
										html += "<tr style='background: #FFFFCC' id='showWs'><th>"
												+ (i + 1)
												+ "</th><th>"
												+ n.markId
												+ "</th><th>"
												+ n.proName
												+ "</th><th>"
												+ n.procardStyle
												+ "</th><th>"
												+ n.selfCard
												+ "</th><th>"
												+ n.filnalCount
												+ "</th><th>"
												+ n.status
												+ "</th><th align='right'>"
												+ cb
												+ "</th><th colspan='2'>" +
												"<a href='javascript:;' onclick = 'tanchu("+n.priceId+")'>查看</a></th></tr>";
									});
					$("#showTable>tbody>tr").eq(index+1).after(html);
				}
			});
}
function tanchu (priceId){
	var  src = "PriceAction!getProcardMx.action?id="+priceId;
	document.getElementById("xiugaiIframe").src = src;
	chageDiv('block');
}
		</script>
	</body>
</html>
