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
			<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width:100%; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
				<table style="width: 100%">
					<tr>
						<td>
							
						</td>
						<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				</center>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 800px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		
	
	
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
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
					内部计划管理(Internal project management)
				</h3>
				<form action="internalOrder_queryInternalOrderByCondition.action"
					method="post">
					<table>
						<tr>
							<td>
								订单编号(Order Number)：
								<input type="text" name="orderNum" value="${orderNum}" />
							</td>
							<td>
								客户(Customers)：
								<select name="customeId"
									style="margin-left: 30px; width: 160px;">
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
								开始日期(Start date)：
								<input style="width: 155px" class="Wdate" type="text"
									name="beginTime" value="${beginTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								结束日期(End Date)：
								<input style="width: 155px" class="Wdate" type="text"
									name="endTime" value="${endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								<input type="hidden" name="errorMessage" value="all" />
								<input type="hidden" name="pageStatus" value="${pageStatus}" />
								<input type="hidden" name="id" value="${id}" />
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
							<br />
							No.
						</td>
						<td align="center">
							计划编号
							<br />
							Project Number
						</td>
						<td align="center">
							客户
							<br />
							Customers
						</td>
						
						<td align="center">
							下计划时间
							<br />
							planned time
						</td>
						<td align="center">
							采购计划日期
							<br />
							Production Plan
						</td>
						<td align="center">
							跟单人
							<br />
							With a single person
						</td>
						<td align="center">
							状态
							<br />
							State
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
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
							${pageList.num }
						</td>
						<td>
							${pageList.custome.clientcompanyname}
						</td>
						<td>
							${pageList.genertorDate}
						</td>
						<td>
							${pageList.newDate }
						</td>
						<td>
							${pageList.documentaryPeople}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
							<input type="button" value="采购明细"
								style="width: 60px; height: 30px;"
								onclick="detail(${pageList.id})" />
								
						 <a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">查看审批记录</a>
						</td>
						</tr>
					</s:iterator>
					<s:if test="waitErrorMessage != null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${waitErrorMessage}
							</td>
						</tr>
					</s:if>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center" colspan="13">
							审核通过
							<br />
							Audit by
						</td>
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
							${pageList.num }
						</td>
						<td>
							${pageList.custome.clientcompanyname}
						</td>
						<td>
							${pageList.genertorDate}
						</td>
						<td>
							${pageList.newDate }
						</td>
						<td>
							${pageList.documentaryPeople}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
							<input type="button" value="明细"
								style="width: 60px; height: 30px;"
								onclick="detail(${pageList.id})" />
							
							<s:if test="pageStatus=='sc'">
								<input type="button" value="打印"
									style="width: 60px; height: 30px;"
									onclick="print(${pageList.id })" />
								<input type="button" value="删除"
									style="width: 60px; height: 30px;"
									onclick="del(${pageList.id })" />
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
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
		<script type="text/javascript">
	function detail(id){
			var url=encodeURI(encodeURI("DingdanAction!showcaigou.action?internalOrderzhaobiao.id="+id));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	
function del(id) {
	window.location = "internalOrder_del.action?id=" + id
			+ "&pageStatus=${pageStatus}";
}
function print(id) {
	window.location = "internalOrder_initPrint.action?id=" + id;
}
function order(id) {
	window.open("internalOrder_queryOrderManagerByInnerOrder.action?id=" + id);
}
function agree(id) {
	window.location = "internalOrder_auditProcess.action?id=" + id
			+ "&ifAgree=" + true + "&pageStatus=${pageStatus}";
}
function goBack(id) {
	window.location = "internalOrder_auditProcess.action?id=" + id
			+ "&ifAgree=" + false + "&pageStatus=${pageStatus}";
}
</script>
	</body>
</html>
