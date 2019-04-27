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
						href="skillScoreAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					不合格产品管理<br/>(Unqualified product Management)
				</h3>
				<form action="productUnqualifiedAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								客户名称（customer Name）：
								<input type="text" name="productUnqualified.cusName" value="<s:property value="productUnqualified.cusName"/>" />
							</td>
							<td align="center">
								件号（part number）：
								<input type="text" name="productUnqualified.markId" value="<s:property value="productUnqualified.markId"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								状态（source）：
								<SELECT name="productUnqualified.source">
								 <s:if test="productUnqualified.source!=null">
								  <option><s:property value="productUnqualified.source"/></option>
								 </s:if>
								 <option>全部</option>
								 <option>开票</option>
								 <option>入库</option>
								 <option>生产</option>
								</SELECT>
							</td>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加技能系数(add)" onclick="add()"/>
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
							客户名称<br/>（customer Name）
						</td>
						<td align="center">
							件号<br/>（part number）
						</td>
						<td align="center">
							名称<br/>（part name）
						</td>
						<td align="center">
							总数量<br/>（total Count）
						</td>
						<td align="center">
							入库数量<br/>（input Count）
						</td>						
						<td align="center">
							剩余数量<br/>（surplus Count）
						</td>						
						<td align="center">
							失败数量<br/>（failure Count）
						</td>						
						<td align="center">
							来源<br/>（source）
						</td>						
						<td align="center">
							添加时间<br/>（add time）
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="productUnqualifiedList" id="pageproduct" status="pageStatus">
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
							${pageproduct.cusName}
						</td>
						<td>
							${pageproduct.markId}
						</td>
						<td>
							${pageproduct.proName}
						</td>
						<td>
							${pageproduct.totalCount}
						</td>
						<td>
							${pageproduct.rukuCount}
						</td>
						<td>
							${pageproduct.surplusCount}
						</td>
						<td>
							${pageproduct.failureCount}
						</td>
						<td>
							${pageproduct.source}
						</td>
						<td>
							${pageproduct.addTime}
						</td>
						
						<td  colspan="2">
							<input type="button" value="申请入库(update)"
								style="width: 60px; height: 30px;"
								onclick="apply(${pageproduct.id})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="if(window.confirm('您将删除数据是否继续？')){todelete(${pageproduct.id },${cpage})};" />
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
function apply(id) {
	window.location.href = "productUnqualifiedAction_toapply.action?productUnqualified.id=" + id;
}
function todelete(id,cpage) {
	window.location.href = "productUnqualifiedAction_delete.action?productUnqualified.id=" + id+"&cpage="+cpage;
}
</script>
	</body>
</html>
