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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<font id="msg_font" color="red"></font>
				<table class="table">
					<tr>
						<th>序号</th>
						<th>件号</th>
						<th>批次</th>
						<th>数量</th>
						<th>工序号</th>
						<th>工序名</th>
						<th>生产类型</th>
						<th style="width: 80px;">特殊工序</th>
						<th>工序点数</th>
						<th>计件单价</th>
						<th>系数</th>
						<th>操作</th>
					</tr>
					<s:iterator value="processList1"  id="pageprocess" status="pagestatus">
						<s:if test="#pagestatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pagestatus.index+1" />
								</td>
								<td>${pageprocess.markId}</td>
								<td>${pageprocess.selfCard}</td>
								<td>${pageprocess.count}</td>
								<td>${pageprocess.processNO}</td>
								<td>${pageprocess.processName}</td>
								<td>${pageprocess.productStyle}</td>
								<td>${pageprocess.isSpecial}</td>
								<td style="width: 80px;">
									<input style="width: 80px;" class="process_price_${pageprocess.id}" type="text" onchange="numyanzheng(this)" value="${pageprocess.procesdianshu}" disabled="disabled" name="" id="procesdianshu_${pageprocess.id}"/>	
								</td>
								<td style="width: 80px;">
									<input style="width: 80px;" class="process_price_${pageprocess.id}" type="text" onchange="numyanzheng(this)"  value="${pageprocess.processjjMoney}" disabled="disabled" name="" id="processjjMoney_${pageprocess.id}"/>	
								</td>
								<td style="width: 80px;">
									<input style="width: 80px;" class="process_price_${pageprocess.id}" type="text" onchange="numyanzheng(this)"  value="${pageprocess.jjratio}" disabled="disabled" name="" id="jjratio_${pageprocess.id}"/>	
								</td>
								<td>
									<a href="javascript:;" onclick="toupadteProcessPrice(${pageprocess.id})" id="xiugai_a_${pageprocess.id}"><span id="xiugai_span_${pageprocess.id}">修改工序单价</span></a>
								</td>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
	//修改工序计件单价
function toupadteProcessPrice(id){
	if(id!=null){
	var bool = true;
			$.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!isNeedJjPCPrice.action",
				dataType : "json",
				async:false,
				data : {
					id:id
				},
				success : function(data) {
					bool = data;
				}
			});
		if(bool){
			$(".process_price_"+id).removeAttr("disabled");
			$("#xiugai_a_"+id).attr("onclick",'upadteProcessPrice('+id+')');
			$("#xiugai_span_"+id).html('修改');
		}else{
			$("#msg_font").html('该工序已有按工时计价奖金的单价，不可添加计件奖金单价!');
		}
			
	}
}

function upadteProcessPrice(id){
	var procesdianshu = $("#procesdianshu_"+id).val();
	var processjjMoney = $("#processjjMoney_"+id).val();
	var jjratio = $("#jjratio_"+id).val();
	var bool = true;
	if(procesdianshu == ''){
		$("#msg_font").html('请输入工序点数!~');
		$("#procesdianshu_"+id).focus();
		bool= false;
	}else if(processjjMoney == ''){
		$("#msg_font").html('请输入工序单件计件工资!~');
		$("#processjjMoney_"+id).focus();
		bool= false;
	}else if(processjjMoney == ''){
		$("#msg_font").html('请输入工序奖金系数!~');
		$("#jjratio_"+id).focus();
		bool= false;
	}
	if(bool){
		$("#msg_font").html('');
		$.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!updatProcesPcPrice.action",
				dataType : "json",
				data : {
					'process.id':id,
					'process.procesdianshu':procesdianshu,
					'process.processjjMoney':processjjMoney,
					'process.jjratio':jjratio,
				},
				success : function(data) {
					if(data=="true"){
						$(".process_price_"+id).attr("disabled","disabled");
						$("#xiugai_a_"+id).attr("onclick",'toupadteProcessPrice('+id+')');
						$("#xiugai_span_"+id).html('修改工序单价');
						$("#msg_font").html('修改成功!~');
					}else{
						$("#msg_font").html(data);
					}
				}
			});
	}
	
	
}
	
	</SCRIPT>
	</body>
</html>
