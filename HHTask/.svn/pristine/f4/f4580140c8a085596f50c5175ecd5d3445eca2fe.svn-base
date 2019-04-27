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
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/javascript/layer/theme/default/layer.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/layer/layer.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" >
			<div align="center">
				<h3>
					外委工序合同核对<br/>
				</h3>
				<form action="ProcardAction!forwaiweiCheckList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								总成件号：
								<input type="text" name="pwwApply.markId" value="<s:property value="pwwApply.markId"/>" />
							</td>
							<td align="center">
								业务件号：
								<input type="text" name="pwwApply.ywMarkId" value="<s:property value="pwwApply.ywMarkId"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								总成名称：
								<input type="text" name="pwwApply.proName" value="<s:property value="pwwApply.proName"/>" />
							</td>
							<td align="center">
								总成批次：
								<input type="text" name="pwwApply.selfCard" value="<s:property value="pwwApply.selfCard"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								内部订单号：
								<input type="text" name="pwwApply.orderNumber" value="<s:property value="pwwApply.orderNumber"/>" />
							</td>
							<td align="center">
								申请人：
								<input type="text" name="pwwApply.userName" value="<s:property value="pwwApply.userName"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								申请单号：
								<input type="text" name="pwwApply.wwApplyNumber" value="<s:property value="pwwApply.wwApplyNumber"/>" />
							</td>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
								<input type="reset" style="width: 100px; height: 40px;"
									value="重置" />
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
							申请单号
						</td>
						<td align="center">
							内部订单号
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							业务件号
						</td>
						<td align="center">
							总成名称
						</td>
						<td align="center">
							总成批次
						</td>
						<td align="center">
							批次总数量
						</td>
						<td align="center">
							进度
						</td>						
						<td align="center">
							申请人
						</td>						
						<td align="center">
							申请时间
						</td>						
						<td align="center" colspan="2">操作</td>
					</tr>
					<s:iterator value="list" id="pageApply" status="pageStatus">
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
							${pageApply.wwApplyNumber}
						</td>
						<td>
							${pageApply.orderNumber}
						</td>
						<td>
							${pageApply.markId}
						</td>
						<td>
							${pageApply.ywMarkId}
						</td>
						<td>
							${pageApply.proName}
						</td>
						<td>
							${pageApply.selfCard}
						</td>
						<td>
							${pageApply.finalCount}
						</td>
						<td>
							${pageApply.processStatus}
						</td>
						<td>
							${pageApply.userName}
						</td>
						<td>
							${pageApply.addTime}
						</td>
						
						<td  colspan="2">
							<input type="button" value="合同核对"
								style="width: 80px; height: 30px;"
								onclick="toCheckHt(${pageApply.id})" />
							<input type="button" value="下载图纸"
								style="width: 80px; height: 30px;"
								onclick="downloadtz(${pageApply.id})" />
							<input value="删除" type="button"
													style="width: 40px; height: 20px;"
													onclick="deleteapply(${pageApply.id})" />
							<input onclick="exportWwApplyDetail(${pageApply.id});todisabledone(this)" data="downData"
								type="button" value="导出明细" style="width: 80px; height: 25px;" />
	
						</td>
					</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="13" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="13" align="center" style="color: red">
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
//function toCheckHt(id) {
//	window.location.href = "ProcardAction!toCheckHtList.action?id=" + id;
//}
function toCheckHt(id) {
layer.open({
  type: 2,
  title: '合同核对',
  area: ['1250px', '700px'],
  fixed: false, //不固定
  maxmin: true,
  content: '<%=basePath%>/ProcardAction!toCheckHtList.action?id='+id
//  end: function () {
//          location.reload();
//    }
});
}

function downloadtz(id) {
	window.location.href = "ProcardAction!daochutzBywwapply.action?id=" + id;
}
function deleteapply(id){
	if(confirm("您是否要删除此条记录!")){
		window.location.href="ProcardAction!deleteWwApply.action?id="+id;
	}
}
function exportWwApplyDetail(id){
	window.location.href="ProcardAction!exportWwApplyDetail.action?id="+id;
}
</script>
	</body>
</html>
