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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="JiaoXiaoKaoHeAction!huoqudlppList.action"
					method="POST">
					<input type="text" value="${months}" name="months" class="Wdate"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
					<input type="submit" value="查询" class="input">
				</form>
				<div>
				<form action="JiaoXiaoKaoHeAction!huoqudlppList.action" method="POST">
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="25px">
							<th rowspan="2">
								部门
							</th>
							<th rowspan="2">
								姓名
							</th>
							<th rowspan="2">
								部门长
								<br />
								绩效(元)
							</th>
							<th colspan="2">
								部门长
								<br />
								目标7项
							</th>
							<th colspan="3">
								部门长周列会3项
							</th>
							<th colspan="2">
								年度改善自选
							</th>
							<th colspan="2">
								总得分
							</th>
							<th rowspan="2">
								绩效
								<br />
								系数
							</th>
							<th rowspan="2">
								部门
								<br />
								人数
							</th>
							<th rowspan="2">
								部门人均
								<br />
								绩效(元)
							</th>
						</tr>
						<tr align="center" bgcolor="#c0dcf2" height="25px">
							<th>
								得分1
							</th>
							<th>
								系数1
							</th>
							<th>
								加分
							</th>
							<th>
								减分
							</th>
							<th>
								得分2
							</th>
							<th>
								系数2
							</th>
							<th>
								得分3
							</th>
							<th>
								系数和
							</th>
							<th>
								排名
							</th>
						</tr>
						<s:iterator value="dlppList" id="pagedlpp" status="statussdf">
							<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								${pagedlpp.dept}
								<input type="hidden" value="${pagedlpp.id}" style="width: 75px;" 
								name="dlppList[${statussdf.index}].id"/> 
							</td>
							<td>${pagedlpp.name}</td>
							<td>
								${pagedlpp.leaderjx}
							</td>
							<td>${pagedlpp.deptMubiaoScore}</td>
							<td>
								<input type="text" value="${pagedlpp.deptMubiaoXiShu}" class="update_" style="width: 75px; " 
								name="dlppList[${statussdf.index}].deptMubiaoXiShu"/>
								<span style="display: none">${pagedlpp.deptMubiaoXiShu}</span> 
							</td>	
							<td>${pagedlpp.deptZlhAddScore}</td>
							<td>${pagedlpp.deptZlhReduceScore}</td>
							<td>${pagedlpp.deptZlhScore}</td>
							<td>
								<input type="text" value="${pagedlpp.deptZlhXiShu}" class="update_" style="width: 75px;" 
								name="dlppList[${statussdf.index}].deptZlhXiShu"/> 
								<span style="display: none">${pagedlpp.deptZlhXiShu}</span> 
							</td>
							<td>${pagedlpp.ndgzScore}</td>
							<td>
								<input type="text" value="${pagedlpp.ndgzXiShu}" style="width: 75px;" 
								name="dlppList[${statussdf.index}].ndgzXiShu"/> 
								<span style="display: none">${pagedlpp.ndgzXiShu}</span> 
							</td>
							<td>
								${pagedlpp.xiShuSum}
							</td>
							<td>${pagedlpp.paiMing}</td>
							<td>${pagedlpp.jiXiaoXiShu}</td>
							<td>${pagedlpp.deptPeopelNum}</td>
							<td>${pagedlpp.deptAvgjx}</td>
						</s:iterator>
					</table>
					<input type="submit" value="更新" class="input update_submit">
				</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
$(function(){
	if('${pageStatus}' != 'update'){
		$(".update_").hide();
		$(".update_submit").hide();
		$("span").show();
	}
})
</script>
	</body>
</html>
