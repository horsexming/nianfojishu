<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'qinataoshuju.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/util/sonHead.jsp"%>
	<script type="text/javascript" src="System/YcB2B/jquery-1.11.3.min.js"></script>
	

	
	<script type="text/javascript" src="System/YcB2B/yuce.js"></script>
	<style type="text/css">
	table td{
	border:0.5px solid #D2DDF1;
	}
	
	</style>
  </head>
  
  <body style="font-family: "微软雅黑";">
  
  

  
  		<p class="size" style="display: none;">${sizeInteger}</p>
   	<table border="1" cellspacing="0"  rules="all" id="qinagtao">
   	<tr height="1%" id="evey">
   	<td align="center"  ><input type="text"   align="left" value="编号" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   		<td align="center"  ><input type="text"   align="left" value="供方物料编码" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   			<td align="center" ><input type="text"   align="left" value="华为物料编码" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   				<td align="center"  ><input type="text"   align="left" value="版本号" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   					<td align="center" ><input type="text"   align="left" value="采购模式" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   						<td align="center"  ><input type="text"   align="left" value="器件分类" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   							<td align="center" ><input type="text"   align="left" value="采购员" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   								<td align="center" ><input type="text"   align="left" value="发布日期" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   									<td align="center" ><input type="text"   align="left" value="在途订单数量" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   										<td align="center"   ><input type="text"   align="left" value="VMI实时库存" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   											<td align="center" ><input type="text"   align="left" value="VMI库存" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   												<td align="center" ><input type="text"   align="left" value="供应商库存" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   													<td align="center"  ><input type="text"   align="left" value="数据类型" style="background-color: #EBF1FF;border: none;"  readonly="readonly"  /></td>
   														<td align="center" class="today"></td>	
   															<td align="left" class="nexttoday"  ></td>
   												<td align="left" class="twonextday"  ></td>	
											<td align="left" class="threeday"  ></td>	
										<td align="left" class="forday" ></td>
									<td align="left" class="friveday" ></td>
								<td align="left" class="sixday" ></td>		
							<td align="left" class="sevenday" ></td>		
						<td align="left" class="eitday" ></td>	
					<td align="left" class="nineday" ></td>
			<td align="left" class="tenday" ></td>	
		<td align="left" class="elevenday" ></td>	
	<td align="left" >合计</td>																	
   	</tr>
   	
   		<s:if test="list==null">
   		<tr>
   		<td colspan="26" align="center"><input type="text" value="主上 没有数据可查询!" readonly="readonly" style="border: none;background-color:#F7F7F7;font-size: 20px;"></td>
   		</tr>
   		</s:if>
   		
	   	<s:else>
	   	
	   	</s:else>
  			<s:iterator id="liste" value="list" status="stauts">
				
   				
   				<s:if test="#stauts.index%3==0">
		   				<tr  style="background-color: #F1F2F3;">
		   				<!-- 编号 -->
		   				<td rowspan="4" class="id"><s:property value="(#stauts.index/3)+1" /></td>
		   				<!-- 供方物料编码 -->
		   				<s:if test="#liste.vendorCode ==null"><td rowspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:if>
						<s:else><td rowspan="4">${liste.vendorCode}</td></s:else>
						<!-- 华为物料编码 -->
						<s:if test="#liste.itemCode ==null"><td rowspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:if>
						<s:else><td rowspan="4">${liste.itemCode}</td></s:else>
						<!-- 版本号 -->
						<s:if test="#liste.itemRevision ==null"><td rowspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:if>
						<s:else><td rowspan="4">${liste.itemRevision }</td></s:else>
						<!-- 采购模式 -->
						<s:if test="#liste.businessMode ==null"><td  rowspan="4"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:if>
						<s:else><td rowspan="4">${liste.businessMode}</td></s:else>
						<!-- 器件分类 -->
						<s:if test="#liste.vendorName ==null"><td  rowspan="4"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:if>
						<s:else><td rowspan="4">${liste.vendorName }</td></s:else>
						<!-- 采购员 -->
						<s:if test="#liste.agentName ==null"><td  rowspan="4"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:if>
						<s:else><td rowspan="4">${liste.agentName }</td></s:else>
						</tr>
						
				</s:if>
				

				<s:else>
						<tr  id="xianshi">
				</s:else>
						<!-- 发布日期 -->
						<s:if test="#liste.publishDate !=null"><td align="center"  style="background-color: #F1F2F3;">${liste.publishDate}</td></s:if>
						<s:else><td align="center"  style="background-color: #F1F2F3;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:else>
						
						<!--在途订单数量  -->
						<s:if test="#liste.termsMode !=null"><td align="center"  style="background-color: #F1F2F3;">${liste.termsMode }</td></s:if>
						<s:else><td align="center"  style="background-color: #F1F2F3;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:else> 
					
						
						<!-- VMI实时库存 -->
						<td  style="background-color: #F1F2F3;"></td>
			
						<!--VMI库存 -->
						<s:if test="#liste.quantityRejected !=null"><td align="center"  style="background-color: #F1F2F3;">${liste.quantityRejected }</td></s:if>
						<s:else><td align="center"  style="background-color: #F1F2F3;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></s:else>
						
						<!--供应商库存 -->
						<td align="center"  class="gon"  style="background-color: #F1F2F3;">
						<s:if test="#liste.currencyCode !=null">${liste.currencyCode }</s:if>
						<s:else>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:else>
						</td>
						
						
						<!-- 数据类型 -->
						<s:if test="#liste.itemDescription !=null"><td class="leixing" align="center"  style="background-color: #F1F2F3;"><s:if test="#liste.itemDescription=='net_requirement'">本期需求</s:if><s:if test="#liste.itemDescription=='gap'">本期缺口</s:if><s:if test="#liste.itemDescription=='supplier_response'">本期供应</s:if></td></s:if>
						<s:else><td class="leixing" align="center"  style="background-color: #F1F2F3;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    </td></s:else>	
						
						<!-- #liste.currencyCode库存数量如果大于库存数量就是蓝色如果小于库存数量就是缺货红色等于数量就是不足 -->
						
						
						
						
						
						<!-- 第一次 -->
						<s:if test="#stauts.index%3==0">
						<td align="center"  style="background-color: #F1F2F3;" class="Q1" >				
						<s:if test="#liste.expireDate =='null'" ><input type="text"   align="left" value="0"" style=" background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"   align="left" value="${liste.expireDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>						
						</td>
						
						
						<td align="center"  style="background-color: #F1F2F3;" class="Q2">
						<s:if test="#liste.phrLastUpdateDate =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"   align="left" value="${liste.phrLastUpdateDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #F1F2F3;" class="Q3">
						<s:if test="#liste.startDate =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"   align="left" value="${liste.startDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
												
						 
						<td align="center"  style="background-color: #F1F2F3;" class="Q4">
						<s:if test="#liste.phrCreationDate =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"   align="left" value="${liste.phrCreationDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q5">
						<s:if test="#liste.lastUpdateDate =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"   align="left" value="${liste.lastUpdateDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						
						
								
						
						<td align="center"  style="background-color:#F1F2F3;"  class="Q6">
						<s:if test="#liste.rateDate  =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"   align="left" value="${liste.rateDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>	
						</td>
						
								
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q7">
						<s:if test="#liste.creationDate  =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"/></s:if>
						<s:else><input type="text"   align="left" value="${liste.creationDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q8">
						<s:if test="#liste.approvedDate  =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:if>
						<s:else><input type="text"   align="left" value="${liste.approvedDate }" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:else>
						</td>
						
								
						<td align="center"  style="background-color: #F1F2F3;"  class="Q9">
						<s:if test="#liste.recvVendorTelNum  =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly" /></s:if>
						<s:else><input type="text"   align="left" value="${liste.recvVendorTelNum }" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:else>	
						</td>
						
						
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q10">
						<s:if test="#liste.prhaInterfaceSourceCode  =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly" /></s:if>
						<s:else><input type="text"   align="left" value="${liste.prhaInterfaceSourceCode }" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:else>
						</td>
						
						
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q11">
						<s:if test="#liste.createdBy  =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly" /></s:if>
						<s:else><input type="text"   align="left" value="${liste.createdBy }" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q12">
						<s:if test="#liste.billToLocationId  =='null'"><input type="text"   align="left" value="0" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:if>
						<s:else><input type="text"   align="left" value="${liste.billToLocationId }" style="background-color: #F1F2F3;border: none;"  readonly="readonly" /></s:else>
						</td>
						</s:if>
						
						
						
						
						<!-- 循环到第二次 -->
						<s:if test="#stauts.index%3==1">
						
						<td align="center"  style="background-color: #FFFFFF;" class="Q1" >	
						<s:if test="#liste.expireDate =='null'" ><input type="text"   align="left" value="0"" style="background-color: #FFFFFF;border: none;" class="Q1hf" ></s:if>
						<s:else><input type="text"   class="Q1hf"  align="left" value="${liste.expireDate }" style="background-color: #FFFFFF;border: none;"></s:else>

					</td>
						
						<td align="center"  style="background-color: #FFFFFF;" class="Q2">
						<s:if test="#liste.phrLastUpdateDate =='null'"><input type="text"   class="Q2hf"  align="left" value="0" style="background-color: #FFFFFF;border: none;"></s:if>
						<s:else><input type="text"   class="Q2hf"  align="left" value="${liste.phrLastUpdateDate }" style="background-color: #FFFFFF;border: none;"></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #FFFFFF;" class="Q3">
						<s:if test="#liste.startDate =='null'"><input type="text"  class="Q3hf"   align="left" value="0" style="background-color: #FFFFFF;border: none;"></s:if>
						<s:else><input type="text"   class="Q3hf"  align="left" value="${liste.startDate }" style="background-color: #FFFFFF;border: none;"></s:else>
						</td>
						
												
						 
						<td align="center"  style="background-color: #FFFFFF;" class="Q4">
						<s:if test="#liste.phrCreationDate =='null'"><input type="text"   class="Q4hf"  align="left" value="0" style="background-color: #FFFFFF;border: none;"></s:if>
						<s:else><input type="text"   class="Q4hf"  align="left" value="${liste.phrCreationDate }" style="background-color: #FFFFFF;border: none;"></s:else>
						</td>
						
						
						
						<td align="center"  style="background-color: #FFFFFF;"  class="Q5">
						<s:if test="#liste.lastUpdateDate =='null'"><input type="text"   class="Q5hf"  align="left" value="0" style="background-color: #FFFFFF;border: none;"></s:if>
						<s:else><input type="text"    class="Q5hf" align="left" value="${liste.lastUpdateDate }" style="background-color: #FFFFFF;border: none;"></s:else>
						</td>
						
						
						
								
						
						<td align="center"  style="background-color:#FFFFFF;"  class="Q6">
						<s:if test="#liste.rateDate  =='null'"><input type="text"   class="Q6hf"  align="left" value="0" style="background-color: #FFFFFF;border: none;"></s:if>
						<s:else><input type="text"   class="Q6hf"  align="left" value="${liste.rateDate }" style="background-color: #FFFFFF;border: none;"></s:else>	
						</td>
						
								
						
						<td align="center"  style="background-color: #FFFFFF;"  class="Q7">
						<s:if test="#liste.creationDate  =='null'"><input type="text"   class="Q7hf"  align="left" value="0" style="background-color: #FFFFFF;border: none;" /></s:if>
						<s:else><input type="text"   class="Q7hf"  align="left" value="${liste.creationDate }" style="background-color: #FFFFFF;border: none;"></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #FFFFFF;"  class="Q8">
						<s:if test="#liste.approvedDate  =='null'"><input type="text"   class="Q8hf"  align="left" value="0" style="background-color: #FFFFFF;border: none;" /></s:if>
						<s:else><input type="text"    class="Q8hf" align="left" value="${liste.approvedDate }" style="background-color: #FFFFFF;border: none;" /></s:else>
						</td>
						
								
						<td align="center"  style="background-color: #FFFFFF;"  class="Q9">
						<s:if test="#liste.recvVendorTelNum  =='null'"><input type="text"    class="Q9hf" align="left" value="0" style="background-color: #FFFFFF;border: none;" /></s:if>
						<s:else><input type="text"   class="Q9hf"  align="left" value="${liste.recvVendorTelNum }" style="background-color: #FFFFFF;border: none;" /></s:else>	
						</td>
						
						
						
						
						<td align="center"  style="background-color: #FFFFFF;"  class="Q10">
						<s:if test="#liste.prhaInterfaceSourceCode  =='null'"><input type="text"   class="Q10hf"  align="left" value="0" style="background-color: #FFFFFF;border: none;" /></s:if>
						<s:else><input type="text"   class="Q10hf"  align="left" value="${liste.prhaInterfaceSourceCode }" style="background-color: #FFFFFF;border: none;" /></s:else>
						</td>
						
						
						
						
						<td align="center"  style="background-color: #FFFFFF;"  class="Q11">
						<s:if test="#liste.createdBy  =='null'"><input type="text"  class="Q11hf"   align="left" value="0" style="background-color: #FFFFFF;border: none;" /></s:if>
						<s:else><input type="text"   class="Q11hf"  align="left" value="${liste.createdBy }" style="background-color: #FFFFFF;border: none;" /></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #FFFFFF;"  class="Q12">
						<s:if test="#liste.billToLocationId  =='null'"><input type="text"   class="Q12hf"  align="left" value="0" style="background-color: #FFFFFF;border: none;" /></s:if>
						<s:else><input type="text"   class="Q12hf"  align="left" value="${liste.billToLocationId }" style="background-color: #FFFFFF;border: none;" /></s:else>
						</td>
						</s:if>
						
						
						
						<!-- 第三次 -->
						<s:if test="#stauts.index%3==2">
						<td align="center"  style="background-color: #F1F2F3;" class="Q1" >	
						<s:if test="#liste.expireDate =='null'" ><input type="text"  id="Q1threefor"  align="left" value="0"" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"  id="Q1threefor"  align="left"    value="${liste.expireDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						<td align="center"  style="background-color: #F1F2F3;" class="Q2">
						<s:if test="#liste.phrLastUpdateDate =='null'"><input type="text" id="Q2threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text" id="Q2threefor"  align="left" value="${liste.phrLastUpdateDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #F1F2F3;" class="Q3">
						<s:if test="#liste.startDate =='null'"><input type="text"  id="Q3threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text" id="Q3threefor"  align="left" value="${liste.startDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
												
						 
						<td align="center"  style="background-color: #F1F2F3;" class="Q4">
						<s:if test="#liste.phrCreationDate =='null'"><input type="text" id="Q4threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"  id="Q4threefor"  align="left" value="${liste.phrCreationDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q5">
						<s:if test="#liste.lastUpdateDate =='null'"><input type="text" id="Q5threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text" id="Q5threefor"  align="left" value="${liste.lastUpdateDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						
						
								
						
						<td align="center"  style="background-color:#F1F2F3;"  class="Q6">
						<s:if test="#liste.rateDate  =='null'"><input type="text"  id="Q6threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:if>
						<s:else><input type="text"  id="Q6threefor"  align="left" value="${liste.rateDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>	
						</td>
						
								
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q7">
						<s:if test="#liste.creationDate  =='null'"><input type="text"  id="Q7threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly"/></s:if>
						<s:else><input type="text"  id="Q7threefor"  align="left" value="${liste.creationDate }" style="background-color: #F1F2F3;border: none;"  readonly="readonly"></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q8">
						<s:if test="#liste.approvedDate  =='null'"><input type="text" id="Q8threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:if>
						<s:else><input type="text"  id="Q8threefor"  align="left" value="${liste.approvedDate }" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:else>
						</td>
						
								
						<td align="center"  style="background-color: #F1F2F3;"  class="Q9">
						<s:if test="#liste.recvVendorTelNum  =='null'"><input type="text"  id="Q9threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly" /></s:if>
						<s:else><input type="text"  id="Q9threefor"  align="left" value="${liste.recvVendorTelNum }" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:else>	
						</td>
						
						
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q10">
						<s:if test="#liste.prhaInterfaceSourceCode  =='null'"><input type="text" id="Q10threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly" /></s:if>
						<s:else><input type="text"   id="Q10threefor" align="left" value="${liste.prhaInterfaceSourceCode }" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:else>
						</td>
						
						
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q11">
						<s:if test="#liste.createdBy  =='null'"><input type="text"  id="Q11threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"  readonly="readonly" /></s:if>
						<s:else><input type="text"  id="Q11threefor"  align="left" value="${liste.createdBy }" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:else>
						</td>
						
						
						<td align="center"  style="background-color: #F1F2F3;"  class="Q12">
						<s:if test="#liste.billToLocationId  =='null'"><input type="text"  id="Q12threefor"  align="left" value="0" style="background-color: #F1F2F3;border: none;"   readonly="readonly"/></s:if>
						<s:else><input type="text"  id="Q12threefor"  align="left" value="${liste.billToLocationId }" style="background-color: #F1F2F3;border: none;"  readonly="readonly" /></s:else>
						</td>
						</s:if>
						
						

						
						
						<!-- 合计 -->
						<td align="center"  style="background-color: #F1F2F3;">${liste.number }</td>	
					</tr>
				

			
				
				
				
			
   	</s:iterator>
   	
  
   	
   	
					<tr>
						<td colspan="26" align="right">
							第
							<font color="red"><s:property value="cpage"   /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
   	</table>

  </body>
</html>
