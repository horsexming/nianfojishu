<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>回复面板</title>
<%@include file="/util/sonHead.jsp"%>
<link rel="stylesheet" href="<%=basePath %>css/main.css"/>
<link rel="stylesheet" href="<%=basePath %>css/style.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery1.42.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.SuperSlide.2.1.1.js"></script>
</head>

<body>
    <!--header start-->
    <div id="header">
      <h1>回复面板</h1>
      <p></p>
    </div>
    <!--header end-->
    <!--content start-->
    <div id="say">
     <div class="weizi">
     <div class="wz_text">问题标题：<h1>${question.title}</h1>----分类:<h1>${question.type}</h1></div>
           </div>
         
     <div align="left">
     <a onclick="toAll()"  style="text-decoration:none;">
      	所有问题
     </a></div>
            <s:if test="errorMessage==null">
           <s:iterator value="answerList" id="list" status="pageStatus">   
          <ul class="say_box">
                     <div class="sy">
                         <p> ${list.answer}</p>
                         <p align="right">${list.person}</p>
                     </div>
                     
                  <span class="dateview">${list.time}</span>
          </ul>
          </s:iterator>
          	<div align="right">
       	第
		<font color="red"><s:property value="cpage" /> </font>/
		<s:property value="total" />
		页
		<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
		styleClass="page" theme="number" />
		</div>
	</s:if>
	<s:else>
		<div><p align="center">暂时无回复</p></div>
	</s:else>
     </div>
    <!--content end-->
    <!--footer-->
    <div id="footer" >
    <div style="height:10px"></div>
   <div style="position: relative;background-color: #E8E8E8;width: 800px;height: 350px;" >
   <center>
   <form action="QuestionAction_update.action" method="post">
		<p>
		<input type="hidden" name="question.id" value="${question.id}"/>
			<textarea id="content" name="question.answer" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;"></textarea>	
		</p>
		<div style="height:5px"></div>
		<div align="left">
			<input type="submit" value="回复" style="width:80px;height:30px;border:0 none;background-color:#F7F7F7;">
		</div>
	</form>
	</center>
	
	</div>
	</div>
    
    	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
		KindEditor.ready(function(K) {
		var editor1 = K.create('#content', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '800px',
			height: '300px',
			minWidth : 400,
			minHeight: 100,
			uploadJson : 'affixAction!upload.action',
            allowFileManager : false,
			items : [ 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
			afterCreate : function() {
				self = this;
			}
		});
	});
	var editor2;
	var html;
	var pId;
	$('#reviewButton').bind('click', function(){
		submit();
	});
	$("#container span[data-boardReview]").bind('click',function(){
		var parentId=$(this).attr("data-boardReview");
		if(pId){
			$("#"+pId+'boardReviewII').remove();
			$("span[data-boardReview='"+pId+"']").show();
			pId=parentId;
		}else{
			pId=parentId;
		}
		//var id=new Date().getMilliseconds();
		html='<div id="'+parentId+'boardReviewII"><textarea id="'+parentId+'content" name="boardReview.content" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;"></textarea>';
		html+='<p style="text-align: left;"><input type="button" style="background:#3CB371;cursor: pointer;" value="回复" data-boardReviewButton="'+parentId+'"/></p><div>';
		
		$(this).before($(html));
		editor2 = KindEditor.create('#'+parentId+'content', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '550px',
			height: '100px',
			minWidth : 400,
			minHeight: 100,
			uploadJson : 'affixAction!upload.action',
            allowFileManager : false,
			items : [  'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
			afterCreate : function() {
			}
		});
		$(this).hide();
		$("#container input[data-boardReviewButton='"+parentId+"']").bind('click',boardReviewButton);
	});
	function toAll() {
	window.location.href = "QuestionAction_findAll.action";
}
</script>
</body>
</html>

	

