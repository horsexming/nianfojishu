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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					凭证信息管理
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="CwCertificateAction_showList.action?tag=${tag}&num_1=${num_1}"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="right">
								编号：
							</td>
							<td align="left">
								<input type="text" name="cwCertificate.number" />
							</td>
							<td align="right">
								凭证日期：
							</td>
							<td align="left">
								<input type="text" name="cwCertificate.pzDate" class="Wdate" 
								onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								凭证简介：
							</td>
							<td align="left">
								<input type="text" name="cwCertificate.introduction" />
							</td>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px; margin-left: 70px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							凭证编号
						</td>
						<td align="center">
							凭证年月
						</td>
						<td align="center">
							简介
						</td>
						<td align="center">
							存放位置
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="cwCertificateList" id="samples" status="pageStatus">
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
							${samples.number}
						</td>
						<td align="center">
							${samples.pzDate}
						</td>
						<td align="center">
							${samples.introduction}
						</td>
						<td align="center">
							${samples.danganguiNum}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
						<td align="center" colspan="2">
							<input type="button" value="选择"
								style="width: 50px; height: 31px;"
								onclick="selectcwDangan('${samples.introduction}','${samples.number}','${samples.danganguiNum}','${samples.danganguiId}','','${num_1}','${samples.id}')" />
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="8" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
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
		function selectcwDangan(name, num, guihao, guid, flieName,num_1,priceID) {
			parent.$("#daName"+num_1).val(name);
			parent.$("#daNum"+num_1).val(num);
			parent.$("#daGuiId"+num_1).val(guid);
			parent.$("#daGuihao"+num_1).val(guihao);
			parent.$("#fileName"+num_1).val(flieName);
			parent.$("#priceID"+num_1).val(priceID);
			parent.chageDiv('none');
		}
</script>
	</body>
</html>
