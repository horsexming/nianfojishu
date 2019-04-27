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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<form id="fromcw" action="CwVouchersAction!insertCwVouchersDetail.action" method="post">
				<div id="contentDiv" 
					style="height:200px;overflow-y:auto; display: none; position:fixed; border: solid 1px #DDDDDD; background-color: #ffffff; margin-left: 300px; margin-top: 100px;"
					align="center">
					<!-- 显示科目树形 -->
					<div style="height: 100%;" align="center">
						<div style="height: 100%;">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</div>
					<table id="addTable" class="table" style="line-height: 30px;"
						align="center">
						<tr>
							<td colspan="7" align="center">
								<font style="font-size: 21px; font-weight: bolder;">记账凭证</font>
							</td>
						</tr>
						<tr style="font-size: 19px;">
							<th width=20%>
								摘要
							</th>
							<th width=20%>
								明细科目
							</th>
							<th width=20%>
								借方金额
							</th>
							<th width=20%>
								贷方金额
							</th>
							<th colspan="2" width=20%>
								<input type="button" style="font-weight: bolder;" value="添加凭证"
									onclick="addVouDetail()">
							</th>
						</tr>
						<tr align="center" id="materialtr0">
							<td width=20%>
								<input id="remark" class="cremark" style="width: 230px;"
									name="cwVouchersDetailList[0].remark" />
							</td>
							<td width=20%>
								<input class="cdeptname" readonly="readonly" value=""/>
								<a href="javascript:void(0)" style="font-size: 16px;"
									onclick="deptAdd(0)">选择</a>
								<input type="hidden" class="cdept" value=""
									name="cwVouchersDetailList[0].detailSub" />
							</td>
							<td width=20%>
								<input id="jieMoney" class="cjieMoney"
									onkeyup="keyupinput()" onblur="keyupinput()" oninput="keyupinput()"
									name="cwVouchersDetailList[0].jieMoney" />
							</td>
							<td width=20%>
								<input id="daiMoney" class="cdaiMoney"
									onkeyup="keyupinput()" onblur="keyupinput()" oninput="keyupinput()"
									name="cwVouchersDetailList[0].daiMoney" />
							</td>
							<td width=8%>
								<input type="button" style="font-weight: bolder;" value="删除凭证"
									onclick="deleteVouDetail(0)">
							</td>
							<td width=12%>
								<input type="button" style="font-weight: bolder;" value="添加辅助明细"
									onclick="addUseDetail(0)">
								<input id="num0" type="hidden" value="0">
								<input id="int0" type="hidden" value="0">
							</td>
						</tr>
						<tr id="materialtr00">
							<td colspan="6">
								<table class="table" id="addTableuse0">
									<tr></tr>
								</table>
							</td>
						</tr>


						<tr align="center" id="materialtr1">
							<td width=20%>
								<input id="remark1" class="cremark" style="width: 230px;"
									name="cwVouchersDetailList[1].remark" />
							</td>
							<td width=20%>
								<input class="cdept" readonly="readonly" value="" />
								<a href="javascript:void(0)" style="font-size: 16px;"
									onclick="deptAdd(1)">选择</a>
								<input type="hidden" class="cdept" value=""
									name="cwVouchersDetailList[1].detailSub" />
							</td>
							<td width=20%>
								<input id="jieMoney1" class="cjieMoney"
									onkeyup="keyupinput()" onblur="keyupinput()" oninput="keyupinput()"
									name="cwVouchersDetailList[1].jieMoney" />
							</td>
							<td width=20%>
								<input id="daiMoney1" class="cdaiMoney"
									onkeyup="keyupinput()" onblur="keyupinput()" oninput="keyupinput()"
									name="cwVouchersDetailList[1].daiMoney" />
							</td>
							<td width=8%>
								<input type="button" style="font-weight: bolder;" value="删除凭证"
									onclick="deleteVouDetail(1)">
							</td>
							<td width=12%>
								<input type="button" style="font-weight: bolder;" value="添加辅助明细"
									onclick="addUseDetail(1)">
								<input id="num1" type="hidden" value="0">
								<input id="int1" type="hidden" value="0">
							</td>
						</tr>
						<tr id="materialtr01">
							<td colspan="6">
								<table class="table" id="addTableuse1">
									<tr></tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
							          合计:
							</td>
							<td align="center" id="jfje">
							</td>
							<td align="center" id="dfje">
							</td>
							<td colspan="2">
							</td>
						</tr>
					</table>
					<table class="table" style="font-size: 19px;">
						<tr>
							<td width=20% align="left">
								制单人:${session.Users.name}
							</td>
							<td width=60%>
							</td>
							<td width=20% align="right">
								日期:
								<input id="createTime" style="height: 26px;" class="Wdate"
									type="text" name="createTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>

					</table>
					<table class="table" style="font-size: 19px;">
						<tr>
							<td colspan="2" align="center">
								<input style="font-size: 16px;" type="button" value="提交" onclick="check()" />
							</td>
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
				$(obj)
						.append(
								"<option value='" + n.id + "' >" + n.name
										+ "</option>");
			})
		}
	});
}

var listTrMaxIndex = 1;
var listTrSiz = 1;
//添加凭证
function addVouDetail() {
	listTrMaxIndex++;
	var html = "<tr id='materialtr"+listTrMaxIndex+"'>"
			+ "<td align='center'><input id='remark' class='cremark' style='width:230px;' name='cwVouchersDetailList["+listTrMaxIndex+"].remark'></td>"
			+ "<td align='center'><input class='cdeptname' readonly='readonly' value=''/><a href='javascript:void(0)' style='font-size: 16px;' onclick='deptAdd("+listTrMaxIndex+")'>选择</a><input type='hidden' class='cdept' value='' name='cwVouchersDetailList["+listTrMaxIndex+"].detailSub'/></td>"
			+ "<td align='center'><input id='jieMoney' class='cjieMoney' onkeyup='keyupinput()' onblur='keyupinput()' oninput='keyupinput()' name='cwVouchersDetailList["+listTrMaxIndex+"].jieMoney' ></td>"
			+ "<td align='center'><input id='daiMoney' class='cdaiMoney' onkeyup='keyupinput()' onblur='keyupinput()' oninput='keyupinput()' name='cwVouchersDetailList["+listTrMaxIndex+"].daiMoney' ></td>"
			+ "<td align='center'><input type='button' style='font-weight: bolder;' value='刪除凭证'  onclick='deleteVouDetail("+listTrMaxIndex+")'></td>"
			+ "<td align='center'><input type='button' style='font-weight: bolder;' value='添加辅助明细' onclick='addUseDetail("+listTrMaxIndex+")'><input id='num"+listTrMaxIndex+"' type='hidden' value='0'><input id='int"+listTrMaxIndex+"' type='hidden' value='0'></td>" 
			+ "</tr>"
			+ "<tr id='materialtr0"+listTrMaxIndex+"'><td colspan='6'><table class='table' id='addTableuse"+listTrMaxIndex+"'><tr></tr></table></td></tr>"
	$("#addTable>tbody>tr").eq(4 + listTrSiz).after(html);
	listTrSiz = parseInt(listTrSiz) + 2;
}
//删除凭证
function deleteVouDetail(index) {
	if (listTrSiz == 1) {
		return;
	}
	if ((index - listTrMaxIndex) == 0) {
		listTrMaxIndex--;
	}
	$("#materialtr" + index).remove(); //删除一级标签(凭证)
	$("#materialtr0" + index).remove(); //删除二级标签(辅助明细)
	listTrSiz = parseInt(listTrSiz) - 2;
}

var intIndex = 0;
//添加辅助明细 
function addUseDetail(index) {
	var num = "num" + index;
	var int = "int" + index;
	var num1 = document.getElementById(num).value;
	var int1 = document.getElementById(int).value;
	var indexfz = parseInt(int1);
	var indexsiz = parseInt(num1);
	var html = "<tr id='materialtrfz0"+index+""+indexfz+"'>"
			+ "<td width=30% align='right'>辅&nbsp;助&nbsp;明&nbsp;细&nbsp;&nbsp;</td>"
			+ "<td>摘要:<input id='useFor' name='cwVouchersDetailList["+index+"].cwUseDetailList["+indexfz+"].useFor' style='width:200px;'/></td>"
			+ "<td>收款单位:<input id='payee' name='cwVouchersDetailList["+index+"].cwUseDetailList["+indexfz+"].payee'/></td>"
			+ "<td>使用金额:<input id='usemoney' name='cwVouchersDetailList["+index+"].cwUseDetailList["+indexfz+"].usemoney'/></td>"
			+ "<td align='center'><input type='button' value='刪除辅助明细' onclick='deleteUseDetail("+index+","+indexfz+")'></td>"
	        + "/tr>"
	var tjxb = "#addTableuse" + index + ">tbody>tr";
	$(tjxb).eq(indexsiz).after(html);
	document.getElementById(num).value = parseInt(num1) + 1;
	document.getElementById(int).value = parseInt(int1) + 1;
}

//删除辅助明细
function deleteUseDetail(index, listindex2) {
	$("#materialtrfz0" + index + "" + listindex2).remove();
	var zhi = parseInt(index);
	var num = "num" + zhi;
	var num1 = document.getElementById(num).value;
	document.getElementById(num).value = parseInt(num1) - 1;
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

function check() {
	var createTime = document.getElementById("createTime").value;
	var remark = $(".cremark");
	var cdept = $(".cdept");
	var jieMoney = $(".cjieMoney");
	var daiMoney = $(".cdaiMoney");
	var xsReg=/^\d+(\.\d+)?$/; //小数
	var zsReg=/^\d*$/;         //整数
	var tjsjze=0;
    var tjsdze=0;
	var sta = true;
	$(remark).each(function(i, n) {
		var remark1 = $(n).val();
		if ($(remark[i]).val() == null || $(remark[i]).val() == "") {
			alert("请输入第" + (i + 1) + "行摘要");
			$(n).focus();
			sta = false;
		} else if ($(cdept[i]).val()=="") {
			alert("请选择第" + (i + 1) + "行科目");
			$(cdept[i]).focus();
			sta = false;
		} else if ($(jieMoney[i]).val() == null
				&& $(daiMoney[i]).val() == null
				|| $(jieMoney[i]).val() == ""
				&& $(daiMoney[i]).val() == "") {
			alert("请输入第" + (i + 1) + "借方金额或者贷方金额");
			sta = false;
		} else if ($(jieMoney[i]).val() != null&& $(daiMoney[i]).val() != null
				&& $(jieMoney[i]).val() != ""&& $(daiMoney[i]).val() != ""
				&&!(isNaN($(jieMoney[i]).val()))&&!(isNaN($(daiMoney[i]).val()))
				&&$(jieMoney[i]).val()>0&&$(daiMoney[i]).val()>0) {
			alert("请检查借方金额和贷方金额是否符合格式");
			sta = false;
		} else if (i > 0 && createTime == null || i > 0&& createTime == "") {
			alert("请选择日期");
			sta = false;
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
			return sta;
		}
	});
	if(sta==true){
		if(tjsjze==tjsdze){
			//ajax提交信息
	        $.ajax({
	            async: false,
	            type: "POST",
	            url:'CwVouchersAction!insertCwVouchersDetail.action',
	            data:$("#fromcw").serialize(),
	            dataType: "json",
	            success: function (data) {
	        	    alert(data);
	              },
	            error: function () {
	                alert("修改失败");  
	            }
	        })
			return sta;
		}else{
			alert("请检查金额是否正确");
			return false;
		}
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
