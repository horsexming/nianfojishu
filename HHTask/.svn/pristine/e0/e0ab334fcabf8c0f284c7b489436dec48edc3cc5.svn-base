<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<a name="showTop"></a>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
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
									<span id="title">您正进行系统需求添加</span>
								</td>
								<td align="right">
									<img alt="" src="<%=basePath%>/images/closeImage.png"
										width="30" id="closeTcDiv" height="32"
										onclick="chageDiv('none')">
								</td>
							</tr>
						</table>
						<div id="operatingDiv"
							style="background-color: #ffffff; width: 100%;">
							<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
								hspace="0" vspace="0" frameborder="0" scrolling="yes"
								style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>

						</div>
					</div>
				</div>
				<h2 style="">
					系统需求信息
				</h2>
				<div align="center">
					<a href="javascript:;" onclick="tanchu('')">添加</a>

					<br />
					<form
						action="${pageContext.request.contextPath}/SystemDemandAction_showsdByConditon.action"
						method="post">
						<table class="table" width="90%">
							<tr>
								<th>
									需求编号:
								</th>
								<td>
									<input type="text" name="systemDemand.sdNum"
										value="${systemDemand.sdNum}" />
								</td>

								<th>
									需求状态:
								</th>
								<td>
									<select name="systemDemand.status" class="select">
										<option value="">
											--请选择--
										</option>
										<s:iterator value="{'审核中','解决中','待确认','确认关闭'}" id="item">
											<s:if test="systemDemand.status==#item">
												<option value="${item}" selected="selected">
													${item}
												</option>
											</s:if>
											<s:else>
												<option value="${item}">
													${item}
												</option>
											</s:else>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									需求简称:
								</th>
								<td>
									<input type="text" name="systemDemand.sdShortName"
										value="${systemDemand.sdShortName}" />
								</td>
								<th>
									功能名称:
								</th>
								<td>
									<input type="text" name="systemDemand.functionName"
										value="${systemDemand.functionName}" />
								</td>
							</tr>
							<tr>
								<th>
									需求类型:
								</th>
								<td>
									<select name="systemDemand.sdType" class="select">
										<option value="">
											--请选择--
										</option>
										<s:iterator value="{'系统新需求','系统问题'}" id="item">
											<s:if test="systemDemand.sdType==#item">
												<option value="${item}" selected="selected">
													${item}
												</option>
											</s:if>
											<s:else>
												<option value="${item}">
													${item}
												</option>
											</s:else>
										</s:iterator>
									</select>
								</td>
								<th>
									需求级别:
								</th>
								<td>
									<input type="text" name="sytemDemand.sdLeave"
										value="${sytemDemand.sdLeave}" />
								</td>
							</tr>


							<tr>
								<th colspan="4">
									<input type="submit" value="查询" class="button">
									<input type="button" value="查询全部" class="button"
										onclick="location.href='${pageContext.request.contextPath}/SystemDemandAction_showsdByConditon.action?pageStatus=all'" />
								</th>
							</tr>
						</table>
					</form>

				</div>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							需求简称
						</th>
						<th>
							需求添加人
						</th>
						<th>
							功能名称
						</th>
						<th>
							需求类型
						</th>
						<th>
							需求级别
						</th>
						<th>
							添加时间
						</th>
						<th>
							需求描述
						</th>
						<th>
							需求文件
						</th>
						<th>
							审批状态
						</th>
						<th>
							需求状态
						</th>

						<th>
							操作
						</th>
					</tr>
					<s:iterator value="sdList" id="sd" status="pageStatus">
						<tr align="center">
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
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${sd.sdShortName}
							</td>
							<td>
								${sd.userName}
							</td>
							<td>
								${sd.functionName}
							</td>
							<td>
								${sd.sdType}
							</td>
							<td>
								${sd.sdLeave}
							</td>
							<td>
								${sd.addTime}
							</td>
							<td align="left">
								<c:if test="${fn:length(sd.sdDesc)>15 }">  
				                         ${fn:substring(sd.sdDesc, 0, 15)}...  
				                   </c:if>
								<c:if test="${fn:length(sd.sdDesc)<=15 }">  
				                         ${sd.sdDesc }  
				                   </c:if>
							</td>
							<td>
								<s:if test="#sd.demandFile!=null">
									<a
										href="${pageContext.request.contextPath}/upload/file/${sd.demandFile}">${sd.demandFile}</a>
								</s:if>
								<s:else>
										无
								</s:else>
							</td>
							<td>
								<a
									href="CircuitRunAction_findAduitPage.action?id=${sd.epId}">
									${sd.epStatus}</a>
							</td>
							<td>
								${sd.status}
							</td>
							<td>
								<a
									href="SystemDemandAction_getSystemDemandById.action?id=${sd.id}">查看详细</a>/
								<a href="javascript:;" onclick="tanchu('${sd.id}')">修改</a>/
								<a href="SystemDemandAction_delSystemDemand.action?id=${sd.id}"
									onclick="return confirm('确定还要删除吗？')">删除</a>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="16" align="right">
							共
							<s:property value="total" />
							页 第
							<s:property value="cpage" />
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
	location.href="#showTop";
	if(num!=""){
		document.getElementById("xiugaiIframe").src = "${pageContext.request.contextPath}/SystemDemandAction_getSystemDemandById.action?id="+num+"&pageStatus=update";
	}else{
		document.getElementById("xiugaiIframe").src = "${pageContext.request.contextPath}/System/systemAbout/systemDemand_add.jsp";
	}
	chageDiv('block')
}

</SCRIPT>
	</body>
</html>
