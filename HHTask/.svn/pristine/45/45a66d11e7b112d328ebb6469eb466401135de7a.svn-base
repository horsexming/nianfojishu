<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<s:if test='"财务特殊通知"==show'>
							<th align="center">
								类型
							</th>
							<th align="center">
								项目（一）
							</th>
						</s:if>
						<s:elseif test='"行政通知"==show'>
							<th align="center">
								标题
							</th>
							<th align="center">
								副标题
							</th>
						</s:elseif>
						<s:else>
							<th align="center">
								姓名
							</th>
							<th align="center">
								工号
							</th>
						</s:else>
						<th align="center">
							部门/班组
						</th>
						<s:if test='"离场通知"==show'>
							<th align="center">
								离职原因
							</th>
							<th align="center">
								离职申请编号
							</th>
						</s:if>
						<s:elseif test='"入职通知"==show'>
							<th align="center">
								入职类型
							</th>
							<th align="center">
								入职申请编号
							</th>
						</s:elseif>
						<s:elseif test='"调职通知"==show'>
							<th align="center">
								调职原因
							</th>
							<th align="center">
								调职职申请编号
							</th>
						</s:elseif>
						<s:elseif test='"财务特殊通知"==show'>
							<th align="center">
								项目（二）
							</th>
							<th align="center">
								申请编号
							</th>
						</s:elseif>
						<s:elseif test='"行政通知"==show'>
							<th>
								人员
							</th>
							<th align="center">
								申请编号
							</th>
						</s:elseif>
						<s:else>
							<th align="center">
								卡号
							</th>
							<th align="center">
								申请编号
							</th>
						</s:else>

						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="leaveInform" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout=outBgcolor(this,'');>
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
							</s:if>
							<s:else>
								<font color="#c0dcf2"></font>
							</s:else>
							<s:property value="#pageStatus.index+1" />

						</td>
						<td>
							${leaveInform.username}
						</td>
						<td>
							${leaveInform.code}
						</td>
						<td>
							${leaveInform.dept}
						</td>
						<td>
							${leaveInform.reason}
						</td>
						<td>
							${leaveInform.time}
						</td>

						<td>
							<s:if test='%{"离场通知".equals(#leaveInform.fuck1)}'>
								<a
									href="InformAction!leaveprint.action?leaveInform.id=${leaveInform.id}">
									打印</a>
							</s:if>
							<s:elseif test='%{"入职通知".equals(#leaveInform.fuck1)}'>
								<s:if test='%{"实习生".equals(#leaveInform.reason)}'>
									<a
										href="InformAction!findCame.action?leaveInform.id=${leaveInform.id}">
										打印</a>
								</s:if>
								<s:elseif test='%{"新进公司".equals(#leaveInform.reason)}'>
									<a
										href="InformAction!findCame.action?leaveInform.id=${leaveInform.id}">
										打印</a>
								</s:elseif>
							</s:elseif>
							<s:elseif test='%{"财务入职通知".equals(#leaveInform.fuck1)}'>
								<a
									href="InformAction!caiwuruzhiPrint.action?leaveInform.code=${leaveInform.code}">
									打印</a>
							</s:elseif>
							<s:elseif test='%{"财务离职通知".equals(#leaveInform.fuck1)}'>
								<a
									href="InformAction!caiwulizhiPrint.action?leaveInform.code=${leaveInform.code}">
									打印</a>
							</s:elseif>
							<s:elseif test='%{"财务内退通知".equals(#leaveInform.fuck1)}'>
								<a
									href="InformAction!caiwuneituiPrint.action?leaveInform.code=${leaveInform.code}">
									打印</a>
							</s:elseif>
							<s:elseif test='%{"财务个人工资调整通知".equals(#leaveInform.fuck1)}'>
								<a
									href="InformAction!caiwugerenPrint1.action?leaveInform.id=${leaveInform.id}">
									打印</a>
							</s:elseif>
							<s:elseif test='%{"财务特殊通知".equals(#leaveInform.fuck1)}'>
								<a
									href="InformAction!caiwutstzprint.action?leaveInform.id=${leaveInform.id}">
									打印</a>
							</s:elseif>
							<s:elseif test='%{"行政通知".equals(#leaveInform.fuck1)}'>
								<a
									href="InformAction!xingzhengtzprint.action?leaveInform.id=${leaveInform.id}">
									打印</a>
							</s:elseif>
							<s:elseif test='%{"调职通知".equals(#leaveInform.fuck1)}'>
								<a
									href="InformAction!findCame.action?leaveInform.id=${leaveInform.id}">
									打印</a>
							</s:elseif>
							<a
								href="InformAction!deleteleaveInform.action?leaveInform.id=${leaveInform.id}">&nbsp;&nbsp;&nbsp;删除</a>

						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="11" align="right">
							总数为：${shu}
						</td>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
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
