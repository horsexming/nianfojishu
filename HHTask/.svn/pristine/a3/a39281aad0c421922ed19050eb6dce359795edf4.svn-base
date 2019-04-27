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
		<div id="gongneng">
		</div>

		<div align="center">
			<h2>
				成品出库
			</h2>
			<form action="goodsAction!findGoodsPhoen.action?tag=${tag}" method="post">
				<table class="table" style="width: 95%;">
					<tr>
						<th>
							批次:
						</th>
						<td>
							<input type="text" name="goods.goodsLotId"
								value="${goods.goodsLotId }" />
						</td>
						<th>
							件号:
						</th>
						<td>
							<input type="text" name="goods.goodsMarkId"
								value="${goods.goodsMarkId }" />
						</td>
						<th>
							库位:
						</th>
						<td>
							<input type="text" name="goods.goodsPosition"
								value="${goods.goodsPosition}" />
						</td>
					</tr>
					<tr>
						<th>
							<input type="submit" value="查找"
								style="width: 50px; height: 30px；; margin-top: 5px;" />
						</th>
					</tr>
				</table>
			</form>
			<table class="table" style="width: 95%;">
				<tr bgcolor="#c0dcf2" height="30px"
					style="border-collapse: separate;">
					<th align="center">
						序号
					</th>
					<th align="center">
						批次
					</th>
					<th align="center">
						件号
					</th>
					<th align="center">
						数量
					</th>
					<th align="center">
						单位
					</th>
					<th align="center">
						库位
					</th>
					<th align="center">
						操作
					</th>
				</tr>
				<s:if test="{list.size()>0}">
					<s:iterator value="list" status="see" id="gs">
						<s:if test="#see.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#see.index+1" />
						</td>
						<td>
							${gs.goodsLotId}
						</td>
						<td>
							${gs.goodsMarkId}
						</td>
						<td>
							${gs.goodsCurQuantity}
						</td>
						<td>
							${gs.goodsUnit}
						</td>
						<td>
							${gs.goodsPosition}
						</td>
						<td>
								<a href="goodsAction!getOneGoods.action?id=${goodsId}&tag=out">出库</a>
							<s:if test="true==#gs.bedit">
								<a
									href="goodsAction!getOneGoods.action?id=${goodsId}&tag=update">修改</a>
							</s:if>
							<s:if test="#gs.kuweiId!=null">
								<input type="button" id = "open<s:property value="#see.index+1" />" value="开库" 
								 onclick="openDoor('${gs.kuweiId}','<s:property value="#see.index+1" />')"/>
								<input type="button" id = "close<s:property value="#see.index+1" />" value="关闭" style="display: none;"
								onclick="closeDoor('${gs.kuweiId}','<s:property value="#see.index+1" />')"/>
							</s:if>
<%--							<a href="goodsAction!deletegs.action?goods.goodsId=${gs.goodsId}"--%>
<%--								onclick="return confirm('本次操作将会删除相关所有记录,确认执行删除操作?');">删除</a>--%>
<%--							<s:if test="#gs.fcApplyStatus!=null&&#gs.fcApplyStatus!=''">--%>
<%--							<a href="CircuitRunAction_findAduitPage.action?id=${gs.epId}">审批动态</a>--%>
<%--							</s:if>--%>
<%--							<s:if test="#gs.fcStatus=='封存'">--%>
<%--							 <s:if test="#gs.fcApplyStatus!='审批中'">--%>
<%--							  <a href="javaScript:;if(window.confirm('您将申请解封此库存?')){window.location.href ='goodsAction!goodsApplyFcStatus.action?id=${goodsId}'};">申请解封</a>--%>
<%--							 </s:if>--%>
<%--							</s:if>--%>
<%--							<s:else>--%>
<%--							<s:if test="#gs.fcApplyStatus!='审批中'">--%>
<%--							<a href="javaScript:;if(window.confirm('您将申请封存此库存?')){window.location.href ='goodsAction!goodsApplyFcStatus.action?id=${goodsId}'};">申请封存</a>--%>
<%--							 </s:if>--%>
<%--							</s:else>--%>
						</td>
					</s:iterator>
					<tr>
						<td colspan="7" align="right">
							共
							<s:property value="total" />
							页 第
							<s:property value="cpage" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="7" style="font-size: 15px; color: red;">
							对不起，没有查到相关的库存信息
						</td>
					</tr>
				</s:else>
			</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function exportExcel(objForm) {
	objForm.action = "goodsAction!exportEXCEL.action?tag=goodsDetail";
	objForm.submit();
	objForm.action = "goodsAction!findGoods.action";
	
}

function openDoor(id,it){
	$.ajax({
		url : "WarehouseAreaAction_OpenWNById_1.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					var ui = document.getElementById("close"+it);
     				ui.style.display="block";
					var ui = document.getElementById("open"+it);
     				ui.style.display="none";
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function closeDoor(id,it){
	$.ajax({
		url : "WarehouseAreaAction_ColseWNById_1.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					var ui = document.getElementById("close"+it);
     				ui.style.display="none";
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
</script>
	</body>
</html>
