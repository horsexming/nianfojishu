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
			<div id="xitong" >
			</div>
			
			<div align="center">
				<h3>
					设变在制品管理
				</h3>
				<form action="procardTemplateGyAction_findProcardSbWasterList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<th>
								设变单号：
							</th>
							<td align="center">
								<input type="text" name="procardSbWaster.sbNumber" value="<s:property value="procardSbWaster.sbNumber"/>" />
							</td>
							<th>
								订单号：
							</th>
							<td align="center">
								<input type="text" name="procardSbWaster.orderNumber" value="<s:property value="procardSbWaster.orderNumber"/>" />
							</td>
							
						</tr>
						<tr>
						<th>
								件号：
							</th>
							<td align="center">
								<input type="text" name="procardSbWaster.markId" value="<s:property value="procardSbWaster.markId"/>" />
							</td>
							<th>
								批次：
							</th>
							<td align="center">
								<input type="text" name="procardSbWaster.selfCard" value="<s:property value="procardSbWaster.selfCard"/>" />
							</td>
						</tr>
						<tr>
							<th>
								名称：
							</th>
							<td align="center">
								<input type="text" name="procardSbWaster.proName" value="<s:property value="procardSbWaster.proName"/>" />
							</td>
							<th>
								类型：
							</th>
							<td align="center">
								<SELECT name="procardSbWaster.procardStyle">
									<s:if test="procardSbWaster.procardStyle!=null">
									<option><s:property value="procardSbWaster.procardStyle"/></option>
									</s:if>
									<option></option>
									<option>总成</option>
									<option>自制</option>
									<option>外购</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th>
								总成件号：
							</th>
							<td align="center">
								<input type="text" name="procardSbWaster.rootMarkId" value="<s:property value="procardSbWaster.rootMarkId"/>" />
							</td>
							<th>
								总成批次：
							</th>
							<td align="center">
								<input type="text" name="procardSbWaster.rootMarkId" value="<s:property value="procardSbWaster.rootMarkId"/>" />
							</td>
						</tr>
						<tr>
							<th>
								状态：
							</th>
							<td align="center">
							</td>
							<th>
							<input type="text"  value="<s:property value="procardSbWaster.status"/>" />
								<SELECT name="procardSbWaster.status">
									<s:if test="procardSbWaster.status!=null">
									<option><s:property value="procardSbWaster.status"/></option>
									</s:if>
									<option></option>
									<option>待处理</option>
									<option>完成</option>
								</SELECT>
							</th>
							<th>
							</th>
							<td align="center">
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
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
						设变单号
						</td>
						<td align="center">
						订单号
						</td>
						<td align="center">
						总成件号
						</td>
						<td align="center">
						总成批次
						</td>
						<td align="center">
						件号
						</td>
						<td align="center">
						批次
						</td>						
						<td align="center">
						名称
						</td>						
						<td align="center">
						类型
						</td>						
						<td align="center">
						批次数量
						</td>						
						<td align="center">
						下产数量
						</td>
						<td align="center">
						单位
						</td>
						<td align="center">
						工序号
						</td>						
						<td align="center">
						工序名称
						</td>							
						<td align="center">
						状态
						</td>							
						<td align="center">
						操作
						</td>
					</tr>
					<s:iterator value="procardSbWasterList" id="pageWaster" status="pageStatus">
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
								<s:property value="#pageStatus.index+1" />
								</font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
								<s:property value="#pageStatus.index+1" />
								</font>
							</s:else>
						</td>
						<td align="center">
						${pageWaster.sbNumber}
						</td>
						<td align="center">
						${pageWaster.orderNumber}
						</td>
						<td align="center">
						${pageWaster.rootMarkId}
						</td>
						<td align="center">
						${pageWaster.rootSelfCard}
						</td>
						<td align="center">
						${pageWaster.markId}
						</td>
						<td align="center">
						${pageWaster.procardStyle}
						</td>						
						<td align="center">
						${pageWaster.proName}
						</td>						
						<td align="center">
						${pageWaster.selfCard}
						</td>						
						<td align="center">
						${pageWaster.finalCount}
						</td>						
						<td align="center">
						${pageWaster.bfCount}
						</td>
						<td align="center">
						${pageWaster.unit}
						</td>
						<td align="center">
						${pageWaster.processNo}
						</td>						
						<td align="center">
						${pageWaster.processName}
						</td>							
						<td align="center">
						${pageWaster.status}
						</td>							
						
						<td  align="center">
							<s:if test="#pageWaster.status=='待处理'">
							<a href="procardTemplateGyAction_toJudegWaster.action?id=${pageWaster.id}">判定</a>
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
</script>
	</body>
</html>
