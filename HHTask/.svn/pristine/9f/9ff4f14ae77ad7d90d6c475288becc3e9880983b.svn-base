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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>
					生产退料申请单
				</h2>
				<font size="5" color="red" id="zhiti"></font>
				<form action="SCTuiliaoSqDanAction!addmoresqd.action" method="post" onsubmit="check()">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" name="sqd.markId" value="${sqd.markId}"
									 id="markId" readonly="readonly"/>
							</td>
							<th align="right">
								生产批次
							</th>
							<td>
								<input type="text" name="sqd.selfCard" value="${sqd.selfCard}"
									 id="selfCard" readonly="readonly"/>
							</td>
							<th align="right">
								品名
							</th>
							<td>
								<input type="text" name="sqd.proName" id="proName" value="${sqd.proName}"
									readonly="readonly" />
							</td>
							<th align="right">
								图号
							</th>
							<td>
								<input type="text" name="sqd.tuhao" id="tuhao" value="${sqd.tuhao}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								总成件号
							</th>
							<td>
								<input type="text" name="sqd.rootmarkId" id="rootmarkId" value="${sqd.rootmarkId}"
									readonly="readonly" />
							</td>
							<th align="right">
								总成批次
							</th>
							<td>
								<input type="text" name="sqd.rootLotId" id="rootLotId" value="${sqd.rootLotId}"
									readonly="readonly" />
							</td>
							<th align="right">
								供料属性
							</th>
							<td>
								<input type="text" name="sqd.kgliao" id="kgliao" value="${sqd.kgliao}"
									readonly="readonly" />
							</td>
							<th align="right">
								物料类别
							</th>
							<td>
								<input type="text" name="sqd.wgType" id="wgType" value="${sqd.wgType}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								版本号
							</th>
							<td>
								<input type="text" name="sqd.banBenNumber" id="banBenNumber" value="${sqd.banBenNumber}"
									readonly="readonly" />
							</td>
							<th align="right">
								单位
							</th>
							<td>
								<input type="text" name="sqd.unit" id="unit" readonly="readonly" value="${sqd.unit}" />
							</td>
							<th align="right">
								规格
							</th>
							<td>
								<input type="text" name="sqd.specification" id="specification" value="${sqd.specification}"
									readonly="readonly" />
							</td>
							<th align="right">
								供应商
							</th>
							<td>
								<input type="text" name="sqd.gys" id="gys" readonly="readonly" value="${sqd.gys}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								卡片类型
							</th>
							<td>
								<input type="text" name="sqd.procardStyle" id="procardStyle" value="${sqd.procardStyle}"
									readonly="readonly" />
							</td>
							<th align="right">
								产品类型
							</th>
							<td>
								<input type="text" name="sqd.productStyle" id="productStyle" readonly="readonly" value="${sqd.productStyle}" />
							</td>
							<td colspan="4" id="td_num">
								<b>物料追踪批次:</b>${sqd.examineLot} &nbsp;&nbsp;&nbsp;&nbsp;
								<b>来料数量:</b>${sqd.llNumber}&nbsp;&nbsp;&nbsp;&nbsp;
								<b>退料数量:</b><input type="text" value="${sqd.tlNumber}" id="tlNumber" 
									onkeyup="changvalue()" onblur="changvalue()"
								 name="sqd.tlNumber"/>
							</td>
						</tr>
						<tr>
							<th align="right"> 
								退料原因
							</th>
							<td colspan="8" >
								<textarea rows="5" cols="120" name="sqd.tlyuanyin" >
									${sqd.tlyuanyin}
								</textarea>
							</td>
						</tr>
					</table>
					<s:if test='pageStatus =="xg" '>
						<input type="submit" value="修改" class="input" onclick="todisabled(this)" id="sub" >
					</s:if>
					<input type="hidden" name="sqd.id" value="${sqd.id}" />
				</form>
				
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function changvalue(){
	var llNumber =	${sqd.llNumber};
	var tlNumber = $("#tlNumber").val();
	if(tlNumber>llNumber){
		$("#tlNumber").val('');
		alert('退料数量不能大于该批次的领料数量');
	}
	
}


</script>
	</body>
</html>
