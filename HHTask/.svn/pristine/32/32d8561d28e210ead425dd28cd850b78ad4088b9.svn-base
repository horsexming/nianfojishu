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
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
							<table class="table" style="width: 50%;">
					<tr>
						<th colspan="2">
							招标信息
						</th>
					</tr>
					<tr>
						<th align="right">
							招标题目:
						</th>
						<td>
							${zhaobiao.title}
						</td>
					</tr>

					<tr>
						<th align="right">
							招标负责人:
						</th>
						<td>
							${zhaobiao.fuze}
						</td>
					</tr>
					<tr>
						<th align="right">
							负责人电话:
						</th>
						<td>
							${zhaobiao.phone}
						</td>
					</tr>
					<tr>
						<th align="right">
							开始时间:
						</th>
						<td>
							${zhaobiao.moban}
						</td>
					</tr>
					<tr>
						<th align="right">
							结束时间:
						</th>
						<td>
							${zhaobiao.kongxian}
						</td>
					</tr>
					<tr>
						<th align="right">
							招商简介:
						</th>
						<td colspan="2" height="100px">
							${zhaobiao.loc}
						</td>
					</tr>
				</table>
					<br/><br/><br/>
	          <table  class="table">
	            <tr><td>序号</td><td>使用模版</td> <td>物料名称</td><td>数量</td><td>财务预算</td><td>规格要求</td></tr>
	             <s:iterator value="list" id="zhaobiaoXi"  status="pageIndex">
						 <tr><td>${pageIndex.index+1}</td>
						 <td> ${zhaobiaoXi.zhmoban.name}</td>
						  <td>${zhaobiaoXi.t6}</td>
						 <td>${zhaobiaoXi.t2} ${zhaobiaoXi.t3}</td>
						 <td>部门：${zhaobiaoXi.deptMonthBudget.budgetMonth}&nbsp;&nbsp;&nbsp;   
						            月份：${zhaobiaoXi.deptMonthBudget.userDept}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						             预算金额：${zhaobiaoXi.deptMonthBudget.name}( ${zhaobiaoXi.deptMonthBudget.accountMoney})</td>
						 <td>${zhaobiaoXi.t5}</td>
						</tr>
						</s:iterator>
	          </table>
	          </br></br></br>
	           	 <form action="zhaobiaoAction!addshenhe.action" method="post"  theme="simple">
			                         <table class="table">
			                         <tr><th colspan="2"><font size="6px">审批</font> <input type="hidden" name="zhaobiao.id" value="${zhaobiao.id}"  /></th></tr>
			                         <tr><td align="right"><input type="radio" name="zhaobiao.status" value="Y" checked="checked" />同意</td><td><input type="radio" name="zhaobiao.status" value="N" id="" />不同意</td></tr>
			                      	<tr><th colspan="2" align="left">审批意见：</th></tr>
			                       <tr><th colspan="2"> <textarea rows="5" cols="100" name="zhaobiao.t1"></textarea></th></tr>
			                       <tr><td align="right"><s:submit value="保存"/></td>
					                    <td><input type="button" name="Submit2" value="取消"  class="right-buttons" onclick="window.history.go(-1);"/></td></tr>
			                         </table>
			                        </form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
</html>
