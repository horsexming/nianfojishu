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
				<form action="gongxuAction!selectGongxu.action" method="post">
					<table width="95%" class="table">
						<tr><td>班组：<select name="gongwei.banzu" id="deptname">
						<OPTION ></OPTION>
<%--						<OPTION value="零件班">零件班</OPTION>--%>
<%--						<OPTION value="排气管班">排气管班</OPTION>--%>
<%--						<OPTION value="制消一班">制消一班</OPTION>--%>
<%--						<OPTION value="制消二班">制消二班</OPTION>--%>
<%--						<OPTION value="组件班">组件班</OPTION>--%>
						</select></td>
						<td>设备编号：<input type="text" name="gongwei.shebeiCode" size="5"/></td>
						<td>设备名称：<input type="text" name="gongwei.shebeiName" size="5"/></td>
						<td>操作技能指数：<select  name="gongwei.caozJineng" >
						<OPTION ></OPTION>
						<OPTION value="0">0</OPTION><OPTION value="0.1">0.1</OPTION><OPTION value="0.2">0.2</OPTION>
						<OPTION value="0.3">0.3</OPTION><OPTION value="0.4">0.4</OPTION><OPTION value="0.5">0.5</OPTION>
						<OPTION value="0.6">0.6</OPTION><OPTION value="0.7">0.7</OPTION><OPTION value="0.8">0.8</OPTION>
						<OPTION value="0.9">0.9</OPTION><OPTION value="1">1</OPTION>
						</select>
						
						</td>
						<td>操作可替换人数：<input type="text" name="gongwei.caoztihuanrenshu" size="5" /></td>
						<td>操作负荷指数：<select  name="gongwei.caozFuhe" >
						<OPTION ></OPTION>
						<OPTION value="0">0</OPTION><OPTION value="0.1">0.1</OPTION><OPTION value="0.2">0.2</OPTION>
						<OPTION value="0.3">0.3</OPTION><OPTION value="0.4">0.4</OPTION><OPTION value="0.5">0.5</OPTION>
						<OPTION value="0.6">0.6</OPTION><OPTION value="0.7">0.7</OPTION><OPTION value="0.8">0.8</OPTION>
						<OPTION value="0.9">0.9</OPTION><OPTION value="1">1</OPTION>
						</select>						
						</td>				
						
						</tr>
						<tr>
						<td>工位：<input type="text" name="gongwei.gongweihao" size="5"/></td>
						
						<td>设备型号：<input type="text" name="gongwei.shebeiXinghao" size="5"/></td>
						<td>操作人数：<input type="text" name="gongwei.caozMinimumMan" size="5"/></td>
						<td>工装技能指数：<select  name="gongwei.gongzhuangJineng" >
						<OPTION ></OPTION>
						<OPTION value="0">0</OPTION><OPTION value="0.1">0.1</OPTION><OPTION value="0.2">0.2</OPTION>
						<OPTION value="0.3">0.3</OPTION><OPTION value="0.4">0.4</OPTION><OPTION value="0.5">0.5</OPTION>
						<OPTION value="0.6">0.6</OPTION><OPTION value="0.7">0.7</OPTION><OPTION value="0.8">0.8</OPTION>
						<OPTION value="0.9">0.9</OPTION><OPTION value="1">1</OPTION>
						</select>
						
						</td>
						<td>工装可替换人数：<input type="text" name="gongwei.gongzhuangtihuanrenshu" size="5"/></td>
						<td>工装负荷指数：<select  name="gongwei.gongzhuangFuhe" >
						<OPTION ></OPTION>
						<OPTION value="0">0</OPTION><OPTION value="0.1">0.1</OPTION><OPTION value="0.2">0.2</OPTION>
						<OPTION value="0.3">0.3</OPTION><OPTION value="0.4">0.4</OPTION><OPTION value="0.5">0.5</OPTION>
						<OPTION value="0.6">0.6</OPTION><OPTION value="0.7">0.7</OPTION><OPTION value="0.8">0.8</OPTION>
						<OPTION value="0.9">0.9</OPTION><OPTION value="1">1</OPTION>
						</select>						
						</td>
						</tr>
						<tr>
						<td colspan="6" align="center"><input type="submit" value="查找" />
						<a href="System/SOP/gongwei/saveGongwei.jsp" >添加工位信息 </a>
						</td></tr>
					</table>
				</form>
				<br>
				<table width="95%" class="table">  			  	
			   	<tr align="center" bgcolor="#c0dcf2" style="height: 20px; font-weight: bold;">
			   	<td rowSpan="2">序号</td><td rowSpan="2">班组</td><td rowSpan="2">工位</td><td rowSpan="2">设备名</td><td rowSpan="2">设备号</td>
			   	<td colspan="3">A*操作技能指数</td><td rowSpan="2">操作人数</td><td colspan="3">B*工装技能指数</td>
			     <td rowSpan="2">备注</td>
			    <td rowSpan="2">操作</td>		   	
			   	</tr>
			   	<tr bgcolor="#c0dcf2" style="height: 20px; font-weight: bold;">			   	
			   	<td>技能指数</td><td>可替换人数</td><td>负荷指数</td>
			   	<td>技能指数</td><td>可替换人数</td><td>负荷指数</td>
			   	
			   	</tr>
			   	 <s:iterator value="list" status="se" id="sell">
			    	<s:if test="#se.index%2==1">
					<tr align="center" bgcolor="#e6f3fb"
						onmouseover="chageBgcolor(this)"
						onmouseout="outBgcolor(this,'#e6f3fb')">
				  </s:if>
				  <s:else>
				  <tr align="center" onmouseover="chageBgcolor(this)"
					onmouseout="outBgcolor(this,'')">
			      </s:else>
			    	<td><s:property value="#se.index+1" /></td> 
			    	<td><s:property value="banzu" /></td>
			    	<td><s:property value="gongweihao" /></td>
			    	<td><s:property value="shebeiName" /></td>
			    	<td><s:property value="shebeiCode" /></td>			    	
			    	<td><s:property value="caozJineng" /></td>
			    	<td><s:property value="caoztihuanrenshu" /></td>			    	
			    	<td><s:property value="caozFuhe" /></td>			    	
			    	<td><s:property value="caozMinimumMan" /></td>
			    	<td><s:property value="gongzhuangJineng" /></td>
			    	<td><s:property value="gongzhuangtihuanrenshu" /></td>
			    	<td><s:property value="gongzhuangFuhe" /></td>
			    	<td><s:property value="more" /></td>	    	
			    	<td>			    	
			    	<a href="gongxuAction!getOneGongwei.action?id=<s:property value='id' />&cpage=<s:property value='cpage' />">修改</a>
			    	<a href="gongxuAction!deleteGongwei.action?id=<s:property value='id' />&cpage=<s:property value='cpage' />">删除</a>		    	
			    	
			    	
			    	</td>			    	  	
			    	
			    	</tr>
			    </s:iterator>
			    <tr><td colspan="14" align="right">
			    	 共 <s:property value="total"/> 页 第 <s:property value="cpage"/> 页
			        <fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page" theme="number"/> 				
			    	
			    	</td></tr>
			    	 
			   </table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		$(function() {
	$.ajax( {
		type : "post",
		url : "DeptNumberAction!findAllDept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='"+ this.dept + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
					});
			//$("#deptname").tinyselect();
		}
	});
});
		</SCRIPT>
	</body>
</html>
