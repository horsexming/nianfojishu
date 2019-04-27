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
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>出库信息管理</h3>
				<br/>
				<form action="outlib_queryOutLibByCondition.action" method="post" name="myForm" onsubmit="return testTime()"> 
				<table border="1">
					<tr>
						<td>使用人：<input type="text" name="voou.person" value="${voou.person}"/></td>
						<td>品名：<input type="text"  name="voou.name" value="${voou.name}"/></td>
						<td>规格：<input type="text" name="voou.standard" value="${voou.standard}"/></td>
					</tr>
					<tr>
						<td>仓库：<input type="text" name="voou.storehouse" value="${voou.storehouse }" style="margin-left: 18px;"/></td>
						<td>类别：<input type="text" name="voou.category" value="${voou.category}"/></td>
						<td>位置：<input type="text" name="voou.place" value="${voou.place}"/></td>
					
					</tr>
					<tr>
						<td>单据号：<input type="text" name="voou.number" value="${voou.number}"/></td>
						<td>开始日：<input style="width: 155px" class="Wdate"
									type="text" name="voou.startTime" value="${voou.startTime}" id="startTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td>结束日：<input style="width: 155px" class="Wdate"
									type="text" name="voou.endTime" value="${voou.endTime}" id="endTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td><input type="hidden" name="errorMessage" value="all"/>
							<input type="submit" value="查询" style="width: 80px; height: 50px;"/>
							<input type="button" value="导出Excel" style="width: 80px; height: 50px;" onclick="exportExcel();todisabledone(this)" data="downData"/>
						</td>
					</tr>
				</table>
				</form>
				<table class="table">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td style="width: 30px;">序号</td>
						<td>单据号</td>
						<td>日期</td>
						<td style="width: 60px;">使用人</td>
						<td>部门</td>
						<td>品名</td>
						<td>规格</td>
						<td>单位</td>
						<td>仓库</td>
						<td>类别</td>
						<td>位置</td>
						<td style="width: 60px;">出库量</td>
						<td>单价</td>
						<td>金额</td>
						<td style="width: 30px;"></td>
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
									<td>${pageList.danjuhao}</td>
									<td>${pageList.date}</td>
									<td>${pageList.peopleName}</td>
									<td>${pageList.dept}</td>
									<td>${pageList.matetag}</td>
									<td>${pageList.format}</td>
									<td>${pageList.unit}</td>
									<td>${pageList.storehouse}</td>
									<td>${pageList.parClass}</td>
									<td>${pageList.place}</td>
									<td>${pageList.num}</td>
									<td>${pageList.price}</td>
									<td>${pageList.money}</td>
									<td>
										<a href="outlib_del.action?voou.id=${pageList.id}" onclick="return del()">删除</a>
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
						document.forms.myForm.action="outlib_export.action";
						document.forms.myForm.submit();
						document.forms.myForm.action="outlib_queryOutLibByCondition.action";
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
