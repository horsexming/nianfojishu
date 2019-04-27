<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		//jisuanWage();
		var selfCode = $("#selfCode"), selfRenshu = $("#selfRenshu"), selfOneRengong = $("#selfOneRengong"), selfUserName = $("#selfUserName");
		//计算所需工资
		$.ajax( {
			type : "POST",
			url : "osaAction!jsWage.action",
			data : {
				selfCode : selfCode.val()
			},
			dataType : "json",
			success : function(data) {
				selfUserName.val(data[0]);
				selfOneRengong.val(data[1]);
				selfRenshu.val(data[2]);
				selfCode.val(data[3])
				if (data[4] != "") {
					alert(data[4]);
				}
			}
		});
});
//表单检查
function chackForm() {
	//var selfCode = document.getElementById("selfCode");//员工号
	var customer = document.getElementById("customer");//客户
	var markID = document.getElementById("markID");//件号
	var processNO = document.getElementById("processNO");//籍贯
	var deliveryCount = document.getElementById("deliveryCount");//交付数量
	var orderId = document.getElementById("orderId");//订单号
	var yuceCount = document.getElementById("yuceCount");//预测产量
	var procardCycle = document.getElementById("procardCycle");//加工周期
	var productCysle = document.getElementById("productCysle");//产品生命周期
	var osOneRengong = document.getElementById("osOneRengong");//外委单件人工费用
	var osOneMater = document.getElementById("osOneMater");//单件材料
	var osOneOthsers = document.getElementById("osOneOthsers");//单价其他
	var selfOneRengong = document.getElementById("selfOneRengong");//自制所需工资总额
	//var presentAddress = document.getElementById("presentAddress");//现住址
	/*if(selfCode.value()==""){
		alert("请填写员工号!");
		selfCode.focus();
		return false;
	}*/
	if (customer.value == "") {
		alert("客户信息不能为空!");
		customer.focus();
		return false;
	} else if (markID.value == "") {
		alert("零件号不能为空!");
		markID.focus();
		return false;
	} else if (processNO.value == "") {
		alert("工序号不能为空!");
		processNO.focus();
		return false;
	} else if (deliveryCount.value == "") {
		alert("交付数不能为空!");
		deliveryCount.focus();
		return false;
	} else if (orderId.value == "") {
		alert("订单号不能为空!");
		orderId.focus();
		return false;
	} else if (yuceCount.value == "") {
		alert("预测产量不能为空!");
		yuceCount.focus();
		return false;
	} else if (procardCycle.value == "") {
		alert("加工周期不能为空!");
		procardCycle.focus();
		return false;
	} else if (productCysle.value == "") {
		alert("产品生命周期不能为空!");
		productCysle.focus();
		return false;
	} else if (osOneRengong.value == "") {
		alert("外委单件人工不能为空!");
		osOneRengong.focus();
		return false;
	} else if (osOneMater.value == "") {
		alert("单价材料不能为空!")
		osOneMater.focus();
		return false;
	} else if (osOneOthsers.value == "") {
		alert("单价其他不能为空!")
		osOneOthsers.focus();
		return false;
	//} else if (selfOneRengong.value == "") {
		//alert("自制所需工资总额不能为空!");
	//	selfOneRengong.focus();
	//	return false;
	} else {
		return true;
	}
}

$(function() {
	//下拉订单号
	$.ajax( {
		type : "POST",
		url : "osaAction!findOrderNum.action",
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$("<option value='" + this + "'>" + this + "</option>")
								.appendTo("#orderId")
					});
		}
	});

	//报警单号
	$.ajax( {
		type : "POST",
		url : "osaAction!findMaintenance.action",
		dataType : "json",
		success : function(data) {
			var bj = "<option></option>";
			$(data).each(function() {
				bj += "<option value='" + this + "'>" + this + "</option>";
			});
			$(bj).appendTo("#alertNum")
		}
	});
	//下拉原材料
	$.ajax( {
		type : "POST",
		url : "osaAction!tosaveOSApp.action",
		dataType : "json",
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				$(bj += "<option value='"
						+ [ obj.paihao, obj.guige, obj.jiage, obj.danwei ]
						+ "'>牌号" + obj.paihao + "                规格"
						+ obj.guige + "</option>");
			});
			$(bj).appendTo("#yuancailiao")
		}
	});
});

function jisuanWage() {
	var selfCode = $("#selfCode"), selfRenshu = $("#selfRenshu"), selfOneRengong = $("#selfOneRengong"), selfUserName = $("#selfUserName");
	if (selfCode.val() == "") {
		alert("请填写工号!");
		return false;
	} else {
		//计算所需工资
		$.ajax( {
			type : "POST",
			url : "osaAction!jsWage.action",
			data : {
				selfCode : selfCode.val()
			},
			dataType : "json",
			success : function(data) {
				selfUserName.val(data[0]);
				selfOneRengong.val(data[1]);
				selfRenshu.val(data[2]);
				selfCode.val(data[3])
				if (data[4] != "") {
					alert(data[4]);
				}
			}
		});
	}
}
function getJiaGe(Value) {
	var aaa = Value.split(",");
	document.getElementById("paihao").value = aaa[0];
	document.getElementById("guige").value = aaa[1];
	document.getElementById("jiage").value = aaa[2];
	document.getElementById("danwei").value = aaa[3];

	var shuliang = document.getElementById("shuliang").value;
	if (shuliang != "") {
		var jiage = document.getElementById("jiage").value;
		document.getElementById("osOneMater").value = jiage * shuliang;
	}

}
function getZonge() {
	var jiage = document.getElementById("jiage").value;
	var shuliang = document.getElementById("shuliang").value;
	document.getElementById("osOneMater").value = jiage * shuliang;
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
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
							style="color: #ffffff">添加自制新增成本</a>
					</div>
				</div>

				<div align="center">
					<form action="osaAction!updateOSAById4.action?cpage=<s:property value="#parameters.cpage"/>" method="post"
						onsubmit="return chackForm()" enctype="multipart/form-data">
						<table width="100%" border="0" class="table">
						<tr>
								<th colspan="6" align="left">
									<br />
									产品信息
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									申报部门:
								</th>
								<td>
								${sessionScope.Users.dept}
								</td>
								<th align="right">
									客户名称:
								</th>
								<td>
									${osa.customer}
								</td>
								<th align="right">
									加急:
								</th>
								<td>
									${osa.isJiaji}
								</td>
							</tr>
							<tr>

								<th align="right">
									零件号:
								</th>
								<td>
								${osa.markID}
								</td>
								<th align="right">
									外委交付数量:
								</th>
								<td colspan="3">
									${osa.deliveryCount}
								</td>
							</tr>
							<tr>
							<th align="right">
									工序号:
								</th>
								<td>
								${osa.processNO} 
								</td>
								<th align="right">
									工序名:
								</th>
								<td colspan="3">
								${osa.processName}
								</td>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									自制新增成本
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									增加人数:
								</th>
								<td>
									<s:if test="osa.addWorker!=0&&osa.addWorker!=null">
											<input id="addWorker"
												onkeyup="this.value=this.value.replace(/\D/g,'')"
												name="osa.addWorker" class="horizontalLine" value="${osa.addWorker}"
												onfocus="chageClass(this,'')"
												onblur="chageClass(this,'horizontalLine')" maxlength="11">
											<font color="red"> 位</font>
									</s:if>
									<s:else>
										<input id="addWorker"
												onkeyup="this.value=this.value.replace(/\D/g,'')"
												name="osa.addWorker" class="horizontalLine" value="0"
												onfocus="chageClass(this,'')"
												onblur="chageClass(this,'horizontalLine')" maxlength="11">
											<font color="red"> 位</font>
									
									</s:else>								
									
										
								</td>
								<th align="right">
									累计人工成本:
								</th>
								<td>
									<s:if test="osa.oneWorkerMoney!=0 &&osa.oneWorkerMoney!=null">
										<input id="oneWorkerMoney" name="osa.oneWorkerMoney"
												class="horizontalLine" value="${osa.oneWorkerMoney}" onfocus="chageClass(this,'')"
												onblur="chageClass(this,'horizontalLine')">
											<font color="red"> 元/月</font>
								</s:if>
								<s:else>
											<input id="oneWorkerMoney" name="osa.oneWorkerMoney"
												class="horizontalLine" value="0" onfocus="chageClass(this,'')"
												onblur="chageClass(this,'horizontalLine')">
											<font color="red"> 元/月</font>
								</s:else>
								
								</td>

								<th align="right">
									新增设备报价:
								</th>
								<td>
									<s:if test="osa.addNewMachineFile!=null ">
									<input type="file" name="abilityLack"  style="width: 150px;">
<%--									<a href="DownAction.action?fileName=${osa.addNewMachineFile}&directory=/upload/osa/abilityLack/">附件</a>--%>
									<a href="FileViewAction.action?FilePath=/upload/osa/abilityLack/${osa.addNewMachineFile}">附件</a>
									</s:if>
									<s:else>
										<input type="file" name="abilityLack"  style="width: 150px;">
									</s:else>
<%--									<input type="file" name="abilityLack" style="width: 150px;">--%>
								</td>
							</tr>
							<tr>
								<th align="right">
									增加设备成本:
								</th>
								<td>
								<s:if test="osa.addMachineCost!=0 && osa.addMachineCost!=null">
										<input id="addMachineCost" name="osa.addMachineCost"
											class="horizontalLine" value="${osa.addMachineCost}" onfocus="chageClass(this,'')"
											onblur="chageClass(this,'horizontalLine')">
										<font color="red"> 元*</font>
								</s:if>
									<s:else>
											<input id="addMachineCost" name="osa.addMachineCost"
											class="horizontalLine" value="0" onfocus="chageClass(this,'')"
											onblur="chageClass(this,'horizontalLine')">
										<font color="red"> 元*</font>
									</s:else>
								</td>

								<th align="right">
									回收期:
								</th>
								<td>
								<s:if test="osa.recoryPeriod!=0&&osa.recoryPeriod!=null">
										<input id="recoryPeriod" name="osa.recoryPeriod"
										class="horizontalLine" value="${osa.recoryPeriod}" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" />
									<font color="red"> 年*</font>
								</s:if>
								<s:else>
										<input id="recoryPeriod" name="osa.recoryPeriod"
										class="horizontalLine" value="9" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" />
									<font color="red"> 年*</font>
								</s:else>
								</td>
								<th align="right">
									增加辅助人数:
								</th>
								<td>
								<s:if test="osa.addAssistWorker!=0&&osa.addAssistWorker!=null">
										<input id="addAssistWorker"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										name="osa.addAssistWorker" class="horizontalLine" value="${osa.addAssistWorker}"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" maxlength="11">
								</s:if>
								<s:else>
									<input id="addAssistWorker"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										name="osa.addAssistWorker" class="horizontalLine" value="0"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" maxlength="11">
								</s:else>
								</td>
							</tr>
							<tr>
							
								<th align="right">
									员工号(自制):
									<br />
									人数(自制):
								</th>
								<td>
									<input id="selfCode" name="osa.selfCode" class="horizontalLine"
										value="${osa.selfCode}" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine'),jisuanWage()">
									(员工号以","分割)
									<br />
									<input id="selfRenshu" name="osa.selfRenshu"
										value="${osa.selfRenshu}" readonly="readonly"
										class="horizontalLine">
									<font color="red"> 位*</font>
								</td>
								<th align="right">
									姓名(自制):
								</th>
								<td>
										<input id="selfUserName" class="horizontalLine"
										readonly="readonly">
									<input type="hidden" id="selfOneRengong"
										name="osa.selfOneRengong" class="horizontalLine" value="${osa.selfOneRengong}"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
								</td>
								
								<th align="right">
									辅助累计成本:
								</th>
								<td>
								<s:if test="osa.oneAssistWorkerMoney!=0 && osa.oneAssistWorkerMoney!=null">
										<input id="oneAssistWorkerMoney"
										name="osa.oneAssistWorkerMoney" class="horizontalLine"
										value="${osa.oneAssistWorkerMoney}" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
									<font color="red"> 元/月</font>
								</s:if>
								<s:else>
									<input id="oneAssistWorkerMoney"
										name="osa.oneAssistWorkerMoney" class="horizontalLine"
										value="0" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')">
									<font color="red"> 元/月</font>
								</s:else>
									
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<br />
									<br />
									<input type="hidden" name="osa.id" value="${osa.id}">
									<input type="submit" value="修改"
										style="width: 80px; height: 50px;" />
									<input type="reset" value="重置"
										style="width: 80px; height: 50px;" />
									<br />
									<br />
								</td>
							</tr>
						</table>
					</form>
					<div>
						<font color="red">${successMessage}</font>
						<font color="red">${errorMessage}</font>
					</div>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
