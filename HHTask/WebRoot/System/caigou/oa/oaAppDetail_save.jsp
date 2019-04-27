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

.tt-menu,
.gist {
  text-align: left;
}


.table-of-contents li {
  display: inline-block;
  *display: inline;
  zoom: 1;
}

.typeahead,
.tt-query,
.tt-hint {
  width: 550px;
  height: 30px;
  padding: 8px 12px;
  font-size: 14px;
  line-height: 30px;
  border: 2px solid #ccc;
  outline: none;
}

.typeahead {
  background-color: #fff;
}

.typeahead:focus {
  border: 2px solid #0097cf;
}

.tt-hint {
  color: #999
}

.tt-menu {
  width: 550px;
  margin: 12px 0;
  padding: 8px 0;
  background-color: #fff;
  border: 1px solid #ccc;
  border: 1px solid rgba(0, 0, 0, 0.2);
}

.tt-suggestion:hover {
  cursor: pointer;
  color: #fff;
  background-color: #0097cf;
}

.tt-suggestion.tt-cursor {
  color: #fff;
  background-color: #0097cf;

}

.tt-suggestion p {
  margin: 0;
}

.gist {
  font-size: 14px;
}

		</style>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<h3>
					${companyInfo.name}采购申报平台
				</h3>
				<div style="font-size: 13; color: red; font-weight: bold;">
					${message}
				</div>
				<form action="oaAppDetailAction!saveOADetail.action"
					enctype="multipart/form-data" id="oaform" method="post"
					onsubmit="return checkForm()">
					<input type="hidden" name="oadetail.appayTag"
						value="${oadetail.appayTag}">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th>申报类型</th>
								<td>
									<input type="radio" id="budgetDept" onclick="hiddenProjectNO()"
										name="oadetail.detailClass" value="普通申购" checked="checked" />
									普通申购
									<input type="radio" id="budgetDept2" onclick="showProjectNO()"
										name="oadetail.detailClass" value="项目申购" />
									项目申购
								</td>
								<th>项目编号</th>
								<td>
									<div id="projectNO" style="display: none;">
										<select name="oadetail.detailItemId" style="width: 150px;" 
											id="detailItemId">
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<th>物品类别</th>
								<td id="childClassParent">
									<select name="oadetail.detailChildClass" style="width: 130px;"
										id="detailChildClass"
										onMouseOver="createDept('detailChildClass','OAAppDetailTemplateAction!findOASelect.action?tag=detailChildClass&powerTag=${powerTag}')"
										onchange="selectGoodsName()" >
										<option value="">选择类别</option>
									</select>
								</td>
								<th>物品检索</th>
								<td id="detailName">
									<!-- <input id="detailAppName" name="oadetail.detailAppName" /> -->
									<input type="text" style="width: 550px; height: 30px" id="appName"
								class="search_text form-control typeahead" accesskey="s"
								tabindex="9" autocomplete="off" x-webkit-speech=""
								x-webkit-grammar="builtin:search" /><!-- onblur="blurAppName()"  -->
								<!-- <div class="tt-suggestion tt-selectable">&lt;span style='color: red'&gt;类别:&lt;/span&gt;办公类,
								编号:BG.000.000.010,物品名称:显示器,规格:30*30,单位:捆</div> -->
									<!-- <select id="selectAppName" onchange="changeAppName()"></select> -->
								</td>
							</tr>
							<tr>
								<th>
									物料编码
								</th>
								<td align="left" valign="top" style="width: 450px">
									<input type="text" id="wlcode" name="oadetail.wlcode" onblur="chooseWlcode()" />
									<span id="hint" style="color: red"></span>
								</td>
								<th>
									物品名称
								</th>
								<td>
									<input type="text" id="detailAppName" name="oadetail.detailAppName" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									物品规格
								</th>
								<td align="left" valign="top">
									<input type="text" id="detailFormat" name="oadetail.detailFormat" readonly="readonly"/>
								</td>
								<th>
									物品单位
								</th>
								<td>
									<input type="text" id="detailUnit" name="oadetail.detailUnit" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									参考单价
								</th>
								<td>
									<input type="text" name="oadetail.detailBudgetMoney"
										id="detailBudgetMoney" onblur="mustBeNumber('detailBudgetMoney')"/>
								</td>
								<th>
									申购数量
								</th>
								<td>
									<input type="text" name="oadetail.detailCount" id="detailCount" />
								</td>
							</tr>
							<tr>
								<th>
									到货期限
								</th>
								<td>
									<input class="Wdate" type="text" name="oadetail.detailArrDate"
										size="15"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<th>
									是否加急
								</th>
								<td>
									<input type="radio" id="jiaji1" onclick="chagebaoxiaoClass()"
										name="oadetail.detailIsBusy" value="不加急" checked="checked" />
									不加急
									<input type="radio" id="jiaji2" " name="oadetail.detailIsBusy"
										value="加急" />
									加急
								</td>
							</tr>
							<tr>
								<th>
									计划依据
								</th>
								<td colspan="3">
									<textarea cols="88" name="oadetail.detailPlanAcco"
										id="detailPlanAcco"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="button" value="提交"
										onclick="compareCount(this.form)"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
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
									4、处理后统一跳转到个人申报历史记录里。 5、审批通过后采购进行打印
									6、采购入库后预算完成，入库单价和数量均不可超过申购的单价和数量。
									7、物品检索为物料编码、物品名称、规格、单位的搜索
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
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/typeahead.js/typeahead.bundle.js"></script>
	<script type="text/javascript">	
	$("#selectAppName").hide();
function changeF() {  
   // 从下面的赋值可以的值，你在action 中只要得到name =“ccdd” 的值就可以了。  
    document.getElementById('ccdd').value=document.getElementById('detailFormat').
    options[document.getElementById('detailFormat').selectedIndex].value;  
}  
var text = "";
function writeSelect(obj) {
	text = text + String.fromCharCode(event.keyCode);
	var index = obj.selectedIndex; //序号，取当前选中选项的序号
	obj.options.length = 0;//清除所有option 
	obj.options.add(new Option(text, text)); //这个兼容IE与firefox
}
function selectSub() {
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
function hiddenProjectNO() {
	$("#projectNO").hide();
}
var inforDivHTML = "";
var lineCount = 1;
var begAddLineNum = 6;
//选择物品名称
function selectGoodsName() {
	var childClass = $("#detailChildClass").val();
	var goodsName = $("#detailAppName");
	//判断是不是项目
	var proStyle = $("#budgetDept2").val();//是不是项目申请
	var proNO = $("#detailItemId").val(); //项目id
	var url = "OAAppDetailTemplateAction!getDetailAppName.action?tag=" + childClass;

	if ("项目申购" == proStyle && "工装" == childClass) {
		url = "OAAppDetailTemplateAction!getDetailAppName.action?powerTag=project&tag="
				+ proNO;
	}
	if(childClass==""){
		/*$("#selectAppName").hide();
		$("#detailName .tinyselect").remove();
		$("#detailAppName").show();*/
		
	}else{
		//url = encodeURI(url);
		/*$("#selectAppName").empty();
		$("#appName").hide();
		$("#selectAppName").show();
		$("#detailName .tinyselect").remove();
		createDept('selectAppName', url);*/
	}
	
}
//选择规格
function selectFormat() {
	var proStyle = $("#budgetDept2").val();//是不是项目申请
	var proNO = $("#detailItemId").val(); //项目编号

	var childClass = $("#detailChildClass").val();
	var goodsName = $("#detailAppName").val();//物品名称
	var detailFormat = $("#detailFormat");
	var url = "oaAppDetailAction!findFormat.action?tag=" + childClass
			+ "&powerTag=" + goodsName;
	if ("项目申购" == proStyle && "工装" == childClass) {
		url = "oaAppDetailAction!findFormatByProject.action?tag=" + proNO
				+ "&powerTag=" + goodsName;
	}
	url = encodeURI(url);
	//$("#detailName .tinyselect").remove();
	createDept('detailFormat', url);
}
//提交验证
function checkForm() {
	var detailAppName = $("#detailAppName");//物品名称
	var detailChildClass = $("#detailChildClass");//物品类型
	var detailBudgetMoney = $("#detailBudgetMoney");//预算单价
	var detailCount = $("#detailCount");//数量
	var detailPlanAcco = $("#detailPlanAcco");//计划依据	

	if (detailChildClass.val() == "") {
		alert("物品类别不能为空!");
		detailChildClass.focus();
		return false;
	} else if (detailAppName.val() == "") {
		alert("物品名称不能为空!");
		detailAppName.focus();
		return false;
	} else if (detailBudgetMoney.val() == "") {
		alert("预算单价不能为空!");
		detailBudgetMoney.focus();
		return false;
	} else if (detailCount.val() == "") {
		alert("预算数量不能为空!");
		detailCount.focus();
		return false;
	} else if (detailPlanAcco.val() == "") {
		alert("计划依据不能为空!");
		detailPlanAcco.focus();
		return false;
	}
	return true;
}
//比较预算金额与申报金额大小
function compareCount(objForm) {
	if(checkForm()){
		objForm.submit();
	}
	
}
//上传文件
function strat(objForm) {
	document.getElementById("start").style.disabled = "disabled";
	startCheck();//验证
	objForm.action = "oaAppDetailAction!saveLotUpload.action";
	objForm.submit();
}
//------------------------------------------
var count = 0;
function ondiv(obj) {
	count++;
	obj.style.background = "gray";
};
function outdiv(obj) {
	obj.style.background = "#ffffff";
	hidediv()

}
function hidediv() {
	count--;
	if (count == 0) {
		var showAll = document.getElementById("showAll");
		showAll.style.visibility = "hidden";
	}

}
function init() {
	count++;
	var detailAppName = document.getElementById("detailAppName");
	var showAll = document.getElementById("showAll");

	showAll.style.top = getTop(detailAppName) + 20;
	showAll.style.left = getLeft(detailAppName);
	if (detailAppName != "") {
		getAllNames();
	}
	showAll.style.visibility = "visible";
}
//获取元素的纵坐标（相对于窗口）
function getTop(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += getTop(e.offsetParent);
	return offset;
}
//获取元素的横坐标（相对于窗口）
function getLeft(e) {
	var offset = e.offsetLeft;
	if (e.offsetParent != null)
		offset += getLeft(e.offsetParent);
	return offset;
}
function selectdiv(obj) {
	var detailAppName = document.getElementById("detailAppName");
	detailAppName.value = obj.innerHTML;

	var showAll = document.getElementById("showAll");
	showAll.style.visibility = "hidden";
	//onchange="selectFormat()"
	selectFormat();
}

//ajax获取所有的类似的全称   
function getAllNames() {
	$("#detailFormat").empty();
	document.getElementById("ccdd").value="";
	$.ajax( {
		type : "POST",
		url : "oaAppDetailAction!getNameBymingcheng.action",
		dataType : "json",
		data : {
			tag : $("#detailChildClass").val(),powerTag:$("#detailAppName").val()
		},
		success : function(data) {
			$("#showAll").empty();
			$(data).each(
				function (){
					$("#showAll").append("<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)'>"
						+this+"</div>");
				});
			}
		}
	);
}

function changeAppName(){
	var appName = $("#selectAppName").val();
	//根据物品名称查找物品信息
	$.ajax({
		type : "POST",
		url : "OAAppDetailTemplateAction!getTempByAppName.action",
		dataType : "json",
		data : {
			"template.detailAppName" : appName
		},
		success : function(data) {
			var format = data.detailFormat;
			var unit = data.detailUnit;
			$("#detailFormat").val(format);
			$("#detailUnit").val(unit);
		}
	});
	
	
}

var ModuleName =new Bloodhound({
  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  prefetch: '',
  remote: {
	url: 'OAAppDetailTemplateAction!searchAppName.action',
    prepare: function (query, settings) {
         settings.url += '?template.detailAppName=' + encodeURI(encodeURI(query))
         +"&template.detailChildClass="+encodeURI(encodeURI($("#detailChildClass").val()));
                     //alert(encodeURI(encodeURI($("#detailChildClass").val())));
                     $("#hint").empty();
          return settings;
       }
  }
});

$('#detailName .typeahead').typeahead({

	highlight:true
}, {
  name: 'names',
  display: function(data){
	//alert(data.detailChildClass);
	
	if(data.detailChildClass!=null ){
		var returns = "类别:"+data.detailChildClass//"<span style='color: red'>类别:</span>
			+",编号:"+data.wlcode
			+",物品名称:" + data.detailAppName
			+",规格:"+data.detailFormat
			+",单位:"+data.detailUnit;
		//var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
 		 //return returns.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
 		 //alert(returns);
		return returns;
	}
		//alert(returns);
  },
  //formatItem:function(item){alert(item); return item},
  source:ModuleName
}).on('typeahead:selected',function(evt,datum){  
    $('#wlcode').val(datum.wlcode); //用户从下拉中选择某项后，刷新那个只读的wlcode值
    $("#detailFormat").val(datum.detailFormat);
    $("#detailUnit").val(datum.detailUnit);
    $("#detailAppName").val(datum.detailAppName);
    $("#childClassParent .tinyselect").remove();
    $("#detailChildClass").attr("style","display: bolck");
    $("#detailChildClass").append("<option value='"+datum.detailChildClass+"' selected='true'>"+datum.detailChildClass+"</option>");
    //alert($(".tt-suggestion.tt-selectable").html());
});

//getTempByCode
function chooseWlcode(){
	$("#hint").empty();
	var wlcode = $("#wlcode").val();
	$.ajax({
		type : "POST",
		url : "OAAppDetailTemplateAction!getTempByCode.action",
		dataType : "json",
		data : {
			"template.wlcode" : wlcode
		},
		success : function(data) {
			if(data!=null){
				//$('#wlcode').val(data.); //用户从下拉中选择某项后，刷新那个只读的personId值
			    $("#detailFormat").val(data.detailFormat);
			    $("#detailUnit").val(data.detailUnit);
			    $("#detailAppName").val(data.detailAppName);
			    $("#childClassParent .tinyselect").remove();
			    $("#detailChildClass").attr("style","display: bolck");
			    $("#detailChildClass").append("<option value='"+data.detailChildClass+"' selected='true'>"+data.detailChildClass+"</option>");
			    $("#hint").empty();
			}else{
				$("#detailFormat").val("");
			    $("#detailUnit").val("");
			    $("#detailAppName").val("");
			    $("#childClassParent .tinyselect").remove();
			    $("#detailChildClass").attr("style","display: bolck");
			    $("#hint").append("物料编码有误！");
			}
		}
	});
	
}


</script>

</html>
