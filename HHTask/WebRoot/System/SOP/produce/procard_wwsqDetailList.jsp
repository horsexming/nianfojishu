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
  <style type="text/css"> 　　
       .chk_1 { 
    display: none; 
} 
 
.chk_1 + label { 
    background-color: #FFF; 
    border: 1px solid #C1CACA; 
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05); 
    padding: 9px; 
    border-radius: 5px; 
    display: inline-block; 
    position: relative; 
    margin-right: 30px; 
} 
.chk_1 + label:active { 
    box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1); 
} 
 
.chk_1:checked + label { 
    background-color: #ECF2F7; 
    border: 1px solid #92A1AC; 
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05), inset 15px 10px -12px rgba(255, 255, 255, 0.1); 
    color: #243441; 
} 
 
.chk_1:checked + label:after { 
    content: '\2714'; //勾选符号 
    position: absolute; 
    top: 0px; 
    left: 0px; 
    color: #00FF00; 
    width: 100%; 
    text-align: center; 
    font-size: 1.4em; 
    padding: 1px 0 0 0; 
    vertical-align: text-top; 
} 　
        </style> 
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="ProcardAction!wwsq.action" method="post">
				<table class="table">
					<tr>
						<th>序号
						</th>
						<th>件号
						</th>
						<th>名称
						</th>
						<th>批次
						</th>
						<th>数量
						</th>
					</tr>
					<s:iterator value="procardList" id="pageProcard" status="step1">
							<s:if test="#step1.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#step1.index+1" />
							</td>
							<td>
								${pageProcard.markId}
							</td>
							<td>
								${pageProcard.proName}
							</td>
							<td>
								${pageProcard.selfCard}
							</td>
							<td>
								${pageProcard.filnalCount}
							</td>
							</tr>
							<tr>
									<td rowspan="<s:property value="#pageProcard.processList.size()+1"/>"></td>
									<td colspan="4" bgcolor="#b6f3fb">
										<table class="table">
											<tr>
											<th width="20%">工序号
											</th>
											<th width="20%">工序名称
											</th>
											<th width="20%">预选数量
											</th>
											<th width="20%">合同状态
											</th>
											<th width="20%">选择
											</th>
											</tr>
										</table>
									</td>
								</tr>
							<s:iterator value="#pageProcard.processList" id="pageProcess" status="step2">
								<tr>
									<td colspan="4" bgcolor="#b6f3fb">
										<table class="table">
											<tr>
											<td align="center" width="20%">${pageProcess.processNO}
											</td>
											<td align="center" width="20%">${pageProcess.processName}
											</td>
											<td align="center" width="20%">${pageProcess.selectWwCount}
											</td>
											<td align="center" width="20%">${pageProcess.htStatus}
											</td>
											<td align="center" width="20%">
												<s:if test="#pageProcess.htStatus!='可同'">
													<font color="red">请先录入外委合同<font/>
												</s:if>
												<s:else>
												<input style="display: none;" class="chk_1" type="checkbox" id="process_<s:property value="#step1.index" />_<s:property value="#step2.index" />"  name="selected" value="${pageProcess.id}">
												<label for="process_<s:property value="#step1.index" />_<s:property value="#step2.index" />">确定</label>
												</s:else>
											</td>
											</tr>
										</table>
									</td>
								</tr>
							</s:iterator>
						</s:iterator>
					<tr>
						<td align="center" colspan="5">
						<input type="button" style="height: 40px;width: 80px;" value="提交">
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
