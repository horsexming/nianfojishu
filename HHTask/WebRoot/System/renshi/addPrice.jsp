<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<script language="javascript">
var count = 0;
var maxfile = 100;
function addUpload() {
	if (count >= maxfile)
		return;//限制最多maxfile个文件框
	count++;
	//自增id不同的HTML对象，并附加到容器最后
	var newDiv = "<div id=divUpload" + count + ">" + " <input id=file" + count
			+ " type=file size=50 name=imgpath>"
			+ " <a href=javascript:delUpload('divUpload" + count + "');>删除</a>"
			+ " </div>";
	document.getElementById("uploadContent").insertAdjacentHTML("beforeEnd",
			newDiv);
}
//删除指定元素
function delUpload(diva) {
	count--;
	document.getElementById(diva).parentNode.removeChild(document
			.getElementById(diva));
}
</script>
		<script type="text/javascript">
function check() {
	var jh = document.getElementById("jh");
	var xb = document.getElementById("xb");
	var jjdj = document.getElementById("jjdj");
	var kaishi = document.getElementById("kaishi");
	if (jh.value == "") {
		alert("件号不能为空");
		jh.focus();
		return false;
	} else if (xb.value == "") {
		alert("型别不能为空");
		xb.focus();
		return false;
	} else if (jjdj.value == "") {
		alert("计件单价不能为空");
		jjdj.focus();
		return false;
	} else if (kaishi.value == "") {
		alert("开始数量不能为空");
		kaishi.focus();
		return false;
	} else {
		return true;
	}
}
</script>
		<%
			String goodjh = (String) request.getParameter("goodjh");
			String setDate = (String) request.getParameter("setDate");
			String endDate = (String) request.getParameter("endDate");
		%>

	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
					<div style="float: left; width: 48%" align="right">
						<a href="" style="color: #ffffff">添加功能</a>
					</div>
				</div>
				
				<div align="center">
					<form
						action="TijingpriceAction.action?setDate=<%=setDate%>&endDate=<%=endDate%>"
						method="post" onsubmit="return check()"
						enctype="multipart/form-data">
						<table align="center">
							<tr>
								<th colspan="6">
									<font size="5">添加提奖价格表 </font>
									<%
										if (setDate != null && endDate != null) {
									%>
									<a
										href="TijingpriceAction!conditionAllBefore.action?setDate=<%=setDate%>&endDate=<%=endDate%>">返回</a>
									<%
										}
									%>
								</th>
							</tr>
							<s:if test="str==123">
								<tr>
									<th colspan="6">
										<font color="red">${jianhao}:添加成功</font>
									</th>
								</tr>
							</s:if>
							<tr>
								<th>
									件号
								</th>
								<td>
									<%
										if (goodjh == null) {
									%>
									<input type="text" name="tijiangjianhao" id="jh" />
									<%
										} else {
									%>
									<input type="text" name="tijiangjianhao" id="jh"
										value="<%=goodjh%>" />
									<%
										}
									%>

								</td>
								<th>
									型别
								</th>
								<td>
									<input type="text" name="tijiangxingbie" id="xb" />
								</td>
								<th>
									规格
								</th>
								<td>
									<input type="text" name="tijingguige" id="gg" />
								</td>
							</tr>

							<tr>
								<th>
									批次
								</th>
								<td>
									<input type="text" name="tijingpici" id="pc" />
								</td>
								<th>
									计件单价
								</th>
								<td>
									<input type="text" name="tijingdanjia" id="jjdj" />
								</td>
								<th>
									含税价
								</th>
								<td>
									<input type="text" name="tijianghanshuijia" id="hsdj" />
								</td>
							</tr>

							<tr>
								<th>
									定额（工时）
								</th>
								<td>
									<input type="text" name="tijiangdie" id="de" />
								</td>
								<th>
									开始数量
								</th>
								<td>
									<input type="text" name="kaishicount" id="kaishi" />
								</td>
								<th>
									结束数量
								</th>
								<td>
									<input type="text" name="jiusucount" id="jiesu" />
								</td>
							</tr>

							<tr>
								<th>
									上传文件
								</th>
								<td colspan="5">
									<input type="button" value="上传文件" onclick=" return addUpload()" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 提奖类别
									<select name="priceTjStyle">
										<option value="单价计价">
											单件计价
										</option>
										<option value="累计计价">
											累计计价
										</option>
										<option value="配套计价">
											配套计价
										</option>

									</select>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div id="uploadContent"></div>
								</td>
							</tr>
							<tr>
								<th>
									备注
								</th>
								<td colspan="5">
									<textarea name="tijingbeizhu"
										style="height: 70px; width: 280px;"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="确   定" />
									&nbsp;&nbsp;&nbsp;
									<input type="reset" value="取    消" />
								</td>
							</tr>
						</table>

					</form>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
