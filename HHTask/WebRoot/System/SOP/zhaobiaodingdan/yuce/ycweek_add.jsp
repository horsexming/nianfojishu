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
<STYLE type="text/css">
#msg_font b{
	color: red;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<input type="hidden" value="${ycProduct.num}" id="num"/>
			<font id="msg_font" >
				该产品下单量:<b>${ycProduct.num}</b> &nbsp;&nbsp;目前可分配量:<b>
				<s:if test="ycProduct.yfpNum!=null">
					${ycProduct.num-ycProduct.yfpNum}
				</s:if>	
				<s:else>
					${ycProduct.num}
				</s:else>
				</b>&nbsp;&nbsp;  已分配量:<b>
					<s:if test="ycProduct.yfpNum!=null">
						${ycProduct.yfpNum}
					</s:if>	
					<s:else>
						0
					</s:else>
				</b>
			</font>
			<form action="orderManager_addycWeek.action" method="post">
				<table class="table" id="mytable" style="width: 65%;">
					<s:iterator value="ycWeekList" id="pageycweek" status="statussdf">
					<tr id="tr_${statussdf.index+1}">
						<th align="right">
							第<span id="span_1">${statussdf.index+1}</span>周数量
						</th>
						<td>
							<input type="text" value="${pageycweek.fpNum}" name="ycWeekList[${statussdf.index}].fpNum" id="fpNum_${statussdf.index}"  onchange="changvalue(this)"
							onkeyup="numyanzheng(this,'zhengshu')" onblur="numyanzheng(this,'zhengshu')"/>
							<input type="hidden" value="${pageycweek.whateWeek}" name="ycWeekList[${statussdf.index}].whateWeek"  />
							<input type="hidden" value="${pageycweek.id}" name="ycWeekList[${statussdf.index}].id"  />
							<input type="button" value="追加" onclick="addlin()"/>
							<input type="button" value="删除" onclick="dellin()"/>
						</td>
							
					</tr>
					</s:iterator>
					<tr id="tr_1">
						<th align="right">
							第<span id="span_1"><s:property value="ycWeekList.size()+1"/></span>周数量
						</th>
						<td>
							<input type="text" value="" name="ycWeekList[<s:property value='ycWeekList.size()'/>].fpNum" id="fpNum_<s:property value='ycWeekList.size()'/>"  onchange="changvalue(this)"
							onkeyup="numyanzheng(this,'zhengshu')" onblur="numyanzheng(this,'zhengshu')"/>
							<input type="hidden" value="<s:property value='ycWeekList.size()+1'/>" name="ycWeekList[<s:property value='ycWeekList.size()'/>].whateWeek"  />
							<input type="button" value="追加" onclick="addlin()"/>
							<input type="button" value="删除" onclick="dellin()"/>
						</td>
							
					</tr>
				</table>
				<input type="hidden" value="${param.id}" id="" name="id"/>
				<input type="submit" value="提交" class="input" />
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if('${errorMessage}'=='true'){
		alert("预分配数量成功!");
		parent.chageDiv('none');
		parent.window.location.reload();
		
	}
})
var index = <s:property value="ycWeekList.size()+1"/>;
function addlin(){
	index++;
	$("#mytable").append('<tr id="tr_'+index+'"><th  align="right">第<span id="span_'+index+'">'+index+'</span>周数量</th>' +
	'<td><input type="text" value="" name="ycWeekList['+(index-1)+'].fpNum" id="fpNum_'+(index-1)+'" onkeyup="numyanzheng(this,&apos;zhengshu&apos;)" onchange="changvalue(this)" onblur="numyanzheng(this,&apos;zhengshu&apos;)"/>' +
	'<input type="hidden" value="'+index+'" name="ycWeekList['+(index-1)+'].whateWeek" /></td> </tr>');
}
function dellin(){
	var n = $('#mytable tr').length;
	if(index == 1){
		alert("剩最后一行了，再删就没有了");
		return;
	}
	$($('#mytable tr')[n-1]).remove();
	index--;
}

function changvalue(obj){
	var  num = $("#num").val();
	var fenfeiNum = 0;
	var num0=0;
		for(var i=0;i<index;i++){
			var obj = $("#fpNum_"+i).val();
			if(num0>0){
				obj = 0;
				continue;
			}
			if(obj!=''){
				fenfeiNum+=parseInt(obj);
				num0 = fenfeiNum-num;
				if(num0>0){
					var a = obj;
					 $("#fpNum_"+i).val(a-num0);
					fenfeiNum-=num0;
				}
			}
		}
	$("#msg_font").html("该产品下单量:<b>"+num+"</b>目前可分配量:<b> &nbsp;&nbsp;"+(num-fenfeiNum)+"</b> &nbsp;&nbsp;已分配量:<b>"+fenfeiNum+"</b>")
}
</SCRIPT>
	</body>
</html>
