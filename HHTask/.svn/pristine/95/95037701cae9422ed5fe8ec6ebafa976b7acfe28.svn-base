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
		<div id="gongneng" >
			
			<div align="center">
				<form action="OSWorkAction!findAllOSW.action" method="post">
					<table class="table" style="width: 85%;">
						<tr>
							<td>
								合同号：
								<select name="osWork.contractNO" id="contractNO"
									style="width: 140px;"
									onMouseOver="createDept('contractNO','OSWorkAction!selectItem.action?tag=contractNO')">
									<option value=""></option>
									<option value="${osWork.contractNO}"
										selected="selected">
										${osWork.contractNO}
									</option>
								</select>

							</td>
							<td>
								批次：
								<select name="osWork.lotId" id="lotId"
									style="width: 140px;"
									onMouseOver="createDept('lotId','OSWorkAction!selectItem.action?tag=lotId')">
									<option value=""></option>
									<option value="${osWork.lotId}"
										selected="selected">
										${osWork.lotId}
									</option>
								</select>
							</td>
							<td>
								件号：
								<select name="osWork.markID" id="markID"
									style="width: 140px;"
									onMouseOver="createDept('markID','OSWorkAction!selectItem.action?tag=markID')">
									<option value=""></option>
									<option value="${osWork.markID}" selected="selected">
										${osWork.markID}
									</option>
								</select>
							</td>
							<td rowspan="3" align="center">
								<input type="submit" style="width: 50px; height: 70px;"
									value="查询" />
								&nbsp;
								<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData" style="width:45px;height:30px；;margin-top:5px;"/> &nbsp;					
					
							</td>
						</tr>
						<tr>
							<td>
								名称：
								<select name="osWork.partName" id="partName"
									style="width: 140px;"
									onMouseOver="createDept('partName','OSWorkAction!selectItem.action?tag=partName')">
									<option value=""></option>
									<option value="${osWork.partName}" selected="selected">
										${osWork.partName}
									</option>
								</select>
							</td>

							<td>
								日期从：
								<input class="Wdate" type="text" name="startDate"
									value="${ startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />

							</td>
							<td>
								<span style="width: 21px;"></span>到
								<span style="width: 21px;"></span>：
								<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td>条码：<input type="text" name="osWork.number" value="${osWork.number }" /></td>
							<td>状态：
								<select name="osWork.status" id="status"
									style="width: 140px;"
									onMouseOver="createDept('status','OSWorkAction!selectItem.action?tag=status')">
									<option value=""></option>
									<option value="${osWork.status}" selected="selected">
										${osWork.status}
									</option>
								</select>
								</td>
							<td>班组：
								<select name="osWork.dept" id="dept"
									style="width: 140px;"
									onMouseOver="createDept('dept','OSWorkAction!selectItem.action?tag=dept')">
									<option value=""></option>
									<option value="${osWork.dept}" selected="selected">
										${osWork.dept}
									</option>
								</select>
								</td>	
						</tr>
						</form>
						<table class="table" style="width: 95%;">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									条码
								</th>
								<th align="center">
									零件号
								</th>
								<th align="center">
									零件名
								</th>
								<th align="center">
									批次
								</th>
								<th align="center">
									外委数量
								</th>
								<th align="center">
									接收数量
								</th>
								<th align="center">
									单位
								</th>
								<th align="center">
									合同编号
								</th>
								<th align="center">
									外委时间
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									外委人
								</th>
								<th align="center">
									接收人
								</th>
								<th align="center">
									说明
								</th>
								<th align="center">
									操作
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="osw">
									<s:if test="#se.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#se.index+1" />
									</td>
									<td>
										${osw.number}
									</td>
									<td>
										${osw.markID}<s:if test="#osw.ywMarkId!=null"><font color="red">(${osw.ywMarkId})</font></s:if>
									</td>
									<td>
										${osw.partName}
									</td>
									<td>
										${osw.lotId}
									</td>

									<td>
										${osw.outSourceCount }
									</td>
									<td>
										${osw.receiveCount}
									</td>
									<td>
										${osw.unit}
									</td>
									<td>
										${osw.contractNO}
									</td>
									<td>
										${osw.outSourceTime}
									</td>
									<td>
										${osw.status}
									</td>
									<td>
										${osw.username}
									</td>
									<td>
										${osw.receivePerson}
									</td>
									<td>
										${osw.explain}
									</td>
									<td>
										<a href="OSWorkAction!getOneOSWorkById.action?id=${id}">查看</a>
									</td>

									</tr>
								</s:iterator>
								<tr>
									<td colspan="15" align="right">
										共
										<s:property value="total" />
										页 第
										<s:property value="cpage" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />

									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td style="font-size: 15px; color: red;">
										对不起，没有查到相关的标识贴信息
									</td>
								</tr>
							</s:else>
						</table>
						</div>
						<br>
						</div>
						<%@include file="/util/foot.jsp"%>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
					<script type="text/javascript">
					function exportExcel(objForm) {	
					objForm.action="OSWorkAction!exportEXCEL.action";
					objForm.submit();	
					}
					</script>
	</body>
</html>
