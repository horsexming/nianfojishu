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
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/javascript/jquery-easyui-1.3.1/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/javascript/jquery-easyui-1.3.1/themes/icon.css">
		<script type="text/javascript"
			src="<%=basePath%>/javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/jquery-easyui-1.3.1/jquery.easyui.min.js">
</script>
		<script>
var list = new Array();
$(function() {
	$('#tab').treegrid( {
		url : 'ProcardAction!initTrial.action?id=${id}',
		singleSelect : true,
		toolbar : '#tb',
		idField : 'id',
		treeField : 'name',
		onClickRow : editNod,
		onAfterEdit : function sendData(myRow, changes) {
			list.push(myRow.id);
			if (myRow.jobNum != "") {
				$.ajax( {
					url : "productPriceAction!getNameByNum.action",
					type : "post",
					dataType : 'json',
					data : {
						code : myRow.jobNum
					},
					success : function(d) {
						if (d) {
							myRow.handlers = d.name;
							$('#tab').treegrid('updateRow', {
								index : myRow.id,
								row : myRow
							});
						}
					}
				});
			} else {
				myRow.handlers = "";
				$('#tab').treegrid('updateRow', {
					index : myRow.id,
					row : myRow
				});
			}
		},
		fitColumns : true,
		frozenColumns : [ [ {
			field : 'name',
			title : '名称',
			align : 'left',
			rowspan : 3,
			width : 200
		} ] ],
		columns : [ [ {
			field : 'pieceNum',
			title : '件号',
			align : 'right',
			rowspan : 3,
			width : 65
		}, {
			field : 'stationNum',
			title : '工位号',
			align : 'center',
			rowspan : 3,
			width : 65
		}, {
			field : 'equipmentNum',
			title : '设备号',
			align : 'center',
			rowspan : 3,
			width : 65
		}, {
			field : 'handlers',
			title : '操作者',
			align : 'center',
			rowspan : 3,
			width : 10,
			width : 65
		}, {
			field : 'jymes',
			title : '检验记录',
			align : 'left',
			rowspan : 3,
			width : 20
		}] ]
	});
});
var editIndex = undefined;
var editIndex2 = undefined;

function onClickRow() {
	nod = $('#tab').treegrid('getSelected');
	if (editIndex != nod.id) {
		if (endEditing()) {
			$('#tab').treegrid("beginEdit", nod.id);
			editIndex = nod.id;
		}
	}
}
function accept() {
	if (endEditing()) {
		$('#tab').treegrid('acceptChanges');
	}
}
function editNod() {
	endEditing();
	nod = $('#tab').treegrid('getSelected');
	if (nod.type != "PR") {
		return true;
	}
	if (editIndex != nod.id) {
		endEditing();
		$('#tab').treegrid("beginEdit", nod.id);
		editIndex = nod.id;
	}
}
function endEditing() {
	if (editIndex == undefined) {
		return true
	}
	if ($('#tab').treegrid('validateRow', editIndex)) {
		$('#tab').treegrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function editNod2(index) {
	if (editIndex2 != index) {
		if (endEditing2()) {
			$('#pro').datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex2 = index;
		} else {
			$('#pro').datagrid('selectRow', editIndex2);
		}
	}
}
function endEditing2() {
	if (editIndex2 == undefined) {
		return true
	}
	if ($('#pro').datagrid('validateRow', editIndex2)) {
		var ed = $('#pro').datagrid('getEditor', {
			index : editIndex2,
			field : 'productid'
		});

		$('#pro').datagrid('endEdit', editIndex2);
		editIndex2 = undefined;
		return true;
	} else {
		return false;
	}
}

function trial(id) {
	var bol = ergodic();
	jump(id);
}
function jump(id) {
	//处理日产量
	var dayOutputVal;
	if ($('#pro').datagrid('validateRow', editIndex2)) {
		var dayOutput = $('#pro').datagrid('getEditor', {
			index : editIndex2,
			field : 'dayOutput'
		});
		if (dayOutput != null) {
			dayOutputVal = $(dayOutput.target).val();
			window.location = "<%=basePath%>System/SOP/produce/Bonus_showTrial.jsp?id="
					+ id + "&dayOutput=" + dayOutputVal;
		} else {
			window.location = "<%=basePath%>System/SOP/produce/Bonus_showTrial.jsp?id="
					+ id;
		}
	}
}
var index = 0;
function ergodic() {
	if (list) {
		if (index >= list.length) {
			return false;
		}
		var myRow = $('#tab').treegrid("find", list[index]);
		$.ajax( {
			url : "ProcardTemplateAction!saveData.action",
			type : "post",
			async : false,
			data : {
				id : myRow.realId,
				jobNum : myRow.jobNum,
				OPLabourBeat : myRow.oPLabourBeat,
				OPEquipmentBeat : myRow.oPEquipmentBeat,
				PRLabourBeat : myRow.pRLabourBeat,
				PRPrepareTIme : myRow.pRPrepareIndex
			}
		});
		index++;
		ergodic();
	} else {
		return false;
	}
}
function test() {
	var a = $('#tab').treegrid('getData');
	$.each(a, function(index, element) {
		if (element) {
			$.each(element.children, function(i, ele) {
				if (ele) {
					$.each(ele.children, function(ind, el) {
						if (el.type == 'PR') {
						}
					});
				}
			});
		}
	});
}
function send() {
	if (list) {
		for ( var i = 0; i < list.length; i++) {
			var myRow = $('#tab').treegrid("find", list[i]);
			$.ajax( {
				url : "productPriceAction!saveData.action",
				type : "post",
				data : {
					id : myRow.realId,
					jobNum : myRow.jobNum,
					OPLabourBeat : myRow.oPLabourBeat,
					OPEquipmentBeat : myRow.oPEquipmentBeat,
					PRLabourBeat : myRow.pRLabourBeat,
					PRPrepareTIme : myRow.pRPrepareIndex
				}
			});
		}
	}
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form
					action="ProcardAction!findProcardByRunCard2.action?id=51287&pageStatus=history&viewStatus="
					method="post">
					<table class="table">
						<tr>
							<th colspan="6">
								产品全息信息查询
							</th>
						</tr>
						<tr>
							<td>
								件号:
							</td>
							<td>
								<input name="procard.markId" value="${procard.markId}" />
							</td>
							<td>
								批次:
							</td>
							<td>
								<input name="procard.selfCard" value="${procard.selfCard}" />
							</td>
							<td>
								<s:if test="pageStatus!='bzsq'">
									<input type="submit" value="查询" class="input">
								</s:if>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div>
			<h3>
				订单信息
				<%--					(Order Information)--%>
			</h3>
			<table class="table" style="width: 100%;">
				<tr>
					<th align="right" style="width: 150px;">
						订单编号:
						<%--							<br />--%>
						<%--							Order Number :--%>
					</th>
					<td>
						${orderManager.orderNum}
					</td>
				</tr>
				<%--<tr>
						<th align="right">
							总金额:
							<br />
							Total amount :
						</th>
						<td>
							${orderManager.totalAmount}
						</td>
					</tr>
					--%>
				<tr>
					<th align="right">
						客户编号:
						<%--							<br />--%>
						<%--							:--%>
					</th>
					<td>
						${orderManager.custome.number}
					</td>
				</tr>
				<tr>
					<th align="right">
						跟单人:
						<%--							<br />--%>
						<%--							:--%>
					</th>
					<td>
						${orderManager.documentaryPeople}
					</td>
				</tr>
				<tr>
					<th align="right">
						交付日期:
						<%--							<br />--%>
						<%--							:--%>
					</th>
					<td>
						${orderManager.paymentDate}
					</td>
				</tr>
				<tr>
					<th align="right">
						协商交付日期:
						<%--							<br />--%>
						<%--							:--%>
					</th>
					<td>
						${orderManager.paymentDate2}
					</td>
				</tr>
				<tr>
					<th align="right">
						交付状态:
						<%--							<br />--%>
						<%--							:--%>
					</th>
					<td>
						${orderManager.deliveryStatus}
					</td>
				</tr>
			</table>
			<%--<h3>
				订单对应产品
									<br />
									(Order the corresponding product)
			</h3>
			<table class="table" style="width: 100%;">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
						序号
													<br />
													No.
					</td>
					<td align="center">
						件号
													<br />
													Part No.
					</td>
					<td align="center">
						产品名称
													<br />
													Product Name
					</td>
					<td align="center">
						数量
													<br />
													Quantity
					</td>
				</tr>
				<s:iterator value="orderManager.products" id="pageList"
					status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#pageStatus.index+1" />
					</td>
					<td>
						${pageList.pieceNumber }
						<s:if test="#pageList.ywMarkId!=null">
							<br />
							<font color="red">(${pageList.ywMarkId})</font>
						</s:if>
					</td>
					<td>
						${pageList.name}
					</td>
					<td>
						${pageList.num}
					</td>
					<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.unitPrice}
						</td>
						
					<tr>
						<th align="left" colspan="20"
							style="background-color: #c33e41; border: 0px; margin: 0px; padding: 0px; font-size: 3px;">
							<div align="left"
								style="background-color: #33cc33; width:${pageList.sellCount/pageList.num*100}%; text-overflow: ellipsis; white-space: nowrap; height: 100%;">
								&nbsp;&nbsp;&nbsp;&nbsp;产品交付进度:
								${pageList.sellCount}/${pageList.num}
							</div>
						</th>
					</tr>
				</s:iterator>
				</tr>
			</table>
		--%>
		</div>
		<div>
			<div style="margin-top: 10px;">
			</div>
			<div>
			</div>
			<form id="test" action="">
				<table class="table" title="产品过程数据汇总">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							件号
						</td>
						<td align="center">
							产品名称
						</td>
						<td align="center">
							批次
						</td>
						<td align="center">
							数量
						</td>
					</tr>
					<tr>
						<td align="center">
							${procard.markId}
						</td>
						<td align="center">
							${procard.proName}
						</td>
						<td align="center">
							${procard.selfCard}
						</td>
						<td align="center">
							${nums}
						</td>
					</tr>
				</table>
				<table id="tab" title="生产数据明细">
				</table>
			</form>
			<div id="tb" style="height: auto">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
