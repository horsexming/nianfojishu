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
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="printDiv">
					<div>
						<table border="1"
							style="border-collapse: collapse; margin-left: 5px;">
							<tr>
								<td align="center">
									<table>
										<tr>
											<td align="left">
												<b>${companyInfo.shortName}</b>
											</td>
											<td width="10px"></td>
											<td align="right">
												<b>出库单</b>
											</td>
										</tr>
										<tr>
											<td colspan="3" height="2">
												<hr>
											</td>
										</tr>
									</table>
								</td>

							</tr>
							<tr>
								<td>
									单据号：${vos.number }
								</td>
								<td rowspan="11">
									白
									<br>
									联
									<br>
									仓
									<br>
									库
									<br>
									，
									<br>
									红
									<br>
									联
									<br>
									财
									<br>
									务
									<br>
									，
									<br>
									黄
									<br>
									联
									<br>
									存
									<br>
									档
								</td>
							</tr>
							<tr>
								<td>
									日期：
									<s:date name="vos.date" format="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<td>
									使用人：${vos.peopleName }
								</td>
							</tr>
							<tr>
								<td>
									卡号：${vos.cardNum }
								</td>
							</tr>
							<tr>
								<td>
									部门：${vos.dept }
								</td>
							</tr>
							<tr>
								<td>
									品名：${vos.matetag }
								</td>
							</tr>
							<tr>
								<td>
									规格：${vos.format }
								</td>
							</tr>
							<tr>
								<td>
									单位：${vos.unit }
								</td>
							</tr>
							<tr>
								<td>
									仓库：${vos.storehouse }
								</td>
							</tr>
							<tr>
								<td>
									类别：${vos.parClass }
								</td>
							</tr>
							<tr>
								<td>
									位置：${vos.place }
								</td>
							</tr>
							<tr>
								<td>
									出库量：${vos.num }
								</td>
								<td rowspan="4"></td>
							</tr>
							<tr>
								<td>
									单价：
								</td>
							</tr>
							<tr>
								<td>
									金额：
								</td>
							</tr>
							<tr>
								<td>
									备注：
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div>
					<input type="button" value="打印" onclick="pagePrintOld('printDiv')" />
					<input type="button" value="返回" onclick="goBack()" />
				</div>
				<script type="text/javascript">
function goBack() {
	window.location = "store_initQueryLoan.action?vos.formUrl=consuming";
}
</script>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>