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
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				<table style="width: 70%" class="table">
					<tr>
						<th align="right">
							工位编号:
						</th>
						<td>
							${zhShebei.gongweiId}
						</td>
					</tr>
					<tr>
						<th align="right">
							设备编号:
						</th>
						<td>
							${zhShebei.shebeiId}
						</td>
					</tr>
					<tr>
						<th align="right">
							设备名称:
						</th>
						<td>
							${zhShebei.shebeiname}
						</td>
					</tr>
					<tr>
						<th align="right">
							设备工序名称:
						</th>
						<td>
							${zhShebei.shebeigongxuName}
						</td>
					</tr>
				</table>
				<br />
				<br />
				<br />
				<table class="table">
					<tr bgcolor="#c0dcf2">
						<th>
							序号
						</th>
						<th>
							可操作员工工号
						</th>
						<th>
							可操作员工名称
						</th>
						<th>
							操作员工所对应等级
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="zhCaozuoEmp1" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>
								${pageIndex.index+1}
							</th>
							<th>
								${zhCaozuoEmp1.nameId}
							</th>
							<th>
								${zhCaozuoEmp1.name}
							</th>
							<th>
								<s:iterator value="listAll" id="zhCaozuoDengji1" status="pageIndex">
								<s:if test="#zhCaozuoEmp1.dengjiId==#zhCaozuoDengji1.id">
										${zhCaozuoDengji1.caozuodengji}
								</s:if>
								</s:iterator>
							</th>
							<th> <a href="caoZuoAction!deletezhCaozuoEmp.action?zhCaozuoEmp.id=${zhCaozuoEmp1.id}&zhShebei.id=${zhShebei.id}">删除</a></th>
						
						</tr>
					</s:iterator>
				</table>
				<br />
				<br />
				<br />
				<br />
				<br />



				<form action="caoZuoAction!addzhCaozuoEmp.action" method="post"
					theme="simple">
					<table class="table">
						<tr>
							<th colspan="4">
								<font style="color: red;">添加操作员工</font>
							</th>
						</tr>
						<tr>
							<th align="right">
								部门
							</th>
							<td>
								<select   id='dept'  name="queXian.tianbaodept" onclick="createDept('dept')" style="width:90px;" onchange="getDept(this.value)">
			   			  </select>
							</td>

							<th align="right">
								员工工号
							</th>
							<td>
								<select id="gonghao" name="gonghao" style="width: 270px;"
									onchange="f1(this.value)"></select>
							</td>
						</tr>
						<tr>
							<th align="right">
								员工姓名
							</th>
							<td>
								<input type="text" name="zhCaozuoEmp.name" id="name"
									readonly="readonly">
									<input type="hidden" name="zhCaozuoEmp.nameId" id="NameId">
							<input type="hidden" value="${zhShebei.id}"
								name="zhCaozuoEmp.shebeiId">
							</td>
							<th>操作等级</th>
							<td>	<s:select id="dengjiId" name="zhCaozuoEmp.dengjiId" list="listAll" listKey="id"
									listValue="caozuodengji" /></td>

						</tr>

						<tr>
							<td colspan="4" align="center">
								<s:submit value="添加" cssClass="input" align="center" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<SCRIPT type="text/javascript">
	function getDept(deptName){
		$.ajax( {
		type : "POST",
		url : "caoZuoAction!listEmp.action",
		data: {pageName:deptName},
		dataType : "json",
		success : function(object) {
			var bj = "";
			$.each(object, function(i, obj) {
				bj += "<option value='"+ [obj.code,obj.name]+ "'>"+"工号:"+ obj.code+"              "+  "姓名:"+ obj.name   +"</option>";
			});
			$(bj).appendTo("#gonghao")
		}
	  });
	}
	/**
	 $(function(){
	 //下拉工位编号
	$.ajax( {
			var dept=document.getElementById("dept");
		type : "POST",
		url : "caoZuoAction!listEmp.action",
		data: {pageName:dept},
		dataType : "json",
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='"+ [obj.code,obj.name]+ "'>"+ obj.code +"</option>";
			});
			$(bj).appendTo("#gonghao")
		}
	  });
	})*/
	function f1(gonghao){
			var gonghaoIdex=gonghao.split(",");
			document.getElementById("NameId").value=gonghaoIdex[0];
			document.getElementById("name").value=gonghaoIdex[1];
	}
	</SCRIPT>

</html>
