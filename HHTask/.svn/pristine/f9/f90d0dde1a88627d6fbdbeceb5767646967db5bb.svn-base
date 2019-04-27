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
		<STYLE type="text/css">
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

.chartOptionsFlowTrend li { /* float:left; */
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
		</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			
			<form action="ProcessCollectAction_showpcList.action" method="post"  >
			<s:if test="status !='person'">
				<table class="table">
					<tr>
						<th>业务件号</th>
						<td>
							<input type="text" name="pc.ywmarkId" value="${pc.ywmarkId}"/>
						</td>
						<th>工号</th>
						<td>
							<input type="text" name="pc.usercodes" value="${pc.usercodes}" />
						</td>
						<th>姓名</th>
						<td>
							<input type="text" name="pc.usernames" value="${pc.usernames}"  />
						</td>
						<th>件号</th>
						<td>
							<input type="text" name="pc.markId" value="${pc.markId}"/>
						</td>
						<th>批次</th>
						<td>
							<input type="text" name="pc.selfCard" value="${pc.selfCard}"/>
						</td>
						<td rowspan="2">
							<input type="hidden" value="${status}" name="status" id="status">
							<input type="submit" value="查询 " class="input"/>
							<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData" class="input"/>
							<input type="button" value="汇总导出" onclick="exportExcel(this.form,'hz');todisabledone(this)" data="downData" class="input"/>
						</td>
					</tr>
					<tr>
						<th>内部订单号</th>
						<td>
							<input type="text" name="pc.orderNumber" value="${pc.orderNumber}"/>
						</td>
						<th>工序号</th>
						<td>
							<input type="text" name="pc.processNO" value="${pc.processNO}"/>
						</td>
						<th>工序名</th>
						<td>
							<select  name="pc.processName" id="processName">
								<option></option>
							</select>
						</td>
						<th>开始时间</th>
						<td>
							<input type="text" name="startDate" value="${pc.firstApplyDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" class="Wdate"/>
						</td>
						<th>结束时间</th>
						<td>
							<input type="text" name="endDate" value="${pc.sumitApplyDate}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" class="Wdate"/>
						</td>
					</tr>
					<tr>
						<th>车间</th>
						<td>
						<input type="hidden" value="${workShopName}" name="workShopName" id="workShopName">
							<select name="workShop" id="workShop">
							<s:if test= "workShop!=null&&workShop!=''">
							
								<option value="${workShop}">
										${workShop}
								</option>
								</s:if>
								<option value="">
								</option>
							</select>
						</td>
						<th>生产类型</th>
						<td>
						<select name="productStyle" id="productStyle">
							<s:if test= "productStyle!=null&&productStyle!=''">
								<option value="${productStyle}">
										${productStyle}
								</option>
								</s:if>
								<option value="">
										
								</option>
								<option value="试制">
									试制
								</option>
								<option value="批产">
									批产
								</option>
						</td>
						<th>版本号</th>
						<td>
							<input type="text" name="pc.banBenNumber" value="${pc.banBenNumber}"
						</td>
					</tr>
				</table>
			</s:if>
			<s:else>
				<table>
					<tr>
						<th>件号</th>
						<td>
							<input type="text" name="pc.markId" value="${pc.markId}"/>
						</td>
						<th>批次</th>
						<td>
							<input type="text" name="pc.selfCard" value="${pc.selfCard}"/>
						</td>
						<th>工序号</th>
						<td>
							<input type="text" name="pc.processNO" value="${pc.processNO}"/>
						</td>
						<td rowspan="3">
							<input type="hidden" name="pc.usercodes" value="${pc.usercodes}"/>
							<input type="hidden" name="pc.usernames" value="${pc.usernames}"/>
							<input type="submit" value="查询 " class="input"/>
							<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData" class="input"/>
							<input type="button" value="汇总导出" onclick="exportExcel(this.form,'hz');todisabledone(this)" data="downData" class="input"/>
						</td>
					</tr>
					<tr>
						
						<th>工序名</th>
						<td>
							<input type="text" name="pc.processName" value="${pc.processName}"/>
						</td>
						<th>开始时间</th>
						<td>
							<input type="text" name="startDate" value="${startDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" class="Wdate"/>
						</td>
						<th>结束时间</th>
						<td>
							<input type="text" name="endDate" value="${endDate}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" class="Wdate"/>
						</td>
					</tr>
					<tr>
						<th>车间</th>
						<td>
							<select name="workShop" id="workShop">
							<option value="">
								</option>
								<s:if test= "workShop!=null&&workShop!=''">
								<option value="${workShop}">
										${workShop}
								</option>
								</s:if>
							</select>
						</td>
						<th></th>
						<td>
						</td>
						<th></th>
						<td>
						</td>
					</tr>
				</table>
			</s:else>
			</form>
		
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th>
								工号
							</th>
							<th>
								姓名
							</th>
							<th>
								业务件号
							</th>
							<th>
								内部订单号
							</th>
							<th>
								件号
							</th>
							<th>
								产品名称
							</th>
							<th>
								版本号
							</th>
							<th>
								批次号
							</th>
							<th>
								生产类型
							</th>
							<th>
								工序号
							</th>
							<th>
								工序名
							</th>
							<th>
								订单数量
							</th>
							<th>
								需求数量
								<hr>
								${needCount}
							</th>
							<th>
								完成数量
								<hr>
								${finishCount}
							</th>
							<th>
								工序次数
								<hr>
								<fmt:formatNumber type="number" value="${spotWeldingCount}"
									pattern="0.0000" maxFractionDigits="4" /></td>
							</th>
							<th>
								未完成数量
								<hr>
								${hascount} 
							<th>
								工位
							</th>
							<th>
								设备编号
							</th>
							<th>
								是否并行
							</th>
							<th>
								投产日期
							</th>
							<th>
								计划日期
							</th>
							<th>
								领取时间
							</th>
							<th>
								提交时间
							</th>
						</tr>
						<s:iterator id="pagelist" value="pcList"
							status="pagestatus">
							<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pagestatus.index+1" />
							</td>
							<td>${pagelist.usercodes}</td>
							<td>${pagelist.usernames}</td>
							<td>${pagelist.ywmarkId}</td>
							<td>${pagelist.orderNumber}</td>
							<td>${pagelist.markId}</td>
							<td style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${pagelist.proName}</font>
									<ul class="qs_ul">
										<li>
											${pagelist.proName}
										</li>
							</ul>
							</td>
							<td>${pagelist.banBenNumber}</td>
							<td>${pagelist.selfCard}</td>
							<td>${pagelist.productStyle}</td>
							<td>${pagelist.processNO}</td>
							<td>${pagelist.processName}</td>
							<td>${pagelist.orderNum}</td>
							<td>${pagelist.finalCount}</td>
							<td>${pagelist.tjNumber}</td>
							<td>
							<fmt:formatNumber type="number" value="${pagelist.procesdianshu*pagelist.tjNumber}"
									pattern="0.0000" maxFractionDigits="4" /></td>
							<td>${pagelist.finalCount-pagelist.tjNumber}</td>
							<td>${pagelist.gongwei}</td>
							<td>${pagelist.shebeiNo}</td>
							<td>${pagelist.processStatus}</td>
							<td>${pagelist.mrpjihuoDate}</td>
							<td>${pagelist.needFinalDate}</td>
							<td>${pagelist.firstApplyDate}</td>
							<td>${pagelist.sumitApplyDate}</td>
						</s:iterator>
						<tr >
				<td colspan="25" align="right">
								第
					<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
							页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
									
				</td>
			</tr>
					</table>
			
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
$(function() {
	$.ajax( {
		type : "post",
		url : "ProcessCollectAction_findall.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this[1] + "'>"
										+ this[1] + "</option>")
								.appendTo("#workShop");
					});

		}
	});
	$.ajax( {
		type : "post",
		url : "ProcessCollectAction_findAllProcessGzstore.action",
		dataType : "json",
		async : false,
		success : function(data) {
			$("#processName").empty();
			$(data).each(
					function() {
						$(
								"<option value='" + this + "'>"
										+ this + "</option>")
								.appendTo("#processName");
					});
			duoxuaSelect("processName",'${pc.processName}');
		}
	});
	
});
 function exportExcel(objForm,status) {
	document.getElementById("status").name="";
	// objForm.action = "ProcessCollectAction_exportEXCEL.action?status="+status;
     objForm.action = "ProcessCollectAction_exportEXCELbyPoi.action?status="+status;
	objForm.submit();
	objForm.action = "ProcessCollectAction_showpcList.action";
	document.getElementById("status").name="status";
}

</script>

	</body>
</html>
