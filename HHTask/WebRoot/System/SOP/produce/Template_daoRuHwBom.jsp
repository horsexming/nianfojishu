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
			<s:if test="pageStatus=='LY'">
				<form action="procardTemplateGyAction_daoRuLYBom.action" method="post" enctype="multipart/form-data" onsubmit="return validate();">
				<input type="hidden" value="批产" name="type">
			</s:if>
			<s:else>
				<form action="procardTemplateGyAction_daoRuHwBom.action" method="post" enctype="multipart/form-data" onsubmit="return validate();">
			</s:else>
			<font size="4">导入BOM:</font>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="file" name="bomTree">
			<input id="scbtn" type="submit" value="上传" style="width: 80px;height: 20px;"><label id="lb" style="display: none;">正在导入中请耐心等待</label>
		</form>
<%--						<form action="procardTemplateGyAction_daorutest.action" method="post" enctype="multipart/form-data" onsubmit="return validate();">--%>
<%--							<font size="4">导入BOM长宽厚:</font>&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<input type="file" name="bomTree">--%>
<%--							<input id="scbtn" type="submit" value="上传" style="width: 80px;height: 20px;"><label id="lb" style="display: none;">正在导入中请耐心等待</label>--%>
<%--						</form>--%>
			</div>
			<s:if test="successMessage!=null">
				<div align="left" style="color: red;" >
					${successMessage}
				</div>
			</s:if>
			<br/>
						<div align="center" style="border: 1px solid #00000;">
					<form action="procardTemplateGyAction_toDaoRuHwBom.action"
					method="post">
					<!-- 只查询第一层
					<input type="hidden" name="procardTemplate.belongLayer" value="1" /> -->
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<th colspan="6">
								BOM管理
							</th>
						</tr>
						<tr>
							<th>
								件号(或业务件号):
							</th>
							<td>
								<input name="procardTemplate.markId"
									value="${procardTemplate.markId}" />
							</td>
							<th>
								名称:
							</th>
							<td>
								<input name="procardTemplate.proName"
									value="${procardTemplate.proName}" />
							</td>
						</tr>
						<tr>
							</td>
								<th>
								零件类型:
							</th>
							<td>
								<select name="procardTemplate.procardStyle"
									style="width: 155px;">
									<option>
										${procardTemplate.procardStyle}
									</option>
									<option></option>
									<option>
										总成
									</option>
									<option>
										组合
									</option>
									<option>
										外购
									</option>
									<option>
										自制
									</option>
								</select>
							</td>
							<th></th>
							<th></th>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询(Query)" class="input" />
								<input type="reset" value="清空(Empty)" class="input" />
							</th>
						</tr>
					</table>
				</form>
				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								零件类型
							</th>
							<th align="center">
								产品类型
							</th>
							<th align="center">
								编制状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								<a href="javascript:;"
									onclick="toshowPro('${pageProcardTem.rootId}')">
									${pageProcardTem.rootMarkId}<font color="red">(${pageProcardTem.ywMarkId})</font> </a>
							</td>
							<td style="width: 180px;">
								${pageProcardTem.proName}
							</td>
							<td>
								${pageProcardTem.procardStyle}
							</td>
							<td>
								${pageProcardTem.productStyle}
							</td>
							<td>
								${pageProcardTem.bzStatus}
							</td>
							<td align="center">
								<input type="button" value="导出"
													onclick="daochu(${pageProcardTem.id});todisabledone(this)" data="downData">
								<input type="button" value="BOM维护"
													onclick="updateBOM('<%=basePath%>','${pageProcardTem.rootId}','${tag}')">
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function daochu(id){
	window.location.href="procardTemplateGyAction_daoChuHWBom.action?id="+id;
}
function toshowPro(id) {
	$("#showProcardDetail").attr("src",
	"ProcardTemplateAction!findCardTemForShow.action?id=" + id);
	chageDiv('block');
}
function validate(){
	$("#scbtn").attr("disabled","disabled");
}
function updateBOM(parth, id,tag) {
	if(tag!=''){
		window.open(parth + "System/SOP/produce/Template_findProcardjjbom.jsp?id=" + id);
	}else{
		window.open(parth + "System/SOP/produce/Template_findProcard.jsp?id=" + id);	
	}
	
}
		</SCRIPT>
	</body>
</html>
