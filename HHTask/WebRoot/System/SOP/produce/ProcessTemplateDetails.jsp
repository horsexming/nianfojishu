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
		<style  type="text/css">
		.innerTb{
		border-top: 0px;border-left: 0px
		}
		</style >
	</head>
	<body>
		<center>
			<input type="hidden" id="sparePartsId" name="sparePartsId" value='0'>
			<form action="ProcardTemplateAction!updateProcessT.action"
				method="post" onsubmit="return checkForm();">
				<input type="hidden" name="processTemplate.id"
					id="processTemplateid" value="${processTemplate.id}">
				<input type="hidden" name="processTemplate.parallelId"
					value="${processTemplate.parallelId}">
				<h2>
					工序信息
				</h2>
				<div align="center" style="color: red;">
					${successMessage}
				</div>
				<table class="table" id="tb">
					<tr>
						<th align="right">
							工序号:
						</th>
						<td>
							<input type="text" name="processTemplate.processNO"
								value="${processTemplate.processNO}" />
						</td>
						<th align="right">
							工序名称:
						</th>
						<td>
							<select id="processName" name="processTemplate.processName"
								style="width: 150px;" onchange="getMachineBygongxu();">
								<option value="${processTemplate.processName}">
									${processTemplate.processName}
								</option>
							</select>
							<!--    "
							<input type="text" name="processTemplate.processName"
								value="${processTemplate.processName}" /> -->
						</td>
						<th align="right">
							生产类型:
						</th>
						<td>
							<select name="processTemplate.productStyle">
								<option value="${processTemplate.productStyle}">
									${processTemplate.productStyle}
								</option>
								<option>
									自制
								</option>
								<option>
									外委
								</option>
							</select>
						</td>

					</tr>
					<tr>
						<th align="right">
							是否首检:
						</th>
						<td>
							<select id="processStatus" name="processTemplate.zjStatus">
								<option value="${processTemplate.zjStatus}">
									${processTemplate.zjStatus}
								</option>
								<option value="no">
									no
								</option>
								<option value="yes">
									yes
								</option>
							</select>
						</td>
						<th align="right">
							是否并行:
						</th>
						<td>
							<select id="processStatus" name="processTemplate.processStatus">
								<option value="${processTemplate.processStatus}">
									<s:if test="processTemplate.processStatus=='yes'">
										并行
									</s:if>
									<s:else>不并行</s:else>
								</option>
								<option value="no">
									不并行
								</option>
								<option value="yes">
									并行
								</option>
							</select>
						</td>


						<th align="right">
							奖金分配:
						</th>
						<td>
							<select name="processTemplate.isPrice">
								<option value="${processTemplate.isPrice}">
									<s:if test="processTemplate.isPrice=='yes'">
										参与
									</s:if>
									<s:else>不参与</s:else>
								</option>
								<option value="yes">
									参与
								</option>
								<option value="no">
									不参与
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right">
							考勤验证:
						</th>
						<td>
							<select name="processTemplate.kaoqingstatus">
								<s:if test="processTemplate.gongzhuangstatus!=null">
									<option value="${processTemplate.kaoqingstatus}">
										${processTemplate.kaoqingstatus}
									</option>
								</s:if>
								<option value="是">
									是
								</option>
								<option value="否">
									否
								</option>
							</select>
						</td>
						<th align="right">
							规范验证:
						</th>
						<td>
							<select name="processTemplate.guifanstatus">
								<s:if test="processTemplate.gongzhuangstatus!=null">
									<option value="${processTemplate.guifanstatus}">
										${processTemplate.guifanstatus}
									</option>
								</s:if>
								<option value="是">
									是
								</option>
								<option value="否">
									否
								</option>
							</select>
						</td>
						<th align="right">
							精益计算:
						</th>
						<td>
							<select name="processTemplate.isJisuan">
								<s:if
									test="processTemplate.isJisuan!=null&&processTemplate.isJisuan=='no'">
									<option value="no">
										否
									</option>
									<option value="yes">
										是
									</option>
								</s:if>
								<s:else>
									<option value="yes">
										是
									</option>
									<option value="no">
										否
									</option>
								</s:else>
							</select>
						</td>
					</tr>
					<tr>

						<th align="right">
							工装验证:
						</th>
						<td>
							<select name="processTemplate.gongzhuangstatus">
								<s:if test="processTemplate.gongzhuangstatus!=null">
									<option value="${processTemplate.gongzhuangstatus}">
										${processTemplate.gongzhuangstatus}
									</option>
								</s:if>
								<option value="是">
									是
								</option>
								<option value="否">
									否
								</option>
							</select>
						</td>
						<th align="right">
							工装:
						</th>
						<td colspan="3">
							<div id="showAll1"
								style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1; width: 200px">
							</div>
							<input type="text" id="shortname1" onkeyup="getAllNames1()"
								style="height: 20px; width: 200px" onFocus="init1()"
								onBlur="hidediv1()" name="markId1"
								value="${processTemplate.number}${processTemplate.matetag}" />
							<font style="color: red;">*根据名称模糊查工装</font>

							<!-- 12221333444444442333344555
							<select id="gongzhuang" name="processTemplateshebeiId"
								style="width: 155px;" onchange="ji(this.value);"></select>
								 -->
							<input id="gid" name="processTemplate.gzstoreId" type="hidden"
								value="${processTemplate.gzstoreId}" />
							<input id="number" name="processTemplate.number" type="hidden"
								value="${processTemplate.number}" />
							<input id="matetag" name="processTemplate.matetag" type="hidden"
								value="${processTemplate.matetag}" />

						</td>
					</tr>
					<tr>
						<th align="right">
							量具验证:
						</th>
						<td>
							<select name="processTemplate.liangjustatus">
								<s:if test="processTemplate.gongzhuangstatus!=null">
									<option value="${processTemplate.liangjustatus}">
										${processTemplate.liangjustatus}
									</option>
								</s:if>
								<option value="是">
									是
								</option>
								<option value="否">
									否
								</option>
							</select>
						</td>

						<th align="right">
							量具:
						</th>
						<td colspan="3">

							<div id="showAll"
								style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1; width: 200px">
							</div>
							<input type="text" id="shortname" onkeyup="getAllNames()"
								style="height: 20px; width: 200px" onFocus="initlj()"
								onBlur="hidedivlj()" name="markId"
								value="${processTemplate.measuringMatetag}${processTemplate.measuring_no}" />

							<font style="color: red;">*根据名称模糊查量具</font>
							<input id="measuringId" name="processTemplate.measuringId"
								type="hidden" value="${processTemplate.measuringId}" />
							<input id="measuringMatetag"
								name="processTemplate.measuringMatetag" type="hidden"
								value="${processTemplate.measuringMatetag}" />
							<input id="measuring_no" name="processTemplate.measuring_no"
								value="${processTemplate.measuring_no}" type="hidden" />
						</td>

					</tr>
					<tr style="display: none">
						<th align="right">
							部门
						</th>
						<td align="left">
							<select name="processTemplate.operatorDept" id="dept"
								style="width: 100px; float: left;">
								<option value="${processTemplate.operatorDept}">
									${processTemplate.operatorDept}
								</option>
							</select>
						</td>
						<th align="right">
							操作者:
						</th>
						<td align="left">
							<input id="userId" name="processTemplate.operatorUserId"
								type="hidden" />
							<input id="userName" name="processTemplate.operatorName"
								value="${processTemplate.operatorName}" type="hidden" />
							<select id="users" style="width: 100px; float: left;">
								<option value="${processTemplate.operatorName}">
									${processTemplate.operatorName}
								</option>
							</select>
						</td>
						<th align="right">
							操作人工号:
						</th>
						<td>
							<input type="text" id="code" name="processTemplate.operatorCode"
								value="${processTemplate.operatorCode}" />
							<input id="cardId" name="processTemplate.operatorCardId"
								value="${processTemplate.operatorCardId}" type="hidden" />
						</td>
					</tr>
					<tr>
						<th align="right">
							设备验证:
						</th>
						<td>
							<select name="processTemplate.shebeistatus">
								<s:if test="processTemplate.gongzhuangstatus!=null">
									<option value="${processTemplate.shebeistatus}">
										${processTemplate.shebeistatus}
									</option>
								</s:if>
								<option value="是">
									是
								</option>
								<option value="否">
									否
								</option>
							</select>
						</td>
						<th align="right">
							设备编号:
						</th>
						<td colspan="3">
							<select id="shebeiNo" name="processTemplate.shebeiNo"
								style="width: 200px;" onchange="getGongweiAndOth();">
								<s:if test="shebeiNo!=''">
									<option value="${processTemplate.shebeiNo}">
										${processTemplate.shebeiNo}${processTemplate.gongwei}${processTemplate.shebeiName}
									</option>
								</s:if>
								<option></option>
							</select>
							<a href="processGzstoreAction_findGX.action" target="_getM">找不到设备?前往绑定</a>
						</td>
					</tr>
					<tr>
						<th align="right">
							工位号:
						</th>
						<td>
							<select name="processTemplate.gongwei" id="gongwei"
								style="width: 155px;">
								<!--  onchange="getshebeiCode(this)" -->
								<option value="${processTemplate.gongwei}">
									${processTemplate.gongwei}
								</option>
							</select>
						</td>
						<th align="right">
							设备名称:
						</th>
						<td>
							<input type="text" name="processTemplate.shebeiName"
								id="shebeiName" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${processTemplate.shebeiName}" />
						</td>
						<th>是否转存</th>
						<td><select name="processTemplate.needSave" class="yesOrNo" id="needSave">
							<option value="${processTemplate.needSave}">
									${processTemplate.needSave}
								</option>
								<option value="否">
									否
								</option>
								<option value="是">
									是
								</option>
							</select>
						</td>
					</tr>

					<tr>
						<td colspan="6" align="center">
							<b>操作过程</b>
						</td>
					</tr>
					<tr>
						<th align="right">
							人工节拍:
						</th>
						<td>
							<input type="text" name="processTemplate.opcaozuojiepai"
								value="${processTemplate.opcaozuojiepai}" />
							(s)
						</td>
						<th align="right">
							设备节拍:
						</th>
						<td>
							<input id="opshebeijiepai" type="text"
								name="processTemplate.opshebeijiepai"
								value="${processTemplate.opshebeijiepai}" />
							(s)
						</td>
						<th align="right">
							&nbsp;
						</th>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<th align="right">
							负荷指数:
						</th>
						<td>
							<input type="text" name="processTemplate.opfuheRate"
								value="${processTemplate.opfuheRate}" />
						</td>
						<th align="right">
							技能指数:
						</th>
						<td>
							<input type="text" name="processTemplate.optechnologyRate"
								id="optechnologyRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${processTemplate.optechnologyRate}" />
						</td>
						<th align="right">
							可替换人数:
						</th>
						<td>
							<input type="text" name="processTemplate.opCouldReplaceRate"
								id="opCouldReplaceRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${processTemplate.opCouldReplaceRate}" />
						</td>

					</tr>
					<tr>
						<td colspan="6" align="center">
							<b>准备过程</b>
						</td>
					</tr>
					<tr>
						<th align="right">
							人工节拍:
						</th>
						<td>
							<input type="text" name="processTemplate.gzzhunbeijiepai"
								value="${processTemplate.gzzhunbeijiepai}" />
							(s)
						</td>
						<th align="right">
							准备次数:
						</th>
						<td>
							<input type="text" name="processTemplate.gzzhunbeicishu"
								value="${processTemplate.gzzhunbeicishu}" />
						</td>
						<th align="right">
							&nbsp;
						</th>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<th align="right">
							负荷指数:
						</th>
						<td>
							<input type="text" name="processTemplate.gzfuheRate"
								id="gzfuheRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${processTemplate.gzfuheRate}" />
						</td>
						<th align="right">
							技能指数:
						</th>
						<td>
							<input type="text" name="processTemplate.gztechnologyRate"
								id="gztechnologyRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${processTemplate.gztechnologyRate}" />
						</td>
						<th align="right">
							可替换人数:
						</th>
						<td>
							<input type="text" name="processTemplate.gzCouldReplaceRate"
								id="gzCouldReplaceRate" readOnly="readonly"
								style="background-color: #cccccc;"
								value="${processTemplate.gzCouldReplaceRate}" />
						</td>

					</tr>
					<tr>
						<th>
							配送时长
						</th>
						<td>
							<input name="processTemplate.deliveryDuration"
								value="${processTemplate.deliveryDuration}">
							(h)
						</td>
						<th>
							固定节拍
						</th>
						<td>
							<select name="processTemplate.guding">
								<option value="${processTemplate.guding}">
									${processTemplate.guding}
								</option>
								<option value="是">
									是
								</option>
								<option value="否">
									否
								</option>
							</select>
						</td>
					</tr>
					<tr >
					 <td colspan="6" align="center">是否需要辅料
					 <s:if test="processTemplate.isNeedFuliao=='yes'">
					  <input type="radio" name='processTemplate.isNeedFuliao'  value="yes" checked="checked" onchange="changeFuliao('是')">是
					  <input type="radio" name='processTemplate.isNeedFuliao'  value="no" onchange="changeFuliao('否')"> 否
					 </s:if>
					 <s:else>
					  <input type="radio" name='processTemplate.isNeedFuliao'  value="yes" onchange="changeFuliao('是')">是
					  <input type="radio" name='processTemplate.isNeedFuliao'  value="no" checked="checked" onchange="changeFuliao('否')"> 否
					 </s:else>
					 </td>
					</tr>
					<tr id="fuliaoTr">
					<td colspan="6" align="center">
					<s:if test="processTemplate.isNeedFuliao=='yes'">
					 <table id="fuliaoTb" >
					 </s:if>
					 <s:else>
					 <table id="fuliaoTb" style="display: none;">
					 </s:else>
					 <tr >
					  <td style="border-top: 0px;border-left: 0px" align="center">类别
					  </td>
					  <td style="border-top: 0px;border-left: 0px" align="center">名称
					  </td>
					  <td style="border-top: 0px;border-left: 0px" align="center">规格
					  </td>
					  <td style="border-top: 0px;border-left: 0px" align="center">单位
					  </td>
					  <td style="border-top: 0px;border-left: 0px" align="center">权值比(工序:辅料) 
					  </td>
					  <td style="border-top: 0px;border-left: 0px;border-right: 0px" align="center"><input type="button" value="增加" onclick="addFuliaoLine()"> 
					  </td>
					 </tr>
					 <s:iterator value="processTemplate.processFuLiaoTemplate" id="pagefuliao" status="index">
					  <tr id='fuliaoTr<s:property value="#index.index"/>'>
					  <td style="border-top: 0px;border-left: 0px" align="center">
					  <input type="hidden" name="processTemplate.fuliaoList[<s:property value="#index.index"/>].id" value="<s:property value="#pagefuliao.id"/>">
					  <SELECT id="type<s:property value="#index.index"/>" name="processTemplate.fuliaoList[<s:property value="#index.index"/>].type">
					  <option ><s:property value="#pagefuliao.type"/></option>
					  <option >备件</option><option >金属五交材料</option><option>工具</option><option>办公用品</option><option>杂品</option><option>金属五交</option><option>工装</option><option>五金</option><option>包装物</option>
					  </SELECT>
					  <td style="border-top: 0px;border-left: 0px" align="center">
					        <div id="showAllfl<s:property value="#index.index"/>"
								style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
							</div>
							<input type="text" id="shortnamefl<s:property value="#index.index"/>" onkeyup="getAllNamefls(<s:property value="#index.index"/>)"
								style="height: 20px;" onFocus="init('shortnamefl<s:property value="#index.index"/>','showAllfl<s:property value="#index.index"/>',<s:property value="#index.index"/>)"
								onBlur="hidediv('showAllfl<s:property value="#index.index"/>')" name="processTemplate.fuliaoList[<s:property value="#index.index"/>].name" value="<s:property value="#pagefuliao.name"/>"/>
					  </td>
					  <td style="border-top: 0px;border-left: 0px" align="center"><input id="specification<s:property value="#index.index"/>" name="processTemplate.fuliaoList[<s:property value="#index.index"/>].specification" value="<s:property value="#pagefuliao.specification"/>"> </td>
					  
					  <td style="border-top: 0px;border-left: 0px" align="center"><input id="unit<s:property value="#index.index"/>" name="processTemplate.fuliaoList[<s:property value="#index.index"/>].unit" value="<s:property value="#pagefuliao.unit"/>"> </td>
					 
					  <td style="border-top: 0px;border-left: 0px" align="center"><input id="quanzhi1<s:property value="#index.index"/>" name="processTemplate.fuliaoList[<s:property value="#index.index"/>].quanzhi1" style="width: 80" value="<s:property value="#pagefuliao.quanzhi1"/>"> 
					  :<input id="quanzhi2<s:property value="#index.index"/>" name="processTemplate.fuliaoList[<s:property value="#index.index"/>].quanzhi2" style="width: 80" value="<s:property value="#pagefuliao.quanzhi2"/>"></td>
					  <td style="border-top: 0px;border-left: 0px;border-right: 0px" align="center"><input type="button" value="删除" onclick="deleteFuliaoLine(<s:property value="#index.index"/>)">  
					  </td>
					  </tr>
					  </s:iterator>
					 </table>
					</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="submit" class="input" value="修改" />
							&nbsp;
							<input type="reset" class="input" value="取消">

						</td>
					</tr>
				</table>
			</form>
		</center>
		<script type="text/javascript">
//设备编号
function ji(names) {
	var arrys = names.split(",");
	document.getElementById("gid").value = arrys[0];
	document.getElementById("number").value = arrys[1];
	document.getElementById("matetag").value = arrys[2];

}
/* $(function(){
 var pid=document.getElementById("processTemplateid").value;
 //下拉工位编号
 $.ajax( {
 type : "POST",
 url : "ProcardTemplateAction!listgongzhuang.action",
 data: {processTemplate.id:pid},
 dataType : "json",
 success : function(object) { 
 var bj = "<option></option>";
 $.each(object, function(i, obj) {
 bj += "<option value='"+ obj.id+ "'>" + obj.matetag +"</option>";
 });
 $(bj).appendTo("#gongzhuang")
 }
 });
 })*/
//查询工位信息
$(function() {
	var count = 3;
	$('#addTrBtn').bind('click', function() {
		var b = $('#tb tr').eq(count).clone();
		$('#tb tr').eq(count).after(b);
		count++;
	});

	//--------------
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!listgongzhuang.action",
		dataType : "json",
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='" + [ obj.id, obj.number, obj.matetag ]
						+ "'>" + obj.number + "           " + obj.matetag
						+ "</option>";
			});
			$(bj).appendTo("#gongzhuang");
		}
	});
	//-------------
	//------------------------------------------

	//查询所有的部门
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			$("<option></option>").appendTo("#dept");
			$(allDdept).each(
					function() {
						$(
								"<option value='" + this.dept + "'>"
										+ this.dept + "</option>").appendTo(
								"#dept");
					});
		}

	});
});

//根据工位查询设备编号
function getshebeiCode(obj) {
	var gongwei = document.getElementById("gongwei").value;
	var select = document.getElementById("shebeiCode");
	select.options.length = 1;
	if (null != gongwei && "" != gongwei) {
		createDept("shebeiCode",
				"productPriceAction!findShebeiCode.action?tag=" + gongwei);
	}
}

//查询工位对象和交付量
function getGongweiAndOth(isload) {

	//工位和设备编号
	//var gongwei = document.getElementById("processName").value;
	var shebeicode = document.getElementById("shebeiNo").value;
	//----------------------------------------------------------------
	if (shebeicode != "") {
		$
				.ajax( {
					type : "POST",
					url : "productPriceAction!getGongwei.action",
					data : "shebeiCode=" + shebeicode,
					success : function(msg) {
						var d = $.parseJSON(msg);
						//赋值
						$("#gongwei").empty();
						$(
								"<option value='" + d.gongweihao + "'>"
										+ d.gongweihao + "</option>").appendTo(
								"#gongwei");
						if(d.shebeiName!=null&&d.shebeiName.length>0&&d.shebeiName!="null"){
						document.getElementById("shebeiName").value = d.shebeiName;
						}else{
						document.getElementById("shebeiName").value = "";
						}
						if(d.caozJineng!=null&&d.caozJineng.length>0&&d.caozJineng!="null"){
						document.getElementById("optechnologyRate").value = d.caozJineng;
						}else{
						document.getElementById("optechnologyRate").value = "";
						}
						if(d.caoztihuanrenshu!=null&&d.caoztihuanrenshu.length>0&&d.caoztihuanrenshu!="null"){
						document.getElementById("opCouldReplaceRate").value = d.caoztihuanrenshu;
						}else{
						document.getElementById("opCouldReplaceRate").value = "";
						}
						if(d.gongzhuangFuhe!=null&&d.gongzhuangFuhe.length>0&&d.gongzhuangFuhe!="null"){
						document.getElementById("gzfuheRate").value = d.gongzhuangFuhe;
						}else{
						document.getElementById("gzfuheRate").value = "";
						}
						if(d.gongzhuangJineng!=null&&d.gongzhuangJineng.length>0&&d.gongzhuangJineng!="null"){
						document.getElementById("gztechnologyRate").value = d.gongzhuangJineng;
						}else{
						document.getElementById("gztechnologyRate").value = "";
						}
						if(d.gongzhuangtihuanrenshu!=null&&d.gongzhuangtihuanrenshu.length>0&&d.gongzhuangtihuanrenshu!="null"){
						document.getElementById("gzCouldReplaceRate").value = d.gongzhuangtihuanrenshu;
						}else{
						document.getElementById("gzCouldReplaceRate").value = "";
						}
					}
				});
	} else {
		document.getElementById("shebeiName").value = "";
		document.getElementById("optechnologyRate").value = "";
		document.getElementById("opCouldReplaceRate").value = "";
		document.getElementById("gzfuheRate").value = "";
		document.getElementById("gztechnologyRate").value = "";
		document.getElementById("gzCouldReplaceRate").value = "";
		//-------------------------下拉工位号---------------------------------------
		//查询所有工位
		//  createDept('gongwei', 'productPriceAction!findGongwei.action?');
		$.ajax( {
			type : "POST",
			cache : false,//防止数据缓存
			url : "productPriceAction!findGongwei.action",
			data : "tag=" + shebeicode,
			dataType : "json",
			success : function(object) {
				if (isload != 'yes') {
					$("#gongwei").empty();
				}
				var bj = "<option></option>";
				$.each(object, function(i, obj) {
					bj += "<option value='" + obj.gongweihao + "'>"
							+ obj.gongweihao + "</option>";
				});
				$(bj).appendTo("#gongwei");
			}
		});
	}
}

//级联查询出部门所对应的所有人员
$("#dept")
		.bind(
				"change",
				function() {
					if ($("#dept").val() != "") {
						$
								.ajax( {
									url : "UsersAction!findUsersByDept.action",
									type : 'post',
									dataType : 'json',
									cache : false,//防止数据缓存
									contentType : "application/x-www-form-urlencoded; charset=utf-8",
									data : {
										deptName : $("#dept").val()
									},
									success : function(useradsfa) {
										$("#users").empty();//清空
										$("<option></option>").appendTo(
												"#users");
										$(useradsfa)
												.each(
														function() {
															$(
																	"<option value='"
																			+ this.code
																			+ "|"
																			+ this.name
																			+ "|"
																			+ this.id
																			+ "|"
																			+ this.cardId
																			+ "'>"
																			+ this.name
																			+ "</option>")
																	.appendTo(
																			"#users")
														});
										$("#users")
												.bind(
														"change",
														function() {
															var user = $(
																	"#users")
																	.val();
															var userCodeName = user
																	.split("|");
															if (userCodeName != "") {
																$("#code")
																		.val(
																				userCodeName[0]);
																$("#userName")
																		.val(
																				userCodeName[1]);
																$("#userId")
																		.val(
																				userCodeName[2]);
																$("#cardId")
																		.val(
																				userCodeName[3]);
															} else {
																$("#code").val(
																		"");
																$("#userName")
																		.val("");
															}
														})
									},
									error : function() {
										alert("服务器异常!");
									}
								});
					}

				});

//--------------------------量具------------------------------------------
var count = 0;
function ondiv(obj) {
	count++;
	obj.style.background = "gray";
};
function outdiv(obj) {
	obj.style.background = "#ffffff";
	hidediv()

}
function hidedivlj() {
	count--;
	if (count == 0) {
		var showAll = document.getElementById("showAll");
		showAll.style.visibility = "hidden";
	}

}
function initlj() {
	count++;
	var shortname = document.getElementById("shortname");
	var showAll = document.getElementById("showAll");

	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	if (shortname.value != "") {
		getAllNames();
	}
	showAll.style.visibility = "visible";
}
//获取元素的纵坐标（相对于窗口）
function getTop(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += getTop(e.offsetParent);
	return offset;
}
//获取元素的横坐标（相对于窗口）
function getLeft(e) {
	var offset = e.offsetLeft;
	if (e.offsetParent != null)
		offset += getLeft(e.offsetParent);
	return offset;
}
function selectdiv(obj) {
	var shortname = document.getElementById("shortname");
	divvalue = obj.innerHTML;
	shortname.value = divvalue;
	document.getElementById("measuringId").value = divvalue.split(",")[0];
	document.getElementById("measuringNumber").value = divvalue.split(",")[1];
	document.getElementById("measuringMatetag").value = divvalue.split(",")[2];
	document.getElementById("measuring_no").value = divvalue.split(",")[3];

	var showAll = document.getElementById("showAll");
	showAll.style.visibility = "hidden";
}
function selectdiv(obj, shortnameId, showAllId) {
	var shortname = document.getElementById("shortname");
	divvalue = $(obj).find("span").html();
	shortname.value = divvalue.replace(divvalue.split(",")[0] + ",", '');

	document.getElementById("measuringId").value = divvalue.split(",")[0];
	document.getElementById("measuringMatetag").value = divvalue.split(",")[1];
	document.getElementById("measuring_no").value = divvalue.split(",")[2];

	var showAll = document.getElementById("showAll");
	showAll.style.visibility = "hidden";
}

//ajax获取所有的类似的全称
function getAllNames() {
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!listliangju.action",
				dataType : "json",
				data : {
					markId : $("#shortname").val()
				},
				success : function(data) {
					$("#showAll").empty();
					$(data)
							.each(
									function() {
										var markid = $(this)
												.attr('matetag')
												.replace(
														$("#shortname").val(),
														"<font color='red'>"
																+ $(
																		"#shortname")
																		.val()
																+ "</font>");
										$("#showAll")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)'>"
																+ "<span style='display:none'>"
																+ $(this).attr(
																		'id')
																+ ","
																+ $(this)
																		.attr(
																				'matetag')
																+ ","
																+ $(this)
																		.attr(
																				'measuring_no')
																+ ","
																+ $(this)
																		.attr('format')
																+ "</span>"
																+ $(this)
																		.attr(
																				'number')
																+ ","
																+ $(this)
																		.attr(
																				'matetag')
																+ ","
																+ $(this)
																		.attr(
																				'measuring_no')
																+ ","
																+ $(this)
																		.attr('format')
																+ "</div>");
									});
				}
			});
}
//-------------------------------------模糊下拉工装  1---------------------------------

var count1 = 0;
function ondiv1(obj) {
	count1++;
	obj.style.background = "gray";
};
function outdiv1(obj) {
	obj.style.background = "#ffffff";
	hidediv()

}
function hidediv1() {
	count1--;
	if (count1 == 0) {
		var showAll1 = document.getElementById("showAll1");
		showAll1.style.visibility = "hidden";
	}

}
function init1() {
	count1++;
	var shortname1 = document.getElementById("shortname1");
	var showAll1 = document.getElementById("showAll1");

	showAll1.style.top = getTop(shortname1) + 20;
	showAll1.style.left = getLeft(shortname1) - 10;
	if (shortname1.value != "") {
		getAllNames();
	}
	showAll1.style.visibility = "visible";
}
//获取元素的纵坐标（相对于窗口）
function getTop1(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += getTop(e.offsetParent);
	return offset;
}
//获取元素的横坐标（相对于窗口）
function getLeft1(e) {
	var offset = e.offsetLeft;
	if (e.offsetParent != null)
		offset += getLeft(e.offsetParent);
	return offset;
}
/*
 function selectdiv1(obj) {
 var shortname1 = document.getElementById("shortname1");
 divvalue = obj.innerHTML;
 shortname1.value = divvalue;
 document.getElementById("gid").value = divvalue.split(",")[0];
 document.getElementById("number").value = divvalue.split(",")[1];
 document.getElementById("matetag").value = divvalue.split(",")[2];

 var showAll1 = document.getElementById("showAll1");
 showAll1.style.visibility = "hidden";
 }
 */
function selectdiv1(obj, shortnameId, showAllId) {
	var shortname1 = document.getElementById("shortname1");
	divvalue = $(obj).find("span").html();
	shortname1.value = divvalue.replace(divvalue.split(",")[0] + ",", '');

	document.getElementById("gid").value = divvalue.split(",")[0];
	document.getElementById("number").value = divvalue.split(",")[2];
	document.getElementById("matetag").value = divvalue.split(",")[1];

	var showAll1 = document.getElementById("showAll1");
	showAll1.style.visibility = "hidden";
}
//ajax获取所有的类似的全称
function getAllNames1() {
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!listgongzhuang.action",
				dataType : "json",
				data : {
					markId : $("#shortname1").val()
				},
				success : function(data) {
					$("#showAll1").empty();
					$(data)
							.each(
									function() {
										var markid = $(this)
												.attr('matetag')
												.replace(
														$("#shortname1").val(),
														"<font color='red'>"
																+ $(
																		"#shortname1")
																		.val()
																+ "</font>");
										$("#showAll1")
												.append(
														"<div onmouseover='ondiv1(this)' onmouseout='outdiv1(this)' onclick='selectdiv1(this)'>"
																+ "<span style='display:none'>"
																+ $(this).attr(
																		'id')
																+ ","
																+ $(this)
																		.attr(
																				'number')
																+ ","
																+ $(this)
																		.attr(
																				'matetag')
																+ ",</span>"
																+ $(this)
																		.attr(
																				'number')
																+ ","
																+ $(this)
																		.attr(
																				'matetag')
																+ "</div>");
									});
				}
			});
}

//-----------------------------------------------------------------------
//查询所有工序
function getProcess() {
	$.ajax( {
		type : "POST",
		url : "processGzstoreAction_getProcessGzstoreListAllForSelect.action",
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				//$("#processName").empty();
				$("#processName").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#processName").append(
							"<option value='" + n.processName + "'>"
									+ n.processName + "</option>");
				});
				$("#processName").tinyselect();
			} else {
				alert(msg.message);
			}
		}
	});
}
//通过工序查询设备信息
function getMachineBygongxu(isload) {
var processName = $("#processName").val();
	$.ajax( {
		type : "POST",
		url : "GzstoreAction!getMachineBygongxu.action",
		dataType : "json",
		data : {
			matetag : processName
		},
		success : function(object) {
			if (isload != 'yes') {
				$("#shebeiNo").empty();
			}
			var bj = "";
			$.each(object, function(i, obj) {
				bj += "<option value='" + obj.no + "'>" + obj.no + "、"
						+ obj.workPosition + "、" + obj.name + "</option>";
			});
			$(bj).appendTo("#shebeiNo")
		}
	});

}
//通过工序名称查询设备
function getMachine() {
	var processName = $("#processName").val();
	if (processName != "") {
		$.ajax( {
			type : "POST",
			url : "GzstoreAction!getMachineListByGongxuName.action",
			dataType : "json",
			data : {
				processName : processName
			},
			success : function(msg) {
				if (msg.success) {
					$("#shebeiNo").empty();
					$("#shebeiNo").append("<option></option>");
					$.each(msg.data, function(i, n) {
						$("#shebeiNo").append(
								"<option value='" + n.no + "'>" + n.no + "("
										+ n.name + ")" + "</option>");
					});
				} else {
					alert(msg.message);
				}
			}
		});
	} else {
		$("#shebeiNo").empty();
	}
}

//调用
getProcess();
getMachineBygongxu('yes');
getGongweiAndOth('yes');
//getMachine();
//--------------------------------------------辅料--------------------------------------
function changeFuliao(obj){
	if(obj=='是'){
		$("#fuliaoTb").show();
	}else{
		$("#fuliaoTb").hide();
	}
}



var fuliaoSize='<s:property value="processTemplate.processFuLiaoTemplate.size()"/>';
var maxFuLiaoIndex=fuliaoSize-1;//辅料最大下标
function addFuliaoLine(){
	maxFuLiaoIndex++;
	var html="<tr id='fuliaoTr"+maxFuLiaoIndex+"'><td style='border-top: 0px;border-left: 0px' align='center'>" +
	"<SELECT id='type"+maxFuLiaoIndex+"' name='processTemplate.fuliaoList["+maxFuLiaoIndex+"].type'><option>请选择</option><option >备件</option><option >金属五交材料</option><option>工具</option><option>办公用品</option><option>杂品</option><option>金属五交</option><option>工装</option><option>五金</option><option>包装物</option></SELECT> </td>"+
	          "<td style='border-top: 0px;border-left: 0px' align='center'> <div id='showAllfl"+maxFuLiaoIndex+"' style='background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;'> </div>" +
	          "<input type='text' id='shortnamefl"+maxFuLiaoIndex+"' onkeyup='getAllNamefls("+maxFuLiaoIndex+")' style='height: 20px;' onFocus='init(\"shortnamefl"+maxFuLiaoIndex+"\",\"showAllfl"+maxFuLiaoIndex+"\","+maxFuLiaoIndex+")' onBlur='hidediv(\"showAllfl"+maxFuLiaoIndex+"\")' name='processTemplate.fuliaoList["+maxFuLiaoIndex+"].name' /> </td>"+
	          "<td style='border-top: 0px;border-left: 0px' align='center'><input id='specification"+maxFuLiaoIndex+"' name='processTemplate.fuliaoList["+maxFuLiaoIndex+"].specification'> </td>"+
	          "<td style='border-top: 0px;border-left: 0px' align='center'><input id='unit"+maxFuLiaoIndex+"' name='processTemplate.fuliaoList["+maxFuLiaoIndex+"].unit'> </td>"+
	          "<td style='border-top: 0px;border-left: 0px' align='center'><input id='quanzhi1"+maxFuLiaoIndex+"' name='processTemplate.fuliaoList["+maxFuLiaoIndex+"].quanzhi1' style='width: 80'>"+
	                   ":<input id='quanzhi2"+maxFuLiaoIndex+"' name='processTemplate.fuliaoList["+maxFuLiaoIndex+"].quanzhi2' style='width: 80'></td>"+
	          "<td style='border-top: 0px;border-left: 0px;border-right: 0px' align='center'><input type='button' value='删除' onclick='deleteFuliaoLine("+maxFuLiaoIndex+")'> </td>"+
	          "</tr>";
	$("#fuliaoTb>tbody>tr").eq(fuliaoSize).after(html);
	fuliaoSize++;
}

function deleteFuliaoLine(index){
	if(fuliaoSize==1){
		alert("至少一条辅料");
		return;
	}
	if((maxFuLiaoIndex-index)==0){
	 maxFuLiaoIndex--;
	}
	fuliaoSize--;
	$("#fuliaoTr"+index).remove();
}
//--------模糊查询辅料
function getAllNamefls(index) {
	count_seach=1;
	var shortname=$("#shortnamefl"+index).val();
	if(shortname==null||shortname==""){
		return ;
	}
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction_getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.name' : shortname,
					'yuanclAndWaigj.clClass' : '辅料'
				},
				success : function(data) {
					$("#showAllfl"+index).empty();
					$(data)
							.each(
									function() {
										var name=$(this).attr('name').replace($("#shortnamefl"+index).val(),"<font color='red'>"+$("#shortnamefl"+index).val()+"</font>");
										$("#showAllfl"+index)
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this,shortnamefl"+index+")' onclick='selectdivfl(this,"+index+")' align='left'>"
																+ name
																+ ","
																+ $(this).attr('specification')
																+ ","
																+ $(this).attr('unit')
																+ "<span style='display: none;'>"+$(this).attr('name')+","+$(this).attr('specification')+","+$(this).attr('unit')+"</span></div>");
									});
				}
			});
}
//选中工装
function selectdivfl(obj,index){
	var shortname=document.getElementById("shortnamefl"+index);
	var spaniner=$(obj).find("span").html();
	var spaniners=spaniner.split(",");
	shortname.value=spaniners[0];
	var specification=document.getElementById("specification"+index);
	specification.value=spaniners[1];
	var unit=document.getElementById("unit"+index);
	unit.value=spaniners[2];
	var showAll=document.getElementById("showAllfl"+index); 
	showAll.style.visibility = "hidden";
}


</script>
	</body>
</html>
