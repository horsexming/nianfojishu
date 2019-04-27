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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
				<table style="width: 100%">
					<tr>
						<td>
							
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				</center>
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
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
			 <form action="OsTemplate_list.action" method="post"  theme="simple">
			 <input type="hidden" value="${status}" name="status"/>
			  <table class="table">
			   		<tr><th align="center">种类：</th><td>
			   						<select name="t.ctype1" style="width:150px " >
			   						<option></option>
									<option>外购件</option>
									<option>原材料</option>
									<option>自制</option>
									<option>组合</option>
									<option>总成</option>
								</select>
			   				
			   		</td>
			   			<th align="center">类型：</th><td>
			   					<select name="t.ctype" style="width:150px " >
			   					<option></option>
									<option>端盖</option>
									<option>隔盘</option>
									<option>内管</option>
									<option>外管</option>
									<option>吊钩</option>
									<option>法兰</option>
									<option>护板</option>
									<option>岩棉</option>
									<option>筒体</option>
									<option>螺帽</option>
									<option>其它</option>
									<option>螺纹嘴</option>
									<option>净化器</option>
									<option>波纹管</option>
									<option>玻璃纤维</option>
								</select>
			   			
			   			</td>
			   			<td rowspan="3"><input type="submit" value="查询"   class="input"/>	
			   							
			   		</tr>
			   		<tr>
			   			<th align="center">名称：</th><td>
			   				 <input name="t.name"/>
			   			</td>
			   			<th align="center">件号：</th><td>
			   			<input name="t.partNumber"/>
			   			</td>
			   			
			   		</tr>
			   		<tr>
			   		   <th align="center">编号：</th><td>
			   		  <input name="t.serialNumber"/>
			   		   </td>
			   		</tr>
			  
			  </table>
			  </form>
			
			
			
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>种类</th>
						<th>车型</th>
						<th>模板类型</th>
						<th>类型</th>
						<th>名称</th>
						<th>件号</th>
						<th>材料</th>
						<th>工序</th>
						<th>工序名</th>
						<th>资料</th>
						<th>操作</th>
					</tr>
					<s:iterator value="ts" status="st" id="tt">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
							<th>${st.index + 1 }</th>
							<td>${tt.zhonglei}</td>
							<td>${tt.cmodel}</td>
							<td>${tt.zhonglei}</td>
							<td>${tt.ctype}</td>
							<td>${tt.name}</td>
							<td>${tt.partNumber}</td>
							<td>${tt.material}</td>
							<td>${tt.gongxuNum}</td>
							<td>${tt.gongxuName}</td>
							<td><a href="DownAction.action?fileName=${tt.filename}&directory=upload/file/OsTemplate/">下载</a></td>
							<td>
								<a href="OsTemplate_showScope.action?t.id=${tt.id}">查看检查项</a>
								 <a href="OsTemplate_deleteOsTemplate.action?t.id=${tt.id}" onclick="return confirm('确定删除此记录?')">删除</a> 
								<a onclick="toUpdateOsTemplate(${tt.id},'${status}')">修改</a>
							</td>
							
						</tr>
						
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="20" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			function toUpdateOsTemplate(id,status){
			var url=encodeURI(encodeURI("OsTemplate_toUpdateOsTemplate.action?t.id="+id+"&status="+status));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	
		
		
		</SCRIPT>
	</body>
</html>
