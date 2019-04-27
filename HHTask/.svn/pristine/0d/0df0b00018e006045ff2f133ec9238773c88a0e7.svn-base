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
				<div id="printDiv">
					<br />
					<div style="font-weight: bolder; font-size: 30px; height: 30px;"
						align="center">
						送货签收确认
					</div>
					<s:if test="waigouDelivery!=null">
						<table class="table">
							<tr>
								<td align="left">
									供应商:${waigouDelivery.gysName}
								</td>
							</tr>
							<tr>
								<td align="left">
									供应商编号:${waigouDelivery.userCode}
								</td>
							</tr>
							<tr>
								<td align="left">
									送货人姓名: ${waigouDelivery.shContacts}
								</td>
							</tr>
							<tr>
								<td>
									送货人联系电话: ${waigouDelivery.shContactsPhone}
								</td>
							</tr>
						</table>
					</s:if>
					<br />
					<form action="WaigouwaiweiPlanAction!updateDeliveryToQr.action?pageStatus=${pageStatus}"
						method="post" onsubmit="return check()">
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center" rowspan="2">
									<input type="hidden"
										value="<s:property value="list.size() " />" id="size" />
									序号
								</th>
								<!-- 
								<th align="center">
									采购订单号
								</th> -->
								<th align="center">
									件号
								</th>
								<th align="center">
									零件名称
								</th>
								<th align="center">
									规格
								</th>
								<th align="center">
									图号
								</th>
								<th align="center">
									转换数量
								</th>
							</tr>
							<tr>
								<th align="center">
									版本
								</th>
								<th align="center">
									送货数量
								</th>
								<th align="center">
									实际数量
								</th>
								<th align="center">
									物料位置
								</th>
								<th align="center">
									转换单位
								</th>
							</tr>
							<s:iterator value="list" id="pageWgww2" status="pageStatus2">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
									<td rowspan="2">
										<s:property value="#pageStatus2.index+1" />
									</td>
									<!-- 
									<td>
										${pageWgww2.cgOrderNum}
									</td> -->
									<td>
										${pageWgww2.markId}
									</td>
									<td>
										${pageWgww2.proName}
									</td>
									<td>
										${pageWgww2.specification}
									</td>
									<td>
										${pageWgww2.tuhao}
									</td>
									<td>
										<input type="text"
											name="list_wdd[${pageStatus2.index}].zhuanhuanNum"
											style="width: 60px;" value="${pageWgww2.zhuanhuanNum}"
											id="zhuanhuanNum_${pageStatus2.index}">
									</td>
								</tr>
								<tr align="center">
									<td>
										${pageWgww2.banben}
									</td>
									<td>
										${pageWgww2.shNumber}${pageWgww2.unit}
									</td>
									<td>
										<input type="hidden" name="list_wdd[${pageStatus2.index}].id"
											value="${pageWgww2.id}" id="id_${pageStatus2.index}">
										<input type="hidden"
											name="list_wdd[${pageStatus2.index}].shNumber"
											value="${pageWgww2.shNumber}">
										<input type="hidden"
											name="list_wdd[${pageStatus2.index}].markId"
											value="${pageWgww2.markId}">
										<input type="text" style="width: 60px;"
											name="list_wdd[${pageStatus2.index}].qrNumber"
											value="${pageWgww2.shNumber}"
											id="qrNumber_${pageStatus2.index}"
											onchange="getzhuanhuanNum('${pageStatus2.index}')">
									</td>
									<td>
										<select name="list_wdd[${pageStatus2.index}].changqu"
											id="HouseName${pageStatus2.index}"
											onchange="changvalue1('${pageStatus2.index}')">
										</select>
										<select name="list_wdd[${pageStatus2.index}].kuwei"
											id="Position${pageStatus2.index}">
											<option value=""></option>
										</select>
									</td>
									<td>
										<input type="text" value="${pageWgww2.zhuanhuanUnit}"
											name="list_wdd[${pageStatus2.index}].zhuanhuanUnit"
											style="width: 75px;" />
									</td>
							</s:iterator>
							<tr>
								<th colspan="8">
									<input type="submit" value="确认签收" class="input" id="subGray"
										onclick="todisabled(this)">
								</th>
							</tr>
						</table>
					</form>
					<input type="hidden" value="${pageStatus}" id="ps">
				</div>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	var ps = $("#ps").val();
	if(ps!=null && ps=="fl"){
		changvalue("综合库", $("#size").val());
	}else{
		changvalue("待检库", $("#size").val());
	}
})
function changvalue(obj, num) {
	if (obj != null && obj != "") {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwaListByNO.action",
			data : {
				wareHouseName : obj
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					for ( var i = 0; i < num; i++) {
						$(data).each(
								function() {
									$("#HouseName" + i).append(
											'<option value='
													+ this.goodHouseName + '>'
													+ this.goodHouseName
													+ '</option>');
								});
						$("#Position" + i).val("");
						changvalue1(i);
					}
				}
			}
		});
	}
}

function changvalue1(num) {
	var obj = $("#HouseName" + num).val();
	if (obj != null && obj != "") {
		var ps = $("#ps").val();
		if(ps!=null && ps=="fl"){
			ps = "综合库";
		}else{
			ps="待检库";
		}
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwnListByNO.action",
			data : {
				wareHouseName : ps,
				cangqu : obj
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					$("#Position" + num).empty();
					$(data).each(
							function() {
								$("#Position" + num).append(
										'<option value=' + this.number + '>'
												+ this.number + '</option>');
							});
				}
			}
		});
	}
}

function getzhuanhuanNum(num) {
	$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!getzhuanhuanNum.action",
		data : {
			id : $("#id_" + num).val(),
			sumNum : $("#qrNumber_" + num).val()
		},
		dataType : "json",
		success : function(data) {
			if (data != null && data.length == 2) {
				$("#zhuanhuanNum_" + num).val(data[0]);
			}
		}
	})
}

function check() {
	var sumObj = $("#subGray");
	sumObj.attr("disabled", "disabled");
	return true;
}
</script>
	</body>
</html>
