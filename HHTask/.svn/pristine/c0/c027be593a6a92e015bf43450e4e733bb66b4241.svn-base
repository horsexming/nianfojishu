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
		<center>
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
						href="QuotedPrice_trackQuotedPrice.action?id=<s:property value='quotedPrice.rootId'/>"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div>
			<div align="center">
			<h3><font color="red">${successMessage} </font></h3>
			<h3><font color="red">${errorMessage} </font></h3>
			</div>
			 <form id="updateform" method="Post" enctype="multipart/form-data"
			 action="QuotedPrice_updateQuotedPriceLog.action?id=<s:property value='quotedPrice.rootId'/>"
			  onsubmit="return updateviledate();">
			  <table align="center">
			   <tr align="center">
							<th style="width: 20%" align="right">
								标题:
							</th>
							<th align="left">
							<input type="hidden" name="quotedPriceLog.id" value="<s:property value='quotedPriceLog.id'/>">
								<input type="text" id="title" name="quotedPriceLog.title" style="width: 303"
								value="<s:property value='quotedPriceLog.title'/>">
							</th>
						</tr>
						<tr align="right">
							<th style="width: 20%" align="right">
								内容:
							</th>
							<th align="left">
								<textarea id="msgContext" name="quotedPriceLog.msg" rows="10" cols="40"><s:property value='quotedPriceLog.msg'/></textarea>
							</th>
						</tr>
					   <tr align="right">
							<th style="width: 20%" align="right">
								涉及金额:
							</th>
							<th align="left">
								<input type="text" id="money" name="quotedPriceLog.money"
								value="<s:property value='quotedPriceLog.money'/>" style="width: 303">
							</th>
						</tr>
						
						<tr align="center">
						<th style="width: 20%" align="right">
						<s:if test="quotedPriceLog.accessory==null">
								上传附件:
						</s:if>
						<s:else>
						 	           重新上传:
						</s:else>
							</th>
							<th align="center" colspan="2">
								<input type="file" id="accessory" name="accessory">
							</th>
						</tr>
						<tr align="center">
							<th align="center" colspan="2">
								<input type="submit" value="修改" class="input">
							</th>
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
		function updateviledate(){
			var msg=$("#msgContext").val();
			if(msg==null||msg==""){
				alert("请填写日志内容！");
				return false;
			}
			var money=$("#money").val();
			if(money!=null&&isNaN(money)){
				alert("金额内容请填写数字！");
				return false;
			}
		}
		</script>
	</body>
</html>
