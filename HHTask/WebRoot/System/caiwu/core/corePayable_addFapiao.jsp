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
		<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<div id="submitProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在确认已付金额:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="NoncoreReceAction!querenDetail.action?tag=${tag}"
							method="post" onsubmit="return validate()">
							<input type="hidden" id="wuId" name="nonCoreReceivablesDetail.id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										确认已付金额:
									</th>
									<th align="left">
										<input name="nonCoreReceivablesDetail.realfuJine"
											id="realfuJine" />
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" value="提交"
											style="width: 65px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					<font color="red">${nonCoreReceivables.chengzufang}</font>主营业务应付表
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							类型
						</td>
						<td align="center">
							供应商名称
						</td>
						<td align="center">
							摘要
						</td>
						<td align="center">
							含税单价
						</td>
						<td align="center">
							数量
						</td>
						<td align="center">
							应付金额
						</td>
						<td align="center">
							已付金额
						</td>
						<td align="center">
							未付金额
						</td>
						<td align="center">
							订单号
						</td>
						<td align="center">
							送货单号
						</td>
						<td align="center">
							创建时间
						</td>
						<td align="center">
							付款周期
						</td>
						<td align="center">
							应付款日期
						</td>
						<td align="center">
							添加人
						</td>
						<td align="center">
							负责人
						</td>
						<td align="center">
							状态
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="corePayableList" id="pagecorePayable"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pagecorePayable.subjectItem}
						</td>
						<td>
							${pagecorePayable.supplierName}
						</td>
						<td>
							${pagecorePayable.zhaiyao}
						</td>
						<td>
							<a target="_showPriceDetail" href="PriceAction!findPriceById.action?id=${pagecorePayable.priceId}&statue=find">
								${pagecorePayable.hsPrice}
							</a>
						</td>
						<td>
							${pagecorePayable.number}
						</td>
						<td>
							${pagecorePayable.yingfukuanJine}
						</td>
						<td>
							${pagecorePayable.realfukuanJine}
						</td>
						<td>
							${pagecorePayable.yingfukuanJine-pagecorePayable.realfukuanJine}
						</td>
						<td>
							<a target="_showWpd"
								href="WaigouwaiweiPlanAction!findWaigouPlanDNDetail.action?id=${pagecorePayable.orderId}">
								${pagecorePayable.orderNumber} </a>
						</td>
						<td>
							${pagecorePayable.deliveryNumber}
						</td>
						<td>
							${pagecorePayable.saveTime}
						</td>
						<td>
							${pagecorePayable.fukuanZq}
						</td>
						<td>
							${pagecorePayable.fukuanDate}
						</td>
						<td>
							${pagecorePayable.saveUser}
						</td>
						<td>
							${pagecorePayable.fuzeren}
						</td>
						<td>
							${pagecorePayable.status}
						</td>
						<td>
							<s:if test="pageStatus=='dkp'">
								<a href="">上传发票</a>
							</s:if>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="20" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="20" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//财务确认 
function submitqueren(wuId) {
	$("#wuId").val(wuId);
	$("#submitProcessDiv").show();
	chageDiv("block");
	//单独设置弹出层的高度
	var thisTopHeight = $(obj).offset().top;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}
</script>
	</body>
</html>
