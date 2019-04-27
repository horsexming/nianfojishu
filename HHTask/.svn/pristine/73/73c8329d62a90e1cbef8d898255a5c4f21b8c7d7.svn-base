<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
							<span id="title">您正在付款申请进行操作</span>
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
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<s:if test="test!=null&&test!=''">
					<h3>
						借款单打印
					</h3>
				</s:if>
				<s:else>
					<h3>
						付款申请管理
					</h3>
				</s:else>
				<s:if test="test!=null&&test!=''">
					<form action="paymentVoucherAction_findPaymentVoucher.action?test=<s:property value="test"/>"
						method="post">
				</s:if>
				<s:else>
					<form action="paymentVoucherAction_findPaymentVoucher.action"
						method="post">
				</s:else>
				<table class="table">
					<tr>
						<td align="center">
							编号：
							<input type="text" name="paymentVoucher.number" />
						</td>
						<td align="center">
							创建时间：
							<input type="text" class="Wdate"
								name="paymentVoucher.contractdate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td align="center">
							收款单位：
							<input type="text" name="paymentVoucher.unitname" />
						</td>
						<td align="center">
							类别：
							<select name="paymentVoucher.category">
								<option value="">
									--请选择类别--
								</option>
								<option value="总务性采购">
									总务性采购
								</option>
								<option value="原材料采购">
									原材料采购
								</option>
								<option value="工程设备类采购">
									工程设备类采购
								</option>
								<option value="其他">
									其他
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询" class="input" />
							<s:if test="test!=null&&test!=''">

							</s:if>
							<s:else>
								<input type="button" style="width: 100px; height: 40px;"
									value="添加" class="input" onclick="add()" />
							</s:else>

						</td>
					</tr>
				</table>

				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							编号
						</td>
						<td align="center">
							合同号
						</td>
						<td align="center">
							收款单位
						</td>
						<td align="center">
							关联客户
						</td>
						<td align="center">
							付款金额
						</td>
						<td align="center">
							类别
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							创建时间
						</td>
						<td align="center">
							操作
						</td>
						<td></td>
					</tr>
					<s:iterator value="maps" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pageList.number}
						</td>
						<td>
							${pageList.contractnum}
						</td>
						<td>
							${pageList.unitname }
						</td>
						<td>
							${pageList.relationclient}
						</td>
						<td>
							${pageList.voucherMoney}
						</td>
						<td>
							${pageList.category}
						</td>
						<td>
							${pageList.approvalStatus}
						</td>
						<td>
							${pageList.contractdate}
						</td>
						<td>
							<s:if test="test!=null&&test!=''">
								<a
									href="paymentVoucherAction_salPaymentDetail.action?paymentVoucher.id=${pageList.id}">打印/</a>
								<a
									href="paymentVoucherAction_findPaymentDetail1.action?paymentVoucher.id=${pageList.id}">预览</a>
							</s:if>
							<s:else>
							<a onclick="add1('${pageList.id}')">添加明细</a>
								<a onclick="update(${pageList.id })">修改/</a>
								<s:if test="#pageList.approvalStatus=='待审核'">
									<a
										href="paymentVoucherAction_updatePaymentVouche1.action?paymentVoucher.id=${pageList.id}">提交申请</a>/
						</s:if>
								<a onclick="return window.confirm('此操作关联明细,确定删除?')"
									href="paymentVoucherAction_delPaymentVoucher.action?paymentVoucher.id=${pageList.id }">删除</a>
							</s:else>
						</td>
					</s:iterator>
					</tr>
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
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>

				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(document).ready(function(){ 
		　　var test="${successMessage}";
				if(test!=""){
					alert(test);
				}
		}); 
		function add1(id) {
		var url = "<%=request.getContextPath()%>/System/payment/addPaymentDetail.jsp?paymentid1="+id;
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	function add() {
		//var url = "paymentVoucherAction_addPaymentVoucher.action";
		var url = "<%=request.getContextPath()%>/System/payment/addPaymentVoucher.jsp";
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	function update(obj){
		var url = "<%=request.getContextPath()%>/paymentVoucherAction_findPaymentVoucherById.action?paymentVoucher.id="+obj;
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}

</script>
	</body>
</html>
