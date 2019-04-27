<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
						<a
						href="System/iao/iaoApply_save.jsp"
						style="color: #ffffff">添加</a>
				</div>
			</div>
			
			<div align="center">
				<form action="iaoAction!findIaoApp.action?tag=${tag}" method="post">
					<table class="table">
						<tr>
							<th>
								出入人 
							</th>
							<th>
								<input type="text" name="iaoApply.username" value="${iaoApply.username}" />
							</th>
							<th>
								出入类型
								</th>
							<th>
								<select name="iaoApply.iaoStyle" style="width:150px;">
								<option value="${iaoApply.iaoStyle}">${iaoApply.iaoStyle}</option>
								<option value="">
										无
									</option>
								<option value="公出">
											公出
										</option>
										<option value="病假">
											病假
										</option>
										<option value="事假">
											事假
										</option>
										<option value="来访">
											来访
										</option>
								</select>
							</th>
							<th>出入部门
							</th>
							<th>
								<s:if test="%{tag=='manger'}">
								<select style="width:100px;" name="iaoApply.dept" id="dept" onMouseOver="createDept('dept','iaoAction!findIAOStyle.action?tag=dept')" >
								<option value="">选择部门</option>
								<option value="${iaoApply.dept}" selected="selected" >${iaoApply.dept}</option>								
								</select>
								</s:if>
								<s:else>
								<input type="text" name="iaoApply.dept"  value="<%=user.getDept() %>" />
								</s:else>
								
							</th>
							
						</tr>
						<tr>
							<th>状态：</th>
							<th>
							<select name="iaoApply.status" style="width:150px;">
								<option value="${iaoApply.status}" selected="selected">${iaoApply.status}</option>
								<option value="">
										无
									</option>
								<option value="申请">
											申请
										</option>
										<option value="审批">
											审批
										</option>
										<option value="同意">
											同意
										</option>
										<option value="未返回">
											未返回
										</option>
										<option value="已返回">
											已返回
										</option>
								</select>
							
							</th>
							<th>
								日期从：
								</th>
							<th>
								<input class="Wdate" type="text" name="startDate"  value="${startDate}" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
								
							</th>
							<th>
								<span style="width:21px;"></span>到<span style="width:21px;"></span>：
								</th>
							<th>
								<input class="Wdate" type="text" name="endDate"  value="${endDate}" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</th>
							
						</tr>
						<tr>
						<th>车牌</th>
						<th>
						<select style="width:150px;" name="iaoApply.plateNum" id="plateNum" onMouseOver="createDept('plateNum','iaoAction!findIAOStyle.action?tag=plateNum')" >
								<option value="">选择车牌</option>
								<option value="${iaoApply.plateNum}" selected="selected" >${iaoApply.plateNum}</option>								
						</select>
						</th>
						<th>出门凭证</th>
						<th><select name="iaoApply.iaoPingzheng" style="width:150px;">
						<option value="${iaoApply.iaoPingzheng}">${iaoApply.iaoPingzheng}</option>
						<option value="">无</option>
						<option value="card">员工卡</option>
						<option value="barcode">条形码</option>
						</select></th>
						<th colspan="2">
							<input type="submit" style="width:90px;height:30px;" value="查询" />								
						
							<s:if test="%{tag=='manager'}">
							<input type="button" style="width:90px;height:30px;" value="数据导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData" />	
							</s:if>							
						</th>
						</tr>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							进出人姓名
						</th>
						<th align="center">
							进出对象
						</th>
						<th align="center">
							进出凭证
						</th>
						<th align="center">
							进出条码
						</th>
						<th align="center">
							类型
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							申请人
						</th>	
						<th align="center">
							申请出门时间
						</th>
						<th align="center">
							进门时间
						</th>
						<th align="center">
							说明
						</th>					
						<th align="center">
							随从人员
						</th>	
						<th align="center">
							操作
						</th>
					</tr>
					
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="iao">
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
								${iao.username}
							</td>
							<td>
								${iao.iaoPersonTyle}
							</td>
							<td>
							<s:if test="'card'.equals(#iao.iaoPingzheng)">
							员工卡
							</s:if>
							<s:else>条码</s:else>
							</td>
							<td>
								${iao.barcode}
							</td>
							<td>
								${iao.iaoStyle}
							</td>
							<td>
								${iao.status}
							</td>
							<td>
								${iao.applyName}
							</td>
							<td>
								${iao.applyOutTime}
							</td>
							<td>
								${iao.applyInTime}
							</td>
							<td>
								${iao.result}
							</td>
							<td>
								${iao.followPerson}
							</td>
							
							<td>
								<s:if test="'同意 '.equals(#iao.status)">
								<a href="iaoAction!getIaoAppById.action?id=${id}&crudTag=detail">打印</a>&nbsp;
								</s:if>
								<s:if test="'初始 '.equals(#iao.iaoStatus)">
								<a href="iaoAction!getIaoAppById.action?id=${id}&crudTag=update">修改</a>&nbsp;
								<a onClick="return confirm('确定要删除该条记录吗？')" href="iaoAction!getIaoAppById.action?id=${id}&crudTag=delete">删除</a>&nbsp;
								
								</s:if>
								
								 <!-- 
								<a onClick="return confirm('确定要删除该条记录吗？')" href="BaoXiaoDanAction!deleteBaoXiaoDanById.action?id=${id}" >删除</a>
								 -->
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="13" align="right">			
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
							<td colspan="13" style="font-size: 15px; color: red;">
								对不起，没有查到相关的进出申请信息
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
			objForm.action="iaoAction!exportEXCEL.action";
			objForm.submit();	
		}
		</script>
	</body>
</html>
