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
							<span id="title">条码解锁/锁定</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<form id="lockForm" action="" method="post">
						<table class="table" style="line-height: 35px;">
							<tr>
								<th align="right">
									申请条码:
								</th>
								<td>
									<input name="tbBarcodeLock.FBarcode" id="brocode_inp"
										style="width: 200px;" />
								</td>
							</tr>
							<tr>
								<th align="right">
									申请解锁/锁定:
								</th>
								<td>
									<input name="tbBarcodeLock.FLocked" id="lock_inp"
										style="width: 20px;" />
									(Y表示锁定、N表示解锁)
								</td>
							</tr>
							<tr>
								<th align="right">
									解锁/锁定原因:
								</th>
								<td>
									<textarea rows="10" cols="60"
										name="tbBarcodeLock.FUnlockReason" id="reason_inp"></textarea>
									<%--									<input name="tbBarcodeLock.FUnlockReason" id="reason_inp" style="width: 200px;" />--%>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="button" value="提交" class="input"
										onclick="tosubmitLock()" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form
					action="RwlBarcodeWebServiceAction!findAllTbBarcodeLockList.action"
					method="post">
					<input type="hidden" name="pageStatus" value="${pageStatus}">
					<table class="table">
						<tr>
							<th colspan="6">
								条码锁定/解锁查询
							</th>
						</tr>
						<tr>
							<th>
								物料条码查询:
							</th>
							<td>
								<input name="tbBarcodeLock.FBarcode"
									value="${tbBarcodeLock.FBarcode}" style="width: 200px;" />

							</td>
							<th>
								整机条码查询:
							</th>
							<td>
								<input name="tbBarcodeLock.FMatCode"
									value="${tbBarcodeLock.FMatCode}" style="width: 200px;" />
								(只显示整机下被 锁定/解锁 的物料条码)
							</td>
							<td rowspan="2">
								<input type="submit" value="查询" class="input" />
							</td>
						</tr>
						<tr>
							<th>
								锁定状态:
							</th>
							<td>
								<select name="tbBarcodeLock.FLocked">
									<option>
										${tbBarcodeLock.FLocked}
									</option>
									<option>
										Y
									</option>
									<option>
										N
									</option>
								</select>
							</td>
							<th>
								隔离单号:
							</th>
							<td>
								<input name="tbBarcodeLock.lockNo"
									value="${tbBarcodeLock.lockNo}" />

								只查存在隔离单号:
								<s:if test="tbBarcodeLock.FMatName=='yes'">
									<input name="tbBarcodeLock.FMatName" value="yes"
										type="checkbox" checked="checked" />
								</s:if>
								<s:else>
									<input name="tbBarcodeLock.FMatName" value="yes"
										type="checkbox" />
								</s:else>
							</td>
						</tr>
						<tr>
							<td style="color: red;" colspan="10">
								解锁/锁定
								权限人:027405(周广杰);022032(林文良);024120(谢伟东);031274(宋琪);029591(邹文兵)
							</td>
						</tr>
					</table>
				</form>
				<br />
				<table class="table">
					<s:if test="pageStatus=='lockAndUnLock'">
						<tr>
							<td colspan="7" align="left" style="background-color: red;">
								<form action="RwlBarcodeWebServiceAction!sdLockForHw.action"
									onsubmit="return confirm('将手动锁定条码，是否确认提交?');" method="post">
									<input type="hidden" name="cpage" value="${cpage}">
									<input type="hidden" name="pageStatus" value="${pageStatus}">
									锁定条码:
									<input type="text" name="tbBarcodeLock.FBarcode" />
									锁定原因:
									<input type="text" name="tbBarcodeLock.FUnlockReason" style="width: 255px;"/>
									<input type="submit" value="手动锁定" style="height: 35px;" />
								</form>
							</td>
							<td colspan="3" align="right" style="background-color: green;">
								<form action="RwlBarcodeWebServiceAction!getUnLock.action"
									onsubmit="return confirm('将向华为申请解锁已选中的隔离单以及条码，是否确认提交?');"
									method="post">
									<input type="hidden" name="cpage" value="${cpage}">
									<input type="hidden" name="pageStatus" value="${pageStatus}">
									解锁原因:
									<input type="text" name="reason" style="width: 255px;" />
									<input type="submit" value="申请解锁" style="height: 35px;" />
								</form>
							</td>
						</tr>
					</s:if>
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							条码
						</th>
						<th>
							料号
						</th>
						<th>
							品名
						</th>
						<th>
							锁定
						</th>
						<th>
							锁定原因
						</th>
						<th>
							解锁原因
						</th>
						<th>
							操作人
						</th>
						<th>
							操作日期
						</th>
						<th>
							<s:if test="pageStatus=='lockAndUnLock'">
								<input type="checkbox" onclick="chageAllCheckBox(this)" />
							</s:if>
							隔离单号
						</th>
					</tr>
					<s:iterator value="tblockList" id="pageTbBarcodeLock"
						status="pageIndex">
						<s:if test="#pageIndex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 50px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageIndex.index+1" />
						</td>
						<td align="left" style="width: 150px;">
							${pageTbBarcodeLock.FBarcode}
						</td>
						<td align="left">
							${pageTbBarcodeLock.FMatCode}
						</td>
						<td align="left" style="width: 100px;">
							${pageTbBarcodeLock.FMatName}
						</td>
						<td align="center">
							<s:if test="pageStatus=='lockAndUnLock'">
								<s:if
									test='#pageTbBarcodeLock.lockNo==""||#pageTbBarcodeLock.lockNo==null'>
									<s:if test='#pageTbBarcodeLock.FLocked=="Y"'>
										<input type="checkbox" value="${pageTbBarcodeLock.FBarcode}"
											checked="checked" onclick="toLock(this)" />
									</s:if>
									<s:else>
										<input type="checkbox" value="${pageTbBarcodeLock.FBarcode}"
											onclick="toLock(this)" />
									</s:else>
								</s:if>
								<s:else>
									<s:if test='#pageTbBarcodeLock.FLocked=="Y"'>
										<input type="checkbox" checked="checked" disabled="disabled" />
									</s:if>
									<s:else>
										<input type="checkbox" disabled="disabled" />
									</s:else>
								</s:else>
							</s:if>
							<s:else>
								<s:if test='#pageTbBarcodeLock.FLocked=="Y"'>
									<input type="checkbox" checked="checked" disabled="disabled" />
								</s:if>
								<s:else>
									<input type="checkbox" disabled="disabled" />
								</s:else>
							</s:else>
						</td>
						<td align="left">
							${pageTbBarcodeLock.FLockReason}
						</td>
						<td align="left">
							${pageTbBarcodeLock.FUnlockReason}
						</td>
						<td align="left">
							${pageTbBarcodeLock.FLockUser}
						</td>
						<td align="left" style="width: 190px;">
							${pageTbBarcodeLock.FLockDate}
						</td>
						<td align="left">
							<s:if
								test="#pageTbBarcodeLock.LockNo!=null&&#pageTbBarcodeLock.LockNo!=''">
								<s:iterator id="lockNoSplit"
									value="#pageTbBarcodeLock.lockNo.split(';')">
									<s:if test="pageStatus=='lockAndUnLock'">
										<input name="" class="lockNoCheck"
											value="${lockNoSplit};${pageTbBarcodeLock.FBarcode};"
											type="checkbox" onclick="tosetName(this)" />
									</s:if>${lockNoSplit};
												<br />
								</s:iterator>
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="10" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function tosetName(obj) {
	var ck = $(obj).attr("checked");
	if (ck) {
		$(obj).attr("name", "lockNO");
	} else {
		$(obj).attr("name", "");
	}
}
var checkBox;
function toLock(obj) {
	checkBox = $(obj);
	var ck = $(obj).attr("checked");
	var fLocked = "N";
	if (ck) {
		fLocked = "Y";
	}
	var barcode = $(obj).val();
	$("#brocode_inp").val(barcode);
	$("#lock_inp").val(fLocked);
	$("#brocode_inp").attr("readonly", "readonly");
	$("#lock_inp").attr("readonly", "readonly");
	chageDiv('block');
	$("#reason_inp").focus();
	$(obj).attr("checked", !ck);
}

function tosubmitLock() {
$.ajax({
                cache: true,
                type: "POST",
                url:"RwlBarcodeWebServiceAction!sdUnFLock.action",
                data:$('#lockForm').serialize(),
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
					alert(data);
                    location.reload(true);
                }
            });
}

function chageAllCheckBox(obj, numId) {
	$("input[type='checkbox'][class='lockNoCheck']").each(function(i, element) {
		$(this).attr("checked", obj.checked);
		tosetName($(this));
	})
}
</script>
	</body>
</html>
