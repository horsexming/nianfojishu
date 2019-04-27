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
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				<form action="zhaobiaoAction!updatezhmoban.action" method="post"
					theme="simple">
					<table>
						<tr>
							<td>
								名称
							</td>
							<td>
							   <input type="hidden" id="zhmoban.id" name="zhmoban.id" value="${zhmoban.id}" />
							    <input type="hidden" id="zhmoban.paihao" name="zhmoban.paihao" value="${zhmoban.paihao}" />
							    <input type="hidden" id="zhmoban.xuhao" name="zhmoban.xuhao" value="${zhmoban.xuhao}" />
								<input type="text" id="zhmoban.name" name="zhmoban.name"  value="${zhmoban.name}"/>
							</td>
						</tr>
						<tr>
							<td>
								模版类名
							</td>
							<td>
								<select name="zhmoban.classe" id="zhmoban.classe">
									<option value="${zhmoban.classe}">
										${zhmoban.classe}
									</option>
									<option value="板料">
										板料
									</option>
									<option value="管料">
										管料
									</option>
									<option value="外购件">
										外购件
									</option>
									<option value="工装">
										工装
									</option>
									<option value="包装物">
										包装物
									</option>
								</select>


							</td>
						</tr>
						<tr>
							<td>
								规格
							</td>
							<td>
								<input type="text" id="zhmoban.guige" name="zhmoban.guige"  value="${zhmoban.guige}"/>
							</td>
						</tr>
						<tr>
							<td>
								需要/吨
							</td>
							<td>
								<input type="text" id="zhmoban.xuyao" name="zhmoban.xuyao" value="${zhmoban.xuyao}" />
							</td>
						</tr>
						<tr>
							<td>
								产地
							</td>
							<td>
								<input type="text" id="zhmoban.changdi" name="zhmoban.changdi"  value="${zhmoban.changdi}"/>
							</td>
						</tr>
						<tr>
							<td>
								级别
							</td>
							<td>
								<select name="zhmoban.jibei" id="zhmoban.jibei">
									<option value="${zhmoban.jibei}">
										${zhmoban.jibei}
									</option>
									<option value="特技正品">
										特技正品
									</option>
									<option value="一级正品">
										一级正品
									</option>
									<option value="二级正品">
										二级正品
									</option>
									<option value="三级正品">
										三级正品
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<s:submit value="保存" />
							</td>
							<td>
								<input type="button" name="Submit2" value="取消"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<SCRIPT type="text/javascript">
	 
	</SCRIPT>

</html>
