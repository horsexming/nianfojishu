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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
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
				<table class="table">
					<tr  bgcolor="#c0dcf2">
						<th>
							序号
						</th>
						<th>
							回款方式
						</th>
						<th>
							报价(单价)
						</th>
					</tr>
					<s:iterator value="list" id="huikuangXiangxi" status="pageIndex">
						<s:if test="#huikuangXiangxi.hname.equals(zhaobiaoXi.t11)">
							<tr style="background-color: #5b7983;">
								<td>
									${pageIndex.index+1}
								</td>
								<td>
									${huikuangXiangxi.hname}
								</td>
								<td>
									${huikuangXiangxi.hmoney}元(每${huikuangXiangxi.danwei})
								</td>
								<td></td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td>
									${pageIndex.index+1}
								</td>
								<td>
									${huikuangXiangxi.hname}
								</td>
								<td>
									${huikuangXiangxi.hmoney}元(每${huikuangXiangxi.danwei})
								</td>
							</tr>
						</s:else>
					</s:iterator>
					<tr>
						<td colspan="4" align="center">
							<input type="button" name="Submit2" value="返回" class="input"
								onclick="window.history.go(-1);" />
						</td>
					</tr>
				</table>


				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<SCRIPT type="text/javascript">
	 
	</SCRIPT>


</html>
