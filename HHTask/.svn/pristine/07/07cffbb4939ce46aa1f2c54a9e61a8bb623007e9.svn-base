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
		<script type="text/javascript">
onload = function() {
	var joinTrainList = "${joinTrainList[0]}";

	if (joinTrainList == "") {
		closeShow();
	}
}

function closeShow() {
	var addJoinTrainDiv = document.getElementById("addJoinTrain");
	var showJoinTrainDiv = document.getElementById("showJoinTrain");
	addJoinTrainDiv.style.display = "block";
	showJoinTrainDiv.style.display = "none";
}
</script>

	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="JoinTrainAction!findJoinTrainByUid.action?id=${id}"
						style="color: #ffffff">培训记录</a>
					<a href="javascript:;" onclick="closeShow()" style="color: #ffffff">添加培训记录</a>
				</div>
			</div>
			
			<div align="center" id="addJoinTrain" style="display: none;">
				<form action="JoinTrainAction!addJoinTrain.action" method="post"
					onsubmit="return checkJoinTrainForm()">
					<table border="0" class="table" id="addTable">
						<tbody>
							<tr>
								<th colspan="13">
									添加入职培训
									<input type="hidden" name="joinTrain.user.id" value="${id}" />

								</th>
							</tr>
							<tr>
								<th colspan="13" align="center">
									培训名称:
									<input name="joinTrain.trainName" id="trainName" />
									<input type="button" value="添加内容" onclick="addTr()" />
								</th>
							</tr>
							<tr>
								<td>
									培训内容:
								</td>
								<td>
									<input name="jtDetails.trainContent" id="trainContent_0" />
								</td>
								<td>
									主讲人:
								</td>
								<td>
									<input name="jtDetails.speaker" style="width: 80px;"
										id="speaker_0" />
								</td>
								<td>
									时间:
								</td>
								<td>
									<input name="jtDetails.trainDate" style="width: 100px;"
										class="Wdate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										id="trainDate_0" />
								</td>
								<td>
									考试成绩:
								</td>
								<td>
									<input name="jtDetails.testScore" style="width: 60px;"
										id="testScore_0" />
								</td>
								<td>
									本人签字:
								</td>
								<td>
									<select name="jtDetails.ownSigStatus">
										<option value="是">
											是
										</option>
										<option value="未签字">
											否
										</option>
									</select>
								</td>
								<td>
									讲师签字:
								</td>
								<td>
									<select name="jtDetails.speakerSigStatus">
										<option value="是">
											是
										</option>
										<option value="未签字">
											否
										</option>
									</select>
								</td>
							</tr>
							<tr id="addBeforeTr">
								<td colspan="13" align="center">
									培训效果确认
								</td>
							</tr>
							<tr>
								<td colspan="13" align="center">
									<textarea rows="8" cols="118" name="joinTrain.trainEffect"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="13" align="center">
									<input type="submit" value="添加"
										style="width: 80px; height: 50px;" />
									<input type="reset" value="重置"
										style="width: 80px; height: 50px;" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>

			<div id="showJoinTrain" style="width: 100%">
				<div id="printJoinTrain">
					<div style="padding: 40 px; width: 100%">
						<table class="table" style="width: 100%">
							<tr>
								<th>
									姓名
								</th>
								<th>
									性别
								</th>
								<th>
									出生日期
								</th>
								<th>
									学历
								</th>
								<th>
									入职时间
								</th>
								<th>
									任职部门
								</th>
								<th>
									任职岗位
								</th>
							</tr>
							<tr>
								<th>
									${joinTrainList[0].user.name}
								</th>
								<th>
									${joinTrainList[0].user.sex}
								</th>
								<th>
									${joinTrainList[0].user.bothday}
								</th>
								<th>
									${joinTrainList[0].user.education}
								</th>
								<th>
									${joinTrainList[0].user.joined}
								</th>
								<th>
									${joinTrainList[0].user.dept}
								</th>
								<th>
									${joinTrainList[0].user.duty}
								</th>
							</tr>
							<s:iterator id="pageJoinTrain" value="joinTrainList" status="">
								<tr>
									<th colspan="7">
										${pageJoinTrain.trainName}
										<a
											href="JoinTrainAction!delJoinTrain.action?id=${pageJoinTrain.id}">删除
										</a>
									</th>
								</tr>
								<tr>
									<th colspan="2">
										培训内容
									</th>
									<th>
										主持人
									</th>
									<th>
										时间
									</th>
									<th>
										考试成绩
									</th>
									<th>
										本人签字
									</th>
									<th>
										讲师签字
									</th>
								</tr>
								<s:iterator id="pageJoinTDetails"
									value="#pageJoinTrain.joinTDetails">
									<tr>
										<td colspan="2" align="center">
											${pageJoinTDetails.trainContent}
										</td>
										<td align="center">
											${pageJoinTDetails.speaker}
										</td>
										<td align="center">
											${pageJoinTDetails.trainDate}
										</td>
										<td align="center">
											${pageJoinTDetails.testScore}
										</td>
										<td align="center">
											<s:if test='ownSigStatus=="是"'>
									${joinTrainList[0].user.name}
								</s:if>
											<s:else>${pageJoinTDetails.ownSigStatus}</s:else>
										</td>
										<td align="center">
											${pageJoinTDetails.speakerSigStatus}
										</td>
									</tr>
								</s:iterator>
								<tr>
									<th colspan="7">
										培训效果确认
									</th>
								</tr>
								<tr>
									<th colspan="7" align="left" style="height: 80px;">
										${pageJoinTrain.trainEffect}
										<div align="right">
											签字:_________________ 日期:________________________
										</div>
									</th>
								</tr>
							</s:iterator>
						</table>
					</div>
				</div>
				<input onclick="pagePrint('printJoinTrain')" type="button"
					value="打印">
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	<script type="text/javascript">

function checkJoinTrainForm() {
	var trainName = document.getElementById("trainName");
	if (trainName.value == "") {
		alert("培训名称不能为空!");
		trainName.focus();
		return false;
	}

	for ( var i = 0; i < count; i++) {
		var trainContent = document.getElementById("trainContent_" + i);//培训内容
		var speaker = document.getElementById("speaker_" + i);//主讲人
		var trainDate = document.getElementById("trainDate_" + i);//培训时间
		var testScore = document.getElementById("testScore_" + i);//考试成绩
		if (trainContent.value == "") {
			alert("培训内容不能为空!");
			trainContent.focus();
			return false;
		} else if (speaker.value == "") {
			alert("主讲人不能为空!");
			speaker.focus();
			return false;
		} else if (trainDate.value == "") {
			alert("培训时间不能为空!");
			trainDate.focus();
			return false;
		} else if (testScore.value == "") {
			alert("考试成绩不能为空!");
			testScore.focus();
			return false;
		}

	}
	return true;
}

var count = 1;
function addTr() {
	var oldTtable = document.getElementById("addTable").tBodies[0];//获得table
	var addBeforeTr = document.getElementById("addBeforeTr");//即将添加的tr将增加到该tr之前

	var tr1 = document.createElement("tr");//创建tr
	var td1 = document.createElement("td");//创建td
	var td2 = document.createElement("td");//创建td
	var td3 = document.createElement("td");//创建td
	var td4 = document.createElement("td");//创建td
	var td5 = document.createElement("td");//创建td
	var td6 = document.createElement("td");//创建td
	var td7 = document.createElement("td");//创建td
	var td8 = document.createElement("td");//创建td
	var td9 = document.createElement("td");//创建td
	var td10 = document.createElement("td");//创建td
	var td11 = document.createElement("td");//创建td
	var td12 = document.createElement("td");//创建td
	var td13 = document.createElement("td");//创建td

	var word1 = document.createTextNode("培训内容:");
	var word2 = document.createTextNode("主讲人:");
	var word3 = document.createTextNode("时间:");
	var word4 = document.createTextNode("考试成绩:");
	var word5 = document.createTextNode("本人签字:");
	var word6 = document.createTextNode("讲师签字:");

	var input1 = document.createElement("input");//培训内容
	input1.name = "jtDetails.trainContent";
	input1.id = "trainContent_" + count;

	var input2 = document.createElement("input");//主讲人
	input2.name = "jtDetails.speaker";
	input2.style.width = "80px";
	input2.id = "speaker_" + count;

	var input3 = document.createElement("input");//时间
	input3.name = "jtDetails.trainDate";
	input3.id = "trainDate_" + count;
	input3.className = "Wdate";
	input3.style.width = "100px";
	input3.onclick = function() {

		WdatePicker( {
			dateFmt : 'yyyy-MM-dd',
			skin : 'whyGreen'
		})
	};

	var input4 = document.createElement("input");//考试成绩
	input4.name = "jtDetails.testScore";
	input4.style.width = "60px";
	input4.id = "testScore_" + count;

	var input5 = document.createElement("input");//删除
	input5.type = "button";
	input5.value = "删除";
	input5.onclick = function() {
		oldTtable.deleteRow(tr1.rowIndex);
	}

	var select1 = document.createElement("select");//个人签名
	select1.name = "jtDetails.ownSigStatus";
	select1.options.add(new Option("是", "是"));
	select1.options.add(new Option("否", "未签字"));

	var select2 = document.createElement("select");//讲师签名
	select2.name = "jtDetails.speakerSigStatus";
	select2.options.add(new Option("是", "是"));
	select2.options.add(new Option("否", "未签字"));

	oldTtable.insertBefore(tr1, addBeforeTr);

	tr1.insertBefore(td1, null);//培训内容
	td1.insertBefore(word1, null);
	tr1.insertBefore(td2, null);
	td2.insertBefore(input1, null);

	tr1.insertBefore(td3, null);//主讲人
	td3.insertBefore(word2, null);
	tr1.insertBefore(td4, null);
	td4.insertBefore(input2, null);

	tr1.insertBefore(td5, null);//时间
	td5.insertBefore(word3, null);
	tr1.insertBefore(td6, null);
	td6.insertBefore(input3, null);

	tr1.insertBefore(td7, null);//考试成绩
	td7.insertBefore(word4, null);
	tr1.insertBefore(td8, null);
	td8.insertBefore(input4, null);

	tr1.insertBefore(td9, null);//本人签字
	td9.insertBefore(word5, null);
	tr1.insertBefore(td10, null);
	td10.insertBefore(select1, null);

	tr1.insertBefore(td11, null);//讲师签字
	td11.insertBefore(word6, null);
	tr1.insertBefore(td12, null);
	td12.insertBefore(select2, null);

	tr1.insertBefore(td13, null);//删除
	td13.insertBefore(input5, null);
	count++;
}
</script>
</html>
