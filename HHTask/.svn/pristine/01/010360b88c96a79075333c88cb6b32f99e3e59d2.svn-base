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
		<div id="gongneng">

			<div align="center">
				<h2>
					外委工作单流程跟踪管理
				</h2>

				<form action="OSWorkAction!getOSWorkByBarcode.action" method="post">
					<span style="font-size: 16px; color: red; font-weight: bold;">${message
						}</span>
					<br />
					<s:if test="%{'out'==tag}">
						<input id="outSear" type="radio" name="tag" value="out"
							checked="checked" onclick="getFoucus()" />
					</s:if>
					<s:else>
						<s:if test="%{null==tag}">
							<input id="outSear" type="radio" name="tag" value="out"
								checked="checked" onclick="getFoucus()" />
						</s:if>
						<s:else>
							<input id="outSear" type="radio" name="tag" value="out"
								onclick="getFoucus()" />
						</s:else>
					</s:else>
					<label for="outSear">
						<b style="font-size: 22px;">出厂扫描</b>
					</label>
					<br />
					<br>
					<s:if test="%{'in'==tag}">
						<input id="inSear" type="radio" name="tag" value="in"
							checked="checked" onclick="getFoucus()">
					</s:if>
					<s:else>
						<input id="inSear" type="radio" name="tag" value="in"
							onclick="getFoucus()">
					</s:else>
					<label for="inSear">
						<b style="font-size: 22px;">入厂扫描</b>
					</label>
					<br>
					<br>
					<s:if test="%{'rece'==tag}">
						<input id="receSear" type="radio" name="tag" value="rece"
							checked="checked" onclick="getFoucus()">
					</s:if>
					<s:else>
						<input id="receSear" type="radio" name="tag" value="rece"
							onclick="getFoucus()">
					</s:else>
					<label for="receSear">
						<b style="font-size: 22px;">班组接收扫描</b>
					</label>
					<br>
					<br />
					<b>请扫描外委工作单条码：</b>
					<input type="text" id="barcode" name="barcode" size="30"
						style="height: 40px;" value="${barcode}">
					<input type="submit" value="提交" style="width: 60px; height: 40px;">
					<br />

				</form>
				<s:if test="%{'rece'==tag}">
					<div style="margin-top: 20px;">
						<form action="OSWorkAction!updateReceiveOSW.action" method="post">
							<input type="hidden" name="osWork.id" value="${osWork.id}">
							<input type="hidden" name="osWork.outSourceCount"
								value="${osWork.outSourceCount}">
							<table class="table" style="width: 70%;">
								<tr>
									<th>
										可接收数量：
										<input type="text" name="osWork.receiveCount"
											value="${osWork.outSourceCount-osWork.receiveCount-osWork.breakCount}" />
									</th>
									<th>
										报废数量:
										<input type="text" name="osWork.breakCount" value="0" />
									</th>
									<th>
										已接收数量:${osWork.receiveCount} 已报废数量:${osWork.breakCount}
									</th>
								</tr>
								<tr>
									<th>
										零件号：${osWork.markID}
									</th>
									<th>
										批次号：${osWork.lotId}
									</th>
									<th>
										外委数量：${osWork.outSourceCount}
									</th>
								</tr>

								<!-- 显示外委的工序 -->
								<tr>
									<th>
										序号
									</th>
									<th>
										工序号
									</th>
									<th>
										工序名称
									</th>
								</tr>
								<!-- 遍历 -->
								<s:iterator value="list" status="se" id="pro">
									<tr>
										<td align="center">
											<s:property value="#se.index+1" />
										</td>
										<td align="center">
											${pro.processNO}
										</td>
										<td align="center">
											${pro.processName}
										</td>
									</tr>
								</s:iterator>
							</table>
							<input type="submit" value="接收"
								style="width: 50px; height: 40px;">
							&nbsp;
							<input type="reset" value="取消" style="width: 40px; height: 40px;">
						</form>
					</div>
				</s:if>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		$(function(){
			//$("#barcode").select();
			getFoucus();
		})
		function getFoucus(){
			$("#barcode").select();
		}
		onload=function(){
			var me="${message }";			
			if(me.length>0){
				alert(me);
			}
			
			}
		</SCRIPT>
	</body>
</html>
