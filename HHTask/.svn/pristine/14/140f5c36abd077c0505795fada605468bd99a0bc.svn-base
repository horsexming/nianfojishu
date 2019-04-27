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
		<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<div id="submitProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在确认收款金额:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="NoncoreReceAction!querenDetail.action?tag=${tag}" method="post" onsubmit="return validate()">
							<input type="hidden" id="wuId" name="nonCoreReceivablesDetail.id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										确认收款金额:
									</th>
									<th align="left">
										<input name="nonCoreReceivablesDetail.realfuJine" id="realfuJine"/>
									</th>
								</tr>
								<tr>
									<th align="right">
										收款日期:
									</th>
									<th align="left">
										<input class="Wdate" name="nonCoreReceivablesDetail.querenTime" id="querenTime"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" value="提交" style="width: 65px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					<font color="red">${nonCoreReceivables.chengzufang}</font>非主营业务应收明细查看
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								科目
							</td>
							<td align="center">
								收款状态
							</td>
							<td align="center">
								上次读表数
							</td>
							<td align="center">
								本次读表数
							</td>
							<td align="center">
								记录读表数
							</td>
							<td align="center">
								应收金额
							</td>
							<td align="center">
								实收金额
							</td>
							<td align="center">
								费用截止日
							</td>
							<td align="center">
								添加时间
							</td>
							<td align="center">
								添加人
							</td>
							<td align="center">
								审批状态
							</td>
							<td align="center" colspan="2">
								操作类型
							</td>
						</tr>
					<s:if test="tag=='caiwu'">
						<s:if test="nonCoreReceivablesDetailweiList.size()>0">
							<tr><th colspan="13" bgcolor="red">未收</th></tr>
							<s:iterator value="nonCoreReceivablesDetailweiList" id="samples" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 25px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="center">
									${samples.kemu}
								</td>
								<td align="center">
									${samples.zhuangtai}
								</td>
								<td align="center">
									${samples.lastbiaoshu}
								</td>
								<td align="center">
									${samples.thisbiaoshu}
								</td>
								<td align="center">
									${samples.biaoshu}
								</td>
								<td align="center">
									${samples.yingfuJine}
								</td>
								<td align="center">
									${samples.realfuJine}
								</td>
								<td align="center">
									${samples.jiluTime}
								</td>
								<td align="center">
									${samples.shangchuanTime}
								</td>
								<td align="center">
									${samples.saveUser}
								</td>
								<td align="center">
									${samples.status}
								</td>
								<td align="center" colspan="2">
		  							<a onclick="submitqueren('${samples.id}')">财务确认</a>
								</td>
							</s:iterator>
						</s:if>
						<s:if test="nonCoreReceivablesDetailweiqingList.size()>0">
							<tr><th colspan="13" bgcolor="yellow">未收清</th></tr>
							<s:iterator value="nonCoreReceivablesDetailweiqingList" id="samples" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 25px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="center">
									${samples.kemu}
								</td>
								<td align="center">
									${samples.zhuangtai}
								</td>
								<td align="center">
									${samples.lastbiaoshu}
								</td>
								<td align="center">
									${samples.thisbiaoshu}
								</td>
								<td align="center">
									${samples.biaoshu}
								</td>
								<td align="center">
									${samples.yingfuJine}
								</td>
								<td align="center">
									${samples.realfuJine}
								</td>
								<td align="center">
									${samples.jiluTime}
								</td>
								<td align="center">
									${samples.shangchuanTime}
								</td>
								<td align="center">
									${samples.saveUser}
								</td>
								<td align="center">
									${samples.status}
								</td>
								<td align="center" colspan="2">
									<a href="NoncoreReceAction!toselectDe.action?nonCoreReceivablesDetail.id=${samples.id}&tag=${tag}">打印  </a>
									<a onclick="submitqueren('${samples.id}')">财务确认</a>
								</td>
							</s:iterator>
						</s:if>
						<s:if test="nonCoreReceivablesDetailweiscList.size()>0">
							<tr><th colspan="13" bgcolor="yellow">待上传加章附件</th></tr>
							<s:iterator value="nonCoreReceivablesDetailweiscList" id="samples" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 25px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="center">
									${samples.kemu}
								</td>
								<td align="center">
									${samples.zhuangtai}
								</td>
								<td align="center">
									${samples.lastbiaoshu}
								</td>
								<td align="center">
									${samples.thisbiaoshu}
								</td>
								<td align="center">
									${samples.biaoshu}
								</td>
								<td align="center">
									${samples.yingfuJine}
								</td>
								<td align="center">
									${samples.realfuJine}
								</td>
								<td align="center">
									${samples.jiluTime}
								</td>
								<td align="center">
									${samples.shangchuanTime}
								</td>
								<td align="center">
									${samples.saveUser}
								</td>
								<td align="center">
									${samples.status}
								</td>
								<td align="center" colspan="2">
									<a href="NoncoreReceAction!toselectDe.action?nonCoreReceivablesDetail.id=${samples.id}&tag=${tag}">打印  </a>
									<a href="NoncoreReceAction!tobackFujian.action?nonCoreReceivablesDetail.id=${samples.id}&tag=${tag}">上传加章附件  </a>
								</td>
							</s:iterator>
						</s:if>
						<s:if test="nonCoreReceivablesDetailList.size()>0">
							<tr><th colspan="13" bgcolor="green">已收</th></tr>
						</s:if>
					</s:if>
					<s:iterator value="nonCoreReceivablesDetailList" id="samples" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.kemu}
						</td>
						<td align="center">
							${samples.zhuangtai}
						</td>
						<td align="center">
							${samples.lastbiaoshu}
						</td>
						<td align="center">
							${samples.thisbiaoshu}
						</td>
						<td align="center">
							${samples.biaoshu}
						</td>
						<td align="center">
							${samples.yingfuJine}
						</td>
						<td align="center">
							${samples.realfuJine}
						</td>
						<td align="center">
							${samples.jiluTime}
						</td>
						<td align="center">
							${samples.shangchuanTime}
						</td>
						<td align="center">
							${samples.saveUser}
						</td>
						<td align="center">
							${samples.status}
						</td>
						<td align="center" colspan="2">
							<s:if test="#samples.epId!=null">
								<a href="CircuitRunAction_findAduitPage.action?id=${samples.epId}">审批动态  </a>
							</s:if>
						<s:if test='tag=="admin"&&#samples.zhuangtai!="已收"'>
							<a onclick="return window.confirm('您将删除数据，是否继续?')"
								href="NoncoreReceAction!deleteDetail.action?id=${samples.id}&nonCoreReceivables.id=${nonCoreReceivables.id}&tag=${tag}">删除</a>
						</s:if>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="14" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		//财务确认 
function submitqueren(wuId) {
	$("#wuId").val(wuId);
	$("#submitProcessDiv").show();
	chageDiv("block");
	//单独设置弹出层的高度
	var thisTopHeight = $(obj).offset().top;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}
		
function validate(){
	if (!validateText("realfuJine", "已收金额")) {
		return false;
	}
	if (!validateText("querenTime", "收款日期")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
