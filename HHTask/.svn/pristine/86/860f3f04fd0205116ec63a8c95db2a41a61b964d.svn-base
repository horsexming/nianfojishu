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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				align="left">
			</div>
			
			<div align="center">
				<h3>
					添加pcn
				</h3>
				<form action="procardTemplateGyAction_addpcn.action" method="post"  enctype="multipart/form-data" 
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								提出日期
							</th>
							<td colspan="2">
								<input type="text" name="pcn.applyDate" id="applyDate" 
								class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								发起人
							</th>
							<td colspan="2">
							<div id="showAll"
										style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1; top: 40px">
									</div>
							 <input type="text" value="" id="shortname"
										 onkeyup="getAllNames()" autocomplete="off"
										 style="height: 20px; width: 115px;" onFocus="init('')"
										onBlur="hidediv('')"/>
								<lable id="deptshow"></lable>	
								<input type="hidden" id="userId" name="pcn.applyUserId" />		
							</td>
						</tr>
						<tr>
							<th align="right">
								PCN编号
							</th>
							<td colspan="2">
								<input type="text" name="pcn.pcnNumber" id="pcnNumber" />
							</td>
						</tr>
						<tr>
							<th align="right">
								产品编码
							</th>
							<td colspan="2">
								<input type="text" name="pcn.ywMarkId" id="ywMarkId" />
							</td>
						</tr>
						<tr>
							<th align="right">
								所属
							</th>
							<td colspan="2">
								<input type="text" name="pcn.belongto" id="belongto" />
							</td>
						</tr>
						<tr>
							<th align="right">
								主题
							</th>
							<td colspan="2">
								<input type="text" name="pcn.pcntheme" id="pcntheme" />
							</td>
						</tr>
						<tr>
							<th align="right">
								提交客户日期
							</th>
							<td colspan="2">
								<input type="text" name="pcn.submitDate" id="submitDate" 
								class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								客户反馈日期
							</th>
							<td colspan="2">
								<input type="text" name="pcn.feedbackDate" id="feedbackDate" 
								class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr id="cbtr">
							<th align="right">
								客户反馈意见
							</th>
								<td align="left" colspan="2">
								<SELECT name="pcn.feedbackoption">
									<option></option>
									<option>接受</option>
									<option>拒绝</option>
								</SELECT>
								</td>
						</tr>
						<tr>
							<th align="right">
								执行情况
							</th>
							<td colspan="2">
								<input type="text" name="pcn.zxStatus" id="zxStatus" />
							</td>
							</td>
						</tr>
						<tr>
							<th align="right">
								是否结案
							</th>
							<td colspan="2">
								<SELECT name="pcn.isend" >
									<option>已结案</option>
									<option>未结案</option>
								</SELECT>
							</td>
							</td>
						</tr>
						<tr>
						<th align="right"></th>
						<td><input type="button" id="fileButton_1"
											onclick="uploadFile(this,1)" value="上传附件">
										<div id="fileDiv_1" style="display: none;">

										</div>
						</td>
						</tr>
						
						<tr>
							<td colspan="3" align="center">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + "," + few + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}

var nrsize=1;
var nrindex=0;
function addnr(){
	nrsize++;
	nrindex++;
	var html="<tr id='nrtr"+nrindex+"'><th align='right'><input type='text' name='technicalchangeLog.tclDetailList["+nrindex+"].markId' id='sonmarkId' /></th>" 
		+ "<td align='left' colspan='2'><textarea name='technicalchangeLog.tclDetailList["+nrindex+"].changeLog' rows='3' cols='40'></textarea>" 
		+ "<input type='button' value='删除' onclick='deletenr("+nrindex+")'></td></td></tr>";
	
	$("#cbtr").before(html);
}
function deletenr(index){
	if(nrsize==1){
		alert("最少留一行!");
		return ;
	}
	nrsize--;
	nrindex--;
	$("#nrtr"+index).remove();
}
//初始化显示div位置
function init() {
	count_seach++;
	var shortname =document.getElementById("shortname");
	var showAll =document.getElementById("showAll");
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
}
function hidediv(className) {
	count_seach--;
	if (count_seach == 0) {
		var showAll =$("#showAll").hide();
	}

}
function outdiv(obj) {
	obj.style.background = "#ffffff";
	hidediv();

}
//ajax获取所有的类似的全称
function getAllNames() {
	var shortname = $("#shortname").val();
	if (shortname == null || shortname == "") {
		return;
	}
	$
			.ajax( {
				type : "POST",
				url : "UsersAction!getAllName.action",
				dataType : "json",
				cache:false,
				data : {
					name : shortname
				},
				success : function(data) {
					$("#showAll" ).empty();
					$(data)
							.each(function() {
										$("#showAll")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' "
																+ "onclick='selectdiv(this)' align='left'>"
																+ $(this).attr('name')
																+ "/"
																+ $(this).attr('code')
																+ "/"
																+ $(this).attr('dept')
																+ "<span style='visibility: hidden;'>"
																+ $(this).attr('id')
																+ "/"
																+ $(this).attr('name')
																+ "/"
																+ $(this).attr('code')
																+ "/"
																+ $(this).attr('dept')
																+ "</span>"
																+ "</div>");//wxf
									});
					$("#showAll" ).show();
				}
			});
}
function selectdiv(obj) {
	var html = $(obj).find("span").html();
	$("#showAll").hide();
	var html2 = html.split("/");
	 $("#userId").val(html2[0]);
	 $("#shortname").val(html2[1]);
	  $("#deptshow").html(html2[3]);
}
</script>
	</body>
</html>
