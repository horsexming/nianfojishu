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
					  <form action="caoZuoAction!listCaoZuo.action" method="post"  theme="simple">
			     <table class="table">
			   		<tr><th align="center">工位编号：</th><td><input type="text" id="zhShebei.gongweiId" name="zhShebei.gongweiId" /></td>
			   			<th align="center">设备编号：</th><td>
			   			    <input type="text" id="zhShebei.shebeiId" name="zhShebei.shebeiId" />
			   			
			   			<td rowspan="2"><input type="submit" value="查询"   class="input"/>
			   							<input type="button" value="添加" class="input" onclick="f1();" />
			   			</td>
			   		</tr>
			   		<tr>
			   			<th align="center">设备名称</th><td>
			   					  <input type="text" id="zhShebei.shebeiname" name="zhShebei.shebeiname" />
			   			</td>
			   			<th align="center"></th><td>
			   				
			   			</td>
			   		</tr>
			  
			  </table>
			  </form>
			
			
			
				<table class="table">
					<tr  bgcolor="#c0dcf2">
						<th>序号</th>
						<th>工位编号</th>
						<th>设备编号</th>
						<th>设备名称</th>
						<th>设备工序名称</th>
						<th>操作</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>${pageIndex.index+1}</th>
							<th>${zhUser1.gongweiId}</th>
							<th>${zhUser1.shebeiId}</th>
							<th>${zhUser1.shebeiname}</th>
							<th>${zhUser1.shebeigongxuName}</th>
							<th>
							<s:if test="#zhUser1.status==null">
		    			         <a href="caoZuoAction!todengji.action?zhShebei.id=${zhUser1.id}">添加操作等级</a>
		    			          <a href="caoZuoAction!toupdatezhShebei.action?zhShebei.id=${zhUser1.id}">修改</a>
		    			           <a href="caoZuoAction!deletezhShebei.action?zhShebei.id=${zhUser1.id}">删除</a>
		    			         </s:if>
		    			         <s:if test='#zhUser1.status=="设备操作等级添加完成"'>
		    			         	  <a href="caoZuoAction!tozhCaozuoEmp.action?zhShebei.id=${zhUser1.id}">添加操作员工</a>
		    			         	   <a href="caoZuoAction!deletezhShebeiEmp.action?zhShebei.id=${zhUser1.id}">删除</a>
		    			         </s:if>
		    			        
		    			         <s:if test='#zhUser1.status=="添加操作员工"'>
		    			         	  <a href="caoZuoAction!tozhCaozuoEmp.action?zhShebei.id=${zhUser1.id}">添加操作员工</a>
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
 function f1(){
	    window.location.href="<%=basePath%>/System/renshi/caozuonengli/shebeiAndEmp_add.jsp";
	}
	</SCRIPT>

</html>
