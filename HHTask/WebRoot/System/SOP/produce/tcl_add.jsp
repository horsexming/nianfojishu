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
					添加EC
				</h3>
				<form action="procardTemplateGyAction_addtclog.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right" >
								设变单号
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.sbNumber" id="sbNumber" />
							</td>
						</tr>
						<tr>
							<th align="right">
								外部设变单号
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.outbsNumber" id="outbsNumber" />
							</td>
						</tr>
						<tr>
							<th align="right">
								接收时间
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.recriveDate" id="recriveDate" 
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
								<input type="hidden" id="userId" name="technicalchangeLog.applyUserId" />		
							</td>
						</tr>
						<tr>
							<th align="right">
								涉及车间
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.aboutPlace" id="aboutPlace" />
							</td>
						</tr>
						<tr>
							<th align="right">
								产品编码
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.ywMarkId" id="ywMarkId" />
							</td>
						</tr>
						<tr>
							<th align="right">
								产品类别
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.producttype" id="producttype" />
							</td>
						</tr>
						<tr>
							<th align="center">
								件号
							</th>
							<th align="center" colspan="2">
								描述 <input type="button" value="增加" onclick="addnr()">
							</td>
						</tr>
						<tr id="nrtr0">
							<th align="right">
								<input type="text" name="technicalchangeLog.tclDetailList[0].markId" id="sonmarkId" />
							</th>
							<td align="left" colspan="2"><textarea name="technicalchangeLog.tclDetailList[0].changeLog" rows="3" cols="40"></textarea>
							<input type="button" value="删除" onclick="deletenr(0)">
							</td>
							</td>
						</tr>
						<tr id="cbtr">
							<th align="right">
								成本影响
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.aboutcb" id="aboutcb" />
							</td>
							</td>
						</tr>
						<tr>
							<th align="right">
								在制品处理
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.zzpcl" id="zzpcl" />
							</td>
							</td>
						</tr>
						<tr>
							<th align="right">
								已完成品处理
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.wcpcl" id="wcpcl" />
							</td>
							</td>
						</tr>
						<tr>
							<th align="right">
								未投订单处理
							</th>
							<td colspan="2">
								<input type="text" name="technicalchangeLog.wtdcl" id="wtdcl" />
							</td>
							</td>
						</tr>
						<tr>
							<th align="right">
								涉及部门
							</th>
							<td colspan="2">
								<input type="checkbox" name="checkboxs" id="gcb" value="1"/>工程部
								<input type="checkbox" name="checkboxs" id="scb" value="2"/>生产部
								<input type="checkbox" name="checkboxs" id="pzb" value="3"/>品质部
								<input type="checkbox" name="checkboxs" id="pmcwk" value="4"/>PMC物控
								<input type="checkbox" name="checkboxs" id="pmcsg" value="5"/>PMC生管
								<input type="checkbox" name="checkboxs" id="cgwx" value="6"/>采购外协
								<input type="checkbox" name="checkboxs" id="cgwg" value="7"/>采购外购
								<input type="checkbox" name="checkboxs" id="ck" value="8"/>仓库
							</td>
							</td>
						</tr>
						<tr>
							<th align="right">
								备注
							</th>
							<td align="left" colspan="2"><textarea name="technicalchangeLog.remark" rows="3" cols="40"></textarea>
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
