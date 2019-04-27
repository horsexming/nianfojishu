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
			<div id="xitong" >
			</div>
			
			<div align="center">
				<h3>
					月度委托汇总管理
				</h3>
				<form action="EscrowAction_escrowMonthShow.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								被委托方：
								<input type="text" name="escrowMonth.payCom" value="<s:property value="escrowMonth.payCom"/>" />
							</td>
							<td align="center">
								申请人：
								<input type="text" name="escrowMonth.applyUserName" value="<s:property value="escrowMonth.applyUserName"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								月份：
								<input type="text" name="escrowMonth.month" value="<s:property value="escrowMonth.month"/>" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"/>
							</td>
							<td align="center">
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>
						</td>
						<td align="center">
							被委托方
						</td>
						<td align="center">
							月份
						</td>
						<td align="center">
							申请人
						</td>
						<td align="center">
							申请时间
						</td>
						<td align="center">
							审批状态
						</td>						
						<td align="center">
							操作
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="list" id="pageescrowMonth" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageescrowMonth.payCom}
						</td>
						<td>
							${pageescrowMonth.month}
						</td>
						<td>
							${pageescrowMonth.applyUserName}
						</td>
						<td>
							${pageescrowMonth.applyTime}
						</td>
						<td>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pageescrowMonth.epId}">${pageescrowMonth.epStatus}</a>
						</td>
						
						<td  colspan="2">
						<a href="EscrowAction_escrowMonthPrint.action?id=${pageescrowMonth.id}">明细</a>
<%--							<input type="button" value="修改(update)"--%>
<%--								style="width: 60px; height: 30px;"--%>
<%--								onclick="update(${pageescrowMonth.id})" />--%>
<%----%>
<%--							<input type="button" value="删除(delete)"--%>
<%--								style="width: 60px; height: 30px;"--%>
<%--								onclick="if(window.confirm('您将删除数据是否继续？')){todelete(${pageescrowMonth.id },${cpage})};" />--%>
						</td>
					</tr>
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
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	if(obj.checked==true){
		$("#dyBtn").removeAttr("disabled");
	}else{
		$("#dyBtn").attr("disabled","disabled");
	}
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
			}
		}
	}
}
var complany = "";
function chageNum(obj,value) {
	if (complany.length > 0 && complany != value) {
		obj.checked = false;
		alert("请选择相同的收款单位!");
		return false;
	}else{
		complany=value;
	}
	var inputs = document.getElementsByTagName("input");
	var num=0;
	var checkAll=true;
	for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if(checkBox.id != "checkAll"){
					if (checkBox.checked == true) {
						num++;
					}else{
						checkAll=false;
					}
				}
			}
	}
	if(num==0){
		$("#dyBtn").attr("disabled","disabled");
		complany = "";
	}else{
		$("#dyBtn").removeAttr("disabled");
	}
}
</script>
	</body>
</html>
