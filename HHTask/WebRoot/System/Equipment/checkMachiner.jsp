<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">信息</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="submitProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在提交工序:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv1"
						style="background-color: #ffffff; width: 100%;">
						<form id="submitProcess">
							<input id="submitProId" type="hidden" name="process.id" />
							<input type="hidden" id="submitProcardId" name="id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<td colspan="2" align="center">
										<b>提交工序</b>
									</td>
								</tr>
								<tr>
									<th>
										提交数量:
									</th>
									<th align="left">
										<input id="subNumber" name="process.submmitCount"
											maxsize="100" />
										最大可提交数量
									</th>
								</tr>
								<tr>
									<th>
										不合格数量:
									</th>
									<th align="left">
										<input name="process.breakCount" value="0" />
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input id="submitProce" type="button" value="提交"
											onclick='submitForm()' style="width: 80px; height: 50px;" />
										<div id="showWait"></div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; display: none;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng">
			<div align="center">
				<font>工位设备信息</font>
					<table class="table">
						<tr height="40px">
							<th>
								序号
							</th>
							<th>
								工位号
							</th>
							<th>
								设备号
							</th>
							<th>
								设备名称
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:iterator value="gongweiList" id="samples" status="pageStatus">
							<tr align="center">
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="center">
									${samples.gongweihao}
								</td>
								<td align="center">
								<a href="javascript:;"
								onclick="showDetail('ProdEquipmentAction!findMachineByNum.action?machine.no=${samples.shebeiCode}')">${samples.shebeiCode}</a>
								</td>
								<td align="center">
									${samples.shebeiName}
								</td>
								<td align="center">
									<s:if test="#samples.shebeiId!=null">
										<button onclick="baoxiu('${samples.shebeiId}')" style="width: 80px;height: 40px;font-size: 14px;">报修</button>
										<button onclick="window.location.href='EquipmentAction!findAlll.action?maintenance.name='" style="width: 80px;height: 40px;font-size: 14px;">报修确认</button>
										
									</s:if>
									<s:else>
										设备编号和名称都为空<br/>
										请先完善设备信息
									</s:else>
								</td>
							</tr>
						</s:iterator>
					</table>
			</div>
			<div style="margin-bottom: 5px; margin-top: 5px;" align="right">
					<div
						style="float: left; margin-top: 9px; left: 100px; margin-left: 40px; font-size: 16px;">
						<b>工位：${gongweiList[0].gongweihao}</b>
						操作者:
						<s:if test="#session.Users.name!=null">
						${Users.name}&nbsp;&nbsp;&nbsp;&nbsp;
						</s:if>
						<s:else>
							<a href="javascript:;" onclick="denglu('')">点击登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</s:else>
					</div>
					<div style="float: left; margin-top: 9px; right: 100px;">
						<s:if test="#session.Users.name!=null">
							<a href="javascript:;" onclick="lingqu()"
								style="font-size: 22px;">[领取工序]</a>&nbsp;&nbsp;
							<a href="javascript:;" onclick="tijiaoList()"
								style="font-size: 22px;">[提交工序]</a>&nbsp;&nbsp;
							<a href="javascript:;" onclick="qita()" style="font-size: 22px;">[查看其它]</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</s:if>
					</div>
					<div id="lightId" style="float: left; margin-left: 10px;">
						<s:if test="#session.Users.name!=null">
							<s:iterator value="listLight" id="light" status="pageIndex">
								<s:if test='#light.lightStatus=="关闭"'>
									<img height="40" width="30" src='images/Loff.png'
										style="margin-right: 10px;"
										onclick="lightOnOff('${light.id}','${light.lightZhiLing}','${ipAddress}',this)"
										title="${light.lightNum}" />
								</s:if>
								<s:else>
									<img style="margin-right: 10px;"
										onclick="lightOnOff('${light.id}','${light.lightZhiLing}','${ipAddress}',this)"
										height="40" width="30" src='images/Lon.png'
										title="${light.lightNum}" />
								</s:else>
							</s:iterator>
						</s:if>
					</div>
					<div
						style="float: right; margin-top: 9px; margin-right: 30px; font-size: 18px;">
						<a href="javascript:reload();">[刷新]</a>
						<s:if test="#session.Users.name!=null">
							<a href="SuspsomAction_toOut.action?ipAddress=${ipAddress}">[退出登录]</a>
						</s:if>
					</div>
					<div style="clear: both;"></div>
				</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function showDetail(url) {
	$("#xiugaiIframe").attr("src", url);
	$("#operatingDiv").show();
	chageDiv("block");
}

function denglu(cardid) {
	var url = "SuspsomAction_denglu.action";
	if(cardid!=''){
		url+="?gow="+cardid;
	}
	$("#xiugaiIframe").attr("src", url);
	$("#operatingDiv").show();
	$("#submitProcessDiv").hide();
	chageDiv('block');
}
function tijiao() {
	var url = "<%=basePath%>System/SOP/produce/Process_shuaka.jsp?pageStatus=gongweitijiao";
	$("#xiugaiIframe").attr("src", url);
	$("#operatingDiv").show();
	$("#submitProcessDiv").hide();
	chageDiv('block');
}
function lingqu(){
	window.location.href = "OneMachineAction_findProcardByCardGxNum.action?&tc=no&pageStatus=lingqu";
}
function lingqu1() {
	var url = "OneMachineAction_findProcardByCardGxNum.action";
	$("#xiugaiIframe").attr("src", url);
	$("#operatingDiv").show();
	$("#submitProcessDiv").hide();
	$("#contentDiv").removeAttr("style");
	$("#contentDiv").removeAttr("class");
	$("#contentDiv").addClass("contentDiv1");//关闭统一样式
	chageDiv('block');
}
function baoxiu(id){
	window.location.href = "EquipmentAction!findAllId.action?machine.id="+id;
}
function baoxiu1(id) {
	var url = "EquipmentAction!findAllId.action?machine.id="+id;
	$("#xiugaiIframe").attr("src", url);
	$("#operatingDiv").show();
	$("#submitProcessDiv").hide();
	$("#contentDiv").removeAttr("style");
	$("#contentDiv").removeAttr("class");
	$("#contentDiv").addClass("contentDiv1");//关闭统一样式
	chageDiv('block');
}
function tijiaoList(){
	window.location.href = "ProcardAction!findProcardByCardNum.action?pageStatus=noCardHadYlGx&cardNumber=${session.Users.cardId}&tc=no";
}
function tijiaoList1() {
	var url = "ProcardAction!findProcardByCardNum.action?pageStatus=noCardHadYlGx&cardNumber=${session.Users.cardId}";
	$("#xiugaiIframe").attr("src", url);
	$("#operatingDiv").show();
	$("#submitProcessDiv").hide();
	$("#contentDiv").removeAttr("style");
	$("#contentDiv").removeAttr("class");
	$("#contentDiv").addClass("contentDiv1");//关闭统一样式
	chageDiv('block');
}
function qita(){
	window.location.href = "SuspsomAction_xuanzhe.action";
	}
function qita1() {
	var url = "SuspsomAction_xuanzhe.action";
	$("#xiugaiIframe").attr("src", url);
	$("#operatingDiv").show();
	$("#submitProcessDiv").hide();
	$("#contentDiv").removeAttr("style");
	$("#contentDiv").removeAttr("class");
	$("#contentDiv").addClass("contentDiv1");//关闭统一样式
	chageDiv('block');
}
</script>

	</body>
</html>
