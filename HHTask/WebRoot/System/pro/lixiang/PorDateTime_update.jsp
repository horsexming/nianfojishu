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
		<script type="text/javascript">
$(function() {
var size = "<s:property value='list.size()'/>";
	for ( var i = 0; i < size; i++) {
     var deptvalue=$("#dept" + i).val();
	  if(deptvalue!=null&&deptvalue!=""){
		findUsers(i,'no');
		}
	}

	//查询所有的部门
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			//$("<option></option>").appendTo("#dept");
			$(allDdept).each(
					function() {
						//var size = "<s:property value='list.size()'/>";
						for ( var i = 0; i < size; i++) {
							$(
									"<option value='" + this.dept + "'>"
											+ this.dept + "</option>")
									.appendTo("#dept" + i);
									}
							
					});
		}

	});
})

//级联用户名称
function findUsers(index,no) {
	$.ajax( {
		url : "UsersAction!findUsersByDept.action",
		type : 'post',
		dataType : 'json',
		data : {
			deptName : $("#dept" + index).val()
		},
		success : function(useradsfa) {
		  if(no==null){
			$("#users" + index).empty();
			$("<option></option>").appendTo("#users" + index);
		  }
			$(useradsfa).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "(" + this.code + ")</option>")
								.appendTo("#users" + index)
					});
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}

//指派
function submitForm(index) {
	$.ajax( {
		type : "POST",
		url : "ProjectManage_addProTime.action",
		dataType : "json",
		data : $("#timeForm" + index).serialize(),
		success : function(msg) {
			if (msg.success) {
				alert("指派成功!");
				if ($("#oldOk" + index).id == null) {
					$("#newOk" + index).show();
				}
			} else {
				alert(msg.message);

			}
		}
	});
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<table class="table">
					<tr>
						<th colspan="6">
							<h2>
								项目核算时间指派管理
							</h2>
							(
							<a href="ProjectManage_findDeptProTime.action?id=${id}&pageStatus=show">查看完成进度</a>)
						</th>
					</tr>
					<tr>
						<th rowspan="2">
							类别名称
						</th>
						<th colspan="4">
							指派信息
						</th>
						<th rowspan="2">
							操作
						</th>
					</tr>
					<tr>
						<th>
							部门
						</th>
						<th>
							人员
						</th>
						<th>
							RTX消息
						</th>
						<th>
							指派时间
						</th>
					</tr>
					<s:iterator value="list" id="pageProTime" status="pageSt">
						<form id="timeForm${pageSt.index}" action="" method="post">
							<tr>
								<th>
									<input name="projectTime.id" value="${pageProTime.id}"
										type="hidden" />
									${pageProTime.className}录入:
								</th>
								<td align="center">
									<select id="dept${pageSt.index}" style="width: 155px;"
										name="projectTime.dept"
										onchange="findUsers('${pageSt.index}')">
										<option value="${pageProTime.dept}">
											${pageProTime.dept}
										</option>
									</select>
								</td>
								<td align="center">
									<select id="users${pageSt.index}" style="width: 155px;"
										name="projectTime.userId">
										<option value="${pageProTime.userId}">
											${pageProTime.userName}
										</option>
									</select>
								</td>
								<td align="center">
									<s:if
										test="#pageProTime.isSendSms==null||#pageProTime.isSendSms=='yes'">
										<input name="projectTime.isSendSms" type="radio" value="yes"
											checked="checked" />
										是
										<input name="projectTime.isSendSms" type="radio" value="no" />
										否</s:if>
									<s:else>
										<input name="projectTime.isSendSms" type="radio" value="yes" />
										是
										<input name="projectTime.isSendSms" type="radio" value="no"
											checked="checked" />
										否</s:else>
								</td>
								<td align="center">
									<input class="Wdate" type="text"
										name="projectTime.provisionTime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',skin:'whyGreen'})"
										value="${pageProTime.provisionTime}" />
								</td>
								<td>
									<input type="button" value="指派"
										onclick="submitForm('${pageSt.index}')" />
									<s:if
										test="#pageProTime.provisionTime!=''&&#pageProTime.provisionTime!=null">
										<img id="oldOk${pageSt.index}" alt=""
											src="<%=basePath%>/images/success.jpg" align="middle">
									</s:if>
									<img id="newOk${pageSt.index}" alt="" style="display: none;"
										src="<%=basePath%>/images/success.jpg" align="middle">
								</td>
							</tr>
						</form>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
