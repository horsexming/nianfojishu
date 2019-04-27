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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>${cm.clientcompanyname}</h2>
			<div align="right"><a href="javascript:;" onclick="back('${cm.id}')" >返回</a></div>	
				<form action="PriceAction!showKHPrice.action" method="post" >
				<table class="table">
					<tr>
						<th>件号</th>
						<td> 
							<input type="text" name="price.partNumber"/>
						</td>
						<th>名称</th>
						<td> 
							<input type="text" name="price.name"/>
						</td>
						<td  align="center">
							<input type="hidden" name="successMessage" value="all"/>
							<input type="hidden" name="id" value="${id}"/>
							<input type="hidden" name="statue" value="KHPrice"/>
							<input type="submit" value="查询" style="width:75px; height: 35px;"/>
						</td>
					</tr>
				</table>
				</form>
			<form action="PriceAction!updatekehuId.action" method="post" onsubmit="fuzhi()">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							<input type="checkbox" name="chexkboxs" onclick="checkAll(this)">全选
						</th>
						<th>序号</th>
						<th>件号</th>
						<th>名称</th>
						<th>型别</th>
						<th>规格</th>
						<th>产品类别</th>
						<th>含税价格</th>
						<th>不含税价格</th>
						<th>税率</th>
						<th>生产类型</th>
						<th>合同编号</th>
						<th>负责人</th>
						<th>价格有效期开始日期</th>
						<th>价格有效期结束日期</th>
						<th>录入人</th>
						<th>客户</th>
					</tr>
					<s:iterator value="priceList" id="list" status="pageStatus">
						<s:if test="#pageStatus.first">
								<tr bgcolor="green">
									<th colspan="17" align="center">
										<font color="#ffffff">未绑定产品信息<br /> </font>
									</th>
								</tr>
							</s:if>
						<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<input type="checkbox" name="chexkboxs" value="${list.id}" >
					</td>
					<td>
						<s:property value="#pageStatus.index+1" />
					</td>
					<td>
						${list.partNumber}
					</td>
					<td>
						${list.name}
					</td>
					<td>
						${list.type}
					</td>
					<td>
						${list.specification}
					</td>
					<td>
						${list.productCategory}
					</td>
					<td>
						${list.hsPrice}
					</td>
					<td>
						${list.bhsPrice}
					</td>
					<td>
						<s:if test="#list.taxprice!=null && #list.taxprice>=0">
									${list.taxprice}%
						</s:if>
					</td>
					<td>
						${list.produceType}
					</td>
					<td>
						${list.contractNumber}
					</td>
					<td>
						${list.chargePerson}
					</td>
					<td>
						${list.pricePeriodStart}
					</td>
					<td>
						${list.pricePeriodEnd}
					</td>
					<td>
						${list.inputPeople}
					</td>
					<td>
						<s:iterator id="cu" value="cmList">
							<s:if test="#cu.id==#list.kehuId">
								${cu.clientcompanyname}
							</s:if>
						</s:iterator>
					</td>
					</s:iterator>
				<tr>
						<s:if test="errorMessage==null">
							<td colspan="17" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="18=7" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
					<tr>
						<td colspan="17">
							<input type="submit" value="绑定客户" style=" width: 75px;height: 35px;" id="sub" />
						</td>
					</tr>
				</table>
				<input type="hidden" value="${id}" name="id"/>
				<div id="fuzhi">
				
				</div>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function checkAll(obj){
	var chexkboxs = document.getElementsByName("chexkboxs"); 
	if(chexkboxs!=null && chexkboxs.length>0){
		if(obj!=null && obj.checked==true){
			for(var i=0;i<chexkboxs.length;i++){
				chexkboxs[i].checked=true;
			}
		}else{
			for(var i=0;i<chexkboxs.length;i++){
				chexkboxs[i].checked=false;
			}
		}
	}
	
}

function fuzhi(){
	var chexkboxs = document.getElementsByName("chexkboxs"); 
	if(chexkboxs!=null && chexkboxs.length>0){
		for(var i=0;i<chexkboxs.length;i++){
				if(chexkboxs[i].checked==true && chexkboxs[i].value!=null && chexkboxs[i].value>0){
					$("#fuzhi").append('<input type="hidden" value='+chexkboxs[i].value+' name="idArray"/>');
				}
			}
	}
	
	document.getElementById("sub").disabled="disabled";
}
function back(id){
	window.location.href = "PriceAction!findPriceBykehuId.action?id="+id+"&statue=KHPrice"
}
</SCRIPT>
	</body>
</html>
