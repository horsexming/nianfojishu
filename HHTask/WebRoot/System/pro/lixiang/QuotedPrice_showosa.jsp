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
				<table class="table">
				  <tr>
				  <td><input type="checkbox" name="checkAll" id="checkAll" onchange="chageAllCheck()">全选
				      <input type="hidden" id="ids">
				  </td>
				   <td>零件号
				   </td>
				   <td>零件名
				   </td>
				   <td>工序号
				   </td>
				   <td>工序名称
				   </td>
				   <td>执行状态
				   </td>
				   <td>操作
				   </td>
				  </tr>
				  <s:iterator value="osaList" id="osa">
				  <tr>
				  <td>
				   <s:if test="#osa.executeStatus!='评审完成'">
				  <input type="checkbox" name="checkboxs" id="checkboxs" value="<s:property value='#osa.id'/>" onchange="chageNum()">
				  </s:if>
				  </td>
				   <td><s:property value="#osa.markID"/>
				   </td>
				   <td><s:property value="#osa.partName"/>
				   </td>
				   <td><s:property value="#osa.processNO"/>
				   </td>
				   <td><s:property value="#osa.processName"/>
				   </td>
				   <td><s:property value="#osa.executeStatus"/>
				   </td>
				   <td><s:if test="#osa.executeStatus!='评审完成'">
				         <a onclick="sendnotify(<s:property value="#osa.id"/>)">发送通知</a>
				       </s:if>
				   </td>
				   </tr>
				  </s:iterator>
				  <s:if test="osaList!=null&&osaList.size()>0">
				   <tr>
				    <td colspan="6" align="center">
				     <a onclick="sendnotify()">通知全部</a>
				    </td>
				   </tr>
				  </s:if>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		 function sendnotify(val){
			 var ids;
			 if(val!=null&&val!=""){
				ids=val; 
			 }else{
				ids=$("#ids").val(); 
			 }
			 if(ids==null||ids==""){
				 alert("没有选中的外委外购评审");
				 return ;
			 }
			 $.ajax({
				 type : "post",
				 dataType : "json",
				 url : "QuotedPrice_sendOsaNotify.action",
				 data : {
				   ids : ids
				 },
				 success : function(data){
					 alert(data);
				 }
			 })
		 }
		 function chageAllCheck(){
			var checkAll=document.getElementById("checkAll");
			var checkboxs=document.getElementsByName("checkboxs");
			    var ids="";
			if(checkAll.checked==true){
				for(var i=0;i<checkboxs.length;i++){
					checkboxs[i].checked=true;
					if(i==0){
						ids=checkboxs[i].value;
					}else{
						ids=ids+","+checkboxs[i].value;
					}
				}
				
			}else{
				for(var i=0;i<checkboxs.length;i++){
					checkboxs[i].checked=false;
				}
			}
			$("#ids").val(ids);
		}
		function chageNum(){
			var checkAll=document.getElementById("checkAll");
			var checkboxs=document.getElementsByName("checkboxs");
			var count=0;
			var ids="";
			for(var i=0;i<checkboxs.length;i++){
				if(checkboxs[i].checked==false){
				}else{
					if(count==0){
						ids=checkboxs[i].value;
					}else{
						ids=ids+","+checkboxs[i].value;
					}
					count++;
				}
			}
			$("#ids").val(ids);
			if(count==checkboxs.length){
				checkAll.checked=true;
			}else{
				checkAll.checked=false;
			}
		}
		</SCRIPT>
	</body>
</html>
