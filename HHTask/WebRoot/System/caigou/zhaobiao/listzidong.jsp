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
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				<table class="table">
					<tr>
						<th>id</th>
						<th>招商标题</th>
						<th>负责人</th>
						<th>负责人电话</th>
						
						<th>开始时间</th>
						<th>结束时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<s:iterator value="list" id="zhaobiao" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>${pageIndex.index+1}</th>
							<th>${zhaobiao.title}</th>
							<th>${zhaobiao.fuze}</th>
							<th>${zhaobiao.phone}</th>
							
							<th>${zhaobiao.moban}</th>
							<th>${zhaobiao.kongxian}</th>
							<th><s:if test='#zhaobiao.status=="N"'>未提交</s:if>
							    <s:if test='#zhaobiao.status=="C"'>正在审批</s:if>
							    <s:if test='#zhaobiao.status=="D"'>招标中</s:if>
							     <s:if test='#zhaobiao.status=="Y"'>已审批</s:if>
							      <s:if test='#zhaobiao.status=="S"'>评标中</s:if>
							</th>
							<th>
							
							<s:if test='#zhaobiao.status=="N"'>
								<a href="zhaobiaoAction!toUpdateMoban.action?zhaobiao.id=${zhaobiao.id}">修改</a>
		    			        <a href="zhaobiaoAction!deletezhaobiao.action?zhaobiao.id=${zhaobiao.id}">删除</a>
		    			        <a href="zhaobiaoAction!listById.action?zhaobiao.id=${zhaobiao.id}">添加采购料</a>
							</s:if>
							<s:if test='#zhaobiao.status=="Y"'>
							     <a href="zhaobiaoAction!fabu.action?zhaobiao.id=${zhaobiao.id}">发布</a> 
							</s:if>	
							<s:if test='#zhaobiao.status=="D"'>
							     <a href="zhaobiaoAction!jieshu.action?zhaobiao.id=${zhaobiao.id}">结束</a> 
							</s:if>	
							<s:if test='#zhaobiao.status=="S"'>
							     <a href="zhaobiaoAction!listzidongXi.action?zhaobiao.id=${zhaobiao.id}">自动评选</a> 
							     
							</s:if>			
		    			      
							
							</th>
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
							<th colspan="11" align="center" style="color: red" >
						</s:else>
						</th>
					</tr>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
</script>
	</body>
</html>
