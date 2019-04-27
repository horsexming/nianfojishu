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
								您正在上传付款凭证:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="ReceiptAction!uploadPayProof.action"
							enctype="multipart/form-data" method="post"
							onsubmit="return validate()">
							<input type="hidden" id="coreId" name="receiptLog.id" />
							<input type="hidden" name="pageStatus" value="${pageStatus}">
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										票据编号:
									</th>
									<th align="left">
										<input type="text" name="receiptLog.fkpzNum" />
									</th>
								</tr>
								<tr>
									<th align="right">
										支付方式:
									</th>
									<th align="left">
										<select name="id" id="zjstyle"></select>
									</th>
								</tr>
								<tr>
									<th align="right">
										付款凭证附件:
									</th>
									<th align="left">
										<input name="attachment" type="file" />
										<input type="hidden" name="tages" id="tages"/>
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
					付款申请记录管理
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="ReceiptAction!findReceiptLogList.action" method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden">
					<table class="table">
						<tr>
							<td>
								付款流水号
							</td>
							<td>
								<input value="${receiptLog.pkNumber}" name="receiptLog.pkNumber">
							</td>
							<td>
								收款单位
							</td>
							<td>
								<input value="${receiptLog.receipt.payee}" name="receiptLog.receipt.payee">
							</td>
							<td>
								状态
							</td>
							<td>
								<select name="receiptLog.status">
									<option value="${receiptLog.status}">
										${receiptLog.status}
									</option>
									<option>
									</option>
									<option>
										上传凭证
									</option>
									<option>
										收款确认
									</option>
									<option>
										完成
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询" class="input" />
							</th>
						</tr>
					</table>
				</form>
				<form action="ReceiptAction!weituo.action" method="post"
					onsubmit="return window.confirm('您确定要申请委托付款吗？')">
					<input type="hidden" name="pageStatus" value="${pageStatus}">
					<table class="table" style="border-collapse: collapse;">
						<tr bgcolor="#c0dcf2" height="50px">
							<td></td>
							<td align="center">
								序号
							</td>
							<td align="center">
								付款流水号
							</td>
							<td align="center">
								收款单位
							</td>
							<td align="center">
								摘要
							</td>
							<td align="center">
								付款金额
							</td>
							<td align="center">
								批准 日期
							</td>
							<td align="center">
								实付日期
							</td>
							<td align="center">
								支付凭证
							</td>
							<td align="center">
								负责人
							</td>
							<td align="center">
								状态
							</td>
							<td align="center">
								操作
							</td>
						</tr>
						<tr>
							<th colspan="15" style="color: #ffffff; background-color: red;">
								待审批付款申请
							</th>
						</tr>
						<s:iterator value="receiptLogListAudit" id="pagecoreReceiptLog"
							status="pageIndex">
							<s:if test="#pageIndex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 25px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<%--								<s:if--%>
								<%--									test="#pagecoreReceiptLog.haswt==null||#pagecoreReceiptLog.haswt!='是'.toString()">--%>
								<%--									<input type="checkbox" value="${pagecoreReceiptLog.id}"--%>
								<%--										name="ids" onchange="chageNum(this)">--%>
								<%--								</s:if>--%>
							</td>
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${pagecoreReceiptLog.pkNumber}
							</td>
							<td align="left" style="width: 200px;">
								${pagecoreReceiptLog.receipt.payee}
							</td>
							<td align="left">
								${pagecoreReceiptLog.receipt.summary}
							</td>
							<td align="right">
								${pagecoreReceiptLog.allMoney}
							</td>
							<td>
								${pagecoreReceiptLog.needPayDate}
							</td>
							<td>
								${pagecoreReceiptLog.realPayDate}
							</td>
							<td>
									<a
										href="FileViewAction.action?FilePath=/upload/file/payPz/${pagecoreReceiptLog.fileName}">${pagecoreReceiptLog.fileName}</a>
							</td>
							<td>
								${pagecoreReceiptLog.payUserName}
							</td>
							<td>
								${pagecoreReceiptLog.status}
							</td>
							<td>
								<s:if test='#pagecoreReceiptLog.status=="上传凭证"'>
									<a href="javascript:;"
										onclick="addFapiao('${pagecoreReceiptLog.id}')">上传付款流水号</a>
								</s:if>
							</td>
						</s:iterator>
						<tr>
							<th colspan="15" style="color: #ffffff; background-color: red;">
								待付款&待确认
							</th>
						</tr>
						<s:iterator value="receiptLogListSkqr" id="pagecoreReceiptLog"
							status="pageIndex">
							<s:if test="#pageIndex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 25px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if
									test="#pagecoreReceiptLog.haswt==null||#pagecoreReceiptLog.haswt!='是'.toString()">
									<input type="checkbox" value="${pagecoreReceiptLog.id}"
										name="ids" onchange="chageNum(this)">
								</s:if>
							</td>
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${pagecoreReceiptLog.pkNumber}
							</td>
							<td align="left" style="width: 200px;">
								${pagecoreReceiptLog.receipt.payee}
							</td>
							<td align="left">
								${pagecoreReceiptLog.receipt.summary}
							</td>
							<td align="right">
								${pagecoreReceiptLog.allMoney}
							</td>
							<td>
								${pagecoreReceiptLog.needPayDate}
							</td>
							<td>
								${pagecoreReceiptLog.realPayDate}
							</td>
							<td>
								<a href="FileViewAction.action?FilePath=/upload/file/payPz/${pagecoreReceiptLog.fileName}">${pagecoreReceiptLog.fileName}</a>
							</td>
							<td>
								${pagecoreReceiptLog.payUserName}
							</td>
							<td>
								${pagecoreReceiptLog.status}
							</td>
							<td>
								<s:if test='#pagecoreReceiptLog.status=="上传凭证"'>
									<a href="javascript:;"
										onclick="addFapiao('${pagecoreReceiptLog.id}')">上传付款流水号</a>
								</s:if>
							</td>
						</s:iterator>
<%--						<tr>--%>
<%--							<td colspan="25" align="left"--%>
<%--								style="color: red; font-size: 20px;">--%>
<%--								<span id="showCheckDetail"></span>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr id="wtdiv" style="display: none;">--%>
<%--							<td colspan="16" align="center">--%>
<%--								被委托方:--%>
<%--								<input name="bwtCompany">--%>
<%--								&nbsp;&nbsp; 支付方式:--%>
<%--								<SELECT name="payWay">--%>
<%--									<option>--%>
<%--										现金--%>
<%--									</option>--%>
<%--									<option>--%>
<%--										银行转账--%>
<%--									</option>--%>
<%--								</SELECT>--%>
<%----%>
<%--								<br />--%>
<%--								<input id="wtBtn" value="委托" type="submit"--%>
<%--									style="width: 60px; height: 40px;" disabled="disabled">--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<th colspan="15" style="color: #ffffff; background-color: red;">
								待上传付款凭证
							</th>
						</tr>
						<s:iterator value="receiptLogListUpload" id="pagecoreReceiptLog"
							status="pageIndex">
							<s:if test="#pageIndex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 25px;" onmouseout="outBgcolor(this,'')">
							</s:else>
<%--								<s:if--%>
<%--									test="#pagecoreReceiptLog.haswt==null||#pagecoreReceiptLog.haswt!='是'.toString()">--%>
<%--									<input type="checkbox" value="${pagecoreReceiptLog.id}"--%>
<%--										name="ids" onchange="chageNum(this)">--%>
<%--								</s:if>--%>
							<td>
							</td>
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${pagecoreReceiptLog.pkNumber}
							</td>
							<td align="left" style="width: 200px;">
								${pagecoreReceiptLog.receipt.payee}
							</td>
							<td align="left" style="width: 200px;">
								${pagecoreReceiptLog.receipt.summary}
							</td>
							<td align="right">
								${pagecoreReceiptLog.allMoney}
							</td>
							<td>
								${pagecoreReceiptLog.needPayDate}
							</td>
							<td>
								${pagecoreReceiptLog.realPayDate}
							</td>
							<td>
								<a
									href="FileViewAction.action?FilePath=/upload/file/payPz/${pagecoreReceiptLog.fileName}">${pagecoreReceiptLog.fileName}</a>
							</td>
							<td>
								${pagecoreReceiptLog.payUserName}
							</td>
							<td>
								${pagecoreReceiptLog.status}
							</td>
							<td>
								<s:if test='#pagecoreReceiptLog.status=="上传凭证"'>
									<a href="javascript:;"
										onclick="addFapiao('${pagecoreReceiptLog.id}')">上传付款流水号</a>
								</s:if>
							</td>
						</s:iterator>
<%--						<tr>--%>
<%--							<td colspan="25" align="left"--%>
<%--								style="color: red; font-size: 20px;">--%>
<%--								<span id="showCheckDetail"></span>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr id="wtdiv" style="display: none;">--%>
<%--							<td colspan="16" align="center">--%>
<%--								被委托方:--%>
<%--								<input name="bwtCompany">--%>
<%--								&nbsp;&nbsp; 支付方式:--%>
<%--								<SELECT name="payWay">--%>
<%--									<option>--%>
<%--										现金--%>
<%--									</option>--%>
<%--									<option>--%>
<%--										银行转账--%>
<%--									</option>--%>
<%--								</SELECT>--%>
<%----%>
<%--								<br />--%>
<%--								<input id="wtBtn" value="委托" type="submit"--%>
<%--									style="width: 60px; height: 40px;" disabled="disabled">--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<th colspan="15" style="color: #ffffff; background-color: green;">
								${pageStatus=='all'?'所有':'历史'}付款申请
							</th>
						</tr>
						<s:iterator value="receiptLogList" id="pagecoreReceiptLog"
							status="pageIndex">
							<s:if test="#pageIndex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 25px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<%--								<s:if--%>
								<%--									test="#pagecoreReceiptLog.haswt==null||#pagecoreReceiptLog.haswt!='是'.toString()">--%>
								<%--									<input type="checkbox" value="${pagecoreReceiptLog.id}"--%>
								<%--										name="ids" onchange="chageNum(this)">--%>
								<%--								</s:if>--%>
							</td>
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${pagecoreReceiptLog.pkNumber}
							</td>
							<td style="width: 200px;">
								${pagecoreReceiptLog.receipt.payee}
							</td>
							<td align="left" style="width: 200px;">
								${pagecoreReceiptLog.receipt.summary}
							</td>
							<td align="right">
								${pagecoreReceiptLog.allMoney}
							</td>
							<td>
								${pagecoreReceiptLog.auditTime}
							</td>
							<td>
								${pagecoreReceiptLog.realPayDate}
							</td>
							<td>
								<s:if test='#pagecoreReceiptLog.status=="完成"&&(#pagecoreReceiptLog.fileName==null||#pagecoreReceiptLog.fileName==""||#pagecoreReceiptLog.fileName=="上传文件失败!")'>
									<a href="javascript:;"
										onclick="updateFapiao('${pagecoreReceiptLog.id}')">重传付款流水号</a>
								</s:if>
								<s:else>
									<a
										href="FileViewAction.action?FilePath=/upload/file/payPz/${pagecoreReceiptLog.fileName}">${pagecoreReceiptLog.fkpzNum==null?pagecoreReceiptLog.fileName:pagecoreReceiptLog.fkpzNum}</a>
								</s:else>
							</td>
							<td>
								${pagecoreReceiptLog.payUserName}
							</td>
							<td>
								${pagecoreReceiptLog.status}
							</td>
							<td>
								<s:if test='#pagecoreReceiptLog.status=="上传凭证"'>
									<a href="javascript:;"
										onclick="addFapiao('${pagecoreReceiptLog.id}')">上传付款流水号</a>
								</s:if>
								<s:if test='#pagecoreReceiptLog.status=="完成1"'>
									<a href="javascript:;"
										onclick="addFapiao('${pagecoreReceiptLog.id}')">上传付款流水号</a>
								</s:if>
								<s:if test='#pagecoreReceiptLog.status=="收款确认"'>
									<a
										href="ReceiptAction!chenkreceiptLog.action?id=${pagecoreReceiptLog.id}">收款确认</a>
								</s:if>
							</td>
						</s:iterator>
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
	$("#tages").val("");
	$("#coreId").val(coreId);
	$("#submitProcessDiv").show();
	$.ajax( {
		type : "POST",
		url : "BaoXiaoDanAction!findchildSubjects.action",
		data : {
			tag : "payType"
		},
		dataType : 'json',
		success : function(data) {
			$("#zjstyle").empty();
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "</option>").appendTo("#zjstyle");
					});
		}
	})
	chageDiv("block");
	//单独设置弹出层的高度
	var thisTopHeight = $(obj).offset().top;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}
function updateFapiao(coreId) {
	$("#tages").val("chong");
	$("#coreId").val(coreId);
	$("#submitProcessDiv").show();
	$.ajax( {
		type : "POST",
		url : "BaoXiaoDanAction!findchildSubjects.action",
		data : {
			tag : "payType"
		},
		dataType : 'json',
		success : function(data) {
			$("#zjstyle").empty();
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "</option>").appendTo("#zjstyle");
					});
		}
	})
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
function chageNum(obj) {
	var inputs = document.getElementsByTagName("input");
	var num = 0;
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked == true) {
				num++;
			}
		}
	}
	if (num == 0) {
		$("#wtBtn").attr("disabled", "disabled");
		$("#wtdiv").hide();
	} else {
		$("#wtBtn").removeAttr("disabled");
		$("#wtdiv").show();
	}
}
</script>
	</body>
</html>
