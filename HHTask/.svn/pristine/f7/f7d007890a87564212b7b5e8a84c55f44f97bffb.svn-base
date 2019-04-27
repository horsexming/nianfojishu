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
					常访车辆信息查询
				</h3>
				<form action="InEmployeeCarInforAction_showList.action"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 10%">
								请输入常访车牌号：
								<input type="hidden" name="tag" value="${tag}" />
								<input type="hidden" name="all" value="${all}" />
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="inEmployeeCarInfor.nplates" />
							</td>
							<th align="center" style="width: 10%">
								常访人姓名：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="inEmployeeCarInfor.oftenname" />
							</td>
							<td align="center" style="width: 30%">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
								<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"
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
							被访人姓名
						</td>
						<td align="center">
							常访人姓名
						</td>
						<td align="center">
							常访人性别
						</td>
						<td align="center">
							常访人车牌
						</td>
						<td align="center">
							车型
						</td>
						<td align="center">
							常访原因
						</td>
						<td align="center">
							常访失效日期
						</td>
						<td align="center">
							审批状态
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
							${samples.oftenname}
						</td>
						<td align="center">
							${samples.oftenSex}
						</td>
						<td align="center">
							${samples.nplates}
						</td>
						<td align="center">
							${samples.carModels}
						</td>
						<td align="center">
							${samples.oftenInfor}
						</td>
						<td align="center">
							${samples.effectiveDate}
						</td>
						<td align="center">
							${samples.oftenStatus}
						</td>
						<td align="center" colspan="2">
							<s:if
								test="#samples.oftenStatus=='未审批'||#samples.oftenStatus=='打回'||#samples.oftenStatus==null">
								<a
									href="InEmployeeCarInforAction_toupdate.action?inEmployeeCarInfor.id=${samples.id}&tag=cf&all=${all}">修改</a>
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="InEmployeeCarInforAction_delete.action?inEmployeeCarInfor.id=${samples.id}&tag=cf&all=${all}">删除/</a>
							</s:if>
							<s:if test="#samples.epId!=null">
								<a
									href="CircuitRunAction_findAduitPage.action?id=${samples.epId}">审批动态/</a>
							</s:if>
							<s:if test="#samples.oftenStatus=='同意'">
								<a
									href="InEmployeeCarInforAction_agreen.action?inEmployeeCarInfor.id=${samples.id}&tag=cf&all=${all}"><font
									style="color: #76d092">再次申请</font>/</a>
							</s:if>
							<s:if test="#samples.carFiles!=null">
								<a href="<%=path%>${samples.carFiles}">查看附件</a>
							</s:if>
						</td>
					</s:iterator>
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
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		
	function exportExcel(obj){
		obj.action = "InEmployeeCarInforAction_exportExcel.action";
	 	obj.submit();
	  	obj.action = "InEmployeeCarInforAction_showList.action?tag=${cf}&all=${all}";
	 	}

</script>
	</body>
</html>
