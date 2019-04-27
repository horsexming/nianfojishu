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
		<style type="text/css">
			.ztree{background-color: #ffffff;position: absolute;  z-index: 100; top: 40px;width: 500px;display: none;}
		</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<form action="productEBAction!noChuQinApplyAudit.action" method="post" 
					 id="myform" autocomplete=off onsubmit="return check()">
				<div class="row clearfix">
					<h2 align="center">
						未出勤人数填报
					</h2>
	            </div>
	            <div class="row">
	            	<div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">申请班组</span>
	                        <select class="form-control" name="pebUsers.banZu" id="sqBanzu" value="${subTeam.subName }" >
	                        	<option value="">请选择</option>
	                        	<s:if test="subTeamList!=null && subTeamList.size()>0">
	                        		<s:iterator value="subTeamList" id="st" >
	                        			<s:if test="#st.subName==subTeam.subName">
	                        				<option value="${st.subName}" selected="selected">${st.subName}</option>
	                        			</s:if>
	                        			<s:else>
			                            	<option value="${st.subName}">${st.subName}</option>
	                        			</s:else>
	                        		</s:iterator>
	                        	</s:if>
	                        </select>
	 	                </div>
	                </div>
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">未出勤人数</span>
	                        <input class="form-control" type="text" name="pebUsers.applyNum" id="noChuQinNum">
	                    </div>
	                </div>
	            </div>
	            	
	            <div class="row">
	            	<div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">年份</span>
	                        <input class="form-control" type="text" name="pebUsers.year" id="year">
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">月份</span>
	                        <input class="form-control" type="text" name="pebUsers.month" id="month">
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">日</span>
	                        <input class="form-control" type="text" name="pebUsers.day" id="day">
	                    </div>
	                </div>
	            </div>
	            <div class="form-group">
					<label for="">
						备注
					</label>
					<input type="text" data-role="datebox" class="form-control" name="pebUsers.applyRemark">
				</div>
				</br>
				<button type="submit" class="btn btn-default text-center" >
					提交
				</button>
			</div>
			</form>
		</div>
		</div>
	</body>	
	<script type="text/javascript">
	
	$jq(function(){
		//设置默认时间
		var d = new Date();
	 	var day = d.getDate();        //获取当前日(1-31)
	 	if((day-10)<0){
	 		day="0"+day;
	 	}
	    
	    $("#year").val(d.getFullYear().toString());
	    $("#month").val(d.getMonth()+1);
	    $("#day").val(day);
	});
	
	function submitFile(){
		var form = new FormData(document.getElementById("fromFile"));
	    var file = $jq("#file").val();
	    if(file!=null){
	    	$jq.ajax({
	              url:"${pageContext.request.contextPath}/productEBAction!importPdata.action",
	              type:"post",
	              data:form,
	              processData:false,
	              contentType:false,
	              async : false,
	              dataType:"json",
	              success:function(data){
	            	  if(data!=""){
			              	alert(data);
	            	  }else{
	            		  alert("导入成功");
	            	  }
	              },
	              error:function(e){
	                  alert("错误！！");
	              }
	          });
	    }
	}
	
	//提交验证
	function check(){
		var sqBanzu = $("#sqBanzu").val();
		var noChuQinNum = $("#noChuQinNum").val();
		if(sqBanzu==null || sqBanzu==""){
			alert("请选择申请班组");
			return false;
		}
		if(borrowDate==null || borrowDate==""){
			alert("请选择借入时间");
			return false;
		}
		if(borrowBanzu==null || borrowBanzu==""){
			alert("请选择借出班组");
			return false;
		}
		if(noChuQinNum==null || noChuQinNum==""){
			alert("请选择借入班组人数");
			return false;
		}
		return true;
	}
	</script>
</html>
