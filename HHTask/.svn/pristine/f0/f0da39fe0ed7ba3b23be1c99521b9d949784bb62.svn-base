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
	<body onload="gys()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					扣款单管理
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				
				<form action="zhaobiaoAction!showList.action"
					method="post" id="myForm">
					<table class="table" align="center">
						<tr>
							<td align="right">
								经办人：
							</td>
							<td align="left">
								<input type="text" name="chargebackNotice.jbName" />
							</td>
							<td align="right">
								供应商：
							</td>
							<td align="left">
								<select name="chargebackNotice.zhUser_name" id="zhUser">
									<option></option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								扣款单编号：
							</td>
							<td align="left">
								<input type="text" name="chargebackNotice.number" />
							</td>
							<td align="left" colspan="2">
								<input type="submit" style="width: 100px; height: 40px; margin-left: 70px;"
									value="查询(select)" />
									<input type="button" style="width: 100px; height: 40px; margin-left: 70px;"
									value="导出" onclick="daochu();todisabledone(this)" data="downData"/>
							</td>
						</tr>
					</table>
				</form>
				
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							扣款单编号
						</td>
						<td align="center">
							供应商
						</td>
						<td align="center">
							扣款事由
						</td>
						<td align="center">
							扣款金额
						</td>
						<td align="center">
							提报单位
						</td>
						<td align="center">
							扣款月份
						</td>
						<td align="center">
							经办人
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							附件
						</td>
						<td align="center">
							审批动态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="chargebackNoticeList" id="samples" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.number}
						</td>
						<td align="center">
							${samples.zhUser_name}
						</td>
						<td align="center">
							${samples.kkCause}
						</td>
						<td align="center">
							${samples.kkMoney}
						</td>
						<td align="center">
							${samples.reportUnit}
						</td>
						<td align="center">
							${samples.kkMouth}
						</td>
						<td align="center">
							${samples.jbName}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
						<td align="center">
							<s:if test="#samples.fileName!=null">
								<a href="FileViewAction.action?FilePath=/upload/file/ChargebackNotice/${samples.fileName}">查看附件</a>
							</s:if>
							<s:else>
								无附件
							</s:else>
						</td>
						<td>
							<s:if test="#samples.epId!=null">
								<a href="CircuitRunAction_findAduitPage.action?id=${samples.epId}">${samples.epstatus}</a>
							</s:if>
							<s:else>
								无
							</s:else>
						</td>
						<td align="center">
							<a
								href="zhaobiaoAction!toselect.action?chargebackNotice.id=${samples.id}">打印</a>
							<a
								href="zhaobiaoAction!toupdate.action?chargebackNotice.id=${samples.id}">/修改</a>
							<a onclick="return window.confirm('您将删除数据，是否继续?')"
								href="zhaobiaoAction!delete.action?id1=${samples.id}">/删除</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function gys(){
	$.ajax( {
		type : "post",
		url : "PriceAction!findZhuerList.action",
		dataType : "json",
		success : function(data) {
			//填充供应商信息
			var i=0;
			$(data).each(
				function() {
					$(
					"<option value='" + data[i][1] + "'>" + data[i][1]
							+ "</option>").appendTo("#zhUser");
					i++;
				});
			$("#zhUser").tinyselect();
		}
	});
}
function daochu(){  
         document.getElementById("myForm").action = "zhaobiaoAction!daochukoukuan.action";  
         document.getElementById("myForm").submit();  
     } 
</script>
	</body>
</html>
