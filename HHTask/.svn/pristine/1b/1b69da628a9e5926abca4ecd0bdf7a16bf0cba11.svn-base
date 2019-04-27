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
		<style type="text/css">
.table th,.table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
	height: 24px;
}

#gongyiNav {
	width: 100%;
	background: #666666;
	color: #FFFFFF;
	font-family: 黑体;
	text-align: center;
	font-size: 13px;
	font-weight: bold;
}

#gongyiNav a,span {
	border-left: #CCCCCC 1px solid;
	display: inline-block;
	height: 40px;
	line-height: 40px;
	padding: 0px 10px;
	margin-left: -8px;
}

#gongyiNav a:link,a:visited,a:active {
	text-decoration: none;
	color: #FFFFFF;
}

#gongyiNav a:hover {
	background: #CCCCCC;
}

form span {
	border: 0px;
}
</style>

	</head>
	<body style="text-align: center;">
		<div align="center" style="width: 900px;">
			<form id="markingForm" method="post" style="">
				<input type="hidden" id="gygcId"
					name="gongyiGuichengScoreItem.gongyiGuichengId"
					value="${gongyiGuichengScoreItem.gongyiGuichengId}" />
				<input type="hidden" id="processDataId"
					name="gongyiGuichengScoreItem.processDataId"
					value="${gongyiGuichengScoreItem.processDataId}" />
				<input type="hidden" id="gongyiGuichengScoreJiluParentId"
					name="gongyiGuichengScoreJilu.parentId"
					value="${gongyiGuichengScoreJilu.parentId}" />
				<!-- 首页 -->
				<table id="gongyiGuichengScoreJiluTable"
					style="display: block; width: 900px;">

				</table>
				<table id="guichengScoreItemHuizongTable"
					style="display: block; width: 900px;" class="table">
					<tr>
						<td colspan="4">
							<span>当前页面得分：<a id="score">0</a> </span><span>总分：<a
								id="totalScore">0</a> </span>
						</td>
					</tr>
					<tr>
						<td>
							工序名称
						</td>
						<td>
							工序号
						</td>
						<td>
							打分
						</td>
						<td>
							总分
						</td>
					</tr>
				</table>
			</form>
			<table style="width: 100%; text-align: center; border: 0px;">
				<tr>
					<td>
						<input type="button" id="markingButton" value="打分" class="input" />
					</td>
				</tr>
			</table>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function() {
	var gygcId = $("#gygcId").val();
	var processDataId = $("#processDataId").val();
	var model = '';
	var gongyiGuichengScoreJiluParentId = $("#gongyiGuichengScoreJiluParentId")
			.val();
	$
			.ajax( {
				type : "get",
				dataType : "json",
				url : "gongyiGuichengScoreAction!getGongyiGuichengScoreLeibieById.action",
				data : {
					'gongyiGuichengScoreLeibie.id' : gongyiGuichengScoreJiluParentId
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						var gongyiGuichengScoreLeibie = data.data;
						model = gongyiGuichengScoreLeibie.biaoshi;
						var score = gongyiGuichengScoreLeibie.score;
						$("#totalScore").text(score);
					}
				}
			});
	$
			.ajax( {
				type : "get",
				dataType : "json",
				url : "gongyiGuichengScoreAction!getGongyiGuichengScoreJiluListByParentId.action",
				data : {
					'gongyiGuichengScoreJilu.parentId' : gongyiGuichengScoreJiluParentId
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						var gongyiGuichengScoreLeibieList = data.data;
						$(gongyiGuichengScoreLeibieList)
								.each(
										function(i, n) {
											var gongyiGuichengScoreLeibie = n;
											var gongyiGuichengScoreLeibieName = gongyiGuichengScoreLeibie.name;
											var gongyiGuichengScoreLeibieBiaoshi = gongyiGuichengScoreLeibie.biaoshi;
											var gongyiGuichengScoreXuanxiangList = gongyiGuichengScoreLeibie.gongyiGuichengScoreXuanxiangList;
											var html = '';
											html += '<tr>';
											html += '<td style="width: 300px;">';
											html += '<span>' + gongyiGuichengScoreLeibieName + '：</span>';
											html += '</td>';
											html += '<td style="width: 600px;">';

											$(gongyiGuichengScoreXuanxiangList)
													.each(
															function(j, n) {
																var gongyiGuichengScoreXuanxiang = n;
																var gongyiGuichengScoreXuanxiangName = gongyiGuichengScoreXuanxiang.name;
																var gongyiGuichengScoreXuanxiangScore = gongyiGuichengScoreXuanxiang.score;
																var timeFor = new Date()
																		.getTime()
																		+ "_"
																		+ i
																		+ "_"
																		+ j;
																//var timeFor=new Date().getTime();
																html += '<label for="' + timeFor + '">';
																html += '<input type="button" value="'
																		+ gongyiGuichengScoreXuanxiangName
																		+ ":"
																		+ gongyiGuichengScoreXuanxiangScore
																		+ '"/>';
																//html+=gongyiGuichengScoreXuanxiangName+":"+gongyiGuichengScoreXuanxiangScore;
																html += '</label>';
																//if(j=0){
																html += '<input type="hidden" name="gongyiGuichengScoreItem.id"/><input type="radio" id="'
																		+ timeFor
																		+ '" name="'
																		+ gongyiGuichengScoreLeibieBiaoshi
																		+ '" value="'
																		+ gongyiGuichengScoreXuanxiangScore
																		+ '" ';
																if (j == 0) {
																	html += "checked='checked'";
																}
																html += "/>";
																//}else {
																//html+='<input type="hidden" name="gongyiGuichengScoreItem.id"/><input type="radio" id="'+timeFor+'" name="'+gongyiGuichengScoreLeibieBiaoshi+'" value="'+gongyiGuichengScoreXuanxiangScore+'"/>';
																//}
															});
											html += '</td>';
											html += '</tr>';
											html += '<tr><td colspan="2"><textarea name="gongyiGuichengScoreItem.remark" style="width: 100%;"></textarea></td></tr>';
											$(html)
													.appendTo(
															"#gongyiGuichengScoreJiluTable");
										});

					}
				}
			});

	$
			.ajax( {
				type : "get",
				dataType : "json",
				url : "gongyiGuichengScoreAction!getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId.action",
				data : {
					'gongyiGuichengScoreItem.gongyiGuichengId' : $("#gygcId")
							.val(),
					'gongyiGuichengScoreItem.processDataId' : $(
							"#processDataId").val(),
					'gongyiGuichengScoreItem.model' : model
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						var gongyiGuichengScoreItemList = data.data;
						var totalScore = 0;
						$(gongyiGuichengScoreItemList)
								.each(
										function(i, n) {
											var id = this.id;
											var biaoshi = this.biaoshi;
											var score = this.score;
											if (score) {
												totalScore += score;
											}
											var remark = this.remark;
											var radio = $("input[name='"
													+ biaoshi + "'][value='"
													+ score + "']");
											radio.attr("checked", true);
											$("input[name='" + biaoshi + "']")
													.parent()
													.find(
															"input[name='gongyiGuichengScoreItem.id']")
													.val(id);
											radio
													.parent()
													.parent()
													.next()
													.find(
															"textarea[name='gongyiGuichengScoreItem.remark']")
													.html(remark);
										});
						$("#score").text(totalScore);
					}
				}
			});
	//工艺规程打分汇总
	$
			.ajax( {
				type : "get",
				dataType : "json",
				url : "gongyiGuichengScoreAction!getGongyiGuichengScoreItemHuizong.action",
				data : {
					'gongyiGuichengScoreItem.gongyiGuichengId' : $("#gygcId")
							.val()
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						var guichengScoreItemHuizongList = data.data;
						$(guichengScoreItemHuizongList).each(
								function() {
									name = this.name;
									no = this.no;
									score = this.score;
									leibieScore = this.leibieScore;
									$(
											'<tr><td>' + name + '</td><td>'
													+ no + '</td><td>' + score
													+ '</td><td>' + leibieScore
													+ '</td></tr>').appendTo(
											"#guichengScoreItemHuizongTable");
								});
					}
				}
			});

	function getGongyiGuichengScoreItemParams() {
		var gongyiGuichengScoreItemArr = new Array();
		var $gongyiGuichengScoreItemRemarkArr = $("textarea");
		//var $gongyiGuichengScoreItemIdArr=$("#gongyiGuichengScoreJiluTable :hidden");
		$(":radio:checked")
				.each(
						function(i, n) {
							var $gongyiGuichengScoreItem = $(this);
							gongyiGuichengScoreItemArr[i] = {
								'id' : $gongyiGuichengScoreItem
										.parent()
										.find(
												"input[name='gongyiGuichengScoreItem.id']")
										.val(),
								'biaoshi' : $gongyiGuichengScoreItem
										.attr("name"),
								'score' : $gongyiGuichengScoreItem.val(),
								'gongyiGuichengId' : gygcId,
								'processDataId' : processDataId,
								'model' : model,
								'remark' : $gongyiGuichengScoreItem
										.parent()
										.parent()
										.next()
										.find(
												"textarea[name='gongyiGuichengScoreItem.remark']")
										.val()
							}
						});
		return JSON.stringify(gongyiGuichengScoreItemArr);
	}
	$('#markingButton')
			.bind(
					'click',
					function() {
						//var param = $('#markingForm').serialize();
						var gongyiGuichengScoreItemParams = getGongyiGuichengScoreItemParams();
						$
								.ajax( {
									type : "get",
									dataType : "json",
									url : "gongyiGuichengScoreAction!updateGongyiGuiChengMarkingPage.action",
									data : {
										'gongyiGuichengScoreItem.params' : gongyiGuichengScoreItemParams
									},
									async : false,
									success : function(data) {
										var success = data.success;
										if (success) {
											alert('打分成功!');
											window.location.reload();
										} else {
											alert('打分失败!');
										}
									}
								});
					});
});
</script>
