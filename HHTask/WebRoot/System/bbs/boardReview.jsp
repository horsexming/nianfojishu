<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
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
		<title>${board.name}</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
			Date.prototype.format = function(format)
			{
			    var o = {
			    "M+" : this.getMonth()+1, //month
			    "d+" : this.getDate(),    //day
			    "h+" : this.getHours(),   //hour
			    "m+" : this.getMinutes(), //minute
			    "s+" : this.getSeconds(), //second
			    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
			    "S" : this.getMilliseconds() //millisecond
			    }
			 
			    if(/(y+)/.test(format))
			    {
			        format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
			    }
		     
			    for(var k in o)
			    {
			        if(new RegExp("("+ k +")").test(format))
			        {
			            format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
			        }
			    }
			    return format;
			}
		</script>
		<style type="text/css">
			body {
				text-align: center;
			}
			#center { margin-right: auto; margin-left: auto; }
			#board{
				border-color: black;
				border-width:10px;
				border-style: inset;
				
			}
			#head{
				font-size: 20px;
				font-family: serif;
				font-style: normal;
				margin-top: 40px;
			}
			#head a{
				text-decoration: none;
			}
			#head a:hover{
				color: red;
				cursor: pointer;
			}
			#container{
				padding-left: 0px;
				padding-right: 300px;
			}
			#container>div:hover {
				background:  #d3d3d3;
			}
			#container>#publish:hover {
				background: #F0F7FC;
			}
			
		</style>

	</head>

	<%--<body><h1 style="text-align:center;">	<span style="line-height:1.5;">${board.name}</span></h1>${board.content }</body>
--%>
	<body>
		<!-- 返回页面顶部 -->
		<!-- 返回页面顶部 -->
		<!-- 内容 -->
		<div id="center" style="width:960px;">
			<!-- 电子屏幕 -->
			<div id="board">
				<h1 style="text-align:center;">	<span style="line-height:1.5;">${board.name}</span></h1>
				<hr style="border-bottom-style: dotted" />
				${board.content }
			</div>
			<!-- 电子屏幕 -->
			<!-- 评论回复 -->
			<div id="head">
			<%--
			<form action="affixAction!upload.action" enctype="multipart/form-data" method="post">
				<input type="file" value="上传" name="fileList" />
                <input type="submit" value= "提交12" id="submit12"/>
			</form>
				--%>
			<%--	<form action="File_save.action" enctype="multipart/form-data" method="post">
				<input type="file" value="上传" name="imgpath" />
                <input type="submit" value= "提交12" id="submit12"/>
			</form>--%>
				<span style="float:left;">共有<em>${count}</em>条评论&nbsp;&nbsp;<a style="font-size:15px;">最后评论时间(<s:date name="lastBoardReviewDate" format="MM-dd HH:mm"/>)</a></span>
				<span style="float:right;">&nbsp;<a href="boardReviewAction!getBoardReviewPage.action?boardReview.boardId=${board.id}">显示最新评论</a></span>
				<div style="clear: both"></div>
			</div>
			<hr style="border-bottom-style:solid;" />
			<!-- 评论回复 -->
			<!-- 评论容器 -->
			<div id="container">
				<!-- 单条-->
				<s:iterator id="br" value="boardReviewList" status="st">
				<div name="boardReview">
					<!-- 评论 -->
					<div  style="text-align: left;">
						<div style="float: right;text-align: right;width: 20%">
						<p>${br.createUser.name}:用户名</p>
						<p><s:date name="createDate" format="MM-dd HH:mm"/>:时间</p>
						</div>
						<div style="float: left;width: 80%">
							<p>${br.content }</p>
						</div>
						<div style="clear: both;"></div>
					</div>
					<!-- 评论 -->
					<!-- 回复 -->
					<s:iterator id="subBr" value="subBoardReviewList" status="subSt">
					<div name="boardReview">
						<!-- 评论 -->
						<div  style="text-align: left;">
							<div style="float: left;text-align: left;width: 20%">
							<p>用户名：${subBr.createUser.name}</p>
							<p>时间：<s:date name="createDate" format="MM-dd HH:mm"/></p>
							</div>
							<div style="float: right;width: 80%">
								<p>${subBr.content }</p>
							</div>
							<div style="clear: both;"></div>
						</div>
						<!-- 评论 -->
					</div>
					</s:iterator>
					<!-- 回复 -->
					<!-- 回复按钮 -->
					<div style="text-align:left;position: relative">
						<span data-boardReview="${br.id}" style="cursor: pointer;color: green;">回复</span>
					</div>
					<!-- 回复按钮 -->
					
				</div>
				</s:iterator>
			
				<!-- 单条-->
				<!-- 发表 -->
				
				<div id="publish">
					<table>
						<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
					</tr>
					</table>
					<div style="position: relative;left: 0px;top: 0px;">
					<p>
						<form id="reviewForm" action="">
							<input type="hidden" id="boardId" name="boardReview.boardId" value="${board.id}"/>
							<textarea id="content" name="boardReview.content" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;"></textarea>	
						</form>
					</p>
					<p style="text-align: left;"><input id="reviewButton" name="reviewButton" type="button" style="background:#3CB371;" value="发表评论"></p>
					</div>
				</div>
				<!-- 发表 -->
			</div>
			<!-- 评论容器 -->
		</div>
		<!-- 内容-->
		
		
	</body>
</html>
<script type="text/javascript">
	
	
$(function(){
	var self;
	function submit(){
		self.sync();
		var content=$('#content').val();
		var isNull=/^[\s]*$/;
		if(isNull.test(content)){
			alert('请填写内容！');
			return;
		}
		$.ajax({
			type: "post",
			url: "boardReviewAction!addBoardReview.action",
			data: $('#reviewForm').serialize(),
			dataType : "json",
			success: function(data){
				 var boardReview=data.data;
				 var userName=boardReview.createUser.name;
				 var createDate=new Date(boardReview.createDate).format('MM-dd hh:mm');
				 var content=boardReview.content;
				 var boardId=boardReview.boardId;
				 var id=boardReview.id;
				if(data.success){
					var html=
						"<div >"
							<!-- 评论 -->
							+"<div id='' style='text-align: left;'>"
								+"<div style='float: right;text-align: right;width: 20%'>"
								+"<p >"+userName+":用户名</p>"
								+"<p >"+createDate+":时间</p>"
								+"</div>"
								+"<div style='float: left;width: 80%'>"
								+	'<p>'+content+'</p>'
								+"</div>"
								+"<div style='clear: both;'></div>"
							+"</div>"
							<!-- 评论 -->
							<!-- 回复 -->
							+'<div style="text-align:left;position: relative">'
								+'<span data-boardReview="'+id+'" style="cursor: pointer;color: green;">回复</span>'
							+"</div>"
							<!-- 回复 -->
						+"</div>";
					$("#container").prepend($(html));
					$('#content').val('');
					self.html('');
					
					$("#container span[data-boardReview='"+id+"']").bind('click',function(){
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
				}//if
			}
		});
	}
	KindEditor.ready(function(K) {
		var editor1 = K.create('#content', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '550px',
			height: '100px',
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
	//$("#container div[name='boardReview']").bind('mouseenter',function(){
	//});
	//$("#container div[name='boardReview']").bind('mouseleave',function(){
	//});
	
	var boardReviewButton=function(){
		editor2.sync();
		var parentId=$(this).attr("data-boardReviewButton");
		var content=$('#'+parentId+'content').val();
		var isNull=/^[\s]*$/;
		if(isNull.test(content)){
			alert('请填写内容！');
			return ;
		}
		$.ajax({
			type: "post",
			url: "boardReviewAction!addBoardReview.action",
			data: {'boardReview.content':content,'boardReview.parentId':parentId},
			dataType : "json",
			success: function(data){
				 var boardReview=data.data;
				 var userName=boardReview.createUser.name;
				 var createDate=new Date(boardReview.createDate).format('MM-dd hh:mm');
				 var content=boardReview.content;
				 var boardId=boardReview.boardId;
				 var parentId=boardReview.parentId;
				if(data.success){
					var html=
						"<div name='boardReview'>"
							<!-- 评论 -->
							+"<div id='' style='text-align: left;'>"
								+"<div style='float: left;text-align: left;width: 20%'>"
								+"<p >用户名："+userName+"</p>"
								+"<p >时间："+createDate+"</p>"
								+"</div>"
								+"<div style='float: right;width: 80%'>"
								+	'<p>'+content+'</p>'
								+"</div>"
								+"<div style='clear: both;'></div>"
							+"</div>"
							<!-- 评论 -->
						+"</div>";
					$("#container span[data-boardReview='"+parentId+"']").parent().before($(html));
					$("#"+parentId+'boardReviewII').remove();
					$("span[data-boardReview='"+parentId+"']").show();
				}//if
			}
		});
	};
});
</script>
