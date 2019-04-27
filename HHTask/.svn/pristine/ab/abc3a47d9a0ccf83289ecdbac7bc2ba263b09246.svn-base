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

			<div align="center" id="d1">
				<form action="zhaobiaoAction!Updatezhgongxu.action" method="post">
					<table class="table">
						<tr>
							<th colspan="4" align="center">
								<font size="5px;">修改原材料</font>
							</th>
						</tr>

						<tr>
							<th align="right">
								牌号
							</th>


							<td>
								<input type="text" name="zhgongxu.paihao"
									value="${zhgongxu.paihao}" />
							</td>

						</tr>

						<tr>
							<th align="right">
								规格:
							</th>
							<td>
								<input type="text" name="zhgongxu.guige"
									value="${zhgongxu.guige}" />
								<input type="hidden" name="zhgongxu.person"
									value="${zhgongxu.person}" />
							</td>
						</tr>


						<tr>
							<th align="right">
								单位
							</th>
							<td>
								<SELECT name="zhgongxu.danwei" id="danwei">
									<option value="${zhgongxu.danwei}">
										${zhgongxu.danwei}
									</option>

								</SELECT>
							</td>
						</tr>

						<tr>
							<th align="right">
								月份
								<input type="hidden" name="zhgongxu.id" value="${zhgongxu.id}" />

							</th>
							<td>
								<input class="Wdate" type="text" id="zhgongxu.yuefen"
									name="zhgongxu.yuefen"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
									value="${zhgongxu.yuefen}" />
							</td>
							<tr>

								<th align="right">
									税率
								</th>
								<td>
									<select name="zhgongxu.kongxian" id="zhgongxu.kongxian"
										onchange="f33()">
										<option value="${zhgongxu.kongxian}">
											税率 ${zhgongxu.kongxian*100}%
										</option>
										<option value="0.15">
											税率 15%
										</option>
										<option value="0.17">
											税率 17%
										</option>
										<option value="0.16">
											税率 16%
										</option>
										<option value="0.15">
											税率 15%
										</option>
										<option value="0.14">
											税率 14%
										</option>
										<option value="0.13">
											税率 13%
										</option>
										<option value="0.12">
											税率 12%
										</option>
										<option value="0.11">
											税率 11%
										</option>
										<option value="0.10">
											税率 10%
										</option>
									</select>


								</td>
							</tr>
						</tr>
						<tr>
							<th align="right">
								含税价格
							</th>
							<td>
								<input type="text" name="zhgongxu.jiage" id="t11s"
									value="${zhgongxu.jiage}" onkeyup="f1();" />
							</td>
						</tr>
						<tr>
							<th align="right">
								不含税价格
							</th>
							<td>
								<input type="text" name="zhgongxu.buhanshui"
									id="zhgongxu.buhanshui" value="${zhgongxu.buhanshui}"
									onkeyup="f2();" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="保存" class="input">
								<input type="reset" value="取消" class="input" />
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
		$(function(){
			getUnit("danwei");
		})
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
