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
		<div id="gongneng" style="width: 100%;">
			<div align="left">
				<div id="printDiv">
					<br />
					<div
						style="font-weight: bolder; font-size: 30px; margin-bottom: 10px;"
						align="center">
						<s:if test="pageStatus=='ww'">
							外委
						</s:if>
						<s:elseif test="pageStatus=='yf'">
							研发
						</s:elseif>
						<s:else>
							外购
						</s:else>
						待检验批次
					</div>
					<form action="WaigouwaiweiPlanAction!findDjyDelivery.action?pageStatus=${pageStatus}" method="post">
						<table>
							<tr>
								<td>
									供应商:
								</td>
								<td>
									<input type="text"
										style="width: 120px;" name="waigoudd.gysName" value="${waigoudd.gysName}"/>
								</td>
								<td>
									件号：
								</td>
								<td>
									<input type="text"
										style="width: 120px;" name="waigoudd.markId" value="${waigoudd.markId}"/>
								</td>
								<s:if test="pageStatus=='ww'">
									<td>产品编码：</td>
									<td>
										<input type="text"
											style="width: 120px;" name="waigoudd.strs" value="${waigoudd.strs}"/>
									</td>
								</s:if>
								
							</tr>
							<tr>
								<td>
									签收时间：
								</td>
								<td>
									<input type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										style="width: 120px;" name="waigoudd.querenTime" value="${waigoudd.querenTime}"/>
								</td>
								<td>
									下工序：
								</td>
								<td>
									<input type="text"  style="width: 120px;" name="waigoudd.nextProcessName" value="${waigoudd.nextProcessName}"/>
								</td>
								<td colspan="2" align="center">
									<input type="submit" value="查询" class="input"/> &nbsp;
								</td>
							</tr>
						</table>
					</form>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								供应商
							</th>
							<th align="center">
								产品编码
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								零件名称
							</th>
							<s:if test="pageStatus=='ww'">
							<th align="center">
								委外工序
							</th>
							<th align="center">
								下工序
							</th>
							</s:if>
							<th align="center">
								版本
							</th>
							<th align="center">
								物料类别
							</th>
							<th align="center">
								供料属性
							</th>
							<th align="center">
								图号
							</th>
							<th align="center">
								规格
							</th>
							<th align="center">
								类型
							</th>
							<th align="center">
								数量
							</th>
							<th align="center">
								箱数
							</th>
							<th align="center">
								物料位置
							</th>
							<th align="center">
								检验批次
							</th>
							<th align="center">
								签收时间
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageWgww2" status="pageStatus2">
							<s:if test="#pageStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus2.index+1" />
							</td>
							<td>
								<s:if test="#pageWgww2.gysjc==null||pageWgww2.gysjc==''">
									${pageWgww2.gysName}
								</s:if>
								<s:else>
									${pageWgww2.gysjc}
								</s:else>
							</td>
							<td>
								${pageWgww2.strs}
							</td>
							<td>
								${pageWgww2.markId}
							</td>
							<td >
								${pageWgww2.proName}
							</td>
							<s:if test="pageStatus=='ww'">
							<td>
								${pageWgww2.processName}
							</td>
							<td >
								${pageWgww2.nextProcessName} 
							</td>
							</s:if>
							<td>
								<s:if test="#pageWgww2.banben!=null&&#pageWgww2.banben!='null'">
								${pageWgww2.banben}
								</s:if>
							</td>
							<td>
								${pageWgww2.wgType}
							</td>
							<td>
								${pageWgww2.kgliao}
							</td>
							<td>
								${pageWgww2.tuhao}
							</td>
							<td>
								${pageWgww2.specification}
							</td>
							<td>
								${pageWgww2.type}
							</td>
							<td >
								${pageWgww2.qrNumber}${pageWgww2.unit}
							</td>
							<td>
								${pageWgww2.ctn}
							</td>
							<td>
								${pageWgww2.qrWeizhi}
							</td>
							<td>
								${pageWgww2.examineLot}
							</td>
							<td>
								${pageWgww2.querenTime}
							</td>
							<td>
								<div
									style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
									<br />
									<a href="WaigouwaiweiPlanAction!toCheckwgww.action?id=${pageWgww2.id}&pageStatus=${pageStatus}&cpage=${cpage}" 
										style=" font-size: 22px;color: white;"
										>检验</a>
								</div>
							</td>
						</s:iterator>
						<s:if test="list.size()<=0">
							<tr>
							<s:if test="pageStatus=='ww'">
							<th colspan="24">
							</s:if>
							<s:else>
							<th colspan="22">
							</s:else>
								没有待检验任务,可以休息一会儿了~</th>
							</tr>
						</s:if>
						<s:else>
							<tr>
							<s:if test="pageStatus=='ww'">
							<th colspan="24" align="right">
							</s:if>
							<s:else>
							<th colspan="22" align="right">
							</s:else>
								第
								<font size="14" color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</tr>
						</s:else>
					</table>
				</div>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	window.location.href = "WaigouwaiweiPlanAction!findDeliveryDeByBacode.action?bacode="
			+ tm;
}
</script>
	</body>
</html>
