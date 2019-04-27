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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
.ztree li a {
	color: #fff;
}

/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li {
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center" style="margin-top:20px;">
				<form action="LendNectAction!findNectedBycardNum.action" method="post">
					<p>
						<span><font style="font-size: 20px; font-weight: bold">请刷卡：</font>
						</span>
						<input type="text" id="cardNum" name="changeNewNect.cardNum" value="${changeNewNect.cardNum}" />
						<input type="hidden" name="cpage" value="1">
						<input type="submit" value="查询" />
						<span style="color:red;">${errorMessage}</span>
					</p>
				</form>

				<hr>
				
				
			
			
<%--				<s:if test="listAll!=null&&!listAll.isEmpty()">--%>
			<s:if test="changeNewNect!=null">
				
					<form action="LendNectAction!findNectedBycardNum.action" method="post" onsubmit="return testTime()" name="myForm">
				<table class="table" >
					<tr>
						<th>
							件号：
						</th>
						<td>
							<input type="text" name="changeNewNect.goodsMarkId"
								value="${changeNewNect.goodsMarkId}" />
							<input type="hidden" name="changeNewNect.cardNum"
								value="${changeNewNect.cardNum}" />
						</td>
						<th>
							批次：
						</th>
						<td>
							<input type="text" name="changeNewNect.goodsLotId"
								value="${changeNewNect.goodsLotId}" />
						</td>
						<th>
							名称：
						</th>
						<td>
							<input type="text" name="changeNewNect.goodsFullName" value="${changeNewNect.goodsFullName }"/>
						</td>
					</tr>
					
					<tr>
						<th>
							仓区:
						</th>
						<td>
							<select id="goodHouseName" name="changeNewNect.goodHouse"
								style="width: 155px;">
								
							</select>
						</td>
						<th>
							库位:
						</th>
						<td>
							<input type="text" name="changeNewNect.wareHouse"
								value="${changeNewNect.wareHouse}" />
						</td>
						<th>
							领用日期从
						</th>
						<td>
							<input class="Wdate" type="text" name="startDate"
								value="${startDate}" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" id="startTime"/>
								到
							<input class="Wdate" type="text" name="endDate"
								value="${ endDate}" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  id="endTime"/>
						</td>
					</tr>
					
					<tr>
						<th colspan="8">
							<input type="submit" value="查询" class="input" />
							
						</th>
					</tr>
				</table>
			</form>
			
			<s:if test="listAll!=null&&!listAll.isEmpty()">
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								卡号
							</td>
							<td align="center">
								领用人
							</td>
							<td  align="center">
								领用方式
							</td>
							<td align="center">
								部门
							</td>
							<td align="center">
								件号
							</td>
							<td align="center">
								批次
							</td>
							<td align="center">
								物品名称
							</td>
							<td align="center">
								规格
							</td>
							<td align="center">
								库别
							</td>
							<td align="center">
								仓区
							</td>
							<td align="center">
								库位
							</td>
							<td align="center">
								加工件号
							</td>
							<td align="center">
								单位
							</td>
							<td align="center">
								可以旧换新数量
							</td>
							<td align="center">
								领物时间
							</td>
							<td></td>
						</tr>
						<s:iterator value="listAll" id="pageList" status="pageStatus">
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
								${pageList.cardNum}
							</td>
							<td>
								${pageList.peopleName}
							</td>
							<td>
								${pageList.status}
							</td>
							<td>
								${pageList.dept}
							</td>
							<td>
								${pageList.goodsMarkId}
							</td>
								<td>
								${pageList.goodsLotId}
							</td>
							
							<td>
								${pageList.goodsFullName}
							</td>
							<td>
								<s:if test="pageList.format!=null">
									${pageList.format}</s:if>
									<%--<s:else>${pageList.goods.format}</s:else>--%>
							</td>
							<td>
								${pageList.storehouse}	
							</td>
							<td>
								${pageList.goodHouse}	
							</td>
							<td>
								${pageList.wareHouse}	
							</td>
							<td>
								${pageList.processPieceNum}
							</td>
							<td>
								${pageList.unit}
							</td>
							<td>
								${pageList.canChangeNum}
							</td>
							<td>
								${pageList.date}
							</td>
							<td>

<%--									<a--%>
<%--									href="LendNectAction!showOneScrap.action?lend.id=${pageList.id}&lend.cardNum=${lend.cardNum}"--%>
<%--									>以旧换新</a>--%>
									<a
<%--									href="LendNectAction!showOneScrap.action?lend.id=${pageList.id}&lend.cardNum=${lend.cardNum}"--%>
									href="javascript:;" onclick="queryGoodsNect(${pageList.id})"
									>以旧换新</a>
	
							</td>
							</tr>
						</s:iterator>
						<tr>
						<td colspan="30" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total"/>
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
				
				
				
					</s:if>
				</s:if>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script>
		
		function testTime() {
			var startStr = document.getElementById("startTime").value;
			var endStr = document.getElementById("endTime").value;
			if (startStr != "" && endStr != "") {
				var start = startStr.split("-");
				var end = endStr.split("-");
				var startTime = new Date(start[0], start[1], start[2]);
				var endTime = new Date(end[0], end[1], end[2]);
				var myDate = new Date(); //获得当前时间
				myDate.setMonth(myDate.getMonth() + 1);//为当前date的月份+1后重新赋值
				if (startTime <= endTime == false) {
					alert("开始时间不能大于结束时间!请重新选择!谢谢!");
					return false;
				} else if (endTime <= myDate == false) {
					alert("结束时间不能大于当前时间!请重新选择!谢谢!");
					return false;
				}
			}
			return true;
		}
		//获取仓区
		$(function(){
			$.ajax({
				type : "POST",
				url : "WarehouseAreaAction_findwaListByNO.action",
				data : {
					wareHouseName:'综合库'    
				},
				dataType : "json",
				async:false,
				success : function(data) {
					if (data != null) {
						$("#goodHouseName").empty();
						$(data).each(function(){
								$("#goodHouseName").append('<option value='+this.goodHouseName+' >'+this.goodHouseName+'</option>');
						});
					}
		
				}
			});
			duoxuaSelect('goodHouseName');
			$("#textselectgoodHouseName").val('${changeNewNect.goodHouse}');
		});	
		
		//以旧换新
			function queryGoodsNect(nectId){
				$.ajax({
					type : "POST",
					url : "LendNectAction!ajaxQueryGoodsCanChange.action",
					data : {
						nectId:nectId,
					},
					dataType : "json",
					async:false,
					success : function(data) {
						if(data>0){
							window.location.href="LendNectAction!gainOneChangeNew.action?nectId="+nectId;
						}else{
							alert("库存不足，未找到，谢谢合作");
						}
	
					}
			  });
		 }
		</script>
	</body>
</html>
