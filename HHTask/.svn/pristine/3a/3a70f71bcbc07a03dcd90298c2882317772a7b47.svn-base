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
 		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/calendar/css/redmond/jquery-ui-1.8.1.custom.css">
 		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
 		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/calendar/jquery/jquery-ui-1.8.2.custom.min.js"></script>
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
				<form action="productEBAction!findborrowLogByCon.action" method="post" autocomplete=off>
					<table class="table table-responsive">
						<caption>人员借入申请记录</caption>
						<tr>
							<th>借入班组</th>
							<td>
								<input type="text" name="log.sqBanzu" value="${log.sqBanzu}">
							</td>
							<th>借出班组</th>
							<td>
								<input type="text" name="log.borrowBanzu" value="${log.borrowBanzu}">
							</td>
							<th>申请时间</th>
							<td>
								<input type="text" name="log.sqTime" value="${log.sqTime}">
							</td>
						<tr>
						<tr>
							<th>添加时间</th>
							<td>
								<input type="text" name="log.addTime" value="${log.addTime}">
							</td>
							<th>申请人数</th>
							<td>
								<input type="text" name="log.borrowNum" value="${log.borrowNum}">
							</td>
							<th>审批状态</th>
							<td>
								<select name="log.epStatus" style="width:173px" >
									<option></option>
									<s:iterator id="status" value="{'同意','审批中','未审批','打回'}" >
										<s:if test="#status==log.epStatus">
											<option value="${status}" selected="selected">${status}</option>
										</s:if>
										<s:else>
											<option value="${status}">${status}</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td class="text-center" colspan="6">
								<input type="hidden" value="${pageStatus}" name="pageStatus" >
								<input type="submit" value="查询" class="input">
<!-- 								<input type="button" value="前往添加" class="input" onclick="toAddProductionbanjin()"> -->
								<input type="button" value="导出" class="input" onclick="toExportBorrowLog(this.form);todisabledone(this)" data="downData">
<!-- 								<input type="button" value="批量修改系数" class="input" onclick="toupdateXiShu()"> -->
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<form id="piliangForm" method="post">
				<table class="table table-responsive">
					<tr>
						<s:if test="pageStatus!=null && pageStatus=='audit'">
							<td>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
								全选
							</td>
						</s:if>
						<th>序号</th>
						<th>申请时间</th>
						<th>借入班组</th>
						<th>借出班组</th>
						<th>申请人数</th>
						<th>借调工时</th>
						<th>申请人</th>
						<th>添加时间</th>
						<th>备注</th>
						<th>审批动态</th>
						<th>操作</th>
					</tr>
					<s:iterator value="logList" id="ll" status="ps">
						<tr>
							<s:if test="pageStatus!=null && pageStatus=='audit'">
								<td>
									<input type="checkbox" value="${ll.id}" name="ids"/>
								</td>
							</s:if>
							<td>${ps.index+1}</td>
							<td>${ll.sqTime}</td>
							<td>${ll.sqBanzu}</td>
							<td>${ll.borrowBanzu}</td>
							<td>${ll.borrowNum}</td>
							<td>${ll.gzHour}</td>
							<td>${ll.addUserName}</td>
							<td>${ll.addTime}</td>
							<td>${ll.remarks }</td>
							<td><a href="CircuitRunAction_findAduitPage.action?id=${ll.epId}">${ll.epStatus}</a></td>
							<td>
								<s:if test="#ll.epId!=null && #ll.cancalEpId!=null">
									<a href="CircuitRunAction_findAduitPage.action?id=${ll.cancalEpId}">取消审批动态</a>
								</s:if>
								<s:elseif test="#ll.epStatus!='同意'">
									<input type="button" value="删除" onclick="toDelete(${ll.id})"> 
								</s:elseif>
								<s:else>
									<input type="button" value="取消申请" onclick="toUnApply(${ll.id})">
								</s:else>
							</td>
						</tr>
					</s:iterator>
					<s:if test="pageStatus!=null && pageStatus=='audit'">
						<tr>
							<td>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
								全选
							</td>
							<td colspan="20">
								<input id="ok" class="input" style="width: 120px;" align="top"
									type="button" value="审批通过"
									onclick="toSubmitTl1(this.form,'ok')" />
								<input id="ng" class="input" align="top" type="button"
									value="审批驳回" onclick="toSubmitTl1(this.form,'no')" />
							</td>
						</tr>
					</s:if>
					<s:else>
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
					</s:else>
				</table>
				</form>
			</div>
		</div>
		<div style="display: none;">
			<div id ="dialogs" >
				<form method="post" id="applyForm">
					<p>申请备注：</p>
					<input type="hidden" value="" id="logId" name="id">
					<textarea rows="3" cols="40" name="remark" id="applyRemark"></textarea>
					<br>
				</form>
			</div>
		</div>
	</body>	
	<script type="text/javascript">
		function toDelete(id){
			if(confirm("确定要删除吗？")){
				location.href="productEBAction!deleteBorrowLog.action?id="+id;
			}
		}
		function toExportBorrowLog(objForm){
			objForm.action = "productEBAction!exportBorrowLog.action";
			objForm.submit();
			objForm.action = "productEBAction!findborrowLogByCon.action";
		}
		function toSubmitTl1(form,tag){
			form.action = "productEBAction!auditMultLogList.action?tag="+tag;
			form.submit();
		}
		function toUnApply(id){
			$("#logId").val(id);
			$("#dialogs").dialog({
		        buttons:{
		        	"提交":function(){
		        		var form = new FormData(document.getElementById("applyForm"));
		        	   	$.ajax({
		        		    url:"${pageContext.request.contextPath}/productEBAction!unApplyBorrow.action",
		        		    type:"post",
		        		    data:form,
		        		    processData:false,
		        		    contentType:false,
		        		    async : false, 
		        		    dataType:"json",
		        		    success:function(data){
		        	        	alert(data);	
		        	        	if(data.indexOf("申请成功")>=0){
		        	        		window.location.reload();
		        	        	}
		        		    },
		        		    error:function(e){
		        		        alert("错误！！");
		        		    }
		        	    });
		        	},
		        	"取消":function(){$(this).dialog('close');}
		        },
		        title:"取消申请对话框",
		        modal: true,// 创建模式对话框
		        width: 300,
		        height: "auto",
		        position: { my: "center top", at: "center top", of: window }
		    });	
		}
		
		
		
	</script>
</html>
