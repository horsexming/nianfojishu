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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			   <div align="center">
			    <h3>机</h3>
			   </div>
			   <s:if test="processTemplateJY.progressStatus=='未分析'||processTemplateJY.progressStatus=='分析中'">
			<form action="procardTemplateJYAction_updatejgljwr.action" method="post" onsubmit="return validateFrom();">
				<table>
					<tr>
						<th>
							改进方案：
						</th>
						<td>
							<textarea id="updateContext" name="updateContext" rows="5" cols="40"><s:property value="processTemplateJY.contextJi"/></textarea>
						</td>
					</tr>
					<tr>
						<th>
							设备节拍
						</th>
						<td>
						<input type="hidden" value="ji" name="pageStatus">
						<input type="hidden" value="<s:property value="processTemplateJY.id"/>" name="processTemplateJY.id">
                         <input id="opshebeijiepai" name="processTemplateJY.opshebeijiepai"
                         value="<s:property value="processTemplateJY.opshebeijiepai"/>" onkeyup="checkNum()"/>
						</td>
					</tr>
					<tr>
					 <td colspan="2" align="center">
					  <input type="submit" value="修改" class="input">
					 </td>
					</tr>
				</table>
				</form>
				</s:if>
				<s:else>
				<br/>
				改进方案：<textarea id="updateContext" name="updateContext" rows="5" cols="40"><s:property value="processTemplateJY.contextJi"/></textarea>
				<div id="showAll" style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;width: 200px"></div>
						<br/>选择设备：	<s:if test="processTemplateJY.shebeiId!=null">
							<input type="text" id="shortname" onkeyup="getAllNames()" style="height: 20px; width: 200px" onFocus="init()"
								onBlur="hidediv()"  value="<s:property value="processTemplateJY.shebeiNo"/>::<s:property value="processTemplateJY.shebeiName"/>" />
                        </s:if>
                        <s:else>
							<input type="text" id="shortname" onkeyup="getAllNames()" style="height: 20px; width: 200px" onFocus="init()"
								onBlur="hidediv()"   />
                        </s:else><br/>
								<input type="button" value="确定" onclick="updateSheBei()"/>
				</s:else>
				<input type="hidden" value="<s:property value='successMessage'/>" id="successMessage">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		 function checkNum(){
			 var needNumber= $("#opshebeijiepai").val();
			 if(isNaN(needNumber)){
				 alert("请输入数字！");
				$("#opshebeijiepai").val(0);
			 }
		 }
		 function validateFrom(){
			 var updateContext= $("#updateContext").val();
			 if(updateContext==null||updateContext==""){
				 alert("请填写改进方案！");
				 return false;
			 }
		 }
		 $(document).ready(function(){
			  var successMessage=$("#successMessage").val();
			if(successMessage!=""){
　　                          alert(successMessage);
　　                          }
		  });
function selectdiv(obj) {
	var shortname = document.getElementById("shortname");
	//shortname.value = obj.innerHTML;
	shortname.value =divvalue=$(obj).find("span").html();
	var showAll = document.getElementById("showAll");
	showAll.style.visibility = "hidden";
}

//ajax获取所有的类似的全称   oaAppDetailAction!findchildClass.action
function getAllNames() {
	var shortname=$("#shortname").val();
	if(shortname==null||shortname==""){
		$("#showAll").empty();
		return;
	}
	var shortnames=shortname.split("::");
	var shortNo="";
	var shortname2="";
	if(shortnames.length=2){
		shortNo=shortnames[0];
		shortname2=shortnames[1];
	}else{
		shortNo=shortnames[0];
		shortname2=shortnames[0];
	}
	$.ajax({
				type : "POST",
				url : "procardTemplateJYAction_getAllName.action",
				dataType : "json",
				data : {
					noandname:$("#shortname").val(),
					pageStatus : "ji"
				},
				success : function(data) {
					$("#showAll").empty();
					$(data).each(function() {
										var no = $(this).attr('no').replace(shortNo,"<font color='red'>"+shortNo
																+ "</font>");
										var name = $(this).attr('name').replace(shortname2,"<font color='red'>"+shortname2
																+ "</font>");
										$("#showAll").append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)' align='left'>"
																+ no
																+ "::"
																+ name
																+"<span style='display: none;'>"
																+ $(this).attr('no')
																+ "::"
																+ $(this).attr('name')
																+ "</span></div>");
									});
				}
	});
}
function updateSheBei(){
	$.ajax({
		type : "POST",
		url : "procardTemplateJYAction_updateSBGZLJ.action",
		dataType : "json",
		data :{
		id : ${processTemplateJY.id},
		  noandname : $("#shortname").val(),
		  pageStatus : "ji"
		},
		success : function (data){
		 if(data.success){
			 alert("修改成功!");
		 }else{
			 alert("修改失败!找不到该设备!");
		 }
		}
	});
	
}
		</script>
	</body>
</html>
