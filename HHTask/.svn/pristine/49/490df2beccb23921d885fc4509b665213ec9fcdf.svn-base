<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
   		<link type="text/css" href="${pageContext.request.contextPath}/javascript/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/mobiscroll.core-2.5.2.css">
<%--		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"> </script> --%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap-datetimepicker/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap-datetimepicker/bootstrap-datetimepicker.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<%--		<script type="text/javascript">--%>
<%--			// 现在window.$和window.jQuery是3.2.1版本:--%>
<%--			console.log($().jquery); // => '3.2.1'--%>
<%--			var $jq = jQuery.noConflict(true);--%>
<%--			// 现在window.$和window.jQuery被恢复成1.5版本:--%>
<%--			console.log($().jquery); // => '1.5.0'--%>
<%--		</script>--%>
<style type="text/css">
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}

.ztree li a {
	color: #fff;
}

/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<form action="CorePayableAction!addPayMonth.action" method="post"  id="myform"  >
				<div class="row clearfix">
					<h2 align="center">
						月度结算
					</h2>
	            </div>
	            <div class="row">
	            	<div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">供应商</span>
<%--	                        data-live-search="true" --%>
	                         <select class="selectpicker show-tick form-control" name="payMonth.supplierName" id="supplierName">
	                         </select>
<%--	                      	 <input class="form-control" type="text" name="payMonth.supplierName" id="supplierName">--%>
	 	                </div>
	                </div>
	                <div class="form-group col-lg-6">
	             		<div class="input-group">
						<span class="input-group-addon">发票税率</span>
						<input type="text" class="form-control" name="payMonth.taxprice">
						</div>
					</div>
	            </div>
	            <div class="row">
	                <div class="form-group col-lg-6" id="time">
	                    <div class="input-group">
	                        <span class="input-group-addon">请款时间段</span>
							<div class="input-group date form_date col-md-5" data-date="" data-date-format="" id="time" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
			                    <input id="firstTime" name=firstTime class="form-control" size="16" type="text" value="" readonly>
			                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			                </div>
			                 </div>
			                 <div class="input-group">
		                        <span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		                        <div class="input-group date form_date col-md-5" data-date="" data-date-format="" id="time" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
		                    	<input id="endTime" name="endTime" class="form-control" size="16" type="text" value="" readonly>
		                   	 	<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
	               			 </div>
	                   
	                    </div>
	                    
	                </div>
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">付款方式</span>
	                      	 <select class="selectpicker show-tick form-control" name="payMonth.fukuanfs" data-live-search="true">
	    						<option value="月结">月结</option>
							    <option value="现金">现金</option>
   							 </select>
	               			 </div>
	                    </div>
				</div>
	            <div class="row">
	             <div class="form-group col-lg-6" >
	             <div class="input-group">
	                        <span class="input-group-addon">库别</span>
							<select name="goods.goodsClass" id="whView" style="width: 155px;" class="selectpicker show-tick form-control"
								onchange="changvalue(this)">
								<option value="">
									选择库别
								</option>
								<option value="${goods.goodsClass }" selected="selected">
									${goods.goodsClass }
								</option>
							</select>
			                 </div>
			                 </div>
			                  <div class="form-group col-lg-6" >
			                 <div class="input-group">
	                        <span class="input-group-addon">仓区</span>
	                        <select id="houseName"  name ="houseName" class="selectpicker show-tick form-control"
									style="width: 155px;">
									<option></option>
								</select>
	               			 </div>
	               </div>
	            </div>
				<div class="form-group col-lg-13">
						<span class="input-group-addon">请款项目</span>
							<div style="border:1px solid #EEEEEE" align="center">
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox1" name ="payMonth.proName" value="材料"> 材料
							    </label>
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox2" name ="payMonth.proName" value="模具费"> 模具费
							    </label>
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox3" name ="payMonth.proName" value="表处费"> 表处费
							    </label>
							     <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox1" name ="payMonth.proName" value="加工费"> 加工费
							    </label>
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox2" name ="payMonth.proName" value="设备"> 设备
							    </label>
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox3" name ="payMonth.proName" value="工具用品"> 工具用品
							    </label>
							      <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox1" name ="payMonth.proName" value="伙食费"> 伙食费
							    </label>
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox2" name ="payMonth.proName" value="水电费"> 水电费
							    </label>
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox3" name ="payMonth.proName" value="房租"> 房租
							    </label>
							      <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox1" name ="payMonth.proName" value="文具用品"> 文具用品
							    </label>
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox2" name ="payMonth.proName" value="运费"> 运费
							    </label>
							    <label class="checkbox-inline">
							        <input type="checkbox" id="inlineCheckbox3" name ="payMonth.proName" value="其他"> 其他
							    </label>
							 </div>
 	                </div>
				 <div class="row">
				 <div class="form-group col-lg-12">
					<button type="button" class="btn btn-default text-center" onclick="adddetail()" id="addmx" style="display: display">
					添加明细
					</button>
				</div>
				<div id="mingxi" class="form-group col-lg-12">
				
				</div>
				<div id="addmx_more" class="form-group col-lg-12" style="display: none" >
					<input type="button"  value="增加" class="btn btn-default text-center" onclick="adddetail_more()">
					<input type="button"  value="提交" class="btn btn-default text-center" onclick="setName()">
				</div>
			</form>
			
		</div>
	</body>	
	<script type="text/javascript">
	$('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
	//加载供应商;
	$.ajax( {
		type : "POST",
		url : "PriceAction!findAllZhUser.action",
		dataType : "json",
		success : function(data) {
			$("#supplierName").empty();
			$("#supplierName").append("<option value=''>选择供应商</option>");
			if(data!=null){
				$(data).each(function(){
					$("#supplierName").append('<option value='+this.name+'>'+this.usercode+'_'+this.name+'</option>');
				})
			}
			$("#supplierName").tinyselect();
		}
	})
var maxsize =0;
	function adddetail(){
		alert($("#textselecthouseName").val());
		$.ajax( {
		type : "POST",
		url:"CorePayableAction!findPayMonthDetail.action",
		data : {
			'payMonth.supplierName':$("#supplierName").val(),
			'firstTime':$("#firstTime").val(),
			'endTime':$("#endTime").val(),
			'houseName':$("#textselecthouseName").val(),//仓区
			'maxsize' :maxsize,
			},
		dataType : "json",
		success : function(data) {
			$(data).each(
						function() {
							$(	
								"<div class='row' id='mx_"+maxsize+"'>"+
	            					"<div class='form-group col-lg-5'>"+
	                    				"<div class='input-group'>"+
	                      					 "<span class='input-group-addon'>费用摘要说明</span>"+
	                      						 "<input class='form-control illustrate' type='text' name='' id='illustrate' value='"+this.illustrate+"'>"+
	 	               				" </div></div>"+
	 	               				"<div class='form-group col-lg-5'>"+
	                    				"<div class='input-group'>"+
	                      					 "<span class='input-group-addon'>金额</span>"+
	                      						 "<input class='form-control jine' type='text' name='' id='jine' value='"+this.jine+"'>"+
	 	               				" </div></div><button class='btn btn-primary btn' onclick='deleteMx("+maxsize+")'>"+
	 	               				"<span class='glyphicon glyphicon-minus'></span>" +
	 	               				"</div>"
						).appendTo("#mingxi");
						maxsize++;
						});
			document.getElementById("addmx_more").style.display='block';
<%--			document.getElementById("addmx").style.display='none';--%>
		}
	});
	}
	function adddetail_more(){
		$(	
								"<div class='row' id='mx_"+maxsize+"'>"+
	            					"<div class='form-group col-lg-5'>"+
	                    				"<div class='input-group'>"+
	                      					 "<span class='input-group-addon'>费用摘要说明</span>"+
	                      						 "<input class='form-control illustrate' type='text' name='' id='illustrate' value=''>"+
	 	               				" </div></div>"+
	 	               				"<div class='form-group col-lg-5'>"+
	                    				"<div class='input-group'>"+
	                      					 "<span class='input-group-addon'>金额</span>"+
	                      						 "<input class='form-control jine' type='text' name='' id='jine' value=''>"+
	 	               				" </div></div><button class='btn btn-primary btn' onclick='deleteMx("+maxsize+")'>"+
	 	               				"<span class='glyphicon glyphicon-minus'></span>" +
	 	               				"</div>"
						).appendTo("#mingxi");
		maxsize++;
	}
	function deleteMx(num){
		$("#mx_"+num).remove();
	}

function addSelect() {
	$
			.ajax( {
				type : "POST",
				url : "GoodsStoreAction!getViewAuth.action",
				data : {},
				dataType : "json",
				success : function(msg) {
					if (msg.success) {
						for (k in msg.data) {
							$('#whView').append(
									"<option>" + msg.data[k] + "</option>");
						}
					} else {
						alert(msg.message);
					}
					duoxuaSelect("whView", '${goods.goodsClass}');
				}
			});

}
function changvalue(obj) {
	if (obj != null && obj.value != "") {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwaListByNO.action",
			data : {
				wareHouseName : obj.value
			},
			dataType : "json",
			success : function(data) {
				if (data != null && data.length > 0) {
					$("#houseName").empty();
					$(data).each(
							function() {
								$("#houseName").append(
										'<option value=' + this.goodHouseName
												+ '>' + this.goodHouseName
												+ '</option>');
							});
					var duoxuanselect = $(".duoxuaselect_div");
					if (duoxuanselect[1] != null) {
						$(duoxuanselect[1]).remove();
					}
					duoxuaSelect("houseName");
				}
			}
		});
	}
}
	function setName(){
		var i =0;
		$(".illustrate").each(function(){
			$(this).attr("name","pmdList["+i+"].illustrate");
			i++;
		});
		var j=0;
		$(".jine").each(function(){
			$(this).attr("name","pmdList["+j+"].jine");
			j++;
		});
		var form = document.getElementById('myform');
		form.submit();
	}
	addSelect();
	</script>
</html>
