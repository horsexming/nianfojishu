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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				现场在制品申请入库
			</div>
			<br/>
			<div>
				<form action="GoodsStoreAction!zaizhiApplyInput.action" method="post">
				<table class="table">
					<tr>
						<th align="center">内部订单号:</th>
						<td align="center"><input name="barCode" id="barCode" value="${barCode}"/> </td>
						<th align="center">件号:</th>
						<td align="center"><input name="procard.markId" value="${procard.markId}"/> </td>
						<th align="center">批次:</th>
						<td align="center"><input name="procard.selfCard" value="${procard.selfCard}"/> </td>
					</tr>
					<tr>
						<td align="center" colspan="6"><input type="submit" value="提交" style="width: 60px;height: 40px;"/></td>
					</tr>
				</table>
				</form>
			</div>
			<div align="center">
				<s:if test="listIn==null||listIn.size()==0">
					<font color="red">没有找到对应的申请入库的在制品</font>
				</s:if>
				<s:else>
				<table class="table">
					<tr>
						<th>序号</th>
						<th>内部订单号</th>
						<th>业务件号</th>
						<th>外部订单号</th>
						<th>总成件号</th>
						<th>总成批次</th>
						<th>件号</th>
						<th>名称</th>
						<th>工序号</th>
						<th>工序名称</th>
						<th>批次</th>
						<th>批次数量</th>
						<th>已转库数量</th>
						<th>待转库数量</th>
						<th>可转库数量</th>
						<th>操作</th>
					</tr>
					<s:iterator value="listIn" id="pageprocard" status="pageStatus1">
					<s:if test="#pageStatus1.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus1.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus1.index+1" />
							</font>
						</td>
						<td>
							${pageprocard.orderNumber}
						</td>
						<td>
							${pageprocard.ywMarkId}
						</td>
						<td>
							${pageprocard.outOrderNum}
						</td>
						<td>
							${pageprocard.rootMarkId}
						</td>
						<td>
							${pageprocard.rootSelfCard}
						</td>
						<td>
							${pageprocard.markId}
						</td>
						<td>
							${pageprocard.proName}
						</td>
						<td>
							${pageprocard.processNo}
						</td>
						<td>
							${pageprocard.processName}
						</td>
						<td>
							${pageprocard.selfCard}
						</td>
						<td>
							${pageprocard.filnalCount}
						</td>
						<td>
							${pageprocard.zaizhizkCount}
						</td>
						<td>
							${pageprocard.zaizhiApplyZk}
						</td>
						<td>
							${pageprocard.zaizhikzkCount}
						</td>
						
						<td  colspan="2">
							<input type="button" value="申请入库"
								style="width: 60px; height: 30px;"
								onclick="apply(${pageprocard.id},${pageprocard.processNo})" />
						</td>
					</tr>
					</s:iterator>
				</table>
				</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function apply(id,processNo){
	window.location.href="GoodsStoreAction!zaizhiApplyInputDtial.action?id="+id+"&processNo="+processNo;
}
$(document).ready(function(){
	$("#barCode").focus();
})
</SCRIPT>
	</body>
</html>
