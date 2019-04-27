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
		<SCRIPT type="text/javascript">
			function trecordOk(id){
				if(window.confirm("确定已经完成了?")){
					$.ajax({
						type: "POST",
						url: "ProjectTrackRecord_complete.action",
						data: "p.id=" + id,
						dataType: "json",
						success: function(msg){
							alert(msg.message);
							if(msg.success){
								window.location.reload();
							}
						}
					});
				}
			}
			
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table" width="100%" border="0" style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							事件
						</th>
						<th align="center">
							申请人
						</th>
						<th align="center">
							预算花费
						</th>
						<th align="center">
							开始时间
						</th>
						<th align="center">
							结束时间
						</th>
						<th align="center">
							类型
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="recoreds" id="r" status="st">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
							<td>${st.index+1}</td>
							<td>${r.thing}</td>
							<td>${r.username}</td>
							<td>${r.budgetMoney}</td>
							<td>${r.startTime}</td>
							<td>${r.endTime}</td>
							<td>${r.recordType}</td>
							
							<td>
								<s:if test="#r.recordType.equals(\"待审批\") && startUser!= null">
									<a target="_blank" href="ProjectTrackRecord_checkInput.action?p.id=${r.id}&p.root.id=${r.root.id}">审批</a>
								</s:if><s:elseif test="#r.recordType.equals(\"进行中\") && startUser!= null">
									<a target="_blank" href="javascript:void(0);" onclick="trecordOk(${r.id})">完成</a>
								</s:elseif>
								<a target="_blank" href="ProjectTrackRecord_listSingle.action?p.id=${r.id}">查看</a>
							</td>
						</tr>
						
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
