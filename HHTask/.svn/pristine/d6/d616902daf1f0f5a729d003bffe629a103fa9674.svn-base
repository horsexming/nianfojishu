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
.s {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

.s th,.s td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<SCRIPT type="text/javascript">
  function check1(id) {
	 var bonus_id = $("#bonus_id").val();
	$.ajax( {
				type : "POST",
				url : "BonusAction!updateBus.action",
				data : {
					bonus_id : bonus_id
				},
				dataType : "json",
				success : function(data) {
					return true;
				}
			});
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
function addDeptYu(idNumber,status) {
	var money=0;
	 for(var i=0;i<idNumber;i++){
		var gongziValue= document.getElementById("gongzi" + i);
		if(gongziValue==null){
			idNumber++;
			continue;
		}
		var jiacanValue= document.getElementById("jiacan" + i).value;
		if(gongziValue.value==""){
			gongziValue=0;
		}
		if(jiacanValue==""){
			jiacanValue=0;
		}
		money+=parseFloat(gongziValue.value)+parseFloat(jiacanValue);
	}
	document.getElementById("deptMoney").innerHTML=money;
	document.getElementById("hiddDeptMoney").value=money;
	var deptYuInput = document.getElementById("deptYu");
	if(status!="load"){
		deptYuInput.value=Math.round(money/19*100)/100;
	}
	document.getElementById("allMoney").innerHTML=parseFloat(deptYuInput.value)+money;
	
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
	document.getElementById("allMoney").innerHTML=Math.round(money*100)/100;
}

function updateResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			if(ulMessage!="success"){
		  	 alert(ulMessage);
			}
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
function saveChengyuan(gonghao,id) {
		var todayDate="${dateyuefen}";
		var pageStatus="${pageStatus}";
		var gongziValue= document.getElementById("gongzi" + id).value;
		var jiacanValue= document.getElementById("jiacan" + id).value;
		var deptYuInput = document.getElementById("deptYu").value;
		var zongmoney=document.getElementById("allMoney").innerHTML;
		var url="BonusAction!addBonus.action?todayDate="+todayDate+"&gonghao="+gonghao+"&chengyuangongzi="+gongziValue+"&jbfctmoney="+jiacanValue+"&deptYu="+deptYuInput+"&zongjiangji="+zongmoney+"&pageStatus="+pageStatus;
		sendRequest(url,updateResponse);
}
 function updatedeptlu(){
	 if(window.confirm('确定要提交部留金额吗?')){
		 var todayDate="${dateyuefen}";
		 var pageStatus="${pageStatus}";
		 var deptYuInput = document.getElementById("deptYu").value;
		 var zongmoney=document.getElementById("allMoney").innerHTML;
		 var url="BonusAction!updateDeptlu.action?deptYu="+deptYuInput+"&zongjiangji="+zongmoney+"&pageStatus="+pageStatus+"&todayDate="+todayDate;
		 sendRequest(url,updateResponse);
		  }
 }

  //只能输入数字和小数点
		function clearNoNum(obj)
		 {
		  //先把非数字的都替换掉，除了数字和.
		  obj.value = obj.value.replace(/[^\d.]/g,"");
		  //必须保证第一个为数字而不是.
		  obj.value = obj.value.replace(/^\./g,"");
		  //保证只有出现一个.而没有多个.
		  obj.value = obj.value.replace(/\.{2,}/g,".");
		  //保证.只出现一次，而不能出现两次以上
		  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
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
				<form action="GoodsStoreAction.action" method="post">
					<table align="center" class="s">
						<tr>
							<td align="center">
								<input class="Wdate" type="text" name="todayDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="submit" value="查看入库数量">
							</td>
						</tr>
					</table>
				</form>
				<form action="BonusmoneyAction.action?pageStatus=${pageStatus}"
					method="post"
					onsubmit="return check1('<s:property value='bonuslist.size'/>')">
					<table align="center" class="s">
						<tr>
							<th colspan="8">
								<font size="5">奖金分配</font>
							</th>
						</tr>
						<tr>
							<th>
								成员工号
							</th>
							<th>
								成员卡号
							</th>
							<th>
								成员姓名
							</th>
							<th>
								班组
							</th>
							<th>
								成员奖金
							</th>
							<th>
								加班费及饭贴
							</th>
						</tr>
						<s:iterator id="bo" value="bonuslist" status="status">
							<s:if test="#bo.bonusteamname!='部留'">
								<tr>
									<td align="center">
										${bo.bonusmembernumber}
									</td>

									<td align="center">
										${bo.bonuscardnumber}
									</td>

									<td align="center">
										${bo.bonusteamname}
									</td>
									<td align="center">
										${bo.bonusteam}
									</td>
									<td align="center">
										<input type="text" name="chengyuangongzi"
											value="${bo.bonusmembermoney}"
											onkeyup="clearNoNum(this),addDeptYu(<s:property value='bonuslist.size-1'/>)"
											id="gongzi<s:property value='#status.index' />"
											onblur="saveChengyuan('${bo.bonusmembernumber}','<s:property value="#status.index" />')" />

									</td>
									<td align="center">
										<input type="text" name="jbfctmoney"
											value="${bo.bonusovertimemealmoney}"
											onkeyup="clearNoNum(this),addDeptYu(<s:property value='bonuslist.size-1'/>)"
											id="jiacan<s:property value='#status.index' />"
											onblur="saveChengyuan('${bo.bonusmembernumber}','<s:property value="#status.index" />')" />
									</td>
								</tr>
							</s:if>
							<s:else>

							</s:else>
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
									value="${bonus.bonusmembermoney}"
									onkeyup="deptMoney(this),clearNoNum(this)"
									onblur="updatedeptlu()" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="right">
								总额:
								<font color="red"><span id="allMoney">0</span> </font>元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>

						<tr>
							<td colspan="8" align="center">
							<input type="hidden"  id="bonus_id" value="${bonusmoney.id}" name="bonusmoney.id">
								<input type="reset" value="取  消">
								&nbsp;&nbsp;&nbsp;
								<input type="submit" value="下 一  步" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<SCRIPT type="text/javascript">
										addDeptYu(<s:property value='bonuslist.size-1'/>,'load');
				</SCRIPT>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
