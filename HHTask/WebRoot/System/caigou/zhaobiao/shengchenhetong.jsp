<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.util.Util"%>
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
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
			
			
			
			<div id="printDiv" class="my_show" align="center">
			<table  class="table">
			<tr><td align="center" colspan="7"><font size="6px"><b>${companyInfo.name}</b></font></td></tr>
			<tr><td align="center" colspan="7">        <font size="5px">     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原材料采购合同</font></td></tr>
			<tr><td colspan="3">甲方：${companyInfo.name} </td><td colspan="3">签定地点：${companyInfo.name}</td></tr>
			<tr><td colspan="3">乙方：${zhtoubiao.tname}</td><td colspan="3">签定时间：${zhaobiao.fabushijian}</td></tr>
			
			
			<tr><td align="center" colspan="7">&nbsp;&nbsp;</td></tr>
			<tr><td align="center" colspan="7">&nbsp;&nbsp;</td></tr>
			<tr><td colspan="7">1、乙方按订货合同及采购定单向甲方提供以下材料：</td></tr>
			<tr><td colspan="7">
						<table border="1px" width="100%" class="table">
					<tr>
						<th>模版名称</th>
						<th>序号</th>
						<th>牌号</th>
						
						<th>规格</th>
						<th>需要</th>
						<th>产地</th>
						<th>级别</th>
					</tr>
						<tr>
							<th>${ZhaobiaoXi.zhmoban.name}</th>
							<th>${ZhaobiaoXi.zhmoban.xuhao}</th>
							<th>${ZhaobiaoXi.zhmoban.paihao}</th>
							
							<th>${ZhaobiaoXi.zhmoban.guige}</th>
							<th>${ZhaobiaoXi.zhmoban.xuyao}</th>
							<th>${ZhaobiaoXi.zhmoban.changdi}</th>
							<th>${ZhaobiaoXi.zhmoban.jibei}</th>
						</tr>
						<table></td></tr>
						
						<tr><td align="center" colspan="7">&nbsp;&nbsp;</td></tr>
						<tr><td colspan="7">
							<table   width="100%" border="1px"  class="table">
						<tr>
							<td>使用模版</td>
							<td>数量/单位</td>
							<td>预计金额</td>
							<td>规格要求</td>
							<td>物料介绍</td>
						</tr>
						<tr >
							<td>${zhaobiaoXi.zhmoban.name}</td>
							<td>${zhaobiaoXi.t2}/${zhaobiaoXi.t3}</td>
							<td>${zhaobiaoXi.t4}</td>
							<td>${zhaobiaoXi.t5}</td>
							<td>${zhaobiaoXi.t6}</td>
						</tr>	
						<table></td></tr>
						
						
							
			<tr><td colspan="7">2、质量要求、技术标准：按技术协议、质量协议、118WISH8.04-02原材料检验规程及GB/T700-88; Q/BQB303-2003标准执行。</td></tr>
			<tr><td colspan="7">3、交货地点：2014年4月23日送到上海市嘉定区安亭镇和静路1200号（${companyInfo.name}）</td></tr>
			<tr><td colspan="7">4、运输方式及费用负担：由乙方负责运输，费用乙方承担。</td></tr>
			<tr><td colspan="7">5、包装要求：由乙方负责包装，应保证牢固，符合产品特性，以确保产品质量。</td></tr>
			<tr><td colspan="7">6、合理损耗标准及计算方法：以实际来料净重计算确定（磅重）。</td></tr>
			<tr><td colspan="7">7、验收标准、方法和期限：按第二条验收，每批货物需附产品质量证明书。</td></tr>
			<tr><td colspan="7">8、结算方式及期限：货到收到发票30天付款。</td></tr>
			<tr><td colspan="7">9、甲方在生产过程中，发现因乙方原因（如材质或焊接问题等）造成零件的报废或返修，由此发生的各项费用由乙方承担。</td></tr>
			<tr><td colspan="7">10、违约责任：按《经济合同法》。</td></tr>　　
			<tr><td colspan="7">11、本合同一式两份，甲乙双方各执一份。</td></tr>　
			<tr><td align="center" colspan="7">&nbsp;&nbsp;</td></tr>
			<tr><td align="center" colspan="7">&nbsp;&nbsp;</td></tr>
			
			<tr><td colspan="3">
			      <table>
			      <tr  height="30px"><td colspan="3" align="center">甲方</td></tr>
			      <tr height="30px"><td>单位名称：</td><td>${companyInfo.name}</td></tr>
			      <tr height="30px"><td>单位地址：</td><td>上海安亭和静路1200号</td></tr>
			      <tr  height="30px"><td>法定代表人：</td><td>______________________</td></tr>
			      <tr height="30px"><td>委托代理人：</td><td>______________________</td></tr>
				    <tr height="30px"><td> 电    话：</td><td>______________________</td></tr>
				    <tr height="30px"><td>开户银行：</td><td>______________________</td></tr>
				    <tr height="30px"><td> 帐    号：</td><td>______________________</td></tr>
				    <tr height="30px"><td>邮政编码：201805
			    
			      </table>
				</td>
				<td colspan="4">
			      <table>
			      <tr height="30px"><td colspan="4" align="center">乙  方</td></tr>
			      <tr height="30px"><td>单位名称：${zhtoubiao.tname}</td></tr>
			      <tr height="30px"><td>单位地址：</td><td>______________________</td></tr>
			      <tr height="30px"><td>法定代表人：</td><td>______________________</td></tr>
			      <tr height="30px"><td>委托代理人：</td><td>______________________</td></tr>
			       <tr height="30px"><td> 电    话：</td><td>______________________</td></tr>
				   <tr height="30px"><td>开户银行：</td><td>______________________</td></tr>
				   <tr height="30px"><td> 帐    号：</td><td>______________________</td></tr>
				   <tr height="30px"><td>邮政编码：</td><td>201805</td></tr>
			      </table>
				</td>
			</tr>　
			</table>
				</div>
				<center>
				  <s:if test='zhtoubiao.tkong7=="同意"'>
					<input type="button" id="print" value="打印" class="input" onclick="pagePrint('printDiv')" />
			     </s:if>
			</center>
			
			
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	  
	</SCRIPT>

</html>
