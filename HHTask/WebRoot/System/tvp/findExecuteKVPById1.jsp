<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
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
				</div>
			</div>

			<div align="center">
			<center>
			<div id="printDiv">
				<table style="width: 98%;" class="table" >
				<tr>
				<th colspan="5">
					<font size="4"> <b>产品持续改进项目执行单</b></font>
				</th>
				</tr>
						<tr>
							<th colspan="5"  >
									产品评估
							</th>
							</tr>
							<tr >
							<th rowspan="2" >
								产品信息
								</th>
								<th>
								零件名称/项目名
								</th>
								<th align="left">
									${kvpAssess.part_name}
								</th>
								<th>
								零件号/项目号
								</th>
								<th align="left">
									${kvpAssess.part_number}
								</th>
							</tr>
							<tr >
								<th>
								工序名/项目描述
								</th>
								<th align="left">
									${kvpAssess.process_name}
<%--									<input type="text" name="kvpAssess.process_name" style="width: 500px;" value="${kvpAssess.process_name}">  --%>
								</th>
								<th colspan="2"></th>
							</tr>
							<tr>
								<th id="change" rowspan="5">
									持续改进问题描述
								</th>
							</tr>
							<tr>
								<th colspan="2" >
									改进前问题	 
								</th>
								<th colspan="2" >
									改进后问题	&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
							</tr>
							<tr>
							<th colspan="2"  align="left">
								<div style="position: relative;left: 0px;top: 0px;">
								${kvpAssess.improved_beforeproblems}
										</div>
							</th>
							<th colspan="2"  align="left">
										<div style="position: relative;left: 0px;top: 0px;">
										${kvpAssess.improved_endproblems}
<%--												<textarea disabled="disabled" id="content_2" name="kvpAssess.improved_endproblems"   cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;">${kvpAssess.improved_endproblems}</textarea>	--%>
										</div>
							</th>
							</tr>
						<tr>
							<th>
							评估结论
							</th>
							<th colspan="2" >
							是否可以开展持续改进
							</th>
							<th>
							${kvpAssess.assessment_findings}
							</th>
						</tr>
					</table>
					<table style="width: 98%;" class="table" id="complexselectedlist">
							<tr>
							<th colspan="6"  >
									项目执行
							</th>
							</tr>
							<tr>
								<th colspan="2">
								风险评估单编号
								</th>
							<th align="left">
							${kvpAssess.kvp_number}
								</th>
								<th colspan="2">
								项目执行编号
								</th>
								<th align="left">
								${executeKVP.executeNumber}
								</th>
							</tr>
							<tr>
							<th rowspan="3" >
								改进部门
								</th>
								<th>
								部门编号
								</th>
								<th align="left">
								${executeKVP.improve_deptNum}
								</th>
								<th rowspan="3" >
								责任部门
								</th>
								<th>
								部门编号
								</th>
								<th align="left">
								${executeKVP.res_deptNum}
								</th>
							</tr>
							<tr>
							<th>
								责任员工
								</th>
								<th align="left">
								${executeKVP.improve_username}
								</th>
								<th>
								责任员工
								</th>
								<th align="left">
								${executeKVP.res_username}
								</th>
							</tr>
							<tr>
							<th>
								员工编号
								</th>
								<th align="left">
								${executeKVP.improve_usercode}
								</th>
								<th>
								员工编号
								</th>
								<th align="left">
								${executeKVP.res_usercode}
								</th>
							</tr>
							<tr >
							<th rowspan="2" >
								产品信息
								</th>
								<th>
								零件名称/项目名
								</th>
								<th align="left">
								${kvpAssess.part_name}
								</th>
								<th>
								零件号/项目号
								</th>
								<th align="left">
								${kvpAssess.part_number}
								</th>
								<th>
								</th>
							</tr>
							<tr >
								<th>
								工序名/项目描述
								</th>
								<th colspan="3" align="left">
								${kvpAssess.process_name}
								</th>
								<th></th>
							</tr>
							<tr>
								<th id="change" rowspan="3">
									持续改进问题描述
								</th>
							</tr>
							<tr>
								<th colspan="3" >
									改进前问题	 
								</th>
								<th colspan="3" >
									改进后问题	&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
							</tr>
							<tr>
							<th colspan="3"  align="left">
								<div style="position: relative;left: 0px;top: 0px;">
								${executeKVP.improved_beforeproblems}
										</div>
							</th>
							<th colspan="3"  align="left">
										<div style="position: relative;left: 0px;top: 0px;">
										${executeKVP.improved_endproblems}
										</div>
							</th>
							</tr>
							<tr >
							<th rowspan="2" >
								 成本分析
								</th>
								<th>
								材料成本
								</th>
								<th colspan="2">
								人工成本
								</th>
								<th>
								材料成本
								</th>
								<th colspan="2">
								人工成本
								</th>
							</tr>
							<tr>
								<th>
									${executeKVP.materialcosts}
								</th>
								<th colspan="2">
									${executeKVP.laborcosts}
								</th>
								<th>
									${executeKVP.materialcosts1}
								</th>
								<th colspan="2">
								${executeKVP.laborcosts1}
								</th>
							</tr>
					</table>
					</div>
					<table class="table" style="width: 98%">
						<tr>
							<td align="center">
								<s:if test="'同意'==executeKVP.status">
									<input style="width: 80px; font-size: 18px;"
										onclick="pagePrint1()" type="button" value="打印">
								</s:if>
								<s:else>
									<span style="font-size: 20; color: red; font-weight: bold">等待审核人通过后，方可打印借款！</span>
								</s:else>
							</td>
						</tr>
					</table>
					</center>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
		function pagePrint1(){
			pagePrint('printDiv','sy');
		}
</script>

</html>
