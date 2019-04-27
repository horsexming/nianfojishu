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
				<h2>制定调查问卷模板</h2>
				<form action="QuestionTemplateAction_addqt.action" method="post">
					<table id="mytable">
						<tr>
							<th align="right">
								问卷名称:
							</th>
							<td>
								<input type="text" name="qt.name"/>
								<input type="button" value="添加条例" onclick="addLine()"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="提交" style="width: 75px;height: 35px;"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
var index=0;
function addLine() {
	var newLine = '<tr id=tr_'+index+' ><th align=right id =th_'+index+'>第'+(index+1)+'条 </th> <td>' +
	'<textarea rows=2 cols=80 name=qt.qeList['+index+'].content id=content_'+index+'>' +
	'</textarea><input type=button value=追加 onclick="addLine()" />' +
	'<input type="button" value="删除" onclick = delLine("tr_'+index+'")></td></tr>';
	$("#mytable").append(newLine);
	index++;
}

function delLine(obj) {
	$("#"+obj).remove();
	index--;
	var n = $('#mytable tr').length;
	for(var i=0;i<index;i++){
		var id=	$('#mytable tr')[n-(i+1)].id 
		var num =id.split('_');
		if(num.length == 2){
			$("#content_"+num[1]).attr('name','qt.qeList['+(index-i-1)+'].content')
			$("#th_"+num[1]).html('第'+(index-i)+'条');
		}
		
	}
	
}




</script>
	</body>
</html>
