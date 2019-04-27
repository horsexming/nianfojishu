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
				指派
				<table class="table" id="zptable">
					<tr>
						<td align="center">
							类型
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							人员
						</td>
						<td align="center">
							预完成时间
						</td>
						<td align="center">
							操作
							<input type="button" value="增加" onclick="addline()">
						</td>
					</tr>
					<s:if test="qpjyUserList!=null&&qpjyUserList.size()>0">
						<s:iterator value="qpjyUserList" id="pagejyUser"
							status="jyuserStatus">
							<tr id="zptr${jyuserStatus.index}">
								<td align="left">
									${pagejyUser.userType}
									<input id="hidId${jyuserStatus.index}" type="hidden"
										value="${pagejyUser.id}">
								</td>
								<td align="left">
									${pagejyUser.userDept}
								</td>
								<td align="left">
									${pagejyUser.userName}
								</td>
								<td align="left">
									${pagejyUser.yqTime}
								</td>
								<td>
									<input type="button" value="取消"
										onclick="quxiaozp(${jyuserStatus.index})" />
									<img id="newOk${jyuserStatus.index}" alt=""
										src="<%=basePath%>/images/success.jpg" align="middle">
								</td>
							</tr>
						</s:iterator>
						<tr id="zptr<s:property value="qpjyUserList.size()"/>">
							<td align="center">
								<SELECT id="type<s:property value="qpjyUserList.size()"/>">
									<option value="1">
										执行
									</option>
									<option value="2">
										协作
									</option>
								</SELECT>
								<input id="hidId<s:property value="qpjyUserList.size()"/>"
									type="hidden" value="0">
							</td>
							<td id="depttd<s:property value="qpjyUserList.size()"/>"
								align="center">
								<SELECT id="dept<s:property value="qpjyUserList.size()"/>"
									onchange="selectdept(<s:property value="qpjyUserList.size()"/>)">
								</SELECT>
							</td>
							<td id="usertd<s:property value="qpjyUserList.size()"/>"
								align="center">
								<SELECT id="user<s:property value="qpjyUserList.size()"/>">
								</SELECT>
							</td>
							<td align="center">
								<input id="time<s:property value="qpjyUserList.size()"/>"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td align="center">
								<input id="zp<s:property value="qpjyUserList.size()"/>"
									type="button" value="指派"
									onclick="zp(<s:property value="qpjyUserList.size()"/>)" />
								<input id="quxiao<s:property value="qpjyUserList.size()"/>"
									type="button" value="取消"
									onclick="quxiaozp(<s:property value="qpjyUserList.size()"/>)" />
								<img id="newOk<s:property value="qpjyUserList.size()"/>" alt=""
									style="display: none;" src="<%=basePath%>/images/success.jpg"
									align="middle">
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr id="zptr0">
							<td align="center">
								<SELECT id="type0">
									<option value="1">
										执行
									</option>
									<option value="2">
										协作
									</option>
								</SELECT>
								<input id="hidId0" type="hidden" value="0">
							</td>
							<td id="depttd0" align="center">
								<SELECT id="dept0" onchange="selectdept(0)">
								</SELECT>
							</td>
							<td id="usertd0" align="center">
								<SELECT id="user0">
									<option>
										请先选择部门
									</option>
								</SELECT>
							</td>
							<td align="center">
								<input id="time0" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td align="center">
								<input id="zp0" type="button" value="指派" onclick="zp(0)" />
								<input id="quxiao0" type="button" value="取消"
									onclick="quxiaozp(0)" />
								<img id="newOk0" alt="" style="display: none;"
									src="<%=basePath%>/images/success.jpg" align="middle" />
							</td>
						</tr>
					</s:else>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var zpsize=1;
var zpindex=0;
var depts;
$(document).ready(function() {
	var hadsize="<s:property value="qpjyUserList.size()"/>";
	if(hadsize==null||hadsize==""){
		hadsize=0;
	}else{
		zpsize =zpsize+(hadsize-0);
		zpindex =zpindex+(hadsize-0);
	}
	//查询所有的部门
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
		depts=allDdept;
		setDept(zpindex);
		}

	});
});

function setDept(index){
	$("<option value='0'>请选择部门</option>").appendTo("#dept"+index);
	$(depts).each(function(){
		$( "<option value='" + this.id + "'>" + this.dept + "</option>") .appendTo("#dept" + index);
	});
	var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("depttd"+index).removeChild(
									tinyselect[1]);
						}
						$("#dept"+index).tinyselect();
}
function selectdept(index){
	var deptId = $("#dept" + index).val();
	$("#user"+index).empty();
	if(deptId==0){
		$("<option value='0'>请先选择部门</option>").appendTo("#user"+index);
	}else{
		$.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					data : {
						id : deptId
					},
					dataType : "json",
					success : function(data) {
							$("<option value='0'>请选择人员</option>").appendTo(
									"#user"+index);
						$(data).each( function() { $( "<option value='" + this.id + "'>" + this.name + "</option>")
											.appendTo("#user"+index);
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("usertd"+index).removeChild(
									tinyselect[1]);
						}
						$("#user"+index).tinyselect();
					}
				});
	}
}

function zp(index){
	var type=$("#type"+index).val();
	var user=$("#user"+index).val();
	var time=$("#time"+index).val();
	$.ajax( {
		url : 'QuotedPrice_jydispatche.action',
		dataType : 'json',
		type : "post",
		data : {
					'qpjyUser.jydId' : ${id},
					'qpjyUser.userType' : type,
					'qpjyUser.userId' : user,
					'qpjyUser.yqTime' : time
					},
		cache : false,//防止数据缓存
		success : function(data) {
					if(data.message=="成功"){
						$("#zp"+index).hide();
						$("#quxiao"+index).show();
						$("#newOk"+index).show();
						$("#hidId"+index).val(data.data);
					}else{
						alert(data.message);
					}
		}

	});
}

function addline(){
	zpsize++;
	zpindex++;
	var html = "<tr id='zptr"+zpindex+"'>" +
	"<td align='center'><SELECT id='type"+zpindex+"'><option>执行</option><option>协作</option><input id='hidId"+zpindex+"' type='hidden' value='0'> </SELECT></td> "+
	"<td id='depttd"+zpindex+"' align='center'><SELECT id='dept"+zpindex+"' onchange='selectdept("+zpindex+")'></SELECT></td>" +
	"<td id='usertd"+zpindex+"' align='center'><SELECT id='user"+zpindex+"'><option value='0'>请先选择部门</option></SELECT></td>" +
	"<td align='center'><input id='time"+zpindex+"' class='Wdate' onClick='WdatePicker({dateFmt:\"yyyy-MM-dd\",skin:\"whyGreen\"})'></td>" +
	"<td align='center'><input id='zp"+zpindex+"' type='button' value='指派' onclick='zp("+zpindex+")'/>" +
	"<input id='quxiao"+zpindex+"'  type='button' value='取消' onclick='quxiaozp("+zpindex+")'/>" +
	"<img id='newOk"+zpindex+"' alt='' style='display: none;' src='<%=basePath%>/images/success.jpg' align='middle'/></td>" +
	"</tr>";
	$("#zptable").append(html);
	setDept(zpindex);
}

function quxiaozp(index){
	if(confirm("确定取消?")){
		var hidId=$("#hidId"+index).val();
		if(hidId==null||hidId==0){
			zpsize--;
			zpindex--;
			$("#zptr"+index).remove();
		}else{
			$.ajax( {
			url : 'QuotedPrice_jydispatcheremove.action',
			dataType : 'json',
			data : {
						id : hidId,
						},
			cache : false,//防止数据缓存
			success : function(data) {
						if(data=="成功"){
							zpsize--;
							zpindex--;
							$("#zptr"+index).remove();
						}else{
							alert(data);
						}
			}
	
		});
	}
	}
}
</SCRIPT>
	</body>
</html>
