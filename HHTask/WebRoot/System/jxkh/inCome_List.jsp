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
			<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在进行手动下单修改操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								姓名
							</th>
							<td>
								<select name="yearimprove.name"></select>
							</td>
							<th align="right">
								月份
							</th>
							<td>
								<input type="text" name="yearimprove.months" id="months"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							部门
						</th>
						<th>
							姓名
						</th>
						<th>
							职务
						</th>
						<th>
							现薪资
						</th>
						<th>
							部门人均
						<th>
							岗位系数
						</th>
						<th>
							绩效考核
						</th>
						<th>
							合计
						</th>
						<th>
							工资系数
						</th>
						<th>
							结构比
						</th>
						<th>
							委外比
						</th>
						<th>
							差值
						</th>
						<th>
							绩效奖
						</th>
						<th>
							收入
						</th>
						<th>
							月份
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="inComeList" id="pageList"
						status="statussdf">
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
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${pageList.dept}
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.post}
						</td>
						<td>
							${pageList.nowsalary}
						</td>
						<td>
							${pageList.deptaverage}
						</td>
						<td>
							${pageList.postfactor}
						</td>
						<td>
							${pageList.jixiao}
						</td>
						<td>
							${pageList.heji}
						</td>
						<td>
							${pageList.wagesCoefficient}
						</td>
						<td>
							${pageList.jieGouBi}
						</td>
						<td>
							${pageList.weiwaibi}
						</td>
						<td>
							${pageList.d_value}
						</td>
						<td>
							${pageList.zzIncome}
						</td>
						<td>
							${pageList.shouruHeJi}
						</td>
						<td>
							${pageList.months}
						</td>
						<td>
							<s:if test="pageStatus == 'jisuan' ">
								<a href="javaScript:;" onclick="tanchu('${pageList.id}')">计算收入</a>
							</s:if>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	findDudList();
})
findDudList = ()=>{
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isgsx':1},
		dataType : "json",
		success : function(data) {
			$("#name").empty();
			$("#name").append('<option value=""></option>');
			if(data!=null){
				$(data).each(function(){
					$("#name").append('<option value='+this.leader+'>'+this.leader+'</option>');
				})
			}
		}
	})
}
function tanchu(num){
	if(num>0){
		document.getElementById("xiugaiIframe").src = "JiaoXiaoKaoHeAction_getInComeById.action?id="+num;
	}
	chageDiv('block');
}
</SCRIPT>
	</body>
</html>
