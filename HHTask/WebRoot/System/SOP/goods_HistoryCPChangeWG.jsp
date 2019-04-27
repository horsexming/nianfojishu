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
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
			</div>
			
			<div align="center">
				<h3>成品库   入   外购件库   调仓历史记录管理</h3>
				<br/>
				<form action="goodsAction!showAllCPOneChangeWG.action" method="post" onsubmit="return testTime()" name="myForm">
				<table class="table" >
					<tr>
<%--						<th>--%>
<%--							部门：--%>
<%--						</th>--%>
<%--						<td>--%>
<%--							<input type="text" name="lendHistory.dept"--%>
<%--								value="${lendHistory.dept }" />--%>
<%--						</td>--%>
<%--						<th>--%>
<%--							卡号：--%>
<%--						</th>--%>
<%--						<td>--%>
<%--							<input type="text" name="lendHistory.cardNum"--%>
<%--								value="${lendHistory.cardNum }" />--%>
<%--						</td>--%>
<%--						<th>--%>
<%--							姓名：--%>
<%--						</th>--%>
<%--						<td>--%>
<%--							<input type="text" name="lendHistory.peopleName"--%>
<%--								value="${lendHistory.peopleName}" />--%>
<%--						</td>--%>
					</tr>
					<tr>
						<th>
							件号：
						</th>
						<td>
							<input type="text" name="cpChangeWg.goodsMarkId"
								value="${cpChangeWg.goodsMarkId}" />
						</td>
						<th>
							批次：
						</th>
						<td>
							<input type="text" name="cpChangeWg.goodsLotId"
								value="${cpChangeWg.goodsLotId}" />
						</td>
						
						<th>
							品名：
						</th>
						<td>
							<input type="text" name="cpChangeWg.goodsFullName" value="${cpChangeWg.goodsFullName }"/>
						</td>
						
						<th>
							日期从
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
<%--						<th>--%>
<%--							规格：--%>
<%--						</th>--%>
<%--						<td>--%>
<%--							<input type="text" name="lendHistory.format" value="${lendHistory.format}"/>--%>
<%--						</td>--%>
<%--						<th>--%>
<%--							仓区:--%>
<%--						</th>--%>
<%--						<td>--%>
<%--							<select id="goodHouseName" name="lendHistory.goodHouse"--%>
<%--								style="width: 155px;">--%>
<%--								--%>
<%--							</select>--%>
<%--						</td>--%>
<%--						<th>--%>
<%--							库位:--%>
<%--						</th>--%>
<%--						<td>--%>
<%--							<input type="text" name="lendHistory.wareHouse"--%>
<%--								value="${lendHistory.wareHouse}" />--%>
<%--						</td>--%>
						
					</tr>
					<tr>
						<td align="center" colspan="8"><input type="submit" value="查询" class="input" />
							
						<input type="button" value="导出Excel" style="width: 80px; height: 50px;"   onclick="exportExcel(this.form);todisabledone(this)" data="downData">
					</tr>
				</table>
			</form>
			<div  style="font-size: 15px; color: red;">
				
				<s:if test="errorMessage=='true'">
					<s:if test="tag=='del'">
						删除成功
					</s:if>
					<s:else>
						申请审批成功，待审批
					</s:else>
					
				</s:if>
				<s:elseif test="errorMessage=='false'">
					申请审批失败
				</s:elseif>
			</div>	
			<form action="LendNectAction!exportLendHis.action"	 method="post" onsubmit="return checkPrint()" name="myForm1">	
				<table class="table">
					<tr  bgcolor="#c0dcf2" height="50px" align="center">
						<td>序号</td>
						<td>件号</td>
						<td >批次</td>
						<td>品名</td>
						<td>规格</td>
						<td>单位</td>
						<td >申请调仓数量</td>
						<td >实际调仓数量</td>
						<td>申请时间</td>
						<td>调仓人姓名</td>
						<td>审批状态</td>
						<td>操作</td>
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
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						
						
						
									<td>${pageList.goodsMarkId}</td>
									<td>${pageList.goodsLotId}</td>
									<td>${pageList.goodsFullName}</td>
									<td>${pageList.goodsFormat}</td>
									<td>${pageList.goodsUnit}</td>
									<td>${pageList.changeCount}</td>
									<td>${pageList.actualChangeCount}</td>
									<td>${pageList.changeDate}</td>
									<td>${pageList.changePeoleName}</td>
									<td>${pageList.ep_status}</td>
									<td>
									
									<a href="goodsAction!deleteOneChange.action?cpChangeWg.id=${pageList.id}" onclick="return confirm('确认删除此条记录吗') ">删除</a>
									</td>
<%--									<td>--%>
<%--										<s:if test='#pageList.ep_status=="待审批"  '>--%>
<%--											<a href="goodsAction!changeEP.action?cpChangeWg.id=${pageList.id}" onclick="return confirm('是否确认审批')">审批</a>--%>
<%--										</s:if>--%>
										
<%--									</td>--%>
						</s:iterator>
						</tr>
						<tr>
							<td colspan="6"></td>
							<th align="center">${sumcount}</th>
							<td colspan="9"></td>
						</tr>
						<tr>
<%--						<s:if test="errorMessage==null">--%>
							<td colspan="17" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
					</tr>
<%--		onclick="exportBtn()"			<tr>--%>
<%--							<td colspan="17">--%>
								
<%--								<input type="submit" value="导出Excel" style="width: 80px; height: 50px;" >--%>
<%--							</td>--%>
<%--					</tr>--%>
				</table>
			</div>
			</form>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
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
			function shenPi(num){
				if(num>0){
					alert("物品未还清，无法删除");
					return false;
				}else{
					if(confirm("确定删除吗?")){
						return;
					}else{
						return false;
					}
				}
				
			}
			
			function exportExcel(obj){
				$(obj).attr("action","LendNectAction!exportLendHis.action");
				$(obj).submit();
				$(obj).attr("action","LendNectAction!queryLendHistory.action");
			}
		</script>
		
		<script>
	$("#quanxuan").click(function(){
				var flag=$(this).prop("checked");
				if(flag){
					$(".qx").prop("checked",true);
				}else{
					$(".qx").prop("checked",false);
				}
	});
	
	function checkPrint(){
		var selectEd=document.getElementsByName("selectedLend");
		for(var i=0;i<selectEd.length;i++){
			if(selectEd[i].checked){
				return true;
			}
		}
		alert("请选择需要打印的记录！谢谢");
		return false;
	}
	
		
		//获取仓区
$(function(){
	$.ajax( {
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
	$("#textselectgoodHouseName").val('${lendHistory.goodHouse}');
			
});
function exportExcel(obj){
				$(obj).attr("action","goodsAction!exportCPChangeWG.action");
				$(obj).submit();
				$(obj).attr("action","goodsAction!showAllCPOneChangeWG.action");
}
		
		
		</script>
	</body>
</html>
