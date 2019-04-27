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
				<form action="purchasedPartsInputAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								件号（markId）：
								<input type="hidden" value="${pageStatus}" name="pageStatus">
								<input type="text" name="purchasedPartsInput.markId"
									value="<s:property value="purchasedPartsInput.markId"/>" />
							</td>
							<td align="center">
								批次（selfCard）：
								<input type="text" name="purchasedPartsInput.selfCard"
									value="<s:property value="purchasedPartsInput.selfCard"/>" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr>
						<th colspan="12"
							style="height: 35px; color: #ffffff; background-color: green;">
							外购件入库记录
						</th>
					</tr>
					<s:if test="successMessage!=null">
						<tr>
							<td colspan="15" align="center" style="color: red">
								${successMessage}
							</td>
						</tr>
					</s:if>
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							供应商
						</th>

						<th align="center">
							件号
						</th>
						<th align="center">
							零件名称
						</th>
						<th align="center">
							总数量
						</th>
						<th>
							已入库次数
						</th>
						<th align="center">
							批次号
						</th>
						<th align="center">
							添加时间
						</th>
						<th align="center">
							送货单号
						</th>
						<th align="center">
							状态
						</th>
						<td colspan="2">
							操作
						</td>
					</tr>
					<s:iterator value="purchasedPartsInputList" id="pagePPINput"
						status="pageStatus">
						<s:if test="#pageStatus2.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 50px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pagePPINput.company}
						</td>
						<td>
							${pagePPINput.markId}
						</td>
						<td>
							${pagePPINput.name}
						</td>
						<td>
							${pagePPINput.inCount}
						</td>
						<td>
							<s:property value="#pagePPINput.hasruku" />
						</td>

						<td>
							${pagePPINput.selfCard}
						</td>
						<td>
							${pagePPINput.time}
						</td>
						<td>
							${pagePPINput.inNumber}
						</td>
						<td>
							${pagePPINput.status}
						</td>
						<td colspan="2">
							<s:if
								test="#pagePPINput.status==null||#pagePPINput.status=='未检测'">
								<input type="button" value="修改(update)"
									style="width: 60px; height: 30px;"
									onclick="update(${pagePPINput.id})" />

								<input type="button" value="删除(delete)"
									style="width: 60px; height: 30px;"
									onclick="todelete(${pagePPINput.id })" />
							</s:if>
							<s:else>
						  不可修改和删除
						 </s:else>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function update(id) {
	window.location.href = "purchasedPartsInputAction_toupdate.action?purchasedPartsInput.id=" + id;
     }
function todelete(id) {
	window.location.href = "purchasedPartsInputAction_delete.action?purchasedPartsInput.id=" + id;
   }
		</SCRIPT>
	</body>
</html>
