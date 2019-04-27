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
			<form action="productEBAction!addProduction.action" method="post"  id="myform" autocomplete=off >
				<div class="row clearfix">
					<h2 align="center">
						添加产品下线
					</h2>
					<div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">班组</span>
	                        <input class="form-control" type="text" name="pebProduction.banzu" value="${pebProduction.banzu }">
	                    </div>
	                </div>
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">产品编码</span>
	                        <input class="form-control" type="text" name="pebProduction.markId" value="${pebProduction.markId }">
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">产出台数</span>
	                        <input class="form-control" type="text" name="pebProduction.cuNumber" value="${pebProduction.cuNumber }">
	                    </div>
	                </div>
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">系数</span>
	                        <input class="form-control" type="text" name="pebProduction.xiShu" value="${pebProduction.xiShu }">
	                    </div>
	                </div>
				</div>
				 <div class="row">
					<div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">年</span>
	                        
	                        <s:if test="pebProduction.year!=null">
	   							<input type="text"class="form-control"  name="pebProduction.year"  value="${pebProduction.year }" readonly="readonly"/>
	   						</s:if>
	   						<s:else>
	   							<input type="text"class="form-control"  name="pebProduction.year"  value="${pebProduction.year }"/>
	   						</s:else>
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">月</span>
	                        <s:if test="pebProduction.month!=null">
	   							<input type="text"class="form-control"  name="pebProduction.month"  value="${pebProduction.month }" readonly="readonly"/>
	   						</s:if>
	   						<s:else>
	   							<input type="text"class="form-control"  name="pebProduction.month"  value="${pebProduction.month }"/>
	   						</s:else>
	                    </div>
	                </div>
	                <div class="form-group col-lg-4">
	                    <div class="input-group">
	                        <span class="input-group-addon">日</span>
	                        <s:if test="pebProduction.day!=null">
	                        	<input type="text"class="form-control"  name="pebProduction.day" value="${pebProduction.day }" readonly="readonly"/>
	                        </s:if>
	                        <s:else>
		   						<input type="text"class="form-control"  name="pebProduction.day" value="${pebProduction.day }" />
	                        </s:else>
	                    </div>
	                </div>
				</div>
<!-- 	            <div class="row"> -->
	                <!--下拉框-->
<!-- 	                <div class="form-group col-lg-3"> -->
<!-- 	                    <div class="input-group"> -->
<!-- 	                        <span class="input-group-addon">内容类型</span> -->
<!-- 	                        <select class="form-control"> -->
<!-- 	                            <option>default</option> -->
<!-- 	                            <option>one</option> -->
<!-- 	                            <option>two</option> -->
<!-- 	                        </select> -->
<!-- 	                    </div> -->
<!-- 	                </div> -->
<!-- 	            </div> -->
	            <div class="form-group">
					<label for="">
						异常
					</label>
					<input type="text" data-role="datebox" class="form-control"
						 name="pebProduction.message"  value="${pebProduction.message }">
				</div>
	            <div class="form-group">
					<label for="">
						采取措施
					</label>
					<textarea class="form-control" rows="3"
						name="pebProduction.measure" >${pebProduction.measure }</textarea>
				</div>
				<div class="form-group">
					<label for="">
						责任单位
					</label>
					<input type="text" data-role="datebox" class="form-control"  value="${ pebProduction.zrComp}" name="pebProduction.zrComp">
				</div>
				<div class="form-group">
					<label for="">
						责任单位措施
					</label>
					<textarea class="form-control" rows="3"
						name="pebProduction.zrCompMeasure" >${pebProduction.zrCompMeasure }</textarea>
				</div>
				<div class="form-group">
					<label for="">
						备注
					</label>
					<textarea class="form-control" rows="3"
						name="pebProduction.remarks" >${pebProduction.remarks}</textarea>
				</div>
				
				</br>
				<input type="hidden" value="${pebProduction.id}" name="pebProduction.id" id="proId">
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
			$jq("#myform").attr("action","productEBAction!updateProduction.action");
			$jq("#addOrUpdate").text("修改");
		}
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
	
	
	</script>
</html>
