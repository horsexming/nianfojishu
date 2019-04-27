<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				</div>
			</div>
			
			<div align="center">
				<form action="BaoXiaoDanAction!findSumBaoXiaoDan.action" method="post">
					<table class="table">
						<tr>
							<th>
								部门 
							</th>
							<th>
								<select style="width:100px;" name="baoxiaodan.dept" id="dept" onMouseOver="createDept('dept','BaoXiaoDanAction!findBaoXiaoStyle?tag=dept')" >
								<option value="">选择部门</option>
								<option value="${baoxiaodan.dept}" selected="selected" >${baoxiaodan.dept}</option>								
								</select>
							</th>
							<th>
								报销科目
							</th>	
							<th>
								<select style="width:100px;" name="baoxiaodan.baoxiaoClass" id="course" onMouseOver="createDept('course','BaoXiaoDanAction!findBaoXiaoStyle?tag=course')" >
								<option value="">选择科目</option>
								<option value="${baoxiaodan.baoxiaoClass}" selected="selected">${baoxiaodan.baoxiaoClass}</option>
								
								</select>
							</th>						
							<th>
								开始日期：
								</th>
							<th>
								<input class="Wdate" type="text" name="startDate"  value="${startDate}" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
								
							</th>
							<th>
								<span style="width:21px;"></span>截止日期<span style="width:21px;"></span>：
								</th>
							<th>
								<input class="Wdate" type="text" name="endDate"  value="${endDate}" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</th>
						
						<th>
							<input type="submit" style="width:60px;height:30px;" value="汇总" />		
							<input type="button" style="width:60px;height:30px;" value="导出数据" onclick="exportExcel(this.form);todisabledone(this)" data="downData">						
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
							部门
						</th>
						<th align="center">
							科目
						</th>
						<th align="center">
							合计金额
						</th>
						<th align="center">
							币种
						</th> 
						<th align="center">
							开始日期
						</th>
						<th align="center">
							截止日期
						</th>										
					</tr>
					
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="huizong">
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
								${huizong[1]}
							</td>
							<td>
							<s:if test="%{null==baoxiaodan.baoxiaoClass || ''==baoxiaodan.baoxiaoClass}">
							全部
							</s:if>
							<s:else>
							${baoxiaodan.baoxiaoClass}
							</s:else>
							
							</td>
							<td>
								<fmt:formatNumber value="${huizong[0]}" pattern="#.00"/>
							</td>
							<td>
							${huizong[2]}
							</td>
							<td>
								${startDate}
							</td>
							<td>
								${endDate}
							</td>							
							</tr>
						</s:iterator>
						<tr>
							<td colspan="7" align="right">			
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
							<td colspan="9" style="font-size: 15px; color: red;">
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
			objForm.action="BaoXiaoDanAction!exportEXCEL.action?tag=sum";
			objForm.submit();	
		}
		</script>
	</body>
</html>
