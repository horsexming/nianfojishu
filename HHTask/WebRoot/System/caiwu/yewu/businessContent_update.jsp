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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
					<form action="business_update.action" method="post" onsubmit="return validate()">
					<table>
						<tr>
							<th align="center" colspan="2">修改业务内容</th>
						</tr>
						<tr>
							<td>业务类型：</td><td><input type="text" value="${bu.type }" name="bu.type"/></td>
						</tr>
						<tr>
							<td>业务内容：</td><td><input type="text" id="content" value="${bu.content }" name="bu.content" style="width: 400px;"/></td>
						</tr>
						<tr>
							<td>收款单位：</td><td><input type="text" id="collectionUnit" value="${bu.collectionUnit }" name="bu.collectionUnit" style="width: 300px;"/></td>
						</tr>
						<tr>
							<td>经办人：</td><td><input type="text" id="transactor" value="${bu.transactor }" name="bu.transactor"/></td>
						</tr>
						<tr>
							<td>办理时间：</td><td><input id="time" style="width: 155px" class="Wdate"
									type="text" name="bu.time" value="${bu.time }"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						</tr>
						<tr>
							<input type="hidden" value="${bu.dept }" name="bu.dept"/>
							<input type="hidden" value="${bu.paymentBasis }" name="bu.paymentBasis"/>
							<input type="hidden" value="${bu.id }" name="bu.id"/>
							<input type="hidden" name="bu.money" value="${bu.money }"/>
							<input type="hidden" name="bu.currencyType" value="${bu.currencyType }"/>
							<input type="hidden" name="bu.status" value="${bu.status }"/>
							<input type="hidden" name="bu.flow" value="${bu.flow }"/>
							<td align="center" colspan="2"><input type="submit" value="提交审核"/></td>
						</tr>
					</table>
					</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script>
			function validate() {
				var valiTime =/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/ ;
				var time = document.getElementById("time").value; 
				var timeNOblank = time.replace(/(^\s+)|(\s+$)|(\s+)/g, ""); //去除前后中所有空
				var content = document.getElementById("content").value; //业务内容
				var collectionUnit = document.getElementById("collectionUnit").value; //收款单位
				var paymentBasis = document.getElementById("myfile").value; //付款依据
				var transactor = document.getElementById("transactor").value;  //经办人
				if(content == ""){
					alert("请输入业务内容!谢谢");
					return false;
				}
				if(collectionUnit == ""){
					alert("请输入收款单位！谢谢");
					return false;
				}
				if(paymentBasis == ""){
					alert("请上传付款依据！谢谢");
					return false;
				}
				if(transactor == ""){
					alert("请输入经办人！谢谢");
					return false;
				}
				if(valiTime.test(timeNOblank)){
					return true;
				}else{
					alert("请输入正确的时间： 年-月-日!谢谢");
				}
				return false;
			}
		</script>
	</body>
</html>
