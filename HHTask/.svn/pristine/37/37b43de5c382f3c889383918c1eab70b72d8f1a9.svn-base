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
		<div id="gongneng">
			<div align="center">
				<form action="BlockAction_findUserBlock.action" method="post">
					<table class="table" align="center">
						<tr>
							<th>
								工作区领取以及提交
							</th>
						</tr>
						<tr>
							<th align="center">
								请刷卡:
								<input name="card" id="card" value="${card}" />
								<input type="submit" value="确定" class="input" /> &nbsp;
								<input class="input" onclick="window.history.back();" type="button" value="返回"/>
							</th>
						</tr>
					</table>
				</form>

				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							描述
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="blockList" id="pageBlock" status="pageStatus">
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
							<a href="BlockAction_findAllBlock.action?blockStatus=xcws">
								${pageBlock.blockName}</a>
						</td>
						<td>
							${pageBlock.blockMore}
						</td>
						<td>
							${pageBlock.status}
						</td>
						<td>
							<s:if test='status=="初始"||status=="完成"'>
								<a
									href="BlockAction_receiveBlock.action?id=${pageBlock.id}&card=${card}"
									style="color: red; font-weight: bolder;">领取</a>
							</s:if>
							<s:elseif test='status=="已领"'>
								<a
									href="BlockAction_sendBlock.action?id=${pageBlock.id}&card=${card}"
									style="color: red; font-weight: bolder;">提交</a>
							</s:elseif>
							/
							<a
								href="BlockAction_findAllReceiveBlock.action?id=${pageBlock.id}&card=${card}">领取明细</a>
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
				<table class="table" style="width: 600px;">
					<tr>
						<th colspan="3">
							物业清洁工作职责
						</th>
					</tr>
					<tr>
						<th>
							序号
						</th>
						<th>
							清洁区域
						</th>
						<th>
							检查标准检查方法
						</th>
					</tr>
					<tr>
						<td>
							1
						</td>
						<td>
							现场所有网状隔栏
						</td>
						<td>
							目视无可见油污、污迹，无灰尘
						</td>
					</tr>
					<tr>
						<td>
							2
						</td>
						<td>
							现场所有门窗低于1.5米区域
						</td>
						<td>
							目视无可见污迹、灰尘
						</td>
					</tr>
					<tr>
						<td>
							3
						</td>
						<td>
							现场的厕所内清洁
						</td>
						<td>
							地面无积水、下水无堵塞，便池无异味
						</td>
					</tr>
					<tr>
						<th>
							要求
						</th>
						<th colspan="2">
							每天现场工作不得低于6小时，每周至少对上述责任区域打扫一次，检查发现不合格应立即整改。
						</th>
					</tr>
					<tr>
						<th colspan="3" style="color: red">
							说明:上午07:00以前领取,11:00以后提交
							<br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下午13:00以前领取,15:00以后提交
							<br />


						</th>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
$(function() {
	$("#card").select();
})
</script>
		</center>
	</body>
</html>
