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
			<div id="xitong" >
			</div>
			
			<div align="center">
				<h3>
					生产半成品转存管理
				</h3>
				<form action="ProcardAction!findRootProcardListForbcp.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								总成件号
								<input type="text" name="procard.rootMarkId" value="<s:property value="procard.rootMarkId"/>" />
							</td>
							<td align="center">
								总成批次
								<input type="text" name="procard.rootSelfCard" value="<s:property value="procard.rootSelfCard"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								业务件号
								<input type="text" name="procard.ywMarkId" value="<s:property value="procard.ywMarkId"/>" />
							</td>
							<td align="center">
								订单号
								<input type="text" name="procard.orderNumber" value="<s:property value="procard.orderNumber"/>" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(num)
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							总成批次
						</td>
						<td align="center">
							业务件号
						</td>
						<td align="center">
							订单号
						</td>
						<td align="center">
							名称
						</td>
						<td align="center">
							批次数量
						</td>
						<td align="center">
							单位
						</td>
						<td align="center">
							操作
						</td>						
					</tr>
					<s:iterator value="list" id="pageprocard" status="pageStatus">
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
							${pageprocard.markId}
						</td>
						<td>
							${pageprocard.selfCard}
						</td>
						<td>
							${pageprocard.ywMarkId}
						</td>
						<td>
							${pageprocard.orderNumber}
						</td>
						<td  align="left">
							${pageprocard.proName}
						</td>
						<td align="right">
							${pageprocard.filnalCount}
						</td>
						<td>
							${pageprocard.unit}
						</td>
						
						<td colspan="2">
							<input type="button" value="生产进度" style="height: 20px;width: 80px;" onclick="showscjd(${pageprocard.id})">
							<input type="button" value="半成品领料" style="height: 20px;width: 80px;" onclick="showProcardbcpList(${pageprocard.id})">
						</td>
					</tr>
					</s:iterator>
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
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function showProcardbcpList(id){
	window.location.href="ProcardAction!showProcardbcpList.action?id="+id;
}
function showscjd(id){
	window.open("ProcardAction!findProcardView.action?id="+id+"&pageStatus=history&viewStatus=");
}
</script>
	</body>
</html>
