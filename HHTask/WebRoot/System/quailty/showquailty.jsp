<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
							<span id="title">您正在对品质管理进行操作</span>
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
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
			<h3>质量审核管理</h3>
			<s:if test="test!=null">
				<s:if test="test==1">
				<form action="QualityAction_findQuailty.action?test=1" method="post" >
				<table class="table" >
					<tr>
						<td>姓名： <input type="text" name="quality.quality_pop" />
						</td>
						<td>时间： <input type="text"  class="Wdate" name="quality.quality_time"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="submit" style="width: 100px; height: 40px;" value="查询"  class="input" />
						<input type="button" style="width: 100px; height: 40px;" value="添加"  class="input"  onclick="add()"/>
						 </td>
					</tr>
				</table>
			</form>
			</s:if>
			
			<s:if test="test==2">
				<form action="QualityAction_findQuailty.action?test=2" method="post" >
				<table class="table" >
					<tr>
					<td>部门：
						<select  id="department" name="quality.quality_name">
						<option selected="selected" value="">选择部门</option>
						<s:iterator id="cu" value="list">
									<option value="${cu.ta_dept}">
										${cu.ta_dept}
									</option>
								</s:iterator>
						</select>
						</td>
						<td>姓名： <input type="text" name="quality.quality_pop" />
						</td>
						<td>时间： <input type="text"  class="Wdate" name="quality.quality_time"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
						<input type="submit" style="width: 100px; height: 40px;" value="查询"  class="input" />
<%--						<input type="button" style="width: 100px; height: 40px;" value="添加"  class="input"  onclick="add()"/>--%>
						 </td>
					</tr>
				</table>
			</form>
			</s:if>
		</s:if>
			<s:else>
				<form action="QualityAction_findQuailty.action" method="post" >
				<table class="table" >
					<tr>
						<td>标题： <input type="text" name="quality_title" />
						</td>
						<td>类别：
						<SELECT name="quality.quality_type">
						  <s:if test="quality.quality_type!=null">
						  <option>
						   <s:property value="quality.quality_type"/>
						  </option>
						  </s:if>
						 <option>所有
						 </option>
						 <option>产品审核
						 </option>
						 <option>过程审核
						 </option>
						 <option>体系审核
						 </option>
						</SELECT>
						</td>
					</tr>
					<tr>
						<td>姓名： <input type="text" name="quality.quality_pop" />
						</td>
						<td>时间： <input type="text"  class="Wdate" name="quality.quality_time"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="submit" style="width: 100px; height: 40px;" value="查询"  class="input" />
						<input type="button" style="width: 100px; height: 40px;" value="添加"  class="input"  onclick="add()"/>
						</td>
					</tr>
				</table>
			</form>
		</s:else>
			
			
				
			<table  class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">序号</td>
					<td align="center">部门</td>
					<td align="center">类别</td>
					<td align="center">标题</td>
					<td align="center">描述</td>
					<td align="center">人员</td>
					<td align="center">时间</td>
					<td align="center">操作</td>
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
					<td><s:if test="#pageStatus.index%2==1">
							<font>
						</s:if> <s:else>
							<font color="#c0dcf2">
						</s:else> <s:property value="#pageStatus.index+1" /> </font></td>
					<td>${pageList.quality_name}</td>
					<td>${pageList.quality_type}</td>
					<td>${pageList.quality_title}</td>
					<td>${pageList.quality_context}</td>
					<td>${pageList.quality_pop }</td>
					<td>${pageList.quality_time}</td>
					<td>
<%--					<a href="QualityAction_downloadQuality.action?sal_id=${pageList.id }">下载</a>--%>
<%--					<a href="DownAction.action?fileName=${pageList.quality_file}&directory=/upload/pinzhi/">下载</a>--%>
					<a href="FileViewAction.action?FilePath=/upload/pinzhi/${pageList.quality_file}">下载</a>
					<s:if test="test!=null">
						<s:if test="test==1">
						<a onclick="update(${pageList.id })">修改</a>/
						<a href="QualityAction_delQuality.action?test=1&del_id=${pageList.id }">删除</a>
						</s:if>
						<s:if test="test==2">
						<a onclick="update(${pageList.id })">修改</a>/
						<a href="QualityAction_delQuality.action?test=2&del_id=${pageList.id }">删除</a>
						</s:if>
					</s:if>
					<s:else>
					<a onclick="update(${pageList.id })">修改</a>/
						<a href="QualityAction_delQuality.action?del_id=${pageList.id }">删除</a>
					</s:else>
					
<%--					<s:if test="test==null">--%>
<%--						<a href="QualityAction_delQuality.action?del_id=${pageList.id }">删除</a>--%>
<%--					</s:if>--%>
<%--					<s:else>--%>
<%--						<a href="QualityAction_delQuality.action?test=1&del_id=${pageList.id }">删除</a>--%>
<%--					</s:else>--%>
					
					</td>
				</s:iterator>
				</tr>
				<tr>
					<s:if test="errorMessage==null">
						<td colspan="12" align="right">第 <font color="red"><s:property
									value="cpage" /> </font> / <s:property value="total" /> 页 <fenye:pages
								cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page"
								theme="number" />
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
	<script type="text/javascript"  src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	function add() {
	var test = "${test}";
	if(test!=""){
		if(test==1){
			var url = "QualityAction_toaddQuality.action?test=2";
		}if(test==2){
			var url = "QualityAction_toaddQuality.action?test=3";
		}
	}else{
		var url = "QualityAction_toaddQuality.action";
	}
	
<%--	if(test==""){--%>
<%--		var url = "QualityAction_toaddQuality.action";--%>
<%--	}else{--%>
<%--		var url = "QualityAction_toaddQuality.action?test=2";--%>
<%--	}--%>
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	
	function update(obj){
		var url = "QualityAction_salQuality.action?sal_id="+obj;
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	$(function() {
		if('${test}'=='2'){
		createDept('department');
	}
})
</script>
</body>
</html>
