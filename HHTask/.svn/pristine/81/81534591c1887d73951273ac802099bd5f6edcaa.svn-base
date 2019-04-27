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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					财务收款明细汇总
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="NoncoreReceAction!showListDetailcaiwuShou.action?tag=${tag}"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="right">
								月份：
							</td>
							<td align="left">
								<input type="text" name="financialReceipts.zhangqi" class="Wdate" 
								onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"/>
							</td>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px; "
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							科目
						</td>
						<td align="center">
							收款月份
						</td>
						<td align="center">
							收款日期
						</td>
						<td align="center">
							收款金额
						</td>
						<td align="center">
							费用截止日
						</td>
						<td align="center">
							收款人
						</td>
					</tr>
					<s:iterator value="financialReceiptsList" id="samples" status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.kemu}
						</td>
						<td align="center">
							${samples.zhangqi}
						</td>
						<td align="center">
							${samples.jiluTime}
						</td>
						<td align="center">
							${samples.querenMoney}
						</td>
						<td align="center">
							${samples.jiezTime}
						</td>
						<td align="center">
							${samples.saveUser}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="7" align="right">
							本页合计金额：<font color="red">${addUp}</font>元 
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="7" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
