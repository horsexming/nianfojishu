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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					订单管理
				</h3>
				<br />
				<form
					action="orderManager_queryOrderManagerByCondition.action?status=check"
					method="post">
					<table class="table">
						<tr>
							<td>
								订单编号：
								<input type="text" name="orderNum" value="${orderNum}" />
							</td>
							<td>
								交付状态：
								<input type="text" name="deliveryStatus"
									value="${deliveryStatus}" />
							</td>
							<td>
								跟单人：
								<input type="text" name="documentaryPeople"
									value="${documentaryPeople}" />
							</td>
							<td>
								客户：
								<select name="customeId">
									<option value="0" selected="selected">
										选择用户
									</option>
									<s:iterator id="c" value="clients">
										<s:if test="#c.id == customeId">
											<option value="${c.id}" selected="selected">
												${c.clientcompanyname}
											</option>
										</s:if>
										<s:else>
											<option value="${c.id}">
												${c.clientcompanyname}
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								开单人：
								<input type="text" name="billingPeople" value="${billingPeople}" />
							</td>
							<td>
								开始日期：
								<input style="width: 155px" class="Wdate" type="text"
									name="beginTime" value="${beginTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								结束日期：
								<input style="width: 155px" class="Wdate" type="text"
									name="endTime" value="${endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								<input type="hidden" name="errorMessage" value="all" />
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							订单编号
						</td>
						<td align="center">
							总金额
						</td>
						<td align="center">
							客户
						</td>
						<td align="center">
							交付日期
						</td>
						<td align="center">
							跟单人
						</td>
						<td align="center">
							开单人
						</td>
						<td align="center">
							交付状态
						</td>
						<td align="center">
							回款状态
						</td>
						<td></td>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
						<tr>
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
							${pageList.orderNum}
						</td>
						<s:if test="#pageList.totalAmount == 0.0">
							<td>
								0.00
							</td>
						</s:if>
						<s:else>
							<td>
								${pageList.totalAmount }
							</td>
						</s:else>
						<td>
							${pageList.custome.clientcompanyname}
						</td>
						<td>
							${pageList.paymentDate}
						</td>
						<td>
							${pageList.documentaryPeople }
						</td>
						<td>
							${pageList.billingPeople}
						</td>
						<td>
							${pageList.deliveryStatus }
						</td>
						<td>
							${pageList.backSection }
						</td>
						<td>
							<input type="button" value="产品"
								style="width: 70px; height: 30px;"
								onclick="detail(${pageList.id})" />
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="10" align="right">
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
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			function detail(id){
				window.location="orderManager_queryDetail.action?id="+id+"&status=inner";
			}
		</SCRIPT>
	</body>
</html>
