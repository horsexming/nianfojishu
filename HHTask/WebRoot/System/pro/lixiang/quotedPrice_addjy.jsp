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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<h3>添加进度纪要</h3>
				<form action="QuotedPrice_addjy.action" method="post" onsubmit="return valedata();">
				<input type="hidden" value="${id}" name="id">
					<table class="table">
						<tr>
							<th width="50%" align="right">标题</th>
							<td width="50%"><input name="qpjy.title" style="width: 150px;"> </td>
						</tr>
						<tr>
							<th width="50%" align="right">纪要时间</th>
							<td width="50%"><input name="qpjy.jyTime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th colspan="2">纪要内容<input type="button" value="增加" onclick="addnr()"></th>
						</tr>
						<tr id="nrtr0">
							<td align="center" colspan="2"><textarea name="qpjy.qpjyDetailList[0].jyContext" rows="3" cols="40"></textarea><input type="button" value="删除" onclick="deletenr(0)">
							</td>
						</tr>
						<tr id="submitTr">
						<td align="center" colspan="2">
						<input id="subtn" type="submit" value="提交" >
						</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
var nrsize=1;
var nrindex=0;
function addnr(){
	nrsize++;
	nrindex++;
	var html = "<tr id='nrtr"+nrindex+"'><td align='center' colspan='2'>" +
	"<textarea name='qpjy.qpjyDetailList["+nrindex+"].jyContext' rows='4' cols='40'></textarea>" +
	"<input type='button' value='删除' onclick='deletenr("+nrindex+")'>" +
	"</td></tr>";
	$("#submitTr").before(html);
}
function deletenr(index){
	if(nrsize==1){
		alert("最少留一行!");
		return ;
	}
	nrsize--;
	nrindex--;
	$("#nrtr"+index).remove();
}
function valedata(){
	$("#subtn").attr("disabled","disabled");
}
</SCRIPT>
		
	</body>
</html>
