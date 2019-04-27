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
function check() {
	var ctype = document.getElementById("ctype");
	var material = document.getElementById("material");
	var serialNumber = document.getElementById("serialNumber");
	var content = document.getElementById("content");
	var zltz = document.getElementById("zltz");
	var jcff = document.getElementById("jcff");
	if (document.getElementById("abc").value == "") {
		alert("没有文件");
		return false;
	} else if (ctype != null && ctype.value == "") {
		alert("请选择类型");
		return false;
	} else if (material != null && material.value == "") {
		alert("请填写材料");
		return false;
	} else if (serialNumber != null && serialNumber.value == "") {
		alert("请填写检验规程编号");
		return false;
	} else if (content != null && content.value == "") {
		alert("请填写检查条目");
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

var index = '${size}';

function addLine() {
	index++;
	var newLine = '<tr> <td> <select name="t.scope['
			+ index
			+ '].type" style="width:150px " > <option>手动填写</option> <option>OKorNo</option> </select> </td> <td> <input name="t.scope['
			+ index
			+ '].content"/> </td> <td> <input name="t.scope['
			+ index
			+ '].zltz"/> </td> <td> <input name="t.scope['
			+ index
			+ '].jcff"/> <input type="button" onclick="addLine();" value="追加"> <input type="button" onclick="delLine();" value="删除"> </td> </tr>';
	$($('#mytable tr')[$('#mytable tr').length - 2]).insertBefore(newLine);
	$('#lastTr').before(newLine);

}

function delLine() {
	if (index == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
	$($('#mytable tr')[$('#mytable tr').length - 3]).remove();
	index--;
}
$(function() {

})
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">			
		<div align="center">
			<s:set name="tid">
				<s:property value="t.getId()" />
			</s:set>
			<s:if test="#tid>0">
				<form id="myForm" action="OsTemplate_updateOsTemplate.action"
					method="post" enctype="multipart/form-data">
					<input type="hidden" name="t.dept" value="${companyInfo.shortName}" />
					<table id="mytable" class="table" style="width: 90%">
						<tr>
							<th colspan="4">
								<font size="6">修改模版</font>
							</th>
						</tr>
						<tr>
							<th>
								名称
							</th>
							<td>
								<input name="t.name" value="${t.name}" />
							</td>
							<th>
								件号
							</th>
							<td>
								<input name="t.partNumber" value="${t.partNumber}" />
							</td>
						</tr>
						<tr>
							<th>
								材料
							</th>
							<td align="left">
								<input name="t.material" value="${t.material}" />
							</td>
							<th>
								检验规程编号
							</th>
							<td>
								<input name="t.serialNumber" value="${t.serialNumber}" />
							</td>
						</tr>
						<tr>
							<th>
								资料上传
							</th>
							<td>
								<a
									href="DownAction.action?fileName=${t.filename}&directory=upload/file/OsTemplate/">下载</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 重新上传
								<input type="file" name="attachment" />
							</td>
							<th>
								类型
							</th>
							<td>
								<select name="t.ctype1">
									<option>
										${t.ctype1}
									</option>
									<option>
										外购件
									</option>
									<option>
										原材料
									</option>
									<option>
										自制
									</option>
									<option>
										组合
									</option>
									<option>
										总成
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								工序号
							</th>
							<td>
								<input type="text" name="t.gongxuNum" value="${gongxuNum}" />
							</td>
							<tr>
						<td>检验标准</td>
						<td>
							<select name="t.xjbzId" id="xjbzId">
								<option></option>
							</select>
						</td>
					</tr>
						</tr>
						<tr id="lastTr">
							<td align="center" colspan="4">
								<input type="hidden" name="t.id" value="${id}" />
								<input type="submit" value="修改" class="input">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								常用符号: Φ ± ° ≤ ≥ ℃ < > № ⊥ ◎ ○ &nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</form>
				<br>
				<Strong><font size="5">查看检查项</font> </Strong>
				<iframe id="showCardIframe"
					src="OsTemplate_showScope.action?t.id=${t.id}&id=${id}"
					marginwidth="0" marginheight="0" frameborder="0" scrolling="no"
					style="width: 100%; height: 1500px; margin: 0px; padding: 0px;"></iframe>

			</s:if>
			<s:else>
				<form id="myForm" action="OsTemplate_add.action" method="post"
					enctype="multipart/form-data" onsubmit="return check();">
					<input type="hidden" name="t.dept" value="${companyInfo.shortName}" />

					<table id="mytable" class="table" style="width: 90%">
						<tr>
							<th colspan="4">
								<font size="6">添加模版</font>
							</th>
						</tr>
						<tr>
							<th>
								件号
							</th>
							<td>
								<input name="t.partNumber" value="${procardTemplate.markId}"
									readonly="readonly" />
							</td>
							<th>
								名称
							</th>
							<td>
								<input name="t.name" value="${procardTemplate.proName}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								检验图纸
							</th>
							<td>
								<input id="abc" type="file" name="attachment" />
							</td>
							<th>
								产品类型
							</th>
							<td>
								<input type="text" name="t.ctype1"
									value="${procardTemplate.procardStyle}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>
								工序号
							</th>
							<td>
								<input type="text" name="t.gongxuNum" value="${gongxuNum}" />
							</td>
							<td></td>
							<td></td>
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
						<s:iterator id="scope" value="sc" status="statussdf">
							<tr>
								<td>
									<input type="text" name="t.scope[${statussdf.index}].type"
										value="${scope.type}" />
								</td>
								<td>
									<input type="text" name="t.scope[${statussdf.index}].content"
										value="${scope.content}" />
								</td>
								<td>
									<input type="text" name="t.scope[${statussdf.index}].zltz"
										value="${scope.zltz}" />
								</td>
								<td>
									<input type="text" name="t.scope[${statussdf.index}].jcff"
										value="${scope.jcff}" />
									<input type="button" onclick="delLine();" value="删除">
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td>
								<select name="t.scope[${size}].type" style="width: 150px">
									<option>
										手动填写
									</option>
									<option>
										OKorNo
									</option>
								</select>
							</td>
							<td>
								<input name="t.scope[${size}].content" id="content" />
							</td>
							<td>
								<input name="t.scope[${size}].zltz" id="zltz" />
							</td>
							<td>
								<input name="t.scope[${size}].jcff" id="jcff" />
								<input type="button" onclick="addLine();" value="追加">
								<input type="button" onclick="delLine();" value="删除">
							</td>
						</tr>
						<tr id="lastTr">
							<td align="center" colspan="4">

								<input type="hidden" name="id" value="${id}" />
								<input id="submit" type="submit" value="提交">
							</td>
						</tr>
						<tr>
							<th colspan="4">
								常用符号: Φ ± ° ≤ ≥ ℃ < > № ⊥ ◎ ○
							</th>
						</tr>
					</table>
					<input type="hidden" value="${errorMessage}" id="rebeack" />
				</form>
			</s:else>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	var rebeack = $("#rebeack").val();
	if(rebeack == "添加成功!"){
		alert(rebeack);
		parent.chageDiv('none');
		parent.window.location.reload();
	}
	findAllJyPc();
	
})

function findAllJyPc(){
		$.ajax( {
					type : "POST",
					url : "markIdAction!findAllJyPc.action?status=xj",
					dataType : "json",
					success : function(data) {
						$("#xjbzId").empty();
						if(data!=null){
							$(data).each(function(){
								$("#xjbzId").append("<option value="+this.id+">"+this.leixing+"</option>")
							})
						}
					}
				})
}
	

</SCRIPT>
	</body>
</html>
