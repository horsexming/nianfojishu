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
		<div id="gongneng">

			<div align="center">
				<%--			<s:if test="pageStatus == 'xj'">--%>
				<%--				<a href="InsRecord_list.action">更新之前的巡检记录</a>--%>
				<%--			</s:if>--%>
				<%--			<s:else>--%>
				<%--				<a href="OsRecord_list.action?pageStatus=xj">更新之后的巡检记录</a>--%>
				<%--			</s:else>--%>
					<s:if test='pageStatus == "xj"'>
						<font style="font-size: 22px;font-weight: bolder;">巡检记录</font>
					</s:if>
					<s:elseif test='pageStatus == "sj"'>
						<font style="font-size: 22px;font-weight: bolder;">首检记录</font>
					</s:elseif>
				<s:if test="tag==null || tag!='pingmu'">
					<form action="OsRecord_list.action" method="post">
					<table width="100%" border="0" style="border-collapse: collapse;"
					class="table"> 
						<tr>
							<th align="right">
								件号:
							</th>
							<td>
								<input type="text" value="${record.markId}" name="record.markId">
							</td>
							<th align="right">
								名称:
							</th>
							<td>
								<input type="text" value="${record.proName}" name="record.proName">
							</td>
						</tr>
						<tr>
							<th align="right">
								内部订单号:
							</th>
							<td>
								<input type="text" value="${record.neiordeNum}" name="record.neiordeNum">
							</td>
							<th align="right">
								业务件号:
							</th>
							<td>
								<input type="text" value="${record.ywmakrId}" name="record.ywmakrId">
							</td>
						</tr>
						<tr>
							<th align="right">
								工序号:
							</th>
							<td>
								<input type="text" value="${record.template.gongxuNum}" name="record.template.gongxuNum">
							</td>
							<th align="right">
								工序名:
							</th>
							<td>
								<input type="text" value="${record.template.gongxuName}" name="record.template.gongxuName">
							</td>
						</tr>
						<tr>
							<th align="right">
								时间从:
							</th>
							<td>
								<input type="text" value="${firsttime}" name="firsttime"
								 class="Wdate"
									 onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<th align="right">
								止:
							</th>
							<td>
								<input type="text" value="${endtime}" name="endtime"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
					</table>
					<input type="hidden" value="${pageStatus}" name="pageStatus"/>
					<input type="submit" value="查询" class="input"/>
				</form>
				</s:if>
				
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							内部订单号
						</th>
						<th>
							业务件号
						</th>
						<th>
							生产批次
						</th>
						<th>
							产品类别
						</th>
						<th>
							零件号
						</th>
						<th>
							零件名称
						</th>
						<th>
							版本号
						</th>
						<th>
							工序号
						</th>
						<th>
							工序名
						</th>
						<th>
							工位号
						</th>
						<th>
							量具号
						</th>
						<th>
							时间
						</th>
						<th>
							当天次数
						</th>
						<th>
							检验人
						</th>
						<s:if test="tag==null || tag!='pingmu'">
						<th>
							操作
						</th>
						</s:if>
					</tr>
					<s:if test="pageStatus !='xj' && pageStatus !='sj'">
						<s:iterator value="list" status="st" id="tt">
							<s:if test="#tt.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<th>
								${st.index +1 }
							</th>
							<td>
								${tt.root.productType}
							</td>
							<td>
								${tt.template.partNumber}   
							</td>
							<td>
								${tt.template.name}
							</td>
							<td>
								${tt.banbenNumber}
							</td>
							<td>
								${tt.root.processNumber}
							</td>
							<td>
								${tt.template.gongxuName} 
							</td>
							<td>
								${tt.root.workStation}
							</td>
							<td>
								${tt.groupDate}
							</td>
							<td>
								${tt.dateCount}
							</td>
							<s:if test="tag==null || tag!='pingmu'">
							<td>
								<a
									href="InsRecord_get.action?record.root.id=${tt.root.id}&record.groupDate=${tt.groupDate}">检查结果</a>
							</td>
							</s:if>
							</tr>

						</s:iterator>
					</s:if>
					<s:else>
						<s:iterator value="list" status="st" id="tt">
							<s:if test="#st.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<th>
								${st.index +1 }
							</th>
							<td>${tt.neiordeNum}</td>
							<td>${tt.ywmakrId}</td>
							<td>${tt.selfCard}</td>
							<td>
								${tt.template.productType}
							</td>
							<td>
								${tt.template.partNumber}  
							</td>
							<td>
								${tt.template.name}
							</td>
							<td>
								${tt.banbenNumber}
							</td>
							<td>
								${tt.template.gongxuNum}
							</td>
							<td>
								${tt.template.gongxuName} 
							</td>
							<td>
								${tt.gongwei}
							</td>
							<td>
								${tt.measuring_no}
							</td>
							<td>
								${tt.nowDate}
							</td>
							<td>
								${tt.dateCount}
							</td>
							<td>
								${tt.username}
							</td>
							<s:if test="tag==null || tag!='pingmu'">
							<td>
								<a
									href="OsRecord_showScope.action?record.id=${tt.id}&pageStatus=${pageStatus}">检查结果</a>
							</td>
							</s:if>
							</tr>

						</s:iterator>
					</s:else>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="18" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="18" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
