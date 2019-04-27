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
			href="<%=basePath%>/css/button.css" />
	</head>
	<%--	onload="f1();"--%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<s:if test="sticker.sbNumber!=null">
					<div
						style="background-color: #00FF00; color: #FF4500; font-weight: bolder; padding-left: 100px; font-size: 15px; font-family: 黑体;">
						批次设变提醒:[设变单号:
						<a
							href="procardTemplateGyAction_showSbApplyJd2.action?bbAply.id=${sticker.sbId}">${sticker.sbNumber}</a>]
					</div>
					<div align="left">
						<h3>
							设变提示:
							<font color="red">${sticker.sbmsg}</font>
						</h3>
					</div>
				</s:if>
				<s:if test="pamList!=null && pamList.size()>0">
					<div id="mea" style="display: none;">
						<div id="mea_pc" style="display: none;">
							<h2>
								选择量、检具
							</h2>
							<form method="POst" id="pamForm">
								<table class="table">
									<s:iterator value="pamList" id="pagepam" status="pageIndex">
										<s:if test="#pageIndex.index%3==0">
											<tr>
										</s:if>
										<td>
											<input type="checkbox" name="ids" value="${pagepam.id}">
											${pagepam.measuring_no}、(${pagepam.matetag})
										</td>
										<s:if test="(#pageIndex.index+1)%3==0">
											</tr>
										</s:if>
									</s:iterator>
								</table>
								<input type="button" value="选择" onclick="xuanzeMea(this);" />
							</form>
						</div>
						<div id="mea_phone" style="display: none;">
							<input type="button" value="选择量、检具" class="button0 black"
								onclick="autocheckList()" />
						</div>
					</div>
				</s:if>
				<s:else>
					<font color="red">
						${sticker.processName}工序，未绑定相关的量、检具信息。请前往工艺工序管理绑定。 </font>
				</s:else>
				<form action="LogoStickerAction!updateLogoSticker2.action"
					onsubmit="return checkForm()" method="post">
					<h2>
						${companyInfo.name}首件样品检验
					</h2>
					<input type="hidden" name="sticker.id" value="${sticker.id}">
					<table class="table">
						<tr align="center">
							<td colspan="4" style="font-size: 20px;">
								标识类别：首检样品
							</td>
						</tr>
						<tr>
							<td align="right">
								零件号：
							</td>
							<td>
								${sticker.markId}
								<input id="markId" type="hidden" readonly="readonly"
									value="${sticker.markId}" />
							</td>
							<td align="right">
								工序号：
							</td>
							<td>
								${sticker.processNO}
								<input id="processNO" type="hidden" readonly="readonly"
									value="${sticker.processNO}" />
							</td>
						</tr>
						<tr>
							<td align="right">
								零件名：
							</td>
							<td>
								${sticker.processNO}
								<input id="partsName" type="hidden" readonly="readonly"
									value="${sticker.partsName}" />
							</td>
							<td align="right">
								工序名：
							</td>
							<td>
								${sticker.processName}
								<input id="processName" type="hidden"
									value="${sticker.processName}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="right">
								批次号：
							</td>
							<td>
								${sticker.lotId}
								<input id="lotId" type="hidden" value="${sticker.lotId}"
									readonly="readonly" />
							</td>
							<td align="right">
								工位：
							</td>
							<td>
								${sticker.gongwei}
								<input id="gongwei" type="hidden" readonly="readonly"
									value="${sticker.gongwei}" />
							</td>
						</tr>
						<tr>
							<td align="right">
								操作者：
							</td>
							<td>
								${sticker.operator}
								<input id="operator" onMouseOut="getUserBy('na',this)"
									type="hidden" name="sticker.operator"
									value="${sticker.operator}" readonly="readonly" />
							</td>
							<td align="right">
								员工号：
							</td>
							<td>
								${sticker.code}
								<input onMouseOut="getUserBy('co',this)" id="code" type="hidden"
									name="sticker.code" value="${sticker.code}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="right">
								检验员：
							</td>
							<td>
								${Users.name}
								<input type="hidden" name="sticker.examinerName"
									value="${Users.name}" readonly="readonly" />
							</td>
							<td align="right">
								图纸：
							</td>
							<td>
								<s:if test="osTemplate.filename!=''&&osTemplate.filename!=null">
									<a target="_showTz"
										<%--										href="<%=basePath%>/upload/file/OsTemplate/${osTemplate.filename}">--%>
										href="FileViewAction.action?FilePath=/upload/file/OsTemplate/${osTemplate.filename}">
										<img alt=""
											src="<%=basePath%>/upload/file/OsTemplate/${osTemplate.filename}"
											width="80px" height="60px"> </a>
								</s:if>
								<s:else>
									<a
										href="ProcardAction!findGongyiGuifan.action?markid=${osTemplate.partNumber}&processNO=${osTemplate.gongxuNum}">查看图纸</a>
								</s:else>
							</td>
						</tr>

						<tr>
							<td colspan="4">
								<div id="fp" style="text-align: center;">
									<table class="table" id="mytable">
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
												<input type="hidden"
													value="<s:property value="osTemplate.scopes.size() " />"
													id="ossindex" />
											</td>
											<s:if test='osTemplate.ispublic== "是"'>
												<td>
													<input type="hidden" value="ty" name="tag" />
												</td>
											</s:if>
										</tr>
										<s:if test="list.size<=0">
											<tr>
												<th colspan="5">
													<font style="color: red; font-weight: bolder;">尚未录入首检检验模版,请联系质检部门,谢谢!</font>
												</th>
											</tr>
										</s:if>
										<s:iterator value="list" id="pageOsScope" status="pageIndex">
											<tr id="tr_${pageIndex.index}">
												<th id="td_${pageIndex.index}" align="left">
													${pageIndex.index+1}
												</th>
												<th align="left">
													${pageOsScope.content}
												</th>
												<th align="left">
													${pageOsScope.zltz}
												</th>
												<th align="left">
													${pageOsScope.jcff}
												</th>
												<td align="center">
													<input type="hidden" value="${pageOsScope.id}"
														name="osRecordScopeList[${pageIndex.index}].scopeId">
													<s:if test="#pageOsScope.type=='OKorNo'">
														<input id="cjjl${pageIndex.index}"
															name="osRecordScopeList[${pageIndex.index}].content"
															type="radio" value="ok" checked="checked">OK
														<input id="cjjl${pageIndex.index}"
															name="osRecordScopeList[${pageIndex.index}].content"
															type="radio" value="no">NO   
													</s:if>
													<s:else>
														<input id="cjjl${pageIndex.index}"
															name="osRecordScopeList[${pageIndex.index}].content">
													</s:else>
												</td>
												<%--												<s:if test='osTemplate.ispublic== "是"'>--%>
												<th>
													<input type="button" value="添加" onclick="addLine()" />

													<input type="button" value="删除"
														onclick="delLine(${pageIndex.index})" />

												</th>
												<%--												</s:if>--%>
											</tr>
										</s:iterator>
										<tr id="lastTr">
											<td colspan="6" align="center">
												结果：
												<input type="radio" name="sticker.isHege" value="合格"
													onclick="aa()" checked="checked" />
												合格&nbsp;&nbsp;
												<input type="radio" name="sticker.isHege" value="不合格"
													onclick="getbhgType(this)" />
												不合格
												<span id="buhegespan"></span>

												<select id="buhegeType_cp" style="display: none;"
													name="sticker.bhgcode">
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
												<select id="buhegeType" style="display: none;"
													name="sticker.buhegeId">
												</select>
												<select id="buhegeType_class" style="display: none;"
													name="sticker.bhgcode">
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
						<tr>
							<td colspan="4" style="text-align: center">
								<s:if test="list.size>0">
									<input class="input" type="submit" value="确认">
								</s:if>
								<s:else>
									<input class="input" type="submit" disabled="disabled"
										value="确认">
								</s:else>
								<span style="width: 20px;"></span>
								<input class="input" type="button" onclick="goBack()" value="返回">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var isjy = false;
$(function() {
	var measuring_no = '${sticker.measuring_no}';
	if (measuring_no != 'null' && measuring_no != null && measuring_no != '') {
		isjy = true;
	}
	if (!isjy) {
		$("#mea").show();
		if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
			$("#mea_pc").show();
			$("#mea_phone").show();
		} else {
			$("#mea_pc").show();
		}
	}
})
/*function f1() {
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
 }*/
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
	} else if (!isjy) {
		alert("请先选择量、检具!");
		return false;
	}
	var listSize = "<s:property value='list.size()'/>";
	for ( var i = 0; i < listSize.length; i++) {
		var context = $("#cjjl" + i).va();
		if (context == null || context == "") {
			alert("首件记录尚未填写完毕!");
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

var ossindex = $("#ossindex").val();

function addLine() {
	ossindex = parseInt(ossindex);
	var newLine = '<tr align="center" id="tr_'
			+ ossindex
			+ '"><th id="td_'
			+ ossindex
			+ '">'
			+ (ossindex + 1)
			+ '</th><th><input id="content1_'
			+ ossindex
			+ '"  name="osRecordScopeList['
			+ ossindex
			+ '].scope.content" class = "content1_'
			+ ossindex
			+ '"  /></th>'
			+ '<th><input id="zltz_'
			+ ossindex
			+ '" name="osRecordScopeList['
			+ ossindex
			+ '].scope.zltz" class = "zltz_'
			+ ossindex
			+ '"   /></th>'
			+ '<th><input id="jcff_'
			+ ossindex
			+ '" name="osRecordScopeList['
			+ ossindex
			+ '].scope.jcff"  class = "jcff_'
			+ ossindex
			+ '"   />'
			+ '<th><input id="ontent2_'
			+ ossindex
			+ '" name="osRecordScopeList['
			+ ossindex
			+ '].content"   class = "content2_'
			+ ossindex
			+ '" )" /></th>'
			+ '</th><td><input type="button" value="添加" onclick="addLine()" class="adddebutton"/>'
			+ '<input type="button" value="删除" onclick="delLine(' + ossindex
			+ ')" class="delbutton"/> </td></tr>';
	$($('#mytable tr')[$('#mytable tr').length - 2]).insertBefore(newLine);
	$("#lastTr").before(newLine);
	ossindex++;
}
function delLine(num) {
	if (ossindex == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
	$("#tr_" + num).remove();
	var table = document.getElementById("mytable");
	for ( var j = 1; j <= (ossindex - 1); j++) {
		table.rows[j].cells[0].innerHTML = j;
		var inputArrays4 = table.rows[j].cells[4].getElementsByTagName("input");
		if (inputArrays4 != null && inputArrays4.length > 1) {
			inputArrays4[0].name = "osRecordScopeList[" + (j - 1) + "].scopeId";
			inputArrays4[1].name = "osRecordScopeList[" + (j - 1) + "].content";
			if (inputArrays4[2] != null) {
				inputArrays4[2].name = "osRecordScopeList[" + (j - 1)
						+ "].content";
			}
		} else {
			inputArrays4[0].name = "osRecordScopeList[" + (j - 1) + "].content";
		}
		var inputArrays3 = table.rows[j].cells[3].getElementsByTagName("input");
		if (inputArrays3 != null && inputArrays3.length > 0) {
			inputArrays3[0].name = "osRecordScopeList[" + (j - 1)
					+ "].scope.jcff";
		}
		var inputArrays2 = table.rows[j].cells[2].getElementsByTagName("input");
		if (inputArrays2 != null && inputArrays2.length > 0) {
			inputArrays2[0].name = "osRecordScopeList[" + (j - 1)
					+ "].scope.zltz";
		}
		var inputArrays1 = table.rows[j].cells[1].getElementsByTagName("input");
		if (inputArrays1 != null && inputArrays1.length > 0) {
			inputArrays1[0].name = "osRecordScopeList[" + (j - 1)
					+ "].scope.content";
		}
	}
	ossindex--;

}

function getbhgType(obj) {
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
					$("#buhegeType").empty();
					$("#buhegespan").html("&nbsp;&nbsp;&nbsp;&nbsp;缺陷类型:");
					$(data).each(
							function() {
								$("#buhegeType").append(
										'<option value=' + this.id + '>'
												+ this.code + this.type
												+ '</option>');
							})
					$("#buhegeType_cp").show();
					$("#buhegeType").show();
					$("#buhegeType_class").show();
				}
			}
		})

	}
}

function aa() {
	$("#buhegeType").hide();
	$("#buhegeType_cp").hide();
	$("#buhegeType_class").hide();
	$("#buhegespan").html("");

}

function xuanzeMea(obj) {
	var ids = document.getElementsByName("ids");
	var bool = true;
	if (ids != null && ids.length > 0) {
		for ( var i = 0; i < ids.length; i++) {
			if (ids[i].checked) {
				bool = false;
				break;
			}
		}
	}
	if (bool) {
		$(obj).removeAttr("disabled");
		alert('请至少选择一个量、检具');
	} else {
		$(obj).attr('disabled', 'disabled');
		$.ajax( {
			type : "POST",
			url : "LogoStickerAction!xuanzeMea.action?id=${id}",
			dataType : "json",
			data : $("#pamForm").serialize(),
			success : function(data) {
				if (data == "true") {
					isjy = true;
					alert('选择成功!~')
				} else {
					alert(data);
				}
				$(obj).attr('disabled', 'disabled');
			}
		})
	}
}

function autocheckList() {
if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("未检测到摄像头!无法扫码!");
	}
}
function funFromjs(tm) {
	if (tm != null && tm != 'null' && tm != '') {
		var strs = tm.split("measuring.id=");
		if (strs != null && strs.length == 2) {
			var id1= strs[1];
			if (id1 != null && id1 > 0) {
				$.ajax( {
					type : "POST",
					url : "LogoStickerAction!xuanzeMea.action?",
					dataType : "json",
					data : {
						id:${id},
						id1:id1
					},
					success : function(data) {
						if(data == "true"){
							isjy = true;
							alert('选择成功!~')
						}else{
							if(data==null || data=='null'){
								data = '选择失败!~';
							}
							alert(data);
						}
						$(obj).attr('disabled', 'disabled');
					}
				})
			}
		}

	}
}
</script>
	</body>
</html>
