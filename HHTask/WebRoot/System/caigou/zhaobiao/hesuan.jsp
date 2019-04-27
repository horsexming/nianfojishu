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
				</div>
			</div>
			
			<div align="center">
			
<table class="table" style="width: 50%;">
					<tr><th colspan="2">招标信息</th></tr>
					<tr><th align="right">招标题目:</th>
						<td>${zhaobiao.title}</td>
					</tr>
					<tr>
						<th align="right">招标负责人:</th>
						<td>${zhaobiao.fuze}</td>
					</tr>
					<tr>
						<th align="right">负责人电话:</th>
						<td>${zhaobiao.phone}</td>
					</tr>
					<tr>
						<th align="right">开始时间:</th>
						<td>${zhaobiao.moban}</td>
					</tr>
					<tr>
						<th align="right">结束时间:</th>
						<td>${zhaobiao.kongxian}</td>
					</tr>
					<tr>
						<th align="right">招商简介:</th>
						<td colspan="2" height="100px">${zhaobiao.loc}</td>
					</tr>
				</table>
				<!--               -->
				<br />
				<br />
				<table class="table" style="width: 100%">
					<tr bgcolor="#c0dcf2">
						<td>
							使用模版
						</td>
						<td>
							数量/单位
						</td>
						<td>
							财务预算
						</td>
						<td>
							规格要求
						</td>
						<td>
							物料名称
						</td>
					</tr>
						<tr align="center"
								>
									<td>
								${zhaobiaoXi.t6}
							
							</td>
							<td>
								${zhaobiaoXi.zhmoban.name}
							</td>
							<td>
								${zhaobiaoXi.t2}/${zhaobiaoXi.t3}
							</td>
							<td>
								部门：${zhaobiaoXi.deptMonthBudget.budgetMonth}&nbsp;&nbsp;&nbsp;   
							            月份：${zhaobiaoXi.deptMonthBudget.userDept}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							             预算金额：${zhaobiaoXi.deptMonthBudget.name}( ${zhaobiaoXi.deptMonthBudget.accountMoney})
							</td>
							<td>
								${zhaobiaoXi.t5}
							</td>
						
						</tr>
				</table>
				<br/><br/><br/>
				<div id="t1"></div>
				  <form action="zhaobiaoAction!hesuanjiage.action" method="post"  theme="simple">
				<table class="table">
					<input type="hidden" value="${zhaobiaoXi.id}" id="zhaobiaoXiid" name="zhaobiaoXi.id"/>
				<input type="hidden" value="${zhtoubiao.tid}" name="zhtoubiao.tid" />
				<input type="hidden" value="${zhtoubiao.tkong2}" name="zhtoubiao.tkong2" />
				<input type="hidden" value="${zhtoubiao.tkong6}" name="zhtoubiao.tkong6" />
				<input type="hidden" value="${zhtoubiao.tkong7}" name="zhtoubiao.tkong7" />
				<input type="hidden" value="${zhtoubiao.tkong8}" name="zhtoubiao.tkong8" />
					<tr><th colspan="4" align="center"><font size="6px" style="size: 18px">投标记录</font></th></tr>
				   <tr><th align="right">投标公司：</th>
				        <td><input type="text" value="${zhtoubiao.tname}" name="zhtoubiao.tname" readonly="readonly"/></td>
				     <th align="right">备注：</th>
				        <td><input type="text" value="${zhtoubiao.tshuliang}" name="zhtoubiao.tshuliang" readonly="readonly"/></td> </tr>
				   <tr><th align="right">订单需求到货日期：</th>
				        <td><input type="text" value="${zhtoubiao.ttime}" name="zhtoubiao.ttime" readonly="readonly"/></td>
				       <th align="right">单价：</th>
				        <td><input type="text" value="${zhtoubiao.tkong1}" name="zhtoubiao.tkong1" readonly="readonly"/></td> </tr>
				    <tr><th align="right">负责人：</th>
				        <td><input type="text" value="${zhtoubiao.tkong4}" name="zhtoubiao.tkong4" readonly="readonly"/></td>
				     <th align="right">联系方式</th>
				        <td><input type="text" value="${zhtoubiao.tkong5}" name="zhtoubiao.tkong5" readonly="readonly"/></td> </tr>
				    <tr><th align="right">订单需求数量：</th>
				        <td><input type="text" value="${zhtoubiao.xuqiushuliang}" name="zhtoubiao.xuqiushuliang" readonly="readonly"/></td> 
				     <th align="right">订单需求到货日期：</th>
				        <td><input type="text" value="${zhtoubiao.xuqiudanhuoriqi}" name="zhtoubiao.xuqiudanhuoriqi" readonly="readonly"/></td></tr>
				        	
					
					
						   <tr><th align="center" colspan="2">货到付款</th >        <th align="center" colspan="2">款到发货</th ></tr>
				        <tr><th align="right">首笔比例:</th>
					        <td><input type="text" value="${zhtoubiao.shoubihuo}" name="zhtoubiao.shoubihuo" readonly="readonly"/></td>
					        
					        
					        <th align="right">首笔比例:</th>
					        <td><input type="text" value="${zhtoubiao.shoubikuan}" name="zhtoubiao.shoubikuan" readonly="readonly"/></td>
				        </tr>
				        <tr><th align="right">首笔   到货后周期</th>
					        <td><input type="text" value="${zhtoubiao.zhouqihuo}" name="zhtoubiao.zhouqihuo" readonly="readonly"/></td>
					        
					        
					             <th align="right">款到后 到货周期</th>
				        <td><input type="text" value="${zhtoubiao.zhouqikuan}" name="zhtoubiao.zhouqikuan" readonly="readonly"/></td>
					        
					        </tr>
						      <tr><th align="right">第二笔比例:</th>
					        <td><input type="text" value="${zhtoubiao.erbihuo}" name="zhtoubiao.erbihuo" readonly="readonly"/></td>
					        
					   
					     </tr>
				        <tr><th align="right">第二笔  到货后周期</th>
				        <td><input type="text" value="${zhtoubiao.zhouqier}" name="zhtoubiao.zhouqier" readonly="readonly"/></td></tr>
						   <tr><th align="right">末笔比例:</th>
				        <td><input type="text" value="${zhtoubiao.mobihuo}" name="zhtoubiao.mobihuo" readonly="readonly"/></td></tr>
				        <tr><th align="right">末笔  到货周期</th>
				        <td><input type="text" value="${zhtoubiao.mobiuo}" name="zhtoubiao.mobiuo" readonly="readonly"/></td></tr>
					
					
					
					
					  <tr><th align="center" colspan="4">物流部门</th ></tr>
					  
				        <tr><th align="right">实际到货数量:</th>
				        <td><input type="text" name="zhtoubiao.shijidaohuo" /><font style="color: red;">*可填</font></td>
				        <th align="right">多余货物消耗周期</th>
				        <td><input type="text"  name="zhtoubiao.zhouqi" /><font style="color: red;">*可填</font></td></tr>
				        
					      <tr><th align="right">实际到货工用天数:</th>
				        <td><input type="text"  name="zhtoubiao.daohuoriqi" /><font style="color: red;">*可填</font></td>
				        <th align="right">质量换货周期</th>
				        <td><input type="text"  name="zhtoubiao.huanhuozhouqi" /><font style="color: red;">*可填</font></td></tr>
						   <tr><th align="right">加班费用(元）:</th>
				        <td><input type="text" name="zhtoubiao.jiabanfei" /><font style="color: red;">*可填</font></td></tr>
					 <tr><td colspan="2" align="right"><s:submit value="保存" cssClass="input"/></td>
					      <td colspan="2"align="left"><input type="button" name="Submit2" value="返回"  class="input" onclick="window.history.go(-1);"/></td></tr>
				</table>
</form>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	
	</body>
</html>
