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
			$(function(){
				$('#basicRightBtn').bind('click', function(){
					swap('userSelect', 'basicSelect');
				});
				$('#basicLeftBtn').bind('click', function(){
					swap('basicSelect', 'userSelect');
				});

				$('#deputuRightBtn').bind('click', function(){
					swap('userSelect', 'deputuSelect');
				});
				$('#deputuLeftBtn').bind('click', function(){
					swap('deputuSelect', 'userSelect');
				});
				$('#bossRightBtn').bind('click', function(){
					swap('userSelect', 'bossSelect');
				});
				$('#bossLeftBtn').bind('click', function(){
					swap('bossSelect', 'userSelect');
				});

				function swap(selector1 , selector2){
					$('#' + selector1 + ' option').each(function(){
						if(this.selected){
							$(this).appendTo($('#' + selector2));
							$(this).attr('selected', false);
						}
					});
				};

				$.ajax({
					type: 'POST',
					url: 'ProjectUser_selectFromProject.action',
					dataType: "json",
					data: "user.root.id=" + $("#rootId").val(),
					success: function(msg){
						if(msg.success){
							for(var i = 0; i< msg.data.length; i++){
								$('#userSelect').append('<option value="' + msg.data[i].id + '">'+msg.data[i].name+'</option>');
							}
						} else {
							alert(msg.message);
						}
					}
				});
				
				$('#submitBtn').bind('click', function(){
					var a = serializeSelector('basicSelect');
					var b = serializeSelector('deputuSelect');
					var c = serializeSelector('bossSelect');
					$.ajax({
						type: 'POST',
						url: 'ProjectProposal_add.action',
						dataType: "json",
						data: $('#proposalForm').serialize() + '&basicSelect=' + a + '&deputuSelect=' + b+ '&bossSelect=' + c,
						success: function(msg){
							alert(msg.message);
						}
					});
					
				});
				
				function serializeSelector(selectorName){
					var s = '';
					for(var i = 0; i < $('#' + selectorName)[0].options.length; i++){
						if(s == ''){
							s = $('#' + selectorName)[0].options[i].value;
						} else {
							s += "," + $('#' + selectorName)[0].options[i].value;
						}
					}
					return s;
				}
			});
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="msgDiv" style="color: red"></div>
				<form id="proposalForm" action="ProjectProposal_add.action" method="POST">
					<input id="rootId" type="hidden" name="proposal.root.id" value="${proposal.root.id}">
					<table class="table">
						<tr>
							<th colspan="4">添加项目建议书</th>
						</tr>
						<tr>
							<th>客户名称</th>
							<td> <input name="proposal.customerName"> </td>
							<th>项目建议书编号</th>
							<td> <input name="proposal.serialNumber"> </td>
						</tr>
						<tr>
							<th>客户介绍</th>
							<td colspan="3"><textarea rows="5" cols="45" name="proposal.customerAbout"></textarea> </td>
						</tr>
						<tr>
							<th>市场定向</th>
							<td colspan="3">
								<input type="radio" checked="checked" name="proposal.pricePosition" value="高" >高
								<input type="radio" checked="checked" name="proposal.pricePosition" value="中" >中
								<input type="radio" checked="checked" name="proposal.pricePosition" value="低" >低
							</td>
						</tr>
						<tr>
							<th>产量预测</th>
							<td> <input name="proposal.forecast"> </td>
							<th>生产基地</th>
							<td> <input name="proposal.productionBase"> </td>
						</tr>
						<tr>
							<th>市场对标车型</th>
							<td> <input name="proposal.marketcar"> </td>
							<th>工艺对标车型</th>
							<td> <input name="proposal.technologycar"> </td>
						</tr>
						<tr>
							<th>保密协议</th>
							<td>
								<input type="radio" checked="checked" name="proposal.confidentiality" value="已签" >已签
								<input type="radio" checked="checked" name="proposal.confidentiality" value="未签" >未签
							</td>
							<th>工艺协议</th>
							<td>
								<input type="radio" checked="checked" name="proposal.crafts" value="已签" >已签
								<input type="radio" checked="checked" name="proposal.crafts" value="未签" >未签
							</td>
						</tr>
						<tr>
							<th>产品数模</th>
							<td>
								<input type="radio" checked="checked" name="proposal.digifax" value="有" >有
								<input type="radio" checked="checked" name="proposal.digifax" value="无" >无
							</td>
							<th>产品图纸</th>
							<td>
								<input type="radio" checked="checked" name="proposal.drawing" value="有" >有
								<input type="radio" checked="checked" name="proposal.drawing" value="无" >无
							</td>
						</tr>
						<tr>
							<th rowspan="3">项目建议1</th>
							<td rowspan="3" valign="middle" align="center">
								<select id="userSelect" multiple="multiple" size="10" >
								</select>
							</td>
							<td>
								基本部门<br/>
								<input  id="basicRightBtn" type="button" value="==>"><br/>
								<input  id="basicLeftBtn" type="button" value="<=="><br/>
							</td>
							<td>
								<select id="basicSelect" multiple="multiple" ></select>
							</td>
						</tr>
						<tr>
							<td>
								副总经理名单<br/>
								<input  id="deputuRightBtn" type="button" value="==>"><br/>
								<input  id="deputuLeftBtn" type="button" value="<=="><br/>
							</td>							
							<td>
								<select id="deputuSelect" multiple="multiple" ></select>
							</td>
						</tr>
						<tr>
							<td>
								总经理<br/>
								<input  id="bossRightBtn" type="button" value="==>"><br/>
								<input  id="bossLeftBtn" type="button" value="<=="><br/>
							</td>
							<td>
								<select id="bossSelect" multiple="multiple" ></select>
							</td>						
						</tr>
						
						<tr>
							<th>项目建议</th>
							<td colspan="3"><textarea name="proposal.descriptions" rows="5" cols="45"></textarea> </td>
						</tr>
						<tr>
							<td align="center" colspan="4" >
								<input id="submitBtn" type="button" value="提交">
								<input type="button" value="清空">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
