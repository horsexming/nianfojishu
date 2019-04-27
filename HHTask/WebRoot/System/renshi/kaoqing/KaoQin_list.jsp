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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center" id="d1">
				<form action="nianXiuAction!listnianxiu.action" method="post"
					theme="simple">
					<table class="table">
						<tr>
							<th align="center">
								月份：
							</th>
							<td>
								<input class="Wdate" type="text" id="yuefen"
									name="kaoQin.yuefen"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
							<th align="center">
								卡号：
							</th>
							<td>
								<input type="text" id="kaoQin.carId" name="kaoQin.carId" />

								<td rowspan="3">
									<input type="submit" value="查询" class="input" />
									<input type="button" value="导出" class="input"
										onclick="daochuExec();todisabledone(this)" data="downData" />
									<input type="button" value="更新" class="input"
										onclick="gengxin()" />
								</td>
						</tr>
						<tr>
							<th align="center">
								姓名：
							</th>
							<td>
								<input type="text" name="kaoQin.name" id="kaoQin.name" />
							</td>
							<th align="center">
								部门：
							</th>
							<td>
								<select id='dept' name="kaoQin.dept"
									onclick="createDept('dept')" style="width: 90px;">
								</select>
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2">
						<th>
							编号
						</th>
						<th>
							卡号
						</th>
						<th>
							名称
						</th>
						<th>
							部门
						</th>
						<th>
							出勤天数
						</th>
						<th>
							应出勤天数
						</th>
						<th>
							事假
						</th>
						<th>
							公出
						</th>
						<th>
							换/调休
						</th>
						<th>
							迟到
						</th>
						<th>
							早退
						</th>
						<th>
							缺勤
						</th>
						<th>
							对比
						</th>
						<th>
							加班
						</th>
						<th>
							月份
						</th>
						<th>
							更新日期
						</th>
						<th>
							调整类型
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>
								${pageIndex.index+1}
								<s:if test='#pageIndex.index=="0"'>
									<input type="hidden" name="time2" id="time2"
										value="${zhUser1.yuefen}" />
								</s:if>
							</th>
							<th>
								${zhUser1.carId}
							</th>
							<th>
								${zhUser1.name}
							</th>
							<th>
								${zhUser1.dept}
							</th>
							<th>
								${zhUser1.chuqintianshu}
							</th>
							<th>
								${zhUser1.yingchuqin}
							</th>
							<th>
								${zhUser1.shijia}
							</th>
							<th>
								${zhUser1.gongchu}
							</th>
							<th>
								${zhUser1.tiaoxiu}
							</th>
							<th>
								${zhUser1.lateTime}
							</th>
							<th>
								${zhUser1.earlyTime}
							</th>
							<th>
								${zhUser1.kuanggong}
							</th>
							<th>
								${zhUser1.bijiao}
							</th>
							<th>
								${zhUser1.jiaban}
							</th>
							<th>
								${zhUser1.yuefen}
							</th>
							<th>
								${zhUser1.addTime}
							</th>
							<th>
								<s:if test="#zhUser1.StatusInt=='1'">
									手动调整
								</s:if>
								<s:else>
									自动计算
								</s:else>
							</th>
							<th>
								<a href="nianXiuAction!mingxi.action?kaoQin.id=${zhUser1.id}">查看详细</a>
								<a href="nianXiuAction!toupdate.action?kaoQin.id=${zhUser1.id}">
									/调整</a>
								<input type="button" value="删除" onclick="shanchu(${zhUser1.id},${cpage})"/>
								<input type="button" value="更新" onclick="jisuan(${zhUser1.id},${cpage})"/>
							</th>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<th colspan="18" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<th colspan="18" align="center" style="color: red">
						</s:else>
						</th>
					</tr>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	  var time2=document.getElementById("time2").value;
	document.getElementById("yuefen").value= time2;
	function daochuExec(){
		var yuefen=document.getElementById("yuefen").value;
		//objForm.action="nianXiuAction!exportExcel.action?kaoQin.yuefen="+yuefen;
		//objForm.submit();
		window.location.href="nianXiuAction!exportExcel.action?kaoQin.yuefen="+yuefen;
	}
	function gengxin(){
		var yuefen=document.getElementById("yuefen").value;
		window.location.href="nianXiuAction!yuebao.action?kaoQin.yuefen="+yuefen;
	}
	function shanchu(id,cpage){
		window.location.href="nianXiuAction!shanchuById.action?kaoQin.id="+id+"&cpage="+cpage;
	}
	function jisuan(id,cpage){
		window.location.href="nianXiuAction!jiSuanById.action?kaoQin.id="+id+"&cpage="+cpage;
	}
	</SCRIPT>

</html>
