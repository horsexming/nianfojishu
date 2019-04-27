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
					出借记录管理
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<td>
							序号
						</td>
						<td>
							借主
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
							仓库名
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
							${pageList.matetag}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.num}
						</td>
						<td>
							${pageList.storehouse}
						</td>
						<td>
							${pageList.wareHouse}
						</td>
						<td colspan="2">
							<s:if test='#pageList.cqStatus=="待取"'>
								<a onclick="shansuo(${pageList.ware_id})" style="text-decoration:none;">闪烁</a><b>╱</b><br/>
								<a onclick="getsendTow(${pageList.id})" style="text-decoration:none;">打开</a>
							</s:if>
							<s:elseif test='#pageList.cqStatus=="取中"'>
								<a href="WaigouwaiweiPlanAction!closeGz.action?id=${pageList.id}" >关闭</a>
							</s:elseif>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="9" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="9" align="center" style="color: red">
								${errorMessage}
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function shansuo(id){
	$.ajax({
		url : "WarehouseApplicationAction_shansuo.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {
			"id" : id,
		},
	});
}
function getsendTow(id){
	$.ajax({
		url : "WaigouwaiweiPlanAction!GZsendTow.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					getcheckList2();
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	window.location.href = "WaigouwaiweiPlanAction!upBorrowGzBacode.action?bacode=" + tm +"&tag=${tag}";
}
</script>
	</body>
</html>
