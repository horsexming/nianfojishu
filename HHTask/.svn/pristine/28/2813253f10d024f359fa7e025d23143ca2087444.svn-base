<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
<script type="text/javascript">
	function deleteC(cid){
		if(!confirm('确定将此记录删除?')){
			return;
		}
		$.ajax({
			type: "POST",
			url: "tclaimform_delete.action",
			data: "claimform.id=" + cid,
			dataType:"json",
			success: function(msg){
				alert(msg.message);
				if(msg.success){
					location.reload();
				}
			}
		});
	}
</script>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
				
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<s:if test="\"tj\".equals(dept)"><a href="tclaimform_addInput.action" style="color: #ffffff">添加</a>&nbsp;&nbsp;&nbsp;</s:if>
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
			<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							索赔公司
						</th>
						<th align="center">
							登记时间
						</th>
						<th align="center">
							负责人
						</th>
						<th align="center">
							索赔金额
						</th>
						<th>
							索赔单号
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="tclaimforms" id="d" status="detailsStatus">
						<s:if test="#detailsStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#detailsStatus.index%2==0">
								<font color="red"> <s:property value="#detailsStatus.index+1" /> </font>
							</s:if>
							<s:else>
								<s:property value="#detailsStatus.index+1" />
							</s:else>
						</td>

						<td>
							${d.otherCompany}
						</td>
						<td>
							${d.regDate}
						</td>
						<td>
							${d.ourPerson}
						</td>
						<td>
							${d.claimAmount}
						</td>
						<td>
							${d.tclaimNumber}
						</td>
						<td>
							${d.status}
						</td>
						<td>
							<a href="tclaimsRecord_list.action?tclaimsRecord.root.id=${d.id}">查看</a>
							
							<s:if test="\"tj\".equals(dept)">
								<!-- 添加报价单的人可以看 -->
								<a href="tclaimform_updateInput.action?claimform.id=${d.id}">修改</a>
								<a href="javascript:void(0)" onclick="deleteC(${d.id});">删除</a>
							</s:if>

							<!-- 分析的人可以看 -->
							<s:if test="\"fx\".equals(dept)">
								<a href="tclaimsRecord_analysisInput.action?tclaimsRecord.root.id=${d.id}">分析</a>
							</s:if>
							
							<!-- 通知的人可以看 -->
							<s:if test="\"tz\".equals(dept)">
								<a href="tclaimsRecord_notificationInput.action?tclaimsRecord.root.id=${d.id}">通知</a>
							</s:if>
							
							<!-- 扣款 -->
							<s:if test="\"kk\".equals(dept)">
								<a href="tclaimform_debitInput.action?claimform.id=${d.id}">扣款</a>
							</s:if>
							
							<!-- 整改 -->
							<s:if test="\"zg\".equals(dept)">
								<a href="tclaimsRecord_handleInput.action?tclaimsRecord.root.id=${d.id}">整改</a>
							</s:if>

						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="8" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
</body>
</html>
