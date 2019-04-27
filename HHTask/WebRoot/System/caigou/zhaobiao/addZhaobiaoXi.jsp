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
	<body onload="getDept('dept');">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table" style="width: 50%;">
					<tr><th colspan="2">招标信息</th></tr>
					<tr><th align="right">招标题目:</th>
						<td>${zhaobiao.title}</td>
					</tr>
					<tr>
						<th align="right">招标负责人:</th>
						<td>${zhaobiao.fuze}</td>
					</tr>
					<tr>
						<th align="right">负责人电话:</th>
						<td>${zhaobiao.phone}</td>
					</tr>
					<tr>
						<th align="right">开始时间:</th>
						<td>${zhaobiao.moban}</td>
					</tr>
					<tr>
						<th align="right">结束时间:</th>
						<td>${zhaobiao.kongxian}</td>
					</tr>
					<tr>
						<th align="right">招商简介:</th>
						<td colspan="2" height="100px">${zhaobiao.loc}</td>
					</tr>
				</table>
				<br />
				<br />
				<form action="zhaobiaoAction!addxi.action" method="post"
					theme="simple">
					<!-- 招标id -->
					<input type="hidden" id=zhaobiao.id name="zhaobiao.id"
						value="${zhaobiao.id}" />
					<table class="table" style="width: 100%">
						<tr>
							<th colspan="20" width="100%" align="center">
								添加版料
							</th>
						</tr>
						<tr>
							<td>
								预算部门
							</td>
							<td>
								预算月份
							</td>
							<td>
								预算科目
							</td>
							<td>
								使用模版
							</td>
							<td>
								物料名称
							</td>
							<td>
								数量/单位
							</td>
							<td>
								需求到货天数
							</td> 
							<td>
								交付时间
							</td>
							<td>
								规格要求
							</td>
							
							<td>
								<input type="button" value="添加行" onclick="addRow();" />
							</td>
						</tr>
						<tr id="mode">
							<td>
							   <!--下拉部门  -->
								<select id="dept" name="deptname" style="width:50px;"
									onchange="javascript:ff1(this.value,this.id);">
									<option></option>
								</select>

							</td>
							<td>
							  <!--下拉月份  -->
								<select id="shijian" style="width: 90px;"
									name="deptMonthBudget.name"
									onchange="javascript:ff12(this.value);"></select>

							</td>
							<td>
							    <!--下拉科目 -->
								<select id="yuefen" style="width: 100px;" name="zhaobiaoXis.t9"></select>

							</td>
							<td>
								<s:select id="t1" name="zhaobiaoXis.t1" list="list" listKey="id"
									listValue="name" />

							</td>
								<td>
								<input type="text" name="zhaobiaoXis.t6" id="zhaobiaoXis.t6"
									style="width: 80px;" />
							</td>
							<td>
								<input type="text" name="zhaobiaoXis.t2" id="t2"
									style="width: 80px;" onkeyup="if(isNaN(value))execCommand('undo')"/>
								<SELECT name="zhaobiaoXis.t3" style="width: 40px" id="danwei">
								</SELECT>
							</td>
							
							<td>
								<input type="text" name="zhaobiaoXis.t4" id="t4"
									style="width: 50px;" />天
							</td>
							<td>
							<input class="Wdate" type="text" id="zhaobiaoXis.t8"
									name="zhaobiaoXis.t8"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								<textarea name="zhaobiaoXis.t5" id="t5"
									style="width: 100px; height: 50px" ></textarea>
							</td>
						
							<td>
								<input type="button" value="删除行" onclick="deleteRow(this);" >
							</td>
						</tr>
						<tr>
							<td colspan="9" align="center">
								<input type="submit" value="保存" class="input" />
							
								<input type="button" name="Submit2" value="返回"  class="input"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		               
		$(function(){
				getUnit("danwei");
		})
		var ids=1;
		var  shijianId;//
		var deptId;//部门ID
		var yuefenID;//月份ID<option></<option>
			//下拉月份
			function ff1(deptValue,id){
				var idIndex=id.split("t")[1];
		         $.ajax( {
					type : "POST",
					url : "zhaobiaoAction!listDept.action",
					data:{pageStatus:deptValue},
					dataType : "json",
					success : function(object) {
						$("#shijian"+idIndex).empty();//清空
						//$("").appendTo(
						//						"#shijian"+idIndex);
						$(object).each(function(i,n) {
									$("<option value='"+ [n,deptValue,idIndex]
													   + "'>"
														+ n+
														"</option>")
																	.appendTo(
																			"#shijian"+idIndex)
									});
		       	     }
                       
		  		   });
		}
		//下拉科目
		function ff12(id){
		var aaa=id.split(",");
		//alert(aaa[0]);
		//alert(aaa[1]);<option></option>
		         $.ajax( {
					type : "POST",
					url : "zhaobiaoAction!listMoth.action?deptMonthBudget.name="+encodeURI(aaa[0])+"&deptMonthBudget.userDept="+encodeURI(aaa[1]),
					data:{pageStatus:aaa[0],pagename:aaa[1]},
					dataType : "json",
					success : function(object) {
						$("#yuefen"+aaa[2]).empty();//清空
						//$("<option></option>").appendTo(
						//						"#yuefen"+aaa[2]);
						$(object).each(function() {
									$("<option value='"+ this.id
													   + "'>"
														+ this.name+"("+this.accountMoney+")"
														+ "</option>")
																	.appendTo(
																			"#yuefen"+aaa[2])
									});
		       	     }
                          
		  		   });
		}
		
		/*
		//根据下拉列表部门得到下拉列表月份
		function ff12(id){
		var aaa=id.split(",");
		//alert(aaa[0]);
		//alert(aaa[1]);
		         $.ajax( {
					type : "POST",
					url : "zhaobiaoAction!listMoth.action?deptMonthBudget.name="+encodeURI(aaa[0])+"&deptMonthBudget.userDept="+encodeURI(aaa[1]),
					data:{pageStatus:aaa[0],pagename:aaa[1]},
					dataType : "json",
					success : function(object) {
						$("#yuefen"+aaa[2]).empty();//清空
						$("<option></option>").appendTo(
												"#yuefen"+aaa[2]);
						$(object).each(function() {
									$("<option value='"+ this.id
													   + "'>"
														+ "("+this.budgetMonth+")"
														+ "</option>")
																	.appendTo(
																			"#yuefen"+aaa[2])
									});
		       	     }
                          
		  		   });
		}
		
		//根据下拉列表部门得到下拉列表名称
			function ff1(deptValue,id){
				var idIndex=id.split("t")[1];
		         $.ajax( {
					type : "POST",
					url : "zhaobiaoAction!listDept.action",
					data:{pageStatus:deptValue},
					dataType : "json",
					success : function(object) {
						$("#shijian"+idIndex).empty();//清空
						$("<option></option>").appendTo(
												"#shijian"+idIndex);
						$(object).each(function() {
									$("<option value='"+ [this.name,deptValue,idIndex]
													   + "'>"
														+ this.name+"("+this.accountMoney+")"
														+ "</option>")
																	.appendTo(
																			"#shijian"+idIndex)
									});
		       	     }
                          
		  		   });
		}
    */
		function addRow(){
			var m=$("#mode").clone();
			//m.attr('id','mode'+ids);
			$("#mode").after(m);
			$("#mode").attr('id','mode'+ids);
			$("#dept").attr('id','dept'+ids);
			//
			$("#shijian").attr('id','shijian'+ids);
			shijianId="shijian"+ids;
			//
			$("#yuefen").attr('id','yuefen'+ids);
			yuefenID="yuefen"+ids;

			//
			deptId="dept"+ids;
			 getDept(deptId);
			//$("#"+deptId).bind('click',function(){
			//});
			//
			/*$("#"+deptId).bind('click',function(){
				var select =$(this);
				$.ajax( {
					url : 'zhaobiaoAction!findAllDept1.action',
					dataType : 'json',
					cache : false,//防止数据缓存
					success : function(allDdept) {
						$("<option>111</option>").appendTo(select);
						$(allDdept).each(
								function() {
									$(
											"<option value='" + this.dept + "'>"
													+ this.dept + "</option>").appendTo(
											"#"+deptId);
								});
					}
			
				});
			});*/
			ids++;
		}
		//下拉部门
		function getDept(id){
				var select =$(this);
				$.ajax( {
					url : 'zhaobiaoAction!findAllDept1.action',
					dataType : 'json',
					cache : false,//防止数据缓存
					success : function(allDdept) {
						$("#"+id).empty();
						$("<option></option>").appendTo("#"+id);
						$(allDdept).each(
								function() {
									$(
											"<option value='" + this + "'>"
													+ this + "</option>").appendTo(
											"#"+id);
								});
					}
				});
		}
		function deleteRow(th){
			var del=th.parentNode.parentNode;
			del.parentNode.removeChild(del);
		}
		
		function fun1(th) {
			var hj=0;
			$("input[name='ms.yfje']").each(function(){
				if($(this).val()!=null&&$(this).val()!="") {
					hj=parseInt(hj)+parseInt($(this).val());
				}
			});
			$("#yfje").val(hj);
			
			jisuan(th);
		}
		
		function fun2(th){
			var hj=0;
			$("input[name='ms.dkfhf']").each(function(){
				if($(this).val()!=null&&$(this).val()!="") {
					hj=parseInt(hj)+parseInt($(this).val());
				}
			});
			$("#dkfhf").val(hj);
			
			jisuan(th);
		}
		function fun3(th){
			var hj=0;
			$("input[name='ms.dksj']").each(function(){
				if($(this).val()!=null&&$(this).val()!="") {
					hj=parseInt(hj)+parseInt($(this).val());
				}
			});
			$("#dksj").val(hj);
			
			jisuan(th);
		}
		function fun4(){
			var hj=0;
			$("input[name='ms.sfje']").each(function(){
				if($(this).val()!=null&&$(this).val()!="") {
					hj=parseInt(hj)+parseInt($(this).val());
				}
			});
			$("#sfje").val(hj);
		}
	
		function shijian() {
			var data = new Date();
			$("#bxrq").val(data.toLocaleString());
		}
	</SCRIPT>
	</body>
</html>
