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
				<form action="">
					<table>
						<tr>
							<th>
								兑换码:
							</th>
							<td>
								<input type="text" name="integralgift.dhnum" id="dhnum"
									onchange="getgift(this)" />
							</td>
						</tr>
						<tr>
							<th>
								礼品名称:
							</th>
							<td id="td_igiftName">

							</td>
						</tr>
						<tr>
							<th>
								所需积分:
							</th>
							<td id="td_igiftjf">

							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="兑换" id="sub" disabled="disabled" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function getgift(obj) {
	if (obj != null && obj.value != '') {
		$.ajax( {
			type : "POST",
			url : "IntegralGiftAction_dhchoujiang.action",
			data : {
				dhnum:obj.value
			},
			dataType : "json",
			success : function(data) {
				if(data == "error"){
					alert("啊哦，出错了!");
				}else if(data == null){
					alert("兑换码错误，或已失效")
				}else{
					var gift = data;
					$("#td_igiftName").append(gift.name+'<input type="hidden" value='+gift.name+' name="integralgift.giftNmae">');
					$("#td_igiftjf").append(gift.xy_Integral+'<input type="hidden" value='+gift.xy_Integral+' name="integralgift.djIntegral">');
					$("#sub").removeAttr("disabled");
				}
			}
		})
	}
}
</script>
	</body>
</html>
