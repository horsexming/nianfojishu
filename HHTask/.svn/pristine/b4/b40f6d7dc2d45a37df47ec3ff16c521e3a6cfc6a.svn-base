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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.sszzIndex {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	width: 99%;
}

.sszzIndex div {
	height: 150px;
	background-color: gray;
	color: #ffffff;
	margin: 5px;
	text-align: center;
	font-size: 30px;
}
</style>
	</head>
	<body>
		<div id="gongneng">
			<table class="sszzIndex">
				<tr>
					<th width="50%">
						<div onclick="gotoUrl('LogoStickerAction!findSjList.action')"
							id="sj_div" >
							<img style="margin-top: 10px;" alt=""
								src="<%=path%>/img/scytj/sj.jpg" height="70%" width="70%">
							<div style="margin-top: 0px;">
								首检
							</div>
						</div>
					</th>
					<th width="50%">
						<div onclick="gotoUrl('InsRecord_getToXjList.action')" id="xj_div"
							>
							<img style="margin-top: 10px" alt=""
								src="<%=path%>/img/scytj/xj.jpg" height="70%" width="70%">
							<div style="margin-top: 0px;">
								巡检
							</div>
						</div>
					</th>
				</tr>
				<tr>
					<th width="50%">
						<div
							onclick="gotoUrl('WaigouwaiweiPlanAction!findDqrDelivery.action')"
							id="qs_div" >
							<img style="margin-top: 10px;" alt=""
								src="<%=path%>/img/scytj/qs.jpg" height="70%" width="70%">
							<div style="margin-top: 0px;">
								签收
							</div>
						</div>
					</th>
					<th width="50%">
						<div
							onclick="gotoUrl('WaigouwaiweiPlanAction!findDjyDelivery.action')"
							id="wj_div" >
							<img style="margin-top: 10px;" alt=""
								src="<%=path%>/img/scytj/wj.jpg" height="70%" width="70%">
							<div style="margin-top: 0px;">
								外检
							</div>
						</div>
					</th>
				</tr>
				<tr>
					<th width="50%">
						<div
							onclick="gotoUrl('WaigouwaiweiPlanAction!findAllDrk.action?pageStatus=screanRuku')"
							id="rk_div" >
							<img style="margin-top: 10px;" alt=""
								src="<%=path%>/img/scytj/rk.jpg" height="70%" width="70%">
							<div style="margin-top: 0px;">
								入库
							</div>
						</div>
					</th>
					<th width="50%">
						<div
							onclick="gotoUrl('WaigouwaiweiPlanAction!findDqrDeliRuGui.action')"
							id="cr_div" >
							<img style="margin-top: 10px;" alt=""
								src="<%=path%>/img/scytj/tm.png" height="70%" width="70%">
							<div style="margin-top: 0px;">
								存入
							</div>
						</div>
					</th>
				</tr>

				<tr>
					<th width="50%">
						<div onclick="gotoUrl('ProcardAction!PersonLingLiao.action')"
							id="ll_div" >
							<img style="margin-top: 10px;" alt=""
								src="<%=path%>/img/scytj/rk.jpg" height="70%" width="70%">
							<div style="margin-top: 0px;">
								领料
							</div>
						</div>
					</th>
					<th width="50%">
						<div
							onclick="gotoUrl('ProcardAction!findNeedRukuPro.action?pageStatus=phone')"
							id="cr_div" >
							<img style="margin-top: 10px;" alt=""
								src="<%=path%>/img/scytj/rk.jpg" height="70%" width="70%">
							<div style="margin-top: 0px;">
								总成入库申请(无卡)
							</div>
						</div>
					</th>
				</tr>

			</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$.ajax( {
		type : "POST",
		url : "WareHouseAuth_getshow.action",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data != null && data.length > 0) {
				for ( var i = 0; i < data.length; i++) {
					var str = data[i];
					var obj = document.getElementById(str + "_div");
					if (obj != null) {
						$(obj).show();
					}
				}
			}
		}
	})
})
function gotoUrl(url) {
	if (url != "") {
		location.href = url;
	}
}
</script>
	</body>
</html>
