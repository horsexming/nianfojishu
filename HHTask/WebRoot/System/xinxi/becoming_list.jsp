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
								"<option value='" + this.id+"_"+this.dept+ "'>" + this.dept
										+ "</option>").appendTo("#deptname");
						//userlist($("#deptname").val());
					});
			$("#deptname").tinyselect();
			
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
			$("#dept").val(obj[1]);
			
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
</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>员工转正信息查看
				 </h2>
					<font color="red" size="5">${errorMessage}</font>
				<form action="BecomingAction_findBecoming.action" method="post" >
					<table>
						<tr>
							<th>
								待转正人工号:
							</th>
							<td>
								<input type="text" name="becoming.code"/>
							</td>
							<th>
								待转正人姓名:
							</th>
							<td>
								<input type="text" name="becoming.name"/>
							</td>
							<td rowspan="2">
								<input type="hidden" value="${status}" name="status"/>
								<input type="submit" value="查询" style="width: 75px;height: 35px;"/>
							</td>
						</tr>
						<tr>
							<th>
								申请人姓名:
							</th>
							<td>
								<input type="text" name="becoming.sqname"/>
							</td>
							<th>
								申请人时间:
							</th>
							<td>
								<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  class="Wdate" name="becoming.sqadte"/>  
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>
							序号
						</th>
						<th>
							待装正人姓名
						</th>
						<th>
							待装正人部门
						</th>
						<th>
							当前状态
						</th>
						<th>
							转正后状态
						</th>
						<th>
							申请时间
						</th>
						<th>
							转正时间
						</th>
						<th>
							申请人姓名
						</th>
						<th>
							审批动态
						</th>
						<th>
							操作
						</th>
					</tr>
					
					<s:iterator value="becomingsList" id="pageList" status="pageStatus">
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
							${pageList.dept}
						</td>
						<td>
							${pageList.befroestatus}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
							${pageList.sqdate}
						</td>
						<td>
							${pageList.zzdate}
						</td>
						<td>
							${pageList.sqname}
						</td>
						<td>
							<a href="CircuitRunAction_findAduitPage.action?id=${pageList.ep_Id}">审批动态</a>
						</td>
						<td>
							<a href="BecomingAction_getBecomingByid.action?id=${pageList.id}&status=show">明细</a>
							<s:if test="#pageList.ep_status == '未审批' || #pageList.ep_status == '打回'">
								/<a href="BecomingAction_getBecomingByid.action?id=${pageList.id}&status=update">修改</a>/
								<a href="BecomingAction_del.action?becoming.id=${pageList.id}&cpage=${cpage}" onclick="return confirm('确定要删除吗?')">删除</a>
							</s:if>
							
						</td>
						</tr>
					</s:iterator>
						<tr>

							<td colspan="30" align="right">
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
