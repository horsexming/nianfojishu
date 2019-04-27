<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	//公告
	$.ajax( {
		url : "NoticeAction!show.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(useradsfa) {
			$("#show").empty();//清空
		var message = "";
		$(useradsfa).each(function(i, n) {
			message += (i + 1) + "、" + n.content + "&nbsp;&nbsp;&nbsp;&nbsp;";
		});
		$("#show").html(message);
	}
	});

	//系统消息提醒
	$.ajax( {
		type : "POST",
		url : "AlertMessagesAction!findAMCountByUid.action",
		dateType : "json",
		success : function(msg) {
			if (msg > 0) {
				$("#pmImg").show();
				$("#messageCount").text("消息(" + msg + ")");
				$("#messageCount").css( {
					color : "red"
				});
				$("#messageCount").bind("mouseover", function() {
					$("#messageCount").css("background-color", "#696969");
				})
				$("#messageCount").bind("mouseout", function() {
					$("#messageCount").css("background-color", "#ffffff");
				})
			}
		}
	});

	//========================================zTree显示
	//自动组装树形结构
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : onClick
		}
	};
	//读取树形数据
	$(document).ready(function() {
		$.ajax( {
			url : 'ModuleFunctionAction!findAllMfForJson.action',
			type : 'post',
			dataType : 'json',
			cache : true,
			success : function(doc) {
				var zNodes = [];
				$(doc).each(function() {
					zNodes.push( {
						id : $(this).attr('id'),
						pId : $(this).attr('fatherId'),
						name : $(this).attr('functionName'),
						click : false
					});

				});
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			}
		});

	});
	//点击回调函数
	function onClick(event, treeId, treeNode, clickFlag) {
		$('#workMainIframe').attr(
				"src",
				"ModuleFunctionAction!findMfByIdForJump.action?id="
						+ treeNode.id);

	}
	//=================================== zTree显示结束

	//获取邮箱已读未读
	$.ajax({
		type:"POST",
		url:"JavaMailAction!getMailInfo.action",
		dataType:"json",
		success:function(data){
			if(data!=null){
				$("#allMail").text(data[0]);
				$("#unReadMail").text(data[1]);
				$("#allMail").parent().attr("href",data[2]);
				$("#unReadMail").parent().attr("href",data[2]);
			}else{
				//$("#allMail").parent().html("");
				//$("#unReadMail").parent().html("");
			}
		},error:function(){
			//$("#allMail").parent().remove();
			//$("#unReadMail").parent().remove();
		}
	});
});
</script>
<div class="header">
	<div class="header_top fr ">
		<a href="newIndex.jsp?url=userCenter/userManage.jsp">欢迎您,${sessionScope.Users.name}！</a>
		<%--<a href="newIndex.jsp?url=userCenter/userManage.jsp"
					class="grzx fff">个人中心</a>
				--%>
		<a href="newIndex.jsp?url=userCenter/updatePassword.jsp">修改密码</a>
		<img id="pmImg" src="images/33.gif" width="18px" height="18px"
			style="display: none;" />
		<a id="messageCount"
			href="newIndexxk.jsp?url=AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=no">消息
			(0)</a>
		<!-- 邮件显示开始 -->
		<a href="${pageContext.request.contextPath}/UsersAction!perfectUsers.action" target="_blank" style="color: red"><img src="img/icon_12.jpg"
									height="13" width="14"><span id="allMail"></span></a>
		<a href="${pageContext.request.contextPath}/UsersAction!perfectUsers.action" target="_blank" style="color: red"><img src="img/icon_11.jpg"
									height="13" width="14"><span id="unReadMail"></span> </a>
		<!-- 邮件显示结束 -->
		<a id="nav-add-favorite" href="javascript:;">收藏本站</a>
		<a href="logOff.jsp">退出</a>
	</div>
	<div class="spanb"></div>
	<div class="search">
		<a class="a" href="ModuleFunctionAction!findMfByUser.action?pageStatus=">简约首页</a>
		<a class="a" href="ModuleFunctionAction!findMfByUser.action?pageStatus=qx">清新首页</a>
		<a class="a" href="ModuleFunctionAction!findMfByUser.action?pageStatus=xk">炫酷首页</a>
		<form action="ModuleFunctionAction!searchModuleFunction.action"
			method="post" style="margin-left: 0px; padding: 0px;"
			target="workMain">
			<div
				style="background: url('images/ss.png') no-repeat; width: 578px; height: 45px; margin-left: 109px;">
			</div>
			<%--<input type="text" name="moduleFunction.functionName"
						class="search_text" accesskey="s" tabindex="9" autocomplete="off"
						autofocus="true" x-webkit-speech=""
						x-webkit-grammar="builtin:search">
					<input type="submit" value="搜索" class="search_bt" />
				--%>
		</form>
	</div>
	<div class="spana"></div>
	<div class="header_bt">
		<div style="float: left; width: 70px; color: #47df00;">
			<strong>公告：</strong>
		</div>
		<div style="float: left; width: 928px">
			<marquee direction="left" scrollamount="5" onmouseout="this.start()"
				onmouseover="this.stop()">
				<font color="#47df00" id="show"></font>
			</marquee>
		</div>
	</div>
</div>