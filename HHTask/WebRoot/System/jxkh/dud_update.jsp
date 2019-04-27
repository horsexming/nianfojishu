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
				<form action="JiaoXiaoKaoHeAction_updateDud.action" method="POST" onsubmit="return check()">
					<table class="table">
						<tr>
							<th align="right">
								部门名称:
							</th>
							<td>
								<input type="text" value="${dud.deptName}" name="dud.deptName" id="deptName"/>	
							</td>
							<th align="right">
								部门长:
							</th>
							<td>
								<input type="text" value="${dud.leader}" name="dud.leader" id="leader"/>	
							</td>
						</tr>
						<tr>
							<th align="right">
								一线或二线:
							</th>
							<td>
								<select name="dud.oneOrTwo" id="oneOrTwo">
									<option value="${dud.oneOrTwo}">
										<s:if test="dud.oneOrTwo ==1">一线</s:if>
										<s:else>二线</s:else>
									</option>
									<option value="1">一线</option>
									<option value="2">二线</option>
								</select>
							</td>
							<th align="right">
								职位:
							</th>
							<td>
								<SELECT name="" id="rank_rankNo" onchange="changvalue(this)">
									<option value="${dud.rankNo}_${dud.rank}">${dud.rank}</option>
									<option value="3_经理">经理</option>
									<option value="4_副总监">副总监</option>
									<option value="5_总监">总监</option>
									<option value="6_副厂长">副厂长</option>
									<option value="7_厂长">厂长</option>
									<option value="8_总助">总助</option>
									<option value="9_副总经理">副总经理</option>
									<option value="10_总经理">总经理</option>
								</SELECT>
								<input type="hidden" value="${dud.rankNo}" name="dud.rankNo" id="rankNo"/>
								<input type="hidden" value="${dud.rank}" name="dud.rank" id="rank"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								计算绩效:
							</th>
							<td>
								<select name="dud.isJiaoXiao" id="isJiaoXiao">
									<option value="${dud.isJiaoXiao}">
										<s:if test="dud.oneOrTwo ==0">不需要</s:if>
										<s:else>需要</s:else>
									</option>
									<option value="0">不需要</option>
									<option value="1">需要</option>
								</select>
							</td>
							<th align="right">
								部门分配:
							</th>
							<td>
								<select name="dud.isdeptFenPei" id="isdeptFenPei">
									<option value="${dud.isdeptFenPei}">
										<s:if test="dud.isdeptFenPei ==0">不需要</s:if>
										<s:else>需要</s:else>
									</option>
									<option value="0">不需要</option>
									<option value="1">需要</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								制造评价:
							</th>
							<td>
								<select name="dud.isZzPj" id="isZzPj">
									<option value="${dud.isZzPj}">
										<s:if test="dud.isZzPj ==0">不需要</s:if>
										<s:else>需要</s:else>
									</option>
									<option value="0">不需要</option>
									<option value="1">需要</option>
								</select>
							</td>
							<th align="right">
								增长效率:
							</th>
							<td>
								<select name="dud.isZzXl" id="isZzXl">
									<option value="${dud.isZzXl}">
										<s:if test="dud.isZzXl ==0">不需要</s:if>
										<s:else>需要</s:else>
									</option>
									<option value="0">不需要</option>
									<option value="1">需要</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								部门长目标:
							</th>
							<td>
								<select name="dud.isbmzmb" id="isbmzmb">
									<option value="${dud.isbmzmb}">
										<s:if test="dud.isbmzmb ==0">不需要</s:if>
										<s:else>需要</s:else>
									</option>
									<option value="0">不需要</option>
									<option value="1">需要</option>
								</select>
							</td>
							<th align="right">
								部门长目标:
							</th>
							<td>
								<select name="dud.isbmzZlh" id="isbmzZlh">
									<option value="${dud.isbmzZlh}">
										<s:if test="dud.isbmzZlh ==0">不需要</s:if>
										<s:else>需要</s:else>
									</option>
									<option value="0">不需要</option>
									<option value="1">需要</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								年度改善:
							</th>
							<td>
								<select name="dud.isgsx" id="isgsx">
									<option value="${dud.isgsx}">
										<s:if test="dud.isgsx ==0">不需要</s:if>
										<s:else>需要</s:else>
									</option>
									<option value="0">不需要</option>
									<option value="1">需要</option>
								</select>
							</td>
							<th align="right">
								委外比结构比:
							</th>
							<td>
								<select name="dud.isWeiWaiJieGou" id="isWeiWaiJieGou">
									<option value="${dud.isWeiWaiJieGou}">
										<s:if test="dud.isWeiWaiJieGou ==0">不需要</s:if>
										<s:else>需要</s:else>
									</option>
									<option value="0">不需要</option>
									<option value="1">需要</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								岗位系数:
							</th>
							<td>
								<input type="text" value="${dud.postCoefficient}" name="dud.postCoefficient" 
								id="postCoefficient" onchange="numyanzheng(this)">
							</td>
							<td colspan="2"></td>
						</tr>
					</table>
					<input type="hidden" value="${dud.id}" name="dud.id"/>
					<input type="submit" value="修改" class="input" id="sub"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if('${errorMessage}' == '修改成功!~'){
		alert('修改成功!~');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
function check(){
	var oneOrTwo = $("#oneOrTwo").val();
	var isJiaoXiao = $("#isJiaoXiao").val();
	var isZzPj = $("#isZzPj").val();
	var isZzXl = $("#isZzXl").val();
	var isdeptFenPei = $("#isdeptFenPei").val();
	
	var isZzPj = $("#isZzPj").val();
	var isZzXl = $("#isZzXl").val();
	var isbmzmb = $("#isbmzmb").val();
	var isbmzZlh = $("#isbmzZlh").val();
	var isgsx = $("#isgsx").val();
	var isWeiWaiJieGou = $("#isWeiWaiJieGou").val();
	var postCoefficient = $("#postCoefficient").val();
	if(oneOrTwo == 0){
		alert('请选择是一线还是二线人员')
		$("#oneOrTwo").focus();
		return false;
	}else if(isJiaoXiao == ''){
		alert('请确定是否需要计算绩效')
		$("#isJiaoXiao").focus();
		return false;
	}else if(isZzPj == ''){
		alert('请确定是否需要制造评价')
		$("#isZzPj").focus();
		return false;
	}else if(isZzXl == ''){
		alert('请确定是否需要增长效率')
		$("#isZzXl").focus();
		return false;
	}else if(isdeptFenPei == ''){
		alert('请确定是否需要部门分配')
		$("#isdeptFenPei").focus();
		return false;
	}else if(isZzPj == ''){
		alert('请确定是否需要制造评价')
		$("#isZzPj").focus();
		return false;
	}else if(isZzXl == ''){
		alert('请确定是否需要增长效率')
		$("#isZzXl").focus();
		return false;
	}else if(isbmzmb == ''){
		alert('请确定是否需要部门长目标')
		$("#isbmzmb").focus();
		return false;
	}else if(isbmzZlh == ''){
		alert('请确定是否需要部门长周列会')
		$("#isbmzZlh").focus();
		return false;
	}else if(isgsx == ''){
		alert('请确定是否需要年度改善')
		$("#isgsx").focus();
		return false;
	}else if(isWeiWaiJieGou == ''){
		alert('请确定是否关联委外比结构比')
		$("#isWeiWaiJieGou").focus();
		return false;
	}else if(!postCoefficient){
		alert('请填写岗位系数')
		$("#postCoefficient").focus();
		return false;
		
	}
	$("#sub").attr('disabled','disabled');
	return true;
}
function changvalue(obj){
	var value = obj.value;
	if(value!=''){
		var  strs = value.split("_");
		$("#rankNo").val(strs[0]);
		$("#rank").val(strs[1]);
	}
}
</SCRIPT>
	</body>
</html>
