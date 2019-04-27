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
							<span id="title">您正在进行手动下单修改操作</span>
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
				<form action="JiaoXiaoKaoHeAction_findAllzbSjZk.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								部门:
							</th>
							<th align="left">
								<input type="text" value="${zbSjZk.dept}" name="zbSjZk.dept"/>
							</th>
							<th align="right">
								姓名:
							</th>
							<th align="left">
								<input type="text" value="${zbSjZk.name}" name="zbSjZk.name"/>
							</th>
						</tr>
						<tr>
							<th align="right">
								月份:
							</th>
							<th align="left">
								<input type="text" value="${zbSjZk.months}" class="Wdate"   name="zbSjZk.months" 
								onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</th>
							<th align="right">
								职位:
							</th>
							<th align="left">
								<input type="text" value="${zbSjZk.rank}"  name="zbSjZk.rank" 
								/>
							</th>
						</tr>
					</table>
					<input type="submit" value="查询" class="input"/>
					<input type="button" value="添加" class="input" onclick="tanchu(0)"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" >
						<th rowspan="3">
							序号
						</th>
						<th rowspan="3">
							部门
						</th>
						<th rowspan="3">
							姓名
						</th>
						<th rowspan="3">
							职位
						</th>
						<th rowspan="3">
							月份
						</th>
						<th colspan="6">
							质量指标
						</th>
						<th colspan="3" rowspan="2">
							总销售产值(亿)
						</th>
						<th colspan="3" rowspan="2">
							人均销售(万)
						</th>
						<th colspan="3" rowspan="2">
							实际人数
						</th>
						<th colspan="3" rowspan="2">
							三按两遵守
						</th>
						<th colspan="3" rowspan=2">
							变动费用(万)
						</th>
						<th rowspan="3">
							总分
						</th>
						<th rowspan="3">
							排名
						</th>
						<th rowspan="3">
							操作
						</th>
					</tr>
					<tr  bgcolor="#c0dcf2">
						<th colspan="3">
							LAR
						</th>
						<th colspan="3">
							RID
						</th>
					</tr>
					<tr  bgcolor="#c0dcf2">
						<th>
							目标
						</th>
						<th>
							实际
						</th>
						<th>
							得分
						</th>
						<th>
							目标
						</th>
						<th>
							实际
						</th>
						<th>
							得分
						</th>
						<th>
							目标
						</th>
						<th>
							实际
						</th>
						<th>
							得分
						</th>
						<th>
							目标
						</th>
						<th>
							实际
						</th>
						<th>
							得分
						</th>
						<th>
							目标
						</th>
						<th>
							实际
						</th>
						<th>
							得分
						</th>
						<th>
							目标
						</th>
						<th>
							实际
						</th>
						<th>
							得分
						</th>
						<th>
							目标
						</th>
						<th>
							实际
						</th>
						<th>
							得分
						</th>
					</tr>
					<s:iterator value="zbSjZkList" id="pagezbSjZk" status="statussdf">
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
									${pagezbSjZk.dept}
								</td>
								<td>
									${pagezbSjZk.name}
								</td>
								<td>
									${pagezbSjZk.rank}
								</td>
								<td>
									${pagezbSjZk.months}
								</td>
								<td>
									${pagezbSjZk.larzhimubiao}
								</td>
								<td>
									${pagezbSjZk.larzhibiao}
								</td>
								<td>
									${pagezbSjZk.larscore}
								</td>
								<td>
									${pagezbSjZk.ridmubiao}
								</td>
								<td>
									${pagezbSjZk.ridzhibiao}
								</td>
								<td>
									${pagezbSjZk.ridscore}
								</td>
								<td>
									${pagezbSjZk.zczmubiao}
								</td>
								<td>
									${pagezbSjZk.zczzhibiao}
								</td>
								<td>
									${pagezbSjZk.zczscore}
								</td>
								<td>
									${pagezbSjZk.rjxsmubiao}
								</td>
								<td>
									${pagezbSjZk.rjxszhibiao}
								</td>
								<td>
									${pagezbSjZk.rjxsscore}
								</td>
								<td>
									${pagezbSjZk.sjrsmubiao}
								</td>
								<td>
									${pagezbSjZk.sjrszhibiao}
								</td>
								<td>
									${pagezbSjZk.sjrsscore}
								</td>
								<td>
									${pagezbSjZk.salzsmubiao}
								</td>
								<td>
									${pagezbSjZk.salzszhibiao}
								</td>
								<td>
									${pagezbSjZk.salzsscore}
								</td>
								<td>
									${pagezbSjZk.bdfymubiao}
								</td>
								<td>
									${pagezbSjZk.bdfyzhibiao}
								</td>
								<td>
									${pagezbSjZk.bdfyscore}
								</td>
								<td>
									${pagezbSjZk.sumscocer}
								</td>
								<td>
									${pagezbSjZk.ranking}
								</td>
								<td>
									<a href="javaScritp:;" onclick="tanchu(${pagezbSjZk.id})">修改</a>/
									<a href="JiaoXiaoKaoHeAction_delzbSjZk.action?dud.id=${pagezbSjZk.id}" onclick="return confirm('确定要删除吗?')">删除</a>
								</td>
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
function tanchu(num){
	if(num>0){
		document.getElementById("xiugaiIframe").src = "JiaoXiaoKaoHeAction_findzbSjZkbyId.action?id="+num;
	}else{
		document.getElementById("xiugaiIframe").src = "./System/jxkh/zbSjZk_add.jsp";
	}
	chageDiv('block');
}
</SCRIPT>
	</body>
</html>
