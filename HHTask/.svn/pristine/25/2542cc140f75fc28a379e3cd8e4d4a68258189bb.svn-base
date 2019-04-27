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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">图纸查看</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<input type="radio" name="showtable" value="showall"
					onchange="showchange(this.value)" checked="checked" />
				显示全部
				<input type="radio" name="showtable" value="showselected"
					onchange="showchange(this.value)" />
				显示未核对
			</div>
			<div align="center">
				<table class="table">
					<tr>
						<th>
							件号
						</th>
						<th>
							业务
						</th>
						<th>
							名称
						</th>
						<th>
							批次
						</th>
						<th>
							申请人
						</th>
						<th>
							申请时间
						</th>
					</tr>
					<tr>
						<td align="center">
							${pwwApply.markId}
						</td>
						<td align="center">
							${pwwApply.ywMarkId}
						</td>
						<td align="center">
							${pwwApply.proName}
						</td>
						<td align="center">
							${pwwApply.selfCard}
						</td>
						<td align="center">
							${pwwApply.userName}
						</td>
						<td align="center">
							${pwwApply.addTime}
						</td>
					</tr>
					<tr>
						<td align="center" colspan="6">
							<table class="table" style="table-layout: fixed;">
								<tr bgcolor="#c0dcf2" height="50px">
									<th>
										序号
									</th>
									<th>
										供应商
									</th>
									<th width="120px">
										件号
									</th>
									<th>
										零件名称
									</th>
									<th>
										版本
									</th>
									<th>
										版次
									</th>
									<th width="120px">
										批次
									</th>
									<th>
										工序号
									</th>
									<th>
										工序名称
									</th>
									<th>
										数量
									</th>
									<th>
										添加人
									</th>
									<th>
										添加时间
									</th>
									<th>
										外委类型
									</th>
									<th>
										状态
									</th>
									<th>
										图纸更新
									</th>
									<th>
										变更件号
									</th>
									<th>
										不含税单价
									</th>
									<th>
										含税单价
									</th>
									<th>
										关联件号
									</th>
									<th>
										操作
									</th>
								</tr>
								<tr bgcolor="red">
								 <td colspan="19">未下单</td>
								</tr>
								<s:iterator value="pwwApplyDetailList" id="pagedetail"
									status="step1">
								<s:if test="#pagedetail.processStatus!='订单外委采购'">
									<s:if test="#step1.index%2==1">
										<s:if test="#pagedetail.priceId!=null">
											<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
												onmouseover="chageBgcolor(this)"
												onmouseout="outBgcolor(this,'#e6f3fb')" class="showall">
										</s:if>
										<s:else>
											<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
												onmouseover="chageBgcolor(this)"
												onmouseout="outBgcolor(this,'#e6f3fb')"
												class="unshowselected">
										</s:else>
									</s:if>
									<s:else>
										<s:if test="#pagedetail.priceId!=null">
											<tr align="center" onmouseover="chageBgcolor(this)"
												style="height: 50px;" onmouseout="outBgcolor(this,'')"
												class="showall">
										</s:if>
										<s:else>
											<tr align="center" onmouseover="chageBgcolor(this)"
												style="height: 50px;" onmouseout="outBgcolor(this,'')"
												class="unshowselected">
										</s:else>
									</s:else>
									<td align="center">
										<s:property value="#step1.index+1" />
									</td>
									<td align="center">
										<s:if test="#pagedetail.gysId==null">
											<font color="red">无供应商</font>
										</s:if>
										<s:else>
											<font color="green">${pagedetail.gysName}</font>
										</s:else>
									</td>
									<td width="120px">
										${pagedetail.markId}
									</td>
									<td align="center">
										${pagedetail.proName}
									</td>
									<td align="center">
										${pagedetail.banbenNumber}
									</td>
									<td align="center">
										${pagedetail.banci}
									</td>
									<td width="120px">
										${pagedetail.selfCard}
									</td>
									<td align="left">
										${pagedetail.processNOs}
									</td>
									<td align="left">
										${pagedetail.processNames}
									</td>
									<td align="center">
										${pagedetail.applyCount}
									</td>
									<td align="center">
										${pagedetail.userName}
									</td>
									<td align="center">
										${pagedetail.addTime}
									</td>
									<td align="center">
										${pagedetail.wwType}
									</td>

									<s:if test="#pagedetail.gysId!=null">
										<td align="center" style="color: #ffffff" bgcolor="green">
											OK
										</td>
									</s:if>
									<s:else>
										<td align="center" style="background-color: red;">
											NO
										</td>
									</s:else>
									<td align="center">
										${pagedetail.tzupdate}
									</td>
									<td align="center">
										${pagedetail.bgMarkIds}
									</td>
									<th>
										<fmt:formatNumber value="${pagedetail.price}" pattern="#.###"></fmt:formatNumber>	
									</th>
									<th>
										<fmt:formatNumber value="${pagedetail.hsprice}" pattern="#.###"></fmt:formatNumber>	
									</th>
									<th title="${pagedetail.guanlianMarkId}" style="table-layout: fixed;text-overflow: ellipsis;-moz-text-overflow: ellipsis;overflow: hidden;white-space: nowrap; border: 1px solid;text-align: left;width: 200px; ">
										${pagedetail.guanlianMarkId}
									</th>
									<td align="center">
										<input onclick="gxcf(${pagedetail.id})" type="button" value="工序拆分">
										<input onclick="toCheckHt(${pagedetail.id})" type="button"
											value="绑定合同">
										<input onclick="backsdWw(${pagedetail.id},<s:property value="#step1.index" />)" type="button" id="backBtn<s:property value="#step1.index" />" value="取消外委">
										<input onclick="toviwetz(${pagedetail.id})" type="button"
											value="图纸查看">
										<input onclick="dachutz(${pagedetail.id})" type="button"
											value="下载图纸">
									</td>
									</tr>
								</s:if>
								</s:iterator>
								 <s:if test="pwwApply.processStatus=='合同待确认'">
						<tr>
							<td colspan="19" align="center">
								<s:if test="wwxd =='是'.toString()">
								<input type="button" style="height: 30px;" value="核对无误,确定"
									onclick="surewwapplyht(${pwwApply.id},this);">
								<div id="showMess" style="color: red; display: none;">
									正在确认委外订单中,请稍候......
								</div>
								<div id="showMess2" style="color: red; display: none;">
									正在生成物料计划中,请稍候......
								</div>
								<div id="showMess3" style="color: red; display: none;">
									正在激活生产任务中,请稍候......
								</div>
								</s:if>
								<s:else>
								<input type="button" style="height: 30px;" value="核对无误,下单"
									onclick="surewwapplyht(${pwwApply.id},this);">
								</s:else>
							</td>
						</tr>
					</s:if>
					<s:elseif test="pwwApply.processStatus=='外委待下单'||pwwApply.processStatus=='订单外委采购'">
						<tr>
							<td colspan="19" align="center">
								<input type="button" style="height: 30px;" value="下单"
									onclick="surewwapplyht(${pwwApply.id},this);">
								<div id="showMess" style="color: red; display: none;">
									正在确认委外订单中,请稍候......
								</div>
								<div id="showMess2" style="color: red; display: none;">
									正在生成物料计划中,请稍候......
								</div>
								<div id="showMess3" style="color: red; display: none;">
									正在激活生产任务中,请稍候......
								</div>
							</td>
						</tr>
					</s:elseif>
								<tr bgcolor="red">
								 <td colspan="19">已下单</td>
								</tr>
								<s:iterator value="pwwApplyDetailList" id="pagedetail"
									status="step1">
									<s:if test="#pagedetail.processStatus=='订单外委采购'">
									<s:if test="#step1.index%2==1">
										<s:if test="#pagedetail.priceId!=null">
											<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
												onmouseover="chageBgcolor(this)"
												onmouseout="outBgcolor(this,'#e6f3fb')" class="showall">
										</s:if>
										<s:else>
											<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
												onmouseover="chageBgcolor(this)"
												onmouseout="outBgcolor(this,'#e6f3fb')"
												class="unshowselected">
										</s:else>
									</s:if>
									<s:else>
										<s:if test="#pagedetail.priceId!=null">
											<tr align="center" onmouseover="chageBgcolor(this)"
												style="height: 50px;" onmouseout="outBgcolor(this,'')"
												class="showall">
										</s:if>
										<s:else>
											<tr align="center" onmouseover="chageBgcolor(this)"
												style="height: 50px;" onmouseout="outBgcolor(this,'')"
												class="unshowselected">
										</s:else>
									</s:else>
									<td align="center">
										<s:property value="#step1.index+1" />
									</td>
									<td align="center">
										<s:if test="#pagedetail.gysId==null">
											<font color="red">无供应商</font>
										</s:if>
										<s:else>
											<font color="green">${pagedetail.gysName}</font>
										</s:else>
									</td>
									<td width="120px">
										${pagedetail.markId}
									</td>
									<td align="center">
										${pagedetail.proName}
									</td>
									<td align="center">
										${pagedetail.banbenNumber}
									</td>
									<td align="center">
										${pagedetail.banci}
									</td>
									<td width="120px">
										${pagedetail.selfCard}
									</td>
									<td align="left">
										${pagedetail.processNOs}
									</td>
									<td align="left">
										${pagedetail.processNames}
									</td>
									<td align="center">
										${pagedetail.applyCount}
									</td>
									<td align="center">
										${pagedetail.userName}
									</td>
									<td align="center">
										${pagedetail.addTime}
									</td>
									<td align="center">
										${pagedetail.wwType}
									</td>

									<s:if test="#pagedetail.gysId!=null">
										<td align="center" style="color: #ffffff" bgcolor="green">
											OK
										</td>
									</s:if>
									<s:else>
										<td align="center" style="background-color: red;">
											NO
										</td>
									</s:else>
									<td align="center">
										${pagedetail.tzupdate}
									</td>
									<th>
										<fmt:formatNumber value="${pagedetail.price}" pattern="#.###"></fmt:formatNumber>	
									</th>
									<th>
										<fmt:formatNumber value="${pagedetail.hsprice}" pattern="#.###"></fmt:formatNumber>	
									</th>
									<th title="${pagedetail.guanlianMarkId}" style="table-layout: fixed;text-overflow: ellipsis;-moz-text-overflow: ellipsis;overflow: hidden;white-space: nowrap; border: 1px solid;text-align: left;width: 200px; ">
										${pagedetail.guanlianMarkId}
									</th>
									<td align="center">
<%--										<input onclick="gxcf(${pagedetail.id})" type="button" value="工序拆分">--%>
<%--										<input onclick="toCheckHt(${pagedetail.id})" type="button"--%>
<%--											value="绑定合同">--%>
<%--										<input onclick="backsdWw(${pagedetail.id},<s:property value="#step1.index" />)" type="button" id="backBtn<s:property value="#step1.index" />" value="取消外委">--%>
<%--										<input onclick="toviwetz(${pagedetail.id})" type="button"--%>
<%--											value="图纸查看">--%>
<%--										<input onclick="dachutz(${pagedetail.id})" type="button"--%>
<%--											value="下载图纸">--%>
									</td>
									</tr>
									</s:if>
								</s:iterator>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<br />
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function toCheckHt(id){
	window.location.href="ProcardAction!toCheckHt.action?id="+id;
}
function surewwapplyht(id,obj){
	window.location.href="ProcardAction!surewwapplyht.action?id="+id;
	$(obj).attr("disabled","disabled");
		$("#backform").hide();
		$("#showMess").show();
	setTimeout(function () {
		$("#showMess2").show();
    }, 1000*60);
	setTimeout(function () {
		$("#showMess3").show();
    }, 1000*90);
}
function showchange(value){
	if(value=="showall"){
		$(".showall").show();
		nowShow="showall";
	}else{
		$(".showall").hide();
	}
}
function backsdWw(id,index){
	if(confirm("您确定要取消此外委？")){
		$("#backBtn"+index).attr("disabled","disabled");
		window.location.href="ProcardAction!backsdWwdetail.action?id="+id;
	}
	
}

function toviwetz(id){
	$("#showProcess").attr("src",
			"ProcardAction!showtzBywwapplyDetail.action?id=" + id);
	chageDiv('block');
}
function dachutz(id){
	window.location.href="ProcardAction!daochutzBywwapplyDetail.action?id="+id;
}
function gxcf(id){
	$("#showProcess").attr("src",
			"ProcardAction!towwgxcf.action?id=" + id);
	chageDiv('block');
}
</SCRIPT>
	</body>
</html>
