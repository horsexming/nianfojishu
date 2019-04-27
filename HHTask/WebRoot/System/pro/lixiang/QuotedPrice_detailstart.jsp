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
		<SCRIPT type="text/javascript">
			$(function (){
				var procardStyle="${quotedPrice.procardStyle}"
				if(procardStyle=="总成"||procardStyle=="组合"){
					$("#showZong").show();
				}else if(procardStyle=="自制"){
					$("#showZi").show();
				}else if(procardStyle=="外购"){
					$("#showWai").show();
				}
			});
		</SCRIPT>
	</head>
	<body>
		<center>
			<div id="updateProcardT" style="display: none">
				<form method="post"
					style="margin: 0px; padding: 0px;">
					<input type="hidden" name="id" value="${quotedPrice.id}" />
					<input id="gxingbie" type="hidden" value="${quotedPrice.xingbie}"/>
				</form>
			</div>
			<div id="showZong" style="display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 规范
				</div>
				<div style="font-weight: bolder;">
					名称:${quotedPrice.proName} &nbsp;&nbsp;件号:${quotedPrice.markId}
					&nbsp;&nbsp; 卡片类型:${quotedPrice.procardStyle} &nbsp;&nbsp;
					产品类型:${quotedPrice.productStyle} &nbsp;&nbsp;
					型别:${quotedPrice.xingbie}&nbsp;&nbsp;
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th>
							数量(权值)
						</th>
						<th>
							1 : ${quotedPrice.corrCount==null?0:quotedPrice.corrCount}
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr align="center">
						<td>
							零组件
						</td>
						<td>
							名称
						</td>
						<td>
							数量
						</td>
						<td>
							卡片类型
						</td>
						<th>
							&nbsp;
						</th>

					</tr>
					<s:iterator value="quotedPriceList" id="pageQuotedPrice">
						<tr align="center">
							<th>
								${pageQuotedPrice.markId}
							</th>
							<th>
								${pageQuotedPrice.proName}
							</th>
							<th>
								${pageQuotedPrice.filnalCount}
							</th>
							<th>
								${pageQuotedPrice.procardStyle}
							</th>
							<th>
								&nbsp;
							</th>
						</tr>
					</s:iterator>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
				</table>
			</div>
			<div id="showZi" style="display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺规范
				</div>
				<div style="font-weight: bolder;">
					名称:${quotedPrice.proName} &nbsp;&nbsp;件号:${quotedPrice.markId}
					&nbsp;&nbsp; 卡片类型:${quotedPrice.procardStyle} &nbsp;&nbsp;
					产品类型:${quotedPrice.productStyle} &nbsp;&nbsp;
					型别:${quotedPrice.xingbie}&nbsp;&nbsp;
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="3" width="40%">
						</th>
						<th colspan="2" width="50%">
							数量(权值) 1 : ${quotedPrice.corrCount==null?0:quotedPrice.corrCount}
						</th>
					</tr>
					<tr>
						<td rowspan="5" style="width: 5px;" align="center">
							原
							<br />
							<br />
							材
							<br />
							<br />
							料
						</td>
						<td width="15%">
							牌号
						</td>
						<td width="15%">
							${quotedPrice.trademark}
						</td>
						<td rowspan="5" align="center" width="10%">
							备
							<br />
							<br />
							<br />
							注
						</td>
						<td rowspan="5">
							${quotedPrice.remark}
						</td>
					</tr>
					<tr>
						<td>
							规格
						</td>
						<td>
							${quotedPrice.specification}
						</td>
					</tr>
					<tr>
						<td>
							数量(权值)
						</td>
						<td>
							${quotedPrice.quanzi1} : ${quotedPrice.quanzi2}
						</td>
					</tr>
					<tr>
						<td>
							是否外购
						</td>
						<td>
							${quotedPrice.yucailiaostatus}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
			</div>
			<div id="showWai" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 规范
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th align="right" style="width: 25%;">
							件号:
						</th>
						<td>
							${quotedPrice.markId}
						</td>
					</tr>
					<tr>
						<th align="right">
							名称:
						</th>
						<td>
							${quotedPrice.proName}
						</td>
					</tr>
					<tr>
						<th align="right">
							型别:
						</th>
						<td>
							${quotedPrice.xingbie}
						</td>
					</tr>
					<tr>
						<th align="right">
							卡片类型:
						</th>
						<td>
							${quotedPrice.procardStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							单位:
						</th>
						<td>
							${quotedPrice.unit}
						</td>
					</tr>
					<tr>
						<th align="right">
							权值:
						</th>
						<td>
							${quotedPrice.quanzi1} : ${quotedPrice.quanzi2}
						</td>
					</tr>
					<tr>
						<th align="right">
							产品类型:
						</th>
						<td>
							${quotedPrice.productStyle}
						</td>
					</tr>
				</table>
			</div>
			<div>
				<table class="table" style="width: 100%">
					<tr align="center">
						<th>
							工序号
						</th>
						<th>
							名称
						</th>
						<th>
							生产类型
						</th>
						<th>
							设备
						</th>
						<th>
							工装
						</th>
					</tr>
					<s:iterator value="list" id="pageProcessTem" status="index">
						<s:if test="#pageProcessTem.gongzhuangId==null&&((#pageProcessTem.gongzhuang!=null&&#pageProcessTem.gongzhuang!='')||(pageProcessTem.gongzhuangNumber!=null&&pageProcessTem.gongzhuangNumber!=''))">
					<tr align="center">
							<th rowspan="2">
								${pageProcessTem.processNO}
							</th>
							<th rowspan="2">
								${pageProcessTem.processName}
							</th>
							<th rowspan="2">
								${pageProcessTem.productStyle}
							</th>
							<th rowspan="2">
								${pageProcessTem.shebeiName} ${pageProcessTem.shebeiNo}
							</th>
							<th id="oldgz<s:property value='#index.index'/>">
							 ${pageProcessTem.gongzhuangNumber} ${pageProcessTem.gongzhuang}
							</th>
							
						</tr>
						<tr align="center">
						<th>
								<div id="showAll<s:property value="#index.index"/>"
								style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
							</div>
							<input type="text" id="shortname<s:property value='#index.index'/>" onkeyup="getAllNames('<s:property value="#index.index"/>')"
								style="height: 20px; width: 280px" onFocus="init('shortname<s:property value="#index.index"/>','showAll<s:property value="#index.index"/>',<s:property value='#index.index'/>)"
								onBlur="hidediv('showAll<s:property value="#index.index"/>')" name="gz" />
								<input type="button" value="修改" 
								onclick="motifygz(<s:property value='#pageProcessTem.id'/>,<s:property value='#index.index'/>)"/>
								<input id="gzId<s:property value="#index.index"/>" type="hidden">
							</th>
						</tr>
					</s:if>
					<s:else>
					<tr align="center">
							<th>
								${pageProcessTem.processNO}
							</th>
							<th>
								${pageProcessTem.processName}
							</th>
							<th>
								${pageProcessTem.productStyle}
							</th>
							<th>
								${pageProcessTem.shebeiName} ${pageProcessTem.shebeiNo}
							</th>
							<th>
								 ${pageProcessTem.oldgongzhuangNumber} ${pageProcessTem.oldgongzhuang}
							</th>
						</tr>
					</s:else>
					</s:iterator>
				</table>
				<input type="button" class="input" value="提交项目" onclick="submitproject()">
			</div>
		</center>
<SCRIPT type="text/javascript">
		
//录入新工装
function getAllNames(index) {
	count_seach=1;
	var gxingbie=$("#gxingbie").val();
	var shortname=$("#shortname"+index).val();
	var shortnames=shortname.split(" ");
	if(shortnames.length>0){
		shortname=shortnames[0];
	}else{
		return false;
	}
	$
			.ajax( {
				type : "POST",
				url : "GzstoreAction!getGzstoreAllName.action",
				dataType : "json",
				data : {
					'gzstore.number' : shortname,
					'gzstore.xingbie' : gxingbie
				},
				success : function(data) {
					$("#showAll"+index).empty();
					$(data)
							.each(
									function() {
										var no=$(this).attr('no').replace($("#shortname"+index).val(),"<font color='red'>"+$("#shortname"+index).val()+"</font>");
										var name=$(this).attr('name').replace($("#shortname"+index).val(),"<font color='red'>"+$("#shortname"+index).val()+"</font>");
										$("#showAll"+index)
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this,shortname"+index+")' onclick='selectdiv(this,"+index+")' align='left'>"
																+ no
																+ ","
																+ name
																+ "<span style='display: none;'>"+$(this).attr('no')+" "+$(this).attr('name')+","+$(this).attr('id')+"</span></div>");
									});
				}
			});
}
//选中工装
function selectdiv(obj,index){
	var shortname=document.getElementById("shortname"+index);
	var spaniner=$(obj).find("span").html();
	var spaniners=spaniner.split(",");
	shortname.value=spaniners[0];
	var gzId=document.getElementById("gzId"+index);
	gzId.value=spaniners[1];
	var showAll=document.getElementById("showAll"+index); 
	showAll.style.visibility = "hidden";
}
//修改工装
function motifygz(id,index){
	var gzId=document.getElementById("gzId"+index).value;
	var oldgz=document.getElementById("oldgz"+index);
	 
	var shortname=document.getElementById("shortname"+index);
	var spaniner=shortname.value;
	$.ajax({
	  type : "POST",
	  url : "GzstoreAction!updateqpinfroGz.action",
	  dataType : "json",
	  data : {
		process_id : id,
		'gzstore.id' : gzId
	  },
	  success : function(data){
		  alert(data.message);
		  if(data.success){
			  oldgz.innerHTML="";
			  oldgz.innerHTML=spaniner;
			 
		  }
	  }
	});
}
//提交项目,开始项目跟踪
function submitproject(){
	var rootId="${quotedPrice.rootId}";
	$.ajax({
		type : "POST",
	    url : "QuotedPrice_submitProject.action",
	    dataType : "json",
	    data : {
		 id:rootId
	    },
	   success : function(data){
		alert(data.message);
		if(data.success){
		 window.parent.location.href="QuotedPrice_trackQuotedPrice.action?id=${quotedPrice.rootId}";	
		}
	   }
	});
}
</SCRIPT>
	</body>
</html>
