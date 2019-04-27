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
				<form action="JiaoXiaoKaoHeAction_findAllDudList.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								部门长:
							</th>
							<th align="left">
								<input type="text" value="${dud.leader}" name="dud.leader"/>
							</th>
							<th align="right">
								部门:
							</th>
							<th align="left">
								<input type="text" value="${dud.deptName}" name="dud.deptName"/>
							</th>
						</tr>
						<tr>
							<th align="right">
								职位:
							</th>
							<th align="left">
								<input type="text" value="${dud.rank}" name="dud.rank"/>
							</th>
							<th align="right">
								一线或二线:
							</th>
							<th align="left">
								<select name="dud.oneOrTwo" id="oneOrTwo">
									<option value="${dud.oneOrTwo}">
										<s:if test="dud.oneOrTwo==1">一线</s:if>
										<s:elseif test="dud.oneOrTwo==2">二线</s:elseif>
									</option>
									<option value="1">一线</option>
									<option value="2">二线</option>
								</select>
							</th>
						</tr>
					</table>
					<input type="submit" value="查询" class="input"/>
					<input type="button" value="添加" class="input" onclick="tanchu(0)"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							部门
						</th>
						<th>
							部门长
						</th>
						<th>
							一线或二线
						</th>
						<th>
							职位
						</th>
						<th>
							计算绩效
						</th>
						<th>
							部门分配
						</th>
						<th>
							制造评价
						</th>
						<th>
							增长效率
						</th>
						<th>
							部门长目标
						</th>
						<th>
							部门长周例会
						</th>
						<th>
							年度改善
						</th>
						<th>
							关联结构委外比
						</th>
						<th>
							岗位系数
						</th>
						<th>
							添加人
						</th>
						<th>
							添加时间
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="dudList" id="pagedud" status="statussdf">
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
									${pagedud.deptName}
								</td>
								<td>
									${pagedud.leader}
								</td>
								<td>
									${pagedud.oneOrTwo}
								</td>
								<td>
									${pagedud.rank}
								</td>
								<td>
									<s:if test="#pagedud.isJiaoXiao==1">
										需要
									</s:if>
									<s:else>
										不需要
									</s:else>
								</td>
								<td>
									<s:if test="#pagedud.isdeptFenPei==1">
										需要
									</s:if>
									<s:else>
										不需要
									</s:else>
								</td>
								<td>
									<s:if test="#pagedud.isZzPj==1">
										需要
									</s:if>
									<s:else>
										不需要
									</s:else>
								</td>
								<td>
									<s:if test="#pagedud.isZzXl==1">
										需要
									</s:if>
									<s:else>
										不需要
									</s:else>
								</td>
								<td>
									<s:if test="#pagedud.isbmzmb==1">
										需要
									</s:if>
									<s:else>
										不需要
									</s:else>
								</td>
								<td>
									<s:if test="#pagedud.isbmzZlh==1">
										需要
									</s:if>
									<s:else>
										不需要
									</s:else>
								</td>
								<td>
									<s:if test="#pagedud.isgsx==1">
										需要
									</s:if>
									<s:else>
										不需要
									</s:else>
								</td>
								<td>
									<s:if test="#pagedud.isWeiWaiJieGou==1">
										需要
									</s:if>
									<s:else>
										不需要
									</s:else>
								</td>
								<td>
									${pagedud.postCoefficient}
								</td>
								<td>
									${pagedud.addTime}
								</td>
								<td>
									${pagedud.addUserName}
								</td>
								<td>
									<a href="javaScritp:;" onclick="tanchu(${pagedud.id})">修改</a>/
									<a href="JiaoXiaoKaoHeAction_delDud.action?dud.id=${pagedud.id}" onclick="return confirm('确定要删除吗?')">删除</a>
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
		document.getElementById("xiugaiIframe").src = "JiaoXiaoKaoHeAction_findDudById.action?id="+num;
	}else{
		document.getElementById("xiugaiIframe").src = "./System/jxkh/dud_add.jsp";
	}
	chageDiv('block');
}
</SCRIPT>
	</body>
</html>
