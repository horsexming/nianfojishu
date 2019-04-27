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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font id="ziti_font" color="red">${errorMessage}</font>
				<form action="IntegralGiftAction_addintegralgift.action"
					method="post" onsubmit="">
					<table>
						<tr>
							<th>
								部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:
							</th>
							<td>
								<input type="text" value="${user.dept}" name="integralgift.dept"
									readonly="readonly" style="width: 200px;height: 34;"/>
							</td>
						</tr>
						<tr>
							<th>
								姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
							</th>
							<td>
								<input type="text" name="integralgift.userName" id="userName"
									readonly="readonly" value="${user.name}" style="width: 200px;height: 34;"/>
								<input type="hidden" id="userId" value="${user.id}"
									name="integralgift.userId">
								<input type="hidden" id="code" name="integralgift.userCode"
									value="${user.code}" />
							</td>
						</tr>
						<tr>
							<th>
								礼品名称:
							</th>
							<td>
								<select class="cxselect" onchange="changvalue(this)">
									<option value="">
										--请选择--
									</option>
									<s:iterator value="giftList" id="pagelist" status="pagestatus">
										<option
											value="${pagelist.id}_${pagelist.num}_${pagelist.name}_${pagelist.xy_Integral}">
											${pagelist.name}
										</option>
									</s:iterator>
								</select>
								<input type="hidden" value="${pagelist.id}"
									name="integralgift.giftId" id="giftId" />
								<input type="hidden" value="${pagelist.num}" id="num" />
								<input type="hidden" value="${gift.name}"
									name="integralgift.giftNmae" id="giftNmae" />
							</td>
						</tr>
						<tr>
							<th>
								领取数量:
							</th>
							<td>
								<input type="text" value="" name="integralgift.lqnum" id="lqnum"
									onkeyup="numyanzheng(this,'zhengshu');jisuan()" style="width: 200px;height: 34;"/>
							</td>
						</tr>
						<tr>
							<th>
								单件积分:
							</th>
							<td>
								<input type="text" style="width: 200px;height: 34;"
									name="integralgift.djIntegral" id="djIntegral"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								共需积分:
							</th>
							<td>
								<input type="text" name="integralgift.xaIntegral"
									id="xaIntegral" readonly="readonly" style="width: 200px;height: 34;"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" value="兑换" name="integralgift.type" />
								<input type="submit" value="领取" class="input">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function changvalue(obj) {
	if (obj != null && obj.value != "") {
		var value = obj.value;
		var arrays = value.split("_");
		if (arrays != null && arrays.length == 4) {
			$("#giftId").val(arrays[0]);
			$("#num").val(arrays[1]);
			$("#giftNmae").val(arrays[2]);
			$("#djIntegral").val(arrays[3]);
		}
	}
}

function jisuan() {
	var num = $("#num").val();
	var djIntegral = $("#djIntegral").val();
	var lqnum = $("#lqnum").val();
	djIntegral = parseFloat(djIntegral);
	lqnum = parseFloat(lqnum);
	var xaIntegral = 0;
	num = parseFloat(num);
	if (lqnum > 0 && djIntegral > 0) {
		if (lqnum <= num) {
			xaIntegral = lqnum * djIntegral;
			$("#xaIntegral").val(xaIntegral);
			var obj = document.getElementById("xaIntegral");
			bijiao(obj);
		} else {
			$("#ziti_font").html(
					$("#giftNmae").val() + '库存剩余量不足' + lqnum + '件。请重新输入数量');
			$("#lqnum").val('');
			$("#xaIntegral").val('');
		}
	}

}
function bijiao(obj) {
	var userId = $("#userId").val()
	var xaIntegral = $("#xaIntegral").val();
	var userName = $("#userName").val();
	var lqnum = $("#lqnum").val();
	var giftNmae = $("#giftNmae").val();
	if (obj != null && obj.value != '' && userId != '' && xaIntegral != null
			&& xaIntegral != '') {
		$.ajax( {
			type : "POST",
			url : "IntegralGiftAction_isenough.action",
			data : {
				id : userId,
				xaIntegral : xaIntegral
			},
			dataType : "json",
			success : function(data) {
				if (data == 'error') {
					alert("啊哦,出错了呢")
				} else if (!data) {
					$("#ziti_font").html(
							userName + "所剩余积分不足兑换" + lqnum + "件" + giftNmae
									+ "。请重新输入数量");
					$("#lqnum").val('');
					$("#xaIntegral").val('');
				} else {
					$("#ziti_font").html("");
				}
			}
		})
	}
}
</script>
	</body>
</html>
