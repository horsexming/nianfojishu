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
				<h3>报修管理</h3>
				<br/>
				<form action="maintain_queryMaintainByCondition.action" method="post" name="myForm"> 
				<table>
					<tr>
						<td>编号：<input type="text" name="voma.number" value="${voma.number}"/></td>
						<td>名称：<input type="text" name="voma.matetag" value="${voma.matetag}"/></td>
						<td>状态: <select name="voma.state">
									<option value="状态选择" selected="selected">状态选择</option>
									<s:iterator id="c" value="{'维修中','已修复'}">
									    <s:if test="#c == voma.state">
											<option value="${c}" selected="selected">${c}</option>
									    </s:if>
									    <s:else>
									    	<option value="${c}">${c}</option>
									    </s:else>
									</s:iterator>
								 </select></td>
						<td><input type="hidden" name="errorMessage" value="all"/>
							<input type="submit" value="查询" style="width: 80px; height: 50px;"/>
							<input type="button" value="导出Excel" style="width: 80px; height: 50px;" onclick="exportExcel();todisabledone(this)" data="downData"/>
						</td>
					</tr>
				</table>
				</form>
				<table class="table">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td>序号</td>
						<td>编号</td>
						<td>名称</td>
						<td>规格</td>
						<td width="30px;">单位</td>
						<td width="30px;">数量</td>
						<td width="50px;">状态</td>
						<td>报修时间</td>
						<td>修复日期</td>
						<td>备注</td>
						<td width="70px;">修复周期(天)</td>
						<td width="90px;"></td>
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
									<td>${pageList.number}</td>
									<td>${pageList.matetag}</td>
									<td>${pageList.format}</td>
									<td>${pageList.unit}</td>
									<td>${pageList.amount}</td>
									<td>${pageList.state}</td>
									<td>${pageList.fixDate}</td>
									<td>${pageList.restorDate}</td>
									<td>${pageList.more}</td>
									<td></td>
									<td>
										<s:if test="#pageList.state=='已修复'">
											<span style="color:blue;">已修复</span>
										</s:if>
										<s:else>
											<a href="maintain_repair.action?voma.id=${pageList.id}">已修复</a>
											<a href="maintain_initUpdate.action?voma.id=${pageList.id}">修改</a>
										</s:else>
										
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
			function exportExcel(){
				document.forms.myForm.action="maintain_export.action";
				document.forms.myForm.submit();
				document.forms.myForm.action="maintain_queryMaintainByCondition.action";
			}
			function del(){
				if(confirm("确定删除吗?")){
					return;
				}else{
					return false;
				}
			}
			function add(){
				window.location="maintain_add.action";
			}
		</script>
	</body>
</html>
