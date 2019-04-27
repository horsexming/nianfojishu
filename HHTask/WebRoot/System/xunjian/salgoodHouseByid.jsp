<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<form action="GoodsStoreAction!updategoodHouse.action" method="post"  onsubmit="return check()">
			<table align="center" class="table">
				<tr>
					<th colspan="3">
						<font size="5">修改仓区</font>
					</th>
				</tr>
				<tr>
					<th align="right">
							所属仓库:
					</th>
					<td colspan="2">
						<select id="wareHouseName"  name="goodHouse.goodsStoreWarehouse" onchange="change1()" >
							<option value="${goodHouse.goodsStoreWarehouse}">
									${goodHouse.goodsStoreWarehouse}
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right" width="300">
						区名: 
					</th>
					<td width="300">
						<input name="goodHouse.GoodHouseName"
							value="${goodHouse.goodHouseName}" id="cangqu"  onchange="change1()"/>
					</td>
					
					<td id="cangqu_Exist" width="300" style="color: red;">
						
					</td>
					
				</tr>
				
				<tr>
					<th align="right">
						编号:
					</th>
					<td >
						<input name="goodHouse.GoodHouseNum" value="${goodHouse.goodHouseNum}" readonly style="color: #ccc;"/>
					</td>
					<td ></td>
				</tr>
				
				<tr>
					<th align="right">
						仓区总面积(m2):
					</th>
					<td><!--
						 -->
						<input type="text" name="goodHouse.goodAllArea"  value="${goodHouse.goodAllArea}" id="all0" onchange="allChange()" />
					</td>
					<td id="cangqu_all" width="300" style="color: red;"></td>
				</tr>
				<tr>
					<th align="right">
						仓区已用面积(m2):
					</th>
					<td >
						<input type="text" name="goodHouse.goodIsUsedArea" value="${goodHouse.goodIsUsedArea}" id="use0" />
					</td>
					<td id="cangqu_use" width="300" style="color: red;"></td>
				</tr>
				

				<tr>
					<td align="center" colspan="4">
						<input type="hidden" name="goodHouse.id"
							value="${goodHouse.id}" />
						<input type="submit" value="修改" class="input" id="sub" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})

$(function(){
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_getAllwarehouse.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			
			if (data != null) {
				$(data).each(function(){
						$("#wareHouseName").append('<option value='+this.name+'>'+this.name+'</option>');
					});
				$("#wareHouseName").tinyselect();
			}
		}
	})
})

var cangquFlag=true,allFlag=true,useFlag=true;

var wareName,goodName;
$(function(){
	wareNameInit=$("#wareHouseName").val().trim();
	goodNameInit=$("#cangqu").val().trim();
});

//仓库名称，仓区名称改变
function change1(){
	$("#cangqu_Exist").html("");
	var wareHouseName=$("#wareHouseName").val().trim();
	var cangqu=$("#cangqu").val().trim();
	
	if(wareNameInit!=wareHouseName ||  goodNameInit!=cangqu){
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_ajaxIsExsitHouseName.action",
			data : {
				wareHouseName:wareHouseName,
				cangqu:cangqu
			},
			dataType : "json",
			success : function(data) {
				
				if(!data){
					$("#cangqu_Exist").html("存在，请重输");
					$(this).focus();
					cangquFlag=false;
					$("#sub").attr("disabled","disabled");
				}else{
					$("#cangqu_Exist").html("不存在，可以录入");
					cangquFlag=true;
					$("#sub").removeAttr("disabled");
				}	
			}
		});
	}else if(wareNameInit==wareHouseName ||  goodNameInit==cangqu){
		$("#sub").removeAttr("disabled");
	}
	
	 return  cangquFlag;
	
}

//检验总面积
function allChange(){
	var all=$("#all0").val().trim();
	var allObj=document.getElementById("all0");
	if(all.length==0){
		$("#cangqu_all").html("仓区总面积不能为空");
		$(this).focus();
		return  false;
	}else{
		return allFlag=numyanzheng(allObj,null);
		
	}
	
}
//检验已用
function useChange(){
	var all=document.getElementById("all0").value.trim();
	var use=document.getElementById("use0").value.trim();
	
	var useObj=document.getElementById("use0");
	var flag1=numyanzheng(useObj,null);
	if(use<0){
		$("#use0").val("0");
		$("#use0").focus();
		$("#cangqu_use").html("已用面积不能为负数");
		return false;
	}else if(use>all){
		$("#use0").val("0");
		$("#use0").focus(); 
		$("#cangqu_use").html("已用面积不能大于总面积");                                                                                                                    
		return false;
	}
	return true;
}

function check(){
	return allChange()&&useChange();
}
</script>
</html>
