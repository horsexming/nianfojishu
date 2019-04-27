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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<script
			src="${pageContext.request.contextPath}/javascript/jquery-table2excel-master/dist/jquery.table2excel.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<input type="button" value="导出" class="input" onclick="exportExcel();todisabledone(this)" data="downData">
				<input type="button" value="返回" class="input" onclick="javascript :history.back(-1);">
			</div>
			<div align="center">
				<div> 总成件号：${procard.markId}&nbsp;&nbsp;&nbsp;业务件号：${procard.ywMarkId}&nbsp;&nbsp;&nbsp;订单号：${procard.orderNumber}&nbsp;&nbsp;&nbsp;批产数量${procard.filnalCount}
				</div>
				<div>
					<form action="ProcardAction!procardOutww.action" method="post" onsubmit="return lq();">
					<input type="hidden" value="${procard.id}" name="id">
					<table class="table">
					 <tr>
					 	<td align="center"> 序号</td>
					 	<td align="center">件号</td>
					 	<td align="center">版本</td>
					 	<td align="center">批次</td>
					 	<td align="center">名称</td>
					 	<td align="center">外委工序号</td>
					 	<td align="center">外委工序名</td>
					 	<td align="center">下工序</td>
					 	<td align="center">可领数量</td>
					 	<td align="center">已领数量</td>
					 	<td align="center">批次数量</td>
				 		<td align="center">外委单号</td>
				 		<td align="center">外委状态</td>
				 		<td align="center">外委数量</td>
				 		<td align="center" >库存批次</td>
				 		<td align="center" >对应数量</td>
					 </tr>
					 <s:iterator value="list" id="pvo" status="pvoindex">
					 	<s:if test="#pvo.wwylCount==#pvo.filnalCount">
							<tr align="center" bgcolor="green" >
					 	</s:if>
					 	<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
					 	</s:else>
					 	<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
							<td align="right" rowspan="${pvo.wwshowList.size()}">
						</s:if>
						<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
							<td align="right" rowspan="${pvo.goodsList.size()}">
						</s:elseif>
						<s:else>
							<td align="right">
						</s:else>
						<s:if test="#pvoindex.index%2==1">
							<font>
						</s:if>
						<s:else>
							<font color="#c0dcf2">
						</s:else>
							<b><s:property value="#pvoindex.index+1" />
							</b>
						</font>
					</td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else>
						<s:property value="#pvo.markId"/>
					</td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else>
						<s:property value="#pvo.banBenNumber"/>
					</td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else><s:property value="#pvo.selfCard"/></td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else><s:property value="#pvo.proName"/></td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else><s:property value="#pvo.processnos"/></td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else><s:property value="#pvo.processnames"/></td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else><s:property value="#pvo.nextProcessName"/></td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else><s:property value="#pvo.wwklCount"/></td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else><s:property value="#pvo.wwylCount"/></td>
					<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
						<td align="right" rowspan="${pvo.wwshowList.size()}">
					</s:if>
					<s:elseif test="#pvo.goodsList.size()>#pvo.wwshowList.size()">
						<td align="right" rowspan="${pvo.goodsList.size()}">
					</s:elseif>
					<s:else>
						<td align="right">
					</s:else><s:property value="#pvo.filnalCount"/></td>
					<s:if test="#pvo.goodsList!=null&&#pvo.goodsList.size()>0">
						<s:if test="#pvo.wwshowList!=null && #pvo.wwshowList.size()>=0">
							<s:if test="#pvo.wwshowList.size()>=#pvo.goodsList.size()">
								<s:iterator value="#pvo.wwshowList" id="wwshow" status="wwshowListStatus">
									<td align="right"><s:property value="#wwshow.wwnumber"/></td>
									<td align="right" ><s:property value="#wwshow.wwstatus"/></td>
									<td align="right"><s:property value="#wwshow.wwcount"/></td>
									<s:iterator value="#pvo.goodsList" id="wwgoods" status="wwGoodsStatus">
										<s:if test="#wwshowListStatus.index==#wwGoodsStatus.index">
											<td align="right" rowspan="${pvo.wwshowList.size()/pvo.goodsList.size()}">
												<s:property value="#wwgoods.goodsLotId"/>
												<s:if test="#wwgoods.dtcFlag!=null&&#wwgoods.dtcFlag=='外协调委外'">
													<br/>
													<font color="red">外协调委外</font>
												</s:if>
											</td>
											<td align="right" rowspan="${pvo.wwshowList.size()/pvo.goodsList.size()}">
												<s:property value="#wwgoods.goodsCurQuantitySting"/>
											</td>
											</tr>
										</s:if>
									</s:iterator>
								</s:iterator>
							</s:if>
							<s:else>
								<s:iterator value="#pvo.wwshowList" id="wwshow" status="wwshowListStatus">
									<td align="right"  rowspan="${pvo.goodsList.size()/pvo.wwshowList.size()}"><s:property value="#wwshow.wwnumber"/></td>
									<td align="right" rowspan="${pvo.goodsList.size()/pvo.wwshowList.size()}"><s:property value="#wwshow.wwstatus"/></td>
									<td align="right"rowspan="${pvo.goodsList.size()/pvo.wwshowList.size()}"><s:property value="#wwshow.wwcount"/></td>
									<s:iterator value="#pvo.goodsList" id="wwgoods" status="wwGoodsStatus">
<%--										<s:if test="#wwshowListStatus.index==#wwGoodsStatus.index">--%>
											<td align="right">
												<s:property value="#wwgoods.goodsLotId"/>
												<s:if test="#wwgoods.dtcFlag!=null&&#wwgoods.dtcFlag=='外协调委外'">
													<br/>
													<font color="red">外协调委外</font>
												</s:if>
											</td>
											<td align="right">
												<s:property value="#wwgoods.goodsCurQuantitySting"/>
											</td>
											</tr>
<%--										</s:if>--%>
									</s:iterator>
							
								
								</s:iterator>
							</s:else>
							
						</s:if>
						<s:else>
							<s:iterator value="#pvo.goodsList" id="wwgoods" status="wwGoodsStatus">
								<td></td>
								<td></td>
								<td></td>
								<td  align="right">
									<s:property value="#wwgoods.goodsLotId"/>
									<s:if test="#wwgoods.dtcFlag!=null&&#wwgoods.dtcFlag=='外协调委外'">
										<br/>
										<font color="red">外协调委外</font>
									</s:if>
								</td>
								<td align="right" >
									<s:property value="#wwgoods.goodsCurQuantitySting"/>
								</td>
								</tr>
							</s:iterator>
						</s:else>
					</s:if>
					<s:elseif test="#pvo.wwshowList!=null && #pvo.wwshowList.size()>0">
						<s:iterator value="#pvo.wwshowList" id="wwshow">
							<td align="right"><s:property value="#wwshow.wwnumber"/></td>
							<td align="right" ><s:property value="#wwshow.wwstatus"/></td>
							<td align="right"><s:property value="#wwshow.wwcount"/></td>
							<td></td>
							<td></td>
							</tr>
						</s:iterator>
					</s:elseif>
					<s:else>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						</tr>
					</s:else>
					
				 </s:iterator>
				</table>
				</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">

function exportExcel(){
	$(".table").table2excel({
		exclude: ".noExl",
	    name: "外委总览",
	    filename: "${procard.markId}_外委总览.xls" //do not include extension
	});
}


</SCRIPT>
	</body>
</html>
