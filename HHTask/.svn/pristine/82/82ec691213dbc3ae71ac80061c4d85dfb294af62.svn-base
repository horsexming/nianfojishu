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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="projectOrderAction_showList.action" style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					项目需求单管理
					<br />
					(project order Management)
				</h3>
				<form action="projectOrderAction_showList.action" method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								项目名称（project name）：
								<input type="text" name="projectOrder.proName"
									value="<s:property value="projectOrder.proName"/>" />
							</td>

							<td align="center">
								试制单编号（order number）：
								<input type="text" name="projectOrder.orderNO"
									value="<s:property value="projectOrder.orderNO"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								客户名称（customer name）：
								<input type="text" name="projectOrder.cusName"
									value="<s:property value="projectOrder.cusName"/>" />
							</td>
							<td align="center">
								项目处理（deal with）：
								<SELECT name="projectOrder.deal">
									<s:if test="projectOrder.deal==1">
										<option value="1">
											冲抵生产计划,入库销售
										</option>
										<option value="0">
											不选
										</option>
										<option value="2">
											试验用,不予入库
										</option>
									</s:if>
									<s:if test="projectOrder.deal==2">
										<option value="2">
											试验用,不予入库
										</option>
										<option value="0">
											不选
										</option>
										<option value="1">
											冲抵生产计划,入库销售
										</option>
									</s:if>
									<s:if test="projectOrder.deal==null">
										<option value="0">
											不选
										</option>
										<option value="1">
											冲抵生产计划,入库销售
										</option>
										<option value="2">
											试验用,不予入库
										</option>
									</s:if>
									<s:if test="projectOrder==null">
										<option value="0">
											不选
										</option>
										<option value="1">
											冲抵生产计划,入库销售
										</option>
										<option value="2">
											试验用,不予入库
										</option>
									</s:if>

								</SELECT>
							</td>
						</tr>
						<tr>
							<td align="center">
								审批状态（project name）：
								<SELECT name="projectOrder.status">
									<s:if test="projectOrder.status!=null">
										<option value="<s:property value="projectOrder.status"/>">
											<s:property value="projectOrder.status" />
										</option>
									</s:if>
									<option>
										全部
									</option>
									<option>
										未申请
									</option>
									<option>
										未审批
									</option>
									<option>
										审批中
									</option>
									<option>
										同意
									</option>
									<option>
										打回
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加项目需求单(add)" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>
				<table width="100%" border="0" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							组别
							<br />
							(group name)
						</td>
						<td align="center">
							项目名称
							<br />
							(project name)
						</td>
						<td align="center">
							月份
							<br />
							(month)
						</td>
						<td align="center">
							试制单编号
							<br />
							（order number）
						</td>
						<td align="center">
							审批状态
							<br />
							（status）
						</td>
						<td align="center">
							产品工程师
							<br />
							（product engineer）
						</td>
						<td align="center">
							技术工程师
							<br />
							（technical engineer）
						</td>
						<td align="center">
							客户名称
							<br />
							（customer name）
						</td>
						<td align="center">
							项目依据
							<br />
							（project Based）
						</td>
						<td align="center">
							项目目的
							<br />
							（project intent）
						</td>
						<td align="center">
							交付信息
							<br />
							（delivery infomation）
						</td>
						<td align="center">
							项目处理
							<br />
							（deal with）
						</td>
						<td align="center">
							备注
							<br />
							（remark）
						</td>
						<td align="center" colspan="4">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<s:iterator value="pOrderList" id="pOrder" status="pageStatus">
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
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pOrder.groupName}
						</td>
						<td>
							${pOrder.proName}
						</td>
						<td>
							${pOrder.month}
						</td>
						<td>
							${pOrder.orderNO}
						</td>
						<td>
							${pOrder.status}
						</td>
						<td>
							${pOrder.productEngineer}
						</td>
						<td>
							${pOrder.technicalEngineer}
						</td>
						<td>
							${pOrder.cusName}
						</td>
						<td>
							${pOrder.projectBy}
						</td>
						<td>
							${pOrder.projectTo}
						</td>
						<td>
							${pOrder.deliveryInfo}
						</td>
						<td>
							<s:if test="#pOrder.deal!=null&&#pOrder.deal==1">
						冲抵生产计划,入库销售
						</s:if>
							<s:if test="#pOrder.deal!=null&&#pOrder.deal==2">
							试验用,不予入库
						</s:if>
						</td>
						<td>
							${pOrder.remark}
						</td>

						<td colspan="4">
							<s:if
								test="#pOrder.status==null||#pOrder.status=='未申请'||#pOrder.status=='打回'">
								<input type="button" value="申请审批"
									style="width: 60px; height: 30px;"
									onclick="approval(${pOrder.id},${cpage})" />
							</s:if>
							<s:if
								test="#pOrder.status==null||#pOrder.status=='未申请'||#pOrder.status=='未审批'||#pOrder.status=='打回'">
								<input type="button" value="修改"
									style="width: 60px; height: 30px;"
									onclick="update(${pOrder.id})" />
							</s:if>
							<s:if test="#pOrder.status!=null&&#pOrder.status=='同意'">
								<input type="button" value="试制单转生产"
									style="width: 100px; height: 30px;"
									onclick="toproduct(${pOrder.id})" />
							</s:if>
							<input type="button" value="删除"
								style="width: 60px; height: 30px;"
								onclick="todelete(${pOrder.id},${cpage})" />
							<input type="button" value="明细"
								style="width: 60px; height: 30px;"
								onclick="todetail(${pOrder.id})" />
							<input type="button" value="审批动态"
								style="width: 60px; height: 30px;"
								onclick="showshenpi(${pOrder.epId})" />
						</td>

					</s:iterator>
					</tr>
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

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="15" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function approval(id, cpage) {
	window.location.href = "projectOrderAction_toapproval.action?projectOrder.id="
			+ id + "&cpage=" + cpage;
}
function update(id) {
	window.location.href = "projectOrderAction_toupdate.action?projectOrder.id="
			+ id;
}
function todelete(id, cpage) {
	window.location.href = "projectOrderAction_delete.action?projectOrder.id="
			+ id + "&cpage=" + cpage;
}
function todetail(id) {
	window.location.href = "projectOrderAction_prodetail.action?projectOrder.id="
			+ id;
}
function add() {
	window.location.href = "projectOrderAction_toadd.action";
}
function showshenpi(id) {
	window.location.href = "CircuitRunAction_findAduitPage.action?id=" + id;
}
function toproduct(id) {
	window.location.href = "projectOrderAction_toProduct.action?projectOrder.id="
			+ id;
}
</script>
	</body>
</html>
