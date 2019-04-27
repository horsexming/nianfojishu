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
			<link rel="stylesheet"
			href="${pageContext.request.contextPath}/javascript/layer/theme/default/layer.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/layer/layer.js">
</script>
	<SCRIPT type="text/javascript">
	
	$(document).ready(function() {
		var rebeack = rebeack = '${errorMessage}';
	if (rebeack == "true") {
		alert('处理完成!');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
	
	
	</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<br>
				<form action="WaigouwaiweiPlanAction!DefChuLiSq.action" method="post" onsubmit="return fenjiancheck()" enctype="multipart/form-data" id="myform">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								${dp.markId}
							</td>
							<th align="right">
								品名
							</th>
							<td>
								${dp.proName}
							</td>
							<th align="right">
								物料类别
							</th>
							<td>
								${dp.wgType}
							</td>
							<th align="right">
								供货属性
							</th>
							<td>
								${dp.kgliao}
							</td>
						</tr>
						<tr>
							<th align="right">
								送货单号
							</th>
							<td>
								${dp.shOrderNum}
							</td>
							<th align="right">
								采购订单编号
							</th>
							<td>
								${dp.cgOrderNum}
							</td>
							<th align="right">
								规格
							</th>
							<td>
								${dp.specification}
							</td>
							<th align="right">
								单位
							</th>
							<td>
								${dp.unit}
							</td>
						</tr>
						<tr>
							<th align="right">
								检验批次
							</th>
							<td>
								${dp.examineLot}
							</td>
							<th align="right">
								 版本
							</th>
							<td>
								${dp.banben}
							</td>
							<th align="right">
								供应商名称
							</th>
							<td>
								${dp.gysName}
							</td>
							<th align="right">
								图号
							</th>
							<td>
								${dp.tuhao}
							</td>
						</tr>
						<tr>
							
							<th align="right">
								不良类型
							</th>
							<td>
								${dp.type}
							</td>
							<th align="right">
								检验人姓名
							</th>
							<td>
								${dp.jyuserName}
							</td>
							<th align="right">
								检验人工号
							</th>
							<td>
								${dp.jyuserCode}
							</td>
							<th align="right">
								检验时间
							</th>
							<td>
								${dp.checkTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								来料数量
							</th>
							<td>
								${dp.llNumber}
							</td>
							<th align="right">
								检验数量
							</th>
							<td>
								${dp.jyNumber}
							</td>
							<th align="right">
								检验合格数量
							</th>
							<td>
								${dp.jyhgNumber}
							</td>
							<th align="right">
								检验不合格数量
							</th>
							<td>
								${dp.jybhgNumber}
							</td>
						</tr>
						<tr>
							<th align="right" colspan="1">
								本批次是否合格
							</th>
							<td colspan="1">
								${dp.ishege}
							</td>
								<th align="right">
									处理结果
								</th>
								<td  colspan="2">
									<input type="radio" value="挑选" name="dp.result" title="分拣" onclick="isAgainCheck(this)" checked="checked" />挑选
									<input type="radio" value="退货" name="dp.result" title="来料不良" onclick="tuhui()"  />退货
									<input type="radio" value="特采" name="dp.result" title="让步接收" onclick="chaoyue()"/>特采
									<input type="radio" value="报废" name="dp.result" title="在制不良" />报废
									<input type="hidden" value="${dp.zjbhgNumber}" name="dp.zjbhgNumber" id="buhegeNumber"/>
									<input type="hidden" value="${dp.zjhgNumber}" name="dp.zjhgNumber" id="hegeNumber"/>
									<input type="hidden" value="${dp.id}" name="dp.id" id=""/>
									<input type="hidden" value="挑选" id="result"/>
								</td>
								<th align="right" id="fenjian_th">
									挑选后数量
								</th>
								<td colspan="2" id="fenjian_td">
									<input type="text" value="${dp.fenjianNum}" name="dp.fenjianNum" id="fenjianNum"
									onkeyup="numyanzheng(this);daxiao(this)"
									 onchange="numyanzheng(this);daxiao(this)"/>
								</td>
						</tr>
						<tr>
							<th align="right">
								上传附件
							</th>
							<td colspan="">
								<input type="file"  name="attachment">
							</td>
							<th align="right">
								不良描述
							</th>
							<td colspan="10">
								<textarea rows="1" cols="30" name="dp.ramk">${dp.ramk}</textarea>
							</td>
						</tr>
						<s:if test="pageStatus!='admins'">
						<tr>
						<th align="right">
								审批人员
							</th>
						<td colspan="10">
						     <div id="freeDeptDiv" >
								<font color="red">审批人员:</font><input type="button" value="增加" onclick="addFreeDept()" style="width: 60px;height: 30px">
								<hr/>
								<ul id="freeDeptUl0">
									<li id="freeDeptli0">
									 <SELECT id="zrdept0" name="approvalId" onchange="changefreeDept(0)"></SELECT>
									 <SELECT id="zrpeople0" name="ids"></SELECT>
									 <input type="button" value="删除" onclick="deleteFreeDept(0)" style="width: 60px;height: 30px">
									</li>
								</ul>
								</div>
							</td>	
						</tr>
						<tr>
							<th align="right">
								推送人员
								<br />
								<br />
								<input id="test2" type="button" value="选择推送人员">
							</th>
							<td colspan="3">
								<input id="fid" name="tuisongId    "
									value="" readonly="readonly"
									type="hidden" />
								<textarea id="tishiPerson" name="tishiPerson"
									rows="5" cols="80" readonly="readonly"></textarea>
							</td>
						</tr>
						</s:if>
					</table>
				<s:if test='dp.status == "待确认"'>
					<input type="hidden" value="${dp.id}" name="id"/>
					<s:if test='dp.againcheck == "待复检"'>
					</s:if>
					<s:elseif test='dp.againcheck == "待分检"'>
					</s:elseif>
				</s:if>
				<s:if test='dp.status == "待确认"&&pageStatus=="dqr" '>
					<input type="submit" id="sub" value="处理"  class="input" />
				</s:if>
					<input type="button"  value="查看图纸" id="sub"  onclick="javascript:location.href='WaigouwaiweiPlanAction!gysTzview.action?waigouPlan.markId=${dp.markId}&waigouPlan.banci=${dp.banci}';"
						style="width: 105px; height: 50px;"/>
				</form>
			</div>
			
			<table class="table" >
									<tr bgcolor="#c0dcf2" height="50px">
										<th>
											需检项
										</th>
										<th>
											质量特征
										</th>
										<th>
											检查方法
										</th>
										<th>
											检查结果
										</th>
									</tr>
									<s:iterator value="list" id="pageList" status="pagestatus">
									<tr>
										<td>${pageList.scope.content}</td>
										<td>${pageList.scope.zltz}</td>
										<td>${pageList.scope.jcff}</td>
										<td>${pageList.content}</td>
									</tr>
									</s:iterator>
								</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
function changbhgNum(){
		$("#bhgNumber").val(${waigoudd.qrNumber});
	var obj = document.getElementById("bhgNumber");
		changvalue('bhgNumber',${dp.llNumber});
}	
function changbhgNum1(){
	$("#bhgNumber").val("");
	var obj = document.getElementById("bhgNumber");
		changvalue('hgNumber',${dp.llNumber});
}
	
function changvalue1(obj,num){
	var num = num;
	if(num == ''){
		num = $("#"+obj).val();
	}
	if(num!=''){
	 var bhgNum = 0;
	 var hgNum = 0;
	 var znum = ${dp.llNumber};
	 var aa ='${dp.zhuanhuanNum}'
	 if(aa!=''){
		 znum = parseInt(aa);
	 }
	if(obj == "hgNumber"){
		hgNum = num;
		bhgNum =  znum- num;
	}else{
		bhgNum =  num;
		hgNum = znum - bhgNum;
	}
	if(hgNum <0 || bhgNum <0){
		alert("合格数量和不合格数量都不能小于0，请重新输入!");
		hgNum = '';
		bhgNum = '';
	}
		$("#zifont").html(bhgNum);
		$("#bhgNumber").val(bhgNum);
		$("#hgNumber").val(hgNum);
		$("#sub").removeAttr("disabled");
	}else{
		$("#zifont").html('');
		$("#bhgNumber").val('');
		$("#hgNumber").val('');
		$("#sub").attr("disabled","disabled");
		
	}
	}
	
<%--	function jisuan(obj) {--%>
<%--	var value = $("#" + obj).val();--%>
<%--if(value !=null && value != ""){--%>
<%--	  	$.ajax( {--%>
<%--		type : "POST",--%>
<%--		url : "yuanclAndWaigjAction!getYWbytrademark.action",--%>
<%--		data : {--%>
<%--			'yuanclAndWaigj.markId':'${dp.markId}',--%>
<%--		},--%>
<%--		dataType : "json",--%>
<%--		success : function(data) {--%>
<%--			if(data!=null && data>0){--%>
<%--				if(obj == "bhgNumber"){--%>
<%--					$("#bhgNumber").val((value*data).toFixed(3))--%>
<%--				}else if(obj == "hgNumber"){--%>
<%--					$("#hgNumber").val((value*data).toFixed(3))--%>
<%--				}--%>
<%--				changvalue(obj,'');--%>
<%--			}else{--%>
<%--			alert("该原材料没有相关的单张重量")--%>
<%--		}--%>
<%--		}--%>
<%--	})--%>
<%----%>
<%--  }else{--%>
<%--	  alert("请先输入数量")--%>
<%--  }--%>
<%--	--%>
<%--}--%>

function isAgainCheck(obj){
	var isAgainCheck = obj.value;
	var fenjianNum =0;
	if(isAgainCheck == '挑选'){
		fenjianNum =  $("#fenjianNum").val();
		if( fenjianNum == ''){
			alert('请填写挑选后数量!');
			$("#fenjian_th").show();
			$("#fenjian_td").show();
			$("#result").val("挑选");
			return;
		}
	}
	$("#fenjian_th").show();
	$("#fenjian_td").show();
	$("#result").val(isAgainCheck);
	
}
function fenjiancheck(){
	var isAgainCheck = $("#result").val();
	if(isAgainCheck == '挑选'){
		var fenjianNum =  $("#fenjianNum").val();
		if(fenjianNum == null || fenjianNum == ''){
			$("#sub").removeAttr("disabled");
			alert('请填写挑选后数量!');
			return false;
		}
	}
	$("#sub").attr("disabled","disabled")
}

function tuhui(){
	if('${dp.type}'.indexOf('在库不良')>=0){
		$("#buhegeNumber").val(${dp.dbNumber});
	}else{
		$("#buhegeNumber").val(${dp.llNumber});
	}
	$("#hegeNumber").val(0);
	$("#fenjian_th").hide();
	$("#fenjian_td").hide();
	$("#result").val("退货");
}
function chaoyue(){
	$("#buhegeNumber").val(0);
	if('${dp.type}'.indexOf('在库不良')>=0){
		$("#hegeNumber").val(${dp.dbNumber});
	}else{
		$("#hegeNumber").val(${dp.llNumber});
	}
	$("#fenjian_th").hide();
	$("#fenjian_td").hide();
	$("#result").val("特采");
}
$(function() {
	var i=0;
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						var html="";
						if(this.dept=="${Users.dept}"){
							html="<option selected='selected' value='" + this.id + "'>" + this.dept+ "</option>";
							
						}else{
							html="<option value='" + this.id + "'>" + this.dept+ "</option>";
						}
						$(html).appendTo("#zrdept"+i);
					});
				changefreeDept(i);
				$("#zrdept"+i).tinyselect();
		}
	});
});
	function changefreeDept(i){
	var deptId=$("#zrdept"+i).val();
	if(deptId>0){
	$.ajax( {
		type : "post",
		url : "AskForLeaveAction!getDeptUsers.action",
		dataType : "json",
		data : {
			id : deptId
		},
		success : function(data) {
			//填充部门信息
			var selectbox=$("#freeDeptUl"+i+" .tinyselect");
			if(selectbox.length>1){
				var len=selectbox.length-1;
				for(var n=len;n>=1;n--){
					$(selectbox[n]).remove();
				}
			}
			$("#zrpeople"+i).empty();
			$("#zrpeople"+i).append("<option value=''>--请选择--</option>");
			$(data).each(
					function() {
						var html="<option value='" + this.userId + "'>" + this.userName+ "</option>";
						$(html).appendTo("#zrpeople"+i);
					});
			$("#zrpeople"+i).tinyselect();
			
		}
	});
	}
}
 var deptIndex=0;
function setDept(i){
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						var html="";
						if(this.dept=="${Users.dept}"){
							html="<option selected='selected' value='" + this.id + "'>" + this.dept+ "</option>";
						}else{
							html="<option value='" + this.id + "'>" + this.dept+ "</option>";
						}
						$(html).appendTo("#zrdept"+i);
					});
				changefreeDept(i);
				$("#zrdept"+i).tinyselect();
		}
	});
}
function changefreeDept(i){
	var deptId=$("#zrdept"+i).val();
	if(deptId>0){
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getusers.action",
		dataType : "json",
		data : {
			id : deptId
		},
		success : function(data) {
			//填充部门信息
			var selectbox=$("#freeDeptUl"+i+" .tinyselect");
			if(selectbox.length>1){
				var len=selectbox.length-1;
				for(var n=len;n>=1;n--){
					$(selectbox[n]).remove();
				}
			}
			$("#zrpeople"+i).empty();
			$("#zrpeople"+i).append("<option value='0'>--请选择--</option>");
			$(data).each(
					function() {
						var html="<option value='" + this.id + "'>" + this.name+ "</option>";
						$(html).appendTo("#zrpeople"+i);
					});
			$("#zrpeople"+i).tinyselect();
			
		}
	});
	}
}
function addFreeDept(){
	deptIndex++;
	var html="<ul id='freeDeptUl"+deptIndex+"'> <hr/>" +
	"<li id='freeDeptli"+deptIndex+"'>" +
	"<SELECT id='zrdept"+deptIndex+"' name='approvalId' onchange='changefreeDept("+deptIndex+")'></SELECT>" +
	"<SELECT id='zrpeople"+deptIndex+"' name='ids'></SELECT>" +
	"<input type='button' value='删除' onclick='deleteFreeDept("+deptIndex+")' style='width: 60px;height: 30px'>" +
	"</li></ul>"
	$(html).appendTo("#freeDeptDiv");
	setDept(deptIndex);
}
function deleteFreeDept (index){
	$("#freeDeptUl"+index).remove();
}
$('#test2').on('click', function(){
layer.open({
  type: 2,
  title: '选择推送人员',
  area: ['450px', '700px'],
  fixed: '200px', //不固定
  maxmin: true,
  content: '<%=basePath%>/System/systemfile/checkPersonForhege.jsp'
});
});


function daxiao(obj){
	if(obj!=null){
		var num = obj.value;
		if(num!=''){
			num = parseFloat(num);
			var llnum = parseFloat('${dp.llNumber}')
			var	msg = '分拣后数量不能大于来料数量。';
			if('${dp.type}'.indexOf('在库不良')>=0){
				llnum =parseFloat('${dp.dbNumber}')
				msg = '分拣后数量不能大于调拨数量。';
			}
		 	$("#buhegeNumber").val(llnum-num);
		 	$("#hegeNumber").val(num);
			if(num>llnum){
				alert(msg);
				$("#fenjianNum").val(llnum);
			}
		}
	}
}
	</SCRIPT>
	</body>
</html>
