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
<body >
<h3></h3>
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
		<h4>纠正措施以及预防方案</h4>
				<table class="table">
					<tr>
					  <th>原因分析
					  </th>
					  <td colspan="3">
					   ${setupCheck.reason}
					  </td>
					  </tr>
					  <tr>
					  <th>纠正措施
					  </th>
					  <td colspan="3">
					  	${setupCheck.shortWay}
					  </td>
					</tr>
					<tr>
						<th>纠正措施文件上传
					  </th>
					  <td colspan="3">
						<FONT color="red">${setupCheck.jiuzhenFile}</FONT><a href="FileViewAction.action?FilePath=/upload/file/setupCheck/${setupCheck.jiuzhenFile}">下载</a>
					  </td>
					</tr>
					<tr>
					  <th>纠正措施负责人
					  </th>
					  <td>
					  所属部门：&nbsp;&nbsp;&nbsp;&nbsp;${setupCheck.department}
					  &nbsp;&nbsp;&nbsp;&nbsp;负责人：&nbsp;&nbsp;&nbsp;&nbsp;
					 		${setupCheck.shortPerson}
					  </td>
					  <th>纠正措施完成日期
					  </th>
					  <td>
					  	${setupCheck.shortTime}
					  </td>
					</tr>
					<tr>
					  <th>预防措施
					  </th>
					  <td colspan="3">
					  	 ${setupCheck.longWay}
					  </td>
					  </tr>
					   <tr>
						<th>预防措施文件
					  </th>
					  <td colspan="3">
						<FONT color="red">${setupCheck.yufangFile}</FONT><a href="FileViewAction.action?FilePath=/upload/file/setupCheck/${setupCheck.yufangFile}">下载</a>
					  </td>
					</tr>
					  <tr>
					  <th>预防措施负责人
					  </th>
					  <td>
					 	 所属部门：&nbsp;&nbsp;&nbsp;&nbsp;
					  	${setupCheck.department}
					  	&nbsp;&nbsp;&nbsp;&nbsp;
					  	负责人：&nbsp;&nbsp;&nbsp;&nbsp;
					  	${setupCheck.longPerson}
					  </td>
					    <th>预防措施完成日期
					    </th>
					    <td colspan="3">
					  		${setupCheck.longTime}
					    </td>
					</tr>
					<tr>
					<th>佐证文件</th> 
					<td colspan="3">
					<s:if test="setupCheck.longFile!=null">
						<FONT color="red">${setupCheck.longFile}</FONT><a href="FileViewAction.action?FilePath=/upload/file/longWay/${setupCheck.longFile}">下载</a>
					</s:if>
					<s:else><FONT color="red">无文件</FONT></s:else>
					</td>
					</tr>
					 <td colspan="4" align="center">
					 <input type="hidden" name="tag" value="${tag}" id="tag"/>
		 <input type="hidden" name="nooperate" value="${nooperate}" id="nooperate"/>
					 <s:if test="nopass!='nopass'">
					   <input type="button" value="修改"
							style="width: 60px; height: 30px;"
								onclick="addWay(${setupCheck.id},'${tag}')" /></s:if>
					   <input type="button" value="返回" style="width: 60px; height: 30px;" 
					   		onclick="findAll('${tag}','${nooperate}')" />
					 </td>
					</tr>
				</table>
			
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
	function addWay(id,tag) {
	window.location.href = "SetupCheckAction_toAddWay.action?setupCheck.id="+ id+"&tag="+tag;
}
	function findAll(tag,nooperate){
		if(tag == "self"){
			if(nooperate=="nooperate"){
			window.location.href = "SetupCheckAction_findAllByName.action?"+"&nooperate="+"nooperate";
		}else{
			window.location.href = "SetupCheckAction_findAllByName.action";
		}
			}else{
			window.location.href = "SetupCheckAction_findAll.action";
		}
}
	</script>
</body>
</html>

