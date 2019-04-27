<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				style="width: 100%; font-weight: bold; height: 50px; background: url('<%=basePath%>images/title.jpg') no-repeat;"
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>
			<div align="center">
				<form id="xform"
					action="QualityccAction!addQualitycheckta.action?qualitycheckto.Id=${qualitycheckto.id}"
					method="post">
					<table class="table" style="width: 85%;">
						<tr>
							<td align="right">
								客户名称： </td><td align="left">${qualitycheckto.kehu }
								<input type="hidden" name="qualitycheckta.kehu" id="kehu"
									value="${qualitycheckto.kehu }" />
							</td>
						
							<td align="right">
								产品名称： </td><td align="left">${qualitycheckto.productname}
								<input type="hidden" name="qualitycheckta.productname"
									value="${qualitycheckto.productname}" />
							</td>
						</tr>
						<tr>
							<td align="right">检测人员：</td>
							<td align="left">${qualitycheckto.renyuan}</td>
							<td align="right">检测批次：</td>
							<td align="left">${qualitycheckto.pici}</td>
						</tr>
						<tr>
							<td align="right">
								产品图号：</td><td align="left">${qualitycheckto.leibie}
								<input type="hidden" name="qualitycheckta.leibie"
									value="${qualitycheckto.leibie}" />
							</td>
						
							<td align="right">
								检验项目：</td><td align="left">
								<input type="text" name="qualitycheckta.additem"  id="xiangmu"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								标准值/要求：</td><td align="left">
								<input type="text" name="qualitycheckta.biaozhun" id="yaoqiu"/>
							</td>
						
							<td align="right">
								量检具/编号：</td><td align="left">
								<input type="text" name="qualitycheckta.fangshi" id="bianhao"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								检测编号： </td><td align="left">${qualitycheckto.addtime}
							</td>
						
							<td align="right">
								缺陷系数类别：</td><td align="left">
								<select  name="qualitycheckta.xishul"
									style="width: 130px;">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								填写类型：</td><td align="left">
								<select  name="qualitycheckta.bij"
									style="width: 130px;">
									<option value="判断">判断</option>
									<option value="手填">手填</option>
								</select>
							</td>
						
							<td align="right">
								缺陷系数：</td><td align="left">
								<input type="text" name="qualitycheckta.xishuz"  id="quexian"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								引用标准：
								</td><td align="left">
								${qualitycheckto.yybz}
								<input type="hidden" name="qualitycheckta.yybz" value="${qualitycheckto.yybz}"/>
							</td>
							<td align="right">备 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注：</td>
							<td align="left"><input type="text" name="qualitycheckta.beizhu" /></td>
						</tr>
							
							
						<tr>
							<td align="center" colspan="4">
								<s:if test='%{"已审批".equals(qualitycheckto.shenpi)}'>
							<font size="+3">	${qualitycheckto.shenpi} </font>
							</s:if>
							<s:else>
								<input type="button" value="添加" onclick="tijiao();"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
							</s:else>
							</td>
						</tr>
							
					</table>
				</form>
			</div>

			<div align="center">

				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							 检验项目
						</th>
						<th align="center">
							标准值/要求
						</th>
						<th align="center">
							量检具/编号
						</th>
						<th align="center">
							缺陷类别
						</th>
						<th align="center">
							缺陷系数
						</th><th align="center">
							填写类型
						</th>
						<th align="center">
							备注
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list"
						id="qualitychecktalist" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout=outBgcolor(this,'');>
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
							</s:if>
							<s:else>
								<font color="#c0dcf2"></font>
							</s:else>
							<s:property value="#pageStatus.index+1" />

						</td>
						<td>
							${qualitychecktalist.additem}
						</td>
						<td width="20%">
							${qualitychecktalist.biaozhun}
						</td>
						<td>
							${qualitychecktalist.fangshi}
						</td>
						<td>
							${qualitychecktalist.xishul}
						</td>
						<td>
							${qualitychecktalist.xishuz}
						</td>
						<td>
							${qualitychecktalist.bij}
						</td>
						<td>
							${qualitychecktalist.beizhu}
						</td>
						<td><s:if test='%{"已审批".equals(qualitycheckto.shenpi)}'></s:if><s:else>
							<a	href="QualityccAction!deleteQualitycheckta.action?qualitycheckta.Id=${qualitychecktalist.id}
																				&&qualitycheckto.Id=${qualitycheckto.id}"
								onclick="if(confirm('确实要删除该记录吗?')) return true; return false;">删除</a>
								</s:else>
						</td>
						</tr>
					</s:iterator>
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function check(){
	var Value1=document.getElementById("kehu").value;
 	if(testValue1==""){
 		alert("请填写客户!");
 		return false;
 	}
 }
 function tijiao(){
 	var xiangmu=document.getElementById("xiangmu").value;
 	var tyaoqiu=document.getElementById("yaoqiu").value;
 	var bianhao=document.getElementById("bianhao").value;
 	var quexian=document.getElementById("quexian").value;
 	if(xiangmu==""){
 		alert("请填写检验项目!");
 		return false;
 	}else if(yaoqiu==""){
 		alert("请填写标准值/要求!");
 		return false;
 	}else if(bianhao==""){
 		alert("请填写量检具/编号!");
 		return false;
 	}else if(quexian==""){
 		alert("请填写缺陷系数!");
 		return false;
 	}else{
	document.forms.xform.action = "QualityccAction!addQualitycheckta.action?qualitycheckto.Id=${qualitycheckto.id}";
	document.forms.xform.submit();
	}
}
</script>
	</body>
</html>
