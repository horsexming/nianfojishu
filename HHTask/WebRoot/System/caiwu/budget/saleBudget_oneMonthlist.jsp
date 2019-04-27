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
				</div>
			</div>
			
			<div align="center">
				计划月份：${saleBudget.planMonth } 
				<s:if test="%{'update'==tag}">
				<s:if test="%{'同意'!=saleBudget.status}">
					<input type="button" onclick="add()" value="添加产品" class="input" />
					<span style="width: 300px;"> <input type="button"
							onclick="sub()" value="提交预算" class="input" />
							</span>
					</s:if>
				</s:if>
				<form action="saleBudgetAction!findOneBudget.action" method="post">
					<input type="hidden" id="budgetID" name="id" value="${id}" />
<%--					<input type="text" id="tag"  value="update" />--%>
<%--					<input type="text" id="id"   value="${saleBudget.id}" />--%>
					<table class="table">
						<tr>
							<th>
								件号
							</th>
							<th>
								<select name="sbdetail.markID" style="width: 150px;"
									id="detailMarkId"
									onMouseOver="createDept('detailMarkId','saleBudgetAction!selectStyle.action?tag=detailMarkId')">
									<option value="">
										选择件号
									</option>
									<option value="${sbdetail.markID}">
										${sbdetail.markID}
									</option>


								</select>
							</th>

							<th>
								品名
							</th>
							<th>
								<input type="text" name="sbdetail.goodsName"
									value="${sbdetail.goodsName}" />
							</th>
							<th>
								型别
							</th>
							<th>
								<input type="text" name="sbdetail.customer"
									value="${sbdetail.customer}" />
							</th>
							<th>
								<input type="submit" style="width: 90px; height: 30px;"
									value="查询" />
							</th>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							品名
						</th>
						<th align="center">
							型别
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							预算金额
						</th>
						<th align="center">
							是否计入预算
						</th>
						<th align="center">
							操作 
						</th>
					</tr>

					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="sbd">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${sbd.markID}
							</td>
							<td>
								${sbd.goodsName}
							</td>
							<td>
								${sbd.customer} 
							</td>
							<td>
								<div id="div${sbd.id}">
									${sbd.forecastCount}
								</div>
								<div id="div2${sbd.id}" style="display: none;">
									<input type="text" id="${sbd.id}" value="${sbd.forecastCount}"
										style="width: 65px" />
									<input type="button" value="确定"
										onclick="submitform('${sbd.id}')" />
									<input type="button" value="取消" onclick="hidde('${sbd.id}')" />
								</div>
							</td>
							<td>
								<div id="reven${sbd.id}">
									${sbd.saleReven}
								</div>
							</td>

							<td>
								<s:if test="'no'.equals(#sbd.isIncludBudget)" >否</s:if>
								<s:else>是</s:else>
							</td>
							<td>
								<s:if test="%{'update'==tag}">
									<s:if test="%{'同意'!=saleBudget.status}">
									<a href="javascript:;" onclick="show('${sbd.id}')">修改</a>&nbsp;&nbsp;&nbsp;
								 <!--  判断状态显示-->
									<a onClick="return confirm('确定要删除该条记录吗？')"
										href="saleBudgetAction!deleteDetailById.action?sbId=${sbd.id}">删除</a>
									</s:if>
								</s:if>

							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="10" align="right">
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
							<td colspan="10" style="font-size: 15px; color: red;">
								对不起，没有查到相关的预算信息
							</td>
						</tr>
					</s:else>
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var id = document.getElementById("budgetID").value;
function add() {
	window.location = "saleBudgetAction!findAllPruduct.action?id=" + id;
}
//提交月度预算汇总
function sub() {
	window.location = "saleBudgetAction!updateBudgetById.action?id=" + id+"&tag=subb";
}
function show(obj) {
	$("#div2" + obj).show();
	$("#div" + obj).hide();
}
function hidde(obj) {
	$("#div2" + obj).hide();
	$("#div" + obj).show();
}
function submitform(id) {
	var forecastCount = $("#" + id).val();
	var price = "${sbd.onePrice}";
	$.ajax( {
		type : "POST",
		url : 'saleBudgetAction!updateDetail.action?sbdetail.id=' + id
				+ "&sbdetail.forecastCount=" + forecastCount,
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(mes) {
			if (mes.success) {
				alert("成功!");
				$("#div" + id).html(forecastCount);
				$("#reven" + id).html(forecastCount * price);
				hidde(id);
			}
		}
	});
}
</script>
	</body>
</html>
