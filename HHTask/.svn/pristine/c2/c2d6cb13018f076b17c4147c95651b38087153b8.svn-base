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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<form action="IntegralAction_showListXf.action" method="post">
				<table >
					<tr>
						<th align="right">
							消费日期
						</th>
						<td>
							<input class="Wdate"  type="text" value="" name="xf.xiaofeitime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
						</td>
						<th align="right">
							消费内容
						</th>
						<td>
							<input type="text" value="" name="xf.neirong"/>
						</td>
						<td >
							<input type="hidden" value="${xf.in_code}" name="xf.in_code"/>
							<input type="submit" value="查找">
						</td>
					</tr>
				</table>
			</form>
			<input type="hidden" id="rebeack" value="${errorMessage}"/>
			<h2><strong>消费来源项</strong></h2>
				<hr/>
				<table class="table">
					<tr align="center">
						<th >序号</th>
						<th >消费积分</th>
						<th >消费内容</th>
						<th >消费时间</th>
						<th >操作</th>
					</tr>
					<s:iterator value="xfList" id="xflist" status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')" width="80%">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')" width="80%">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${xflist.xiaofeijifen}
						</td>
						<td>
							${xflist.neirong}
						</td>
						<td>
							${xflist.xiaofeitime}
						</td>
						<td>
							<s:if test="statue != 'person'">
								<a onclick="return confirm('确定要删除吗？')" href="IntegralAction_delXiaoFei.action?xf.id=${xflist.id}">删除</a>
							</s:if>
						</td>
					</s:iterator>
					<tr>
						<td colspan="5" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="没有找到你要查询的内容,请检查后重试!"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})

</SCRIPT>
	</body>
</html>
