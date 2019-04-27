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
		<div style="width: 100%;">
			<div align="center">
				<form action="QualityccAction!addQualityto.action"
					method="post">
					<table class="table" style="width: 50%;">
						<tr>
							<td style="stress: 16px;" align="center">

							</td>
						</tr>
						<tr>
							<td>
								检模编号：
								<select id="addtime" 
									style="width: 130px;">
								</select>
								<input type="hidden" name="qualityto.qualitychecktoid" id="qualitychecktoid" />
								<input type="hidden" name="qualityto.jcbh" id="jcbh" />
							</td>
						</tr>
						<tr>
							<td>
								客户名称：
								<input type="text" name="qualityto.kehu" id="kehu" />
							</td>
						</tr>
						<tr>
							<td>
								产品名称：
								<input type="text" name="qualityto.productname"
									id="productname" />
							</td>
						</tr>
						<tr>
							<td>
								产品类别：
								<input type="text" name="qualityto.leibie" id="leibie" />
							</td>
						</tr>
						<tr>
							<td>
								产品编号：
								<input type="text" name="qualityto.bianhao" id="leibie" />
							</td>
						</tr>
						<tr>
							<td>
								备 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注：
								<input type="text" name="qualityto.beizhu" />
							</td>
						</tr>

						<tr>
							<td align="center">
								<input type="submit" value="添加"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
$(function() {

})

function xiala() {
	$.ajax( {
		type : "POST",
		url : "QualityccAction!findQualitycheckto.action",
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(data) {
			$("#addtime").empty();//清空
		$("<option></option>").appendTo("#addtime");

		$(data).each(
				function() {
					$(
							"<option value='" + this.kehu + "|"
									+ this.productname + "|" + this.leibie
									+ "|" + this.id + "|" + this.addtime
									+ "'>" + this.addtime + "</option>")
							.appendTo("#addtime")

				});
	},
	error : function() {
		alert("服务器异!");
	}
	});
	$("#addtime").bind("change", function() {
		var tota = $("#addtime").val();
		var totaData = tota.split("|");
		var kehu = totaData[0];
		var productname = totaData[1];
		var leibie = totaData[2];
		var qualitychecktoid = totaData[3];
		var jcbh = totaData[4];
		$("#kehu").val(kehu);
		$("#productname").val(productname);
		$("#leibie").val(leibie);
		$("#qualitychecktoid").val(qualitychecktoid);
		$("#jcbh").val(jcbh);
	});

}

xiala();
</script>
	</body>
</html>
