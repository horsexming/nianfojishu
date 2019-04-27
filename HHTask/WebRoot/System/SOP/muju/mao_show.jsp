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
		
		
		</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="MouldApplyOrderAction_updateMoa.action" method="post"
					enctype="multipart/form-data" onsubmit="return check()" id="myform">
					<table class="table" id="mytable">
						<tr>
							<th colspan="20">
								套数:<input type="text" name="mao.num" value="${mao.num}" id="num" 
								onchange="numyanzheng(this,'zhengshu')" onblur="numyanzheng(this,'zhengshu')">
							</th>
						</tr>
						<tr>
							<th align="right">
								客户名称
							</th>
							<td>
								<input type="text" value="${mao.kehu}" name="mao.kehu" id="kehu" />
							</td>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="${mao.ywMarkId}" name="mao.ywMarkId"
									id="ywMarkId" />
							</td>
							<th align="right">
								申请日期
							</th>
							<td colspan="1">
								<input type="text" value="${mao.adddate}" name="mao.adddate"
									id="adddate" readonly="readonly" />
							</td>
							<th align="right">
								申请单号
							</th>
							<td colspan="1">
								<input type="text" value="${mao.planNumber}"
									name="mao.planNumber" id="planNumber" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								订单数量
							</th>
							<td>
								<input type="text" value="${mao.orderNum}" name="mao.orderNum"
									id="orderNum" />
							</td>
							<th align="right">
								产品交期
							</th>
							<td>
								<input type="text" value="${mao.projiaoqiTime}"
									name="mao.projiaoqiTime" id="projiaoqiTime" />
							</td>
							<th align="right">
								需求日期
							</th>
							<td colspan="1">
								<input type="text" value="${mao.xqTime}" name="mao.xqTime"
									id="xqTime" />
							</td>
							<th align="right">
								样&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板
							</th>
							<td colspan="5">
								<s:if test='mao.ishaveyangban=="无"'>
									<input type="radio" value="有" name="mao.ishaveyangban" />有
									<input type="radio" value="无" name="mao.ishaveyangban"
										checked="checked" />无
								</s:if>
								<s:else>
									<input type="radio" value="有" name="mao.ishaveyangban"
										checked="checked" />有
									<input type="radio" value="无" name="mao.ishaveyangban" />无
								</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								产&nbsp;&nbsp;品&nbsp;图
							</th>
							<td>
								<s:if test='mao.protuzi=="无"'>
									<input type="radio" value="有" name="mao.protuzi" />有
									<input type="radio" value="无" name="mao.protuzi"
										checked="checked" />无
								</s:if>
								<s:else>
									<input type="radio" value="有" name="mao.protuzi"
										checked="checked" />有
									<input type="radio" value="无" name="mao.protuzi" />无
								</s:else>
							</td>
							<th align="right">
								展&nbsp;&nbsp;开&nbsp;图
							</th>
							<td>
								<s:if test='mao.zaikaitu=="无"'>
									<input type="radio" value="有" name="mao.zaikaitu" />有
									<input type="radio" value="无" name="mao.zaikaitu"
										checked="checked" />无
								</s:if>
								<s:else>
									<input type="radio" value="有" name="mao.zaikaitu"
										checked="checked" />有
									<input type="radio" value="无" name="mao.zaikaitu" />无
								</s:else>
							</td>
							<th align="right">
								3D图
							</th>
							<td colspan="1">
								<s:if test='mao.tu3d=="无"'>
									<input type="radio" value="有" name="mao.tu3d" />有
									<input type="radio" value="无" name="mao.tu3d" checked="checked" />无
								</s:if>
								<s:else>
									<input type="radio" value="有" name="mao.tu3d" checked="checked" />有
									<input type="radio" value="无" name="mao.tu3d" />无
								</s:else>
							</td>
							<th align="right">
								申请类型
							</th>
							<td colspan="1">
								<s:if test='mao.applytype=="改旧模"'>
									<input type="radio" value="开新模" name="mao.applytype" />有
									<input type="radio" value="改旧模" name="mao.applytype"
										checked="checked" />无
								</s:if>
								<s:else>
									<input type="radio" value="开新模" name="mao.applytype"
										checked="checked" />有
									<input type="radio" value="改旧模" name="mao.applytype" />无
								</s:else>
							</td>
						</tr>
						<%--						<tr>--%>
						<%--							<th align="right">--%>
						<%--								申请类型--%>
						<%--							</th>--%>
						<%--							<td colspan="3">--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								<input type="radio" value="开新模" name="mao.applytype" checked="checked"/>开新模--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								<input type="radio" value="改旧模" name="mao.applytype" />改旧模--%>
						<%--							</td>--%>
						<%--							<th align="right">--%>
						<%--								样&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板--%>
						<%--							</th>--%>
						<%--							<td colspan="2">--%>
						<%--								<input type="radio" value="有" name="mao.ishaveyangban" checked="checked"/>有--%>
						<%--								<input type="radio" value="无" name="mao.ishaveyangban" />无--%>
						<%--							</td>--%>
						<%--							<td></td>--%>
						<%--						</tr>--%>
						<tr>
							<th>
								件号
							</th>
							<th>
								零件名称
							</th>
							<th>
								模具号
							</th>
							<th>
								单台用量
							</th>
							<th colspan="2">
								开模原因/要求说明
							</th>
							<th>
								未投产数量
							</th>
							<th>
								添加/删除
							</th>
						</tr>
						<s:iterator value="mdList" id="pagemd" status="statussdf">
							<tr id="pagemd_tr_${pagemd.id}">
								<td>
									<input type="text" value="${pagemd.markId}"
										id="markId_${statussdf.index}" name="" style="width: 100px;" />
								</td>
								<td>
									<input type="text" value="${pagemd.proName}"
										id="proName_${statussdf.index}" name="" />
								</td>
								<td>
									<input type="text" value="${pagemd.mojuNo}"
										id="mojuNo_${statussdf.index}" name="" style="width: 100px;" />
								</td>
								<td>
									<input type="text" value="${pagemd.yongliang}" name=""
										id="yongliang_${statussdf.index}"  />
								</td>
								<td colspan="2">
									<textarea rows="1" cols="30">${pagemd.yuanyin}</textarea>
<%--									<input type="text" value="" name=""--%>
<%--										id="yuanyin_${statussdf.index}" />--%>
								</td>
								<td>
									<input type="text" value="${pagemd.wtcNumber}" name="" style="width: 100px;"
										id="wtcNumber_${statussdf.index}"  />
								</td>
								<td>
									<s:if test="pageStatus=='xiugai'">
										<s:if test=" #statussdf.first">
											<input type="button" value="追加" onclick="addLine()" />
											<input type="button" value="删除" onclick="delLin()" />
										</s:if>
										<input type="button" value="删除本行"
											onclick="delmd('${pagemd.id}')" />
									</s:if>
								</td>
							</tr>
						</s:iterator>
					<s:if test="pageStatus!='caigou'">
						<tr id="lastTr">
							<th colspan="20" align="left">
								开模评估:
							</th>
						</tr>
						<s:iterator value="mpgList" id="pgmpg" status="staus_page">
							<s:if test="pageStatus =='pinggu'">
								<s:if test="#pgmpg.type == mao.status">
									<tr>
										<td valign="top" align="right" style="border-right: none;">
											<b>${pgmpg.type}:
												<input type="hidden" value="${pgmpg.type}" name="mao.mpgList[0].type"/>
											</b>
										</td>
										<td colspan="6" style="border-left: none; border-right: none;">

											<textarea rows="8" cols="120" name="mao.mpgList[0].more">${pgmpg.more}</textarea>
										</td>
										<td valign="bottom" align="center" style="border-left: none;">
											<input type="hidden" value="${pgmpg.id}"
												name="" />
											<b>评估确认:</b>
											<SELECT name=""
												onmouseover="getuser('PMC部',this,'${pgmpg.usersId}')">
												<option value="${pgmpg.usersId}">
													${pgmpg.userName}
												</option>
											</SELECT>
											<b>评估意见:</b>
											<SELECT name="mao.mpgList[0].qrstatus" id="qrstatus">
												<option value="同意">同意</option>
												<option value="不同意">不同意</option>
											</SELECT>
										</td>
									</tr>
								</s:if>
							</s:if>
							<s:else>
								<tr>
									<td valign="top" align="right" style="border-right: none;">
										<b>${pgmpg.type}:</b>
									</td>
									<td colspan="6" style="border-left: none; border-right: none;">

										<textarea rows="8" cols="120">${pgmpg.more}</textarea>
									</td>
									<td valign="bottom" align="center" style="border-left: none;">
										<input type="hidden" value="${pgmpg.id}"
											name="mao.mpgList[${staus_page.index}].id" />
										<b>评估确认:</b>
										<SELECT name="mao.mpgList[${staus_page.index}].usersId"
											onmouseover="getuser('PMC部',this,'${pgmpg.usersId}')">
											<option value="${pgmpg.usersId}">
												${pgmpg.userName}
											</option>
										</SELECT>
									</td>
								</tr>
							</s:else>
						</s:iterator>
				</s:if>
				
						<%--						<tr>--%>
						<%--							<td colspan="20">--%>
						<%--								PMC部(市场需求):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" value="市场需求评估" name="mao.mpgList[0].type" />--%>
						<%--								评估人:<SELECT name="mao.mpgList[0].usersId" onmouseover="getuser('PMC部',this)">--%>
						<%--									</SELECT>--%>
						<%--						--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								生产部(产能需求估):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" value="产能需求估" name="mao.mpgList[1].type" />--%>
						<%--								评估人:<SELECT name="mao.mpgList[1].usersId" onmouseover="getuser('生产部',this)">--%>
						<%--										<option ></option>--%>
						<%--									</SELECT>--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--								工程部(工艺实现评估):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" value="工艺实现评估" name="mao.mpgList[2].type" />--%>
						<%--								评估人:<SELECT name="mao.mpgList[2].usersId" onmouseover="getuser('工程技术部',this)">--%>
						<%--										<option></option>--%>
						<%--									</SELECT>--%>
						<%--							</td>--%>
						<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--								制作方式--%>
<%--							</th>--%>
<%--							<td colspan="20">--%>
<%--								<s:if test='mao.maketype=="自制"'>--%>
<%--									<input type="radio" value="自制" name="mao.maketype"--%>
<%--										checked="checked" /> 自制&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									<input type="radio" value="外发" name="mao.maketype" /> 外发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									<input type="radio" value="其它" name="mao.maketype" /> 其它方式--%>
<%--								</s:if>--%>
<%--								<s:elseif test='mao.maketype=="外发"'>--%>
<%--									<input type="radio" value="自制" name="mao.maketype" /> 自制&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									<input type="radio" value="外发" name="mao.maketype"--%>
<%--										checked="checked" /> 外发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									<input type="radio" value="其它" name="mao.maketype" /> 其它方式--%>
<%--								</s:elseif>--%>
<%--								<s:else>--%>
<%--									<input type="radio" value="自制" name="mao.maketype" /> 自制&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--										<input type="radio" value="外发" name="mao.maketype" /> 外发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--										<input type="radio" value="其它" name="mao.maketype"--%>
<%--										checked="checked" /> 其它方式--%>
<%--								</s:else>--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<th>
								附件
							</th>
							<td colspan="20">
								<s:iterator value="mao.projectWenJianSet" id="pagewenjian">
									<div>
										<a href="FileViewAction.action?FilePath=/upload/file/MouldApplyOrder/${pagewenjian.fileName}&Refresh=true">${pagewenjian.otherName}</a>
									</div>
								</s:iterator>
							</td>
						</tr>
					<s:if test="pageStatus=='mjys'">
						<tr>
							<th>
								模具验收
							</th>
							<td colspan="20">
								<input type="button" id="fileButton" onclick="uploadFile(this)" value="上传附件">
								<div id="fileDiv" style="display: none;">
								</div>	
								<s:iterator value="mjysList" id="mjys">
									<div>
										<a href="FileViewAction.action?FilePath=/upload/file/MouldApplyOrder/${mjys}&Refresh=true">${mjys}</a>
									</div>
								</s:iterator>
							</td>
						</tr>
					</s:if>
						<tr>
							<th>
								备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注
							</th>
							<td colspan="20">
								<textarea rows="1" cols="120" name="mao.more">${mao.more }</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="20">
								<b>申&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请:</b>${Users.name}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核:</b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;准:</b>
							</td>
						</tr>
					</table>
					<s:if test="pageStatus=='xiugai'">
						<input type="submit" value="修改" class="input" id="sub" />
					</s:if>
					<s:elseif test="pageStatus=='pinggu'">
						<input type="submit" value="确认" class="input" id="sub" />
						<input type="button" value="打回" class="input" id="sub" onclick="" />
					</s:elseif>
					<s:elseif test="pageStatus=='wanshan'">
						<input type="submit" value="完善模具号" class="input" id="sub" />
					</s:elseif>
					<s:elseif test="pageStatus=='mjys'">
						<input type="submit" value="模具验收" class="input" id="sub" />
					</s:elseif>
						<input type="hidden" value="${pageStatus}" name="pageStatus" />
						<input type="hidden" value="${mao.id}" name="mao.id" />
						<input type="hidden" value="${mao.status}" name="mao.status" />
				<s:if test="pageStatus=='caigou'">
				<br/>
					<h2 style="font-size: x-large;"> 选择供应商</h2>
				<br/>
					<table class="table" style="width: 50%;">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								
							</th>
							<th>
								供应商
							</th>
							<th>
								含税价
							</th>
							<th>
								不含税价
							</th>
						</tr>
						<s:iterator value="priceList" id="pageprice">
							<tr>
								<th>
									<input type="radio"  value="${pageprice.id}"  name="id" />
								</th>
								<td align="left">
									${pageprice.gys}
								</td>
								<td align="right">
									${pageprice.hsPrice}
								</td>
								<td align="right">
									${pageprice.bhsPrice}
								</td>
							</tr>
						</s:iterator>
					</table>
						<input type="button" value="绑定" class="input" id="sub"  onclick="caigou(this.form)">
				</s:if>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	var nowTime =	getNowFormatDate("-","");
	$("#adddate").val(nowTime);
	getMaxNo();
		
})
function getNowFormatDate(seperator1,seperator2) {
	var date = new Date();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
     var currentdate = "";
    if(seperator1!=null && seperator1!=""){
    	currentdate+=date.getFullYear() + seperator1 + month + seperator1 + strDate;
    }
    if(seperator2!=null && seperator2!=""){
    	if(currentdate.length>0){
    		currentdate+=" ";
    	}
    	currentdate+=date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    }
    return currentdate;
}
function getMaxNo(){
	$.ajax( {
		type : "POST",
		url : "MouldApplyOrderAction_findMaxNo.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#planNumber").val(data);
			}
		}
	})
}
var index =0;
function addLine(){
	var newLine = '<tr><td><input type="text" value="" name="mao.mdList['+index+'].markId" style="width: 100px;"/></td>' +
	'<td><input type="text" value="" name="mao.mdList['+index+'].proName"/></td>' +
	'<td><input type="text" value="" name="mao.mdList['+index+'].mojuNo" style="width: 100px;"/></td>' +
	'<td><input type="text" value="" name="mao.mdList['+index+'].yongliang"  /></td>' +
	'<td colspan="2"><textarea rows="1" cols="30" name="mao.mdList['+index+'].yuanyin"  id="yuanyin_0"></textarea></td>' +
	'<td><input type="text" value="" name="mao.mdList['+index+'].more" style="width: 100px;"/></td>' +
	'<td></td> </tr>';
	$("#lastTr").before(newLine);
	index++;
}
function delLin(){
	if(index<=0){
		alert("只剩最后一项了,不让删了。");
		return;
	}
	var n = $('#mytable tr').length;
	$($('#mytable tr')[n - 8]).remove();
	index--;
}
function delmd(id){
	if(confirm('确定要删除本明细吗？')){
	$.ajax( {
		type : "POST",
		url : "MouldApplyOrderAction_delmd.action",
		data : {
			'md.id':id
		},
		dataType : "json",
		success : function(data) {
			if(data=="true"){
				$("#pagemd_tr_"+id).remove();
			}
		}
	})
	}
}
function getuser(dept,obj,userId){
	$.ajax( {
		type : "POST",
		url : "DeptNumberAction!findUsersBydept.action",
		data : {
			moveType:dept
		},
		dataType : "json",
		success : function(data) {
			$(obj).empty();
			$(obj).append("<option></option>");
			if(data!=null){
				$(data).each(function(){
					if(userId ==this.id ){
						$(obj).append('<option value="'+this.id+'" selected="selected">'+this.name+'</option>');	
					}else{
						$(obj).append('<option value="'+this.id+'" >'+this.name+'</option>');	
					}
					
				})
			}
		}
	})
}

function check(){
	for(var i=0;i<index;i++){
		var markId = $("#markId_"+i).val();
		var proName = $("#proName_"+i).val();
		var mojuNo = $("#mojuNo_"+i).val();
		var yongliang = $("#yongliang_"+i).val();
		if(markId == ''){
			alert('第'+(i+1)+'行开模明细未填写开模件号。')
			$("#sub").removeAttr('disabled')
			$("#markId_"+i).focus();
			return false;
		}else if(proName == ''){
			alert('第'+(i+1)+'行开模明细未填写零件名称。')
			$("#sub").removeAttr('disabled')
			$("#proName_"+i).focus();
			return false;
		}else if(mojuNo == ''){
			alert('第'+(i+1)+'行开模明细未填写模具号。')
			$("#sub").removeAttr('disabled')
			$("#mojuNo_"+i).focus();
			return false;
		}else if(yongliang == ''){
			alert('第'+(i+1)+'行开模明细未填写单台用量。')
			$("#sub").removeAttr('disabled')
			$("#yongliang_"+i).focus();
			return false;
		}
	}
	var attachments = document.getElementsByName('attachment');
	if(attachments!=null && attachments.length>0){
		for(var i=0;i<attachments.length;i++){
			if(attachments[i].value ==''){
				alert('第'+(i+1)+'行开模明细未上传附件,请上传附件')
				$(attachments[i]).focus();
				$("#sub").removeAttr('disabled')
				return false;
			}
		}
	}
	$("#sub").attr('disabled','disabled');
	return true;
}
function caigou(obj){
	$(obj).attr('action','MouldApplyOrderAction_caigou.action');
	var num = $("#num").val();
	var ids = document.getElementsByName("id");
	if(num == ''){
		alert('请填写套数！')
		 $("#num").focus();
		$("#sub").removeAttr('disabled')
		return false;
	}
	var bool = true;
	for(var i=0;i<ids.length;i++){
		if(ids[i].checked){
			bool = false;
			break;
		}
	}
	if(bool){
		alert('请填选择供应商')
		$("#sub").removeAttr('disabled')
		return false;
	}
	$("#sub").attr("disabled","disabled");
	$(obj).submit();
}

// function onsubmit(){
// 	$("#qrstatus").val("不同意");
// 	$("#myform").submit();
// }
var fileDivHTML = "";
var count = 0;
function uploadFile(obj) {
	var fileDiv = document.getElementById("fileDiv");
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + ")'> 删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton").value = "上传附件";
		document.getElementById("fileDiv").style.display = "none";
	}
}
$(function(){
	if('${pageStatus}' == 'mjys'){
		$("input[type='text']").each(function () {
    		$(this).attr("readonly","readonly");
	});
		$("input[type='radio']").each(function () {
    		$(this).attr("disabled","disabled");
	});
		$("textarea").each(function () {
    		$(this).attr("readonly","readonly");
	});
	}
})
</SCRIPT>
	</body>
</html>
