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
		<script type="text/javascript">
			var selectVar;
			function changeType(k, index){
				if($(k).val() == "普通"){
					$(k.parentNode.parentNode.childNodes[7]).empty();
					return;
				} else if($(k.parentNode.parentNode.childNodes[7]).html().length > 0){
					return;
				}
				var tab = $('#mytable').get(0);
				var slt=$('<select>',{
					name:"consumptions[" + index + "].consumption.id" 
				});
				for(b in selectVar){
					if(k.parentNode.parentNode.childNodes[1].childNodes[1].value == selectVar[b][0])
						continue;
					$(slt)[0].options.add(new Option(selectVar[b][1],selectVar[b][0]));
				}
				$(k.parentNode.parentNode.childNodes[7]).append(slt);
			}
			
			$(function(){
				$.ajax({
					type: "POST",
					url: "http://localhost:8080/HHTask/tdetail!getZizhiSelect.action",
					data: "detail.project.id=" + ${detail.project.id},
					dataType: 'json',
					success: function(json){
						selectVar = json;
					}
				});
				
			})
		</script>
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
				<form action="tconsumption_add.action" method="post">
					<table id="mytable" class="table">
						<tr>
							<th>名称</th>
							<th>材料种类</th>
							<th>工艺类型</th>
							<th>特殊件号</th>
							<th>密度</th>
							<th>长</th>
							<th>宽</th>
							<th>高(厚)</th>
							<th>单件材料<br>消耗定额</th>
							<th>零件净重</th>
							<th>材料规格</th>
							<th>利用率</th>
							<th>可出</th>
							<th>备注</th>
						</tr>
						<s:iterator value="details" id="d" status="st">
							<tr>
								<td>
									${d.name}
									<input type="hidden" name="consumptions[${st.index}].detail.id" value="${d.id}">
								</td>
								<td>
									<select name="consumptions[${st.index}].type">
										<option value="管料">管料</option>
										<option value="板料">板料</option>
										<option value="卷料">卷料</option>
									</select>
								</td>
								<td>
									<select name="consumptions[${st.index}].gytype" onchange="changeType(this,${st.index});">
										<option>普通</option>
										<option>合出</option>
										<option>连弯</option>
									</select>
								</td>
								<th></th>
								<td>
									<select name="consumptions[${st.index}].density">
										<option>7.7</option>
										<option>7.85</option>
										<option>7.9</option>
									</select>
								</td>
								<td><input name="consumptions[${st.index}].clength" style="width: 30"/> </td>
								<td><input name="consumptions[${st.index}].cwidth" style="width: 30"/> </td>
								<td><input name="consumptions[${st.index}].chigh" style="width: 30"/> </td>
								<td><input name="consumptions[${st.index}].partQuota" style="width: 70"/> </td>
								<td><input name="consumptions[${st.index}].netWeight" style="width: 30"/> </td>
								<td><input name="consumptions[${st.index}].specifications" style="width: 120"/> </td>
								<td><input name="consumptions[${st.index}].utilization" style="width: 30"/> </td>
								<td><input name="consumptions[${st.index}].molecular" style="width: 30"/> </td>
								<td><input name="consumptions[${st.index}].notes" style="width: 50"/> </td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="14" align="center">
								<input type="submit" value="提交" />
								<input type="reset" value="重置" />
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
