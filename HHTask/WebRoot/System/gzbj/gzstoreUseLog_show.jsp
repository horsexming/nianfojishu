<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setHeader("Expires", "0");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
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
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工装使用记录</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
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
					<a href="" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					工装使用记录
				</h3>
				<form action="gzstoreUseLogAction_showList.action" method="post">
					<table class="table">
					    <tr>
					     <th>
					                  工装编号
					     </th>
					     <td align="center">
					       <input name="gzstoreUseLog.gzNumber" value="${gzstoreUseLog.gzNumber}">
					     </td>
					     <th>
					                  工装名称
					     </th>
					     <td align="center">
					       <input name="gzstoreUseLog.gzName" value="${gzstoreUseLog.gzName}">
					     </td>
					     <th>
					                    零件号
					     </th>
					     <td align="center">
					       <input name="gzstoreUseLog.processName" value="${gzstoreUseLog.processName}">
					     </td>
					    </tr>
					    <tr>
					     <th>
					                    工序名称
					     </th>
					     <td align="center">
					       <input name="gzstoreUseLog.processName" value="${gzstoreUseLog.processName}">
					     </td>
					     <th>
					                      操作人名称
					     </th>
					     <td align="center">
					       <input name="gzstoreUseLog.userName" value="${gzstoreUseLog.userName}">
					     </td>
					     <th>
					                   操作人工号
					     </th>
					     <td align="center">
					       <input name="gzstoreUseLog.userCode" value="${gzstoreUseLog.userCode}">
					     </td>
					    </tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<!--						<td align="center">-->
						<!--							id-->
						<!--						</td>-->
						<td align="center">
							工装编号
						</td>
						<td align="center">
							工装名称
						</td>
						<td align="center">
							零件号
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							操作人
						</td>
							<td align="center">
							操作人工号
						</td>
						<td align="center">
							开始时间
						</td>
							<td align="center">
							结束时间
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="gzstoreUseLogList" id="pageList" status="pageStatus">
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
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						
						<td>
							${pageList.gzNumber}
						</td>
						<td>
							${pageList.gzName}
						</td>
						<td>
							${pageList.markId}
						</td>
						<td>
							${pageList.processNo}
						</td>
						<td>
							${pageList.processName}
						</td>
						<td>
							${pageList.userName}
						</td>
						<td>
							${pageList.userCode}
						</td>
						<td>
							${pageList.stratTime}
						</td>
						<td>
							${pageList.endTime}
						</td>
						<td>
<%--							<input type="button" value="修改" onclick="update(${pageList.id},${cpage})">--%>
							<input type="button" value="删除" onclick="del(${pageList.id},${cpage})">
							
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
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

	<script type="text/javascript">
function update(id,cpage) {
	window.location.href="gzstoreUseLogAction_toUpdate.action?gzstoreUseLog.id=" + id+"&cpage"+cpage;
}
function del(id,cpage){
	// alert(${id});
	if(window.confirm("您将删除这条信息!")){
	window.location = "gzstoreUseLogAction_delete.action?gzstoreUseLog.id="+id+"&cpage"+cpage;
	}
}
</script>
</html>
