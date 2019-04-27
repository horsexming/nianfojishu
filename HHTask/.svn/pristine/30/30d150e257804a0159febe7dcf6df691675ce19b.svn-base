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
		<script type="text/javascript">
$(function() {
	var successMessage = $("#hid").val();
	if (successMessage == "点检完成") {
		alert(successMessage);
			parent.location.reload(true);
		}
		
	
})
</script>
	</head>
	<body style="background: #ffffff url('');" >
		<form action="MachineDayYZSJAction_add.action"
			style="margin: 0px; padding: 0px;" method="post"
			onsubmit="return checkZjForm()">
			<table class="table">
				<tr>
					<th colspan="3" align="center">
						<h1 style="margin: 0px; padding: 0px;">
							现场点检表 日期:${data}
						</h1>
					</th>
				</tr>
				<!-- 
				<tr>
					<th colspan="3">
						工区号:
						<select style="width: 155px;">
						</select>
						工位号:
						<select style="width: 155px;">
						</select>
						工装号:
						<select style="width: 155px;">
						</select>
					</th>
				</tr> -->
				<tr align="c">
					<th>
						序号
					</th>
					<th>
						点检项
					</th>
					<th>
						点检结果
					</th>
				</tr>
			<s:if test="errorMessage==null">
				<s:iterator value="djnrList" id="pageProvision" status="Status">
					<s:if test="#Status.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#Status.index+1" />
					</td>
					<td align="center">
						${pageProvision.nr}
						<input id="content${Status.index}"
							name="mdy.mddList[${Status.index}].machine_djnr"
							value="${pageProvision.nr}" style="display: none;">
					</td>
					<td>
						<input size="200px" id="hege${Status.index}"
							name="mdy.mddList[${Status.index}].dj_status" type="radio"
							 value="正常" >
						<input type="button" onclick="checkedradio('hege',${Status.index})" value="正常"/>
						<input id="buhege${Status.index}"
							name="mdy.mddList[${Status.index}].dj_status" type="radio"
							value="异常">
							<input type="button" onclick="checkedradio('buhege',${Status.index})" value="异常"/>
					</td>
					</tr>
				</s:iterator>
				<tr>
						<td colspan="3" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input class="input" type="submit" value="确定" />
							<input type="hidden" name="mdy.machine_id" value="${id}"/>
						</td>
					</tr>
				</s:if>
			<s:else>
				<tr>
					<td colspan="3">
						<font color="red" size="5">${errorMessage }</font>
					</td>
				</tr>
			</s:else>
			</table>
			<input type="hidden" value="${successMessage}" id="hid">
		</form>
		
		
		<script type="text/javascript">
$(function() {
	$(".chageGW").each(function() {
		$(this).click(function() {
			$(this).find("input").attr("checked", "checked");
		});
	});
	//将主页iframe高度自适应
	$(window.parent.document).find("#showZjProcess").load(function() {//绑定事件
				var main = $(window.parent.document).find("#showZjProcess");//找到iframe对象
				//获取窗口高度 
				var thisheight;
				thisheight = document.body.scrollHeight;
				thisheight = parseFloat(thisheight);
				main.height(thisheight);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
			});
})
function checkedradio(obj,num){
	var radio = document.getElementById(obj+num);
	if(radio!=null){
		radio.checked="checked";
	}
	
}

</script>
	</body>
</html>
