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
		<a name="showTop"></a>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">项目指派</span>
						</td>
						<td align="right">
							<img id="closeimg" alt="" src="<%=basePath%>/images/closeImage.png"
								width="30" height="32" onclick="chageDiv('none');">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showZhipaiWindow" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					项目池展示
				</h3>
			</div>
			<div align="right">
				<form action="projectPoolAction_poolShowList.action" method="post">
					<input type="hidden" value="${id}" name="id">
					<table class="table" align="center">
						<tr>
							<th>
								项目池编号:
							</th>
							<td align="center">
								<input type="text" name="projectPool.poolNumber"
									value="<s:property value="projectPool.poolNumber"/>" />
							</td>
							<th>
								项目池名称:
							</th>
							<td align="center">
								<input type="text" name="projectPool.poolName"
									value="<s:property value="projectPool.poolName"/>" />
							</td>
						</tr>
						<tr>
							<th>
								录入人:
							</th>
							<td align="center">
								<input type="text" name="projectPool.addUserName"
									value="<s:property value="projectPool.addUserName"/>" />
							</td>
							<th></th>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加项目池" onclick="add()" />
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
							项目池编号
						</td>
						<td align="center">
							项目池名称
						</td>
						<td align="center">
							录入人
						</td>
						<td align="center">
							预完成时间
						</td>
						<td align="center">
							总金额
						</td>
						<td align="center">
							项目编号
						</td>
						<td align="center">
							项目名称
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
							描述
						</td>
						<td align="center">
							状态
						</td>
						<td align="center" colspan="2">
							操作
						</td>
					</tr>
					<tr>
						<td align="center" colspan="15" bgcolor="red">
							待处理
						</td>
					</tr>
					<s:set name="jydclIndex" value="0"></s:set>
					<s:iterator value="projectPoolList" id="pagepool" status="jyStatus">
						<s:if
							test="#pagepool.pmyfList==null||#pagepool.pmyfList.size()==0">
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
								<s:property value="#jyStatus.index+1" />
							</td>
							<td align="center">
								<s:property value="#pagepool.poolNumber" />
							</td>
							<td align="left">
								<s:property value="#pagepool.poolName" />
							</td>
							<td align="center">
								<s:property value="#pagepool.addUserName" />
							</td>
							<td align="center">
								<s:property value="#pagepool.reEndTime" />
							</td>
							<td align="center">
								<s:property value="#pagepool.totalMoney" />
								/元
							</td>
							<td align="center">
								<input type="button" value="编辑"
									onclick="toEdit(<s:property value="#pagepool.id"/>)">
								<br/>
								<input type="button" value="删除" onclick="delPool(<s:property value="#pagepool.id"/>)"/>
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
							<td align="center"
								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">
								<s:property value="#jyStatus.index+1" />
							</td>
							<td align="center"
								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">
								<s:property value="#pagepool.poolNumber" />
							</td>
							<td align="left"
								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">
								<s:property value="#pagepool.poolName" />
							</td>
							<td align="center"
								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">
								<s:property value="#pagepool.addUserName" />
							</td>
							<td align="center"
								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">
								<s:property value="#pagepool.reEndTime" />
							</td>
							<td align="center"
								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">
								<s:property value="#pagepool.totalMoney" />
								/元
							</td>
							<s:iterator value="#pagepool.pmyfList" id="pagepoolDetail"
								status="jydStatus">
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
									<s:property value="#pagepoolDetail.proNum" />
								</td>
								<td align="center">
									<s:property value="#pagepoolDetail.proName" />
								</td>
								<td align="center">
									<s:property value="#pagepoolDetail.zpTime" />
								</td>
								<td align="center">
									<s:property value="#pagepoolDetail.principals"/>
								</td>
								<td align="center">
									<s:property value="#pagepoolDetail.reTime" />
								</td>
								<td align="center">
									<s:property value="#pagepoolDetail.remark" />
								</td>
								<td align="center">
									<s:if
										test="#pagepoolDetail.status=='待指派'||#pagepoolDetail.status=='待执行'||#pagepoolDetail.status=='重新执行'">
										<font color="red"><s:property
												value="#pagepoolDetail.status" />
										</font>
									</s:if>
									<s:elseif test="#pagepoolDetail.status=='待确认'">
										<font color="green"><s:property
												value="#pagepoolDetail.status" />
										</font>
									</s:elseif>
									<s:elseif test="#pagepoolDetail.status=='确认关闭'">
										<font color="gary"><s:property
												value="#pagepoolDetail.status" />
										</font>
									</s:elseif>
									<s:else>
										<font color="gary"><s:property
												value="#pagepoolDetail.status" />
										</font>
									</s:else>
								</td>
								<td align="center">
									<s:if
										test="#pagepoolDetail.status=='待确认'||#pagepoolDetail.status=='确认关闭'||#pagepoolDetail.status=='重新执行'">
										<input type="button" value="文件与方案"
											onclick="toshowjyfile(<s:property value="#pagepoolDetail.id"/>)">
									</s:if>
									<s:elseif test="#pagepoolDetail.status!='完成'">
										<input type="button" value="指派"
										onclick="todispatche(<s:property value="#pagepoolDetail.id"/>)">
									</s:elseif>
								</td>
								<s:if test="#jydStatus.index==0">
									<td align="center" colspan="2"
										rowspan="<s:property value="#pagepool.pmyfList.size()"/>">
										<input type="button" value="编辑"
											onclick="toEdit(<s:property value="#pagepool.id"/>)"><br/>
								<input type="button" value="删除" onclick="delPool(<s:property value="#pagepool.id"/>)"/>
									</td>
								</s:if>
								</tr>
							</s:iterator>
						</s:else>
						<s:set name="jydclIndex" value="#jydclIndex+1"></s:set>
					</s:iterator>



					<tr>
						<td align="center" colspan="15" bgcolor="green">
							历史
						</td>
<%--					</tr>--%>
<%--					<s:set name="jyIndex" value="0"></s:set>--%>
<%--					<s:iterator value="qpjyList" id="pagepool" status="jyStatus">--%>
<%--						<s:if--%>
<%--							test="#pagepool.pmyfList==null||#pagepool.pmyfList.size()==0">--%>
<%--							<s:if test="#jyIndex%2==1">--%>
<%--								<tr align="center" bgcolor="#e6f3fb"--%>
<%--									onmouseover="chageBgcolor(this)"--%>
<%--									onmouseout="outBgcolor(this,'#e6f3fb')">--%>
<%--							</s:if>--%>
<%--							<s:else>--%>
<%--								<tr align="center" onmouseover="chageBgcolor(this)"--%>
<%--									onmouseout="outBgcolor(this,'')">--%>
<%--							</s:else>--%>
<%--							<td align="center">--%>
<%--								<s:property value="#jyStatus.index+1" />--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--								<s:property value="#pagepool.jyNumber" />--%>
<%--							</td>--%>
<%--							<td align="left">--%>
<%--								<s:property value="#pagepool.title" />--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--								<s:property value="#pagepool.addUserName" />--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--								<s:property value="#pagepool.jyTime" />--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--							</td>--%>
<%--							</tr>--%>
<%--						</s:if>--%>
<%--						<s:else>--%>
<%--							<s:if test="#jyIndex%2==1">--%>
<%--								<tr align="center" bgcolor="#e6f3fb"--%>
<%--									onmouseover="chageBgcolor(this)"--%>
<%--									onmouseout="outBgcolor(this,'#e6f3fb')">--%>
<%--							</s:if>--%>
<%--							<s:else>--%>
<%--								<tr align="center" onmouseover="chageBgcolor(this)"--%>
<%--									onmouseout="outBgcolor(this,'')">--%>
<%--							</s:else>--%>
<%--							<td align="center"--%>
<%--								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">--%>
<%--								<s:property value="#jyStatus.index+1" />--%>
<%--							</td>--%>
<%--							<td align="center"--%>
<%--								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">--%>
<%--								<s:property value="#pagepool.jyNumber" />--%>
<%--							</td>--%>
<%--							<td align="left"--%>
<%--								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">--%>
<%--								<s:property value="#pagepool.title" />--%>
<%--							</td>--%>
<%--							<td align="center"--%>
<%--								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">--%>
<%--								<s:property value="#pagepool.addUserName" />--%>
<%--							</td>--%>
<%--							<td align="center"--%>
<%--								rowspan="<s:property value="#pagepool.pmyfList.size()"/>">--%>
<%--								<s:property value="#pagepool.jyTime" />--%>
<%--							</td>--%>
<%--							<s:iterator value="#pagepool.pmyfList" id="pagepoolDetail"--%>
<%--								status="jydStatus">--%>
<%--								<s:if test="#jydStatus.index>0">--%>
<%--									<s:if test="#jyIndex%2==1">--%>
<%--										<tr align="center" bgcolor="#e6f3fb"--%>
<%--											onmouseover="chageBgcolor(this)"--%>
<%--											onmouseout="outBgcolor(this,'#e6f3fb')">--%>
<%--									</s:if>--%>
<%--									<s:else>--%>
<%--										<tr align="center" onmouseover="chageBgcolor(this)"--%>
<%--											onmouseout="outBgcolor(this,'')">--%>
<%--									</s:else>--%>
<%--								</s:if>--%>
<%--								<td align="left">--%>
<%--									<s:property value="#pagepoolDetail.proNum" />--%>
<%--								</td>--%>
<%--								<td align="center">--%>
<%--									<s:property value="#pagepoolDetail.proName" />--%>
<%--								</td>--%>
<%--								<td align="center">--%>
<%--									<s:property value="#pagepoolDetail.zpTime" />--%>
<%--								</td>--%>
<%--								<td align="center">--%>
															<s:property value="#pagepoolDetail.yqTime"/>
<%--									<s:property value="#pagepoolDetail.principals"/>--%>
<%--								</td>--%>
<%--								<td align="center">--%>
<%--									<s:property value="#pagepoolDetail.reTime" />--%>
<%--								</td>--%>
<%--								<td align="center">--%>
<%--									<s:property value="#pagepoolDetail.remark" />--%>
<%--								</td>--%>
<%--								<td align="center">--%>
<%--									<s:if--%>
<%--										test="#pagepoolDetail.status=='待指派'||#pagepoolDetail.status=='待执行'||#pagepoolDetail.status=='重新执行'">--%>
<%--										<font color="red"><s:property--%>
<%--												value="#pagepoolDetail.status" />--%>
<%--										</font>--%>
<%--									</s:if>--%>
<%--									<s:elseif test="#pagepoolDetail.status=='待确认'">--%>
<%--										<font color="green"><s:property--%>
<%--												value="#pagepoolDetail.status" />--%>
<%--										</font>--%>
<%--									</s:elseif>--%>
<%--									<s:elseif test="#pagepoolDetail.status=='确认关闭'">--%>
<%--										<font color="gary"><s:property--%>
<%--												value="#pagepoolDetail.status" />--%>
<%--										</font>--%>
<%--									</s:elseif>--%>
<%--									<s:else>--%>
<%--										<font color="gary"><s:property--%>
<%--												value="#pagepoolDetail.status" />--%>
<%--										</font>--%>
<%--									</s:else>--%>
<%--								</td>--%>
<%--								<td align="center" colspan="2">--%>
<%--															<input type="button" value="指派" onclick="todispatche(<s:property value="#pagepoolDetail.id"/>)">--%>
<%--									<s:if--%>
<%--										test="#pagepoolDetail.status=='待确认'||#pagepoolDetail.status=='确认关闭'||#pagepoolDetail.status=='重新执行'">--%>
<%--										<input type="button" value="文件与方案"--%>
<%--											onclick="toshowjyfile(<s:property value="#pagepoolDetail.id"/>)">--%>
<%--									</s:if>--%>
<%--								</td>--%>
<%--								</tr>--%>
<%--							</s:iterator>--%>
<%--						</s:else>--%>
<%--						<s:set name="jyIndex" value="#jyIndex+1"></s:set>--%>
<%--					</s:iterator>--%>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
//添加项目池
function add(){
	window.location.href ="projectPoolAction_toAddPool.action";
}
//指派
function todispatche(id){
	window.location.href="#showTop";
	$("#showZhipaiWindow").attr("src","projectPoolAction_toPoolAssign.action?id="+id);
						chageDiv('block');
	//window.location.href ="projectPoolAction_toPoolAssign.action?id="+id;
	//showZhipaiWindow
}
//编辑
function toEdit(id){
	window.location.href = "projectPoolAction_toEditPool.action?id="+id;
}
//文件与方案
function toshowjyfile(id){
	window.location.href ="projectPoolAction_toExamineSubProject.action?id="+id;
}

//删除
function delPool(id){
	if(confirm("确定要删除吗？")){
		if(confirm("真的要删除吗？")){
			if(confirm("我要删除了哦？")){
				location.href="projectPoolAction_delPool.action?id="+id;
			}
		}
	}
}

</SCRIPT>
	</body>
</html>
