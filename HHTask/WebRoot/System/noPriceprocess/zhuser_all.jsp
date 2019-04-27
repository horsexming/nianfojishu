<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					选择供应商
				</h3>
				<form action="NoPriceprocessAction_findAllZhusers.action" method="post">
					<table class="table" align="center">
						<tr>
							<th>
								供应商编号：
							</th>
							<td align="center">
							<input type="hidden" name="noPriceprocess.id" value="${noPriceprocess.id}"/>
							<input type="text" name="zhUser.usercode"
									value="${zhUser.usercode}" />
							</td>
							<th>
								供应商名称：
							</th>
							<td align="center">
								<input type="text" name="zhUser.cmp"
									value="${zhUser.cmp}" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" style="width: 100px; height: 35px;"
									value="查询(select)" />
								<input type="reset" style="width: 100px; height: 35px;"
									value="重置" />
							</td>
						</tr>
					</table>
				</form>
				<form action="NoPriceprocessAction_bandZhusers.action" method="post">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
						<input type="checkbox" onclick="chageAllCheck(this)"/>
						<input type="hidden"  name="noPriceprocess.id" value="${noPriceprocess.id}"/>
						</td>
						<td align="center">
							序号
						</td>
						<td align="center">
							公司编号
						</td>
						<td align="center">
							公司名称
						</td>
						<td align="center">
							联系人
						</td>
						<td align="center">
							联系方式
						</td>
					</tr>
					<s:iterator value="zhUserList" id="list"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						
						<td>
							<input name="zhUserId" type="checkbox" value="${list.id}" onclick="chageNum(this)"/>
						</td>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list.usercode}
						</td>
						<td align="center">
							${list.cmp}
						</td>
						<td align="center">
							${list.cperson}
						</td>
						<td align="left" >
							${list.ctel}
						</td>
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
							</td>
						</s:else>
					</tr>
					<tr>
					<td colspan="12" align="center" style="color: red">
						<input type="submit" value="绑定" style="height:30PX;width:60px">
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		</script>
	</body>
</html>
