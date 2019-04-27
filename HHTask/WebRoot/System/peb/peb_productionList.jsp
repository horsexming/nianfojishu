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
<!-- 		<div class="row"> -->
<!-- 			<div class="cell col-xs-12 align-center"> -->
				
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="row">
			<div class="cell col-md-12">
				<form action="productEBAction!findProductionByCon.action" method="post" autocomplete=off>
					<table class="table table-responsive">
						<caption>产品下线列表</caption>
						<tr>
							<th>年份</th>
							<td>
								<input name="pebProduction.year"value="${pebProduction.year }"  />
							</td>
							<th>月份</th>
							<td>
								<input name="pebProduction.month" value="${pebProduction.month }"  />
							</td>
							<th>日期</th>
							<td>
								<input name="pebProduction.day" value="${pebProduction.day }" />
							</td>
						</tr>
						<tr>
							<th>班组</th>
							<td>
								<input type="text" name="pebProduction.banzu" value="${pebProduction.banzu}">
							</td>
							<th>产品编码</th>
							<td>
								<input type="text" name="pebProduction.markId" value="${pebProduction.markId}">
							</td>
							<th>产出下线台数</th>
							<td>
								<input type="text" name="pebProduction.cuNumber" value="${pebProduction.cuNumber}">
							</td>
						</tr>
						<tr>
							<th>异常</th>
							<td>
								<input type="text" name="pebProduction.message" value="${pebProduction.message }">
							</td>
							<th>异常采取措施</th>
							<td>
								<input type="text" name="pebProduction.measure" value="${pebProduction.measure }">
							</td>
							<th>责任单位</th>
							<td>
								<input type="text" name="pebProduction.zrComp" value="${pebProduction.zrComp }">
							</td>
						</tr>
						<tr>
							<th>责任单位措施</th>
							<td>
								<input type="text" name="pebProduction.zrCompMeasure" value="${pebProduction.zrCompMeasure }">
							</td>
							<th>系数</th>
							<td>
								<input type="text" name="pebProduction.xiShu" value="${pebProduction.xiShu }">
							</td>
							<th>折算台数</th>
							<td>
								<input type="text" name="pebProduction.zsNumber" value="${pebProduction.zsNumber }">
							</td>
						</tr>
						<tr>
							<td class="text-center" colspan="6">
								<input type="hidden" value="${pageStatus}" name="pageStatus" >
								<input type="hidden" value="${tag}" name="tag" >
								<input type="submit" value="查询" class="input">
								<input type="button" value="前往添加" class="input" onclick="toAddProduction()">
								<input type="button" value="导入" class="input" onclick="toImportProduction()">
								<input type="button" value="导出" class="input" onclick="toExportProduction(this.form);todisabledone(this)" data="downData">
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
						<th>班组</th>
						<th>时间</th>
						<th>产品编码</th>
						<th>产出下线台数</th>
						<th>异常</th>
						<th>异常措施</th>
						<th>责任单位</th>
						<th>责任单位措施</th>
						<th>系数</th>
						<th>折算台数</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
					<s:iterator value="productionList" id="pro" status="ps">
						<tr>
							<td>${ps.index+1}</td>
							<td>${pro.banzu}</td>
							<td>${pro.year}-${pro.month}-${pro.day}</td>
							<td>${pro.markId}</td>
							<td>${pro.cuNumber}</td>
							<td>${pro.message}</td>
							<td>${pro.measure}</td>
							<td>${pro.zrComp}</td>
							<td>${pro.zrCompMeasure}</td>
							<td>${pro.xiShu}</td>
							<td>${pro.zsNumber}</td>
							<td>${pro.remarks }</td>
							<td>
								<input type="button" value="修改" onclick="toUpdateProduction(${pro.id})">
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
		function toAddProduction(){
			location.href="${pageContext.request.contextPath}/productEBAction!toAddProduction.action";
		}
		function toUpdateProduction(id){
			location.href="${pageContext.request.contextPath}/productEBAction!toAddProduction.action?id="+id;
		}
		function toImportProduction(){
// 			var pageStatus=$("input[name='pageStatus']").val();
// 			if(pageStatus!=null && pageStatus=="bj"){
// 				location.href="${pageContext.request.contextPath}/productEBAction!toAddProduction.action?pageStatus=importBJ"
// 			}else{
				location.href="${pageContext.request.contextPath}/productEBAction!toImportData.action?pageStatus=importProduction";
		}
		function toDelete(id){
			if(confirm("确定要删除吗？")){
				location.href="${pageContext.request.contextPath}/productEBAction!deletePebProduction.action?id="+id;
			}
		}
		function toExportProduction(objForm){
			objForm.action = "productEBAction!exportPebProduction.action";
			objForm.submit();
			objForm.action = "productEBAction!findProductionByCon.action";
		}
	</script>
</html>
