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
					所有报价表（供应商）
				</h3>
				<form action="ZhuserOfferAction_findListByZhUser.action" method="post">
					<table class="table" align="center">
						<tr>
							<th>
								件号：
							</th>
							<td align="center">
								<input type="text" name="zhuserOffer.markId"
									value="${zhuserOffer.markId}" />
							</td>
							<th>
								名称：
							</th>
							<td align="center">
								<input type="text" name="zhuserOffer.name"
									value="${zhuserOffer.name}" />
							</td>
						</tr>
						<tr>
							<th>
								规格：
							</th>
							<td align="center">
								<input type="text" name="zhuserOffer.specification"
									value="${zhuserOffer.specification}" />
							</td>
							<th>
								版本：
							</th>
							<td align="center">
								<input type="text" name="zhuserOffer.banbenhao"
									value="${zhuserOffer.banbenhao}" />
							</td>
						</tr>
						<tr>
							<th>
								物料类别：
							</th>
							<td align="center">
								<input type="text" name="zhuserOffer.wgType"
									value="${zhuserOffer.wgType}" />
							</td>
							<th>
								状态：
							</th>
							<td align="center">
									<select name="zhuserOffer.status" id="type"
									style="width: 120px;">
									<option value="${zhuserOffer.status}">
										${zhuserOffer.status}
									</option>
									<option value="未报价">
										未报价
									</option>
									<option value="已报价">
										已报价
									</option>
									<option value="确认">
										确认
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" style="width: 100px; height: 35px;"
									value="查询(select)" />
								<input type="reset" style="width: 100px; height: 35px;"
									value="重置" />
<%--								<input type="button" value="导出"--%>
<%--									style="width: 100px; height: 35px;" onclick="exportFile()" />--%>
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
							件号
						</td>
						<td align="center">
							名称
						</td>
						<td align="center">
							规格
						</td>
						<td align="center">
							版本
						</td>
						<td align="center">
							物料类别
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							开始时间
						</td>
						<td align="center">
							结束时间
						</td>
						<td align="center">
							含税价格
						</td>
						<td align="center">
							不含税价格
						</td>
						<td align="center">
							税率
						</td>
						
						<td align="center">
							操作
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list.markId}
						</td>
						<td align="center">
							${list.name}
						</td>
						<td align="center">
							${list.specification}
						</td>
						<td align="center" >
							${list.banbenhao}
						</td>
						<td align="center">
							${list.wgType}
						</td>
						<td align="center">
							${list.status}
						</td>
						<td align="center">
							${list.joinDate}
						</td>
						<td align="center">
							${list.endDate}
						</td>
						<s:if test='#list.status!="未报价"'>
							<td align="center">
								${list.hsPrice}
							</td>
							<td align="center">
								${list.bhsPrice}
							</td>
							<td align="center" >
								${list.taxprice}
							</td>
						</s:if><s:else>
						<td colspan="3">没有报价</td>
						</s:else>
						
						
						<td colspan="2">
							<a onclick="downloadFile(${list.id},'${cpage}')">下载图纸</a>
							<s:if test='#list.status=="未报价"'>
								<a onclick="update(${list.id},'${cpage}')">报价</a>
							</s:if>
							<s:elseif test='#list.status=="已报价"'>
								<a onclick="update(${list.id},'${cpage}')">修改报价</a>
							</s:elseif>
							<s:elseif test='#list.status=="打样"'>
								<a onclick="tijiao(${list.id},'${cpage}')">样品分析报告</a>
							</s:elseif>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
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
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id, cpage) {
	window.location.href = "ZhuserOfferAction_findZhOffer.action?zhuserOffer.id="
			+ id+"&operate=operate";
}
function tijiao(id, cpage) {
	window.location.href = "ZhuserOfferAction_findZhOfferForDayang.action?zhuserOffer.id="
			+ id + "&cpage=" + cpage;
}
function downloadFile(id, cpage) {
	window.location.href = "ZhuserOfferAction_getwgOrderTz.action?zhuserOffer.id="
			+ id + "&cpage=" + cpage;
}
</script>
	</body>
</html>
