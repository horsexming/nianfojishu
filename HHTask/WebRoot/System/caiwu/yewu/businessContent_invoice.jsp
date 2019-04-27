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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>发票</h3>
				<s:if test="flow == null">
				<label>添加发票：</label><input type="button" value="添加发票" style="width: 80px;height: 50px;" onclick="addInvoice(${id})"/>
				</s:if>
				<hr/>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">序号</td>
						<td align="center">发票号码</td>
						<td align="center">收款单位</td>
						<td align="center">费用金额</td>
						<td></td>
						<td></td>
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
									<td>${pageList.number }</td>
									<td>${pageList.collectionUnit }</td>
									<s:if test="#pageList.money == 0.0">
										<td>0.00${pageList.currencyType }</td>
									</s:if>
									<s:else>
									<td>${pageList.money }${pageList.currencyType }</td>
									</s:else>
									<td><input type="button" value="修改" style="width: 60px; height: 30px;" onclick="update(${pageList.id })"/></td>
									<td><input type="button" value="删除" style="width: 60px; height: 30px;" onclick="del(${pageList.id })"/></td>
						</tr>
						</s:iterator>
							<tr>
						<s:if test="errorMessage==null">
							<td colspan="6" align="right">
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
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function addInvoice(id){
				window.location = "<%=basePath%>/System/caiwu/yewu/businessContent_addInvoice.jsp?id="+id;
			}
			function update(id){
				window.location = "business_initUpdateInvoice.action?inId="+id+"&buId=${id}";
			}
			function del(id){
				window.location = "business_delInvoice.action?inId="+id+"&buId=${id}&flow=${flow}";
			}
		</script>
	</body>
</html>
