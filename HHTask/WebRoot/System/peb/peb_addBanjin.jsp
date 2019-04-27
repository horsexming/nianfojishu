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
			<form action="productEBAction!addBanjin.action" method="post"  id="myform" autocomplete=off >
				<div class="row clearfix">
					<h2 align="center">
						添加工序加工量
					</h2>
					<div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">工序名称</span>
	                        <input class="form-control" type="text" name="banjin.processName" value="${banjin.processName}">
	                    </div>
	                </div>
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">系数</span>
	                        <input class="form-control" type="text" name="banjin.xiShu" value="${banjin.xiShu}">
	                    </div>
	                </div>
	            </div>
				 <div class="row">
					<div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">年</span>
	                        
	                        <s:if test="pebProduction.year!=null">
	   							<input type="text"class="form-control"  name="banjin.year"  value="${banjin.year }" readonly="readonly"/>
	   						</s:if>
	   						<s:else>
	   							<input type="text"class="form-control"  name="banjin.year"  value="${banjin.year }"/>
	   						</s:else>
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">月</span>
	                        <s:if test="pebProduction.month!=null">
	   							<input type="text"class="form-control"  name="banjin.month"  value="${banjin.month }" readonly="readonly"/>
	   						</s:if>
	   						<s:else>
	   							<input type="text"class="form-control"  name="banjin.month"  value="${banjin.month }"/>
	   						</s:else>
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">日</span>
	                        <s:if test="pebProduction.day!=null">
	                        	<input type="text"class="form-control"  name="banjin.day" value="${banjin.day }" readonly="readonly"/>
	                        </s:if>
	                        <s:else>
		   						<input type="text"class="form-control"  name="banjin.day" value="${banjin.day }" />
	                        </s:else>
	                    </div>
	                </div>
				</div>
	            <div class="row">
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">PEBS产出</span>
	                        <input class="form-control" type="text" id="PEBSNumber" onchange="changeNum()"
	                        		 name="banjin.PEBSNumber" value="${banjin.PEBSNumber}">
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">K3产出</span>
	                        <input class="form-control" type="text" id="K3Number" onchange="changeNum()"
	                        		 name="banjin.K3Number" value="${banjin.k3Number}" />
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">加工总量</span>
	                        <input class="form-control" type="text" id="cuNumber" onchange="changeNum()"
	                        		 name="banjin.cuNumber" value="${banjin.cuNumber}">
	                    </div>
	                </div>
				</div>
				<div class="form-group">
					<label for="">
						备注
					</label>
					<textarea class="form-control" rows="3"
						name="banjin.remarks" >${banjin.remarks}</textarea>
				</div>
				</br>
				<input type="hidden" value="${banjin.id}" name="banjin.id" id="proId">
				<input type="hidden" value="${tag}" name="tag">
				<input type="hidden" value="${cpage}" name="cpage">
				<button type="submit" class="btn btn-default" id="addOrUpdate" style="background-color: #fff;">
					添加
				</button>
				<div class="col-md-12 column">
<!-- 					<form method="post" id="fromFile"> -->
<!-- 						<input type="file" name="attachment" id="file" > -->
<!-- 						<input type="button" value="提交" class="input" onclick="submitFile()"> -->
<!-- 					</form> -->
				</div>
			</div>
			</form>
		</div>
		</div>
		</center>
	</body>	
	<script type="text/javascript">
	
	$jq(function(){
		var proId = $jq("#proId").val();
		if(proId!=""){
			$jq("#myform").attr("action","productEBAction!updateBanjin.action");
			$jq("#addOrUpdate").text("修改");
		}
	});
	
	
	function changeNum(){
		var PEBSNumber = $("#PEBSNumber").val();
		if(PEBSNumber==null){
			PEBSNumber=0;
		}
		
		var K3Number = $("#K3Number").val();
		if(K3Number==null){
			K3Number=0;
		}
		$("#cuNumber").val(Number(PEBSNumber)+Number(K3Number));
		
	}
	
	</script>
</html>
