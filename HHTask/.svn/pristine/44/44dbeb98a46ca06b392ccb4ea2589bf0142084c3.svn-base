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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="RequisitionAction!findAll.action?requisition.name="
						style="color: #ffffff">返回</a>
				</div>
			</div>

			<div align="center">
				<font id="msgfont" color="red" size="5mm"></font>
				<s:if test="msg=='修改'">
					<form action="MeasuringAction_updateandsaveMeasuring1.action"
						onsubmit="return check()" method="post"
						enctype="multipart/form-data">
				</s:if>
				<s:else>
					<form action="MeasuringAction_updateandsaveMeasuring.action"
						onsubmit="return check()" method="post"
						enctype="multipart/form-data">
				</s:else>


				<table border="1" width="100%" class="table">
					<tr>
						<td colspan="20" align="center"
							style="font-family: 微软雅黑; font-weight: bold;">
							量具校检操作
						</td>
					</tr>
					<s:if test='msg!="周期"'>
						<tr>
							<th align="right">
								工号
							</th>
							<td>
								<input name="cpage" type="hidden" value="${cpage}">
								<s:if test='msg=="标识"'>
									<input type="text" readonly="readonly" title="只读" name="empno"
										value="${checkrecord.empno}" />
								</s:if>
								<s:else>
									<%--									<input id="code" onblur="send(this)" name="empno" value="${user.code}" />--%>
									<input type="text" readonly="readonly" name="empno"
										value="${code}" />
								</s:else>
							</td>
							<th align="right">
								姓名
							</th>
							<td>
								<s:if test='msg=="标识"'>
									<input name="empname" title="只读" readonly="readonly"
										value="${checkrecord.empname}" />
									<input name="lasttime" type="hidden"
										value="${checkrecord.reportdate}" />
								</s:if>
								<s:else>
									<%--						<input id="userName" name="empname" title="只读" readonly="readonly" />--%>
									<input name="empname" title="只读" readonly="readonly"
										value="${loginname} " />
								</s:else>

							</td>
							<th align="right">
								报修人
							</th>
							<td>
								<input type="text" name="loginname" value="${loginname}">
							</td>
						</tr>
					</s:if>

					<tr>
						<th align="right">
							名称
						</th>
						<td>
							<input type="hidden" value="${measuring.id}" name="measuring.id" />
							<input type="text" " 
									name="measuring.matetag"
								value="${measuring.matetag}" />
						</td>

						<th align="right">
							仓库
						</th>
						<td>

							<input type="text" class="xiugai" name="measuring.storehouse"
								value="${measuring.storehouse} " />
						</td>
						<th align="right">
							分类
						</th>
						<td>
							<input type="text" class="xiugai" name="measuring.parClass"
								value="${measuring.parClass}" />
						</td>
					</tr>
					<tr>
						<th align="right">
							位置
						</th>
						<td>
							<input type="text" name="measuring.place"
								value="${measuring.place}" />
						</td>

						<th align="right">
							校准状态
						</th>
						<td>
							<input type="text" disabled="disabled" type="hidden"
								name="measuring.calibrationstate"
								value="${measuring.calibrationstate}">
						</td>
						<th align="right">
							校准周期
						</th>
						<td>
							<s:if test='msg=="周期"'>
								<input type="text" name="measuring.period"
									value="${measuring.period}">(天)
								<input type="hidden" name="t" value="3" />
							</s:if>
							<s:else>
								<s:if test='msg=="标识"'>
									<input type="text" disabled="disabled" type="hidden"
										name="measuring.period" value="${measuring.period}">
									<input type="hidden" name="period" value="${measuring.period}">
								</s:if>
								<s:else>
									<input type="text" name="measuring.period"
										value="${measuring.period}">
								</s:else>
							</s:else>

						</td>
					</tr>
					<tr>
						<th align="right">
							本厂编号
						</th>
						<td>
							<s:if test="msg=='修改'">
								<input type="text" name="measuring.measuring_no"
									value="${measuring.measuring_no}">
							</s:if>
							<s:else>
								<input type="text" readonly="readonly"
									name="measuring.measuring_no" value="${measuring.measuring_no}"
									onchange="isone(this)">
							</s:else>
						</td>

						</td>
						<s:if test="msg=='标识'||msg=='修改'">
							<th align="right">
								此次校准时间
							</th>
							<td>
								<input class="Wdate" type="text" readonly="readonly"
									id="calibrationTime" name="measuring.calibrationTime"
									value="${measuring.calibrationTime}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								责任人工号
							</th>
							<td>
								<input type="text" value="${measuring.codeliable}"
									onblur="getUsers()" onkeyup="getUsers()" id="codeLiable"
									name="measuring.codeliable">
							</td>
					</tr>
					<tr>
						<th align="right">
							本体编码
						</th>
						<td>
							<input type="text" name="measuring.number"
								value="${measuring.number}">
						</td>
						<th align="right">
							校准类型
						</th>
						<td>
							<s:if test='measuring.jztype=="内校"'>
								<input type="radio" value="内校" name="measuring.jztype"
									checked="checked">内校
									<input type="radio" value="外校" name="measuring.jztype">外校
								</s:if>
							<s:else>
								<input type="radio" value="内校" name="measuring.jztype">内校
									<input type="radio" value="外校" name="measuring.jztype"
									checked="checked">外校
								</s:else>
						</td>
						<th align="right">
							责任人姓名
						</th>
						<td>
							<input type="text" id="personliable" readonly="readonly"
								value="${measuring.personliable}" name="measuring.personliable">
							<input type="hidden" value="${measuring.usersIdliable}"
								name="measuring.usersIdliable" id="usersIdliable" />
						</td>
					</tr>
					</s:if>
					<s:else>
						<th align="right">
							校验报告
						</th>
						<td>
							<input type="file" name="attachment" id="attachment1" />
						</td>
						</tr>
						<tr>
							<th align="right">
								此次校准时间
							</th>
							<td>
								<input class="Wdate" type="text" readonly="readonly"
									id="calibrationTime" name="measuring.calibrationTime"
									value="${measuring.calibrationTime}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td colspan="10">


							</td>
						</tr>
					</s:else>
					<tr>
						<th align="right">
							校验报告
						</th>
						<td>
							<input type="file" name="attachment" id="attachment1" />
						</td>
						<th align="right">
							测试精度
						</th>
						<td>
							<input type="text" name="measuring.csjd"
								value="${measuring.csjd}" />
						</td>
						<th></th>
						<td></td>
						<th></th>
						<td></td>
					</tr>
					<tr>

						<td colspan="6" align="center">
							<input type="hidden" name="text" value="${text}" />
							<s:if test='msg=="标识"'>
								<input type="submit" value="正常"
									style="width: 100px; height: 50px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="报废"
									onclick="window.location.href='MeasuringAction_updateMeasuringandstore.action?num=${measuring.total}&MeasuringId=${measuring.id}&empno=${checkrecord.empno}'"
									style="width: 100px; height: 50px;">
							</s:if>
							<s:else>
								<input type="submit" value="修改"
									style="width: 100px; height: 50px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 100px; height: 50px;">
							</s:else>
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
function check() {
	var attachment1 = $("#attachment1").val();
	if (attachment1 == "") {
		if (!confirm('未上传校验报告是否继续提交?')) {
			return false;
		}
	}
}

function send(obj) {
	sendRequest("UsersAction!findCardIdBCodeForzgkh.action?user.code="
			+ obj.value, messageResponse);
}
// 人员查询		
	function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var code = document.getElementById("code");//工号
			var userName = document.getElementById("userName");//姓名
			var value = message.split("|");
			if (value[1] == null) {
				userName.value = "";
				code.focus();
				code.select();
				code.title = message;
				code.style.border = " solid 1px red";
				return;
			} else {
				code.title = "该工号存在!";
				userName.value = value[1];
				code.style.border = " solid 1px";
			}

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
 

function getUsers(){
		var code = $("#codeLiable").val();
		if(code!=""){
				$.ajax( {
		type : "POST",
		url : "CheckoutAndGagesAction_findUsersByCode.action",
		data : {
			code:code		
		},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				if(data.onWork == '离职'){
					$("#msgfont").html("工号:"+code+"的员工:"+data.name+"已离职，请从新输入责任人工号!");
					$("#codeLiable").val("");
				}else if(data.internal == "否"){
					$("#msgfont").html("工号:"+code+"的员工:"+data.name+"不为内部员工，请从新输入责任人工号!");
					$("#codeLiable").val("");
				}else{
					$("#msgfont").html("");
					$("#personliable").val(data.name);
					$("#usersIdliable").val(data.id);
				}
			}else{
				$("#msgfont").html(code+"不是内部工号，请重新输入。");
				$("#personliable").val("");
				$("#usersIdliable").val("");
			}
		}
	})
		}
		
	}

function isone(obj){
	if(obj!=null && obj.vlaue!=''){
				$.ajax( {
		type : "POST",
		url : "MeasuringAction_findMeasuringById.action",
		data : {
			empname:obj.vlaue	
		},
		dataType : "json",
		success : function(data) {
			if(!data){
				$("#msgfont").html(+"本厂编号:"+obj.vlaue+"重复!");
			}
		}
	})
	}
	
}
</script>
	</body>
</html>
