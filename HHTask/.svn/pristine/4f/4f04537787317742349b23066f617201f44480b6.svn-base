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
				<form action="zhaobiaoAction!addtoubiao.action" method="post"
					enctype="multipart/form-data" theme="simple"
					onsubmit="return panduan();">
					<input type="hidden" name="zhaobiaoXi.id" value="${zhaobiaoXi.id}" />
					<input type="hidden" value="${zhaobiaoXi.t10}" name="zhaobiaoXi.t10" />
					<input type="hidden" value="${zhaobiao.numbers}" name="zhtoubiao.number" />
					<center>
						<table class="table" style="width: 70%;">
							<tr>
								<th colspan="2">
									招标信息
								</th>
							</tr>
							<tr>
								<th align="right">
									招标题目:
								</th>
								<td>
									${zhaobiao.title}
								</td>
							</tr>
							<tr>
								<th align="right">
									招标负责人:
								</th>
								<td>
									${zhaobiao.fuze}
								</td>
							</tr>
							<tr>
								<th align="right">
									负责人电话:
								</th>
								<td>
									${zhaobiao.phone}
								</td>
							</tr>
							<tr>
								<th align="right">
									开始时间:
								</th>
								<td>
									${zhaobiao.moban}
								</td>
							</tr>
							<tr>
								<th align="right">
									结束时间:
								</th>
								<td>
									${zhaobiao.kongxian}
								</td>
							</tr>
							<tr>
								<th align="right">
									招商简介:
								</th>
								<td colspan="2" height="100px">
									${zhaobiao.loc}
								</td>
							</tr>
						</table>
						<br>
						<table class="table" style="width: 100%">
							<tr>
								<th colspan="5" align="center">
									招标详细信息
								</th>
							</tr>
							<tr>
							<td>
									物料名称
								</td>
								<td>
									材料
								</td>
								<td>
									数量
								</td>
								<td>
									 交付时间
									 </td>
								<td>
									规格要求
								</td>
								
							</tr>
							<tr>
								<td>
									${zhaobiaoXi.t6}
								</td>
								<td>
									${zhaobiaoXi.zhmoban.name}
								</td>
								<td>
									${zhaobiaoXi.t2}
								</td>
								<td>
									${zhaobiaoXi.t8}
								</td>
								<td>
									${zhaobiaoXi.t5}
								</td>
							
							</tr>
						</table>
						<br />
						<br />

						<table class="table">
							<tr>
								<th colspan="4" align="center" height="50px">
									<font size="6px;">添加投标信息</font>
								</th>
							</tr>
							<tr>
								<th align="right">
									公司名称:
								</th>
								<td>
									<input type="hidden" value="${Users.name}"
										name="zhtoubiao.tname" readonly="readonly" />
										
										<input type="hidden" value="${zhaobiaoXi.t6}"
										name="zhtoubiao.name" readonly="readonly" />
										
										<input type="hidden" value="${zhaobiaoXi.zhmoban.name}"
										name="zhtoubiao.style" readonly="readonly" />
										
										<input type="hidden" value="${zhaobiaoXi.t5}"
										name="zhtoubiao.guigestyule" readonly="readonly" />
										
										<input type="hidden" value="${zhaobiaoXi.t2}"
										name="zhtoubiao.num" readonly="readonly" />
										
									${zhUser.name}
								</td>
								<th align="right">
									负责人:
								</th>
								<td>
									<input type="hidden" name="zhtoubiao.ttime" value="${zhaobiaoXi.t4}"/>
									<input type="text" name="zhtoubiao.tkong4" id="tkong4" />
								</td>

							</tr>
							<tr>
				
								<th align="right">
									联系方式:
								</th>
								<td>
									<input type="text" name="zhtoubiao.tkong5" id="zhtoubiao.tkong5" />
								</td>
								<th align="right">
									含税单价(含运费):
								</th>
								<td>
									<input type="text" name="zhtoubiao.tkong1" id="zhtoubiao.tkong1" />
									元/${zhaobiaoXi.t3}
								</td>
								<th align="right">
								</th>
								<td>
								</td>
							</tr>
							<!--                            款货结算矩阵表			 -->
							<tr>
								<th align="right" rowspan="2" colspan="2">
									款到发货:
								</th>
								<th align="right">
									首比比例:
								</th>
								<td>
									<input type="text" name="zhtoubiao.shoubikuan" id="shoubikuan" />%
								</td>
							</tr>
							<tr>

								<th align="right">
									款到后到货周期
								</th>
								<td>
									<input type="text" name="zhtoubiao.zhouqikuan"  id="zhtoubiao.zhouqikuan" onkeyup="if(isNaN(value))execCommand('undo')"/>天
								</td>
							</tr>
							<tr>
								<th align="right" rowspan="6" colspan="2">
									货到付款 :
								</th>
								<th align="right">
									首笔:
								</th>
								<td>
									<input type="text" name="zhtoubiao.shoubihuo" id="shoubihuo"  />%
								</td>
							</tr>
							<tr>
								<th align="right">
									首笔款到后到货周期
								</th>
								<td>
									<input type="text" name="zhtoubiao.zhouqihuo"  id="zhtoubiao.zhouqihuo" onkeyup="if(isNaN(value))execCommand('undo')"/>天
								</td>
							</tr>
							<tr>
								<th align="right">
									第二笔比例：
								</th>
								<td>
									<input type="text" name="zhtoubiao.erbihuo" id="erbihuo"/>%
								</td>
							</tr>
							<tr>
								<th align="right">
									第二笔款到后到货周期：
								</th>
								<td>
									<input type="text" name="zhtoubiao.zhouqier"  id="zhtoubiao.zhouqier" onkeyup="if(isNaN(value))execCommand('undo')"/>天
								</td>
							</tr>
							<tr>
								<th align="right">
									末笔比例：
								</th>
								<td>
									<input type="text" name="zhtoubiao.mobihuo" id="mobihuo" />%
								</td>
							</tr>
							<tr>
								<th align="right">
									末笔款到后到货周期：
								</th>
								<td>
									<input type="text" name="zhtoubiao.mobiuo"  id="zhtoubiao.mobiuo" onkeyup="if(isNaN(value))execCommand('undo')"/>天
								</td>
							</tr>

							<tr>
								<th align="right">
									附件：
								</th>
								<td colspan="3" align="left">
									<input type="file" name="t8" />
								</td>
							</tr>
							<tr>
								<th align="right">
									备注：
									<input type="hidden" name="zhtoubiao.xuqiushuliang"
										value="${zhaobiaoXi.t2}" />
									<input type="hidden" name="zhtoubiao.xuqiudanhuoriqi"
										value="${zhaobiao.kongxian}" />
									<input type="hidden" name="zhtoubiao.tkong6"
										value="${zhaobiaoXi.t3}" />
											<input type="hidden" name="zhtoubiao.lilv"
										value="${zhaobiaoXi.lilv}" />
								</th>
								<td colspan="3">
									<textarea name="zhtoubiao.tshuliang"
										style="width: 400px; height: 100px;"></textarea>
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
					</center>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function panduan() {
	
	//负责人:
	var tkong4 = document.getElementById("tkong4").value;
	if(tkong4==""){
			alert("负责人不能为空！！！");
		return false;
	}
	var tkong1 = document.getElementById("zhtoubiao.tkong1").value;
	if(tkong4==""){
			alert("投标价格不能为空！！！");
		return false;
	}
	var tkong5 = document.getElementById("zhtoubiao.tkong5").value;
	var zhouqikuan = document.getElementById("zhtoubiao.zhouqikuan").value;
	var zhouqihuo = document.getElementById("zhtoubiao.zhouqihuo").value;
	var zhouqier = document.getElementById("zhtoubiao.zhouqier").value;
	var mobiuo = document.getElementById("zhtoubiao.mobiuo").value;
		if(tkong5==""){
			alert("联系人不能为空！！！");
		return false;
	}
	if(zhouqikuan==""){
			alert("周期不能为空！！！");
		return false;
	}
	if(zhouqihuo==""){
			alert("周期能为空！！！");
		return false;
	}
	if(zhouqier==""){
			alert("周期不能为空！！！");
		return false;
	}
	if(mobiuo==""){
			alert("周期不能为空！！！");
		return false;
	}
	
	
	var shoubikuan = document.getElementById("shoubikuan").value;
	var shoubihuo = document.getElementById("shoubihuo").value;
	var erbihuo = document.getElementById("erbihuo").value;
	var mobihuo = document.getElementById("mobihuo").value;

	var zonghe =parseFloat(shoubikuan) + parseFloat(shoubihuo) +parseFloat(erbihuo)  + parseFloat(mobihuo);
	if (zonghe != 100) {
		alert("首比比例   首笔   第二笔比例   末笔比例    几项相加要等于100%");
		return false;
	} else {
		return true;
	}
	
	return false;
}
</script>
	</body>
</html>
