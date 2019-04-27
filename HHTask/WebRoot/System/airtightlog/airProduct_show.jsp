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
						href="airtightLogAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					气密产品<br/>
				</h3>
				<form action="airtightLogAction_showProductList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								件号
								<input type="text" name="airProduct.markId" />
							</td>
							<td align="center">
								客户编号
								<input type="text" name="airProduct.customerNumber" />
							</td>
						</tr>
						<tr>
							<td align="center">
								类型
								<input type="text" name="airProduct.type" />
							</td>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
								<input type="button" style="width: 100px; height: 40px;" onclick="add()"
									value="添加" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(NO)
						</td>
						<td align="center">
							件号<br/>(markId)
						</td>
						<td align="center">
							客户编号<br/>(customer number)
						</td>
						<td align="center">
							类型<br/>（type）
						</td>
						<td align="center">
							 测试时长（ms）<br/>(test time)
						</td>
						<td align="center">
							泄漏量标准（L）<br/>(Leakage Standard)
						</td>
						<td align="center">
							压力值标准（KPa）<br/>(Pressure Standard)
						</td>
						<td align="center">
							是否其他标识<br/>（is need other tag）
						</td>						
						<td align="center">
							开关IP
						</td>
						<td align="center">
							传值IP
						</td>
						
						<td align="center">
							状态
						</td>
						
						<td align="center">
							 添加时间<br/>（addtime）
						</td>
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="airProductList" id="airProductPage" status="pageStatus">
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
							${airProductPage.markId}
						</td>
						<td>
							${airProductPage.customerNumber}
						</td>
						<td>
							${airProductPage.type}
						</td>
						<td>
							${airProductPage.testTime}
						</td>
						<td>
							${airProductPage.xielou}
						</td>
						<td>
							${airProductPage.yali}
						</td>
						<td>
						<s:if test="#airProductPage.isNeedOtherContext=='yes'">
						 需要
						</s:if>
						<s:else>
						 不需要
						</s:else>
						</td>
						<td>
							${airProductPage.kgIp}
						</td>
						<td>
							${airProductPage.czIp}
						</td>
						<td>
							${airProductPage.syStatus}
						</td>
						<td>
							${airProductPage.addTime}
						</td>
						<td  colspan="2">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${airProductPage.id})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${airProductPage.id })" />
						</td>
					</s:iterator>
					</tr>
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
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
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
function update(id) {
	window.location.href = "airtightLogAction_toUpdateProduct.action?airProduct.id=" + id;
}
function todelete(id) {
	window.location.href = "airtightLogAction_toDeleteProduct.action?airProduct.id=" + id;
}
function add(){
	window.location.href = "<%=path%>/System/airtightlog/airProduct_add.jsp";
}

</script>
	</body>
</html>

