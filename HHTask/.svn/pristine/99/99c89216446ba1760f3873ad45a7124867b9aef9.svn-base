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
				<table class="table">
					<tr >
						<th colspan="5" style="font-size: x-large;">
							生产成本及制造费用明细表
						</th>
					</tr>
					<tr>
						<td>编制单位：${pam.companyName}</td>
						<td></td>
						<td>${pam.months}</td>
						<td></td>
						<td>金额单位：万元</td>
					</tr>
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</th>
						<td>行次</td>
						<td>本月数</td>
						<td>本年累计数</td>
						<td>上年同期数</td>
					</tr>
					<tr>
						<td>生产成本项目</td>
						<td align="center">0</td>
						<td align="right">
							1
						</td>
						<td align="right">
							2
						</td>
						<td align="right">
							3
						</td>
					</tr>
					<tr>
						<td>生产成本合计：</td>
						<td align="center">1</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sccbhj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy2&months=${months}')" >
									<fmt:formatNumber value="${pam.sccbhj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sccbhj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy2&months=${months}')" >
									<fmt:formatNumber value="${pam.sccbhj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sccbhj3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy1&months=${months}')" >
									<fmt:formatNumber value="${pam.sccbhj3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>一、直接材料</td>
						<td align="center">2</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjcl1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${pam.zjcl1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjcl2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${pam.zjcl2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjcl3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${pam.zjcl3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;（一）原材料</td>
						<td align="center">3</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ycl1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${pam.ycl1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ycl2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${pam.ycl2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ycl3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${pam.ycl3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp; 1.外购成附件</td>
						<td align="center">5</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wgcfj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${pam.wgcfj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wgcfj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${pam.wgcfj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wgcfj3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${pam.wgcfj3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;2.其他原材料</td>
						<td align="center">6</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qtycL1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6&months=${months}')" >
									<fmt:formatNumber value="${pam.qtycL1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qtycL2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6months=${months}')" >
									<fmt:formatNumber value="${pam.qtycL2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qtycL3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6&months=${months}')" >
									<fmt:formatNumber value="${pam.qtycL3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;（二）燃料和动力</td>
						<td align="center">7</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.rlhdL1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7&months=${months}')" >
									<fmt:formatNumber value="${pam.rlhdL1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.rlhdL2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7months=${months}')" >
									<fmt:formatNumber value="${pam.rlhdL2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.rlhdL3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7&months=${months}')" >
									<fmt:formatNumber value="${pam.rlhdL3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;二、直接人工</td>
						<td align="center">8</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjrg1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8&months=${months}')" >
									<fmt:formatNumber value="${pam.zjrg1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjrg2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8months=${months}')" >
									<fmt:formatNumber value="${pam.zjrg2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjrg3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8&months=${months}')" >
									<fmt:formatNumber value="${pam.zjrg3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>三、外部加工费</td>
						<td align="center">9</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wbjgf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${pam.wbjgf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wbjgf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${pam.wbjgf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wbjgf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${pam.wbjgf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>四、制造费用</td>
						<td align="center">10</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfy3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfy3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>五、专项费用</td>
						<td align="center">11</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zxfy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${pam.zxfy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zxfy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${pam.zxfy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zxfy3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${pam.zxfy3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;（一）专用工装</td>
						<td align="center">12</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zygz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${pam.zygz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zygz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${pam.zygz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zygz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${pam.zygz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;（二）试验费</td>
						<td align="center">13</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.syf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${pam.syf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.syf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${pam.syf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.syf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${pam.syf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;（三）其他</td>
						<td align="center">14</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sccbqt1}"
								 pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${pam.sccbqt1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sccbqt2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${pam.sccbqt2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sccbqt3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${pam.sccbqt3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>六、废品损失</td>
						<td align="center">15</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.fpss1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${pam.fpss1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.fpss2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${pam.fpss2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.fpss3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${pam.fpss3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>加：在产品和自制半成品期初余额</td>
						<td align="center">16</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zcpzzbcpqc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${pam.zcpzzbcpqc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zcpzzbcpqc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${pam.zcpzzbcpqc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zcpzzbcpqc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${pam.zcpzzbcpqc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>减：在产品和自制半成品期末余额</td>
						<td align="center">17</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zcpzzbcpqm}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${pam.zcpzzbcpqm}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zcpzzbcpqm2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${pam.zcpzzbcpqm2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zcpzzbcpqm3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${pam.zcpzzbcpqm3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>制造费用合计：</td>
						<td align="center">18</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfyhj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfyhj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfyhj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfyhj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfyhj3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfyhj3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;职工薪酬</td>
						<td align="center">19</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zgxc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${pam.zgxc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zgxc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${pam.zgxc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zgxc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${pam.zgxc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;其中：工资</td>
						<td align="center">20</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.gz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${pam.gz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.gz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${pam.gz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.gz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${pam.gz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;办公费</td>
						<td align="center">21</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.bgf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${pam.bgf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.bgf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${pam.bgf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.bgf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${pam.bgf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;差旅费</td>
						<td align="center">22</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.clf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${pam.clf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.clf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${pam.clf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.clf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${pam.clf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;机物料消耗</td>
						<td align="center">23</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jwlxh1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${pam.jwlxh1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jwlxh2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${pam.jwlxh2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jwlxh3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${pam.jwlxh3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="left">&nbsp;&nbsp;燃料动力费（含水电费）</th>
						<td align="center">24</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.rldlf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${pam.rldlf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.rldlf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${pam.rldlf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.rldlf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${pam.rldlf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;取暖费</td>
						<td align="center" >25</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qnf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${pam.qnf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qnf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${pam.qnf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qnf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${pam.qnf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;折旧费</td>
						<td align="center">26</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${pam.zjf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${pam.zjf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zjf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${pam.zjf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;修理费</td>
						<td align="center">27</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.xlf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${pam.xlf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.xlf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${pam.xlf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.xlf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${pam.xlf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;低值易耗品摊销</td>
						<td align="center">28</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.dzyhptx1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${pam.dzyhptx1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.dzyhptx2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${pam.dzyhptx2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.dzyhptx3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${pam.dzyhptx3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;劳动保护费
						</td>
						<td align="center">29</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ldbhf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${pam.ldbhf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ldbhf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${pam.ldbhf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ldbhf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${pam.ldbhf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						 保险费
						</td>
						<td align="center">30</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.bxf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${pam.bxf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.bxf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${pam.bxf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.bxf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${pam.bxf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						租赁费
						</td>
						<td align="center">31</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zhulinf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${pam.zhulinf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zhulinf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${pam.zhulinf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zhulinf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${pam.zhulinf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						运输费
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ysf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.ysf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ysf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.ysf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.ysf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.ysf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						外部加工费
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wbjgf_1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.wbjgf_1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wbjgf_2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.wbjgf_2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.wbjgf_3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.wbjgf_3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						设计制图费
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sjztf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.sjztf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sjztf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.sjztf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.sjztf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.sjztf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						试验检验费
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.syjyf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.syjyf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.syjyf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.syjyf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.syjyf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.syjyf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						技术资料费
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jszlf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.jszlf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jszlf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.jszlf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jszlf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.jszlf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						季节性和修理期间的停工损失
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.tgss1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.tgss1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.tgss2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.tgss2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.tgss3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.tgss3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						住房补贴
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zfbt1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.zfbt1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zfbt2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.zfbt2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zfbt3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.zfbt3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						借款费用资本化金额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jkfyzbh1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.jkfyzbh1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jkfyzbh2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.jkfyzbh2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.jkfyzbh3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.jkfyzbh3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						其 &nbsp;&nbsp; 他
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qita1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.qita1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qita2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.qita2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.qita3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.qita3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						减：制造费用转出金额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${pam.zzfzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${pam.zzfzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
