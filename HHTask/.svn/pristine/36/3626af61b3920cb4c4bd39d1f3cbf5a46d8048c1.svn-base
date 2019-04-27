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
		<script type="text/javascript">
$(function() {
	var pageStatus = "${pageStatus}";
	if (pageStatus == "zjl" || pageStatus == "sc") {

		var size = parseFloat("${size}");
		var manage = $("#manage");
		var laborcostLabel = $("#laborcostLabel");
		var fenpeiRateLabel = $("#fenpeiRateLabel");
		if (pageStatus != "" && size > 0) {
			for ( var i = 0; i < size; i++) {
				$("#manage" + i).show();
				//市场,隐藏可调系数
				if (pageStatus == "sc") {
					$("#fenpeiRateLabel" + i).hide();
				}
			}
		}
	}
});

function update(obj, index, id, name, oldValue) {
	var laborcost = $("#laborcost" + index).val();
	var fenpeiRate = $("#fenpeiRate" + index).val();
	if (laborcost == "") {
		laborcost = 0;
	}
	if (fenpeiRate == "") {
		fenpeiRate = 0;
	}
	//判断值是否相同
	if (parseFloat(obj.value) == parseFloat(oldValue)) {
		$("#showMessage").html("");
		return false;
	}

	if (fenpeiRate > 1) {
		$("#showMessage").html("可调系数不能大于1,请重新填写!");
		$("#fenpeiRate" + index).select();
		return false;
	}

	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updatelf.action",
		data : {
			id : id,
			laborcost : laborcost,
			fenpeiRate : fenpeiRate
		},
		dataType : "json",
		success : function(msg) {
			if (msg.success == true) {
				$("#showMessage").html(name + "更改成功");
			} else {
				$("#showMessage").html(name + "更改失败,请重新更改!");

			}
		}
	});
}
</script>

	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 90%; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcardDetail" src="" marginwidth="0"
						marginheight="0" hspace="0" vspace="0" frameborder="0"
						scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng">
			<div align="center" style="border: 1px solid #00000;">

				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								层次
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								规格
							</th>
							
							<th align="center">
								卡片类型
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								图号
							</th>
							<th align="center">
								单位
							</th>
							<th align="center">
								单位用量
							</th>
							<th align="center">
								版本
							</th>
							<th align="center">
								单张重量
							</th>
							<th align="center">
								表处
							</th>
							<th align="center">
								材料类别
							</th>
							<th align="center">
								生产类型
							</th>
						</tr>
						<s:set name="myindex" value="0" ></s:set>
						<s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1+#myindex" />
							</td>
							<td align="left">
								<s:iterator begin="1" end="#pageProcardTem.belongLayer" step="1">
							<font size="1"><b>.</b></font>
								</s:iterator><s:property value="#pageProcardTem.belongLayer"/>
							</td>
							<td align="center">
								${pageProcardTem.proName}
							</td>
							<td align="center">
								${pageProcardTem.specification}
							</td>
							<td align="center">
								${pageProcardTem.procardStyle}
							</td>
							<td align="center">
								<a href="javascript:;"
									onclick="toshowPro('${pageProcardTem.id}')">
									${pageProcardTem.markId} </a>
							</td>
							<td align="center">
								${pageProcardTem.tuhao}
							</td>
							<td align="center">
								${pageProcardTem.unit}
							</td>
							<td align="center">
								<s:if test="pageProcardTem.procardStyle=='外购'">
									${pageProcardTem.quanzi2/pageProcardTem.quanzi1}
								</s:if>
								<s:else>
									${pageProcardTem.corrCount}
								</s:else>
							</td>
							<td align="center">
								${pageProcardTem.banBenNumber}
							</td>
							<td align="center">
							</td>
							<td align="center">
								${pageProcardTem.biaochu}
							</td>
							<td align="center">
								${pageProcardTem.caizhi}
							</td>
							<td align="center">
								${pageProcardTem.productStyle}
							</td>
							</tr>
							<!-- 原材料显示 -->
							<s:if test="#pageProcardTem.procardStyle=='自制'||#pageProcardTem.isZhHasYcl()">
							<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							<td>
								<s:property value="#pageindex.index+2+#myindex" />
								<s:set name="myindex" value="#myindex+1"></s:set>
							</td>
							<td align="left" width="9%;">
							
								<s:iterator begin="0" end="#pageProcardTem.belongLayer" step="1">
							<font size="1"><b>.</b></font>
								</s:iterator><s:property value="#pageProcardTem.belongLayer+1"/>
							</td>
							<td align="center">
								${pageProcardTem.yuanName}
							</td>
							<td align="center">
								${pageProcardTem.specification}
							</td>
							<td align="center">
								原材料
							</td>
							<td align="center">
									${pageProcardTem.trademark} </a>
							</td>
							<td align="center">
								${pageProcardTem.tuhao}
							</td>
							<td align="center">
								${pageProcardTem.yuanUnit}
							</td>
							<td align="center">
									${pageProcardTem.quanzi2/pageProcardTem.quanzi1}
							</td>
							<td align="center">
							</td>
							<td align="center">
							${pageProcardTem.bili}
							</td>
							<td align="center">
								${pageProcardTem.biaochu}
							</td>
							<td align="center">
								${pageProcardTem.caizhi}
							</td>
							<td align="center">
								${pageProcardTem.productStyle}
							</td>
							</tr>
							</s:if>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="14" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="14" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toshowPro(id) {
	$("#showProcardDetail").attr("src",
			"ProcardTemplateAction!findCardTemForShow.action?id=" + id);
	chageDiv('block');
}
function showDaoRuDiv() {
	$("#showProcardDetail").attr("src",
			"procardTemplateGyAction_toDaoRuBom.action");
	chageDiv('block');
}
function exportExcel(id) {
	window.location.href = "procardTemplateGyAction_daoChuBom.action?id=" + id;
}
function showgongxu(parth, id) {
	window.open(parth + "System/SOP/produce/procard_showgongxu.jsp?id=" + id);
}
</script>
	</body>
</html>
