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
function shuaixin() {
	document.getElementById("but").disabled = "disabled";
	$("#zifont").html("更新中请勿关闭。。。。。。。。。");
	$.ajax( {
		type : "POST",
		url : "OsTemplate_addAll.action",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data == "true") {
				alert("OK!");
				$("#zifont").html("");
				document.getElementById("shuaixin").disabled = false;
				window.location.reload();
			}
		}
	});

}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="InsTemplate_addInput.action" style="color: #ffffff">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%--					<a--%>
					<%--						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"--%>
					<%--						style="color: #ffffff">刷新</a>--%>
				</div>
			</div>
			<div align="center">
				<font id="zifont" size="8" color="red"></font>
				<form action="InsTemplate_list.action" method="post">
					<table class="table">
						<tr>
							<th>
								零件号
							</th>
							<td>
								<input type="text" name="t1.partNumber" />
							</td>
							<th>
								工序号
							</th>
							<td>
								<input type="text" name="t1.gongxuNum" />
							</td>
							<th>
								工序名称
							</th>
							<td>
								<input type="text" name="t1.gongxuName" value="${t1.gongxuName}" />
							</td>
							<s:if test="status == 'ty'">
								<td></td>
							</s:if>
						</tr>
						<tr>
							<th>
								检查类型
							</th>
							<td>
								<select name="t1.xjtype" style="width: 153px;">
									<option value=""></option>
									<option value="按时间">
										按时间
									</option>
									<option value="按次数">
										按次数
									</option>
								</select>
							</td>
							<th>
								检查频次
							</th>
							<td>
								<input type="text" name="t1.xjcheckpc" />
							</td>
							<th>
								创建人
							</th>
							<td>
								<input type="text" name="t1.username" />
							</td>
							<s:if test="status == 'ty'">
								<th>
									模板种类
								</th>
								<td>
									<select name="t.zhonglei" id="zhonglei">
										<option value=""></option>
										<option value="巡检">
											巡检
										</option>
										<option value="外购件检验">
											外购件检验
										</option>
										<option value="外委">
											外委检验
										</option>
									</select>
								</td>
							</s:if>
							<s:else>
								<td></td>
							</s:else>

							<td>
								<input type="hidden" value="${status}" name="status" />
								<input type="submit" value="查询"
									style="height: 35px; width: 75px;">
								<input id="but" type="button" value="刷新"
									style="height: 35px; width: 75px;" onclick="shuaixin()" />

							</td>
						</tr>



					</table>
				</form>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							零件号
						</th>
						<th>
							产品名称
						</th>
						<th>
							工序号
						</th>
						<th>
							工序名
						</th>
						<th>
							版本号
						</th>
						<th>
							产品类型
						</th>
						<th>
							产品型别
						</th>
						<th>
							检查类型
						</th>
						<th>
							检查频次
						</th>
						<th>
							创建时间
						</th>
						<th>
							创建人
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="t1List" status="st" id="tt">
						<s:if test="#detailsStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${st.index +1 }
						</th>
						<td>
							${tt.partNumber}
						</td>
						<td align="left">
							${tt.name}
						</td>
						<td align="left">
							${tt.gongxuNum}
						</td>
						<td align="left">
							${tt.gongxuName}
						</td>
						<td align="left">
							${tt.banbenNumber}
						</td>
						<td align="left">
							${tt.ctype1}
						</td>
						<td align="left">
							${tt.productType}
						</td>
						<td>
							${tt.xjtype}
						</td>
						<td align="left">
							<s:if test="#tt.xjcheckpc!=null && #tt.xjcheckpc != ''">
									${tt.xjcheckpc}小时每次
								</s:if>
						</td>
						<td>
							${tt.createDate}
						</td>
						<td>
							${tt.username}
						</td>
						<td>
							<%--								<a href="OsTemplate_showScope.action?t.id=${tt.id}">需检项</a>&nbsp;--%>
							<a
								href="OsTemplate_toUpdateOsTemplate.action?t.id=${tt.id}&status=xj">修改</a>/
							<a href="OsTemplate_showScope.action?t.id=${tt.id}">查看检查项</a>
<%--							<s:if test="status == 'del'">--%>
							/
							<a
									href="OsTemplate_deleteOsTemplate.action?t.id=${tt.id}&status=xj">删除</a>
<%--							</s:if>--%>
						</td>

						</tr>

					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="13" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
</html>
