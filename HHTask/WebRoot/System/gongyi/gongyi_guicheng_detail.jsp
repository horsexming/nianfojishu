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
		<style type="text/css">
.table th,.table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
	height: 24px;
}

#gongyiNav {
	width: 100%;
	background: #666666;
	color: #FFFFFF;
	font-family: 黑体;
	text-align: center;
	font-size: 13px;
	font-weight: bold;
}

#gongyiNav a,span {
	border-left: #CCCCCC 1px solid;
	display: inline-block;
	height: 40px;
	line-height: 40px;
	padding: 0px 10px;
	margin-left: -8px;
}

#gongyiNav a:link,a:visited,a:active {
	text-decoration: none;
	color: #FFFFFF;
}

#gongyiNav a:hover {
	background: #CCCCCC;
}

form span {
	border: 0px;
}
</style>
	</head>
	<body style="text-align: center;">
		<!-- <div align="center" style="width: 1687px;"> -->
		<div align="center" style="">
			<div id="gongyiNav">
				<a data-type="gygc"
					href="gongyiGuichengAction!getGongyiGuiChengGygcPage.action?gongyiGuicheng.id=${gongyiGuicheng.id}">首页</a>
				<a data-type="gycxt"
					href="gongyiGuichengAction!getGongyiGuiChengGycxtbPage.action?gongyiGuicheng.id=${gongyiGuicheng.id}">工艺程序图</a>
				<a data-type="gxtblmfqmx"
					href="gongyiGuichengAction!getGongyiGuiChengGxtblmfqmxPage.action?gongyiGuicheng.id=${gongyiGuicheng.id}">工序图表栏目分区明细</a>
				<a data-type="gxsmlq"
					href="gongyiGuichengAction!getGongyiGuiChengGxsmlqPage.action?gongyiGuicheng.id=${gongyiGuicheng.id}"
					style="display: none;">工序说明栏区A3</a>
				<a data-type="gxsmlqa4"
					href="gongyiGuichengAction!getGongyiGuiChengGxsmlqa4Page.action?gongyiGuicheng.id=${gongyiGuicheng.id}"
					style="display: none;">工序说明栏区A4</a>
				<a data-type="jyxmlq"
					href="gongyiGuichengAction!getGongyiGuiChengJyxmlqPage.action?gongyiGuicheng.id=${gongyiGuicheng.id}"
					style="display: none;">检验项目栏区</a>
				<span>&nbsp;</span>
				<select id="processDataId">
				</select>
			</div>
			<div id="showCardTemplate"
				style="display: block;height: 1800px; hoverflow: scroll; position: relative; text-align: center;">
			<!--  style="display: block; width: 1687px; height: 1800px; hoverflow: scroll; position: relative; text-align: center;">-->	
				<iframe id="showGongyiIframe" src="" marginwidth="0"
					marginheight="0" hspace="0" vspace="0" frameborder="0"
					scrolling="yes" style="width: 100%; height: 100%; padding: 0px;">
				</iframe>
			</div>
			<div>
				<form action="">

				</form>
			</div>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function() {
	var type = '';
	var gongyiGuichengId = '${gongyiGuicheng.id}';
	//件ID
	var jianId = '${gongyiGuicheng.jianId}';
	//工艺规程ID
	var processDataId = '';

	//绑定工艺规程导航条
	$('#gongyiNav a').bind("click", function(event) {
		event.preventDefault();
		$("#gongyiNav a[data-status='1']").css( {
			background : '#666666'
		});
		var $this = $(this);
		$this.attr('data-status', '1');
		$this.css( {
			background : '#CCCCCC'
		});
		var src = $this.attr('href');

		//gygc  gycxt gxtblmfqmx gxsmlq jyxmlq
			type = $this.attr('data-type');
			//$("#jianId").css('display','none');
			switch (type) {
			case 'gygc':
				//$("#showCardTemplate").css({display: 'block', width: '100%',height: '1800px',hoverflow: 'visible',position: 'relative',text-align: 'center'});
				$("#processDataId").css('visibility', 'hidden');
				break;
			case 'gycxt':
				$("#processDataId").css('visibility', 'hidden');
				break;
			case 'gxtblmfqmx':
				processDataId = $("#processDataId").find("option:first-child")
						.val();
				if (!processDataId) {
					alert("请添加工序");
					return;
				}
				//$("#showCardTemplate").css({display: 'block', width: '1687px',height: '1800px',hoverflow: 'visible',position: 'relative',text-align: 'center',margin-left: '-180px'});
				//$("#processDataId").css('visibility','visible');
				src += '&processData.gongyiGuichengId=' + gongyiGuichengId
						+ '&processData.id=' + processDataId;
				break;
			case 'gxsmlq':
				if (!processDataId) {
					alert("请添加工序");
					return;
				}
				//$("#showCardTemplate").css({display: 'block', width: '1687px',height: '1800px',hoverflow: 'visible',position: 'relative',text-align: 'center',margin-left: '-180px'});
				$("#processDataId").css('visibility', 'visible');
				src += '&processData.gongyiGuichengId=' + gongyiGuichengId
						+ '&processData.id=' + processDataId;
				break;
			case 'gxsmlqa4':
				if (!processDataId) {
					alert("请添加工序");
					return;
				}
				//$("#showCardTemplate").css({display: 'block', width: '1687px',height: '1800px',hoverflow: 'visible',position: 'relative',text-align: 'center',margin-left: '-180px'});
				$("#processDataId").css('visibility', 'visible');
				src += '&processData.gongyiGuichengId=' + gongyiGuichengId
						+ '&processData.id=' + processDataId;
				break;
			case 'jyxmlq':
				var $processDataIdOption = $("#processDataId").find("option");
				if ($processDataIdOption.length < 3) {
					alert("请添加工序");
					return;
				}
				processDataId = $processDataIdOption.last().val();
				if (!processDataId) {
					alert("请添加工序");
					return;
				}
				//$("#processDataId").css('visibility','visible');
				src += '&processData.gongyiGuichengId=' + gongyiGuichengId
						+ '&processData.id=' + processDataId;
				break;
			default:
				break;
			}
			$('#showGongyiIframe').attr('src', src);
		});
	//查询出所有的工序名称
	/*
	$.ajax({
		type: "get",
		dataType: "json",
	    url: "gongyiGuichengAction!findProcessListForSelect.action",
	    data:{'gongyiGuicheng.id': gongyiGuichengId,'gongyiGuicheng.jianId': jianId},
		async: false,
		success: function(data){
			var processList=data.data;
			//$("<option value=''>请选择</option>").appendTo("#process");
			$(processList).each(function() {
				$("<option value='"+this.id+"'>"+this.processName+"</option>").appendTo("#process");
			});
			processId=$("#process").val();
		}
	});
	 */
	$.ajax( {
		type : "get",
		dataType : "json",
		url : "gongyiGuichengAction!findProcessDataListForSelect.action",
		data : {
			'gongyiGuicheng.id' : gongyiGuichengId
		},
		async : false,
		success : function(data) {
			var processList = data.data;
			//$("<option value=''>请选择</option>").appendTo("#process");
			$(processList).each(
					function() {
						$(
								"<option value='" + this.id + "'>"
										+ this.gongxuName + "</option>")
								.appendTo("#processDataId");
					});
			processDataId = $("#processDataId").val();
		}
	});
	$("#processDataId").find("option[value='" + processDataId + "']").attr(
			"selected", true);
	$("#processDataId")
			.bind(
					"change",
					function() {
						processDataId = $("#processDataId").val();
						switch (type) {
						case 'gxtblmfqmx':
							$('#showGongyiIframe')
									.attr(
											'src',
											'gongyiGuichengAction!getGongyiGuiChengGxtblmfqmxPage.action?gongyiGuicheng.id='
													+ gongyiGuichengId
													+ '&processData.gongyiGuichengId='
													+ gongyiGuichengId
													+ '&processData.id='
													+ processDataId);
							break;
						case 'gxsmlq':
							$('#showGongyiIframe').attr(
									'src',
									'gongyiGuichengAction!getGongyiGuiChengGxsmlqPage.action?gongyiGuicheng.id='
											+ gongyiGuichengId
											+ '&processData.gongyiGuichengId='
											+ gongyiGuichengId
											+ '&processData.id='
											+ processDataId);
							break;
						case 'gxsmlqa4':
							$('#showGongyiIframe').attr(
									'src',
									'gongyiGuichengAction!getGongyiGuiChengGxsmlqa4Page.action?gongyiGuicheng.id='
											+ gongyiGuichengId
											+ '&processData.gongyiGuichengId='
											+ gongyiGuichengId
											+ '&processData.id='
											+ processDataId);
							break;
						case 'jyxmlq':
							$('#showGongyiIframe').attr(
									'src',
									'gongyiGuichengAction!getGongyiGuiChengJyxmlqPage.action?gongyiGuicheng.id='
											+ gongyiGuichengId
											+ '&processData.gongyiGuichengId='
											+ gongyiGuichengId
											+ '&processData.id='
											+ processDataId);
							break;
						}
					});
	$("#processDataId").css('visibility', 'hidden');
	$("#gongyiNav a[data-type='gygc']").trigger("click");
});
</script>
