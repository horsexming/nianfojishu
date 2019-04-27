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
		<style type="text/css">
		</style>
	</head>
  
  <body>
  <form  method="post">
  <table  style="width: 100%"  >
  <tr>
  <td>
  	<span>当前日期：  </span>
  	<input style="border:none" id="nowDate" readonly="readonly"/>
  </td>
  <td>
 	<span>请输入报价期限：  </span> 
 	<input id="deadline" type="text" name="deadline" onkeyup="jisuanshijian()" onblur="jisuanshijian()"/><span>(天)</span>
  </td>
  <td>
	<span>截止日期：  </span>
  	<input style="border:none" id="endDate" readonly="readonly" placeholder="请填写期限..."/>
  </td>
  </tr>
  	</table>
  	<hr>
   <table style="width: 100%" class="table">
   <input  name="offerIds" value="${offerIds}" id="noPriceprocessId" type="hidden"/>
   		<s:iterator value="noPriceprocessList" id="list"
						status="pageStatus">
  		 <tr>
				<td align="center">
					件号：
						${list.markId }
				</td>
				<td align="center">
					工序号：
						${list.processNO}
				</td>
				<td align="center">
					工序名称：
						${list.processName}
				</td>
			</tr>
		</s:iterator>
   </table>
   <hr/>
   <div align="center" style="margin:10px">
   	<input type="button" value="提交" style="height: 30px;width:70px" onclick="submit1()"/>
   </div>
   </form>
   <script type="text/javascript">
   	var myDate = new Date(); 
   	document.getElementById("nowDate").value = myDate.toLocaleDateString();
   function jisuanshijian(){
	   var deadline = document.getElementById("deadline").value;
		var szReg=/^\d+(\.\d+)?$/;
		if(deadline!=""){
			if(!szReg.test(deadline)){
				$("#deadline").val("");
				$("#deadline").focus();
				alert("请输入正确格式");
			}
			}
		$.ajax( {
		type : "POST",
		url : "yuanclAndWaigjAction_dayafter.action",
		data : {
			deadline:deadline
			},
		dataType : "json",
		success : function(data) {
			document.getElementById("endDate").value = data;
			}
			});
		}
   function submit1(){
	   var deadline = document.getElementById("deadline").value;
	   var obj = document.getElementsByName("offerIds");
   		 var str = "";
    	for(i=0;i<obj.length;i++){
	    	if(i==0){
	    		str += obj[i].value;
	    	}else{
	    		str += ","+obj[i].value ;
	    	}
	    }
	   $.ajax( {
		type : "POST",
		url : "NoPriceprocessAction_addTimeForMarkId.action",
		data : {
			deadline:deadline,
			offerIds:str
			},
		dataType : "json",
		success : function(data) {
				if(data==true){
					parent.window.location.reload();
					alert("成功");
				}else{
					alert(data);
				}
					
			}
			});
	   
   
   }
   </script>
  </body>
  
</html>

