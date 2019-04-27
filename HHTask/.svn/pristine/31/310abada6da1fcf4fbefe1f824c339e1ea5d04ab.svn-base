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
					  <form action="markIdAction!listyanshoupincizi.action" method="post"  theme="simple">
			  <table class="table">
			  <tr><th colspan="10" align="center"></th></tr>
			   		<tr><th align="center">范围：
			   		<input type="hidden" id="waigoujianpinci.id" name="waigoujianpinci.id" value="${waigoujianpinci.id}" />
			   		<input type="text" id="waigoujianpinciZi.caigoushuliang1" name="waigoujianpinciZi.caigoushuliang1" />-
			   			<input type="text" id="waigoujianpinciZi.caigoushuliang2" name="waigoujianpinciZi.caigoushuliang2" />
			   			<input type="submit" value="查询"   class="input"/>
			   			<s:if test='tag!="show"'>
			   				<input type="button" value="添加"   class="input" onclick="add()"/>
			   			</s:if>
			   			</td>
			   		</tr>
			  </table>
			  </form>
				<table class="table">
					<tr bgcolor="#c0dcf2">
						<th>序号</th>
						<th>巡检批量范围</th>
						<th>样本代码</th>
						<th>样本大小</th>
						<th>AC</th>
						<th>RE</th>
						<th>再次抽检样本大小</th>
						<th>再次抽检AC1</th>
						<th>再次抽检RE1</th>
						<s:if test='tag!="show"'>
							<th>操作</th>
						</s:if>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>${pageIndex.index+1}</th>
							<th align="right">${zhUser1.caigoushuliang1} ~ ${zhUser1.caigoushuliang2}    </th>
							<th>${zhUser1.ybCode}</th>
							<th align="right">${zhUser1.choujian}</th>
							<th>${zhUser1.ac}</th>
							<th>${zhUser1.re}</th>
							<th>${zhUser1.erchoujian}</th>
							<th>${zhUser1.ac1}</th>
							<th>${zhUser1.re1}</th>
						<s:if test='tag!="show"'>
							<th>
							<a onclick="toUpdatezhaobiao(${zhUser1.id})">修改</a>
							<a href="markIdAction!deletewaigoujianpinciZi.action?waigoujianpinciZi.id=${zhUser1.id}">删除</a>
							
							</th>
						</s:if>
							
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
			function add(){
			//var url=encodeURI(encodeURI("${pageContext.request.contextPath}/System/caigou/zhaobiao/bangding/WaigoujianpinciZi_add.jsp?waigoujianpinciid="+${waigoujianpinci.id}));
			var url=encodeURI(encodeURI("markIdAction!toaddwaigoujianpinci.action?waigoujianpinci.id=${waigoujianpinci.id}&status=${status}"));
			$("#showProcess").attr("src", url);	
		   chageDiv('block');
	}
		function toUpdatezhaobiao(id){
			var url=encodeURI(encodeURI("markIdAction!toUpdatewaigoujianpinciZi.action?waigoujianpinciZi.id="+id+"&status=${status}"));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	</SCRIPT>

</html>
