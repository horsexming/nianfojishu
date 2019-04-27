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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.dhlabel {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000;
	border-left: 1px solid #000;
	border-right: 1px solid #000;
	margin-left: 5px;
	margin-right: 5px;
	padding: 3px 5px;
	white-space: nowrap;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<s:if test="tag=='All'">
						<label style="background-color: gray;" class="dhlabel">
							所有
						</label>
					</s:if>
					<s:else>
						<label style="background-color: #5cb85c;"
							onclick="toShowWW('All');" class="dhlabel">
							<font color="white">所有</font>
						</label>
					</s:else>
					<s:if test="tag=='wei'">
						<label style="background-color: gray;" class="dhlabel">
							未申请
						</label>
					</s:if>
					<s:else>
						<label style="background-color: #5cb85c;"
							onclick="toShowWW('wei');" class="dhlabel">
							<font color="white">未申请</font>
						</label>
					</s:else>
				<form action="procardBlAction_findAllWxTuiliao.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${wxtl.markId}" name="wxtl.markId" />
							</td>
							<th align="right">
								批次
							</th>
							<td>
								<input type="text" value="${wxtl.selfCard}" name="wxtl.selfCard" />
							</td>
						</tr>
						<tr>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="${wxtl.ywMarkid}" name="wxtl.ywMarkid" />
							</td>
							<th align="right">
								订单号
							</th>
							<td>
								<input type="text" value="${wxtl.orderNum}" name="wxtl.orderNum" />
							</td>
						</tr>
						<input type="submit" value="查询" class="input" />
					</table>
				</form>
				<form action="" method="POST">
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
								全选
							</th>
							<th>
								序号
							</th>
							<th>
								件号
							</th>
							<th>
								名称
							</th>
							<th>
								批次
							</th>
							<th>
								订单号
							</th>
							<th>
								业务件号
							</th>
							<th>
								对应工序号
							</th>
							<th>
								对应工序名
							</th>
							<th>
								申请退料数量
							</th>
							<th>
								同意数量
							</th>
							<th>
								审批状态
							</th>
							<th>
								申请时间
							</th>
							<th>
								申请人
							</th>
						</tr>  
						<s:iterator id="pageList" value="wxtlList" status="statussdf">
							<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<input type="checkbox" value="${pageList.id}" name="procardIds1"/>
							</td>
							<td>
								<s:property value="#statussdf.index+1" />
							</td>
							<td>
								${pageList.markId}
							</td>
							<td>
								${pageList.proName}
							</td>
							<td>
								${pageList.selfCard}
							</td>
							<td>
								${pageList.orderNum}
							</td>
							<td>
								${pageList.ywMarkid}
							</td>
							<td>
								${pageList.processNos}
							</td>
							<td>
								${pageList.processNames}
							</td>
							<td>
								${pageList.sqtlNum}
							</td>
							<td>
								${pageList.agreeNum}
							</td>
							<td>
								<a
									href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">${pageList.epstatus}</a>
							</td>
							<td>
								${pageList.addTime}
							</td>
							<td>
								${pageList.addUsersName}
							</td>
						</s:iterator>
						<tr>
							<td colspan="30" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
						<tr>
							<th colspan="1" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll2" name="procardIds">
								全选
							</th>
							<td colspan="20">
								<s:if test="tag=='wei'">
									<input id="ok" class="input" style="width: 120px;" align="top"
										type="button" value="审批通过"
										onclick="toSubmitTl1(this.form,'ok')" />
									<input id="ng" class="input" align="top" type="button"
										value="审批驳回" onclick="toSubmitTl1(this.form,'no')" />
								</s:if>
							</td>
						</tr>
					</table>

				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function toSubmitTl1(form,tag){
	form.action = "procardBlAction_auditWxTuiliao.action?okNo="+tag+"&tag=${tag}";
	form.submit();
}

function toShowWW(tag){
	window.location.href="procardBlAction_findAllWxTuiliao.action?tag="+tag;
}


</SCRIPT>
	</body>
</html>
