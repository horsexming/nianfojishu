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
					修改E-CAR
				</h3>
				<form action="procardTemplateGyAction_updateecar.action" method="post" 
					onsubmit="return validate()">
					<input type="hidden" value="${ecar.id}" name="ecar.id">
					<table class="table">
						<tr>
							<th align="right">
								提出日期
							</th>
							<td colspan="2">
								<input type="text" name="ecar.applyDate" id="applyDate"  value="${ecar.applyDate}"
								class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								ecar编号
							</th>
							<td colspan="2">
								<input type="text" name="ecar.ecarNumber" id="ecarNumber"  value="${ecar.ecarNumber}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								产品编码
							</th>
							<td colspan="2">
								<input type="text" name="ecar.ywMarkId" id="ywMarkId" value="${ecar.ywMarkId}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								问题点
							</th>
								<td align="left" colspan="2"><textarea name="ecar.problempoint" rows="3" cols="40">${ecar.problempoint}</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								提交人
							</th>
							<td colspan="2">
								<div id="showAll"
										style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1; top: 40px">
									</div>
							 <input type="text" value="${ecar.tjUserName}" id="shortname" 
										 onkeyup="getAllNames()" autocomplete="off"
										 style="height: 20px; width: 115px;" onFocus="init('')"
										onBlur="hidediv('')"/>
								<lable id="deptshow"></lable>	
								<input type="hidden" id="userId" name="ecar.tjUserId" value="${ecar.tjUserId}"/>	
							</td>
						</tr>
						<tr>
							<th align="right">
								所属
							</th>
							<td colspan="2">
								<input type="text" name="ecar.belongto" id="belongto" value="${ecar.belongto}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								目前状态
							</th>
							<td colspan="2">
								<SELECT name="ecar.zxstatus" id="zxstatus" >
									<option>${ecar.zxstatus}</option>
									<option>待创建人确认</option>
									<option>待处理人处理</option>
									<option>待处理试制报告</option>
									<option>待提交试制报告</option>
									<option>待回归试制报告</option>
									<option>待确认试制报告</option>
									<option>待审核试制报告</option>
									<option>已驳回</option>
									<option>已取消</option>
									<option>已关闭</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								关闭时间
							</th>
							<td colspan="2">
								<input type="text" name="ecar.endTime" id="endTime"  value="${ecar.endTime}"
								class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
							</td>
						</tr>
						<tr>
							<th align="right">
								时效性
							</th>
							<td colspan="2">
								<input type="text" name="ecar.sxx" id="sxx" value="${ecar.sxx}" />
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
