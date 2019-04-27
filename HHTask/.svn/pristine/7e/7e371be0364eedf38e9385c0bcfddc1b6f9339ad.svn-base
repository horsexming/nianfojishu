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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 90%; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcardDetail" src="" marginwidth="0"
						marginheight="0" hspace="0" vspace="0" frameborder="0"
						scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				总成件号:
				<a
					href="<%=basePath%>System/SOP/produce/Template_findProcard.jsp?id=${id}">${procardTemplate.markId}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				业务件号:${procardTemplate.ywMarkId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				名称：${procardTemplate.proName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				编制状态：${procardTemplate.bzStatus}
				<form action="procardTemplateGyAction_findSonsForBz.action"
					method="post">
					<input name="id" value="${id}" type="hidden">
					<table class="table">
						<tr>
							<th>
								件号:
							</th>
							<td>
								<input name="pageProcardTemplate.markId"
									value="${pageProcardTemplate.markId}" />
							</td>
							<th>
								名称:
							</th>
							<td>
								<input name="pageProcardTemplate.proName"
									value="${pageProcardTemplate.proName}" />
							</td>
							<th>
								编制状态:
							</th>
							<td>
								<select name="pageProcardTemplate.bzStatus"
									style="width: 155px;">
									<option>
										${pageProcardTemplate.bzStatus}
									</option>
									<option></option>
									<option>
										初始
									</option>
									<option>
										待编制
									</option>
									<option>
										已编制
									</option>
									<option>
										已校对
									</option>
									<option>
										已审核
									</option>
									<option>
										已批准
									</option>
								</select>
							</td>
							<td>
								<input type="submit" class="input" value="查询" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr>
						<td align="center">
							<font size="4" color="red">统一选择</font>
						</td>
						<td>
							编制人:
							<select name="procardTemplate.bianzhiId" class="bianzhi"
								id='bianzhiTem' onchange="changepeople('bz')">
								<option></option>
							</select>
						</td>
						<s:if test="bzjdCount!='2'.toString()">
							<td>
								校对人:
								<select name="procardTemplate.jiaoduiId" class="jiaodui"
									id='jiaoduiTem' onchange="changepeople('jd')">
									<option></option>
								</select>
							</td>
						</s:if>
						<s:if test="bzjdCount!='2'.toString()&&bzjdCount!='3'.toString()">
							<td>
								审核人:
								<select name="procardTemplate.shenheId" class="shenhe"
									id='shenheTem' onchange="changepeople('sh')">
									<option></option>
								</select>
							</td>
						</s:if>
						<td>
							批准人:
							<select name="procardTemplate.pizhunId" class="pizhun"
								id='pizhunTem' onchange="changepeople('pz')">
								<option></option>
							</select>
						</td>
						<td>
							<input id="agree" type="button" value="统一指派"
								style="width: 80px; height: 40px;" onclick="tizp()">
						</td>
					</tr>
				</table>
				<br />
				<form id="form"
					action="procardTemplateGyAction_batchApprovalBz.action"
					method="post">
					<input name="tag" type="hidden" id="tag">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								<input type="checkbox" id="checkAll" onchange="chageAllCheck()">
								全选
							</td>
							<td align="center">
								序号
							</td>
							<td align="center">
								件号
							</td>
							<td align="center" style="max-width: 300px;">
								名称
							</td>
							<td align="center">
								编制状态
							</td>
							<td align="center" style="max-width: 300px;">
								设变提示
							</td>
							<td align="center">
								编制人
							</td>
							<s:if test="bzjdCount!='2'.toString()">
								<td align="center">
									校对人
								</td>
							</s:if>
							<s:if test="bzjdCount!='2'.toString()&&bzjdCount!='3'.toString()">
								<td align="center">
									审核人
								</td>
							</s:if>
							<td align="center">
								批准人
							</td>
							<td align="center" colspan="2">
								操作
								<br />
								(Operation)
							</td>
						</tr>
						<s:iterator value="procardTemplateList" id="pgetpt"
							status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')"
									ondblclick="showptbb(${pageStatus.index})">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')"
									ondblclick="showptbb(${pageStatus.index})">
							</s:else>
							<td>
								<input type="checkbox" name="checkboxs" value="${pgetpt.id}"
									onchange="chageNum()">
								<input type="hidden"
									name="procardTemplateList[<s:property value="#pageStatus.index"/>].id"
									value="${pgetpt.id}">
							</td>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td align="center">
								<a href="procardTemplateGyAction_tosbjd.action?id=${pgetpt.id}">${pgetpt.markId}</a>
							</td>
							<td align="left" style="max-width: 300px;">
								${pgetpt.proName}
							</td>
							<td align="center">
								${pgetpt.bzStatus}
							</td>
							<td align="center" style="max-width: 300px;">
								${pgetpt.sbmsg}
							</td>
							<td align="center">
								<select
									name="procardTemplateList[<s:property value="#pageStatus.index"/>].bianzhiId"
									class="bianzhi"
									id='bianzhi<s:property value="#pageStatus.index"/>'>
									<s:if test="#pgetpt.bianzhiId!=null">
										<option value="${pgetpt.bianzhiId}">
											${pgetpt.bianzhiName}
										</option>
									</s:if>
									<option></option>
								</select>
							</td>
							<s:if test="bzjdCount!='2'.toString()">
								<td align="center">
									<select
										name="procardTemplateList[<s:property value="#pageStatus.index"/>].jiaoduiId"
										class="jiaodui"
										id='jiaodui<s:property value="#pageStatus.index"/>'>
										<s:if test="#pgetpt.jiaoduiId!=null">
											<option value="${pgetpt.jiaoduiId}">
												${pgetpt.jiaoduiName}
											</option>
										</s:if>
										<option></option>
									</select>
								</td>
							</s:if>
							<s:if test="bzjdCount!='2'.toString()&&bzjdCount!='3'.toString()">
								<td align="center">
									<select
										name="procardTemplateList[<s:property value="#pageStatus.index"/>].shenheId"
										class="shenhe"
										id='shenhe<s:property value="#pageStatus.index"/>'>
										<s:if test="#pgetpt.shenheId!=null">
											<option value="${pgetpt.shenheId}">
												${pgetpt.shenheName}
											</option>
										</s:if>
										<option></option>
									</select>
								</td>
							</s:if>
							<td align="center">
								<select
									name="procardTemplateList[<s:property value="#pageStatus.index"/>].pizhunId"
									class="pizhun"
									id='pizhun<s:property value="#pageStatus.index"/>'>
									<s:if test="#pgetpt.pizhunId!=null">
										<option value="${pgetpt.pizhunId}">
											${pgetpt.pizhunName}
										</option>
									</s:if>
									<option></option>
								</select>
							</td>

							<td colspan="2">
								<a onclick="shenyue(${pgetpt.id})">审阅</a>
								<s:if test="#pgetpt.bzStatus!='已批准'">
									<a id="zpa${pageStatus.index}"
										onclick="tozpbz(${pgetpt.id},${pageStatus.index})">指派</a>
									<a onclick="submitProcard(${pgetpt.id},'${pgetpt.bzStatus}')">同意</a>
								</s:if>
								<s:if
									test="#pgetpt.bzStatus=='已编制'||#pgetpt.bzStatus=='已校对'||#pgetpt.bzStatus=='已审核'">
									<a onclick="backProcard(${pgetpt.id},'${pgetpt.bzStatus}')">打回</a>
								</s:if>
							</td>
							</tr>
							<tr id="hidetr${pageStatus.index}"
								style="display: none; background-color: yellow">
								<td colspan="11">
									<input type="hidden" id="showtype${pageStatus.index}"
										value="hide">
									<table class="table">
										<tr>
											<th>
												件号
											</th>
											<th>
												名称
											</th>
											<th>
												单位
											</th>
											<th>
												零件类型
											</th>
											<th>
												生产类型
											</th>
											<th>
												原版本号
											</th>
											<th>
												升级版本号
											</th>
											<th>
												版次
											</th>
											<th>
												新版次
											</th>
											<th>
												升级类型
											</th>
											<th>
												是否修改工序
											</th>
											<th>
												是否修改图纸
											</th>
											<th>
												在制数量
											</th>
											<th>
												在途数量
											</th>
											<th>
												库存数量
											</th>
											<th>
												设变明细
											</th>
										</tr>
										<tr>
											<td align="left">
												${pgetpt.ptbb.markId}
											</td>
											<td align="left">
												${pgetpt.ptbb.proName}
											</td>
											<td align="left">
												${pgetpt.ptbb.unit}
											</td>
											<td align="left">
												${pgetpt.ptbb.procardStyle}
											</td>
											<td align="left">
												${pgetpt.ptbb.productStyle}
											</td>
											<td align="left">
												${pgetpt.ptbb.banBenNumber}
											</td>
											<td align="left">
												${pgetpt.ptbb.newBanBenNumber}
											</td>
											<td align="right">
												${pgetpt.ptbb.banci}
											</td>
											<td align="right">
												${pgetpt.ptbb.newbanci}
											</td>
											<td align="left">
												${pgetpt.ptbb.uptype}
											</td>
											<td align="left">
												${pgetpt.ptbb.changeProcess}
											</td>
											<td align="left">
												${pgetpt.ptbb.changeTz}
											</td>
											<td align="right">
												${pgetpt.ptbb.zaizhiCount}
											</td>
											<td align="right">
												${pgetpt.ptbb.zaituCount}
											</td>
											<td align="right">
												${pgetpt.ptbb.kucunCount}
											</td>
											<td align="left">
												${pgetpt.ptbb.remark}
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="11" align="center">
								<input id="agree" type="button" value="批量同意"
									style="width: 80px; height: 40px;" onclick="plsp('agree')">
								<input id="back" type="button" value="批量打回"
									style="width: 80px; height: 40px;" onclick="plsp('back')">
								<%--						<input id="agree" type="button" value="统一指派" style="width: 80px;height: 40px;" onclick="tizp()" >--%>
							</td>
						</tr>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>

						<s:if test="successMessage!=null">
							<tr>
								<td colspan="11" align="center" style="color: red">
									${successMessage}

								</td>
							</tr>
						</s:if>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		
function shenyue(id){
<%--	$("#showProcardDetail").attr("src","procardTemplateGyAction_shenyue.action?id="+id)--%>
	$("#showProcardDetail").attr("src","procardTemplateGyAction_shenyue.action?id="+id);
	chageDiv('block');
}
function submitProcard(id,bzStatus) {
	var spms="";
	if(bzStatus=="已审核"){
		spms=",您审批同意后即将加盖印章";
	}
	if (confirm("当前模板编制状态为:"+bzStatus+spms+",是否提交?")) {
		
		$.ajax( {
			type : "POST",
			url : "procardTemplateGyAction_submitProcard.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(data) {
				alert(data.message);
				if (data.success) {
					window.location.href="procardTemplateGyAction_findSonsForBz.action?id=${procardTemplate.id}";
				}
			}
		});
	}
}

function getGyPeople(type) {
	$.ajax( {
		type : "POST",
		url : "procardTemplateGyAction_getGyPeople.action?tag=" + type,
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						if (type == "bz") {
							$(".bianzhi").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "jd") {
							$(".jiaodui").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "sh") {
							$(".shenhe").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "pz") {
							$(".pizhun").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");

						}
					});
		}
	});
}

function backProcard(id,bzStatus) {
	if (confirm("当前模板编制状态为:"+bzStatus+",是否打回?")) {
		$
				.ajax( {
					type : "POST",
					url : "procardTemplateGyAction_backProcard.action",
					dataType : "json",
					data : {
						id : id
					},
					success : function(data) {
						alert(data.message);
						if (data.success) {
							window.location.href="procardTemplateGyAction_findProcardTem.action?cpage=${cpage}";
						}
					}
				});
	}
}
function tozpbz(id,index){
<%--	$("#showProcardDetail").attr("src",--%>
<%--			"procardTemplateGyAction_tozpbz.action?id="+id);--%>
<%--	chageDiv('block');--%>
	//$("#zpa"+index).attr("background-color","red");
	var bzjdCount="${bzjdCount}";
	var bianzhiId=$("#bianzhi"+index).val();
	var jiaoduiId=0;
	if(bzjdCount!="2"){
		jiaoduiId=$("#jiaodui"+index).val();
	}
	var shenheId=0;
	if(bzjdCount!="2"&&bzjdCount!="3"){
		shenheId=$("#shenhe"+index).val();
	}
	var pizhunId=$("#pizhun"+index).val();
	if (confirm("是否提交指派!")) {
		$
				.ajax( {
					type : "POST",
					url : "procardTemplateGyAction_zpbz.action",
					dataType : "json",
					data : {
						'procardTemplate.bianzhiId' : bianzhiId,
						'procardTemplate.jiaoduiId' : jiaoduiId,
						'procardTemplate.shenheId' : shenheId,
						'procardTemplate.pizhunId' : pizhunId,
						'procardTemplate.id':id,
						tag : "ajax"
					},
					success : function(data) {
						alert(data);
					}
				});
	}

}

function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var peopleLabel = document.getElementById("peopleLabel");
	var peopleLabel2 = document.getElementById("peopleLabel2");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}

function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var peopleLabel = document.getElementById("peopleLabel");
	var peopleLabel2 = document.getElementById("peopleLabel2");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
function plsp(tag){
	$("#agree").attr("disabled","disabled");
	$("#back").attr("disabled","disabled");
	$("#tag").val(tag);
	$(".bianzhi").attr("disabled","disabled");
	$(".jiaodui").attr("disabled","disabled");
	$(".shenhe").attr("disabled","disabled");
	$(".pizhun").attr("disabled","disabled");
	var form=$("#form");
	form.submit();
}

function changepeople(type){
	var before="";
	if(type=="bz"){
		before="bianzhi";
	}else if(type=="jd"){
		before="jiaodui";
	}else if(type=="sh"){
		before="shenhe";
	}else if(type=="pz"){
		before="pizhun";
	}
	var value=$("#"+before+"Tem").val();
	if(value==null||value.lenth==0){
		$("."+before).val("");
	}else{
		$("."+before).val(value);
	}
}

function tizp(){
	if (confirm("是否提交指派!")) {
		$.ajax( {
					type : "POST",
					url : "procardTemplateGyAction_batchZpBz.action",
					dataType : "json",
					data : $('#form').serialize(),
					success : function(data) {
						alert(data);
					}
				});
	}
	
}

$(document).ready(function() {
	var bzjdCount="${bzjdCount}";
	getGyPeople("bz")
	if(bzjdCount!="2"){
		getGyPeople("jd")
	}
	if(bzjdCount!="2"&&bzjdCount!="3"){
		getGyPeople("sh")
	}
	getGyPeople("pz")
})
function showptbb(ptIndex){
	if($("#showtype"+ptIndex).val()=="hide"){
		$("#hidetr"+ptIndex).show();
		$("#showtype"+ptIndex).val("show");
	}else{
		$("#hidetr"+ptIndex).hide();
		$("#showtype"+ptIndex).val("hide");
	}
}
		</SCRIPT>
	</body>
</html>
