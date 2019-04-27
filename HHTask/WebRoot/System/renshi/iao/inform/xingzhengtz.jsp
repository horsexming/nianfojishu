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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div>
					
					<form action="InformAction!xingzhengtz.action" method="post">
					<table class="table">
						<tr>
							<td align="center">通知标题</td><td><input type="text" name="leaveInform.username" size="30"/></td>
						</tr>
						<tr>
							<td align="center">副标题</td><td><input type="text" name="leaveInform.code" size="30"/></td>
						</tr>
						<tr>
							<td align="center">正文</td>
							<td><textarea name="leaveInform.fuck2" id="descTa"
														cols="80" rows="15"/></textarea></td>
						</tr>
						<tr>
							<td align="center">通知人员</td><td><input type="text" name="leaveInform.reason"
														value="全体人员"/></td>
						</tr>
						<tr>
							<td align="center">通知部门</td><td><input type="text" name="leaveInform.dept"
														value="全部部门"/></td>						
						</tr>
						 <!--   <tr>  
						
							<td>1111</td><td>
								<input type=text name=show style="width:100px;height:22px;font-size:10pt;border-top:1px solid #7F9DB9;border-right:0px;border-bottom:1px solid #7F9DB9;border-left:1px solid #7F9DB9;">
 								<span style="width:18px;border:0px solid red;">
   									<select name="r00" style="margin-left:-100px;width:118px; background-color:#FFEEEE;" onChange="show.value=this.value;" onclick="this.selectedIndex=-1;">
 										<option value="111">111</option>
 										<option value="222">222</option>
 										<option value="333">333</option>
 									</select>
 								</span>
							</td>
						</tr>    -->
						<tr>
							
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value= "提交" style="width: 100px; height: 50px;"/>
								<input type="reset" value= "重置" style="width: 100px; height: 50px;"/>
							</td>
						</tr>
					</table>
					</form>
				</div>
				
				
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			var self;
			KindEditor.ready(function(K) {
				var editor1 = K.create('#descTa', {
					cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
					width : '510px',
					height: '200px',
					minWidth : 700,
					minHeight: 169,
					uploadJson : 'affixAction!upload.action',
            		allowFileManager : false,
					items : [ 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
					afterCreate : function() {
						self = this;
						K.ctrl(document, 13, function() {
							submit();
						});
						K.ctrl(self.edit.doc, 13, function() {
							submit();
						});
					}
				});
				prettyPrint();
			});
			
			


</script>
			
	</body>
</html>
