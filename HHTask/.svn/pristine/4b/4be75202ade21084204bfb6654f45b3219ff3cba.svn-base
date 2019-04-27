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
				<h3>出借记录管理</h3>
				<br/>
				<form action="borrow_queryBorrowByCondition.action" method="post" onsubmit="return testTime()" name="myForm"> 
				<table class="table">
					<tr>
						<td>部门：<input type="text" name="vobo.dept" value="${vobo.dept}"/></td>
						<td>卡号：<input type="text" name="vobo.cardId" value="${vobo.cardId}"/></td>
						<td>姓名：<input type="text" name="vobo.person" value="${vobo.person}"/></td>
					</tr>
					<tr>
						<td>物品：<input type="text" name="vobo.name" value="${vobo.name }"/></td>
						<td>规格：<input type="text" name="vobo.standard" value="${vobo.standard}"/></td>
						<td>编号：<input type="text" name="vobo.number" value="${vobo.number}"/></td>
					
					</tr>
					<tr>
						<td>加工件号：<input type="text" name="vobo.pieceNum" value="${vobo.pieceNum}"/></td>
						<td>仓库：<input type="text" name="vobo.storehouse" value="${vobo.storehouse}"/></td>
						<td></td>
					</tr>
					<tr>
							<td>开始日期：<input style="width: 155px" class="Wdate"
									type="text" name="vobo.startTime" value="${vobo.startTime}" id="startTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
							<td>结束日期：<input style="width: 155px" class="Wdate" id="endTime"
									type="text" name="vobo.endTime" value="${vobo.endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td><input type="hidden" name="errorMessage" value="all"/>
							<input type="submit" value="查询" style="width: 80px; height: 50px;"/>
							<input type="button" value="导出Excel" style="width: 80px; height: 50px;" onclick="exportExcel();todisabledone(this)" data="downData"/>
						</td>
					</tr>
				</table>
				</form>
				<table class="table">
					<tr  bgcolor="#c0dcf2" height="50px" align="center">
						<td>序号</td>
						<td>卡号</td>
						<td >借主</td>
						<td>部门</td>
						<td>编号</td>
						<td>名称</td>
						<td>规格</td>
						<td>加工件号</td>
						<td>单位</td>
						<td >数量</td>
						<td >仓库名</td>
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
									<td>${pageList.number}</td>
									<td>${pageList.matetag}</td>
									<td>${pageList.format}</td>
									<td>${pageList.processPieceNum}</td>
									<td>${pageList.unit}</td>
									<td>${pageList.num}</td>
									<td>${pageList.storehouse}</td>
									<td>${pageList.date}</td>
									<td>
										<a href="borrow_del.action?vobo.id=${pageList.id}" onclick="return del()">删除</a>
									</td>
						</s:iterator>
						</tr>
							<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="15" align="center" style="color: red">
								${errorMessage}
						</s:else>
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
			function exportExcel() {
						document.forms.myForm.action="borrow_export.action";
						document.forms.myForm.submit();
						document.forms.myForm.action="borrow_queryBorrowByCondition.action";
			}
			function del(){
				if(confirm("确定删除吗?")){
					return;
				}else{
					return false;
				}
			}
		</script>
	</body>
</html>
