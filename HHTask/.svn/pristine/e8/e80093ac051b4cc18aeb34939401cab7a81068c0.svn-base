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
								您正在确认非主营应收明细:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="NoncoreReceAction!querenDetailYingshou.action?tag=${tag}" method="post" onsubmit="return validate()">
							<input type="hidden" id="wuId" name="nonCoreReceivablesDetail.id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										确认应收款金额:
									</th>
									<th align="left">
										<input name="nonCoreReceivablesDetail.yingfuJine" id="yingfuJine"/>
									</th>
								</tr>
								<tr id="shu" style="display: none;">
									<th align="right">
										用<b id="shui1" style="display: none;">水</b><b id="dian1" style="display: none;">电</b>量:
									</th>
									<th align="left">
										<input name="nonCoreReceivablesDetail.biaoshu" id="biaoshu"
										/>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" value="确认" style="width: 65px; height: 40px;" />
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
					待确认非主营业务应收明细
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form method="post" action="NoncoreReceAction!showListDetailDqr.action?tag=${tag}">
					<table class="table">
						<tr>
							<td align="right">
								承租方:
							</td>
							<td>
								<input type="text" name="nonCoreReceivablesDetail.chengzufang"
								value="${nonCoreReceivablesDetail.chengzufang}"/>
							</td>
							<td align="right">
								科目:
							</td>
							<td>
								<SELECT name="nonCoreReceivablesDetail.kemu" style="width: 152px;">
									<option value="${nonCoreReceivablesDetail.kemu}">${nonCoreReceivablesDetail.kemu}</option>
									<option value=""></option>
									<option value="水费">水费</option>
									<option value="电费">电费</option>
									<option value="地产租赁">地产租赁</option>
									<option value="设备租赁">设备租赁</option>
									<option value="其他">其他</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th colspan="4">
								<input type="submit" value="查询" class="input" /> 
							</th>
						</tr>
					</table>
				</form>
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
					<s:if test="nonCoreReceivablesDetaildqrList.size()>0">
						<tr><th colspan="13" bgcolor="green">待确认</th></tr>
							<s:iterator value="nonCoreReceivablesDetaildqrList" id="samples" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 25px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td rowspan="2">
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
							<td align="center" colspan="1" rowspan="2">
								<input type="button" value="确认" style="background: #9c0;color: #006" onclick="submitqueren('${samples.id}','qr','${samples.kemu}')"/>
								<input type="button" value="取消" style="background: #ccc;color: #006" onclick="submitqueren('${samples.id}','qx','')"/>
							</td>
							</tr>
							<tr align="center">
								<td colspan="11" align="left">
									承租方：<b>${samples.nonCoreReceivables.chengzufang}</b> 
									承租类型：<b>${samples.nonCoreReceivables.receiveType}</b>
									<s:if test='#samples.nonCoreReceivables.receiveType=="地产租赁"'>
										是否代缴水电：<b>${samples.nonCoreReceivables.isNeeddaitijiaofei}</b>
									</s:if>
									<s:if test="#samples.nonCoreReceivables.more!=null&&#samples.nonCoreReceivables.more!=''">
										备注：<b>${samples.nonCoreReceivables.more}</b>
									</s:if>
									<br/>
									经办人：<b>${samples.nonCoreReceivables.jignbanren}</b>
									有效起始日期：<b>${samples.nonCoreReceivables.youxiaoqiStart}</b>
									有效截止日期：<b>${samples.nonCoreReceivables.youxiaoqi}</b>
									缴租周期：<b>${samples.nonCoreReceivables.jiaozuzhouqi}月</b>
								</td>
							</tr>
						</s:iterator>
					</s:if>
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
		//明细确认 
function submitqueren(wuId,type,kemu) {
	if(type=='qx'){
		//取消逻辑
		if(window.confirm('您将取消该应收明细，请问是否继续？')){
			window.location.href = "NoncoreReceAction!querenDetailqux.action?tag=${tag}&nonCoreReceivablesDetail.id="+wuId;
		}
	}else if(type=='qr'){
		//确认金额
		if(kemu=='水费'){
			$("#shu").show();
			$("#shui1").show();
			$("#dian1").hide();
		}else if(kemu=='电费'){
			$("#shu").show();
			$("#dian1").show();
			$("#shui1").hide();
		}else{
			$("#shu").hide();
		}
		$("#wuId").val(wuId);
		$("#submitProcessDiv").show();
		chageDiv("block");
		//单独设置弹出层的高度
		var thisTopHeight = $(obj).offset().top;
		$('#contentDiv').css( {
			'top' : thisTopHeight + 'px'
		});
	}
}
		
function validate(){
	if (!validateText("yingfuJine", "应收金额")) {
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
