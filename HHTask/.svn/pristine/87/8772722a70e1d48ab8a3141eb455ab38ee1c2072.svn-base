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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
				<h3>
					设变在制品入库接收
				</h3>
				<form action="procardTemplateGyAction_findProcardsbWasterxcList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								设变单号:
								<input type="text" name="procardSbWaster.sbNumber" value="<s:property value="procardSbWaster.sbNumber"/>" />
							</td>
							<td align="center">
								订单号:
								<input type="text" name="procardSbWaster.orderNumber" value="<s:property value="procardSbWaster.orderNumber"/>" />
							</td>
							
						</tr>
						<tr>
							<td align="center">
								总成件号:
								<input type="text" name="procardSbWaster.rootMarkId" value="<s:property value="procardSbWaster.rootMarkId"/>" />
							</td>
							<td align="center">
								业务件号:
								<input type="text" name="procardSbWaster.ywmarkId" value="<s:property value="procardSbWaster.ywmarkId"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								件号:
								<input type="text" name="procardSbWasterxc.markId" value="<s:property value="procardSbWasterxc.markId"/>" />
							</td>
							<td align="center">
								批次:
								<input type="text" name="procardSbWasterxc.selfCard" value="<s:property value="procardSbWasterxc.selfCard"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								名称:
								<input type="text" name="procardSbWasterxc.proName" value="<s:property value="procardSbWasterxc.proName"/>" />
							</td>
							<td align="center">
								类型:
								<input type="text" name="procardSbWasterxc.procardStyle" value="<s:property value="procardSbWasterxc.procardStyle"/>" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							设变单号
						</td>
						<td align="center">
							订单号
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
							件号
						</td>						
						<td align="center">
							批次
						</td>						
						<td align="center">
							名称
						</td>						
						<td align="center">
							零件类型
						</td>						
						<td align="center">
							工序号
						</td>						
						<td align="center">
							工序名称
						</td>						
						<td align="center">
							供料属性
						</td>						
						<td align="center">
							物料类别
						</td>						
						<td align="center">
							批次数量
						</td>						
						<td align="center">
							关联数量
						</td>						
						<td align="center">
							报废数量
						</td>						
						<td align="center">
							退库数量
						</td>						
						<td align="center">
							单位
						</td>						
						<td align="center">操作</td>
					</tr>
					<s:iterator value="procardSbWasterxcList" id="pagexc" status="pageStatus">
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
						<td align="center">
							<s:property value="#pagexc.sbNumber"/>
						</td>
						<td align="center">
							<s:property value="#pagexc.orderNumber"/>
						</td>
						<td align="center">
							<s:property value="#pagexc.rootMarkId"/>
						</td>
						<td align="center">
							<s:property value="#pagexc.rootSelfCard"/>
						</td>
						<td align="center">
							<s:property value="#pagexc.ywmarkId"/>
						</td>						
						<td align="center">
							<s:property value="#pagexc.markId"/>
						</td>						
						<td align="center">
							<s:property value="#pagexc.selfCard"/>
						</td>						
						<td align="center">
							<s:property value="#pagexc.proName"/>
						</td>						
						<td align="center">
							<s:property value="#pagexc.procardStyle"/>
						</td>						
						<td align="center">
							<s:property value="#pagexc.processNo"/>
						</td>						
						<td align="center">
							<s:property value="#pagexc.processName"/>
						</td>						
						<td align="center">
							<s:property value="#pagexc.kgliao"/>
						</td>						
						<td align="center">
							<s:property value="#pagexc.wgType"/>
						</td>						
						<td align="right">
							<s:property value="#pagexc.finalCount"/>
						</td>						
						<td align="right">
							<s:property value="#pagexc.glCount"/>
						</td>						
						<td align="right">
							<s:property value="#pagexc.bfclCount"/>
						</td>						
						<td align="right">
							<s:property value="#pagexc.tkclcount"/>
						</td>				
						<td align="center">
							<s:property value="#pagexc.util"/>
						</td>		
						<td align="center">
							<input type="button" value="入废品库" onclick="sbWasterTobf(${pagexc.id})"/>
							<input type="button" value="退回原库" onclick="sbWasterToback(${pagexc.id})"/>
						</td>
					</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="20" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="20" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="20" align="center" style="color: red">
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
<SCRIPT type="text/javascript">
function sbWasterTobf(id){
	window.location.href="procardTemplateGyAction_sbWasterTobf.action?id="+id+"&cpage=${cpage}";
}
function sbWasterToback(id){
	window.location.href="procardTemplateGyAction_sbWasterToback.action?id="+id+"&cpage=${cpage}";
}
</SCRIPT>
	</body>
</html>
