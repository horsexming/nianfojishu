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
				<form action="JiaoXiaoKaoHeAction_addzbSjZk.action" method="POST"
					onsubmit="return check()">
					<table class="table">
						<tr>
							<th align="right">
								姓名
							</th>
							<td>
								<select name="" id="name">
									<option></option>
								</select>
								<input type="hidden" value="" name="zbSjZk.name" id="name0" />
							</td>
							<th align="right">
								部门
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.dept" id="dept"
									readonly="readonly" />
							</td>
							<th align="right">
								职位
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.rank" id="rank"
									readonly="readonly" />
								<input type="hidden" value="" name="zbSjZk.rankNo" id="rankNo" />
							</td>
							<th align="right">
								月份
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.months" id="months" class="Wdate" 
								onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								LAR
							</th>
							<th align="right">
								对应部门
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.lardyDept"
									id="lardyDept" />
							</td>
							<th align="right">
								目标值
							</th>
							<td>
								<select name="zbSjZk.larzhimubiao" id="larzhimubiao">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
								</select>
							</td>
							<th align="right">
								实际值
							</th>
							<td>
								<select name="zbSjZk.larzhibiao" id="larzhibiao">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								RID
							</th>
							<th align="right">
								对应部门
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.riddyDept"
									id="riddyDept" />
							</td>
							<th align="right">
								目标值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.ridmubiao"
									id="ridmubiao" onchange="numyanzheng(this)"/>
							</td>
							<th align="right">
								实际值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.ridzhibiao"
									id="ridzhibiao" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								总销售产值(亿)
							</th>
							<th align="right">
								对应部门
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.zczdyDept"
									id="zczdyDept" />
							</td>
							<th align="right">
								目标值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.zczmubiao"
									id="zczmubiao" onchange="numyanzheng(this)"/>
							</td>
							<th align="right">
								实际值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.zczzhibiao"
									id="zczzhibiao" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								人均销售(万)
							</th>
							<th align="right">
								对应部门
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.rjxsdyDept"
									id="rjxsdyDept" />
							</td>
							<th align="right">
								目标值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.rjxsmubiao"
									id="rjxsmubiao" onchange="numyanzheng(this)"/>
							</td>
							<th align="right">
								实际值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.rjxszhibiao"
									id="rjxszhibiao" onchange="numyanzheng(this)" />
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								实际人数
							</th>
							<th align="right">
								对应部门
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.sjrsdyDept"
									id="sjrsdyDept" />
							</td>
							<th align="right">
								目标值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.sjrsmubiao"
									id="sjrsmubiao" onchange="numyanzheng(this)"/>
							</td>
							<th align="right">
								实际值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.sjrszhibiao"
									id="sjrszhibiao" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								三按两遵守
							</th>
							<th align="right">
								对应部门
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.salzsdyDept"
									id="salzsdyDept" />
							</td>
							<th align="right">
								目标值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.salzsmubiao"
									id="salzsmubiao" onchange="numyanzheng(this)"/>
							</td>
							<th align="right">
								实际值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.salzszhibiao"
									id="salzszhibiao" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								变动费用(万)
							</th>
							<th align="right">
								对应部门
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.bdfydyDept"
									id="bdfydyDept" />
							</td>
							<th align="right">
								目标值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.bdfymubiao"
									id="bdfymubiao" onchange="numyanzheng(this)"/>
							</td>
							<th align="right">
								实际值
							</th>
							<td>
								<input type="text" value="" name="zbSjZk.bdfyzhibiao"
									id="bdfyzhibiao" onchange="numyanzheng(this)"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="添加" class="input" id="sub" />
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	if('${errorMessage}' == '添加成功!~'){
		alert('添加成功!~');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
 let findDudList = ()=>{
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isbmzmb':1},
		dataType : "json"
	})
	.then(function(data){
		if(data!=null && data.length>0){
			$("#name").empty();
			$("#name").append('<option></option>');
			console.log(data);
			$(data).each(function(){
				$("#name").append('<option value='+this.id+'>'+this.leader+'</option>');
			})
		}
	},
	function(data){
		console.log(data);
	}
	)
};
	let changvalue =()=>{
		$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudById0.action",
		data:{id:$("#name").val()},
		dataType : "json"
	})
	.then(function(data){
		if(data!=null){
			$("#name0").val(data.leader);
			$("#dept").val(data.deptName);
			$("#rank").val(data.rank);
			$("#rankNo").val(data.rankNo);
		}
	})
	};
	findDudList();
	$("#name").change(()=>changvalue());
})
function check(){
	
}



</SCRIPT>
	</body>
</html>
