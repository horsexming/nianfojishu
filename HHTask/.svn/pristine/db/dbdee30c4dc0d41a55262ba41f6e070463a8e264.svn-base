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
				<table style="width: 50%;">
					<tr>
						<th align="right">
							部门:
						</th>
						<td>
							<select id="deptname" style="width: 100px;"
								onchange="userlist(0)">
								<s:if test="user!=null">
									<option value="${deptId}_${user.dept}">
										${user.dept}
									</option>
								</s:if>
								<s:else>
									<option value="0">
										请选择部门
									</option>
								</s:else>
							</select>
						</td>
						<th align="right">
							名字:
						</th>
						<td id="userstd">
							<select id="username" style="width: 100px;"
								onchange="changvalue(this)">
								<s:if test="user!=null">
									<option value="${user.name}">
										${user.name}
									</option>
								</s:if>
								<s:else>
									<option value="0">
										请先选择部门
									</option>
								</s:else>
							</select>
							<input type="hidden" value="${user.id}" id="userId" name="id" />
						</td>
					</tr>
				</table>
				<font id="zifont" color="red" size="5"></font>
				<form action="ProdEquipmentAction!findAll.action" method="post">
					<table>
						<tr>
							<th>
								工位:
							</th>
							<td>
								<input type="text" name="machine.workPosition" />
							</td>
							<td>
								<input type="hidden" value="${user.id}" name="id" />
								<input type="hidden" value="${pageStatus}" name="pageStatus" />
								<input type="hidden" value="chaxun" name="tag">
								<input type="submit" value="查询"
									style="width: 75px; height: 35px;">
							</td>
						</tr>
					</table>
				</form>
				<div id="ybd_div" style="display: none;" align="center">
					<h2 id="username1">
						${user.name}已绑定的工位设备
					</h2>
					<a href="javascript:;" onclick="changdiv('wbdgw')">绑定工位设备</a>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px" align="center">
							<th>
								序号
							</th>
							<th>
								工位
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:iterator value="machineList" id="pagemachine"
							status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pagemachine.workPosition}
							</td>
							<td>
								<a
									href="ProdEquipmentAction!Usersjcbdgw.action?arrayId=${pagemachine.id}&pageStatus=ybdgw&id=${user.id}&cpage=${cpage}">解绑</a>
							</td>
						</s:iterator>
						<tr>
							<td colspan="6" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</table>
				</div>
				<div id="wbd_div" style="display: none;" align="center">
					<h2 id="username2">
						${user.name}的未绑定的工位设备
					</h2>
					<a href="javascript:;" onclick="changdiv('ybdgw')"> 已绑定的工委设备</a>
					<form action="ProdEquipmentAction!Usersbdgw.action" method="post"
						onsubmit="return yanzheng()">
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px" align="center">
								<th>
									<input type="checkbox" onclick="checkAll(this)" id="all"
										value="0" />
									全选
								</th>
								<th>
									序号
								</th>
								<th>
									工位
								</th>
							</tr>
							<s:iterator value="machineList" id="pagemachine2" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')" onclick="check(this)">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')" onclick="check(this)">
								</s:else>
								<td>
									<input type="checkbox" name="arrayId" value="${pagemachine2.id}" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td>
									${pagemachine2.workPosition}
								</td>
							</s:iterator>
							<tr>
								<td colspan="7" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

								</td>
							</tr>
							<tr>
								<td colspan="7">
									<input type="hidden" value="wbdgw" name="pageStatus" />
<%--									<input type="hidden" value="${cpage}" name="cpage" />--%>
									<input type="hidden" value="${user.id}" name="id" />
									<input type="submit" value="绑定"
										style="width: 75px; height: 35px;" id="sub" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="bd_div" style="" align="center">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px" align="center">
							<th>
								序号
							</th>
							<th>
								工位
							</th>
						</tr>
						<s:iterator value="machineList" id="pagemachine3" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pagemachine3.workPosition}
							</td>
						</s:iterator>
						<tr>
							<td colspan="6" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</table>
				</div>

			</div>
			<%@include file="/util/foot.jsp"%>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "DeptNumberAction!findAllDept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id+"_"+this.dept + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
						//userlist($("#deptname").val());
					});
			$("#deptname").tinyselect();
		}
	});
});
function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var obj = $("#deptname").val();
		obj	=	obj.split("_");
		var deptid = 0;
		var deptname = "";
		if(obj!=null && obj.length == 2){
			deptid = obj[0];
			$("#dept").val(obj[1]);
		}
	if (deptid == "0") {
		$("#username").empty();
			if("${user.name}!=null && ${user.name}!=''"){
			$("<option value='${user.name}'>${user.name}</option>").appendTo("#username");
		}else{
			$("<option value='0'>请先选择部门</option>").appendTo("#username");
		}
		$("#username").tinyselect();
	} else {
		$
				.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					data : {
						id : deptid
					},
					dataType : "json",
					success : function(data) {
						if (flag == 0) {
							$("#username").empty();
							$("<option value='0'>请选择人员</option>").appendTo(
									"#username");

						}
						$(data).each(
								function() {
									
											$(
											"<option value='" + this.id +"_"+this.name+ "'>"
													+ this.name + "</option>")
											.appendTo("#username");
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("userstd").removeChild(
									tinyselect[1]);
						}
						$("#username").tinyselect();

					}
				});
	}

}
$(document).ready(function() {
	userlist(1);
});
	
function changvalue(obj){
	if(obj!=null && obj.value!="" && obj.value!="0"){
		
		var v = obj.value;
		var array = v.split("_");
		var id = array[0];
		if(array.length == 2){
			$("#userId").val(array[0]);
			$("#username1").html(array[1]+"已绑定的工位设备");
			$("#username2").html(array[1]+"未绑定的工位设备");
			window.location.href ="ProdEquipmentAction!findAll.action?pageStatus=ybdgw&id="+id+"&tag=nocx"		;
		}
		
	}
}

function changdiv(pageStatus){
	var id = $("#userId").val();
	window.location.href ="ProdEquipmentAction!findAll.action?pageStatus="+pageStatus+"&id="+id+"&tag=nocx"	;
}
var pageStatus = "${pageStatus}"
$(function(){
	if(pageStatus == "ybdgw"){
		$("#bd_div").hide();
		$("#wbd_div").hide();
		$("#ybd_div").show();
	}else if(pageStatus == "wbdgw"){
		$("#ybd_div").hide();
		$("#bd_div").hide();
		$("#wbd_div").show();
	}  
	
})
var bool = true ;
function checkAll(obj){
	var chexkboxs = document.getElementsByName("arrayId"); 
	if(chexkboxs!=null && chexkboxs.length>0){
		if(obj!=null && obj.checked==true){
			for(var i=0;i<chexkboxs.length;i++){
				chexkboxs[i].checked=true;
			}
		}else{
			for(var i=0;i<chexkboxs.length;i++){
				chexkboxs[i].checked=false;
			}
		}
	}

	bool = false;
}

function check(obj){
	var a =	$(obj).find("input").attr("id");
	 if($(obj).find("input").attr("checked") == "checked" ){
		 $(obj).find("input").removeAttr("checked");
	 }else{
		 $(obj).find("input").attr("checked","checked");
	 }
	 if(a == "all"){
		var obj =	document.getElementById(a);
		checkAll(obj);
	 }
	
}

function yanzheng(){
	var chexkboxs = document.getElementsByName("arrayId");
	var bool = true;
	if(chexkboxs!=null && chexkboxs.length>0){
		for(var i=0;i<chexkboxs.length;i++){
			if(chexkboxs[i].checked == true){
				bool = false;
				break;
			}
		}
	}
	
	if(bool){
		$("#zifont").html("请至少选择一个工位设备");
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return  true;
}
</SCRIPT>
	</body>
</html>
