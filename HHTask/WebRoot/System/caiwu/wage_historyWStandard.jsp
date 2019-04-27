<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<body bgcolor="#ffffff" onload="checkStatus(),createDept('selectDept')">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
					<div style="float: left; width: 48%" align="right">
						<s:if test="WsList.size()>0">
							<a target="_blank"
								href="WageStandardAction!findWageXieYiByCode.action?code=${WsList[0].code}&cardId=${WsList[0].cardId}"
								style="color: #ffffff">查看薪资协议书</a>
						</s:if>
					</div>
				</div>
				
				<!-- 查询所有工资模板 -->
				<div align="center" id="findWageStrandar">
					<table class="table2">
						<tr>
							<th colspan="14" align="center">
								<font color="red">${WsList[0].userName}(${WsList[0].dept}部门)</font>的所有工资信息
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								岗位工资
							</th>
							<th align="center">
								保密津贴
							</th>
							<th align="center">
								补贴
							</th>
							<th align="right">
								技能工资
							</th>
							<th align="right">
								工龄工资
							</th>
							<th align="center">
								绩效考核工资
							</th>
							<th align="center">
								养老保险
							</th>
							<th align="center">
								医疗保险
							</th>
							<th align="center">
								失业保险
							</th>
							<th align="center">
								公积金
							</th>
							<th align="center">
								处理时间
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								备注
							</th>
						</tr>
						<s:iterator value="WsList" id="pageWsList" status="pageStatus">
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
								${pageWsList.gangweigongzi}
							</td>
							<td>
								${pageWsList.baomijintie}
							</td>
							<td>
								${pageWsList.dianhuabutie}
							</td>
							<td>
								${pageWsList.jinenggongzi}
							</td>
							<td>
								${pageWsList.gonglinggongzi}
							</td>
							<td>
								${pageWsList.jixiaokaohegongzi}
							</td>
							<td>
								${pageWsList.tongchoujin}
							</td>
							<td>
								${pageWsList.yiliaobaoxian}
							</td>
							<td>
								${pageWsList.shiyebaoxian}
							</td>
							<td>
								${pageWsList.gongjijin}
							</td>
							<td>
								${pageWsList.inputDate}
							</td>
							<td>
								${pageWsList.standardStatus}
							</td>
							<td>
								${pageWsList.isOnWork}
							</td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
