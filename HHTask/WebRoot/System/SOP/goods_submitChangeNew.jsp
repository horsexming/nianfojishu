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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>库存以旧换新个人申请</h3>
				<br/>
			<form action="LendNectAction!getGoodsCanNectOrder.action" onsubmit="return validate()" method="post">
				<table class="table"  >
					<tr>
						<th>
							部门：
						</th>
						<td>
							
							<input type="text" name="nect.dept"
								value="${nect.dept}" />
						</td>
						<th>
							卡号：
						</th>
						<td>
							<input type="text" name="nect.cardNum"
								value="${nect.cardNum}" />
						</td>
						<th>
							姓名：
						</th>
						<td>
							<input type="text" name="nect.peopleName"
								value="${nect.peopleName}" />
						</td>
					</tr>
					
					<tr>
						<th>
							件号：
						</th>
						<td>
							<input type="text" name="nect.goodsMarkId"
								value="${nect.goodsMarkId}" />
						</td>
						<th>
							批次：
						</th>
						<td>
							<input type="text" name="nect.goodsLotId"
								value="${nect.goodsLotId}" />
						</td>
						<th>
							名称：
						</th>
						<td>
							<input type="text" name="nect.goodsFullName" value="${nect.goodsFullName }"/>
						</td>
					</tr>
					<tr>
						<th>申请以旧换新数量：
						</th>
						<td>
							<input type="text" name="canChangeNum"  value="${canChangeNum}" id="num"/>
							<span style="color:red;">最多${nect.canChangeNum}</span>
							<input type="hidden" name="maxNum" id="maxNum" value="${nect.canChangeNum}">
							
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<th colspan="8">
							<input type="submit" value="查找库存" class="input"  id="submitBtn"/>
							<input type="hidden" name="nect.id"	 value="${nect.id}"/>
						</th>
					</tr>
				</table>
			</form>
				
				
			
				
				
				
				
				
			<s:if test="listChange!=null&&!listChange.isEmpty()">	
				   <table class="table"  id="goodsTable">
				
				<s:if test="{listChange.size()>0}">
					<tr bgcolor="#c0dcf2" height="30px"
					style="border-collapse: separate;">
					<th align="center">
						序号
					</th>
					<th align="center" style="width: 57px;">
						库别
					</th>
					<th align="center">
						仓区
					</th>
					<th align="center">
						库位
					</th>
					<th align="center">
						件号
					</th>
					<th align="center">
						批次
					</th>
					<th align="center">
						版本
					</th>
					<th align="center">
						供料属性
					</th>
					<th align="center">
						物料类别
					</th>
					<th align="center">
						品名
					</th>
					<th align="center">
						规格
					</th>
					<th align="center">
						可领用数量
					</th>
					<th align="center">
						是否可换
					</th>
					<th align="center">
						单位
					</th>
					<th align="center">
						转换数量
					</th>
					<th align="center">
						转换单位
					</th>
					<th align="center">
						客户
					</th>
					<th align="center">
						供应商
					</th>
					<th align="center">
						入库类型
					</th>
					<th align="center">
						入库日期
					</th>
					<th align="center">
						状态
					</th>
					<th align="center">
						封存审批
					</th>
					<th align="center" width="30">
						操作</h1>
					</th>
					
				</tr>
				
					<s:iterator value="listChange" status="see" id="gs">
						<s:if test="#see.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						
						
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#see.index+1" />
						</td>
						<td align="left" style="color: gray;">
							${gs.goodsClass}
						</td>
						<td align="left" style="color: gray;width:100px;">
							${gs.goodHouseName}
						</td>
						<td align="left" style="color: gray;">
							${gs.goodsPosition}
						</td>
						<td align="left">
							${gs.goodsMarkId}
<%--							<s:if test="#gs.processNo!=null">(<font color="red">${gs.processNo}</font> )</s:if>--%>
<%--							<s:if test='#gs.ywmarkId!=null'>--%>
<%--								(<font color="green">${gs.ywmarkId}</font> )--%>
<%--							</s:if>--%>
						</td>
						<td align="right">
							${gs.goodsLotId}
						</td>
						<td>
							${gs.banBenNumber}
						</td>
						<td align="left">
							<s:if test="#gs.kgliao=='TK'">
										自购(TK)
										</s:if>
							<s:elseif test="#gs.kgliao=='TK AVL'">
										指定供应商(TK AVL)
										</s:elseif>
							<s:elseif test="#gs.kgliao=='CS'">
										客供(CS)
										</s:elseif>
							<s:elseif test="#gs.kgliao=='TK Price'">
										完全指定(TK Price)
										</s:elseif>
						</td>
						<td align="left">
							${gs.wgType}
						</td>
						<td align="left" style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${gs.goodsFullName}</font>
									<ul class="qs_ul">
										<li>
											${gs.goodsFullName}
										</li>
							</ul>
						</td>
						<td align="left" style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${gs.goodsFormat}</font>
									<ul class="qs_ul">
										<li>
											${gs.goodsFormat}
										</li>
							</ul>
						</td>
						<td align="right" >
							<fmt:formatNumber value="${gs.goodsCurQuantity}" pattern="#.0000"/>
<%--					<fmt:formatNumber value='${gs.goodsCurQuantity}' pattern='#.0000'/>		--%>
							<input type="hidden" name="goodsNum" id="goodsNum" value="${gs.goodsCurQuantity}">
						</td>
						<td>${gs.nectCanChangeStatus}</td>
						<td>
							${gs.goodsUnit}
						</td>
						<td align="right">
							${gs.goodsZhishu}
						</td>
						<td>
							${gs.goodsStoreZHUnit}
						</td>
						<td>
							${gs.goodsCustomer}
						</td>
						<td>
							${gs.goodsArtsCard}
						</td>
						<td>
							${gs.goodsStyle}
						</td>
						<td>
							${gs.goodsChangeTime}
						</td>
						<td>
							<s:if test="#gs.fcStatus=='封存'">
								<font color="red">封存</font>
							</s:if>
							<s:else>可用</s:else>
						</td>
						<td>
							<s:if test="#gs.fcApplyStatus!=null">
							${gs.fcApplyStatus}
						   </s:if>
						</td>
						<td>
							
<%--							<a href="LendNectAction!insertChangeNew.action?goodsId=${gs.goodsId}&nectId=${nect.id}&num=${nect.num}">以旧换新</a>--%>
							<a href="javascript:;" onclick="insertChangeNew(${gs.goodsId},${nect.id}) ">以旧换新</a>
							
						</td>
						
						</tr>
					</s:iterator>
				</s:if>
				
				
				<s:else>
					<tr>
						<td colspan="21" style="font-size: 15px; color: red;">
							对不起，没有查到相关的库存信息
						</td>
					</tr>
				</s:else>

			</table>
				
			</s:if>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function validate(){
			var num = document.getElementById("num").value;
			var numObj=document.getElementById("num");
			var maxNum=document.getElementById("maxNum").value;
			if (num == "") {
				alert("请输入数量!");
				$("#num").focus();
				$("#num").val("");
				return false;
			}
			numFlag=numyanzheng(numObj,null);
			if(!numFlag){
				return false;
			}
			
			if(num-maxNum>0){
				alert("可换数量不能大于领用量!");
				$("#num").focus();
				$("#num").val("");
				return false;
			}
			
			return true;
		}
		
		function insertChangeNew(goodsId,nectId){
			
			var flag1=validate();
			if(flag1){
				var num = document.getElementById("num").value.trim();
				var goodsNum=document.getElementById("goodsNum").value.trim();
				if(num-goodsNum>0){
					var flag=window.confirm("库存不足，只能更换"+goodsNum+",是否继续申请");
					if(flag){
						successInsert(goodsId,nectId,goodsNum);
					}
				}else{
					alert("申请以旧换新数量为"+num+",系统处理中");
					successInsert(goodsId,nectId,num);
				}
			}
			
		}
		function successInsert(goodsId,nectId,num){
			$.ajax({
					type : "POST",
					url : "LendNectAction!ajaxInsertChangeNew.action",
					data : {
						goodsId:goodsId,
						nectId:nectId,
						num:num
					},
					dataType : "json",
					async:false,
					success : function(data) {
						if(data["success"]){
							//alert("请办理以旧换新业务"+nectId);
							alert(data["message"]);
							window.location.href="LendNectAction!printChangeNew.action?nectId="+data["data"];
						}else{
							alert(data["message"]);
						}
					}
			});
		}

		</SCRIPT>
	</body>
</html>
