<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<s:if test="waigouOrder!=null">
					<table class="table" style="width: 50%">
						<tr>
							<th colspan="2">
								采购订单明细
							</th>
						</tr>
						<tr>
							<th align="right">
								供应商编号:
							</th>
							<td>
								${waigouOrder.userCode}
							</td>
						</tr>
						<tr>
							<th align="right">
								供应商:
							</th>
							<td>
								${waigouOrder.gysName}
							</td>
						</tr>
						<tr>
							<th align="right">
								采购月份:
							</th>
							<td>
								${waigouOrder.caigouMonth}
							</td>
						</tr>
						<tr>
							<th align="right">
								订单状态:
							</th>
							<td>
								${waigouOrder.status}
							</td>
						</tr>
						<tr>
							<th align="right">
								计划单号:
							</th>
							<td>
								${waigouOrder.planNumber}
							</td>
						</tr>
						<tr>
							<th align="right">
								添加日期:
							</th>
							<td>
								${waigouOrder.addTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								通知日期:
							</th>
							<td>
								${waigouOrder.tongzhiTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								确认采购日期:
							</th>
							<td>
								${waigouOrder.querenTime}
							</td>
						</tr>
					</table>
					<br />
					<br />
				</s:if>
				<s:else>
					<form action="WaigouwaiweiPlanAction!findFllScz.action"
						method="post">
						<ul
							style="float: left; text-align: center; text-align: left; font-weight: 200; color: red;">
							<li>
								多件号查询请用换行、空格、;等分开，输入多个件号后点击查询
							</li>
							<li>
								如：1.03.10002;1.03.10016
							</li>
							<li>
								件号必须完整输入不能错不能少。
							</li>
						</ul>
						<table class="table">
							<tr>
								<th>
									件号:
								</th>
								<td>
									<textarea rows="4" cols="28" name="waigouPlan.markId">${waigouPlan.markId}</textarea>
								</td>
								<th>
									订单号:
								</th>
								<td>
									<textarea rows="4" cols="28"
										name="waigouPlan.waigouOrder.planNumber">${waigouPlan.waigouOrder.planNumber}</textarea>
								</td>
								<th>
									物料类别:
								</th>
								<td>
									<textarea rows="4" cols="28" name="waigouPlan.wgType">${waigouPlan.wgType}</textarea>
								</td>
								<th>
									每页条数:
								</th>
								<td>
									<select name="pageSize">
										<option value="${pageSize}">
											${pageSize}
										</option>
										<option value="15">
											15
										</option>
										<option value="30">
											30
										</option>
										<option value="50">
											50
										</option>
										<option value="80">
											80
										</option>
										<option value="100">
											100
										</option>
									</select>
								</td>
								<th>
									订单类型:
								</th>
								<td>
									<select name="waigouPlan.type">
										<option value="${waigouPlan.type}">
											${waigouPlan.type}
										</option>
										<option value="外购">
											外购
										</option>
										<option value="外委">
											外委
										</option>
										<option value="辅料">
											辅料
										</option>
									</select>
								</td>
								<td>
									<input type="submit" value="查询" class="input">
								</td>
							</tr>
						</table>
					</form>
				</s:else>
				<form action="WaigouwaiweiPlanAction!orderToSonghuo.action"
					method="post" onsubmit="return checkOK()">
					<table class="table">
						<tr>
							<th align="left" colspan="18">
								<s:if test="wwPlanList!=null&&wwPlanList.size()>0">
									<input type="submit" value="申请送货" class="input" />
									<br />
								</s:if>
							</th>
						</tr>
						<th align="right">
							<input type="checkbox" onclick="chageAllCheck(this)"
								id="checkAll">
						</th>
						<th align="right">
							序号
						</th>
						<th align="center">
							订单类型
						</th>
						<th align="center">
							订单号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							零件名称
						</th>
						<th align="center">
							产品编码
						</th>
						<th align="center">
							规格
						</th>
						<th align="center">
							图号
						</th>
						<th align="center">
							版本
						</th>
						<th align="center">
							供货属性
						</th>
						<th align="center">
							交付进度
							<br />
							(
							<font style="font-size: 5px;">待交付/总数量</font>)
						</th>
						<th align="center">
							总额(含税)
						</th>
						<th align="center">
							库存
						</th>
						<th align="center">
							协商交付日期
						</th>
						<th align="center">
							应到货时间
						</th>
						<th align="center">
							产品状态
						</th>
						<th align="center">
							图纸
						</th>
						</tr>
						<s:iterator value="wwPlanList" id="pageWgww2" status="pageStatus2">

							<s:if test="#pageStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="right">
								<input type="checkbox" name="processIds" value="${pageWgww2.id}"
									onclick="chageNum(this)">
							</td>
							<td align="right">
								<s:property value="#pageStatus2.index+1" />
							</td>
							<td align="left">
								${pageWgww2.type}
							</td>
							<td>
								<a
									href="WaigouwaiweiPlanAction!findWgPlanList.action?id=${pageWgww2.waigouOrder.id}&pageStatus=gysall">
									${pageWgww2.waigouOrder.planNumber}</a>
							</td>
							<td align="left">
								${pageWgww2.markId}
							</td>
							<td align="left">
								${pageWgww2.proName}
							</td>
							<td align="left">
								${pageWgww2.ywmarkId}
							</td>
							<td align="left">
								${pageWgww2.specification}
							</td>
							<td>
								${pageWgww2.tuhao}
							</td>
							<td>
								${pageWgww2.banben}
							</td>
							<td>
								${pageWgww2.kgliao}
							</td>
							<th align="right">
								${pageWgww2.syNumber}/
								<br />
								${pageWgww2.number}&nbsp;&nbsp;
							</th>
							<td align="right">
								<fmt:formatNumber type="number" value="${pageWgww2.money}"
									pattern="0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								${pageWgww2.kuCunCount}
							</td>
							<th align="right">
								${pageWgww2.jiaofuTime}
							</th>
							<td align="right">
								${pageWgww2.acArrivalTime}
							</td>
							<td>
								${pageWgww2.status}
							</td>
							<td>
								<input type="button" value="查看图纸" style="height: 35px;"
									onclick="javascript:location.href='WaigouwaiweiPlanAction!gysTzview2.action?id=${pageWgww2.id}';">

							</td>
							</tr>
						</s:iterator>
						<tr>
							<th align="right" colspan="10">
								<font size="2">待交付数量合计</font>:
							</th>
							<th align="right">
								${sumNum }
							</th>
							<th>
								金额合计:
								<br />
								<fmt:formatNumber type="number" value="${sumMoney }" pattern="0.00" maxFractionDigits="2"/>
							</th>
							<td colspan="5"></td>
						</tr>
						<tr>
							<th align="right">
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll2">
							</th>
							<td colspan="18" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
						<tr>
							<th colspan="18">
								<s:if test="wwPlanList!=null&&wwPlanList.size()>0">

									<input type="submit" value="申请送货" class="input" />
									<br />
								(点击后进入数量确认)
								</s:if>
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
function checkOK() {
	//多选不能为空
	var cheke = document.getElementsByName("processIds");
	var sbdate = "";
	for ( var i = 0; i < cheke.length; i++) {
		if (cheke[i].checked == true) {
			sbdate += cheke[i].value + ",";
		}
	}
	if (sbdate == "") {
		alert("请选择要送货的物品!");
		return false;
	}
}
</script>
</html>
