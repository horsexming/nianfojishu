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
		<div id="gongneng">
			<div align="center">
				<font style="font-size: 22px; font-weight: bolder;"> EC展示 </font>
				<s:if test="pageStatus==null || pageStatus!='pingmu'">
					<s:if test="type=='1'.toString()">
						<form action="procardTemplateGyAction_tclshowListi.action"
							method="post">
					</s:if>
					<s:elseif test="type=='2'.toString()">
						<form action="procardTemplateGyAction_tclshowListo.action"
							method="post">
					</s:elseif>
					<s:elseif test="type=='3'.toString()">
						<form action="procardTemplateGyAction_tclshowLista.action"
							method="post">
					</s:elseif>
					<table class="table" align="center">
						<tr>
							<td align="center">
								设变单号：
								<input type="text" name="technicalchangeLog.sbNumber"
									value="<s:property value="technicalchangeLog.sbNumber"/>" />
							</td>
							<td align="center">
								外部设变单号：
								<input type="text" name="technicalchangeLog.outbsNumber"
									value="<s:property value="technicalchangeLog.outbsNumber"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								设变发起人：
								<input type="text" name="technicalchangeLog.applyUserName"
									value="<s:property value="technicalchangeLog.applyUserName"/>" />
							</td>
							<td align="center">
								涉及车间：
								<input type="text" name="technicalchangeLog.aboutPlace"
									value="<s:property value="technicalchangeLog.aboutPlace"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								产品编码：
								<input type="text" name="technicalchangeLog.ywMarkId"
									value="<s:property value="technicalchangeLog.ywMarkId"/>" />
							</td>
							<td align="center">
								产品类别：
								<input type="text" name="technicalchangeLog.producttype"
									value="<s:property value="technicalchangeLog.producttype"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								接收时间（始）：
								<input type="text" name="start"
									value="<s:property value="start"/>" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="center">
								接收时间（止）：
								<input type="text" name="end" value="<s:property value="end"/>"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
					</form>
				</s:if>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center" rowspan="2">
							序号
						</td>
						<td align="center" rowspan="2">
							接收时间
						</td>
						<td align="center" rowspan="2">
							设变发起人
						</td>
						<td align="center" rowspan="2">
							涉及楼层车间
						</td>
						<td align="center" rowspan="2">
							产品编码
						</td>
						<td align="center" rowspan="2">
							产品类别
						</td>
						<td align="center" rowspan="2">
							设变单号
						</td>
						<td align="center" rowspan="2">
							外部设变单号
						</td>
						<td align="center" rowspan="2">
							图号
						</td>
						<td align="center" rowspan="2">
							设变内容简述
						</td>
						<td align="center" rowspan="2">
							设变对成本的影响
						</td>
						<td align="center" colspan="3">
							设变结论
						</td>
						<td align="center" colspan="8">
							设变涉及所需部门
						</td>
						<td align="center" rowspan="2">
							备注
						</td>
						<td align="center" rowspan="2">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<tr bgcolor="#c0dcf2">
						<td align="center">
							在制品
						</td>
						<td align="center">
							已完成品
						</td>
						<td align="center">
							未投订单
						</td>
						<td align="center">
							工程部
						</td>
						<td align="center">
							生产部
						</td>
						<td align="center">
							品质部
						</td>
						<td align="center">
							PMC物控
						</td>
						<td align="center">
							PMC生管
						</td>
						<td align="center">
							采购外协
						</td>
						<td align="center">
							采购外购
						</td>
						<td align="center">
							仓库
						</td>
					</tr>
					<s:iterator value="tclList" id="tcl" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
						</s:if>
						<s:else>
							<td align="center"
								rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
						</s:else>
						<s:if test="#pageStatus.index%2==1">
							<font>
						</s:if>
						<s:else>
							<font color="#c0dcf2">
						</s:else>
						<s:property value="#pageStatus.index+1" />
						</font>
						</td>
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
						</s:if>
						<s:else>
							<td align="center"
								rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
						</s:else>
							${tcl.recriveDate}
						</td>
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
						</s:if>
						<s:else>
							<td align="center"
								rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
						</s:else>
							${tcl.applyUserName}
						</td>
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
						</s:if>
						<s:else>
							<td align="center"
								rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
						</s:else>
							${tcl.aboutPlace}
						</td>
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
						</s:if>
						<s:else>
							<td align="center"
								rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
						</s:else>
							${tcl.ywMarkId}
						</td>
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
						</s:if>
						<s:else>
							<td align="center"
								rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
						</s:else>
							${tcl.producttype}
						</td>
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
						</s:if>
						<s:else>
							<td align="center"
								rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
						</s:else>
							${tcl.sbNumber}
						</td>
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
						</s:if>
						<s:else>
							<td align="center"
								rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
						</s:else>
							${tcl.outbsNumber}
						</td>
						<!-- 明细开始 -->
						<s:if test="#tcl.tclDetailList.size()==0">
							<td align="center">
							</td>
							<td align="center">
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							${tcl.aboutcb}
						</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
						${tcl.zzpcl}
						</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
						${tcl.wcpcl}
					 	</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
						${tcl.wtdcl}
					 	</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.gcb=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.scb=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.pzb=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.pmcwk=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.pmcsg=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.cgwx=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.cgwg=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.ck=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="#tcl.remark=='yes'">★
					 	</s:if>
							</td>
							<s:if test="#tcl.tclDetailList.size()==0">
								<td align="center">
							</s:if>
							<s:else>
								<td align="center"
									rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
							</s:else>
							<s:if test="pageStatus==null || pageStatus!='pingmu'">
								<input value="修改" type="button" onclick="update(${tcl.id})">
								<input value="删除" type="button" onclick="todelete(${tcl.id})">
							</s:if>
							</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="#tcl.tclDetailList" id="pagetcld"
								status="tcldStatus">
								<s:if test="#tcldStatus.index>0">
									<s:if test="#pageStatus.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
										<td align="center">
											${pagetcld.markId}
										</td>
										<td align="center">
											${pagetcld.changeLog}
										</td>
									</tr>
								</s:if>
								<s:else>
									<td align="center">
										${pagetcld.markId}
									</td>
									<td align="center">
										${pagetcld.changeLog}
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
							${tcl.aboutcb}
						</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
						${tcl.zzpcl}
						</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
						${tcl.wcpcl}
					 	</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
						${tcl.wtdcl}
					 	</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.gcb=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.scb=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.pzb=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.pmcwk=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.pmcsg=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.cgwx=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.cgwg=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.ck=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="#tcl.remark=='yes'">★
					 	</s:if>
									</td>
									<s:if test="#tcl.tclDetailList.size()==0">
										<td align="center">
									</s:if>
									<s:else>
										<td align="center"
											rowspan="<s:property value="#tcl.tclDetailList.size()"/>">
									</s:else>
									<s:if test="pageStatus==null || pageStatus!='pingmu'">
										<input value="修改" type="button" onclick="update(${tcl.id})">
										<input value="删除" type="button" onclick="todelete(${tcl.id})">
									</s:if>
									</td>
									</tr>
								</s:else>
							</s:iterator>
						</s:else>
						<!-- 明细结束 -->


					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="24" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="24" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id) {
	window.location.href = "procardTemplateGyAction_totclupdate.action?cpage=${cpage}&type=${type}&id="
			+ id;
}
function todelete(id) {
	if (confirm("确定要删除此记录？")) {
		window.location.href = "procardTemplateGyAction_totcldelete.action?cpage=${cpage}&type=${type}&id="
				+ id;
	}
}
</script>
	</body>
</html>
