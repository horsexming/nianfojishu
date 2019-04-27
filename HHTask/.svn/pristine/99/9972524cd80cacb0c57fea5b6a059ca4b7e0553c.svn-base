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
			印章使用申请修改
			</div>
			<form method="post" id="form" enctype="multipart/form-data">
			<table class="table" style="width: 100%;">
				<tr>
			 <td></td>
			 <td colspan="2">申请单编号</td>
			 <td><s:property value="sealLog.number"/></td>
			</tr>
		<s:if test='sealLog.type == null || sealLog.type == "" ||	(sealLog.type == "合同评审" && sealLog.aduitStatus2 == "同意" )'	>
			<tr>
			 <td rowspan="">使用印章名称
			 </td>
			 <td colspan="3">
			 		<input type="checkbox" value="公章" id="sealName" name="sealLog.name"  />公章
					<input type="checkbox" value="合同章" id="sealName" name="sealLog.name" />合同章
					<input type="checkbox" value="发票章" id="sealName" name="sealLog.name" />发票章
					<input type="checkbox" value="财务章" id="sealName" name="sealLog.name" />财务章
					<input type="checkbox" value="名章" id="sealName" name="sealLog.name" />名章
					<input type="checkbox" value="法人章" id="sealName" name="sealLog.name" />法人章
					<input type="checkbox" value="机密文件" id="sealName" name="sealLog.name" />机密文件
<%--			 <s:if test="sealLog.name=='公章'">--%>
<%--			 <input type="radio" value="公章" id="sealName" name="sealLog.name" checked="checked">公章--%>
<%--			 <input type="radio" value="合同章" id="sealName" name="sealLog.name">合同章--%>
<%--			 <input type="radio" value="发票章" id="sealName" name="sealLog.name">发票章--%>
<%--			 <input type="radio" value="法人章" id="sealName" name="sealLog.name" />法人章--%>
<%--			 </s:if>--%>
<%--			 <s:elseif test="sealLog.name=='合同章'">--%>
<%--			<input type="radio" value="公章" id="sealName" name="sealLog.name">公章--%>
<%--			 <input type="radio" value="合同章" id="sealName" name="sealLog.name" checked="checked">合同章--%>
<%--			 <input type="radio" value="发票章" id="sealName" name="sealLog.name">发票章--%>
<%--			 <input type="radio" value="法人章" id="sealName" name="sealLog.name" />法人章--%>
<%--			 </s:elseif>--%>
<%--			 <s:elseif test="sealLog.name=='法人章'">--%>
<%--			<input type="radio" value="公章" id="sealName" name="sealLog.name">公章--%>
<%--			 <input type="radio" value="合同章" id="sealName" name="sealLog.name" >合同章--%>
<%--			 <input type="radio" value="发票章" id="sealName" name="sealLog.name">发票章--%>
<%--			 <input type="radio" value="法人章" id="sealName" name="sealLog.name" checked="checked"/>法人章--%>
<%--			 </s:elseif>--%>
<%--			 <s:else>--%>
<%--			 <input type="radio" value="公章" id="sealName" name="sealLog.name">公章--%>
<%--			 <input type="radio" value="合同章" id="sealName" name="sealLog.name">合同章--%>
<%--			 <input type="radio" value="发票章" id="sealName" name="sealLog.name" checked="checked">发票章--%>
<%--			 <input type="radio" value="法人章" id="sealName" name="sealLog.name" />法人章--%>
<%--			 </s:else>--%>
			 </td>
			</tr>
		</s:if>
			<tr> 
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			</tr>
			<tr> 
			<td rowspan="2">使用人员</td>
			<td>工号</td>
			<td>名称</td>
			<td></td>
			</tr>
			<tr> 
			<td><input type="text" id="userCode" name="sealLog.userCode" value="<s:property value="sealLog.userCode"/>" onblur="setUser(this.value,'code')" > </td>
			<td><input type="text" id="userName" name="sealLog.userName" value="<s:property value="sealLog.userName"/>" onblur="setUser(this.value,'code')" ></td>
			<td><input type="text" id="userDept" name="sealLog.userDept" value="<s:property value="sealLog.userDept"/>" readonly="readonly"></td>
			</tr>
			<tr>
			 <td rowspan="2">使用用途简述</td>
			 <td rowspan="2" colspan="2"><textarea id="useFor"  cols="50px" name="sealLog.useFor"><s:property value="sealLog.useFor"/></textarea> </td>
			 <td><a href="<%=path%>/<s:property value='sealLog.fujian'/>">查看附件</a>
			 </td>
			</tr>
			<tr>
			 <td>
			 </td>
			</tr>
			<tr>
			 <td> 是否需要存档
			 </td>
			 <td>是<input type="radio" id="isSave" name="sealLog.isSave" value="yes" checked="checked" onchange="changeSaveTr(this.value)"/></td>
<%--			 <s:if test="sealLog.isSave=='yes'">--%>
<%--			 <td>是<input type="radio" id="isSave" name="sealLog.isSave" value="yes" checked="checked" onchange="changeSaveTr(this.value)"/></td>--%>
<%--			 <td>否<input type="radio" id="isSave" name="sealLog.isSave" value="no" onchange="changeSaveTr(this.value)"/></td>--%>
<%--			 <td><s:property value="sealLog.saveNumber"/></td>--%>
<%--			 </s:if>--%>
<%--			 <s:else>--%>
<%--			 <td>是<input type="radio" id="isSave" name="sealLog.isSave" value="yes" onchange="changeSaveTr(this.value)"/></td>--%>
<%--			 <td>否<input type="radio" id="isSave" name="sealLog.isSave" value="no" checked="checked" onchange="changeSaveTr(this.value)"/></td>--%>
<%--			 <td></td>--%>
<%--			 </s:else>--%>
			</tr>
			<tr id="needSave1">
			  <td rowspan="4">
			          存档资料
			  </td>
			  <td>文件名称
			  </td>
			  <td>类别
			  </td>
			  <td>预计存档时间
			  </td>
			</tr>
			<tr id="needSave2">
			  <td><input type="text" name="sealLog.documentName" id="documentName" value="<s:property value='sealLog.documentName'/>">
			  </td>
			  <td>
			  <s:if test='sealLog.type == "合同评审" && sealLog.aduitStatus2 == "同意"'>
			  	<input type="text" value="${sealLog.documentType}" name="sealLog.documentType" id="documentType" readonly="readonly"/>
			  </s:if>
			  <s:else>
			  	 <SELECT id="documentType" name="sealLog.documentType" >
			       <option value="<s:property value='sealLog.documentType'/>"><s:property value="sealLog.documentType"/></option>
			       <option value="销售">销售</option>
			       <option value="订单">订单</option>
			       <option value="采购合同">采购合同</option>
			       <option value="人事合同">人事合同</option>
			       <option value="其他">其他</option>
			 	 </SELECT>
			  </s:else>
			  </td>
			  <td>
			  	<s:if test='sealLog.type == "合同评审" && sealLog.aduitStatus2 == "同意"'>
			  		<input type="text"  class="Wdate" name="sealLog.retime" value="<s:property value='sealLog.retime'/>"
								id="retime"   />
			  	</s:if>
			  	<s:else>
			  		<input type="text"  class="Wdate" name="sealLog.retime" value="<s:property value='sealLog.retime'/>"
								id="retime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
			  	</s:else>
			  </td>
			</tr>
			<tr id="needSave3">
			  <td>负责人
			  </td>
			  <td>录入人
			  </td>
			  <td>
			  </td>
			</tr>
			<tr id="needSave4">
			  <td><input type="text" name="sealLog.chargePerson" id="chargePerson" value="<s:property value='sealLog.chargePerson'/>">
			  </td>
			  <td><s:if test="<s:property value='sealLog.inputPeople'/>!=null">
			  <input type="text" name="sealLog.inputPeople" id="inputPeople" value="<s:property value='sealLog.inputPeople'/>" readonly="readonly">
			  </s:if>
			  <s:else>
			  <input type="text" name="sealLog.inputPeople" id="inputPeople" value="${sessionScope.Users.name}" readonly="readonly">
			  </s:else>
			  </td>
			  <td>
			  </td>
			</tr>
			<tr>
			 <td colspan="4"><input type="button" value="提交" class="input" onclick="tosubmit()">
			 </td>
			</tr>
			</table>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
	$(function(){
		var name = '${sealLog.name}'
		var names = name.split(",");
		var checkboxs = document.getElementsByName("sealLog.name");
		if(names!=null && names.length>0){
			for(var i=0;i<checkboxs.length;i++){
				for(var j=0;j<names.length ;j++){
					if(checkboxs[i].value == names[i]){
						checkboxs[i].checked = "checked";
					}
				}
			}
		}
		
		if('${sealLog.type}' == '合同评审' && '${sealLog.aduitStatus2}' == '同意'){
			$("#userCode").attr("readonly","readonly");
			$("#userName").attr("readonly","readonly");
			$("#documentName").attr("readonly","readonly");
			$("#chargePerson").attr("readonly","readonly");
			$("#retime").attr("readonly","readonly")
		}
		
	})
		function tosubmit(){
		 var id=${sealLog.id};
 		 var sealNames=document.getElementsByName("sealLog.name");//印章名称
         var userName=$("#userName").val();//申请人名字
         var userCode=$("#userCode").val();//申请人工号
         var useFor=$("#useFor").val();//用途
         var isSaves=document.getElementsByName("sealLog.isSave");//是否存档（yes,no）
         var sealName="";
         var isSave="";
         for ( var i = 0; i < sealNames.length; i++) {
			if (sealNames[i].checked == true) {
				//sealName=sealNames[i].value; 单选框时使用;
				sealName +=sealNames[i].value+",";
			}
		}
         for(var i=0;i<isSaves.length;i++){
        	 if(isSaves[i].checked==true){
        		 isSave=isSaves[i].value;
        	 }
         }
         if(sealName==null||sealName==""){
        	  alert("请选择印章名称");
        	  return;
         }else if(userCode==null||userCode==""){
        	  alert("请输入申请人工号");
        	  return ;
         }else if(userName==null||userName==""){
        	  alert("请输入申请人名字");
        	  return;
         }else if(useFor==null||useFor==""){
        	  alert("请输入申请通途");
        	  return ;
         }
          if(isSave=='yes'){
        	 if($("#documentName").val()==null||$("#documentName").val()==""){
        		alert("请输入文件名称!"); 
        		return ;
        	 }else if($("#documentType").val()==null||$("#documentType").val()==""){
        		 alert("请输选择文档类型!"); 
        		return ;
        	 }else if($("#retime").val()==null||$("#retime").val()==""){
        		 alert("请输入预输入时间!"); 
        		return ;
        	 }else if($("#chargePerson").val()==null||$("#chargePerson").val()==""){
        		 alert("请输入负责人!"); 
        		return ;
        	 }
         }
         $.ajax
        (
            {
                url:'sealLogAction_update.action',//用于文件上传的服务器端请求地址
                type : 'post',
                dataType: 'json',//返回值类型 一般设置为json
                data : {
            	 'sealLog.id' :id,
            	 'sealLog.name' :sealName,
            	 'sealLog.userName' :userName,
            	 'sealLog.userCode' :userCode,
            	 'sealLog.userDept' :$("#userDept").val(),
            	 'sealLog.useFor' :useFor,
            	 'sealLog.isSave' :isSave,
            	 'sealLog.documentName' : $("#documentName").val(),
            	 'sealLog.documentType' : $("#documentType").val(),
            	 'sealLog.retime' : $("#retime").val(),
            	 'sealLog.chargePerson' : $("#chargePerson").val(),
            	 'sealLog.inputPeople' : $("#inputPeople").val()
                },
                success: function (data)  //服务器成功响应处理函数
                {
                	if(data){
                    alert("修改成功!");
                	}else{
                		alert("修改失败!");
                	}
                	window.location.href = "sealLogAction_toupdate.action?sealLog.id=" + id;
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
		}
			function setUser(value,type){
			if(value==null||value==""){
				$("#userName").val();
				$("#userCode").val();
				$("#userDept").val();
				return;
			}
			var url="sealLogAction_getUser.action?";
			if(type=='code'){
				url=url+"sealLog.userCode="+value
			}else{
				url=url+"sealLog.userName="+value
			}
			$.ajax({
				url : url,
				type : 'post',
				dataType : 'json',
				success : function(data){
				 if(data.success){
					 $("#userName").val(data.data.name);
				     $("#userCode").val(data.data.code);
				     $("#userDept").val(data.data.dept);
				 }else{
					$("#userName").val();
				    $("#userCode").val();
				    $("#userDept").val();
				 }
				 
				}
			})
			
			
		}
		function changeSaveTr(value){
			if(value=='yes'){
				$("#needSave1").show();
				$("#needSave2").show();
				$("#needSave3").show();
				$("#needSave4").show();
			}else if(value=='no'){
				$("#needSave1").hide();
				$("#needSave2").hide();
				$("#needSave3").hide();
				$("#needSave4").hide();
			}
			
		}
<%--		$(document).ready(function(){--%>
<%--			var isSave='<s:property value="sealLog.isSave"/>';--%>
<%--			if(isSave=="no"){--%>
<%--				$("#needSave1").hide();--%>
<%--				$("#needSave2").hide();--%>
<%--				$("#needSave3").hide();--%>
<%--				$("#needSave4").hide();--%>
<%--			}--%>
<%--		})--%>
		</script>
	</body>
</html>
