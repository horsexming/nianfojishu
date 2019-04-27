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
<STYLE type="text/css">
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<a href="WaigouwaiweiPlanAction!findReturnSingle.action?pageStatus=${pageStatus}&tag=${tag}">退货单模式</a>&nbsp;&nbsp;&nbsp;&nbsp;<span><b>退货单明细模式</b></span>
				<form action="WaigouwaiweiPlanAction!findReturnsDetails.action"
					method="post">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" name="rds.markId" value="${rds.markId}" />
							</td>
							<th align="right">
								版本
							</th>
							<td>
								<input type="text" name="rds.banben" value="${rds.banben}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								零件名称
							</th>
							<td>
								<input type="text" name="rds.proName" value="${rds.proName}" />
							</td>
							<th align="right">
								物料类别
							</th>
							<td>
								<input type="text" name="rds.wgType" value="${rds.wgType}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								供应商
							</th>
							<td>
								<input type="text" name="rds.gysName" value="${rds.gysName}" />
							</td>
							<th align="right">
								状态
							</th>
							<td>
								<SELECT name="rds">
									<option value="${rds.status}">
										${rds.status}
									</option>
									<option value="待领">
										待领
									</option>
									<option value="已领">
										已领
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								批准时间从
							</th>
							<td>
								<input class="Wdate" type="text" name="startDate"
									value="${startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								到
							</th>
							<td>
								<input class="Wdate" type="text" name="endDate"
									value="${endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								采购单号
							</th>
							<td>
								<input type="text" value="${rds.cgOrderNum}" name="rds.cgOrderNum" />
							</td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="hidden" value="${tag}" name="tag" />
					<input type="submit" value="查询" class="input" />
					<input type="button" value="导出" class="input"  onclick="exprotrds(this.form);todisabledone(this)" data="downData"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							采购单号
						</th>
						<th>
							送货单号
						</th>
						<th>
							件号
						</th>
						<th>
							零件名称
						</th>
						<th>
							供料属性
						</th>
						<th>
							物料类别
						</th>
						<th>
							规格
						</th>
						<th>
							检验批次
						</th>
						<th>
							退货数量
						</th>
						<th>
							单价（含税）
						</th>
						<th>
							金额
						</th>
						<th>
							供应商名称
						</th>
						<th>
							退货类型
						</th>
						<th>
							批准时间
						</th>
						<th>
							状态
						</th>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="list" id="pageList" status="statussdf">
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
							${pageList.cgOrderNum}
						</td>
						<td>
							${pageList.shOrderNum}
						</td>
						<td>
							${pageList.markId}
						</td>
						<td style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${pageList.proName}</font>
									<ul class="qs_ul">
										<li>
											${pageList.proName}
										</li>
							</ul>
						</td>
						<td>
							${pageList.kgliao}
						</td>
						<td>
							${pageList.wgType}
						</td>
						<td style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${pageList.specification}</font>
									<ul class="qs_ul">
										<li>
											${pageList.specification}
										</li>
							</ul>
						</td>
						<td>
							${pageList.examineLot}
						</td>
						<td>
							${pageList.thNumber}
						</td>
						<td>
							${pageList.hsPrice}
						</td>
						<td>
							-<fmt:formatNumber value="${pageList.thNumber*pageList.hsPrice}" pattern="#.####"></fmt:formatNumber>
						</td>
						<td>
							${pageList.gysName}
						</td>
						<td>
							${pageList.returnSingle.type}
						</td>
						<td>
							${pageList.approvalTime}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
							<a
								href="WaigouwaiweiPlanAction!findReturnsDetailsByrsId.action?id=${pageList.returnSingle.id}&pageStatus=${pageStatus}">退货单</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="20" align="right">
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
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function exprotrds(obj){
	$(obj).attr("action","WaigouwaiweiPlanAction!exportrds.action");
	$(obj).submit();
	$(obj).attr("action","WaigouwaiweiPlanAction!findReturnSingle.action?pageStatus=${pageStatus}");
}

</SCRIPT>
	</body>
</html>
