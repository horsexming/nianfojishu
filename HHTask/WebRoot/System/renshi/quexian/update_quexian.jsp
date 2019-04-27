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
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				<form action="queXianAction!UpdatequeXian.action" method="post">
					<table class="table">
						<tr>
							<th colspan="4" align="center">
								<font size="5px;">修改</font>
							</th>
						</tr>

						<tr>
							<th align="right">
								类型
							</th>


							<td>
										<select name="queXian.leixing" id="queXian.leixing" style="width:100px;" >
										    <option value="${queXian.leixing}">${queXian.leixing}</option>
										       <option value="人">人</option>
				    							<option value="人">人</option>
				                       			<option value="机">机</option>
				                       			<option value="法">法</option>
				                       			<option value="环">环</option>
				                       			<option value="料">料</option>
				                        </select>
							</td>

						</tr>

						<tr>
							<th align="right">
								缺陷描述:
							</th>
							<td>
								<input type="text" name="queXian.loc"
									value="${queXian.loc}" style="width:200px; height: 50px;"  />
								<input type="hidden" name="queXian.id"
									value="${queXian.id}" />
									<input type="hidden" name="queXian.tianbaoren"
									value="${queXian.tianbaoren}" />
									<input type="hidden" name="queXian.tianbaoshijian"
									value="${queXian.tianbaoshijian}" />
									<input type="hidden" name="queXian.tianbaodept"
									value="${queXian.tianbaodept}" />
							</td>
						</tr>


						<tr>
							<th align="right">
								备注
							</th>
							<td>
								<input type="text" name="queXian.beizhu" value="${queXian.beizhu}" style="width:200px; height: 50px;"  />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="保存" class="input">
								<input type="button" name="Submit2" value="取消" class="input"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			function f33(){
				//alert("1111");
				var shuilv=document.getElementById("zhgongxu.kongxian").value;
				var jiage=document.getElementById("t11s").value;
				
				var buhanshui=jiage/(1+parseFloat(shuilv));
				document.getElementById("zhgongxu.buhanshui").value=buhanshui.toFixed(2);
			}
			function f1(){
				var jiage=document.getElementById("t11s").value;
				var shuilv=document.getElementById("zhgongxu.kongxian").value;
				var buhanshui=jiage/(1+parseFloat(shuilv));
				document.getElementById("zhgongxu.buhanshui").value=buhanshui.toFixed(2);
			
			}
			function f2(){
				var buhanshui=document.getElementById("zhgongxu.buhanshui").value;
				var shuilv=document.getElementById("zhgongxu.kongxian").value;
				var hanshui=buhanshui*(1+parseFloat(shuilv));
				document.getElementById("t11s").value=hanshui.toFixed(2);
			
			}
			
	
	
	</SCRIPT>

	</body>

</html>
