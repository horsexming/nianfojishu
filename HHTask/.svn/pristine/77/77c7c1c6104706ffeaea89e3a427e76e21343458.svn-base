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
		<div>
			<div align="center">
				<div id="rootTemplateDiv" style="height: 600px;">
					<form action="ProcardTemplateAction!addRootrProcardTem.action"
						method="post" onsubmit="check">
						<input type="hidden" name="procardStruts" value="总成" />
						<table class="table" style="width: 60%;">
							<tr>
								<th align="center" colspan="2">
									添加总成流水卡片(Add water card assembly)
								</th>
							</tr>
							<tr>
								<th align="right" style="width: 25%;">
									件号(Part No.):
								</th>
								<td>
									<input name="procardTemplate.markId" id="markId">（生产使用）
								</td>
							</tr>
							<tr>
								<th align="right" style="width: 25%;">
									业务件号(Part No.):
								</th>
								<td>
									<input name="procardTemplate.ywMarkId" id="ywMarkId">（对外使用）
								</td>
							</tr>
							<tr>
								<th align="right">
									名称(Name):
								</th>
								<td>
									<input name="procardTemplate.proName">
								</td>
							</tr>
							<tr>
								<th align="right">
									车型(Models):
								</th>
								<td>
									<input name="procardTemplate.carStyle">
								</td>
							</tr>
							<tr>
								<th align="right">
									版本号(Version Number):
								</th>
								<td>
									<input name="procardTemplate.banBenNumber">
								</td>
							</tr>
							<tr style="display: none">
								<th align="right">
									数量(Quantity):
								</th>
								<td>
									<input name="procardTemplate.maxCount" value="0">
									(生产批次的最大数量)
								</td>
							</tr>
							<tr>
								<th align="right">
									零件类型(Part Type):
								</th>
								<td>
									<select name="procardTemplate.procardStyle"
										style="width: 155px;">
										<option value="总成">
											总成(Assembly)
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									生产类型(Product Type):
								</th>
								<td>
									<select name="procardTemplate.productStyle"
										style="width: 155px;">
										<option value="批产">
											批产(Batch production)
										</option>
										<option value="试制">
											试制(Trial)
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									激活类型:
								</th>
								<td>
									<select name="procardTemplate.jihuoType" style="width: 155px;">
										<option value="cc">
											层次激活
										</option>
										<option value="zzj">
											自制件激活
										</option>
									</select>
								</td>
							</tr>
<%--							<tr>--%>
<%--								<th align="right">--%>
<%--									工艺规程编号:--%>
<%--								</th>--%>
<%--								<td>--%>
<%--								<input name="procardTemplate.numb">--%>
<%--								</td>--%>
<%--							</tr>--%>
							<tr>
								<th align="right">
									发出日:
								</th>
								<td>
								<input class="Wdate" type="text" name="procardTemplate.fachuDate"
											id="fachuDate"  readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								</td>
							</tr>
<%--							<tr>--%>
<%--								<th align="right">--%>
<%--									页数:--%>
<%--								</th>--%>
<%--								<td>--%>
<%--								<input name="procardTemplate.pageTotal" id="pageTotal" onkeyup="mustBeNumber('pageTotal')">--%>
<%--								</td>--%>
<%--							</tr>--%>
							<tr>
								<th align="right">
									编辑人 ：  <select name="procardTemplate.bianzhiId" id='bianzhi' ></select>
								</th>
								<td>
									时间 ： <input class="Wdate" type="text" name="procardTemplate.bianzhiDate"
											id="bianzhiDate"  readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								</td>
							</tr>
							<tr>
								<th align="right">
									校对人 ：  <select name="procardTemplate.jiaoduiId" id='jiaodui' ></select>
								</th>
								<td>
									时间 ： <input class="Wdate" type="text" name="procardTemplate.jiaoduiDate"
											id="jiaoduiDate"  readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								</td>
							</tr>
							<tr>
								<th align="right">
									审核人 ：  <select name="procardTemplate.shenheId" id='shenhe' ></select>
								</th>
								<td>
									时间 ： <input class="Wdate" type="text" name="procardTemplate.shenheDate"
											id="shenheDate"  readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								</td>
							</tr>
							<tr>
								<th align="right">
									批准人 ：  <select name="procardTemplate.pizhunId" id='pizhun' ></select>
								</th>
								<td>
									时间 ： <input class="Wdate" type="text" name="procardTemplate.pizhunDate"
											id="pizhunDate"  readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								</td>
							</tr>
							<tr style="display: none;">
								<th align="right">
									是否为单交件:
								</th>
								<td>
									否
									<input type="radio" name="procardTemplate.danjiaojian" value=""
										checked="checked" onchange="danjiao('否');">
									是
									<input type="radio" name="procardTemplate.danjiaojian"
										value="单交件" onchange="danjiao('是');">
								</td>
							</tr>
                        <tr>
								<th align="right">
									备注(Remarks):
								</th>
								<td>
									<input name="procardTemplate.remark">
								</td>
							</tr>
						</table>
						<!-- ***************************************************************************************** -->
						<div id="t1" style="display: none;">
							<table class="table" style="width: 60%;">
								<tr>
									<th colspan="2">
										原材料信息
									</th>
								</tr>
								<tr>
									<th align="right">
										原材料:
									</th>
									<td>
									<div id="showAll" style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
						            </div>
						            <input type="text" id="shortname" onkeyup="getAllNames()" style="height: 20px" onFocus="init('showAll')" onBlur="hidediv('showAll')" name="markId" />
									<input type="hidden" id="trademark" name="procardTemplate.trademark">
									<input type="hidden" id="specification" name="procardTemplate.specification">
									<input type="hidden" id="unit" name="procardTemplate.yuanUnit">
									</td>
								</tr>
								<tr>
									<th align="right" >
										权值:
									</th>
									<td>
										<input name="procardTemplate.quanzi1" style="width: 71px;" />
										:
										<input name="procardTemplate.quanzi2" style="width: 71px;" />
										(自制件:原材料,格式如1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										炉号:
									</th>
									<td>
										<input name="procardTemplate.luhao">
									</td>
								</tr>
								<tr>
									<th align="right">
										编号:
									</th>
									<td>
										<input name="procardTemplate.number">
									</td>
								</tr>
								<tr>
									<th align="right">
										实际定额:
									</th>
									<td>
										<input name="procardTemplate.actualFixed">
									</td>
								</tr>
								<tr>
									<th align="right">
										是否外购:
									</th>
									<td>
										<select name="procardTemplate.status" style="width: 155px;">

											<option value="否">
												否
											</option>
											<option value="是">
												是
											</option>
										</select>
									</td>
								</tr>
							</table>
						</div>
						<table class="table" style="width: 60%;">
							<tr>
								<td align="center" colspan="2">
									<input type="submit" value="添加(Add)"
										style="width: 80px; height: 50px;" />
									<input type="reset" value="重置(Reset)"
										style="width: 80px; height: 50px;" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		var count_seach=0;
		function danjiao(val){
			if("是"==val){
				$("#t1").show();
				getUnit("danwei2");
			}else{
				$("#t1").hide();
			}
		}
function init() {
	count_seach++;
	var shortname = document.getElementById("shortname");
	var showAll = document.getElementById("showAll");
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
}
//ajax获取所有的类似的全称
function getAllNames() {
	if($("#shortname").val()==null||$("#shortname").val()==""){
		$("#showAll").empty();
		return;
	}
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction!getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.trademark' : $("#shortname").val(),
					'yuanclAndWaigj.clClass' : '原材料'
				},
				success : function(data) {
					$("#showAll").empty();
					$(data).each(
									function() {
										var trademark = $(this)
												.attr('trademark')
												.replace(
														$("#shortname").val(),
														"<font color='red'>"
																+ $("#shortname").val()
																+ "</font>");
										$("#showAll")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)' align='left'>"
																+ trademark
																+ ":"
																+ $(this).attr('specification')
																+":"
																+$(this).attr('unit')
																+"<span style='visibility: hidden;'>"
																+ $(this).attr('trademark')
																+ ":"
																+ $(this).attr('specification')
																+":"
																+$(this).attr('unit')
																+"</span>"
																+ "</div>");
									});
				}
			});
}
function selectdiv(obj){
	var html=$(obj).find("span").html();;
	var showAll=document.getElementById("showAll"); 
	showAll.style.visibility = "hidden";
	var htmls=html.split(":");
	$("#shortname").val(html);
	$("#trademark").val(htmls[0]);
	$("#specification").val(htmls[1]);
	$("#unit").val(htmls[2]);
}
function getGyPeople(type){
		$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_getGyPeople.action?tag="+type,
				dataType : "json",
				success : function(data) {
					$(data).each(
									function() {
										if(type=="bz"){
										    $("#bianzhi")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="jd"){
											$("#jiaodui")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="sh"){
											$("#shenhe")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="pz"){
											$("#pizhun")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
											
										}
									});
				}
			});
}

$(document).ready(function(){
	getGyPeople("bz");
	getGyPeople("jd");
	getGyPeople("sh");
	getGyPeople("pz");
})

		</SCRIPT>
	</body>
</html>
