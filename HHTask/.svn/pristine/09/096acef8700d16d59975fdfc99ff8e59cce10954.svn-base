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
		<div id="gongneng" >
			</div>
			
			<div align="center">
				<h3>
					待完善合同
				</h3>
				<form action="skillScoreAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								总成件号(或业务件号)：
								<input type="text" name="pwwApplyDetail.processInforWWApply.markId" value="<s:property value="pwwApplyDetail.processInforWWApply.markId"/>" />
							</td>
							<td align="center">
								总成批次：
								<input type="text" name="pwwApplyDetail.processInforWWApply.selfCard" value="<s:property value="pwwApplyDetail.processInforWWApply.selfCard"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								件号：
								<input type="text" name="pwwApplyDetail.markId" value="<s:property value="pwwApplyDetail.markId"/>" />
							</td>
							<td align="center">
								批次：
								<input type="text" name="pwwApplyDetail.selfCard" value="<s:property value="pwwApplyDetail.selfCard"/>" />
							</td>
						</tr>
						<tr>
						<td align="center">
								外委类型：
								<SELECT name="pwwApplyDetail.wwType">
									<option></option>
									<option>工序外委</option>
									<option>包工包料</option>
								</SELECT>
							</td>
							<td align="center">
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
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							总成批次
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							零件名称
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							批次
						</td>
						<td align="center">
							外委类型
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="pwwApplyDetailList" id="pageDetail" status="pageStatus">
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
							${pageDetail.rootMarkId}<br/><font color="red">(${pageDetail.ywMarkId})</font>
						</td>
						<td align="center">
							${pageDetail.rootSelfCard}
						</td>
						<td align="center">
							${pageDetail.markId}
						</td>
						<td align="center">
							${pageDetail.proName}
						</td>
						<td align="center">
							${pageDetail.processNOs}
						</td>
						<td align="center">
							${pageDetail.processNames}
						</td>
						<td align="center">
							${pageDetail.selfCard}
						</td>
						<td align="center">
							${pageDetail.wwType}
						</td>	
						
						<td  colspan="2">
							<input type="button" value="核对合同"
								style="width: 80px; height: 30px;"
								onclick="checkHt(${pageDetail.id})" />
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
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function checkHt(id) {
	window.location.href = "ProcardAction!toCheckHt.action?id=" + id;
}
</script>
	</body>
</html>
