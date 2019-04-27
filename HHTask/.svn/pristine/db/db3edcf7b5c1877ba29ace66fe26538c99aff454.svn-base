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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>

			</div>

			<div align="center">
			<h3>
					开票未通过产品管理<br/>(unpass bill product Management)
				</h3>
				<s:if test="successMessage!=null">
					<h2>
				       <br/><font color="red">${successMessage}</font>
				    </h2>
					</s:if>
				<form action="huikuanAction!checkBillCount.action" method="post"  enctype="multipart/form-data">
					<input type="hidden" name="taHk.id" value="${taHk.id}">
					<table style="width: 98%; margin: 5 px" class="table">
						<tr>
							<td colspan="11" align="right">
								编号：
								<s:property value="taHk.hkNum" />
								<span style="width: 50px;"></span>
							</td>
						</tr>
						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td>
								序号
							</td>
							<td>
								客户
							</td>
							<td>
								零件号
							</td>
							<td>
								申请开票数量
							</td>
							<td>
								实际开票数量
							</td>
							<td>
								送货单号
							</td>
							<td>
								订单号
							</td>
							
						</tr>
						<s:iterator value="listHkSellSta" status="hk" id="huikuan">
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
								<s:property value="#hk.index+1" />
							</td>
							<td>
							<input type="hidden"
								name="hkset[<s:property value='#hk.index' />].id" size="7"
								readonly="readonly" value="${id}" />
								<input
									name="hkset[<s:property value='#hk.index' />].hkSellCumpanyName"
									size="7"  value="${hkSellCumpanyName}" />
							</td>
							<td>
								<input style="width: 100px;"
									name="hkset[<s:property value='#hk.index' />].hkSellMarkId"
									id="markId<s:property value='#hk.index' />" size="7"
									readonly="readonly" value="${hkSellMarkId}" />
							</td>
							<td>
								<input
									name="hkset[<s:property value='#hk.index' />].applyCount"
									size="7" readonly="readonly" value="${applyCount}" />
							</td>
							<td>
								<input id="hkSellCount<s:property value='#hk.index' />"
									name="hkset[<s:property value='#hk.index' />].hkSellCount"
									size="7" value="${hkSellCount}" onblur="mustBeNumber('hkSellCount${hk.index}')"/>
							</td>
							<td>
								<input
									name="hkset[<s:property value='#hk.index' />].hkSellSendId"
									size="20" readonly="readonly" value="${hkSellSendId}" />
							</td>
							<td>
								<input
									name="hkset[<s:property value='#hk.index' />].hkSellOutOrderId"
									size="20" readonly="readonly" value="${hkSellOutOrderId}" />
							</td>
							</tr>
						</s:iterator>
                        <tr><td colspan="6">&nbsp;</td></tr>
						<tr>
						<td>客户公司名称：</td><td>
						<input type="hidden" name="taHk.hkNum" size="15" value="${taHk.hkNum}" />
						<input type="text" name="taHk.hkClientComp" size="15" value="${taHk.hkClientComp}" /></td>
						<td>客户负责人：</td><td><input type="text" name="taHk.hkClientName" size="15" value="${taHk.hkClientName}" /></td>
						<td>申请开票时间：</td><td><input class="Wdate" type="text" name="taHk.hkAppPayDate" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/></td>
						</tr>		
						<tr><td>上传附件</td><td colspan="5">
						<input type="button" id="fileButton_1"
											onclick="uploadFile(this,1)" value="上传附件">
											<div id="fileDiv_1" style="display: none;">

						</div>
						<tr>
							<td colspan="11" align="center">
								<input type="submit" value="确认并申请开票" Onclick="return check22()"
									style="width: 60px; height: 40px;" align="top">
								&nbsp;
								<input type="reset" value="取消"
									style="width: 60px; height: 40px;" align="top">
								&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

	</body>
	<script type="text/javascript">
//下载合同
function check22() {
	var circle = document.getElementById("backCircle");
	//alert(circle.value);
	if (null == circle.value || "" == circle.value) {
		alert("回款周期不能为空!");
		backCircle.focus();
		return false;
	}
}
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

</html>
