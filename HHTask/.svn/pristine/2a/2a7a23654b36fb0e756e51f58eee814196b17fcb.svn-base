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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="sellAction!getCradInfor.action?tag=card" method="post">
					<table class="table" style="width:85%;">
						
						<tr>
							<th>
								请刷生产周转卡，或扫描补料单领料：</th>
								<th>
								<input type="text" name="runningWaterCard.cardNum" />
								<span style="color:red;font-weiht:bold;">${message}</span>
							</th>

						</tr>
				</form>
				<table class="table" style="width:85%;">
					<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						
						<th align="center">
							客户
						</th>
						<th align="center">
							车型
						</th>
						<th align="center">
							件号
						</th>
						
						<th align="center">
							跟踪批次						
						</th>
						<th align="center">
							持卡者					
						</th>
						<th align="center">
							跟踪状态						
						</th>
						
						<th align="center">
							操作				
						</th>
					</tr>
					<tr>
							<td colspan="8" style="font-size: 15px; color: red;text-align:center;">
								待领料的生产周转卡信息
							</td>
						</tr>
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="card">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							
							<td>
								${card.customer}
							</td>
							<td>
								${card.carStyle}
							</td>
							<td>
								${card.markId}
							</td>
								
							<td>
								${card.followCardId}
							</td>
							<td>
								${card.ownUsername}
							</td>
							<td>
								${card.cardStatus}
							</td>
							
							<td>
							<!--<a href="sellAction!getCradInfor.action?id=${id}&tag=id">领料</a>  -->
							
							</td>
							
							</tr>
						</s:iterator>
						<tr>
							<td colspan="8" align="right">			
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="8" style="font-size: 15px; color: red;text-align:center;">
								对不起，没有查到相关的流水卡信息
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		//提交验证
			onload=function(){
			var me="${message }";			
			if(me.length>0){
				alert(me);
			}
			}
		</script>
	</body>
</html>
