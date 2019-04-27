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
				<font size="5" color="red"> <span id="xcg_span"> </span> <span
					id="sjcg_span"> </span> <span id="msg_span"> </span> </font>
				<table class="table">
					<tr>
						<th colspan="10">
							<font color="red">件号${wgmoq.markId} 需采购数量:
								${wgmoq.cgNumber}
								<input type="hidden" value="${wgmoq.cgNumber}" id="cgNum" />
								 </font>
						</th>
					</tr>
					<tr>
						<th colspan="10">
							请先选择是否启用MOQ采购机制:
							<input type="button" value="启用" onclick="yesUse()" id="yesUse" />
							<input type="button" value="不启用" onclick="NoUse()" id="NoUse" />
						</th>
					</tr>
					<tr>
						<th colspan="10">
							供应商分配数量信息
						</th>
					</tr>
					<tr>
						<th>
							供应商
						</th>
						<th>
							供应商编号
						</th>
						<th>
							按比例数量
						</th>
						<th>
							按MOQ数量
						</th>
						<th>
							实际采购量
						</th>
					</tr>
					<s:iterator value="zhuserList" id="pagezhuser" status="pagestatus">
						<tr>
							<th align="left">
								${pagezhuser.name}
								<input type="hidden" value="${pagezhuser.name}"
									id="name_${pagestatus.index}" />
							</th>
							<th>
								${pagezhuser.usercode}
								<input type="hidden" value="${pagezhuser.usercode}"
									id="usercode_${pagestatus.index}" />
							</th>
							<th align="right">
								${pagezhuser.bilifpNum}
								<input type="hidden" value="${pagezhuser.bilifpNum}"
									id="bilifpNum_${pagestatus.index}" />
							</th>
							<th align="right">
								${pagezhuser.moqNum}
								<input type="hidden" value="${pagezhuser.moqNum}"
									id="moqNum_${pagestatus.index}" />
							</th>
							<th>
								<input type="text" value="" style="width: 80px;"
									id="sjcgNum_${pagestatus.index}"
									onchange="numyanzheng(this); getsumsjcgNum(${pagestatus.index})" />
							</th>
						</tr>
					</s:iterator>
				</table>
				<input type="button" value="MOQ申请" onclick="moqsqs()" id="qy_but"
					disabled="disabled" class="input" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var size = <s:property value="zhuserList.size()"/>
var id = ${manualPlan.id};
function NoUse(){
	$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!isUseMOQ.action",
		data : {
			id:	id,
			pageStatus:'NO'
		},
		dataType : "json",
		success : function(data) {
			if(data){
				for(var i=0;i<size;i++){
					$("#sjcgNum_"+i).val($("#bilifpNum_"+i).val());
					$("#sjcgNum_"+i).attr("disabled","disabled");
				}
			}
		}
	})
	}
	
function yesUse(){
	var cgNum = $("#cgNum").val();
	var sjNum = 0;
	if(cgNum>0){
		for(var i=0;i<size;i++){
			if(cgNum<=0){
				$("#sjcgNum_"+i).val(0);
			}else{
				var moqNum = $("#moqNum_"+i).val();
				$("#sjcgNum_"+i).val(moqNum);
				sjNum+=parseFloat(moqNum);
				cgNum -= moqNum;
			}
		}
	}
	$("#xcg_span").html("需采购量:"+$("#cgNum").val()+"<br>");
	$("#sjcg_span").html("总实际采购量:"+sjNum+"<br>");
	$("#msg_span").html("请调整实际采购数量后再进行MOQ数量申请!<br>");
	$("#yesUse").attr("disabled","disabled");
	$("#NoUse").attr("disabled","disabled");
	$("#qy_but"). removeAttr("disabled");
		
}
	
function getsumsjcgNum(id){
	var num =0;
	var cgNum = $("#cgNum").val();
	var sjcgNum0 = parseFloat($("#sjcgNum_"+id).val());
	var moqNum0 =  parseFloat($("#moqNum_"+id).val());
	var name = $("#name_"+id).val();
	if(sjcgNum0>0 && sjcgNum0<moqNum0){
		$("#xcg_span").html(name+"MOQ量:"+moqNum0+"<br>");
		$("#sjcg_span").html(name+"实际采购量:"+sjcgNum0+"<br>");
		$("#msg_span").html("实际采购数量只能为0或者不能小于本身的MOQ数量");
		return false;
	}
	
	for(var i=0;i<size;i++){
		if(num>=cgNum){
			$("#sjcgNum_"+i).val(0);
		}
		var sjcgNum = $("#sjcgNum_"+i).val();
		if(sjcgNum!=''){
			sjcgNum = parseFloat(sjcgNum);
			num+=sjcgNum;
		}
	}
	if(num<cgNum){
		$("#xcg_span").html("需采购量:"+$("#cgNum").val()+"<br>");
		$("#sjcg_span").html("总实际采购量:"+num+"<br>");
		$("#msg_span").html("实际采购数量数量不能低于需采购量,请重新输入.");
		
		return false;
	}
		$("#qy_but").removeAttr("disabled");
		$("#xcg_span").html("");
		$("#sjcg_span").html("");
		$("#msg_span").html("");
	return true;
}
function moqsqs(){
	var gysCodeAndNum = '';
	for(var i=0;i<size;i++){
		var code = $("#usercode_"+i).val();
		var num = parseFloat($("#sjcgNum_"+i).val());
		if(num>0){
			gysCodeAndNum+=";"+code+":"+num;
		}
	}
	if(gysCodeAndNum!=null && gysCodeAndNum.length>1){
		gysCodeAndNum = gysCodeAndNum.substring(1);
	}
	$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!isUseMOQ0.action",
		data : {
			id:	id,
			pageStatus:'YES',
			mxId:gysCodeAndNum
		},
		dataType : "json",
		success : function(data) {
			if(data){
				for(var i=0;i<size;i++){
					$("#sjcgNum_"+i).attr("disabled","disabled");
				}
				$("#msg_span").html("申请成功，请等待审批");
				$("#qy_but").attr("disabled","disabled");
			}
		}
	})
}

</SCRIPT>
	</body>
</html>
