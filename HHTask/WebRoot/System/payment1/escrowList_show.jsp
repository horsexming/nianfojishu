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
					委托管理
				</h3>
				<form action="EscrowAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								收款方：
								<input type="text" name="escrow.shoukuanFang" value="<s:property value="escrow.shoukuanFang"/>" />
							</td>
							<td align="center">
								申请人：
								<input type="text" name="escrow.username" value="<s:property value="escrow.username"/>" />
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
				<form action="EscrowAction_toPrint.action"
					method="post">
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
<%--						<input type="checkbox" id="checkAll"--%>
<%--													onclick="chageAllCheck(this)">--%>
<%--												全选--%>
							序号<br/>
						</td>
						<td align="center">
							收款方
						</td>
						<td align="center">
							收款账号
						</td>
						<td align="center">
							付款用途
						</td>
						<td align="center">
							应付款金额
						</td>
						<td align="center">
							应付登记单单号
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
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="list" id="pageescrow" status="pageStatus">
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
						<s:if test="#pageescrow.status=='同意'">
							<s:if test="pagestatus=='ddy'">
							<s:if test="#pageescrow.printStatus==null||#pageescrow.printStatus!='是'.toString()">
								<input type="checkbox" value="${pageescrow.id}" name="ids" id="escrowId<s:property value="#ststusfunction.index"/>"
									onchange="chageNum(this)">
							</s:if>
							</s:if>
							<s:else>
							<input type="checkbox" value="${pageescrow.id}" name="ids" id="escrowId<s:property value="#ststusfunction.index"/>"
									onchange="chageNum(this,'${pageescrow.shoukuanFang}')">
							</s:else>
						</s:if>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageescrow.shoukuanFang}
						</td>
						<td>
							${pageescrow.shoukuanZhanghao}
						</td>
						<td>
							${pageescrow.fukuanYongtu}
						</td>
						<td>
							${pageescrow.yingfuJine}
						</td>
						<td>
							${pageescrow.zijinshiyongNum}
						</td>
						<td>
							${pageescrow.username}
						</td>
						<td>
							${pageescrow.applyTime}
						</td>
						<td>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pageescrow.epId}">${pageescrow.status}</a>
						</td>
						
						<td  colspan="2">
<%--							<input type="button" value="修改(update)"--%>
<%--								style="width: 60px; height: 30px;"--%>
<%--								onclick="update(${pageescrow.id})" />--%>
<%----%>
<%--							<input type="button" value="删除(delete)"--%>
<%--								style="width: 60px; height: 30px;"--%>
<%--								onclick="if(window.confirm('您将删除数据是否继续？')){todelete(${pageescrow.id },${cpage})};" />--%>
						</td>
					</tr>
					</s:iterator>
					<tr>
						<td colspan="16" align="center" >
							<input id="dyBtn" value="打印" type="submit" style="width: 60px;height: 40px;" disabled="disabled" >
						</td>
					</tr>
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
				</form>
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
