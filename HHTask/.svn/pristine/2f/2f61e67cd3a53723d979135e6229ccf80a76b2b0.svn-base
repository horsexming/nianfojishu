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
		if(obj!=null && obj.length>1){
			deptid = obj[0];
			deptname = obj[1];
		}
		$("#ck_dept").val(deptname);
	if (deptid == "0") {
		$("#username").empty();
		$("<option value='0'>请先选择部门</option>").appendTo("#username");
		var tinyselect = $(".tinyselect");
		if (tinyselect[1] != null) {
			document.getElementById("userstd").removeChild(tinyselect[1]);
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
											"<option value='" + this.name + "'>"
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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="CareertrackAction_findckList.action" method="post">
					<table>
						<tr>
							<th>
								工号:
							</th>
							<td align="center" id="userstd">
								<input type="text" name="ck.code" style="width: 200px;height: 34px;"/>
							</td>
							<th>
								姓名:
							</th>
							<td align="center" id="userstd">
								<input type="text" name="ck.userName" style="width: 200px;height: 34px;"/>
							</td>
							<td rowspan="2" id="sub_td"> 
								<input type="hidden" value="${statue}" name="statue"/>
								<input type="submit" value="查询" style="width: 75px;height: 70px;" id="sub"/>
<%--								<a href="javascript:;" onclick="gengxin()">更新</a>--%>
							</td>
						</tr>
					
						<tr id="dept_tr" style="display: none;">
							<th>
								部门:
							</th>
							<td align="center">
								<select id="deptname" style="width: 100px;"
									onchange="userlist(0)">
									<s:if test="users==null">
										<option value="0">
											请选择部门
										</option>
									</s:if>
									<s:else>
										<option value="<s:property value="users.deptId"/>">
											<s:property value="users.dept" />
										</option>
									</s:else>
								</select>
								<input type="hidden" value="" name="ck.dept" id="ck_dept" />
							</td>
							<th>
								状态:
							</th>
							<td>
								<SELECT name="ck.status" class="cxselect">
									<option value="">
									</option>
									<option value="面试">
										面试
									</option>
									<option value="不录用">
										不录用
									</option>
									<option value="待入职">
										待入职
									</option>
									<option value="试用">
										试用
									</option>
									<option value="实习">
										实习
									</option>
									<option value="在职">
										在职(转正了)
									</option>
									<option value="病休">
										病休
									</option>
									<option value="内退">
										内退
									</option>
									<option value="退休">
										退休
									</option>
									<option value="离职">
										离职
									</option>
								</SELECT>
							</td>
							
							
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>
							序号
						</th>
						<th>
							姓名
						</th>
						<th>
							部门(注1)
						</th>
						<th>
							状态
						</th>
						<th>
							面试时间
						</th>
						<th>
							入职时间
						</th>
						<th>
							转正时间
						</th>
						<th>
							绩效时间（注2）
						</th>
						<th>
							奖惩时间
						</th>
						<th>
							调动时间
						</th>
						<th>
							晋升时间
						</th>
						<th>
							离职时间
						</th>
						<th>
							职业轨迹
						</th>
					</tr>
					<s:iterator value="ckList" id="pageList" status="pageStatus">
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
							<a href="CareertrackAction_showckById.action?id=${pageList.id}">${pageList.userName}</a>
						</td>
						<td>
							${pageList.dept}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
							${pageList.mianshiTime}
						</td>
						<td>
							${pageList.ruzhiTime}
						</td>
						<td>
							${pageList.zhuanzhengTime}
						</td>
						<td>
							${pageList.jixiaoTime}
						</td>
						<td>
							${pageList.jiangchengTime}
						</td>
						<td>
							${pageList.diaodongTime}
						</td>
						<td>
							${pageList.jinshengTime}
						</td>
						<td>
							${pageList.lizhiTime}
						</td>
						<td>
							<a href="CareertrackAction_showckById.action?id=${pageList.id}">查看</a>
						</td>
						</tr>
					</s:iterator>
					<s:if test="errorMessage ==null || errorMessage == ''">
						<tr>

							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="12" align="center">
								<font color="red" size="5">${errorMessage}</font>
							</td>

						</tr>
					</s:else>
				</table>
				<div align="left" style="right: 0%; position: absolute;">
					<font> <strong>注:</strong> <br>
						1、在职人员的部门为当前在职部门，面试人员的部门为面试部门。<br /> 2、绩效时间、奖惩时间、调动时间、晋升时间
						都为最近一次的时间。<br /> </font>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function gengxin(){
	if(confirm('确定要更新吗?')){
	$.ajax( {
		type : "POST",
		url : "CareertrackAction_addMore.action",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data == "true") {
				alert("OK!");
				window.location.reload();
			}
		}
	})
	}
}
var statue = "${statue}"
$(function(){
	if(statue != 'dept'){
		document.getElementById("dept_tr").removeAttribute("style");
		
	}else{
		$("#sub_td").attr('rowspan','1');
		$("#sub").css({
			height : "35px",
			width : "75px",
		})
	}
	
	
	
});

</SCRIPT>
	</body>
</html>
