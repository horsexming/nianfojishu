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
					选择需要确认样品
				</h3>
				<form action="NoPriceprocessAction_findAllBys.action"
				method="post" id="form">
				<input name="pageStatus" value="${pageStatus}" type="hidden">
				<table class="table" align="center">			
				<tr>
					<td align="center">
						件号：
					<input type="text" name="noPriceprocess.markId"
							value="<s:property value="noPriceprocess.markId"/>" />
					</td>
					<td align="center">
							工序号：
							<input  type="text" name="noPriceprocess.processNO"
								value="<s:property value="noPriceprocess.processNO"/>" />
						</td>
						<td align="center">
							工序名称：
							<input  type="text" name="noPriceprocess.processName"
								value="<s:property value="noPriceprocess.processName"/>" />
						</td>
					<td align="center">
							状态：
						<select name="noPriceprocess.stutas"
								 value="<s:property value="noPriceprocess.stutas"/>"  style="width: 155px;">
							<option></option>
								<option value="报价中">
									报价中
								</option >
								<option value="打样中">
									打样中
								</option>
								<option value="同意">
									同意
								</option>
								<option value="有效">
									有效
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询(select)" />
						</td>
					</tr>
				</table>
			</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							件号名称
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							委外申请数量
						</td>
						<td align="center">
							批次数量
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="noPriceprocessList" id="list"
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list.rootMarkId}
						</td>
						<td align="center">
							${list.markId}
						</td>
						<td align="center">
							${list.name}
						</td>
						<td align="center">
							${list.processNO}
						</td>
						<td align="center">
							${list.processName}
						</td>
						<td align="center">
							${list.waiweiShenqiCount}
						</td>
						<td align="center">
							${list.piciCount}
						</td>
						<td align="center">
							${list.stutas}
						</td>
						<td align="center">
						<s:if test="#list.stutas==null">
							未开启报价
						</s:if>
						<s:else>
							<a onclick="luru(${list.id});">查看价格</a>
						</s:else>
						</td>
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
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">
		function luru(id) {
			window.location.href = "NoPriceprocessAction_allZhuserOffer.action?noPriceprocess.id="
				+ id + "&cpage=${cpage}";
		}

</script>
	</body>
</html>
