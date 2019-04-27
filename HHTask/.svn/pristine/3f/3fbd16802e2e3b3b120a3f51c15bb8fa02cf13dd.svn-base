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
				<h3>进度纪要</h3>
			</div>
			<div align="right">
			<form action="QuotedPrice_findjyList.action"
					method="post">
					<input type="hidden" value="${id}" name="id">
					<table class="table" align="center">
						<tr>
							<th>纪要时间 起：</th>
							<td align="center">
								<input type="text" name="start" value="<s:property value="start"/>" class="Wdate"	onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
							<th>纪要时间 止：</th>
							<td align="center">
								<input type="text" name="end" value="<s:property value="end"/>" class="Wdate"	onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<th>主题：</th>
							<td align="center">
								<input type="text" name="title" value="<s:property value="title"/>" />
							</td>
							<th>内容：</th>
							<td align="center">
								<input type="text" name="jyContext" value="<s:property value="jyContext"/>" />
							</td>
						</tr>
						<tr>
							<th>执行人员工号：</th>
							<td align="center">
								<input type="text" name="code" value="<s:property value="code"/>" />
							</td>
							<th>执行人员姓名：</th>
							<td align="center">
								<input type="text" name="name" value="<s:property value="name"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加纪要)" onclick="add()"/>
							</td>
						</tr>
					</table>
				</form>
<%--				<input type="button" style="width: 100px; height: 40px;"--%>
<%--									value="添加纪要)" onclick="add()"/>--%>
			</div>
			<div align="center">
				<table class="table">
				<tr>
					<td align="center">
					序号
					</td>
					<td align="center">
						编号
					</td>
					<td align="center">
						标题
					</td>
					<td align="center">
						录入人
					</td>
					<td align="center">
						纪要时间
					</td>
					<td align="center">
						内容
					</td>
					<td align="center">
						指派时间
					</td>
					<td align="center">
						负责人
					</td>
					<td align="center">
						预完成时间
					</td>
					<td align="center">
						状态
					</td>
					<td align="center" colspan="2">
						操作
					</td>
				</tr>
				<tr>
					<td align="center" colspan="12" bgcolor="red">待处理</td>
				</tr>
				<s:set name="jydclIndex" value="0"></s:set>
				<s:iterator value="qpjydclList" id="pageqpjy" status="jyStatus">
				<s:if test="#pageqpjy.qpjyDetailList==null||#pageqpjy.qpjyDetailList.size()==0">
					<s:if test="#jydclIndex%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
						<s:property value="#jyStatus.index+1"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjy.jyNumber"/>
					</td>
					<td align="left">
						<s:property value="#pageqpjy.title"/>
					</td>
					<td align="center">
					<s:property value="#pageqpjy.addUserName"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjy.jyTime"/>
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
						<input type="button" value="编辑" onclick="toEdit(<s:property value="#pageqpjy.id"/>)">
					</td>
						</tr>
				</s:if>
				<s:else>
					<s:if test="#jydclIndex%2==1">
							<tr align="center" bgcolor="#e6f3fb" 
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<s:property value="#jyStatus.index+1"/>
					</td>
					<td align="center" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<s:property value="#pageqpjy.jyNumber"/>
					</td>
					<td align="left" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<s:property value="#pageqpjy.title"/>
					</td>
					<td align="center" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
					<s:property value="#pageqpjy.addUserName"/>
					</td>
					<td align="center" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<s:property value="#pageqpjy.jyTime"/>
					</td>
					<s:iterator value="#pageqpjy.qpjyDetailList" id="pageqpjyDetail" status="jydStatus">
					<s:if test="#jydStatus.index>0">
					<s:if test="#jydclIndex%2==1">
							<tr align="center" bgcolor="#e6f3fb" 
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
					</s:if>
					<td align="left">
						<s:property value="#pageqpjyDetail.jyContext"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjyDetail.zpTime"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjyDetail.zxUsers"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjyDetail.yqTime"/>
					</td>
					<td align="center">
						<s:if test="#pageqpjyDetail.status=='待指派'||#pageqpjyDetail.status=='待执行'||#pageqpjyDetail.status=='重新执行'">
						<font color="red"><s:property value="#pageqpjyDetail.status"/></font>
						</s:if>
						<s:elseif test="#pageqpjyDetail.status=='待确认'">
						<font color="green"><s:property value="#pageqpjyDetail.status"/></font>
						</s:elseif>
						<s:elseif test="#pageqpjyDetail.status=='确认关闭'">
						<font color="gary"><s:property value="#pageqpjyDetail.status"/></font>
						</s:elseif>
						
					</td>
					<td align="center" >
						<input type="button" value="指派" onclick="todispatche(<s:property value="#pageqpjyDetail.id"/>)">
						<s:if test="#pageqpjyDetail.status=='待确认'||#pageqpjyDetail.status=='确认关闭'||#pageqpjyDetail.status=='重新执行'">
						<input type="button" value="文件与方案" onclick="toshowjyfile(<s:property value="#pageqpjyDetail.id"/>)">
						</s:if>
					</td>
					<s:if test="#jydStatus.index==0">
					<td align="center" colspan="2" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<input type="button" value="编辑" onclick="toEdit(<s:property value="#pageqpjy.id"/>)">
					</td>
					</s:if>
					</tr>
					</s:iterator>
				</s:else>
				<s:set name="jydclIndex" value="#jydclIndex+1"></s:set>
				</s:iterator>
				
				
				
				<tr>
					<td align="center" colspan="12" bgcolor="green">历史</td>
				</tr>
				<s:set name="jyIndex" value="0"></s:set>
				<s:iterator value="qpjyList" id="pageqpjy" status="jyStatus">
				<s:if test="#pageqpjy.qpjyDetailList==null||#pageqpjy.qpjyDetailList.size()==0">
					<s:if test="#jyIndex%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
						<s:property value="#jyStatus.index+1"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjy.jyNumber"/>
					</td>
					<td align="left">
						<s:property value="#pageqpjy.title"/>
					</td>
					<td align="center">
					<s:property value="#pageqpjy.addUserName"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjy.jyTime"/>
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
					<td align="center">
					</td>
						</tr>
				</s:if>
				<s:else>
					<s:if test="#jyIndex%2==1">
							<tr align="center" bgcolor="#e6f3fb" 
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<s:property value="#jyStatus.index+1"/>
					</td>
					<td align="center" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<s:property value="#pageqpjy.jyNumber"/>
					</td>
					<td align="left" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<s:property value="#pageqpjy.title"/>
					</td>
					<td align="center" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
					<s:property value="#pageqpjy.addUserName"/>
					</td>
					<td align="center" rowspan="<s:property value="#pageqpjy.qpjyDetailList.size()"/>">
						<s:property value="#pageqpjy.jyTime"/>
					</td>
					<s:iterator value="#pageqpjy.qpjyDetailList" id="pageqpjyDetail" status="jydStatus">
					<s:if test="#jydStatus.index>0">
					<s:if test="#jyIndex%2==1">
							<tr align="center" bgcolor="#e6f3fb" 
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
					</s:if>
					<td align="left">
						<s:property value="#pageqpjyDetail.jyContext"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjyDetail.zpTime"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjyDetail.zxUsers"/>
					</td>
					<td align="center">
						<s:property value="#pageqpjyDetail.yqTime"/>
					</td>
					<td align="center">
						<s:if test="#pageqpjyDetail.status=='待指派'||#pageqpjyDetail.status=='待执行'||#pageqpjyDetail.status=='重新执行'">
						<font color="red"><s:property value="#pageqpjyDetail.status"/></font>
						</s:if>
						<s:elseif test="#pageqpjyDetail.status=='待确认'">
						<font color="green"><s:property value="#pageqpjyDetail.status"/></font>
						</s:elseif>
						<s:elseif test="#pageqpjyDetail.status=='确认关闭'">
						<font color="gary"><s:property value="#pageqpjyDetail.status"/></font>
						</s:elseif>
					</td>
					<td align="center" colspan="2">
<%--						<input type="button" value="指派" onclick="todispatche(<s:property value="#pageqpjyDetail.id"/>)">--%>
						<s:if test="#pageqpjyDetail.status=='待确认'||#pageqpjyDetail.status=='确认关闭'||#pageqpjyDetail.status=='重新执行'">
						<input type="button" value="文件与方案" onclick="toshowjyfile(<s:property value="#pageqpjyDetail.id"/>)">
						</s:if>
					</td>
					</tr>
					</s:iterator>
				</s:else>
				<s:set name="jyIndex" value="#jyIndex+1"></s:set>
				</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function add(){
	window.location.href ="QuotedPrice_toaddjy.action?id=${id}";
}
function todispatche(id){
	window.location.href ="QuotedPrice_tojydispatche.action?id="+id;
}
function toshowjyfile(id){
	window.location.href ="QuotedPrice_toshowjyfile.action?id="+id;
}
function toEdit(id){
	window.location.href = "QuotedPrice_toEditjy.action?id="+id;
}
</SCRIPT>
	</body>
</html>
