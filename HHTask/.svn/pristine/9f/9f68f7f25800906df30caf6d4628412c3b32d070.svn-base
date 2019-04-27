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
		<SCRIPT type="text/javascript">
			$(function (){
				var procardStyle="${procard.procardStyle}"
				if(procardStyle=="总成"||procardStyle=="组合"||procardStyle=="自制"){
					$("#showZong").show();
				}else if(procardStyle=="自制"){
					$("#showZi").show();
				}
			});
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<h1>
					领取工序(Collection process)
				</h1>
				<br />
				<br />
				<div align="center">
					<form action="ProcardAction!findProcardByCardNum.action"
						target="showJindu" method="post">
						<table>
							<tr>
								<td align="right">
									请刷流水卡片(扫描报废单条码)/(员工卡):
									<br />
									Please brush water card (single bar code scanning scrapped):
								</td>
								<td>
									<input type="hidden" name="pageStatus"
										value="${param.pageStatus}" />
									<input type="text" id="runCard" name="cardNumber"
										value="${cardNumber}" />
									<input type="submit" value="确定" class="input" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//初始选中
$(function() {
	$("#runCard").focus();
})
</script>
	</body>
</html>
