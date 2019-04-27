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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<s:if test="wxtuiliaoList!=null && wxtuiliaoList.size()>0">
					<form action="ProcardAction!WxTuiLiaoSq.action" method="POST" onsubmit="return check()"
						id="sqltform">
						<table class="table">
							<tr align="center" bgcolor="#c0dcf2" height="50px">
								<th>
								</th>
								<th>
									序号
								</th>
								<th>
									业务件号
								</th>
								<th>
									订单号(内部)
								</th>
								<th>
									总成件号
								</th>
								<th>
									件号
								</th>
								<th>
									名称
								</th>
								<th>
									批次
								</th>
								<th>
									对应工序号
								</th>
								<th>
									对应工序名
								</th>
								<th>
									批次数量
								</th>
								<th>
									工序提交量
								</th>
								<th>
									已领数量
								</th>
								<th>
									下工序完成数量
								</th>
								<th>
									操作
								</th>
							</tr>
							<s:iterator value="wxtuiliaoList" id="pagewxtuiliao"
								status="statussdf">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" id="tr_${statussdf.index}"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')" id="tr_${statussdf.index}">
								</s:else>
								<td>
									<input type="checkbox" value="${statussdf.index}"
										name="checkboxs" onclick="" />
									<input type="hidden" value="${pagewxtuiliao.procardId}"
										name="procardId" />
									<input type="hidden" value="${pagewxtuiliao.nextProcessId}"
										name="nextProcessId" />
									<input type="hidden" value="${pagewxtuiliao.rootProcardId}"
										name="rootProcardId" />
								</td>
								<td>
									${statussdf.index+1}
								</td>
								<td>
									${pagewxtuiliao.ywMarkid}
									<input type="hidden" value="${pagewxtuiliao.ywMarkid}"
										name="ywMarkid" />
								</td>
								<td>
									${pagewxtuiliao.orderNum}
									<input type="hidden" value="${pagewxtuiliao.orderNum}"
										name="orderNum" />
								</td>
								<td>
									${pagewxtuiliao.rootMarkId}
									<input type="hidden" value="${pagewxtuiliao.rootMarkId}"
										name="rootMarkId" />
								</td>
								<td>
									${pagewxtuiliao.markId}
									<input type="hidden" value="${pagewxtuiliao.markId}"
										name="markId" />
								</td>
								<td>
									${pagewxtuiliao.proName}
									<input type="hidden" value="${pagewxtuiliao.proName}"
										name="proName" />
								</td>
								<td>
									${pagewxtuiliao.selfCard}
									<input type="hidden" value="${pagewxtuiliao.selfCard}"
										name="selfCard" />
								</td>
								<td>
									${pagewxtuiliao.processNos}
									<input type="hidden" value="${pagewxtuiliao.processNos}"
										name="processNos" />
								</td>
								<td>
									${pagewxtuiliao.processNames}
									<input type="hidden" value="${pagewxtuiliao.processNames}"
										name="processNames" />
								</td>
								<td>
									${pagewxtuiliao.num}
<%--									<input type="hidden" value="${pagewxtuiliao.num}" name="num" />--%>
								</td>
								<td>
									${pagewxtuiliao.gxtjNum}
									<input type="hidden" value="${pagewxtuiliao.gxtjNum}"
										name="gxtjNum" />
								</td>
								<td>
									${pagewxtuiliao.ylNum}
									<input type="hidden" value="${pagewxtuiliao.ylNum}"
										name="ylNum" />
								</td>
								<td>
									${pagewxtuiliao.nextgxtjNum}
									<input type="hidden" value="${pagewxtuiliao.nextgxtjNum}"
										name="nextgxtjNum" />
								</td>
								<td>
									<input type="text"
										value="${pagewxtuiliao.ylNum -pagewxtuiliao.nextgxtjNum }"
										id="sqtlNum_${statussdf.index}"
										onchange="numyanzheng(this,'zhengshu');
							numpanduan(this,${pagewxtuiliao.ylNum -pagewxtuiliao.nextgxtjNum })" />
									<br />
									<input type="button" value="申请退料"
										onclick="sqtl('${statussdf.index}',this)" />
								</td>
								</tr>
							</s:iterator>
						</table>
						<input type="submit" value="申请退料" id="sub"
							onclick="todisabled(this)" class="input" />
						<div id="fuzhi_div">

						</div>
					</form>
				</s:if>
				<s:else>
					<font size="5" color="red">该总成下，无相关外协需退料信息</font>
				</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function check(){
	var checkboxs =	document.getElementsByName("checkboxs");
	var bool = true;
	if(checkboxs!=null && checkboxs.length>0){
		var index0 = 0;
		$("#fuzhi_div").empty();
		for(var i=0;i<checkboxs.length; i++){//checked="checked" 
			if(checkboxs[i].checked){
				bool = false;
				var index = checkboxs[i].value;
				var values =	$("#tr_"+index).find("input[type=hidden]");
				if(values!=null && values.length>0){
					for(var j=0;j<values.length;j++){
						$("#fuzhi_div").append('<input type="hidden" value='+values[j].value+' name="wxtuiliaoList['+index0+'].'+values[j].name+'"/>');
					}
					var sqtlNum = $("#sqtlNum_"+index).val();
					$("#fuzhi_div").append('<input type="hidden" value='+sqtlNum+' name = "wxtuiliaoList['+index0+'].sqtlNum">');
					index0++;
				}
			}
		}
	}
	if(bool){
		alert("请至少选择一个零件申请退料!")
		$("#sub").removeAttr("disabled")
		return false;
	}
}

function numpanduan(obj,num){
	var sqtlNum = $(obj).val();
	if(sqtlNum!=""){
		sqtlNum = +sqtlNum;
		if(sqtlNum>num){
			$(obj).val(0);
			alert("该外协件最大可退料数量为:"+num+"不能超过该数量。")
		}
	}
}
function sqtl(num,obj){
				$("#fuzhi_div").empty();
				var values =	$("#tr_"+num).find("input[type=hidden]");
				if(values!=null && values.length>0){
					for(var j=0;j<values.length;j++){
						$("#fuzhi_div").append('<input type="hidden" value='+values[j].value+' name="wxtuiliaoList[0].'+values[j].name+'"/>');
						$(values[j]).removeAttr("name");
					}
					var sqtlNum = $("#sqtlNum_"+num).val();
					$("#fuzhi_div").append('<input type="hidden" value='+sqtlNum+' name = "wxtuiliaoList[0].sqtlNum">');
				}
		$("#sqltform").removeAttr("onsubmit");
		$("#sqltform").submit();	
		$(obj).attr("disabled","disabled")
		
}

</SCRIPT>

	</body>
</html>
