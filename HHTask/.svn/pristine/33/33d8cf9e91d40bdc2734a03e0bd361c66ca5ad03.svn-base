
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
			  <form action="zhaobiaoAction!listzhongbiaoUsers.action" method="post"  theme="simple">
			  <table class="table">
			   		<tr><th align="center">标题：</th><td><input type="text" id="zhaobiao.title" name="zhaobiao.title" /></td>
			   			<th align="center">负责人：</th><td><input type="text" id="zhaobiao.fuze" name="zhaobiao.fuze" /></td>
			   			<td rowspan="3"><input type="submit" value="查询"   class="input"/>	
			   		</tr>
			   		<tr>
			   			<th align="center">起：</th><td>
			   			
			   				<input class="Wdate" type="text" id="zhaobiao.moban"
									name="zhaobiao.moban"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
			   			</td>
			   			<th align="center">止：</th><td>
			   			<input class="Wdate" type="text" id="zhaobiao.kongxian"
									name="zhaobiao.kongxian"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
			   			</td>
			   			
			   		</tr>
			   		
			  
			  </table>
			  </form>
			
			
			
			
				<table class="table">
					<tr bgcolor="#c0dcf2">
						<th>
							序号
						</th>
						<th>
							招标名称
						</th>
						<th>
							负责人
						</th>
						<th>
							负责人电话
						</th>

						<th>
							开始时间
						</th>
						<th>
							结束时间
						</th>
						<th>
							发布人
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="zhaobiao1" status="pageIndex">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${pageIndex.index+1}
						</th>
						<th>
							${zhaobiao1.title}
						</th>
						<th>
							${zhaobiao1.fuze}
						</th>
						<th>
							${zhaobiao1.phone}
						</th>

						<th>
							${zhaobiao1.moban}
						</th>
						<th>
							${zhaobiao1.kongxian}
						</th>
						<th>
							${zhaobiao1.faburen}
						</th>
						<th>
							招标结束
						</th>
						<th>
				<a	href="zhaobiaoAction!zhtoubiaogyschankan.action?zhaobiao.id=${zhaobiao1.id}">查看中标结果</a>
			
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
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>
