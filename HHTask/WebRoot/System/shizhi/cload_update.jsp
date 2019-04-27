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
				style="width: 100%; font-weight: bold; height: 50px; background: url('<%=basePath%>images/title.jpg') no-repeat;"
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="processPRNScoreAction_toupdate.action"
						style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					修改工艺负荷
					<br />
					（update craft load）
				</h3>
				<form action="craftLoadAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								零件号
								<br />
								（mark id）
							</th>
							<td>
							<input type="hidden" name="craftLoad.id" value="<s:property value="craftLoad.id"/>">
							<input type="hidden" name="cpage" value="<s:property value="cpage"/>">
								<input type="text" name="craftLoad.markId" readonly="readonly"
									value="<s:property value="craftLoad.markId"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								工序号
								<br />
								（processNO）
							</th>
							<td>
								<input type="text" name="craftLoad.processNO"
									readonly="readonly"
									value="<s:property value="craftLoad.processNO"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								工艺负荷分值
								<br />
								（craftLoadScore）：
							</th>
							<td>
								<input type="text" name="craftLoad.craftLoadScore"
									readonly="readonly"
									value="<s:property value="craftLoad.craftLoadScore"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								是否关键工序
								<br />
								（process RPN score）：
							</th>
							<td>
								<input type="text" name="craftLoad.processPRNScore" id="RPNScore"
									readonly="readonly"
									value="<s:property value="craftLoad.processPRNScore"/>" />
								RPN评分1：
								<input type="text" name="craftLoad.rpnScore1" id="rpnScore1"
									value="<s:property value="craftLoad.rpnScore1"/>" onkeyup="checkRPN(1)" >
								RPN评分2：<input type="text" name="craftLoad.rpnScore2" id="rpnScore2"
									value="<s:property value="craftLoad.rpnScore2"/>" onkeyup="checkRPN(2)" >
								RPN评分3：<input type="text" name="craftLoad.rpnScore3" id="rpnScore3"
									value="<s:property value="craftLoad.rpnScore3"/>" onkeyup="checkRPN(3)" >
							</td>
						</tr>
						<tr>
							<th align="right">
								工艺复杂分值
								<br />
								（craft complexity score）：
							</th>
							<td>
								<select id="craftComplexityName"  onchange="getSkills('cc')">
									<s:if
										test="craftLoad.craftComplexityName!=null&craftLoad.craftComplexityName!=''">
										<option value="-1">
											${craftLoad.craftComplexityName}
										</option>
									</s:if>
									<s:else>
										<option value="未选择">
											----未选择----
										</option>
									</s:else>
									<s:iterator value="ccList" id="cc">
											<option value="${cc.id}">
												${cc.name}
											</option>
									</s:iterator>
								</select>
								
								<SELECT id="cSkill" onchange="getSkillScores('cc')">
								<s:if
										test="craftLoad.craftSkillName!=null&craftLoad.craftSkillName!=''">
										<option value="-1">
											${craftLoad.craftSkillName}
										</option>
									</s:if>
									<s:else>
										<option value="0">
											----请先选择工艺复杂系数技能分类----
										</option>
									</s:else>
								</SELECT>
								
								<SELECT id="cScore" name="craftLoad.craftskillId">
								<s:if
										test="craftLoad.craftComplexity!=null">
										<option value="${craftLoad.craftskillId}">
											${craftLoad.craftComplexity}
										</option>
									</s:if>
									<s:else>
										 <option value="0">
											请先选择工艺系数技能
										</option>
									</s:else>
								</SELECT>
							</td>
						</tr>
						
						<tr>
							<th align="right">
								加工难点分值
								<br />
								（process difficulty score）：
							</th>
							<td>
								<select id="processDifficultyName" onchange="getSkills('ppd')">
									<s:if
										test="craftLoad.processDifficultyName!=null&processDifficultyName!=0">
										<option value="${craftLoad.processDifficultyName}">
											${craftLoad.processDifficultyName}
										</option>
									</s:if>
									<s:else>
										<option value="未选择">
											----未选择----
										</option>
									</s:else>
									<s:iterator value="ppdList" id="ppd">
											<option value="${ppd.id}">
												${ppd.name}
											</option>

									</s:iterator>
								</select>
								
								<SELECT id="pSkill" onchange="getSkillScores('ppd')">
								<s:if test="craftLoad.ppdSkillName!=null&craftLoad.ppdSkillName!=''">
										<option value="${craftLoad.ppdSkillName}">
											${craftLoad.ppdSkillName}
										</option>
									</s:if>
									<s:else>
								        <option value="0">
											请先选择加工难点技能分类
										</option>
									</s:else>
								</SELECT>
								
								<SELECT id="pScore" name='craftLoad.ppdSkillId'>
								<s:if
										test="craftLoad.processDifficulty!=null">
										<option value="${craftLoad.ppdSkillId}">
											${craftLoad.processDifficulty}
										</option>
									</s:if>
									<s:else>
										 <option value="0">
											请先选择加工难点技能
										</option>
									</s:else>
								</SELECT>
							</td>
						</tr>

						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
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
function validate() {
	var pscore = document.getElementById("pScore").value;
	var cscore = document.getElementById("cScore").value;
	if (pscore == "" || pscore == "未选择") {
		alert("请选择加工难点分值!");
		return false;
	}
	if (cscore == "" || cscore == "未选择") {
		alert("请选择工艺复杂分值!");
		return false;
	}
}
function getSkills(flag){
	var id=0;
	if(flag=='cc'){
		id=$("#craftComplexityName").val();
	}else if(flag=='ppd'){
		id=$("#processDifficultyName").val();
	}
	if(id==0){
		return;
	}
	$.ajax( {
		type : "post",
		url : "craftLoadAction_getSkills.action",
		dataType : "json",
		data : {
		id : id,
		flag : flag
		},
		success : function(data) {
			if(flag=='cc'){
				$("#cSkill").empty();
				$("#cScore").empty();
				$("<option value='0'>请选择技能</option>").appendTo("#cSkill");
				$("<option value='0'>请先选择工艺系数技能</option>").appendTo("#cScore");
	            //填充技能信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name+",分值范围:"+this.minscore+"~"+this.maxscore
										+ "</option>").appendTo("#cSkill");
					});
			}else if(flag='ppd'){
				$("#pSkill").empty();
				$("#pScore").empty();
				$("<option value='0'>请选择技能</option>").appendTo("#pSkill");
				$("<option value='0'>请先选择加工难点技能</option>").appendTo("#pScore");
				$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name+",分值范围:0~"+this.maxscore
										+ "</option>").appendTo("#pSkill");
					});
			}
		}
	});
}

function getSkillScores(flag){
	var id=0;
	if(flag=='cc'){
		id=$("#cSkill").val();
	}else if(flag=='ppd'){
		id=$("#pSkill").val();
	}
	if(id==0){
		return;
	}
	$.ajax( {
		type : "post",
		url : "craftLoadAction_getSkillScores.action",
		dataType : "json",
		data : {
		id : id,
		flag : flag
		},
		success : function(data) {
			if(flag=='cc'){
				$("#cScore").empty();
				$("<option value='0'>请选择技能分值</option>").appendTo("#cScore");
	            //填充技能信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name+",分值:"+this.total
										+ "</option>").appendTo("#cScore");
					});
			}else if(flag='ppd'){
				$("#pScore").empty();
				$("<option value='0'>请选择技能分值</option>").appendTo("#pScore");
				$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name+",分值:"+this.total
										+ "</option>").appendTo("#pScore");
					});
			}
		}
	});
}
function checkRPN(index){
	mustBeNumber("rpnScore"+index);
	if(isNaN($("#rpnScore1").val())){
		$("#rpnScore1").val(0);
	}
	if(isNaN($("#rpnScore2").val())){
		$("#rpnScore2").val(0);
	}
	if(isNaN($("#rpnScore3").val())){
		$("#rpnScore3").val(0);
	}
	//var rpnscore=($("#rpnScore1").val()-0)+($("#rpnScore2").val()-0)+($("#rpnScore3").val()-0);
	var rpnscore=$("#rpnScore1").val()*$("#rpnScore2").val()*$("#rpnScore3").val();
	$("#RPNScore").val(rpnscore);
}
</script>
	</body>
</html>
