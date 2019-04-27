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
				<form action="ProcardAction!tocsbl.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input value="${procard.markId}" name="procard.markId" id="">
							</td>
							<th align="right">
								名称
							</th>
							<td>
								<input value="${procard.proName}" name="procard.proName" id="">
							</td>
						</tr>
					</table>
					<input type="hidden" value="${id}" name="id"/>
					<input type="submit"  value="查询" class="input"/>
				</form>
				<form action="ProcardAction!csbl.action" method="POST" onsubmit="return check()" >
					<input type="hidden" value="${id}" name="id"/>
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th>
<%--								<input type="checkbox" onclick="chageAllCheck(this)"/>--%>
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
								需求数量
							</th>
							<th>
								未领数量
							</th>
							<th>
								完成数量
							</th>
							<th id="blNum_th">
								补料数量
							</th>
							<th >
								备注
							</th>
							<th>
								单位
							</th>
						</tr>
						<s:iterator id="pageProcard" value="procardList"
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
									<input type="hidden" value="${statussdf.index+1}" id="xuhao_${pageProcard.id}" />
								</td>
								<td>
									<input type="checkbox" value="${pageProcard.id}"  name="processIds" onclick="onclickCheckbox(this,'${pageProcard.id}')" />
								</td>
								<td align="left">
									${pageProcard.procardStyle}
								</td>
								<td align="left">
									${pageProcard.markId}
								</td>
								<td style="width: 180px;" align="left">
									${pageProcard.proName}
								</td>
								<td style="width: 180px;" align="left">
									${pageProcard.specification}
								</td>
								<td align="left" >
									${pageProcard.kgliao}
								</td>
								<td align="right">
									${pageProcard.filnalCount}
								</td>
								<td align="right">
									${pageProcard.hascount}
								</td>
								<td align="right">
									${pageProcard.tjNumber}
								</td>
								<td align="left">
										<input type="text" value="" name="blNums" disabled="disabled" style="width: 75px;"
									 id="blNum_${pageProcard.id}" onchange="numyanzheng(this);panduan(this,${pageProcard.filnalCount},
									 ${pageProcard.hascount},'${pageProcard.procardStyle}',${pageProcard.tjNumber})">
								</td>
								<td>
									<input type="text" value="" name="processCards" disabled="disabled" id="remark_${pageProcard.id}"/>
								</td>
								<td align="left">
									${pageProcard.unit}
								</td>
						</s:iterator>
					</table>
					<input type="submit" class="input"  value="提交" onclick="todisabled(this)" id="sub"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function panduan(obj,count,hascount,procardStyle,tjNumber){
	if(obj!=null && obj.value != ''){
		var num = parseFloat(obj.value);
		if(procardStyle == '外购'){
			if(num>(count-hascount)){
				$(obj).val('');
				$(obj).focus();
				alert("补料数量超出了该零件的已领料数量。请重新输入");
			}
		}else{
			if(num>tjNumber){
				$(obj).val('');
				$(obj).focus();
				alert("补料数量超出了该自制件的完成数量。请重新输入");
			}
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
					$("#sub").removeAttr("disabled")
					$("#blNum_th").html("补料数量");
					return false;
				}else{
					$("#blNum_th").append('<input type="hidden" value='+blnum+' name= "processNumbers"/>');
				}
			}
		}
	}
}
function onclickCheckbox(obj,id){
	if(obj!=null){
		if(obj.checked){
			$("#blNum_"+id).removeAttr("disabled");
			$("#remark_"+id).removeAttr("disabled");
		}else{
			$("#blNum_"+id).val("");
			$("#blNum_"+id).attr("disabled","disabled");
			$("#remark_"+id).val("");
			$("#remark_"+id).attr("disabled","disabled");
		}
	}
}
</SCRIPT>
	</body>
</html>
