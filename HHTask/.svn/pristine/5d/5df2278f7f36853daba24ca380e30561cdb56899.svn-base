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
				<h3>
					登记信息管理
				</h3>
				<form action="register_index.action" method="post">
				<table>
					<tr>
						<td>人员姓名：<input type="text" name="people" value="${people }"/></td>
						<td>卡号：<input type="text" name="mark" value="${mark }"/></td>
						<input type="hidden" name="errorMessage" value="all"/>
						<td><input type="submit" value="查询" style="width: 80px; height: 50px;"/></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						添加新登记信息:
						<input type="button" onclick="add()" style="width: 100px; height: 50px;" value="添加新登记信息">
						</td>
					</tr>
				</table>
				</form>
				<hr>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td>
							序号
						</td>
						<td>
							人员姓名
						</td>
						<td align="cen">
							登记号码
						</td>
						<td align="center">
							卡号
						</td>
						<td>
							操作
						</td>
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
						<td>
							${pageList.personName }
						</td>
						<td align="center">
							${pageList.badgenumber }
						</td>
						<td>
							${pageList.cardNo }
						</td>
						<td>
							<input type="button" value="修改" style="margin-left: 20px;width: 80px; height: 50px;"
								onclick="update(${pageList.id})" />
							<input type="button" value="删除" style="margin-left: 20px;width: 80px; height: 50px;"
								onclick="del(${pageList.id})" />
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="6" align="right">
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
		<script src="<%=path%>/javascript/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
	function add(){
		window.location="<%=path%>/System/renshi/register/register_add.jsp";
	}
	function update(id){
		window.location="register_initUpdate.action?reId="+id;
	}
	function del(id){
		window.location="register_del.action?reId="+id;
	}
</script>
	</body>
</html>
