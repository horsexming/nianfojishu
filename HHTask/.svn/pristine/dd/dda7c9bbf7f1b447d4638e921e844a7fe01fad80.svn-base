<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<SCRIPT type="text/javascript">
  function check1(id) {
	 for(var i=0;i<id;i++){
		var gongzi = document.getElementById("gongzi" + i);
		var jiacan= document.getElementById("jiacan" + i);
		if (gongzi.value == "") {
			alert("成员工资不能为空");
			gongzi.focus();
			return false;
		}
		if(jiacan.value==""){
			alert("加班费及饭贴不能为空!");
			jiacan.focus();
			return false;
		}
	}
	return true;
}
  
function addDeptYu(idNumber) {
	var money=0;
	var jiacanMoney=0;
	 for(var i=0;i<idNumber;i++){
		var gongziValue= document.getElementById("gongzi" + i).value;
		var jiacanValue= document.getElementById("jiacan" + i).value;
		if(gongziValue==""){
			gongziValue=0;
		}
		if(jiacanValue==""){
			jiacanValue=0;
		}
		money+=parseFloat(gongziValue)+parseFloat(jiacanValue);
	}	
	 
	 document.getElementById("deptMoney").innerHTML=money;
	 document.getElementById("hiddDeptMoney").value=money;
	 var deptYuInput = document.getElementById("deptYu");
	 deptYuInput.value=Math.round(money/19*100)/100;
}
function deptMoney(obj){
	if(obj.value==""){
		obj.value="0";
	}
	var money=0;
	var deptMoney=parseFloat(document.getElementById("hiddDeptMoney").value);
	var deptYu=parseFloat(obj.value);
	if(deptMoney/19<deptYu){
		alert("部留金额超过预算!请重新填写!");
		obj.value=Math.round(deptMoney/19*100)/100;
		obj.select();
	}
	money=deptMoney+parseFloat(obj.value);
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
			</div>
			
			<div align="center">
				<form
					action="BonusmoneyAction!updateauditDisagree.action?pageStatus=${pageStatus}"
					onsubmit="return check1('<s:property value='listbonusmoney.size-1'/>')"
					method="post">
					<table>
						<tr>
							<th colspan="6">
								<font size="5">修改奖金信息</font> &nbsp;&nbsp;
								<a href="BonusmoneyAction.action?pageStatus=${pageStatus}">返回</a>
							</th>
						</tr>
						<tr>
							<th>
								班组
							</th>
							<th>
								成员姓名
							</th>
							<th>
								成员工号
							</th>
							<th>
								成员卡号
							</th>
							<th>
								成员奖金
							</th>
							<th>
								成员加班费及饭贴
							</th>
						</tr>
						<s:iterator id="bo" value="listbonusmoney" status="status">
							<s:if test="#bo.bonusteamname!='部留'">
								<tr>
									<td align="center">
										<input type="hidden" value="${bo.bonusdata}" name="date" />
										<!-- 月份 -->
										${bo.bonusteam}
										<input type="hidden" name="deptbanzu" value="${bo.bonusteam}" />
									</td>
									<td align="center">
										${bo.bonusteamname}
										<input type="hidden" name="chengyuanname"
											value="${bo.bonusteamname}" />
									</td>
									<td align="center">
										${bo.bonusmembernumber}
										<input type="hidden" name="chengyuangongnumer"
											value="${bo.bonusmembernumber}" />
									</td>
									<td align="center">
										${bo.bonuscardnumber}
										<input type="hidden" name="chengyuankanumber"
											value="${bo.bonuscardnumber}" />
									</td>
									<td align="center">
										<input type="text" name="chengyuanjiangji"
											value="${bo.bonusmembermoney}"
											onkeyup="if(isNaN(value))execCommand('undo');addDeptYu(<s:property value='listbonusmoney.size-1'/>)"
											id="gongzi<s:property value="#status.index" />" />
									</td>
									<td align="center">
										<input type="text" name="chengyuanjbfft"
											value="${bo.bonusovertimemealmoney}"
											onkeyup="if(isNaN(value))execCommand('undo');addDeptYu(<s:property value='listbonusmoney.size-1'/>)"
											id="jiacan<s:property value="#status.index" />" />

									</td>
								</tr>
							</s:if>
						</s:iterator>
						<tr>
							<td colspan="4"></td>
							<td colspan="1" align="center">
								<input type="hidden" name="zongjiangji" id="hiddDeptMoney"
									value="0">
								成员总奖金
								<font color="red"><span id="deptMoney">0</span> </font>元
							</td>
							<td align="center">
								部留
								<input type="text" name="deptYu" id="deptYu"
									onkeyup="deptMoney(this)" value="0" />
							</td>
						</tr>

						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="确  定"
									style="width: 80px; height: 60px;" />
								&nbsp;&nbsp;&nbsp;
								<input type="reset" value="取  消"
									style="width: 80px; height: 60px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<SCRIPT type="text/javascript">
										addDeptYu(<s:property value='listbonusmoney.size-1'/>);
				</SCRIPT>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
