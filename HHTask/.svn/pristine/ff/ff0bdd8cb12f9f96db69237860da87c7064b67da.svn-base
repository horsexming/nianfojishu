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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				
			</div>

			<div align="center">
				<h3>
					添加门卫卡信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="GuardCardAction_addGuardCard.action" method="post"
					 onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="right" style="width: 300px;">
								工号
							</th>
							<td align="left">
								<input type="text" name="guardCard.code" id="code" />
							</td>
							<th align="right">
								姓名
							</th>
							<td align="left">
								<input type="text" name="guardCard.name" id="name" />
							</td>
						</tr>
						<tr>
							<th align="right">
								性别
							</th>
							<td align="left">
								<SELECT name="guardCard.sex" id="sex"
									style="width: 156px;">
									<option value="">
										请选择性别
									</option>
									<option value="男">
										男
									</option>
									<option value="女">
										女
									</option>
								</SELECT>
							</td>
							<th align="right">
									民族
								</th>
								<td align="left">
									<select id="nation" name="guardCard.nation" style="width: 155px;">
										<option value="">
											请选择民族
										</option>
										<option value="汉族">
											汉族
										</option>
										<option value="蒙古族">
											蒙古族
										</option>
										<option value="鄂温克族">
											鄂温克族
										</option>
										<option value="达斡尔族">
											达斡尔族
										</option>
										<option value="赫哲族">
											赫哲族
										</option>
										<option value="朝鲜族">
											朝鲜族
										</option>
										<option value="满族">
											满族
										</option>
										<option value="纳西族">
											纳西族
										</option>
										<option value="藏族">
											藏族
										</option>
										<option value="僳僳族">
											僳僳族
										</option>
										<option value="彝族">
											彝族
										</option>
										<option value="普米族">
											普米族
										</option>
										<option value="白族">
											白族
										</option>
										<option value="独龙族">
											独龙族
										</option>
										<option value="怒族">
											怒族
										</option>
										<option value="阿昌族">
											阿昌族
										</option>
										<option value="景颇族">
											景颇族
										</option>
										<option value="德昂族">
											德昂族
										</option>
										<option value="佤族">
											佤族
										</option>
										<option value="拉祜族">
											拉祜族
										</option>
										<option value="布朗族">
											布朗族
										</option>
										<option value="基诺族">
											基诺族
										</option>
										<option value="傣族">
											傣族
										</option>
										<option value="哈尼族">
											哈尼族
										</option>
										<option value="门巴族">
											门巴族
										</option>
										<option value="珞巴族">
											珞巴族
										</option>
										<option value="塔吉克族">
											塔吉克族
										</option>
										<option value="柯尔克孜族">
											柯尔克孜族
										</option>
										<option value="哈萨克族">
											哈萨克族
										</option>
										<option value="乌孜别克族">
											乌孜别克族
										</option>
										<option value="塔塔尔族">
											塔塔尔族
										</option>
										<option value="俄罗斯族">
											俄罗斯族
										</option>
										<option value="锡伯族">
											锡伯族
										</option>
										<option value="维吾尔族">
											维吾尔族
										</option>
										<option value="裕固族">
											裕固族
										</option>
										<option value="土族">
											土族
										</option>
										<option value="撒拉族">
											撒拉族
										</option>
										<option value="保安族">
											保安族
										</option>
										<option value="东乡族">
											东乡族
										</option>
										<option value="回族">
											回族
										</option>
										<option value="畲族">
											畲族
										</option>
										<option value="黎族">
											黎族
										</option>
										<option value="京族">
											京族
										</option>
										<option value="毛南族">
											毛南族
										</option>
										<option value="壮族">
											壮族
										</option>
										<option value="仫佬族">
											仫佬族
										</option>
										<option value="瑶族">
											瑶族
										</option>
										<option value="侗族">
											侗族
										</option>
										<option value="苗族">
											苗族
										</option>
										<option value="水族">
											水族
										</option>
										<option value="布依族">
											布依族
										</option>
										<option value="仡佬族">
											仡佬族
										</option>
										<option value="土家族">
											土家族
										</option>
										<option value="羌族">
											羌族
										</option>
										<option value="高山族">
											高山族
										</option>
									</select>
						</tr>
						
						<tr>
							
								<th align="right">
								卡号
							</th>
							<td align="left">
								<input type="text" name="guardCard.cardId"
									id="cardId" />
							</td>
							<th align="right">
										部门:
									</th>
									<td colspan="1">
										<select id="dept" name="guardCard.dept"
											style="width: 155px;" onmouseover="createDept('dept')">
											<option value="">
												请选择部门
											</option>
										</select>
									</td>
  
						</tr>
						<tr>
					    <th align="right">
								籍贯
							</th>
							<td align="left">
								<input type="text" name="guardCard.birthplace"
									id="birthplace" />
							</td>
							<th align="right">
								身份证
							</th>
							<td align="left">
								<input type="text" name="guardCard.uid"
									id="uid" />
							</td>
							
							
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 100px; height: 50px;" />
							</td>
							
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function validate() {
	if (!validateText("code", "工号")) {
		return false;
	}
	if (!validateText("name", "姓名")) {
		return false;
	}
	if (!validateText("sex", "性别")) {
		return false;
	}
	if (!validateText("nation", "民族")) {
		return false;
	}
	if (!validateText("birthplace", "籍贯")) {
		return false;
	}
	if (!validateText("dept", "部门")) {
		return false;
	}
	if (!validateText("uid", "身份证")) {
		return false;
	}
	if (!validateText("cardId", "卡号")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>
	</body>
</html>
