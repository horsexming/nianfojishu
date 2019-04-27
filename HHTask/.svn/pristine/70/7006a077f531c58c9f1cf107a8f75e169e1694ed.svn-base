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
		<SCRIPT type="text/javascript">
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "DeptNumberAction!findAllDept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id+"_"+this.dept+"_"+this.deptNumber + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
						$(
								"<option value='" + this.id+"_"+this.dept+"_"+this.deptNumber + "'>" + this.dept
										+ "</option>").appendTo("#deptname1");
						//userlist($("#deptname").val());
					});
			$("#deptname").tinyselect();
			$("#deptname1").tinyselect();
			
		}
	});
});
function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var obj = $("#deptname").val();
		obj	=	obj.split("_");
		var deptid = 0;
		var deptname = "";
		if(obj!=null && obj.length == 3){
			deptid = obj[0];
			$("#beforeDept").val(obj[1]);
			$("#befroedeptNo").val(obj[2]);
			
		}
		
	if (deptid == "0") {
		$("#username").empty();
		$("<option value='0'>请先选择部门</option>").appendTo("#username");
		var tinyselect = $(".tinyselect");
		if (tinyselect[1] != null) {
			document.getElementById("userstd").removeChild(tinyselect[1]);
		}
		$("#username").tinyselect();
	} else {
		$
				.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					data : {
						id : deptid
					},
					dataType : "json",
					success : function(data) {
						if (flag == 0) {
							$("#username").empty();
							$("<option value='0'>请选择人员</option>").appendTo(
									"#username");

						}
						$(data).each(
								function() {
									$(
											"<option value='" +this.id+"_"+this.code+"_"+ this.name + "'>"
													+ this.name + "</option>")
											.appendTo("#username");
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("userstd").removeChild(
									tinyselect[1]);
						}
						$("#username").tinyselect();

					}
				});
	}

}
$(document).ready(function() {
	userlist(1);
});
		
function changvalue(obj){
	if(obj!=null && obj.value!="" && obj.value!="0"){
		var v = obj.value;
		var array = v.split("_");
		if(array.length == 3){
			$("#userId").val(array[0]);
			$("#code").val(array[1]);
			$("#name").val(array[2]);
		}
	}
}
function changdept(obj){
	if(obj!=null && obj.value!="" && obj.value!="0"){
		var v = obj.value;
		var array = v.split("_");
		if(array.length == 3){
			$("#dept").val(array[1]);
			$("#deptNo").val(array[2]);
		}
	}
}


</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>人员调动信息查看
				<a  href="<%=basePath %>System/xinxi/transferadd.jsp">人员调动申请</a>
				 </h2>
					<font color="red" size="5">${errorMessage}</font>
				<form action="TransferAction_findtransferList.action" method="post" >
					<table class="table" style="width: 50%;">
					<s:if test="status != 'person'">
						<tr>
							<th align="right">
								原来部门:
							</th>
							<td >
								<select id="deptname" style="width: 100px;"
									onchange="userlist(0)">
										<option value="0">
											请选择部门
										</option>
								</select>
								<input type="hidden"  name="transfer.beforeDept" id="beforeDept" />
								<input type="hidden"  name="transfer.befroedeptNo" id="befroedeptNo" />
								<font id="deptnamefont" color="red"></font>
							</td>
							<th align="right">
								调动人名字:
							</th>
							<td  id="userstd">
								<select id="username"  style="width: 100px;" onchange="changvalue(this)">
									<option value="0">
										请先选择部门
									</option>
								</select>
							<input type="hidden"  name="transfer.userId" id="userId"/>
							<input type="hidden"  name="transfer.code" id="code"/>
							<input type="hidden"  name="transfer.name" id="name"/>
							<font id="usernamefont" color="red"></font>
						</td>
						<td rowspan="2" align="center">
							<input type="submit" value="查询" id="sub" style="width: 75px;height: 35px;">
						</td>
					</tr>
				</s:if>
					<tr>
						<th align="right">
								所调部门:
							</th>
							<td >
								<select id="deptname1"  style="width: 100px;"onchange="changdept(this)">
										<option value="0">
											请选择部门
										</option>
								</select>
								<input type="hidden" value="" name="transfer.dept" id="dept"/>
								<input type="hidden" value="" name="transfer.deptNo" id="deptNo"/>
								<font id="deptname1font" color="red"></font>
							</td>
						<th align="right">
							调动日期:
						</th>
						<td>
							<input  class="Wdate" type="text" style="width: 200px;height: 34px;"
											name="transfer.dgTime"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>							
						</td>
						<s:if test="status == 'person'">
							<td>
								<input type="hidden" name="status" value="${status}"/>
								<input type="hidden" name="transfer.userId" value="${id}"/>
								<input type="submit" value="查询" id="sub" style="width: 75px;height: 35px;">
							</td>
						</s:if>
					</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>
							序号
						</th>
						<th>
							调动人姓名
						</th>
						<th>
							原来部门
						</th>
						<th>
							所调部门
						</th>
						<th>
							申请时间
						</th>
						<th>
							调动时间
						</th>
						<th>
							申请人
						</th>
						<th>
							审批动态
						</th>
						<th>
							操作
						</th>
					</tr>
					
					<s:iterator value="trList" id="pageList" status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.beforeDept}
						</td>
						<td>
							${pageList.dept}
						</td>
						<td>
							${pageList.sqTime}
						</td>
						<td>
							${pageList.dgTime}
						</td>
						<td>
							${pageList.sqName}
						</td>
						<td>
							<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态</a>
						</td>
						<td>
							<a href="TransferAction_showtransferbyId.action?id=${pageList.id}">明细</a>
							<s:if test="#pageList.ep_statuts == '未审批' || #pageList.ep_statuts == '打回'">
							/<a href="TransferAction_showtransferbyId.action?id=${pageList.id}&status=xg">修改</a>/
							<a href="TransferAction_deltransfer.action?transfer.id=${pageList.id}&cpage=${cpage}" onclick="return confirm('确定要删除吗?')">删除</a>
							</s:if>
						</td>
						</tr>
					</s:iterator>
						<tr>

							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
				</table>
				
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
