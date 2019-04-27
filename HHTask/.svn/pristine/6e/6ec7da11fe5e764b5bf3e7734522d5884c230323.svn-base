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
<body onload="createDept('department');">
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
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
			<h3>报修类别</h3>
			<!-- 对单个部门进行添加 -->
					<s:if test="%{name1=='geti'}">
					<form action="RepairAction!saveRepairtype.action?test='1'" onsubmit="return add()" method="post">
					<table class="table">
					<tr>
							<td align="center">部门：<input id=department  type="text" name="repairType.department" value="${deptname}" readonly="readonly"/></td>
						<td align="center">类别： <input id="depart_category" type="text" name="repairType.category" />
						</td>
					<td align="center"><input type="submit" style="width: 100px; height: 40px;" value="添加"  class="input" /> </td> 
					</tr>
				</table>
			</form>
				</s:if>
			<!-- 对每个部门的类别进行添加 -->
					<s:else>
					<form action="RepairAction!saveRepairtype.action" onsubmit="return add()" method="post">
					<table class="table">
					<tr>
					<td align="center">部门：<select id="department" name="repairType.department" >
							<option selected="selected" value="">
								选择部门
							</option>
							<s:iterator id="cu" value="list">
								<option value="${cu.ta_dept}">
									${cu.ta_dept}
								</option>
							</s:iterator>
						</select>
						</td>
						<td align="center">类别： <input id="depart_category" type="text" name="repairType.category" />
						</td>
					<td align="center"><input type="submit" style="width: 100px; height: 40px;" value="添加"  class="input" /> </td> 
					</tr>
				</table>
			</form>
					</s:else>
			
			
					
			<form action="RepairAction!findRepairtype.action"  method="post">
				<table class="table" >
					<tr>
						<td align="center">部门： <input type="text" name="repairType.department" />
						</td>
						<td align="center">类别： <input type="text" name="repairType.category" />
						</td>
						<td  align="center"><input type="submit" style="width: 100px; height: 40px;" value="查询"  class="input" /> </td>
					</tr>
					</table>
					</form>
			<table  class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">序号</td>
					<td align="center">部门</td>
					<td align="center">类别</td>
					<td align="center">操作</td>
					<td></td>
				</tr>
				<s:iterator value="maps" id="pageList" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td><s:if test="#pageStatus.index%2==1">
							<font>
						</s:if> <s:else>
							<font color="#c0dcf2">
						</s:else> <s:property value="#pageStatus.index+1" /> </font></td>
						<td>${pageList.department }</td>
						<td>${pageList.category}</td>
					<td>
					
					<s:if test="%{name1=='geti'}">
						<a href="RepairAction!salRepairtypeById.action?del_id=${pageList.id }&test='1'">修改/</a>
					</s:if>
					<s:else>
						<a href="RepairAction!salRepairtypeById.action?del_id=${pageList.id }">修改/</a>
					</s:else>
					<s:if test="%{name1=='geti'}">
					<a href="RepairAction!delRepairtype.action?del_id=${pageList.id }&test='1'" >删除</a>
					</s:if>
					<s:else>
					<a href="RepairAction!delRepairtype.action?del_id=${pageList.id }">删除</a>
					</s:else>
					
					</td>
				</s:iterator>
				</tr>
				<tr>
					<s:if test="errorMessage==null">
						<td colspan="12" align="right">第 <font color="red"><s:property
									value="cpage" /> </font> / <s:property value="total" /> 页 <fenye:pages
								cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page"
								theme="number" />
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
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
		function add(){
			var department = $("#department").val();
			var depart_category = $("#depart_category").val();
			if(department==null||department==""){
				alert("请选择部门!");
				return false;
			}
			if(depart_category==null||depart_category==""){
				alert("请输入类别!");
				return false;
			}
		
		}
//		function add() {
//		$("#showProcess").attr("src", "${pageContext.request.contextPath}/System/xinxi/Repair/addrepairtype.jsp");
//		chageDiv('block');
//	}
</script>
</body>
</html>
