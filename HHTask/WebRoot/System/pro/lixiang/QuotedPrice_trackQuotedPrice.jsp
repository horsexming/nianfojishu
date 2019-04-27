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
						href="QuotedPrice_trackQuotedPrice.action?id=<s:property value='quotedPrice.rootId'/>"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div>
			<div align="center">
			<h3><font color="red">${successMessage} </font></h3>
			<h3><font color="red">${errorMessage} </font></h3>
			</div>
			 <form id="addform" method="Post" enctype="multipart/form-data"
			 action="QuotedPrice_addQuotedPriceLog.action?id=<s:property value='quotedPrice.rootId'/>"
			  onsubmit="return addviledate();">
			  <table align="center">
			   <tr align="center">
							<th style="width: 20%" align="right">
								标题:
							</th>
							<th align="left">
								<input type="text" id="title" name="quotedPriceLog.title" style="width: 303"
								value="<s:property value='quotedPriceLog.title'/>">
							</th>
						</tr>
						<tr align="right">
							<th style="width: 20%" align="right">
								正文:
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
								上传附件:
							</th>
							<th align="center" colspan="2">
								<input type="file" id="accessory" name="accessory">
							</th>
						</tr>
						<tr align="center">
							<th align="center" colspan="2">
								<input type="submit" value="添加" class="input">
							</th>
						</tr>
			  </table>
			 </form>
			</div>
			
			<div align="center">
				<table class="table">
					<tr align="center">
						<th colspan="7">
							<s:property value="quotedPrice.proName"/>
						</th>
					</tr>
					<tr>
						<th>
							标题
						</th>
						<th>
							正文
						</th>
						<th>
							涉及金额
						</th>
						<th>
							提交人
						</th>
						<th>
						          工号 
						</th>
						<th>
							提交时间
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:if test="qpLogList==null||qpLogList.size()==0">
					<tr><th colspan="7"><font color="red">没有找到该项目的日志 </font></th>
					</tr>
					</s:if>
					<s:iterator value="qpLogList" id="qpLog">
					<tr>
						<th>
							<s:property value="#qpLog.title"/>
						</th>
						<th style="max-width: 400">
							<s:property value="#qpLog.msg"/>
						</th>
						<th>
							<s:property value="#qpLog.money"/>
						</th>
						<th>
							<s:property value="#qpLog.userName"/>
						</th>
						<th>
						    <s:property value="#qpLog.code"/> 
						</th>
						<th style="max-width: 200">
							<s:property value="#qpLog.time"/>
						</th>
						<th>
							<a href="QuotedPrice_toupdateLog.action?quotedPriceLog.id=<s:property value='#qpLog.id'/>">修改</a>
							<s:if test="#qpLog.accessory==null">
							<font color="gray">查看附件</font>
							</s:if>
							<s:else>
							<a href="<%=basePath%><s:property value="#qpLog.accessory"/>" >查看附件</a>
							</s:else>
							
							<a href="QuotedPrice_deleteLog.action?quotedPriceLog.id=<s:property value='#qpLog.id'/>">删除</a>
						</th>
					</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function addviledate(){
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
