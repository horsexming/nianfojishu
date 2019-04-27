<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

			<div>
				<textarea style="display: none;" id="ta">
					<tr id="zptr_0">
							<td align="center">
								<SELECT id="type_0" >
									<option value="项目主管">
										项目主管
									</option>
								</SELECT>
							</td>
							<td id="depttd_0" align="center">
								<SELECT id="dept_0" onchange="selectdept('_0')">
								</SELECT>
							</td>
							<td id="usertd_0" align="center">
								<SELECT id="user_0">
								</SELECT>
							</td>
							<td align="center">
								<input id="time_0" class="Wdate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
								value='${projectManageyf.reTime}'>
							</td>
							<td align="center">
								<input id="zp_0" type="button" value="指派" onclick="zp('_0')" />
								<input id="quxiao_0" type="button" value="取消"
								onclick="quxiaozp('_0')" />
								<img id="newOk_0" alt="" style="display: none;"
								src="${pageContext.request.contextPath}/images/success.jpg"
								align="middle">
							</td>
						</tr>
				</textarea>
			</div>
			<div align="center">
				指派
				<table class="table" id="zptable">
					<tr>
						<th>
							类型
						</th>
						<th>
							部门
						</th>
						<th>
							人员
						</th>
						<th>
							预完成时间
						</th>
						<th>
							操作
							<input type="button" value="增加" onclick="addline()">
						</th>
					</tr>
					<s:if test="yfUserList!=null&&yfUserList.size()>0">
						<s:iterator value="yfUserList" id="yfUser" status="yfStatus">
							<tr id="zptr_${yfStatus.index}">
								<td align="left">
									${yfUser.usertype}
									<input id="hidId_${yfStatus.index}" type="hidden"
										value="${yfUser.id}">
								</td>
								<td align="left">
									${yfUser.userDept}
								</td>
								<td align="left">
									${yfUser.userName}
								</td>
								<td align="left">
									${yfUser.yfDate}
								</td>
								<td>
									<input type="button" value="取消"
										onclick="quxiaozp('_${yfStatus.index}')" />
									<img id="newOk_${yfStatus.index}" alt=""
										src="<%=basePath%>/images/success.jpg" align="middle">
								</td>
							</tr>
						</s:iterator>
						<input type="hidden" value="${fn:length(yfUserList)}" id="yfUserListSize"/>
					</s:if>
					<s:else>
						<input type="hidden" value="0" id="yfUserListSize"/>				
					</s:else>
				</table>
				<br />
				<br />
				<br />
				<div align="left"
					style="bottom: 30px; position: absolute; left: 25px;">
					<p>
						注：
					</p>
					<p>
						本功能为指派项目主管，由项目主管管理本项目
					</p>
				</div>
			</div>
		</div>
		
		
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var zpindex=0;
var depts;//所有部门
$(document).ready(function() {
	var hadsize=$("#yfUserListSize").val();
	if(hadsize==null||hadsize=="" || hadsize==0){
		zpindex=-1;
	}else{
		zpindex =hadsize-1;//zpindex+(hadsize);
	}
	addline();
	
});

function setDept(index){
	//查询所有的部门
	$.ajax( {
		type:"post",
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		async:true,
		success:function(allDdept) {
			$("<option value='0'>请选择部门</option>").appendTo("#dept_"+index);
			$(allDdept).each(function(){
				$( "<option value='" + this.id + "'>" + this.dept + "</option>") .appendTo("#dept_" + index);
			});
			$("#dept_"+index).tinyselect();
		},
		error:function(){
			alert("获取部门失败");
		}
	});
	
}
function selectdept(className){
	var deptId = $("#dept" + className).val();
	$("#user"+className).empty();
	if(deptId==0){
		$("<option value='0'>请先选择部门</option>").appendTo("#user"+className);
	}else{
		$.ajax( {
			type : "post",
			url : "GzstoreAction_getusers.action",
			data : {
				id : deptId
			},
			dataType : "json",
			success : function(data) {
					$("<option value='0'>请选择人员</option>").appendTo(
							"#user"+className);
				$(data).each( function() { $( "<option value='" + this.id + "'>" + this.name + "</option>")
									.appendTo("#user"+className);
						});
				var tinyselect = $(".tinyselect");
				if (tinyselect[1] != null) {
					document.getElementById("usertd"+className).removeChild(
							tinyselect[1]);
				}
				$("#user"+className).tinyselect();
			}
		});
	}
}

function zp(className){
	var type=$("#type"+className).val();
	var user=$("#user"+className).val();
	var time=$("#time"+className).val();
	var reTime = "${projectManageyf.reTime}";
	d1 = new Date(time.replace(/\-/g, "\/"));
	d2 = new Date(reTime.replace(/\-/g, "\/"));
	if(d1>d2){
		alert("指派预完成时间大于项目预完成时间");
		return false;
	}
	if("请先选择部门"==user){
		return ;
	}
	$.ajax( {
		url : 'projectPoolAction_projectManagerAssign.action',
		dataType : 'json',
		type : "post",
		data : {
					'yfUser.rootId' : ${id},
					'yfUser.usertype' : type,
					'yfUser.userId' : user,
					'yfUser.yfDate' : time
					},
		cache : false,//防止数据缓存
		success : function(data) {
					if(data.message=="成功"){
						$("#zp"+className).hide();
						$("#quxiao"+className).show();
						$("#newOk"+className).show();
						$("#hidId"+className).val(data.data);
					}else{
						alert(data.message);
					}
		}

	});
}

function addline(){
	zpindex++;
	var str = $("#ta").val();
	if(zpindex>0){
		while (str.indexOf('_0') >= 0){
	       str = str.replace('_0', '_'+zpindex);
	    }
	}
	$("#zptable").append(str);
	setDept(zpindex);
}

function quxiaozp(className){
	if(confirm("确定取消?")){
		var hidId=$("#hidId"+className).val();
		if(hidId==null||hidId==0){
	//		zpsize--;
			zpindex--;
			$("#zptr"+className).remove();
		}else{
			$.ajax( {
			url : 'projectPoolAction_cancelAssion.action',
			dataType : 'json',
			data : {
				id : hidId,
			},
			cache : false,//防止数据缓存
			success : function(data) {
						if(data=="成功"){
					//		zpsize--;
							zpindex--;
							$("#zptr"+className).remove();
						}else{
							alert(data);
						}
					}
				});
		}
	}
}
</SCRIPT>
	</body>
</html>
