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
				<form action="UsersAction!findAllUsersCard.action" method="POST">
					<table class="table">
						<tr>
							<th>
								卡号:
							</th>
							<td>
								<input type="text" value="" name="userscard.cardId"/>
							</td>
							<th>
								持卡人姓名:
							</th>
							<td>
								<input type="text" value="" name="userscard.ckUserName"/>
							</td>
						</tr>
						<tr>
							<th>
								持卡人工号:
							</th>
							<td>
								<input type="text" value="" name="userscard.ckUserCode"/>
							</td>
							<th>
								所属部门:
							</th>
							<td>
								<select name="user.dept" id="dept">
								</select>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>卡号</th>
						<th>持卡人姓名</th>
						<th>持卡人工号</th>
						<th>所属部门</th>
						<th>所属班组</th>
						<th>操作</th>
					</tr>
					<s:iterator value="usercardList" id="pageList" status="">
					<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#statussdf.index+1" />
							</td>
							<td>${pageList.cardId}</td>
							<td>${pageList.ckUserName}</td>
							<td>${pageList.ckUserCode}</td>
							<td>${pageList.dept}</td>
							<td ondblclick="ShowupdateGroup(this,'${pageList.id}')">
								${pageList.groupcalass}
							</td>
							<td>
								<a href="UsersAction!findUsersAll.action?id=${pageList.id}&pageStatus=wbd">未绑定人员</a>/
								<a href="UsersAction!findUsersAll.action?id=${pageList.id}&pageStatus=ybd">已绑定人员</a>/
								<a href="UsersAction!delUsersCard.action?id=${pageList.id}" onclick="return confirm('确定要删除吗?')">删除</a>
							</td>
							</tr>
					
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
	$(function(){
		createDept('dept');
	})

	function ShowupdateGroup(obj,id){
		var grop =	$(obj).html();
		grop =$.trim(grop);
		$(obj).html('<input type="text" value="'+grop+'"/>');
		$(obj).attr('ondblclick','updateGroup(this,'+id+')');
	}
	function updateGroup(obj,id){
		var  obj1=	$(obj).find('input')[0];
		var grop = $(obj1).val();
	if(id>0 && grop!=''){
		$.ajax( {
		type : "POST",
		url : "UsersAction!updateUsersCard.action",
		data : {
			'userscard.id':id,
			'userscard.groupcalass':grop
		},
		dataType : "json",
		success : function(data) {
			if(data){
				alert('修改成功!');
				$(obj).html(grop);
				$(obj).attr('ondblclick','ShowupdateGroup(this,'+id+')');
			}
		}
	})
		}	
	}
	</script>
	</body>
</html>
