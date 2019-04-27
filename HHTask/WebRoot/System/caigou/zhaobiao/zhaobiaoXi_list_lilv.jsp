
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
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
			 	<table class="table" style="width: 100%">
						<tr  bgcolor="#c0dcf2">
							<th>序号</th>
							<th>年份</th>
							<th>年利率</th>
							
							<th>操作</th>
						</tr>
				<s:iterator value="list" id="zhaobiaoXi"  status="pageIndex">
						<tr  align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb');">
							<td>${pageIndex.index+1}</td>
							<td>${zhaobiaoXi.nianfen}</td>
							<td>${zhaobiaoXi.lilv}</td>
							<td>
						    <a onclick="lilv(${zhaobiaoXi.id})">选择年利率</a>
							
							<s:if test='#zhaobiaoXi.t11==null'>
								<!-- 
								<select id="zbx${pageIndex.index}" > 
									<s:iterator value="listAllDept" id="pagelistAllDept">
										<option>${pagelistAllDept.h2}</option>
									</s:iterator>
								</select>
								<input type="submit" value="确定回款方式"  onclick="fun1(${zhaobiaoXi.id},'zbx${pageIndex.index}');" />
								 -->
									
								</s:if>
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
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
				
	function lilv(id){
	     alert(id);
			var url=encodeURI(encodeURI("zhaobiaoAction!lilv.action?zhaobiaoXi.id="+id));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
</script>
	</body>
</html>
