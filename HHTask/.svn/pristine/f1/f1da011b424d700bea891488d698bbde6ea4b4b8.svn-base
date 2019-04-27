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
								您正在领取物品:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="ToolCabineAction_shenqingQu.action?tag=${tag}" method="post" onsubmit="return validate()">
							<input type="hidden" id="wuId" name="depositCabinet.id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										<b>领取物品名称:</b>
									</th>
									<td align="left">
										<input id="wuName" value="" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<th align="right">
										领取物品规格:
									</th>
									<th align="left">
										<input id="wuFormat" value="" readonly="readonly"/>
									</th>
								</tr>
								<tr>
									<th align="right">
										要领取数量:
									</th>
									<th align="left">
										<input name="receiveCabinet.receiveQuantity" id="receiveQuantity"/>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" value="提交" style="width: 65px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="ToolCabineAction_showList_Depos.action?tag=${tag}" method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								名称：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="depositCabinet.depArticleName" />
							</td>
							<th align="center" style="width: 25%">
								规格：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="depositCabinet.depArticleFormat" />
							</td>
						</tr>
						<tr>
							<th align="center" style="width: 25%">
								存入状态：
							</th>
							<td align="center" style="width: 25%">
								<SELECT name="depositCabinet.depositStatus" style="width: 152px;">
								<option></option>
								<option value="待入柜">待入柜</option>
								<option value="入柜中">入柜中</option>
								<option value="已入柜">已入柜</option>
								</SELECT>
							</td>
							<th align="center" style="width: 25%">
								OA编码：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="depositCabinet.artOACoding" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							物品名称
						</td>
						<td align="center">
							物品规格
						</td>
						<td align="center">
							OA编码
						</td>
						<td align="center">
							物品数量
						</td>
						<td align="center">
							申购部门
						</td>
						<td align="center">
							实际存入数量
						</td>
						<td align="center">
							已领取数量
						</td>
						<td align="center">
							存入状态
						</td>
						<td align="center">
							生成时间
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="depositCabinetList" id="depositCabinets"
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
						<td align="center">
							${depositCabinets.depArticleName}
						</td>
						<td align="center">
							${depositCabinets.depArticleFormat}
						</td>
						<td align="center">
							${depositCabinets.artOACoding}
						</td>
						<td align="center">
							${depositCabinets.artQuantity}
						</td>
						<td align="center">
							${depositCabinets.applyDept}
						</td>
						<td align="center">
							${depositCabinets.actualDepositQuantity}
						</td>
						<td align="center">
							${depositCabinets.alreadyReceivedQuantity}
						</td>
						<td align="center">
							${depositCabinets.depositStatus}
						</td>
						<td align="center">
							${depositCabinets.addTime}
						</td>
						<td align="center" colspan="2">
						<s:if test="tag=='cun'||tag=='all'">
							<a href="ToolCabineAction_shenqingCun.action?tag=${tag}&depositCabinet.id=${depositCabinets.id}">存柜</a>
						</s:if>
						<s:if test="tag=='qu'||tag=='all'">
							<a onclick="submitlingqu('${depositCabinets.depArticleName}','${depositCabinets.depArticleFormat}','${depositCabinets.id}')">领取</a>
						</s:if>
							</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
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
function validate() {
	if (!validateText("receiveQuantity", "领取数量")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
//领取物品赋值 
function submitlingqu(wuName, wuFormat, wuId) {
	$("#wuName").val(wuName);
	$("#wuFormat").val(wuFormat);
	$("#wuId").val(wuId);
	$("#submitProcessDiv").show();
	chageDiv("block");
	//单独设置弹出层的高度
	var thisTopHeight = $(obj).offset().top;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}

$("#receiveQuantity").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</script>
	</body>
</html>
