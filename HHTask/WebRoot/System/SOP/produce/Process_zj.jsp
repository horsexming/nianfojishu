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
function checkZjForm() {
	var size = "${pageSize}"
	for ( var i = 1; i <= size; i++) {
		var val = $('input:radio[name="isQualifiedList[' + i + ']"]:checked')
				.val();
		if (val == null) {
			var content = $("#content" + i).val();
			alert("第" + i + "行 《" + content + "》 尚未检查!请检查后提交!");
			return false;
		}
	}
	return true;
}

function checkRadio(id) {
	$("#" + id).attr("checked", "checked");
}
$(function() {
	var successMessage = "${successMessage}";
	if (successMessage != "") {
		alert(successMessage)
		parent.location.reload(true);
	}
})

//window.open("DJNRAction_getdjnrbyId.action?id=${id}");

//parent.location.reload(true);

</script>
	</head>
	<body style="background: #ffffff url('');">
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">设备日点检</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<form action="ProcardAction!addZj.action"
			style="margin: 0px; padding: 0px;" method="post"
			onsubmit="return checkZjForm()">
			<input name="process.id" type="hidden" value="${id}" />
			<table class="table">
				<tr>
					<th colspan="3" align="center">
						<h1 style="margin: 0px; padding: 0px;">
							现场自检表 日期:
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
				<tr>
					<th>
						序号
					</th>
					<th>
						自检项
					</th>
					<th>
						自检结果
					</th>
				</tr>
				<s:iterator value="list" id="pageProvision" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#pageStatus.index+1" />
					</td>
					<td align="left">
						${pageProvision.content}
						<input id="content${pageStatus.index+1}"
							name="contentList[${pageStatus.index+1}]"
							value="${pageProvision.content}" style="display: none;">
					</td>
					<td>
						<input size="200px" id="hege${pageStatus.index+1}"
							name="isQualifiedList[${pageStatus.index+1}]" type="radio"
							checked="checked" value="合格">
						<input type="button"
							onclick="checkRadio('hege${pageStatus.index+1}')" value="合格">
						<input id="buhege${pageStatus.index+1}"
							name="isQualifiedList[${pageStatus.index+1}]" type="radio"
							value="不合格">
						<input type="button"
							onclick="checkRadio('buhege${pageStatus.index+1}')" value="不合格">
					</td>
					</tr>
				</s:iterator>
				<s:if test="machineList!=null&&machineList.size()>0">
					<tr>
						<td colspan="3">
							<table class="table">
								<tr>
									<td colspan="5" align="center">
										选择设备(设备编号为manual的设备表示虚拟手工设备可重复选择)
									</td>
									<tr>
										<th>
											序号
										</th>
										<th>
											设备编号
										</th>
										<th>
											设备名称
										</th>
										<th>
											工位
										</th>
										<th>
											选择
										</th>
									</tr>
									<s:iterator value="machineList" id="pagemachine"
										status="pageStatus2">
										<s:if test="#pageStatus2.index%2==1">
											<tr class="chageGW" align="center" bgcolor="#e6f3fb"
												onmouseover="chageBgcolor(this)"
												onmouseout="outBgcolor(this,'#e6f3fb')">
										</s:if>
										<s:else>
											<tr class="chageGW" align="center"
												onmouseover="chageBgcolor(this)"
												onmouseout="outBgcolor(this,'')">
										</s:else>
										<td>
											<s:property value="#pageStatus2.index+1" />
										</td>
										<td>
											<s:property value="#pagemachine.no" />
										</td>
										<td>
											<s:property value="#pagemachine.name" />
										</td>
										<td>
											<s:property value="#pagemachine.workPosition" />
										</td>
										<td>
											<s:if test="process.shebeiNo==#pagemachine.no">
												<input type="radio" name="id" checked="checked"
													value="${pagemachine.id}">
											</s:if>
											<s:else>
												<input type="radio" name="id" value="${pagemachine.id}">
											</s:else>
										</td>
								</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input class="input" type="submit" value="确定" id="sub"
								onclick="todisabled(this)" />
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="3" align="center">
							<s:if test="errorMessage!=null">
								<font color="red"><h3>
										${errorMessage}
									</h3> </font>
							</s:if>
							<s:else>
								<font color="red"><h3>
										对不起该工序没有对应工位，请联系负责人!
									</h3> </font>
							</s:else>

						</td>
					</tr>
				</s:else>
			</table>
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
</script>
	</body>
</html>
