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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>



				</div>
			</div>
			
			<div align="center">


				<table border="1" width="100%" class="table">
					<tr>
						<td colspan="20" align="center"
							style="font-family: 微软雅黑; font-weight: bold;">
							明细说明单
						</td>
					</tr>
					<tr>
<!--						<th>-->
<!--							工号-->
<!--						</th>-->
<!--						<td>-->
<!--							<input type="hidden" value="${repair.id}" name="repair.id" />-->
<!--							${repair.jobnumber}-->
<!--						</td>-->

						<th>
							报修人
						</th>
						<td>

							${repair.name}
						</td>
						<th>
							报修部门
						</th>
						<td>
							${repair.department}
						</td>
						<th>
							维修部门
						</th>
						<td>
							${repair.repairdept}
						</td>
					</tr>
					<tr>
						<th>
							报修物品
						</th>
						<td>
							${repair.devicename}
						</td>

						<th>
							报修类别
						</th>

						<td>
							${repair.category}


						</td>

						<th>
							修理人
						</th>
						<td>
							${repair.personalnominee}
						</td>
					</tr>
					<tr>
						<th>
							报修时间
						</th>
						<td>
							${repair.repairtime}
						</td>
						<th>
							报修故障
						</th>
						<td>
							${repair.repairfailed}
						</td>
						<th>
							修复时间
						</th>
						<td>
							${repair.repairtime}
						</td>
					</tr>	
					
					<s:if test="repair.fk_id!=null">
					<tr>
					<th>
							关联明细
						</th>
						<td>
							<a href="RepairAction!findByclientManagement.action?id=${repair.fk_id} ">关联明细</a>
						</td>
					</tr>
					
					</s:if>
					
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
