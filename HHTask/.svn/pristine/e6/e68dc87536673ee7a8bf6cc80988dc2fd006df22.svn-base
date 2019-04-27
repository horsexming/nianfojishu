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
		<div id="gongneng" style="width: 100%; border: solid 1px #0170b8;">
			<div align="center">
				<span style="color:red;font-size:20px;algin:">${message}</span>
				<input type="hidden" value="${yfUser.usertype}" id="userType"/>
				<input type="hidden" id="projectId">
				<input type="hidden" id="status"/>
			</div>
			<!-- <div align="center"><h3>项目树管理</h3> </div> -->
			<div align="left">
				<!-- 显示树形研发项目 -->
				<div style="width: 30%; height: 100%; float: left;overflow: scroll" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<div style="border-left: 1px solid #000000; float: left; width: 58%">
					<div id="selectDiv" style="display: none;" align="center">
						<input id="module0" type="button" value="项目信息" onclick="chageModule(this,'0')"
							style="width: 80px; height: 50px;"/>
						<input id="module1" type="button" value="添加子项目" onclick="chageModule(this,'1')"
							style="width: 80px; height: 50px;"/>
						<input id="module2" type="button" value="修改项目" onclick="chageModule(this,'2')"
							style="width: 80px; height: 50px;"/>
						<input id="module" type="button" value="删除项目" onclick="delProject()"
							style="width: 80px; height: 50px;"/>
						<input id="module3" type="button" value="审批项目人员" onclick="chageModule(this,'3')"
							style="width: 90px; height: 50px;"/>
						<input id="module4" type="button" value="指派人员" onclick="chageModule(this,'4')"
							style="width: 90px; height: 50px;"/>
						<input id="module5" type="button" value="项目成员" onclick="chageModule(this,'5')"
							style="width: 90px; height: 50px;"/>
						
						<input id="module6" type="button" value="提交文件与方案" onclick="chageModule(this,'6')"
							style="width: 107px; height: 50px;display: none;" />
							
						<input id="module7" type="button" value="填报项目进度" onclick="chageModule(this,'7')"
							style="width: 90px; height: 50px;"/>
						<input type="hidden" id="delProject"/>
						<br />
					</div>
					<div>
						
					<div id="module1_0" style="display: none;">
						<table class="table" style="width: 100%;">
							<tr>
								<th align="center" colspan="2">
									项目信息
								</th>
							</tr>
							<tr>
								<th align="right" style="width: 50%;">项目编号:</th>
								<td align="left" style="width: 50%;"><span id="showProNum"></span></td>
							</tr>
							<tr>
								<th align="right">项目名称:</th>
								<td align="left"><span id="showProName"></span></td>
							</tr>
							<tr>
								<th align="right">开始时间:</th>
								<td align="left">
									<span id="showStartTime" ></span>
									<input type="hidden" id="startTime" />
								</td>
							</tr>
							<tr>
								<th align="right">负责人:</th>
								<td align="left"><span id="showPrincipals"></span></td>
							</tr>
							<tr>
								<th align="right">项目分数:</th>
								<td align="left">
									<span id="gradeStore"></span>
								</td>
							</tr>
							<tr>
								<th align="right">预完成时间:</th>
								<td align="left"><span id="showReTime"></span></td>
							</tr>
							<tr>
								<th align="right">状态:</th>
								<td align="left"><span id="showStatus"></span></td>
							</tr>
							<tr>
								<th align="right">描述:</th>
								<td align="left"><span id="showReMark"></span></td>
							</tr>
						</table>
						<br>
						<br>
						<table id="scheme" style="display: none">
							<tr >
								<td align="center"><span id="downloadFile"></span></td>
							</tr>
							<tr>
								<td><textarea rows="17" cols="108" id="schedule"></textarea></td>
							</tr>
						</table>
					</div>
					<div id="module1_1" style="display: none;">
						<form id="addForm" action="projectPoolAction_saveAndUpdateYf.action" 
							   method="post" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="yfrootId" name="projectManageyf.rootId" />
							<input type="hidden" id="yffatherId" name="projectManageyf.fatherId" />
							<input type="hidden" id="yfbelongLayer" name="projectManageyf.belongLayer" />
							<input type="hidden" id="yfpoolId" name="projectManageyf.poolId" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加子项目
									</th>
								</tr>
								<tr >
									<th align="right" style="width: 40%;">
										项目名称:
									</th>
									<td  style="width: 60%;">
										<input name="projectManageyf.proName" id="addProName">
									</td>
								</tr>
								<tr>
									<th align="right">
										项目分数:
									</th>
									<td>
										<input name="projectManageyf.gradeStore" id="addGradeStore" onkeyup="mustBeNumber('addGradeStore');showProportion(this,'add')">
										<span id="showStoreMessage" style="color:red"></span>	
									</td>
								</tr>
								<tr>
									<th align="right">开始时间:</th>
									<td>
										<input name="projectManageyf.startTime" id="addStartTime" class="Wdate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" >
									</td>
								</tr>
								<tr>
									<th align="right">
										预完成时间:
									</th>
									<td>
										<input name="projectManageyf.reTime" id="addReTime" class="Wdate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
									</td>
								</tr>
								<tr>
									<th align="right">
										描述:
									</th>
									<td>
										<textarea rows="3" cols="30" name="projectManageyf.remark"></textarea>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加" onclick="submitForm('addForm')"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置" style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_2" style="display: none;">
						<form id="updateForm" style="margin: 0px; padding: 0px;"
							   method="post" action="projectPoolAction_saveAndUpdateYf.action" >
							<input type="hidden" id="updateId" name="projectManageyf.id"/>
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										修改项目
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 40%;">
										项目名称:
									</th>
									<td  style="width: 60%;">
										<input name="projectManageyf.proName" id="updateProName">
									</td>
								</tr>
								<tr>
									<th align="right">
										项目分数:
									</th>
									<td>
										<input name="projectManageyf.gradeStore" id="updateGradeStore" onkeyup="mustBeNumber('addGradeStore');showProportion(this,'update')"/>
										<span id="showStoreMessage2" style="color:red"></span>	
									</td>
								</tr>
								<tr>
									<th align="right">开始时间:</th>
									<td>
										<input name="projectManageyf.startTime" id="updateStartTime" class="Wdate"
											 onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
									</td>
								</tr>
								<tr>
									<th align="right">
										预完成时间:
									</th>
									<td>
										<input name="projectManageyf.reTime" id="updateTeTime" class="Wdate"
											 onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
									</td>
								</tr>
								<tr>
									<th align="right">
										描述:
									</th>
									<td>
										<textarea rows="3" cols="30" id="updateRemark"
											 name="projectManageyf.remark"></textarea>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="button" value="修改"
											onclick="submitForm('updateForm')"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<!-- 审批候选项目 -->
					<div id="module1_3" style="display: none;">
						<input type="hidden" id="examineId" name="projectManageyf.id"/>
						<table class="table" id="examineTable">
							<tr>
								<th align="center" colspan="2">
									审批候选项目参与人
								</th>
							</tr>
							<tr>
								<td style="width:50%"></td>
								<td align="left" style="width:50%">
									<input type="checkbox" id="all" onchange="chageAllCheck()">
									全选
								</td>
							</tr>
						</table>
						<table class="table">
							<tr>
								<td align="center" colspan="2">
									<input type="button" value="同意" style="width: 80px; height: 50px;"
										onclick="examine('同意')"/>
									<input type="button" value="打回" style="width: 80px; height: 50px;"
										onclick="examine('打回')"/>
								</td>
							</tr>
						</table>
					</div>
					<div id="module1_4" style="display: none;">
						<input type="hidden" id="rootId" />
						<input type="hidden" id="zhipaiProjectId"/>
						<table class="table" id="zhipaiBind">
							<tr>
								<th align="center" colspan="2">
									指派项目参与人
								</th>
							</tr>
							<tr>
								<td style="width:50%"></td>
								<td align="left" style="width:50%">
									<input type="checkbox" id="zhipaiAll" onchange="zhipaiBindPlayers()">
									全选
								</td>
							</tr>
							
						</table>
						<table class="table">
							<tr>
								<td align="center" colspan="2">
									<input type="button" value="指派" style="width: 80px; height: 50px;"
										onclick="zhipai()"/>
								</td>
							</tr>
						</table>
					</div>
					<!-- 项目成员 -->
					<div id="module1_5" style="display: none;">
						<table class="table" id="canyurenTable">
							<tr>
								<th align="center" colspan="2">
									项目参与人
								</th>
							</tr>
							<tr>
								<td style="width:50%"></td>
								<td align="left" style="width:50%">
									<input type="checkbox" id="playerAll" onchange="projectPlayers()">
									全选
								</td>
							</tr>
							
						</table>
						<table class="table" id="cannelZhiPaiShow">
							<tr>
								<td align="center" colspan="2">
									<input type="button" value="取消指派" style="width: 80px; height: 50px;"
										onclick="cancelZhipai()"/>
								</td>
							</tr>
						</table>
					</div>
					<!-- 文件与方案 -->
					<div id="module1_6" style="display: none;">
						<form action="${pageContext.request.contextPath}/projectPoolAction_finalSubmitFile.action" method="post" enctype="multipart/form-data">
							<input type="hidden" name="projectManageyf.id" value="${id}"/>
							<table class="table" id="projectFile">
								<tr>
									<th align="center" colspan="2">
										文件与方案
									</th>
								</tr>
								<tr>
									<th>项目文件<input type="button" value="增加" onclick="addFile()"/></th>
	<%--								<td align="left" style="width:50%">--%>
	<%--									<span id="document"></span>--%>
	<%--								</td>--%>
								</tr>
								<tr>
									
									<td align="center"><input type="file" name="attachment" id="attachment_0"/></td>
								</tr>
							</table>
							<table class="table">
								<tr>
									<th>项目方案与完成计划</th>
								</tr>
								<tr>
									<td align="center">
										<textarea rows="10" cols="80" name="projectManageyf.schedule"></textarea>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="submit" value="提交" style="width: 80px; height: 50px;"
											onclick="submitFile()"/>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_7" style="display: none;">
						
					</div>
					<!-- 报选项目 -->
					<div id="applyChoose"  style="display: none;" align="center">
						<table class="table">
							<tr>
								<th align="center" colspan="2" >
									报选项目
								</th>
							</tr>
							<tr>
								<th width="50%" align="right">项目编号:</th>
								<td width="50%" align="left"><span id="proNum"></span></td>
							</tr>
							<tr>
								<th width="50%" align="right">项目名称:</th>
								<td width="50%" align="left"><span id="proName"></span></td>
							</tr>
							<tr>
								<th align="right">负责人:</th>
								<td align="left"><span id="principals"></span></td>
							</tr>
							<tr>
								<th align="right">预完成时间:</th>
								<td align="left"><span id="reTime"></span></td>
							</tr>
							<tr>
								<th width="50%" align="right">项目分数:</th>
								<td width="50%" align="left"><span id="gradeStore"></span></td>
							</tr>
							<tr>
								<th align="right">项目描述:</th>
								<td align="left"><span id="remark"></span></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="hidden" id="subProjectId"/>
									<input type="button" value="报选此项目" onclick="chooseSubProject()"
										style="width: 100px;height: 40px"/>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//========================================zTree显示
//自动组装树形结构
var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick,
		hover:false
	},
	view:{
		fontCss:getFont,
		nameIsHTML:true
	}
};
//加载树形数据
$(document).ready(loadTree());
function getFont(treeId,node){
	return node.font?node.font:{};
}
//生成
function loadTree() {
	$.ajax( {
		url : 'projectPoolAction_findProjectManageyfByRootId.action',
		type : 'post',
		dataType : 'json',
		data : {
			rootIdStr : '${id}'
		},
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(function(){
				var status = $(this).attr('status');
				var imgPath;
				if("完成"==status || "确认关闭"==status){
					imgPath = "<img src='${pageContext.request.contextPath}/images/mk/green.png' width='10px' height='10px'>已完成";
				}else if("执行中"==status){
					imgPath = "<img src='${pageContext.request.contextPath}/images/mk/yellow.png' width='10px' height='10px'>执行中";
				}else if("待填报"==status){
					imgPath = "<img src='${pageContext.request.contextPath}/images/mk/red.png' width='10px' height='10px'>待填报";
				}else{
					imgPath = "<img src='${pageContext.request.contextPath}/images/mk/red.png' width='10px' height='10px'>"+status;
				}
				zNodes.push({
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					rootId : $(this).attr('rootId'),
					belongLayer : $(this).attr('belongLayer'),
					name : $(this).attr('proName') + ' ' + $(this).attr('proNum')+"  "+imgPath,
					open : true,
					click : false
				});
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		},
		error : function() {
			alert("服务器异常!");
		}
	});

}

var oldObj;
var oldObj2;
//切换添加类型
function chageModule(obj, obj2) {
		$("#module1_0").hide();
		$("#module1_1").hide();
		$("#module1_2").hide();
		$("#module1_3").hide();
		$("#module1_4").hide();
		$("#module1_5").hide();
		$("#module1_7").hide();
		$("#scheme").hide();
		$("#module1_"+obj2).show('slow');
		$("#cannelZhiPaiShow").show();
		
		if(obj2==0){  //显示项目信息
			var projectId = $("#projectId").val();
			if(null!=projectId){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/projectPoolAction_findProjectManageyfById.action",
					data:{
						id:projectId
					},
					dataType:"json",
					success:function(data){
						if(null!=data){
							if(data.status='完成'){
								$("#scheme").show();
							}
						}
					}
				});
			}
			
		}else if(obj2==1){ //添加项目
			var d = new Date();
 			var day = d.getDate();        //获取当前日(1-31)
		    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
		    var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1)+'-'+day;
		    //document.getElementById('startTime').value=s;
		    $("#addStartTime").val(s);
		}else if(obj2==3){
			var examineId = $("#examineId").val();//项目id
			if(null!=examineId){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/projectPoolAction_selectChooseList.action",
					data:{
						id:examineId
					},
					dataType:"json",
					success:function(data){
						if(null!=data){
							$(".examineList").remove();
							for(var i=0;i<data.length;i++){
								if('同意'==data[i].status){
									$("#examineTable").append("<tr class='examineList'><td></td><td align='left'> " +
									"<input type='checkbox' name='addUserName' checked='checked' value='"+data[i].id+"'" +
									"  onchange='chageNum()'> "+data[i].addUserName+"</td></tr>");
								}else{
									$("#examineTable").append("<tr class='examineList'><td></td><td align='left'>" +
									"<input type='checkbox' name='addUserName' value='"+data[i].id+"' " +
									" onchange='chageNum()'> "+data[i].addUserName+"<span id='examine_"+i+"'></span></td></tr>");
								}
							}
						}
					}
				});
			}
		}else if(obj2==4){
			var examineId = $("#rootId").val();//项目id
			var proId = $("#projectId").val();
			if(null!=examineId ){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/projectPoolAction_getBindPlayers.action",
					data:{
						id:examineId,
						id2:proId
					},
					dataType:"json",
					success:function(data){
						if(null!=data){
							$(".examineList").remove();
							for(var i=0;i<data.length;i++){
								if('同意'==data[i].status){
									//$("#zhipaiBind").append("<tr class='examineList'><td></td><td align='left'> " +
									//"<input type='checkbox' name='addUserName' checked='checked' value='"+data[i].id+"' " +
									//" onchange='chageNum()'> "+data[i].addUserName+"</td></tr>");
								}else{
									$("#zhipaiBind").append("<tr class='examineList'><td></td><td align='left'>" +
									"<input type='checkbox' name='zhipaiUserList' value='"+data[i].id+"' " +
									" onchange='chageZhiPai("+i+")'> "+data[i].userName+"<span  id='zhipai_"+i+"'></span></td></tr>");
								}
							}
						}
					}
				});
			}
		}else if(obj2==5){//获得项目参与人→项目成员
			
			var projectId = $("#projectId").val();
			if(null!=projectId){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/projectPoolAction_findBindPlayers.action",
					data:{
						id:projectId
					},
					dataType:"json",
					success:function(data){
						if(null!=data){
							$(".canyurenList").remove();
							for(var i=0;i<data.length;i++){
								if("项目主管"==data[i].usertype){
									$("#canyurenTable").append("<tr class='canyurenList'><td></td><td align='left'>" +
									"<input type='checkbox' name='canyuren' value='"+data[i].id+"' disabled='disabled' " +
									" onchange='changePlayer()'> "+data[i].userName+"</td></tr>");
								}else{
									if(null==data[i].weight){
										$("#canyurenTable").append("<tr class='canyurenList'><td></td><td align='left'>" +
										"<input type='checkbox' name='canyuren' value='"+data[i].id+"' " +
										" onchange='changePlayer()'> "+data[i].userName+"</td></tr>");
									}else{
										$("#canyurenTable").append("<tr class='canyurenList'><td></td><td align='left'>" +
										"<input type='checkbox' name='canyuren' value='"+data[i].id+"' " +
										" onchange='changePlayer()'> "+data[i].userName+"<input type='text' disabled='disabled' value='"+data[i].weight+"' /></td></tr>");
									}
									
								}
							}
						}
					}
				});
			}
			
		}else if(obj2==6){
			var projectId = $("#projectId").val();
			if(null!=projectId){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/projectPoolAction_findProjectManageyfById.action",
					data:{
						id:projectId
					},
					dataType:"json",
					success:function(data){
						if(null!=data){
							$(".canyurenList").remove();
							for(var i=0;i<data.length;i++){
								$("#download")
								$("document").append("<a id='download' href='${pageContext.request.contextPath}/upload/file/"+
								+"'>下载文件</a>");
								if("项目主管"==data[i].usertype){
									$("#canyurenTable").append("<tr class='canyurenList'><td></td><td align='left'>" +
									"<input type='checkbox' name='canyuren' value='"+data[i].id+"' disabled='disabled' " +
									" onchange='changePlayer()'> "+data[i].userName+"</td></tr>");
								}else{
									$("#canyurenTable").append("<tr class='canyurenList'><td></td><td align='left'>" +
									"<input type='checkbox' name='canyuren' value='"+data[i].id+"' " +
									" onchange='changePlayer()'> "+data[i].userName+"</td></tr>");
								}
							}
						}
					}
				});
			}
			
		}else if(obj2==7){
			var projectId = $("#projectId").val();
			if(null!=projectId){
				$("#module1_7").load(
					"projectPoolAction_gotoFillSchedule.action?pageStatus=applychoose&id="+projectId,
						function(){ $("#module1_7").fadeIn(100);});
			}
			
		}
		
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var proId = treeNode.id;
	var name = treeNode.name;
	$("#scheme").hide();
	$.ajax({
		type:'POST',
		url:'projectPoolAction_findProjectManageyfById.action',
		dataType:'json',
		data: {id:proId} ,
		cache:true,
		success:function(data){
			if(data!=null){
				//显示添加选项的页面
				var userType = $("#userType").val();
				if("项目主管"==userType){
					//显示切换按钮
					
					$("#module1").show();
					$("#module2").show();
					$("#module3").show();
					$("#module4").show();
					$("#module5").show();
<%--					$("#module6").show();审批项目成员--%>
					$("#module7").show();
					$("#module").show();//删除项目
					
					//显示项目信息
					$("#showProNum").text(data.proNum);
					$("#showProName").text(data.proName);
					$("#showStartTime").text(data.startTime);
					$("#startTime").val(data.startTime);
					$("#showPrincipals").text(data.principals);
					if(null!= data.gradeStore){
						$("#gradeStore").text(data.gradeStore);
					}else{
						$("#gradeStore").text("");
					}
					$("#showReTime").text(data.reTime);
					$("#showStatus").text(data.status);
					$("#showReMark").text(data.remark);
					if("完成"==data.status || "确认关闭"==data.status){
						$("#module").hide();
						$("#module1").hide();
						$("#module2").hide();
						$("#module3").hide();
						$("#module4").hide();
						$("#module7").hide();
						$("#cannelZhiPaiShow").hide();
						$("#scheme").show('slow');
						$("#downloadFile").html("");
						if(null==data.yfProjectFile){
							$("#downloadFile").html("没有附件");
						}else{
							var files = data.yfProjectFile.split(",");
							for(var i=0;i<files.length;i++){
								$("#downloadFile").append("&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/upload/file/project/"+files[i]+"'>&nbsp;&nbsp;" +
								"查看附件("+files[i]+")</a>");
							}
								
						}
						$("#schedule").text(data.schedule);
					}else if("待提交"==data.status){
						$("#module6").show();
					}else{
						$("#scheme").hide();
					}
					
					//增加需要的字段
					$("#yfrootId").val(data.rootId);
					$("#yffatherId").val(data.id);
					$("#yfbelongLayer").val(data.belongLayer+1);
					//$("#yfpoolId").val(data.rootId);
					$("#yfpoolId").val(data.poolId);
					
					//修改需要的字段
					$("#updateId").val(data.id);
					$("#updateProName").val(data.proName);
					$("#updateGradeStore").val(data.gradeStore);
					$("#updateTeTime").val(data.reTime);
					$("#updateStartTime").val(data.startTime);
					$("#updateRemark").val(data.remark);
					$("#delProject").val(data.id);
					//审批项目
					$("#examineId").val(data.id);
					$("#selectDiv").show();
					
					//指派人员
					$("#rootId").val(data.rootId);
					$("#zhipaiProjectId").val(data.id);
					$("#projectId").val(data.id);
					
					//关闭已打开的功能Div
					//chageModule();
					$("#module1_1").hide();
					$("#module1_2").hide();
					$("#module1_3").hide();
					$("#module1_4").hide();
					$("#module1_5").hide();
					$("#module1_7").hide();
					$("#module1_0").show('slow');
					//$("#module1_2").hide();
				}else{
					
					$("#proNum").text(data.proNum);
					$("#proName").text(data.proName);
					$("#principals").text(data.principals);
					$("#reTime").text(data.reTime);
					$("#gradeStore").text(data.gradeStore);
					$("#remark").text(data.remark);
					$("#subProjectId").val(data.id);
					
					$("#applyChoose").show();

					//关闭已打开的功能Div
					//chageModule();
					//$("#module1_1").hide();
					//$("#module1_1").show('slow');
					//$("#module1_2").hide();
				}
				
			}else{
				alert("没有找到数据，请刷新后重试");
			}
		}
	});
	
	
}

function tijiao() {
	var tid = '${param.id}';
	 window.location.href="markIdAction!tijiao.action?gysMarkIdjiepai.id="+tid;
}

function submitForm(from){
	if("addForm"==from){
		var addProName = $("#addProName").val();
		if(null==addProName || ""==addProName){
			alert("请输入项目名称");
			 $("#addProName").focus();
			return ;
		}
		var addGradeStore = $("#addGradeStore").val();
		if(null==addGradeStore){
			alert("请输入项目分数");
			$("#addGradeStore").focus();
			return ;
		}
		var showReTime = $("#showReTime").text(); //父预完成时间
		var parentAddTime = $("#startTime").val(); //父开始时间
		var addReTime = $("#addReTime").val();	//预完成时间
		var addStartTime = $("#addStartTime").val();//添加时间
		
		var d1 = new Date(addReTime.replace(/\-/g, "\/"));  
 		var d2 = new Date(showReTime.replace(/\-/g, "\/"));
		var d3 = new Date(parentAddTime.replace(/\-/g, "\/"));
		var d4 = new Date(addStartTime.replace(/\-/g, "\/"));
		if(addReTime!= "" && showReTime!="" && d1>d2){
			alert("子项目预完成时间不能大于父项目完成时间");
			$("#addReTime").focus();
			return;
		}
		if(addReTime!= "" && addStartTime!="" && d1<d4){
			alert("预完成时间不能小于开始时间");
			return;
		}
		if(addStartTime!="" && parentAddTime!="" && d4<d3){
			alert("开始时间不能小于父项目开始时间");
			return ;
		}
		
		if(""!=parentAddTime && addReTime!= "" && d1<d3 ){
			alert("预完成时间不正确");
			$("#addReTime").focus();
		}
 		$("#addForm").submit();
	}else if("updateForm"==from){
		var proName = $("#updateProName").val();
		if(null==proName || ""==proName){
			alert("请输入项目名称");
			 $("#updateProName").facus();
			return ;
		}
		var showReTime = $("#showReTime").text(); //父预完成时间
		var parentAddTime = $("#startTime").val(); //父开始时间
		var addReTime = $("#updateTeTime").val();	//预完成时间
		var addStartTime = $("#updateStartTime").val();//添加时间
		
		var d1 = new Date(addReTime.replace(/\-/g, "\/"));  
 		var d2 = new Date(showReTime.replace(/\-/g, "\/"));
		var d3 = new Date(parentAddTime.replace(/\-/g, "\/"));
		var d4 = new Date(addStartTime.replace(/\-/g, "\/"));
		if(addReTime!= "" && showReTime!="" && d1>d2){
			alert("子项目预完成时间不能大于父项目完成时间");
			$("#addReTime").focus();
			return;
		}
		if(addReTime!= "" && addStartTime!="" && d1<d4){
			alert("预完成时间不能小于开始时间");
			return;
		}
		if(addStartTime!="" && parentAddTime!="" && d4<d3){
			alert("开始时间不能小于父项目开始时间");
			return ;
		}
		
		if(""!=parentAddTime && addReTime!= "" && d1<d3 ){
			alert("预完成时间不正确");
			$("#addReTime").focus();
		}
		$("#updateForm").submit();
	}else{
		alert("没有找到这个按钮");
	}
	
}

function delProject(){
	var id = $("#delProject").val();
	if(null!=id){
		if(confirm("确定要删除这个项目吗？")){
			location.href="${pageContext.request.contextPath}/projectPoolAction_delProject.action?pageStatus=${pageStatus}&id2="+id;
		}
	}
}

function chooseSubProject(){
	var subProjectId = $("#subProjectId").val();
	location.href="${pageContext.request.contextPath}/projectPoolAction_chooseSubProject.action?pageStatus=${pageStatus}&id="+subProjectId;
}

function examine(param){
	//var examineId = $("#examineId").val();
	var chk_value =[]; 
	var checkout ="";
	$("input[name='addUserName']:checked").each(function(){ 
		chk_value.push($(this).val());
		var erId = $(this).val();
		//var userName = $(this).parent().text();
		//alert(userId+userName);
		//alert(proejctYfUserId);
				//var obj =this;
		var weight = $(this).next().find("input").val();
		$.ajax({
			type:"post",
			url:"projectPoolAction_examineProject.action",
			dataType:'json',
		    cache:false, 
      		async:false, 
			data:{
				"er.id":erId,
				"er.status":param,
				"id":${id},
				"weight":weight
			},
			success:function(data){
				//if("success"==data){
					checkout = data;
					//alert(data);
				//}else{
					//alert(data);
				//}
			},
			error:function(){
				//alert("不好意思，出错了哦，刷新重试一下吧");
			}
		});
	}); 
	alert(checkout);
}

//全选→审批项目申请人员
function chageAllCheck(){
	var checkAll=document.getElementById("all");
	var checkboxs=document.getElementsByName("addUserName");
	if(checkAll.checked==true){
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
			$("#examine_"+i).html("<span id='span_examine_"+i+"'><input type='text' name='' onkeyup=\"mustBeNumber('examine_input_"+i+"')\" " +
			"id='examine_input_"+i+"' style='width:35px;' value='50' >%");
		}
	}else{
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=false;
			$("#zhipai_"+i).children("#zhipai_span_"+i).remove();//
		}
	}
}

//全选→直接绑定参与人员
function zhipaiBindPlayers(){
	var checkAll=document.getElementById("zhipaiAll");
	var checkboxs=document.getElementsByName("zhipaiUserList");
	if(checkAll.checked==true){
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
			//设置人员权重
			$("#zhipai_"+i).html("<span id='zhipai_span_"+i+"'><input type='text' name='' style='width:35px;' value='50' >%");
		}
	}else{
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=false;
			$("#zhipai_"+i).children("#zhipai_span_"+i).remove();//
		}
	}
}

//项目成员就
function projectPlayers(){
	var checkAll=document.getElementById("playerAll");
	var checkboxs=document.getElementsByName("canyuren");
	if(checkAll.checked==true){
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
			
		}
	}else{
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=false;
		}
	}
}
//选择单个
function chageNum(){
	var checkAll=document.getElementById("all");
	var checkboxs=document.getElementsByName("addUserName");
	var count=0;
	for(var i=0;i<checkboxs.length;i++){
		if(checkboxs[i].checked==false){
			checkAll.checked=false;
			$("#zhipai_"+i).children("#zhipai_span_"+i).remove();//
		}else{
			count++;
			$("#examine_"+i).html("<span id='span_examine_"+i+"'><input type='text' name='' onkeyup=\"mustBeNumber('examine_input_"+i+"')\" " +
			"id='examine_input_"+i+"' style='width:35px;' value='50' >%");
		}
	}
	if(count==checkboxs.length){
		checkAll.checked=true;
	}
}
function chageZhiPai(index){
	//设置全选按钮
	var checkAll=document.getElementById("zhipaiAll");
	var checkboxs=document.getElementsByName("zhipaiUserList");
	var count=0;
	for(var i=0;i<checkboxs.length;i++){
		if(checkboxs[i].checked==false){
			checkAll.checked=false;
			$("#zhipai_"+i).children("#zhipai_span_"+i).remove();//
		}else{
			count++;
			//设置人员权重
			$("#zhipai_"+i).html("<span id='zhipai_span_"+i+"'><input type='text' name='' onkeyup=\"mustBeNumber('zhipai_input_"+i+"')\" " +
			"id='zhipai_input_"+i+"' style='width:35px;' value='50' >%");
			
		}
	}
	if(count==checkboxs.length){
		checkAll.checked=true;
	}
	
}
function changePlayer(){
	var checkAll=document.getElementById("playerAll");
	var checkboxs=document.getElementsByName("canyuren");
	var count=0;
	for(var i=0;i<checkboxs.length;i++){
		if(checkboxs[i].checked==false){
			checkAll.checked=false;
			return;
		}else{
			count++;
		}
	}
	if(count==checkboxs.length){
		checkAll.checked=true;
	}
}

//直接指派项目成员
function zhipai(){
	//var examineId = $("#examineId").val();
	var chk_value =[]; 
	var checkout ="";
	$("input[name='zhipaiUserList']:checked").each(function(){ 
		chk_value.push($(this).val());
		var projectyfUserId = $(this).val();
		var id = $("#zhipaiProjectId").val();
		var rootId = $("#rootId").val();
		var weight = $(this).next().find("input").val();
		
		$.ajax({
			type:"post",
			url:"projectPoolAction_directBindPlayers.action",
			dataType:'json',
		    cache:false, 
      		async:false, 
			data:{
				"yfuserMiddle.yfUserId":projectyfUserId,
				"yfuserMiddle.projectManagerYfId":rootId,
				"id":id,
				"weight":weight
			},
			
			success:function(data){
				checkout = data;
			},
			error:function(){
				checkout = "不好意思，出错了哦，刷新重试一下吧";
				//alert("不好意思，出错了哦，刷新重试一下吧");
			}
		});
	}); 
	alert(checkout);
}

//取消指派
function cancelZhipai(){
	var chk_value =[]; 
	var checkout ="";
	$("input[name='canyuren']:checked").each(function(){ 
		chk_value.push($(this).val());
		var projectyfUserId = $(this).val();
		var id = $("#projectId").val();
		var obj = this;
		$.ajax({
			type:"post",
			url:"projectPoolAction_cancelBindPlayers.action",
			dataType:'json',
		    cache:false, 
      		async:false, 
			data:{
				"yfuserMiddle.yfUserId":projectyfUserId,
				"yfuserMiddle.projectManagerYfId":id
			},
			success:function(data){
				checkout = data;
				if(data=='取消成功'){
					$(obj).parent().parent().remove();
				}
			},
			error:function(){
				alert("取消异常");
			}
		});
	}); 
	alert(checkout);
}

function showProportion(obj,tag){
	var store =obj.value;
	var proId = $("#projectId").val();
	if(store!=null&& store!=""){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/projectPoolAction_getProPercentnm.action",
			dataType:"json",
			data:{
				"id":proId,
				"store":store,
				"tag":tag
			},success:function(data){
				if('add'==tag){
					$("#showStoreMessage").text("本项目分数占该层分数"+data); 
					
				}else{
					$("#showStoreMessage2").text("本项目分数占该层分数"+data); 
				}
			}
		});
	}
	
}

function showStoreMessage(flag){
	
	
}

var fileIndex=0;
function addFile(){
	fileIndex++;
	$("#projectFile").append("<tr id='attachment_"+fileIndex+"'><td align='center'><input type='file' name='attachment'  >" +
	"<input type='button' value='删除' onclick='delFile("+fileIndex+")'/></td></tr>");
}
function delFile(delIndex){
	$("#attachment_"+delIndex).remove();
}
</script>
	</body>
</html>
