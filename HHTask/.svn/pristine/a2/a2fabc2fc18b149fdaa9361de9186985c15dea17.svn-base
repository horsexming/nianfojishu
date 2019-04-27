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
				<h3>
					修改客户信息
				</h3>
				<form action="clientManager_update.action" method="post"
					enctype="multipart/form-data">
					<table class="table" style="width: 45%">
						<tr>
							<td style="stress: 16px;" align="center">
								<strong>公司信息</strong>
							</td>
						</tr>
						<tr>
							<td>
								公司名称：
								<input type="text" name="cl.clientcompanyname"
									id="clientcompanyname" value="${cl.clientcompanyname}" />
							</td>
						</tr>
						<tr>
							<td>
								公司简称：
								<input type="text" name="cl.companyAbbreviation"
									id="companyAbbreviation" value="${cl.companyAbbreviation}" />
							</td>
						</tr>
						<tr>
							<td>
								公司性质：
								<select name="cl.natureOfBusiness">
									<s:iterator id="typeStr"
										value='{"国有企业 ","中外合作企业","中外合资企业","外商独资企业","集体企业", "私营企业"}'
										status="i">
										<s:if test="#typeStr == cl.natureOfBusiness">
											<option value="${typeStr }" selected="selected">
												${typeStr }
											</option>
										</s:if>
										<s:else>
											<option value="${typeStr }">
												${typeStr }
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								公司法人：
								<input type="text" name="cl.legalPerson"
									value="${cl.legalPerson}" />
							</td>
						</tr>
						<tr>
							<td>
								时间：
								<input style="width: 155px" class="Wdate" type="text"
									name="cl.clientdatatime" value="${cl.clientdatatime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td>
								开户行：
								<input type="text" name="cl.banks" value="${cl.banks}" />
							</td>
						</tr>
						<tr>
							<td>
								备注：
								<input type="text" name="cl.clientremarks"
									value="${cl.clientremarks}" />
							</td>
						</tr>
						<tr>
							<td align="left">
								公司地址：
								<input type="text" name="cl.address" value="${cl.address}"
									style="width: 300px;" />
							</td>
						</tr>
						<tr>
							<td style="size: 16px;" align="center">
								<strong>联系人信息</strong>
							</td>
						</tr>
						<tr>
							<td>
								联系人：
								<input type="text" name="cl.clientname" value="${cl.clientname}" />
							</td>
						</tr>
						<tr>
							<td>
								性别：
								<input type="radio" name="cl.clientsex" value="男"
									checked="checked">
								男
								</input>
								<input type="radio" name="cl.clientsex" value="女">
								女
								</input>
							</td>
						</tr>
						<tr>
							<td>
								职位：
								<input type="text" name="cl.clientposition"
									value="${cl.clientposition}" />
						</tr>
						<tr>
							<td>
								部门：
								<input type="text" name="cl.clientdept" value="${cl.clientdept}" />
							</td>
						</tr>
						<tr>
							<td>
								手机号：
								<input type="text" name="cl.clientmobilenumber"
									value="${cl.clientmobilenumber}" />
							</td>
						</tr>
						<tr>
							<td>
								电话号码：
								<input type="text" name="cl.clientphonenumber"
									value="${cl.clientphonenumber}" />
							</td>
						</tr>
						<tr>
							<td>
								身份证号：
								<input type="text" name="cl.clientcardnumber"
									value="${cl.clientcardnumber}" />
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type="hidden" name="cl.id" value="${cl.id}" />
								<input type="submit" value="修改"
									style="width: 80px; height: 50px;" />
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
