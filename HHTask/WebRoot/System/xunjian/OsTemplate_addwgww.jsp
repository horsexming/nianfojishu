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
var tag = '${param.tag}'
var jyzhonglei;
$(function() {
	if (tag == 'wg') {
		jyzhonglei = "外购件检验";
		$("#span_zhonglei").html('外购件/总成');
	} else if (tag == 'ww') {
		jyzhonglei = "外委检验";
		$("#span_zhonglei").html('外委检验');
	} else if (tag == 'fl') {
		jyzhonglei = "辅料";
		$("#span_zhonglei").html('辅料检验');
	}
	$("#zhonglei").val(jyzhonglei);

})
function check() {
	var ctype = document.getElementById("ctype");
	var material = document.getElementById("material");
	var serialNumber = document.getElementById("serialNumber");
	var content = document.getElementById("content");
	var zltz = document.getElementById("zltz");
	var jcff = document.getElementById("jcff");
	if (ctype != null && ctype.value == "") {
		alert("请选择类型");
		return false;
	} else if (zltz != null && zltz.value == "") {
		alert("请填写质量特征");
		return false;
	} else if (jcff != null && jcff.value == "") {
		alert("请填写检查方法");
		return false;
	}
	document.getElementById("submit").disabled = "disabled";
	return true;

}

var index = 1;

function addLine() {
	var newLine = '<tr align="center"><th><select  name="t.scope['
			+ index
			+ '].type" style="width:209px;"  > '
			+ '<option>手动填写</option> <option>是否合格</option> <option>有无</option> <option>OKorNo</option></select> </th> '
			+ ' <th><input name="t.scope['
			+ index
			+ '].content"/></th>'
			+ '<th><input name="t.scope['
			+ index
			+ '].zltz"  /></th>'
			+ '<th><input name="t.scope['
			+ index
			+ '].jcff"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th></tr>';
	$($('#mytable tr')[$('#mytable tr').length - 2]).insertBefore(newLine);
	$('#lastTr').before(newLine);
	index++;
}

function delLine() {
	var n = $('#mytable tr').length;
	if (index == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
	$($('#mytable tr')[$('#mytable tr').length - 3]).remove();
	index--;
}
<%--$(function() {--%>
<%--	$.ajax( {--%>
<%--		type : "POST",--%>
<%--		url : "InsTemplate_findAllmarkidlist.action",--%>
<%--		data : {--%>
<%--			zhonglei : jyzhonglei--%>
<%--		},--%>
<%--		dataType : "json",--%>
<%--		success : function(data) {--%>
<%--			if (data == "error") {--%>
<%--				//alert("啊哦,出错了哦!")--%>
<%--	} else if (data != null && data.length > 0) {--%>
<%--		$("#partNumber").empty();--%>
<%--		$("<option value=''>--请选择--</option>").appendTo("#partNumber");--%>
<%--		for ( var i = 0; i < data.length; i++) {--%>
<%--			$("<option value='" + data[i] + "'>" + data[i] + "</option>")--%>
<%--					.appendTo("#partNumber");--%>
<%--		}--%>
<%--		$("#partNumber").tinyselect();--%>
<%--	}--%>
<%--}--%>
<%----%>
<%--	})--%>
<%--})--%>


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
							$("#ctype1").val(pt.procardStyle + "件");
							$("#banbenNumber").val(pt.banBenNumber);
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
								gettuzhi1();
							}

						} else {
							alert('啊哦,出错了呢!');
						}
					}

				})
				
	}

}
function findbanben(obj) {
	var markId = obj.value;
if(markId!=null && markId!=""){
		$.ajax({
					type : "POST",
					url : "OsTemplate_findbanbenBymarkId.action",
					data : {
						markIds : markId,
					},
					dataType : "json",
					success : function(data) {
						if(data!=null && data.length>0){
							$("#banben_td").html('<select name="t.banbenNumber" id="banbenNumber" ></select>');
							$(data).each(
								function(){
									if(data.banBenNumber == null){
										$("#banbenNumber").append("<option value=''></option>");	
									}else{
										$("#banbenNumber").append("<option value="+data.banBenNumber+">"+data.banBenNumber+"</option>");
									}
										
								}
							)
						}
					}
		})
	}
	
}

function gettuzhi1() {
	var partNumber = $("#partNumber").val();
	if ( partNumber != null
			&& partNumber != "") {
		$
				.ajax( {
					type : "POST",
					url : "InsTemplate_gettuzhi1.action",
					data : {
						markId : partNumber,
					},
					dataType : "json",
					success : function(data) {
						$("#div_tuzhi").empty();
						if (data == "error") {
							alert("啊哦，出错了呢!");
						} else if (data != null && data.length > 0) {
							for ( var i = 0; i < data.length; i++) {
								var ptf = data[i];
								$("#div_tuzhi")
										.append(
												"<a href='javascript:;' onclick=tanchu('"
														+ ptf.month
														+ "','"
														+ ptf.fileName
														+ "')>"
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
							alert("啊哦，出错了呢!");
						} else if (data != null && data.length > 0) {
							for ( var i = 0; i < data.length; i++) {
								var ptf = data[i];
								$("#div_tuzhi")
										.append(
												"<a href='javascript:;' onclick=tanchu('"
														+ ptf.month
														+ "','"
														+ ptf.fileName
														+ "')>"
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
function getgongxuName(obj){
	var gongxuName =	$("#gongxuName").val();
	if(gongxuName == ""){
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
}
function tanchu(month, fileName) {
	document.getElementById("xiugaiIframe").src = "<%=basePath%>/upload/file/processTz/"
			+ month + "/" + fileName;
	chageDiv('block');
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


		<div align="center">
			<form id="myForm" action="OsTemplate_add.action" method="post"
				enctype="multipart/form-data" onsubmit="return check();">
				<input type="hidden" name="t.dept" value="${companyInfo.shortName}" />

				<table id="mytable1" class="table" style="width: 90%">
					<tr>
						<th colspan="4">
							<font size="6">添加<span id="span_zhonglei"></span>检验模版</font>
						</th>
					</tr>
					<tr>
						<th>
							件号
						</th>
						<td>
							<input type="text" value="" name="t.partNumber" onchange="changevalue(this)"/>
<%--							<select name="t.partNumber" id="partNumber"--%>
<%--								onchange="changevalue(this);findbanben(this)">--%>
<%--								<option value="">--%>
<%--									--请选择----%>
<%--								</option>--%>
<%--								<s:iterator value="markIdList" id="markId">--%>
<%--									<option value="${markId}">--%>
<%--										${markId}--%>
<%--									</option>--%>
<%--								</s:iterator>--%>
<%--							</select>--%>

						</td>
						<th>
							名称
						</th>
						<td>
							<input name="t.name" value="" id="name" />

						</td>
					</tr>
					<tr>
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
						<th>
							工序名
						</th>
						<td>
							<input type="text" value="" name="t.gongxuName"
								readonly="readonly" id="gongxuName" />
						</td>
					</tr>
					<tr>
						<th>
							材料
						</th>
						<td align="left">
							<input id="material" name="t.material" />
						</td>
						<th>
							检验规程编号
						</th>
						<td>
							<input id="serialNumber" name="t.serialNumber" />
						</td>
					</tr>
					<tr>
						<th>
							车型
						</th>
						<td>
							<input id="cmodel" name="t.cmodel" />
						</td>
						<th>
							产品类型
						</th>
						<td>
							<select id="ctype" name="t.ctype" style="width: 150px">
								<option value="00">
								</option>
								<option>
									端盖
								</option>
								<option>
									隔盘
								</option>
								<option>
									内管
								</option>
								<option>
									外管
								</option>
								<option>
									吊钩
								</option>
								<option>
									法兰
								</option>
								<option>
									护板
								</option>
								<option>
									岩棉
								</option>
								<option>
									筒体
								</option>
								<option>
									螺帽
								</option>
								<option>
									其它
								</option>
								<option>
									螺纹嘴
								</option>
								<option>
									净化器
								</option>
								<option>
									波纹管
								</option>
								<option>
									玻璃纤维
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							检验图纸
						</th>
						<td colspan="1" id="td_tuzhi">
							<div style="float: left;">
								<input id="abc" type="file" name="attachment"
									onclick="qxselect()" />
							</div>
							<div style="float: left;" id="div_tuzhi">
							</div>
						</td>
						<th>
							版本号
						</th>
						<td id ="banben_td">
							<input id="banbenNumber" name="t.banbenNumber" />
						</td>
						<%--							<th>--%>
						<%--								生产类型--%>
						<%--							</th>--%>
						<%--							<td>--%>
						<%--								<select id="ctype1" name="t.ctype1">--%>
						<%--									<option>--%>
						<%--									</option>--%>
						<%--									<option>--%>
						<%--										外购件--%>
						<%--									</option>--%>
						<%--									<option>--%>
						<%--										原材料--%>
						<%--									</option>--%>
						<%--									<option>--%>
						<%--										自制件--%>
						<%--									</option>--%>
						<%--									<option>--%>
						<%--										总成件--%>
						<%--									</option>--%>
						<%--									<option>--%>
						<%--										组合件--%>
						<%--									</option>--%>
						<%--								</select>--%>
						<%--							</td>--%>
					</tr>
					<tr>
						<td>
							检验标准
						</td>
						<td>
							<select name="t.xjbzId" id="xjbzId">
								<option></option>
							</select>
						</td>
					</tr>
				</table>


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
							检查条目
						</th>
						<th>
							质量特征
						</th>
						<th>
							检查方法
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
							<input name="t.scope[0].zltz" id="zltz" />
						</th>
						<th>
							<input name="t.scope[0].jcff" id="jcff" />
							<input type="button" onclick="addLine();" value="追加">
							<input type="button" onclick="delLine();" value="删除">
						</th>
					</tr>

					<tr id="lastTr">
						<td align="center" colspan="8">
							<input type="hidden" value="" name="t.zhonglei" id="zhonglei" />
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
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">

</SCRIPT>
	</body>
</html>
