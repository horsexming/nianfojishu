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
						href="System/caiwu/baoxiao/baoXiaoDan_save.jsp"
						style="color: #ffffff">添加</a>
				</div>
			</div>
			
			<div align="center">
				<form action="BaoXiaoDanAction!findBaoXiaoDanDetail.action" method="post">
					<table class="table">
						<tr>
							<th>
								收款单位(个人) 
							</th>
							<th>
								<select style="width:150px;" name="baoxiaodan.shoukuanRen" value="${baoxiaodan.shoukuanRen}" id="shoukuanRen" onMouseOver="createDept('shoukuanRen','BaoXiaoDanAction!findBaoXiaoStyle?tag=shoukuanRen')">
								
								<option value="${baoxiaodan.shoukuanRen}">${baoxiaodan.shoukuanRen}</option>
								<option value="">
										收款单位
									</option>
								</select>
							</th>
							<th>
								付款方式
								</th>
							<th>
								<select name="baoxiaodan.payStyle" style="width:150px;">
								<option value="${baoxiaodan.payStyle}">${baoxiaodan.payStyle}</option>
								<option value="">
										无
									</option>
								<option value="现金">
										现金
									</option>
									<option value="银行对公转账">
										银行对公转账
									</option>
									<option value="归还借款">
										归还借款
									</option>
									<option value="其他">
										其他
									</option>
								</select>
							</th>
							<th>报销人
							</th>
							<th>
								<input type="text" name="baoxiaodan.baoxiaoren"  value="${baoxiaodan.baoxiaoren}" />
							</th>
							<th rowspan="3">
							
							<input type="submit" style="width:80px;height:80px;" value="查询" />								
							<input type="button" style="width:80px;height:80px;" value="数据导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"/>	
							</th>
						</tr>
						<tr>
							<th>申报编号：</th>
							<th>
							<input type="text" name="baoxiaodan.baoxiaoBarcode"  value="${baoxiaodan.baoxiaoBarcode}" />
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
						<th>部门</th>
						<th>
						<select style="width:150px;" name="baoxiaodan.dept" id="dept" onMouseOver="createDept('dept')" >
						<option value="${baoxiaodan.dept}">${baoxiaodan.dept}</option>
						<option value="">选择部门</option>
						</select>
						</th>
						<th>报销类别</th>
						<th>
						<select style="width:150px;" name="detail.baoxiaoStyle" id="baoxiaoStyle" onMouseOver="createDept('baoxiaoStyle','BaoXiaoDanAction!findBaoXiaoStyle?tag=baoxiaoStyle')" >
							<option value="">选择销售类别</option>
							<option value="${detail.baoxiaoStyle}" selected="selected">${detail.baoxiaoStyle}</option>
						</select>
						</th>
						<th>所属总成号</th>
						<th>
						<select style="width:150px;" name="detail.markId" id="markId" onMouseOver="createDept('markId','BaoXiaoDanAction!findBaoXiaoStyle?tag=markId')">
						<option value="">选择销售类别</option>
						<option value="${detail.markId}" selected="selected">${detail.markId}</option>
							
						</select>
						</th>
						
						</tr>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">报销类别</th>
						<th align="center">内容摘要</th>
						<th align="center">所属总成</th>
						<th align="center">金额</th>
						<th align="center">币种</th>
						<th align="center">
							收款单位(个人)
						</th>
						<th align="center">
							报销人
						</th>
						<th align="center">
							部门
						</th>						
						<th align="center">
							付款方式
						</th>
						<th align="center">
							报销日期
						</th>
						<th align="center">
							报销编号
						</th>	
					</tr>
					
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="deta">
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
								${deta.baoxiaoStyle}
							</td>
							<td>
								${deta.baoxiaoContent}
							</td>
							<td>
								${deta.markId}
							</td>
							<td>
								${deta.money}
							</td>
							<td>
								${deta.baoxiaoDan.currency}
							</td>
							<td>
								${deta.baoxiaoDan.shoukuanRen}
							</td>
							<td>
								${deta.baoxiaoDan.baoxiaoren}
							</td>
							<td>
								${deta.baoxiaoDan.dept}
							</td>
								<td>
								${deta.baoxiaoDan.payStyle}
							</td>
							<td>
								${deta.baoxiaoDan.baoxiaoDate}
							</td>
							<td>
								${deta.baoxiaoDan.baoxiaoBarcode}
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="12" align="right">			
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
			objForm.action="BaoXiaoDanAction!exportDetailEXCEL.action?tag=detail";
			objForm.submit();	
		}
		</script>
	</body>
</html>
