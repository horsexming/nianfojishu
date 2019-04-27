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
				现场在制品入库申请
				<div>
					<form action="GoodsStoreAction!applyzaizhiinput.action"
						method="post">
						<input type="hidden" name="id" value="${procardVo.id}">
						<table class="table">
							<tr>
								<th>
									件号
								</th>
								<td>
									${procardVo.markId}
								</td>
								<th>
									名称
								</th>
								<td>
									${procardVo.proName}
								</td>
								<th>
									批次
								</th>
								<td>
									${procardVo.selfCard}
								</td>
							</tr>
							<tr>
								<th>
									工序号
								</th>
								<td>
									${procardVo.processNo}
									<input type="hidden" name="goodsStore.processNo"
										value="${procardVo.processNo}">
								</td>
								<th>
									工序名称
								</th>
								<td>
									${procardVo.processName}
								</td>
								<th>
									业务件号
								</th>
								<td>
									${procardVo.ywMarkId}
									<input type="hidden" name="goodsStore.ywmarkId"
										value="${procardVo.ywMarkId}">
								</td>
							</tr>
							<tr>
								<th>
									仓库：
								</th>
								<td>
									<select name="goodsStore.goodsStoreWarehouse" id="goodsClass"
										onchange="changvalue(this)">
										<option value="">
										</option>
									</select>
								</td>
								<th>
									仓区:
								</th>
								<td>
									<select name="goodsStore.goodHouseName" id="goodHouseName"
										onchange="changvalue1(this)">
									</select>
								</td>
								<th>
									库位:
								</th>
								<td>
									<select name="goodsStore.goodsStorePosition" id="goodsPosition">
									</select>
								</td>
							</tr>
							<tr>
								<th>
									批次数量
								</th>
								<td>
									${procardVo.filnalCount}
								</td>
								<th>
									可转
								</th>
								<td>
									${procardVo.zaizhikzkCount}
								</td>
								<th>
									转库数量
								</th>
								<td>
									<input id="zkCount" onkeyup="mustBeNumber('zkCount')"
										name="goodsStore.goodsStoreCount"
										value="${procardVo.zaizhikzkCount}" />
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="提交">
								</td>
							</tr>
						</table>
					</form>
				</div>
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
					if(this.name == '半成品库'){
						$("#goodsClass").append('<option value='+this.name+' selected="selected" >'+this.name+'</option>');
					}else{
						$("#goodsClass").append('<option value='+this.name+' >'+this.name+'</option>');
					}
						
					});
				$("#goodsClass").tinyselect();
			}
		}
	})
	var wareHouseName = "半成品库";
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:wareHouseName
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodHouseName").empty();
				$(data).each(function(){
						$("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
				$("#goodHouseName").val("");
			}
		}
	});
	
})
function changvalue(obj){
	if(obj!=null && obj.value != ""){
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:obj.value
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodHouseName").empty();
				$(data).each(function(){
						if(this.goodHouseName!='委外库'){
						 $("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
						}
					});
				$("#goodHouseName").val("");
			}
		}
	});
	}
}
function changvalue1(obj){
	if(obj!=null && obj.value != ""){
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwnListByNO.action",
		data : {
			wareHouseName:$("#goodsClass").val(),
			cangqu:obj.value
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodsPosition").empty();
				$(data).each(function(){
						$("#goodsPosition").append('<option value='+this.number+'>'+this.number+'</option>');
					});
			}
		}
	});
	}
}

</SCRIPT>
	</body>
</html>