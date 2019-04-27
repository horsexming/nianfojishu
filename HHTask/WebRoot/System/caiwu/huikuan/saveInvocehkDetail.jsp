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
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>

			</div>

			<div align="center">
				<form action="huikuanAction!saveInvoice.action" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="taHk.id" value="${taHk.id}">
					<table style="width: 98%; margin: 5 px" class="table">
						<tr>
							<td colspan="11" align="right">
								编号：
								<s:property value="taHk.hkNum" />
								<span style="width: 50px;"></span>
							</td>
						</tr>
						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td>
								序号
							</td>
							<td>
								客户
							</td>
							<td>
								零件号
							</td>
							<td>
								开票数量
							</td>
							<td>
								送货单号
							</td>
							<td>
								订单号
							</td>
							<td>
								是否含税
							</td>
							<td>
								单价
							</td>
							<td>
								币种
							</td>
							<td>
								发票号
							</td>
							<td>
								上传发票
							</td>
						</tr>
						<s:iterator value="listHkSellSta" status="hk" id="huikuan">
							<s:if test="#hk.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#hk.index+1" />
							</td>

							<input type="hidden"
								name="hkset[<s:property value='#hk.index' />].id" size="7"
								readonly="readonly" value="${id }" />
							<td>
								<input
									name="hkset[<s:property value='#hk.index' />].hkSellCumpanyName"
									size="7" readonly="readonly" value="${hkSellCumpanyName }" />
							</td>
							<td>
								<input style="width: 100px;"
									name="hkset[<s:property value='#hk.index' />].hkSellMarkId"
									id="markId<s:property value='#hk.index' />" size="7"
									readonly="readonly" value="${hkSellMarkId }" />
							</td>
							<td>
								<input
									name="hkset[<s:property value='#hk.index' />].hkSellCount"
									size="7" readonly="readonly" value="${hkSellCount }" />
							</td>
							<td>
								<input
									name="hkset[<s:property value='#hk.index' />].hkSellSendId"
									size="7" readonly="readonly" value="${hkSellSendId }" />
							</td>
							<td>
								<input
									name="hkset[<s:property value='#hk.index' />].hkSellOutOrderId"
									size="7" readonly="readonly" value="${hkSellOutOrderId }" />
							</td>
							<td>
								<s:if test="hkSellIsTax!=''&&hkSellIsTax!=null">
									<s:if test="hkSellIsTax=='YES'">
										<input type="text" readonly="readonly" size="1px;" value="是" />
									</s:if>
									<s:else>
										<input type="text" readonly="readonly" size="1px;" value="否" />
									</s:else>
								</s:if>
								<s:else>
									<select id="isTax<s:property value='#hk.index' />"
										name="hkset[<s:property value='#hk.index' />].hkSellIsTax"
										onblur="sameSelect('<s:property value='#hk.index' />',this)">
										<option value="YES">
											是
										</option>
										<option value="NO">
											否
										</option>
									</select>
								</s:else>

							</td>
							<td>
								<s:if test="hkSellPrice!=''&&hkSellPrice!=null">
									<select style="width: 100px;" style="width: 155px;">
										<option>
											${hkSellPrice}
										</option>
									</select>
								</s:if>
								<s:else>
									<select style="width: 100px;"
										name="hkset[<s:property value='#hk.index' />].hkSellPrice"
										id="price<s:property value='#hk.index' />"
										style="width: 155px;"
										onfocus="selectPrice('<s:property value="#hk.index" />','${hkSellMarkId}')"
										onblur="samePrice('<s:property value="#hk.index" />','${hkSellMarkId}',this)">
									</select>
								</s:else>
							</td>
							<!-- name="invoiceArr[<s:property value='#hk.index' />].hkInvoMoneyUnit" -->
							<td>
								<!-- list="{'元','美元','欧元','港币','英镑','日元','瑞士法郎','澳元','加元'}"  -->
								<s:if test="hkSellMoneyUnit!=''&&hkSellMoneyUnit!=null">
									<select>
										<option>
											${hkSellMoneyUnit}
										</option>
									</select>
								</s:if>
								<s:else>
									<select
										name="hkset[<s:property value='#hk.index' />].hkSellMoneyUnit">
										<option value="元">
											元
										</option>
										<option value="美元">
											美元
										</option>
										<option value="欧元">
											欧元
										</option>
										<option value="港币">
											港币
										</option>
										<option value="英镑">
											英镑
										</option>
										<option value="日元">
											日元
										</option>
										<option value="瑞士法郎">
											瑞士法郎
										</option>
										<option value="澳元">
											澳元
										</option>

									</select>
								</s:else>
							</td>
							<td>
								<s:if
									test="TaHkHkInvoice.hkInvoInvoNum!=''&&TaHkHkInvoice.hkInvoInvoNum!=null">
									<input type="text" size="7" readonly="readonly"
										value="${TaHkHkInvoice.hkInvoInvoNum}" />
								</s:if>
								<s:else>
									<input
										name="invoiceArr[<s:property value='#hk.index' />].hkInvoInvoNum"
										size="7" />
								</s:else>

							</td>
							<td>
								<!-- 
								<input type="file" name="attachment" onchange="chageFlie(<s:property value="#hk.index+1" />, 'yes')">
								<input type="" name="attachmentStatus" id='value<s:property value="#hk.index+1" />' value="no">
								 -->
								<s:if test="hkSellFile!=''&&hkSellFile!=null">
									<%--								已上传--%>
									<input type="button" value=" 下载合同"
										onclick="xiazai('${hkSellFile}')">
									<%--									<input type="text" readonly="readonly"  value="已上传"/>--%>
								</s:if>
								<s:else>
									<input type="button"
										id="fileButton_<s:property value="#hk.index+1" />"
										onclick="uploadFile(this,'<s:property value="#hk.index+1" />')"
										value="上传发票">
									<input type="hidden" name="attachmentStatus"
										id='value<s:property value="#hk.index+1" />' value="no">
									<div id="fileDiv_<s:property value='#hk.index+1'/>"
										style="display: none;">
									</div>
								</s:else>


							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="11" align="left">
								回款周期：
								<input id="backCircle" name="taHk.hkPayCycle" size="7"
									value="${taHk.hkPayCycle}" />
								(天) 回款倒计时开始时间：
								<input class="Wdate" type="text" name="taHk.hkBillTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									value="${taHk.hkBillTime}">
								折扣合计：
								<input type="text" name="taHk.zhekou" value="${taHk.zhekou}">

							</td>
						</tr>

						<tr>
							<td colspan="11" align="center">
								<input type="submit" value="提交" Onclick="return check22()"
									style="width: 60px; height: 40px;" align="top">
								&nbsp;
								<input type="reset" value="取消"
									style="width: 60px; height: 40px;" align="top">
								&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

	</body>
	<script type="text/javascript">
//下载合同
function xiazai(hkSellFile1) {
	//对中文进行加密
	var fileName1 = encodeURI(encodeURI(hkSellFile1));
<%--	location.href = "DownAction.action?directory=/upload/huikuan/invoice/&fileName="--%>
<%--			+ fileName1;--%>
	location.href = "FileViewAction.action?FilePath=/upload/huikuan/invoice/"
			+ fileName1;
}
function uploadFile(obj, few) {
	obj.style.display = "none";
	var fileDiv = document.getElementById("fileDiv_" + few);
	fileDiv.style.display = "block";
	fileDiv.innerHTML = "<input type='file' name='attachment' onchange=chageFlie('"
			+ few
			+ "','yes') /><a href=javascript:delFile('"
			+ few
			+ "')>删除</a></div>";
}

function delFile(few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	var value = document.getElementById("value" + few);
	value.value = "no";
	fileDiv.innerHTML = "";
	fileDiv.style.display = "none";
	document.getElementById("fileButton_" + few).style.display = "block";
}

function check22() {
	var circle = document.getElementById("backCircle");
	//alert(circle.value);
	if (null == circle.value || "" == circle.value) {
		alert("回款周期不能为空!");
		backCircle.focus();
		return false;
	}
}
//处理伤处附件
function showFile(index) {
	document.getElementById("fileButton_" + index).style.display = "none";
	document.getElementById("fileDiv_" + index).style.display = "block";

}
function hiddenFile(index) {
	document.getElementById("fileButton_" + index).style.display = "block";
	document.getElementById("fileDiv_" + index).style.display = "none";
}

function chageFlie(obj, sta) {
	var value = document.getElementById("value" + obj);
	value.value = sta;
}
function selectPrice(id, hkSellMarkId) {
	var isTax = document.getElementById("isTax" + id).value;
	document.getElementById("price" + id).options.length = 0;
	createDept("price" + id, "huikuanAction!selectPrice.action?idea="
			+ hkSellMarkId + "&isTax=" + isTax);
}
function samePrice(id, hkSellMarkId, obj) {
	var len = "<s:property value='listHkSellSta.size()' />";
	var i = parseFloat(id) + 1;
	for (i; i < len; i++) {
		var nextMarkId = document.getElementById("markId" + i).value;
		if (nextMarkId == hkSellMarkId) {
			var selvalue = obj.value;
			var nextStlect = document.getElementById("price" + i);
			nextStlect.options.length = 0;
			var optionItem = new Option(selvalue);
			nextStlect.options.add(optionItem);
		}
	}
}
function sameSelect(id, obj) {
	var len = "<s:property value='listHkSellSta.size()' />";
	var i = parseFloat(id) + 1;
	for (i; i < len; i++) {
		var nextStlect = document.getElementById("isTax" + i);
		if (obj.value == "NO") {
			nextStlect.value = nextStlect.options[1].value;
		} else {
			nextStlect.value = nextStlect.options[0].value;
		}

	}
}
</script>

</html>
