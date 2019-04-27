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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看企业会员负责人信息</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
						
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font color="red">${errorMessage}</font>
				<form action="CompanyVIPAction_findcompanvipList.action" method="get">
					<table class="table">
						<tr>
							<th>
								企业名称
							</th>
							<td>
								<input type="text" name="companvip.name"/>
							</td>
							<th>
								填表时间
							</th>
							<td>
								<input type="text" name="companvip.writeTime" 
							id="writeTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" class="Wdate" />
							</td>
						</tr>
						<tr>
							<th>
								会员编号
							</th>
							<td>
								<input type="text" name="companvip.vipNo"/>
							</td>
							<th>
							</th>
							<td >
								<input type="submit" value="查找" style="width: 75px; height: 35px;">
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>
							序号
						</th>
						<th>
							企业名称
						</th>
						<th>
							企业负责人
						</th>
						<th>
							会员编号
						</th>
						<th>
							成立时间
						</th>
						<th>
							注册资金
						</th>
						<th>
							Eamil
						</th>
						<th>
							营业执照注册号
						</th>
						<th>
							企业性质
						</th>
						<th>
							所属行业
						</th>
						<th>
							企业网址
						</th>
						<th>
							企业地址
						</th>
						<th>
							邮编
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="cpList" value="companvipList" status="statussdf">
   					<s:if test="#statussdf.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#statussdf.index+1" />
					</td>
					<td>
						${cpList.name}
					</td>
					<td>
						${cpList.companyboss.name}
					</td>
					<td>
						${cpList.vipNo}
					</td>
					<td>
					${cpList.foundingTime}
					</td>
					<td>
					${cpList.rcapital}
					</td>
					<td>
					${cpList.email}
					</td>
					<td>
					${cpList.number}
					</td>
					<td>
					${cpList.typexz}
					</td>
					<td>
					${cpList.industry}
					</td>
					<td>
					${cpList.website}
					</td>
					<td>
					${cpList.address}
					</td>
					<td>
					${cpList.zipcode}
					</td>
					<td>
						<a href="javascript:;" onclick="mingxi(${cpList.id})">查看详细</a>
					</td>
					</tr>
   				 </s:iterator>
   				 <tr>
				<td colspan="14" align="right">
								第
					<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
							页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
									
				</td>
			</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function mingxi(id){
	
	window.open("CompanyVIPAction_findcompanvipById.action?id="+id);
}
function tanchu(id){
	document.getElementById("xiugaiIframe").src="CompanyVIPAction_findcbById.action?id="+id;
	chageDiv('block')
}

</SCRIPT>
	</body>
</html>
