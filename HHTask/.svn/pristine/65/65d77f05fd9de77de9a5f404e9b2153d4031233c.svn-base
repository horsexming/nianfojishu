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
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					库存管理
				</h3>
				<br />
				<form action="store_queryStoreByCondition.action" method="post"
					name="myForm">
					<table>
						<tr>
							<td>
								编号：
								<input type="text" name="number" value="${number}" />
							</td>
							<td>
								名称：
								<input type="text" name="matetag" value="${matetag}" />
							</td>
							<td>
								规格：
								<input type="text" name="format" value="${format}" />
							</td>
						</tr>
						<tr>
							<td>
								库位：
								<input type="text" name="place" value="${place}" />
							</td>
							<td>
								仓库：
								<select name="storehouse">
									<option value="选择性质" selected="selected">
										选择性质
									</option>
									<s:iterator id="house" value='{"工具库 ","工装库","备件库","综合库","油料库"}'>
										<s:if test='#house == storehouse'>
											<option value="${house }" selected="selected">
												${house }
											</option>
										</s:if>
										<s:else>
											<option value="${house }">
												${house }
											</option>
										</s:else>
									</s:iterator>
								</select>
								<input type="hidden" name="errorMessage" value="all" />
							</td>
							<td>
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
								<input type="button" value="导出Excel"
									style="width: 80px; height: 50px;" onclick="exportExcel()" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<td></td>
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
							规格
						</td>
						<td>
							单位
						</td>
						<td>
							可借量
						</td>
						<td>
							总数量
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
							入库时间
						</td>
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
							<input type="checkbox" name="vsto.selected"
								value="${pageList.id }" />
						</td>
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
							${pageList.format}
						</td>
						<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.curAmount}
						</td>
						<td>
							${pageList.total}
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
							${pageList.more}
						</td>
							<td>
							${pageList.startDate}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="14" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
					<tr>
						<td colspan="14">
							<input type="button" value="修改"
								onclick="jump('store_initUpdate.action?id=')"
								style="margin-left: 15px;" />
							<input type="button" value="删除" onclick="del()"
								style="margin-left: 15px;" />
							<input type="button" value="报修"
								onclick="jump('store_repairs.action?id=')"
								style="margin-left: 15px;" />
							<input type="button" value="报废"
								onclick="jump('scrap_initAdd.action?vosc.id=')"
								style="margin-left: 15px;" />
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
function exportExcel() {
	document.forms.myForm.action = "store_export.action";
	document.forms.myForm.submit();
	document.forms.myForm.action = "store_queryStoreByCondition.action";
}
function del() {
	var r = validate();
	if (r) {
		if (confirm("确定删除吗?")) {
			var v = $('input:checked').val();
			window.location = "store_del.action?id=" + v;
			return;
		} else {
			return false;
		}
	}
}
function validate() {
	var v = $('input:checked').length;
	if (v == 0) {
		alert("请选择一个产品!谢谢");
		return false;
	}
	return true;
}
function jump(local) {
	var r = validate();
	if (r) {
		var v = $('input:checked').val();
		window.location = local + v;
	}
}
</script>
	</body>
</html>
