
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
		<style type="text/css">
			body {
				text-align: center;
			}
			body {
				text-align: center;
			}
			.ztree li a {
				color: #fff;
			}
		</style>
		<%@include file="/util/sonHead.jsp"%>
	</head>	
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			
			
			<div align="center">
				<h3>
					${companyInfo.name}采购申报平台
				</h3>
				<div style="font-size: 13; color: red; font-weight: bold;">
					 ${message} 
				</div>
				<form action="OAAppDetailTemplateAction!updateTemp.action" id="oaform" method="post"
					><!-- onsubmit="return checkForm()" -->
					<input type="hidden" name="template.id" value="${template.id}" id="oldId">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th >物品类别</th>
								<td>
									<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType0" type="text" name="template.detailChildClass" style="width: 120px;" value="${template.detailChildClass}" />
											<input id="categoryId0" type="hidden"  onchange="getfirstNo('0')"/>
											<input id="gztypename0" type="hidden" >
											<a id="menuBtn" href="#" color="#fff" onclick="showMenu('0'); return false;">选择</a>
										</li>
									</ul>
									</div>
									<div id="menuContent0" class="menuContent"
										style="display: none; position: absolute;">
										<ul id="treeDemo0" class="ztree" style="margin-top: 0; width: 160px;"></ul>
									</div>
								</td>
								<th>
									物品名称
								</th>
								<td>
									<div id="showAll" style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;width: 200px"></div>
									<input type="text" id="detailAppName" onkeyup="getAllNames()" style="height: 20px; width: 200px" onFocus="init()"
									onBlur="hidediv()" name="template.detailAppName" value="${template.detailAppName}" /></td>
							</tr>
							<tr>
								<th>
									物品规格
								</th>
								<td>
									<input type="text" name="template.detailFormat" value="${template.detailFormat}" />
								</td>
								<th>
									物品单位
								</th>
								<td>
									<select name="template.detailUnit"  id="danwei" 	>
										<option value="${template.detailUnit}">${template.detailUnit}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									物料编码<br/>Material coding
								</th>
								<td>
									<input type="text" name="template.wlcode" value="${template.wlcode}" readonly="readonly" id="wlcode">
								</td>
								<th>供料属性</th>
								<td>${template.kgliao }</td>
							</tr>
							<tr>
								<th>版本</th>
								<td>${template.banben }</td>
								
							</tr>
								<%--<tr>
									<td colspan="4" align="center">
										<input type="hidden" name="template.detailAppDate" value="${template.detailAppDate}" />
										<input type="hidden" name="template.gztype" value="${template.gztype}" id="gztype"/>
										<input type="submit" value="提交" 
											style="width: 60px; height: 40px;" align="top"><!-- onclick="compareCount(this.form)" -->
										&nbsp;&nbsp;
										<input type="reset" value="取消"
											style="width: 60px; height: 40px;" align="top">
									</td>
								</tr>
								--%><tr>
									<td colspan="4" style="font-size: 12px;">
										备注：
										<br>
										1、采购申请前提是有部门预算申请，且预算申请可用余额大于申购金额。<br>
										2、手动添加是单条提交申购明细，批量上传为上传EXCEL标准格式的申报明细。<br>
										3、批量上传的前提是要先选择预算月份和预算科目。<br>
										4、处理后统一跳转到个人申报历史记录里。<br>
										5、审批通过后采购进行打印<br>
										6、采购入库后预算完成，入库单价和数量均不可超过申购的单价和数量。<br>
									</td>
								</tr>
							<tbody>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
	<%--<script type="text/javascript">
		$(function(){
		getUnit("danwei");
	})
	 var text="";
	function writeSelect(obj){
	text=text+String.fromCharCode(event.keyCode);
	var index=obj.selectedIndex; //序号，取当前选中选项的序号
	obj.options.length=0;//清除所有option 
	obj.options.add(new Option(text,text)); //这个兼容IE与firefox
    } 
	//下拉预算月份
	function selectMonth(){
		createDept("detailPlanMon","oaAppDetailAction!findSelectMon.action");		
	}
	function selectSub(){
		alert("");
	}
	//显示项目编号
	function showProjectNO(){
		$("#projectNO").show();
		var tag="projectNO";
		//下拉项目菜单
		$.ajax( {
		type : "POST",
		url : "oaAppDetailAction!findchildSubjects.action",
		data : {
			tag :tag ,
			planMonth : planMonth
		},
		dataType : 'json',
		success : function(data) {
			$("#detailItemId").empty();
			$(data).each(
					function() {
						$("<option value='" + this.code + "'>" + this.name
									+ "(" + this.code
									+ ")</option>").appendTo(
							"#detailItemId");
					});
		}
	});
	}
	//隐藏项目编号
	function hiddenProjectNO(){
		$("#projectNO").hide();
	}
var inforDivHTML = "";
var lineCount = 1;
var begAddLineNum = 6;
//选择部门
var planMonth = $("#planMonth").val();
function selectDept(few) {
	var budgetDept = $("#budgetDept");
	var budgetDetpR = document.getElementsByName("baoxiaodan.isSelfDept");
	for ( var i = 0; i < budgetDetpR.length; i++) {
		if (budgetDetpR[i].checked)
			var budgetDept = budgetDetpR[i].value;
	}
	var id = "course" + few;
	createDept(id, "BaoXiaoDanAction!findchildDept.action?tag=" + budgetDept
			+ "&planMonth=" + planMonth);
	selectSubjects(few);
}
//选择科目
function selectSubjects() {
	var planMonth=$("#detailPlanMon").val();
	var tag="planMonth";
	$.ajax( {
		type : "POST",
		url : "oaAppDetailAction!findchildSubjects.action",
		data : {
			tag : tag,
			planMonth : planMonth
		},
		dataType : 'json',
		success : function(data) {
			$("#subject").empty();
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "(" + this.accountMoney
										+ ")</option>").appendTo(
								"#subject");
					});
		}
	});
}
//选择物品名称
function selectGoodsName(){
	var childClass=$("#detailChildClass").val();
	var  goodsName=$("#detailAppName");
	var url="oaAppDetailAction!findchildClass.action?tag="+childClass;
	url = encodeURI(url);
	$("#detailAppName").empty();
	createDept('detailAppName',url);
}
//选择规格
function selectFormat(){
	
	var childClass=$("#detailChildClass").val();
	var  goodsName=$("#detailAppName").val();
	var detailFormat=$("#detailFormat");
	var url="oaAppDetailAction!findFormat.action?tag="+childClass+"&powerTag="+goodsName;
	url = encodeURI(url);
	$("#detailFormat").empty();
	createDept('detailFormat',url);
}
//提交验证
function checkForm() {	
	var detailPlanMon=$("#detailPlanMon");//预算月份
	var  subject=$("#subject");//预算科目
	var  detailAppName=$("#detailAppName");//物品名称
	var detailChildClass=$("#detailChildClass");//物品类型
	var  detailBudgetMoney=$("#detailBudgetMoney");//预算单价
	var  detailCount=$("#detailCount");//数量
	var  detailPlanAcco=$("#detailPlanAcco");//计划依据	
	
	if (detailPlanMon.val() == "") {
		alert("预算月份不能为空!");
		detailPlanMon.focus();
		return false;
	} else if (subject.val() == "") {
		alert("预算科目不能为空!");
		subject.focus();
		return false;
	} else if (detailChildClass.val() == "") {
		alert("物品类别不能为空!");
		detailChildClass.focus();
		return false;
	} else if (detailAppName.val() == "") {
		alert("物品名称不能为空!");
		detailAppName.focus();
		return false;
	}
	else if (detailBudgetMoney.val() == "") {
		alert("预算单价不能为空!");
		detailBudgetMoney.focus();
		return false;
	}else if (detailCount.val() == "") {
		alert("预算数量不能为空!");
		detailCount.focus();
		return false;
	}else if (detailPlanAcco.val() == "") {
		alert("计划依据不能为空!");
		detailPlanAcco.focus();
		return false;
	}
	return false;
}

function compareCount(objForm) {	
	checkForm();
	var  detailBudgetMoney=$("#detailBudgetMoney");//预算单价
	var  detailCount=$("#detailCount");//数量
	//判断申报金额与预算金额大小
	var money= parseFloat(detailBudgetMoney.val())* parseFloat(detailCount.val());
	var price=$("#oldPrice").val();//老预算单价
	var count=$("#oldCount").val();//老申报数量
	var oldMoney=parseFloat(price)*parseFloat(count);
	//alert(oldMoney);
	var oldId=$("#oldId").val();
	var id=$("#subject").val();
	var id1 = "${oadetail.id}";
	$.ajax( {
			type : "POST",
			url : 'oaAppDetailAction!compareBudgetCount.action',
			data : {
				id1:id1,
				id : id,
				money : money
			},
			dataType : 'json',
			cache : false,//防止数据缓存
			success : function(msg) {
				if ("NO" == msg) {
					alert("预算金额超出预算金额，请核实！");
					detailBudgetMoney.val(0);
					detailBudgetMoney.focus();
					return false;
				}else{
					 objForm.action="oaAppDetailAction!updateOADetail.action";
					 objForm.submit();
				}
			}

		});
	
}
function getfirstNo(num){
	var categoryId  = $("#categoryId"+num).val();
	var gztypename = $("#gztypename"+num).val();
	var sqNum = 1;
	if(categoryId!='' && gztypename!='' && sqNum!= ''){
			$.ajax( {
		url : 'ChartNOSQAction_getfirstNo.action',
		type : 'POST',
		data : {
			'cq.categoryId':categoryId,
			'cq.gztype':gztypename,
			'cq.sqNum':sqNum
		},
		dataType : 'json',
		success : function(data) {
			if(data!=null){
				var firstNo = data.firstNo;
				$("#wlcode").val(firstNo);
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	}
}
var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var size = 0;
var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick
	}
};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);

	return true;
}
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"+size), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	var id = '';
	for ( var i = 0, l = nodes.length; i < l; i++) {
		$.ajax( {
		url : 'CategoryAction_islow.action',
		type : 'post',
		data : {
			id : nodes[i].id
		},
		dataType : 'json',
		cache : true,
		async : false,
		success : function(doc) {
			if(doc){
				v = nodes[i].name;
				id = nodes[i].id;
			}else{
				alert('请选择最底层');
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#wgType"+size).val(v);
	$("#categoryId"+size).val(id);
	getfirstNo('0');
}
function hideMenu(num) {
	$("#menuContent"+num).fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
	size = '';
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent"+size || $(
			event.target).parents("#menuContent"+size).length > 0)) {
		hideMenu(size);
	}
}
function showMenu(num) {
	size = num;
	zNodes = [];
	parent.mfzTree();
	var cityObj = $("#wgType"+num);
	var cityOffset = $("#wgType"+num).offset();
	$("#menuContent"+num).css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}

var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'POST',
		data : {
			status : '编码',
			tag:'fl'
		},
		dataType : 'json',
		async : false,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('name'),
					target : "main",
					click : false
				});

			});
			$.fn.zTree.init($("#treeDemo"+size), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"+size);
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
</script>

--%></html>
