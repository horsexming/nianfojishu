<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<script type="text/javascript">
function checkFileName(id) {
	var fileName = document.getElementById("fileNumber" + id);
	if (fileName.value == "") {
		alert("档案号不能为空!")
		fileName.focus();
		return false;
	} else {
		return true;
	}
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				
			</div>
			
			<div align="center">
				<form action="PriceAction!findPriceByCondition.action?statue=update"
					method="post">
					<table width="99%">
						<tr>
							<td align="right">
								件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								<br/>(Item Number)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.partNumber">
							</td>
							<td align="right">
								名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:
								<br/>(Name)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.name">
							</td>
							<td align="right">
								产品类别:<br/>
								(Product Type)
							</td>
							<td>
								<select style="width: 150px;" name="price.productCategory">
									<option></option>
									<option>
										总成
									</option>
									<option>
										组件
									</option>
									<option>
										零件
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								价格(含税):<br>
								(Price (including tax))
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.hsPrice">
							</td>
							<td align="right">
								价格(不含税):<br>(Price (excluding tax))
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.bhsPrice">
							</td>
							<td align="right">
								生产类型:<br>(Production Type)
							</td>
							<td>
								<select style="width: 150px;" name="price.produceType">
									<option></option>
									<option value="销售">
										销售
									</option>
									<option value="外委">
										外委
									</option>
									<option value="外购">
										外购
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								合同编号<br>(Contract Number)
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="price.contractNumber">
							</td>
							<td align="right">
								档案号<br>(file Number)
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="price.fileNumber">
							</td>
						</tr>
						<tr>
							<td align="right">
								价格有效期开始时间<br>(Price valid start time)
							</td>
							<td>
								<div>
									<input class="Wdate" type="text" name="price.pricePeriodStart"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</div>
							</td>
							<td colspan="3" rowspan="5" align="center">
								<input type="submit" value="查询select"
									style="width: 100px; height: 60px">
								<input type="reset" value="重置reset"
									style="width: 100px; height: 60px">
								<a href="PriceAction!findAllPrice.action?statue=update">查看全部select all</a>
							</td>
						</tr>
						<tr>
							<td align="right">
								价格有效期结束时间<br>(Price valid end time)
							</td>
							<td>
								<div>
									<input class="Wdate" type="text" name="price.pricePeriodEnd"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</div>
							</td>
						</tr>
					</table>
				</form>
				<br>
				<center>
					<div align="center">
						${successMessage}
					</div>
					<table width="99%">
						<tr align="center">
							<td>
								序号<br>(Serial number)
							</td>
							<td>
								产品类别<br>(Product Type)
							</td>
							<td>
								生产类型<br>(Production Type)
							</td>
							<td>
								件号<br>(Item Number)
							</td>
							<td width="60px">
								名称<br>(Name)
							</td>
							<td width="60px">
								签订方<br>(The Signing Party)
							</td>
							<td>
								合同编号<br>(Contract Number)
							</td>
							<td>
								签订日期<br>(Signing date)
							</td>
							<td>
								档案号<br>(File Number)
							</td>
							<td>
								操作<br>(Operation)
							</td>
						</tr>
						<s:iterator id="pricetest" value="priceList" status="fileId">
							<form action="PriceAction!updateFileName.action" method="post"
								onsubmit="return checkFileName('<s:property value="#fileId.index"/>')">
								<tr align="center">
									<td>
										<s:property value="#fileId.index+1" />
									</td>
									<td>
										${pricetest.productCategory}
									</td>
									<td>
										${pricetest.produceType}
									</td>
									<td>
										${pricetest.partNumber}
									</td>
									<td align="left" width="60px">
										${pricetest.name}
									</td>
									<td align="left" width="60px">
										${pricetest.type}
									</td>
									<td>
										${pricetest.contractNumber}
									</td>
									<td>
										${pricetest.pricePeriodStart}
									</td>
									<td>
										<input type="hidden" name="cpage"
											value="<s:property value="cpage" />">
										<input type="hidden" name="id" value="${pricetest.id}">
										<input type="text"
											id="fileNumber<s:property value="#fileId.index"/>"
											name="price.fileNumber" value="${pricetest.fileNumber}">
									</td>
									<td>
										<input type="submit" value="录入">
									</td>
								</tr>
							</form>
						</s:iterator>
						<tr>
							<td colspan="10" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
				</center>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
