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
	<body onload="createDept('ndept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					添加部员车辆信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="InEmployeeCarInforAction_add.action" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="right">
								部门
								<input type="hidden" name="tag" value="nb" />
							</th>
							<td align="left">
								<select id="ndept" name="inEmployeeCarInfor.ndept"
									style="width: 155px;">
									<option value="">
										--请选择部门--
									</option>
								</select>
							</td>
							<th align="right">
								姓名
							</th>
							<td align="left">
								<select id="name" style="width: 155px;" onclick="deptNotNull()">
									<option></option>
								</select>
								<input id="bfName" name="inEmployeeCarInfor.name" type="hidden">
							</td>
						</tr>
						<tr>
							<th align="right">
								工号
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.ncode" id="ncode"
									readonly="readonly" />
							</td>
							<th align="right">
								员工车牌号
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.nplates"
									id="nplates" />
							</td>
						</tr>
						<tr>
							<th align="right">
								车辆类型
								<input type="hidden" name="inEmployeeCarInfor.carInCangType"
									value="内部" />
							</th>
							<td align="left">
								<select name="inEmployeeCarInfor.carType" id="carType"
									style="width: 156px;" onclick="dianji()">
									<option value="">
										请选择车辆类型（公车/个人）
									</option>
									<option value="公车">
										公车
									</option>
									<option value="个人">
										个人
									</option>
								</select>
							</td>
							<th align="right">
								进出是否发送RTX消息
							</th>
							<td align="left">
								<select name="inEmployeeCarInfor.rtxMessage" id="rtxMessage"
									style="width: 156px;">
									<option value="">
										请选择
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>
						</tr>

						<tr>
							<th align="right">
								车型品牌
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.carModels"
									id="carModels" />
							</td>
							<th align="right">
								车颜色
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.carColor"
									id="carColor" />
							</td>
						</tr>
						<tr id="mytable" style="display: none">
							<th align="right">
								价格（元）
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.price" id="price"
									onkeyup="mustBeNumber('price')" />
							</td>
							<th align="right">
								折旧公里数
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.zjDistance"
									id="zjDistance" onkeyup="mustBeNumber('zjDistance')" />
							</td>
						</tr>
						<tr id="mytable1" style="display: none">
							<th align="right">
								行驶公里数
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.driveDistance"
									id="driveDistance" onkeyup="mustBeNumber('driveDistance')" />
							</td>
							<th align="right">
								每公里价钱（不含折旧费）
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.drivePrice"
									id="drivePrice" onkeyup="mustBeNumber('drivePrice')" />
							</td>
						</tr>

						<tr>
							<th align="right">
								汽车行驶证附件
							</th>
							<td align="left" colspan="1">
								<input type="file" name="fujian" id="carFiles" />
							</td>
							<th align="right">
								是否白名单
							</th>
							<td>
								<select name="inEmployeeCarInfor.whiteCar" id="whiteCar"
									style="width: 156px;">
									<option value="">
										请选择
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								出门是否检车
							</th>
							<td colspan="1">
								<select name="inEmployeeCarInfor.nbdaijian" id="nbdaijian"
									style="width: 156px;">
									<option value="">
										请选择
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>
							<th align="right">
								出门是否申请
							</th>
							<td colspan="1">
								<select name="inEmployeeCarInfor.nbdaiSQ" id="nbdaiSQ"
									style="width: 156px;">
									<option value="">
										请选择
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>

				</form>
			</div>
		</div>

		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("ncode", "员工工号")) {
		return false;
	}
	if (!validateText("name", "员工姓名")) {
		return false;
	}
	if (!validateText("ndept", "员工部门")) {
		return false;
	}
	if (!validateText("nplates", "员工车牌号")) {
		return false;
	}
	if (!validateText("carType", "车辆类型（个人/公司）")) {
		return false;
	}
	if (!validateText("rtxMessage", "是否发送RTX消息")) {
		return false;
	}
	if (!validateText("carFiles", "上传文件")) {
		return false;
	}
	if (!validateText("whiteCar", "是否为白名单")) {
		return false;
	}
	if (!validateText("nbdaijian", "出门是否检车")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}



//根据部门显示人员
$(function(){
	//显示部门对应的员工信息
	$("#ndept").bind(
			"change",
			function() {
				if ($("#ndept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#ndept").val()
						},
						success : function(useradsfa) {
							$("#name").empty();//清空
							$("#ncode").val("");
							$("<option></option>").appendTo("#name");
							$(useradsfa).each(
									function() {
										$(
												"<option value='"
															+ this.code + "|"
															+ this.name + "'>"
															+ this.name
															+ "</option>")
												.appendTo("#name")
									});
								$("#name").bind("change", function() {
								var name = $("#name").val();
								var usersData = name.split("|");
								var ncode = usersData[0];
								var bfName = usersData[1];
								$("#ncode").val(ncode);
								$("#bfName").val(bfName);
							});
							
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#name").empty();//清空
				$("#ncode").val("");
				}
		});
});

function deptNotNull() {
	if ($("#ndept").val() == "" || $("#ndept").val() == "") {
		alert("被访人部门不能为空！");
		return false;
	}
}
//判断车是否为个人用车
function dianji(){
	var obj_select = document.getElementById("carType");
	var obj_div = document.getElementById("mytable");
	var obj_div1 = document.getElementById("mytable1");
		if($("#carType").val()=="个人"){
			$("#mytable").hide();
			$("#mytable1").hide();
		}
		else{
			$("#mytable").show();
			$("#mytable1").show();
		}
	
	
}
</script>
	</body>
</html>
