<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>取款页面</h3>
	<form action="register_initWithdrawMon.action" method="post">
		<input type="hidden" name="errorMessage" value="all">
		<table>
			<tr>
				<td>卡号：</td>
				<td><input type="text" name="cardNo" value="${cardNo }" /></td>
				<td>姓名：</td>
				<td><input type="text" name="personName" value="${personName }" /></td>
				<td>部门名称：</td>
				<td>
					<select name="deptID" id="depatment">
						<option value="0">
							选择部门
						</option>
					<s:iterator id="dept" value="deptList">
						<s:if test="#dept.id == deptID">
							<option value="${dept.id }" selected="selected">${dept.name }</option>
						</s:if>
						<s:else>
							<option value="${dept.id }">${dept.name }</option>
						</s:else>
					</s:iterator>
					</select>
				</td>
				<td><input type="submit" value="查询" style="width: 80px; height: 50px;" />
				</td>
			</tr>
		</table>
	</form>
	<form action="register_batchWithdrawMon.action" method="post"
		onsubmit="return vali()">
			<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
					<td></td>
				<td align="center">
					序号
				</td>
				<td align="center">人员姓名</td>
				<td align="center">卡号</td>
				<td align="center">部门</td>
				<td align="center">余额(份)</td>
				<td align="center">应退(元)</td>
				<td align="center">操作</td>
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
						<td><input type="checkbox" name="selected" value="${pageList.id }" /></td>
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
					<td>${pageList.name }
					</td>
					<td>${pageList.cardNo }
					</td>
					<td>${pageList.deptName }
					</td>
					<td>${pageList.balance }
					</td>
					<td>${pageList.refund }
					</td>
					<td><input type="button" onclick="withdrawing(${pageList.id})" style="width: 80px; height: 50px;" 
						value="取款" />
					</td>
				</tr>
				</s:iterator>
			<tr>
				<td><input type="button" onclick="this.value=check(this.form.selected)" style="width: 80px; height: 50px;" value="全选"/></td>
				<td><input type="submit" style="width: 80px; height: 50px;" value="批量取款" />
				</td>
			</tr>
			<tr>
						<s:if test="errorMessage==null">
							<td colspan="10" align="right">
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
	</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function withdrawing(id){
	window.location="register_withdrawMon.action?reId="+id;
}

function vali(){
 var selectList = document.getElementsByName("selected");
  for(var i = 0;i<selectList.length;i++){
    if(selectList[i].checked){
      return true;
    }
  }
  alert("请选择需要取款的人员！谢谢");
  return false;
}

var checkflag = "false";
function check(field) {
	if (checkflag == "false") {
		for (i = 0; i < field.length; i++) {
			field[i].checked = true;}
			checkflag = "true";
			return "取消全选"; 
			}
	else {
	for (i = 0; i < field.length; i++) {
		field[i].checked = false; }
		checkflag = "false";
		return "全选"; 
		}
}
</script>
	</body>
</html>
