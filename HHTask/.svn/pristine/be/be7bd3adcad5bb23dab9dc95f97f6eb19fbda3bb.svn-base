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
					<form action="annualLeave!listAnnualLeave.action"  method="post"  theme="simple">
				<table class="table">
					<tr>
						<td>工号：<input type="text" name="al.jobNum"/></td>
						<td>姓名：<input type="text" name="al.name"/></td>
						<td rowspan="2" align="center">
						  <input type="hidden" name="errorMessage" value="all"/>
						  <input type="hidden" name=pagestatus value="${pagestatus}"/>
						  <input type="submit" value="查询" style="width: 80px; height: 50px;"/>
						  <input type="button" value="添加人员" onclick="add()" style="width: 80px; height: 50px;"/>
						  <input type="button" value="年休调整" onclick="addAnnualLeave()"  style="width: 80px; height: 50px;"/>
						  <input type="button" value="导出信息" onclick="daochu();todisabledone(this)" data="downData"  style="width: 80px; height: 50px;"/>
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
						<td align="center">工龄</td>
						
						<td align="center">当年应获得年休</td>
						<td align="center">累计可用年休</td>
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
							<td>${pageList.lengthOfService}</td>
							<td>${pageList.standardAnnualLeave}</td>
							<td>${pageList.surplus}</td>
							<td>${pageList.status}</td>
							<td>${pageList.remark}</td>
							<td><input type="button" value="修改" onclick="update(${pageList.id})"/>
							<input type="button" value="删除" onclick="del(${pageList.id})" >
							<a href="annualLeave!mingxi.action?al.jobNum=${pageList.jobNum}">明细</a>
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
 			function add(){
				window.location="System/renshi/annualLeave_initAdd.action"
			}
			function addAnnualLeave(){
			 // boolean bool =window.confirm('确定进行年休调整?');
			   var result = confirm("确定进行年休调整?");
			  if(result==true){
			      window.location="annualLeave_BatchAnnualLeave.action";
			  }
			}
			function update(id){
				window.location="annualLeave_initUpdate.action?id="+id;
			}
			function del(id){
				window.location="annualLeave_del.action?id="+id;
			}
			function daochu(){
				window.location="annualLeave_daochu.action?tag=nian";
			}
	</SCRIPT>

</html>
