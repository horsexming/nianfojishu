
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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
		<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序外委拆分</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
					</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<h3>拆分方式：以勾选的工序为节点拆分，如1,2,3,4,5工序勾选4工序则表示拆分文1,2,3和4,5工序两组</h3>
					<table class="table" id="">
					</table>
				</div>
			</div>
			</div>
		<div id="gongneng" style="width: 100%;">
		<div align="center">
					<input type="button"  value="导出" onclick="outPut();todisabledone(this)" data="downData"/>
				</div>
			<div align="center">
					<table class="table">
						<tr>
							<td align="center" colspan="6">
								<table class="table">
								<tr bgcolor="#c0dcf2" height="50px">
									<th>序号</th>
									<th>供应商</th>
									<th>件号</th>
									<th>零件名称</th>
									<th>版本</th>
									<th>版次</th>
									<th>批次</th>
									<th>工序号</th>
									<th>工序名称</th>
									<th>数量</th>
									<th>添加人</th>
									<th>添加时间</th>
									<th>外委类型</th>
									
									<th>状态</th>
								</tr>
								<s:iterator value="pwwApplyDetailList" id="pagedetail" status="step1">
							<s:if test="#step1.index%2==1">
								<s:if test="#pagedetail.priceId!=null">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')" class="showall">
								</s:if>
								<s:else>
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')" class="unshowselected">
								</s:else>
							</s:if>
							<s:else>
								<s:if test="#pagedetail.priceId!=null">
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')" class="showall">
								</s:if>
								<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')" class="unshowselected">
								</s:else>
							</s:else>
							<td align="center">
								<s:property value="#step1.index+1" />
							</td>
							<td align="center">
								<s:if test="#pagedetail.gysId==null">
									<font color="red">无供应商</font>
								</s:if>
								<s:else>
								<font color="green">${pagedetail.gysName}</font>
								</s:else>
							</td>
							<td align="center">
								${pagedetail.markId}
							</td>
							<td align="center">
								${pagedetail.proName}
							</td>
							<td align="center">
								${pagedetail.banbenNumber}
							</td>
							<td align="center">
								${pagedetail.banci}
							</td>
							<td align="center">
								${pagedetail.selfCard}
							</td>
							<td align="left">
								${pagedetail.processNOs}
							</td>
							<td align="left">
								${pagedetail.processNames}
							</td>
							<td align="center">
								${pagedetail.applyCount}
							</td>
							<td align="center">
								${pagedetail.userName}
							</td>
							<td align="center">
								${pagedetail.addTime}
							</td>
							<td align="center">
								${pagedetail.wwType}
							</td>
							<s:if test="#pagedetail.gysId!=null">
									<td align="center" style="background-color: green;"></td>
								</s:if>
								<s:else>
									<td align="center" style="background-color: red;"></td>
								</s:else>
							</tr>
							</s:iterator>
								</table>
							</td>
						</tr>
						<s:if test="pwwApply.processStatus=='合同待确认'">
						<tr>
							<td colspan="11" align="center">
							<input type="button" style="height: 30px;" value="核对无误,确定" onclick="surewwapplyht(${pwwApply.id})">
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td colspan="11" align="center">--%>
<%--							<form method="post" action="ProcardAction!backwwapplyht.action">--%>
<%--								<input type="hidden" value="${pwwApply.id}" name="pwwApply.id">--%>
<%--								<textarea rows="3" cols="40" name="pwwApply.backReMark"></textarea>--%>
<%--								<br/>--%>
<%--								<input type="submit" style="width: 80px;height: 30px;" value="打回">--%>
<%--							</form>--%>
<%--							</td>--%>
<%--						</tr>--%>
						</s:if>
					</table>
					<br/>
					<br/>
					<br/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function outPut(){
	window.location.href="ProcardAction!outDetailByPwwApply.action";
}

function showchange(value){
	if(value=="showall"){
		$(".showall").show();
		nowShow="showall";
	}else{
		$(".showall").hide();
	}
}
</SCRIPT>
	</body>
</html>
