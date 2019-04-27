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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="GoodsStoreAction!findGoodsSum.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${goodsSum.markId}" name="goodsSum.markId"/>
							</td>
							<th align="right">
								名称
							</th>
							<td>
								<input type="text" value="${goodsSum.name}" name="goodsSum.name"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								库别
							</th>
							<td>
								<select id="whView" name="goodsSum.goodsClass"
									style="width: 155px;" >
									<option>
										${goodsSum.goodsClass}
									</option>
									<option></option>
								</select>
							</td>
							<th align="right">
								月份
							</th>
							<td>
								<input type="text" value="${goodsSum.months}" name="goodsSum.months" 
								onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
											onblur="date('3')" />
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input">
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="25px">
						<th rowspan="2">序号</th>
						<th rowspan="2">月份</th>
						<th rowspan="2">件号</th>
						<th rowspan="2">名称</th>
						<th rowspan="2">库别</th>
						<th rowspan="2">单位</th>
						<th rowspan="2">规格</th>
						<th colspan="3">期初库存</th>
						<th colspan="3">本月入库</th>
						<th colspan="3">本月出库</th>
						<th colspan="3">期末库存</th>
					</tr>
					<tr align="center" bgcolor="#c0dcf2" height="25px">
						<th>数量</th>
						<th>单价</th>
						<th>金额</th>
						<th>数量</th>
						<th>单价</th>
						<th>金额</th>
						<th>数量</th>
						<th>单价</th>
						<th>金额</th>
						<th>数量</th>
						<th>单价</th>
						<th>金额</th>
					</tr>
					<s:iterator id="pageList" value="list"
								status="statussdf">
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
								<td>${pageList.months}</td>
								<td>${pageList.markId}</td>
								<td>${pageList.name}</td>
								<td>${pageList.goodsClass}</td>
								<td>${pageList.unit}</td>
								<td>${pageList.specification}</td>
								<td>
									<fmt:formatNumber value="${pageList.qichuNum}" pattern="###,###.###"></fmt:formatNumber> 
								</td>
								<td>
									<fmt:formatNumber value="${pageList.qichuPrice}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.qichuMoney}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.rukuNum}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.rukuPrice}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.rukuMoney}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.chukuNum}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.chukuPrice}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.chukuMoney}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.qimoNum}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.qimoPrice}" pattern="###,###.###"></fmt:formatNumber>
								</td>
								<td>
									<fmt:formatNumber value="${pageList.qimoMoney}" pattern="###,###.###"></fmt:formatNumber>
								</td>
							</tr>
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
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
<SCRIPT type="text/javascript">
$(function(){
	 addSelect();
})
	function addSelect() {
		$.ajax( {
			type : "POST",
			url : "GoodsStoreAction!getViewAuth.action",
			data : {},
			dataType : "json",
			success : function(msg) {
				if (msg.success) {
					for (k in msg.data) {
						$('#whView').append(
								"<option>" + msg.data[k] + "</option>");
					}
				} else {
					alert(msg.message);
				}
			}
		});

	}

</SCRIPT>
	</body>
</html>
