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
			    <h3>文</h3>
			   </div>
			   <s:if test="processTemplateJY.progressStatus=='未分析'||processTemplateJY.progressStatus=='分析中'">
			<form action="procardTemplateJYAction_updatejgljwr.action" method="post" onsubmit="return validateFrom();">
				<table>
					<tr>
						<th>
							改进方案：
						</th>
						<td>
							<textarea id="updateContext" name="updateContext" rows="5" cols="40"><s:property value="processTemplateJY.contextWen"/></textarea>
						</td>
					</tr>
					<tr>
						<th>
							准备次数
						</th>
						<td>
						<input type="hidden" value="wen" name="pageStatus">
						<input type="hidden" value="<s:property value="processTemplateJY.id"/>" name="processTemplateJY.id">
                         <input id="gzzhunbeicishu" name="processTemplateJY.gzzhunbeicishu"
                         value="<s:property value="processTemplateJY.gzzhunbeicishu"/>" onkeyup="checkNum1()"/>
						</td>
					</tr>
					<tr>
						<th>
							人工节拍（准备）
						</th>
						<td>
                         <input id="gzzhunbeijiepai" name="processTemplateJY.gzzhunbeijiepai"
                         value="<s:property value="processTemplateJY.gzzhunbeijiepai"/>" onkeyup="checkNum2()"/>
						</td>
					</tr>
					<tr>
						<th>
							设备节拍
						</th>
						<td>
                         <input id="opshebeijiepai" name="processTemplateJY.opshebeijiepai"
                         value="<s:property value="processTemplateJY.opshebeijiepai"/>" onkeyup="checkNum3()"/>
						</td>
					</tr>
					<tr>
						<th>
							人工节拍（操作）
						<td>
                         <input id="opcaozuojiepai" name="processTemplateJY.opcaozuojiepai"
                         value="<s:property value="processTemplateJY.opcaozuojiepai"/>" onkeyup="checkNum4()"/>
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
				<div align="center">
                   <iframe id="updateFile" style="border: 0;width: 900px;height: 1000;" align="middle">
                   </iframe>
                  </div>
<%--				 <form id="form2" action="procardTemplateJYAction_updatewen.action" method="POST" enctype="multipart/form-data">--%>
<%--				  <table>--%>
<%--				  <tr>--%>
<%--				   <th>--%>
<%--							改进方案：--%>
<%--						</th>--%>
<%--						<td><input type="hidden" value="wen" name="pageStatus">--%>
<%--						<input type="hidden" value="<s:property value="processTemplateJY.id"/>" name="processTemplateJY.id">--%>
<%--							<textarea id="updateContext" rows="5" cols="40"><s:property value="processTemplateJY.contextWen"/></textarea>--%>
<%--						</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--						<td colspan="2">--%>
<%--						<s:if test="processTemplateJY.fileName!=null">--%>
<%--						 <a href="procardTemplateJYAction_gongyiguifanView.action?id=<s:property value="processTemplateJY.id"/>">查看修改后的工艺规范</a>--%>
<%--						</s:if>--%>
<%--						<s:else>--%>
<%--						  <a href="ProcardAction!findGongyiGuifan.action?markid=<s:property value="processTemplateJY.markId"/>&amp;processNO=<s:property value="processTemplateJY.processNO"/>">查看原图</a>--%>
<%--						</s:else>--%>
<%--						</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--						 <th>--%>
<%--						 选择文件--%>
<%--						 </th>--%>
<%--						 <td>--%>
<%--						 <input id="wenImg" name="wenImg" type="file">--%>
<%--						 </td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--						<td><input type="submit" value="修改" /></td>--%>
<%--						</tr>--%>
<%--				  </table>--%>
<%--				 </form>--%>
				</s:else>
				<input type="hidden" value="<s:property value='successMessage'/>" id="successMessage">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		 function checkNum1(){
			 var gzzhunbeicishu= $("#gzzhunbeicishu").val();
			 if(isNaN(gzzhunbeicishu)){
				 alert("请输入数字！");
				$("#gzzhunbeicishu").val(0);
			 }
		 }
		 function checkNum2(){
			 var gzzhunbeijiepai= $("#gzzhunbeijiepai").val();
			 if(isNaN(gzzhunbeijiepai)){
				 alert("请输入数字！");
				$("#gzzhunbeijiepai").val(0);
			 }
		 }
		 function checkNum3(){
			 var opshebeijiepai= $("#opshebeijiepai").val();
			 if(isNaN(opshebeijiepai)){
				 alert("请输入数字！");
				$("#opshebeijiepai").val(0);
			 }
		 }
		 function checkNum4(){
			 var opcaozuojiepai= $("#opcaozuojiepai").val();
			 if(isNaN(opcaozuojiepai)){
				 alert("请输入数字！");
				$("#opcaozuojiepai").val(0);
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
			 $("#updateFile").attr("src","gongyiGuichengAction!getGongyiGuiChengUploadPage.action?"
									+ "&gongyiGuichengAffix.gongyiGuichengId="
									+ ${id}
									+ "&gongyiGuichengAffix.processDataId="
									+ ${id2}
									+ "&gongyiGuichengAffix.weizhi=gxsmlq");
			  var successMessage=$("#successMessage").val();
			if(successMessage!=""){
　　                          alert(successMessage);
　　                          }
		  });
		</script>
	</body>
</html>
