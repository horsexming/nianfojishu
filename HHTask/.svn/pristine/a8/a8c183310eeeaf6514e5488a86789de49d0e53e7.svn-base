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
	<title></title>
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
								<span id="title">您正在进行辅料管理操作</span>
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
							style="width: 98%; height: 800px; margin: 0px; padding: 0px;"></iframe>

					</div>
				</div>
			</div>
			<div align="center">
				<form action="FuLiaoZhuiSuAction_findAllflzs.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								订单编号
							</th>
							<td>
								<input type="text" value="${flzs.orderNum }" name="flzs.orderNum" />
							</td>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="${flzs.ywmarkId }" name="flzs.ywmarkId" />
							</td>
						</tr>
						<tr>
							<th align="right">
								生产日期
							</th>
							<td>
								<input type="text" value="${flzs.scdate }" name="flzs.scdate" />
							</td>
							<th align="right">
								负责人
							</th>
							<td>
								<input type="text" value="${person }" name="person" />
							</td>
						</tr>
					</table>
						<input type="submit" class="input" value="查询"/>
						<input type="button" value="添加" class="input"  onclick="tanchu('')"/>
				</form>	
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							订单编号(内部)
						</th>
						<th>
							业务件号
						</th>
						<th>
							总成批次
						</th>
						<th>
							件号
						</th>
						<th>
							名称
						</th>
						<th>
							生产数量
						</th>
						<th>	
							生产日期
						</th>
						<th>
							工位
						</th>
						<th>
							设备编号
						</th>
						<th>
							组分A
						</th>
						<th>
							组分B
						</th>
						<th>
							负责人
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="flzslist" status="statussdf" id="pageflzs">
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
							<td>${pageflzs.orderNum}</td>
							<td>${pageflzs.ywmarkId}</td>
							<td>${pageflzs.rootselfCard}</td>
							<td>${pageflzs.markId}</td>
							<td>${pageflzs.proName}</td>
							<td>${pageflzs.num}</td>
							<td>${pageflzs.scdate}</td>
							<td>${pageflzs.gongwei}</td>
							<td>${pageflzs.shebeiNo}</td>
							<td>${pageflzs.groupA}</td>
							<td>${pageflzs.groupB}</td>
							<td>${pageflzs.person}</td>
							<td>
								<a href="javascript:;" onclick="tanchu('${pageflzs.id}')" >修改</a>/
								<a href="FuLiaoZhuiSuAction_delflzs.action?flzs.id=${pageflzs.id}" onclick="return confirm('确定要删除吗？')">删除</a>
							</td>
						</tr>
					</s:iterator>
					<tr>
								<td colspan="30" align="right">
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

function tanchu(num) {
	if(num==''){
		document.getElementById("xiugaiIframe").src = "./System/SOP/flzs/flzs_add.jsp";
	}else{
		document.getElementById("xiugaiIframe").src = "FuLiaoZhuiSuAction_findflzsById.action?id="+num;
	}
	chageDiv('block')
}
</SCRIPT>
	</body>
</html>
