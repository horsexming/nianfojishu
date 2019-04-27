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
				<h2>修改调查问卷:${qt.name}</h2>
				<font color="red" size="5">${errorMessage}</font>
				<form action="QuestionTemplateAction_updateqt.action" method="post">
					<table id="mytable">
						<tr>
							<th align="right">
								问卷名称:
							</th>
							<td>
								<input type="text" name="qt.name" value="${qt.name}"/>
								<s:if test="size==0">
									<input type="button" value="添加条列" onclick="addLine(this)" />
								</s:if>
							</td>
						</tr>
				<s:iterator value="qeList" id="pageList" status="pageStatus">
					<tr align="center" id="tr_${pageStatus.index}">
						<th id="th_${pageStatus.index}">
							第<s:property value="#pageStatus.index+1" />条            
						</th>
						<td> 
							<textarea rows="2" cols="80" name="qt.qeList[${pageStatus.index}].content" id="content_${pageStatus.index}">${pageList.content}</textarea>
							<input type="button" value="追加" onclick="addLine()"/>
							<input type="button" value="删除" onclick = "delLine('tr_${pageStatus.index}')">
						</td>                                                                                                                                                                                    
					</tr>
					</s:iterator>
					</table>
					<input type="hidden" value="${qt.id}" name="qt.id"/>
					<input type="hidden" value="${status}" name="status"/>
					<input type="submit" value="修改" style="width: 75px;height: 35px;"/>
					<input type="reset" value="重置" style="width: 75px;height: 35px;"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
var index=${size};
function addLine(obj) {
	$(obj).hide();
	var newLine = '<tr id=tr_'+index+'   align="center"><th  id =th_'+index+'>第'+(index+1)+'条 </th> <td>' +
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
