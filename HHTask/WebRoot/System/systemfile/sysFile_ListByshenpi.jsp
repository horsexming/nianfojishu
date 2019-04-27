<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/calendar/css/redmond/jquery-ui-1.8.1.custom.css">
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
	max-width: 500px;
	overflow: auto;
}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/calendar/jquery/jquery-ui-1.8.2.custom.min.js"></script>
	</head>
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
		<div style="display: none;">
			<div id ="dialogs" >
				<form method="post" id="applyForm">
					<p>申请备注：</p>
					<input type="hidden" value="" id="sFileId" name="id">
					<textarea rows="3" cols="40" name="remark" id="applyRemark"></textarea>
					<br>
				</form>
			</div>
		</div>
			<div align="center">
				<s:if test="pageStatus=='gjb'">
					<h3>
						国军标文件管理
					</h3>
				</s:if>
				<s:else>
					<h3>
						体系文件管理
					</h3>
				</s:else>
				<s:if test="tag=='no'">
					<form action="systemFileAction_findAllByUser.action" method="post">
				</s:if>
				<s:elseif test="tag=='all'">
					<form action="systemFileAction_findAllByshenpi.action"
						method="post">
				</s:elseif>
				<s:else>
					<form action="systemFileAction_findAllByupload.action"
						method="post">
				</s:else>
				<table class="table" align="center">
					<tr>
						<th>
							文件类型：
						</th>
						<td align="center">
							<s:if test="'hetong'==tag">
								<select name="systemFile.fileType" >
									<option value="合同类">合同类</option>
								</select>
							</s:if>
							<s:else>
								<select name="systemFile.fileType" id="fileType">
									<option value="${systemFile.fileType}">${systemFile.fileType}</option>
								</select>
							</s:else>
						</td>
						<th>
							文件编号：
						</th>
						<td align="center">
							<input type="text" name="systemFile.fileNo"
								value="${systemFile.fileNo}" />
						</td>
						<th>
							文件名称：
						</th>
						<td align="center">

							<input type="text" name="systemFile.fileName"
								value="${systemFile.fileName}" />
						</td>
					</tr>
					<tr>
						<th>
							文件等级：
						</th>
						<td align="center">
							<select name="systemFile.fileLevel" id="fileLevel">
								<option value="${systemFile.fileLevel }">${systemFile.fileLevel }</option>
							</select>
						</td>
						<th>
							部门归属：
						</th>
						<td align="center">


							<SELECT id="dept" name="systemFile.department">
								<option  value="${systemFile.department }">${systemFile.department }</option>
							</SELECT>

						</td>
						<th>
							上传人员：
						</th>
						<td align="center">
							<input type="text" name="systemFile.person"
								value="${systemFile.person}" />
						</td>

					</tr>
					<tr>
						<td align="center" colspan="6">
							<input type="hidden" value="${tags}" name="tags" />
							<input type="hidden" value="${pageStatus}" name="pageStatus" />
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询" />
							<input type="reset" style="width: 100px; height: 40px;"
								value="重置" />
						</td>
					</tr>
				</table>
				</form>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							文件类型
						</td>
						<td align="center">
							文件编号
						</td>
						<td align="center">
							文件名称
						</td>
						<td align="center">
							产品编码
						</td>
						<td align="center">
							文件等级
						</td>
						<td align="center">
							部门归属
						</td>
						<td align="center">
							文件描述
						</td>
						<s:if test="tags=='hetong'">
							<td align="center">
								项目编号
							</td>
						</s:if>
						<s:else>
							<td align="center">
								版本号
							</td>
						</s:else>
						<td align="center">
							上传人员
						</td>
						<td align="center">
							上传时间
						</td>
						<td align="center">
							状态
						</td>
						<td align="center" colspan="2">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<s:iterator value="systemFileList" id="sFile" status="pageStatus1">
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
							<s:if test="#pageStatus1.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus1.index+1" />
							</font>
						</td>
						<td align="center">
							${sFile.fileType}
							<input type="hidden" value="${sFile.fileType}" 
							id="fileType${pageStatus1.index}">
						</td>
						<td align="left">
							${sFile.fileNo}
						</td>
						<td align="left">
							${sFile.fileName}
						</td>
						<td align="center">
							${sFile.cpCode}
						</td>
						<td align="center">
							${sFile.fileLevel}
						</td>
						<td align="center">
							${sFile.department}
						</td>
						<td align="left" style="max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							
							<font size="1">${sFile.description}</font>
							<ul class="qs_ul">
								<li>
									${sFile.description}
								</li>
							</ul>
						</td>
						<s:if test="tags=='hetong'">
							<td align="center">
								${sFile.proCode}
							</td>
						</s:if>
						<s:else>
							<td align="center">
								${sFile.banben}
							</td>
						</s:else>
						
						<td align="center">
							${sFile.person}
						</td>
						<td align="center">
							${sFile.uploadDate}
						</td>
						<td align="center">
							${sFile.status}
						</td>
						<td colspan="2">
							
							<s:if test="#sFile.status!='归档'&&#sFile.status!='审批中'&&#sFile.status!='同意'&&tag!='no'">
								<a onclick="todelete(${sFile.id},'${cpage}','${pageStatus1}');">删除</a>
							</s:if>
							<s:if test="#sFile.fileUrl!=null">
								<!-- 显示预览文件 -->
								<span id="showFiles${pageStatus1.index}">
									<input type="hidden" id="fileInput${pageStatus1.index}" value="${sFile.fileUrl}">
									<input type="hidden" id="fileNameInput${pageStatus1.index}" value="${sFile.otherName}" />
								</span>
							</s:if>
							<s:else>无文件</s:else>
							<s:if test="#sFile.epId!=null">
								<s:if test="#sFile.canEpId!=null">
									<a href="CircuitRunAction_findAduitPage.action?id=${sFile.canEpId}">审批动态</a>
								</s:if>
								<s:else>
									<a href="CircuitRunAction_findAduitPage.action?id=${sFile.epId}">审批动态</a>
								</s:else>
							</s:if>
							<s:if test="#sFile.status=='同意' || #sFile.status=='反审失败'">
								<a onclick="guidang(${sFile.id});">/归档</a>
							</s:if>
							<s:if test="#sFile.fileType!='合同类'&&(systemFile==null || systemFile.id==null)">
								<a onclick="shengban(${sFile.id},'${cpage}','${pageStatus1}','${tags }')">升版</a>
								<a onclick="lishibanben(${sFile.id});">/历史版本</a>
								<s:if test="#sFile.loggingFile!=null">
									<a href="${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/sysFile/${sFile.loggingFile}" >文件更改单</a>
								</s:if>
								<s:if test="#sFile.status=='归档' "><!-- && (tag==null || (tag!='no' && tag!='all'))之前设置为申请人才可以申请反审，现在所有人都可以 -->
									<a  onclick="showDialog(${sFile.id })">申请反审</a>
								</s:if>
							</s:if>
							<s:if test="systemFile!=null && systemFile.id!=null">
								<s:if test="#sFile.loggingFile!=null">
									<a href="${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/sysFile/${sFile.loggingFile}" >文件更改单</a>
								</s:if>
							</s:if>
							<s:if test="#sFile.status=='未审批' || #sFile.status=='打回' || #sFile.status=='反审成功'">
								<a onclick="update(${sFile.id},'${cpage}','${pageStatus1}','${tags }')"	>修改</a>	
							</s:if>
							<s:if test="">
								
							</s:if>
						</td>

					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="13" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		
		<%@include file="/util/foot.jsp"%>
		<input type="hidden" id="listSize" value="${fn:length(systemFileList)}" >
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
	$(function() {
	 var tag = "dengji";
	$.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data:{
			tag:tag
		},
		dataType : "json",
		success : function(data) {
			$(data).each(function() {
				$("<option value='" + this.typeName+"'>" + this.typeName
								+ "</option>").appendTo("#fileLevel");
			});
			$("#fileLevel").tinyselect();
		}
	});
});
$(function() {
	 var tag = "leixing";
	 $.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data:{
			tag:tag
		},
		dataType : "json",
		success : function(data) {
			$(data).each(function() {
				$("<option value='" + this.typeName+"'>" + this.typeName
						+ "</option>").appendTo("#fileType");
			});
			$("#fileType").tinyselect();
		}
	});
});
$(function(){
	//显示查看
	var listSize = $("#listSize").val();
	if(listSize!=null && listSize!=0 && listSize!=""){
		for(var i=0;i<listSize;i++){
			var fileInput = $("#fileInput"+i).val();
			if(fileInput!=null && fileInput!=""){
				var str = "";
				if("合同类"==$("#fileType"+i).val()){
					var suffix = fileInput.split(",");
					var fileName = $("#fileNameInput"+i).val().split(",");
					for(var j=0;j<suffix.length;j++){
						var subSuffix = suffix[j].substring(suffix[j].indexOf("."));
<%--						if(subSuffix.indexOf("jpg")<0 && subSuffix.indexOf("pdf")<0 && subSuffix.indexOf("jpg")<0){--%>
<%--							str+="&nbsp;<a href='${pageContext.request.contextPath}/"+--%>
<%--							"upload/file/sysFile/"+suffix[j]+"'>下载</a>";--%>
<%--						}else{--%>
							str+="<a href='${pageContext.request.contextPath}/FileViewAction.action?"+
									"FilePath=/upload/file/sysFile/"+suffix[j]+"'>预览</a>&nbsp;";
<%--						}--%>
					}
				}else{
					var suffix = fileInput.substring(fileInput.indexOf("."));
					//alert(suffix);
<%--					if(suffix.indexOf("jpg")<0 && suffix.indexOf("pdf")<0 && suffix.indexOf("jpg")<0){--%>
<%--						str+="&nbsp;<a href='${pageContext.request.contextPath}/upload/file/sysFile/"+fileInput+"'>下载</a>";--%>
<%--					}else{--%>
						str+="<a href='${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/sysFile/"+fileInput+"'>预览</a>";
<%--					}--%>
				}
				$("#showFiles"+i).append(str);
			}
		}
	}
	
});
function todelete(id,cpage,tag) {
	if(confirm("确定要删除吗？")){
		window.location.href = "systemFileAction_delete.action?systemFile.id="+ id+"&cpage="+cpage+"&pageStatus="+tag;
	}
}
function toDetail(id, cpage) {
	window.location.href = "systemFileAction_toDetail.action?systemFile.id="
			+ id + "&cpage=" + cpage;
}
function guidang(id, cpage) {
	window.location.href = "systemFileAction_guidang.action?systemFile.id="
			+ id + "&cpage=" + cpage;
}
function lishibanben(id, cpage) {
	window.location.href = "systemFileAction_Querylishibanben.action?systemFile.id="
			+ id + "&cpage=" + cpage;
}
function update(id, cpage,pageStatus,tags) {
	window.location.href = "systemFileAction_toupdateByshenpi.action?systemFile.id="
			+ id + "&cpage=" + cpage+"&tags="+tags;
}
function shengban(id, cpage,pageStatus,tags){
	window.location.href = "systemFileAction_toShengBan.action?systemFile.id="
			+ id + "&cpage=" + cpage+"&tags="+tags;
}

function showDialog(id){
	$("#sFileId").val(id);
	$("#dialogs").dialog({
        buttons:{
        	"提交":function(){
        		var form = new FormData(document.getElementById("applyForm"));
        	   	$.ajax({
        		    url:"${pageContext.request.contextPath}/systemFileAction_applyReAudit.action",
        		    type:"post",
        		    data:form,
        		    processData:false,
        		    contentType:false,
        		    async : false, 
        		    dataType:"json",
        		    success:function(data){
        	        	alert(data);	
        	        	if(data.indexOf("申请成功")>=0){
        	        		window.location.reload();
        	        	}
        		    },
        		    error:function(e){
        		        alert("错误！！");
        		    }
        	    });
        	},
        	"取消":function(){$(this).dialog('close');}
        },
        title:"申请反审对话框",
        modal: true,// 创建模式对话框
        width: 300,
        height: "auto",
        position: { my: "center top", at: "center top", of: window }
    });
}

function submitreAuditApply(){
	
}

</script>
	</body>
</html>
