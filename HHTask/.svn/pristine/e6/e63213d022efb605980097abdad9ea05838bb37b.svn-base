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
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
			<!-- 
					  <form action="nianXiuAction!listnianxiu.action" method="post"  theme="simple">
			     <table class="table">
			   		<tr><th align="center">月份：</th><td><input class="Wdate" type="text" id="yuefen"
									name="kaoQin.yuefen"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"  /></td>
			   			<th align="center">工号：</th><td>
			   			    <input type="text" id="kaoQin.carId" name="kaoQin.carId" />
			   			
			   			<td rowspan="3"><input type="submit" value="查询"   class="input"/>
			   						<input type="button" value="导出" class="input" onclick="daochuExec()" />
			   			</td>
			   		</tr>
			   		<tr>
			   			<th align="center">姓名：</th><td>
			   			    <input type="text"  name="kaoQin.name" id="kaoQin.name" />
			   			</td>
			   			<th align="center">部门：</th><td>
			   				 <select   id='dept'  name="kaoQin.dept" onclick="createDept('dept')" style="width:90px;">
			   			  </select>
			   			</td>
			   		</tr>
			  </table>
			  </form>
			   -->
				<table class="table">
				<tr><th colspan="11" align="center">当前在职-在岗-试用-请假-内退-统筹外（退休)-离职中-离职人员统计</th></tr>
					<tr  bgcolor="#c0dcf2">
						<th>编号</th>
						<th>状态</th>
						<th>人数</th>
						<th>统计时间</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex"></s:iterator>
					<!-- 在职--试用-请假-内退- //实习 统筹外（退休)-离职中-离职 -->
						<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>1</th><th>在职</th><th>${list[0]}</th><th>${list[8]}</th></tr><tr>
							<th>2</th><th>试用</th><th>${list[1]}</th><th>${list[8]}</th></tr><tr>
							<th>3</th><th>请假</th><th>${list[2]}</th><th>${list[8]}</th></tr><tr>
							<th>4</th><th>内退</th><th>${list[3]}</th><th>${list[8]}</th></tr><tr>
							<th>5</th><th>实习</th><th>${list[4]}</th><th>${list[8]}</th></tr><tr>
							<th>6</th><th>统筹外（退休)</th><th>${list[5]}</th><th>${list[8]}</th></tr><tr>
							<th>7</th><th>离职中</th><th>${list[6]}</th><th>${list[8]}</th></tr><tr>
							<th>8</th><th>离职</th><th>${list[7]}</th><th>${list[8]}</th>
						</tr>
					
					<tr>
						<s:if test="errorMessage==null">
							<th colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<th colspan="11" align="center" style="color: red">
						</s:else>
						</th>
					</tr>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	  var time2=document.getElementById("time2").value;
	document.getElementById("yuefen").value= time2;
	function daochuExec(){
		 var yuefen=document.getElementById("yuefen").value;
		//objForm.action="nianXiuAction!exportExcel.action?kaoQin.yuefen="+yuefen;
		//objForm.submit();
		 window.location.href="nianXiuAction!exportExcel.action?kaoQin.yuefen="+yuefen;
	}
	</SCRIPT>

</html>
