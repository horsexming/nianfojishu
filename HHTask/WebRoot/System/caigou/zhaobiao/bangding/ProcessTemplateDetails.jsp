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
	<SCRIPT type="text/javascript">
	

	
	
	</SCRIPT>
	<body>
		<center>
			<input type="hidden" id="sparePartsId" name="sparePartsId" value='0'>
			<form action="markIdAction!updateProcessT.action" method="post"
				onsubmit="return checkForm();">
				<input type="hidden" name="pIdZijian.id" value="${pIdZijian.id}">
				<input type="hidden" name="pIdZijian.parallelId"
					value="${pIdZijian.parallelId}">
				<h2>
					工序信息
				</h2>
				<div align="center" style="color: red;">
					${successMessage}
				</div>
				<table class="table" id="tb">
					<tr>
						<th align="right">
							工序号:
						</th>
						<td>
							<input type="text" name="pIdZijian.processNO"
								id="pIdZijian.processNO" value="${pIdZijian.processNO}" />
						</td>
						<th align="right">
							工序名称:
						</th>
						<td>
							<input type="text" name="pIdZijian.processName"
								id="pIdZijian.processName" value="${pIdZijian.processName}" />
						</td>
						<th align="right">
							&nbsp;
						</th>
						<td>
							<input id="addTrBtn" type="button" value="添加工位">
						</td>
					</tr>
					<tr>
						<th align="right">
							并行:
						</th>
						<td>
							<select id="processStatus" name="pIdZijian.processStatus">
								<option value="${pIdZijian.processStatus}">
									<s:if test="pIdZijian.processStatus=='yes'">
										并行
									</s:if>
									<s:else>不并行</s:else>
								</option>
								<option value="no">
									不并行
								</option>
								<option value="yes">
									并行
								</option>
							</select>
						</td>
						<th align="right">
							生产类型:
						</th>
						<td>
							<select name="pIdZijian.productStyle">
								<option value="${pIdZijian.productStyle}">
									${pIdZijian.productStyle}
								</option>
								<option>
									自制
								</option>
								<option>
									外委
								</option>
							</select>
						</td>
						<th align="right">
							是否参与:
						</th>
						<td>
							<select name="pIdZijian.isPrice">
								<option value="${pIdZijian.isPrice}">
									<s:if test="pIdZijian.isPrice=='yes'">
										参与
									</s:if>
									<s:else>不参与</s:else>
								</option>
								<option value="yes">
									参与
								</option>
								<option value="no">
									不参与
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right">
							是否首检:
						</th>
						<td>
							<select id="processStatus" name="pIdZijian.zjStatus">
								<option value="${pIdZijian.zjStatus}">
									${pIdZijian.zjStatus}
								</option>
								<option value="no">
									no
								</option>
								<option value="yes">
									yes
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right">
							部门
						</th>
						<td align="left">
							<select name="pIdZijian.operatorDept" id="dept"
								style="width: 100px; float: left;">
								<option value="${pIdZijian.operatorDept}">
									${pIdZijian.operatorDept}
								</option>
							</select>
						</td>
						<th align="right">
							操作者:
						</th>
						<td align="left">
							<input id="userId" name="pIdZijian.operatorUserId" type="hidden" />
							<input id="userName" name="pIdZijian.operatorName"
								value="${pIdZijian.operatorName}" type="hidden" />
							<select id="users" style="width: 100px; float: left;">
								<option value="${pIdZijian.operatorName}">
									${pIdZijian.operatorName}
								</option>
							</select>
						</td>
						<th align="right">
							操作人工号:
						</th>
						<td>
							<input type="text" id="code" name="pIdZijian.operatorCode"
								value="${pIdZijian.operatorCode}" />
							<input id="cardId" name="pIdZijian.operatorCardId"
								value="${pIdZijian.operatorCardId}" type="hidden" />
						</td>
					</tr>
					<tr>
						<th align="right">
							工位号:
						</th>
						<td>
							<select name="pIdZijian.gongwei" id="gongwei"
								style="width: 155px;" onchange="getshebeiCode(this)">
								<option value="${pIdZijian.gongwei}">
									${pIdZijian.gongwei}
								</option>
								<option></option>
							</select>
						</td>
						<th align="right">
							设备编号:
						</th>
						<td>
							<select id="shebeiCode" name="pIdZijian.shebeiNo"
								style="width: 155px;" onchange="getGongweiAndOth()">
								<option value="${pIdZijian.shebeiNo}">
									${pIdZijian.shebeiNo}
								</option>
								<option></option>
							</select>
						</td>
						<th align="right">
							设备名称:
						</th>
						<td>
							<input type="text" name="pIdZijian.shebeiName" id="shebeiName"
								readOnly="readonly" style="background-color: #cccccc;"
								value="${pIdZijian.shebeiName}" />
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<b>操作过程</b>
						</td>
					</tr>
					<tr>
						<th align="right">
							人工节拍:
						</th>
						<td>
							<input type="text" name="pIdZijian.opcaozuojiepai"
								id="pIdZijian.opcaozuojiepai"
								value="${pIdZijian.opcaozuojiepai}" />
							(s)
						</td>
						<th align="right">
							设备节拍:
						</th>
						<td>
							<input id="pIdZijian.opshebeijiepai" type="text"
								name="pIdZijian.opshebeijiepai"
								value="${pIdZijian.opshebeijiepai}" />
							(s)
						</td>
						<th align="right">
							&nbsp;
						</th>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<th align="right">
							负荷指数:
						</th>
						<td>
							<input type="text" name="pIdZijian.opfuheRate"
								value="${pIdZijian.opfuheRate}" />
						</td>
						<th align="right">
							技能指数:
						</th>
						<td>
							<input type="text" name="pIdZijian.optechnologyRate"
								id="optechnologyRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${pIdZijian.optechnologyRate}" />
						</td>
						<th align="right">
							可替换人数:
						</th>
						<td>
							<input type="text" name="pIdZijian.opCouldReplaceRate"
								id="opCouldReplaceRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${pIdZijian.opCouldReplaceRate}" />
						</td>

					</tr>
					<tr>
						<td colspan="6" align="center">
							<b>准备过程</b>
						</td>
					</tr>
					<tr>
						<th align="right">
							人工节拍:
						</th>
						<td>
							<input type="text" name="pIdZijian.gzzhunbeijiepai"
								id="pIdZijian.gzzhunbeijiepai"
								value="${pIdZijian.gzzhunbeijiepai}" />
							(s)
						</td>
						<th align="right">
							准备次数:
						</th>
						<td>
							<input type="text" name="pIdZijian.gzzhunbeicishu"
								id="pIdZijian.gzzhunbeicishu"
								value="${pIdZijian.gzzhunbeicishu}" />
						</td>
						<th align="right">
							&nbsp;
						</th>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<th align="right">
							负荷指数:
						</th>
						<td>
							<input type="text" name="pIdZijian.gzfuheRate" id="gzfuheRate"
								readOnly="readonly" style="background-color: #cccccc;"
								value="${pIdZijian.gzfuheRate}" />
						</td>
						<th align="right">
							技能指数:
						</th>
						<td>
							<input type="text" name="pIdZijian.gztechnologyRate"
								id="gztechnologyRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${pIdZijian.gztechnologyRate}" />
						</td>
						<th align="right">
							可替换人数:
						</th>
						<td>
							<input type="text" name="pIdZijian.gzCouldReplaceRate"
								id="gzCouldReplaceRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${pIdZijian.gzCouldReplaceRate}" />
						</td>

					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="submit" class="input" value="修改" />
							&nbsp;
							<input type="reset" class="input" value="取消">

						</td>
					</tr>
				</table>
			</form>
		</center>
		<script type="text/javascript">
function checkForm() {

	//-------------------------------------------------------------------------------
	//pIdZijian.processName  工序名称
	var processName = document.getElementById('pIdZijian.processName').value;
	//pIdZijian.processNO  工序号
	var processNO = document.getElementById('pIdZijian.processNO').value;
	//    pIdZijian.opcaozuojiepai      人工节拍
	var opcaozuojiepai = document.getElementById('pIdZijian.opcaozuojiepai').value;
	//设备节拍  opshebeijiepai
	var opshebeijiepai = document.getElementById('pIdZijian.opshebeijiepai').value;
	// 人工节拍 pIdZijian.gzzhunbeijiepai
	var gzzhunbeijiepai = document.getElementById('pIdZijian.gzzhunbeijiepai').value;
	// 准备次数 pIdZijian.gzzhunbeicishu
	var gzzhunbeicishu = document.getElementById('pIdZijian.gzzhunbeicishu').value;
	if (processName == "") {
		alert("工序名称不能为空！！！");
		return false;
	}
	if (processNO == "") {
		alert("工序号不能为空！！！");
		return false;
	}
	if (opcaozuojiepai == "") {
		alert("操作人工节拍不能为空！！！");
		return false;
	}
	if (opshebeijiepai == "") {
		alert("操作设备节拍不能为空！！！");
		return false;
	}
	if (gzzhunbeijiepai == "") {
		alert("设备人工节拍不能为空！！！");
		return false;
	}
	if (gzzhunbeicishu == "") {
		alert("设备准备次数不能为空！！！");
		return false;
	}
	return true;
};

//------------------------------------------------------

//查询工位信息
$(function() {
	var count = 3;
	$('#addTrBtn').bind('click', function() {
		var b = $('#tb tr').eq(count).clone();
		$('#tb tr').eq(count).after(b);
		count++;
	});

	//查询所有工位
	createDept('gongwei', 'productPriceAction!findGongwei.action');

	//查询所有的部门
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			$("<option></option>").appendTo("#dept");
			$(allDdept).each(
					function() {
						$(
								"<option value='" + this.dept + "'>"
										+ this.dept + "</option>").appendTo(
								"#dept");
					});
		}

	});
});

//根据工位查询设备编号
function getshebeiCode(obj) {
	var gongwei = document.getElementById("gongwei").value;
	var select = document.getElementById("shebeiCode");
	select.options.length = 1;
	if (null != gongwei && "" != gongwei) {
		createDept("shebeiCode",
				"productPriceAction!findShebeiCode.action?tag=" + gongwei);
	}
}

//查询工位对象和交付量
function getGongweiAndOth() {
	//工位和设备编号
	var gongwei = document.getElementById("gongwei").value;
	var shebeicode = document.getElementById("shebeiCode").value;
	if (shebeicode != "") {
		$
				.ajax( {
					type : "POST",
					url : "productPriceAction!getGongwei.action",
					data : "gongweihao=" + gongwei + "&shebeiCode="
							+ shebeicode,
					success : function(msg) {
						var d = $.parseJSON(msg);
						//赋值
					document.getElementById("shebeiName").value = d.shebeiName;
					document.getElementById("optechnologyRate").value = d.caozJineng;
					document.getElementById("opCouldReplaceRate").value = d.caoztihuanrenshu;
					document.getElementById("gzfuheRate").value = d.gongzhuangFuhe;
					document.getElementById("gztechnologyRate").value = d.gongzhuangJineng;
					document.getElementById("gzCouldReplaceRate").value = d.gongzhuangtihuanrenshu;
				}
				});
	} else {
		document.getElementById("shebeiName").value = "";
		document.getElementById("optechnologyRate").value = "";
		document.getElementById("opCouldReplaceRate").value = "";
		document.getElementById("gzfuheRate").value = "";
		document.getElementById("gztechnologyRate").value = "";
		document.getElementById("gzCouldReplaceRate").value = "";

	}
}

//级联查询出部门所对应的所有人员
$("#dept")
		.bind(
				"change",
				function() {
					if ($("#dept").val() != "") {
						$
								.ajax( {
									url : "UsersAction!findUsersByDept.action",
									type : 'post',
									dataType : 'json',
									cache : false,//防止数据缓存
									contentType : "application/x-www-form-urlencoded; charset=utf-8",
									data : {
										deptName : $("#dept").val()
									},
									success : function(useradsfa) {
										$("#users").empty();//清空
										$("<option></option>").appendTo(
												"#users");
										$(useradsfa)
												.each(
														function() {
															$(
																	"<option value='"
																			+ this.code
																			+ "|"
																			+ this.name
																			+ "|"
																			+ this.id
																			+ "|"
																			+ this.cardId
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
															var user = $(
																	"#users")
																	.val();
															var userCodeName = user
																	.split("|");
															if (userCodeName != "") {
																$("#code")
																		.val(
																				userCodeName[0]);
																$("#userName")
																		.val(
																				userCodeName[1]);
																$("#userId")
																		.val(
																				userCodeName[2]);
																$("#cardId")
																		.val(
																				userCodeName[3]);
															} else {
																$("#code").val(
																		"");
																$("#userName")
																		.val("");
															}
														})
									},
									error : function() {
										alert("服务器异常!");
									}
								});
					}

				});
</script>
	</body>
</html>
