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
			<h2 style="font-size: 25;">修改礼品</h2>
				<font id="ziti_font" color="red"></font>
				<form action="IntegralGiftAction_updategift.action" method="post"
					onsubmit="return check()" enctype="multipart/form-data">
					<table>
						<tr>
							<th>
								礼品名称:
							</th>
							<td>
								<input type="text" name="gift.name" id="name" value="${gift.name}" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th>
								礼品数量:
							</th>
							<td>
								<input type="text" name="gift.num" id="num" value="${gift.num}"
									onkeyup="numyanzheng(this,'zhengshu')" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th>
								单件所需积分
							</th>
							<td>
								<input type="text" name="gift.xy_Integral" id="xy_Integral" value="${gift.xy_Integral}"
									onchange="numyanzheng(this)" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th>
								中奖概率:
							</th>
							<td>
								<input type="text" name="gift.zjgl" id="中奖概率"
									onchange="numyanzheng(this)" />
							</td>
						</tr>
						<tr>
							<th>
								礼品价值:
							</th>
							<td>
								<input type="text" name="gift.gift_price" id="gift_price" value="${gift.gift_price}"
									onchange="numyanzheng(this)" />
								(元)
							</td>
						</tr>
						<tr>
							<s:if test="gift.picture!=null">
								<th>重新上传礼品图片:</th>
								<td>
									<img alt="${gift.name}" src="<%=basePath%>upload/file/Gift/${gift.picture}" style="width: 100px;height: 100px; ">
									<input type="file" name="attachment">
								</td>
							</s:if>
							<s:else>
								<th>上传礼品图片:</th>
								<td>
									<input type="file" name="attachment">
								</td>
							</s:else>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" value="${gift.id}" name="gift.id"/>
								<input type="hidden" value="${gift.addusers}" name ="gift.addusers"/>
								<input type="hidden" value="${gift.addTime}" name="gift.addTime"/>
								<input type="hidden" value="${gift.isLuckdraw}" name="gift.isLuckdraw"/>
								<input type="hidden" value="${gift.zjgl}" name="gift.zjgl"/>
								<input type="submit" value="修改" class="input" id="sub" />
							</td>
						</tr>
					</table>
				</form>
					<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function check() {
	var name = $("#name").val();
	var num = $("#num").val();
	var xy_Integral = $("#xy_Integral").val();
	if (name == '') {
		$("#name").focus();
		$("#ziti_font").html("请填写礼品名称");
		return false;
	} else if (num == '') {
		$("#num").focus();
		$("#ziti_font").html("请填写礼品数量");
		return false;
	} else if (xy_Integral == '') {
		$("#xy_Integral").focus();
		$("#ziti_font").html("请填写单件所需积分");
		return false;
	}
	document.getElementById("sub").disabled = "disabled";
	return true;
}


$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="修改成功"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
</script>
	</body>
</html>
