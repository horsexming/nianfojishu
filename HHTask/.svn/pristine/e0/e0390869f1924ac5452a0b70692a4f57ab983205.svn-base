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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="huikuanAction!saveHK.action" method="post"
					target="main" enctype="multipart/form-data">
					
					<input type="hidden" name="tag" value="${tag }">
					<table width="900px" class="table">
						<tr>
							<td colspan="7" align="right">
								编号：
								<input type="text" name="taHk.hkNum" value="${hkNum}"
									readonly="readonly" />

							</td>
						</tr>
						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td>
								客户
							</td>
							<td>
								零件号
							</td>
							<td>
								开票数量
							</td>
							<td>
								送货单号
							</td>
							<td>
								订单号
							</td>
							<td>
								备注
							</td>
						</tr>
							<s:iterator value="listHkSellSta" status="hk" id="huikuan">
							<input type="hidden" name="hkset[<s:property value='#hk.index' />].id" 
									value="${id}" />
							<s:if test="#hk.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellCumpanyName"
									value="${hkSellCumpanyName}" />
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellMarkId" readonly="readonly"
									value="${hkSellMarkId}" />
							</td>
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellCount" readonly="readonly"
									value="${hkSellCount}" />
							</td>
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellSendId" readonly="readonly"
									value="${hkSellSendId}" />
							</td>
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellOrderId" />
							</td>
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellMore" />
							</td>
							</tr>
						</s:iterator>	
						<tr><td colspan="6">&nbsp;</td></tr>
						<tr>
						<td>客户公司名称：</td><td><input type="text" name="taHk.hkClientComp" size="15" /></td>
						<td>客户负责人：</td><td><input type="text" name="taHk.hkClientName" size="15" /></td>
						<td>申请开票时间：</td><td><input class="Wdate" type="text" name="taHk.hkAppPayDate" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/></td>
						</tr>		
						<tr><td>上传附件</td><td colspan="5">
						<input type="button" id="fileButton_1"
											onclick="uploadFile(this,1)" value="上传附件">

						<div id="fileDiv_1" style="display: none;">

						</div>
						
						</td></tr>		
						<tr>						
							<td colspan="6" align="center">
								<input type="submit" value="提交"
									style="width: 60px; height: 40px;" align="top">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
			var fileDivHTML = "";
			var count = 0;
			function uploadFile(obj, few) {
				var fileDiv = document.getElementById("fileDiv_" + few);
				if (obj.value == "上传附件") {
					fileDiv.style.display = "block";
					obj.value = "添加文件";
				}
				fileDivHTML = "<div id='file"
						+ count
						+ "'><input type='file' name='attachment'><a href='javascript:delFile("
						+ count + "," + few + ")'>删除</a></div>";
				fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
				count++;
			}
			
			function delFile(obj, few) {
				
				document.getElementById("file" + obj).parentNode.removeChild(document
						.getElementById("file" + obj));
						
				count--;
				if (count <= 0) {
					count = 0;
					document.getElementById("fileButton_" + few).value = "上传附件";
					document.getElementById("fileDiv_" + few).style.display = "none";
				}
			}
			
		</script>
	</body>
















</html>
