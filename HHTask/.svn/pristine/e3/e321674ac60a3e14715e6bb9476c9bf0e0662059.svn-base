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
				<font color="red" size="5">${errorMessage}</font>
				<form action="IntelligentDiagnosisAction_findAllQRCodeList.action"
					method="post">
					<strong>姓名:</strong>
					<input type="text" name="qrcodeku.userName" />
					<input type="hidden" value="${status}" name="status" />
					<input type="submit" value="查询" />
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>
							序号
						</th>
						<th>
							姓名
						</th>
						<th>
							手机号
						</th>
						<th>
							二维码编码
						</th>
						<th>
							上一层编码
						</th>
						<th>
							是否可发展下家
						</th>
						<th>
							产生时间
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:if test="status != 'person'">
						<s:iterator value="qrcodekuList" id="pageList1"
							status="pageStatus">
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
								${pageList1.userName}
							</td>
							<td>
								${pageList1.phoneNumber}
							</td>
							<td>
								${pageList1.no}
							</td>
							<td>
								${pageList1.fatherNo}
							</td>
							<td>
								<s:if test="#pageList1.ischild == 'yes'">
								是
							</s:if>
								<s:else>
								否
							</s:else>
							</td>
							<td>
								${pageList1.addtime}
							</td>
							<td>
								<s:if test="status!='person' && #pageList1.ischild != 'yes'">
									<a
										href="IntelligentDiagnosisAction_updateQRcode1.action?qrcodeku.id=${pageList1.id}&cpage=${cpage}">发展下家</a>/
							</s:if>
								<a href="javascript:;" onclick="xiazai('${pageList1.name}')">下载二维码</a>
							</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<s:if test="qrcodeku!=null && qrcodeku.id!=null">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
								<td colspan="8" style="background-color: #ece9d8">
									个人二维码信息
								</td>
							</tr>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
								<td>
									1
								</td>
								<td>
									${qrcodeku.userName}
								</td>
								<td>
									${qrcodeku.phoneNumber}
								</td>
								<td>
									${qrcodeku.no}
								</td>
								<td>
									${qrcodeku.fatherNo}
								</td>
								<td>
									<s:if test="qrcodeku.ischild == 'yes'">
								是
							</s:if>
									<s:else>
								否
							</s:else>
								</td>
								<td>
									${qrcodeku.addtime}
								</td>
								<td>
									<a href="javascript:;" onclick="xiazai('${qrcodeku.name}')">下载二维码</a>
								</td>
							</tr>
						</s:if>
						<s:iterator value="qrcodekuList" id="pageList1"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr align="center">
									<td colspan="8" style="background-color: #ece9d8">
										下家二维码信息
									</td>
								</tr>
							</s:if>
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
								${pageList1.userName}
							</td>
							<td>
								${pageList1.phoneNumber}
							</td>
							<td>
								${pageList1.no}
							</td>
							<td>
								${pageList1.fatherNo}
							</td>
							<td>
								<s:if test="#pageList1.ischild == 'yes'">
								是
							</s:if>
								<s:else>
								否
							</s:else>
							</td>
							<td>
								${pageList1.addtime}
							</td>
							<td>
								<a href="javascript:;" onclick="xiazai('${pageList1.name}')">下载二维码</a>
							</td>
							</tr>
						</s:iterator>
					</s:else>
					<tr>

						<td colspan="8" align="right">
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
function xiazai(name){
			//对中文进行加密
			var fileName1 = encodeURI(encodeURI(name));
			location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1;
	}

</SCRIPT>
	</body>
</html>
