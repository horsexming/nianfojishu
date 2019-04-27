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

function trims() {
	$("#markId").val($("#markId").val().replace(/(^\s+)|(\s+$)/g, ""));
}

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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center" style="border: 1px solid #00000;">
				<form
					action="procardTemplateGyAction_findSingleProcards.action?tag=${tag}"
					method="post">
					<!-- 只查询第一层
					<input type="hidden" name="procardTemplate.belongLayer" value="1" /> -->
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<th colspan="6">
								图纸查询
							</th>
						</tr>
						<tr>
							<th>
								业务件号:
							</th>
							<td>
								<input id="markId" name="procardTemplate.ywMarkId"
									value="${procardTemplate.ywMarkId}" />
							</td>
							<th>
								件号:
							</th>
							<td>
								<input id="markId" name="procardTemplate.markId"
									value="${procardTemplate.markId}" />
							</td>
						</tr>
						<tr>
							<th>
								名称:
							</th>
							<td>
								<input name="procardTemplate.proName"
									value="${procardTemplate.proName}" />
							</td>
							<th>
								卡片类型(Card Type):
							</th>
							<td>
								<select name="procardTemplate.procardStyle"
									style="width: 155px;">
									<option>
										${procardTemplate.procardStyle}
									</option>
									<option></option>
									<option>
										总成
									</option>
									<option>
										组合
									</option>
									<option>
										外购
									</option>
									<option>
										自制
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								产品类型(Product Type):
							</th>
							<td>
								<select name="procardTemplate.productStyle"
									style="width: 155px;">
									<option>
										${procardTemplate.productStyle}
									</option>
									<option></option>
									<option value="试制">
										试制
									</option>
									<option value="批产">
										批产
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询(Query)" class="input"
									onclick="trims()" />
								<input type="reset" value="清空(Empty)" class="input" />
							</th>
						</tr>
					</table>
				</form>

				<div id="rootTemplateDiv" style="border: 1px solid #00000;">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
								<br />
								No.
							</th>
							<th align="center">
								件号
								<br />
								Part No.
							</th>
							<th align="center">
								业务件号
								<br />
								Part No.
							</th>
							<th align="center">
								名称
								<br />
								Name
							</th>
							<th align="center">
								卡片类型
								<br />
								Card Type
							</th>
							<th align="center">
								产品类型
								<br />
								Product Type
							</th>
							<th align="center">
								编制状态
								<br />
								edit status
							</th>
							<th align="center">
								版本
							</th>
							<th align="center">
								操作
								<br />
								Operation
							</th>
						</tr>
						<s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" height="40px"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									height="40px" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								${pageProcardTem.markId}
							</td>
							<td>
								${pageProcardTem.ywMarkId}
							</td>
							<td style="width: 180px;">
								${pageProcardTem.proName}
							</td>
							<td>
								${pageProcardTem.procardStyle}
							</td>
							<td>
								${pageProcardTem.productStyle}
							</td>
							<td>
								${pageProcardTem.bzStatus}
							</td>
							<td>
								${pageProcardTem.banBenNumber}
							</td>
							<td align="center">
								<s:if test="tag=='quexian'">
									<a target="_showProcess"
										href="procardTemplateGyAction_showProcardDetailForTz.action?id=${pageProcardTem.id}&tag=${tag}">上传缺陷图纸</a>
								</s:if>
								<s:if test="tag=='view'">
									<a target="_showProcess"
									   href="procardTemplateGyAction_showProcardDetailForTz.action?id=${pageProcardTem.id}&pageStatus=${tag}">查看图纸</a>
								</s:if>
								<s:else>
									<a target="_showProcess"
										href="procardTemplateGyAction_showProcardDetailForTz.action?id=${pageProcardTem.id}">查看图纸</a>
								</s:else>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
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
	</body>
</html>
