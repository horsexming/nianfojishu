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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/bootstrap.css" />
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center" id="my">
				<form action="" method="POST" role="form">
					<div class="form-group">
						<lable for="name">
						月份
						</lable>
						<input type="text" class="form-contorl Wdate" id="months"
							name="months" value="${months}"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
					</div>
					<button type="submit" class="btn btn-default">
						查询
					</button>
				</form>
				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<tr align="center" bgcolor="#c0dcf2">
						<th rowspan="2">
							序号
						</th>
						<th rowspan="2">
							部门
						</th>
						<th rowspan="2">
							可分配
							<br />
							总绩效
							<br />
							(元)
						</th>
						<th rowspan="1" colspan="3">
							参数
						</th>
						<th rowspan="2" colspan="">
							产值系数d1
						</th>
						<th rowspan="1">
							结构比d2
						</th>
						<th rowspan="1">
							委外比d3
						</th>
						<th rowspan="2">
							制造评价z
						</th>
						<s:iterator id="pagedud" value="dudList">
							<th rowspan="1">
								${pagedud.deptName}
							</th>
							<th rowspan="1">
								绩效(元)
							</th>
						</s:iterator>
					</tr>
					<tr>
						<th>
							K
						</th>
						<th>
							c
						</th>
						<th>
							d
						</th>
						<th>
							目标
							<labe id="jgmb"></labe>
						</th>
						<th>
							目标
							<labe id="wwmb"></labe>
						</th>
					</tr>
					<s:if test="errorMessage == 'true'">
						<s:iterator value="fpqkList" id="pagefpqk" status="statussdf">
							<tr>
								<%--						<s:if test="#statussdf.index%2==1">--%>
								<%--							<tr align="center" bgcolor="#e6f3fb"--%>
								<%--								onmouseover="chageBgcolor(this)"--%>
								<%--								onmouseout="outBgcolor(this,'#e6f3fb')">--%>
								<%--						</s:if>--%>
								<%--						<s:else>--%>
								<%--							<tr align="center" onmouseover="chageBgcolor(this)"--%>
								<%--								onmouseout="outBgcolor(this,'')">--%>
								<%--						</s:else>--%>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>
									${pagefpqk.dept}
								</td>
								<td>
									${pagefpqk.k}
								</td>
								<td>
									${pagefpqk.c}
								</td>
								<td>
									${pagefpqk.d}
								</td>
								<td>
									${pagefpqk.d1}
								</td>
								<td>
									${pagefpqk.d2}
								</td>
								<td>
									${pagefpqk.d3}
								</td>
								<td>
									${pagefpqk.z}
								</td>
								<s:iterator value="pagefpqk.wstqList" id="pagewstq">
									<td>
										${pagewstq.buMenTqMoney}
									</td>
								</s:iterator>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="25" align="center">
								<label style="color: red;">${errorMessage}</label>
							</td>
						</tr>
					</s:else>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	var years = '${months}'.substring(0, 4);
	$.ajax( {
		url : 'JiaoXiaoKaoHeAction_findWwJgMbBy0.action',
		type : "POST",
		data : {
			'wwjgmb.years' : years
		},
		dataType : "json"
	}).then(function(res) {
		$("#jgmb").html(res.jieGouMuBiao);
		$("#wwmb").html(res.waiweiMuBiao);
	});
})
</script>
	</body>
</html>
