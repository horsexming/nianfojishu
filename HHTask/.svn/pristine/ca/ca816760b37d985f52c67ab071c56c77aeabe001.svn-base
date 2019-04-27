<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/mobiscroll.core-2.5.2.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
<!--  		<script type="text/javascript" -->
<%--  			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script>  --%>
<!--  		<script type="text/javascript" -->
<%--  			src="${pageContext.request.contextPath}/js/mobiscroll.core-2.5.2.js"> </script>  --%>
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<form action="productEBAction!updatePebUsers.action" method="post"  id="myform" autocomplete=off >
				<div class="row clearfix">
					<h2 align="center">
						修改人事信息
					</h2>
					<div class="form-group col-lg-12">
	                    <div class="input-group">
	                        <span class="input-group-addon">班组</span>
	                        <input class="form-control" type="text" name="pebUsers.banZu" value="${pebUsers.banZu }" readonly="readonly">
	                    </div>
	                </div>
	            </div>
	            <div class="row">
					<div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">年</span>
	   						<input type="text"class="form-control"  name="pebUsers.year"  value="${pebUsers.year }" readonly="readonly"/>
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">月</span>
	   						<input type="text"class="form-control"  name="pebUsers.month"  value="${pebUsers.month }" readonly="readonly"/>
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">日</span>
	   						<input type="text"class="form-control"  name="pebUsers.day" value="${pebUsers.day }" readonly="readonly"/>
	                    </div>
	                </div>
				</div>
	            <div class="row">
	            	<div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">人事档案数</span>
	                        <input class="form-control" type="text" name="pebUsers.dangAnNum"
	                        	 value="${pebUsers.dangAnNum }" onchange="compluteActualNum()" id="dangAnNum">
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">未出勤人数</span>
	                        <input class="form-control" type="text" name="pebUsers.noChuQinNum" value="${pebUsers.noChuQinNum}"
								 onchange="compluteActualNum()" id="noChuQinNum">
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">上班小时数</span>
	                        <input class="form-control" type="text" name="pebUsers.gzHour" value="${pebUsers.gzHour}"
	                         id="gzHour">
	                        <input type="hidden" name="pebUsers.borrowNum" value="${pebUsers.borrowNum}" id="borrowNum">
	                        <input type="hidden" name="pebUsers.lendNum" value="${pebUsers.lendNum}" id="lendNum">
	                    </div>
	                </div>
				</div>
				
				<div class="form-group row" id="appendBorrow">
					<label for="">借入信息&nbsp;&nbsp;<a onclick="addBorrowLog()">增加</a></label>
					<br><br>
					<s:if test="pebUsers.borrowLogList!=null && pebUsers.borrowLogList.size()>0">
						<input type="hidden" value="${pebUsers.borrowLogList.size()}" id="borrowMaxIndex">
						<s:iterator value="pebUsers.borrowLogList" id="log" status="ps">
							<div id="showBorrowDiv_${ps.index}">
								<div class="form-group col-lg-5">
				                    <div class="input-group">
				                        <span class="input-group-addon">借入班组</span>
				                        <input class="form-control" type="text" name="pebUsers.borrowLogList[${ps.index}].borrowBanzu" 
				                        	value="${log.borrowBanzu}" class="borrowBanzu_${ps.index }">
				                    </div>
				                </div>
								<div class="form-group col-lg-5">
				                    <div class="input-group">
				                        <span class="input-group-addon">借入人数</span>
				                        <input class="form-control" type="text" name="pebUsers.borrowLogList[${ps.index }].borrowNum" 
				                        	value="${log.borrowNum}" onchange="compluteActualNum()" id="borrowNum_${ps.index}">
				                    </div>
				                </div>
				                <div class="form-group col-lg-2"> 
									<input type="button" value="删除"  class="btn btn-default"  onclick="delLine('borrow','_${ps.index}')">
				                </div>
							</div>
						</s:iterator>
					</s:if>
					
				</div>
				<div class="form-group row" id="appendLend">
					<label for="">借出信息&nbsp;&nbsp;<a onclick="addLendLog()">增加</a></label>
					<br><br>
					
					<s:if test="pebUsers.lendLogList!=null && pebUsers.lendLogList.size()>0">
						<input type="hidden" value="${pebUsers.lendLogList.size()}" id="lendMaxIndex">
						<s:iterator value="pebUsers.lendLogList" id="log" status="ps">
							<div id="showLendDiv_${ps.index}">
								<div class="form-group col-lg-5">
				                    <div class="input-group">
				                        <span class="input-group-addon">借出班组</span>
				                        <input class="form-control" type="text" name="pebUsers.lendLogList[${ps.index}].lendBanzu" 
				                        	value="${log.lendBanzu}" class="lendBanzu_${ps.index }">
				                    </div>
				                </div>
								<div class="form-group col-lg-5">
				                    <div class="input-group">
				                        <span class="input-group-addon">借出人数</span>
				                        <input class="form-control" type="text" name="pebUsers.lendLogList[${ps.index }].lendNum" 
				                        	value="${log.lendNum}" onchange="compluteActualNum()" id="lendNum_${ps.index}">
				                    </div>
				                </div>
				                <div class="form-group col-lg-2"> 
									<input type="button" value="删除"  class="btn btn-default"  onclick="delLine('lend','_${ps.index}')">
				                </div>
							</div>
						</s:iterator>
					</s:if>
				</div>
				<div class="row">
					<div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">实际出勤人数</span>
	                        <input class="form-control" type="text" name="pebUsers.actualNum" 
	                        	value="${pebUsers.actualNum }" readonly="readonly" id="actualNum">
	                    </div>
	                </div>
				</div>
				<div class="form-group">
					<label for="">
						备注
					</label>
					<textarea class="form-control" rows="3"
						name="pebUsers.remarks" >${pebUsers.remarks}</textarea>
				</div>
				
				</br>
				<input type="hidden" value="${pebUsers.id}" name="pebUsers.id" id="proId">
				<input type="hidden" value="${cpage}" name="cpage">
				<button type="submit" class="btn btn-default" id="addOrUpdate" style="background-color: #fff;">
					修改
				</button>
			</div>
			</form>
		</div>
		<textarea id="borrowDiv" style="display: none;">
			<div id="showBorrowDiv_0">
				<div class="form-group col-lg-5">
                    <div class="input-group">
                        <span class="input-group-addon">借入班组</span>
                        <input class="form-control" type="text" name="pebUsers.borrowLogList[0].borrowBanzu" 
                          class="borrowBanzu_0">
                    </div>
                </div>
				<div class="form-group col-lg-5">
                    <div class="input-group">
                        <span class="input-group-addon">借入人数</span>
                        <input class="form-control" type="text" name="pebUsers.borrowLogList[0].borrowNum" 
                        onchange="compluteActualNum()" id="borrowNum_0">
                    </div>
                </div>
                <div class="form-group col-lg-2"> 
					<input type="button" value="删除"  class="btn btn-default"  onclick="delLine('borrow','_0')">
                </div>
        	</div>
		</textarea>
		
		<textarea id="lendDiv" style="display: none;">
			<div id="showLendDiv_0">
				<div class="form-group col-lg-5">
                    <div class="input-group">
                        <span class="input-group-addon">借出班组</span>
                        <input class="form-control" type="text" name="pebUsers.lendLogList[0].lendBanzu" 
                          class="lendBanzu_0">
                    </div>
                </div>
				<div class="form-group col-lg-5">
                    <div class="input-group">
                        <span class="input-group-addon">借出人数</span>
                        <input class="form-control" type="text" name="pebUsers.lendLogList[0].lendNum" 
                        onchange="compluteActualNum()" id="lendNum_0">
                    </div>
                </div>
                <div class="form-group col-lg-2"> 
					<input type="button" value="删除"  class="btn btn-default"  onclick="delLine('lend','_0')">
                </div>
        	</div>
		</textarea>
	</body>	
	<script type="text/javascript">
	function compluteActualNum(){
		var dangAnNum = $("#dangAnNum").val();//人事档案数
// 		var borrowNum = $("#borrowNum").val();//借入人数
// 		var lendNum = $("#lendNum").val();//借出人数
		var borrowNumCount = 0;
		for(var i=0;i<=borrowIndex;i++){
			if($("#borrowNum_"+i)!=null){
				var borrowNum = $("#borrowNum_"+i).val();
				if(borrowNum !=null && borrowNum!=""){
					borrowNumCount+=Number(borrowNum);
					
				}
			}
		}
		$("#borrowNum").val(borrowNumCount);
		var lendNumCount = 0;
		for(var i=0;i<=lendIndex;i++){
			if($("#lendNum_"+i)!=null && $("#lendNum_"+i).val()!="" && $("#lendNum_"+i).val()!=0){
				var lendNum =$("#lendNum_"+i).val();
				if(lendNum!=null && lendNum!=""){
					lendNumCount += Number(lendNum);
				}
			}
		}
		$("#lendNum").val(lendNumCount);
		var noChuQinNum = $("#noChuQinNum").val();//未出勤人数
		var gzHour = $("#gzHour").val();//上班小时数

		if(noChuQinNum==null || noChuQinNum<0){
			noChuQinNum=0;
		}
		if(dangAnNum!=null && dangAnNum>0){
			//实际出勤Object
			$("#actualNum").val(Number(dangAnNum)+Number(borrowNumCount)-Number(lendNumCount)-Number(noChuQinNum));
			//$("#actualNum").val(dangAnNum+borrowNum-lendNum-noChuQinNum);
			
		}
	}
	
	var borrowIndex = 0;
	var lendIndex = 0;
	$(function(){
		if($("#borrowMaxIndex")!=null && $("#borrowMaxIndex").val()>0){
			var borrowMaxIndex = $("#borrowMaxIndex").val();
			borrowIndex = borrowMaxIndex-1;
		}
		addBorrowLog();
		
		if($("#lendMaxIndex")!=null && $("#lendMaxIndex").val()>0){
			var lendMaxIndex = $("#lendMaxIndex").val();
			lendIndex = lendMaxIndex-1;
		}
		addLendLog();
		///var borrowDiv = $("#borrowDiv").val();
		
	});
	
	
	function addBorrowLog(){
		var borrowDiv  = $("#borrowDiv").val();
		borrowIndex++;
		while (borrowDiv.indexOf('_0') >= 0){
			borrowDiv = borrowDiv.replace('_0', '_'+borrowIndex);
			borrowDiv = borrowDiv.replace('[0]', '['+borrowIndex+']');
		}
		
		$("#appendBorrow").append(borrowDiv);
// 		alert(borrowDiv);
	}
	
	function addLendLog(){
		var lendDiv  = $("#lendDiv").val();
		lendIndex++;
		while (lendDiv.indexOf('_0') >= 0){
			lendDiv = lendDiv.replace('_0', '_'+lendIndex);
			lendDiv = lendDiv.replace('[0]', '['+lendIndex+']');
		}
		
		$("#appendLend").append(lendDiv);
	}
	
	function delLine(type,index){
		if(type=='borrow'){
			$("#showBorrowDiv"+index).remove();
		}else if(type=='lend'){
			$("#showLendDiv"+index).remove();
		}
		//$(obj).parent().parent().remove();
		
	}
	</script>
</html>
