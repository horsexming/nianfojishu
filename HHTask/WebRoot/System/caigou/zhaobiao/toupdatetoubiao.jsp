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
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center" id="d1">
				<form action="zhaobiaoAction!updatetoubiao.action" method="post"
					onsubmit="return panduan();">
					<input type="hidden" name="zhtoubiao.tid" value="${zhtoubiao.tid}" />
					<input type="hidden" name="zhtoubiao.number"
						value="${zhtoubiao.number}" />
					<input type="hidden" name="zhtoubiao.zhUserId"
						value="${zhtoubiao.zhUserId}" />
					<input type="hidden" name="zhtoubiao.tkong7"
						value="${zhtoubiao.tkong7}" />
					<input type="hidden" name="zhtoubiao.hetongbiaohao"
						value="${zhtoubiao.hetongbiaohao}" />
					<input type="hidden" name="zhtoubiao.tkong8"
						value="${zhtoubiao.tkong8}" />
					<input type="hidden" name="zhtoubiao.tkong9"
						value="${zhtoubiao.tkong9}" />
					<input type="hidden" name="zhtoubiao.tkong10"
						value="${zhtoubiao.tkong10}" />
					<input type="hidden" name="zhtoubiao.tkong6"
						value="${zhtoubiao.tkong6}" />

					<input type="hidden" value="${zhtoubiao.name}"
						name="zhtoubiao.name" readonly="readonly" />

					<input type="hidden" value="${zhtoubiao.style}"
						name="zhtoubiao.style" readonly="readonly" />

					<input type="hidden" value="${zhtoubiao.guigestyule}"
						name="zhtoubiao.guigestyule" readonly="readonly" />

					<input type="hidden" value="${zhtoubiao.num}" name="zhtoubiao.num"
						readonly="readonly" />

					<table class="table">
						<tr>
							<th colspan="4" align="center">
								<font size="6px;">修改投标信息</font>
							</th>
						</tr>
						<tr>
							<th align="right">
								公司名称:
							</th>
							<td>
								<input type="text" name="zhtoubiao.tname" readonly="readonly"
									value="${zhtoubiao.tname}" />
								<input type="hidden" name="zhtoubiao.ttime"
									value="${zhtoubiao.ttime}" />
							</td>
							<th align="right">
								负责人:
							</th>
							<td>
								<input type="text" name="zhtoubiao.tkong4"
									value="${zhtoubiao.tkong4}" id="zhtoubiao.tkong4" />
							</td>
						</tr>
						<tr>
							<th align="right">
								联系方式:
							</th>
							<td>
								<input type="text" name="zhtoubiao.tkong5"
									value="${zhtoubiao.tkong5}" id="zhtoubiao.tkong5" />
							</td>
							<th align="right">
								税前单价:
							</th>
							<td>
								<input type="text" name="zhtoubiao.tkong1" id="zhtoubiao.tkong1"
									value="${zhtoubiao.tkong1}" />
								元/${zhtoubiao.tkong6}
							</td>
						</tr>

						<!-- 
						<s:iterator value="list" id="huikuangXiangxi" status="pageIndex">
							<s:if test="#pageIndex.index % 2== 0">
								<tr>
							</s:if>
							<th align="right">
								<input type="hidden" name="huikuangXiangxis.hxid"
									value="${huikuangXiangxi.hxid}" />
								<input type="hidden" name="huikuangXiangxis.hname"
									value="${huikuangXiangxi.hname}" />
								<input type="hidden" name="huikuangXiangxis.hxXid"
									value="${huikuangXiangxi.hxXid}" />
								<input type="hidden" name="huikuangXiangxis.danwei"
									value="${huikuangXiangxi.danwei}" />
								${huikuangXiangxi.hname}
							</th>
							<td>
								<input type="text" name="huikuangXiangxis.hmoney"
									value="${huikuangXiangxi.hmoney}" />
							</td>

							<s:if test="#pageIndex.index+1 % 2== 0">
								</tr>
							</s:if>
						</s:iterator>
                 -->
						<!--                            款货结算矩阵表			 -->
						<tr>
							<th align="right" rowspan="2" colspan="2">
								款到发货:
							</th>
							<th align="right">
								首款比例:
							</th>
							<td>
								<input type="text" name="zhtoubiao.shoubikuan"
									value="${zhtoubiao.shoubikuan}" id="shoubikuan" />
							</td>
						</tr>
						<tr>

							<th align="right">
								款到后到货周期
							</th>
							<td>
								<input type="text" name="zhtoubiao.zhouqikuan"
									id="zhtoubiao.zhouqikuan"
									onkeyup="if(isNaN(value))execCommand('undo')" />
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
								<input type="text" name="zhtoubiao.shoubihuo"
									value="${zhtoubiao.shoubihuo}" id="shoubihuo" />
							</td>
						</tr>
						<tr>
							<th align="right">
								首笔款到后到货周期
							</th>
							<td>
								<input type="text" name="zhtoubiao.zhouqihuo"
									id="zhtoubiao.zhouqihuo" value="${zhtoubiao.zhouqihuo}"
									onkeyup="if(isNaN(value))execCommand('undo')" />
							</td>
						</tr>
						<tr>
							<th align="right">
								第二笔比例：
							</th>
							<td>
								<input type="text" name="zhtoubiao.erbihuo"
									value="${zhtoubiao.erbihuo}" id="erbihuo" />
							</td>
						</tr>
						<tr>
							<th align="right">
								第二笔款到后到货周期：
							</th>
							<td>
								<input type="text" name="zhtoubiao.zhouqier"
									id="zhtoubiao.zhouqier" value="${zhtoubiao.zhouqier}"
									onkeyup="if(isNaN(value))execCommand('undo')" />
							</td>
						</tr>
						<tr>
							<th align="right">
								末笔比例：
							</th>
							<td>
								<input type="text" name="zhtoubiao.mobihuo"
									value="${zhtoubiao.mobihuo}" id="mobihuo" />
							</td>
						</tr>
						<tr>
							<th align="right">
								末笔款到后到货周期：
							</th>
							<td>
								<input type="text" name="zhtoubiao.mobiuo" id="zhtoubiao.mobiuo"
									value="${zhtoubiao.mobiuo}"
									onkeyup="if(isNaN(value))execCommand('undo')" />
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
							</th>
							<td colspan="3">
								<input name="zhtoubiao.tshuliang" value="${zhtoubiao.tshuliang}"
									style="width: 400px; height: 100px;"></input>
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
function panduan() {
		//负责人:
	var tkong4 = document.getElementById("zhtoubiao.tkong4").value;
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
	
	//
	var shoubikuan = document.getElementById("shoubikuan").value;
	var shoubihuo = document.getElementById("shoubihuo").value;
	var erbihuo = document.getElementById("erbihuo").value;
	var mobihuo = document.getElementById("mobihuo").value;

	var zonghe =parseFloat(shoubikuan)*10 + parseFloat(shoubihuo)*10 +parseFloat(erbihuo)*10  + parseFloat(mobihuo)*10;
	if (zonghe != 10) {
		alert("首笔比例+首笔+第二笔比例+末笔比例    几项相加要等于1");
		return false;
	} else {
		return true;
	}
	return false;
}

</SCRIPT>
	</body>
</html>
