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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">修改产品价格</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
						
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>产品价格管理</h2>
				<form action="PriceAction!findAllPrice1.action" method="post" >
				<table class="table">
					<tr>
						<th>件号</th>
						<td> 
							<input type="text" name="price.partNumber"/>
						</td>
						<th>名称</th>
						<td> 
							<input type="text" name="price.name"/>
						</td>
						<th>客户</th>
						<td> 
							<select name="price.kehuId" id="custome">
									<option selected="selected" value="0">
										选择客户
									</option>
									<s:iterator id="cu" value="cmList">
										<option value="${cu.id}">
											${cu.clientcompanyname}
										</option>
									</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="hidden" name="statue" value="xgprice"/>
							<input type="hidden" name="successMessage" value="all"/>
							<input type="submit" value="查询" style="width:75px; height: 35px;"/>
						</td>
					</tr>
				</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>件号</th>
						<th>名称</th>
						<th>型别</th>
						<th>规格</th>
						<th>产品类别</th>
						<th>含税价格</th>
						<th>不含税价格</th>
						<th>税率</th>
						<th>生产类型</th>
						<th>合同编号</th>
						<th>负责人</th>
						<th>价格有效期开始日期</th>
						<th>价格有效期结束日期</th>
						<th>录入人</th>
						<th>客户</th>
						<th>修改价格</th>
					</tr>
					<s:iterator value="priceList" id="list" status="pageStatus">
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
						${list.partNumber}
					</td>
					<td>
						${list.name}
					</td>
					<td>
						${list.type}
					</td>
					<td>
						${list.specification}
					</td>
					<td>
						${list.productCategory}
					</td>
					<td>
						${list.hsPrice}
					</td>
					<td>
						${list.bhsPrice}
					</td>
					<td>
						${list.taxprice}
					</td>
					<td>
						${list.produceType}
					</td>
					<td>
						${list.contractNumber}
					</td>
					<td>
						${list.chargePerson}
					</td>
					<td>
						${list.pricePeriodStart}
					</td>
					<td>
						${list.pricePeriodEnd}
					</td>
					<td>
						${list.inputPeople}
					</td>
					<td>
						<s:iterator id="cu" value="cmList">
							<s:if test="#cu.id==#list.kehuId">
								${cu.clientcompanyname}
							</s:if>
						</s:iterator>
					</td>
					<td>
						<a href="javascript:;" onclick="xgprice(${list.id})" >修改价格</a>
					</td>
					</s:iterator>
				<tr>
						<s:if test="errorMessage==null">
							<td colspan="17" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="18" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function xgprice(id){
	document.getElementById("xiugaiIframe").src="PriceAction!findPriceById?id="+id+"&statue=xgprice";
	chageDiv('block');
}

</SCRIPT>
	</body>
</html>
