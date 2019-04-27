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
		<OBJECT ID="jatoolsPrinter"
			CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255"
			codebase="jatoolsPrinter.cab#version=8,6,0,0"></OBJECT>
		<script>
function doPrint() {
	myDoc = {
		documents : document,
		/*
		 要打印的div 对象在本文档中，控件将从本文档中的 id 为 'page1' 的div对象，
		 作为首页打印id 为'page2'的作为第二页打印            */
		copyrights : '杰创软件拥有版权  www.jatools.com' // 版权声明,必须   
	};
	jatoolsPrinter.print(myDoc, false); // 直接打印，不弹出打印机设置对话框 
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;" align="center">
			<div align="center" id="showdiv">
				<s:if test="hadChecked=='no'">
					<h3>
						<font color="red">* 您当前还未锁定件号请先锁定件号</font>
					</h3>
					<table>
						<tr>
							<td align="center">
								<select name="airProduct.markId" id="checkMarkid">
									<option></option>
									<s:iterator value="markIds" var="pageMarkId">
										<option>
											<s:property value="#pageMarkId" />
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="submit" value="锁定" onclick="suoding()">
							</td>
						</tr>
					</table>
				</s:if>
				<s:else>
					<!-- 生成条形码 -->
					<s:if test="airProduct.isNeedOtherContext=='yes'">
						<h3>
							<font color="red">*请先扫描二维码，然后选择件号，最后点击生成条形码</font>
						</h3>
						<div>
							扫描二维码:
							<input style="width: 400px; height: 25px" id="otherContext">
						</div>
						<input type="hidden" id="isNeedOtherContext" value="yes" />
					</s:if>
					<s:else>
						<input type="hidden" id="isNeedOtherContext" value="no" />
					</s:else>
					<div id="markids">
						<input id="markid" value="${airMarkId}" readonly="readonly">
						<input type="button" value="更换件号" onclick="changeMarkId()">
					</div>
					<br />
					<button id="btn" onclick="showcodeimg()">
						生成条形码
					</button>
					<div id="showStatus" style="font-size: 20px; font-weight: bolder;"></div>
			</div>
			<br />
			<br />
			<div id="page1">
				<img id="showcode" alt="" src=""
					style="display: none; width: 200px; height: 60px;">
			</div>
			<%--<button id="printimg" onclick="doPrint()">
				打印
			</button>
		--%>
			</s:else>

		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
//$(document).ready(function() {
//$.ajax( {
//type : "POST",
//url : "airtightLogAction_getProductMarkId.action",
//dataType : "json",
//success : function(data) {
//		$(data).each(function(){
//$("<option value='" + this + "'>" + this
//					+ "</option>").appendTo("#markid");
//});
//	}
//});
//});
function validate() {
	if ($("#checkMarkid").val() == null || $("#checkMarkid").val() == "") {
		alert("请选择件号");
		return false;
	} else {
		return true;
	}
}
function suoding() {
	if ($("#checkMarkid").val() == null || $("#checkMarkid").val() == "") {
		alert("请选择件号");
		return false;
	} else {
		window.location.href = "airtightLogAction_checkMarkId.action?airProduct.markId="
				+ $("#checkMarkid").val();
	}
}
function showcodeimg() {
	$("#btn").hide();
	$("#btn").attr("disabled", "disabled");
	var otherContext = "";
	if ($("#isNeedOtherContext").val() == "yes") {
		if ($("#otherContext").val() == null || $("#otherContext").val() == "") {
			alert("请先扫描二维码");
			return false;
		}
		otherContext = $("#otherContext").val();
	}
	if ($("#markid").val() == null || $("#markid").val() == "") {
		alert("请选择件号");
		return false;
	}
	$("#showStatus").html("<font color='blue'>正在获取气密检测数据,请开始气密测试!</font>");
	$
			.ajax( {
				type : "POST",
				url : "airtightLogAction_createBarCodeUrl.action",
				data : {
					"airtightLog.otherContext" : otherContext,
					"airtightLog.markId" : $("#markid").val()
				},
				dataType : "json",
				success : function(data) {
					if (data == "barcode.action?msg=error") {
						$("#showStatus").html(
								"<font color='red'>数据连接异常!请稍候重试</font>");
						$("#showcode").hide();
						$("#printimg").hide();
					} else if (data == "barcode.action?msg=had") {
						$("#showStatus").html(
								"<font color='red'>该件号的的外部标识已存在!</font>");
						$("#showcode").hide();
						$("#printimg").hide();
						return;
					} else if (data != "barcode.action?msg=noOk") {
						$("#showcode").attr("src", data);
						$("#showcode").show();
						$("#page1").show();
						$("#showStatus")
								.html(
										"<font color='green'>合格<br/>正在自动打印中,请等待....</font>");
						doPrint();
						setTimeout("location.reload(true);", 3000);
						$("#markids").hide();
						$("#btn").hide();
					} else {
						$("#showStatus").html("<font color='red'>不合格</font>");
						$("#showcode").hide();
						$("#printimg").hide();
					}
				}
			});
}
function changeMarkId() {
	window.location.href = "airtightLogAction_changeMarkId.action";
}
</script>
</html>
