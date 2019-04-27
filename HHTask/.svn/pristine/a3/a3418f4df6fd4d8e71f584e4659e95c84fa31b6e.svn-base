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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<style type="text/css">
			.form-control{
				width: 180px;
			}
		</style>
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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div class="row">
					<div class="cell col-md-12">
						<form action="universalAction!showTypeList.action" method="post" autocomplete=off>
							<table class="table table-responsive">
								<caption>${category.categoryName }</caption>
								<tr>
									<th>类别名称</th>
									<td>
										<select name="type.category.id" id="categoryId" >
											<option></option>
											<option value="${type.category.id}"></option>
											<s:iterator value="categoryList" id="cl" status="ps">
												<option value="${cl.id }">${cl.categoryName }</option>
											</s:iterator>
										</select>
		<%-- 								<input name="category.categoryName" value="${category.categoryName}"  /> --%>
									</td>
									<th>类型编码</th>
									<td>
										<input name="type.code" value="${type.code}"  />
									</td>
									<th>类型名称</th>
									<td>
										<input name="type.name" value="${type.name}"  />
									</td>
								</tr>
								<tr>
									<td colspan="6" class="text-center" >
										<input type="submit" class="input" value="查询"  >
										<input type="hidden" name="category.type" value="${category.type }" id="categoryType">
										<input type="hidden" name="pageStatus" value="${pageStatus}">
										<input type="button" class="input" value="前往添加" onclick="toAddType()"> 
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
								<th>类别</th>
								<th>类型编码</th>
								<th>类型名称</th>
								<th>操作</th>
							</tr>
							<s:iterator value="typeList" id="tl" status="ps">
								<tr>
									<td>${ps.index+1}</td>
									<td>${tl.category.categoryName}</td>
									<td>${tl.code}</td>
									<td>${tl.name}</td>
									<td>
										<input type="button" value="修改" onclick="toUpdate(${tl.id})">
										<input type="button" value="删除" onclick="toDelete(${tl.id})"> 
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
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
		$(function(){
			$("#categoryId").tinyselect();
		});
		function toAddType(){
			var categoryType=$("#categoryType").val();
			location.href="${pageContext.request.contextPath}/universalAction!toAddType.action?category.type="+categoryType+"&pageStatus=${pageStatus}";
		}
		function toUpdate(id){
			var categoryType=$("#categoryType").val();
			location.href="${pageContext.request.contextPath}/universalAction!getTypeById.action?id="+id+"&tag=update&category.type="+categoryType+"&pageStatus=${pageStatus}";
		}
		function toDelete(id){
			if(confirm("确定要删除吗")){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/universalAction!deleteType.action?id="+id,
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
