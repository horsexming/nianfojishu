<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<h2 style="font-size: x-large;">
					手动下单
				</h2>
				<form action="WaigouwaiweiPlanAction!sdaddwaigouOrder.action"
					method="post">
					<table class="table">
						<tr>
							<th colspan="10" style="font-size: x-large;">
								物料需求信息
							</th>
						</tr>
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th>
								类别
							</th>
							<th>
								件号
							</th>
							<th>
								图号
							</th>
							<th>
								规格
							</th>
							<th>
								版本
							</th>
							<th>
								供料属性
							</th>
							<th>
								零件名称
							</th>
							<th>
								采购数量
								<br />
								(
								<span style="color: red;">${sumNum}</span>)
							</th>
						</tr>
						<s:iterator value="mopList" id="mop" status="statussdf">
							<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#statussdf.index+1" />
								<input type="hidden" value="${mop.id}" name="processIds" />
							</td>
							<td>
								${mop.wgType}
							</td>
							<td>
								${mop.markId}
							</td>
							<td>
								${mop.tuhao}
							</td>
							<td>
								${mop.specification}
							</td>
							<td>
								${mop.banben}
							</td>
							<td>
								${mop.kgliao}
							</td>
							<td>
								${mop.proName}
							</td>
							<td>
								<input type="text"
									value="<fmt:formatNumber type="number" value="${mop.number-mop.outcgNumber}" pattern="0.0000" maxFractionDigits="4"/>"
									name="caigouNums" id="caigouNum"
									onchange="numyanzheng(this);fzcaigouNum(${mop.number-mop.outcgNumber},this)" />
							</td>
							</tr>
						</s:iterator>
					</table>
					<table class="table">
						<tr>
							<th colspan="10" style="font-size: x-large;">
								供应商信息
							</th>
						</tr>
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th></th>
							<th colspan="2">
								供应商
							</th>
							<th>
								采购数量
							</th>
							<th>
								编码
							</th>
							<th>
								邮箱
							</th>
							<th>
								联系人
							</th>
							<th colspan="2">
								地址
							</th>
						</tr>
						<s:if test="zhuserList!=null && zhuserList.size()>0">
							<s:iterator value="zhuserList" id="pagezhuser"
								status="statussdf0">
								<s:if test="#statussdf0.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf0.index+1" />
								</td>
								<td>
									<input type="radio" value="${pagezhuser.id}" name="id2" />
								</td>
								<td colspan="2" align="left">
									${pagezhuser.cmp}
								</td>
								<td>
									${sumNum}
									<%--									<input type="text" id="caigouNum_${pagezhuser.id}" readonly="readonly" --%>
									<%--									value="${sumNum}" onchange="numyanzheng(this);fzcaigouNum(this)" size="5"/>--%>
								</td>
								<td align="left">
									${pagezhuser.usercode}
								</td>
								<td align="left">
									${pagezhuser.yx}
								</td>
								<td align="left">
									${pagezhuser.cperson}
								</td>
								<td colspan="2" align="left">
									${pagezhuser.companydz}
								</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<th colspan="10" style="font-size: x-large;">
									未找到相应价格的供应商
								</th>
							</tr>
						</s:else>
					</table>
					<input type="hidden" value="yemian" name="pageStatus" />
					<input type="submit" value="确认采购" id="sub"
						onclick="todisabled(this)" class="input" />

				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
	$(function(){
		if('${errorMessage}' == 'true'){
			alert('下单成功!');
			parent.chageDiv('none');
			//parent.window.location.reload();
			$("#sub").attr("disabled","disabled");
		}else if('${errorMessage}'!=''){
			alert('${errorMessage}' );
		}
		
	})
	function fzcaigouNum(num,obj){
		var cgNum = obj.value;
		if(num!='' && num>0){
			cgNum = parseFloat(cgNum);	
			num = parseFloat(num);	
			if(cgNum>num){
				$("#obj").val(num);
				alert("下单数量不能大于未采购量")
			}
			
		}
	}
</SCRIPT>
	</body>
</html>
