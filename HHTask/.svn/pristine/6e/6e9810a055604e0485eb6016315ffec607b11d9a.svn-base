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
					冲销转库
				</h3>
				<form action="goodsAction!cxchangeStore.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								内部订单号
								<input type="text" name="productManager.orderNumber" value="<s:property value="productManager.orderNumber"/>" />
							</td>
							<td align="center">
								外部订单号
								<input type="text" name="productManager.outOrderNumber" value="<s:property value="productManager.outOrderNumber"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								件号
								<input type="text" name="productManager.pieceNumber" value="<s:property value="productManager.pieceNumber"/>" />
							</td>
							<td align="center">
								业务件号
								<input type="text" name="productManager.ywMarkId" value="<s:property value="productManager.ywMarkId"/>" />
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
						<th align="center">
							序号
						</th>
						<th align="center">
							内部订单号
						</th>
						<th align="center">
							外部订单号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							业务件号
						</th>
						<th align="center">
							冲销数量
						</th>						
						<th align="center">
							已转数量
						</th>						
						<th align="center">
							申请数量
						</th>						
						<th align="center">
							待转数量
						</th>						
						<th align="center">
							可转数量
						</th>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="pmList" id="pagePm" status="pageStatus">
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
							${pagePm.orderNumber}
						</td>
						<td align="center">
							${pagePm.outOrderNumber}
						</td>
						<td align="center">
							${pagePm.pieceNumber}
						</td>
						<td align="center">
							${pagePm.ywMarkId}
						</td>
						<td align="center">
							${pagePm.cxCount}
						</td>						
						<td align="center">
							<s:if test="#pagePm.cxzkuCount==null">
								0
							</s:if>
							<s:else>
							<s:property value="#pagePm.cxzkuCount"/>
							</s:else>
						</td>						
						<td align="center">
							<s:if test="#pagePm.cxzkuApplyCount==null">
								0
							</s:if>
							<s:else>
							<s:property value="#pagePm.cxzkuApplyCount"/>
							</s:else>
						</td>						
						<td align="center">
							<s:if test="#pagePm.cxzkuApplyCount==null">
								<s:property value="#pagePm.cxCount"/>
							</s:if>
							<s:elseif test="#pagePm.cxzkuCount==null">
								<s:property value="#pagePm.cxCount-#pagePm.cxzkuApplyCount"/>
							</s:elseif>
							<s:else>
								<s:property value="#pagePm.cxCount-#pagePm.cxzkuApplyCount-#pagePm.cxzkuCount"/>
							</s:else>
						</td>						
						<td align="center">
						<s:if test="#pagePm.cxzkuApplyCount==null">
								<s:property value="#pagePm.cxRukuCount"/>
						</s:if>
						<s:elseif test="#pagePm.cxzkuCount==null">
								<s:property value="#pagePm.cxRukuCount-#pagePm.cxzkuApplyCount"/>
						</s:elseif>
						<s:else>
								<s:property value="#pagePm.cxRukuCount-#pagePm.cxzkuApplyCount-#pagePm.cxzkuCount"/>
						</s:else>
						</td>	
						
						<td  colspan="2">
							<input type="button" value="申请转库"
								style="width: 80px; height: 20px;"
								onclick="appluzk(${pagePm.id})" />

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
function appluzk(id){
	window.location.href="goodsAction!toapplycxzk1.action?id="+id;
}

</script>
	</body>
</html>
