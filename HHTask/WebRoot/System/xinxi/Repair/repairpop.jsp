<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String deptname = (String) request.getSession().getAttribute(
			"deptname");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv" 
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在对修理人员进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
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
					<a href="" style="color: #ffffff" >刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					报修信息
				</h3>
				<form action="RepairAction!findAllpop.action?&t=${test}" method="post">
					<table class="table">
						<tr>
							<td align="center">
								姓名：
								<input type="text" name="responsibilities.repairname" />
							</td>
							<td align="center">
								类别：
								<input type="text" name="responsibilities.repaircategory" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
							</td>
							<td align="center">
								<input type="button" style="width: 100px; height: 40px;"
									value="添加" class="input" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							类别
						</td>
						<td align="center">
							职责
						</td>
						<!--					<td align="center"> 手机号</td>-->
						<!--			       <td align="center">邮箱</td> -->
						<td align="center">
							操作
						</td>
						<td></td>
					</tr>
					<s:iterator value="maps" id="pageList" status="pageStatus">
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
							${pageList.repairname }
						</td>
						<td>
							${pageList.repairdepartment}
						</td>
						<td>
							${pageList.repaircategory }
						</td>
						<td>
							${pageList.repairresponsibilitiesl}
						</td>
						<!--					<td>${pageList.employeenumber }</td>-->
						<!--					   <td>${pageList.phone }</td>-->
						<!-- 				    	<td>${pageList.mailbox}</td>  -->
						<td>
							<input id="dept_name" type="hidden" value="${pageList.repairdepartment }">
							<a href="RepairAction!salRepairpop.action?del_id=${pageList.id }&t=${test}">修改/</a>
							<%--					<a href="GzstoreAction_initUpdate.action?id=${pageList.id }">修改</a>/--%>
							<a href="RepairAction!delRepairpop.action?del_id=${pageList.id }&t=${test}">删除</a>
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
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

function add() {
	var dept1 = "${deptname}";
	//		var dept_name = $("#dept_name").val();
	var test = "${test}";
		if(test==0){
			var url=encodeURI(encodeURI("${pageContext.request.contextPath}/System/xinxi/Repair/addrepairpop.jsp?test=a&dept_name="+dept1));
		}else{
			var url=encodeURI(encodeURI("${pageContext.request.contextPath}/System/xinxi/Repair/addrepairpop.jsp?dept_name="+dept1));
		}
		
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
</script>
	</body>
</html>
