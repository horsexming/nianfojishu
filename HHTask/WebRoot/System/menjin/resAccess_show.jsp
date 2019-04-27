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
				<h3>
					快递信息查询
				</h3>
				<form action="AccessEquipmentAction_showResAccess.action?tag=${tag}"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								快递单号：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="resAccess.cunCodes" />
							</td>
							<th align="center" style="width: 25%">
								添加人：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="resAccess.addName" />
							</td>
						</tr>
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								状态：
							</th>
							<td align="center" style="width: 25%">
								<SELECT name="resAccess.yxType" style="width: 152px;">
									<option value="">
										--状态--
									</option>
									<option value="0">
										有效
									</option>
									<option value="1">
										已使用
									</option>
									<option value="2">
										过期
									</option>
									<option value="3">
										取消
									</option>
								</SELECT>
							</td>
							<td align="center" colspan="2" style="width: 50%">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							类型
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							存物码
						</td>
						<td align="center">
							存状态
						</td>
						<td align="center">
							取物码
						</td>
						<td align="center">
							取状态
						</td>
						<td align="center">
							快递柜编号
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							添加人
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="resAccessList" id="samples" status="pageStatus">
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
							${samples.type}
						</td>
							<s:if test="#samples.yxType==0">
							<td align="center">
								有效
							</s:if>
							<s:elseif test="#samples.yxType==1">
							<td style="background-color:#32CD32" align="center">
								已使用
							</s:elseif>
							<s:elseif test="#samples.yxType==2">
							<td style="background-color:yellow" align="center">
								过期
							</s:elseif>
							<s:elseif test="#samples.yxType==3">
							<td style="background-color:red" align="center">
								取消
							</s:elseif>
						</td>
						<td align="center">
							${samples.cunCodes}
						</td>
						<s:if test="#samples.cuseType==0">
							<td align="center">
							未使用
						</s:if>
						<s:elseif test="#samples.cuseType==1">
							<td align="center">
							使用中
						</s:elseif>
						<s:elseif test="#samples.cuseType==2">
							<td style="background-color:#32CD32" align="center">
							已使用<br />
							${samples.copenTime}
						</s:elseif>
						</td>
						<td align="center">
							${samples.quCodes}
						</td>
						<s:if test="#samples.quseType==0">
							<td align="center">
							未使用
						</s:if>
						<s:elseif test="#samples.quseType==1">
							<td align="center">
							使用中
						</s:elseif>
						<s:elseif test="#samples.quseType==2">
							<td style="background-color:#32CD32" align="center">
							已使用<br />
							${samples.qopenTime}
						</s:elseif>
						</td>
						<td align="center">
							${samples.daGuihao}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
						<td align="center">
							${samples.addName}
						</td>
						<td align="center" colspan="2">
							<s:if test='tag=="aD"&&#samples.yxType==0&&#samples.cuseType!=2'>
								<a
									href="AccessEquipmentAction_updateResAccess.action?id=${samples.id}&tag=${tag}">取消</a>
							</s:if>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="13" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
