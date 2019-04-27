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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="InformAction!caiwulizhiPrint.action" method="post">
				<table class="table">
					<tr>
						<td colspan="7" align="center">
							编号：${leaveInform.time}	</br>
							离职工资结算单				</br>
							收文：财务 发文：人力资源								
						</td>
					</tr>
					<tr>
						<td colspan="7" align="center">事由：离职结算                                          							
						</td>
					</tr>
					<tr>
						<td align="center">
							姓名
						</td>
						<td align="center">
							离职原因
						</td>
						<td align="center">
							离职申请单编号
						</td>
						<td align="center">
							入职时间
						</td>
						<td align="center">
							离职时间
						</td>
						<td align="center">
							员工卡号
						</td>
						<td align="center">
							离职交接单编号
						</td>
					</tr>
					<tr>
						<td>${leaveInform.username}
						<input type="hidden" name="leaveInform.username" value="${leaveInform.username}"/>
						<input type="hidden" name="leaveInform.reason" value="${leaveInform.reason}"/>
						<input type="hidden" name="leaveInform.time" value="${leaveInform.time}"/>
						<input type="hidden" name="leaveInform.dept"
							value="${leaveInform.dept}" />
						<input type="hidden" name="leaveInform.code"
							value="${leaveInform.code}" />
						</td>
						<td>${leaveInform.reason}</td>
						<td>${leaveInform.time}</td>
						<td><input type="text" name="leaveInform.starttime" class="Wdate"
									onblur="manageCheck()" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd日',skin:'whyGreen'})"></td>
						<td><input type="text" name="leaveInform.fintime" class="Wdate"
									onblur="manageCheck()" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd日',skin:'whyGreen'})"></td>
						<td>${users.cardId}</td>
						<td>${leaveInform.time}</td>
					</tr>
					<tr>
						<td colspan="2" align="center">工资结算截止日期	</td>
						<td> <input type="text" name="leaveInform.month" size="1"
									onblur="numberCheck(this.value)">        月份计薪日</td>
						<td align="center">       该  月份应发岗位工资	</td>
						<td align="center">        该 月份应发保密津贴</td>
						<td align="center">        该 月份绩效工资基数</td>
						<td align="center">离职补偿	</td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="leaveInform.endtime" class="Wdate"
									onblur="manageCheck()" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd日',skin:'whyGreen'})"></td>
						<td><input type="text" name="leaveInform.tiantime" 
									onblur="numberCheck(this.value)"></td>
						<td><input type="text" name="leaveInform.postsalary" 
									onblur="numberCheck(this.value)"></td>
						<td><input type="text" name="leaveInform.secrecysalary" 
									onblur="numberCheck(this.value)"></td>
						<td><input type="text" name="leaveInform.performancesalary" 
									onblur="numberCheck(this.value)"></td>
						<td><input type="text" name="leaveInform.offset" 
									onblur="numberCheck(this.value)"></td>
					</tr>
					<tr>
						
						<td colspan="2" align="center">绩效考核得分	</td>
						<td align="center">       该  月份绩效工资</td>
						<td></td>
						<td></td>
						<td ></td>
						<td align="center">合计应发</td>
					</tr>
					<tr>
						
						<td colspan="2" align="center"><input type="text" name="leaveInform.performance" 
									onblur="numberCheck(this.value)"></td>
						<td align="center"><input type="text" name="leaveInform.achievement" 
									onblur="numberCheck(this.value)"></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2" align="center">社保统筹扣款	</td>
						<td align="center">医疗保险</td>
						<td align="center">失业保险	</td>
						<td align="center">公积金</td>
						<td></td>
						<td align="center">实际支付（实发）</td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="leaveInform.shebao" 
									onblur="numberCheck(this.value)"></td>
						<td><input type="text" name="leaveInform.yiliao" 
									onblur="numberCheck(this.value)"></td>
						<td><input type="text" name="leaveInform.shiye" 
									onblur="numberCheck(this.value)"></td>
						<td><input type="text" name="leaveInform.gongji" 
									onblur="numberCheck(this.value)"></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="center">说明</td>
						<td colspan="6">
						<input type="text" name="leaveInform.shuoming" size="120"
									></td>
					</tr>
					<tr>
							<td colspan="7" align="center">
								<input type="submit" value="提交" class="input" />
								<input type="reset" value="重置" class="input" />
							</td>
						</tr>
					
				</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function numberCheck(a){
	if(isNaN(a)){
		alert("请填写数字!");
		return false;
	}
}
</script>
	</body>
</html>
