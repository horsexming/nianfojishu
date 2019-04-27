<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>

			<div align="center">
				<h2 style="font-size: 20px;">
					库存调拨单打印
				</h2>
				<form action="sellAction!findChangeGoods.action" method="post">
					<table class="table">
						<tr>
							<th align="right">件号</th>
							<td><input type="text" name="sell.sellMarkId" value="${sell.sellMarkId}"/></td>
							<th align="right">名称</th>
							<td><input type="text" name="sell.sellGoods" value="${sell.sellGoods}"/></td>
							<th align="right">打印单号</th>
							<td><input type="text" name="sell.changePrint" value="${sell.changePrint}"/></td>
							<th align="right">出库类型</th>
							<td><input type="text" name="sell.style" value="${sell.style}"/></td>
							<th align="right">输入已打印条数</th>
							<td><input type="text" name="pageSize" value="${pageSize}"/></td>
						</tr>
						<tr>
							<td colspan="10" align="center">
								<input type="submit" value="查询" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<form action="sellAction!toPrintChangeGoods.action" method="post">
					<table class="table">
						<tr>
							<th>选择</th>
							<th>序号</th>
							<th>打印单号</th>
							<th>件号</th>
							<th>名称</th>
							<th>规格</th>
							<th>单位</th>
							<th>数量</th>
							<th>调出仓库</th>
							<th>调出仓区</th>
							<th>调出库位</th>
							<th>调入仓库</th>
							<th>调入仓区</th>
							<th>调入库位</th>
							<th>日期</th>
							<th>出库类型</th>
						</tr>
						<tr bgcolor="red">
							<th>全选未打印<br/>
								<input type="checkbox" onchange="changeUnAll(this)"/></th>
							<th colspan="20">调拨未打印</th>
						</tr>
						<s:if test="list!=null && list.size()>0">
							<s:iterator value="list" id="unSell" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
									<td align="center"><input type="checkbox" value="${unSell.sellId}" class="unSell" name="selected"/></td>
									<td>${pageStatus.index+1 }</td>
									<td>${unSell.changePrint}</td>
									<td>${unSell.sellMarkId}</td>
									<td>${unSell.sellGoods}</td>
									<td>${unSell.sellFormat}</td>
									<td>${unSell.sellUnit}</td>
									<td align="right">${unSell.sellCount}</td>
									<td>${unSell.sellWarehouse}</td>
									<td>${unSell.goodHouseName}</td>
									<td>${unSell.kuwei}</td>
									<td style="background-color: lime">${unSell.goodsStoreWarehouse}</td>
									<td style="background-color: lime">${unSell.goodsStorehouseName}</td>
									<td style="background-color: lime">${unSell.goodsStorePosition}</td>
									<td>${unSell.sellTime}</td>
									<td>${unSell.style}</td>
								</tr>
							</s:iterator>
						</s:if>
						<tr bgcolor="green">
							<th>全选已打印<br/>
								<input type="checkbox" onchange="changeAll(this)"/></th>
							<th colspan="20">调拨已打印</th>
						</tr>
						<s:if test="listSell!=null && listSell.size()>0">
							<s:iterator value="listSell" id="lsell" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
									<td align="center"><input type="checkbox" value="${lsell.sellId}" class="lSell" name="selected"/></td>
									<td>${pageStatus.index+1 }</td>
									<td>${lsell.changePrint}</td>
									<td>${lsell.sellMarkId}</td>
									<td>${lsell.sellGoods}</td>
									<td>${lsell.sellFormat}</td>
									<td>${lsell.sellUnit}</td>
									<td align="right">${lsell.sellCount}</td>
									<td>${lsell.sellWarehouse}</td>
									<td>${lsell.goodHouseName}</td>
									<td>${lsell.kuwei}</td>
									<td style="background-color: lime">${lsell.goodsStoreWarehouse}</td>
									<td style="background-color: lime">${lsell.goodsStorehouseName}</td>
									<td style="background-color: lime">${lsell.goodsStorePosition}</td>
									<td>${lsell.sellTime}</td>
									<td>${lsell.style}</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="20" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td colspan="20" align="left">
									<font color="red">没有已打印的记录</font>
								</td>
							</tr>
						</s:else>
						<tr>
						<tr>
							<td colspan="20"><input type="submit" value="前往打印" class="input" ></td>
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
function changeUnAll(obj){
	var flag = obj.checked;
	var unSell = $(".unSell");
	if(flag){
		for(var i=0;i<unSell.length;i++){
			unSell[i].checked= true;
		}
	}else{
		for(var i=0;i<unSell.length;i++){
			unSell[i].checked= false;	
		}
	}
}

function changeAll(obj){
	var flag = obj.checked;
	var lSell = $(".lSell");
	if(flag){
		for(var i=0;i<lSell.length;i++){
			lSell[i].checked= true;
		}
	}else{
		for(var i=0;i<lSell.length;i++){
			lSell[i].checked= false;	
		}
	}
}
</script>
	</body>
</html>
