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
		<div id="gongneng">

			<div align="center">
				<h3>
					${message}
				</h3>
				<form
					action="internalOrder_batchConversion.action?pageStatus=${pageStatus}"
					method="post" onsubmit="return vali()">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								件号
							</td>
							<td align="center">
								业务件号
							</td>
							<td align="center">
								产品名称
							</td>
							<td align="center">
								总数量
							</td>
							<td align="center">
								可分配数量
							</td>
							<td align="center">
								分配数量
							</td>
							<td align="center">
								交付日期
							</td>
							<td align="center">
								备注
							</td>
						</tr>
						<s:iterator value="detailLis" id="pageList" status="pageState">
							<s:if test="#pageState.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								<label id="${pageState.index}_pieceNum" style="color: red;">
									${pageList.pieceNumber }
								</label>
							</td>
							<td>
								${pageList.ywMarkId }
							</td>
							<td>
								${pageList.name}
							</td>
							<td>
								${pageList.num}
							</td>
							<td>
								<s:if test="#pageList.hasTurn==null">
							 ${pageList.num}<label style="display: none;"
										id="surplusCount_${pageState.index}">
										${pageList.num}
									</label>
								</s:if>
								<s:else>
							${pageList.num-pageList.hasTurn}<label style="display: none;"
										id="surplusCount_${pageState.index}">
										${pageList.num-pageList.hasTurn}
									</label>
								</s:else>
							</td>
							<td>
								<input type="text" name="selecteds" id="${pageState.index}_num"
									value="${pageList.num-pageList.hasTurn}"
									onkeyup="send(${pageState.index})" />
								<input type="hidden" name="pieceNum"
									value="${pageList.pieceNumber}" />
							</td>
							<td>
								<input type="text" value="${pageList.paymentDate}" readonly="readonly"/>
							</td>
							<td>
								<textarea name="remerk" cols="20" rows="3">${pageList.remark}</textarea>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td align="center" colspan="10">
								<strong>计划月份:</strong>
								<input style="width: 155px" class="Wdate" type="text" id="title"
									name="title"
									onclick="WdatePicker({dateFmt:'yyyy年MM月',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="10">
								<input type="hidden" name=orderNum value="${beginTime}" id="hid" />
								<input type="hidden" name="pageStatus" value="${pageStatus}" />
								<input type="hidden" name="tag" value="${tag}" />
								<input type="submit" value="转换订单"
									style="width: 100px; height: 50px;" onclick="todisabled(this)" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<script type="text/javascript">
var exp = /^\d+$/;
var bol = true;
function vali() {
	var title = $("#title").val();
	$("input[name='selecteds']").each(function() {
		var va = $(this).val();
		if (exp.test(va)) {
			bol = true;
		} else {
			bol = false;
			return;
		}
	});
	if (!bol) {
		alert("请输入数字!谢谢");
		return false;
	} else if (title == "") {
		alert("请输入计划月份");
		return false;
	}
	return true;
}
function send(i) {
	var surplusCount = $("#surplusCount_" + i).html();
	var num = $('#' + i + '_num').val();
	if ((num - surplusCount) > 0) {
		alert("超出可分配数量!");
		$('#' + i + '_num').val(0);
	}
<%--	$.ajax( {--%>
<%--		url : "internalOrder_validateNum.action",--%>
<%--		type : "post",--%>
<%--		dataType : "json",--%>
<%--		data : {--%>
<%--			title : pieceNum,--%>
<%--			message : num,--%>
<%--			orderNum : hid--%>
<%--		},--%>
<%--		success : function(data) {--%>
<%--			if (data) {--%>
<%--				if (data.msg) {--%>
<%--					alert(data.msg);--%>
<%--				}--%>
<%--			}--%>
<%----%>
<%--		}--%>
<%--	});--%>
}
</script>
	</body>
</html>
