<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sureDelive.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/util/sonHead.jsp"%>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%@include file="/util/sonTop.jsp"%>
    <div>
    <p>已匹配行</p>
   	<input type="button" value="修改PO" />
    </div>
    
    <div>
    <table border="1" cellspacing="0"  rules="all" bordercolor="#DEDEDE">
    
     <tr style="background-color: #E8E8E8;height:30px; ">
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 40px;" value="编号"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="客户"   readonly="readonly"/></td>
      <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="PO号"   readonly="readonly"/></td>
      
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料编码"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料版本"   readonly="readonly"/></td>
      <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="需求数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="可发货数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="本次发货数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="PO类型"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="订单行"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="发货行"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="下单日期"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料描述"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料型号"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="贸易术语"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="任务信息"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"> <input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="走账任务令"   readonly="readonly"/></td>
   <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="PO备注"   readonly="readonly"/></td>
   </tr>
    </table>
    
    </div>
    
  </body>
</html>
