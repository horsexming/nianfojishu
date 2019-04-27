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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
					<table style="width: 100%">
						<tr>
							<td>

							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
				</center>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>

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
<%--				<div style="float: left; width: 45%; padding-top: 5px;"--%>
<%--					align="right">--%>
<%--					<a href="javascript:history.go(-1)">返回</a>--%>
<%--					<a onclick="addtt(${t.id})">添加检查项</a>--%>
<%----%>
<%--					--%>
<%--					<a--%>
<%--						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"--%>
<%--						style="color: #ffffff">刷新</a>--%>
<%--				</div>--%>
			</div>

			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							检查内容
						</th>
						<th>
							检查类型
						</th>
						<th>
							质量特征
						</th>
						<th>
							检测方法
						</th>
					<s:if test="status != 'show'">
						<th>
								操作
						</th>
					</s:if>
					</tr>
					<s:iterator value="sc" status="st" id="ll">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${st.index +1 }
						</th>
						<td>
							${ll.content}
						</td>
						<td>
							${ll.type}
						</td>
						<td align="left">
							${ll.zltz}
						</td>
						<td>
							${ll.jcff}
						</td>
						<s:if test="status != 'show'">
						<td>
							<a onclick="addtt(${t.id})">添加</a>/
							<a onclick="toUpdateScope(${ll.id})">修改</a>/
							 <a href="OsTemplate_deleteOsScope.action?t.id=${t.id}&&tt.id=${ll.id}" onclick="return confirm('确定删除此记录?')">删除</a> 
						</td>
						</s:if>
						</tr>
					</s:iterator>
				</table>
				<br/>
				<s:if test="status != 'show'">
					<strong><font size="4">添加检查项</font> </strong>
				<form id="myForm" action="OsTemplate_addOsScope1.action" method="post" enctype="multipart/form-data" onsubmit="return check();">
					<table id="mytable" border="1">
						<tr>
							<th>
								检查类型
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
							<td>
								<select name="t.scope[0].type" style="width: 150px">
									<option>
										手动填写
									</option>
									<option>
										OKorNo
									</option>
								</select>
							</td>
							<td>
								<input id="content" name="t.scope[0].content" />
							</td>
							<td>
								<input id="zltz" name="t.scope[0].zltz" />
							</td>
							<td>
								<input id="jcff" name="t.scope[0].jcff" />
								<input type="hidden" name="t.id" value="${t.id}"/>
								<input type="button" onclick="addLine();" value="追加">
								<input type="button" onclick="delLine();" value="删除">
							</td>
						</tr>
						<tr id="lastTr">
							<td align="center" colspan="4">
								<input type="hidden" value="${id}" name="id"/>
								<input id="submit" type="submit" value="提交">
							</td>
						</tr>
					</table>
				</form>
				</s:if>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toUpdateScope(id) {
	var url = encodeURI(encodeURI("OsTemplate_toUpdateOsScope.action?tt.id="
			+ id));
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function addtt(id) {
	var url = encodeURI(encodeURI("${pageContext.request.contextPath}/System/xunjian/OsScope_add.jsp?tid="
			+ id));
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
	
var index = 1;
function addLine() {
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
	index++;
}
function delLine() {
	if (index == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
	$($('#mytable tr')[$('#mytable tr').length - 3]).remove();
	index--;
}
function check(){
	
	
}
</script>
	</body>
</html>
