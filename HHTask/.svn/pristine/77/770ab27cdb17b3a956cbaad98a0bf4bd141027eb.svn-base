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
				<form action="QuotedPrice_showInvestors.action" method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden">
					<table class="table">
						<tr>
							<th colspan="6">
								<h2>
									项目投资人管理
								</h2>
							</th>
						</tr>
						<tr>
							<th align="right">
								姓名:
							</th>
							<th align="left">
								<input name="investor.name"
									value="${investor.name}" />
							</th>
							<th align="right">
								工号:
							</th>
							<th align="left">
								<input name="investor.code"
									value="${investor.code}" />
							</th>
						</tr>
						<tr>
							<th align="right">
								部门:
							</th>
							<th align="left">
								<select name="investor.dept" id="dept" style="width: 100px;">
								<s:if test="investor.dept==null">
									<option value="请选择部门">
										请选择部门
									</option>
								</s:if>
								<s:else>
									<option value="<s:property value="investor.dept"/>">
										<s:property value="investor.dept" />
									</option>
								</s:else>
							</select>
							</th>
							<th align="right">
								类型:
							</th>
							<th align="left">
								<select name="investor.type" id="type"
									style="width: 155px;">
								<option></option>
								<option>正常</option>
								<option>黑名单</option>
								</select>
							</th>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="查询" class="input" />
								<input type="button" value="添加投资人" class="input" onclick="addTzr()"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div>
				<table class="table">
				<tr>
						<td colspan="11" align="center"><h3>项目可参与人员管理</h3></td>
					</tr>
					<tr>
					 <th>
					 	序号
					 </th>
					 <th>
					 	姓名
					 </th>
					 <th>
					 	工号
					 </th>
					 <th>
					 	部门
					 </th>
					 <th>
					 	类型
					 </th>
					 <th>
					 	操作
					 </th>
					</tr>
					<s:iterator value="investorList" id="pageinvestor" status="pageIndex" >
					<s:if test="#pageIndex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
					 	<s:property value="#pageIndex.index+1"/>
					 </td>
						<td>
					 	${pageinvestor.name}
					 </td>
					 <td>
					 	${pageinvestor.code}
					 </td>
					 <td>
					 	${pageinvestor.dept}
					 </td>
					 <td>
					 	<label id="type<s:property value="#pageIndex.index"/>">${pageinvestor.type}</label>
					 </td>
					 <td>
					 	<s:if test="#pageinvestor.type=='黑名单'">
					 	<label id="zg<s:property value="#pageIndex.index"/>"><a onclick="updatezg('恢复',${pageinvestor.id},<s:property value="#pageIndex.index"/>)">恢复资格</a></label>
					 	</s:if>
					 	<s:else>
					 	<label id="zg<s:property value="#pageIndex.index"/>"><a onclick="updatezg('取消',${pageinvestor.id},<s:property value="#pageIndex.index"/>)">取消资格</a></label>
					 	</s:else>
					 </td>
					</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		$(document).ready(function(){
			adddept();
		});
function adddept() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.dept + "'>" + this.dept
										+ "</option>").appendTo("#dept");
						//userlist($("#deptname").val());
					});
		}
	});
}
		function addTzr(){
			window.location.href = "QuotedPrice_toaddTzr.action?cpage="+${cpage};
		}
		function updatezg(op,id,index){
			$.ajax( {
		type : "post",
		url : "QuotedPrice_updatezg.action",
		data : {
				op : op,
				id : id
		},
		dataType : "json",
		success : function(data) {
				if("true"==data){
					alert(op+"投资资格成功!");
					if(op=="恢复"){
					$("#type"+index).html("正常");
					$("#zg"+index).html("<a onclick='updatezg(\"取消\","+id+","+index+")'>取消资格</a>");
					}else if(op=="取消"){
					$("#type"+index).html("黑名单");
					$("#zg"+index).html("<a onclick='updatezg(\"恢复\","+id+","+index+")'>恢复资格</a>");
					}
				}else{
					alert(data);
				}
		}
	});
		}
		</script>
	</body>
</html>
