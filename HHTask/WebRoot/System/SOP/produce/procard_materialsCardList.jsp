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
									<div align="center" style="border: 1px solid #00000;">
				<form action="ProcardAction!materialsParocardList.action"
					method="post">
					<table class="table">
						<tr>
							<th colspan="6">
								生产叫料管理
							</th>
						</tr>
						<tr>
							<th>
								件号:
							</th>
							<td>
								<input name="procard.markId"
									value="${procard.markId}" />
							</td>
							<th>
								名称:
							</th>
							<td>
								<input name="procard.proName"
									value="${procard.proName}" />
							</td>
						</tr>
						<tr>
						<th>
								批次
							</th>
							<td>
								<input name="procard.selfCard"
									value="${procard.selfCard}" />
							</td>
								<th>
							</th>
							<td>
							</td>
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
								批次
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								卡片类型
							</th>
							<th align="center">
								产品类型
							</th>
							<th align="center">
								数量
							</th>
							<th align="center">
								备料数量
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageProcard"
							status="pageindex">
							<form method="post" action="ProcardAction!toMaterialsNotice.action">
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
								${pageProcard.markId}
								<input type="hidden" name="markId" value="${pageProcard.markId}">
							</td>
							<td>
								${pageProcard.selfCard}
								<input type="hidden" name="selfCard" value="${pageProcard.selfCard}">
							</td>
							<td style="width: 180px;">
								${pageProcard.proName}
							</td>
							<td>
								${pageProcard.procardStyle}
							</td>
							<td>
								${pageProcard.productStyle}
							</td>
							<td>
								<label id="filnalCount<s:property value="#pageindex.index" />">${pageProcard.filnalCount}</label>
							</td>
							<td>
								<input id="num<s:property value="#pageindex.index" />" name="num" value="${num}" onkeyup="checkNumber(<s:property value="#pageindex.index" />)"/> 
							</td>
							<td align="center">
							<input type="button" onclick="scjd(${pageProcard.rootId})" value="生产进度">
								<input type="submit" value="备料通知">
							</td>
							</tr>
							</form>
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
		 function scjd(id){
			 window.location.href="ProcardAction!findProcardView.action?id="+id+"&pageStatus=history";
		 }
		 function checkNumber(index){
			 var num=$("#num"+index).val();
			if(isNaN(num)){
			alert("请输入数字");
			$("#num"+index).val(0);
			return false;
			}
			var filnalCount =$("#filnalCount"+index).html();
			if((filnalCount-num)<0){
				alert("超出最大限额!");
				$("#num"+index).val(0);
			}
		}
		</SCRIPT>
	</body>
</html>
