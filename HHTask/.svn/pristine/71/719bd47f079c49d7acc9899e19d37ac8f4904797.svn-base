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
					添加客户(Adding customers)
				</h3>
				<form action="clientManager_add.action" method="post"
					enctype="multipart/form-data">
					<table class="table" style="width: 50%;">
						<tr>
							<td style="stress: 16px;" align="center">
								<strong>公司信息(Company Information)</strong>
							</td>
						</tr>
						<tr>
							<td>
								公司名称：
								<input type="text" name="cl.clientcompanyname"
									id="clientcompanyname" />
							</td>
						</tr>
						<tr>
							<td>
								公司简称：
								<input type="text" name="cl.companyAbbreviation"
									id="companyAbbreviation" />
							</td>
						</tr>
						<tr>
							<td>
								公司性质：
								<select name="cl.natureOfBusiness">
									<s:iterator id="typeStr"
										value='{"国有企业 (State-owned enterprises)","中外合作企业(Sino-foreign cooperative enterprises)","中外合资企业(Joint ventures)","外商独资企业(Foreign-owned enterprises)","集体企业(Collective enterprises)", "私营企业(Private)"}'
										status="i">
										<s:if test="#i == 0">
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
								<input type="text" name="cl.legalPerson" />
							</td>
						</tr>
						<tr>
							<td>
								时间：
								<input style="margin-left: 32px; width: 155px" class="Wdate"
									type="text" name="cl.clientdatatime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td>
								开户行：
								<input style="margin-left: 16px;" type="text" name="cl.banks" />
							</td>
						</tr>
						<tr>
							<td>
								营业执照：
								<input type="file" name="businessLicenseFile" />
							</td>
						</tr>
						<tr>
							<td>
								组织机构：
								<input type="file" name="organizationFile" />
							</td>
						</tr>
						<tr>
							<td>
								公司Logo：
								<input type="file" name="logoFile" />
							</td>
						</tr>
						<tr>
							<td align="left">
								公司地址：
								<input type="text" name="cl.address" style="width: 300px;" />
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
								<input type="text" style="margin-left: 16px;"
									name="cl.clientname" />
							</td>
						</tr>
						<tr>
							<td>
								性别：
								<input type="radio" style="margin-left: 20px;"
									name="cl.clientsex" value="男" checked="checked">
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
								<input type="text" style="margin-left: 30px;"
									name="cl.clientposition" />
							</td>
						</tr>
						<tr>
							<td>
								部门：
								<input type="text" style="margin-left: 30px;"
									name="cl.clientdept" />
							</td>
						</tr>
						<tr>
							<td>
								手机号：
								<input type="text" style="margin-left: 18px;"
									name="cl.clientmobilenumber" />
							</td>
						</tr>
						<tr>
							<td>
								电话号码：
								<input type="text" name="cl.clientphonenumber" />
							</td>
						</tr>
						<tr>
							<td>
								身份证号：
								<input type="text" name="cl.clientcardnumber" />
								<input type="hidden" name="orderStatus" value="NO" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="submit" value="添加"
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
