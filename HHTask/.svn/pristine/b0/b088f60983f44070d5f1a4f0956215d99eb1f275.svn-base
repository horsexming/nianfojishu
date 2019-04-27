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
		<script type="text/javascript">

</script>

	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="center" style="border: 1px solid #00000;">
				<form action="ProcardAction!chakan.action"
					method="post">
					<!-- 只查询第一层
					<input type="hidden" name="procardTemplate.belongLayer" value="1" /> -->
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<th align="center">
								内容
								<input name="provision.content"
									value="${provision.content}" />
								<input type="hidden" name="procardTemplate.id" value="${procardTemplate.id}"  />
								<input type="submit" value="查询(Query)" class="input" />
							</th>
						</tr>
					</table>
				</form>

				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<form action="ProcardAction!bangding.action"
					method="post">
					
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
						<th>选择</th>
							<th align="center">
								序号
								<br />
								No.
							</th>
							<th align="center">
								内容
								<br />
								Part No.
							</th>
						</tr>
						<tr  style="background-color:#008000"><td colspan="21" align="center">已绑定的自检项</td></tr>
				<s:iterator value="listAll" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
								<td>
						  <input type="checkbox" name="selected"
											value="${pageProcardTem.id}" checked="checked" />
						</td>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								${pageProcardTem.content}
							</td>
							</tr>
						</s:iterator>
						
						<!-- ---------------------------------------------------------------------- -->
							<tr  style="background-color:#008000"><td colspan="21"  align="center">未绑定的自检项</td></tr>
						<s:iterator value="list" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
								<td>
						  <input type="checkbox" name="selected"
											value="${pageProcardTem.id}" />
						</td>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								${pageProcardTem.content}
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
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
						 <tr align="center">
						 <td align="center" colspan="11" >
						<input type="hidden" name="procardTemplate.id" value="${procardTemplate.id}"  />
						 <s:submit value="绑定自检项" cssClass="input" />
					      <input type="button" name="Submit2" value="取消"  class="input" onclick="window.history.go(-1);"/></td></tr>
							
					</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function vali() {
	var nums = document.getElementsByName("selected");
	for ( var i = 0; i < nums.length; i++) {
		if (nums[i].checked) {
			return true;
		}
	}
	alert("请选择需要绑定的件号！谢谢");
	return false;
}
function exportExcel(objForm) {
	objForm.action = "sellAction!exportEXCEL.action?tag=sellDetail";
	objForm.submit();
}
    function detail(id){
				window.location="orderManager_queryDetail.action?id="+id;
			}
		
		
		</script>
	</body>
</html>
