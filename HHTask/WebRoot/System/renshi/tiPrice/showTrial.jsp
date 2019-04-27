<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'interiorMentioningAwardPrice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="javascript/jquery-easyui-1.3.1/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="javascript/jquery-easyui-1.3.1/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="javascript/jquery-easyui-1.3.1/demo.css">
	<script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
	<script>
	$(function(){
		$('#tab').treegrid({
			url: 'productPriceAction!trial.action?id=${param.id}', 
			    singleSelect: true,   
                idField: 'id',  
                treeField: 'name',
                fitColumns:true,
                frozenColumns:[[
                	{field:'name',title:'名称',align:'left',rowspan:3,width:200}
                ]],
                columns:[[  
                	{field:'pieceNum',title:'件号',align:'right',rowspan:3,width:65},
        			{field:'totalBeat',title:'零件总节拍',align:'right',rowspan:3,width:64},
        			{field:'stationNum',title:'工位号',align:'right',rowspan:3,width:45},
        			{field:'equipmentNum',title:'设备号',align:'right',rowspan:3,width:40},
        			{field:'',title:'操作过程',align:'right',colspan:2,width:110},
        			{field:'',title:'准备过程',align:'right',colspan:2,width:110},
        			{field:'jobNum',title:'工号',align:'right',rowspan:3,width:60},
        			{field:'handlers',title:'操作者',align:'right',rowspan:3,editor:'text',width:60},
        			{field:'processUnitPrice',title:'工序单价',align:'left',rowspan:3,width:60},
        			{field:'sumMoney',title:'分配总额',align:'left',rowspan:3,width:60},
        			{field:'unitPrice',title:'单件额',align:'left',rowspan:3,width:60}
    			],[
                	{field:'oPLabourBeat',title:'人工节拍',align:'right',editor:{type:'numberbox',options:{precision:1}},width:40},
                	{field:'oPEquipmentBeat',title:'设备节拍',align:'right',editor:{type:'numberbox',options:{precision:1}},width:40},
                	{field:'pRLabourBeat',title:'人工节拍',align:'right',editor:{type:'numberbox',options:{precision:1}},width:40},
                	{field:'pRPrepareIndex',title:'准备次数',align:'right',editor:{type:'numberbox',options:{precision:1}},width:40}
    			]]
		});
			$('#pro').datagrid({
				url: 'productPriceAction!packagePro.action?id=${param.id}', 
                fitColumns:true,
                columns:[[  
                	{field:'name',title:'名称',align:'left'},
                	{field:'pieceNum',title:'件号',align:'right'},
                	{field:'dayOutput',title:'入库量',align:'right'},
                	{field:'productType',title:'型别',align:'right'},
                	{field:'carType',title:'车型',align:'right'},
        			{field:'oilPaymentPrice',title:'提奖单价',align:'right'}
        			]]
		});
	});
	</script>
  </head>
  
  <body>
  	<table id="pro" title="产品"></table>
  	<div style="margin:20px 0;"></div>  
	<table id="tab" title="Reports using TreeGrid">  
    </table>  
  </body>
</html>
