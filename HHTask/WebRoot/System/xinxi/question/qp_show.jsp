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
				<h2>修改${qp.usename}的${qp.qename}问卷</h2>
				<font color="red" size="5">${errorMessage}</font>
				<form action="QuestionnairePersonAction_updateqp.action" method="post">
					<table id="mytable" class="table">
						<tr >
							<th align="right">
								问卷名称
							</th>
							<td>
								<input type="text" name="qp.qename" value="${qp.qename}"/>
							</td>
							<td></td>
							</tr>
							<tr>
							<th align="right">
								调查人姓名
							</th>
							<td>
								<input type="text" name="qp.usename" value="${qp.usename}"/>
							</td>
							<td></td>
						</tr>
				<s:iterator value="qulist" id="pageList" status="pageStatus">
					<tr  id="tr_${pageStatus.index}">
						<th id="th_${pageStatus.index}" align="right">
							第<s:property value="#pageStatus.index+1"/>条            
						</th>
						<td align="left"> 
							<span>${pageList.content}</span>
							<input type="hidden" value="${pageList.content}" name="qp.qulist[${pageStatus.index}].content" />
						</td>
						<td>
							<s:if test="pageList.status=='yes'">
								<input type="radio" value="yes" checked="checked" name="qp.qulist[${pageStatus.index}].status"/>是
								<input type="radio" value="no" name="qp.qulist[${pageStatus.index}].status"/>否
							</s:if>
							<s:else>
								<input type="radio" value="yes" name="qp.qulist[${pageStatus.index}].status"/>是
								<input type="radio" value="no" name="qp.qulist[${pageStatus.index}].status" checked="checked"/>否
							</s:else>
						</td>                                                                                                                                                                                    
					</tr>
					</s:iterator>
					</table>
					<input type="hidden" value="${qp.id}" name="qp.id"/>
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
