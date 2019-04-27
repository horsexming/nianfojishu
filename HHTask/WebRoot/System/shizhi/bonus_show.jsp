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
						href="bonusShiZhiAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					试制月奖金额管理<br/>(bonus Management)
				</h3>
				<form action="bonusShiZhiAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								组名（bonus name）：
								<input type="text" name="bonusShiZhi.name" value="<s:property value="bonusShiZhi.name"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加试制月奖金额(add)" onclick="add()"/>
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(num)
						</td>
						<td align="center">
							月份<br/>(month)
						</td>
						<td align="center">
							金额<br/>(bonus)
						</td>
											
						<td align="center" colspan="3">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="bonusList" id="b" status="pageStatus">
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
							${b.month}
						</td>
						<td>
							${b.bonus}
						</td>
						
						<td  colspan="3">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${b.id})" />
<%--                            <input type="button" value="绑定项目(link project)"--%>
<%--								style="width: 60px; height: 30px;"--%>
<%--								onclick="linkPro(${b.id})" />--%>
							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${b.id })" />
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
	window.location.href = "bonusShiZhiAction_toupdate.action?bonusShiZhi.id=" + id;
}
function linkPro(id) {
	window.location.href = "bonusShiZhiAction_proTryMakeView.action?bonusShiZhi.id=" + id;
}
function todelete(id) {
	window.location.href = "bonusShiZhiAction_delete.action?bonusShiZhi.id=" + id;
}
function add() {
	window.location.href = "<%=path%>/System/shizhi/bonus_add.jsp";
}
</script>
	</body>
</html>
