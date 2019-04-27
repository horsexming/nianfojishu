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
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					在途统计
				</h3>
				<form action="borrow_statisticsByCondition.action" method="post">
					<table>
						<tr>
							<td>
								姓名:
								<input type="text" name="vobo.person" value="${vobo.person}" />
							</td>
							<td>
								品名:
								<input type="text" name="vobo.name" value="${vobo.name}" />
							</td>
							<td>
								部门:
								<input type="text" name="vobo.dept" value="${vobo.dept}" />
							</td>
						</tr>
						<tr>
							<td>
								规格:
								<input type="text" name="vobo.format" value="${vobo.format}" />
							</td>

							<td>
								开始日期：
								<input style="width: 155px" class="Wdate" type="text"
									name="vobo.startTime" value="${vobo.startTime}" id="startTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								结束日期：
								<input style="width: 155px" class="Wdate" id="endTime"
									type="text" name="vobo.endTime" value="${vobo.endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								<input type="hidden" name="errorMessage" value="all" />
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
								<input type="button" value="导出Excel"
									style="width: 80px; height: 50px;"
									onclick="exportExcel(this.form);todisabledone(this)" data="downData" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<td align="center">
							序号
						</td>
						<td align="center">
							借用人
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							物品编号
						</td>
						<td align="center">
							物品名称
						</td>
						<td>
							规格
						</td>
						<td>
							单位
						</td>
						<td>
							加工件号
						</td>
						<td>
							应归还量
						</td>
						<td>
							借物时间
						</td>
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
						<td>
							${pageList.peopleName}
						</td>
						<td>
							${pageList.dept}
						</td>
						<td>
							${pageList.number }
						</td>
						<td>
							${pageList.matetag}
						</td>
						<td>
							<s:if test="pageList.format!=null">
							${pageList.format}</s:if>
							<s:else>${pageList.store.format}</s:else>

						</td>
						<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.processPieceNum}
						</td>
						<td>
							${pageList.giveBackNum}
						</td>
						<td>
							${pageList.date}
						</td>
						</tr>
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
							<td colspan="15" align="center" style="color: red">
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
		<SCRIPT type="text/javascript">
				function exportExcel(objform) {
				 var startTime=document.getElementById("startTime");
				  var endTime=document.getElementById("endTime");
				        if(startTime.value==""){
				          alert("请输入开始时间");
				           return false;
				        }
				        else if(endTime.value==""){
				        alert("请输入结束时间");
				        return false;
				        }
						objform.action="borrow_borrowexcel.action";
						objform.submit();
						objform.action="borrow_statisticsByCondition.action";
			}
			</SCRIPT>
	</body>
</html>
