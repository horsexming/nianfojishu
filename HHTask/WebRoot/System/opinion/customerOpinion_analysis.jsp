<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
				
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
			<form action="customerOpinionAction_analysis.action" enctype="multipart/form-data" method="post" onsubmit="return validate();">
				<table class="table">
					<tr>
					  <th>客户
					  </th>
					  <td>
					  <input type="hidden" name="tag" value="<s:property value="tag"/>">
					  <input type="hidden" name="customerOpinion.id" value="<s:property value="customerOpinion.id"/>">
					  <s:property value="customerOpinion.otherCompany"/>
					  </td>
					  <th>投诉标题
					  </th>
					  <td><s:property value="customerOpinion.title"/>
					  </td>
					</tr>
					<tr>
					 <th>投诉内容</th>
					 <td colspan="3">
					   <s:property value="customerOpinion.context"/>
					 </td>
					</tr>
					<tr>
					    <th>分析方案
					    </th>
					    <td colspan="3">
					    <textarea rows="8" cols="80" name="customerOpinion.analysisText"><s:property value="customerOpinion.analysisText"/></textarea>
					    </td>
					</tr>
					<tr>
					 <th>上传文件
					 </th>
					 <td><input type="file" name="analysis" id="analysis" /><s:if test="customerOpinion.analysisFile!=null"><FONT color="red">原有分析文件</FONT></s:if> </td>
				     <th>
				                 上传时间
				     </th>
				     <td>
				      <input id="analysisTime" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
											name="customerOpinion.analysisTime" />
				     </td>
					</tr>
					<tr>
					 <td colspan="4" align="center">
					   <input type="submit" value="提交" />
					 </td>
					</tr>
				</table>
			</form>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
	function validate(){
		if(!validateMsg("已方负责人","ourPerson")){
			return false;
		}
		if(!validateMsg("登记时间","addTime")){
			return false;
		}
		if(!validateMsg("对方负责人","otherPerson")){
			return false;
		}
		if(!validateMsg("对方联系电话","otherPhone")){
			return false;
		}
		if(!validateMsg("对方单位","otherCompany")){
			return false;
		}
		if(!validateMsg("索赔标题","title")){
			return false;
		}
		if(!validateMsg("索赔内容","context")){
			return false;
		}
		
		return true;
	}
	</script>
	
</body>
</html>
