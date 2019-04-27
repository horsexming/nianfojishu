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
			<div align="center">
				<h2>
					现场半成品快速入库
				</h2>
				<form action="GoodsStoreAction!tobcpquickreceiver.action"
					method="post">
					<table class="table" style="width: 95%;">
						<tr>
							<th align="right">
								批次:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreLot"
									value="${goodsStore.goodsStoreLot }" />
							</td>
							<th align="right">
								件号:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreMarkId"
									value="${goodsStore.goodsStoreMarkId }" />
							</td>
							<th align="right">
								名称:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreGoodsName"
									value="${goodsStore.goodsStoreGoodsName }" />
							</td>
							<th rowspan="4">
								<input type="submit" value="查找"
									style="width: 80px; height: 100px; margin-top: 5px;" />
								&nbsp;

							</th>
						</tr>
						<tr>
							<th align="right">
								客户:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreCompanyName"
									value="${goodsStore.goodsStoreCompanyName }" />
							</td>
							<th align="right">
								负责人:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStorePerson"
									value="${goodsStore.goodsStorePerson}" />
							</td>
							<th align="right">
								工艺卡号:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreArtsCard"
									value="${goodsStore.goodsStoreArtsCard }" />
							</td>
						</tr>
						<tr>
							<th align="right">
								订单：
							</th>
							<td>
								<input type="text" name="goodsStore.neiorderId"
									value="${goodsStore.neiorderId}" />
							</td>
							<th align="right">
								日期从
							</th>
							<td>
								<input class="Wdate" type="text" name="startDate"
									value="${ startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								到
							</th>
							<td>
								<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								业务件号 
							</th>
							<td>
								<input type="text" name="goodsStore.ywmarkId"
									value="${goodsStore.ywmarkId}" />
							</td>
							<th align="right">
								库别
							</th>
							<td>
									<SELECT name="goodsStore.goodsStoreWarehouse"  onclick="changekb(1)" id="kbselect">
									<s:if test="goodsStore.goodsStoreWarehouse!=null">
										<option value="${goodsStore.goodsStoreWarehouse}">${goodsStore.goodsStoreWarehouse}</option>
									</s:if>
									<option></option>
									<option value="半成品库">半成品库</option>
									<option value="委外库">委外库</option>
									</SELECT>
							</td>
							<th align="right">
								仓区
							</th>
							<td>
									<SELECT name="goodsStore.goodHouseName" id="cqselect" >
									<s:if test="goodsStore.goodHouseName!=null">
										<option value="${goodsStore.goodHouseName}">${goodsStore.goodHouseName}</option>
									</s:if>
									<option></option>
									</SELECT>
							</td>
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
							订单号
						</th>
						<th align="center">
							业务件号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							批次
						</th>
						<th align="center" style="width: 30px;">
							版本
						</th>
						<th align="center">
							工序名
						</th>
						<th align="center">
							品名
						</th>
						<th align="center">
							批次数量
						</th>
						<th align="center">
							入库数量
						</th>
						<th align="center">
							单位
						</th>
						<th>
							库别
						</th>
						<th>
							仓区
						</th>
						<th>
							库位
						</th>
						<th align="center">
							申请时间
						</th>
						<th align="center" width="80px;">
							入库日期
						</th>
						<th align="center">
							申请人
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<tr>
						<th colspan="19" bgcolor="#FFB6C1"
							style="color: red；font-weight :      bold;">
							入库待确认记录
						</th>
					</tr>
					<s:if test="{listIn.size()>0}">
						<s:iterator value="listIn" status="se" id="goodsIn">
							<form id="bcprkForm<s:property value="#se.index" />" method="post">
							<tr align="center" bgcolor="#FFB6C1"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#FFB6C1')">
								<td>
									<input name="goodsStore.goodsStoreId" type="hidden" value="${goodsIn.goodsStoreId}">
									<s:property value="#se.index+1" />
								</td>
								<td>
									${goodsIn.neiorderId}
								</td>
								<td>
									${goodsIn.ywmarkId}
								</td>
								<td>
									${goodsIn.goodsStoreMarkId}
									<br/>
									（<font color="red">${goodsIn.processNo}</font>）
								</td>
								<td>
									${goodsIn.goodsStoreLot}
								</td>
								<td  style="width: 30px;">
									${goodsIn.banBenNumber}
								</td>
								<td>
									${goodsIn.processName}
								</td>
								<td>
									${goodsIn.goodsStoreGoodsName}
								</td>
								<td>
									${goodsIn.beginning_num}
								</td>
								<td>
									<input id="goodsStoreCount<s:property value="#se.index" />"  style="width: 80px;"
									type="text" value="${goodsIn.goodsStoreCount}" name="goodsStore.goodsStoreCount" onkeyup="mustBeNumber('goodsStoreCount<s:property value="#se.index" />')">
								</td>

								<td>
									${goodsIn.goodsStoreUnit}
								</td>
								<td>
									${goodsIn.goodsStoreWarehouse}
								</td>
								<td>
<%--									${goodsIn.goodHouseName}--%>
									<SELECT name="goodsStore.goodHouseName" id="goodHouseName<s:property value="#se.index" />" onchange="getPosition('${goodsIn.goodsStoreWarehouse}',<s:property value="#se.index" />)"
									 onclick="getcq('${goodsIn.goodsStoreWarehouse}',<s:property value="#se.index" />)"></SELECT>
								</td>
								<td>
<%--									<input type="text" name="goodsStore.goodHouseName">--%>
								<select id="goodsStorePosition<s:property value="#se.index" />" name="goodsStore.goodsStorePosition"></select>
								</td>
								<td>
									${goodsIn.applyTime}
								</td>
								<td>
									<input id="goodsStoreDate<s:property value="#se.index" />" class="Wdate" type="text"
									name="goodsStore.goodsStoreDate"
									value="${goodsIn.goodsStoreDate}" size="10"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /> 
								</td>
								<td>
									<input name="goodsStore.goodsStorePlanner" value="${goodsIn.goodsStorePlanner}" style="width: 80px;"/>
								</td>
								<td>
									${goodsIn.status}
								</td>
								<td>
<%--									<a--%>
<%--										href="GoodsStoreAction!getOneGoodsStore.action?id=${goodsStoreId}&tag=ruku">确认</a>&nbsp;--%>
										<input value="确认" type="button" id="bcprkBtn<s:property value="#se.index" />" onclick="baprkqr(<s:property value="#se.index" />)">
								</td>

							</tr>
							</form>
						</s:iterator>
						<tr>
							<td colspan="18" align="right">
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
							<td style="font-size: 15px; color: red;">
								对不起，没有查到相关的待入库信息(Sorry, no storage-related information found)
							</td>
						</tr>
					</s:else>
<%--					<tr>--%>
<%--						<th colspan="18" bgcolor="#9BCD9B">--%>
<%--							入库历史记录(Warehousing History)--%>
<%--						</th>--%>
<%--					</tr>--%>
<%--					<s:if test="{list.size()>0}">--%>
<%--						<s:iterator value="list" status="see" id="gs">--%>
<%--							<s:if test="#see.index%2==1">--%>
<%--								<tr align="center" bgcolor="#e6f3fb"--%>
<%--									onmouseover="chageBgcolor(this)"--%>
<%--									onmouseout="outBgcolor(this,'#e6f3fb')">--%>
<%--							</s:if>--%>
<%--							<s:else>--%>
<%--								<tr align="center" onmouseover="chageBgcolor(this)"--%>
<%--									onmouseout="outBgcolor(this,'')">--%>
<%--							</s:else>--%>
<%--							<td>--%>
<%--								<s:property value="#see.index+1" />--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodsStoreLot}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.ywmarkId}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodsStoreMarkId}--%>
<%--								<s:if test="#gs.processNo!=null">（<font color="red">${gs.processNo}</font>）</s:if>--%>
<%--							</td>--%>
<%--							<td>--%>
<%--									${gs.banBenNumber}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--									${goodsIn.processName}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodsStoreGoodsName}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--									${gs.beginning_num}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodsStoreCount}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodsStoreUnit}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodsStoreWarehouse}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodHouseName}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodsStorePosition}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.neiorderId}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.applyTime}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${gs.goodsStoreTime}--%>
<%--								</td>--%>
<%--							<td>--%>
<%--								${gs.status}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<a--%>
<%--									href="GoodsStoreAction!getOneGoodsStore.action?id=${goodsStoreId}&tag=update">修改</a>--%>
<%--							</td>--%>
<%----%>
<%--							</tr>--%>
<%--						</s:iterator>--%>
<%--						<tr>--%>
<%--							<td colspan="18" align="right">--%>
<%--								共--%>
<%--								<s:property value="total" />--%>
<%--								页 第--%>
<%--								<s:property value="cpage" />--%>
<%--								页--%>
<%--								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"--%>
<%--									styleClass="page" theme="number" />--%>
<%----%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</s:if>--%>
<%--					<s:else>--%>
<%--						<tr>--%>
<%--							<td style="font-size: 15px; color: red;">--%>
<%--								对不起，没有查到相关的入库信息--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</s:else>--%>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function getcq(ck,lieindex) {
	var cqhtml=$("#goodHouseName"+lieindex).html();
	if (ck != null && ck != ""&&(cqhtml==null||cqhtml=="")) {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwaListByNO.action",
			data : {
				wareHouseName : ck
			},
			dataType : "json",
			success : function(data) {
				if (data != null && data.length > 0) {
					$("#goodHouseName"+lieindex).empty();
					$("#goodHouseName"+lieindex).append("<option value=''></option>");
					$(data).each(
							function() {
								$("#goodHouseName"+lieindex).append("<option value='" + this.goodHouseName
												+ "'>" + this.goodHouseName
												+ "</option>");
							});
<%--					var duoxuanselect = $(".duoxuaselect_div");--%>
<%--					if (duoxuanselect[1] != null) {--%>
<%--						$(duoxuanselect[1]).remove();--%>
<%--					}--%>
<%--					duoxuaSelect("goodHouseName"+lieindex);--%>
				}
			}
		});
	}
}

function getPosition(ck,lieindex){
	var warehouseArea = $("#goodHouseName"+lieindex).val();
	if (ck != "" && ck != null &&warehouseArea != "") {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwnListByNO.action",
			data : {
				wareHouseName : ck,
				cangqu : warehouseArea
			},
			dataType : "json",
			cache:false, 
       		async:false, 
			success : function(data) {
				if (data != null) {
					$("#goodsStorePosition"+lieindex).empty();
					$(data).each(function(){
						$("#goodsStorePosition"+lieindex).append("<option value='"+this.number+"'>"+this.number+"</option>");
					});
<%--					var tinyselect =  $("#goodsStorePosition_td").children(".tinyselect");--%>
<%--				    if (tinyselect[0] != null) {--%>
<%--						document.getElementById("goodsStorePosition_td").removeChild(--%>
<%--								tinyselect[0]);--%>
<%--					}--%>
<%--					$("#goodsStorePosition"+lieindex).tinyselect();--%>
				}

			}
		});
	}
}
 
 
function baprkqr(lieindex){
	$("#bcprkBtn"+lieindex).attr("disabled","disabled");
	$.ajax( {
			type : "POST",
			url : "GoodsStoreAction!bcpquickreceive.action",
			data : $("#bcprkForm"+lieindex).serialize() ,
			dataType : "json",
			cache:false, 
       		async:false, 
			success : function(data) {
				alert(data);
			}
		});
}

function changekb(type){
	var kb = $("#kbselect").val();
	if(kb==null||kb==""){
		$("#cqselect").empty();
	}else{
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwaListByNO.action",
			data : {
				wareHouseName : kb
			},
			dataType : "json",
			success : function(data) {
				if (data != null && data.length > 0) {
					$("#cqselect").empty();
					if(type==0){
						var cq = "${goodsStore.goodHouseName}"
						if(cq!=null&&cq!=""){
							$("#cqselect").append("<option value='"+cq+"'>"+cq+"</option>");
						}
					}
					$("#cqselect").append("<option value=''></option>");
					$(data).each(
							function() {
								$("#cqselect").append("<option value='" + this.goodHouseName
												+ "'>" + this.goodHouseName
												+ "</option>");
							});
				}
			}
		});
	}
	
}
$(document).ready(function() {
	changekb(0);
});

</script>
	</body>
</html>
