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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">关联外购件信息</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;">
					</iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
						<form action="" method="POST">
							<table class="table" style="border-collapse: collapse;">
								<tr>
									<th>
										<input type="checkbox"  name="" onclick="chageAllCheck(this)"/>
										<input type="hidden" value="${id}" name="id"/>
									</th>
									<th>
										序号
									</th>
									<th>
										供应商
									</th>
									<th>
										件号
									</th>
									<th>
										零件名称
									</th>
									<th>
										批次
									</th>
									<th>
										版本
									</th>
									<th>
										版次
									</th>
									<th>
										工序号
									</th>
									<th>
										工序名称
									</th>
									<th>
										数量
									</th>
									<th>
										外委类型
									</th>
									<th>
										状态
									</th>
									<th>
										进度
									</th>
									<th>
										添加人
									</th>
									<th>
										添加时间
									</th>
									<th width="300px">
										关联外购件
									</th>
									<th>
										操作
									</th>
								</tr>
								<s:iterator value="pwwApply.detailList" id="pagedetail"
									status="step1">
									<s:if test="#step1.index%2==1">
										<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											style="height: 50px;" onmouseout="outBgcolor(this,'')">
									</s:else>
									<td align="center">
										<input type="checkbox" value="${pagedetail.id}" name="processIds"/>
									</td>
									<td align="center">
										<s:property value="#step1.index+1" />
									</td>
									<td align="center">
										<s:if test="#pagedetail.gysId==null">
											<font color="red">尚未匹配到合同</font>
										</s:if>
										<s:else>
								${pagedetail.gysName}
								</s:else>
									</td>
									<td align="center">
										${pagedetail.markId}
									</td>
									<td align="left">
										${pagedetail.proName}
									</td>
									<td align="center">
										${pagedetail.selfCard}
									</td>
									<td align="center">
										${pagedetail.banbenNumber}
									</td>
									<td align="center">
										${pagedetail.banci}
									</td>
									<td align="left">
										${pagedetail.processNOs}
									</td>
									<td align="left">
										${pagedetail.processNames}
									</td>
									<td align="center">
										${pagedetail.applyCount}
									</td>
									<td align="center">
										${pagedetail.wwType}
									</td>
									<s:if test='#pagedetail.dataStatus=="删除"'>
										<td align="center" style="background-color: red;">
											${pagedetail.dataStatus}
										</td>
									</s:if>
									<s:else>
										<td align="center">
											${pagedetail.dataStatus}
										</td>
									</s:else>
									<td align="center">
										${pagedetail.processStatus}
									</td>
									<td align="center">
										${pagedetail.userName}
									</td>
									<td align="center">
										${pagedetail.addTime}
									</td>
									<td width="300px" align="left">
										<s:if test='#pagedetail.wwType == "包工包料"'>
											<input type="button" value="已关联外购件"
												onclick="showwgjmarkId(${pagedetail.procardId})" />
										</s:if>
										<s:else>
											<s:property value="#pagedetail.guanlianMarkId" />
										</s:else>
									</td>
									</tr>
								</s:iterator>
							</table>
							<input type="button"  value="同意" class="input" onclick="shenpi('OK',this,this.form)" />
							<input type="button" value="打回" class="input" onclick="shenpi('NO',this,this.form)" />
					</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function sqdt(id){
	window.location.href="CircuitRunAction_findAduitPage.action?id="+id;
}
function sqsp(id){
	$("#sqspbtn").attr("disabled","disabled");
	$("#form_"+id).submit();
	//window.location.href="ProcardAction!wwsq.action?id="+id;
}
function deleteDetail(id){
	if(confirm("您是否要删除此条记录!")){
		window.location.href="ProcardAction!deleteWwApplyDetail.action?id="+id;
	}
}
function showwgjmarkId(id){
	chageDiv("block");
	$("#showProcess").attr("src","ProcardAction!showwgjmarkId.action?id="+id);
	
}
function exportWwApplyDetail(id){
	window.location.href="ProcardAction!exportWwApplyDetail.action?id="+id;
}
function totzgongxu(id){
	window.location.href="ProcardAction!totzgongxu.action?id="+id;
}
function totzwgy(id){
	chageDiv("block");
	$("#showProcess").attr("src","ProcardAction!totzwgj.action?id="+id);
}
function shenpi(tag,obj,obj2){
	$(obj2).attr('action','ProcardAction!shenPiApplyDetail.action?tag='+tag);
	$(obj2).submit();
	$(obj).attr('disabled','disabled');
}

</SCRIPT>
	</body>
</html>
