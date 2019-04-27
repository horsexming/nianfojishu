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
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
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
	<body>
		<div class="container">
			<h2 class="text-center">班组信息</h2>
			<form action="productEBAction!.action" method="post" id="banzuForm">
				<div class="form-group col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon">名&nbsp;&nbsp;&nbsp;称</span>
                        <input id="jiegouName" type="text" name="pebBanzuJiegou.name" value="${pebBanzuJiegou.name }"  />
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label for="name">计算效率</label>
                    <div class="radio">
                    	<s:iterator value="{'是','否'}" id="isSe" >
							<label>
								<s:if test="#isSe==pebBanzuJiegou.se">
									<input type="radio" name="pebBanzuJiegou.se"  value="${isSe}" checked="checked">${isSe}
								</s:if>                    		
								<s:else>
									<input type="radio" name="pebBanzuJiegou.se"  value="${isSe}" >${isSe}
								</s:else>
							</label>
                    	</s:iterator>
                    </div>
                </div>
                <div class="form-group col-lg-12">
					<input type="button" value="修改" onclick="updateContent()" class="input" />
					<input type="button" value="添加下层" onclick="addSubstratum(this.form)" class="input" />
					<input type="button" value="删除" onclick="deleteBanzuJiegou()" class="input" />
					<input type="hidden"  value="${pebBanzuJiegou.id}" name="pebBanzuJiegou.fatherId">
					<input type="hidden"  value="${pebBanzuJiegou.belongLayer+1}" name="pebBanzuJiegou.belongLayer">
				</div>
			</form>	
				<br><br>
               <div class="form-group col-lg-12" id="showPrincipalsDiv">
				<label for="">
					负责人列表<a href="#" onclick="addPrincipals()">添加</a>
				</label>
				<s:iterator value="pebBanzuJiegou.userList" id="users" >
					<div class="row">
						<div class="form-group col-xs-3">
							<div class="input-group">
								<span class="input-group-addon">工&nbsp;&nbsp;&nbsp;号</span>
								<input type="text" name="users.code" value="${users.code}"  />
							</div>
						</div>
						<div class="form-group col-xs-3">
							<div class="input-group">
								<span class="input-group-addon">姓&nbsp;&nbsp;&nbsp;名</span>
								<input type="text" name="users.code" value="${users.name}"  readonly="readonly" />
							</div>
						</div>
						
						<div class="form-group col-xs-3">
							<div class="input-group">
								<span class="input-group-addon">职&nbsp;&nbsp;&nbsp;位</span>
								<input type="text" name="users.code" value="${users.duty}" readonly="readonly" />
							</div>
						</div>
						<div class="form-group col-xs-1">
						</div>
						<div class="form-group col-xs-2">
							<div class="input-group">
								<input type="button" value="删除" onclick="deletePrincipals(${users.id})">
							</div>
						</div>
						
					</div>	
				</s:iterator>
			</div>
		</div>
		<textarea style="display: none;" id="addPrincipalsText">
		<div class="row">
			<div class="form-group col-xs-3">
				<div class="input-group">
					<span class="input-group-addon">工&nbsp;&nbsp;&nbsp;号</span>
					<input type="text" name="users.code"  placeholder="请输入工号" onblur="getUsersByCode(this)"/>
				</div>
			</div>
			<div class="form-group col-xs-3">
				<div class="input-group">
					<span class="input-group-addon">姓&nbsp;&nbsp;&nbsp;名</span>
					<input type="text" name="users.code" readonly="readonly"/>
				</div>
			</div>
			
			<div class="form-group col-xs-3">
				<div class="input-group">
					<span class="input-group-addon">职&nbsp;&nbsp;&nbsp;位</span>
					<input type="text" name="users.code" readonly="readonly" />
				</div>
			</div>
			<div class="form-group col-xs-1">
			</div>
			<div class="form-group col-xs-2">
				<div class="input-group">
					<input type="button" value="删除" onclick="deletePrincipals(${users.id})">
				</div>
			</div>
		</div>
		</textarea>
	</div>
	</body>
	<script type="text/javascript">
	
		//根据users 的id删除负责人
		function deletePrincipals(id){
			if(confirm("确定要删除吗？")){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/productEBAction!deletePrincipals.action",
					data:{
						"id":id,
						"pebBanzuJiegou.id":"${param.id}"
					},
					dataType:"json",
					success:function(data){
						if(data!=null && data=="true"){
							alert("删除成功");
							window.location.reload();
						}else{
							alert("删除失败");
						}
					},error:function(){
						alert("系统异常");
					}
				
				});
			}
		
		}
		//添加负责人
		function getUsersByCode(obj){
			var code = obj.value;
			if(code==null || code==""){
				return false;
			}
			$.ajax({
				url : '${pageContext.request.contextPath}/productEBAction!addBanzuPrincipals.action',
				type:"post",
				data:{
					"code":code,
					"pebBanzuJiegou.id":"${param.id}"
				},
				dataType : 'json',
				async : false,
				success : function(data) {
					if(data!=null && data=="true"){
						alert("添加成功");
						window.location.reload();
					}else{
						$(obj).val(null);
						$(obj).attr("placeholder","人员不存在");
					}
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
		
		
		function addPrincipals(){
			var addPrincipalsText= $("#addPrincipalsText").val();
			$("#showPrincipalsDiv").append(addPrincipalsText);
		}
		
		//修改名称
		function updateContent(){
			var id ="${param.id}";
			var jiegouName = $("#jiegouName").val();
			var se = $("input[name='pebBanzuJiegou.se']:checked").val();
			if(jiegouName==null || jiegouName==""){
				alert("请输入名称");
				return false;
			}
			$.ajax({
				type:"post",
				url:"productEBAction!updateBanzuJieGou.action",
				data:{
					"pebBanzuJiegou.id":id,
					"pebBanzuJiegou.name":jiegouName,
					"pebBanzuJiegou.se":se
				},
				dataType:"json",
				success:function(data){
					if(data=="true"){
						alert("修改成功");
						window.parent.location.reload();
					}else{
						alert(data);
					}
				},error:function(){
					alert("系统异常");
				}
			});
		}
		
		function addSubstratum(form){
			var formData = new FormData(form);
			$jq.ajax({
	              url:"productEBAction!addBanzuJiegou.action",
	              type:"post",
	              data:formData,
	              processData:false,
	              contentType:false,
	              async : false,
	              dataType:"json",
	              success:function(data){
	            	  if( data=="true"){
			              	alert("添加成功");
							window.parent.location.reload();
	            	  }else{
	            		  alert(data);
	            	  }
	              },
	              error:function(e){
	                  alert("系统异常");
	              }
	          });
		}
		
		function deleteBanzuJiegou(){
			if(confirm("确定要删除吗？")){
				$.ajax({
					url : '${pageContext.request.contextPath}/productEBAction!deletePebBanzuJiegou.action',
					type:"post",
					data:{
						"id":"${param.id}"
					},
					dataType : 'json',
					async : false,
					success : function(data) {
						if(data!=null && data=="true"){
							alert("删除成功");
							window.parent.location.reload();
						}else{
							alert(data);
						}
					},
					error : function() {
						alert("服务器异常!");
					}
				});
			}
		}
		
	</script>
</html>
