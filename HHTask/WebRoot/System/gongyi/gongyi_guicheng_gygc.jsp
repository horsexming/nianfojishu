<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
body {
	font-family: 宋体;
}

.table {
	border: 0px solid #999;
	width: 756px;
	border-collapse: collapse;
}

.table th,.table td {
	height: 24px;
	border-width: 0px;
}
a{
 cursor: pointer;
}
</style>
	</head>
	<body style="text-align: center;">
		<!-- 弹出层开始 -->
		<div id="bodyDiv" align="center" class="transDiv" style="left: 0px;"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 999; width: 1024px; display: none;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序信息</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;height: 400px;">
					<iframe id="uploadIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 100%; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<!-- 弹出层结束 -->
		<div align="center">
			<!-- A4页面开始 -->
			<div id="printDiv"
				style="width: 794px; height: 1123px; border: 1px solid #000000;">
				<!-- 页边距内开始 -->
				<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
				<div
					style="width: 756px; height: 1086px; border: 1px solid #000000; position: relative; top: 19px;">
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}" />
					<table class="table" cellpadding="0" cellspacing="0">
						<!-- 1 -->
						<tr align="center">
							<td align="right">
								工艺规程编号：
								<input type="text" id="numb" name="gongyiGuicheng.numb" />
							</td>
						</tr>
						<!-- 2 -->
						<%----%>
						<tr align="center">
							<td>${gongyiGuicheng.status}</td>
						</tr>
						<!-- 3 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 4 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 5 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 6 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 7 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 8 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 9 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 10 -->
						<tr align="center">
							<td style="font-size: 80px; word-spacing: 10px;">
								工&nbsp;艺&nbsp;规&nbsp;程
							</td>
						</tr>
						<!-- 11 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 12 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 13 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 14 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 15 -->
						<tr align="center">
							<td style="font-size: 20px;">
								型别：
								<input type="text" id="xingbie" name="gongyiGuicheng.xingbie" />
								类型<select id="gongyiGuichengType" name="gongyiGuicheng.gongyiGuichengType" >
										<option value="">请选择</option>
										<option value="批产">批产</option>
										<option value="试制">试制</option>
								<select>
							</td>
						</tr>
						<!-- 16 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 17 -->
						<tr align="center">
							<td style="font-size: 20px;">
								件号：
								<input type="text" id="jianNumb" name="gongyiGuicheng.jianNumb" />
								<%--<select type="text" id="jianId" name="gongyiGuicheng.jianId"></select>--%>
								&nbsp;&nbsp;件名：
								<input type="text" id="jianName" name="gongyiGuicheng.jianName" />
								&nbsp;&nbsp;产品类型<SELECT id="procardStyle" name="gongyiGuicheng.procardStyle">
								 <option value="">请选择类型
								 </option>
								 <option value="总成">总成
								 </option>
								 <option value="组合">组合
								 </option>
								 <option value="自制">自制
								 </option>
								 <option value="外购">外购
								 </option>
								</SELECT>
							</td>

						</tr>
						<!-- 18 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 19 -->
						<tr align="center">
							<td style="font-size: 20px;">
								共&nbsp;&nbsp;
								<input type="text" id="pageTotal"
									name="gongyiGuicheng.pageTotal" />
								&nbsp;&nbsp;页
							</td>
						</tr>
						<!-- 20 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 21 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 22 -->
						<tr align="center">
							<td style="font-size: 28px;">
								编制：
								<%--<input type="text" id="bianzhiName" name="gongyiGuicheng.bianzhiName"/>--%>
								<select id="bianzhiDept" style="color: #000000;visibility:hidden;">
								</select>
								<select id="bianzhiId" name="gongyiGuicheng.bianzhiId">
								</select>
								<input type="hidden" id="bianzhiName"
									name="gongyiGuicheng.bianzhiName" />
								<input class="Wdate" type="text" id="bianzhiDate"
									name="gongyiGuicheng.bianzhiDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<!-- 23 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 24 -->
						<tr align="center">
							<td style="font-size: 28px;">
								校对：
								<%--<input type="text" id="jiaoduiName" name="gongyiGuicheng.jiaoduiName"/>--%>
								<select id="jiaoduiDept" style="color: #000000;visibility:hidden;">
								</select>
								<select id="jiaoduiId" name="gongyiGuicheng.jiaoduiId">
								</select>
								<input type="hidden" id="jiaoduiName"
									name="gongyiGuicheng.jiaoduiName" />
								<input class="Wdate" type="text" id="jiaoduiDate"
									name="gongyiGuicheng.jiaoduiDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<!-- 25 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 26 -->
						<tr align="center">
							<td style="font-size: 28px;">
								审核：
								<%--<input type="text" id="shenheName" name="gongyiGuicheng.shenheName"/>--%>
								<select id="shenheDept" style="color: #000000;visibility:hidden;">
								</select>
								<select id="shenheId" name="gongyiGuicheng.shenheId">
								</select>
								<input type="hidden" id="shenheName"
									name="gongyiGuicheng.shenheName" />
								<input class="Wdate" type="text" id="shenheDate"
									name="gongyiGuicheng.shenheDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<!-- 27 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 28 -->
						<tr align="center">
							<td style="font-size: 28px;">
								批准：
								<%--<input type="text" id="pizhunName" name="gongyiGuicheng.pizhunName"/>--%>
								<select id="pizhunDept" style="color: #000000;visibility:hidden;">
								</select>
								<select id="pizhunId" name="gongyiGuicheng.pizhunId">
								</select>
								<input type="hidden" id="pizhunName"
									name="gongyiGuicheng.pizhunName" />
								<input class="Wdate" type="text" id="pizhunDate"
									name="gongyiGuicheng.pizhunDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<!-- 29 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 30 -->
						<tr align="center">
							<td style="font-size: 28px;">
								${companyInfo.name}
							</td>
						</tr>
						<!-- 31 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 32 -->
						<tr align="center">
							<td style="font-size: 18px;">
								<input id="fachuDate" class="Wdate" type="text"
									name="gongyiGuicheng.fachuDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								&nbsp;日发出
							</td>
						</tr>
						<!-- 33 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 34 -->
						<tr align="center">
							<td></td>
						</tr>
						<!-- 35 -->
						<tr align="center">
							<td align="right">
								版次
								<input type="text" id="banci" name="gongyiGuicheng.banci" />
								存图号
								<input type="text" id="cuntuNumb"
									name="gongyiGuicheng.cuntuNumb" />
							</td>
						</tr>
						<tr align="center">
							<td><a id="wenjianGuanli" data-type="open">文件管理</a></td>
						</tr>
					</table>
				</div>
				<!-- 页边距内结束 -->
			</div>
			<!-- A4页面结束 -->
		</div>
		<div style="width: 794px; text-align: center; margin: 0 auto;">
			
			<table style="width: 100%; text-align: center; border: 0px;">
				<tr>
					<td>
						<input type="button" id="saveButton" value="保存" class="input" />
						<input type="button" id="presentButton" value="提交" class="input" />
						<input type="button" id="backButton" value="打回" class="input" />
						<input type="button" id="markingButton" value="打分" class="input" />
						<%--<input type="button" id="printButton" value="打印" class="input"/>--%>
						<input type="button" id="historyButton" value="生成历史版本"
							class="input" style="width: 120px;" />
					</td>
				</tr>
			</table>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function() {
	
	//全局工艺规程状态
	var dengluId = '${sessionScope.Users.id}';
	var status = '';
	var bianzhiId = '';
	var jiaoduiId = '';
	var shenheId = '';
	var pizhunId = '';
	var gygcId=$('#gygcId').val();
	//$("#printButton").bind("click",function() {
	//	window.location="gongyiGuichengAction!getGongyiGuiChengGygcPage.action?gongyiGuicheng.id=${gongyiGuicheng.id}&print=print";
	//});
	//出事化件号
	$("#wenjianGuanli").bind("click",function(){
		chageDiv('block');
		$("#contentDiv").css("top", "150px");
		var _src='gongyiGuichengAction!getGongyiGuiChengUploadPage.action?'
				+ '&gongyiGuichengAffix.gongyiGuichengId='
				+ gygcId+'&gongyiGuichengAffix.weizhi=gygcsy';
		$("#uploadIframe").attr("src", _src);
	});
	
	/*
	$.ajax({
		type: "get",
		dataType: "json",
	    url: "gongyiGuichengAction!findJianNumbForSelect.action",
		async: false,
		success: function(data){
			if(data.success){
				var data=data.data;
				$('#jianId').empty();
				$("<option value='' data-jianNumb='' data-jianName=''>请选择</option>").appendTo("#jianId");
				$(data).each(function(){
					$("<option value='"+this.id+"' data-jianNumb='"+this.jianNumb+"' data-jianName='"+this.jianName+"'>"+this.jianNumb+this.jianName+"</option>").appendTo("#jianId");
				});
			}
		}
	});
	
	$("#jianId").bind("change",function() {
		var jianId=$(this).val();
		var selectedOption=$('#jianId').find("option[value='"+jianId+"']");
		var jianNumb=selectedOption.attr('data-jianNumb');
		var jianName=selectedOption.attr('data-jianName');
		$('#jianNumb').val(jianNumb);
		$('#jianName').val(jianName);
	});
	 */
	//出事化件号--------------------------
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getGongyiGuichengById.action",
		data : {
			'gongyiGuicheng.id' : $('#gygcId').val()
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var data = data.data;
				var numb = data.numb;
				var xingbie = data.xingbie;
				var jianId = data.jianId;
				var jianNumb = data.jianNumb;
				var procardStyle = data.procardStyle;
				var jianName = data.jianName;
				var pageTotal = data.pageTotal;
				var bianzhiName = data.bianzhiName;
				var jiaoduiName = data.jiaoduiName;
				var shenheName = data.shenheName;
				var pizhunName = data.pizhunName;
				var fachuDate = data.fachuDate;
				var gongyiGuichengType=data.gongyiGuichengType;
				if (fachuDate) {
					fachuDate = new Date(fachuDate).format('yyyy-MM-dd');
				}
				var banci = data.banci;
				var cuntuNumb = data.cuntuNumb;
				$("#numb").val(numb);
				$("#xingbie").val(xingbie);
				//$("#jianId").find("option[value='"+jianId+"']").attr("selected",true);
		$("#jianNumb").val(jianNumb);
		$("#jianName").val(jianName);
		$("#pageTotal").val(pageTotal);
		$("#bianzhiName").val(bianzhiName);
		$("#jiaoduiName").val(jiaoduiName);
		$("#shenheName").val(shenheName);
		$("#pizhunName").val(pizhunName);
		$("#fachuDate").val(fachuDate);
		$("#banci").val(banci);
		$("#cuntuNumb").val(cuntuNumb);
		$("#gongyiGuichengType").find("option[value='"+gongyiGuichengType+"']").attr("selected",true);
		$("#procardStyle").find("option[value='"+procardStyle+"']").attr("selected",true);
	}
}
	});
	$("#saveButton")
			.bind(
					"click",
					function() {
						var id = $("#gygcId").val();
						var numb = $("#numb").val();
						var xingbie = $("#xingbie").val();
						//var jianId=$("#jianId").val();
						var jianNumb = $("#jianNumb").val();
						var jianName = $("#jianName").val();
						var procardStyle = $("#procardStyle").val();
						var pageTotal = $("#pageTotal").val();

						var bianzhiId = $("#bianzhiId").val();
						var jiaoduiId = $("#jiaoduiId").val();
						var shenheId = $("#shenheId").val();
						var pizhunId = $("#pizhunId").val();
						var bianzhiName = $("#bianzhiName").val();
						var jiaoduiName = $("#jiaoduiName").val();
						var shenheName = $("#shenheName").val();
						var pizhunName = $("#pizhunName").val();

						var bianzhiDate = $("#bianzhiDate").val();
						var jiaoduiDate = $("#jiaoduiDate").val();
						var shenheDate = $("#shenheDate").val();
						var pizhunDate = $("#pizhunDate").val();

						var fachuDate = $("#fachuDate").val();
						var banci = $("#banci").val();
						var cuntuNumb = $("#cuntuNumb").val();
						var gongyiGuichengType=$("#gongyiGuichengType").val();
						
						$
								.ajax( {
									type : "post",
									dataType : "json",
									url : "gongyiGuichengAction!updateGongyiGuiChengGygcPage.action",
									data : {
										'gongyiGuicheng.id' : id,
										'gongyiGuicheng.numb' : numb,
										'gongyiGuicheng.xingbie' : xingbie,
										//'gongyiGuicheng.jianId': jianId,
										'gongyiGuicheng.jianNumb' : jianNumb,
										'gongyiGuicheng.procardStyle' : procardStyle,
										'gongyiGuicheng.jianName' : jianName,
										'gongyiGuicheng.pageTotal' : pageTotal,

										'gongyiGuicheng.bianzhiId' : bianzhiId == 'undefined' ? ''
												: bianzhiId,
										'gongyiGuicheng.jiaoduiId' : jiaoduiId == 'undefined' ? ''
												: jiaoduiId,
										'gongyiGuicheng.shenheId' : shenheId == 'undefined' ? ''
												: shenheId,
										'gongyiGuicheng.pizhunId' : pizhunId == 'undefined' ? ''
												: pizhunId,

										'gongyiGuicheng.bianzhiName' : bianzhiName,
										'gongyiGuicheng.jiaoduiName' : jiaoduiName,
										'gongyiGuicheng.shenheName' : shenheName,
										'gongyiGuicheng.pizhunName' : pizhunName,

										'gongyiGuicheng.bianzhiDate' : bianzhiDate,
										'gongyiGuicheng.jiaoduiDate' : jiaoduiDate,
										'gongyiGuicheng.shenheDate' : shenheDate,
										'gongyiGuicheng.pizhunDate' : pizhunDate,

										'gongyiGuicheng.fachuDate' : fachuDate,
										'gongyiGuicheng.banci' : banci,
										'gongyiGuicheng.cuntuNumb' : cuntuNumb,
										'gongyiGuicheng.gongyiGuichengType' : gongyiGuichengType
									},
									async : false,
									success : function(data) {
										var success = data.success;
										if (success) {
											alert("保存成功");
											window.location.reload();
										}
									}
								});
					});
	//************************************打分*****************************************************************
	//$("form table:visible").css('display','none');
	
	//***************************************打分***************************************************
	//初始化打分类别
	var gongyiGuichengScoreLeibieId=null;
	$.ajax( {
		url : "gongyiGuichengScoreAction!getGongyiGuichengScoreLeibieListAllForSelect.action",
		type : 'post',
		dataType : 'json',
		data : {},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var gongyiGuichengScoreLeibieList = data.data;
				$(gongyiGuichengScoreLeibieList).each(function(){
					if(this.biaoshi=='gygcsy'){
						gongyiGuichengScoreLeibieId=this.id;
					}
				});
			}
		}
	});
	$('#markingButton').bind('click', function() {
		chageDiv('block');
		$("#contentDiv").css("top", "150px");
		//var _src='gongyiGuichengScoreAction!getGongyiGuichengScorePage.action?gongyiGuichengScore.gongyiGuichengId='+ $("#gygcId").val();
		var _src='gongyiGuichengScoreAction!getGongyiGuiChengMarkingPage.action?gongyiGuichengScoreItem.gongyiGuichengId='+ $("#gygcId").val()+ '&gongyiGuichengScoreJilu.parentId='+gongyiGuichengScoreLeibieId;
		$("#uploadIframe").attr("src", _src);
	});
	//**************************************提交*********************************************
	$('#presentButton').bind('click', function() {
		switch (status) {
		case '打回':
		case '待编制':
			var jiaoduiId = $("#jiaoduiId").val();
			if ('undefined' == jiaoduiId || '' == jiaoduiId) {
				alert('请选择校对人');
				return;
			}
			break;
		case '已编制':
			var shenheId = $("#shenheId").val();
			if ('undefined' == shenheId || '' == shenheId) {
				alert('请选择审核人');
				return;
			}
			break;
		case '已校对':
			var pizhunId = $("#pizhunId").val();
			if ('undefined' == pizhunId || '' == pizhunId) {
				alert('请选择批准人');
				return;
			}
			break;
		case '已审核':

			break;
		case '已批准':

			break;
		default:

			break;
		}
		var isPresent=false;
		$.ajax( {
			type : "get",
			dataType : "json",
			url : "gongyiGuichengScoreAction!isPresent.action",
			data : {
				'gongyiGuichengScoreItem.gongyiGuichengId' : $('#gygcId').val()
			},
			async : false,
			success : function(data) {
				var success = data.success;
				if (success) {
					isPresent=true;
				}
			}
		});
		if(!isPresent){
			alert("请继续修改工艺规程!");
			return;
		}
		//alert('停止');
		//return ;
		$.ajax( {
			type : "get",
			dataType : "json",
			url : "gongyiGuichengAction!presentGongyiGuicheng.action",
			data : {
				'gongyiGuicheng.id' : $('#gygcId').val()
			},
			async : false,
			success : function(data) {
				var success = data.success;
				if (success) {
					alert("提交成功");
					window.location.reload();
				}

			}
		});
	});

	//**************************************打回*********************************************
	$('#backButton').bind('click', function() {
		$.ajax( {
			type : "get",
			dataType : "json",
			url : "gongyiGuichengAction!backGongyiGuicheng.action",
			data : {
				'gongyiGuicheng.id' : $('#gygcId').val()
			},
			async : false,
			success : function(data) {
				var success = data.success;
				if (success) {
					alert("打回成功");
					window.location.reload();
				}
			}
		});
	});
	//*****************************************生成历史版本**************************************
	/**/
	$('#historyButton').bind('click', function() {
		$.ajax( {
			type : "get",
			dataType : "json",
			url : "gongyiGuichengAction!addGongyiGuichengHistory.action",
			data : {
				'gongyiGuicheng.id' : $('#gygcId').val()
			},
			async : false,
			success : function(data) {
				var success = data.success;
				if (success) {
					alert("生成 历史版本成功");
					window.location.reload();
				}
			}
		});
	});
	//********************************部门人员管理***************************************************
	//编制
	
	$.ajax( {
		type : "get",
		dataType : "json",
		url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=gy",
		async : false,
		success : function(data) {
			$("<option value='' >请选择成员组</option>").appendTo("#bianzhiDept");
			$("<option value='' >请选择人员</option>").appendTo("#bianzhiId");
			$(data).each(function(i,n){
				$("<option value='" + this.id + "'>" + this.groupName + "</option>").appendTo("#bianzhiDept");
			});
			
			$('#bianzhiDept').find("option:contains('编制')").attr("selected",true);
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				async : false,
				cache : false,//防止数据缓存
				data : {
					id : $("#bianzhiDept").val()
				},
				success : function(useradsfa) {
					$("#bianzhiId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#bianzhiId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#bianzhiId");
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});
	
	//级联查询出部门所对应的所有人员
	
	$("#bianzhiDept").bind("change",function() {
		if ($("#bianzhiDept").val() != "") {
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					id : $("#bianzhiDept").val()
				},
				success : function(useradsfa) {
					$("#bianzhiId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#bianzhiId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#bianzhiId");
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});
	//校对
	$.ajax( {
		type : "get",
		dataType : "json",
		url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=gy",
		async : false,
		success : function(data) {
			$("<option value='' >请选择成员组</option>").appendTo("#jiaoduiDept");
			$("<option value='' >请选择人员</option>").appendTo("#jiaoduiId");
			$(data).each(function(i,n){
				$("<option value='" + this.id + "'>" + this.groupName + "</option>").appendTo("#jiaoduiDept");
			});
			
			$('#jiaoduiDept').find("option:contains('校对')").attr("selected",true);
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				async : false,
				cache : false,//防止数据缓存
				data : {
					id : $("#jiaoduiDept").val()
				},
				success : function(useradsfa) {
					$("#jiaoduiId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#jiaoduiId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#jiaoduiId");
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});

	//级联查询出部门所对应的所有人员
	
	$("#jiaoduiDept").bind("change",function() {
		if ($("#jiaoduiDept").val() != "") {
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					id : $("#jiaoduiDept").val()
				},
				success : function(useradsfa) {
					$("#jiaoduiId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#jiaoduiId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#jiaoduiId");
					});
					
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});
	//审核
	$.ajax( {
		type : "get",
		dataType : "json",
		url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=gy",
		async : false,
		success : function(data) {
			$("<option value='' >请选择成员组</option>").appendTo("#shenheDept");
			$("<option value='' >请选择人员</option>").appendTo("#shenheId");
			$(data).each(function(i,n){
				$("<option value='" + this.id + "'>" + this.groupName + "</option>").appendTo("#shenheDept");
			});
			
			$('#shenheDept').find("option:contains('审核')").attr("selected",true);
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				async : false,
				cache : false,//防止数据缓存
				data : {
					id : $("#shenheDept").val()
				},
				success : function(useradsfa) {
					$("#shenheId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#shenheId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#shenheId");
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});

	//级联查询出部门所对应的所有人员
	
	$("#shenheDept").bind("change",function() {
		if ($("#shenheDept").val() != "") {
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					id : $("#shenheDept").val()
				},
				success : function(useradsfa) {
					$("#shenheId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#shenheId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#shenheId");
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});
	//批准
	$.ajax( {
		type : "get",
		dataType : "json",
		url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=gy",
		async : false,
		success : function(data) {
			$("<option value='' >请选择成员组</option>").appendTo("#pizhunDept");
			$("<option value='' >请选择人员</option>").appendTo("#pizhunId");
			$(data).each(function(i,n){
				$("<option value='" + this.id + "'>" + this.groupName + "</option>").appendTo("#pizhunDept");
			});
			
			$('#pizhunDept').find("option:contains('批准')").attr("selected",true);
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				async : false,
				cache : false,//防止数据缓存
				data : {
					id : $("#pizhunDept").val()
				},
				success : function(useradsfa) {
					$("#pizhunId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#pizhunId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#pizhunId");
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});

	//级联查询出部门所对应的所有人员
	
	$("#pizhunDept").bind("change",function() {
		if ($("#pizhunDept").val() != "") {
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					id : $("#pizhunDept").val()
				},
				success : function(useradsfa) {
					$("#pizhunId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#pizhunId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#pizhunId")
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});
	$("#bianzhiId").bind(
			"change",
			function() {
				var bianzhiId = $(this).val();
				var selectedOption = $('#bianzhiId').find(
						"option[value='" + bianzhiId + "']");
				var bianzhiName = selectedOption.attr('data-name');
				$('#bianzhiName').val(bianzhiName);
			});
	$("#jiaoduiId").bind(
			"change",
			function() {
				var jiaoduiId = $(this).val();
				var selectedOption = $('#jiaoduiId').find(
						"option[value='" + jiaoduiId + "']");
				var jiaoduiName = selectedOption.attr('data-name');
				$('#jiaoduiName').val(jiaoduiName);
			});
	$("#shenheId").bind(
			"change",
			function() {
				var shenheId = $(this).val();
				var selectedOption = $('#shenheId').find(
						"option[value='" + shenheId + "']");
				var shenheNumb = selectedOption.attr('data-name');
				$('#shenheName').val(shenheNumb);
			});
	$("#pizhunId").bind(
			"change",
			function() {
				var pizhunId = $(this).val();
				var selectedOption = $('#pizhunId').find(
						"option[value='" + pizhunId + "']");
				var pizhunNumb = selectedOption.attr('data-name');
				$('#pizhunName').val(pizhunNumb);
			});
	//初始化工艺规程
	$
			.ajax( {
				type : "get",
				dataType : "json",
				url : "gongyiGuichengAction!getGongyiGuichengById.action",
				data : {
					'gongyiGuicheng.id' : $('#gygcId').val()
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						data = data.data;
						var bianzhiId = data.bianzhiId;
						var bianzhiName = data.bianzhiName == null ? ''
								: data.bianzhiName;
						var jiaoduiId = data.jiaoduiId;
						var jiaoduiName = data.jiaoduiName == null ? ''
								: data.jiaoduiName;
						var shenheId = data.shenheId;
						var shenheName = data.shenheName == null ? ''
								: data.shenheName;
						var pizhunId = data.pizhunId;
						var pizhunName = data.pizhunName == null ? ''
								: data.pizhunName;

						var bianzhiDate = data.bianzhiDate;
						if (!bianzhiDate) {
							bianzhiDate = new Date();
						}
						var jiaoduiDate = data.jiaoduiDate;
						if (!jiaoduiDate) {
							jiaoduiDate = new Date();
						}
						var shenheDate = data.shenheDate;
						if (!shenheDate) {
							shenheDate = new Date();
						}
						var pizhunDate = data.pizhunDate;
						if (!pizhunDate) {
							pizhunDate = new Date();
						}
						bianzhiDate = new Date(bianzhiDate)
								.format('yyyy-MM-dd');
						jiaoduiDate = new Date(jiaoduiDate)
								.format('yyyy-MM-dd');
						shenheDate = new Date(shenheDate).format('yyyy-MM-dd');
						pizhunDate = new Date(pizhunDate).format('yyyy-MM-dd');
						//$('#bianzhiId').find("option[value='"+bianzhiId+"']").attr("selected",true);
						//$('#jiaoduiId').find("option[value='"+jiaoduiId+"']").attr("selected",true);
						//$('#shenheId').find("option[value='"+shenheId+"']").attr("selected",true);
						//$('#pizhunId').find("option[value='"+pizhunId+"']").attr("selected",true);
						/*
						$('#bianzhiId').append(
								"<option value='" + bianzhiId + "' data-name='"
										+ bianzhiName
										+ "' selected='selected'>"
										+ bianzhiName + "</option>");
						$('#jiaoduiId').append(
								"<option value='" + jiaoduiId + "' data-name='"
										+ jiaoduiName
										+ "' selected='selected'>"
										+ jiaoduiName + "</option>");
						$('#shenheId').append(
								"<option value='" + shenheId + "' data-name='"
										+ shenheName + "' selected='selected'>"
										+ shenheName + "</option>");
						$('#pizhunId').append(
								"<option value='" + pizhunId + "' data-name='"
										+ pizhunName + "' selected='selected'>"
										+ pizhunName + "</option>");
						*/
						$('#bianzhiId').find("option[value='"+bianzhiId+"']").attr("selected",true);
						$('#jiaoduiId').find("option[value='"+jiaoduiId+"']").attr("selected",true);
						$('#shenheId').find("option[value='"+shenheId+"']").attr("selected",true);
						$('#pizhunId').find("option[value='"+pizhunId+"']").attr("selected",true);
						
						$('#bianzhiName').val(bianzhiName);
						$('#jiaoduiName').val(jiaoduiName);
						$('#shenheName').val(shenheName);
						$('#pizhunName').val(pizhunName);

						$('#bianzhiDate').val(bianzhiDate);
						$('#jiaoduiDate').val(jiaoduiDate);
						$('#shenheDate').val(shenheDate);
						$('#pizhunDate').val(pizhunDate);
					}
				}
			});
	//****************************************添加权限******************************************************
	/*
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getGongyiGuichengById.action",
		data : {
			'gongyiGuicheng.id' : $('#gygcId').val()
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var data = data.data;

				bianzhiId = data.bianzhiId;
				jiaoduiId = data.jiaoduiId;
				shenheId = data.shenheId;
				pizhunId = data.pizhunId;

				status = data.status;
				//$('#button').attr('disabled',true);添加disabled属性
		//$('#button').removeAttr("disabled"); 移除disabled属性 

		//markingForm
		//markingButton
		//saveButton
		//presentButton
		//printButton
		switch (status) {
		case '打回':
			//$(":enabled:not(option)").attr('disabled',true);
			$("#markingForm input").attr('disabled', true);
			$("#markingButton").attr('disabled', true);
			$("#backButton").attr('disabled', true);
			//$("#historyButton").attr('disabled',true);
			$("[id^='shenhe']").attr("disabled", true);
			$("[id^='pizhun']").attr("disabled", true);
			break;
		case '待编制':
			//$(":enabled:not(option)").attr('disabled',true);
			$("#markingForm input").attr('disabled', true);
			$("#markingButton").attr('disabled', true);
			$("#backButton").attr('disabled', true);
			//$("#historyButton").attr('disabled', true);
			$("[id^='shenhe']").attr("disabled", true);
			$("[id^='pizhun']").attr("disabled", true);
			break;
		case '已编制':
			if (jiaoduiId == dengluId) {
				$(":enabled:not(option)").attr('disabled', true);

				$("#markingForm input").removeAttr("disabled");
				$("#saveButton").removeAttr("disabled");
				$("#presentButton").removeAttr("disabled");
				$("#backButton").removeAttr('disabled');
				$("#markingButton").removeAttr('disabled');
				$("#printButton").removeAttr("disabled");

				$("[id^='shenhe']").removeAttr("disabled");
			} else {
				$(":enabled:not(option)").attr('disabled', true);
			}
			break;
		case '已校对':
			if (shenheId == dengluId) {
				$(":enabled:not(option)").attr('disabled', true);

				$("#markingForm input").removeAttr("disabled");
				$("#saveButton").removeAttr("disabled");
				$("#presentButton").removeAttr("disabled");
				$("#backButton").removeAttr('disabled');
				$("#markingButton").removeAttr('disabled');
				$("#printButton").removeAttr("disabled");

				$("[id^='pizhun']").removeAttr("disabled");
			} else {
				$(":enabled:not(option)").attr('disabled', true);
			}
			break;
		case '已审核':
			if (pizhunId == dengluId) {
				$(":enabled:not(option)").attr('disabled', true);

				$("#markingForm input").removeAttr("disabled");
				$("#saveButton").removeAttr("disabled");
				$("#presentButton").removeAttr("disabled");
				$("#backButton").removeAttr('disabled');
				$("#markingButton").removeAttr('disabled');
				$("#printButton").removeAttr("disabled");

				//$("[id^='pizhun']").removeAttr("disabled");
			} else {
				$(":enabled:not(option)").attr('disabled', true);
			}
			break;
		case '已批准':
			$(":enabled:not(option)").attr('disabled', true);

			$("#printButton").removeAttr("disabled");
			break;
		default:

			break;
		}
	}
}
	});*/
	//**************末尾************************	
});
</script>
