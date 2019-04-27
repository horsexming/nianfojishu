<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
table {
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	border-collapse: collapse; /* 设置边框状粗细：上 右 下 左 = 对应：1px 0 0 1px */
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}

.tag_1 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_1.jpg');
}

.tag_2 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_2_2.jpg');
}
</style>
		<script type="text/javascript">
var oldObj;
var oldObj2;
$(function() {
	getUnit("danwei_1");
	var strList = '${strList}';
	var nums = "";
	if (strList.indexOf('总成') != -1) {
		nums += "1";
	}
	if (strList.indexOf('外购') != -1) {
		nums += "2";
	}
	if (strList.indexOf('外委') != -1) {
		nums += "3";
	}
	if (strList.indexOf('其他') != -1) {
		nums += "4";
	}
	if (strList.indexOf('磨具') != -1) {
		nums += "5";
	}
	if (strList.indexOf('辅料') != -1) {
		nums += "6";
	}
	for (i = 1; i <= 6; i++) {
		if (nums.indexOf(i + '') == -1) {
			$("#moduletd_" + i).hide();
		}
	}
	if (nums != null && nums.length > 0) {
		for ( var i = 0; i < nums.length; i++) {
			if (i == 0) {
				$("#module" + nums[i]).show();
				$("#module" + nums[i]).attr("class", "tag_2")
				$("#module1_" + nums[i]).show();
				oldObj = $("#module" + nums[i]);
				oldObj2 = nums[i];
				getzhuserOfferById(nums[i]);
				getgys(nums[i],"");
			}
			$("#moduletd_" + nums[i]).show();
			gethtNum(nums[i]);
		}
	}

})

function chageModule(obj, obj2) {
	select(obj2);
	allselect(obj2);
	gethtNum(obj2);
	getUnit("danwei_" + obj2);
	getUnit("bzdanwei_" + obj2);
	getgys(obj2,"");
	if (obj.id != "module1") {
		document.getElementById("module1").className = "tag_1";
		document.getElementById("module1_1").style.display = "none";
	}
	if (oldObj != null) {
		oldObj.className = "tag_1";
		$(oldObj).attr("class", "tag_1")
		document.getElementById("module1_" + oldObj2).style.display = "none";
	}
	obj.className = "tag_2";
	document.getElementById("module1_" + obj2).style.display = "block";

	oldObj = obj;
	oldObj2 = obj2;
}
function changetext(num) {
	var taxRate = document.getElementById("taxRate_" + num);
	if (taxRate != null && taxRate.value == "其他") {
		document.getElementById("taxRateTex_" + num).value = "";
		document.getElementById("taxRateTex_" + num).setAttribute("type",
				"text");
		document.getElementById("taxRateTex_" + num).style.width = "70px";

		//	document.getElementById("taxRateTex_"+num).style.display="block";
	} else {
		var v = document.getElementById("taxRate_" + num).value;
		document.getElementById("taxRateTex_" + num).setAttribute("type",
				"hidden");
		document.getElementById("taxRateTex_" + num).value = v;
	}
	document.getElementById("bhsPrice_" + num).value = "";
	document.getElementById("hsPrice_" + num).value = "";
}

function chagePrice(obj, few) {
	var price = obj.value;
	var tax = document.getElementById("taxRate_" + few).value;
	if (tax == "其他") {
		tax = document.getElementById("taxRateTex_" + few).value;
	}
	var taxvalue = 1 + (tax / 100);
	//alert(taxvalue);
	if (price != null) {
		if (obj.id == "hsPrice_" + few) {
			var otherPrice = (price / taxvalue).toFixed(4);
			document.getElementById("bhsPrice_" + few).value = parseFloat(otherPrice);
		} else if (obj.id == "bhsPrice_" + few) {
			document.getElementById("hsPrice_" + few).value = parseFloat((price * taxvalue)
					.toFixed(4));
		} else if (obj.id == "danweihsPrice_" + few) {
			var otherPrice = (price / taxvalue).toFixed(4);
			document.getElementById("danweibhsPrice_" + few).value = parseFloat(otherPrice);
		} else if (obj.id == "danweibhsPrice_" + few) {
			document.getElementById("danweihsPrice_" + few).value = parseFloat((price * taxvalue)
					.toFixed(4));
		}
	}

}

function isPrice(obj) {
	var ty1 = '^\\d+(\\.\\d+)?$';
	var re = new RegExp(ty1);
	if (obj != null && obj.value.match(re) == null) {
		obj.value = "";
		obj.focus();
		obj.select();
	}

}

var fileDivHTML = "";
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

function checkForm(num) {
	var partNumber = document.getElementById("partNumber" + num);
	var type = document.getElementById("type" + num);
	var contractNumber = document.getElementById("contractNumber" + num);
	var hsPrice = document.getElementById("hsPrice_" + num);
	var bhsPrice = document.getElementById("bhsPrice_" + num);
	var name = document.getElementById("name" + num);
	var fileNumber = document.getElementById("fileNumber" + num);
	var fileButton = document.getElementById("fileButton_" + num);
	var custome = document.getElementById("custome");
	var attachment = document.getElementsByName("attachment");
	var gys = document.getElementById("gys_" + num)
	var bool1 = false;
	var str = "请选择客户";
	if (num == "1" && custome != null && custome.value == "") {
		bool1 = true;
	}

<%--	var bool2 = true;--%>
<%--	if(attachment!=null && attachment.length>0){--%>
<%--		for(var i=0;i<attachment.length ;i++){--%>
<%--			if(attachment[i].value != ""){--%>
<%--				bool2 = false;--%>
<%--			}--%>
<%--		}--%>
<%--	}--%>
	if(gys!=null && gys.value == ""){
		gys.focus();
		$("#zifont").html("请选择供应商");
		return false;
	}
	if (partNumber != null && partNumber.value == "") {
		$("#zifont").html("件号不能为空!");
		partNumber.focus();
		return false;
	} else if (name != null && name.value == "") {
		$("#zifont").html("名称不能为空!");
		name.focus();
		return false;
//	} else if (type != null && type.value == "") {
//		$("#zifont").html("型别不能为空!");
//		type.focus();
//		return false;
	} else if (contractNumber != null && contractNumber.value == "") {
		$("#zifont").html("合同编号(业务编号)不能为空!");
		contractNumber.focus();
		return false;
	} else if (hsPrice != null && hsPrice.value == "") {
		$("#zifont").html("价格(含税)不能为空!");
		hsPrice.focus();
		return false;
	} else if (bhsPrice != null && bhsPrice.value == "") {
		$("#zifont").html("价格(不含税)不能为空!");
		bhsPrice.focus();
		return false;
	} else if(bool1){
		$("#zifont").html(str);
		custome.focus();
		return false;
	}
	document.getElementById("add" + num).disabled = "disabled";
}
function confirmsubmit(num) {
	if (confirm("确定要提交吗")) {
		return checkForm(num);
	} else {
		return false;
	}
}
function date(num) {

	var partNumber;
	var produceType = null;
	if (num == "4") {
		partNumber = document.getElementById("name4").value;

	} else {
		partNumber = document.getElementById("partNumber" + num).value;
		produceType = document.getElementById("produceType" + num).value;
	}
	var productCategory = document.getElementById("productCategory" + num).value;
	var starttime = document.getElementById("starttime" + num).value;
	var	kehuId = document.getElementById("custome").value;
	$(function() {
		$
				.ajax( {
					type : "POST",
					url : "PriceAction!pricetime.action",
					data : {
						'price.partNumber' : partNumber,
						'price.productCategory' : productCategory,
						'price.produceType' : produceType,
						'price.kehuId':kehuId
					},
					dataType : "json",
					success : function(data) {
						var startTime = starttime;
						var endTime = data;
						if ("" == startTime) {
							var date = new Date();
							startTime = date.getFullYear() + "-"
									+ (date.getMonth() + 1) + "-"
									+ date.getDate();
							document.getElementById("starttime" + num).value = startTime;

							if ("" == endTime) {
								document.getElementById("hidden" + num).value = "true";
							} else {
								var arr = endTime.split("-");
								var endtimes = new Date(arr[0], arr[1], arr[2])
										.getTime();
								var startTimes = date.getTime();
								if (startTimes < endtimes) {
									if (confirm("该产品的起始日期超出之前的截止日期，是否将其覆盖")) {
										document.getElementById("hidden" + num).value = "true";
									}
								}
							}

						} else {
							if ("" == endTime) {
								document.getElementById("hidden" + num).value = "true";
								//alert(document.getElementById("hidden"+num).value);
							} else {
								var arr = endTime.split("-");
								var endtimes = new Date(arr[0], arr[1], arr[2])
										.getTime();
								var arrs = startTime.split("-");
								var startTimes = new Date(arrs[0], arrs[1],
										arrs[2]).getTime();
								if (startTimes < endtimes) {
									if (confirm("该产品的起始日期超出之前的截止日期，是否将其覆盖")) {
										document.getElementById("hidden" + num).value = "true";
									}

								}

							}

						}

					}

				});

	});
}

// 判断select选项中 是否存在Value和查出的相同的Item   
function jsSelectIsExitItem(objSelect, objItemValue) {
	var isPresence = false;
	for ( var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].value == objItemValue) {
			isPresence = true;
			break;
		}
	}
	return isPresence;
}
function select(num) {
	var select = document.getElementById("selectName" + num);
	$.ajax( {
		type : "POST",
		url : "jimileixing_finAllDeptNumberForSetlect.action",
		data : {},
		dataType : "json",
		success : function(data) {
			var message = data;
			var type = message.split("|");
			for ( var i = 0; i < type.length - 1; i++) {
				var isPresence = jsSelectIsExitItem(select, type[i])
				if (isPresence == false) {
					var optionItem = new Option(type[i], type[i]);
					select.options.add(optionItem);
				}
			}
		}
	})

	$.ajax( {
		type : "POST",
		url : "AccessEquipmentAction_finAllGuihao.action",
		data : {},
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(data) {
			$("#danganWeizhi" + num).empty();//清空
			$("<option value=''>选择柜号</option>").appendTo("#danganWeizhi" + num)
			$(data).each(
					function() {
						$( 
								"<option value='" + this.id + "|"
										+ this.cabinetNum + "'>"
										+ this.cabinetNum + "</option>")
								.appendTo("#danganWeizhi" + num)
					});
			$("#danganWeizhi" + num).bind("change", function() {
				var name = $("#danganWeizhi" + num).val();
				var guihaoData = name.split("|");
				var ghId = guihaoData[0];
				var ghNum = guihaoData[1];
				$("#daweizhi" + num).val(ghNum);
				$("#daId" + num).val(ghId);
			});
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}

function allselect(num) {
	var select = document.getElementById("danganWeizhi" + num);
}

function writename(obj,num) {
	var partNumber = obj.value;
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!getQuotedPricebymarkId.action",
		data : {
			markId : partNumber,
			pageStatus : 'writename'
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#name" + num).val(data.proName);
				$("#danwei_" + num).empty();
				$("#danwei_" + num).append("<option value='"+data.unit+"'>"+data.unit+"</option>");
				//$("#danwei_" + num).val(data.unit);
				/*$("#danwei_" + num).click(function(){
					//console.log("不可编辑");
					alert("不可编辑");
			        return false;
			    });   */
			}else{
				$("#danwei_" + num).empty();
				getUnit("danwei_"+num);
			}
		}
	})

}


var index = 1;

function addLine(num) {
	var newLine ;
	if(num == "1"){
		newLine = '<tr> <td align=right>总成件号:<br/>(Assembly Number)</td><td><input type="text" id=partNumber'+num+''+index+' name=pricelist1.priceList['+index+'].partNumber  onkeyup="getAllName(&apos;'+num+''+index+'&apos;,&apos;'+num+'&apos;,&apos;'+index+'&apos;) " onblur="writename(this,'+num+');getSpecification(&apos;'+num+'_'+index+'&apos;);hidediv(&apos;showAll'+num+'_'+index+'&apos;)" onFocus="init(&apos;'+num+''+index+'&apos;,&apos;'+index+'&apos;,&apos;'+num+'&apos;)">' +
		'<div id=showAll'+num+'_'+index+' style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;"></div>' +
	'</td> <td align="right">&nbsp;&nbsp;名称<br />(Name):</td> <td> <input type="text" id=name'+num+''+index+' name=pricelist1.priceList['+index+'].name style="width: 240px;"></td>' +
	'<td align="right">型&nbsp;&nbsp;&nbsp;别:<br />(Type)</td><td><input type="text" id=type'+num+''+index+' name=pricelist1.priceList['+index+'].type ></td>' +
	'<td align="right">&nbsp;&nbsp;价格:<br />(Price)</td>' +
	'<td>(税&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;率)<select id=taxRate_'+num+''+index+' onblur="changetext('+num+''+index+')" ><option value="0">0%</option><option value="3">3%</option><option value="5">5%</option>' +
	'<option value="6">6%</option><option value="7">7%</option><option value="10">10%</option><option value="11">11%</option><option value="13" selected="selected">13%</option>' +
	'<option value="16">16%</option><option value="17" >17%</option><option value="其他">其他</option></select>' +
	' <input id=taxRateTex_'+num+''+index+' name=pricelist1.priceList['+index+'].taxprice value="13" type="hidden"  /><br/>' +
	'订货量(从)<input type="text" name=pricelist1.priceList['+index+'].firstnum style="width: 70px;" onkeyup="numyanzheng(this)"  value="0"/><br/>' +
	'订货量(到)<input type="text" name=pricelist1.priceList['+index+'].endnum style="width: 70px;" onkeyup="numyanzheng(this)"  value="0"/><br/>' +
	'(含&nbsp;税&nbsp;&nbsp;价&nbsp;)<input type="text" id=hsPrice_'+num+''+index+' name=pricelist1.priceList['+index+'].hsPrice style="width: 70px;" onkeyup="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)"  onblur="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onchange="isPrice(this);chagePrice(this,&apos;'+num+''+index+'&apos;)" readonly="readonly" ><br/>' +
	'(不含税价)<input type="text" id=bhsPrice_'+num+''+index+' name=pricelist1.priceList['+index+'].bhsPrice style="width: 70px;" onkeyup="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onblur="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onchange="isPrice(this);chagePrice(this,&apos;'+num+''+index+'&apos;)">' +
	'</td><td><Strong>单位:</Strong><select name=pricelist1.priceList['+index+'].danwei id="danwei_'+num+'_'+index+'"><option>PCS</option> <select></td></tr>'
	}else if(num == "2"){
		newLine = '<tr style="border-top:solid #ffa150 2px ; "> ' +
		' <td align=right rowspan="1">外购件号:<br/>(Components Number)</td>' +
		'<td rowspan="1"> <div id=showAll'+num+'_'+index+' style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;"></div>' +
		'<input type="text" id=partNumber'+num+''+index+' name=pricelist1.priceList['+index+'].partNumber  onblur="getAllNames(&apos;'+num+''+index+'&apos;,&apos;'+num+'&apos;,&apos;'+index+'&apos;)"  >' +
		'' +
	'</td> <td align="right">&nbsp;&nbsp;名称<br />(Name):</td> <td> <input type="text" id=name'+num+''+index+' name=pricelist1.priceList['+index+'].name style="width: 240px;"></td>' +
	'<td align="right">规&nbsp;&nbsp;&nbsp;格:<br />(Specification)</td><td><input type="text" name=pricelist1.priceList['+index+'].specification id=specification'+num+''+index+'><div id=div_'+num+''+index+'></div></td>' +
	'<td align="right">供料属性:</td><td><select name=pricelist1.priceList['+index+'].kgliao id=kgliao'+num+''+index+' ><option value="TK">自购(TK)</option><option value="TK AVL">指定供应商(TK AVL)</option><option value="CS">客供(CS)</option><option value="TK Price">完全指定(TK Price)</option></select></td>' +
	'<td><Strong>采购比例:</Strong></td><td ><input type="text" name=pricelist1.priceList['+index+'].cgbl style="width:50px;" ></td></tr><tr >' +
	'	<td align="right">版本号:</td><td><input type="text" name=pricelist1.priceList['+index+'].banbenhao id="banbenhao'+num+''+index+'"> ' +
	'<td align="right">供应商:<br />(Customer name):</td><td><select name=pricelist1.priceList['+index+'].gysId id=gys_'+num+''+index+' style="width: 150px;" onchange="getwlType(this,&apos;'+num+''+index+'&apos;,&apos;'+index+'&apos;)"  ><option  value="">	选择供应商</option><s:iterator id="zh" value="zhuserList" ><option value="${zh.id}">${zh.usercode}_${zh.name}</option></s:iterator></select></td>' +
	'<td align="right">&nbsp;&nbsp;价格:<br />(Price)</td>' +
	'<td><select id=taxRate_'+num+''+index+' onblur="changetext('+num+''+index+')" ><option value="0">0%</option><option value="3">3%</option><option value="5">5%</option>' +
	'<option value="6">6%</option><option value="7">7%</option><option value="10">10%</option><option value="11">11%</option><option value="13" selected="selected">13%</option>' +
	'<option value="16" >16%</option><option value="17" >17%</option><option value="其他">其他</option>' +
	'<input id=taxRateTex_'+num+''+index+' name=pricelist1.priceList['+index+'].taxprice value="13" type="hidden"  />(税率)<br/>' +
	'<input type="text" id=hsPrice_'+num+''+index+' name=pricelist1.priceList['+index+'].hsPrice style="width: 70px;" onkeyup="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;);"  onblur="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onchange="isPrice(this);chagePrice(this,&apos;'+num+''+index+'&apos;)">(含税价)<br/>' +
	'<input type="text" id=bhsPrice_'+num+''+index+' name=pricelist1.priceList['+index+'].bhsPrice style="width: 70px;" onkeyup="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onblur="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onchange="isPrice(this);chagePrice(this,&apos;'+num+''+index+'&apos;)">(不含税价)' +
	'</td><td>起始数量<br/><input type="text" id=firstnum'+num+''+index+' name=pricelist1.priceList['+index+'].firstnum style="width: 70px;" onkeyup="numyanzheng(this); daxiao(&apos;'+num+''+index+'&apos;)"  value="0"/></td>' +
	'<td>截止数量<br/><input type="text"  id=endnum'+num+''+index+' name=pricelist1.priceList['+index+'].endnum style="width: 70px;" onkeyup="numyanzheng(this);daxiao(&apos;'+num+''+index+'&apos;)"  value="0"/></td><td id=wltd_'+num+'_'+index+'></td><td></td></tr>' +
	'<tr><td align="right">最低起订量</td><td><input type="text" value="" name="pricelist1.priceList['+index+'].zdqdl" onchange="numyanzheng(this);" /></td>' +
	'<td align="right">最低装箱量</td><td><input type="text" value="" name="pricelist1.priceList['+index+'].zdzxl" onchange="numyanzheng(this);"  /></td>' +
	'<td align="right">最低起送量</td><td><input type="text" value="" name="pricelist1.priceList['+index+'].zdqsl" onchange="numyanzheng(this);" /></td>' +
	'' +
	'<td align="right">图号</td><td><input type="text" value="" id="picNo_'+num+''+index+'" name="pricelist1.priceList['+index+'].picNo" readonly="readonly"  />' +
	'<td align="right">单位</td><td><input type="text" value="" id="unit_'+num+''+index+'" name="pricelist1.priceList['+index+'].danwei" readonly="readonly" style="width: 75px;" /></tr>'
	}else if(num == "3"){
			newLine = '<tr> ' +
		' <td align=right>外委件号:<br/>(Part Number)</td>' +
		'<td> <div id=showAll'+num+'_'+index+' style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;"></div>' +
		'<input type="text" id=partNumber'+num+''+index+' name=pricelist1.priceList['+index+'].partNumber  onblur="getpriceListby(&apos;'+num+''+index+'&apos;)"  >' +
		'' +
	'</td> <td align="right">&nbsp;&nbsp;名称<br />(Name):</td> <td> <input type="text" id=name'+num+''+index+' name=pricelist1.priceList['+index+'].name ></td>' +
	'<td align="right">规&nbsp;&nbsp;&nbsp;格:<br />(Specification)</td><td><input type="text" name=pricelist1.priceList['+index+'].specification id=specification'+num+''+index+' value =" "><div id=div_'+num+''+index+'></div></td>' +
	'' +
	'<td align="right">工序号(多个以;分隔):</td>' +
	'<td id=gxtd_'+num+''+index+'><input type="text" name=pricelist1.priceList['+index+'].gongxunum id=gongxunum'+num+''+index+'  value ="0"></td>' +
	'<td align="right">单位</td><td><SELECT name="pricelist1.priceList['+index+'].danwei"  id="danwei_'+num+'_'+index+'"></SELECT><br/>(单件)<br/>' +
	'<SELECT name="pricelist1.priceList['+index+'].bzdanwei"  id="bzdanwei_'+num+'_'+index+'"></SELECT><br/>(标准)</td></tr><tr>' +
	'<td align="right">&nbsp;&nbsp;价格:<br />(Price)</td>' +
	'<td><select id=taxRate_'+num+''+index+' onblur="changetext('+num+''+index+')" ><option value="0">0%</option><option value="3">3%</option><option value="5">5%</option>' +
	'<option value="6">6%</option><option value="7">7%</option><option value="10">10%</option><option value="11">11%</option><option value="13" selected="selected">13%</option>' +
	'<option value="16" >16%</option><option value="17" >17%</option><option value="其他">其他</option>' +
	'<input id=taxRateTex_'+num+''+index+' name=pricelist1.priceList['+index+'].taxprice value="13" type="hidden"  />(税率)<br/>' +
	'<input type="text" id=hsPrice_'+num+''+index+' name=pricelist1.priceList['+index+'].hsPrice style="width: 70px;" onkeyup="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)"  onblur="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onchange="isPrice(this);chagePrice(this,&apos;'+num+''+index+'&apos;)">(含税价)<br/>' +
	'<input type="text" id=bhsPrice_'+num+''+index+' name=pricelist1.priceList['+index+'].bhsPrice style="width: 70px;" onkeyup="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onblur="mustBeNumber(&apos;hsPrice_'+num+''+index+'&apos;);chagePrice(this,&apos;'+num+''+index+'&apos;)" onchange="isPrice(this);chagePrice(this,&apos;'+num+''+index+'&apos;)">(不含税价)' +
	'</td><td align="right">起始数量</td><td><input type="text" name=pricelist1.priceList['+index+'].firstnum style="width: 70px;" id=firstnum'+num+''+index+' onkeyup="numyanzheng(this);daxiao(&apos;'+num+''+index+'&apos;)"  value="0"/></td>' +
	'<td align="right">截止数量</td><td><input type="text" name=pricelist1.priceList['+index+'].endnum style="width: 70px;" id=endnum'+num+''+index+'  onkeyup="numyanzheng(this);daxiao(&apos;'+num+''+index+'&apos;)"  value="0"/></td>' +
	'<td align="right" style="width:100px;">单位价格</td><td><input type="text" id="danweihsPrice_'+num+''+index+'" name="pricelist1.priceList['+index+'].danweihsPrice" style="width: 70px;"  onkeyup="mustBeNumber(&apos;danweihsPrice_'+num+''+index+'&apos;);chagePrice(this,'+num+''+index+')"' +
	'onblur="mustBeNumber(&apos;danweihsPrice_'+num+''+index+'&apos;);chagePrice(this,'+num+''+index+')"onchange="isPrice(this);chagePrice(this,'+num+''+index+')">（含税）<br />' +
	' <input type="text" id="danweihsPrice_'+num+''+index+'" onblur="mustBeNumber(&apos;danweibhsPrice_'+num+''+index+'&apos;);chagePrice(this,'+num+''+index+')" onchange="isPrice(this);chagePrice(this,'+num+''+index+')"  style="width: 70px;">（不含税）   </td><td colspan="2"></td></tr>'
	}
	$("#lastTr_"+num).before(newLine);
	$("#gys_"+num+''+index).tinyselect();
	getUnit("danwei_"+num+'_'+index);
	getUnit("bzdanwei_"+num+'_'+index);
	getgys(num,index);
	index++;
}

function delLine(num,tag) {
	var n = $('#mytable_'+num+' tr').length;
if (index == 1) {
	if(tag!="noshow"){
		alert("只剩最后一项了,再删真没了");
	}
		return;
	}
	if(num == 1){
		$($('#mytable_'+num+' tr')[n - 11]).remove();
	}else if(num == 3){
		$($('#mytable_'+num+' tr')[n - 12]).remove();
		$($('#mytable_'+num+' tr')[n - 13]).remove();
	}else if(num == 2){
		$($('#mytable_'+num+' tr')[n - 12]).remove();
		$($('#mytable_'+num+' tr')[n - 13]).remove();
		$($('#mytable_'+num+' tr')[n - 14]).remove();
	}
	
	index--;
}

function getSpecification(num){
	var array = num.split("_");
	var str = '';
	var numsp = '';
	if(array!=null && array.length ==2){
		numsp = array[0]+''+array[1];
		str = '<select name=pricelist1.priceList['+array[1]+'].specification id=specification'+array[0]+''+array[1]+' style="width: 158px;"></select>';
	}else if(array!=null && array.length ==1){
		str = '<select name=pricelist1.priceList['+0+'].specification id=specification'+array[0]+' style="width: 158px;"></select>';
		numsp = array[0];
	}
	var partNumber =$("#partNumber"+numsp).val();
	if(partNumber!=null && partNumber != ""){
			$.ajax( {
		type : "POST",
		url : "PriceAction!findSpecification.action",
		data : {
			partNumber : partNumber	
		},
		dataType : "json",
		success : function(data) {
			if(data == "error"){
				alert("啊哦,出错了!")
			}else if(data.length >0){
				$("#specification"+numsp).remove();
				$("#div_"+numsp).empty();
				$("#div_"+numsp).append(str);
				for(var i=0; i<data.length; i++){
					$("#specification"+numsp).append('<option value='+data[i]+'>'+data[i]+'</option>');
				}
			}
		}
	})
		
	}
}

function cgblyanzheng(num){
	var cgbl = $("#cgbl"+num).val();
	var partNumber = $("#partNumber"+num).val();
		cgbl = parseFloat(cgbl);
	if(cgbl!=null && (cgbl>100 || cgbl <0)){
		$("#cgbl").val('');
		alert('采购比例不能超过100%或小于0,请重新输入')
	}else if(partNumber!=''){
		$.ajax( {
		type : "POST",
		url : "PriceAction!findsumcgbl.action",
		data : {
			partNumber : partNumber	
		},
		dataType : "json",
		success : function(data) {
			if(data == 'error'){
				alert("啊哦，出错了!");
			}else if(data!=null){
				data = parseFloat(data);
				var sumcgbl = data+cgbl;
				if(sumcgbl>100){
					alert('该件号的采购比例已经大于100%，请重新输入;');
					$("#cgbl").val('0');
				}
			}		
			
		}
	})
	}
}


//初始化显示div位置
function init(num0,num,num1) {
	count_seach++;
	var shortname = document.getElementById("partNumber"+num0);
	var showAll = document.getElementById("showAll"+num1+"_"+num);
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
}
//ajax获取所有的类似的全称
function getAllName(num0,num1,num2) {
	var num4 = num1+"_"+num2;
	if(num1 == '2'){
	$
			.ajax( {
				type : "POST",
				url : "PriceAction!getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.trademark' : $("#partNumber"+num0).val(),
					'yuanclAndWaigj.markId' : $("#partNumber"+num0).val(),
					'yuanclAndWaigj.clClass' : '外购件'
				},
				success : function(data) {
					$("#showAll"+num4).empty();
					$(data)
							.each(
									function() {
											var specification = $(this).attr(
													'specification');
											if (specification == null
													|| specification.length == 0) {
												specification = "";
											}
											var markId = $(this)
													.attr('markId')
													.replace(
															$("#partNumber"+num0)
																	.val(),
															"<font color='red'>"
																	+ $(
																			"#partNumber"+num0)
																			.val()
																	+ "</font>");
										$("#showAll"+num4)
													.append(
															"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this,&apos;"+num4+"&apos;,"+num0+")' align='left'>"
																	+ markId
																	+ "__"
																	+ $(this)
																			.attr(
																					'name')
																	+ "__"
																	+ specification
																	+"__"
																	+$(this).attr('wgType')
																	+ "<span style='visibility: hidden;'>"
																	+ $(this)
																			.attr(
																					'markId')
																	+ "__"
																	+ $(this)
																			.attr(
																					'name')
																	+ "__"
																	+ specification
																	+"__"
																	+$(this).attr('wgType')
																	+ "</span>"
																	+ "</div>");

									});
				}
			});
	}else{
		if($("#partNumber"+num0).val()!=''){
		$
			.ajax( {
				type : "POST",
				url : "PriceAction!getpriceListby.action",
				dataType : "json",
				data : {
					'pt.markId' : $("#partNumber"+num0).val()
				},
				success : function(data) {
					$("#showAll"+num4).empty();
					$(data)
							.each(
									function() {
											var specification = $(this).attr(
													'specification');

											if (specification == null
													|| specification.length == 0) {
												specification = "";
											}
											var markid = $(this)
													.attr('markId')
													.replace(
															$("#partNumber"+num0)
																	.val(),
															"<font color='red'>"
																	+ $(
																			"#partNumber"+num0)
																			.val()
																	+ "</font>");
											markid=markid+"__"+$(this)
													.attr('ywMarkId')
													.replace(
															$("#partNumber"+num0)
																	.val(),
															"<font color='red'>"
																	+ $(
																			"#partNumber"+num0)
																			.val()
																	+ "</font>");
											var needMarkid=$(this).attr('ywMarkId');
											if(needMarkid==null||needMarkid==""){
												needMarkid=$(this).attr('markId')	
											}
										$("#showAll"+num4)
													.append(
															"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this,&apos;"+num4+"&apos;,"+num0+")' align='left'>"
																	+ markid
																	+ "__"
																	+ $(this)
																			.attr(
																					'proName')
																	+ "__"
																	+ specification
																	+ "<span style='visibility: hidden;'>"
																	+ needMarkid
																	+ "__"
																	+ $(this)
																			.attr(
																					'proName')
																	+ "__"
																	+ specification
																	+ "</span>"
																	+ "</div>");

									});
				}
			});
		
	}}
}

function selectdiv(obj,num,num1) {
	var html = $(obj).find("span").html();
	var showAll = document.getElementById("showAll"+num);
	showAll.style.visibility = "hidden";
	var htmls = html.split("__");
	$("#partNumber"+num1).val(htmls[0]);
	$("#name"+num1).val(htmls[1]);
	$("#specification"+num1).val(htmls[2]);
	if(htmls.length == 4){
		$("#wltd_"+num).append('<b>物料类别:<b>');
		if(htmls[3] == 'null'){
			htmls[3] = '';
		}
	var nums = num.split("_");
		$("#wltd_"+num).append('<input type="hidden" value="'+htmls[3]+'" name=pricelist1.priceList['+nums[1]+'].wlType >'+htmls[3]);
	}
	$("#showAll"+num).hide();
	
	var array1 = num.split("_");

	if(array1!=null && array1.length==2 && array1[0] == '3'){
		 getgongxum(htmls[0],htmls[1],htmls[2],num1,array1[1]);
	}
	
}
function getgongxum(partNumber,name,specification,num,num1){
		$.ajax( {
		type : "POST",
		url : "PriceAction!getgongxunum.action",
		data : {
			'pt.markId' : partNumber,
			'pt.proName' : name,
			'pt.specification':specification
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
					$("#gxtd_"+num).html("");
					$("#gxtd_"+num).append('<input type="hidden" name=pricelist1.priceList['+num1+'].gongxunum id="gongxunum'+num+'">');
					$(data).each(function(){
						$("#gxtd_"+num).append('<input type="checkbox" name="gongxunum'+num+'" onclick="selectgongxu(this,&apos;'+num+'&apos;)" value='+this.processNO+'>'+this.processNO+'_'+this.processName+"<br/>");
					});
				
				
			}
		}
	})
}
function selectgongxu(obj,num){
	if(obj!=null && obj.value != ""){
		var gongxunum = '';
		var gongxunums =	document.getElementsByName("gongxunum"+num);
		if(gongxunums!=null && gongxunums.length>0){
			for(var i=0; i<gongxunums.length;i++){
				if(gongxunums[i].checked){
					 gongxunum +=gongxunums[i].value+";";
				}
			}
		}
		$("#gongxunum"+num).val(gongxunum);
	}
	
}
function gethtNum(num){
	$.ajax( {
		type : "POST",
		url : "PriceAction!getHtNum.action",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data != null) {
					$("#htNum_"+num).empty();
					$("#htNum_"+num).append('<option></option>');
					$(data).each(function(){
						$("#htNum_"+num).append('<option '+this+'>'+this+'</option>');
					});
						$("#htNum_"+num).tinyselect();
				var tinyselect = $("#thhtNum_"+num+"	.tinyselect");
		if (tinyselect[1] != null) {
			document.getElementById("thhtNum_"+num).removeChild(tinyselect[1]);
		}
			
				
			}
		}
	})
	
}
//得到合同和合同明细；
var delsize = 0;
function gethtMx(obj,num){
	if(delsize>0){
		for(var i=0;i<delsize;i++){
			delLine(num,'noshow');
		}
	}
	if(obj!=null && obj.value != ""){
		$.ajax( {
		type : "POST",
		url : "PriceAction!getBarContractByNum.action",
		data : {partNumber:obj.value},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var bdList = data.bcdList;
				$("#contractNumber"+num).val(data.contract_num);//合同编号
				$("#starttime"+num).val(data.startDate);//合同开始时间
				$("#pricePeriodEn"+num).val(data.endDate);//合同结束时间
				delsize = bdList.length;
				if(bdList!=null && bdList.length>0){
					var bd = bdList[0];
					$("#partNumber"+num).val(bd.gx_number);//件号
					$("#name"+num).val(bd.gx_name);//名称
					if(data.istax!='含税'){
						$("#bhsPrice_"+num).val(bd.gx_price);//不含税价
						var obj =	document.getElementById("bhsPrice_"+num);
						chagePrice(obj,num);
					}else{
						$("#hsPrice_"+num).val(bd.gx_price);//含税价
						var obj =	document.getElementById("hsPrice_"+num);
						chagePrice(obj,num);
					}
					$("#gys_"+num).val(data.gysId);//供应商
					if(bd.wgType!=null && bd.wgType!=''){
						$("#wltd_"+num+"_"+0).append('<b>物料类别:'+bd.wgType+'<input type="hidden"  name=pricelist1.priceList['+0+'].wlType  value = '+bd.wgType +'   ></b>');
					}else{
						$("#wltd_"+num+"_"+0).append('<b>物料类别:<input type="text"  name=pricelist1.priceList['+0+'].wlType  value =    ></b>');
					}
						
					for(var i=1;i<bdList.length; i++){
						addLine(num);
						var bd1 = bdList[i];
						$("#partNumber"+num+i).val(bd1.gx_number);
						$("#name"+num+i).val(bd1.gx_name);//名称
					if(data.istax!='含税'){
						$("#bhsPrice_"+num+i).val(bd1.gx_price);//不含税价
						var obj =	document.getElementById("bhsPrice_"+num+i);
						chagePrice(obj,num+i+"");
					}else{
						$("#hsPrice_"+num+i).val(bd1.gx_price);//含税价
						var obj =	document.getElementById("hsPrice_"+num+i);
						chagePrice(obj,num+i+"");
					}
						$("#gys_"+num+i).val(data.gysId);//供应商
						if(bd1.wgType!=null && bd1.wgType!=''){
						$("#wltd_"+num+"_"+i).append('<b>物料类别:'+bd1.wgType+'<input type="hidden"  name=pricelist1.priceList['+i+'].wlType  value = '+bd1.wgType +'   ></b>');
					}else{
						$("#wltd_"+num+"_"+i).append('<b>物料类别:<input type="text"  name=pricelist1.priceList['+i+'].wlType  value = ""    ></b>');
					}
					}
						
					$("table .selectbox").html(data.gyscode+"_"+data.supplier);
					$("#thhtNum_"+num+" .selectbox").html(data.contract_num);
				}
			}
		}
	})
	
	}
}


function getzhuserOfferById(num){
	$.ajax( {
		type : "POST",
		url : "PriceAction!getzhuserOfferById.action",
		data : {id:'${id}'},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#partNumber"+num).val(data.markId);
				$("#partNumber"+num).attr("readonly","readonly");
				$("#name"+num).val(data.name);
				$("#name"+num).attr("readonly","readonly");
				$("#specification"+num).val(data.specification);
				$("#specification"+num).attr("readonly","readonly");
				$("#banbenhao"+num).val(data.banbenhao);
				$("#banbenhao"+num).attr("readonly","readonly");
				$("#gys_"+num).val(data.zhUserId);
				$("#gys_"+num).attr("readonly","readonly");
				$("#taxRate_"+num).val(data.taxprice);
				$("#taxRate_"+num).attr("readonly","readonly");
				$("#taxRateTex_"+num).val(data.taxprice);
				//$("#taxRateTex_"+num).attr("readonly","readonly");
				$("#hsPrice_"+num).val(data.hsPrice);
				$("#hsPrice_"+num).attr("readonly","readonly");
				$("#bhsPrice_"+num).val(data.bhsPrice);
				$("#bhsPrice_"+num).attr("readonly","readonly");
				$("#kgliao"+num).val(data.kgliao);
				$("#wlType"+num).val(data.wgType);
				$("#kgliao"+num).attr("readonly","readonly");
				$("table .selectbox").html(data.zhUserId+"_"+data.cmp);
			}
		}
	})
}

function getAllNames(num,num0,index){
	if( $("#partNumber"+num).val()!=""){
		if(num0 == '1'){
			$.ajax( {
		type : "POST",
		url : "PriceAction!findZCPtName.action",
		data : {
					markId:$("#partNumber"+num).val()
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
					var bbh = data.banBenNumber;
					if(bbh==null||bbh=="null"){
						bbh = "";
					}
					$("#name"+num).val(data.proName);
					$("#specification"+num).val(data.specification);
					$("#type"+num).val(data.carStyle);
					$("#banbenhao"+num).val(bbh);
					$("#danwei_"+num).val(data.unit);
					$('#zhiti_font').html('');
			}else{
				$('#zhiti_font').html('未找到件号:<b>'+$("#partNumber"+num).val()+"</b>的总成");
			}
		}
	})
		}else if(num0 =='2' ){
			$.ajax( {
		type : "POST",
		url : "PriceAction!getAllNames.action",
		data : {
				'yuanclAndWaigj.trademark' : $("#partNumber"+num).val(),
					'yuanclAndWaigj.markId' : $("#partNumber"+num).val(),
					'yuanclAndWaigj.clClass' : '外购件'
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var str ='';
				$(data).each(function(){
					var bbh = this.banbenhao;
					if(bbh==null||bbh=="null"){
						bbh = "";
					}
					$("#wltd_"+num0+"_"+index).empty();
					$("#name"+num).val(this.name);
					$("#specification"+num).val(this.specification);
					$("#kgliao"+num).val(this.kgliao);
					str+= '<option value="'+bbh+'">'+bbh+'</option>';
					$("#banbenhao"+num).append('<option value="'+bbh+'">'+bbh+'</option>');
					$("#wltd_"+num0+"_"+index).append('<b>物料类别:'+this.wgType+'<input type="hidden"  name=pricelist1.priceList['+index+'].wlType  value = '+this.wgType +'   ></b>');
					$("#unit_"+num).val(this.unit);
					$("#picNo_"+num).val(this.tuhao);
				})
					
			}
		}
	})
		}
		
	
	
	}
	}
	function getpriceListby(num){
			if($("#partNumber"+num).val()!=""){
			
			$.ajax( {
				type : "POST",
				url : "PriceAction!getpriceListby.action",
				dataType : "json",
				data : {
					'pt.markId' : $("#partNumber"+num).val()
				},
				success : function(data) {
					if(data!=null && data.length == 2){
						var pt =	data[0];
						if(pt!=null){
							$("#name"+num).val(pt.proName);
						}
						var pstSet = data[1];
						var gongxuhao = "";
						if(pstSet!=null){
							$(pstSet).each(function(){
							gongxuhao+=";"+this.processNO
						})
						}
						
						gongxuhao = gongxuhao.substring(1);
						$("#gongxunum"+num).val(gongxuhao);
					}
				
				}
			});
	}
			}

</script>
	</head>
	<body bgcolor="#ffffff" onload="select('1')">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng">
				<div style="margin-top: 20px;">
					<font size="8" color="red" id="zifont">${errorMessage}</font>
				</div>
				<div align="left">
					<div
						style="padding-top: 10px; padding-left: 20px; border-bottom: solid #0170b8 1px;">
						<table>
							<tr>
								<td align="center" id="moduletd_1" style="display: none;">
									<div id="module1" class="tag_2" onclick="chageModule(this,'1')">
										<a href="javascript:void();"> 添加总成</a>
									</div>
								</td>
								<td align="center" id="moduletd_2" style="display: none;">
									<div id="module2" class="tag_1" onclick="chageModule(this,'2')">
										<a href="javascript:void();"> 添加外购</a>
									</div>
								</td>
								<td align="center" id="moduletd_3" style="display: none;">
									<div id="module3" class="tag_1" onclick="chageModule(this,'3')">
										<a href="javascript:void();"> 添加外委</a>
									</div>
								</td>
								<td align="center" id="moduletd_5" style="display: none;">
									<div id="module5" class="tag_1" onclick="chageModule(this,'5')">
										<a href="javascript:void();"> 添加模具</a>
									</div>
								</td>
								<td align="center" id="moduletd_6" style="display: none;">
									<div id="module6" class="tag_1" onclick="chageModule(this,'6')">
										<a href="javascript:void();"> 添加辅料</a>
									</div>
								</td>
								<td align="center" id="moduletd_4" style="display: none;">
									<div id="module4" class="tag_1" onclick="chageModule(this,'4')">
										<a href="javascript:void();"> 添加其他</a>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div align="center" style="width: 100%">
						<font color="red" id="zhiti_font">${successMessage}</font>
					</div>
					<div id="module1_1"
						style="font-weight: bold; margin-top: 10px; border: solid #000000 1px; width: 100%; display: none;"
						align="center">
						<form action="PriceAction!addPrice.action" method="post"
							onsubmit="return confirmsubmit('1')"
							enctype="multipart/form-data">
							<s:token />
							<table border="1" width="100%" id="mytable_1">
								<tr>
									<td colspan="12" align="center"
										style="font-family: 微软雅黑; font-weight: bold;">
										添 加 总 成(Add Assembly)
									</td>
								</tr>
								<tr>
									<td align="right">
										总成件号:
										<br />
										(Assembly Number)
									</td>
									<td>
										<div id="showAll1_0"
											style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
										</div>
										<input type="text" id="partNumber1"
											name="pricelist1.priceList[0].partNumber"
											onkeyup="getAllNames('1','1','0')"
<%--											onblur="writename(this,1);hidediv('showAll1_0')"--%>
											onFocus="init('1','0','1')">
										<br />
									</td>
									<td align="right">
										&nbsp;&nbsp;名称
										<br />
										(Name):
									</td>
									<td>
										<input type="text" id="name1"
											name="pricelist1.priceList[0].name" style="width: 240px;">
									</td>
									<td align="right">
										型&nbsp;&nbsp;&nbsp;别:
										<br />
										(Type)
									</td>
									<td>
										<input type="text" id="type1"
											name="pricelist1.priceList[0].type" id="type1">
									</td>
									<td align="right">
										&nbsp;&nbsp;价格:
										<br />
										(Price)
									</td>
									<td>
										<%--										<input type="text" id="tax_1" name="price.taxprice"--%>
										<%--											style="width:70px;" --%>
										<%--											/>--%>
										(税&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;率)
										<select id="taxRate_1" onblur="changetext('1')">
											<option value="0">
												0%
											</option>
											<option value="3">
												3%
											</option>
											<option value="5">
												5%
											</option>
											<option value="6">
												6%
											</option>
											<option value="7">
												7%
											</option>
											<option value="10">
												9%
											</option>
											<option value="9">
												9%
											</option>
											<option value="11">
												11%
											</option>
											<option value="13" selected="selected">
												13%
											</option>
											<option value="16" >
												16%
											</option>
											<option value="17" >
												17%
											</option>
											<option value="其他">
												其他
											</option>
										</select>
										<input id="taxRateTex_1"
											name="pricelist1.priceList[0].taxprice" value="13"
											type="hidden" />
										<br />
										订货量(从)
										<input type="text" name="pricelist1.priceList[0].firstnum"
											style="width: 70px;" id="firstnum1"
											onkeyup="numyanzheng(this)" value="0" />
										<br />
										订货量(到)
										<input type="text" name="pricelist1.priceList[0].endnum"
											style="width: 70px;" id="endnum1" onkeyup="numyanzheng(this)"
											value="0" />
										<br />
										(含&nbsp;&nbsp;税&nbsp;&nbsp;价)
										<input type="text" id="hsPrice_1"
											name="pricelist1.priceList[0].hsPrice" style="width: 70px; "
											onkeyup="mustBeNumber('hsPrice_1');chagePrice(this,'1')"
											onblur="mustBeNumber('hsPrice_1');chagePrice(this,'1')"
											onchange="isPrice(this);chagePrice(this,'1')"
											 readonly="readonly" >
										<br />
										(不含税价)
										<input type="text" id="bhsPrice_1"
											name="pricelist1.priceList[0].bhsPrice" style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_1');chagePrice(this,'1')"
											onblur="mustBeNumber('hsPrice_1');chagePrice(this,'1')"
											onchange="isPrice(this);chagePrice(this,'1')">


									</td>
									<td>
										<strong>单位:</strong>
										<SELECT name="pricelist1.priceList[0].danwei" id="danwei_1">
											<option value="PCS">
												PCS
											</option>
											<option value="件">
												件
											</option>
										</SELECT>
									</td>
									<td>
										<input type="button" value="添加" onclick="addLine('1')" />
										<input type="button" value="删除" onclick="delLine('1')" />
									</td>
								</tr>
								<tr id="lastTr_1">
									<th colspan="10" style="background-color: #daebf7">
										合同信息
									</th>
								</tr>
								<tr>
									<td align="right">
										合同编号:
										<br />
										(Contract Number)
									</td>
									<td>
										<input type="text" id="contractNumber1"
											name="price.contractNumber" />
									</td>

									<td align="right">
										负责人:
										<br />
										(Person in charge)
									</td>
									<td>
										<input type="text" name="price.chargePerson"
											id="chargePerson1">
									</td>
									<td align="right">
										机密等级：
										<br />
										(Confidential level' iu)
									</td>
									<td>
										<SELECT name="price.jimiDJ" id="selectName1"
											style="width: 163px;">
											<option value=""></option>
										</SELECT>
									</td>
									<td align="right">
										档 案 号:
										<br />
										(File Number)
									</td>
									<td>
										<input id="fileNumber1" type="text" name="price.fileNumber"
											value="${price.fileNumber}">
									</td>
									<td></td>
								</tr>
								<tr>

									<td align="right">
										客户名字:
										<br />
										(Customer name):
									</td>
									<td align="left">
										<select name="price.kehuId" id="custome" class="cxselect">
											<option selected="selected" value="">
												选择客户
											</option>
											<s:iterator id="cu" value="cmList">
												<option value="${cu.id}">
													${cu.clientcompanyname}
												</option>
											</s:iterator>
										</select>
									</td>
									<td rowspan="7" align="right">
										&nbsp;&nbsp;&nbsp;&nbsp;备注:
										<br />
										(Remarks)
									</td>
									<td rowspan="6" colspan="6">
										<textarea rows="15" cols="100" name="price.rmarks"
											id="rmarks1"></textarea>
									</td>
								</tr>
								<tr>
									<td align="right">
										产品类别:
										<br />
										(Product Type)
									</td>
									<td>
										<input type="text" value="总成" name="price.productCategory"
											id="productCategory1" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td align="right">
										档案柜号:
									</td>
									<td>
										<select id="danganWeizhi1" style="width: 163px;">
											<option>
												请选择档案柜号
											</option>
										</select>
										<input type="hidden" name="price.danganWeizhi" id="daweizhi1" />
										<input type="hidden" name="price.danganId" id="daId1" />
									</td>
								</tr>
								<tr>
									<td align="right">
										生产类型:
										<br />
										(Production Type)
									</td>
									<td>
										<select name="price.produceType" id="produceType1"
											style="width: 163px;">
											<option value="销售">
												销售
											</option>
											<option value="外委">
												外委
											</option>
											<option value="外购">
												外购
											</option>
											<option value="行政">
												象征
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										价格有效期时间从
										<br />
										(Price valid time from)
									</td>
									<td>
										<input id="starttime1" class="Wdate" type="text"
											value="${price.pricePeriodStart}"
											name="price.pricePeriodStart"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											onblur="date('1')" />
										<input id="hidden1" type="hidden" name="hiddenvaul">
									</td>
								</tr>
								<tr>
									<td align="right">
										到
										<br />
										(to)
									</td>
									<td>
										<input class="Wdate" type="text" name="price.pricePeriodEnd"
											value="${price.pricePeriodEnd}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											id="pricePeriodEnd1" />
									</td>
								</tr>
								<tr>
									<td align="right">
										上传附件:
										<br />
										(Upload)
									</td>
									<td colspan="8">
										<input type="button" id="fileButton_1"
											onclick="uploadFile(this,1)" value="上传附件">

										<div id="fileDiv_1" style="display: none;">

										</div>

									</td>
								</tr>
								<tr>
									<td colspan="12" align="center">
										<input type="submit" value="添加总成(add)"
											style="width: 100px; height: 50px;" id="add1">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="重置(reset)"
											style="width: 100px; height: 50px;">
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_2"
						style="font-weight: bold; margin-top: 10px; border: solid #000000 1px; display: none;"
						align="center">
						<form action="PriceAction!Pladdprice.action" method="post"
							enctype="multipart/form-data" onsubmit="return checktype()">
							选择导入文件:
							<input type="file" name="addprice">
							<a href="<%=basePath%>/upload/file/download/wgprice.xls">导入模版下载</a>
							<a
								href="FileViewAction.action?FilePath=/upload/file/download/wgprice.xls&Refresh=true">/预览</a>
							<input type="hidden" value="外购" name="price.produceType" />
							<input type="submit" value="批量导入" id="sub">
						</form>
						<form action="PriceAction!addPrice.action" method="post"
							onsubmit="return confirmsubmit('2')"
							enctype="multipart/form-data">
							<table border="1" width="100%" id="mytable_2">
								<tr>
									<td colspan="13" align="center"
										style="font-family: 微软雅黑; font-weight: bold;">
										添 加 外 购(Add components)
										<input type="hidden" name="id" value="${id}" />
										<%--										<input type="hidden" name="pricelist1.priceList[0].wlType"--%>
										<%--											value="" id="wlType2" />--%>

									</td>
								</tr>
								<s:if test="statue != 'addwg'">
									<tr>
										<th colspan="4" align="right" style="border-right: hidden;">
											合同编号:
										</th>
										<th colspan="6" align="left" id="thhtNum_2">
											<SELECT id="htNum_2" onchange="gethtMx(this,'2')">

											</SELECT>
										</th>
									</tr>
								</s:if>
								<tr>
									<td align="right">
										外购件号:
										<br />
										(Components Number)
									</td>
									<td>
										<div id="showAll2_0"
											style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
										</div>
										<input type="text" id="partNumber2"
											name="pricelist1.priceList[0].partNumber"
											onblur="getAllNames('2','2','0')">
										<br />
									</td>
									<td align="right">
										&nbsp;&nbsp;名称:
										<br />
										(Name)
									</td>
									<td>
										<input type="text" id="name2"
											name="pricelist1.priceList[0].name" style="width: 240px;">
									</td>
									<td align="right">
										规格:
										<br />
										(Specification)
									</td>
									<td>
										<input type="text"
											name="pricelist1.priceList[0].specification"
											id="specification2">
										<div id="div_2">

										</div>
									</td>
									<td align="right">
										供料属性:
									</td>
									<td>
										<select name="pricelist1.priceList[0].kgliao" id="kgliao2">
											<option value="TK">
												自购(TK)
											</option>
											<option value="TK AVL">
												指定供应商(TK AVL)
											</option>
											<option value="CS">
												客供(CS)
											</option>
											<option value="TK Price">
												完全指定(TK Price)
											</option>
										</select>
									</td>
									<th align="left">
										采购比例:
									</th>
									<td rowspan="1">
										<input type="text" name="pricelist1.priceList[0].cgbl"
											style="width: 50px;" />
									</td>
								</tr>
								<tr>
									<td align="right">
										版本号:
									</td>
									<td>
										<SELECT id="banbenhao2" name="pricelist1.priceList[0].banbenhao">
										</SELECT>
									</td>
									<td align="right">
										供应商:
										<br />
										(Customer name):
									</td>
									<td align="left">
										<select name="pricelist1.priceList[0].gysId" id="gys_2"
											style="width: 150px;" >
											<option value="">
												选择供应商
											</option>
										</select>
									</td>
									<td align="right">
										&nbsp;&nbsp;价格:
										<br />
										(Price)
									</td>
									<td>
										<%--										<input type="text" id="tax_2" name="price.taxprice"--%>
										<%--											style="width:70px;" --%>
										<%--											/>--%>
										<select id="taxRate_2" onblur="changetext('2')">
												<option value="0">
												0%
											</option>
											<option value="3">
												3%
											</option>
											<option value="5">
												5%
											</option>
											<option value="6">
												6%
											</option>
											<option value="7">
												7%
											</option>
											<option value="9">
												9%
											</option>
											<option value="10">
												10%
											</option>
											<option value="11">
												11%
											</option>
											<option value="13" selected="selected">
												13%
											</option>
											<option value="16" >
												16%
											</option>
											<option value="17" >
												17%
											</option>
											<option value="其他">
												其他
											</option>
										</select>
										(税率)
										<br />
										<input id="taxRateTex_2"
											name="pricelist1.priceList[0].taxprice" type="hidden"
											value="13" />

										<input type="text" id="hsPrice_2"
											name="pricelist1.priceList[0].hsPrice" style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_2');chagePrice(this,'2')"
											onblur="mustBeNumber('hsPrice_2');chagePrice(this,'2')"
											onchange="isPrice(this);chagePrice(this,'2')">
										(含税价)
										<br />
										<input type="text" id="bhsPrice_2"
											name="pricelist1.priceList[0].bhsPrice" style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_2');chagePrice(this,'2')"
											onblur="mustBeNumber('hsPrice_2');chagePrice(this,'2')"
											onchange="isPrice(this);chagePrice(this,'2')">
										(不含税价)
									</td>
									<td>
										起始数量
										<br />
										<input type="text" name="pricelist1.priceList[0].firstnum"
											style="width: 70px;" id="firstnum2"
											onkeyup="numyanzheng(this);daxiao('2')" value="0" />

									</td>
									<td>
										截止数量
										<br />
										<input type="text" name="pricelist1.priceList[0].endnum"
											style="width: 70px;" id="endnum2"
											onkeyup="numyanzheng(this);daxiao('2')" value="0" />
									</td>
									<td id="wltd_2_0">
									</td>
									<td>
										<s:if test="statue != 'addwg'">
											<input type="button" value="添加" onclick="addLine('2')" />
											<input type="button" value="删除" onclick="delLine('2')" />
										</s:if>
									</td>

								</tr>
								<tr>
									<td align="right">
										最低起订量
									</td>
									<td>
										<input type="text" value=""
											name="pricelist1.priceList[0].zdqdl" />
									</td>
									<td align="right">
										最低装箱量
									</td>
									<td>
										<input type="text" value=""
											name="pricelist1.priceList[0].zdzxl" />
									</td>
									<td align="right">
										最低起送量
									</td>
									<td>
										<input type="text" value=""
											name="pricelist1.priceList[0].zdqsl" />
									</td>
									<td align="right">
										图号
									</td>
									<td>
										<input type="text" value="" id="picNo_2" readonly="readonly"
											name="pricelist1.priceList[0].picNo" />
									</td>
									<td align="right">
										单位
									</td>
									<td>
										<input type="text" value="" id="unit_2" readonly="readonly"
											name="pricelist1.priceList[0].danwei" style="width: 75px;"/>
									</td>
								</tr>
								<tr id="lastTr_2">
									<th colspan="13" style="background-color: #daebf7">
										合同信息
									</th>
								</tr>
								<tr>
									<td align="right">
										合同编号:
										<br />
										(Contract Number)
									</td>
									<td>
										<input type="text" id="contractNumber2"
											name="price.contractNumber" />
									</td>
									<td align="right">
										负责人:
										<br />
										(Person in charge)
									</td>
									<td>
										<input type="text" name="price.chargePerson"
											id="chargePerson2" />
									</td>
									<td align="right">
										机密等级：
										<br />
										(Confidential level)
									</td>
									<td>
										<SELECT name="price.jimiDJ" id="selectName2"
											style="width: 163px;">
											<option value=""></option>
										</SELECT>
									</td>


								</tr>
								<tr>
									<td align="right">
										档案号:
										<br />
										(File Number)
									</td>
									<td>
										<input id="fileNumber2" type="text" name="price.fileNumber"
											value="${price.fileNumber}">
									</td>
									<td rowspan="7" align="right">
										&nbsp;&nbsp;&nbsp;&nbsp;备注:
										<br />
										(Remark)
									</td>
									<td rowspan="7 " colspan="10">
										<textarea rows="15" cols="100" name="price.rmarks" id="rmarks"></textarea>
									</td>
								</tr>
								<tr>
									<td align="right">
										档案柜号:
									</td>
									<td>
										<select id="danganWeizhi2" style="width: 163px;">
											<option>
												请选择档案柜号
											</option>
										</select>
										<input type="hidden" name="price.danganWeizhi" id="daweizhi2" />
										<input type="hidden" name="price.danganId" id="daId2" />
									</td>

								</tr>
								<tr>
									<td align="right">
										型 别:
										<br />
										(Type)
									</td>
									<td>
										<input type="text" id="type2" name="price.type">
									</td>
								</tr>
								<tr>
									<td align="right">
										产品类别:
										<br />
										(Product Type)
									</td>
									<td>
										<input type="text" value="零件" name="price.productCategory"
											id="productCategory2" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td align="right">
										生产类型:
										<br />
										(Production Type)
									</td>
									<td>
										<input type="text" value="外购" name="price.produceType"
											id="produceType2" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td align="right">
										价格有效期时间从
										<br />
										(Price valid time from)
									</td>
									<td>
										<input id="starttime2" class="Wdate" type="text"
											name="price.pricePeriodStart"
											value="${price.pricePeriodStart}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											onblur=" date('2') " />
										<input id="hidden2" type="hidden" name="hiddenvaul">
									</td>
								</tr>
								<tr>
									<td align="right">
										到
										<br />
										(to)
									</td>
									<td>
										<input class="Wdate" type="text" name="price.pricePeriodEnd"
											id="pricePeriodEnd2" value="${price.pricePeriodEnd}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									</td>
								</tr>
								<tr>
									<td align="right">
										上传附件:
										<br />
										(Upload)
									</td>
									<td colspan="13">
										<input type="button" id="fileButton_2"
											onclick="uploadFile(this,2)" value="上传附件">

										<div id="fileDiv_2" style="display: none;">

										</div>

									</td>
								</tr>
								<tr>
									<td colspan="13" align="center">
										<input type="submit" value="添加外购(add)"
											style="width: 100px; height: 50px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="重置(reset)"
											style="width: 100px; height: 50px;">
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_3"
						style="font-weight: bold; margin-top: 10px; width: 100%; border: solid #000000 1px; display: none;"
						align="center">
						<form action="PriceAction!Pladdprice.action" method="post"
							enctype="multipart/form-data" onsubmit="return checktype()">
							选择导入文件:
							<input type="file" name="addprice">
							<a href="<%=basePath%>/upload/file/download/wwprice.xls">导入模版下载</a>
							<a
								href="FileViewAction.action?FilePath=/upload/file/download/wwprice.xls&Refresh=true">/预览</a>
							<input type="hidden" value="外委" name="price.produceType" />
							<input type="submit" value="批量导入" id="sub">
						</form>
						<form action="PriceAction!addPrice.action" method="post"
							onsubmit="return confirmsubmit('3')"
							enctype="multipart/form-data">
							<table border="1" width="99%" id="mytable_3">
								<tr>
									<td colspan="12" align="center"
										style="font-family: 微软雅黑; font-weight: bold;">
										添 加 外 委(Add parts)
									</td>
								</tr>
								<tr>
									<td align="right">
										外委件号:
										<br />
										(Part Number)
									</td>
									<td>
										<div id="showAll3_0"
											style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
										</div>
										<input type="text" id="partNumber3"
											name="pricelist1.priceList[0].partNumber"
											<%--											onchange="getAllNames('3','3','0')"--%>
<%--											onkeyup="getAllNames('3','3','0')"--%>
<%--											onblur="hidediv('showAll3_0');getAllNames('3','3','0')"--%>
<%--											onFocus="init('3','0','3');getAllNames('3','3','0')"--%>
											onblur="getpriceListby('3')">
										<br />
									</td>
									<td align="right">
										&nbsp;&nbsp;名称
										<br />
										(Name):
									</td>
									<td>
										<input type="text" id="name3"
											name="pricelist1.priceList[0].name">
									</td>
									<td align="right">
										规格:
										<br />
										(Specification)
									</td>
									<td>
										<input type="text"
											name="pricelist1.priceList[0].specification"
											id="specification3">
										<div id="div_3">

										</div>
									</td>
									<td align="right">
										工序号(多个以;分隔):
									</td>
									<td align="left" id="gxtd_3">
										<input type="text" id="gongxunum3"
											name="pricelist1.priceList[0].gongxunum" value="0">
									</td>
									<td>
										单位
									</td>
									<td>
										<SELECT name="pricelist1.priceList[0].danwei" id="danwei_3">

										</SELECT>
										<br />
										(单件)
										<br />
										<SELECT name="pricelist1.priceList[0].bzdanwei"
											id="bzdanwei_3">

										</SELECT>
										<br />
										(标准)
									</td>
								</tr>
								<tr>
									<td align="right">
										&nbsp;&nbsp;价格:
										<br />
										(Price)
									</td>
									<td colspan="1">
										<%--										<input type="text" id="tax_3" name="price.taxprice"--%>
										<%--											style="width:70px;" --%>
										<%--											/>--%>

										<select id="taxRate_3" onblur="changetext('3')">
											<option value="0">
												0%
											</option>
											<option value="3">
												3%
											</option>
											<option value="5">
												5%
											</option>
											<option value="6">
												6%
											</option>
											<option value="7">
												7%
											</option>
											<option value="9">
												9%
											</option>
											<option value="10">
												10%
											</option>
											<option value="11">
												11%
											</option>
											<option value="13" selected="selected">
												13%
											</option>
											<option value="16">
												16%
											</option>
											<option value="17" >
												17%
											</option>
											<option value="其他">
												其他
											</option>
										</select>
										<input id="taxRateTex_3"
											name="pricelist1.priceList[0].taxprice" type="hidden"
											value="13" />
										(税率)
										<br />
										<input type="text" id="hsPrice_3"
											name="pricelist1.priceList[0].hsPrice" style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_3');chagePrice(this,'3')"
											onblur="mustBeNumber('hsPrice_3');chagePrice(this,'3')"
											onchange="isPrice(this);chagePrice(this,'3')">
										含税价)
										<br />
										<input type="text" id="bhsPrice_3"
											name="pricelist1.priceList[0].bhsPrice" style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_3');chagePrice(this,'3')"
											onblur="mustBeNumber('hsPrice_3');chagePrice(this,'3')"
											onchange="isPrice(this);chagePrice(this,'3')">
										(不含税价)
									</td>
									<td align="right">
										起始数量
									</td>
									<td>
										<input type="text" name="pricelist1.priceList[0].firstnum"
											style="width: 70px;" id="firstnum3"
											onkeyup="numyanzheng(this);daxiao('3')" value="0" />
									</td>
									<td align="right">
										截止数量
									</td>
									<td>
										<input type="text" name="pricelist1.priceList[0].endnum"
											style="width: 70px;" id="endnum3"
											onkeyup="numyanzheng(this);;daxiao('3')" value="0" />
									</td>
									<td align="right" style="width: 100px;">
										单位价格
										<br />
										<font size="1px;" color="red">如：每平米、每公斤不参与计算</font>
									</td>
									<td>
										<input type="text" id="danweihsPrice_3"
											name="pricelist1.priceList[0].danweihsPrice"
											style="width: 70px;"
											onkeyup="mustBeNumber('danweihsPrice_3');chagePrice(this,'3')"
											onblur="mustBeNumber('danweihsPrice_3');chagePrice(this,'3')"
											onchange="isPrice(this);chagePrice(this,'3')">
										（含税）
										<br />
										<input type="text" id="danweibhsPrice_3"
											name="pricelist1.priceList[0].danweibhsPrice"
											style="width: 70px;"
											onkeyup="mustBeNumber('danweibhsPrice_3');chagePrice(this,'3')"
											onblur="mustBeNumber('danweibhsPrice_3');chagePrice(this,'3')"
											onchange="isPrice(this);chagePrice(this,'3')">
										（不含税）

									</td>
									<td colspan="2">
										<input type="button" value="添加" onclick="addLine('3')" />
										<input type="button" value="删除" onclick="delLine('3')" />
									</td>
								</tr>
								<tr id="lastTr_3">
									<th colspan="12" style="background-color: #daebf7">
										合同信息
									</th>
								</tr>
								<tr id="">
									<td align="right">
										合同编号:
										<br />
										(Contract Number)
									</td>
									<td>
										<input type="text" id="contractNumber3"
											name="price.contractNumber" />
									</td>

									<td align="right">
										负责人:
										<br />
										(Person in charge)
									</td>
									<td>
										<input type="text" name="price.chargePerson"
											id="chargePerson3">
									</td>
									<td align="right">
										机密等级：
										<br />
										(Confidential level)
									</td>
									<td>
										<SELECT name="price.jimiDJ" id="selectName3"
											style="width: 163px;">
											<option value=""></option>
										</SELECT>
									</td>
									<td align="right">
										型 别:
										<br />
										(Type)
									</td>
									<td>
										<input type="text" id="type3" name="price.type">
									</td>
									<td>
										外委类型:
									</td>
									<td>
										<SELECT name="price.wwType">
											<option>
												工序外委
											</option>
											<option>
												包工包料
											</option>
										</SELECT>
									</td>
									<td></td>
								</tr>
								<tr>
									<td align="right">
										供应商:
										<br />
										(Customer name):
									</td>
									<td align="left">
										<select name="price.gysId" id="gys_3" 
											style="width: 150px;">
											<option selected="selected" value="">
												选择供应商
											</option>
										</select>
									</td>
									<td rowspan="7" align="right">
										&nbsp;&nbsp;&nbsp;&nbsp;备注:
										<br />
										(Remark)
									</td>
									<td rowspan="7" colspan="8">
										<textarea rows="15" cols="100" name="price.rmarks"
											id="rmarks3"></textarea>
									</td>
								</tr>
								<tr>
									<td align="right">
										档案号:
										<br />
										(File Number)
									</td>
									<td>
										<input id="fileNumber3" type="text" name="price.fileNumber"
											value="${price.fileNumber}">
									</td>
								</tr>
								<tr>
									<td align="right">
										档案柜号:
									</td>
									<td>
										<select id="danganWeizhi3" style="width: 163px;">
											<option>
												请选择档案柜号
											</option>
										</select>
										<input type="hidden" name="price.danganWeizhi" id="daweizhi3" />
										<input type="hidden" name="price.danganId" id="daId3" />
									</td>

								</tr>
								<tr>
									<td align="right">
										产品类别:
										<br />
										(Product Type)
									</td>
									<td>
										<input type="text" name="price.productCategory" value="零件"
											id="productCategory3" readonly="readonly" />

									</td>
								</tr>
								<tr>
									<td align="right">
										生产类型:
										<br />
										(Production Type)
									</td>
									<td>
										<input type="text" name="price.produceType" value="外委"
											id="produceType3" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td align="right">
										价格有效期时间从
										<br />
										(Price valid time from)
									</td>
									<td>
										<input id="starttime3" class="Wdate" type="text"
											name="price.pricePeriodStart"
											value="${price.pricePeriodStart}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											onblur="date('3')" />
										<input id="hidden3" type="hidden" name="hiddenvaul">
									</td>
								</tr>
								<tr>
									<td align="right">
										到
										<br />
										(to)
									</td>
									<td>
										<input class="Wdate" type="text" name="price.pricePeriodEnd"
											id="pricePeriodEnd3" value="${price.pricePeriodEnd}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									</td>
								</tr>
								<tr>
									<td align="right">
										上传附件:
										<br />
										(Upload)
									</td>
									<td colspan="11">
										<input type="button" id="fileButton_3"
											onclick="uploadFile(this,3)" value="上传附件">

										<div id="fileDiv_3" style="display: none;">

										</div>

									</td>
								</tr>
								<tr>
									<td colspan="12" align="center">
										<input type="submit" value="添加外委(add)"
											style="width: 100px; height: 50px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="重置(reset)"
											style="width: 100px; height: 50px;">
									</td>
								</tr>
							</table>
						</form>

					</div>
					<div id="module1_4"
						style="font-weight: bold; margin-top: 10px; border: solid #000000 1px; display: none;"
						align="center">
						<form action="PriceAction!Pladdprice.action" method="post"
							enctype="multipart/form-data" onsubmit="return checktype()">
							选择导入文件:
							<input type="file" name="addprice">
							<a href="<%=basePath%>/upload/file/download/wgprice.xls">导入模版下载</a>
							<a
								href="FileViewAction.action?FilePath=/upload/file/download/wgprice.xls&Refresh=true">/预览</a>
							<input type="hidden" value="其他" name="price.produceType" />
							<input type="submit" value="批量导入" id="sub">
						</form>
						<form action="PriceAction!addPrice.action" method="post"
							onsubmit="return confirmsubmit('4')"
							enctype="multipart/form-data">
							<table border="1" width="100%">
								<tr>
									<td colspan="6" align="center"
										style="font-family: 微软雅黑; font-weight: bold;">
										添 加 其 他(add other)
									</td>
								</tr>
								<tr>
									<td align="right">
										&nbsp;&nbsp;名称:
										<br />
										(Name)
									</td>
									<td>
										<input type="text" id="name4" name="price.name">
									</td>
									<td align="right">
										&nbsp;&nbsp;价格:
										<br />
										(Price)
									</td>
									<td>
										<%--										<input type="text" id="tax_5" name="price.taxprice"--%>
										<%--											style="width:70px;" --%>
										<%--											/>--%>
										<select id="taxRate_5" onblur="changetext('5')">
												<option value="0">
												0%
											</option>
											<option value="3">
												3%
											</option>
											<option value="5">
												5%
											</option>
											<option value="6">
												6%
											</option>
											<option value="7">
												7%
											</option>
											<option value="9">
												9%
											</option>
											<option value="10">
												10%
											</option>
											<option value="11">
												11%
											</option>
											<option value="13" selected="selected">
												13%
											</option>
											<option value="16">
												16%
											</option>
											<option value="17" >
												17%
											</option>
											<option value="其他">
												其他
											</option>
										</select>
										<input id="taxRateTex_5" name="price.taxprice" type="hidden"
											value="13" />
										(税率)
										<input type="text" id="hsPrice_5" name="price.hsPrice"
											style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_5');chagePrice(this,'5')"
											onblur="mustBeNumber('hsPrice_5');chagePrice(this,'5')"
											value="0" onchange="isPrice(this);chagePrice(this,'5')">
										(含税价)
										<input type="text" id="bhsPrice_5" name="price.bhsPrice"
											style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_3');chagePrice(this,'5')"
											onblur="mustBeNumber('hsPrice_5');chagePrice(this,'5')"
											value="0" onchange="isPrice(this);chagePrice(this,'5')">
										(不含税价)
									</td>
								</tr>
								<tr>
									<td align="right">
										型 别:
										<br />
										(Type)
									</td>
									<td>
										<input type="text" id="type4" name="price.type">
									</td>

									<td align="right">
										档案号:
										<br />
										(File Number)
									</td>
									<td>
										<input type="text" id="fileNumber4" name="price.fileNumber"
											value="${price.fileNumber}">
									</td>
								</tr>
								<tr>
									<td align="right">
										业务编号:
										<br />
										(Business Number)
									</td>
									<td>
										<input type="text" id="contractNumber4"
											name="price.contractNumber" />
									</td>
									<td align="right">
										产品类别:
										<br />
										(Product Type)
									</td>
									<td>
										<select name="price.productCategory" style="width: 163px;"
											id="productCategory4">
											<option value="辅料">
												辅料
											</option>
											<option value="其他">
												其他
											</option>
											<option value="磨具封装">
												磨具封装
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										&nbsp;&nbsp;件号:
										<br />
										(partNumber)
									</td>
									<td>
										<input type="text" name="price.partNumber" onchange="getwgj()" />
										<%--
										<input type="text" name="price.qidingfang">
									--%>
									</td>
									<td align="right">
										&nbsp;&nbsp;版本:
										<br />
										(versions)
									</td>
									<td>
										<input type="text" name="price.banbenhao" id="banben4" />
									</td>
								</tr>
								<tr>
									<td align="right">
										&nbsp;&nbsp;供料属性:
										<br />
										(For the material properties)
									</td>
									<td>
										<input type="text" name="price.kgliao" id="kgliao4" />
									</td>
									<td rowspan="7" align="right">
										&nbsp;&nbsp;&nbsp;&nbsp;备注:
										<br />
										(Remark)
									</td>
									<td rowspan="7" colspan="3">
										<textarea rows="8" cols="63" name="price.rmarks"></textarea>
									</td>
								</tr>
								<tr>
									<td align="right">
										供应商/乙方
									</td>
									<td>
										<%--										<input type="text" name="price.qidingfang" >--%>
										<select name="price.gysId" id="gys_4" style="width: 150px;"
											>
											<option>
												选择供应商
											</option>
										</select>
									</td>


								</tr>
								<tr>
									<td align="right">
										机密等级：
										<br />
										(Confidential level)
									</td>
									<td>
										<SELECT name="price.jimiDJ" id="selectName4"
											style="width: 163px;">
											<option value=""></option>
										</SELECT>
									</td>

								</tr>
								<tr>
									<td align="right">
										档案柜号:
									</td>
									<td>
										<select id="danganWeizhi4" style="width: 163px;">
											<option>
												请选择档案柜号
											</option>
										</select>
										<input type="hidden" name="price.danganWeizhi" id="daweizhi4" />
										<input type="hidden" name="price.danganId" id="daId4" />
									</td>
								</tr>
								<tr>
									<td align="right">
										负责人:
										<br />
										(Person in charge)
									</td>
									<td>
										<input type="text" name="price.chargePerson">
									</td>
								</tr>
								<tr>
									<td align="right">
										价格有效期时间从
										<br />
										(Price valid time from)
									</td>
									<td>
										<input id="starttime4" class="Wdate" type="text"
											name="price.pricePeriodStart"
											value="${price.pricePeriodStart}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											onblur=" date('4') " />
										<input id="hidden4" type="hidden" name="hiddenvaul">
									</td>
								</tr>
								<tr>
									<td align="right">
										到
										<br />
										(to)
									</td>
									<td>
										<input class="Wdate" type="text" name="price.pricePeriodEnd"
											value="${price.pricePeriodEnd}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									</td>
								</tr>
								<tr>
									<td align="right">
										上传附件:
										<br />
										(Upload)
									</td>
									<td colspan="10">
										<input type="button" id="fileButton_4"
											onclick="uploadFile(this,4)" value="上传附件">

										<div id="fileDiv_4" style="display: none;">

										</div>

									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<input type="submit" value="添加其他(add)"
											style="width: 100px; height: 50px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="重置(reset)"
											style="width: 100px; height: 50px;">
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_5"
						style="font-weight: bold; margin-top: 10px; border: solid #000000 1px; display: none;"
						align="center">
						<form action="PriceAction!addPrice.action" method="post"
							onsubmit="return confirmsubmit('5')"
							enctype="multipart/form-data">
							<table border="1" width="100%">
								<tr>
									<td colspan="6" align="center"
										style="font-family: 微软雅黑; font-weight: bold;">
										添 加 模 具(add other)
									</td>
								</tr>
								<tr>
									<th align="center" colspan="10">
										开模申请单号:
										<SELECT id="mjsqplanNum5" name="price.mjsqplanNum"
											onchange="getmao(this)">
											<option></option>
										</SELECT>
									</th>
								</tr>
								<tr>
									<td align="right">
										模具名称
										<br />
										(Name)
									</td>
									<td>
										<input type="text" id="name5" name="price.name">
									</td>
									<td align="right">
										&nbsp;&nbsp;价格
										<br />
										(Price)
									</td>
									<td>
										<%--										<input type="text" id="tax_5" name="price.taxprice"--%>
										<%--											style="width:70px;" --%>
										<%--											/>--%>
										<select id="taxRate_5" onblur="changetext('5')">
												<option value="0">
												0%
											</option>
											<option value="3">
												3%
											</option>
											<option value="5">
												5%
											</option>
											<option value="6">
												6%
											</option>
											<option value="7">
												7%
											</option>
											<option value="9">
												9%
											</option>
											<option value="10">
												10%
											</option>
											<option value="11">
												11%
											</option>
											<option value="13" selected="selected">
												13%
											</option>
											<option value="16">
												16%
											</option>
											<option value="17" >
												17%
											</option>
											<option value="其他">
												其他
											</option>
										</select>
										<input id="taxRateTex_5" name="price.taxprice" type="hidden"
											value="13" />
										(税率)
										<input type="text" id="hsPrice_5" name="price.hsPrice"
											style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_5');chagePrice(this,'5')"
											onblur="mustBeNumber('hsPrice_5');chagePrice(this,'5')"
											value="0" onchange="isPrice(this);chagePrice(this,'5')">
										(含税价)
										<input type="text" id="bhsPrice_5" name="price.bhsPrice"
											style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_5');chagePrice(this,'5')"
											onblur="mustBeNumber('hsPrice_5');chagePrice(this,'5')"
											value="0" onchange="isPrice(this);chagePrice(this,'5')">
										(不含税价)
									</td>
								</tr>
								<tr>
									<td align="right">
										型 别
										<br />
										(Type)
									</td>
									<td>
										<input type="text" id="type5" name="price.type">
									</td>

									<td align="right">
										档案号
										<br />
										(File Number)
									</td>
									<td>
										<input type="text" id="fileNumber5" name="price.fileNumber"
											value="${price.fileNumber}">
									</td>
								</tr>
								<tr>
									<td align="right">
										合同编号
										<br />
										(Business Number)
									</td>
									<td>
										<input type="text" id="contractNumber5"
											name="price.contractNumber" />
									</td>
									<td align="right">
										产品类别
										<br />
										(Product Type)
									</td>
									<td>
										<select name="price.productCategory" style="width: 163px;"
											id="productCategory5">
											<option value="磨具封装">
												磨具封装
											</option>
											<option value="其他">
												其他
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										业务件号
									</td>
									<td>
										<input type="text" id="ywmarkId5" name="price.ywmarkId" />
									</td>
									<td rowspan="7" align="right">
										&nbsp;&nbsp;&nbsp;&nbsp;备注:
										<br />
										(Remark)
									</td>
									<td rowspan="7" colspan="3">
										<textarea rows="8" cols="63" name="price.rmarks"></textarea>
									</td>
								</tr>
								<tr>
									<td align="right">
										供应商
									</td>
									<td>
										<select name="price.gysId" id="gys_5"
											style="width: 150px;">
											<option  value="">
												选择供应商
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										机密等级
										<br />
										(Confidential level)
									</td>
									<td>
										<SELECT name="price.jimiDJ" id="selectName4"
											style="width: 163px;">
											<option value=""></option>
										</SELECT>
									</td>

								</tr>
								<tr>
									<td align="right">
										档案柜号
									</td>
									<td>
										<select id="danganWeizhi5" style="width: 163px;">
											<option>
												请选择档案柜号
											</option>
										</select>
										<input type="hidden" name="price.danganWeizhi" id="daweizhi5" />
										<input type="hidden" name="price.danganId" id="daId5" />
									</td>
								</tr>
								<tr>
									<td align="right">
										负责人
										<br />
										(Person in charge)
									</td>
									<td>
										<input type="text" name="price.chargePerson">
									</td>
								</tr>
								<tr>
									<td align="right">
										价格有效期时间从
										<br />
										(Price valid time from)
									</td>
									<td>
										<input id="starttime5" class="Wdate" type="text"
											name="price.pricePeriodStart"
											value="${price.pricePeriodStart}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											onblur=" date('5') " />
										<input id="hidden5" type="hidden" name="hiddenvaul">
									</td>
								</tr>
								<tr>
									<td align="right">
										到
										<br />
										(to)
									</td>
									<td>
										<input class="Wdate" type="text" name="price.pricePeriodEnd"
											value="${price.pricePeriodEnd}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									</td>
								</tr>
								<tr>
									<td align="right">
										上传附件
										<br />
										(Upload)
									</td>
									<td colspan="10">
										<input type="button" id="fileButton_5"
											onclick="uploadFile(this,5)" value="上传附件">

										<div id="fileDiv_5" style="display: none;">

										</div>

									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<input type="submit" value="添加磨具(add)"
											style="width: 100px; height: 50px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="重置(reset)"
											style="width: 100px; height: 50px;">
									</td>
								</tr>
							</table>
						</form>
					</div>

					<!-- 添加辅料开始 -->
					<div id="module1_6"
						style="font-weight: bold; margin-top: 10px; border: solid #000000 1px; display: none;"
						align="center">
						<form action="PriceAction!Pladdprice.action" method="post"
							enctype="multipart/form-data" onsubmit="return checktype()">
							选择导入文件:
							<input type="file" name="addprice">
							<a href="<%=basePath%>/upload/file/download/wgprice.xls">导入模版下载</a>
							<a
								href="FileViewAction.action?FilePath=/upload/file/download/wgprice.xls&Refresh=true">/预览</a>
							<input type="hidden" value="辅料" name="price.produceType" />
							<input type="submit" value="批量导入" id="sub">
						</form>
						<form action="PriceAction!addPrice.action" method="post"
							onsubmit="return confirmsubmit('6')"
							enctype="multipart/form-data">
							<table border="1" width="100%">
								<tr>
									<td colspan="6" align="center"
										style="font-family: 微软雅黑; font-weight: bold;">
										添 加 辅料(add ingredients)
									</td>
								</tr>
								<tr>
									<td align="right">
										&nbsp;&nbsp;件号:
										<br />
										(partNumber)
									</td>
									<td>
										<input type="text" name="price.partNumber" onchange="getwgj(this)"
											id="partNumber6" />
									</td>
									<td align="right">
										&nbsp;&nbsp;名称:
										<br />
										(Name)
									</td>
									<td>
										<input type="text" id="name6" name="price.name">
									</td>
									<td align="right">
										&nbsp;&nbsp;价格:
										<br />
										(Price)
									</td>
									<td>
										<select id="taxRate_6" onblur="changetext('6')">
												<option value="0">
												0%
											</option>
											<option value="3">
												3%
											</option>
											<option value="5">
												5%
											</option>
											<option value="6">
												6%
											</option>
											<option value="7">
												7%
											</option>
											<option value="9">
												9%
											</option>
											<option value="10">
												10%
											</option>
											<option value="11">
												11%
											</option>
											<option value="13" selected="selected">
												13%
											</option>
											<option value="16">
												16%
											</option>
											<option value="17" >
												17%
											</option>
											<option value="其他">
												其他
											</option>
										</select>
										<input id="taxRateTex_6" name="price.taxprice" type="hidden"
											value="13" />
										(税率)
										<input type="text" id="hsPrice_6" name="price.hsPrice"
											style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_6');chagePrice(this,'6')"
											onblur="mustBeNumber('hsPrice_6');chagePrice(this,'6')"
											value="0" onchange="isPrice(this);chagePrice(this,'6')">
										(含税价)
										<input type="text" id="bhsPrice_6" name="price.bhsPrice"
											style="width: 70px;"
											onkeyup="mustBeNumber('hsPrice_6');chagePrice(this,'6')"
											onblur="mustBeNumber('hsPrice_6');chagePrice(this,'6')"
											value="0" onchange="isPrice(this);chagePrice(this,'6')">
										(不含税价)
									</td>
								</tr>
								<tr>
									<td align="right">
										物料类别
									</td>
									<td>
										<input type="text" name="price.wlType" id="wlType6">
									</td>
									<td align="right">
										单位
									</td>
									<td>
										<input type="text" name="price.danwei" id="unit6" />
									</td>
									<td align="right">
										规格
									</td>
									<td>
										<input type="text" name="price.specification" id="specification6"/> 
									</td>
								</tr>
								<tr>
									<td align="right">
										型 别:
										<br />
										(Type)
									</td>
									<td>
										<input type="text" id="type6" name="price.type">
									</td>
									<td align="right">
										档案号:
										<br />
										(File Number)
									</td>
									<td>
										<input type="text" id="fileNumber6" name="price.fileNumber"
											value="${price.fileNumber}">
									</td>
									<td align="right">
										产品类别:
										<br />
										(Product Type)
									</td>
									<td>
										<select name="price.productCategory" style="width: 163px;"
											id="productCategory6">
											<option value="辅料">
												辅料
											</option>
											<option value="其他">
												其他
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										合同编号:
									</td>
									<td>
										<input type="text" id="contractNumber6"
											name="price.contractNumber" />
									</td>
									<td align="right">
										&nbsp;&nbsp;版本:
										<br />
										(versions)
									</td>
									<td>
										<input type="text" name="price.banbenhao" id="banben6" />
									</td>
									<td align="right">
										&nbsp;&nbsp;供料属性:
										<br />
										(For the material properties)
									</td>
									<td>
										<input type="text" name="price.kgliao" id="kgliao6" />
									</td>
								</tr>
								<tr>
									<td align="right">
										最低起订量:
									</td>
									<td align="left">
										<input type="text" name="price.zdqdl" />
									</td>
									<td align="right">
										最低装箱量:
									</td>
									<td>
										<input type="text" name="price.zdzxl" />
									</td>
									<td align="right">
										最低起送量:
									</td>
									<td>
										<input type="text" name="price.zdqsl" />
									</td>
								</tr>
								<tr>
									<td align="right">
										供应商/乙方
									</td>
									<td>
										<select name="price.gysId" id="gys_6" >
											<option>
												选择供应商
											</option>
										</select>
									</td>
									<td rowspan="6" align="right">
										&nbsp;&nbsp;&nbsp;&nbsp;备注:
										<br />
										(Remark)
									</td>
									<td rowspan="7" colspan="3">
										<textarea rows="8" cols="63" name="price.rmarks"></textarea>
									</td>

								</tr>
								<tr>
									<td align="right">
										机密等级：
										<br />
										(Confidential level)
									</td>
									<td>
										<SELECT name="price.jimiDJ" id="selectName6"
											style="width: 163px;">
											<option value=""></option>
										</SELECT>
									</td>

								</tr>
								<tr>
									<td align="right">
										档案柜号:
									</td>
									<td>
										<select id="danganWeizhi6" style="width: 163px;">
											<option>
												请选择档案柜号
											</option>
										</select>
										<input type="hidden" name="price.danganWeizhi" id="daweizhi6" />
										<input type="hidden" name="price.danganId" id="daId6" />
									</td>
								</tr>
								<tr>
									<td align="right">
										负责人:
										<br />
										(Person in charge)
									</td>
									<td>
										<input type="text" name="price.chargePerson">
									</td>
								</tr>
								<tr>
									<td align="right">
										价格有效期时间从
										<br />
										(Price valid time from)
									</td>
									<td>
										<input id="starttime4" class="Wdate" type="text"
											name="price.pricePeriodStart"
											value="${price.pricePeriodStart}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											onblur=" date('6') " />
										<input id="hidden6" type="hidden" name="hiddenvaul">
									</td>
								</tr>
								<tr>
									<td align="right">
										到
										<br />
										(to)
									</td>
									<td>
										<input class="Wdate" type="text" name="price.pricePeriodEnd"
											value="${price.pricePeriodEnd}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									</td>
								</tr>
								<tr>
									<td align="right">
										上传附件:
										<br />
										(Upload)
									</td>
									<td colspan="10">
										<input type="button" id="fileButton_6"
											onclick="uploadFile(this,6)" value="上传附件">

										<div id="fileDiv_6" style="display: none;">

										</div>

									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<input type="submit" value="添加辅料(add)" id="add6"
											class="input">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="重置(reset)" class="input">
									</td>
								</tr>
							</table>
						</form>
					</div>
					<!-- 添加辅料结束 -->
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
			<SCRIPT type="text/javascript">
$(function(){
	if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		$.ajax( {
		type : "POST",
		url : "PriceAction!findPhoneqx.action",
		data : {},
		dataType : "json",
		success : function(data) {
			var nums ="";
			if(data!=null && data.length>0){
				for(var i=0;i<data.length;i++){
					if(data[i] == "zc"){
						nums+="1";
					}else if(data[i] == "wg"){
						nums+="2";
					}else if(data[i] == "ww"){
						nums+="3";
					}else if(data[i] == "qt"){
						nums+="4";
					}
				}
				}
		if(nums.length == 0){
			window.location.href="<%=basePath%>/System/caiwu/price_noAdd.jsp";
		}else{
	for(i=1;i<=4;i++){
		if(nums.indexOf(i+'') ==-1){    
		$("#moduletd_"+i).hide();
	}
	}
	if(nums!=null && nums.length>0){
		for(var i=0;i<nums.length;i++){
			if(i==0){
				$("#module"+nums[i]).show();	
				$("#module"+nums[i]).attr("class","tag_2")
				$("#module1_"+nums[i]).show();	
				oldObj = $("#module"+nums[i]);
				oldObj2 = nums[i];
			}
			$("#moduletd_"+nums[i]).show();
		}
	}
	}
		}
	})
	}
	$.ajax( {
		type : "POST",
		url : "MouldApplyOrderAction_findmaoListNoPrice.action",
		dataType : "json",
		success : function(data) {
			$("#mjsqplanNum5").empty();
			$("#mjsqplanNum5").append("<option></option>");
			if(data!=null){
				$(data).each(function(){
					$("#mjsqplanNum5").append('<option value='+this.planNumber+'>'+this.planNumber+'</option>');
				})
			}
			$("#mjsqplanNum5").tinyselect();
		}
	})
	
	
})

function daxiao(num){
	var firstnum = $("#firstnum"+num).val();
	var endnum = $("#endnum"+num).val();
	if(firstnum!='' && endnum!=''){
		firstnum = parseInt(firstnum);
		endnum = parseInt(endnum);
		if(firstnum>endnum){
		alert('起始数量大于截止数量，请重新输入!')
		 $("#firstnum"+num).val(0);
		$("#endnum"+num).val(0);
	}
	}
	
	
}
function getmao(obj){
	if(obj!=null && obj.value!=''){
		$.ajax( {
		type : "POST",
		url : "MouldApplyOrderAction_findMaoOne.action",
		data : {
				planNumber:obj.value
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#ywmarkId5").val(data.ywMarkId);
			}
		}
	})
	}
}
function getwgj(obj){
	var markId = obj.value;
	if(markId!=""){
		$.ajax({
			type : "POST",
			//url : "ManualOrderPlanAction_getManualOrderPlan.action",
			url:"OAAppDetailTemplateAction!getTempByCode.action",
			dataType : "json",
			data : {
				"template.wlcode" : markId
			},
			success : function(data) {
				if(data!=null){
					$("#name6").val(data.detailAppName);
					$("#wlType6").val(data.detailChildClass);//物料类别
					$("#unit6").val(data.detailUnit);
					$("#kgliao6").val(data.kgliao);
					$("#specification6").val(data.detailFormat);
					$("#banben6").val(data.banben);
					//$("#type6").val(data.type); //规格
					//$("#type").val("外购件");
					//$("#tuhao").val(data.tuhao);
					//$("#unit").val(data.detailUnit);
				}else{
					alert(markId+'未审批同意。')
					$("#name6").val("");	
					$("#type6").val(""); //规格
					$("#kgliao6").val("");
					$("#banben6").val("");
					$("#wlType6").val("");//物料类别
					//$("#type").val("外购件");
					$("#tuhao6").val("");
					$("#unit").val("");
				}
			},
			error : function(){
				console.log('data:',data);
				alert("出错了");
			}
		});
	}
}
function getgys(num,index){
	var size =	$("#gys_"+num+index+" option").size();
	if(size==1){
		$.ajax( {
		type : "POST",
		url : "PriceAction!findAllZhUser.action",
		dataType : "json",
		success : function(data) {
			$("#gys_"+num+index).empty();
			$("#gys_"+num+index).append("<option value=''>选择供应商</option>");
			if(data!=null){
				$(data).each(function(){
					$("#gys_"+num+index).append('<option value='+this.id+'>'+this.usercode+'_'+this.name+'</option>');
				})
			}
			$("#gys_"+num+index).tinyselect();
		}
	})
	}
}

</SCRIPT>
		</center>
	</body>
</html>