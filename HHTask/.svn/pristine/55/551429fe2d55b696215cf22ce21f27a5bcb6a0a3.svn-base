<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<body onload="loadValue();f1();">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<s:if test="osTemplate.xjbzId!=null && osTemplate.xjbzId>0">
					<form action="InsRecord_OstbdXjbz.action" method="post">
						<div align="center">
							<h2>
								修改检验标准!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</h2>
							<table>
								<tr>
									<td>
										<SELECT name="osTemplate.xjbzId" class="cxselect">
											<option value="${osTemplate.xjbzId}">
												${osTemplate.xjbz}
											</option>
											<s:iterator value="xjbzlist" id="xjbz">
												<option value="${xjbz.id}">
													${xjbz.leixing}
												</option>
											</s:iterator>
										</SELECT>
									</td>
									<td>
										<input type="hidden" value="${osTemplate.id}"
											name="osTemplate.id" />
										<input type="hidden" value="${processInfor.id}" name="id" />
										<input type="hidden" value="${breakSubmit.id}"
											name="breakSubmit.id" />
										<input type="submit" value="修改" class="input" />
									</td>
								</tr>
							</table>

						</div>
					</form>
					<br />

					<form action="InsRecord_xJProcess.action"
						onsubmit="return checkForm()" method="post">
						<h2>
							${companyInfo.name}外购申检
						</h2>
						<input type="hidden" name="processInfor.id"
							value="${breakSubmit.processId}">
						<input type="hidden" name="breakSubmit.id"
							value="${breakSubmit.id}">
						<table class="table" id="table1">
							<tr align="center">
								<td colspan="4" style="font-size: 20px;">
									标识类别：外购申检${i}
								</td>
							</tr>
							<tr>
								<td align="right">
									零件号：
								</td>
								<td>
									${procard.markId}
									<input id="markId" type="hidden" readonly="readonly"
										value="${procard.markId}" />
								</td>
								<td align="right">
									物料类别:
								</td>
								<td>
									${procard.wgType}
									<input id="processNO" type="hidden" readonly="readonly"
										value="${procard.wgType}" />
								</td>
							</tr>
							<tr>
								<td align="right">
									零件名：
								</td>
								<td>
									${procard.proName}
									<input id="partsName" type="hidden" readonly="readonly"
										value="${procard.proName}" />
								</td>
								<td align="right">
									规格：
								</td>
								<td>
									${procard.specification}
									<input id="processName" type="hidden"
										value="${procard.specification}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td align="right">
									批次号：
								</td>
								<td>
									${procard.selfCard}
									<input id="lotId" type="hidden"
										value="${procard.selfCard}" readonly="readonly" />
								</td>
								<td align="right">
									所属工位：
								</td>
								<td>
									${breakSubmit.gongwei}
									<input id="gongwei" type="hidden" readonly="readonly"
										value="${breakSubmit.gongwei}" />
								</td>
							</tr>
							<tr>
								<td align="right">
									申请人:
								</td>
								<td>
									${breakSubmit.tjUsersName}
									<input id="operator" onMouseOut="getUserBy('na',this)"
										type="hidden" name="processInfor.usernames"
										value="${breakSubmit.tjUsersName}" readonly="readonly" />
								</td>
								<td align="right">
									所属自制件：
								</td>
								<td>
									${breakSubmit.markId}
									<input onMouseOut="getUserBy('co',this)" id="code"
										type="hidden" name="processInfor.usercodes"
										value="${breakSubmit.markId}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td align="right">
									检验员：
								</td>
								<td>
									${Users.name}
									<input type="hidden" value="${Users.name}" readonly="readonly" />
								</td>
								<td align="right">
									图纸：
								</td>
								<td>

									<s:if test="osTemplate.filename!=''&&osTemplate.filename!=null">
										<a target="_showTz"
<%--											href="<%=basePath%>/upload/file/OsTemplate/${osTemplate.filename}">--%>
											href="FileViewAction.action?FilePath=/upload/file/OsTemplate/${osTemplate.filename}">
											<img alt=""
												src="<%=basePath%>/upload/file/OsTemplate/${osTemplate.filename}"
												width="80px" height="60px"> </a>
									</s:if>
									<s:else>
										<a
											href="ProcardAction!findGongyiGuifan.action?markid=${processInfor.procard.markId}&processNO=${processInfor.processNO}">查看图纸</a>
									</s:else>
								</td>
							</tr>
							<tr>
								<td style="color: red;" align="left" colspan="4">
									<s:if
										test="breakSubmit!=null && breakSubmit.tjbreakcount!=null">
										申请检验数量:${xujianpingci.choujian} 需检验数量： ${xujianpingci.choujian}
									</s:if>
									<s:else>
										批次数量:${processInfor.applyCount} 需检验数量： ${xujianpingci.choujian}
									</s:else>
									<input type="hidden" name="jcCount"
										value="${xujianpingci.choujian}" id="ossSize" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="4">
									请输入不合格数量
									<input type="text" id="buhegeNum0"
										onblur="buhegeNum(this);numyanzheng(this,'zhengshu')"
										onkeyup="buhegeNum(this);numyanzheng(this,'zhengshu')" />

									<input type="hidden" value="sqjy" name="status" />
									<input type="hidden" value="wgsj" name="tag" />
									<input class="input" id="sub" type="submit" value="确认"
										onclick="todisabled(this)" />
								</td>
							</tr>
							<c:forEach var="i" begin="1" end="${xujianpingci.choujian}">
								<tr>
									<td colspan="4">
										<div id="fp" style="text-align: center;">
											<table class="table" id="mytable1">
												<tr>
													<th colspan="5">
														第${i}件 总需检验： ${xujianpingci.choujian}
													</th>
												</tr>
												<tr>
													<td align="center">
														序号
													</td>
													<td align="center">
														检查条目
													</td>
													<td align="center">
														质量特征
													</td>
													<td align="center">
														检查方法
													</td>
													<td align="center">
														检查结果
													</td>
												</tr>
												<s:if test="osList.size<=0">
													<tr>
														<th colspan="5">
															<font style="color: red; font-weight: bolder;">尚未录入首检检验模版,请联系质检部门,谢谢!</font>
														</th>
													</tr>
												</s:if>
												<s:iterator value="osList" id="pageOsScope"
													status="pageIndex">
													<tr>
														<th>
															${pageIndex.index+1}
														</th>
														<th align="left">
															${pageOsScope.content}
														</th>
														<th align="left">
															${pageOsScope.type}
														</th>
														<th>
															${pageOsScope.jcff}
														</th>
														<td align="center">
															<input type="hidden" value="${pageOsScope.id}"
																name="osRecordList[${i-1}].scopes[${pageIndex.index}].scopeId">
															<s:if test="#pageOsScope.type=='OKorNo'">
																<input id="cjjl${pageIndex.index}"
																	name="osRecordList[${i-1}].scopes[${pageIndex.index}].content"
																	type="radio" value="ok" checked="checked">OK
															<input id="cjjl${pageIndexs.index}"
																	name="osRecordList[${i-1}].scopes[${pageIndex.index}].content"
																	type="radio" value="no">NO
															</s:if>
															<s:else>
																<input id="cjjl${pageIndexs.index}"
																	name="osRecordList[${i-1}].scopes[${pageIndex.index}].content"
																	value="">
															</s:else>
														</td>
													</tr>
												</s:iterator>
												<tr>
													<td colspan="5" align="center" id="buhegetd${i-1}">
														<input type="hidden"
															name="osRecordList[${i-1}].templateId"
															value="${osTemplate.id}" />
														结果：
														<input type="radio"
															name="osRecordList[${i-1}].verification" value="合格"
															onclick="aa('${i-1}');bb(this)" checked="checked" class="hgradio" />

														合格&nbsp;&nbsp;
														<input type="radio"
															name="osRecordList[${i-1}].verification" value="不合格"
															onclick="getbhgType(this,'${i-1}');bb(this)" class="bhgradio" />
														不合格
														<span id="buhegespan${i-1}"></span>

														<select id="buhegeType_cp_${i-1}" style="display: none;"
															name="osRecordList[${i-1}].code">
															<option value="SJ">
																塑胶件(SJ)
															</option>
															<option value="WJ">
																五金件(WJ)
															</option>
															<option value="BJ">
																钣金件(BJ)
															</option>
															<option value="YC">
																原材料(YC)
															</option>
															<option value="JG">
																紧固件(JG)
															</option>
															<option value="BC">
																包材类(BC)
															</option>
															<option value="DD">
																电镀类(DD)
															</option>
															<option value="DZ">
																电子类(DZ)
															</option>
															<option value="PT">
																喷涂件(PT)
															</option>
															<option value="FL">
																辅料(FL)
															</option>
															<option value="ZJ">
																整机(ZJ)
															</option>
														</select>
														<select id="buhegeType${i-1}" style="display: none;"
															name="osRecordList[${i-1}].buhegeId">
														</select>
														<select id="buhegeType_class_${i-1}"
															style="display: none;" name="osRecordList[${i-1}].code">
															<option value="CR">
																致命缺陷(CR)
															</option>
															<option value="MA">
																主要缺陷(MA)
															</option>
															<option value="MI">
																次要缺陷(MI)
															</option>
														</select>
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="4" style="text-align: center">
									<s:if test="osList.size>0">
										<input class="input" type="submit" value="确认">
									</s:if>
									<s:else>
										<input class="input" type="button" disabled="disabled"
											value="确认">
									</s:else>
									<span style="width: 20px;"></span>
									<input class="input" type="button" onclick="goBack()"
										value="返回">
								</td>
							</tr>
						</table>
					</form>
					<br />
					<br />
				</s:if>
				<s:else>
					<h2>
						未绑定巡检标准，请先选择巡检标准!
					</h2>
					<form action="InsRecord_OstbdXjbz.action" method="post">
						<SELECT name="osTemplate.xjbzId" class="cxselect">
							<s:iterator value="xjbzlist" id="xjbz">
								<option value="${xjbz.id}">
									${xjbz.leixing}
								</option>
							</s:iterator>
						</SELECT>
						<input type="hidden" value="${osTemplate.id}" name="osTemplate.id" />
						<input type="hidden" value="${processInfor.id}" name="id" />
						<input type="hidden" value="${breakSubmit.id}"
							name="breakSubmit.id" />
						<input type="submit" value="绑定" class="input" />
					</form>
				</s:else>
			</div>
			<br>
		</div>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function f1() {
	var leixing = document.getElementById("leixing").value;
	if ("人员" == leixing) {
		document.getElementById("t1").style.display = "block";
	} else {
		document.getElementById("t1").style.display = "none";
	}

}
function getyuanyin(deptValue) {
	if (deptValue == "人员") {
		document.getElementById("t1").style.display = "block";
	} else {
		document.getElementById("t1").style.display = "none";

	}
	$.ajax( {
		type : "POST",
		url : "caoZuoAction!listBaofeiname.action",
		data : {
			pageStatus : deptValue
		},
		dataType : "json",
		success : function(object) {
			$("#yuefen").empty();
			$("<option></option>").appendTo("#yuefen");
			$(object).each(
					function() {
						$("<option value='" + this + "'>" + this + "</option>")
								.appendTo("#yuefen");
					});
		}
	});
}
//--------------------------------------------------
//提交验证
function checkForm() {
	var code = document.getElementById("code");
	var operator = document.getElementById("operator");
	if (code.value == "") {
		alert("操作人工号不能为空!");
		code.focus();
		return false;
	} else if (operator.value == "") {
		alert("操作人姓名不能为空!");
		operator.focus();
		return false;
	}
	var listSize = "<s:property value='list.size()'/>";
	for ( var i = 0; i < listSize.length; i++) {
		var context = $("#cjjl" + i).va();
		if (context == null || context == "") {
			alert("巡检记录尚未填写完毕!");
			return false;
		}
	}
}
function getUserBy(obj, o) {
	if (obj == "co") {//根据工号查询
		var code = o.value;
		$.ajax( {
			type : "POST",
			url : "LogoStickerAction!findUserInfor.action",
			data : {
				tag : obj,
				content : code
			},
			dataType : "json",
			success : function(msg) {
				if (msg.content == "") {
					alert(msg.message);
				} else {
					document.getElementById("operator").value = msg.content;
				}

			}
		});
	} else if (obj == "na") {
		var name = o.value;
		$.ajax( {
			type : "POST",
			url : "LogoStickerAction!findUserInfor.action",
			data : {
				tag : obj,
				content : name
			},
			dataType : "json",
			success : function(msg) {
				if (msg.content == "") {
					alert(msg.message);
				} else {
					document.getElementById("code").value = msg.content;
				}

			}
		});
	}
}
function getbhgType(obj, num) {
	if (obj != null && obj.checked == true) {
		$.ajax( {
			type : "POST",
			url : "BuHeGePinAction_findAllbuhegepinlist.action",
			data : {},
			dataType : "json",
			success : function(data) {
				if (data == "error") {
					alert("出错了哦!")
				} else if (data != null) {
					$("#buhegeType" + num).empty();
					$("#buhegespan" + num)
							.html("&nbsp;&nbsp;&nbsp;&nbsp;缺陷类型:");
					$(data).each(
							function() {
								$("#buhegeType" + num).append(
										'<option value=' + this.id + '>'
												+ this.code + this.type
												+ '</option>');
							})
					$("#buhegeType_cp_" + num).show();
					$("#buhegeType" + num).show();
					$("#buhegeType_class_" + num).show();
				}
			}
		})

	}
}
function aa(num) {
	$("#buhegeType" + num).hide();
	$("#buhegeType_cp_" + num).hide();
	$("#buhegeType_class_" + num).hide();
	$("#buhegespan" + num).html("");

}
function bb(obj){
	if(obj!=null){
		var value = obj.value;
		var buhegeNum0 = $("#buhegeNum0").val();
	if (buhegeNum0 != "") {
		buhegeNum0 = parseInt(buhegeNum0);
		if(value == "合格"){
			if (buhegeNum0 > 0) {
			buhegeNum0--
			}
		}else if(value == "不合格"){
			if(buhegeNum0<${xujianpingci.choujian}){
				buhegeNum0++;
			}
		}
		$("#buhegeNum0").val(buhegeNum0);
	}
	}
}
function buhegeNum(obj) {
	if (obj != null) {
		var bhgnum = parseInt(obj.value);
		var num = $("#ossSize").val();
		var bhgradios = $(".bhgradio");
		var hgradios = $(".hgradio");
		if (obj.value != "") {
			if (num < bhgnum) {
				alert("不合格数量大于检验数量请重新输入");
				$(obj).val('');
			} else {
				for ( var i = 0; i < bhgnum; i++) {
					bhgradios[i].checked = "checked";
					getbhgType(bhgradios[i], i);
				}
				for ( var i = 0; i < num - bhgnum; i++) {
					hgradios[bhgnum + i].checked = "checked";
					aa(i);
				}
			}
		} else if (obj.value == "" || obj.value == 0) {
			for ( var i = 0; i < num; i++) {
				hgradios[i].checked = "checked";
				aa(i)
			}
		}
	}
}
</script>
	</body>
</html>
