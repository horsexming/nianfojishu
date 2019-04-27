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
	<script type="text/javascript" src="../../../javascript/jquery-easyui-1.3.1/jquery-1.4.3.js"></script>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h1>
					<font color="#46d0f5">修改请假</font>
				</h1>
				<form action="AskForLeaveAction!saveOrUpdate.action" method="post" onsubmit="return checkType();">
					<input type="hidden" name="askForLeave.leaveId"
						value="${askForLeave.leaveId}" />
					<table class="table">
						<tr>
							<th align="right">
								请假类型:
							</th>
							<td align="left">
								<s:if test="askForLeave.leaveType=='个人请假'">
									<input id="" type="radio" name="askForLeave.leaveType"
										value="${askForLeave.leaveType}" onclick="changeType('1')"
										checked="checked" readonly="readonly"/>
									个人请假
									<input type="radio" name="askForLeave.leaveType"
										value="${askForLeave.leaveType}" onclick="changeType('2')" />
									代理请假
								</s:if>
								<s:else>
									<input id="" type="radio" name="askForLeave.leaveType"
										value="${askForLeave.leaveType}" onclick="changeType('1')" />
									个人请假
									<input type="radio" name="askForLeave.leaveType"
										value="${askForLeave.leaveType}" onclick="changeType('2')"
										checked="checked" readonly="readonly" />
									代理请假
								</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								请假人所在部门:
							</th>
							<td align="left">
								<select id="dept" style="width: 155px;"
									name="askForLeave.leavePersonDept">
									<option value="${askForLeave.leavePersonDept}">
										${askForLeave.leavePersonDept}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								请&nbsp;假&nbsp;人:&nbsp;
								<input type="hidden" id="leaveUserCardId"
									name="askForLeave.leaveUserCardId" value="${askForLeave.leaveUserCardId}"
								/>
								<input type="hidden" id="accessStatus"
									name="askForLeave.accessStatus" value="${askForLeave.accessStatus}"
								/>
								<input type="hidden" id="leaveObjectType"
									name="askForLeave.leaveObjectType" value="${askForLeave.leaveObjectType}"
								/>
								<input type="hidden" id="carPaiNum"
									name="askForLeave.carPaiNum" value="${askForLeave.carPaiNum}"
								/>
							</th>
							<td align="left">
								<input id="userName" type="text" name="askForLeave.leavePerson"
									readonly="readonly" value="${askForLeave.leavePerson}" />
								<select id="users" style="display: none; width: 155px;">
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								请假人工号:
							</th>
							<td align="left">
								<input type="text" id="leavePersonCode" name="askForLeave.leavePersonCode"
									value="${askForLeave.leavePersonCode}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								请假时间:
							</th>
							<td align="left">
								<input class="Wdate" type="text" id="startDate"
									name="askForLeave.leaveStartDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
									value="${askForLeave.leaveStartDate}" />
								至
								<input class="Wdate" type="text" id="endDate"
								    name="askForLeave.leaveEndDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
									value="${askForLeave.leaveEndDate}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								假事类型:
							</th>
							<td align="left">
								<s:radio id="radioType" list="#{'事假':'事假','病假':'病假','丧假':'丧假','婚假':'婚假','换休':'换休','年休':'年休','公出':'公出','产假':'产假'}"
									listKey="key" label="假事类型" required="true" onclick="gongchu(this)"
									requiredposition="center" listValue="value" value="事假"
									name="askForLeave.leaveTypeOf" cssClass="fileType"/>
								<div id="gongchuStyle" style="display:none;">
								选择公出目的地：
								<select name="askForLeave.gongchuPlace" >
									<option value="省内">省内</option>
									<option value="省外">省外</option>
								</select>
								</div>
							</td>
						</tr>
						<tr>
							<th align="right">
								请假原因:
							</th>
							<td align="left">
								<s:textarea label="请假原因" name="askForLeave.leaveReason" id="reason"
									cols="50" rows="10" value="%{#attr.askForLeave.leaveReason}" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" class="input" value="修改请假" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		//处理公出
		function gongchu(obj){
			var qjStyle=obj.value;
			if("公出"==qjStyle){
				$("#gongchuStyle").show();
			}else{
				$("#gongchuStyle").hide();
			}
		}
	//单选按钮的鼠标点击触发事件
	function changeType(type) {
		if (type == "1") {
			$("#userName").val("${Users.name}");
			$("#userName").css("display", "block");
			$("#users").css("display", "none");
			$("#userName").attr("readonly", "readonly");
			$("#dept").empty();
			$("<option value='${Users.dept}'>${Users.dept}</option>").appendTo(
					"#dept");
		} else if (type == "2") {
			$("#userName").css("display", "none");
			$("#users").css("display", "block");
			//显示所有部门信息
			$.ajax({
				url : 'DeptNumberAction!findAllDept.action',
				dataType : 'json',
				cache : false,//防止数据缓存
				success : function(allDdept) {
					$("#dept").empty();
					$("<option value=''>--请选择部门--</option>").appendTo("#dept");
					$(allDdept).each(
							function() {
								$(
										"<option value='" + this.dept + "'>"
												+ this.dept + "</option>")
										.appendTo("#dept");
							});
				}

			});
			//显示部门对应的员工信息
			$("#dept")
					.bind(
							"change",
							function() {
								if ($("#dept").val() != "") {
									$
											.ajax({
												url : "UsersAction!findUsersByDept.action",
												type : 'post',
												dataType : 'json',
												cache : false,//防止数据缓存
												data : {
													deptName : $("#dept").val()
												},
												success : function(useradsfa) {
													$("#users").empty();//清空
													$("<option></option>")
															.appendTo("#users");
													$(useradsfa)
															.each(
																	function() {
																		$(
																				"<option value='"
														                    + this.code+"|"
																			+ this.name
																			+ "'>"
																						+ this.name
																						+ "</option>")
																				.appendTo(
																						"#users")
																	});
													$("#users")
															.bind(
																	"change",
																	function() {
																		$(
																				"#userName")
																				.val(
																						$(
																								"#users")
																								.val());
																	});
												},
												error : function() {
													alert("服务器异常!");
												}
											});
								}

							});

		}
		$("#users").bind("change", function() {
			var users = $("#users").val();
			var usersData = users.split("|");
			var code = usersData[0];
			$("#leavePersonCode").val(code);
		});
	}
	function checkType(){
            var val = document.getElementById('startDate').value;
		    var val2 = document.getElementById('endDate').value;
		    var val3 = document.getElementById('reason').value;
		    if(!val || val == "" || !val2 || val2 == ""){
		        alert("时间不能为空，请选择请假开始和结束时间！！！");
		        return false;
		    }else if(!val3 || val3 == ""){
		        alert("请假原因不能为空，请准确填写请假原因！！！");
		        return false;
		    }
		     if(val>val2){
		    	 alert("请假开始时间不能大于结束时间！！！");
		        return false;
		    }
            var obj2=document.getElementsByName('askForLeave.leaveTypeOf');
            var error='';
            /* 非空验证 ******************************** */
            var i=0,type=false;
            for (i=0;i<obj2.length;i++){
	            if (obj2[i].checked) type=true;  
            }
            if (type==false){ 
	                 error="请选择假事类型！\n"; 
	                 alert(error); 
            }else{
            	return true;
            }
             return false;
     }
</script>
	</body>
</html>
