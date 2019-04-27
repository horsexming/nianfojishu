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
			<form action="InformAction!caiwuruzhiPrint.action"  method="post" id="xform">
				<table class="table">
					<tr>
					<td colspan="6" align="center"></td>
					</tr>
					<tr>
						<td align="center">工号</td>
						<td align="center"> <input type="hidden" name="leaveInform.code"
							value="${leaveInform.code}" />${leaveInform.code}</td>
						<td align="center">卡号</td>
						<td align="center">${users.cardId}</td>
						<td align="center">姓名</td>
						<td> align="center"<input type="hidden" name="leaveInform.username" value="${leaveInform.username}"/>
							 ${leaveInform.username}</td>
					</tr>
					<tr>
						<td align="center">部门</td>
						<td align="center"><input type="hidden" name="leaveInform.dept"
							value="${leaveInform.dept}" />${leaveInform.dept}
						</td>
						<td align="center">岗位工资</td>
						<td align="center"><input type="text" name="leaveInform.postsalary" 
									onblur="numberCheck(this.value)"></td>
						<td align="center">保密津贴</td>
						<td align="center"><input type="text" name="leaveInform.secrecysalary" 
									onblur="numberCheck(this.value)"></td>
					</tr>
					<tr>
						<td align="center">补贴</td>
						<td align="center"><input type="text" name="leaveInform.offset" 
									onblur="numberCheck(this.value)"></td>
						<td align="center">技能工资</td>
						<td align="center"><input type="text" name="leaveInform.performancesalary" 
									onblur="numberCheck(this.value)"></td>
						<td align="center">特殊补贴</td>
						<td align="center"><input type="text" name="leaveInform.heji" 
									onblur="numberCheck(this.value)"></td>
					</tr>
					<tr>
						<td align="center">绩效考核工资</td>
						<td align="center"><input type="text" name="leaveInform.achievement" 
									onblur="numberCheck(this.value)"></td>
						<td align="center">公积金基数</td>
						<td align="center"><input type="text" name="leaveInform.gongji" 
									onblur="numberCheck(this.value)"></td>
						<td align="center">社保基数</td>
						<td align="center"><input type="text" name="leaveInform.shebao" 
									onblur="numberCheck(this.value)"></td>
					</tr>
					<tr>
						<td align="center">住房费</td>
						<td align="center"><input type="text" name="leaveInform.yiliao" 
									onblur="numberCheck(this.value)"></td>
						<td align="center">是否补差</td>
						<td align="center">
							<input type="radio" name="leaveInform.fintime" value="是" />是
							<input type="radio" name="leaveInform.fintime" value="否" />否
						</td>
						<td align="center">起薪日期</td>
						<td align="center"><input type="text" name="leaveInform.starttime" class="Wdate"
									onblur="manageCheck()" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd日',skin:'whyGreen'})"></td>
					</tr>
					<tr>
						<td align="center">养老保险</td>
						<td align="center"><input type="radio" name="leaveInform.endtime" value="是" />是
							<input type="radio" name="leaveInform.endtime" value="否" />否
						</td>
						<td align="center">医疗保险</td>
						<td align="center"><input type="radio" name="leaveInform.performance" value="是" />是
							<input type="radio" name="leaveInform.performance" value="否" />否
						</td align="center">
						<td align="center">失业保险</td>
						<td align="center">
							<input type="radio" name="leaveInform.fuck2" value="是" />是
							<input type="radio" name="leaveInform.fuck2" value="否" />否
						</td>
					</tr>
					<tr>
						<td align="center">本地或外地</td>
						<td align="center"><input type="radio" name="leaveInform.fuck3" value="本地" />本地
							<input type="radio" name="leaveInform.fuck3" value="外地" />外地
						</td>
						<td align="center">户口类型</td>
						<td align="center"><input type="radio" name="leaveInform.fuck4" value="农村" />农村
							<input type="radio" name="leaveInform.fuck4" value="城市" />城市
						</td>
						<td align="center">保险类型</td>
						<td align="center"><input type="radio" name="leaveInform.fuck5" value="城保" />城保
							<input type="radio" name="leaveInform.fuck5" value="社保" />社保
						</td>
					</tr>
					<tr>
						<td align="center">说明</td>
						<td colspan="5"><input type="text" name="leaveInform.shuoming" size="120"
									></td>
					</tr>
					<tr>
					<td colspan="6" align="center">
									<s:if test='"财务入职通知"==leaveInform.fuck1'>
									<input type="button" value="入职确认"
										style="width: 100px; height: 50px;" onclick="fuck1();"/>
									</s:if>
									<s:elseif test='"财务内退通知"==leaveInform.fuck1'>
									<input type="button" value="内退确认"
										style="width: 100px; height: 50px;" onclick="fuck2();"/>
									</s:elseif>
									<s:elseif test='"财务个人工资调整通知"==leaveInform.fuck1'>
									<input type="button" value="调整确认"
										style="width: 100px; height: 50px;" onclick="fuck3();"/>
									</s:elseif>
									<input type="reset" value="重置"  style="width: 100px; height: 50px;"/>
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
function fuck1(){
	document.forms.xform.action = "InformAction!caiwuruzhiPrint.action";
	document.forms.xform.submit();
}
function fuck2(){
	document.forms.xform.action = "InformAction!caiwuneituiPrint.action";
	document.forms.xform.submit();
}
function fuck3(){
	document.forms.xform.action = "InformAction!caiwugerenPrint.action";
	document.forms.xform.submit();
}
function numberCheck(a){
	if(isNaN(a)){
		alert("请填写数字!");
		return false;
	}
}
</script>
	</body>
</html>
