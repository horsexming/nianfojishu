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
	<body onkeydown="enter()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 100px;">
<%--			--%>
			<div align="center" style="margin-top:20px;">
				<form action="LendNectAction!findIsLendBycardNum.action" method="post">
					<p>
						<span><font style="font-size: 20px; font-weight: bold">请刷卡：</font>
						</span>
						
						<%--cardNum	 value="${lend.cardNum}"--%>
						<input type="text" id="cardNum" name="lend.cardNum" value="${lend.cardNum}"/>
						<input type="submit" value="查询" />
						<span style="color:red;">${errorMessage}</span>
					</p>
				</form>
				<hr>
				<s:if test="listAll!=null&&!listAll.isEmpty()">
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								卡号
							</td>
							<td align="center">
								借用人
							</td>
							<td align="center">
								部门
							</td>
							<td align="center">
								件号
							</td>
							<td align="center">
								批次
							</td>
							<td align="center">
								物品名称
							</td>
							<td align="center">
								规格
							</td>
							<td align="center">
								库别
							</td>
							<td align="center">
								仓区
							</td>
							<td align="center">
								库位
							</td>
							<td align="center">
								加工件号
							</td>
							<td align="center">
								单位
							</td>
							<td align="center">
								应归还量
							</td>
							<td align="center">
								借物时间
							</td>
							<td></td>
						</tr>
						<s:iterator value="listAll" id="pageList" status="pageStatus">
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
								${pageList.cardNum}
							</td>
							<td>
								${pageList.peopleName}
							</td>
							<td>
								${pageList.dept}
							</td>
							<td>
								${pageList.goodsMarkId}
							</td>
								<td>
								${pageList.goodsLotId}
							</td>
							
							<td>
								${pageList.goodsFullName}
							</td>
							<td>
								<s:if test="pageList.format!=null">
									${pageList.format}</s:if>
									<%--<s:else>${pageList.goods.format}</s:else>--%>
							</td>
							<td>
								${pageList.storehouse}	
							</td>
							<td>
								${pageList.goodHouse}	
							</td>
							<td>
								${pageList.wareHouse}	
							</td>
							<td>
								${pageList.processPieceNum}
							</td>
							<td>
								${pageList.unit}
							</td>
							<td>
								${pageList.giveBackNum}
							</td>
							<td>
								${pageList.date}
							</td>
							<td><%--
								target="giveBack"
								--%><a
									href="LendNectAction!showOneGiveBack.action?lend.id=${pageList.id}&lend.cardNum=${lend.cardNum}"
									>归还</a>
<%--								<a--%>
<%--									href="borrow_queryBorById.action?vobo.id=${pageList.id}&vobo.cardNum=${vobo.cardNum}&vobo.opType=scrap"--%>
<%--									>报废</a>--%>
									<a
									href="LendNectAction!showOneScrap.action?lend.id=${pageList.id}&lend.cardNum=${lend.cardNum}"
									>报废</a>
	
							</td>
							</tr>
						</s:iterator>
						<tr>
						<td colspan="30" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
				</s:if>
				
				
				<div style="height: 900px">
					<iframe id="giveBack" name="giveBack" frameborder="0"
						height="800px" width="100%" scrolling="no"></iframe>
				</div>
			</div>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
var cardNumber = "";
function enter() {
	var keyCode = window.event.keyCode;
	if (keyCode < 48 || keyCode > 57) {
		if (keyCode == 13 && cardNumber.length == 10) {
			document.getElementById("cardNum").value = cardNumber;
			document.getElementById("cardNum2").value = cardNumber;
			cardNumber = "";
			return;
		} else {
			cardNumber = "";
			event.returnvalue = false;
			return;
		}
	}
	if (keyCode == 48) {
		keyCode = 0;
	} else if (keyCode == 49) {
		keyCode = 1;
	} else if (keyCode == 50) {
		keyCode = 2;
	} else if (keyCode == 51) {
		keyCode = 3;
	} else if (keyCode == 52) {
		keyCode = 4;
	} else if (keyCode == 53) {
		keyCode = 5;
	} else if (keyCode == 54) {
		keyCode = 6;
	} else if (keyCode == 55) {
		keyCode = 7;
	} else if (keyCode == 56) {
		keyCode = 8;
	} else if (keyCode == 57) {
		keyCode = 9;
	}
	cardNumber = cardNumber + keyCode;

}
</script>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
