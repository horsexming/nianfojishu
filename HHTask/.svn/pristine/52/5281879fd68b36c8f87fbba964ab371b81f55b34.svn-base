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
	<body onload="getDept();" >
		<center>
			<form action="DingdanAction!addzhaobiao.action" method="post"
				theme="simple">

				<table class="table" style="width: 40%;">
					<tr >
						<th colspan="2" align="center">
							添加招标
						</th>
					</tr>
					<tr>
						<th align="right">
							招标名称：
						</th>
						<td>
							<input type="text" id="zhaobiao.title" name="zhaobiao.title"
								value="请购征询单SHLD-Y-${internalOrderzhaobiao.num}" />
						</td>
					</tr>
					<tr>
						<th align="right">
							开始时间：
						</th>
						<td>
							<input class="Wdate" type="text" id="zhaobiao.moban"
								name="zhaobiao.moban"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<th align="right">
							结束时间：
						</th>
						<td>
							<input class="Wdate" type="text" id="zhaobiao.kongxian"
								name="zhaobiao.kongxian"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<th align="right">
							招标负责人：
						</th>
						<td>
							<input type="text" id="zhaobiao.fuze" name="zhaobiao.fuze"
								value="${sessionScope.Users.name}" />
						</td>
					</tr>
					<tr>
						<th align="right">
							负责人电话：
						</th>
						<td>
							<input type="text" id="zhaobiao.phone" name="zhaobiao.phone"
								value="${sessionScope.Users.password.phoneNumber}" />
						</td>
					</tr>
					<tr>
						<th align="right">
							招商简介：
						</th>
						<td>
							<textarea rows="2" cols="50" id="zhaobiao.loc"
								name="zhaobiao.loc"  /></textarea>
						</td>
					</tr>
				</table>
				<table>

					<tr bgcolor="#c0dcf2">
						<td>
							预算部门:
						</td>
						<td>
							预算月份:
						</td>
						<td>
							预算科目:
						</td>
						<td>
							物料名称:
						</td>
						<td>
							类别:
						</td>
						<td>
							总数量:&nbsp;&nbsp;&nbsp;可采购数量：
						</td>
						<td>
							实际采购数量:
						</td>
						<td>
							单位:
						</td>
						<td>
							需求到货天数:
						</td>
						<td>
							交付时间:
						</td>
						<td>
							规格要求:
						</td>
					</tr>

					<s:iterator value="list" id="pageList" status="pageStatus">
						<tr >
							<td>
							 <!--jiahua   id -->
								<input type="hidden" id="zhaobiaoXis.t11" name="zhaobiaoXis.t11"
									value="${pageList.id}"  readonly="readonly"/>
							
							
								<select id="dept${pageStatus.index}" style="width: 90px;"
									name="deptMonthBudget.name"
									onchange="javascript:ff1(this.value,${pageStatus.index});"
									></select>
							</td>
							<td>
								<select id="yuefen${pageStatus.index}" style="width: 100px;"
									onchange="getKemu(this.value,${pageStatus.index});"></select>
							</td>
							<td>
							 <!--下拉科目 -->
								<select id="kemu${pageStatus.index}" name="zhaobiaoXis.t9"
									style="width: 100px;"></select>
							</td>
							<td>
							
							
							 <!--物料名称 -->
								
									<input type="text" id="zhaobiaoXis.t61" name="zhaobiaoXis.t6"
										value="${pageList.markId}" readonly="readonly"/>
								
							</td>
							<td>
							 <!--模板 -->
								<select id="moban${pageStatus.index}"  style="width: 90px;"
									name="zhaobiaoXis.t1"
									></select>
							</td>
							<td>
							 <!--总数量 -->
								<input type="text" id="shuliang${pageStatus.index}" name="shuliang"
									value="${pageList.shuliang}"  style="width: 50px;" readonly="readonly"/>&nbsp;&nbsp;
									<input type="text" id="shiji${pageStatus.index}" name="shiji"
									value="${pageList.shiji}"  style="width: 50px;" readonly="readonly"/>
							</td>
							<td>
							 <!--可采购数量 -->
								<input type="text" id="t2${pageStatus.index}" name="zhaobiaoXis.t2"
									value="${pageList.shiji}" />
							</td>

							<td>
							 <!--单位-->
								<input type="text" id="zhaobiaoXis.t3" name="zhaobiaoXis.t3"
									value="${pageList.danwei}" />
							</td>

							<td>
							 <!--需求到货天数 -->
								<input type="text" id="zhaobiaoXis.t4" name="zhaobiaoXis.t4"
									value="7" />
							</td>
							<td>
							 <!--交付时间-->
								<input type="text" id="zhaobiaoXis.t8" name="zhaobiaoXis.t8"
									value="${pageList.genertorDate}" />
							</td>
							<td>
							 <!--规格要求 -->
								<input type="text" id="zhaobiaoXis.t5" name="zhaobiaoXis.t5"
									 value="${pageList.guige}"/>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="10" align="center">

							<input type="submit" value="保存" class="input" />
							<input type="button" name="Submit2" value="取消" class="input"
								class="right-buttons" onclick="window.history.go(-1);" />
						</td>
					</tr>
				</table>
				</form>
		</center>
	</body>
	<script type="text/javascript">
function getDept() {
		 var len=<s:property value='list.size()'/>;
		 
	
		//--------------------------------------------
		$.ajax( {
		url : 'zhaobiaoAction!findAllDept1.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			for(var i=0;i<=len;i++){//
			$("#dept" + i).empty();
			$("<option></option>").appendTo("#dept" + i);
			$(allDdept).each(
					function() {
						$("<option value='" + this + "'>" + this + "</option>")
								.appendTo("#dept" + i);
					});
		}
		}//for
	});
	//---------------------------
	$.ajax( {
		type : "POST",
		url : "DingdanAction!listmoban.action",
		dataType : "json",
		success : function(obj) {
			for(var j=0;j<=len;j++){//for
			$("#moban"+j).empty();
			$("<option></option>").appendTo("#moban"+j);
			$(obj).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "(" + this.classe + ")"
										+ "</option>").appendTo("#moban"+j);
					});
		}
			}//for
	});
	
//--------------------------------------------
/*
for(var k=0;k<=len;k++){
	var shuliang = document.getElementById("shuliang"+k).value;
	var shiji = document.getElementById("shiji"+k).value;
	var ke=shuliang-shiji
	document.getElementById("shiji"+k).value=ke;
}*/
//-----------------------------
}
function ff1(deptValue, eid) {
	$.ajax( {
		type : "POST",
		url : "zhaobiaoAction!listDept.action",
		data : {
			pageStatus : deptValue
		},
		dataType : "json",
		success : function(object) {
			$("#yuefen"+eid).empty();
			$("<option></option>").appendTo("#yuefen"+eid);
			$(object).each(
					function() {
						$("<option value='" + this + "'>" + this + "</option>")
								.appendTo("#yuefen"+eid);
					});
		}
	});
}
function getKemu(yuefen,tid) {
	//alert(yuefen);
	var dept = document.getElementById("dept"+tid).value;
	$.ajax( {
		type : "POST",
		url : "zhaobiaoAction!listMoth.action",
		data : {
			pageStatus : yuefen,
			pagename : dept
		},
		dataType : "json",
		success : function(obj) {
			$("#kemu"+tid).empty();
			$("<option></option>").appendTo("#kemu"+tid);
			$(obj).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "(" + this.accountMoney + ")"
										+ "</option>").appendTo("#kemu"+tid);
					});
		}
	});
}
</script>
</html>
