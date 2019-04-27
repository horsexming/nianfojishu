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
					非主营业务应收总表管理
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form method="post" action="NoncoreReceAction!showList.action?tag=${tag}">
					<table class="table">
						<tr>
							<td align="right">
								承租方:
							</td>
							<td>
								<input name="nonCoreReceivables.chengzufang"
									value="${nonCoreReceivables.chengzufang}" />
							</td>
							<td align="right">
								经办人:
							</td>
							<td>
								<input name="nonCoreReceivables.jignbanren"
									value="${nonCoreReceivables.jignbanren}" />
							</td>
							<td align="right">
								有效期至~:
							</td>
							<td>
								<input name="endDate" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									value="${endDate}" />
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询" class="input" /> 
								<input type="button" value="添加非主营应收" onclick="addfer()" class="input" style="width: 110px;"/> 
								<input type="button" value="添加收款单位" onclick="adddw()" class="input" style="width: 96px;"/>
								<input class="input" onclick="window.history.back();" type="button" value="返回"/>
							</th>
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
							承租方
						</td>
						<td align="center">
							承租地址/设备 
						</td>
						<td align="center">
							合同编号
						</td>
						<td align="center">
							缴租周期
						</td>
						<td align="center">
							经办人
						</td>
						<td align="center">
							有效期
						</td>
						<td align="center">
							保证金
						</td>
						<td align="center">
							租金
						</td>
						<td align="center">
							是否代缴
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							状态
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="nonCoreReceivablesList" id="samples" status="pageStatus">
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
							${samples.receiveType}
						</td>
						<td align="center">
							${samples.chengzufang}
						</td>
						<td align="center">
							${samples.chengzudizhi}
						</td>
						<td align="center">
							${samples.hetongbianhao}
						</td>
						<td align="center">
							${samples.jiaozuzhouqi}月
						</td>
						<td align="center">
							${samples.jignbanren}
						</td>
						<td align="center">
							${samples.youxiaoqi}
						</td>
						<td align="center">
							${samples.baozhegnjin}
						</td>
						<td align="center">
							${samples.zujin}
						</td>
						<td align="center">
							${samples.isNeeddaitijiaofei}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
						<td align="center">
							${samples.typeStatus}
						</td>
						<td align="center" colspan="2">
							<s:if test="#samples.epId!=null">
								<a href="CircuitRunAction_findAduitPage.action?id=${samples.epId}">审批动态</a>
							</s:if>
							<s:if test="tag=='administratorse'">
								<a
									href="NoncoreReceAction!toupdate.action?nonCoreReceivables.id=${samples.id}&tag=${tag}">修改/</a>
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="NoncoreReceAction!delete.action?id=${samples.id}&tag=${tag}">删除/</a>
							</s:if>
							<s:if test='tag=="code"'>
								<a
									href="NoncorePayableAction!toupdate.action?corePayable.id=${samples.id}&tag=${tag}">修改/</a>
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="NoncorePayableAction!delete.action?id=${samples.id}&tag=${tag}">删除/</a>
							</s:if>
							<a href="NoncoreReceAction!toaddDetail.action?id=${samples.id}&tag=${tag}">添加明细/</a>
							<a href="NoncoreReceAction!showListDetail.action?id=${samples.id}&tag=${tag}">查看明细</a>
							<input type="button" value="下载附件" onclick="xiazai('${samples.hetongfujian}')"/>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="14" align="center" style="color: red">
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
		//下载合同
	function xiazai(url){
			//对中文进行加密
			//var fileName1 = encodeURI(encodeURI("${list.priceUrl}"));
			var fileName1 = url;
<%--			location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1+"&directory=/upload/file/feiZY/";--%>
			location.href="<%=request.getContextPath()%>/FileViewAction.action?FilePath=/upload/file/feiZY/"+fileName1;
	}
function addfer(){
	window.location.href = "NoncoreReceAction!toadd.action";
}
function adddw(){
	window.location.href = "<%=path%>/System/payee/payee_add.jsp";
}
</script>
	</body>
</html>
