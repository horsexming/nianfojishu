<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" type="text/css"
	href="<%=basePath %>/css/button.css" />
<STYLE type="text/css">
.button:disabled{
    border: 1px solid #DDD;
    background-color: #F5F5F5;
    color:#ACA899;
   pointer-events:none;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>添加量、检具信息</h2>
				<form action="MeasuringAction_daorumeasuring.action" method="post"
							enctype="multipart/form-data" onsubmit="return checktype()">
							选择导入文件:
							<input type="file" name="measuringfile" >
							<a href="<%=basePath%>/upload/file/download/Measuring.xls">导入模版下载</a>
							<a href="FileViewAction.action?FilePath=/upload/file/download/Measuring.xls&Refresh=true">/预览</a>
							<input type="submit" value="批量导入" id="sub">
					</form>
				<p style="color: red" >
					${errorMessage}	
				</p>
				<form action="MeasuringAction_addmeasuring.action" method="POST" onsubmit="return check()" >
				<font id="msgfont" color="red" size="5mm"></font>
					<table class="table">
						<tr>
							<th align="right">
								名称
							</th>
							<td>
								<input type="text" value="" name="measuring.matetag" id="matetag" /><font color="red">*</font>
							</td>
							<th align="right">
								单位
							</th>
							<td>
								<input type="text" value="" name="measuring.unit" id="unit" /><font color="red">*</font>
							</td>
							<th align="right">
								规格
							</th>
							<td>
								<input type="text" value="" name="measuring.format" id="format"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								责任人工号
							</th>
							<td>
								<input type="text" value="" name="measuring.codeliable"  onblur="getUsers()" onkeyup="getUsers()"
								id="codeLiable"/><font color="red">*</font>
							</td>
							<th align="right">
								责任人姓名
							</th>
							<td>
								<input type="text" value="" name="measuring.personliable" id="personliable"  readonly="readonly"  /><font color="red">*</font>
								<input type="hidden" value="" name="measuring.usersIdliable" id="usersIdliable"/>
							</td>
							<th align="right">
								校准周期
							</th>
							<td>
								<input type="text" value="" name="measuring.period" id="period" 
								onkeyup="numyanzheng(this,'zhengshu')" onblur="numyanzheng(this,'zhengshu')"/>（天）<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								位置
							</th>
							<td>
								<input type="text" value="" name="measuring.place"/>
							</td>
							<th align="right">
								仓库
							</th>
							<td>
								<input type="text" value="" name="measuring.storehouse"/>
							</td>
							<th align="right">
								分类
							</th>
							<td>
								<c:choose>
									<c:when test="${param.tag}=='gj'">
										<input type="text" value="工具" readonly="readonly" name="measuring.parClass" id="parClass" />
									</c:when>
									<c:when test="${param.tag}=='lj'">
										<input type="text" value="量具" readonly="readonly" name="measuring.parClass" id="parClass" />
									</c:when>
									<c:when test="${param.tag}=='jj'">
										<input type="text" value="检具" readonly="readonly" name="measuring.parClass" id="parClass" />
									</c:when>
									<c:otherwise>
										<input type="text" value="特种设备" readonly="readonly" name="measuring.parClass" id="parClass" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th align="right">
								价格
							</th>
							<td>
								<input type="text" value="" name="measuring.price"/>
							</td>
							<th align="right">
								总数
							</th>
							<td>
								<input type="text" value="" name="measuring.total"/>
							</td>
							<th align="right">
								合计金额
							</th>
							<td>
								<input type="text" value="" name="measuring.totMoney"/>
							</td>
							
						</tr>
						<tr>
							<th align="right">
								最低库存
							</th>
							<td>
								<input type="text" value="" name="measuring.minStore"/>
							</td>
							<th align="right">
								当前量
							</th>
							<td>
								<input type="text" value="" name="measuring.curAmount" onkeyup="numyanzheng(this,'zhengshu')" onblur="numyanzheng(this,'zhengshu')" />
							</td>
							<th align="right">
								最大可借量
							</th>
							<td>
								<input type="text" value="" name="measuring.maxBorrowNum" onkeyup="numyanzheng(this,'zhengshu')" onblur="numyanzheng(this,'zhengshu')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								申报部门
							</th>
							<td>
								<input type="text" value="" name="measuring.appDept"/>
							</td>
							<th align="right">
								本厂编号
							</th>
							<td>
								<input type="text" value="" name="measuring.measuring_no"   onchange="isone(this)"/>
							</td>
							<th align="right">
								本体编号
							</th>
							<td>
								<input type="text" value="" name="measuring.number"   onchange="isone(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								校准类型
							</th>
							<td>
								<input type="radio" value="内校" name="measuring.jztype" >内校
								<input type="radio" value="外校" name="measuring.jztype" checked="checked">外校
							</td>
							<th  align="right">
								校准时间
							</th>
							<td>
								<input type="text" name="measuring.calibrationTime" class="Wdate" 
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<th  align="right">
								测试精度
							</th>
							<td>
								<input type="text" name="measuring.csjd" />
							</td>
						</tr>
						<tr>
							<th align="right">
								备注
							</th>
							<td colspan="8">
								<textarea rows="" name="more" style="width: 300px;"></textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${param.tag}" name="tag"/>
					<input type="submit" value="提交" class="button blue " onclick="todisabled(this)" id="sub">
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function getUsers(){
		var code = $("#codeLiable").val();
		if(code!=""){
				$.ajax( {
		type : "POST",
		url : "CheckoutAndGagesAction_findUsersByCode.action",
		data : {
			code:code		
		},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				if(data.onWork == '离职'){
					$("#msgfont").html("工号:"+code+"的员工:"+data.name+"已离职，请从新输入责任人工号!");
					$("#codeLiable").val("");
				}else if(data.internal == "否"){
					$("#msgfont").html("工号:"+code+"的员工:"+data.name+"不为内部员工，请从新输入责任人工号!");
					$("#codeLiable").val("");
				}else{
					$("#msgfont").html("");
					$("#personliable").val(data.name);
					$("#usersIdliable").val(data.id);
				}
			}else{
				$("#msgfont").html(code+"不是内部工号，请重新输入。");
			}
		}
	})
		}
		
	}
function check(){
	var matetag = $("#matetag").val();
	var  unit = $("#unit").val();
	var format = $("#format").val();
	var codeLiable = $("#codeLiable").val();
	var period = $("#period").val();
	if(matetag == ""){
		$("#msgfont").html("请填写名称。");
		$("#sub").remove("disabled");
		return false;
	}else if(unit == ""){
		$("#msgfont").html("请填写单位。");
		$("#sub").remove("disabled");
		return false;
	}else if(codeLiable == ""){
		$("#msgfont").html("请填写工号。");
		$("#sub").remove("disabled");
		return false;
	}else if(period == ""){
		$("#msgfont").html("请填写校准周期。");
		$("#sub").remove("disabled");
		return false;
	}
	$("#msgfont").html("");
	return true;
	
}

$(document).ready(function() {
	var rebeack = '${errorMessage}';
	if (rebeack == "true") {
		alert("添加成功!");
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
function isone(obj){
	if(obj!=null && obj.vlaue!=''){
				$.ajax( {
		type : "POST",
		url : "MeasuringAction_findMeasuringById.action",
		data : {
			empname:obj.vlaue	
		},
		dataType : "json",
		success : function(data) {
			if(!data){
				$("#msgfont").html(+"本厂编号:"+obj.vlaue+"重复!");
			}
		}
	})
	}
	
}
$(function(){
	var tag = '${param.tag}'
	if(tag=='lj'){
		$("#parClass").val('量具');
	}else if(tag == 'jj'){
		$("#parClass").val('检具');
	}else if(tag == 'gj'){
		$("#parClass").val('工具');
	}
	
})
</SCRIPT>
	</body>
</html>
