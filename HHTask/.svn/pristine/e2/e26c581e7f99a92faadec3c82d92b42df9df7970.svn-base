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
//显示日志明细
function showLogging(dataId) {
	$.ajax( {
		type : "post",
		url : "AlertMessagesAction!findLogging.action",
		data : {
			id : dataId
		},
		dataType : "json",
		success : function(obj) {
			chageDiv("block");
			var message = "<b>对 象 名 </b>: " + obj.objectName;
			message += "<br/><b>操作状态</b>: " + obj.status;
			message += "<br/><b>操 作 者 </b>: " + obj.userName;
			message += "<br/><b>所属部门</b>: " + obj.userDept;
			message += "<br/><b>操作时间</b>: " + obj.addTime;
			message += "<br/><b>详细</b>:<br/> ";

			var obj = jQuery.parseJSON(obj.more);
			$.each(obj, function(i, n) {
				message += "&nbsp;&nbsp;&nbsp;&nbsp;字段: " + i + ";&nbsp;&nbsp; 值: " + n + "<br/>";
			});
			$("#showLoggingDetail").html(message);
		}
	})
}
</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 980px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%; margin-top: ">
					<tr>
						<td>
							您正在查看日志明细:
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; margin: 0px;; padding: 0px;">
					<div id="showLoggingDetail" align="left" style="margin-left: 20px;">

					</div>
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
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新<br/>(Refresh)</a>
				</div>
			</div>
			
			<div align="center">
				<form action="AlertMessagesAction!findLoggingByCondition.action"
					method="post">
					<table class="table">
						<tr>
							<th align="center" colspan="6">
								查询日志信息<br/>(Query log information)
							</th>
						</tr>
						<tr>
							<th align="right">
								对象名:<br/>(Object Name)
							</th>
							<td>
								<input name="logging.objectName" value="${logging.objectName}" />
							</td>
							<th align="right">
								操作者:<br/>(Operator)
							</th>
							<td>
								<input name="logging.userName" value="${logging.userName}"/>
							</td>
							<th align="right">
								操作类型:<br/>(Type of operation)
							</th>
							<td>
								<select name="logging.status" style="width: 155px;">
									<option></option>
									<option value="添加">
										添加
									</option>
									<option value="删除">
										删除
									</option>
									<option value="修改">
										修改
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								开始时间:<br/>(Start Time)
							</th>
							<td>
								<input class="Wdate" type="text" name="date"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								结束时间:<br/>(End Time)
							</th>
							<td>
								<input class="Wdate" type="text" name="date"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							<th align="right">
								操作内容:<br/>(Operating content)
							</th>
							<td>
								<input name="logging.more" value="${logging.more}" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询select" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号<br/>(Number)
						</th>
						<th align="center">
							对象名<br/>(Object Name)
						</th>
						<th align="center">
							操作状态<br/>(Operating status)
						</th>
						<th align="center">
							操作者<br/>(Operator)
						</th>
						<th align="center">
							所属部门<br/>(Department)
						</th>
						<th align="center">
							添加时间<br/>(Add Time)
						</th>
						<th align="center">
							操作<br/>(Operation)
						</th>
					</tr>
					<s:iterator value="loggingList" id="pageLogging"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageLogging.objectName}
						</td>
						<td>
							${pageLogging.status}
						</td>
						<td>
							${pageLogging.userName}
						</td>
						<td>
							${pageLogging.userDept}
						</td>
						<td>
							${pageLogging.addTime}
						</td>
						<td>
							<a href="javascript:;" onclick="showLogging('${pageLogging.id}')">明细Details</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
