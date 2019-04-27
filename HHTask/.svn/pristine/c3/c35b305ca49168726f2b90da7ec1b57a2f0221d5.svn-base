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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="business_queryByCondition.action" method="post"
					onsubmit="return validate()">
					<table>
						<tr>
							<td>
								业务类型：
							</td>
							<td>
								<select name="type">
									<option value="选择类型" selected="selected">
										选择类型
									</option>
									<s:iterator id="typeStr" value='{"运输","报关","仓储","仓储配送"}'>
										<s:if test='#typeStr == nature'>
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
							<td>
								业务内容：
							</td>
							<td>
								<input type="text" name="content" value="${content }" />
							</td>
							<td>
								收款单位：
							</td>
							<td>
								<input type="text" name="collectionUnit"
									value="${collectionUnit }" />
							</td>
						</tr>
						<tr>
							<td>
								经办人：
							</td>
							<td>
								<input type="text" name="transactor" value="${transactor }" />
							</td>
							<td>
								开始时间：
							</td>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="beginTime" value="${beginTime }"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								结束时间：
							</td>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="endTime" value="${endTime }"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<input type="hidden" value="all" name="errorMessage" />
							<td>
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
							</td>
							<td>
								<input type="button" value="添加新业务" onclick="add()"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr>
						<th align="center" colspan="11">
							业务
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							业务类型
						</td>
						<td align="center" width="260px;">
							业务内容
						</td>
						<td align="center">
							费用金额
						</td>
						<td align="center">
							收款单位
						</td>
						<td align="center">
							经办人
						</td>
						<td align="center">
							办理时间
						</td>
						<td></td>
						<td></td>
						<td></td>
						<Td></Td>
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
							${pageList.type }
						</td>
						<td>
							${pageList.content }
						</td>
						<s:if test="#pageList.money == 0.0">
							<td>
								0.00
							</td>
						</s:if>
						<s:else>
							<td>
								${pageList.money }
							</td>
						</s:else>
						<td>
							${pageList.collectionUnit }
						</td>
						<td>
							${pageList.transactor }
						</td>
						<td>
							${pageList.time }
						</td>
						<td>
							<input type="button" value="明细"
								style="width: 60px; height: 30px;"
								onclick="detail(${pageList.id})" />
						</td>
						<td>
							<input type="button" value="发票"
								style="width: 60px; height: 30px;"
								onclick="invoice(${pageList.id})" />
						</td>
						<td>
							<input type="button" value="修改"
								style="width: 60px; height: 30px;"
								onclick="update(${pageList.id })" />
						</td>
						<td>
							<input type="button" value="删除"
								style="width: 60px; height: 30px;"
								onclick="del(${pageList.id })" />
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="6" align="right">
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
function add() {
	window.location = "<%=basePath%>/System/caiwu/yewu/businessContent_add.jsp";
}
function update(id) {
	window.location = "business_initUpdate.action?id=" + id;
}
function del(id) {
	window.location = "business_del.action?id=" + id;
}
function invoice(id) {
	window.location = "business_queryInvoiceByBusinessId.action?id=" + id;
}
function validate() {
	var valiTime = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	var beginTime = document.getElementById("beginTime").value;
	var endTime = document.getElementById("endTime").value;
	var beginTimeNOblank = beginTime.replace(/(^\s+)|(\s+$)|(\s+)/g, ""); //去除前后中所有空
	var endTimeNOblank = endTime.replace(/(^\s+)|(\s+$)|(\s+)/g, ""); //去除前后中所有空
	if (valiTime.test(beginTimeNOblank) && valiTime.test(endTimeNOblank)) {
	} else {
		alert("请输入正确的时间： 年-月-日!谢谢");
	}
	return false;
}
function detail(id) {
	window.location = "business_queryDetailByBusinessId.action?id=" + id;
}
</script>
	</body>
</html>
