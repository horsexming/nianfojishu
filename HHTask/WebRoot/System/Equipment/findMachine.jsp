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
		<center>
			<div>
				<br />
				<s:if test="machine!=null">
					<table class="table">
						<tr>
							<th colspan="20" align="center"
								style="font-family: 微软雅黑; font-weight: bold;">
								设备信息
							</th>
						</tr>
						<tr>
							<th>
								工区
							</th>
							<td>
								${machine.workArea}
							</td>
							<th>
								工位
							</th>
							<td>
								${machine.workPosition}
							</td>
							<th>
								设备编号
							</th>
							<td>
								${machine.no}
							</td>
						</tr>
						<tr>
							<th>
								设备类型
							</th>
							<td>
								${machine.type}
							</td>

							<th>
								设备名称
							</th>

							<td>
								${machine.name}
							</td>
							<th>
								所在班组
							</th>
							<td>
								${machine.classGroup}
							</td>
						</tr>

						<tr>
							<th>
								折旧年限
							</th>
							<td>
								${machine.depreciationYear}

							</td>
							<th>
								购买时间
							</th>
							<td>
								${machine.buytime}
							</td>
							<th>
								购买金额
							</th>
							<td>
								${machine.buyamount}
							</td>
						</tr>
					</table>
					<br />
					<div style="color: #00FF00; font-size: 20px; font-weight: bolder;">
						<a href="ProdEquipmentAction!findMachineByNum.action?id=${id}">本次设备能耗:${nowSbNh}kw/h</a>
					</div>
					<br />
					<table class="table">
						<tr>
							<th colspan="20">
								设备报修记录
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">

							<th align="center">
								编号
							</th>
							<th align="center">
								工区
							</th>
							<th align="center">
								工位
							</th>
							<th align="center">
								设备编码
							</th>
							<th align="center">
								设备类型
							</th>
							<th align="center">
								设备名称
							</th>

							<th align="center">
								报修时间
							</th>
							<th align="center">
								所在班组
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								修理反馈
							</th>
							<th align="center">
								修理人
							</th>
							<th align="center">
								修复时间
							</th>
							<th align="center">
								确认时间
							</th>
							<th align="center" width="100px">
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageMaintenance" status="pageStatus">
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
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>


							<td>
								${pageMaintenance.workArea}
							</td>
							<td>
								${pageMaintenance.workPosition}
							</td>
							<td>
								${pageMaintenance.no}

							</td>

							<td>
								${pageMaintenance.type}
							</td>
							<td>
								${pageMaintenance.name}
							</td>
							<td>
								<fmt:formatDate value="${pageMaintenance.alarmTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								${pageMaintenance.classGroup}
							</td>
							<td>
								${pageMaintenance.status}
							</td>
							<td>
								${pageMaintenance.faultReason}
							</td>
							<td>
								${pageMaintenance.repairMan}
							</td>
							<td>
								${pageMaintenance.timetorepair}
							</td>
							<td>
								${pageMaintenance.persontime}
							</td>
							<td>
								<a
									href="EquipmentAction!findByclintMangagement.action?id=<s:property value="id" />">明细</a>
							</td>
							<s:if test="#pageStatus.last">

							</s:if>
						</s:iterator>
					</table>
				</s:if>
				<s:else>
					<table class="table">
						<tr>
							<th colspan="11">
								物料确认记录
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th>
								员工号
							</th>
							<th>
								员工姓名
							</th>
							<th>
								确认时间
							</th>
							<th>
								确认数量
							</th>
						</tr>
						<s:iterator id="pageProcesswlqr" value="ListProcesswlqr" status="pageIndex0">
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 30px;" onmouseout="outBgcolor(this,'')"
								onclick="clickBgcolor(this);showproLog(this,'${pagePro.id}')">
								<th>
									${pageIndex0.index+1}
								</th>
								<th>
									${pageProcesswlqr.addUsersCode}
								</th>
								<th>
									${pageProcesswlqr.addUsers}
								</th>
								<th>
									${pageProcesswlqr.addTime}
								</th>
								<th>
									${pageProcesswlqr.count}
								</th>
							</tr>
						</s:iterator>
					</table>
					<hr/>
					<table class="table">
						<tr>
							<th colspan="12">
								工序领取记录
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th>
								员工号
							</th>
							<th>
								员工姓名
							</th>
							<th>
								辅助人员
							</th>
							<th>
								领取时间
							</th>
							<th>
								提交时间
							</th>
							<th>
								领取数量
							</th>
							<th>
								提交数量
							</th>
							<th>
								本次节拍(S)
							</th>
							<th>
								生产能耗(kw/h)
							</th>
							<th>
								待机能耗(kw/h)
							</th>
							<th>
								生产状态
							</th>
						</tr>
						<s:iterator id="pagePro" value="proList" status="pageIndex">
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 30px;" onmouseout="outBgcolor(this,'')"
								onclick="clickBgcolor(this);showproLog(this,'${pagePro.id}')">
								<th>
									${pageIndex.index+1}
								</th>
								<th>
									${pagePro.usercodes}
								</th>
								<th>
									${pagePro.usernames}
								</th>
								<th>
									${pagePro.fzworkname}
								</th>
								<th>
									${pagePro.firstApplyDate}
								</th>
								<th>
									${pagePro.sumitApplyDate}
								</th>
								<th>
									${pagePro.receiveNumber}
								</th>
								<th>
									${pagePro.submitNumber}
								</th>
								<th>
									${pagePro.allJiepai}s
								</th>
								<th>
									${pagePro.allNenghao}
								</th>
								<th>
									${pagePro.allDjNenghao}
								</th>
								<th>
									${pagePro.status}
								</th>
							</tr>
						</s:iterator>
					</table>
					
					<s:if test="flList!=null&&flList.size()>0">
					<br />
					<table class="table">
							<tr>
								<th colspan="10">
									辅料使用记录
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px">
								<td align="center">
									序号
								</td>
								<td align="center">
									名称
								</td>
								<td align="center">
									类别
								</td>
								<td align="center">
									规格
								</td>
								<td align="center">
									数量
								</td>
								<td align="center">
									单位
								</td>
							</tr>
							<s:iterator value="flList" id="flPage"
								status="flpageStatus">
								<s:if test="#flpageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#flpageStatus.index+1" />
								</td>
								<td>
									${flPage.name}
								</td>
								<td>
									${flPage.type}
								</td>
								<td>
									${flPage.specification}
								</td>
								<td>
									${flPage.outCount}
								</td>
								<td>
									${flPage.unit}
								</td>
								</tr>
							</s:iterator>
						</table>
					</s:if>
					<s:if test="psaveLogList!=null&&psaveLogList.size()>0">
					<br />
					<table class="table">
							<tr>
								<th colspan="10">
									半成品入库记录
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px">
								<td align="center">
									序号
								</td>
								<td align="center">
									库别
								</td>
								<td align="center">
									仓区
								</td>
								<td align="center">
									库位
								</td>
								<td align="center">
									数量
								</td>
								<td align="center">
									时间
								</td>
								<td align="center">
									人员
								</td>
							</tr>
							<s:iterator value="psaveLogList" id="saveLogPage"
								status="slpageStatus">
								<s:if test="#slpageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#slpageStatus.index+1" />
								</td>
								<td>
									${saveLogPage.warehouse}
								</td>
								<td>
									${saveLogPage.goodHouseName}
								</td>
								<td>
									${saveLogPage.goodsStorePosition}
								</td>
								<td>
									${saveLogPage.ccCount}
								</td>
								<td>
									${saveLogPage.addTime}
								</td>
								<td>
									${saveLogPage.addUser}
								</td>
								</tr>
							</s:iterator>
						</table>
					</s:if>
					<s:if test="airList!=null">
						<br />
						<table class="table">
							<tr>
								<th colspan="10">
									气密测试记录
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px">
								<td align="center">
									序号
								</td>
								<td align="center">
									件号
								</td>
								<td align="center">
									产品序号
								</td>
								<td align="center">
									泄漏量
								</td>
								<td align="center">
									压力值
								</td>
								<td align="center">
									条码内容
								</td>
								<td align="center">
									其他标识
								</td>
								<td align="center">
									操作者
								</td>
							</tr>
							<s:iterator value="airList" id="airtightLogPage"
								status="pageStatus">
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
									${airtightLogPage.markId}
								</td>
								<td>
									${airtightLogPage.number}
								</td>
								<td>
									${airtightLogPage.xielou}
								</td>
								<td>
									${airtightLogPage.yali}
								</td>
								<td>
									${airtightLogPage.context}
								</td>
								<td>
									${airtightLogPage.otherContext}
								</td>
								<td>
									${airtightLogPage.operator}
								</td>
								</tr>
							</s:iterator>
						</table>

					</s:if>
				</s:else>

			</div>
		</center>
		<script type="text/javascript">
//展示历史模版
var num = 0;
var oldobj;
var count = 0;
function showproLog(obj, code) {
	for ( var i = 0; i < num; i++) {
		$("#showWs").remove();
	}
	if (oldobj == obj) {
		if (count == 1) {
			count = 0;
			return;
		}
	} else {
		count = 0;
	}
	oldobj = obj;
	count++;
	$
			.ajax( {
				type : "POST",
				url : "ProdEquipmentAction!findPIRLogByFk_pirLId.action",
				data : "id=" + code,
				dataType : "json",
				success : function(msg) {
					var oldWageS = "";
					$(msg)
							.each(
									function(i, n) {
										num++;
										oldWageS += "<tr style='background: #FFFFCC' id='showWs'><th>"
												+ (i + 1)
												+ "</th><th>"
												+ n.usercodes
												+ "</th><th>"
												+ n.usernames
												+ "</th><th>"
												+ n.firstApplyDate
												+ "</th><th>"
												+ n.sumitApplyDate
												+ "</th><th>"
												+ n.receiveNumber
												+ "</th><th>"
												+ n.submitNumber
												+ "</th><th>"
												+ n.allJiepai
												+ "</th><th>"
												+ n.allNenghao
												+ "</th><th></th><th>"
												+ n.status + "</th></tr>";
									});
					$(obj).after(oldWageS);
				}
			});
}
</script>
	</body>
</html>
