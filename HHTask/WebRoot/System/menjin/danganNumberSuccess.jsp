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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">

			</div>

			<div align="center">
				<form
					action="PriceAction!findPriceByCondition.action?statue=${statue}&num_1=${num_1}"
					method="post">
					<table width="99%">
						<tr>
							<td align="right">
								件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								<br />
								(Item Number)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.partNumber">
							</td>
							<td align="right">
								名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:
								<br />
								(Name)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.name">
							</td>
							<td align="right">
								产品类别:
								<br />
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
								价格(含税):
								<br>
								(Price (including tax))
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.hsPrice">
							</td>
							<td align="right">
								档案柜号
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.danganWeizhi">
							</td>
							<td align="right">
								生产类型:
								<br>
								(Production Type)
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
								合同编号
								<br>
								(Contract Number)
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="price.contractNumber">
							</td>
							<td align="right">
								签订方
								<br>
								(The Signing Party)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.type">
							</td>
							</td>
							<td align="right">
								档案号
								<br>
								(file Number)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.fileNumber">
							</td>
						</tr>
						<tr>
							<td align="right">
								价格有效期开始时间
								<br>
								(Price valid start time)
							</td>
							<td>
								<div>
									<input class="Wdate" type="text" name="price.pricePeriodStart"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</div>
							</td>
							<td colspan="4" rowspan="2" align="center">
								<input type="submit" value="查询select"
									style="width: 100px; height: 40px">
								<input type="reset" value="重置 submit"
									style="width: 100px; height: 40px">
							</td>
						</tr>
						<tr>
							<td align="right">
								价格有效期结束时间
								<br>
								(Price valid end time)
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
					<table width="99%">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<td>
								序号
								<br>
								(Serial number)
							</td>
							<td>
								产品类别
								<br>
								(Product Type)
							</td>
							<td>
								生产类型
								<br>
								(Production Type)
							</td>
							<td>
								件号
								<br>
								(Item Number)
							</td>
							<td width="60px">
								名称
								<br>
								(Name)
							</td>
							<td width="60px">
								签订方
								<br>
								(The Signing Party)
							</td>
							<td>
								价格(含税)
								<br>
								(Price (including tax))
							</td>
							<td>
								价 格 (不含税)
								<br>
								(Price (excluding tax))
							</td>
							<td>
								合同编号
								<br>
								(Contract Number)
							</td>
							<td>
								档案编号
								<br>
								(file Number)
							</td>
							<td>
								存档柜号
								<br>
							</td>
							<td>
								操作
								<br>
								(Operation)
							</td>
						</tr>
						<s:iterator id="pricetest" value="priceList" status="statussdf">
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
								<s:property value="#statussdf.index+1" />
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
								${pricetest.hsPrice}
							</td>
							<td>
								${pricetest.bhsPrice}
							</td>
							<td>
								${pricetest.contractNumber}
							</td>
							<td>
								${pricetest.fileNumber}
							</td>
							<td>
								${pricetest.danganWeizhi}
							</td>
							<td>
								<!-- <a href="PriceAction!findPriceById.action?id=${pricetest.id}&statue=${statue}"
									target="_blank">查看详细Details</a> -->
								<input type="button" value="选择"
									style="width: 50px; height: 31px;"
									onclick="selectDangan('${pricetest.name}','${pricetest.fileNumber}','${pricetest.danganWeizhi}','${pricetest.danganId}','${pricetest.attachmentName}','${num_1}','${pricetest.id}')" />
							</td>
						</s:iterator>
						<tr>
							<td colspan="12" align="right">
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
		<SCRIPT type="text/javascript">
		function selectDangan(name, num, guihao, guid, flieName,num_1,priceID) {
	parent.$("#daName"+num_1).val(name);
	parent.$("#daNum"+num_1).val(num);
	parent.$("#daGuiId"+num_1).val(guid);
	parent.$("#daGuihao"+num_1).val(guihao);
	parent.$("#fileName"+num_1).val(flieName);
	parent.$("#priceID"+num_1).val(priceID);
	parent.chageDiv('none');
}
		</SCRIPT>
	</body>
</html>
