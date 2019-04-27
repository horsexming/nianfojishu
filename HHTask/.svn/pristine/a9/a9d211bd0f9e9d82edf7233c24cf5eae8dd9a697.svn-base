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
			<div align="center">
				<h3>
					归还记录管理
					<input type="hidden" id="ids" value="0"/>
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<td>
							序号
						</td>
						<td>
							归还
						</td>
						<td>
							名称
						</td>
						<td>
							规格
						</td>
						<td>
							数量
						</td>
						<td>
							库位编号
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
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
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pageList.peopleName}
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.processQuantity}
						</td>
						<td>
							${pageList.wareHouse}
						</td>
						<td colspan="2">
							<s:if test='#pageList.cqStatus=="待存"'>
								<div
									onclick="getcheckList2('${pageList.id}')"
									style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
									<br />
									库位码 
									<br />
									扫描
								</div>
							</s:if>
							<s:else>
								<div
									onclick="closeM('${pageList.id}')"
									style="width: 55px; height: 55px; border-radius: 50%; background-color: #c0dcff; color: #ffffff; font-size: 10px;">
									<br />关闭
								</div>
							</s:else>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="8" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var i = "";
function getcheckList2(id) {
	i = id;
	$("#ids").val(i);
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	var va = $("#ids").val();
	window.location.href = "WaigouwaiweiPlanAction!upAlsoBacode.action?bacode=" + tm +"&tag=${tag}&mxId=" + va;
}
function closeM(id){
	window.location.href = "WaigouwaiweiPlanAction!closeGzAlso.action?id="+id;
}
</script>
	</body>
</html>
