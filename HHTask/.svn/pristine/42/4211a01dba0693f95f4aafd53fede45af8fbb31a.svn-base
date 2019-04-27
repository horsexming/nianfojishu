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
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="productUnPassKpAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					开票未通过产品管理<br/>(unpass bill product Management)
				</h3>
				<s:if test="successMessage!=null">
					<h2>
				       <br/><font color="red">${successMessage}</font>
				    </h2>
					</s:if>
				<form action="productUnPassKpAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								订单编号（order number）：
								<input type="text" name="productUnPassKp.odrerNumber" value="<s:property value="productUnPassKp.odrerNumber"/>" />
							</td>
							<td align="center">
								客户名称（customer Name）：
								<input type="text" name="productUnPassKp.cusName" value="<s:property value="productUnPassKp.cusName"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								件号（part number）：
								<input type="text" name="productUnPassKp.markId" value="<s:property value="productUnPassKp.markId"/>" />
							</td>
							<td align="center">
								状态（customer Name）：
								<SELECT name="productUnPassKp.status">
								 <s:if test="productUnPassKp.status!=null">
								  <option><s:property value="productUnPassKp.status"/></option>
								 </s:if>
								 <option>全部</option>
								 <option>初始</option>
								 <option>已确定</option>
								</SELECT>
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
						<td align="center">
							序号
						</td>
						<td align="center">
							订单编号
						</td>
						<td align="center">
							客户名称
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
							送货单号
						</td>
						<td align="center">
							总数量
						</td>
						<td align="center">
							返修数量
						</td>						
						<td align="center">
							重新生产数量
						</td>						
						<td align="center">
							废除订单数量
						</td>						
						<td align="center">
							合格数量
						</td>						
						<td align="center">
							状态
						</td>						
						<td align="center">
							添加时间
						</td>						
						<td align="center" colspan="2">操作</td>
					</tr>
					<s:iterator value="productUnPassKpList" id="pageproduct" status="pageStatus">
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
							${pageproduct.odrerNumber}
						</td>
						<td>
							${pageproduct.cusName}
						</td>
						<td>
							${pageproduct.markId}
						</td>
						<td>
							${pageproduct.selfCard}
						</td>
						<td>
							${pageproduct.proName}
						</td>
						<td>
							${pageproduct.hkSellSendId}
						</td>
						<td>
							${pageproduct.totalCount}
						</td>
						<td>
							${pageproduct.reworkCount}
						</td>
						<td>
							${pageproduct.rebuildCount}
						</td>
						<td>
							${pageproduct.cutCount}
						</td>
						<td>
							${pageproduct.okCount}
						</td>
						<td>
							${pageproduct.status}
						</td>
						<td>
							${pageproduct.addTime}
						</td>
						
						<td  colspan="2">
						<s:if test="#pageproduct.status!='已确认'">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${pageproduct.id},${cpage})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="if(window.confirm('您将删除数据是否继续？')){todelete(${pageproduct.id },${cpage})};" />
								</s:if>
						</td>

					</s:iterator>
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
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function update(id,cpage) {
	window.location.href = "productUnPassKpAction_toupdate.action?productUnPassKp.id=" + id+"&cpage="+cpage;
}
function todelete(id,cpage) {
	window.location.href = "productUnPassKpAction_delete.action?productUnPassKp.id=" + id+"&cpage="+cpage;
}
</script>
	</body>
</html>
