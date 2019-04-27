<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			.js-load-more{
			    padding:0 15px;
			    width:120px;
			    height:30px;
			    background-color:#999;
			    color:#fff;
			    line-height:30px;
			    text-align:center;
			    border-radius:5px;
			    margin:20px auto;
			    border:0 none;
			    font-size:16px;
			   /* display:none;默认不显示，ajax调用成功后才决定显示与否*/
			}
		</style>
	</head>
	<body>
		<div class="row">
			<div class="cell col-md-12">
				<form action="productEBAction!findPebUsersByCon.action" method="post" autocomplete=off>
					<table class="table table-responsive">
						<caption>人数列表</caption>
						<tr>
							<th>年份</th>
							<td>
								<input name="pebUsers.year" value="${pebUsers.year }"  />
							</td>
							<th>月份</th>
							<td>
								<input name="pebUsers.month" value="${pebUsers.month }" />
							</td>
							<th>日期</th>
							<td>
								<input name="pebUsers.day" value="${pebUsers.day }"  />
							</td>
						</tr>
						<tr>
							<th>班组</th>
							<td>
								<input type="text" name="pebUsers.banZu" value="${pebUsers.banZu}">
							</td>
							<th>人事档案数</th>
							<td>
								<input type="text" name="pebUsers.dangAnNum" value="${pebUsers.dangAnNum}">
							</td>
							<th>折算台数</th>
							<td>
								<input type="text" name="pebUsers.zsNumber" value="${pebUsers.zsNumber}">
							</td>
						</tr>
						<tr>
							<td class="text-center" colspan="6">
								<input type="hidden" value="${pageStatus}" name="pageStatus">
								<input type="hidden" value="${tag}" name="tag">
								<input type="submit" value="查询" class="input">
								<input type="button" value="导入" class="input" onclick="toImport()">
								<input type="button" value="导出" class="input" onclick="toExportPebUsers(this.form);todisabledone(this)" data="downData">
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
						<th>人事档案数</th>
						<th>借入人数</th>
						<th>借出人数</th>
						<th>未出勤人数</th>
						<th>实际出勤人数</th>
						<th>折算台数</th>
						<th>人均台数</th>
						<th>上班小时</th>
						<th>UPPH</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
					<tbody id="tbody">
					<s:iterator value="pebUsersList" id="users" status="ps">
						<tr>
							<td>${ps.index+1}</td>
							<td>${users.banZu}</td>
							<td>${users.year}-${users.month}-${users.day}</td>
							<td>${users.dangAnNum}</td>
							<td>${users.borrowNum}</td>
							<td>${users.lendNum}</td>
							<td>${users.noChuQinNum}</td>
							<td>${users.actualNum}</td>
							<td>
						<fmt:formatNumber pattern="#.##" minFractionDigits="2">${users.zsNumber}</fmt:formatNumber></td>
							<td>${users.avgNumber}</td>
							<td>${users.gzHour}</td>
							<td>${users.UPPH}</td>
							<td>${users.remarks}</td>
							<td>
								<input type="button" value="修改" onclick="updateUsers(${users.id})">
								<s:if test="tag!=null && tag=='deleteUsers'">
									<input type="button" value="删除" onclick="deleteUsers(${users.id})">
								</s:if>
							</td>
						</tr>
					</s:iterator>
					</tbody>
					<tr>
						<td colspan="7"></td>
						<td id="personCountTotal">${chuqinPersonCount }</td>
						<td id="zsNumberTotal">${zsNumber }</td>
						<td id="avgNumberTotal">${avgNumber }</td>
						<td colspan="4"></td>
					</tr>
					<s:if test="total>cpage">
						<tr id="js-load-more-tr">
							<td colspan="14" class="js-load-more">
								加载更多
							</td>
						</tr>
					</s:if>
					<tr id="fenyeTr">
						<td class="text-right" colspan="20"> 
							第
							<font color="red" id="cpage">${cpage}</font> / <font id="total">${total}</font> 页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
 								styleClass="page" theme="number" /> 
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function updateUsers(id){
			location.href="${pageContext.request.contextPath}/productEBAction!toAddProduction.action?pageStatus=toAddUsers&id="+id+"&cpage=${cpage}&tag=${tag}";
		}
		
		function toImport(){
			location.href="${pageContext.request.contextPath}/productEBAction!toImportData.action?pageStatus=importpebUsers";
		}
		
		function toExportPebUsers(objForm){
			
			objForm.action = "productEBAction!exportPebUsers.action";
			objForm.submit();
			objForm.action = "productEBAction!findPebUsersByCon.action";
		}
		
		function deleteUsers(id){
			location.href="${pageContext.request.contextPath}/productEBAction!deletePebUsers.action?pageStatus=toAddUsers&id="+id+"&cpage=${cpage}&tag=${tag}";
		}
		$(function(){
			$(".js-load-more").click(function(){
				getData();
			});
		});
		var number = 0;
		function getData(){
			var cpage = $("#cpage").text();
			var total = $("#total").text();
			if(cpage==total){
				return false;
			}
			cpage = parseInt(cpage)+1+"";
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/productEBAction!findPebUsersByConAjax.action",
				data:{
					"cpage":cpage
				},
				dataType:"json",
				success:function(data){
					if(data!=null){
						if(number<=0){
							number = "${fn:length(pebUsersList)}";
							
						}
						var personCountTotal = parseFloat($("#personCountTotal").text());
						var zsNumberTotal= parseFloat($("#zsNumberTotal").text());
						var avgNumberTotal = parseFloat($("#avgNumberTotal").text());
						$(data).each(function(){
							number++;
							var pebUsers = this;
							var dangAnNum =checkNull(pebUsers.dangAnNum);
							var borrowNum =checkNull(pebUsers.borrowNum);
							var lendNum =checkNull(pebUsers.lendNum);
							var noChuQinNum =checkNull(pebUsers.noChuQinNum);
							var actualNum =checkNull(pebUsers.actualNum);
							var zsNumber = checkNull(pebUsers.zsNumber);
							var avgNumber =checkNull(pebUsers.avgNumber);
							var gzHour =checkNull(pebUsers.gzHour);
							var UPPH =checkNull(pebUsers.UPPH);
							var remarks =checkNull(pebUsers.remarks);
							
							var appendHtml = "<tr>"+
									"<td>"+number+"</td>"+
									"<td>"+$(this).attr("banZu")+"</td>"+
									"<td>"+$(this).attr("year")+"-"+$(this).attr("month")+"-"+$(this).attr("day")+"</td>"+
									"<td>"+dangAnNum+"</td>"+
									"<td>"+borrowNum+"</td>"+
									"<td>"+lendNum+"</td>"+
									"<td>"+noChuQinNum+"</td>"+
									"<td>"+actualNum+"</td>"+
									"<td>"+zsNumber+"</td>"+
									"<td>"+avgNumber+"</td>"+
									"<td>"+gzHour+"</td>"+
									"<td>"+UPPH+"</td>"+
									"<td>"+remarks+"</td>"+
									"<td>"+
										"<input type='button' value='修改' onclick='updateUsers("+$(this).attr("id")+")'>"+
									"</td>"+
								"</tr>";
							$("#tbody").append(appendHtml);
							if(actualNum!=null && actualNum!=""){
								personCountTotal+=parseFloat(actualNum);
							}
							if(zsNumber!=null && zsNumber!=""){
								zsNumberTotal+=parseFloat(zsNumber);
							}
							if(avgNumber!=null && avgNumber!=""){
								avgNumberTotal+=parseFloat(avgNumber);
							}
							
						});
						$("#cpage").text(cpage);
					}
					$("#personCountTotal").text(personCountTotal);
					$("#zsNumberTotal").text(zsNumberTotal);
					$("#avgNumberTotal").text(avgNumberTotal);
					if(cpage==total){
						$(".js-load-more").text("已加载全部");
						$("#fenyeTr").hide();
					}
				},error:function(){
					$(".js-load-more").text("加载更多异常，请点击重新加载。");
				}
			});
			
		}
		
		function checkNull(e){
			if(e==null || e=="null"){
				return "";
			}else{
				return e;
			}
		}
	</script>
</html>
