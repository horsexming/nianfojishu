<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
				<form action="universalAction!showCategoryList.action" method="post" autocomplete=off>
					<table class="table table-responsive">
						<caption>系统类别表</caption>
						<tr>
							<th>类别编码</th>
							<td>
								<input name="category.type" value="${category.type}"  />
							</td>
							<th>类别名称</th>
							<td>
								<input name="category.categoryName" value="${category.categoryName}"  />
							</td>
							<th>备注</th>
							<td>
								<input name="category.remarks" value="${category.remarks}"  />
							</td>
						</tr>
						<tr>
							<td colspan="6" class="text-center" >
								<input type="submit" class="input" value="查询"  >
								<input type="button" class="input" value="前往添加" onclick="toAddCategory()"> 
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
						<th>类别编码</th>
						<th>类别名称</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
					<s:iterator value="categoryList" id="cl" status="ps">
						<tr>
							<td>${ps.index+1}</td>
							<td>${cl.type}</td>
							<td>${cl.categoryName}</td>
							<td>${cl.remarks}</td>
							<td>
								<input type="button" value="修改" onclick="toUpdate(${cl.id})">
								<input type="button" value="删除" onclick="toDelete(${cl.id})"> 
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
		function toAddCategory(){
			location.href="${pageContext.request.contextPath}/System/universal/universal_addCategoryList.jsp";
		}
		function toUpdate(id){
			location.href="${pageContext.request.contextPath}/universalAction!getCategoryById.action?id="+id+"&pageStatus=update";
		}
		function toDelete(id){
			if(confirm("确定要删除吗")){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/universalAction!deleteCategory.action?id="+id,
					dataType:"json",
					success:function(data){
						if(data!=null && data.indexOf("成功")>0){
							alert("删除成功");
							window.location.reload();
						}else{
							alert(data);
						}
					},error:function(){
						alert("系统异常");
					}
				});
			}
		}
	</script>
</html>
