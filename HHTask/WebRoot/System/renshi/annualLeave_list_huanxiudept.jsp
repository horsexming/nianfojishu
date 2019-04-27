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
					<form action="annualLeave!listhuanxiudept.action"  method="post"  theme="simple">
				<table class="table">
					<tr>
						<td>工号：<input type="text" name="al.jobNum"/></td>
						<td>姓名：<input type="text" name="al.name"/></td>
						<td rowspan="2" align="center">
						  <input type="submit" value="查询" style="width: 80px; height: 50px;"/>
						  <input type="button" value="清空换休时间"  onclick="qinkong();" style="width: 80px; height: 50px;"/>
						  <!-- <input type="button" value="添加人员" onclick="add()" style="width: 80px; height: 50px;"/>
						  <input type="button" value="年休调整" onclick="addAnnualLeave()"  style="width: 80px; height: 50px;"/> -->
						</td>
						</tr></tr>
						<td>部门：<select   id='dept'  name="al.dept" onclick="createDept('dept')" style="width:90px;">
			   			  </select>
						</td>
						<td>工龄：
						   <input type="text" name="al.lengthOfService"/>
						</td>
						
					</tr>
				</table>
				</form>
				
				
				<table class="table">
					<tr   bgcolor="#c0dcf2">
						<td align="center">序号</td>
						<td align="center">工号</td>
						<td align="center">姓名</td>
						<td align="center">部门</td>
						
						<td align="center">累计可用换休时间/天</td>
						<td align="center">状态</td>
						<td align="center">备注</td>
						<td align="center">操作</td>
						
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
							<tr  onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
					
						<td>
							<s:property value="#pageStatus.index+1" />
							
						</td>
							<td>${pageList.jobNum}</td>
							<td>${pageList.name}</td>
							<td>${pageList.dept}</td>
							
							
							<td>${pageList.surplus}</td>
							<td>${pageList.status}</td>
							<td>${pageList.remark}</td>
							<td>
							
							<a href="annualLeave!mingxijiaban.action?al.jobNum=${pageList.jobNum}">加班明细</a>
							
							
							<a href="annualLeave!mingxihuanxiu.action?al.jobNum=${pageList.jobNum}">换休明细</a>
							</td>
							
						
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
		function qinkong(){
			var gnl=confirm("确定要清空?");
			if (gnl==true){
		  //return true;
		  alert("true");
		  /*	var url=encodeURI(encodeURI("annualLeave!qinkongdept.action"));
				$("#showProcess").attr("src", url);	
				chageDiv('block');*/
				window.location.href="annualLeave!qinkongdept.action";
		}else{
		  return false;
		}
		
	}
	</SCRIPT>

</html>
