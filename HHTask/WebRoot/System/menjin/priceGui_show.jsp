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
				<s:if test="bangpriceList!=null">
					<table class="table" align="center">
						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td>
								序号
							</td>
							<td>
								件号
								<br>
								(Item Number)
							</td>
							<td>
								名称
								<br>
								(Name)
							</td>
							<td>
								签订方
								<br>
								(The Signing Party)
							</td>
							<td>
								价格(含税)
								<br>
								(Price (including tax))
							</td>
							<td>
								价 格 (不含税)
								<br>
								(Price (excluding tax))
							</td>
							<td>
								合同编号
								<br>
								(Contract Number)
							</td>
							<td>
								档案编号
								<br>
								(file Number)
							</td>
							<td>
								价格有效期
								<br>
								(Price valid)
							</td>
						</tr>
						<s:iterator id="pageprice" value="bangpriceList"
							status="ststusfunction">
							<s:if test="#ststusfunction.first">
								<tr>
									<td colspan="10" style="font-family: 微软雅黑; font-weight: bold;"
										align="center">
										<font>在柜档案</font>
									</td>
								</tr>
							</s:if>
							<s:if test="#ststusfunction.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#ststusfunction.index%2==1">
								</s:if>
								<s:else>
								</s:else>
								<s:property value="#ststusfunction.index+1" />
							</td>
							<td>
								${pageprice.partNumber}
							</td>
							<td>
								${pageprice.name}
							</td>
							<td>
								${pageprice.type}
							</td>
							<td>
								${pageprice.hsPrice}
							</td>
							<td>
								${pageprice.bhsPrice}
							</td>
							<td>
								${pageprice.contractNumber}
							</td>
							<td>
								${pageprice.fileNumber}
							</td>
							<td>
								${pageprice.pricePeriodEnd}
							</td>
						</s:iterator>
					</table>
				</s:if>
				<s:elseif test="sealLogTypeList!=null">
					<font>在柜印章</font>
					<table class="table" align="center">
						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td>
								序号
							</td>
							<td>
								印章类型
							</td>
						</tr>
						<s:iterator id="pageprice" value="sealLogTypeList"
							status="ststusfunction">
							<s:if test="#ststusfunction.first">
								<tr>
									<td colspan="2" style="font-family: 微软雅黑; font-weight: bold;"
										align="center">
										<font>在柜印章</font>
									</td>
								</tr>
							</s:if>
							<s:if test="#ststusfunction.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#ststusfunction.index%2==1">
								</s:if>
								<s:else>
								</s:else>
								<s:property value="#ststusfunction.index+1" />
							</td>
							<td>
								${pageprice.slname}
							</td>
						</s:iterator>
					</table>
				</s:elseif>
				<s:elseif test="fileManagerList!=null">
					<div>
						<h3>机密档案</h3>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									文件类型
								</th>
								<th align="center">
									文件名称
								</th>
								<th align="center">
									文件性质
								</th>
								<th align="center">
									文件存放位置
								</th>
								<th align="center">
									存档编号
								</th>
								<th align="center">
									备注
								</th>
								<th>
									文件编号
								</th>
								<th>
									文件数量
								</th>
								<th align="center">
									文件签订日期
								</th>
								<th>
									是否申请存档
								</th>
								<th>
									申请存档数量
								</th>
								<th>
									档案柜号
								</th>
							</tr>
							<s:iterator value="fileManagerList" status="se" id="bxd">
								<s:if test="#se.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#se.index+1" />
								</td>
								<td>
									${bxd.fileType}
								</td>
								<td>
									${bxd.fileName}
								</td>
								<td>
									${bxd.fileXingzhi}
								</td>
								<td>
									${bxd.fileLocation}
								</td>
								<td>
									${bxd.filearchivesNo}
								</td>
								<td>
									${bxd.fileRemarks}
								</td>
								<td>
									${bxd.fileNumber}
								</td>
								<td>
									${bxd.fileCount}
								</td>
								<td>
									${bxd.fileSignDate}
								</td>
								<td>
									${bxd.isCundang}
								</td>
								<td>
									${bxd.cundangCount}
								</td>
								<td>
									${bxd.danganWeizhi}
								</td>
							</s:iterator>
						</table>
					</div>
				</s:elseif>
				<s:elseif test="cwCertificateList!=null">
					<div>
						<h3>财务档案</h3>
						<form action="AccessEquipmentAction_selectPrice.action?tag=${tag}"
							method="post">
							<table class="table" align="center">
								<tr>
									<td align="right">
										编号：
									</td>
									<td align="left">
										<input type="text" name="certificate.number" />
										<input type="hidden" name="id" value="${id}"/>
									</td>
									<td align="right">
										凭证日期：
									</td>
									<td align="left">
										<input type="text" name="certificate.pzDate" class="Wdate" 
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										凭证简介：
									</td>
									<td align="left">
										<input type="text" name="certificate.introduction" />
									</td>
									<td align="center" colspan="2">
										<input type="submit" style="width: 100px; height: 40px; margin-left: 70px;"
											value="查询(select)" />
									</td>
								</tr>
							</table>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									凭证编号
								</th>
								<th align="center">
									凭证日期
								</th>
								<th>
									凭证简介
								</th>
							</tr>
							<s:iterator value="cwCertificateList" status="se" id="bxd">
								<s:if test="#se.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#se.index+1" />
								</td>
								<td>
									${bxd.number}
								</td>
								<td>
									${bxd.pzDate}
								</td>
								<td>
									${bxd.introduction}
								</td>
							</s:iterator>
						</table>
					</div>
				</s:elseif>
				<s:else>
					<br />
					<br />
					<s:if test="fileManagerList==null">
						<font color="red" size="18px">${errorMessage}</font>
					</s:if>
				</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function chageBgcolor(obj) {
	obj.style.background = "#c0dcf2";
}
function outBgcolor(obj, oldColor) {
	obj.style.background = oldColor;
}
</script>
	</body>
</html>
