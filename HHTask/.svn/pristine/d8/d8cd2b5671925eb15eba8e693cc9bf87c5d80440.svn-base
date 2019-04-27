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
			<div align="center">
				<table class="table" style="width: 70%">
					<tr>
						<th align="center" colspan="2">
							个人 ${kaoQin.yuefen}考勤汇总
						</th>
					</tr>
					<tr>
						<th align="right">
							卡号:
						</th>
						<td>
							${kaoQin.carId}
						</td>
					</tr>
					<tr>
						<th align="right">
							姓名:
						</th>
						<td>
							${kaoQin.name}
						</td>
					</tr>
					<tr>
						<th align="right">
							部门:
						</th>
						<td>
							${kaoQin.dept}
						</td>
					</tr>
					<tr>
						<th align="right">
							出勤天数:
						</th>
						<td>
							${kaoQin.chuqintianshu}
						</td>
					</tr>
					<tr>
						<th align="right">
							应该出勤天数:
						</th>
						<td>
							${kaoQin.yingchuqin}
						</td>
					</tr>
					<tr>
						<th align="right">
							事假:
						</th>
						<td>
							${kaoQin.shijia}
						</td>
					</tr>
					<tr>
						<th align="right">
							矿工:
						</th>
						<td>
							${kaoQin.kuanggong}
						</td>
					</tr>
					<tr>
						<th align="right">
							病假:
						</th>
						<td>
							${kaoQin.bingjia}
						</td>
					</tr>
					<tr>
						<th align="right">
							年休:
						</th>
						<td>
							${kaoQin.nianxiujia}
						</td>
					</tr>
					<tr>
						<th align="right">
							调休.换休:
						</th>
						<td>
							${kaoQin.tiaoxiu}
						</td>
					</tr>
					<tr>
						<th align="right">
							公休:
						</th>
						<td>
							${kaoQin.gongxiu}
						</td>
					</tr>
					<tr>
						<th align="right">
							产假/陪护假:
						</th>
						<td>
							${kaoQin.chanjia}
						</td>
					</tr>
					<tr>
						<th align="right">
							婚/丧假:
						</th>
						<td>
							${kaoQin.huncangjia}
						</td>
					</tr>
					<tr>
						<th align="right">
							迟到:
						</th>
						<td>
							${kaoQin.lateTime}
						</td>
					</tr>
					<tr>
						<th align="right">
							早退:
						</th>
						<td>
							${kaoQin.earlyTime}
						</td>
					</tr>
					<tr>
						<th align="right">
							其他:
						</th>
						<td>
							${kaoQin.qita}
						</td>
					</tr>
					<tr>
						<th align="right">
							加班.小时:
						</th>
						<td>
							${kaoQin.jiaban}
						</td>
					</tr>
					<tr>
						<th align="right">
							夜班:
						</th>
						<td>
							${kaoQin.yeban}
						</td>
					</tr>
					<tr>
						<th align="right">
							公出:
						</th>
						<td>
							${kaoQin.gongchu}
						</td>
					</tr>
					<tr>
						<th align="right">
							月份:
						</th>
						<td>
							${kaoQin.yuefen}
						</td>
					</tr>
					<tr style="width: 100%; height:200px;">
						<th align="right">
							备注:
						</th>
						<td>
							${kaoQin.beizhu}
						</td>
					</tr>
					<tr>
						<th colspan="2">
							说明:
						</th>
					</tr>
					<tr style="width:100%; height:200px;">
						<td colspan="2" align="center">
							<div id="t1"></div>
						</td>
					</tr>
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		$(function(){
			var shouming="${kaoQin.shuoming}";
			shouming=shouming.replace(/\s+/g," "); 
			var aaa=shouming.split("|");
			var shuo="";
			for(var i=0;i<aaa.length;i++){
			     shuo=shuo+aaa[i]+"</br>";
			}
			document.getElementById("t1").innerHTML=shuo;
		})
	</SCRIPT>
	</body>
</html>
