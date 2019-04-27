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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>项目合伙人管理<h3/>
				<br>
			<div align="center">
			<table  class="table">
					<tr>
					 <td colspan="8" align="center"> 件号：${quotedPrice.markId}</td>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							投资人
						</td>
						<td align="center">
							角色
						</td>
						<td align="center">
							部门
						</td>
						<s:if test="proStatus=='no'">
						<td align="center">
							当前票数
						</td>
						</s:if>
						<td align="center">
							被赞次数
						</td>
						<td align="center">
							被吐槽次数
						</td>
						<td align="center">
							操作
						</td>						
					</tr>
					<s:iterator value="investorOfQpList" id="pageiOfQp" status="pageStatus">
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
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="center">
							${pageiOfQp.name}
						</td>
						<td align="center">
							${pageiOfQp.role}
						</td>
						<td align="center">
							${pageiOfQp.dept}
						</td>
						<s:if test="proStatus=='no'">
						<td align="center">
						<s:if test="#pageiOfQp.zanCount==null">
							0
							</s:if>
							<s:else>
							${pageiOfQp.selectedCount}
							</s:else>
						</td>
						</s:if>
						<td align="center">
							<s:if test="#pageiOfQp.zanCount==null">
							0
							</s:if>
							<s:else>
							${pageiOfQp.zanCount}
							</s:else>
						</td>
						<td align="center">
						<s:if test="#pageiOfQp.zanCount==null">
							0
							</s:if>
							<s:else>
							${pageiOfQp.tocaoCount}
							</s:else>
						</td>
						<td align="center">
						<s:if test="proStatus=='no'">
							<input type="button" onclick="selectLeader(${quotedPrice.id},${pageiOfQp.investorId})" value="推选组长">
						</s:if>
						<s:if test="#pageiOfQp.role=='组长'">
							<input type="button" onclick="topj(${quotedPrice.id},${pageiOfQp.investorId})" value="评价">
						</s:if>
						</td>		
					</tr>
					</s:iterator>
					<tr>
						<s:if test="investorOfQpList.size()>0">
							<td colspan="7" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								没有找到项目组成员
						</s:else>
						</td>
					</tr>
			</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
	function selectLeader(id,id2){
		if (window.confirm("确定推选组长吗?")) {
			window.location.href="QuotedPrice_selectLeader.action?id="+id+"&id2="+id2;
		}
	}
	function topj(id,id2){
			window.location.href="QuotedPrice_toEvaluation.action?id="+id+"&id2="+id2;
	}
	</script>