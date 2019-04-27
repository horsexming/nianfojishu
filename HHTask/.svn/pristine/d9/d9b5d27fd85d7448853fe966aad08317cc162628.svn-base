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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: right; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					关键备件管理
				</h3>
				<br />
				<form action="machineSparePartAction_showList.action" method="post"
					name="myForm">
					<table>
						<tr>
							<td>
								编号：
								<input type="text" name="machineSparePart.number" value="${machineSparePart.number}" />
							</td>
							<td>
								名称：
								<input type="text" name="machineSparePart.matetag" value="${machineSparePart.matetag}" />
							</td>
							<td>
								规格：
								<input type="text" name="machineSparePart.format" value="${machineSparePart.format}" />
							</td>
						</tr>
						<tr>
							<td>
								库位：
								<input type="text" name="machineSparePart.place" value="${machineSparePart.place}" />
							</td>
							<td>
								设备名称：
								<input type="text" name="machineSparePart.machine.name" value="${machineSparePartVo.machineName}" />
							</td>
							<td>库存状态：
								<SELECT name="machineSparePart.safeStatus">
								 <s:if test="machineSparePart.safeStatus!=null">
								 <option value="<s:property value="machineSparePart.safeStatus"/>"><s:property value="machineSparePart.safeStatus"/> </option>
								 </s:if>
								 <option>全部
								 </option>
								 <option>安全
								 </option>
								 <option>缺少
								 </option>
								</SELECT>
							</td>
						</tr>
						<tr>
						  <td colspan="3" align="center">
						  <input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
						 <input type="button" value="生成下月采购计划"
									style="width: 160px; height: 50px;" onclick="nextMonthOa()" />
						 <input type="button" value="添加"
									style="width: 80px; height: 50px;" onclick="add()" />
<%--						 <input type="button" value="更新库存量"--%>
<%--									style="width: 80px; height: 50px;" onclick="add()" />--%>
						  </td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<td>
							序号
						</td>
						<td>
							编号
						</td>
						<td>
							名称
						</td>
						<td>
							设备图片
						</td>
						<td>
							设备名称
						</td>
						<td>
							规格
						</td>
						<td>
							单位
						</td>
						<td>
							安全库存
						</td>
						<td>
							当前库存
						</td>
						<td>
							库存状况
						</td>
						
						<td>
							仓库名
						</td>
						<td>
							类别
						</td>
						<td>
							库位
						</td>
						<td>
							备注
						</td>
						<td>
							添加时间
						</td>
						<td colspan="2">
							操作
						</td>
					</tr>
					<s:iterator value="mspVoList" id="pageList" status="pageStatus">
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
							${pageList.number}
						</td>
						<td>
							${pageList.matetag}
						</td>
						<td>
							<a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/Msp/${pageList.pic}"><img style ="width: 80px;height: 80px;max-width: 100%;max-height: 100%;" alt="111" src="${pageContext.request.contextPath}/upload/file/Msp/${pageList.pic}"></a>
						</td>
						<td>
							${pageList.machineName}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.safeCount}
						</td>
						<td>
							${pageList.curCount}
						</td>
						<td>
						 <s:if test="#pageList.safeCount<#pageList.curCount">
						  安全
						 </s:if>
						 <s:else>
						  <font color="red">缺少</font>
						 </s:else>
						</td>
						<td>
							${pageList.storehouse}
						</td>
						<td>
							${pageList.parClass}
						</td>
						<td>
							${pageList.place}
						</td>
						<td>
							${pageList.remake}
						</td>
							<td>
							${pageList.addtime}
						</td>
						<td colspan="2">
							<input type="button" value="修改" onclick="update(${pageList.id})" style="margin-left: 15px;" />
							<input type="button" value="删除" onclick="todelete(${pageList.id},'${cpage}')" style="margin-left: 15px;" />
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="16" align="right">
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
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id) {
	  window.location.href = "machineSparePartAction_toUpdate.action?machineSparePart.id=" + id;
}
function todelete(id,cpage) {
	window.location.href = "machineSparePartAction_delete.action?machineSparePart.id=" + id+"&cpage="+cpage;
}
function add() {
	window.location.href = "<%=path%>/System/Equipment/machineSparePart_add.jsp";
}
function nextMonthOa(){
	 window.location.href = "machineSparePartAction_toNextMonthOa.action?";
}
</script>
	</body>
</html>
