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
			var index = 1;
			var shebeiSelect;
			var gongzhuangSelect;
			$(function(){
				$.ajax({
					type: "POST",
					url: "http://localhost:8080/HHTask/tequipment!selector.action",
					data: "equipment.project.id=1",
					dataType: 'json',
					success: function(json){
						shebeiSelect = json;
						var slt=$('<select>',{
							name:"processes[0].equipment.id" 
						});
						$(slt)[0].options.add(new Option("不需设备",""));
						for(b in json){
							$(slt)[0].options.add(new Option(json[b][1],json[b][0]));
						}
						$($('#mytable tr')[2].childNodes[5]).append(slt);
					}
				});
				$.ajax({
					type: "POST",
					url: "http://localhost:8080/HHTask/ttooling!selector.action",
					data: "tooling.project.id=1",
					dataType: 'json',
					success: function(json){
						gongzhuangSelect = json;
						var slt=$('<select>',{
							name:"processes[0].tooling.id" 
						});
						$(slt)[0].options.add(new Option("不需工装",""));
						for(b in json){
							$(slt)[0].options.add(new Option(json[b][1],json[b][0]));
						}
						$($('#mytable tr')[2].childNodes[7]).append(slt);
					}
				});
			})
			
			function addLine(){
				var tabTr ='<tr> <td> <input name="processes[' + index + '].xuhao" /> </td> <td> <input name="processes[' + index + '].name" /> </td> <td></td> <td><input type="hidden" name="processes[' + index + '].detail.id" value="${detail.id}"/></td> </tr>'; 
				$($('#mytable tr')[$('#mytable tr').length-2]).after(tabTr);
				
				var slt=$('<select>',{
					name:"processes[" + index + "].equipment.id" 
				});
				$(slt)[0].options.add(new Option("不需设备",""));
				for(b in shebeiSelect){
					$(slt)[0].options.add(new Option(shebeiSelect[b][1],shebeiSelect[b][0]));
				}
				$($('#mytable tr')[$('#mytable tr').length-2].childNodes[5]).append(slt);
				
				
				var slt1=$('<select>',{
					name:"processes[" + index + "].tooling.id" 
				});
				$(slt1)[0].options.add(new Option("不需工装",""));
				for(b in gongzhuangSelect){
					$(slt1)[0].options.add(new Option(gongzhuangSelect[b][1],gongzhuangSelect[b][0]));
				}
				$($('#mytable tr')[$('#mytable tr').length-2].childNodes[7]).append(slt1);
				
				index++;
			}
			
			function removeLine(){
				if(index == 1){
					return;
				}
				$($('#mytable tr')[$('#mytable tr').length-2]).remove();
				index--;
			}
			
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
				<form action="tprocess_add.action" method="post">
					<table id="mytable" class="table" style="width: 80%">
						<tr>
							<th>件号</th>
							<th>${detail.partNumber}</th>
							<th>名称</th>
							<th>${detail.name}</th>
						</tr>
						<tr>
							<td align="center">工序号</td>
							<td align="center">工序名称</td>
							<td align="center">所用设备</td>
							<td align="center">所用工装</td>
						</tr>
						<tr>
							<td> <input name="processes[0].xuhao" /> </td>
							<td> <input name="processes[0].name" /> </td>
							<td></td>
							<td><input type="hidden" name="processes[0].detail.id" value="${detail.id}"/></td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="button" value="追加" onclick="addLine();" />
								<input type="button" value="删除" onclick="removeLine();" />
								<input type="submit">
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
