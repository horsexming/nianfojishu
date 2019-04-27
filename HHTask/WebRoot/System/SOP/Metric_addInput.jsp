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

<script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js" ></script>
<script type="text/javascript">
	var up = false;
	function saveMetric1() {
		if(!up){
			alert("请上传规格!");
			return false;
		}
		
		if (!checkForm){
			return;
		}
		$("#metricForm").submit();;
	}

	//验证form
	function checkForm() {
		var mmodel = $("#made");
		if (mmodel.val().trim().length == 0) {
			alert("请输入产地!");
			return false;
		}

		mmodel.val(mmodel.val().trim());

		var classs = $("#classs");
		if (classs.val().trim().length == 0) {
			alert("请输入板料级别!");
			return false;
		}
		classs.val(classs.val().trim());

		var cycle = $("#cycle");
		var reg = /^[1-9]\d*|0$/;
		if (cycle.val().trim().length == 0) {
			alert("请输入板料级别!");
			return false;
		} else if (!reg.test(cycle.val().trim())) {
			alert("天数必须是一个数字!");
		}
		cycle.val(cycle.val().trim());

		var mdsp = $("#mdsp");
		if (mdsp.val().trim().length == 0) {
			alert("请输入物料介绍!");
			return false;
		}
		mdsp.val(mdsp.val().trim());
		return true;
	}
	
	function selectorChange(){
		var mmodel = $("#mmodel").find("option:selected").val();
		var classs = $("#classs").val();
		if(classs == '板料'){
			if(mmodel == "报价模版1" ){
				$('#madeText').html('产地');
			} else {
				$('#madeText').html('执行标准铜厂');
			}
		}
	}
	
</script>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; " align="left">
			<div style="float: left; width: 50%" align="left">
				
			</div>
			<div style="float: left; width: 48%" align="right">
				<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
			<div>
				<form id="metricForm" action="Metric_add.action" method="post">
					<input type="hidden" name="metric.fid" value="${flow.fid}" />
					<input type="hidden" name="metric.mid" value="${metric.xh}" />
					<input type="hidden" name="product.id" value="${product.id}" />
					<table class="table">
						<tr>
							<td colspan="2" align="center">采购料详细</td>
							<td colspan="2" align="center">报价条件设置</td>
						</tr>
						<tr>
							<td align="right">物料类别:</td>
							<td><input id="mclass" name="metric.mclass" value="${product.type}" readonly="readonly" style="background-color: #cccccc; width: 240px;" /></td>
							<td align="right">报价模版:</td>
							<td><select onchange="selectorChange();" name="metric.mmodel" id="mmodel" style="width: 280px">
							</select></td>
						</tr>
						<tr>
							<td align="right">序号:</td>
							<td><input name="metric.xh" value="${metric.xh}" readonly="readonly" style="background-color: #cccccc; width: 240px;" /></td>
							<td align="right"><font id="madeText" >产地</font>:</td>
							<td><input id="made" name="metric.made" style="width: 280 px" /></td>
						</tr>
						<tr>
							<td align="right">件号:</td>
							<td><input name="metric.ph" value="${product.partsNumber}" readonly="readonly" style="background-color: #cccccc; width: 240px;" /></td>
							<td align="right">板料级别:</td>
							<td><input id="classs" name="metric.classs" style="width: 280" /></td>
						</tr>
						<tr>
							<td align="right">需求量/吨:</td>
							<td><input name="metric.xl" value="${product.quantity}" readonly="readonly" style="background-color: #cccccc; width: 240px;" /></td>
							<td align="right">报价有效期(天):</td>
							<td><input name="metric.cycle" id="cycle" style="width: 280" /></td>
						</tr>
						<tr>
							<td align="right">规格:</td>
							<td>
								<textarea name="metric.gg" readonly="readonly" rows="3" cols="34" style="background-color: #cccccc;">${product.specification}</textarea>
								<a id="guigeLink" href="File_fileUpload.action?file.fid=${flow.fid}&file.mid=${metric.xh}" onclick="javascript:up=true;" target = "_blank">设置规格要求</a>
							</td>
							<td align="right">物料介绍:</td>
							<td><textarea name="metric.mdsp" id="mdsp" rows="3" cols="34"></textarea></td>
						</tr>
						<tr>
							<td colspan="4" align="center"><input type="button" onclick="saveMetric1();" value="添加"> <input type="reset" value="清空"></td>
						</tr>
					</table>
				</form>
			</div>

			<div>
				<table class="table">
					<tr>
						<th>物料ID</th>
						<th>物料类别</th>
						<th>报价模版</th>
						<th>物料介绍</th>
						<th>规格/要求</th>
						<th>明细</th>
						<th>操作</th>
					</tr>
					<s:iterator id="mi" value="metrics">
						<tr>
							<td>${mi.xh}</td>
							<td>${mi.mclass}</td>
							<td>${mi.mmodel}</td>
							<td>${mi.mdsp}</td>
							<td>${mi.gg}</td>
							<td></td>
							<td></td>
						</tr>
					</s:iterator>
				</table>
			</div>

		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	<SCRIPT type="text/javascript">
		var selector1 = document.getElementById("mmodel");
		var productType = "${product.type}";
		var openSpecificationUpload = document.getElementById("openSpecificationUpload");
		function init() {
			if (productType == "板料") {
				selector1.innerHTML = "";
				selector1.options[0] = new Option('报价模版1', "报价模版1");
				selector1.options[1] = new Option('报价模版2', "报价模版2");
				up = true;
				$("#guigeLink").hidden();
			} else if (productType == "包装物") {
				selector1.innerHTML = "";
				selector1.options[0] = new Option('报价模版1', "报价模版1");
				up = false;
				$("#guigeLink").show();
			} else if (productType == "工装") {
				selector1.innerHTML = "";
				selector1.options[0] = new Option('报价模版1', "报价模版1");
				up = false;
				$("#guigeLink").show();
			} else if (productType == "管料") {
				selector1.innerHTML = "";
				selector1.options[0] = new Option('报价模版1', "报价模版1");
				up = true;
				$("#guigeLink").hidden();
			} else if (productType == "外购件") {
				selector1.options[0] = new Option('报价模版1', "报价模版1");
				up = false;
				$("#guigeLink").show();
			}
			
		}
		init();
	</SCRIPT>
</body>
</html>
