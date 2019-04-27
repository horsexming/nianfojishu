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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				确认物料数量
				<br />
				<div id="wlquerenDiv">
					<table class="table" style="width: 100%; margin-top: ">
						<tr>
							<th colspan="2"  align="center">
								<a target="abc" href="<%=basePath%>/ProcardAction!showProcesstzforsc.action?id=${process.id}">图纸</a>
							</th>
						</tr>
						<tr>
							<th colspan="2" id="wlqr_msg" align="center" style="color: red">
								该工序可领数量为:${process.totalCount}已确认数量为:${process.wlqrcount}
								本次最大可确认数量为:${process.totalCount-process.wlqrcount}
							</th>
						</tr>
						<tr>
							<th align="right">
								本次确认数量:
							</th>
							<td>
								<input type="text"
									value="${process.totalCount-process.wlqrcount}"
									id="bcwlqrcount"
									onchange="numyanzheng(this,'zhengshu');wlqryanzheng(this)" />
								<input type="hidden" value="${process.wlqrcount}" name=""
									id="wlqrcount">
								<input type="hidden" value="${process.id}" name=""
									id="wlqr_processId">
								<input type="hidden" value="${process.totalCount}" name=""
									id="wlqr_totalCount">
							</td>
						</tr>
					</table>
					<input type="button" value="确认物料" onclick="wlqueren()"
						class="input" Id="sub">
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">
function wlqryanzheng(obj) {
	if (obj != null && obj.value != '') {
		var maxcount = "${process.totalCount-process.wlqrcount}";
		var count = obj.value;
		count = parseInt(count);
		if (count > maxcount) {
			obj.value = maxcount;
			alert('本次最大确认数量' + maxcount + '请不要超出此范围');
		}

	}
}

function wlqueren() {
	var bcwlqrcount = $("#bcwlqrcount").val();
	if (bcwlqrcount != '') {
		bcwlqrcount = parseInt(bcwlqrcount);
		if (bcwlqrcount > 0) {
			$("#sub").attr("disabled", "disabled");
			$.ajax( {
				type : "POST",
				url : "ProcardAction!wlqueren.action",
				data : {
					id : '${process.id}',
					num : bcwlqrcount
				},
				dataType : "json",
				success : function(data) {
					if (data == "true") {
						alert("确认物料成功!");
						parent.location.reload(true);
					}else{
						alert(data);
					}
				}
			});
		}else{
			alert("确认数量需要大于0!");
		}
	}else{
		alert("请填写确认数量!");
	}

}
</script>
	</body>
</html>
