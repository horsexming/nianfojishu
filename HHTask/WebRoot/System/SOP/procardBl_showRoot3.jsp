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
					生产退料管理
				</h3>
				<form action="procardBlAction_findRootProcardforblList.action?pagestatus=${pagestatus}"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								总成件号
								<input type="text" name="procard.rootMarkId" value="<s:property value="procard.rootMarkId"/>" />
							</td>
							<td align="center">
								总成批次
								<input type="text" name="procard.rootselfCard" value="<s:property value="procard.rootselfCard"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								业务件号
								<input type="text" name="procard.ywMarkId" value="<s:property value="procard.ywMarkId"/>" />
							</td>
							<td align="center">
								订单号
								<input type="text" name="procard.orderNumber" value="<s:property value="procard.orderNumber"/>" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="hidden" value="${tag}" name="tag"/>
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(num)
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							总成批次
						</td>
						<td align="center">
							业务件号
						</td>
						<td align="center">
							订单号
						</td>
						<td align="center">
							名称
						</td>
						<td align="center">
							批次数量
						</td>
						<td align="center">
							单位
						</td>
						<td align="center">
							操作
						</td>						
					</tr>
					<s:iterator value="procardList" id="pagebl" status="pageStatus">
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
							${pagebl.markId}
						</td>
						<td>
							${pagebl.selfCard}
						</td>
						<td>
							${pagebl.ywMarkId}
						</td>
						<td>
							${pagebl.orderNumber}
						</td>
						<td  align="left">
							${pagebl.proName}
						</td>
						<td align="right">
							${pagebl.filnalCount}
						</td>
						<td>
							${pagebl.unit}
						</td>
						
						<td colspan="2">
							<input type="button" value="生产进度" style="height: 20px;width: 80px;" onclick="showscjd(${pagebl.id})">
							<s:if test="tag == 'wg'">
								<input type="button" value="外购退料" style="height: 20px;width: 80px;" onclick="showtuiliao(${pagebl.id},this)">
							</s:if>
							<s:elseif test="tag == 'wx'">
								<input type="button" value="外协退料" style="height: 20px;width: 80px;" onclick="showwxtuiliao(${pagebl.id},this)">
							</s:elseif>
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
function showtuiliao(id,obj){
	$(obj).attr("disabled","disabled");
	window.location.href="procardBlAction_findProcardTlDetailbyRootId.action?id="+id;
}
function showwxtuiliao(id,obj){
	$(obj).attr("disabled","disabled");
	window.location.href="ProcardAction!findWxTuiLiaoByRootId.action?id="+id;
}
function showscjd(id){
	window.open("ProcardAction!findProcardView.action?id="+id+"&pageStatus=history&viewStatus=");
}
</script>
	</body>
</html>
