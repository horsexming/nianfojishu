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
			<style type="text/css">
.dhlabel {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000;
	border-left: 1px solid #000;
	border-right: 1px solid #000;
	margin-left: 5px;
	margin-right: 5px;
	padding: 3px 5px;
	white-space: nowrap;
}

.mingxi {
	background-color: #FFFFCE;
}

td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
</style>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center" id="printDiv">
				<h2>
					采购申请单领料
				</h2>
				<br />
				<p align="right" style="color: red">☆淡黄色列请双击选择库存&nbsp;&nbsp;</p>
				<form id="submitForm" method="post">
				<table class="table">
					<tr height="50px" ondblclick="showmingxi('')" >
						<th>件号</th>
						<th>物料类别</th>
						<th>名称</th>
						<th>供料属性</th>
						<th>规格</th>
						<th>需求数量</th>
						<th>单位</th>
						<th>是否紧急</th>
						<th>版本号</th>
						<th>图号</th>
						<th>项目编号</th>
						<th>备注</th>
						<th>已领取数量</th>
						<th>领料数量</th>
					</tr>
					<s:if test="planTotal.details!=null">
						<s:iterator value="planTotal.details" id="detail" status="pageStatus">
							<s:if test="#detail.goodsList==null || #detail.goodsList.size()==0">
								<tr class="zong" id="zong_${detail.id}">
							</s:if>
							<s:elseif test="#detail.goodsList.size()>1">
								<tr bgcolor="#FFEC8B" ondblclick="showmingxi(${detail.id})"
									 class="zong" id="zong_${detail.id}">
							</s:elseif>
							<s:elseif test="#detail.pickingStatus!=null && #detail.pickingStatus=='已领完'">
								<tr class="zong" id="zong_${detail.id}">
							</s:elseif>
							<s:else>
								<tr ondblclick="showmingxi(${detail.id})" class="zong" id="zong_${detail.id}">
							</s:else>
								<td align="center">
									${detail.markId}
									<!-- <input type="hidden" value="${detail.markId}" class="markIds"> -->
								</td>
								<td align="center">
									${detail.wgType}
								</td>
								<td align="center">
									${detail.proName}
								</td>
								<td align="center">
									${detail.kgliao}
								</td>
								<td align="center">
									${detail.specification}
								</td>
								<td align="right">
									${detail.cgnumber}
									<input type="hidden" value="${detail.cgnumber-detail.pickingNumber}"
										 id="cgNumber_${detail.id}" >
								</td>
								<td align="center">
									${detail.unit}
								</td>
								<td align="center">
									${detail.isurgent}
								</td>
								<td align="center">
									${detail.banben}
								</td>
								<td align="center">
									${detail.tuhao}
								</td>
								<td align="center">
									${detail.proNumber}
								</td>
								<td align="center">
									${detail.remarks}
								</td>
								<td align="center">
									${detail.pickingNumber}
								</td>
								<td align="center">
									<s:if test="#detail.pickingStatus!=null && #detail.pickingStatus=='已领完'">
										已领完
									</s:if>
									<s:elseif test="#detail.goodsList==null || #detail.goodsList.size()==0">
										缺料
									</s:elseif>
									<s:else>
										<input type="text" value="${detail.lingquNum}" size="6" readonly="readonly" 
											id="totalNumber_${detail.id}">
									</s:else>
								</td>
							</tr>
							<s:if test="#detail.goodsList!=null && #detail.goodsList.size()>0">
								<tr class="mingxi" id="mingxi_${detail.id}" style="display: none;"><!--  -->
									<td colspan="25">
										<table class="table" style="margin-left: 20px;">
											<tr>
												<td>仓库</td>
												<td>仓区</td>
												<td>库位</td>
												<td>物料类别</td>
												<td>件号</td>
												<td>品名</td>
												<td>规格</td>
												<td>库存量</td>
												<td>单位</td>
												<td>供料属性</td>
												<td>供应商</td>
												<td>批次</td>
												<td>出库数量</td>
											</tr>
											<s:iterator value="#detail.goodsList" id="goods" >
												<tr>
													<td>${goods.goodsClass }</td>
													<td>${goods.goodHouseName }</td>
													<td>${goods.goodsPosition }</td>
													<td>${goods.wgType }</td>
													<td>${goods.goodsMarkId }</td>
													<td>${goods.goodsFullName }</td>
													<td>${goods.goodsFormat }</td>
													<td>${goods.goodsCurQuantity}
														<input type="hidden" value="${goods.goodsCurQuantity }"
															 id="goodsCount${goods.goodsId }">
													</td>
													<td>${goods.goodsUnit }</td>
													<td>${goods.kgliao }</td>
													<td>${goods.goodsSupplier }</td>
													<td>${goods.goodsLotId }</td>
													<td>
														<s:if test="#goods.xqCount==null">
															<input type="text" value="0" size="6" id="sellCount${goods.goodsId }"
																onkeyup="mustBeNumber('sellCount${goods.goodsId }')" 
																class="classCount${detail.id} allCount"
																onblur="checkoutNumber('${goods.goodsId }','${detail.id}')"
																name="sellList[${goods.listIndex}].sellCount">
														</s:if>
														<s:else>
															<input type="text" value="${goods.xqCount}" size="6" id="sellCount${goods.goodsId }"
																onkeyup="mustBeNumber('sellCount${goods.goodsId }')" 
																class="classCount${detail.id} allCount"
																onblur="checkoutNumber('${goods.goodsId}','${detail.id}')"
																name="sellList[${goods.listIndex}].sellCount">
														</s:else>
														
														<input type="hidden" name="list[${goods.listIndex}].goodsId" value="${goods.goodsId}">
														<input type="hidden" name="sellList[${goods.listIndex}].mopdId" value="${detail.id}">
													</td>
												</tr>
											</s:iterator>
										</table>
									</td>
								</tr>
								
							</s:if>
						</s:iterator>
				</table>
				<table class="table">
					<tr>
						<th width="15%" align="right">
							出库日期
						</th>
						<td  width="35%">
							<input class="Wdate" type="text" id="goodsChangeTime"
								name="sell.sellDate" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							<span style="color: red">*</span>
						</td>
						<th width="15%" align="right">
							出库类型
						</th>
						<td width="35%">
							<select name="sell.style" id="sellStyle">
								<option value="领料出库">
									领料出库
								</option>
								<option value="销售出库">
									销售出库
								</option>
								<option value="返修出库">
									返修出库
								</option>
								<option value="退料出库">
									退料出库
								</option>
								<option value="报废出库">
									报废出库
								</option>
								<option value="转仓出库">
									转仓出库
								</option>
								<option value="损耗出库">
									损耗出库
								</option>
								<option value="研发耗用">
									研发耗用
								</option>
								<option value="售后出库">
									售后出库
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<th  align="right">
							负责人
						</th>
						<td>
							<input id="sellCompanyPeople" type="text"
								name="sell.sellCompanyPeople" />
						</td>
						<th  align="right">
							领物品人
						</th>
						<td>
							<input type="text" name="sell.sellCharger" />
						</td>
					</tr>
					<tr>
						<th  align="right">
							备注：
						</th>
						<td colspan="9">
							<textarea rows="2"  cols="100" name="sell.sellGoodsMore" >${planTotal.remark }</textarea>
						</td>
					</tr>
					<tr>
						<th colspan="8">
							<input type="button" value="出库" class="input" id="submitBtn" onclick="checkForm()"/>
							&nbsp;
							<input type="reset" value="重置" class="input" />
						</th>
					</tr>
				</table>
				</form>
				
				<form id="printForm" method="post" action="${pageContext.request.contextPath}/sellAction!printStorage.action">
					
				</form>
			</div>
			<br />
			</s:if>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		var count =0;
		
		//设置默认出库时间
		$(function() {
			var d = new Date();
		 	var day = d.getDate();        //获取当前日(1-31)
		    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
		    var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1)+'-'+day;
		    $("#goodsChangeTime").val(s);
		});
		function showmingxi(id){
			if(count == 0){
				if(id==''){
					$(".mingxi").show();
					$(".zong").attr("style","font-weight: bold;");
				}else{
					$("#mingxi_"+id).show();
					$("#zong_"+id).attr("style","font-weight: bold;");
				}
				count =1;
			}else if(count == 1){
				if(id==''){
					$(".mingxi").hide();
					$(".zong").removeAttr("style");
				}else{
					$("#mingxi_"+id).hide();
					$("#zong_"+id).removeAttr("style");
				}
				count =0;
			}
		}
		
		//校验数量
		function checkoutNumber(goodsId,detailId){
			var sellCountVal = $("#sellCount"+goodsId).val();
			var goodsCountVal = $("#goodsCount"+goodsId).val();
			if(sellCountVal==null || sellCountVal==''){
				sellCountVal = 0;
			}
			var sellCount = parseFloat(sellCountVal);
			var goodsCount = parseFloat(goodsCountVal);
			if(sellCount<0){//校验库存数量
				
				alert("出库数量不能小于0");
				$("#sellCount"+goodsId).val(0);
				$("#sellCount"+goodsId).focus();
				return false;
			}
			
			if(sellCount>goodsCount){//校验库存数量
				
				alert("出库数量不能大于库存数量");
				$("#sellCount"+goodsId).val(0);
				$("#sellCount"+goodsId).focus();
				return false;
			}else{
				var cgNumber =parseFloat( $("#cgNumber_"+detailId).val());//需求数量
				var totalNumber = 0;//已选择总数量
				
				var classCount = $(".classCount"+detailId);
				for(var i=0;i<classCount.length;i++){
					totalNumber+=parseFloat(classCount[i].value);
				}
				if(totalNumber>cgNumber){
					alert("总数量超出需求数量");
					$("#sellCount"+goodsId).val(0);
					$("#sellCount"+goodsId).focus();
					return false;
				}
				$("#totalNumber_"+detailId).val(totalNumber);
			}
		}
		
		//提交
		function checkForm(){
			var allCount = $(".allCount");
			if(allCount==null || allCount.length==0){
				alert("没有可用库存");
				return false;
			}
			
			if ($("#goodsChangeTime").val() == "") {
				alert("请填写出库时间!");
				$("#goodsChangeTime").focus();
				return false;
			}
			if ( $("#sellCompanyPeople").val() == "") {
				alert("请填写负责人!");
				return false;
			}
			
		    var form = new FormData(document.getElementById("submitForm"));
	      	$.ajax({
                url:"${pageContext.request.contextPath}/goodsAction!sellByManualOrder.action",
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                async : false, 
                success:function(data){
                    if(data!=null && data!=""){
                    	try{
                    	  //在这里运行代码
	                    	var obj = eval('('+data+')');
	                    	if(obj.success){
		                    	alert(obj.message);
		                    	var printData = obj.data;
		                    	var printData = printData.substring(1,printData.length);
		                    	var ids = printData.split(',');
		                    	for(var i=0;i<ids.length;i++){
		                    		$("#printForm").append("<input type='hidden' value='"+ids[i]+"' name='selected'>");
		                    	}
		                    	$("#printForm").submit();
	                    	}else{
	                    		alert(obj.message);
	                    	}
                    	}catch(err){
                    	  alert("数据异常");
                    	}
                    	
                    }
                },error:function(){
                	alert("系统异常！");
                }
	      	});
		}
		</SCRIPT>
	</body>
</html>
