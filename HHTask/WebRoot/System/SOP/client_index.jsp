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
		<script type="text/javascript"
			src="<%=basePath%>/javascript/popwin.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<h3>
					客户管理(Customer Management)
				</h3>
				<form action="clientManager_queryClientByCondition.action"
					method="post">
					<table class="table">
						<tr>
							<td align="right">
								联系人(Contacts)：
							</td>
							<td>
								<input type="text" name="peopleName" value="${peopleName}" />
							</td>
							<td align="right">
								公司名称(Company Name)：
							</td>
							<td>
								<input type="text" name="companyName" value="${companyName}" />
							</td>
							<td rowspan="2">
								<input type="submit" value="查询(Query)"
									style="width: 80px; height: 50px;" />
								<input type="button" onclick="add()" value="添加客户(Add)"
									style="height: 50px;" />
							</td>
						</tr>
						<tr>
							<td align="right">
								公司编号：
							</td>
							<td>
								<input type="text" name="number" value="${number}" />
							</td>
							<td align="right">
								公司性质(Company Type)：
							</td>
							<td>
								<select name="nature">
									<option value="选择性质" selected="selected">
										选择性质(Select Properties)
									</option>
									<s:iterator id="typeStr"
										value='{"国有企业 (State-owned enterprises)","中外合作企业(Sino-foreign cooperative enterprises)","中外合资企业(Joint ventures)","外商独资企业(Foreign-owned enterprises)","集体企业(Collective enterprises)", "私营企业(Private)"}'>
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
								<input type="hidden" name="errorMessage" value="all" />
							</td>

						</tr>
					</table>
				</form>
				<br />
				<br />
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							No.
						</td>
						<td align="center">
							公司名称
							<br />
							Company Name
						</td>
						<td align="center">
							公司编号
						</td>
						<td align="center">
							公司法人
							<br />
							Corporate
						</td>
						<td align="center">
							联系人
							<br />
							Contacts
						</td>
						<td align="center">
							公司性质
							<br />
							Company Type
						</td>
						<td align="center">
							手机号
							<br />
							Phone number
						</td>
						<td align="center">
							电话号码
							<br />
							telephone number
						</td>
						<td align="center"></td>
						<td align="center"></td>
						<td align="center"></td>
						<td align="center"></td>
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
						<td align="center">
							${pageList.clientcompanyname}
						</td>
						<td align="center">
							${pageList.number}
						</td>
						<td align="center">
							${pageList.legalPerson}
						</td>
						<td align="center">
							${pageList.clientname }
						</td>
						<td align="center">
							${pageList.natureOfBusiness }
						</td>
						<td align="center">
							${pageList.clientmobilenumber}
						</td>
						<td align="center">
							${pageList.clientphonenumber }
						</td>
						<td>
							<input type="button" value="产品"
								style="width: 60px; height: 30px;"
								onclick="showprice(${pageList.id})" />
						</td>
						<td>
							<input type="button" value="详细"
								style="width: 60px; height: 30px;"
								onclick="infor(${pageList.id })" />
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
							<td colspan="12" align="right">
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
	window.location = "<%=basePath%>/System/SOP/client_add.jsp";
}
function update(id) {
	window.location = "clientManager_initUpdate.action?id=" + id;
}
function del(id) {
	window.location = "clientManager_del.action?id=" + id;
}
function infor(id) {
	window.location = "clientManager_infor.action?id=" + id;
}
function showprice(id){
	popWin.showWin("1200", "750", "您正在查看客户产品信息", "PriceAction!findPriceBykehuId.action?id="+id+"&statue=KHPrice");
}
</script>
	</body>
</html>
