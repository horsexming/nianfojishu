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
					进出门记录查询
				</h3>
				<form action="AccessRecordsAction_showList.action?tag=${tag}"
					method="post">
					<table class="table">
						<s:if test="tag=='code'">
							<tr style="width: 100%">
								<th align="center" style="width: 25%">
									请输入您要查询的日期
								</th>
								<td align="center" style="width: 25%">
									<input class="Wdate" type="text" name="accessRecords.addTime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<td align="center" style="width: 50%">
									<input type="submit" value="查询"
										style="width: 100px; height: 25px;" />
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr style="width: 100%">
								<th align="center" style="width: 25%">
									请输入员工号
								</th>
								<td align="center" style="width: 25%">
									<input type="text" name="accessRecords.inCode" />
								</td>
								<th align="center" style="width: 25%">
									请输入您要查询的日期
								</th>
								<td align="center" style="width: 25%">
									<input class="Wdate" type="text" name="accessRecords.addTime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
							</tr>

							<tr>
								<th align="center" style="width: 15%">
									通过状态
								</th>
								<td align="center" style="width: 25%">
									<select name="accessRecords.recordStatus" style="width: 156px;">
										<option value="">
											请选择记录状态
										</option>
										<option value="已通过">
											已通过
										</option>
										<option value="已识别">
											已识别
										</option>
										<option value="已开门">
											已开门
										</option>
									</select>
								</td>
								<th align="center" style="width: 25%">
									请输入员工姓名
								</th>
								<td align="center" style="width: 25%">
									<input type="text" name="accessRecords.inName" />
								</td>
							</tr>
							<tr>
								<th align="center" style="width: 25%">
									请输入车牌号
								</th>
								<td align="center" style="width: 25%">
									<input type="text" name="accessRecords.recordContents" />
								</td>
								<td align="center" style="width: 50%" colspan="2">
									<input type="submit" value="查询"
										style="width: 100px; height: 25px;" />
								</td>
							</tr>
						</s:else>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							工号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							卡号/车牌号
						</td>
						<td align="center">
							内部/来访
						</td>
						<td align="center">
							进出类型
						</td>
						<td align="center">
							进出状况
						</td>
						<td align="center">
							进出行道
						</td>
						<td align="center">
							出入时间
						</td>
					</tr>
					<s:iterator value="accessRecordList" id="samples"
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
							${samples.inCode}
						</td>
						<td align="center">
							${samples.inName}
						</td>
						<td align="center">
							${samples.recordContents}
						</td>
						<td align="center">
							${samples.recordisIn}
						</td>
						<td align="center">
							${samples.openType}
						</td>
						<td align="center">
							${samples.recordStatus}
						</td>
						<td align="center">
							${samples.equipmentDaoType}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="9" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="9" align="center" style="color: red">
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
