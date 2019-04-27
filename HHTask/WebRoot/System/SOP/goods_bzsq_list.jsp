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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" align="center"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="qxmIframe" src="" marginwidth="0" marginheight="0"
						frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					查询
				</h3>
				<form action="goodsAction!findGoodsbzsq.action" method="post">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${goodsBzsq.markId}"
									name="goodsBzsq.markId" />
							</td>
							<th align="right">
								生产批次
							</th>
							<td>
								<input type="text" value="${goodsBzsq.selfCard}"
									name="goodsBzsq.selfCard" />
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>

						<th>
							件号
						</th>
						<th>
							批次
						</th>
						<th>
							名称
						</th>
						<th>
							数量
						</th>
						<th>
							包装时间
						</th>
						<%--<th>
							预计开始
						</th>
						<th>
							预计结束
						</th>
						--%><th>
							实际开始
						</th>
						<th>
							实际结束
						</th>
						<th>
							领取人员
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="pagegoodsBzsq" status="pageindex">
						<s:if test="#pageindex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" id="tr${pageindex.index}"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								id="tr${pageindex.index}" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="left">
							${pageindex.index+1}
						</td>
						<td align="left">
							${pagegoodsBzsq.markId}
						</td>
						<td align="left">
							${pagegoodsBzsq.selfCard}
						</td>
						<td align="left">
							${pagegoodsBzsq.procardName}
						</td>
						<td align="right">
							${pagegoodsBzsq.count}
						</td>
						<td align="right">
							${pagegoodsBzsq.bzDate}
						</td>
						<%--<td align="right">
							${pagegoodsBzsq.yjStartTime}
						</td>
						<td align="right">
							${pagegoodsBzsq.yjEndTime}
						</td>
						--%><td align="right">
							${pagegoodsBzsq.sjStartTime}
						</td>
						<td align="right">
							${pagegoodsBzsq.sjEndTime}
						</td>
						<td align="right">
							${pagegoodsBzsq.zpbzUserName}
						</td>
						<td align="center">
						<a
									href="goodsAction!findGoodsBzSqmx.action?id=${pagegoodsBzsq.id}">包装物明细/</a>
						<a onclick="return window.confirm('该操作将删除该条包装申请以及包装物明细,是否删除?')"
								href="goodsAction!deleteall.action?id=${pagegoodsBzsq.id}">删除</a>
							<s:if test='#pagegoodsBzsq.status=="待领"'>
								<form
									action="goodsAction!updateGoodsBzSqFroLq.action?id=${pagegoodsBzsq.id}"
									method="post">
									<input id="cards${pageindex.index}_0" name="cardIds[0]">
									<input type="hidden" id="hidIndex${pageindex.index}" value="0">
									<input type="button" id="duorenButton${pageindex.index}"
										onclick="addpeople(this,${pageindex.index})" value="多人">
									<div id="fileDiv_${pageindex.index}" style="display: none;"></div>
									<input type="submit" value="领取">
								</form>
							</s:if>
							<s:elseif test='#pagegoodsBzsq.status=="已领"'>
								<a
									href="goodsAction!updateGoodsBzSqFroLq.action?id=${pagegoodsBzsq.id}">提交</a>
							</s:elseif>
							<s:else>
								<a
									onclick="printQxm('${pagegoodsBzsq.id}','${pagegoodsBzsq.markId}','${pagegoodsBzsq.selfCard}')"
									href="javascript:;">全息码打印</a>
							</s:else>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
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
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
function addpeople(obj, few) {
	var hidIndex = $("#hidIndex" + few).val();
	hidIndex++;
	var fileDiv = document.getElementById("fileDiv_" + few);
	fileDiv.style.display = "block";
	fileDivHTML = "<div align='center' id='file" + few + "_" + hidIndex
			+ "' style='padding-left:20px;' align='left'><input name='cards["
			+ hidIndex + "]' />" + "<a href='javascript:delpeople(" + few + ","
			+ hidIndex + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	$("#hidIndex" + few).val(hidIndex);
}
function delpeople(few, index) {
	$("#file" + few + "_" + index).remove();
}
function printQxm(id, markid, selfCard) {
	document.getElementById("qxmIframe").src = "<%=basePath%>System/wuliu/WarehouseNumber_kwm.jsp?number=<%=basePath%>/ProcardAction!findProcardForQx.action?id="
			+ id
			+ "&ku="
			+ markid
			+ "<br/><br/>"
			+ selfCard;
	chageDiv('block')
}
</script>
</html>
