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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/uploadify/uploadify.css">
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="test" style="position: absolute;right: 5px;top: 10px;">
					<canvas id="myCanvas" width="200" height="100"></canvas>
				</div>
				<form  enctype="multipart/form-data" method="post">  
				<table class="table">
				<s:if test="pageStatus!='noUpload'&&pageStatus!='view'">
							<tr>
								<td align="left">
									<h3>
										工艺规程文件
									</h3>
								</td>
								<td align="left">
									<input type="file" value="上传"  id ="accessory" name="accessory">
									<!-- <input type="button" id="fileButton_1"
										onclick="uploadFile(this,1)" value="上传附件"> -->
									<div id="fileDiv_1" style="display: none;">
									</div>
								<td align="left" colspan="3">
									<input type="hidden" name="id" value="${id}" id="id">
									<input type="hidden" name="pageStatus" value="process">
									<select name="quotedPriceFile.type" id="type">
										<option>
											工艺规范
										</option>
										<option>
											视频文件
										</option>
										<option>
											3D文件
										</option>
										<option>
											3D模型
										</option>
										<option>
											SOP文件
										</option>
										<option>
											SIP文件
										</option>
										<option>
											成型图
										</option>
										<option>
											其它文件
										</option>
									</select>
									<!-- <input type="submit" value="上传"
										style="width: 50px; height: 20px;"> -->
									<a href="javascript:$('#accessory').uploadify('upload','*')">上传</a>
									
								</td>
							</tr>
						</s:if>
				<tr id="spTr">
				 <td colspan="5" align="left">
				 <h3>视频</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="spFile">
				 <s:if test="#spFile.type=='视频文件'">
				  <tr>
				   <td align="left"> <video src="<%=path%>/upload/file/bjTz/${spFile.month}/<s:property value="#spFile.fileName"/>" controls="controls">您的浏览器不支持 video 标签。</video> </td>
				   <td align="left"><s:property value="#spFile.oldfileName"/></td>
				   <td align="left"><s:property value="#spFile.addTime"/></td>
				   <td align="left"><s:property value="#spFile.userName"/></td>	
				   <td><a href="QuotedPrice_DownloadProcessTz.action?id=${spFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'">
				    <input type="button" onclick="deletetz(${spFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?quotedPriceFile.id=${spFile.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				  </tr>
				 </s:if>
				</s:iterator>
				<tr id="qxTr">
				 <td colspan="5" align="left">
				 <h3>缺陷图纸</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="qxFile">
				 	<s:if test="#qxFile.type=='缺陷图纸'">
						<tr>
							<td align="left">
								<a
<%--									href="<%=path%>/upload/file/bjTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>">--%>
									href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/bjTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>">
									<img
										src="<%=path%>/upload/file/bjTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>"
										style="width: 80px; height: 80px;" />
								</a>
							</td>
							<td align="left">
								${oldfileName}
							</td>
							<td>
								<%--<a
									href="DownAction.action?fileName=${qxFile.fileName}&directory=/upload/file/bjTz/${qxFile.month}/">下载</a>&nbsp;&nbsp;
								--%><s:if test="pageStatus!='view'">
								 <input type="button" onclick="deletetz(${qxFile.id},${param.id})" value="删除"/>
<%--								<a--%>
<%--									href="ProcardTemplateAction!deleteProcessTz.action?quotedPriceFile.id=${qxFile.id}&&id=${param.id}&&pageStatus=${pageStatus}"--%>
<%--									onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a>--%>
								</s:if>
							</td>
						</tr>
					</s:if>
				</s:iterator>
				<tr id="3DTr">
				 <td colspan="5" align="left">
				 <h3>3D文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="file3D">
				 <s:if test="#file3D.type=='3D文件'">
				  <tr>
				   <td align="left"> <a href="<%=path%>/upload/file/bjTz/${file3D.month}/<s:property value="#file3D.fileName"/>"> <img src="<%=path%>/upload/file/bjTz/${file3D.month}/<s:property value="#file3D.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left"><s:property value="#file3D.oldfileName"/></td>
				   <td align="left"><s:property value="#file3D.addTime"/></td>
				   <td align="left"><s:property value="#file3D.userName"/></td>
				   <td><a href="QuotedPrice_DownloadProcessTz.action?id=${file3D.month}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'">
				   <input type="button" onclick="deletetz(${file3D.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?quotedPriceFile.id=${file3D.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
					</s:if>
				  </tr>
				 </s:if>
				</s:iterator>
				<tr id="gfTr">
				 <td colspan="5" align="left">
				 <h3>工艺规范</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="tzFile">
				 <s:if test="#tzFile.type=='工艺规范'">
				  <tr>
<%--				   <td align="left"><a  target="_showPri" href="<%=path%>/upload/file/bjTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"> <img src="<%=path%>/upload/file/bjTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a  target="_showPri" href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/bjTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"> <img src="<%=path%>/upload/file/bjTz/${tzFile.month}/<s:property value="#tzFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left">${oldfileName}</td>
				   <td align="left"><s:property value="#tzFile.addTime"/></td>
				   <td align="left"><s:property value="#tzFile.userName"/></td>
				   <td><a href="QuotedPrice_DownloadProcessTz.action?id=${tzFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'">
				    <input type="button" onclick="deletetz(${tzFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?quotedPriceFile.id=${tzFile.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				  </tr>
				 </s:if>
				 </s:iterator>
				<tr id="sopTr">
				 <td colspan="5" align="left">
				 <h3>SOP文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="sopFile">
				 <s:if test="#sopFile.type=='SOP文件'">
				  <tr>
				   <td align="left"><a  target="_showPri" href="<%=path%>/upload/file/bjTz/${sopFile.month}/<s:property value="#sopFile.fileName"/>"> <img src="<%=path%>/upload/file/bjTz/${sopFile.month}/<s:property value="#sopFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left">${oldfileName}</td>
				   <td align="left"><s:property value="#sopFile.addTime"/></td>
				   <td align="left"><s:property value="#sopFile.userName"/></td>
				   <td><a href="QuotedPrice_DownloadProcessTz.action?id=${sopFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'">
				    <input type="button" onclick="deletetz(${sopFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?quotedPriceFile.id=${sopFile.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				  </tr>
				 </s:if>
				 </s:iterator>
				<tr id="sapTr">
				 <td colspan="5" align="left">
				 <h3>SIP文件</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="sapFile">
				 <s:if test="#sapFile.type=='SIP文件'">
				  <tr>
				   <td align="left"><a  target="_showPri" href="<%=path%>/upload/file/bjTz/${sapFile.month}/<s:property value="#sapFile.fileName"/>"> <img src="<%=path%>/upload/file/bjTz/${sapFile.month}/<s:property value="#sapFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left">${oldfileName}</td>
				   <td align="left"><s:property value="#sapFile.addTime"/></td>
				   <td align="left"><s:property value="#sapFile.userName"/></td>
				   <td><a href="QuotedPrice_DownloadProcessTz.action?id=${sapFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'">
				    <input type="button" onclick="deletetz(${sapFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?quotedPriceFile.id=${sapFile.id}&&id=${param.id}&&pageStatus=${pageStatus}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				  </tr>
				 </s:if>
				 </s:iterator>
				 <tr id="mxTr">
				 <td colspan="5" align="left">
				 <h3>3D模型</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="modelFile">
				 <s:if test="#modelFile.type=='3D模型'">
				  <tr>
				   <td align="left"><a href="<%=path%>/upload/file/bjTz/${modelFile.month}/<s:property value="#modelFile.fileName"/>"> <img src="<%=path%>/upload/file/bjTz/${modelFile.month}/<s:property value="#modelFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left"><s:property value="#modelFile.oldfileName"/></td>
				   <td align="left"><s:property value="#modelFile.addTime"/></td>
				   <td align="left"><s:property value="#modelFile.userName"/></td>
				   <td><a href="QuotedPrice_DownloadProcessTz.action?id=${modelFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'">
				    <input type="button" onclick="deletetz(${modelFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?quotedPriceFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				  </tr>
				 </s:if>
				 </s:iterator>
				 <tr id="cxTr">
				 <td colspan="5" align="left">
				 <h3>成型图</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="cxFile">
				 <s:if test="#cxFile.type=='成型图'">
				  <tr>
<%--				   <td align="left"><a href="<%=path%>/upload/file/bjTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"> <img src="<%=path%>/upload/file/bjTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>--%>
				   <td align="left"><a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/bjTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"> <img src="<%=path%>/upload/file/bjTz/${cxFile.month}/<s:property value="#cxFile.fileName"/>"  style="width:80px;height:80px;"/></a> </td>
				   <td align="left"><s:property value="#cxFile.oldfileName"/></td>
				   <td align="left"><s:property value="#cxFile.addTime"/></td>
				   <td align="left"><s:property value="#cxFile.userName"/></td>
				   <td><a href="QuotedPrice_DownloadProcessTz.action?id=${cxFile.id}">下载</a>&nbsp;&nbsp;
				   <s:if test="pageStatus!='view'">
				    <input type="button" onclick="deletetz(${cxFile.id},${param.id})" value="删除"/>
<%--				   <a href="ProcardTemplateAction!deleteProcessTz.action?quotedPriceFile.id=${modelFile.id}&pageStatus=card&id=${param.id}" onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td>--%>
				 	</s:if>
				  </tr>
				 </s:if>
				 </s:iterator>
				</table>
				 </form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/uploadify/jquery.uploadify.min.js"></script>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		var fileDivHTML = "";
var count = 0;
<%--function uploadFile(obj, few) {--%>
<%--	var fileDiv = document.getElementById("fileDiv_" + few);--%>
<%--	if (obj.value == "上传附件") {--%>
<%--		fileDiv.style.display = "block";--%>
<%--		obj.value = "添加文件";--%>
<%--	}--%>
<%--	fileDivHTML = "<div id='file"--%>
<%--			+ count--%>
<%--			+ "'><input type='file' name='attachment'><a href='javascript:delFile("--%>
<%--			+ count + "," + few + ")'>删除</a></div>";--%>
<%--	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);--%>
<%--	count++;--%>
<%--}--%>

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}
function deletetz(fileId,id){
	if(window.confirm("确认要删除该条记录?")){
		window.location.href="QuotedPrice_deleteProcessTz.action?quotedPriceFile.id="+fileId+"&pageStatus=${pageStatus}&id="+id
	}
	
}
window.onscroll=function(){ 
	var oDiv=document.getElementById("test"); 
	oDiv.style.top=document.body.scrollTop + 10;  //控制上下位置
} 
$(function(){
	var c=document.getElementById("myCanvas");
	var ctx=c.getContext("2d");
 	ctx.strokeStyle = 'red';
	ctx.font="30px Arial";
	ctx.strokeText("${Users.code} ${Users.name}",10,50);
})

$(function() {
    $('#accessory').uploadify({
  		'method'   : 'post',
        'swf'      : '${pageContext.request.contextPath}/javascript/uploadify/uploadify.swf',
        'uploader' : 'QuotedPrice_updateQuotedPriceProcessTz.action;jsessionid=<%=session.getId()%>',//请求路径  
	    'auto' 	   : false, //是否自动上传 
	    'debug'    : false,
	    'fileObjName' : 'accessory',//和input的name属性值保持一致就好，Struts2就能处理了
	    'buttonText'     : '上传附件',//按钮上的文字
	    'onUploadStart' : function(file) {
	   		 var typeval=document.getElementById('type').value;
	   		 var idval=document.getElementById('id').value;
	  		 var obj={'quotedPriceFile.type' :typeval,'id':idval};
            $("#accessory").uploadify("settings", "formData", obj);
<%--        },--%>
<%--        'onUploadSuccess' : function (file, data, response){--%>
<%--        	alert(data);--%>
        }
	      
		/*'onUploadStart' : function(file) {
            
            $("#file_upload").uploadify("settings","formData",obj );
        }*/
	    /*
		'onFallback':function(){    
	            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");    
	        }, 
	        'onUploadSuccess' : function(file, data, response){//单个文件上传成功触发  
	                               //data就是action中返回来的数据  
	        },'onQueueComplete' : function(){//所有文件上传完成  
	        	alert("文件上传成功!");
	       		}  
		*/
        // Put your options here
    });
});	
		</script>
	</body>
</html>
