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
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href=""
						style="color: rgb(79, 77, 77)"><br />
					</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加入库申请
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="WarehouseApplicationAction_add.action"
					method="post" onsubmit="return validate()">
					<table class="table" id="mytable">
						<tr style="width: 100%">
							<th align="center">
								入库物品
							</th>
							<td align="center">
								<input type="text" name="warehouseApplication.selfCard" value="${waigoudd.proName}"
									id="selfCard" readonly="readonly"/>
								<input type="hidden" name="warehouseApplication.type" value="采购入库"
									id="type" readonly="readonly"/>
								<input type="hidden" name="warehouseApplication.cuiqutype" value="存"
									id="type" readonly="readonly"/>
								<input type="hidden" name="warehouseApplication.classNameId" value="${waigoudd.id}"
									id="classNameId" readonly="readonly"/>
								<input type="hidden" name="warehouseApplication.className" value="${waigoudd.classNames}"
									id="className" readonly="readonly"/>
							</td>
							<th align="center">
								入库总数量
							</th>
							<td align="center">
								<input type="text" name="warehouseApplication.totalNumber"
									id="totalNumber" value="${waigoudd.shNumber}" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th >单个库位存放量</th>
							<td align="center">
								<input type="text" id="dangeNum" value=""   onchange="numyanzheng(this,'zhengshu');shengchengtr()"/>	
							</td>
							<td></td>
							<td></td>
						</tr>
<%--						<tr>--%>
<%--							<th>选择库位</th>--%>
<%--							<td colspan="1" align="center">--%>
<%--								<SELECT id="kuwei"style="width: 153px;" name="warehouseCertificateList[0].warehouseId">--%>
<%--									<option></option>--%>
<%--									<s:iterator value="wnList" id="wn" status="pageStatus">--%>
<%--										<option value="${wn.id}">--%>
<%--											${wn.number}|${wn.ip}--%>
<%--										</option>--%>
<%--									</s:iterator>--%>
<%--								</SELECT>--%>
<%--							</td>--%>
<%--							<th>存入数量</th>--%>
<%--							<td align="center">--%>
<%--								<input type="text" name="warehouseCertificateList[0].number"--%>
<%--									id="cabAceIp" value=""/>--%>
<%--							</td>--%>
<%--						</tr>--%>
						
						<tr id="lastTr">
							<td colspan="4" align="center">
								<input type="submit" value="申请"
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
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
var index =0;
function shengchengtr(){
	if(index>0){
		for(var i=0;i<index;i++){
			$("#tr_"+i).remove();
		}
	}
	var newLinen;
	var dangeNum =	$("#dangeNum").val();
	var totalNumber = $("#totalNumber").val();
	if(dangeNum!="" && dangeNum>0 && totalNumber != "" && totalNumber>0){
		index = Math.ceil(totalNumber/dangeNum);
		for(var i=0;i<index;i++){
			newLinen = '<tr id="tr_'+i+'"><th>选择库位</th><td align="center">' +
			'<SELECT id="kuwei" style="width: 173px;" name="warehouseCertificateList['+i+'].warehouseId"  ><option></option><s:iterator value="wnList" id="wn" status="pageStatus">' +
			'<option value="${wn.id}">${wn.number}|${wn.ip}</option></s:iterator></SELECT>' +
			'</td><th>存入数量</th><td align="center"><input type="text" name= "warehouseCertificateList['+i+'].number" id="cabAceIp" value= "'+dangeNum+'"  /></td></tr>';
			if(totalNumber%dangeNum!=0 && (i+1) == index ){
				newLinen = '<tr id="tr_'+i+'" ><th>选择库位</th><td align="center">' +
			'<SELECT id="kuwei" style="width: 173px;" name="warehouseCertificateList['+i+'].warehouseId"  ><option></option><s:iterator value="wnList" id="wn" status="pageStatus">' +
			'<option value="${wn.id}">${wn.number}|${wn.ip}</option></s:iterator></SELECT>' +
			'</td><th>存入数量</th><td align="center"><input type="text" name="warehouseCertificateList['+i+'].number" id="cabAceIp" value="'+totalNumber%dangeNum+'"  /></td></tr>';
			}	
			$("#lastTr").before(newLinen);
		}
	}
}
</script>
	</body>
</html>
