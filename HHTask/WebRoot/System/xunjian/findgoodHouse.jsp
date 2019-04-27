<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在仓区进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<h3>
					仓区管理
				</h3>
				<form action="GoodsStoreAction!findgoodHouse.action" method="post">
					<table class="table">
						<tr>
							<td>
								仓库:
								<select id="wareHouseName" name="goodHouse.goodsStoreWarehouse" >
									<option value="">--请选择--</option>
								</select>
							</td>
							<td align="center">
								区名:
								<input type="text" name="goodHouse.GoodHouseName"
									value="<s:property value="goodHouse.GoodHouseName"/>" />
							</td>
							<td align="center">
								编号:
								<input type="text" name="goodHouse.GoodHouseNum"
									value="<s:property value="goodHouse.GoodHouseNum"/>" />
							</td>
						</tr>
							
							
						<tr>
							<td colspan="3" align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加" class="input" onclick="add()" />
							</td>
						</tr>
					</table>

					<table width="100%" border="0" style="border-collapse: collapse;" class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								仓库
							</td>
							<td align="center">
								区名
							</td>
							
							<td align="center">
								仓区总面积(m2)
							</td>
							<td align="center">
								仓区已用面积(m2)
							</td>
							<td align="center">
								仓区剩余面积(m2)
							</td>
							
							
							<td align="center">
								编号
							</td>
							<td align="center" colspan="2">
								操作
							</td>
						</tr>
						<tr>
							<s:iterator value="goodHouseList" id="pageLists" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="center">
									<s:if test="#pageStatus.index%2==1">
									</s:if>
									<s:else>
										<font color="#c0dcf2"></font>
									</s:else>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="center">
									${pageLists.goodsStoreWarehouse}
								</td>
								<td align="center">
									${pageLists.goodHouseName}
								</td>
								
								<td align="center">
									<fmt:formatNumber value="${pageLists.goodAllArea}" pattern="#0.00" />
								</td>
								<td align="center" >
									<fmt:formatNumber value="${pageLists.goodIsUsedArea}" pattern="#0.00" />
								</td>
								<td align="center">
									<fmt:formatNumber value="${pageLists.goodLeaveArea}" pattern="#0.00" />
								</td>
								<td align="center" colspan="2">
									<a onclick="update(${pageLists.id})">修改</a>/
									<a   onclick="delwarehouseArea(${pageLists.id})"  href="javascript:;">删除</a>
									<input type="hidden" name="use'+${pageLists.id}+'"  id="use${pageLists.id}"   value="${pageLists.goodIsUsedArea}"/>
								</td>
							</s:iterator>
						</tr>

						<tr>
							<s:if test="errorMessage==null">
								<td colspan="8" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="6" align="center" style="color: red">
									${errorMessage}
							</s:else>
						</tr>
					</table>

				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
function add() {
	var url = "<%=request.getContextPath()%>/System/wuliu/WarehouseArea_add.jsp";
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function update(obj) {
	var url = "<%=request.getContextPath()%>/GoodsStoreAction!salgoodHouseByid.action?goodHouse.id="
			+ obj;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
$(function(){
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_getAllwarehouse.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$(data).each(function(){
						$("#wareHouseName").append('<option value='+this.name+'>'+this.name+'</option>');
					});
				$("#wareHouseName").tinyselect();
			}
		}
	})
})



function delwarehouseArea(id){
	var useVal=$("#use"+id).val();
	if(useVal!=0){
		alert("该仓区现在被占用，无法执行删除操作");
	}else{
		if(confirm('确定要删除本仓区和本仓区下所有库位吗?')){
			window.location.href = "WarehouseAreaAction_delwarehouseArea.action?warehouseArea.id="+id;
		}
	}
	
}
</script>
	</body>
</html>
