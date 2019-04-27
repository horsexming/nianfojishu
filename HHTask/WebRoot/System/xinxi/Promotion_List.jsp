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
				<h2>人员调动信息查看
				<a  href="<%=basePath %>System/xinxi/Promotion_add.jsp">人员晋升申请</a>
				 </h2>
					<font color="red" size="5">${errorMessage}</font>
				<form action="PromotionAction_findpnList.action" method="post" >
					<table class="table" style="width: 50%;">
					<s:if test="status != 'person'">
						<tr>
							<th align="right">
								晋升人部门:
							</th>
							<td >
								<select id="deptname" style="width: 100px;"
									onchange="userlist(0)">
										<option value="0">
											请选择部门
										</option>
								</select>
								<input type="hidden"  name="pn.dept" id="dept" />
								<font id="deptnamefont" color="red"></font>
							</td>
							<th align="right">
								晋升人名字:
							</th>
							<td  id="userstd">
								<select id="username"  style="width: 100px;" onchange="changvalue(this)">
									<option value="0">
										请先选择部门
									</option>
								</select>
							<input type="hidden"  name="pn.userId" id="userId"/>
							<input type="hidden"  name="pn.code" id="code"/>
							<input type="hidden"  name="pn.name" id="name"/>
							<font id="usernamefont" color="red"></font>
						</td>
						<td rowspan="2" align="center">
							<input type="submit" value="查询" id="sub" style="width: 75px;height: 35px;">
						</td>
					</tr>
				</s:if>
					<tr>
						<th align="right">
								晋升职级:
							</th>
							<td >
								<select id="rank" name="pn.rank" class="cxselect">
									<option value=""></option>
									<option value="GP">GP</option>
									<option value="DM">DM</option>
									<option value="DGM">DGM</option>
									<option value="SW">SW</option>
									<option value="GM">GM</option>
								</select>
							</td>
						<th align="right">
							晋升日期:
						</th>
						<td>
							<input  class="Wdate" type="text" style="width: 200px;height: 34px;"
											name="pn.jsTime"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>							
						</td>
						<s:if test="status == 'person'">
							<td>
								<input type="hidden" name="status" value="${status}"/>
								<input type="hidden" name="pn.userId" value="${id}"/>
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
							晋升人姓名
						</th>
						<th>
							晋升人部门
						</th>
						<th>
							原来职级
						</th>
						<th>
							晋升职级
						</th>
						<th>
							原来职务
						</th>
						<th>
							晋升职务
						</th>
						<th>
							申请时间
						</th>
						<th>
							晋升时间
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
					
					<s:iterator value="pnList" id="pageList" status="pageStatus">
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
							${pageList.beforeRank}
						</td>
						<td>
							${pageList.rank}
						</td>
						<td>
							${pageList.beforeDuty}
						</td>
						<td>
							${pageList.duty}
						</td>
						<td>
							${pageList.sqName}
						</td>
						<td>
							${pageList.jsTime}
						</td>
						<td>
							${pageList.sqName}
						</td>
						<td>
							<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态</a>
						</td>
						<td>
							<a href="PromotionAction_findpnById.action?id=${pageList.id}&status=show">明细</a>
							<s:if test="#pageList.ep_status == '未审批' || #pageList.ep_status == '打回'">
								/<a href="PromotionAction_findpnById.action?id=${pageList.id}&status=update">修改</a>/
								<a href="PromotionAction_delpn.action?pn.id=${pageList.id}&cpage=${cpage}" onclick="return confirm('确定要删除吗?')">删除</a>
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
