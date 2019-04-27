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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在对库位修改进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="WarehouseAreaAction_findwnList.action?tag=${tag}" method="post"
					id="myfrom">
					<table class="table">
						<tr>
							<th>
								所属仓库:
							</th>
							<td>
								<select id="wareHouseName" name="warehouseNumber.wareHouseName"
									onchange="changvalue(this)">
									<s:if test="warehouseNumber!=null&&warehouseNumber.wareHouseName!=null">
										<option value="${warehouseNumber.wareHouseName}">
											${warehouseNumber.wareHouseName}
										</option>
									</s:if>
									<option value="">

									</option>
								</select>
							</td>
							<th>
								仓区名称:
							</th>
							<td id="cangqu_td">
								<input type="hidden" value="${warehouseNumber.warehouseArea}" id="cangqu"/>
								<select id="warehouseArea" name="warehouseNumber.warehouseArea"
									class="cxselect">
									<s:if test="warehouseNumber!=null&&warehouseNumber.warehouseArea!=null">
										<option  value="${warehouseNumber.warehouseArea}">
											${warehouseNumber.warehouseArea}
										</option>
									</s:if>
									<option value="">

									</option>
								</select>
							</td>
							<td rowspan="2">
								<input type="submit" value="查询" class="input" />
								<input type="hidden" value="" name="statue" id="statue" />
								<s:if test="tag!='kg'&&tag!='show'">
									<input type="button" value="打印库位码" onclick="printkuweima()"
										class="input" />
								</s:if>
							</td>
						</tr>
						<tr>
							<th>
								库位号:
							</th>
							<td>
								<input type="text" value="${warehouseNumber.number}" name="warehouseNumber.number"
								style="width: 200px; height: 30px;"/>
							</td>
							<th>
								库位状态:
							</th>
							<td>
								<input type="text" value="${warehouseNumber.markTyptName}" name="warehouseNumber.markTyptName"
								 style="width: 200px; height: 30px;"/>
							</td>
						</tr>
						<tr>
							<th>
								库位层:
							</th>
							<td>
								<SELECT style="width: 200px; height: 30px;" name="warehouseNumber.floorNumbe" class="cxselect">
									<option value="${warehouseNumber.floorNumbe}">${warehouseNumber.floorNumbe}</option>
									<option value="">无</option>
									<s:if test="tag == 'kg'">
										<option value="下" selected="selected">下</option>
									</s:if>
									<s:else>
										<option value="下">下</option>
									</s:else>
									<option value="中">中</option>
									<option value="上">上</option>
								</SELECT>
							</td>
							<th colspan="2">
							</th>
						</tr>
					</table>
				</form>
				<s:if test="tag!='kg'&&tag!='show'">
					<form action="WarehouseAreaAction_addplwarehouseNumber.action"
						method="post" enctype="multipart/form-data"
						onsubmit="return checktype()">
						选择导入文件:
						<input type="file" name="addwarehouseNumber">
						<a href="<%=basePath%>/upload/file/download/kuwei.xls">导入模版下载</a>
						<a href="FileViewAction.action?FilePath=/upload/file/download/kuwei.xls&Refresh=true">/预览</a>
						<input type="submit" value="批量导入" id="sub">
					</form>
				</s:if>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							所属仓库
						</th>
						<th>
							所属仓区
						</th>
						<th>
							库位号
						</th>
						<th>
							库位状态
						</th>
						<s:if test="tag != 'show'">
							<th>
								库位IP
							</th>
							<th>
								库位编码
							</th>
						</s:if>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="pagelist" value="wnList" status="pagestatus">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pagestatus.index+1" />
						</td>
						<td>
							${pagelist.wareHouseName}
						</td>
						<td>
							${pagelist.warehouseArea}
						</td>
						<td>
							${pagelist.number}
						</td>
						<td>
							${pagelist.markTyptName}
						</td>
						<s:if test="tag != 'show'">
							<td>
								${pagelist.ip}
							</td>
							<td>
								${pagelist.numid}
							</td>
						</s:if>
							<td>
								<s:if test="tag == 'kg'">
									<s:if test="#pagelist.czUserId!=null">
										<a href="WarehouseAreaAction_ColseWNById.action?id=${pagelist.id}&tag=${tag}&cpage=${cpage}" 
										style="border-radius: 20%; background-color: red; color: #ffffff; font-size: 25px;">
										关门</a>&nbsp;&nbsp;
									</s:if>
									<s:else>
										<a href="WarehouseAreaAction_OpenWNById.action?id=${pagelist.id}&tag=${tag}&cpage=${cpage}"
											style="border-radius: 20%; background-color: green; color: #ffffff; font-size: 25px;">
										 开门</a>&nbsp;&nbsp;
									</s:else>
									<a onclick="getlight('${pagelist.id}')"> 重置灯</a>/&nbsp;&nbsp;
									<a onclick="getpingmu('${pagelist.id}')"> 重置屏</a>/&nbsp;&nbsp;
									<a onclick="shansuo('${pagelist.id}')"> 闪烁</a>/&nbsp;&nbsp;
									<a onclick="sendKuWei('${pagelist.id}')"> 库位码</a>/
									<a onclick="zhuYe('${pagelist.id}')"> 主页</a>
								</s:if>
								<s:elseif test="tag == 'show'">
								</s:elseif>
								<s:else>
								<a href="javascript:;"
									onclick="tanchu(0,'${pagelist.barCode}','${pagelist.number}')">查看库位码</a>/
								<a href="javascript:;" onclick="tanchu(${pagelist.id})">修改</a>/
								<a
									href="WarehouseAreaAction_delwarehouseNumber.action?warehouseNumber.id=${pagelist.id}"
									onclick="return confirm('确定要删除吗？')">删除</a>
								</s:else>
								<s:if test='#pagelist.markTyptName!="空"'>
									<a onclick="mingXi('${pagelist.id}')"> &nbsp;/查看明细</a>
								</s:if>
							</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="8" align="left">
								第
								<font color="red" size="8px"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_getAllwarehouse.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$(data).each(function(){
						$("#wareHouseName").append('<option value='+this.name+'>'+this.name+'</option>');
					});
				$("#wareHouseName").tinyselect();
			}
		}
	})
	getCangqu();
})
function changvalue(obj){
	if(obj==null){
		obj = document.getElementById("wareHouseName");
	}
	
	var cangqu = $("#cangqu").val();
	if(obj!=null && obj.value != ""){
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:obj.value
		},
		dataType : "json",
		success : function(data) {
			$("#warehouseArea").empty();
		//	$("#warehouseArea").append('<option value='+cangqu+'>'+cangqu+'</option>')
			$("#warehouseArea").append('<option value="">请选择仓区</option>')
			if (data != null) {
				$(data).each(function(){
						$("#warehouseArea").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
				$("#warehouseArea").tinyselect();
				var tinyselect = $(".tinyselect");
				if (tinyselect[2] != null) {
							document.getElementById("cangqu_td").removeChild(
									tinyselect[2]);
						}
			}
		}
	});
	}
}

function getCangqu(){
	
	var obj = document.getElementById("wareHouseName");
	
	var cangqu = $("#cangqu").val();
	if(obj!=null && obj.value != ""){
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:obj.value
		},
		dataType : "json",
		success : function(data) {
			$("#warehouseArea").empty();
			$("#warehouseArea").append('<option value='+cangqu+'>'+cangqu+'</option>')
			$("#warehouseArea").append('<option value="">请选择仓区</option>')
			if (data != null) {
				$(data).each(function(){
						$("#warehouseArea").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
				$("#warehouseArea").tinyselect();
				var tinyselect = $(".tinyselect");
				if (tinyselect[2] != null) {
							document.getElementById("cangqu_td").removeChild(
									tinyselect[2]);
						}
			}
		}
	});
	}
}

function printkuweima(){
	$("#statue").val("dykwm");
	$("#myfrom").submit();
	$("#statue").val("");
}
function tanchu(num,number,obj){
	if(num == 0){
		document.getElementById("xiugaiIframe").src="<%=basePath%>System/wuliu/WarehouseNumber_kwm.jsp?number="+number+"&ku="+obj;
	}else{
			document.getElementById("xiugaiIframe").src="WarehouseAreaAction_getwarehouseNumberById.action?id="+num;
	}

		chageDiv('block')
}
function getlight(id){
	$.ajax({
		url : "WarehouseAreaAction_czlight.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					//getcheckList2();
					alert(data.message)
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function getpingmu(id){
	$.ajax({
		url : "WarehouseAreaAction_czpinmu.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					//getcheckList2();
					alert(data.message)
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function sendKuWei(id){
	$.ajax({
		url : "WarehouseAreaAction_sendKuWei.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					//getcheckList2();
					alert(data.message)
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function zhuYe(id){
	$.ajax({
		url : "WarehouseAreaAction_sendZhuYe.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					//getcheckList2();
					alert(data.message)
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function mingXi(id){
	$.ajax({
		url : "WarehouseAreaAction_mingXi.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					alert(data.message)
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function shansuo(id){
	$.ajax({
		url : "WarehouseAreaAction_shansuo.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					alert(data.message)
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
</SCRIPT>
	</body>
</html>
