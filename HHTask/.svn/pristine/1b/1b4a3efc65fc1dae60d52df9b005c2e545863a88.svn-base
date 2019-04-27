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
				<br>
				<br>
				<table class="table">
					<tr>
						<th align="center" colspan="8">
							在线人员统计
						</th>
					</tr>
					<tr bgcolor="#c0dcf2">
						<th>
							编号
						</th>
						<th>
							工号
						</th>
						<th>
							姓名
						</th>
						<th>
							部门
						</th>
						<th>
							登录次数
						</th>
						<th>
							在线总时长(分钟)
						</th>
						<th>
							当天在线时长(分钟)
						</th>
						<th>
							IP
						</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>
								${pageIndex.index+1}
							</th>
							<th>
								${zhUser1.code}
							</th>
							<th>
								${zhUser1.name}
							</th>
							<th>
								${zhUser1.dept}
							</th>
							<th>
								${zhUser1.loginCount}
							</th>
							<th>
								${zhUser1.whenOnlineLong}
							</th>
							<th>
								${zhUser1.whenOnlineLongOfDay}
							</th>
							<th>
								${zhUser1.loginIp}
							</th>
						</tr>
					</s:iterator>
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
