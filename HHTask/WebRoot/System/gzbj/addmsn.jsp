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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
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
				<form action="GzstoreAction_addGzMsn.action" method="post" enctype="multipart/form-data">
					<table class="table">
						<tr>
							<th colspan="2" align="center">
								<h3>
									添加模具MSN
								</h3>
							</th>
						</tr>
						<tr>
							<th align="right">
								模具编号
							</th>
							<td>
								<input type="text" id="number" name="gzMsn.number" value="" />
							</td>
						</tr>
						<tr>
							<th align="right">
								模具名称:
							</th>
							<td>
								<input type="text" id="matetag" name="gzMsn.matetag" value=" " />
							</td>
						</tr>
						<tr>
							<th align="right">
								测量精度:
							</th>
							<td>
								<input type="text" id="cljd" name="gzMsn.cljd"
									value="" />
							</td>
						</tr>
						<tr>
							<th align="right">
								是否在有效期内:
							</th>
							<td>
								<SELECT name="gzMsn.isyouxiao">
									<option>是</option>
									<option>否</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								测试人员:
							</th>
							<td>
								<input type="text" name="gzMsn.person"  />
							</td>
						</tr>
						<tr>
							<th align="right">
								职位:
							</th>
							<td>
								<input type="text" name="gzMsn.dept" value="" />
							</td>
							</tr>
							<tr>
								<th align="right">
										GR&R值:
								</th>
								<td>
										<input type="text" name="gzMsn.GRR" value="" id="GRR" onblur="zhengshu();"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									上传图片:
								</th>
								<td>
									<input type="file" value="" name="gzMsn.picF" />
								</td>
								
							</tr>
						<tr >
							<th align="right" ></th>
							<td >
								<input type="submit" value="添加" class="input" />
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
		<script type="text/javascript">
function zhengshu() {
	var testNum = document.getElementById("GRR").value;
	if(testNum==""){
		alert("不能为空");
	}else{
	var reg = new RegExp("^(\\d|[1-9]\\d|100)$");
	if(!reg.test(testNum)) {
	       alert("请输入0-100的整数！");
	
	}
	}
}
</script>
	</body>
</html>
