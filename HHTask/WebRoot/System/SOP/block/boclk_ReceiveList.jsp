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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<%--<form action="" method="post">
				<table class="table" style="width: 500px;">
					<tr>
						<th colspan="2">
							查询
						</th>
					</tr>
					<tr>
						<th>
							姓名:
						</th>
						<td>
							<input name="" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="查询" class="input">
						</td>
					</tr>
				</table>
			</form>
			--%><div align="center">
				<div align="right">
					<input type="button" onclick="fanhui()" value="返回"
				style="width: 80px;height: 50px;"/>
				</div>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							卡号
						</th>
						<th align="center">
							姓名
						</th>
						<th align="center">
							领取时间
						</th>
						<th align="center">
							提交时间
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="pageBlock" status="pageStatus">
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
							${pageBlock.card}
						</td>
						<td>
							${pageBlock.userName}
						</td>
						<td>
							${pageBlock.startTime}
						</td>
						<td>
							${pageBlock.endTime}
						</td>
						<td>
							${pageBlock.status}
						</td>
						<td>
							<s:if test='#pageBlock.status=="完成"'>  
								<input type="button" value="确认" onclick="queren('${pageBlock.id}')"/>
							</s:if>
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
		<SCRIPT type="text/javascript">
		function queren(id){
			window.location.href = "BlockAction_qrBlock.action?id=" + id + "&id1=${id}&cpage=${cpage}&total=${total}";
		}
		function fanhui(){
			window.location.href = "BlockAction_findBlockByCondition.action";
		}
		
		</SCRIPT>
	</body>
</html>
