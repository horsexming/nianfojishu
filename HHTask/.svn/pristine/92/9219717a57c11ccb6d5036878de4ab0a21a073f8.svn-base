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
			<form action="business_querySellByBusinessId.action" method="post">
				<table>
					<tr><td>件次：</td><td><input type="text" name="sell.sellLot" value="${sell.sellLot }"/></td>
					<td>件号：</td><td><input type="text" name="sell.sellMarkId" value="${sell.sellMarkId }"/></td>
					<td>客户：</td><td><input type="text" name="sell.sellCompanyName" value="${sell.sellCompanyName }"/></td>
					<td>库位：</td><td><input type="text" name="sell.sellWarehouse" value="${sell.sellWarehouse }"/></td></tr>
					<tr><td>产品名称：</td><td><input type="text" name="sell.sellGoods" value="${sell.sellGoods }"/></td>
					<td>开始时间：</td><td><input type="text" name="beginTime" value="${beginTime }"/></td>
					<td>结束时间：</td><td><input type="text" name="endTime" value="${endTime }"/></td>
					<input type="hidden" name="id" value="${id }"/>
					<input type="hidden" name="errorMessage" value="all"/>
					<td colspan="2"><input type="submit" value="查询" style="width: 80px;height: 35px;"/></td></tr>
				</table>
				</form>
				<form action="business_batchAddDetail.action" method="post" onsubmit="return vali()">
				<table  class="table">
					<tr><th align="center" colspan="7">出库明细</th></tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td></td>
						<td align="center">序号</td>
						<td align="center">批次</td>
						<td align="center">件号</td>
						<td align="center">产品名称</td>
						<td align="center">客户</td>
						<td align="center">出库时间</td>
						<td align="center">库位</td>
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
						<td>${pageList.sellId }<input type="checkbox" name="selected" value="${pageList.sellId }" /></td>
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
									<td>${pageList.sellLot }</td>
									<td>${pageList.sellMarkId }</td>
									<td>${pageList.sellGoods }</td>
									<td>${pageList.sellCompanyName }</td>
									<td>${pageList.sellDate }</td>
									<td>${pageList.sellWarehouse }</td>
						</tr>
						</s:iterator>
						<tr>
						<input type="hidden" name="id" value="${id }"/>
						<td colspan="7" align="center"><input type="submit" value="添加明细" style="width: 80px;height: 35px;"/></td></tr>
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
			function vali(){
				var selectList = document.getElementsByName("selected");
				for(var i = 0;i<selectList.length;i++){
					if(selectList[i].checked){
				      return true;
				    }
				  }
			    alert("请选择需要的出库明细再添加！谢谢");
				return false;
			}
		</script>
	</body>
</html>
