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
		<div align="center" id="gongneng">
			<div align="center"
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				<table class="table" align="center">
					<tr>
						<th align="center" colspan="8" style="size: 20pt;">
							查看离职工资
						</th>
					</tr>
					<tr>
						<th colspan="4" align="left" style="border-right-width: 0px;">
							&nbsp;&nbsp;&nbsp;&nbsp;离职申请单编号：${dimissionZhengYi.log_number}
						</th>
						<th colspan="4" align="right" style="border-left-width: 0px;">
							离职交接单编号：${dimissionZhengYi.hand_number} &nbsp;&nbsp;&nbsp;&nbsp;
						</th>
					</tr>
					<tr>
						<td align="center">
							姓名
						</td>
						<td align="center" style="width: 102px;">
							${dimissionZhengYi.name}
						</td>
						<td align="center">
							入职日期
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.ruzhiTime}
						</td>
						<td align="center">
							离职日期
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.liTime}
						</td>
					</tr>
					<tr>
						<td align="center">
							员工卡号
						</td>
						<td align="center" style="width: 102px;">
							${dimissionZhengYi.code}
						</td>
						<td align="center">
							离职原因
						</td>
						<td align="center" colspan="5">
							${dimissionZhengYi.dimi_Reason}
						</td>
					</tr>
					<%--
						以下内容由人事填写：
						--%>
					<tr>
						<td align="center" colspan="2">
							工资结算截止日期
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.jz_Time}
						</td>
						<td align="center" colspan="2">
							当月计薪日
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.jxr_Time}
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							生产奖金或绩效分配数
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.jx_Money} 元
						</td>
						<td align="center" colspan="2">
							应发岗位工资
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.gw_Money} 元
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							应发奖金绩效
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.jiangj_Money} 元
						</td>
						<td align="center" colspan="2">
							餐费
						</td>
						<td colspan="2" align="center">
							${dimissionZhengYi.canfei} 元
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							合计离职工资
						</td>
						<td colspan="6">
							${dimissionZhengYi.add_up_number} 元
						</td>
					</tr>

					<tr>
						<td align="center">
							离职补偿金额
						</td>
						<td align="center" style="width: 102px;">
							${dimissionZhengYi.bc_Money} 元
						</td>
						<td align="center">
							大写
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.daxie}
						</td>
						<td align="center">
							合计应发
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.addup_yf} 元
						</td>
					</tr>
					<tr>
						<td align="center">
							补贴
						</td>
						<td colspan="2">
							${dimissionZhengYi.butie} 元
						</td>
						<td align="center">
							补贴说明
						</td>
						<td colspan="4">
							${dimissionZhengYi.butie_shuoming} 元
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							社保、公积金缴费截止日期
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.sbjf_jzTime}
						</td>
						<td align="center" colspan="2">
							是否需要补足最低工资标准
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.buzu_min}
						</td>
					</tr>

					<tr>
						<th colspan="8">
							以下扣款项
						</th>
					</tr>
					<tr>
						<td align="center">
							养老
						</td>
						<td align="center">
							医疗
						</td>
						<td align="center">
							失业
						</td>
						<td align="center">
							公积金
						</td>
						<td align="center">
							房租
						</td>
						<td align="center">
							其他扣款
						</td>
						<td align="center" colspan="2">
							实发金额合计
						</td>
					</tr>
					<tr>

						<td align="center" style="width: 98px;">
							${dimissionZhengYi.pension} 元
						</td>
						<td align="center" style="width: 102px;">
							${dimissionZhengYi.yiliao} 元
						</td>
						<td align="center">
							${dimissionZhengYi.shiye} 元
						</td>
						<td align="center">
							${dimissionZhengYi.fund} 元
						</td>
						<td align="center">
							${dimissionZhengYi.rent} 元
						</td>
						<td align="center">
							${dimissionZhengYi.other} 元
						</td>
						<td align="center" colspan="2">
							${dimissionZhengYi.real_money} 元
						</td>

					</tr>
					<tr>
						<td align="center">
							备注
						</td>
						<td colspan="7">
							${dimissionZhengYi.remark}
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
