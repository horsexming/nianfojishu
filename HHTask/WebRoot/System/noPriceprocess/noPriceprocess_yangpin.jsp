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
					选择需要确认样品
				</h3>
				<s:if test="sumProcessList!=null">
				<table class="table">
				<tr >
					<td colspan="15" align="center" style="color: blue">
						需要确认价格的报价单（总成）
					</td>
				</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							件号
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
							${list1.rootMark}
						</td>
						<td align="center">
							${list1.markId}
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
							<a onclick="detail(${list1.id})">报价单详情</a>
						</td>
					</s:iterator>
					<s:if test="tishi!=null">
						<td colspan="12" align="center" style="color: red">
								${tishi}
						</td>
					</s:if>
				</table>
				<hr/>
				</s:if>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							件号名称
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							委外申请数量
						</td>
						<td align="center">
							批次数量
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="noPriceprocessList" id="list"
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
							${list.rootMarkId}
						</td>
						<td align="center">
							${list.markId}
						</td>
						<td align="center">
							${list.name}
						</td>
						<td align="center">
							${list.processNO}
						</td>
						<td align="center">
							${list.processName}
						</td>
						<td align="center">
							${list.waiweiShenqiCount}
						</td>
						<td align="center">
							${list.piciCount}
						</td>
						<td align="center">
							${list.stutas}
						</td>
						<td align="center">
						<s:if test="#list.stutas=='打样中'">
							<a onclick="checkZhuser(${list.id});">确认样品</a>
						</s:if>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
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
		function checkZhuser(id) {
			window.location.href = "NoPriceprocessAction_toQueren.action?noPriceprocess.id="
				+ id + "&cpage=${cpage}"+"&pageStatus=yangpin";
		}
		function detail(id) {
			window.location.href = "NoPriceprocessAction_findZhuserForYangpin.action?id="
			+ id 
		}

</script>
	</body>
</html>
