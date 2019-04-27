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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					档案存档申请详细
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<table class="table">
					<tr>
						<th align="right">
							存档室名称
						</th>
						<td align="left" colspan="2">
							${dangAn.cdAceName}
						</td>
						<th align="right">
							存档室门禁编号
						</th>
						<td align="left" colspan="2">
							${dangAn.cdAceNum}
						</td>
					</tr>
					<tr>
						<th align="right">
							申请人手机号
						</th>
						<td align="left" colspan="2">
							${dangAn.sqTel}
						</td>
						<th align="right">
							申请存档日期
						</th>
						<td align="left" colspan="2">
							${dangAn.shenqingdate}
						</td>
					</tr>
					<s:if test="tag=='fp'">
						<tr>
							<th align="right">
								申请张数：
							</th>
							<td align="left" colspan="2">
								${dangAn.num}
							</td>
							<th align="right">
								开票金额：
							</th>
							<td align="left" colspan="2">
								${dangAn.money}
							</td>
						</tr>
					</s:if>
					<tr>
						<th align="right">
						取档原因
						</th>
						<td colspan="5">
							${dangAn.quDangReason}
						</td>
					</tr>
					<tr>
						<th colspan="6">
							存取明细
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							存取类型
						</td>
						<td align="center">
							档案名称
						</td>
						<td align="center">
							档案编号
						</td>
						<td align="center">
							存放柜号
						</td>
						<s:if test="tag=='pz'||tag=='fp'">
						</s:if>
						<s:else>
							<td align="center">
								查看附件
							</td>
						</s:else>
					</tr>
					<s:iterator value="archiveUnarchiverApltList" id="samples"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.cqType}
						</td>
						<td align="center">
							${samples.daName}
						</td>
						<td align="center">
							${samples.daNum}
						</td>
						<td align="center">
							${samples.daGuihao}
						</td>
						<s:if test="tag=='pz'||tag=='fp'">
						</s:if>
						<s:else>
							<td align="center">
								<s:if test="#samples.fileName!=null&&#samples.fileName!=''">
									<a  onclick="xiazai('${samples.fileName}')">查看附件</a>
								</s:if>
							</td>
						</s:else>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//下载合同
	function xiazai(obj){
			//对中文进行加密
			var fileName1 = encodeURI(encodeURI(obj));
			location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1;
	}
</script>
	</body>
</html>
