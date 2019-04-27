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
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
	</script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
	</script>
	</head>
	<style type="text/css">
		.upload{position:relative; display:inline-block; height:33px;line-height:33px; overflow:hidden;vertical-align:middle; cursor:pointer;}
	.upload .upload-input-file{position:absolute; right:0; top:0; font-size:100px; opacity:0; filter:alpha(opacity=0);cursor:pointer;}
	.upload .upload-btn{outline:none;border:0; padding:7px 10px;cursor:pointer; margin:3px; border-radius:3px;}
	.upload .upload-url { -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box; border-radius:2px;
	    border: solid 1px #ddd; width: 200px; height:30px;-webkit-line-height: 1;line-height: 30px\9;-ms-line-height: 30px; text-indent:3px;}
	.upload .upload-tip {display:none; background-color:#1c84c6; padding:2px 10px; color:#fefefe; font-size:12px !important;border-radius:3px;}
	.horizontalLine{
		text-align:center;
	}
  </style>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<form action="ReliabilityTestAction!submitTestResult.action" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${sheet.id}" name="sheet.id" />
			<table class="table">
				<tr align="center">
					<td colspan="8" style="font-size: 20px;">
						可靠性项目测试
					</td>
				</tr>
				
				<tr>
					<th align="right" class="col-xs-1">申请单流水号:</th>
					<td class="col-xs-2"><input type="text" name="sheet.number" value="${sheet.number }" readonly="readonly"></td>
					<th align="right" class="col-xs-1">申请单位:</th>
					<td class="col-xs-2"><input type="text" name="sheet.company" value="${sheet.company }" readonly="readonly"></td>
					<th align="right" class="col-xs-1">申请日期:</th>
					<td class="col-xs-2"><input type="text" name="sheet.addTime" value="${sheet.addTime }" readonly="readonly"></td>
					<th align="right" class="col-xs-1">申请人:</th>
					<td class="col-xs-2"><input type="text" name="sheet.addUserName"  value="${sheet.addUserName }" readonly="readonly"></td>
				</tr>
				<tr>
					<th align="right">物料类别:</th>
					<td><input type="text" name="sheet.materialCategory" value="${sheet.materialCategory }" readonly="readonly"></td>
					<th align="right">物料来源:</th>
					<td><input type="text" name="sheet.materialResource" value="${sheet.materialResource }" readonly="readonly"></td>
					<th align="right">样品编号:</th>
					<td colspan="3"><input type="text" name="sheet.specimenNum" value="${sheet.specimenNum }" readonly="readonly"></td>
				</tr>
				<tr>
					<th align="right">机柜名称:</th>
					<td><input type="text" name="sheet.jGname" value="${sheet.jGname }" readonly="readonly"></td>
					<th align="right">件号:</th>
					<td><input type="text" name="sheet.markId" value="${sheet.markId }" readonly="readonly"></td>
					<th align="right">检验批次号:</th>
					<td colspan="3"><input type="text" name="sheet.lotId" value="${sheet.lotId }" readonly="readonly"></td>
				</tr>
				<tr>
					<th align="right">测试项目:</th>
					<td colspan="7">
						<s:iterator value="proList" id="pro" >
							<s:if test="sheet.recordSet!=null&&sheet.recordSet.size()>0">
								<s:if test="listString.contains(#pro.proName)">
									&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${pro.proName }" 
										name="recordList.proName" onchange="changePro(this)" checked="checked">${pro.proName }
								</s:if>		
								<s:else>
									&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${pro.proName }" 
										name="recordList.proName" onchange="changePro(this)">${pro.proName}
								</s:else>
							</s:if>
							<s:else>
								&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${pro.proName }" 
									name="recordList.proName"onchange="changePro(this)">${pro.proName }
							</s:else>
							
						</s:iterator>
					</td>
				</tr>
			</table>
			<br/>
			<br/>
			<br/>
			<table class="table">
				<thead>
					<tr>
						<th colspan="3" style="font-size: 20px;text-align: center;">
							测试记录
							<input type="hidden" value="${sheet.recordSet.size()}" id="opt_index"/>
						</th>
					</tr>
					<tr>
						<th class="col-xs-2">测试项目</th>
						<th class="col-xs-7">测试记录</th>
						<th class="col-xs-5">附件</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="sheet.recordSet" id="record" status="pageStatus" >
						<tr>
							<td>${record.proName }
								<input type="hidden" value="${record.id}" name="recordList[${pageStatus.index}].id" />
								<input type="hidden" value="${record.proName}" name="recordList[${pageStatus.index}].proName" />
							</td>
							<td><textarea rows='3' cols='100' name='recordList[${pageStatus.index}].testRecord'>${record.testRecord }</textarea></td>
							<td><a href="${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/rts/${record.testFile }">${record.otherFileName }</a>
								<br/>
								<input type='file' name='attachments[${pageStatus.index}]' onchange='changeFile(${pageStatus.index},this)' >
								<input type='hidden' name='attachmentsFileName[${pageStatus.index}]' id='fileName_${pageStatus.index}' >
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td class="col-xs-12" colspan="3" style="padding: 0;margin: 0">
							<table class="table" style="padding: 0;margin: 0">
								<tr>
									<th class="col-xs-2">记录人</th>
									<td class="col-xs-3" style="margin: 0;padding: 0">
										<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.jlPerson" >${sheet.jlPerson}</textarea>
									</td>
									<th class="col-xs-3">记录填写日期</th>
									<td class="col-xs-4" style="margin: 0;padding: 0">
										<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.jlAddTime">${sheet.jlPerson}</textarea>
									</td>
								</tr>
								<tr>
									<th class="col-xs-2">判定标准</th>
									<td class="col-xs-3" style="margin: 0;padding: 0">
										<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.decideBasis" >${sheet.decideBasis}</textarea>
									</td>
									<th class="col-xs-3">判定结果</th>
									<td class="col-xs-4" style="margin: 0;padding: 0">
										<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.decideResult">${sheet.decideResult}</textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						
					</tr>
				</tfoot>
			</table>
			<br/>
<%--			<br/>--%>
<%--			<br/>--%>
<%--			<table class="table" align="center">--%>
<%--				<tr>--%>
<%--					<th colspan="4" style="font-size: 20px;text-align: center">结果判定</th>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<th class="col-xs-2">判定依据</th>--%>
<%--					<td class="col-xs-4" style="margin: 0;padding: 0">--%>
<%--						<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.decideBasis">${sheet.jlPerson}</textarea>--%>
<%--					</td>--%>
<%--					<th class="col-xs-2">判定结果</th>--%>
<%--					<td class="col-xs-4" style="margin: 0;padding: 0">--%>
<%--						<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.decideResult">${sheet.jlPerson}</textarea>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<th colspan="1" class="col-xs-2">记录/异常描述</th>--%>
<%--					<td colspan="3" class="col-xs-10" style="margin: 0;padding: 0">--%>
<%--						<textarea style="width: 100%;height: 70px;padding: 0;resize:none;border:none" name="sheet.errRemark">${sheet.jlPerson}</textarea>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--			</table>--%>
			<div align="center">
				<input class="input" type="submit" value="提交"/>
			</div>
		</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		
	</body>
<script type="text/javascript">
	var opt_index = 0;
	$(function(){
		var index = $("#opt_index").val();
		if(index!=null && index>0){
			opt_index = index;
		}
	})
	function changePro(object){
		
		var proName = $(object).val();
		if($(object).is(':checked')){//选中
			// multiple='multiple' 
			$("#tbody").append("<tr></tr><tr><td><input type='hidden' value='"+proName+"' name='recordList["+
			opt_index+"].proName'>"+proName+"</td>"+
				"<td><textarea rows='3' cols='100' name='recordList["+opt_index+"].testRecord'></textarea></td>"+
				"<td><input type='file' name='attachments["+opt_index+"]'onchange='javascript:changeFile("+opt_index+",this)' >" +
				"	<input type='hidden' name='attachmentsFileName["+opt_index+"]' id='fileName_"+opt_index+"' ></td></tr>");
		}else{
			
			var html = $("#tbody").html();
			var trHtml = html.split("<tr></tr>");
			for(var i =0;i<trHtml.length;i++){
				var tr = trHtml[i];
				if(tr.indexOf(proName)>=0){
					$("#tbody tr").filter(":contains('"+proName+"')").remove();
				}
			}
		}
		opt_index++;
	}

	function changeFile(index,obj){
		 var fileUrl = $(obj).val();
		 var urlArr = fileUrl.split("\\");
		 var getName = urlArr[urlArr.length-1];
		 $("#fileName_"+index).val(getName);
	 }
	 
	 function changeFile(index,obj){
		 var fileUrl = $(obj).val();
		 var urlArr = fileUrl.split("\\");
		 var getName = urlArr[urlArr.length-1];
		 $("#fileName_"+index).val(getName);
	 }
</script>
</html>
