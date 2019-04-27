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
		<div id="gongneng" style="width: 100%;">
			
			<div align="center">
				<h3>
				内部员工进出门记录查询
				</h3>
						<form action="AccessRecordsAction_showList_carType.action" method="post">
							<table class="table">
								<tr style="width: 100%">
									<th align="center" style="width: 15%">
										请输入车牌号
									</th>
									<td align="center" style="width: 25%">
										<input type="text" name="carInOutType.carPai"
											/>
									</td>
									<th align="center" style="width: 15%">
										请输入您要查询的日期
									</th>
									<td align="center" style="width: 25%">
										<input class="Wdate" type="text" name="carInOutType.updateTime" 
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
									</td>
									<td align="center" style="width: 30%">
										<input type="submit" value="查询"
											style="width: 100px; height: 25px;" />
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
							车牌号
						</td>
						<td align="center">
							进出类型
						</td>
						<td align="center">
							最近一次进出时间
						</td>
						<td align="center">
							最近一次进出时间
						</td>
					</tr>
					<s:iterator value="carInOutTypeList" id="samples"
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
							${samples.carPai}
						</td>
						<td align="center">
							${samples.carInOut}
						</td>
						<td align="center">
							${samples.updateTime}
						</td>
						<td align="center">
							<a href="AccessRecordsAction_toupdate.action?carInOutType.id=${samples.id}">修改</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="5" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="5" align="center" style="color: red">
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

</script>
	</body>
</html>
