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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>超损补料</h2>
				<form action="ProcardAction!findCsblListById.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input value="${csbl.markId}" name="csbl.markId" id="">
							</td>
							<th align="right">
								名称
							</th>
							<td>
								<input value="${csbl.proName}" name="csbl.proName" id="">
							</td>
						</tr>
					</table>
					<input type="submit"  value="查询" class="input"/>
				</form>
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th>
								卡片类型
							</th>
							<th>
								件号
							</th>
							<th>
								名称
							</th>
							<th>
								规格
							</th>
							<th>
								供料属性
							</th>
							<th>
								物料类别
							</th>
							<th>
								补料数量
							</th>
							<th>
								含税单价
							</th>
							<th>
								不含税单价
							</th>
							<th>
								税率
							</th>
							<th>
								补料金额(含税)
							</th>
							<th>
								单位
							</th>
							<th>
								备注
							</th>
						</tr>
						<s:iterator id="pagecsbl" value="csblList"
								status="statussdf">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf.index+1" />
									<input type="hidden" value="${statussdf.index+1}" id="xuhao_${pageProcard.id}"/>
								</td>
								<td>
									${pagecsbl.procardStyle}
								</td>
								<td>
									${pagecsbl.markId}
								</td>
								<td>
									${pagecsbl.proName}
								</td>
								<td>
									${pagecsbl.specification}
								</td>
								<td>
									${pagecsbl.kgliao}
								</td>
								<td>
									${pagecsbl.wgtype}
								</td>
								<td>
									${pagecsbl.blNum}
								</td>
								<td>
									${pagecsbl.hsprice}
								</td>
								<td>
									${pagecsbl.bhsprice}
								</td>
								<td>
									${pagecsbl.taxprice}
								</td>
								<td>
									${pagecsbl.sumPrice}
								</td>
								<td>
									${pagecsbl.unit}
								</td>
								<td>
									${pagecsbl.remark}
								</td>
						</s:iterator>
					</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function panduan(obj,count,hascount){
	if(obj!=null && obj.value != ''){
		var num = parseFloat(obj.value);
		if(num>(count-hascount)){
			$(obj).val('');
			$(obj).focus();
			alert("补料数量超出了该零件的已领数量。请重新输入");
		}
	}
	
}

function check(){
	var ids =	document.getElementsByName("processIds");	
	if(ids!=null && ids.length>0){
		for(var i=0;i<ids.length;i++){
			if(ids[i].checked){
				var id = ids[i].value;
				var blnum =	$("#blNum_"+id).val();
				if(blnum==''){
					var xuhao = $("#xuhao_"+id).val();
					$("#blNum_"+id).focus();
					alert("序号:"+xuhao+"未填写补料数量。");
					return false;
				}
			}
		}
	}
}

</SCRIPT>
	</body>
</html>
