<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.entity.seal.SealLog"%>
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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="sealLogAction_showList.action" style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					印章使用申请记录
					<br />
					seal apply log
				</h3>
				<form action="sealLogAction_showList.action" method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								<input type="hidden" name="pageStatus"
									value="<s:property value='pageStatus'/>" />
								申请单编号（apply number）：
								<input type="text" name="sealLog.number"
									value="<s:property value="sealLog.number"/>" />
							</td>
							<td align="center">
								印章名称（seal name）：
								<input type="text" name="sealLog.name"
									value="<s:property value="sealLog.name"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								申请人工号（apply user code）：
								<input type="text" name="sealLog.userCode"
									value="<s:property value="sealLog.userCode"/>" />
							</td>
							<td align="center">
								申请人姓名（apply user name）：
								<input type="text" name="sealLog.userName"
									value="<s:property value="sealLog.userName"/>" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="reset" style="width: 100px; height: 40px;"
									value="重置" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							申请单编号
							<br />
							(apply number)
						</td>
						<td align="center">
							印章名称
							<br />
							（seal name）
						</td>
						<td align="center">
							使用人
							<br />
							（apply user name）
						</td>
						<td align="center">
							使用用途
							<br />
							（use for）
						</td>
						<td align="center">
							是否需要存档
							<br />
							（is save）
						</td>
						<td align="center">
							是否机密
							<br />
							（is Confidential）
						</td>
						<td align="center">
							存档编号
							<br />
							（save number）
						</td>
						<td align="center">
							使用状态
							<br />
							（user status）
						</td>
						<td align="center">
							审核状态
							<br />
							（aduitStatus）
						</td>
						<td align="center" colspan="3">
							操作(Operation)
						</td>
					</tr>
					<s:iterator value="sealLogList" id="sealLogpage" status="index">
						<s:if test="#index.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#index.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#index.index+1" />
							</font>
						</td>
						<td>
							${sealLogpage.number}
						</td>
						<td>
							${sealLogpage.name}
						</td>
						<td>
							${sealLogpage.userName}
						</td>
						<td>
							${sealLogpage.useFor}
						</td>
						<td>
							${sealLogpage.isSave}
						</td>
						<td>
							${sealLogpage.isConfidential}
						</td>
						<td>
							${sealLogpage.saveNumber}
						</td>
						<td>
							<s:if test="#sealLogpage.makeSure.indexOf('公章')>=0">${sealLogpage.makeSure}</s:if>
							<s:elseif test="#sealLogpage.makeSure.indexOf('合同章')>=0">${sealLogpage.makeSure}</s:elseif>
							<s:elseif test="#sealLogpage.makeSure.indexOf('发票章')>=0">${sealLogpage.makeSure}</s:elseif>
							<s:elseif test="#sealLogpage.makeSure.indexOf('财务章')>=0">${sealLogpage.makeSure}</s:elseif>
							<s:elseif test="#sealLogpage.makeSure.indexOf('名章')>=0">${sealLogpage.makeSure}</s:elseif>
							<s:else>未使用</s:else>
							<%--							<s:if test="#sealLogpage.makeSure=='yes'">--%>
							<%--								使用</s:if>--%>
							<%--							<s:else>--%>
							<%--							 未使用--%>
							<%--							</s:else>--%>

						</td>
						<td>
							<s:if test='#sealLogpage.aduitStatus2!=null && #sealLogpage.aduitStatus2!= "同意"'>
								${sealLogpage.aduitStatus2}
							</s:if>
							<s:else>
								${sealLogpage.aduitStatus}
							</s:else>
						</td>

						<td colspan="3">
							<s:if
								test="(pageStatus=='hetong'||pageStatus=='gongzhang')&&#sealLogpage.isConfidential=='是'.toString()">
							</s:if>
							<s:else>
								<s:if test='#sealLogpage.aduitStatus2!=null && #sealLogpage.aduitStatus2!= "同意"'>
									<input type="button" value="审批动态"
									style="width: 65px; height: 30px;"
									onclick="aduitView(${sealLogpage.epId2})" />
								</s:if>
								<s:else>
									<input type="button" value="审批动态"
									style="width: 65px; height: 30px;"
									onclick="aduitView(${sealLogpage.epId})" />
								</s:else>
							</s:else>
							<s:if test="pageStatus!=null&&pageStatus=='single'">
								<s:if test='#sealLogpage.type == "合同评审"'>
									<s:if test="#sealLogpage.aduitStatus2=='未审批'||#sealLogpage.aduitStatus2=='打回'">
										<input type="button" value="修改"
										style="width: 60px; height: 30px;"
										onclick="update(${sealLogpage.id})" />
									</s:if>
									<s:elseif test='#sealLogpage.aduitStatus2=="同意"'>
										<input type="button" value="申请印章"
										style="width: 60px; height: 30px;"
										onclick="update(${sealLogpage.id})" />
									</s:elseif>
								</s:if>
								<s:else>
								<s:if
									test="(#sealLogpage.aduitStatus=='未审批'||#sealLogpage.aduitStatus=='打回')
									 ">
									<input type="button" value="修改"
										style="width: 60px; height: 30px;"
										onclick="update(${sealLogpage.id})" />
								</s:if>
								</s:else>
								
							</s:if>
							<s:else>
								<s:if
									test="#sealLogpage.aduitStatus=='同意'&&pageStatus!='single'&&pageStatus!='dept'">
									<s:if
										test="pageStatus=='gongzhang'&&#sealLogpage.name.indexOf('公章')>=0&&#sealLogpage.makeSure!=null&&#sealLogpage.makeSure.indexOf('公章')==-1">
										<input type="button" value="确认使用"
											style="width: 65px; height: 30px;"
											onclick="makeSure(${sealLogpage.id},'${cpage}')" />
											<input type="hidden" value="all" id="hid_${sealLogpage.id}"/>
									</s:if>
									<s:elseif
										test="pageStatus=='hetong'&&#sealLogpage.name.indexOf('合同章')>=0&&#sealLogpage.makeSure!=null&&#sealLogpage.makeSure.indexOf('合同章')==-1">
										<input type="button" value="确认使用"
											style="width: 65px; height: 30px;"
											onclick="makeSure(${sealLogpage.id},'${cpage}')" />
											<input type="hidden" value="all" id="hid_${sealLogpage.id}"/>
									</s:elseif>
									<s:elseif
										test="pageStatus=='fapiao'&&#sealLogpage.name.indexOf('发票章')>=0&&#sealLogpage.makeSure!=null&&#sealLogpage.makeSure.indexOf('发票章')==-1">
										<input type="button" value="确认使用"
											style="width: 65px; height: 30px;"
											onclick="makeSure(${sealLogpage.id},'${cpage}')" />
											<input type="hidden" value="all" id="hid_${sealLogpage.id}"/>
									</s:elseif>
									<s:elseif
										test="pageStatus=='all'&&#sealLogpage.makeSure!=#sealLogpage.name">
										<input type="button" value="确认使用"
											style="width: 65px; height: 30px;"
											onclick="makeSure(${sealLogpage.id},'${cpage}')" />
											<input type="hidden" value="all" id="hid_${sealLogpage.id}"/>
									</s:elseif>
									<s:elseif test="#sealLogpage.makeSure==null">
										<input type="button" value="确认使用"
											style="width: 65px; height: 30px;"
											onclick="makeSure(${sealLogpage.id},'${cpage}')" />
											<input type="hidden" value="all" id="hid_${sealLogpage.id}"/>
									</s:elseif>
								</s:if>
							</s:else>
<%--							<s:if test="pageStauts=='all'">0000</s:if>--%>
							<s:if
								test="(#sealLogpage.aduitStatus=='未审批'||#sealLogpage.aduitStatus=='打回')&&pageStatus=='single'
								">
								<input type="button" value="删除"
									style="width: 60px; height: 30px;"
									onclick="if(window.confirm('您将删除数据是否继续？')){todelete(${sealLogpage.id },${cpage})};" />
							</s:if>
							<s:if
								test="#sealLogpage.isConfidential==null||#sealLogpage.isConfidential!='是'.toString()">
								<a href="<%=path%>/<s:property value='#sealLogpage.fujian'/>"><br />下载附件</a>
							</s:if>
							<s:if test="#sealLogpage.fujian2Status=='待上传'.toString()">
								<a href="sealLogAction_tobackFujian.action?sealLog.id=${sealLogpage.id}&pageStatus=${pageStatus}" ><br/>上传加章后文件</a>
							</s:if>
							<s:elseif test="#sealLogpage.fujian2Status=='待存档'.toString()">
								<font color="red">${sealLogpage.fujian2Status}</font>
							</s:elseif>
							<s:else>
								<font color="green">${sealLogpage.fujian2Status}</font>
							</s:else>
						</td>

					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="13" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="13" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id) {
	var pageStatus = '<s:property value="pageStatus"/>';
	window.location.href = "sealLogAction_toupdate.action?pageStatus="
			+ pageStatus + "&sealLog.id=" + id;
}
function aduitView(id) {
	var pageStatus = '<s:property value="pageStatus"/>';
	window.location.href = "CircuitRunAction_findAduitPage.action?id=" + id;
}

function makeSure(id,cpage) {
	var pageStatus=document.getElementById("hid_"+id).value;
	if (pageStatus != ""&& pageStatus!=null && pageStatus!=undefined) {
		window.location.href = "sealLogAction_makeSure.action?sealLog.id=" + id
				+ "&cpage=" + cpage + "&pageStatus=" + pageStatus;
	} else {
		alert("确认失败！")
	}

}
function todelete(id, cpage) {
	var pageStatus = '<s:property value="pageStatus"/>';
	window.location.href = "sealLogAction_delete.action?sealLog.id=" + id
			+ "&cpage=" + cpage + "&pageStatus=" + pageStatus;
}
</script>
	</body>
</html>
