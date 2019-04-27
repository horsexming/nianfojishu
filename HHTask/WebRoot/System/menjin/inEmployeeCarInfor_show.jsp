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
					内部员工车辆信息查询
				</h3>
				<form action="InEmployeeCarInforAction_showList.action"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 10%">
								请输入车牌号：
								<input type="hidden" name="tag" value="${tag}" />
								<input type="hidden" name="all" value="${all}" />
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="inEmployeeCarInfor.nplates" />
							</td>
							<th align="center" style="width: 10%">
								员工姓名：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="inEmployeeCarInfor.name" />
							</td>
							<td align="center" style="width: 50%">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
								<input type="button" value="导出" onclick="exportExcel_1(this.form);todisabledone(this)" data="downData"
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
							车主
						</td>
						<td align="center">
							工号
						</td>
						<td align="center">
							车牌
						</td>
						<td align="center">
							车辆类型
						</td>
						<td align="center">
							价格（元）
						</td>
						<td align="center">
							折旧公里数
						</td>
						<td align="center">
							行驶公里数
						</td>
						<td align="center">
							每公里价钱（不含折旧费）
						</td>
						<td align="center">
							车型
						</td>
						<td align="center">
							颜色
						</td>
						<td align="center">
							是否发送消息
						</td>
						<td align="center">
							是否白名单
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="inEmployeeCarInforList" id="samples"
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
							${samples.name}
						</td>
						<td align="center">
							${samples.ncode}
						</td>
						<td align="center">
							${samples.nplates}
						</td>
						<td align="center">
							${samples.carType}
						</td>
						<td align="center">
							${samples.price}
						</td>
						<td align="center">
							${samples.zjDistance}
						</td>
						<td align="center">
							${samples.driveDistance}
						</td>
						<td align="center">
							${samples.drivePrice}
						</td>
						<td align="center">
							${samples.carModels}
						</td>
						<td align="center">
							${samples.carColor}
						</td>
						<td align="center">
							${samples.rtxMessage}
						</td>
						<td align="center">
							${samples.whiteCar}
						</td>
						<td align="center" colspan="2">
							<a
								href="InEmployeeCarInforAction_toupdate.action?inEmployeeCarInfor.id=${samples.id}&tag=nb">修改</a>
							<a onclick="return window.confirm('您将删除数据，是否继续?')"
								href="InEmployeeCarInforAction_delete.action?inEmployeeCarInfor.id=${samples.id}&tag=nb">删除/</a>
							<s:if test="#samples.carFiles!=null">
								<a href="<%=path%>${samples.carFiles}">查看附件</a>
							</s:if>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
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
function exportExcel_1(obj){
		obj.action = "InEmployeeCarInforAction_exportExcel_1.action";
	 	obj.submit();
	  	obj.action = "InEmployeeCarInforAction_showList.action?tag=${cf}&all=${all}";
	 	}
</script>

	</body>
</html>
