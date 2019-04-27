<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<body bgcolor="#ffffff" onload="createDept('dept');">
		<%@include file="/util/sonTop.jsp"%>
		<div>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看员工卡号二维码</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
			<div align="center">
				<form id="testFrom" action="UsersAction!findAllUsers.action"
					method="post" style="margin: 0px; padding: 0px;">
					<br>
					<table class="table">
						<tr>
							<td align="right">
								工号:
								<br />
								Job number:
							</td>
							<td>
								<input type="text" name="user.code" value="${user.code}" />
							</td>
							<td align="right">
								卡号:
								<br />
								Card number:
							</td>
							<td>
								<input type="text" name="user.cardId" value="${user.cardId }" />
							</td>
							<td align="right">
								姓名:
								<br />
								Name:
							</td>
							<td>
								<input type="text" name="user.name" value="${user.name}" />
							</td>
							<td align="right">
								入职流程:
								<br />
								Entry Process:
							</td>
							<td>
								<select name="user.password.userStatus" style="">
									<option value="${user.password.userStatus}">
										${user.password.userStatus}
									</option>
									<option value=""></option>
									<option value="上传简历">
										上传简历
									</option>
									<option value="签订合同">
										签订合同
									</option>
									<option value="薪资处理">
										薪资处理
									</option>
									<option value="完成">
										完成
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								部门:
								<br />
								Department:
							</td>
							<td>
								<select name="user.dept" id="dept">
									<option value="${user.dept}">
										${user.dept}
									</option>
									<option></option>
								</select>
							</td>
							<td align="right">
								职务:
								<br />
								Title:
							</td>
							<td>
								<input type="text" name="user.duty" value="${user.joined}" />
							</td>
							<td align="right">
								入职时间:
								<br />
								Entry time:
							</td>
							<td>
								<input type="text" name="user.joined" value="${user.joined}" />
							</td>
							<td align="right">
								状态:
								<br />
								Status:
							</td>
							<td>
								<select name="user.onWork" style="width: 80px;">
									<option value=""></option>
									<option value="实习">
										实习
									</option>
									<option value="试用">
										试用
									</option>
									<option value="在职">
										在职
									</option>
									<option value="离职中">
										离职中
									</option>
									<option value="离职">
										离职
									</option>
									<option value="退休">
										退休
									</option>
									<option value="内退">
										内退
									</option>
								</select>
							</td>

						</tr>
						<tr>
							<td align="right">
								显示外部:
								<br />
								show External:
							</td>
							<td>
								<s:if test='external!=null&&external=="是"'>
									<input type="radio" name="external" checked="checked" value="是" />是
								<input type="radio" name="external" value="否" />否
							   </s:if>
								<s:else>
									<input type="radio" name="external" value="是" />是
								<input type="radio" name="external" checked="checked" value="否" />否
							   </s:else>
							</td>
							<td align="right">
								籍贯:
								<br/>
								Place of origin:
							</td>
							<td>
								<input type="text" name="user.birthplace" value="${user.birthplace}" />
							</td>
							<td align="right">
								住址:
								<br/>
								Address:
							</td>
							<td>
								<input type="text" name="user.password.presentAddress" value="${user.password.presentAddress}" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="8">
							<input type="hidden" value="${tag}" name="tag"/>
								<input type="submit" value="查询(Query)"
									style="width: 100px; height: 50px;" />
								<input type="reset" value="重置(Reset)"
									style="width: 100px; height: 50px;" />
								<input type="button" style="width: 100px; height: 50px;"
									value="导出(Export)" class="input" onclick="daochuExec(1);todisabledone(this)" data="downData" />
							</td>
						</tr>
					</table>
				</form>
		<s:if test="pageStatus == 'lizhi'">
				<form action="UsersAction!daoruLizhi.action" method="post"
							enctype="multipart/form-data">
							选择导入文件:
							<input type="file" name="picture">
							<input type="submit" value="批量导入" id="sub">
					</form>
		</s:if>
				<table class="table" align="center">
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>
							序号
							<br />
							No.
						</td>
						<td>
							工号
							<br />
							Job number
						</td>
						<td>
							卡号
							<br />
							Card number
						</td>
						<td>
							姓名
							<br />
							Name
						</td>
						<td>
							部门
							<br />
							Department
						</td>
						<td>
							部门编号
							<br />
							Department number
						</td>
						<td>
							职位
							<br />
							Jobs
						</td>
						<td>
							学历
							<br />
							Education
						</td>
						<td>
							入职流程
							<br />
							Entry Process
						</td>
						<td>
							状态
							<br />
							State
						</td>
						<td>
							操作
							<br />
							Operation
						</td>
					</tr>

					<s:iterator id="users" value="tryDaysEndList" status="ststusUser">
						<s:if test="#ststusUser.first">
							<tr bgcolor="red">
								<th colspan="11" align="center">
									<font color="#ffffff"> 试用期将到期员工信息<br /> Trial period
										will expire employee information</font>
								</th>
							</tr>
						</s:if>
						<s:if test="#ststusUser.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this,'red')"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this,'red')"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ststusUser.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="red">
							</s:else>
							<s:property value="#ststusUser.index+1" />
							</font>
						</td>
						<td>
							${users.code}
						</td>
						<td>
							${users.cardId}
						</td>
						<td>
							<a target="_blank" title="到期时间:${users.tryDays}"
								href="UsersAction!findUserByIdForDetails.action?id=${users.id}&tag=${tag}">
								${users.name}</a>
						</td>
						<td>
							${users.dept}
						</td>
						<td>
							${users.password.deptNumber}
						</td>
						<td>
							${users.duty}
						</td>
						<td>
							${users.education}
						</td>
						<td>
							<a href="UsersAction!findUserById.action?id=${users.id}"
								target="_blank"> ${users.password.userStatus}</a>
						</td>
						<td>
							${users.onWork}
						</td>
						<td>
							<a target="_blank"
								href="UsersAction!findUserByIdForDetails.action?id=${users.id}&tag=${tag}" onclick="">
								维护</a>/
							<input type="button" value="删除(Delete)"
								onclick="dele12('${users.name}','${users.id}')" />
								
							<!-- <a onclick="return window.confirm('确认要删除${users.name}的所有信息?')"
								href="UsersAction!delUser.action?id=${users.id}">删除(Delete)</a> -->
						</td>
						</tr>
					</s:iterator>

					<s:iterator id="users" value="contractEndList" status="ststusUser">
						<s:if test="#ststusUser.first">
							<tr bgcolor="red">
								<th colspan="11" align="center">
									<font color="#ffffff"> 合同将到期员工信息<br /> Contract will
										expire employee information</font>
								</th>
							</tr>
						</s:if>
						<s:if test="#ststusUser.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this,'red')"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this,'red')"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ststusUser.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="red">
							</s:else>
							<s:property value="#ststusUser.index+1" />
							</font>
						</td>
						<td>
							${users.code}
						</td>
						<td>
							${users.cardId}
						</td>
						<td>
							<a target="_blank"
								href="UsersAction!findUserByIdForDetails.action?id=${users.id}&tag=${tag}">
								${users.name}</a>
						</td>
						<td>
							${users.dept}
						</td>
						<td>
							${users.password.deptNumber}
						</td>
						<td>
							${users.duty}
						</td>
						<td>
							${users.education}
						</td>
						<td>
							<a href="UsersAction!findUserById.action?id=${users.id}"
								target="_blank"> ${users.password.userStatus}</a>
						</td>
						<td>
							${users.onWork}
						</td>
						<td>
							<a target="_blank"
								href="UsersAction!findUserByIdForDetails.action?id=${users.id}&tag=${tag}">
								维护</a>/
								<input type="button" value="删除(Delete)"
								onclick="dele12('${users.name}','${users.id}')" />
								<a href="javascript:;" onclick="tanchu('${users.cardId}','${users.name}')">卡号二维码</a>
								</a>
							<!-- <a onclick="return window.confirm('确认要删除${users.name}的所有信息?')"
								href="UsersAction!delUser.action?id=${users.id}">删除(Delete)</a> -->
						</td>
						</tr>
					</s:iterator>


					<s:iterator id="users" value="userList" status="ststusUser">
						<s:if test="#ststusUser.first">
							<tr bgcolor="green">
								<th colspan="11" align="center">
									<font color="#ffffff"> 员工信息<br />Employee Information</font>
								</th>
							</tr>
						</s:if>
						<s:if test="#ststusUser.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ststusUser.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="red">
							</s:else>
							<s:property value="#ststusUser.index+1" />
							</font>
						</td>
						<td>
							${users.code}
						</td>
						<td>
							${users.cardId}
						</td>
						<td>
							<a target="_blank"
								href="UsersAction!findUserByIdForDetails.action?id=${users.id}&tag=${tag}">
								${users.name}</a>
						</td>
						<td>
							${users.dept}
						</td>
						<td>
							${users.password.deptNumber}
						</td>
						<td>
							${users.duty}
						</td>
						<td>
							${users.education}
						</td>
						<td>
							<a href="UsersAction!findUserById.action?id=${users.id}"
								target="_blank"> ${users.password.userStatus}</a>
						</td>
						<td>
							${users.onWork}
						</td>
						<td>
							<a
								href="UsersAction!findUserByIdForDetails.action?id=${users.id}&tag=${tag}">
								维护(Maintenance)</a>/
								<input type="button" value="删除(Delete)"
								onclick="dele12('${users.name}','${users.id}')" />
								<a href="javascript:;" onclick="tanchu('${users.cardId}','${users.name}')">卡号二维码</a>
								<!-- 
							<a onclick="return window.confirm('确认要删除${users.name}的所有信息?')"
								href="UsersAction!delUser.action?id=${users.id}">删除(Delete)</a> -->
						</td>
						</tr>
					</s:iterator>

					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
					</tr>
					<tr>
						<td colspan="7" align="center">
							<font color="red">${errorMessage}${successMessage}</font>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	<SCRIPT type="text/javascript">
	function daochuExec(a){
		if(a==1){
			$("#testFrom").attr("action","UsersAction!daochuExec.action");
			$("#testFrom").submit();
			$("#testFrom").attr("action","UsersAction!findAllUsers.action");
		}
	}
	function dele12(name,id1){
		if(window.confirm('确认要删除'+name+'的所有信息?')){
		window.location.href = "UsersAction!delUser.action?id="+id1;
		}
		}
	
	
function kcUser(id,name){
	if(confirm('确定要开除'+name+'吗?')){
		window.location.href='UsersAction!kcUsers.action?id='+id;
	}
}
function tanchu(number,obj){
		document.getElementById("xiugaiIframe").src="<%=basePath%>System/wuliu/WarehouseNumber_kwm.jsp?number="+number+"&ku="+obj;
		chageDiv('block')
}
	</SCRIPT>
</html>
