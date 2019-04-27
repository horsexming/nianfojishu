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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					选择入库
				</h3>
				<form action="storage_selectStorage.action" method="post">
					<table class="table">
						<tr>
							<td>
								名称:
								<input type="text" name="vsto.matetag" value="${vsto.matetag}" />
							</td>
							<td>
								规格：
								<input type="text" name="vsto.format" value="${vsto.format}" />
							</td>
							<td>
								部门：
								<input type="text" name="vsto.dept" value="${vsto.dept}" />
							</td>
						</tr>
						<tr>
							<td>
								日期:
								<input style="width: 155px" class="Wdate" id="startTime"
									type="text" name="vsto.month" value="${vsto.month}"
									onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
							<td>
								编号：
								<input type="text" name="vsto.applyForNum"
									value="${vsto.applyForNum}" />
							</td>
							<td>
								状态：
								<select name="vsto.status">
									<option value="状态" selected="selected">
										选择状态
									</option>
									<s:iterator id="typeStr" value='{"采购中","入库"}'>
										<s:if test='#typeStr == vsto.status'>
											<option value="${typeStr }" selected="selected">
												${typeStr }
											</option>
										</s:if>
										<s:else>
											<option value="${typeStr }">
												${typeStr }
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type="hidden" name="errorMessage" value="all" />
								<input type="submit" value="查询"
									style="height: 50px; width: 80px;" />
							</td>
						</tr>
					</table>
				</form>
				<h3>
					入库申请详细
				</h3>
				<form action="storage_statisticalProcurement.action" method="post"
					onsubmit="return vali()">
					<table class="table">
						<tr>
							<td colspan="15">
								<input type="submit" style="width: 80px; height: 50px;"
									value="入库" />
							</td>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px" align="center">
							<td></td>
							<td>
								序号
							</td>
							<td>
								部门
							</td>
							<td>
								类别
							</td>
							<td>
								编号
							</td>
							<td>
								名称
							</td>
							<td>
								规格
							</td>
							<td>
								单位
							</td>
							<td>
								数量
							</td>
							<td>
								计划月份
							</td>
							<td>
								到货期限
							</td>
							<td>
								计划依据
							</td>
							<td>
								预算金额
							</td>
							<td>
								加急
							</td>
							<td></td>
						</tr>
						<s:iterator value="list" id="pageList" status="pageStatus">
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
								<input type="checkbox" name="vsto.selected"
									value="${pageList.id }" />
							</td>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageList.detailAppDept}
							</td>
							<td>
								${pageList.detailChildClass}
							</td>
							<td>
								${pageList.detailSeqNum}
							</td>
							<td>
								${pageList.detailAppName}
							</td>
							<td>
								${pageList.detailFormat}
							</td>
							<td>
								${pageList.detailUnit}
							</td>
							<td>
								${pageList.detailCount}
							</td>
							<td>
								${pageList.detailPlanMon}
							</td>
							<td>
								${pageList.detailArrDate}
							</td>
							<td>
								${pageList.detailPlanAcco}
							</td>
							<td>
								${pageList.detailBudgetMoney}
							</td>
							<td>
								${pageList.detailIsBusy}
							</td>
							<td>
								<a
									href="storage_getOaAppDetail.action?vsto.oaDetailId=${pageList.id}&vsto.applyForNum=${vsto.applyForNum}&vsto.jump=select&vsto.number=${pageList.id}">入库</a>
							</td>
							</tr>
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
								<td colspan="15" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>
						<tr>
							<td colspan="15">
								<input type="submit" style="width: 80px; height: 50px;"
									value="入库" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function vali() {
	var selectList = document.getElementsByName("vsto.selected");
	for ( var i = 0; i < selectList.length; i++) {
		if (selectList[i].checked) {
			return true;
		}
	}
	alert("请选择入库物品！谢谢");
	return false;
}
</script>
	</body>
</html>
