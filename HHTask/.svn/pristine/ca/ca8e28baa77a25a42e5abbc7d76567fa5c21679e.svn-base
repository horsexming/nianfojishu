
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
				<form action="oaAppDetailAction!updateOADetail.action" id="oaform" method="post"
					onsubmit="return checkForm()">
					<input type="hidden" name="oadetail.id" value="${oadetail.id}" id="oldId">
					<input type="hidden" value="${oadetail.detailBudgetMoney}" id="oldPrice">
					<input type="hidden" value="${oadetail.detailCount}" id="oldCount">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th>
									申报类型
								</th>
								<td>
								<s:if test="%{'普通申购'==oadetail.detailClass}">
								<input type="radio" id="budgetDept"
										onclick="hiddenProjectNO()" name="oadetail.detailClass"
										value="普通申购" checked="checked" />
									普通申购
									<input type="radio" id="budgetDept2" onclick="showProjectNO()"
										name="oadetail.detailClass" value="项目申购" />
									项目申购
								</s:if>
								<s:else>
								<input type="radio" id="budgetDept"
										onclick="hiddenProjectNO()" name="oadetail.detailClass"
										value="普通申购" />
									普通申购
									<input type="radio" id="budgetDept2" onclick="showProjectNO()"
										name="oadetail.detailClass" value="项目申购"  checked="checked"/>
									项目申购
								</s:else>
									
								</td>
								<th>
									项目编号
								</th>
								<td>
								<s:if test="%{'普通申购'==oadetail.detailClass}">
								<div id="projectNO" style="display:none;">
									<select name="oadetail.detailItemId" style="width: 150px;"
										onMouseOver="showProjectNO()" id="detailItemId">
									</select>
								</div>
								</s:if>
								<s:else>
								<div id="projectNO" style="display:block;">
									<select name="oadetail.detailItemId" style="width: 150px;"
										onMouseOver="selectProjectNO()" id="detailItemId">
										<option value="${oadetail.detailItemId}">
										${oadetail.detailItemId}
										</option>
									</select>
								</div>
								</s:else>
								
								</td>
							</tr>
							<tr>
								<th colspan="4">
									手动添加申购明细 
								</th>
							</tr>
							<tr>
								<th>
									物品类别
								</th>
								<td>
									<select name="oadetail.detailChildClass" style="width: 130px;"
										id="detailChildClass" >
<%--										>onMouseOver="createDept('detailChildClass','oaAppDetailAction!findOASelect.action?tag=detailChildClass&powerTag=${powerTag}')" onchange="selectGoodsName()"--%>
										<option value="${oadetail.detailChildClass}">
											${oadetail.detailChildClass}
										</option>
										<%--<option value="">
											选择类别
										</option>
									--%></select>
								</td>
							</tr>
							<tr>
								<th>物料编码</th>
								<td>
									<input type="text" name="oadetail.wlcode" value="${oadetail.wlcode}" readonly="readonly" />
								</td>
								<th>
									物品名称
								</th>
								<td>
									<div id="showAll" style="background-color: #ffffff; position: absolute;
									 visibility: hidden; z-index: 1;width: 200px"></div>
							<input type="text" id="detailAppName" style="height: 20px; width: 200px" 
								 name="oadetail.detailAppName" value="${oadetail.detailAppName}" readonly="readonly"/>
								<!--  onkeyup="getAllNames()"onBlur="hidediv()" onFocus="init()" -->
								</td>
							</tr>
							<tr>
								<th>
									物品规格
								</th>
								<td>
									<select name="oadetail.detailFormat" style="width: 130px;"
										id="detailFormat">
										<option value="${oadetail.detailFormat}">
											${oadetail.detailFormat}
										</option>
<%--										<option value="">--%>
<%--											选择规格--%>
<%--										</option>--%>
									</select>
								</td>
								<th>
									物品单位
								</th>
								<td>
<%--									<s:select  name="oadetail.detailUnit" list="{'件','包','瓶','本','捆','斤','箱','桶','套','只','支','kg','张','部','条','副','根','个','双','对','卷','米','尺','平方米','立方米','公升','把','打','吨','盒','块','台','升'}" cssStyle="width:155px;" />--%>
										<select name="oadetail.detailUnit"  id="danwei" 	>
										<option value="${oadetail.detailUnit}">${oadetail.detailUnit}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									参考单价
								</th>
								<td>
									<input type="text" name="oadetail.detailBudgetMoney" id="detailBudgetMoney" value="${oadetail.detailBudgetMoney}" onblur="mustBeNumber('detailBudgetMoney')"/>
								</td>
								<th>
									申购数量
								</th>
								<td>
									<input type="text" name="oadetail.detailCount" id="detailCount" value="${oadetail.detailCount}" />
								</td>
							</tr>
							<tr>
								<th>
									到货期限
								</th>
								<td>
									<input class="Wdate" type="text" name="oadetail.detailArrDate" value="${oadetail.detailArrDate}"
										size="15" 
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<th>
									是否加急
								</th>
								<td>
								<s:if test="%{'不加急'==oadetail.detailIsBusy}">
								<input type="radio" id="jiaji1" onclick="chagebaoxiaoClass()"
										name="oadetail.detailIsBusy" value="不加急" checked="checked" />
									不加急
									<input type="radio" id="jiaji2" " name="oadetail.detailIsBusy" value="加急" />
									加急
								</s:if>
								<s:else>
								<input type="radio" id="jiaji1" onclick="chagebaoxiaoClass()"
										name="oadetail.detailIsBusy" value="不加急"  />
									不加急
									<input type="radio" id="jiaji2" " name="oadetail.detailIsBusy" value="加急" checked="checked" />
									加急
								</s:else>
								
								
									
								</td>
							</tr>
							<tr>
								<th>
									计划依据
								</th>
								<td colspan="3">
									<textarea cols="88" name="oadetail.detailPlanAcco" id="detailPlanAcco">${oadetail.detailPlanAcco}</textarea>
								</td>
							</tr>
								<tr>
									<td colspan="4" align="center">
										<input type="submit" value="提交" 
											style="width: 60px; height: 40px;" align="top">
										&nbsp;&nbsp;<!-- onclick="compareCount(this.form)" -->
										<input type="reset" value="取消"
											style="width: 60px; height: 40px;" align="top">
									</td>
								</tr>
								<tr>
									<td colspan="4" style="font-size: 12px;">
										备注：
										<br>
										1、采购申请前提是有部门预算申请，且预算申请可用余额大于申购金额。
										2、手动添加是单条提交申购明细，批量上传为上传EXCEL标准格式的申报明细。
										3、批量上传的前提是要先选择预算月份和预算科目。
										4、处理后统一跳转到个人申报历史记录里。
										5、审批通过后采购进行打印
										6、采购入库后预算完成，入库单价和数量均不可超过申购的单价和数量。
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
	<script type="text/javascript">
<%--		$(function(){--%>
<%--		getUnit("danwei");--%>
<%--	})--%>
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
function showProjectNO() {
	$("#projectNO").show();
	var tag = "projectNO";
	//下拉项目菜单
	$.ajax( {
		type : "POST",
		url : "ProjectManage_findAllProMan.action",
		dataType : "json",
		success : function(msg) {
			$("#detailItemId").append("<option value=''></option>");
			$.each(msg, function(i, n) {
				$("#detailItemId").append(
						"<option value='" + n.id + "'>" + n.projectNum + "("
								+ n.projectName + ")</option>");
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
	return true;
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
</script>

</html>
