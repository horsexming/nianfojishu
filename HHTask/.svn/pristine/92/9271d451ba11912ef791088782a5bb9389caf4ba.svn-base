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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>物流内部计划管理</h3>
				<form action="internalOrder_queryLogisticsCheckByCondition.action" method="post"> 
				<table>
					<tr>
						<td>订单编号：<input type="text" name="orderNum" value="${orderNum}"/></td>
						<td>客户：<select name="customeId" style="margin-left: 30px;width:160px;">
									<option value="0" selected="selected">选择用户</option>
									<s:iterator id="c" value="clients">
									    <s:if test="#c.id == customeId">
											<option value="${c.id}" selected="selected">${c.clientcompanyname}</option>
									    </s:if>
									    <s:else>
									    	<option value="${c.id}">${c.clientcompanyname}</option>
									    </s:else>
									</s:iterator>
								 </select>
						</td>
					</tr>
					<tr>
						<td>开始日期：<input style="width: 155px" class="Wdate"
									type="text" name="beginTime" value="${beginTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td>结束日期：<input style="width: 155px" class="Wdate"
									type="text" name="endTime" value="${endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td>
							<input type="hidden" name="errorMessage" value="all"/>
							<input type="hidden" name="id" value="${id}"/>
							<input type="submit" value="查询" style="width: 80px; height: 50px;"/>
						</td>
					</tr>
				</table>
				</form>
				<form action="internalOrder_applyPurchase.action" method="post"
			onsubmit="return vali()">
					<table  class="table">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td></td>
						<td align="center">序号</td>
						<td align="center">计划编号</td>
						<td align="center">客户</td>
						<td align="center">生产计划</td>
						<td align="center">下计划时间</td>
						<td align="center">跟单人</td>
						<td align="center">状态</td>
						<td align="center">采购状态</td>
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
						<td><input type="checkbox" name="selected" value="${pageList.id }" /></td>
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
									<td>${pageList.num }</td>
									<td>${pageList.custome.clientcompanyname}</td>
									<td>${pageList.genertorDate}</td>
									<td>${pageList.newDate }</td>
									<td>${pageList.documentaryPeople}</td>
									<td>${pageList.status}</td>
									<td>${pageList.whetherPurchase}</td>
									<td><input type="button" value="明细" style="width: 60px; height: 30px;" onclick="detail(${pageList.id})"/></td>
						</tr>
						</s:iterator>
						<tr>
							<td><input type="submit" style="width: 80px; height: 50px;" value="申请订单"/>
						</tr>
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
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function generator(){
				window.location="internalOrder_initGenerateInnerOrder.action";
			}
			function detail(id){
				window.location="internalOrder_queryInternalOrderDetail.action?id="+id+"&message=check";
			}
			function vali(){
 				var selectList = document.getElementsByName("selected");
 				for(var i = 0;i<selectList.length;i++){
    				if(selectList[i].checked){
     					return true;
    					}
  					}
  				alert("请选择需要申请采购的计划！谢谢");
  				return false;
			}
		</script>
	</body>
</html>
