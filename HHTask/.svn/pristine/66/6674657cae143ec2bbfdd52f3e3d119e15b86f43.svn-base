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
var index = 1;

function addLine() {
	var newLine = '<tr align="center"><td><select  name="t.scope['
			+ index
			+ '].type" style="width:209px;"  > '
			+ '<option>手动填写</option> <option>是否合格</option> <option>有无</option> <option>OKorNo</option></select> </td> '
			+ ' <td><input name="t.scope['
			+ index
			+ '].content"/></td>'
			+ ''
			+ '<th><input name="t.scope['+index+'].zltz" id="content" /></th><td>'
			+'<input name="t.scope['			
			+ index
			+ '].jcff"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>';
	$($('#mytable tr')[$('#mytable tr').length - 2]).insertBefore(newLine);
	$('#lastTr').before(newLine);
	index++;
}

function delLine() {
	var n = $('#mytable tr').length;
	if (index == 1) {
		alert("请至少保留一项了。");
		return;
	}
	$($('#mytable tr')[$('#mytable tr').length - 3]).remove();
	index--;
}

function submitForm() {
	var attachment1 = document.getElementsByName('attachment1');
	var bool = true;

	var abc = $("#abc").val();
	if (attachment1 != null && attachment1.length > 0) {
		for ( var i = 0; i < attachment1.length; i++) {
			if (attachment1[i].value != '') {
				bool = false;
				break;
			}
		}
	}
	if ($("#partNumber").val() == "") {
		$("#zi_font").html("请选择零件号!");
		return false;
	} else if ($("#gongxuNum").val() == "") {
		$("#zi_font").html("请选择工序号!");
		return false;
	} else if ($("#xjcheckpc").val() == "") {
		$("#zi_font").html("请填写巡检频次!");
		return false;
	} else if (($("#abc").val() == '') && bool) {
		$("#zi_font").html("请上传检验图纸!");
		return false;
	} else if ($("#content").val() == "") {
		$("#zi_font").html("请填写检查内容!");
		return false;
	} else if ($("#zltz").val() == "") {
		$("#zi_font").html("请填写质量特证!");
		return false;
	} else if ($("#jcff").val() == "") {
		$("#zi_font").html("请填写检测方法!");
		return false;
	}

	document.getElementById("sub").disabled = "disabled";
	return true;
}
function numyanzheng(obj) {
	var ty1 = /^(\d+)(\.\d+)?$/;
	var bChk = ty1.test(obj.value);
	;
	if (!bChk) {
		obj.value = "";
		obj.focus();
		obj.select();
	}
}

$(function() {
	$.ajax( {
		type : "POST",
		url : "InsTemplate_findAllmarkidlist.action",
		data : {zhonglei:'巡检'},
		dataType : "json",
		success : function(data) {
			if (data == "error") {
				alert("件号信息获取异常!")
			} else if (data != null && data.length > 0) {
				$("#partNumber").empty();
				$("<option value=''>--请选择(字母请小写)--</option>").appendTo("#partNumber");
				for ( var i = 0; i < data.length; i++) {
					$(
							"<option value='" + data[i] + "'>" + data[i]
									+ "</option>").appendTo("#partNumber");
				}
				$("#partNumber").tinyselect();
			}
		}

	})
})
function changevalue(obj) {
	var markId = obj.value;
	if (markId != null && markId != '') {
		$
				.ajax( {
					type : "POST",
					url : "InsTemplate_findprocardBymarkId.action",
					data : {
						markId : markId
					},
					dataType : "json",
					success : function(data) {
						if (data != null && data != 'error') {
							var pt = data[0];
							var gongxuNumList = null;
							if (data.length == 2) {
								gongxuNumList = data[1];
							}
							$("#productType").val(pt.carStyle);
							$("#name").val(pt.proName);
							$("#banbenNumber").val(pt.banBenNumber);
							$("#ctype1").val(pt.procardStyle + "件");
							$("#gongxuNum").empty();
							$("#gongxuNum").append(
									'<option value="">--请选择--</option>');
							if (gongxuNumList != null
									&& gongxuNumList.length > 0) {
								for ( var i = 0; i < gongxuNumList.length; i++) {
									$(
											"<option value='"
													+ gongxuNumList[i] + "'>"
													+ gongxuNumList[i]
													+ "</option>").appendTo(
											"#gongxuNum");
								}
								$("#sub").removeAttr("disabled");
								$("#zi_font").html("");
							} else {
								$("#zi_font").html("该件号上所有工序都已添加过模板，无需添加!");
								document.getElementById("sub").disabled = "disabled";
							}
						} else {
							alert('工序模版获取失败!');
						}
					}

				})
	}

}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您查看检验图纸</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng">
			<div align="center">
				<font id="zi_font" color="red" size="5"></font>
				<form id="myForm" action="OsTemplate_add.action" method="post"
					onsubmit="return submitForm()" enctype="multipart/form-data">
					<input id="productType" name="t.productType" style="display: none;" />
					<select id="ctype1" name="t.ctype1"
						style="width: 209px; display: none;">
						<option>
						</option>
						<option value="外购件">
							外购件
						</option>
						<option value="原材料">
							原材料
						</option>
						<option value="自制件">
							自制件
						</option>
						<option value="总成件">
							总成件
						</option>
						<option value="组合件">
							组合件
						</option>
					</select>
					<table class="table" style="width: 80%">
						<tr>
							<th colspan="8">
								<font size="6">添加质量检验模版</font>
							</th>
						</tr>
						<tr>
							<th>
								零件号
							</th>
							<td>
								<select name="t.partNumber" id="partNumber"
									onchange="changevalue(this)">
									<option value="">
										--请选择--
									</option>
									<s:iterator value="markIdList" id="markId">
										<option value="${markId}">
											${markId}
										</option>
									</s:iterator>
								</select>
							</td>
							<th>
								产品名称
							</th>
							<td>
								<input name="t.name" value="" readonly="readonly" id="name" />
							</td>
							<th>
								工序号
							</th>
							<td>
								<SELECT name="t.gongxuNum" id="gongxuNum" style="width: 75px;"
									onchange="gettuzhi(this);getgongxuName(this)">
									<option value="">
										--请选择--
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th>
								巡检类型
							</th>
							<td align="left">
								<select name="t.xjtype" id="xjtype" style="width: 209px;" onchange="changvalue(this)">
									<option value="按时间">
										按时间
									</option>
									<option value="按次数">
										按次数
									</option>
									<option value="按批次">
										按批次
									</option>
									<option value="按占比">
										按占比
									</option>
								</select>
							</td>
							<th>
								巡检频次
							</th>
							<td id= "xjcheckpc_id">
								<input name="t.xjcheckpc" id="xjcheckpc"
									onchange="numyanzheng(this);changxjcheckpc(this)" />
									<span id="xjcheckpc_span"></span>
							</td>
							<th>工序名</th>
							<td>
								<input type="text" value="" name="t.gongxuName" readonly="readonly" id="gongxuName"/>
							</td>
						</tr>
						<tr>
							<th>
								检验图纸
							</th>
							<td colspan="3" id="td_tuzhi">
								<div style="float: left;">
									<input id="abc" type="file" name="attachment"
										onclick="qxselect()" />
								</div>
								<div style="float: left;" id="div_tuzhi">
								</div>
							</td>
							<th>
								版本
							</th>
							<td>
								<input type="text" value="" name="t.banbenNumber" id="banbenNumber" readonly="readonly"/>
							</td>
						</tr>
					</table>
					<br />
					<table class="table" id="mytable">
						<tr>
							<th colspan="4" align="center" style="font-size: 28">
								检验明细
							</th>
						</tr>
						<tr>
							<th>
								结果类型
							</th>
							<th>
								检查内容
							</th>
							<th>
								质量特征
							</th>
							<th>
								检测方法
							</th>
						</tr>
						<tr>
							<th>
								<select name="t.scope[0].type" style="width: 209px;" id="type">
									<option>
										手动填写
									</option>
									<option>
										是否合格
									</option>
									<option>
										有无
									</option>
									<option>
										OKorNo
									</option>
								</select>
							</th>
							<th>
								<input name="t.scope[0].content" id="content" />
							</th>
							<th>
								<input name="t.scope[0].zltz" id="content" />
							</th>
							<th>
								<input name="t.scope[0].jcff" id="jcff" />
								<input type="button" onclick="addLine();" value="追加">
								<input type="button" onclick="delLine();" value="删除">
							</th>
						</tr>

						<tr id="lastTr">
							<td align="center" colspan="8">
								<input type="hidden" value="xj" name="status" />
								<input type="hidden" value="巡检" name="t.zhonglei" />
								<input type="submit" value="提交" id="sub" class="input">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="8">
								常用符号: Φ ± ° ≤ ≥ ℃ < > № ⊥ ◎ ○ &nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function showsc() {
	$("#abc").removeAttr('style');
	$("#div_tuzhi").hide();
	var attachment = document.getElementsByName("attachment");
}
function hidesc() {
	$("#abc").hide();
	$("#div_tuzhi").show();
}
function gettuzhi(obj) {
	var gongxuNum = obj.value;
	var partNumber = $("#partNumber").val();
	if (gongxuNum != null && gongxuNum != "" && partNumber != null
			&& partNumber != "") {
		$
				.ajax( {
					type : "POST",
					url : "InsTemplate_gettuzhi.action",
					data : {
						markId : partNumber,
						gongxuNum : gongxuNum
					},
					dataType : "json",
					success : function(data) {
						$("#div_tuzhi").empty();
						if (data == "error") {
							alert("图纸信息获取异常!");
						} else if (data != null && data.length > 0) {
							for ( var i = 0; i < data.length; i++) {
								var ptf = data[i];
								var tzurl = "/upload/file/processTz/"
										+ ptf.month + "/" + ptf.fileName;
								var gongxuName = $("#gongxuName").val();
								if(gongxuName == ""){
									$("#gongxuName").val(ptf.processName);
								}
								$("#div_tuzhi")
										.append(
												"<a target='showjyTz'  href='"+tzurl+"'>"
														+ ptf.oldfileName
														+ "</a>"
														+ " <input type='radio' name='attachment1' value='"
														+ ptf.month
														+ "/"
														+ ptf.fileName
														+ "'> &nbsp;&nbsp;&nbsp;&nbsp;");
							}
						} else {
							$("#abc").removeAttr('style');
						}
					}
				})
	}
}
function tanchu(month, fileName) {
	document.getElementById("xiugaiIframe").src = "<%=basePath%>/upload/file/processTz/"
			+ month + "/" + fileName;
	chageDiv('block');
}
function qxselect() {
	var attachment = document.getElementsByName("attachment1");
	if (attachment != null && attachment.length > 0) {
		for ( var i = 0; i < attachment.length; i++) {
			if (attachment[i].checked == true) {
				attachment[i].checked = false;
			}
		}
	}
}



function changxjcheckpc(obj){
	var value = $("#xjtype").val();
	if(obj!=null && obj.value != "" && value == "按时间"){
		$("#xjcheckpc_span").html("表示:"+$("#xjcheckpc").val()+"小时一次");
	}
}

function getgongxuName(obj){
	var gongxuName =	$("#gongxuName").val();
	var gongxuNum = obj.value;
	var partNumber = $("#partNumber").val();
	if (gongxuNum != null && gongxuNum != "" && partNumber != null
			&& partNumber != "") {
		$
				.ajax( {
					type : "POST",
					url : "InsTemplate_getgongxuName.action",
					data : {
						markId : partNumber,
						gongxuNum : gongxuNum
					},
					dataType : "json",
					success : function(data) {
						if(data == "error"){
								alert("啊哦，出错了呢!");
						}else if(data!=null && data!=""){
							$("#gongxuName").val(data);
						}
					}
				})
	}
}
</script>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
