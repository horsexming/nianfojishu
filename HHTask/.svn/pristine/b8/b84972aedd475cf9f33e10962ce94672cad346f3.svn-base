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
.sss {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

.sss th,.sss td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="" style="color: #ffffff">添加功能</a>
				</div>
			</div>

			<div align="center">
			<form action="MentionrecordAction!updateMentionrecord1.action" method="post">
				<table class="table">
				<tr>
							<td align="right" colspan="12">
								<font color="red">共选择 <label id="peopleLabel">
										${count}
									</label> <input type="hidden" id="propleText" name="peopleNum"
										style="width: 20px;" readonly="readonly"> 条记录</font>
								<br>
								<br>
							</td>
						</tr>
					<tr>
						<th>
							月份
						</th>
						<th>
							应提奖额
						</th>
						<th>
							提奖状态
						</th>
						<th>
							实际提奖额
						</th>
						<th>
							操作
						</th>
						<th align="center" style="width: 40px;">
								<input type="checkbox" id="checkAll"
									onclick="chageAllCheck(this)">
								全选
							</th>
					</tr>
					<s:iterator id="m" value="list" status="stauts">
						<s:if test="#stauts.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
							${m.mentionMonth}
						</td>
						<td align="center">
							${m.mentionshallMoney }
						</td>
						<td align="center">
							${m.mentionstatus}
						</td>
						<td align="center">
							${m.mentionactualMoney}
						</td>
						<td align="center">
							<s:if test="pageStatus=='hr'">
								<a
									href="TijingAction!showtijing.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查询详细</a>
								<a
									href="TijingAction!findGoodStore.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查看入库记录</a>
							</s:if>
							&nbsp;&nbsp;&nbsp;
							<s:if test="pageStatus=='all'">
								<a
									href="TijingAction!showtijing.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查询详细</a>
								<a
									href="TijingAction!findGoodStore.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查看入库记录</a>
<%--								<s:if test="mentionstatus=='可提奖'">--%>
<%--									<font color="gray">可提奖</font>--%>
<%--								</s:if>--%>
<%--								<s:elseif test="mentionstatus=='审核中'">--%>
<%--									<a--%>
<%--										href="MentionrecordAction!updateMentionrecord.action?id=${m.id}">可提奖</a>--%>
<%--								</s:elseif>--%>
							</s:if>
							<s:elseif test="pageStatus=='dept'">
								<a
									href="TijingAction!showtijing.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查询详细</a>
								<a
									href="TijingAction!findGoodStore.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查看入库记录</a>
								<s:if test="mentionstatus=='可提奖'">
									<a href="MentionrecordAction!updateFind.action?id=${m.id}">提奖</a>
								</s:if>
								<s:elseif test="mentionstatus=='已提奖'">
									<font color="gray">提奖</font>
								</s:elseif>
							</s:elseif>
						</td>
							<td>
								<s:if test="mentionstatus=='审核中'">
								<input type="checkbox"  
									name="detailSelect" value="${m.id}"  onclick="chageNum(this)">
									 </s:if>
									 <s:if test="mentionstatus=='审批中'">
								<input type="checkbox"  
									name="detailSelect" value="${m.id}"  onclick="chageNum(this)">
									 </s:if>
									 <a href="CircuitRunAction_findAduitPage.action?id=${m.epId}"">审批动态</a>
							</td>
						</tr>
					</s:iterator>
					<tr>
								<td colspan="9" align="right"
								style="font-weight: bold; padding-right: 40px">
								<input type="checkbox" id="checkAll2"
									onclick="chageAllCheck(this)">
								全选
							</td>
						</tr>
					<tr>
						<td colspan="16" align="right">
						<font color="red">共选择 <label id="peopleLabel2">
										${count}
									</label> <input type="hidden" id="propleText" name="peopleNum"
										style="width: 20px;" readonly="readonly"> 条记录</font>
										
										<input id="ok" class="input"  style="width:120px;" align="top" type="button" value="批量审批通过" onclick="chageType(this,this.form)"/>
<%--    									<input id="ng" class="input" align="top" type="button" value="批量驳回" onclick="chageType(this,this.form)" />--%>
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
				</table>
					</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	<script type="text/javascript">
    	function chageType(obj,form){
    		if(obj.id=="ok"){
    			form.action="MentionrecordAction!updateMentionrecord1.action?tag=ok";
    			form.submit();
    		}else if (obj.id=="ng"){
    			form.action="MentionrecordAction!updateMentionrecord1.action?tag=ng";
    			form.submit();
    		}
    	}

 
	function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox,checkBox.id);
				}
			}
		}
	}
}
		var num = "${count}";
		if (num == "") {
			num = 0;
		}
		var money = 0;
function chageNum(obj,obj2) {
	
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		money+=parseFloat(obj2);
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			checkAll2.checked = true;
		}
		num++;
	} else if (num == 0 && check.checked == false) {
		money=0;
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		money=money-obj2;
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
}
</script>
</html>
