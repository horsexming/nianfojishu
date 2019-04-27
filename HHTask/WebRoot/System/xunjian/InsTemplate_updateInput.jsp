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

var partNumber ='${t.partNumber}' ;
$(function(){
		$.ajax({
			type : "POST",
			url : "OsTemplate_findAllmarkelist.action",
			data : {},
			dataType : "json",
			success : function(data) {
				$("#partNumber").empty();
				if(data!=null && data!='error' && data.length>0){
						for(var i=0;i<data.length ; i++){
							if(partNumber == data[i]){
								$(
											"<option value='" + data[i] + "' selected=selected>"
													+ data[i] + "</option>")
											.appendTo("#partNumber");
						}else{
								$(
											"<option value='" + data[i] + "'>"
													+ data[i] + "</option>")
											.appendTo("#partNumber");
							}
							
						}
					$("#partNumber").tinyselect();
			}else{
					alert('啊哦，出错了好像!');
				}
				
			}
		})
});
		




function submitForm() {
	$.ajax( {
		type : "POST",
		url : "OsTemplate_updateOsTemplatexj.action",
		dataType : "json",
		data : {$('#myForm').serialize()},
		success : function(msg) {
			if (msg=="true") {
				alert("修改成功");
				window.location.reload();
			}
		}
	});
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				
			</div>
			
			<div align="center">
				<font size="5" color="red">${errorMessage}</font>
				<form id="myForm"  method="post" action="OsTemplate_updateOsTemplatexj.action" enctype="multipart/form-data">
					<input type="hidden" name="t.id" value="${t.id}" />
					<input id="productType" name="t.productType" style="display: none;" value="${t.productType}"/>
					<select id="ctype1" name="t.ctype1"
						style="width: 209px; display: none;">
						<option value="${t.ctype1}">
							${t.ctype1}
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
								<font size="6">修改质量检验模版</font>
							</th>
						</tr>
						<tr>
							<th>
								零件号
							</th>
							<td>
								<input type="text" value="${t.partNumber}" name="t.partNumber" readonly="readonly"/>
							</td>
							<th>
								产品名称
							</th>
							<td>
								<input name="t.name" value="${t.name}" readonly="readonly" id="name" />
							</td>
							<th>
								工序号
							</th>
							<td>
								<input name="t.gongxuNum" value="${t.gongxuNum}" readonly="readonly" />
							</td>
						</tr>
						<tr >
							<th>
								巡检类型
							</th>
							<td align="left">
								<select name="t.xjtype" style="width: 209px;">
									<option value="${t.xjtype}">${t.xjtype}</option>
									<option value="按时间" >
										按时间
									</option>
									<option value="按次数">
										按次数
									</option>
								</select>
							</td>
							<th>
								巡检频次
							</th>
							<td>
								<input name="t.xjcheckpc" value="${t.xjcheckpc}"/>
							</td>
							<th>
								检验图纸
							</th>
							<td>
								<div style="float: left;">
									<input id="abc" type="file" name="attachment"
										onclick="qxselect()" />
								</div>
								<div style="float: left;" id="div_tuzhi">
								</div>
<%--								<a--%>
<%--									href="DownAction.action?fileName=${t.filename}&directory=upload/file/OsTemplate/">下载</a>--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 重新上传--%>
<%--								<input type="hidden" value="${t.filename}" name="t.filename"/>--%>
<%--								<input type="file" name="attachment" />--%>
							</td>
						</tr>
						<tr>
							<th>
								版本
							</th>
							<td>
								<input type="text" name="t.banbenNumber" value="${t.banbenNumber}"/>
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<br/>
					<table class="table" id="mytable" >
						<tr>
							<th colspan="4" align="center" style="font-size: 28">检验明细</th>
						</tr>
						<tr>
							<th>
								结果类型
							</th>
							<th>
								检查内容
							</th>
							<th>
								检测方法
							</th>
						</tr>
					<s:iterator value="sc" id="scope" status="pagestatus">
						<tr id="tr_${pagestatus.index}" align="center">
							<th>
								<select name="t.scope[${pagestatus.index}].type" style="width: 209px;" id ="type_${pagestatus.index}">
									<option value="${scope.type}">${scope.type}</option>
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
								<input name="t.scope[${pagestatus.index}].content" value="${scope.content}" id="content_${scope.content}"/>
							</th>
							<th>
								<input name="t.scope[${pagestatus.index}].jcff" id="jcff_${pagestatus.index}" value="${scope.jcff}"/>
								<input type="button" onclick="addLine()" value="追加">
								<input type="button" onclick="delLine('tr_${pagestatus.index}')" value="删除">
							</th>
						</tr>
			</s:iterator>
						<tr id="lastTr">
							<td align="center" colspan="8">
								<input type="hidden" value="${status}" name="status" />
								<input type="hidden" value="${t.zhonglei}" name="t.zhonglei" />
								<input type="hidden" value="${t.createDate}" name="t.createDate"/>
								<input type="hidden" value="${t.username}" name="t.username"/>
<%--								<input type="button" value="提交" id="sub" class="input" onclick="submitForm()">--%>
								<input type="submit" value="修改" class="input"/>
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
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function changevalue(obj) {
	var markId = obj.value;
if(markId!=null && markId != ''){
		$.ajax({
			type : "POST",
			url : "InsTemplate_findprocardBymarkId.action",
			data : {
				markId : markId,
				},
			dataType : "json",
			success : function(data) {
			if (data != null) {
				var pt = data[0];
				var gongxuNumList = null;
				if(data.length == 2){
					gongxuNumList = data[1];
				}
				$("#productType").val(pt.carStyle);
				$("#name").val(pt.proName);
				$("#ctype1").val(pt.procardStyle+"件");
				$("#gongxuNum").empty();
				if(gongxuNumList!=null && gongxuNumList.length>0){
					for(var i = 0; i<gongxuNumList.length;i++){
						$("<option value='" + gongxuNumList[i] + "'>"+gongxuNumList[i]+"</option>" ).appendTo("#gongxuNum");				
					}
				}
			}
			}
		})
	}
	
}

var index=${size};
function addLine() {
	var newLine = '<tr align="center"><td><select  name="t.scope['
			+ index
			+ '].type" style="width:209px;"  > '
			+ '<option>手动填写</option> <option>是否合格</option> <option>有无</option> <option>OKorNo</option></select> </td> '
			+ ' <td><input name="t.scope[' + index
			+ '].content"/></td>' + ''
			+ '<td><input name="t.scope[' + index
			+ '].jcff"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>';
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
$(function(){
	gettuzhi();
})
function gettuzhi() {
	var gongxuNum = '${t.gongxuNum}';
	var partNumber = '${t.partNumber}';
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
								var tzurl = "/upload/file/processTz/"
										+ ptf.month + "/" + ptf.fileName;
								var gongxuName =	$("#gongxuName").val();
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
</script>
	</body>
</html>
