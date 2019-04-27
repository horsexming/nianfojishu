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
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>入库打印任务</h3>
				<form action="storage_historyIndex.action" method="post">
				<table>
					<tr>
						<td>开始日期：<input style="width: 155px" class="Wdate" id="startTime"
									type="text" name="vsto.startTime" value="${vsto.startTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td>结束日期：<input style="width: 155px" class="Wdate" id="endTime"
									type="text" name="vsto.endTime" value="${vsto.endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td>
						<input type="hidden" name="errorMessage" value="all"/>
						<input type="submit" value="查询"/></td>
					</tr>
				</table>
				</form>
				<form action="storage_printStorage.action" method="post" onsubmit="return vali()">
					<table class="table">
						<tr  bgcolor="#c0dcf2" height="50px">
						<td></td>
						<td align="center" width="40px;">编号</td>
						<td align="center">入库时间</td>
						<td align="center">类型</td>
						<td align="center">物品名称</td>
						<td align="center" width="50px;">规格</td>
						<td align="center">物品编码</td>
						<td align="center" width="50px;">单位</td>
						<td align="center" width="70px;">数量</td>
						<td align="center" width="60px;">库别</td>
						<td align="center" width="50px;">库位</td>
						<td align="center">经办人</td>
						<td align="center">申请部门</td>
						<td align="center" width="160px;">用途</td>
					
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
						<td><input type="checkbox" name="vsto.selected" value="${pageList.id }" /></td>
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
									<td>${pageList.date}</td>
									<td>${pageList.parClass}</td>
									<td>${pageList.matetag}</td>
									<td>${pageList.format}</td>
									<td>${pageList.number}</td>
									<td>${pageList.unit}</td>
									<td>${pageList.num}</td>
									<td>${pageList.storehouse}</td>
									<td>${pageList.position}</td>
									<td>${pageList.jinbanren}</td>
									<td>${pageList.dept}</td>
									<td>${pageList.more}</td>
									<!--  <td>${pageList.date}</td>
									<td>${pageList.matetag}</td>
									<td>${pageList.format}</td>
									<td>${pageList.carModel}</td>
									<td>${pageList.unit}</td>
									<td>${pageList.storehouse}</td>
									<td>${pageList.parClass}</td>
									<td>${pageList.position}</td>
									<td>${pageList.num}</td>
									<td>${pageList.storageTaxPrice}</td>
									<td>${pageList.storageTaxMoney}</td>
									<td>${pageList.more}</td>-->
									<%-- <td><a href="storage_del.action?vsto.id=${pageList.id}">删除</a></td>--%>
						</tr>
						</s:iterator>
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
							<td colspan="15" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
					<tr>
						<td colspan="15">
							<input type="submit" style="width: 80px; height: 50px;" value="打印"/>
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
 				var selectList = document.getElementsByName("vsto.selected");
 				for(var i = 0;i<selectList.length;i++){
    				if(selectList[i].checked){
     					return true;
    					}
  					}
  				alert("请选择需要打印的记录！谢谢");
  				return false;
			}
		</script>
	</body>
</html>
