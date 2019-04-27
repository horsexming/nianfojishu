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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
 		<script type="text/javascript"
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
		<style type="text/css">
			caption{
				text-align: center;
			}
			body{
				margin:5px;
/* 				padding: 5px; */
			}
			.row{
				margin:0;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<div class="cell col-md-12">
				<form action="productEBAction!findPebProductionBanjinByCon.action" method="post" autocomplete=off>
					<table class="table table-responsive">
						<caption>工序加工量列表</caption>
						<tr>
							<th>年份</th>
							<td>
								<input name="banjin.year"value="${banjin.year }"  />
							</td>
							<th>月份</th>
							<td>
								<input name="banjin.month" value="${banjin.month }"  />
							</td>
							<th>日期</th>
							<td>
								<input name="banjin.day" value="${banjin.day }" />
							</td>
						</tr>
						<tr>
							<th>工序名称</th>
							<td>
								<input type="text" name="banjin.processName" value="${banjin.processName}">
							</td>
							<th>产出总量</th>
							<td>
								<input type="text" name="banjin.cuNumber" value="${banjin.cuNumber}">
							</td>
							<th>系数</th>
							<td>
								<input type="text" name="banjin.xiShu" value="${banjin.xiShu }">
							</td>
						</tr>
						<tr>
							<th>折算台数</th>
							<td>
								<input type="text" name="banjin.zsNumber" value="${banjin.zsNumber }">
							</td>
						</tr>
						<tr>
							<td class="text-center" colspan="6">
								<input type="hidden" value="${pageStatus}" name="pageStatus" >
								<input type="submit" value="查询" class="input">
								<input type="button" value="前往添加" class="input" onclick="toAddProductionbanjin()">
								<input type="button" value="导入" class="input" onclick="toImportProduction()">
								<input type="button" value="导出" class="input" onclick="toExportPebBanjin(this.form);todisabledone(this)" data="downData">
								<input type="button" value="批量修改系数" class="input" onclick="toupdateXiShu()">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<table class="table table-responsive">
					<tr>
						<th>序号</th>
						<th>工序</th>
						<th>时间</th>
						<th>PEBS产出</th>
						<th>K3产出</th>
						<th>产出下线台数</th>
						<th>系数</th>
						<th>折算台数</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
					<s:iterator value="banjinList" id="pro" status="ps">
						<tr>
							<td>${ps.index+1}</td>
							<td>${pro.processName}</td>
							<td>${pro.year}-${pro.month}-${pro.day}</td>
							<td>${pro.PEBSNumber}</td>
							<td>${pro.k3Number}</td>
							<td>${pro.cuNumber}</td>
							<td>${pro.xiShu}</td>
							<td>${pro.zsNumber}</td>
							<td>${pro.remarks }</td>
							<td>
								<input type="button" value="修改" onclick="toUpdateProductionbanjin(${pro.id})">
								<input type="button" value="删除" onclick="toDelete(${pro.id})"> 
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td class="text-right" colspan="20"> 
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
 								styleClass="page" theme="number" /> 
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>	
	<script type="text/javascript">
		function toAddProductionbanjin(){
			location.href="${pageContext.request.contextPath}/productEBAction!toAddProduction.action?pageStatus=addbanjin";
		}
		function toUpdateProductionbanjin(id){
			location.href="${pageContext.request.contextPath}/productEBAction!toAddProduction.action?pageStatus=updatebanjin&id="+id+"&tag=${tag}&cpage=${cpage}";
		}
		function toImportProduction(){
			location.href="${pageContext.request.contextPath}/productEBAction!toImportData.action?pageStatus=importBanjinProduction";
		}
		
		function toDelete(id){
			if(confirm("确定要删除吗？")){
				location.href="${pageContext.request.contextPath}/productEBAction!deletePebProBanjin.action?id="+id+"&tag=${param.tag}&cpage=${cpage}";
			}
		}
		
		function toupdateXiShu(){
			location.href="${pageContext.request.contextPath}/productEBAction!toAddProduction.action?pageStatus=to_updateBanjinMultpart";
		}
		
		function toExportPebBanjin(objForm){
			objForm.action = "productEBAction!exportPebBanjin.action";
			objForm.submit();
			objForm.action = "productEBAction!findPebProductionBanjinByCon.action";
		}
	</script>
</html>
