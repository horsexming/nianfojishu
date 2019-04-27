<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
								您正在上传发票:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="CorePayableAction!uploadFapiao.action"
							enctype="multipart/form-data" method="post"
							onsubmit="return validate()">
							<input type="hidden" id="coreId" name="corePayable.id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										发票号:
									</th>
									<th align="left">
										<input name="corePayable.fapiaoNum" />
									</th>
								</tr>
								<tr>
									<th align="right">
										发票附件:
									</th>
									<th align="left">
										<input name="attachment" type="file" />
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
					月度对账单
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
							对账编号
						</td>
						<td align="center">
							收款单位
						</td>
						<td align="center">
							预付款金额
						</td>
						<td align="center">
							应付款金额
						</td>
						<td align="center">
							已付款金额
						</td>
						<td align="center">
							未付款金额
						</td>
						<td align="center">
							记账月份
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作类型
						</td>
					</tr>
					<s:iterator value="corePayableMonthList" id="pagecorePayableMonth"
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
							${pagecorePayableMonth.recNumber}
						</td>
						<td>
							<a
								href="zhaobiaoAction!listByIdZhUser.action?zhUser.id=${pagecorePayableMonth.supplierId}">
								${pagecorePayableMonth.supplierName} </a>
						</td>
						<td>
							${pagecorePayableMonth.yufukuanJine}
						</td>
						<td align="right">
							<fmt:formatNumber type="number"
								value="${pagecorePayableMonth.yingfukuanJine}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<td align="right">
							<fmt:formatNumber type="number"
								value="${pagecorePayableMonth.realfukuanJine}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<td>
							<fmt:formatNumber type="number"
								value="${pagecorePayableMonth.weifukuanJine}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<td align="right">
							${pagecorePayableMonth.jzMonth}
						</td>
						<td align="right">
							${pagecorePayableMonth.status}
						</td>
						<td align="right">
							<s:if test="pageStatus=='gys'">
								<a
									href="CorePayableAction!findCorePaybleList.action?pageStatus=gysdz&corePayable.fk_CPMId=${pagecorePayableMonth.id}">明细</a>
							</s:if>
							<s:else>
								<a
									href="CorePayableAction!findCorePaybleList.action?pageStatus=all&corePayable.fk_CPMId=${pagecorePayableMonth.id}">明细</a>
							</s:else>
						</td>
					</s:iterator>
					<tr>
						<td colspan="25" align="left" style="color: red; font-size: 20px;">
							<span id="showCheckDetail"></span>
						</td>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="25" align="right">
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
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//财务确认 
function addFapiao(coreId) {
	$("#coreId").val(coreId);
	$("#submitProcessDiv").show();
	chageDiv("block");
	//单独设置弹出层的高度
	var thisTopHeight = $(obj).offset().top;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}

function tosubmitNo(obj) {
	$("#qrTag").val("no");
	obj.submit();
}
</script>
	</body>
</html>
