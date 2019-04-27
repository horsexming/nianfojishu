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
								<input type="text" name="sqd.markId" onkeyup="getprocard()"
									onblur="getprocard()" id="markId" />
							</td>
							<th align="right">
								生产批次
							</th>
							<td>
								<input type="text" name="sqd.selfCard" onkeyup="getprocard()"
									onblur="getprocard()" id="selfCard" />
							</td>
							<th align="right">
								品名
							</th>
							<td>
								<input type="text" name="sqd.proName" id="proName"
									readonly="readonly" />
							</td>
							<th align="right">
								图号
							</th>
							<td>
								<input type="text" name="sqd.tuhao" id="tuhao"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								总成件号
							</th>
							<td>
								<input type="text" name="sqd.rootmarkId" id="rootmarkId"
									readonly="readonly" />
							</td>
							<th align="right">
								总成批次
							</th>
							<td>
								<input type="text" name="sqd.rootLotId" id="rootLotId"
									readonly="readonly" />
							</td>
							<th align="right">
								供料属性
							</th>
							<td>
								<input type="text" name="sqd.kgliao" id="kgliao"
									readonly="readonly" />
							</td>
							<th align="right">
								物料类别
							</th>
							<td>
								<input type="text" name="sqd.wgType" id="wgType"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								版本号
							</th>
							<td>
								<input type="text" name="sqd.banBenNumber" id="banBenNumber"
									readonly="readonly" />
							</td>
							<th align="right">
								单位
							</th>
							<td>
								<input type="text" name="sqd.unit" id="unit" readonly="readonly" />
							</td>
							<th align="right">
								规格
							</th>
							<td>
								<input type="text" name="sqd.specification" id="specification"
									readonly="readonly" />
							</td>
							<th align="right">
								供应商
							</th>
							<td>
								<input type="text" name="sqd.gys" id="gys" readonly="readonly" />
								<input type="hidden" name="sqd.zhuserId" id="zhuserId" />
							</td>
						</tr>
						<tr>
							<th align="right">
								卡片类型
							</th>
							<td>
								<input type="text" name="sqd.procardStyle" id="procardStyle"
									readonly="readonly" />
							</td>
							<th align="right">
								产品类型
							</th>
							<td>
								<input type="text" name="sqd.productStyle" id="productStyle" readonly="readonly" />
							</td>
							<td colspan="4" id="td_num">
							</td>
						</tr>
						<tr>
							<th align="right"> 
								退料原因
							</th>
							<td colspan="8" >
								<textarea rows="5" cols="120" name="sqd.tlyuanyin" >
								
								</textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" value="" name="sqd.procardId" id="procardId" />
					<input type="hidden" value="" name="sqd.priceId" id="priceId" />
					<input type="hidden" value="" name="sqd.hsPrice" id="hsPrice" />
					<input type="hidden" value="" name="sqd.bhsPrice" id="bhsPrice" />
					<input type="hidden" value="" name="sqd.taxprice" id="taxprice" />
					<input type="submit" value="申请" class="input" onclick="todisabled(this)" id="sub" disabled="disabled" >
				</form>
				
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function getprocard() {
	var markId = $("#markId").val();
	var selfCard = $("#selfCard").val();
	if (markId != "" && selfCard != "") {
		$.ajax( {
			type : "POST",
			url : "SCTuiliaoSqDanAction_findProcardOne.action",
			data : {
				markId : markId,
				selfCard : selfCard
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					$("#proName").val(data.proName);
					$("#tuhao").val(data.tuhao);
					$("#rootmarkId").val(data.rootMarkId);
					$("#rootLotId").val(data.rootSelfCard);
					$("#kgliao").val(data.kgliao);
					$("#wgType").val(data.wgType);
					$("#banBenNumber").val(data.banBenNumber);
					$("#unit").val(data.unit);
					$("#specification").val(data.specification);
					$("#gys").val(data.gys);
					$("#zhuserId").val(data.zhuserId);
					$("#procardStyle").val(data.procardStyle);
					$("#productStyle").val(data.productStyle);
					$("#procardId").val(data.id);
					$("#priceId").val(data.priceId);
					$("#hsPrice").val(data.hsPrice);
					$("#bhsPrice").val(data.bhsPrice);
					$("#taxprice").val(data.taxprice);
					var str = data.lingliaoDetail;
					$("#td_num").empty();
					var newstr = '<table >';
					if(str!=null && str != ''){
						var  aa = str.split(",");
						if(aa!=null && aa.length>0){
							for(var i=0;i<aa.length;i++){
								var b = aa[i];
								var bb = b.split(":");
								if(bb!=null && bb.length == 2){
									var examineLot = "";
									if(bb[0] != 'null'){
										examineLot = bb[0];
									}
									if(bb[1]>0){
										newstr += '<tr><td style="border: hidden;"><b>物料追踪批次:</b><input type="checkbox"  value="'+examineLot+'" name="examineLots" id="examineLot'+i+'" onclick = "check() " />'+examineLot
										+'</td><td style="border: hidden;"><b>来料数量:</b><input type="text" value="'+bb[1]+'" name="llNumbers" id="llNumber'+i+'"  readonly="readonly" />' +
										'</td><td style="border: hidden;"><b>退料数量:</b><input type="text" value="'+bb[1]+'" name="tlNumbers" id="tlNumber'+i+'" onkeyup="changvalue('+i+');numyanzheng(this,&apos;zhengshu&apos;)" onblur="changvalue('+i+');numyanzheng(this,&apos;zhengshu&apos;)" /></td></tr>'
									}
								}
							}
							newstr+='</table>'
							$("#td_num").append(newstr);
						}
					}else{
						$("#sub").attr('disabled','disabled');
						$("#zhiti").html("未找到相关的物料追踪批次，无法申请!");
					}
				}else  if(data.status == '完成' || data.status == '待入库' ||  data.status == '入库'){
					$("#zhiti").html("件号:"+markId+"，批次:"+selfCard+" 上层已完成无需申请退料单!");
					$("#proName").val('');
					$("#tuhao").val('');
					$("#rootmarkId").val('');
					$("#rootLotId").val('');
					$("#kgliao").val('');
					$("#wgType").val('');
					$("#banBenNumber").val('');
					$("#unit").val('');
					$("#specification").val('');
					$("#gys").val('');
					$("#zhuserId").val('');
					$("#procardStyle").val('');
					$("#productStyle").val('');
					$("#procardId").val('');
					$("#priceId").val('');
					$("#hsPrice").val('');
					$("#bhsPrice").val('');
					$("#taxprice").val('');
					$("#td_num").empty();
					$("#sub").attr('disabled','disabled');
				}else if(data.status == '初始'){
					$("#zhiti").html("件号:"+markId+"，批次:"+selfCard+" 上层未激活不能申请退料单!");
					$("#proName").val('');
					$("#tuhao").val('');
					$("#rootmarkId").val('');
					$("#rootLotId").val('');
					$("#kgliao").val('');
					$("#wgType").val('');
					$("#banBenNumber").val('');
					$("#unit").val('');
					$("#specification").val('');
					$("#gys").val('');
					$("#zhuserId").val('');
					$("#procardStyle").val('');
					$("#productStyle").val('');
					$("#procardId").val('');
					$("#priceId").val('');
					$("#hsPrice").val('');
					$("#bhsPrice").val('');
					$("#taxprice").val('');
					$("#td_num").empty();
					$("#sub").attr('disabled','disabled');
				}else{
					$("#proName").val('');
					$("#tuhao").val('');
					$("#rootmarkId").val('');
					$("#rootLotId").val('');
					$("#kgliao").val('');
					$("#wgType").val('');
					$("#banBenNumber").val('');
					$("#unit").val('');
					$("#specification").val('');
					$("#gys").val('');
					$("#zhuserId").val('');
					$("#procardStyle").val('');
					$("#productStyle").val('');
					$("#procardId").val('');
					$("#priceId").val('');
					$("#hsPrice").val('');
					$("#bhsPrice").val('');
					$("#taxprice").val('');
					$("#td_num").empty();
					$("#zhiti").html("未查询到相关生产卡片");
					$("#sub").attr('disabled','disabled');
				}
			}
		})
	}

}
function changvalue(num){
	var llNumber =	$("#llNumber"+num).val();
	var tlNumber = $("#tlNumber"+num).val();
	if(tlNumber>llNumber){
		$("#tlNumber"+num).val('');
		alert('退料数量不能大于该批次的领料数量');
	}
	
}
function check(){
	var examineLots = document.getElementsByName('examineLots');
	var	bool = true;
	if(examineLots!=null && examineLots.length>0){
		for(var i=0;i<examineLots.length;i++){
			if(examineLots[i].checked == true){
				$("#sub").removeAttr('disabled');
				bool = false;
				break;
			}
		}
	}
	if(bool){
		$("#sub").attr('disabled','disabled');
	}
}

</script>
	</body>
</html>
