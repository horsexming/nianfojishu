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
				<h3>归还历史记录管理</h3>
				<br/>
		<form action="LendNectAction!queryRepayHistory.action" method="post" onsubmit="return testTime()" name="myForm">
				<table class="table" >
					<tr>
						<th>
							部门：
						</th>
						<td>
							<input type="text" name="repayHistory.dept"
								value="${repayHistory.dept}" />
						</td>
						<th>
							卡号：
						</th>
						<td>
							<input type="text" name="repayHistory.cardNum"
								value="${repayHistory.cardNum }" />
						</td>
						<th>
							姓名：
						</th>
						<td>
							<input type="text" name="repayHistory.peopleName"
								value="${repayHistory.peopleName}" />
						</td>
					</tr>
					<tr>
						<th>
							件号：
						</th>
						<td>
							<input type="text" name="repayHistory.goodsMarkId"
								value="${repayHistory.goodsMarkId}" />
						</td>
						<th>
							批次：
						</th>
						<td>
							<input type="text" name="repayHistory.goodsLotId"
								value="${repayHistory.goodsLotId}" />
						</td>
						<th>
							名称：
						</th>
						<td>
							<input type="text" name="repayHistory.goodsFullName" value="${repayHistory.goodsFullName }"/>
						</td>
<%--						<th>--%>
<%--							规格：--%>
<%--						</th>--%>
<%--						<td>--%>
<%--							<input type="text" name="repayHistory.format" value="${repayHistory.format}"/>--%>
<%--						</td>--%>
						
					</tr>
					<tr>
						
						<th>
							仓区:
						</th>
						<td>
							<select id="goodHouseName" name="repayHistory.goodHouse"
								style="width: 155px;">
								
							</select>
						</td>
						<th>
							库位:
						</th>
						<td>
							<input type="text" name="repayHistory.wareHouse"
								value="${repayHistory.wareHouse}" />
						</td>
						<th>
							借出日期从
						</th>
						<td>
							<input class="Wdate" type="text" name="startDate"
								value="${startDate}" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" id="startTime"/>
								到
							<input class="Wdate" type="text" name="endDate"
								value="${endDate}" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  id="endTime"/>
						</td>
					</tr>
					
					<tr>
						<th colspan="8">
							<input type="submit" value="查询" class="input" />
							<input type="button" value="导出Excel" class="input"  onclick="exportExcel(this.form);todisabledone(this)" data="downData"/>
						</th>
					</tr>
					
				</table>
			</form>
			
			<div style="color: red;font-size: 14px;">
			<s:if test="errorMessage=='true'">
				删除成功
			</s:if>
			<s:elseif test="errorMessage=='false'">
				删除失败
			</s:elseif>
			</div>	
				
			<form action="LendNectAction!exportRepayHis.action"	 method="post" onsubmit="return checkPrint()" name="myForm1">		
				<table class="table">
					<s:if test="{list.size()>0}">
					<tr  bgcolor="#c0dcf2" height="50px" align="center">
<%--						<td><input type="checkbox" name="" id="quanxuan"/>全选</td>--%>
						<td>序号</td>
						<td>卡号</td>
						<td >借主</td>
						<td>部门</td>
						<td>件号</td>
						<td>批次</td>
						<td>名称</td>
						<td>规格</td>
<%--						<td>加工件号</td>--%>
						<td>数量</td>
						<td>单位</td>
						
						<td>仓库名</td>
						<td>仓区名</td>
						<td>库位名</td>
						<td>借出时间</td>
						<td></td>
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
						
<%--						<td><input type="checkbox" name="selectedRepay" class="qx" value="${pageList.id}"/></td>--%>
						
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
									<td>${pageList.cardNum}</td>
									<td>${pageList.peopleName}</td>
									<td>${pageList.dept}</td>
									<td>${pageList.goodsMarkId}</td>
									<td>${pageList.goodsLotId}</td>
									<td>${pageList.goodsFullName}</td>
									<td>${pageList.format}</td>
<%--									<td>${pageList.processPieceNum}</td>--%>
									<td>${pageList.num}</td>
									<td>${pageList.unit}</td>
									
									<td>${pageList.storehouse}</td>
									<td>${pageList.goodHouse}</td>
									<td>${pageList.wareHouse}</td>
									<td>${pageList.ldate}</td>
									<td>
										<a href="LendNectAction!deleteOneRepayHistory.action?repay.id=${pageList.id}" onclick="return del(${pageList.id})">删除</a>
									</td>
						</s:iterator>
					
					
						</tr>
						<tr>
							<td colspan="8"></td>
							<th align="center">${sumcount}</th>
							<td colspan="6"></td>
						</tr>
						<tr>
							<td colspan="16" align="right">
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
						<td colspan="21" style="font-size: 15px; color: red;">
							对不起，没有查到相关的库存信息
						</td>
					</tr>
					
					
					
					
				</s:else>
<%--				<tr>--%>
<%--							<td colspan="17">--%>
<%--								<input type="submit" value="导出Excel" style="width: 80px; height: 50px;" >--%>
<%--							</td>--%>
<%--					</tr>--%>
				</table>
			</div>
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
			function del(obj){
				if(confirm("确定删除吗?")){
					return;
				}else{
					return false;
				}
			}
		function exportExcel(obj){
				$(obj).attr("action","LendNectAction!exportRepayHis.action");
				$(obj).submit();
				$(obj).attr("action","LendNectAction!queryRepayHistory.action");
		}
		$("#quanxuan").click(function(){
			var flag=$(this).prop("checked");
			if(flag){
				$(".qx").prop("checked",true);
			}else{
				$(".qx").prop("checked",false);
			}
		});
	
		function checkPrint(){
			var selectEd=document.getElementsByName("selectedRepay");
			for(var i=0;i<selectEd.length;i++){
				if(selectEd[i].checked){
					return true;
				}
			}
			alert("请选择需要打印的记录！谢谢");
			return false;
		}
		</script>




		<script>
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
			$("#textselectgoodHouseName").val('${repayHistory.goodHouse}');
			
		});
<%--		--%>
<%--		$(function(){--%>
<%--		var errorMessage = "${errorMessage}";--%>
<%--		if(errorMessage!=null && errorMessage.length>0){--%>
<%--			alert(errorMessage);--%>
<%--		}--%>
	});
		</script>
	</body>
</html>
