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
				<s:if test="sumProcessList!=null">
				<table class="table">
				<tr >
					<td colspan="15" align="center" style="color: blue">
						报价单
					</td>
				</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							表头
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							业务件号
						</td>
						<td align="center">
							需要报价的数量
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							开始时间
						</td>
						<td align="center">
							结束时间
						</td>
						<td align="center">
							周期
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="sumProcessList" id="list1"
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
							${list1.title}
						</td>
						<td align="center">
							${list1.rootMark}
						</td>
						<td align="center">
							${list1.ywmarkId}
						</td>
						<td align="center">
							${list1.count}
						</td>
						<td align="center">
							${list1.addTime}
						</td>
						<td align="center">
							${list1.bjStartDate}
						</td>
						<td align="center">
							${list1.bjEndDate}
						</td>
						<td align="center">
						<s:if test="#list1.cycle!=null">
							${list1.cycle}天
						</s:if>
						</td>
						<td align="center">
							${list1.stutas}
						</td>
						<td align="center">
							<a onclick="baojia(${list1.id})">报价单详情</a>
							<a onclick="downloadAll(${list1.id})">图纸下载</a>
						</td>
					</s:iterator>
					<s:if test="tishi!=null">
						<td colspan="12" align="center" style="color: red">
								${tishi}
						</td>
					</s:if>
				</table>
				</s:if>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							状态
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
							${list.processNO}
						</td>
						<td align="center">
							${list.processName}
						</td>
						<td align="center">
							${list.status}
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
							<a onclick="tuzhi(${list.id})">下载图纸</a>/
							<s:if test='#list.status=="未报价"'>
								<a onclick="update(${list.id},'${cpage}')">报价</a>
							</s:if>
							<s:elseif test='#list.status=="已报价"'>
								<a onclick="update(${list.id},'${cpage}')">修改报价</a>
							</s:elseif>
							<s:elseif test='#list.status=="打样"'>
								<a onclick="tijiao(${list.id},'${cpage}')">样品报告</a>
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
	window.location.href = "NoPriceprocessAction_baojiaForzhUser.action?zhuserOffer.id="
			+ id;
}
function tuzhi(id, cpage) {
	window.location.href = "ZhuserOfferAction_getwgOrderTz.action?zhuserOffer.id="
			+ id;
}
function baojia(id) {
	window.location.href = "NoPriceprocessAction_findZhuserForSumProcessId.action?id="
			+ id;
}
function downloadAll(id, cpage) {
	window.location.href = "ZhuserOfferAction_daochutz.action?sumProcess.id="
			+ id;
}

function tijiao(id, cpage) {
	window.location.href = "ZhuserOfferAction_findZhOfferForDayang.action?zhuserOffer.id="
			+ id + "&cpage=" + cpage+"&status=gongxu";
}
</script>
	</body>
</html>
