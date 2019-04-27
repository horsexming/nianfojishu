<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
				<h3>
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							样品是否到达
						</td>
						<td align="center">
							图纸
						</td>
						<td align="center">
							检验报告
						</td>
						<td align="center">
							材质证明
						</td>
						<td align="center">
							环保报告
						</td>
						<td align="center">
							材料性能
						</td>
						<td align="center">
							盐雾检验
						</td>
						<td align="center">
							模具认证资料
						</td>
						<td align="center">
							工艺认证
						</td>
						<td align="center">
							状态
						</td>
						<s:if test="operate=='operate'">
						<td align="center">
							操作
						</td>
						</s:if>
					</tr>
					<s:iterator value="sampleList" id="list"
						status="pageStatus">
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
							${list.yangPin}
						</td>
						<td align="center">
							${list.pic}
						</td>
						<td align="center">
							${list.checkNote}
						</td>
						<td align="center" >
							${list.caiZhi}
						</td>
						<td align="center">
							${list.huanBao}
						</td>
						<td align="center">
							${list.caiLiao}
						</td>
						<td align="center">
							${list.yanWu}
						</td>
						<td align="center" >
							${list.moJuRenZhen}
						</td>
						<td align="center">
							${list.gongYi}
						</td>
						<td align="center">
							${list.status}
						</td>
						<s:if test="operate=='operate'">
						<td align="center">
						<s:if test="#list.status=='分析文档已提交'">
							<a onclick="update(${list.id},'${cpage}')">采购检验</a>
						</s:if>
						<s:elseif test="#list.status=='到货'">
							<a onclick="update1(${list.id},'${cpage}')">审批</a>
						</s:elseif>
						<s:elseif test="#list.status=='同意'">
							<a onclick="update2(${list.id},'${cpage}')">录入价格</a>
						</s:elseif>
						</td>
						</s:if>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
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
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">
function update(id, cpage) {
	window.location.href = "ZhuserOfferAction_findSample.action?sample.id="
			+ id + "&cpage=" + cpage+"&status=caigou";
}
function update1(id, cpage) {
	window.location.href = "ZhuserOfferAction_shenpi.action?sample.id="
			+ id + "&cpage=" + cpage+"&status=daohuo";
}
function update2(id, cpage) {
	window.location.href = "ZhuserOfferAction_inputPrice.action?sample.id="
			+ id + "&cpage=" + cpage+"&status=pass";
}

</script>
	</body>
</html>
