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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
	</head>
	<body>
		<div>
			<form method="post" id="forms">
				<div class="row">
					<div class="col-xs-12 text-center">
						<table class="table" >
							<tr>
								<th>订单编号</th>
								<th>供应商</th>
								<th>采购月份</th>
								<th>状态</th>
								<th>添加日期</th>
								<th>联系人</th>
							</tr>
							<tr>
								<td>${waigouOrder.planNumber}</td>
								<td>${waigouOrder.gysName}</td>
								<td>${waigouOrder.caigouMonth}</td>
								<td>${waigouOrder.status}</td>
								<td>${waigouOrder.addTime}</td>
								<td>${waigouOrder.addUserName}<br>(${waigouOrder.addUserCode})</td>
							</tr>
						</table>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									序号
								</th>
								<th>
									物料类别
								</th>
								<th align="center">
									件号
								</th>
								<th align="center">
									零件名称
								</th>
								<th align="center">
									规格
								</th>
								<th align="center">
									版本
								</th>
								<th align="center">
									版次
								</th>
								<th align="center">
									供货属性
								</th>
								<th align="center">
									图号
								</th>
								<th align="center" id="th_danwei">
									单位
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									添加时间
								</th>
								<th align="center">
									备注
								</th>
							</tr>
							<s:iterator value="wwPlanList" id="pageWgww2" status="pageStatus2">
								<s:if test="#pageStatus2.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 50px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus2.index+1" />
								</td>
								<td>
									${pageWgww2.wgType}
								</td>
								<td align="left">
									${pageWgww2.markId}
								</td>
								<td align="left">
									${pageWgww2.proName}
								</td>
								<td align="left">
									${pageWgww2.specification}
								</td>
								<td>
									${pageWgww2.banben}
								</td>
								<td>
									${pageWgww2.banci}
								</td>
								<td>
									${pageWgww2.kgliao}
								</td>
								<td>
									${pageWgww2.tuhao}
								</td>
								<td>
									${pageWgww2.unit}
								</td>
								<td align="right">
									${pageWgww2.number}
								</td>
								<td>
									${pageWgww2.addTime}
								</td>
								<td>
									<input type="hidden" value="${pageWgww2.id}" name="waigouOrder.wwpList[${pageStatus2.index}].id">
									<textarea rows="3" cols="25" name="waigouOrder.wwpList[${pageStatus2.index}].remark">${pageWgww2.remark}</textarea>
								</td>
								</tr>
							</s:iterator>
						</table>
<%-- 							<input type="hidden" value="${waigouOrder.id}" id="wgOrderId" name="waigouOrder.id"> --%>
<%-- 							<textarea rows="10" cols="50" name="waigouOrder.remarkMore" id="remarkMore">${waigouOrder.remarkMore}</textarea> --%>
						<input type="hidden" value="${waigouOrder.id}" name="waigouOrder.id" >
						<input type="button" value="保存" class="input" onclick="toSubmit(this)">
						<input type="button" value="取消" class="input" onclick="cancelAddRemark()">
					</div>
				</div>
			</form>
		</div>
	<script type="text/javascript">
		function toSubmit(obj){
			var wgOrderId = $("#wgOrderId").val();
			$jq.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/WaigouwaiweiPlanAction!addwaigouOrderRemark.action",
				data:$jq("#forms").serialize(),
				dataType:"json",
				success:function(data){
					if(data!=null&& data=="true"){
						alert("添加成功");
						obj.disabled = "disabled";
						window.parent.chageDiv('none');
						//window.parent.document.getElementById("remark"+wgOrderId).title=$("#remarkMore").val();
					}else{
						alert("添加失败，请联系管理员");
					}
				},error: function(XMLHttpRequest, textStatus, errorThrown) {
	                alert("系统异常\n状态："+XMLHttpRequest.status+",状态值："+XMLHttpRequest.readyState+",异常信息:"+textStatus);
				}
			});
		}
		function cancelAddRemark(){
			window.parent.chageDiv('none');
		}
	</script>
	</body>
</html>
