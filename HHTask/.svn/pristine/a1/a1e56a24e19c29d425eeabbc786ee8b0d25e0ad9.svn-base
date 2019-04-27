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
	<body onload="description()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="AccessEquipmentAction_toadd_Web.action?tag=${tag}"
						style="color: rgb(79, 77, 77)"><br />
					</a>
				</div>
			</div>

			<div align="center">
				<h3>
					<s:if test="tag=='dag'">
					添加档案柜
				</s:if>
					<s:else>
					添加摄像头
				</s:else>
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="AccessEquipmentAction_add_Web.action?tag=${tag}"
					method="post" onsubmit="return validate()">
					<s:if test="tag=='dag'">
						<table class="table">
							<tr style="width: 100%">
								<th align="center">
									档案柜类型
								</th>
								<td align="center">
									<SELECT name="accessWebcam.type" id="type" style="width: 173px;" onchange="description()">
										<option value="dag">档案柜</option>
										<option value="yz">印章柜</option>
										<option value="pz">凭证柜</option>
										<option value="fp">发票柜</option>
									</SELECT>
								</td>
								<th align="center">
									档案柜编号
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.cabinetNum"
										id="cabinetNum" />
								</td>
							</tr>
							<tr>
								<th align="center">
									柜子开门指令
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.cabinetOpenInstruction"
										id="cabinetOpenInstruction" />
								</td>
								<th align="center">
									存放文件类型
								</th>
								<td align="center">
									<select id="select" style="width:150px;height:21px;" onchange="fuzhi()">
									</select>
									<input id="cabinetType" name="accessWebcam.cabinetType"
									style="width:132px;height:19px;margin-left:-153px;border-width: 0px;" type="text"  />
								</td>
							</tr>
							<tr>
								<th align="center">
									柜子最大存储数
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.maxNum" id="maxNum" />
								</td>
								<th align="center">
									柜子Ip
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.cabinetAccessSim"
										id="cabinetAccessSim" />
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="submit" value="添加(Add)"
										style="width: 100px; height: 50px;" />
								</td>
							</tr>
						</table>
					</s:if>
					<s:else>
						<table class="table">
							<tr style="width: 100%">
								<th align="center">
									摄像头名称
									<input type="hidden" name="accessEquipment.id"
										value="${accessEquipment.id}" />
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.webcamName"
										id="webcamName" />
								</td>
								<th align="center">
									摄像头序列号
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.webcamNum" id="webcamNum" />
								</td>
							</tr>
							<tr>
								<th align="center">
									摄像头供应商
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.webcamSupplier"
										id="webcamSupplier" />
								</td>
								<th align="center">
									摄像头用途
								</th>
								<td align="center">
									<select name="accessWebcam.webcamOutIn" id="webcamOutIn"
										style="width: 152px;">
										<option value="">
											设备用途(进门/出门)
										</option>
										<option value="进门">
											进门
										</option>
										<option value="出门">
											出门
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="center">
									摄像头IP
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.webcamIP" id="webcamIP" />
								</td>
								<th align="center">
									摄像头端口
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.webcamPort"
										id="webcamPort" />
								</td>
							</tr>
							<tr>
								<th align="center">
									摄像头安装位置
								</th>
								<td align="center">
									<input type="text" name="accessWebcam.webcamLocation"
										id="webcamLocation" />
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="submit" value="添加(Add)"
										style="width: 100px; height: 50px;" />
								</td>
							</tr>
						</table>
					</s:else>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
if('${tag}'=='dag'){
		if (!validateText("cabinetNum", "档案柜编号")) {
			return false;
		}
		if (!validateText("type", "档案柜类型")) {
			return false;
		}
		if (!validateText("cabinetOpenInstruction", "柜子开启指令")) {
			return false;
		}
		if (!validateText("cabinetType", "存放文件类型")) {
			return false;
		}
		if (!validateText("maxNum", "柜子最大存贮数")) {
			return false;
		}
		if (!validateText("cabinetAccessSim", "柜子IP")) {
			return false;
		}
	}else{
		if (!validateText("webcamName", "摄像头名称")) {
			return false;
		}
		if (!validateText("webcamNum", "摄像头编号")) {
			return false;
		}
		if (!validateText("webcamOutIn", "摄像头用途(进门/出门)")) {
			return false;
		}
		if (!validateText("webcamIP", "摄像头IP")) {
			return false;
		}
		if (!validateText("webcamPort", "摄像头端口")) {
			return false;
		}
		if (!validateText("webcamLocation", "摄像头摆放位置")) {
			return false;
		}
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
function fuzhi() {
	$("#cabinetType").val($("#select option:selected").text());
}
function description(){
	var dangan = $("#type").val();
	if (dangan != "") {
		$.ajax( {
			url : "AccessEquipmentAction!findDescription.action",
			type : 'post',
			dataType : 'json',
			cache : false,//防止数据缓存
			data : {
				type : dangan
			},
			success : function(useradsfa) {
				$("#select").empty();//清空
				if(useradsfa==null||useradsfa==''){
					$("<option value=''>无</option>").appendTo("#select");
					$("#cabinetType").val('');
				}else{
					var se = useradsfa.split('|');
					$(se).each(
						function() {
							$(
								"<option value='"
									+ this + "'>"
									+ this
									+ "</option>")
								.appendTo("#select")
						}
					);
				}
			},
			error : function() {
				alert("服务器异常!");
			}
		});
	} else {
	}
}

</script>
	</body>
</html>
