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
				<div align="center">
				<form id="form1" action="ProcardAction!toMaterialsNotice.action" method="post">
					<table class="table">
						<tr>
						<th align="center">总成件号:</th>
						<td align="center"><input id="markId" name="markId" value="${markId}"/> </td>
						<th align="center">批次:</th>
						<td align="center"><input id="selfCard" name="selfCard" value="${selfCard}"/> </td>
						<th align="center">数量:</th>
						<td align="center"><input id="num" name="num" value="${num}"/> </td>
						<td align="center"><input type="submit" value="提交" onclick="getSonMaterial()"/> </td>
						</tr>
					</table>
				</form>
				</div>
				<br/>
				<div align="center" >
				<s:if test="procardList!=null&&procardList.size()>0">
				<form id="form2" method="post" action="ProcardAction!saveSonMaterial.action" >
				<input type="hidden" id="markId2" name="pmHead.markId" value="${markId}"/>
				<input type="hidden" id="selfCard2" name="pmHead.selfCard" value="${selfCard}"/>
				<input type="hidden" id="num2" name="pmHead.thisCount" value="${num}"/>
				 <table id="addtable" class="table" >
				 	<tr>
				 		<th>序号</th>
				 		<th>件号</th>
				 		<th>类型</th>
				 		<th>名称</th>
				 		<th>批次</th>
				 		<th>数量</th>
				 		<th>单位</th>
				 		<th>子件号</th>
				 		<th>子牌号</th>
				 		<th>子规格</th>
				 		<th>子名称</th>
				 		<th>子数量</th>
				 		<th>子单位</th>
				 	</tr>
				 	<s:iterator value="procardList" id="pageProcard" status="pageStatus1">
				 	
					  <s:if test="#pageProcard.procardMateriallist==null">
					  <s:if test="#pageStatus1.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus1.index+1" />
							</td>
							<td>
								${pageProcard.markId}
							</td>
							<td>
								${pageProcard.procardStyle}
							</td>
							<td>
								${pageProcard.proName}
							</td>
							<td>
								${pageProcard.selfCard}
							</td>
							<td>
								${pageProcard.thisAlertCount}
							</td>
							<td>
								${pageProcard.unit}
							</td>
							<td colspan="6">
							</td>
							</tr>
					  </s:if>
					  
					  <s:iterator value="#pageProcard.procardMateriallist" id="pageMaterial" status="pageStatus2">
					  <s:if test="#pageStatus1.index%2==1&#pageStatus2.index==0">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<s:if test="#pageStatus2.index==0">
							<td rowspan="<s:property value="#pageProcard.procardMateriallist.size()"/>">
								<s:property value="#pageStatus1.index+1"/>
							</td>
							<td rowspan="<s:property value="#pageProcard.procardMateriallist.size()"/>">
								${pageProcard.markId}
							</td>
							<td rowspan="<s:property value="#pageProcard.procardMateriallist.size()"/>">
								${pageProcard.procardStyle}
							</td>
							<td rowspan="<s:property value="#pageProcard.procardMateriallist.size()"/>">
								${pageProcard.proName}
							</td>
							<td rowspan="<s:property value="#pageProcard.procardMateriallist.size()"/>">
								${pageProcard.selfCard}
							</td>
							<td rowspan="<s:property value="#pageProcard.procardMateriallist.size()"/>">
								<!-- 零件id -->
								<input type="hidden" id="fId<s:property value="#pageStatus1.index"/>" name="procardList[<s:property value="#pageStatus1.index"/>].id" value="${pageProcard.id}"/>
								<!-- 其下材料长度 -->
								<input type="hidden" id="sonListSize<s:property value="#pageStatus1.index"/>"  value="<s:property value="#pageProcard.procardMateriallist.size()"/>"/>
								<!-- 原来的数量 -->
								<input type="hidden" id="oldvalue<s:property value="#pageStatus1.index"/>"  value="<s:property value="#pageProcard.thisAlertCount"/>"/>
								<!-- 数量 -->
								<input id="newvalue<s:property value="#pageStatus1.index"/>" name="procardList[<s:property value="#pageStatus1.index"/>].thisAlertCount" value="${pageProcard.thisAlertCount}"
								onkeyup="changeSonCount(<s:property value="#pageStatus1.index"/>)"/>
							</td>
							<td rowspan="<s:property value="#pageProcard.procardMateriallist.size()"/>">
								${pageProcard.unit}
							</td>
							</s:if>
							<td>
							${pageMaterial.markId}
							</td>
							<td>
							${pageMaterial.trademark}
							</td>
							<td>
							<s:if test="#pageMaterial.specification=='在制品'||#pageMaterial.specification=='原材料在制品'">
							<font color="red">${pageMaterial.specification}</font>
							</s:if>
							<s:else>
							${pageMaterial.specification}
							</s:else>
							</td>
							<td>
							${pageMaterial.name}
							</td>
							<td>
							<s:if test="#pageMaterial.specification=='在制品'">
							<label id="sonCount_<s:property value="#pageStatus1.index"/>_<s:property value="#pageStatus2.index"/>">
								${pageMaterial.wlZaiCount}
							</label>
							<label style="display: none" id="zaiziCount<s:property value="#pageStatus1.index"/>">
								${pageMaterial.zaiCount}
							</label>
							</s:if>
							<s:elseif test="#pageMaterial.specification=='原材料在制品'">
							<label id="sonCount_<s:property value="#pageStatus1.index"/>_<s:property value="#pageStatus2.index"/>">
								${pageMaterial.wlYzaiCount}
							</label>
							<label style="display: none" id="yzaiziCount<s:property value="#pageStatus1.index"/>">
								${pageMaterial.yzaiCount}
							</label>
							</s:elseif>
							<s:else>
							<label id="sonCount_<s:property value="#pageStatus1.index"/>_<s:property value="#pageStatus2.index"/>">
								${pageMaterial.thecount}
							</label>
							<label style="display: none" id="bili_<s:property value="#pageStatus1.index"/>_<s:property value="#pageStatus2.index"/>">
								${pageMaterial.bili}
							</label>
							</s:else>
							<label style="display: none" id="type_<s:property value="#pageStatus1.index"/>_<s:property value="#pageStatus2.index"/>">${pageMaterial.type}</label>
							</td>
							<td>
							${pageMaterial.unit}
							</td>
							</tr>
					    
					  </s:iterator>
					</s:iterator>
					<tr>
					<td align="left" colspan="2"> 领料人：
					</td>
					<td align="center" colspan="5">
							<select id="deptname" style="width: 100px;" 
								onchange="userlist(0)">
									<option value="-1">
										请选择部门
									</option>
							</select>

						</td>
						<td align="center" id="userstd" colspan="6">
								<select id="username" name="pmHead.receiverId" style="width: 100px;">
									<option value="0">
										请先选择部门
									</option>
								</select>
						</td>
					<tr>
					</tr>
					<tr>
						<td colspan="13" align="center"><input  type="submit" value="提交" style="width: 80px;height: 30px;"> </td>
					</tr>
				 </table>
				</form>
				</s:if>
				<s:else>
				 <font color="red"><h3>没有数据!</h3></font>
				</s:else>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function changeSonCount(index){
			if(!mustBeNumber("newvalue"+index)){
				$("#newvalue"+index).val($("#oldvalue"+index).val());
			}
			var num=$("#newvalue"+index).val();
			//在制品数量
			var zaiziCount = $("#zaiziCount"+index).html();
			//原材料在制品数量
			var yzaiziCount = $("#yzaiziCount"+index).html();
			if(zaiziCount==null||zaiziCount==""){
				zaiziCount=0;
			}
			if(yzaiziCount==null||yzaiziCount==""){
				yzaiziCount=0;
			}
			num = num-zaiziCount;
			if(num<0){
				num=0;
			}
			var ynum = num-yzaiziCount;
			if(ynum<0){
				ynum=0;
			}
			var sonListSize=$("#sonListSize"+index).val();
			if(sonListSize>0){
				for(var i=0;i<sonListSize;i++){
					var type = $("#type_"+index+"_"+i).html();
					if(type=="外购件"){
						var bili=$("#bili_"+index+"_"+i).html();
						$("#sonCount_"+index+"_"+i).html(bili*num);
					}else if(type=="原材料"){
						var bili=$("#bili_"+index+"_"+i).html();
						$("#sonCount_"+index+"_"+i).html(bili*ynum);
					}
				}
			}
			
		}
		
		
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
		$("<option value='" + 0 + "'>本批次人员</option>").appendTo("#deptname");
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
						//userlist($("#deptname").val());
					});
			$("#deptname").tinyselect();
		}
	});
});
function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var deptid = $("#deptname").val();
	if (deptid == "-1") {
		$("#username").empty();
		$("<option value='-1'>请先选择部门</option>").appendTo("#username");
		var tinyselect = $(".tinyselect");
		if (tinyselect[1] != null) {
			document.getElementById("userstd").removeChild(tinyselect[1]);
		}
		$("#username").tinyselect();
	} else {
		$
				.ajax( {
					type : "post",
					url : "ProcardAction!getusers.action",
					data : {
						id : deptid,
						markId : "${markId}" ,
						selfCard : "${selfCard}"
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
											"<option value='" + this.id + "'>"
													+ this.code + "__"
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
		</SCRIPT>
	</body>
</html>
