<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
				<h3>
					价格确认
				</h3>
				<form action="NoPriceprocessAction_querenPrice.action" method="post">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
						</td>
						<td align="center">
							序号
						</td>
						<td align="center">
							公司编号
						</td>
						<td align="center">
							公司名称
						</td>
						<td align="center">
							联系人
						</td>
						<td align="center">
							联系方式
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							含税价格
						</td>
						<td align="center">
							税率
						</td>
						<td align="center">
							不含税价格
						</td>
					</tr>
					<s:iterator value="zhuserOfferList" id="list"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						
						<td>
						</td>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list.usercode}
						</td>
						<td align="center">
							${list.cmp}
						</td>
						<td align="center">
							${list.cperson}
						</td>
						<td align="left" >
							${list.ctel}
						</td>
						<td align="center">
							${list.status}
						</td>
						<s:if test='#list.status!="未报价"'>
							<td align="center">
								${list.hsPrice}
							</td>
							<td align="center">
								${list.taxprice}
							</td>
							<td align="center" >
								${list.bhsPrice}
							</td>
						</s:if><s:else>
							<td colspan="3"><FONT color="red">没有报价</FONT></td>
						</s:else>
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
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
					<tr>
					</tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		</script>
	</body>
</html>
