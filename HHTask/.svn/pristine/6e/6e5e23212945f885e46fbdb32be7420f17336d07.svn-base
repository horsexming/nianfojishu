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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
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
								<span id="title"></span>
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
			<div align="center">

				<div id="jionVip_div" style="display: none;">
					<a href="javascript:;" onclick="jionVip('${campanyname}')">感觉不错，加入会员</a>
				</div>
				<form action="IntelligentDiagnosisAction_finaAllisList.action"
					method="post">
					<strong>公司名称:</strong>
					<input type="text" name="cn.campanyname" />
					<input type="hidden" value="${status}" name="status"/>
					<input type="submit" value="查询" style="width: 75px; height: 35px;">
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>
							序号
						</th>
						<th>
							公司名称
						</th>
						<th>
							测试编号
						</th>
						<th>
							联系人
						</th>
						<th>
							联系人手机号
						</th>
						<th>
							公司岗位总人数
						</th>
						<th>
							使用系统前公司人工成本(万元)
						</th>
						<th>
								操作
						</th>
					</tr>
					<s:iterator value="cnList" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageList.campanyname}
						</td>
						<td>
							${ pageList.ceshiNo}
						</td>
						<td>
							${ pageList.lianxiren}
						</td>
						<td>
							${ pageList.phone}
						</td>
						<td>
							${pageList.totalman}
						</td>
						<td>
							${pageList.total}
						</td>
						<td>
							<a href="javascript:;"
								onclick="tanchu('${pageList.id}','mingxi')">详情</a>/
							<a href="javascript:;"
								onclick="tanchu('${pageList.id}','update')">修改</a>/
							<a
								href="IntelligentDiagnosisAction_delcn.action?cn.id=${pageList.id}"
								onclick="confirm('确定要删除吗')">删除</a>
						</td>
					</s:iterator>
					<s:if test="errorMessage ==null || errorMessage == ''">
						<tr>

							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="12" align="center">
								<font color="red" size="5">${errorMessage}</font>
							</td>

						</tr>
					</s:else>
				</table>


			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function jionVip(obj){
var fileName1 = encodeURI(encodeURI(obj));
window.location.href="CompanyVIPAction_initadd.action?campanyname="+fileName1;
}
	
function tanchu(num,status){
	document.getElementById("xiugaiIframe").src="IntelligentDiagnosisAction_findHzByid.action?id="+num+"&status="+status;
		chageDiv('block')
}
		</SCRIPT>

	</body>
</html>
