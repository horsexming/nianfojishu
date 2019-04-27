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
			<h3>返修单管理</h3>
			<form action="procardTemplateGyAction_findProcardReProductList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								返修单号：
								<input type="text" name="preProducte.fxNumber" value="<s:property value="preProducte.fxNumber"/>" />
							</td>
							<td align="center">
								件号：
								<input type="text" name="preProducte.markId" value="<s:property value="preProducte.markId"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">序号</td>
						<td align="center">单号</td>
						<td align="center">件号</td>
						<td align="center">批次</td>
						<td align="center">规格</td>
						<td align="center">图号</td>						
						<td align="center">版本号</td>						
						<td align="center">数量</td>						
						<td align="center">单位</td>						
						<td align="center">工序号</td>						
						<td align="center">工序名称</td>						
						<td align="center">状态</td>						
						<td align="center">操作</td>
					</tr>
					<s:iterator value="preProductList" id="pageproduct" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="right">
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${pageproduct.fxNumber}
						</td>
						<td align="center">
							${pageproduct.markId}
						</td>
						<td align="center">
							${pageproduct.selfCard}
						</td>
						<td align="center">
							${pageproduct.specification}
						</td>
						<td align="center">
							${pageproduct.tuhao}
						</td>
						<td align="center">
							${pageproduct.banbenNumber}
						</td>
						<td align="right">
							${pageproduct.fxCount}
						</td>
						<td align="center">
							${pageproduct.unit}
						</td>
						<td align="center">
							${pageproduct.processNo}
						</td>
						<td align="center">
							${pageproduct.processName}
						</td>
						<td align="center">
							${pageproduct.status}
						</td>
						<td  colspan="2">
							<input type="button" value="工序图纸"
								style="width: 60px; height: 30px;"
								onclick="showfx(${pageproduct.id})" />
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
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function showfx(id){
	window.location.href="procardTemplateGyAction_getfxd.action?id="+id+"&cpage="+${cpage};
}
</SCRIPT>
	</body>
</html>
