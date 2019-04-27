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
				<h3>
				       修改项目材料清单<br/>(update project material order)
				</h3>
				<form action="projectMaterialOrderAction_update.action"
					method="post" onsubmit="return validate();">
					<table id="addTable" width="100%" border="1" style="border-collapse: collapse;">
						<tr>
						   <td align="center" colspan="3">
								项目名称（project Name）：
							</td>
							<td align="center" colspan="5" >
							<input  type="hidden" name="projectMaterialOrder.id" value="<s:property value="projectMaterialOrder.id"/>" style="width: 70%"/>
								<input id="proName" type="text" name="projectMaterialOrder.proName" value="<s:property value="projectMaterialOrder.proName"/>" style="width: 70%"/>
							</td>
						</tr>
						<tr>
						   <td align="center" colspan="3">
								备注（remark）：
							</td>
							<td align="center" colspan="5" >
								<input type="text" name="projectMaterialOrder.remark" value="<s:property value="projectMaterialOrder.remark"/>" style="width: 70%"/>
							</td>
						</tr>
						<tr>
						   <td align="center">
								件号（part Number）
							</td>
							<td align="center">
								名称（part Name）
							</td>
							<td align="center">
								牌号（trademark）
							</td>
							<td align="center">
								规格（specification）
							</td>
							<td align="center">
								数量（need Number）
							</td>
							<td align="center">
								单位（part unit）
							</td>
							<td align="center">
								供应商（supplier）
							</td>
							<td align="center">
								<input type="button" value="添加材料"  onclick="addMaterial()"> 
							</td>
						</tr>
						<s:iterator value="pmList" id="pm" status="listIndex">
						<tr id="materialtr<s:property value="#listIndex.index"/>">
							 <td align="center">
							 <input name="pmList[<s:property value="#listIndex.index"/>].id" value="<s:property value="#pm.id"/>" type="hidden">
								<input name="pmList[<s:property value="#listIndex.index"/>].markId" value="<s:property value="#pm.markId"/>">
							</td>
							<td align="center">
								<input name="pmList[<s:property value="#listIndex.index"/>].materialName" value="<s:property value="#pm.materialName"/>">
							</td>
							<td align="center">
								<input name="pmList[<s:property value="#listIndex.index"/>].paihao" value="<s:property value="#pm.paihao"/>">
							</td>
							<td align="center">
								<input name="pmList[<s:property value="#listIndex.index"/>].guige" value="<s:property value="#pm.guige"/>">
							</td>
							<td align="center">
								<input id="needNumber<s:property value="#listIndex.index"/>" name="pmList[<s:property value="#listIndex.index"/>].needNumber" value="<s:property value="#pm.needNumber"/>"
								onkeyup="chekNum(<s:property value="#listIndex.index"/>)">
							</td>
							<td align="center">
								<input name="pmList[<s:property value="#listIndex.index"/>].unit" value="<s:property value="#pm.unit"/>">
							</td>
							<td align="center">
								<input name="pmList[<s:property value="#listIndex.index"/>].supplier" value="<s:property value="#pm.supplier"/>">
							</td>
							<td align="center">
								<input type="button" value="删除材料"  onclick="deleteMaterial('<s:property value="#listIndex.index"/>')"> 
							</td>
						</tr>
							</s:iterator>
						<tr id="tijiao">
							<td align="center" colspan="8">
								<input type="submit" name="提交" />
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" value="<s:property value='successMessage'/>" id="successMessage">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		var size="<s:property value='pmList.size()'/>";
		var listTrMaxIndex=0;
		var listTrSiz=1;
		if(size>0){
			listTrSiz=size;
			listTrMaxIndex=size-1;
		}
		 //添加材料
		 function addMaterial(){
			 listTrMaxIndex++;
			 var html="<tr id='materialtr"+listTrMaxIndex+"'>" +
			 "<td align='center'><input name='pmList["+listTrMaxIndex+"].markId' ></td>" +
			 "<td align='center'><input name='pmList["+listTrMaxIndex+"].materialName'></td>" +
			 "<td align='center'><input name='pmList["+listTrMaxIndex+"].paihao' ></td>" +
			 "<td align='center'><input name='pmList["+listTrMaxIndex+"].guige' ></td>" +
			 "<td align='center'><input id='needNumber"+listTrMaxIndex+"' name='pmList["+listTrMaxIndex+"].needNumber' onkeyup='chekNum("+listTrMaxIndex+")'></td>" +
			 "<td align='center'><input name='pmList["+listTrMaxIndex+"].unit' ></td>" +
			 "<td align='center'><input name='pmList["+listTrMaxIndex+"].supplier' ></td>" +
			 "<td align='center'><input type='button' value='刪除材料'  onclick='deleteMaterial("+listTrMaxIndex+")'></td>" +
			 "/tr>"
			 $("#tijiao").before(html);
			 listTrSiz++;
		 }
		  function deleteMaterial(index){
			  alert(index);
			  if(listTrSiz==1){
				  return;
			  }
			  if((index-listTrMaxIndex)==0){
			    listTrMaxIndex--;
			  }
			   $("#materialtr"+index).remove();
			    listTrSiz--;
		 }
		  function validate(){
			  if($("#proName").val()==""){
				  alert("项目名称不能为空！");
				  return false;
			  }
			  return true;
		  };
		   function chekNum(index){
			 var needNumber= $("#needNumber"+index).val();
			 if(isNaN(needNumber)){
				 alert("请输入数字！");
				$("#needNumber"+index).val(0);
			 }
			 
		  }
		  $(document).ready(function(){
			  var successMessage=$("#successMessage").val();
			if(successMessage!=""){
　　                          alert(successMessage);
　　                           if(successMessage=="修改成功!"){
　　                        	  window.location.href="projectMaterialOrderAction_pmdetail.action?projectMaterialOrder.id=${projectMaterialOrder.id}";
              }
　　                          }
		  });
		</SCRIPT>
	</body>
</html>
