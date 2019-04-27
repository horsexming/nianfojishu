<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<form id="fromcw" action="CwVouchersAction!updateDetailList.action" method="post">
				<div id="contentDiv"
					style="height:200px;overflow-y:auto; display: none; position:fixed; border: solid 1px #DDDDDD; background-color: #ffffff; margin-left: 260px; margin-top: 130px;"
					align="center">
					<!-- 显示科目树形 -->
					<div style="height: 100%;" align="center">
						<div style="height: 100%;">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</div>
				<table id="addTable" class="table">
					<tr style="border: 0 0 0 0px;">
						<th colspan="10"
							style="font-size: 28px; font-weight: bolder; font-family: 黑体;">
							记账凭证修改
						</th>
					</tr>
					<tr>
						<th colspan="10">
							日期：${cwVouchers.voucherdate}
						</th>
					</tr>
					<tr>
						<th colspan="3" align="left">
							单位:${companyInfo.name}
						</th>
						<th colspan="10" align="right">
							编号 No:${cwVouchers.number}
						</th>
					</tr>
					<tr>
						<th align="center" rowspan="2">
							摘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要
							<br />
							Explanation
						</th>
						<!--<th align="center" rowspan="2">
							一 级 科 目
							<br />
							Gen. Leg. Acc
						</th>  -->
						<th align="center" rowspan="2">
							明 细 科 目
							<br />
							CorrespondingAccounts
						</th>
						<th align="center" rowspan="2">
							外 币 金 额
							<br />
							For. Cur Amount
						</th>
						<th align="center" rowspan="2">
							汇 率
							<br />
							Exc. Rat
						</th>
						<th align="center" colspan="2">
							金 额 Amount
						</th>
						<th rowspan="2">
						    <input type="button" style="font-weight: bolder;" value="添加凭证" onclick="addVouDetail()">
						    <input type="hidden" name="cwVouChersId" id="cwVouId" value="${id}"/>
						</th>
					</tr>
					<tr>
						<th align="center">
							借 方 Debit
						</th>
						<th align="center">
							贷 方 Credit
						</th>			
					</tr>
					<s:iterator value="cwVouchersDetailList" id="pagevdetail" status="pageStatus">
						<tr id="materialtr${pageStatus.index}" align="center" height="34px" >
							<td align="center">
							    <input type="hidden" name="cwVouchersDetailList[${pageStatus.index}].cwVouchers.id" value="${pagevdetail.cwVouchers.id}" />
							    <input type="hidden" name="cwVouchersDetailList[${pageStatus.index}].id" value="${pagevdetail.id}" />
								<input type="text" class="cremark" name="cwVouchersDetailList[${pageStatus.index}].remark"  value="${pagevdetail.remark}"/>
							</td>
							<td>
								<input class="cdeptname" readonly="readonly" value="${pagevdetail.detailSub}"/>
								<a href="javascript:void(0)" style="font-size: 16px;"
									onclick="deptAdd(${pageStatus.index})">选择</a>
								<input type="hidden" class="cdept" value="${pagevdetail.detailSubId}"
									name="cwVouchersDetailList[${pageStatus.index}].detailSub" />
							</td>
							<td>
							</td>
							<td>
							</td>
							<td>
								<s:if test="#pagevdetail.jieMoney<0">
									<input type="text" class="cjieMoney" onkeyup="keyupinput()" onblur="keyupinput()" oninput="keyupinput()" name="cwVouchersDetailList[${pageStatus.index}].jieMoney"  value="${pagevdetail.jieMoney}"/>
								</s:if>
								<s:else>
									<input type="text" class="cjieMoney" onkeyup="keyupinput()" onblur="keyupinput()" oninput="keyupinput()" name="cwVouchersDetailList[${pageStatus.index}].jieMoney"  value="${pagevdetail.jieMoney}"/>
								</s:else>
							</td>
							<td>
								<s:if test="#pagevdetail.daiMoney<0">
									<input type="text" class="cdaiMoney" onkeyup="keyupinput()" onblur="keyupinput()" oninput="keyupinput()"  name="cwVouchersDetailList[${pageStatus.index}].daiMoney"  value="${pagevdetail.daiMoney}"/>
								</s:if>
								<s:else>
									<input type="text" class="cdaiMoney" onkeyup="keyupinput()" onblur="keyupinput()" oninput="keyupinput()" name="cwVouchersDetailList[${pageStatus.index}].daiMoney"  value="${pagevdetail.daiMoney}"/>
								</s:else>
							</td>
							<s:if test="#pagevdetail.cwUseDetailList ==null||#pagevdetail.cwUseDetailList.size==0">
							    <td>
							        <input type="button" style="font-weight: bolder;" value="删除凭证"  onclick="deleteVouDetail(${pageStatus.index})">
						            &nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">无辅助明细</a>&nbsp;&nbsp;&nbsp;&nbsp;
						            <input type="button" style="font-weight: bolder;" value="添加辅助明细" onclick="addUseDetail(${pageStatus.index})">	
						            <input id="num${pageStatus.index}" type="hidden" value="${fn:length(pagevdetail.cwUseDetailList)}">
								    <input id="int${pageStatus.index}" type="hidden" value="0">
						        </td>
							</s:if>
							<s:else>
								<td>
								    <input type="button" style="font-weight: bolder;" value="删除凭证"  onclick="deleteVouDetail(${pageStatus.index})">
							        <input type="button" style="font-weight: bolder;" value="打开辅助明细"  onclick="openUseDetail(${pageStatus.index})"> 
							        <input type="hidden" value="no" id="fzdetail${pageStatus.index}">	
							        <input type="button" style="font-weight: bolder;" value="添加辅助明细" onclick="addUseDetail(${pageStatus.index})">	
							        <input id="num${pageStatus.index}" type="hidden" value="${fn:length(pagevdetail.cwUseDetailList)}">
								    <input id="int${pageStatus.index}" type="hidden" value="${fn:length(pagevdetail.cwUseDetailList)}">					        
							    </td>
							</s:else> 
						</tr>
						<s:if test="#pagevdetail.cwUseDetailList !=null&&#pagevdetail.cwUseDetailList.size>0">						    
							<tr id="materialtr0${pageStatus.index}" align="center" height="30px" style="display: none;">
							    <td colspan="7">
							        <table class="table" width=100% id="addTableuse${pageStatus.index}">
							        <tr></tr> 
								        <s:iterator value="#pagevdetail.cwUseDetailList" id="cwuse" status="status">								            
								            <tr id="materialtrfz0${pageStatus.index}${status.index}">								                
								                <td width=28% align="right"> 
								                                                     辅&nbsp;助&nbsp;明&nbsp;细&nbsp;&nbsp;：
								                </td>
											    <td colspan="0">
											                     摘要：<input type="text" name="cwVouchersDetailList[${pageStatus.index}].cwUseDetailList[${status.index}].useFor"  value="${cwuse.useFor}"  style="width:190px;"/>
											    </td>
											    <td colspan="0">
											                     收款单位：<input type="text" name="cwVouchersDetailList[${pageStatus.index}].cwUseDetailList[${status.index}].payee"  value="${cwuse.payee}"/>
											    </td>
											    <td colspan="0">
											                     使用金额：<input type="text" name="cwVouchersDetailList[${pageStatus.index}].cwUseDetailList[${status.index}].usemoney"  value="${cwuse.usemoney}"/>
											                 <input type="hidden" name="cwVouchersDetailList[${pageStatus.index}].cwUseDetailList[${status.index}].id"  value="${cwuse.id}"/>    
											    </td>
											    <td align="center">
											        <input type="button" value="刪除辅助明细"  onclick="deleteUseDetail(${pageStatus.index},${status.index})">
											    </td>
								            </tr>
							            </s:iterator>
							        </table>
							    </td>				    
							</tr>							
						</s:if>
						<s:else>
						    <tr id="materialtr0${pageStatus.index}" align="center" height="30px" style="display:none;">
							    <td colspan="7">
							        <table class="table" width=100% id="addTableuse${pageStatus.index}">
							          <tr></tr> 
							        </table>
							    </td>
							</tr>
						</s:else>					
					</s:iterator>
					<tr>
					    <td colspan="4" align="right">
						          合计：
						</td>
						<td align="center" id="jfje">
						</td>
						<td align="center" id="dfje">
						</td>
						<td colspan="0">
						</td>
					</tr>
					<tr align="center">
						<th colspan="7">
							<input style="font-size:16px;" type="button" value="提交" onclick="check()" />
						</th>
					</tr>
				</table>
				</form>
			</div>
		</div>
<%@include file="/util/foot.jsp"%>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function sbrSelect(obj) {
	var selected = obj.id;//获取到当前列的id
	$.ajax( {
		type : "POST",
		url : "SubjectBudgetAction!findAllSBRate.action",
		data : {},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, n) {
				$(obj).append("<option value='" + n.id + "' >" + n.name+ "</option>");
			})
		}
	});
}

  
var size = <s:property value="cwVouchersDetailList.size()"/>  //集合长度
var listTrMaxIndex = parseInt(size)-parseInt(1);
var listTrSiz = parseInt(size)*2;
//添加凭证
function addVouDetail(){	
	listTrMaxIndex++;
	var html = "<tr id='materialtr"+listTrMaxIndex+"' align='center'>"
		+ "<td align='center'><input type='hidden' name='cwVouchersDetailList["+listTrMaxIndex+"].id' value='-1'/><input type='text' class='cremark' name='cwVouchersDetailList["+listTrMaxIndex+"].remark'  value=''/></td>"
		+ "<td><input class='cdeptname' readonly='readonly' value=''/><a href='javascript:void(0)' style='font-size: 16px;' onclick='deptAdd("+listTrMaxIndex+")'>&nbsp;选择</a><input type='hidden' class='cdept' value='' name='cwVouchersDetailList["+listTrMaxIndex+"].detailSub'/></td>"
		+ "<td></td><td></td>"
		+ "<td><input type='text' class='cjieMoney' onkeyup='keyupinput()' onblur='keyupinput()' oninput='keyupinput()' name='cwVouchersDetailList["+listTrMaxIndex+"].jieMoney' value=''/></td>"
		+ "<td><input type='text' class='cdaiMoney' onkeyup='keyupinput()' onblur='keyupinput()' oninput='keyupinput()' name='cwVouchersDetailList["+listTrMaxIndex+"].daiMoney' value=''/></td>"
		+ "<td><input type='button' style='font-weight: bolder;' value='删除凭证' onclick='deleteVouDetail("+listTrMaxIndex+")'>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)'>无辅助明细</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' style='font-weight: bolder;' value='添加辅助明细' onclick='addUseDetail("+listTrMaxIndex+")'><input id='num"+listTrMaxIndex+"' type='hidden' value='0'><input id='int"+listTrMaxIndex+"' type='hidden' value='0'></td>"
		+ "</tr>"
		+ "<tr id='materialtr0"+listTrMaxIndex+"' align='center' height='30px' id='ycuse"+listTrMaxIndex+"' style='display: none;'><td colspan='7'><table class='table' id='addTableuse"+listTrMaxIndex+"'><tr></tr></table></td></tr>"
	$("#addTable>tbody>tr").eq(4 + listTrSiz).after(html); 
	listTrSiz = parseInt(listTrSiz) + 2;
			                
		/*var html1 =	"<tr align='center' height='30px' id='ycuse"+listMater+"' style=''><td colspan='7'><table class='table' width=100% ><tr id='materialtrfz0"+listMater+"'>"
		+ "<tr align='center' height='30px' id='ycuse"+listMater+"' style=''><td colspan='7'><table class='table' width=100% ><tr id='materialtrfz0"+listMater+"'>	"
		+ "<td width=30% align='right'>辅&nbsp;助&nbsp;明&nbsp;细:&nbsp;&nbsp;</td>"
		+ "<td colspan='0' align='right'>摘要：<input type='text' name='cwVouchersDetailList["+listMater+"].cwUseDetailList["++"].useFor' value=''/></td>"
		+ "<td colspan='0' align='right'>收款单位：<input type='text' name='cwVouchersDetailList["+listMater+"].cwUseDetailList["++"].payee'  value=''/></td>"
		+ "<td colspan='0' align='right'>使用金额：<input type='text' name='cwVouchersDetailList["+listMater+"].cwUseDetailList["++"].usemoney'  value=''/><input type='hidden' name='cwVouchersDetailList["+listMater+"].cwUseDetailList["++"].id'  value=''/></td>"
		+ "<td><input type='button' value='刪除辅助明细'  onclick='deleteUseDetail(${pageStatus.index},${status.index})'></td></tr></table></td>"
		+ "</tr>"*/
 		
	
}
//删除凭证
function deleteVouDetail(index) {	
	if (listTrSiz == 4) {
		return;
	}
	if ((index - listTrMaxIndex) == 0) {
		listTrMaxIndex--;
	}
	$("#materialtr" + index).remove(); //删除一级标签(凭证)
	$("#materialtr0" + index).remove(); //删除二级标签(辅助明细)
	listTrSiz = parseInt(listTrSiz) - 2;
	
	
	//记录所删除行的id  有id就记录  无id的默认-1   
	
}
//添加辅助明细 
function addUseDetail(index) {
	var num = "num" + index;
	var int = "int" + index;
	var num1 = document.getElementById(num).value;
	var int1 = document.getElementById(int).value;
	var indexfz = parseInt(int1);
	var indexsiz = parseInt(num1);
	var html = "<tr id='materialtrfz0"+index+""+indexfz+"'>"
			+ "<td width=28% align='right'>辅&nbsp;助&nbsp;明&nbsp;细&nbsp;&nbsp;：</td>"
			+ "<td>摘要：<input id='useFor' name='cwVouchersDetailList["+index+"].cwUseDetailList["+indexfz+"].useFor' style='width:190px;'/></td>"
			+ "<td>收款单位：<input id='payee' name='cwVouchersDetailList["+index+"].cwUseDetailList["+indexfz+"].payee'/></td>"
			+ "<td>使用金额：<input id='usemoney' name='cwVouchersDetailList["+index+"].cwUseDetailList["+indexfz+"].usemoney'/><input type='hidden' name='cwVouchersDetailList["+index+"].cwUseDetailList["+indexfz+"].id'  value='-1'/> </td>"
			+ "<td align='center'><input type='button' value='刪除辅助明细' onclick='deleteUseDetail("+index+","+indexfz+")'></td>"
	        + "/tr>"
	var tjxb = "#addTableuse" +index+ ">tbody>tr";
	$(tjxb).eq(indexsiz).after(html);
	document.getElementById(num).value = parseInt(num1) + 1;
	document.getElementById(int).value = parseInt(int1) + 1;
	
	var ycuse = "#materialtr0"+index;
	$(ycuse).show();
}

//删除辅助明细
function deleteUseDetail(index, listindex2) {
	$("#materialtrfz0" + index + "" + listindex2).remove();
	var zhi = parseInt(index);
	var num = "num" + zhi;
	var num1 = document.getElementById(num).value;
	document.getElementById(num).value = parseInt(num1) - 1;
	
	var ycuse = "#materialtr0"+index;
	//alert(document.getElementById(num).value);
	if(document.getElementById(num).value==0){
		$(ycuse).hide();
	}
}

function openUseDetail(index){
    //先获取辅助明细的状态(打开或隐藏)
    var ycuse = "#materialtr0"+index;
    if($(ycuse).is(':hidden')){
		$(ycuse).show();
	}else{
		$(ycuse).hide();
	}
}
function keyupinput(){
	var xsReg=/^\d+(\.\d+)?$/; //小数
	var zsReg=/^\d*$/;         //整数	
    setTimeout(function(){
		//计算借方金额总额和贷方金额总额
		var jieMoney = $(".cjieMoney");
		var daiMoney = $(".cdaiMoney");
		var jze = 0;
        var dze = 0;
		$(jieMoney).each(function(i, n) {
			if($(jieMoney[i]).val()!=null&&$(jieMoney[i]).val()!=""){
			    if(xsReg.test($(jieMoney[i]).val())){
					jze = accAdd(parseFloat(jze),parseFloat($(jieMoney[i]).val()));
				}else if(zsReg.test($(jieMoney[i]).val())){
					jze = accAdd(parseFloat(jze),parseFloat($(jieMoney[i]).val()));
				}else{
					jze = accAdd(parseFloat(jze),parseFloat(0));
				}	
			}else{
				jze = accAdd(parseFloat(jze),parseFloat(0));
			}
			if($(daiMoney[i]).val()!=null&&$(daiMoney[i]).val()!=""){
				if(xsReg.test($(daiMoney[i]).val())){
					dze = accAdd(parseFloat(dze),parseFloat($(daiMoney[i]).val()));
				}else if(zsReg.test($(daiMoney[i]).val())){
					dze = accAdd(parseFloat(dze),parseFloat($(daiMoney[i]).val()));
				}else{
					dze = accAdd(parseFloat(dze),parseFloat(0));
				}		
			}else{
				dze = accAdd(parseFloat(dze),parseFloat(0));
			}		
		});
		$("#jfje").text(jze);
		$("#dfje").text(dze);
    });
}
function accAdd(num1,num2){   //小数相加
    var r1,r2,m;
    try{
        r1 = num1.toString().split('.')[1].length;
    }catch(e){
        r1 = 0;
    }
    try{
        r2=num2.toString().split(".")[1].length;
    }catch(e){
        r2=0;
    }
    m=Math.pow(10,Math.max(r1,r2));
    return Math.round(num1*m+num2*m)/m;
}
//form表单提交验证
function check(){
    var remark =$(".cremark");
    var cdept =$(".cdept");
    var jieMoney =$(".cjieMoney");
    var daiMoney =$(".cdaiMoney");
    var xsReg=/^\d+(\.\d+)?$/; //小数
	var zsReg=/^\d*$/;
    var tjsjze=0;
    var tjsdze=0;
    var sta=true;
    $(remark).each(function(i,n){
		var remark1 = $(n).val();
		if(remark1==null&&$(cdept[i]).val()=='-1'||remark1==""&&$(cdept[i]).val()=='-1'){
			if($(jieMoney[i]).val()==null&&$(daiMoney[i]).val()==null||$(jieMoney[i]).val()==""&&$(daiMoney[i]).val()==""){	
				return true;
			}										
		}	
		if(remark1==null||remark1==""){
			alert("请输入第"+(i+1)+"行摘要");
			$(n).focus();	
			sta= false;
		}else if ($(cdept[i]).val()==null||$(cdept[i]).val()==""){
			alert("请选择第"+(i+1)+"行科目");
			$(cdept[i]).focus();	
			sta= false;
		}else if ($(jieMoney[i]).val()==null&&$(daiMoney[i]).val()==null||$(jieMoney[i]).val()==""&&$(daiMoney[i]).val()==""
				||$(jieMoney[i]).val()==""&&$(daiMoney[i]).val()==undefined||$(jieMoney[i]).val()==undefined&&$(daiMoney[i]).val()==""||$(jieMoney[i]).val()==undefined&&$(daiMoney[i]).val()==undefined){
			alert("请输入第"+(i+1)+"借方金额或者贷方金额");
			sta= false;
		}
		if($(jieMoney[i]).val()!=null&&$(jieMoney[i]).val()!=""&&$(daiMoney[i]).val()!=null&&$(daiMoney[i]).val()!=""){
			if(xsReg.test($(jieMoney[i]).val())){
				
			}else if(zsReg.test($(jieMoney[i]).val())){	
				
			}else{
				alert("请输入符合的金额");
			    sta = false;
			}
			if(xsReg.test($(daiMoney[i]).val())){

			}else if(xsReg.test($(daiMoney[i]).val())){
				
			}else{
				alert("请输入符合的金额");
			    sta = false;
			}
		}else if($(jieMoney[i]).val()!=null&&$(jieMoney[i]).val()!=""){
			if(xsReg.test($(jieMoney[i]).val())){
				
			}else if(zsReg.test($(jieMoney[i]).val())){	
				
			}else{
				alert("请输入符合的金额");
			    sta = false;
			}		
		}else if($(daiMoney[i]).val()!=null&&$(daiMoney[i]).val()!=""){
			if(xsReg.test($(daiMoney[i]).val())){

			}else if(xsReg.test($(daiMoney[i]).val())){
				
			}else{
				alert("请输入符合的金额");
			    sta = false;
			}
		}
		if($(jieMoney[i]).val()!=null&&$(jieMoney[i]).val()!=""){
			tjsjze = accAdd(parseFloat($(jieMoney[i]).val()),parseFloat(tjsjze));
		}else{
			tjsjze = accAdd(parseFloat(0),parseFloat(tjsjze));
		}
		
		if($(daiMoney[i]).val()!=null&&$(daiMoney[i]).val()!=""){
			tjsdze = accAdd(parseFloat($(daiMoney[i]).val()),parseFloat(tjsdze));
		}else{
			tjsdze = accAdd(parseFloat(0),parseFloat(tjsdze));
		}
		//return sta;
		if(sta==false){
    	    return false;
        }
	});
   
    if(tjsjze==tjsdze){
    	var cwVouId = $("#cwVouId").val();
    	//alert(cwVouId);
    	//ajax提交信息
    	if(sta){
	    	$.ajax({
	            async: false,
	            type: "POST",
	            url:'CwVouchersAction!updateDetailList.action',
	            data:$("#fromcw").serialize(),
	            dataType: "json",
	            success: function (data) {
	        	    alert(data);
	        	    if(data=="修改成功"){
	        	    	location.href="CwVouchersAction!showDetailList.action?id="+cwVouId;
	        	    }
	              },
	            error: function () {
	                alert("修改失败");  
	            }
	        })
    	}
		return sta;
	}else{
		alert("请检查借贷总额是否正确");
		return false;
	}
}
			 
			 
			 //========================================zTree显示
var id, pId, name, subjectRate, rootId, belongLayer;
//自动组装树形结构
var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	mfzTree();
});
var mfzTree =function() {
	$.ajax( {
		url : 'SubjectBudgetAction!findAllSBRate.action',
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							name : $(this).attr('name'),
							hiddenName : $(this).attr('name'),
							subjectRate : $(this).attr('subjectRate'),
							pId : $(this).attr('fatherId'),
							rootId : $(this).attr('rootId'),
							belongLayer : $(this).attr('belongLayer'),
							click : false,
						});
					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};


var indexDeptName;
var indexDeptId; //点击选择时确认所选行下标
function deptAdd(obj){ 
	var vl = "#materialtr"+obj;
	indexDeptName = $(vl).children("td").eq(1).children("input").eq(0);
	indexDeptId = $(vl).children("td").eq(1).children("input").eq(1);
	if($('#contentDiv').is(':hidden')){
		$("#contentDiv").show();
	}else{
		$("#contentDiv").hide();
	}	
}
//点击回调函数
function onClick(event, treeId, treeNode, clickFlag){
	if(!treeNode.isParent){
		indexDeptName.val(treeNode.hiddenName);
		indexDeptId.val(treeNode.id);
		if($('#contentDiv').is(':hidden')){
			$("#contentDiv").show();
		}else{
			$("#contentDiv").hide();
		}
		mfzTree();
		//alert(treeNode.id);
		//赋值
		$("#id").val(treeNode.id);
		$("#rootId").val(treeNode.rootId);
		$("#fatherId").val(treeNode.id);
		$("#belongLayer").val(treeNode.belongLayer + 1);
		$("#name").val(treeNode.hiddenName);
		$("#fatherName").val(treeNode.hiddenName);
		$("#subjectRate").val(treeNode.subjectRate);
	}else{
		//alert("请选择明细科目!");   
		//点击父标签时代码块
	}
}


//=================================== zTree显示结束
</script>
	</body>
</html>

