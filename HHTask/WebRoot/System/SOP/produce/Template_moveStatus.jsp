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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center">
					<font color="red">注:1.调换位置只需将两个位置的值对换一下。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
						2.插入位置将位置的值调到比前一个目标大后一个目标小，可以为小数。<br />
						(比如将第五个插入第一和第二之间可以将第五个的位置设置为1.1)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
						3.当两个件号的值相等时两个件号的排序先后会已最初始的先后排序&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</font>
				</div>
				<div align="center">
					子层零件排序
				</div>
				<div style="font-weight: bolder; font-size: 16px;">
					名称:${procardTemplate.proName}
					&nbsp;&nbsp;件号:${procardTemplate.markId} &nbsp;&nbsp;
					卡片类型:${procardTemplate.procardStyle} &nbsp;&nbsp;
					产品类型:${procardTemplate.productStyle} &nbsp;&nbsp;
					型别:${procardTemplate.carStyle}&nbsp;&nbsp;
					图号:${procardTemplate.tuhao}&nbsp;&nbsp;
					表处:${procardTemplate.biaochu}&nbsp;&nbsp;
					<s:if test="procardTemplate.procardStyle=='外购'">
					重要性:<font color="red">${procardTemplate.importance}</font>&nbsp;&nbsp;
					</s:if>
				</div>
				<form id="sonform" method="post">
					<input type="hidden" name="id" value="${procardTemplate.id}">
					<table class="table">
						<tr>
							<th>
								<input type="checkbox" id="checkAll" onchange="chageAllCheck()">
								全选
							</th>
							<th>
								件号
							</th>
							<th>
								版本
							</th>
							<th>
								名称
							</th>
							<th>
								图号
							</th>
							<th>
								规格
							</th>
							<th>
								单位
							</th>
							<th>
								类型
							</th>
							<th>
								用量
							</th>
							<th>
								是否领料
							</th>
							<th>
								位置
							</th>
						</tr>
						<tr>
							<s:iterator value="procardTemplateList" id="pageProcardTem"
								status="pageindex">
								<s:if test="#pageProcardTem.hasChange=='是'.toString()">
									<tr align="center" onmouseover="chageBgcolor(this)"
										bgcolor="yellow" onmouseout="outBgcolor(this,'')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<input type="checkbox" name="checkboxs"
										value="${pageProcardTem.id}" onchange="chageNum()" />
								</td>
								<td align="left">
									<s:property value="#pageProcardTem.markId" />
								</td>
								<td align="left">
									<s:property value="#pageProcardTem.banBenNumber" />
								</td>
								<td align="left">
									<s:property value="#pageProcardTem.proName" />
								</td>
								<td align="left">
									<s:property value="#pageProcardTem.tuhao" />
								</td>
								<td align="left">
									<s:property value="#pageProcardTem.specification" />
								</td>
								<td align="left">
									<s:property value="#pageProcardTem.unit" />
								</td>
								<td align="left">
									<s:property value="#pageProcardTem.procardStyle" />
								</td>
								<s:if test="procardTemplate.bzStatus=='已批准'">
									<td align="center" style="width: 100px;">
										<s:if test="#pageProcardTem.procardStyle=='外购'">
											<s:property value="#pageProcardTem.quanzi1" />:<s:property
												value="#pageProcardTem.quanzi2" />
										</s:if>
										<s:else>
								1:<s:property value="#pageProcardTem.corrCount" />
										</s:else>
									</td>
									<td align="center" style="width: 100px;">
										<s:if test="#pageProcardTem.lingliaostatus=='否'.toString()">
									否
								</s:if>
										<s:else>
									是
								</s:else>
									</td>
								</s:if>
								<s:else>
									<td align="center" style="width: 100px;">
										<s:if test="#pageProcardTem.procardStyle=='外购'">
											<input
												name="procardTemplateList[<s:property value="#pageindex.index" />].quanzi1"
												value="<s:property value="#pageProcardTem.quanzi1" />"
												style="width: 30px;">
								:
								<input
												name="procardTemplateList[<s:property value="#pageindex.index" />].quanzi2"
												value="<s:property value="#pageProcardTem.quanzi2" />"
												style="width: 40px;">
										</s:if>
										<s:else>
								1
								:
								<input
												name="procardTemplateList[<s:property value="#pageindex.index" />].corrCount"
												value="<s:property value="#pageProcardTem.corrCount" />"
												style="width: 40px;">
										</s:else>
									</td>
									<td align="center" style="width: 100px;">
										<SELECT
											name="procardTemplateList[<s:property value="#pageindex.index" />].lingliaostatus">
											<s:if test="#pageProcardTem.lingliaostatus=='否'.toString()">
												<option>
													否
												</option>
												<option>
													是
												</option>
											</s:if>
											<s:else>
												<option>
													是
												</option>
												<option>
													否
												</option>
											</s:else>
										</SELECT>
									</td>
								</s:else>

								<td align="center">
									<input type="hidden"
										name="procardTemplateList[<s:property value="#pageindex.index" />].id"
										value="<s:property value="#pageProcardTem.id" />">
									<input
										name="procardTemplateList[<s:property value="#pageindex.index" />].xuhao"
										value="<s:property value="#pageindex.index+1" />"
										style="width: 60px;">
								</td>
						</tr>
						</s:iterator>
						<tr align="center">

							<td colspan="11" align="center">
								<input id="deleteBtn" type="button" value="批量删除"
									onclick="deleteSons()" style="width: 80px; height: 40px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="sureBtn" type="button" value="确认"
									onclick="sonMoveStatus()" style="width: 80px; height: 40px;">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function sonMoveStatus() {
	$("#sureBtn").attr("disabled", "disabled");
	$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_sonMoveStatus.action",
				dataType : "json",
				data : $("#sonform").serialize(),
				success : function(msg) {
					alert(msg);
					$("#sureBtn").removeAttr("disabled");
					parent.loadTree();//重新加载树形
				window.location.href = "procardTemplateGyAction_moveStatus.action?id=${procardTemplate.id}";
			}
			});
}
function deleteSons() {
	$("#deleteBtn").attr("disabled", "disabled");
	$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_deleteSons.action",
				dataType : "json",
				data : $("#sonform").serialize(),
				success : function(msg) {
					alert(msg);
					$("#deleteBtn").removeAttr("disabled");
					parent.loadTree();//重新加载树形
				window.location.href = "procardTemplateGyAction_moveStatus.action?id=${procardTemplate.id}";
			}
			});
}
$(function() {
	//将主页iframe高度自适应
	$(window.parent.document).find("#showProcess").load(function() {//绑定事件
				var main = $(window.parent.document).find("#showProcess");//找到iframe对象
				//获取窗口高度 
				var thisheight;
				thisheight = document.body.scrollHeight;
				thisheight = parseFloat(thisheight);
				main.height(thisheight);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
			});
})
function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
</script>
	</body>
</html>
