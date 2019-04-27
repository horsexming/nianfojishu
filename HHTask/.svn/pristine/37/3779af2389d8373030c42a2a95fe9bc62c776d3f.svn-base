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
			<div align="center" style="height: 1000px;">
				<br />
				<h2 style="font-size: x-large;">
					对比下层外购件
				</h2>
				<form action="ProcardTemplateAction!ProcardTemduibiWgj.action"
					method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>
								第一个件号:
							</th>
							<td>
								<div id="showAll1"
									style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
								</div>
								<input type="text" value="" name="markId1" id="markId1"
									onFocus="init('1')"
									onblur="hidediv('showAll1')"
									onkeyup="getAllMarkId('1')" />
							</td>
							<th>
								第二个件号:
							</th>
							<td>
								<div id="showAll2"
									style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
								</div>
								<input type="text" value="" name="markId2" id="markId2"
									onFocus="init('2')"
									onblur="hidediv('showAll2')"
									onkeyup="getAllMarkId('2')" />
							</td>
						</tr>
						<tr>
							<th colspan="4">
								<input type="submit" value="对比" id="sub" class="input" />
							</th>
						</tr>
					</table>
				</form>
				<s:if test="markId1 !=null && markId2!=null">
					<div id="duibi_div">
						<table width="99%">
							<tr>
								<td valign="top">
									<table class="table">
										<tr>
											<th colspan="5" height="50px" style="font-size: x-large;">
												件号:${markId1}
											</th>
										</tr>
										<tr align="center" bgcolor="#c0dcf2" height="50px">
											<th>
												序号
											</th>
											<th>
												件号
											</th>
											<th>
												物料类别
											</th>
											<th>
												版本号
											</th>
											<th>
												供料属性
											</th>
										</tr>
										<s:if test="pt1_wg!=null">
											<s:iterator value="pt1_wg" id="pt1" status="pagestatus1">
												<s:if test="#pagestatus1.index == 0">
													<tr bgcolor="blue">
														<th colspan="16" align="center">
															<font color="#ffffff">不相同的外购件<br /> </font>
														</th>
													</tr>
												</s:if>
												<s:if test="#pagestatus1.index%2==1">
													<tr align="center" bgcolor="#e6f3fb"
														onmouseover="chageBgcolor(this)"
														onmouseout="outBgcolor(this,'#e6f3fb')">
												</s:if>
												<s:else>
													<tr align="center" onmouseover="chageBgcolor(this)"
														onmouseout="outBgcolor(this,'')">
												</s:else>
												<td>
													<s:property value="#pagestatus1.index+1" />
												</td>
												<td>
													${pt1.markId}
												</td>
												<td>
													${pt1.wgType}
												</td>
												<td>
													${pt1.banBenNumber}
												</td>
												<td>
													${pt1.kgliao}
												</td>
												</tr>
											</s:iterator>
										</s:if>
										<s:else>
											<tr>
												<td colspan="5">
													件号:${markId1}的下层没有与件号${markId2}不相同的外购件
												</td>
											</tr>
										</s:else>
										<s:if test="pt1_zz!=null">
											<s:iterator value="pt1_zz" id="pt2" status="pagestatus2">
												<s:if test="#pagestatus2.index == 0">
													<tr bgcolor="red">
														<th colspan="16" align="center">
															<font color="#ffffff">件号:${markId1 } 下层的自制件<br />
															</font>
														</th>
													</tr>
												</s:if>
												<s:if test="#pagestatus2.index%2==1">
													<tr align="center" bgcolor="#e6f3fb"
														onmouseover="chageBgcolor(this)"
														onmouseout="outBgcolor(this,'#e6f3fb')">
												</s:if>
												<s:else>
													<tr align="center" onmouseover="chageBgcolor(this)"
														onmouseout="outBgcolor(this,'')">
												</s:else>
												<td>
													<s:property value="#pagestatus2.index+1" />
												</td>
												<td>
													${pt2.markId}
												</td>
												<td>
													${pt2.wgType}
												</td>
												<td>
													${pt2.banBenNumber}
												</td>
												<td>
													${pt2.kgliao}
												</td>
												</tr>
											</s:iterator>
										</s:if>
									</table>
								</td>
								<td valign="top">
									<table class="table">
										<tr>
											<th colspan="5" height="50px" style="font-size: x-large;">
												件号:${markId2}
											</th>
										</tr>
										<tr align="center" bgcolor="#c0dcf2" height="50px">
											<th>
												序号
											</th>
											<th>
												件号
											</th>
											<th>
												物料类别
											</th>
											<th>
												版本号
											</th>
											<th>
												供料属性
											</th>
										</tr>
										<s:if test="pt2_wg!=null">
											<s:iterator value="pt2_wg" id="pt3" status="pagestatus3">
												<s:if test="#pagestatus3.index == 0">
													<tr bgcolor="blue">
														<th colspan="16" align="center">
															<font color="#ffffff">不相同的外购件<br /> </font>
														</th>
													</tr>
												</s:if>
												<s:if test="#pagestatus3.index%2==1">
													<tr align="center" bgcolor="#e6f3fb"
														onmouseover="chageBgcolor(this)"
														onmouseout="outBgcolor(this,'#e6f3fb')">
												</s:if>
												<s:else>
													<tr align="center" onmouseover="chageBgcolor(this)"
														onmouseout="outBgcolor(this,'')">
												</s:else>
												<td>
													<s:property value="#pagestatus3.index+1" />
												</td>
												<td>
													${pt3.markId}
												</td>
												<td>
													${pt3.wgType}
												</td>
												<td>
													${pt3.banBenNumber}
												</td>
												<td>
													${pt3.kgliao}
												</td>
												</tr>
											</s:iterator>
										</s:if>
										<s:else>
											<tr>
												<td colspan="5">
													件号:${markId2}的下层没有与件号${markId1}不相同的外购件
												</td>
											</tr>
										</s:else>
										<s:if test="pt2_zz!=null">
											<s:iterator value="pt2_zz" id="pt4" status="pagestatus4">
												<s:if test="#pagestatus4.index == 0">
													<tr bgcolor="red">
														<th colspan="16" align="center">
															<font color="#ffffff">件号:${markId2}下层的自制件<br /> </font>
														</th>
													</tr>
												</s:if>
												<s:if test="#pagestatus4.index%2==1">
													<tr align="center" bgcolor="#e6f3fb"
														onmouseover="chageBgcolor(this)"
														onmouseout="outBgcolor(this,'#e6f3fb')">
												</s:if>
												<s:else>
													<tr align="center" onmouseover="chageBgcolor(this)"
														onmouseout="outBgcolor(this,'')">
												</s:else>
												<td>
													<s:property value="#pagestatus4.index+1" />
												</td>
												<td>
													${pt4.markId}
												</td>
												<td>
													${pt4.wgType}
												</td>
												<td>
													${pt4.banBenNumber}
												</td>
												<td>
													${pt4.kgliao}
												</td>
												</tr>
											</s:iterator>
										</s:if>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</s:if>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function check(){
	var markId1	=	$("#markId1").val();
	var markId2	=	$("#markId2").val();
	if(markId1 == ""){
		alert("请选择第一个件号")
		return false;
	}else if(markId2 == ""){
		alert("请选择第二个件号")
		return false;
	}else if(markId1 == markId2){
		alert("请选择第两个不相同的件号进行比较")
		return false;
	}
	document.getElementById("sub").disabled="disabled";
}
//初始化显示div位置
function init(num) {
	count_seach++;
	var shortname = document.getElementById("markId"+num);
	var showAll = document.getElementById("showAll"+num);
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
}
//ajax获取所有的类似的全称
function getAllMarkId(num) {
	var markId =	$("#markId"+num).val();
	if(markId!=""){
			$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!findAllMarkId.action",
		dataType : "json",
		data:{markId:markId},
		success : function(data) {
			if (data != null) {
				$("#showAll"+num).empty();
				$(data).each(function(){
									$("#showAll"+num).append(
															"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this,&apos;"+num+"&apos;)' align='left'>"
																	+ this
																	+ "<span style='visibility: hidden;'>"
																	+this
																	+ "</span>"
																	+ "</div>");
					
				})
			}
		}
	})
	}

}
function selectdiv(obj,num) {
	var markId = $(obj).find("span").html();
	var showAll = document.getElementById("showAll"+num);
	showAll.style.visibility = "hidden";
	if(markId!=''){
		$("#markId"+num).val(markId);
	}
	$("#showAll"+num).hide();
}
</SCRIPT>
	</body>
</html>
