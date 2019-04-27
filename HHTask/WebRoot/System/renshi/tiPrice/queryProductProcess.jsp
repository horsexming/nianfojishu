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

		<script type="text/javascript"
			src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
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
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
				<div id="uploadChageWageDiv"
					style="background-color: #ffffff; width: 100%;">
					<form
						action="productPriceAction!saveProductProcess.action?id=<%=request.getParameter("id")%>"
						method="post">
						<input type="hidden" id="sparePartsId" name="sparePartsId"
							value='<%=request.getParameter("id")%>'>
						<h2>
							添加 工序信息
						</h2>
						<table border="1">
							<tr>
								<td>
									工序号：
									<input type="text" name="productProcess.processNo" />
								</td>
								<td>
									工序名称：
									<input type="text" name="productProcess.processName" />
								</td>
								<td>
									人工节拍：
									<input type="text" name="productProcess.realJIEPAI" value="0" />
									(s)
								</td>
							</tr>
							<tr>
								<td>
									操作人工号：
									<input type="text" onblur="send(this)" id="code"
										name="productProcess.operatorCode" />
									(s)
								</td>
								<td>
									操作者：
									<input type="text" id="username"
										name="productProcess.operatorName" readonly="true" />
								</td>
								<td>
									单件节拍：
									<input type="text" name="productProcess.danjianJIEPAI"
										value="0" />
									(s)
								</td>
							</tr>
							<tr>
								<td>
									工位号：
									<select name="productProcess.gongwei" id="gongwei"
										style="width: 155px;" onmouseover="findgongwei()">
										<option value=""></option>
									</select>
								</td>
								<td>
									设备编号：
									<select id="shebeiCode" name="productProcess.shebeiNo"
										style="width: 155px;" onmouseover="getshebeiCode(this)"
										onmouseout="getGongweiAndOth()">

										<option value=""></option>
									</select>
								</td>
								<td>
									设备名称：
									<input type="text" name="" id="shebeiName" readOnly="readonly"
										style="background-color: #cccccc;" />
								</td>
							</tr>
							<tr>
								<td colspan="3"
									style="text-align: center; background-color: gray;">
									操作过程
								</td>
							</tr>
							<tr>

								<td>
									人工节拍：
									<input type="text" name="productProcess.OPcaozuojiepai"
										value="0" />
									(s)
								</td>
								<td>
									设备节拍：
									<input type="text" name="productProcess.OPshebeijiepai"
										value="0" />
									(s)
								</td>
								<td>
									交付量：
									<input type="text" name="productProcess.OPjiaofu"
										id="Dayjiaofu" readOnly="readonly"
										style="background-color: #cccccc;" />
								</td>

							</tr>
							<tr>
								<td>
									负荷指数：
									<input type="text" name="productProcess.OPfuheRate" value="0" />
								</td>
								<td>
									技能指数：
									<input type="text" name="productProcess.OPtechnologyRate"
										id="OPtechnologyRate" readOnly="readonly"
										style="background-color: #cccccc;" />
								</td>
								<td>
									可替换人数：
									<input type="text" name="productProcess.OPCouldReplaceRate"
										id="OPCouldReplaceRate" readOnly="readonly"
										style="background-color: #cccccc;" />
								</td>

							</tr>
							<tr>
								<td colspan="3"
									style="text-align: center; background-color: gray;">
									准备过程
								</td>
							</tr>
							<tr>
								<td>
									人工节拍：
									<input type="text" name="productProcess.GZzhunbeijiepai"
										value="0" />
									(s)
								</td>
								<td>
									准备次数：
									<input type="text" name="productProcess.GZzhunbeicishu"
										value="0" />
								</td>
								<td>
									备注：
									<input type="text" name="productProcess.more" />
								</td>
							</tr>
							<tr>
								<td>
									负荷指数：
									<input type="text" name="productProcess.GZfuheRate"
										id="GZfuheRate" readOnly="readonly"
										style="background-color: #cccccc;" />
								</td>
								<td>
									技能指数：
									<input type="text" name="productProcess.GZtechnologyRate"
										id="GZtechnologyRate" readOnly="readonly"
										style="background-color: #cccccc;" />
								</td>
								<td>
									可替换人数：
									<input type="text" name="productProcess.GZCouldReplaceRate"
										id="GZCouldReplaceRate" readOnly="readonly"
										style="background-color: #cccccc;" />
								</td>

							</tr>
							<tr>
								<td>
									同步工序：
									<input type="text" name="productProcess.fuheProcessNo" />
								</td>
								<td>
									视频连接地址：
									<input type="text" name="productProcess.videoFile" />
								</td>
								<td>
									备注：
									<input type="text" name="productProcess.processMore" />
								</td>

							</tr>
							<tr>
								<td colspan="3" align="center">
									<input type="submit" style="width: 120px; text-align: ceneter;"
										value="添加" />
									&nbsp;
									<input type="reset" style="width: 100px; text-align: ceneter;"
										value="取消">

								</td>
							</tr>
						</table>
					</form>
				</div>


			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">

				<table width="95%" class="table">
					<tr>
						<td colspan="13" align="center"
							style="font-size: 15px; font-weight: bold;">
							<input type="button" onclick="chageDiv('block');" value="添加工序" />
							&nbsp;&nbsp;&nbsp;
							<a href="javascript:history.back()">返回</a>
						</td>
					</tr>
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 20px; font-weight: bold;">
						<td rowspan="2">
							工序号
						</td>
						<td rowspan="2">
							工序
						</td>
						<td rowspan="2">
							工位号
						</td>
						<td rowspan="2">
							设备号
						</td>
						<td colspan="3">
							操作过程
						</td>
						<td colspan="2">
							准备过程
						</td>
						<td rowspan="2">
							操作者
						</td>
						<td rowspan="2">
							工序累计奖金
						</td>
						<td rowspan="2">
							工序单价
						</td>
						<td rowspan="2">
							操作
						</td>

					</tr>
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 20px; font-weight: bold;">
						<td>
							负荷指数
						</td>
						<td>
							人工节拍
						</td>
						<td>
							设备节拍
						</td>
						<td>
							人工节拍
						</td>
						<td>
							准备次数
						</td>
					</tr>
					<s:iterator value="list" status="se" id="sell">
						<s:if test="#se.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="processNo" />
						</td>
						<td>
							<s:property value="processName" />
						</td>
						<td>
							<s:property value="gongwei" />
						</td>
						<td>
							<s:property value="shebeiNo" />
						</td>

						<td>
							<s:property value="OPfuheRate" />
						</td>
						<td>
							<s:property value="OPcaozuojiepai" />
						</td>
						<td>
							<s:property value="OPshebeijiepai" />
						</td>

						<td>
							<s:property value="GZzhunbeijiepai" />
						</td>
						<td>
							<s:property value="GZzhunbeicishu" />
						</td>
						<td>
							<s:property value="operatorName" />
						</td>

						<td>
							<s:property value="picizonge" />
						</td>
						<td>
							<s:property value="processMomey" />
						</td>
						<td>
							<a
								href="productPriceAction!getOneProductProcessById.action?id=<s:property value='id' />&tag=update">修改</a>
							<a
								href="productPriceAction!getOneProductProcessById.action?id=<s:property value='id' />&tag=delete">删除</a>
							<input type="button" value="查看明细" />

						</td>

						</tr>
					</s:iterator>
					<tr>
						<td colspan="13" align="right">
							共
							<s:property value="total" />
							页 第
							<s:property value="cpage" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

//查询工位信息
function findgongwei() {
	createDept('gongwei', 'productPriceAction!findGongwei.action')
}
//根据工位查询设备编号
function getshebeiCode(obj) {
	var gongwei = document.getElementById("gongwei").value;
	var select = document.getElementById("shebeiCode");
	select.options.length = 0;
	if (null != gongwei && "" != gongwei) {
		createDept("shebeiCode",
				"productPriceAction!findShebeiCode.action?tag=" + gongwei);
	}
	//getGongweiAndOth(obj);
}
//查询工位对象和交付量
function getGongweiAndOth(obj) {
	//工位和设备编号
	var gongwei = document.getElementById("gongwei").value;
	var shebeicode = document.getElementById("shebeiCode").value;
	var id = document.getElementById("sparePartsId").value;
	$
			.ajax( {
				type : "POST",
				url : "productPriceAction!getGongweiAndOth.action",
				data : "gongweihao=" + gongwei + "&shebeiCode=" + shebeicode
						+ "&id=" + id,
				success : function(msg) {
					var d = $.parseJSON(msg);
					//赋值
				document.getElementById("Dayjiaofu").value = d.dalyOut;
				document.getElementById("shebeiName").value = d.gongwei.shebeiName;
				document.getElementById("OPtechnologyRate").value = d.gongwei.caozJineng;
				document.getElementById("OPCouldReplaceRate").value = d.gongwei.caoztihuanrenshu;
				document.getElementById("GZfuheRate").value = d.gongwei.gongzhuangFuhe;
				document.getElementById("GZtechnologyRate").value = d.gongwei.gongzhuangJineng;
				document.getElementById("GZCouldReplaceRate").value = d.gongwei.gongzhuangtihuanrenshu;
				// alert(d.gongwei.gongzhuangJineng+"id="+d.gongwei.id+"====="+d.dalyOut);
			}
			});
}
//根据工号查询人员姓名	
function send(obj) {
	var value = encodeURI(obj.value);//对strValue进行编码 
	sendRequest("productPriceAction!findUserName.action?code=" + value,
			messageResponse);

}
// 联系人查询
function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			document.getElementById("username").value = message;

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
</script>
	</body>
</html>
