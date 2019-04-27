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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>
			
			<div align="center">
			  <form action="caoZuoAction!listUsers.action" method="post"  theme="simple">
			  <table class="table">
			     <tr><td>工号:</td><td><input type="text" id="userss.code" name="userss.code" /></td>
			     	 <td>卡号:</td><td><input type="text" id="userss.cardId" name="userss.cardId" /></td> 	
			     	 <Td rowspan="2"><input type="submit" value="查询"   class="input"/>
			     	 <input type="button" value="添加"   class="input" onclick="add()"/>	
			     	 </Td>
			     </tr>
			  	<tr><td>姓名:</td><td><input type="text" id="userss.name" name="userss.name" /></td>
			     	 <td>部门:</td><td><select   id='dept'  name="userss.dept" onclick="createDept('dept')" style="width:90px;">
			   			  </select></td> 	
			     </tr>
			  </table>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号

						</td>
						<td align="center">
							报废类型 
						</td>
						<td align="center">
							报废原因 
						</td>
						<td>操作</td>
					</tr>
						<s:iterator value="list" id="zhaobiao1" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						<td>
							${pageIndex.index+1}
						</td>
						<td>
							${zhaobiao1.leixing}
						</td>
						<td>
							${zhaobiao1.yuanyin}
						</td>
						<td>
						<a onclick="toUpdatezhaobiao(${zhaobiao1.id})">修改</a>
					<a href="caoZuoAction!deleteBaofei.action?baofei.id=${zhaobiao1.id}" onclick="return confirm('确定要删除吗？')">删除</a>
						
						</td>
				
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
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function add(){
			var url=encodeURI(encodeURI("${pageContext.request.contextPath}/System/SOP/sanjianbaofei/baofei_add.jsp"));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
		function toUpdatezhaobiao(id){
			var url=encodeURI(encodeURI("caoZuoAction!toUpdatbaofei.action?baofei.id="+id));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
		</SCRIPT>
	</body>
</html>
