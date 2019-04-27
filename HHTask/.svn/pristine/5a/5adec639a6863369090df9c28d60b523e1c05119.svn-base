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
		<style type="text/css">
			.table{
				border:0px solid #999;
				border-width: 1px;
				border-collapse:collapse;
			}
			.table th,.table td {
				border-width: 1px;
				padding: 0px;
			}
			
			.subTable{
				text-align: center;
				border-collapse:collapse;
				width: 100%;
				border-width:1px; 
				border-style:hidden;
			}
		</style>
		<script type="text/javascript">
		var processImg='';
		function uploadProcessDataAffixResend(data){
			var processData=JSON.parse(data);
			var hanjieImg=processData.hanjieImg==null? '': processData.hanjieImg;
			document.getElementById("hanjieImg").src=hanjieImg;
		}
		</script>
	</head>
	<body style="text-align: center;">
			<div align="center" style="width: 100%;">
				<!-- A4页面开始 -->
				<div id="printDiv">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：1549×1084   5mm/18.89  19-->
					<div style="width:1549px;border:1px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<%--<table border="0" class="table" cellspacing="0" cellpadding="0" style="width: 100%;">
						<!-- 1  19 9-->
						<tr>
							<td rowspan="2" style="">
							<img style="width: 80px;height: 90px;" src="<%=basePath%>${companyInfo.shhhjpg}"/>
							</td>
							<td rowspan="2" colspan="7" style="">焊接图表</td><td colspan="2" style="">型别</td><td colspan="5" style="">件号</td><td colspan="4" style="">件名</td>
							<td colspan="3" style="">工序名称</td><td colspan="2" style="">序号</td><td colspan="3" style="">材料</td><td style="">第<input type="text" id="twoPage" name="processData.twoPage" style="width: 20px;"/>1页</td>
						</tr>
						<!-- 2 -->
						<tr>
							<td colspan="2">${gongyiGuicheng.xingbie}</td><td colspan="5">${gongyiGuicheng.jianNumb}</td><td colspan="4">${gongyiGuicheng.jianName}</td>
							<td colspan="3">${processData.gongxuName}</td><td colspan="2">${processData.gongxuNo}</td><td colspan="3"><input type="text" name="processData.cailiao" style="width: 50px;"/></td><td>共1页</td>
						</tr>
						<!-- 3 -->
						<tr>
							<td colspan="2">设备</td><td colspan="17"><input type="text" name="processData.shebeiName" disabled="disabled"/></td>
							<td style="width: 50xp;">项目</td><td colspan="8">作业规范</td>
						</tr>
						<!-- 4 -->
						<tr>
							<td colspan="2">基准</td><td colspan="17"><input type="text" name="processData.jizhun"/></td>
							<td style="width: 50xp;"><input type="hidden" name="hanjieZuoyeGuifan.id" /><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 50px;;"/></td><td colspan="8"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
						</tr>
						<!-- 5 -->
						<tr>
							<td colspan="2">夹\模具</td><td colspan="17"><input type="text" name="processData.jiaOrMoju" disabled="disabled"/></td>
							<td style="width: 50xp;"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
						</tr>
						<!-- 6 -->
						<tr>
							<td colspan="19" rowspan="27">
								<p><img id="hanjieImg" style="width: 500px;height: 600px;" src="${processData.hanjieImg}"/></p>
								<p>
								    <form action="gongyiGuichengAction!uploadProcessDataAffix.action?affixType=hanjieImg" enctype="multipart/form-data" method="post" target="hidden_iframe">  
								      <div style="position:relative;display:none;">  
								        <input type="hidden" name="processData.id" value="${processData.id}" />  
								        <iframe name="hidden_iframe" id="hidden_iframe"></iframe>  
								      </div>  
							    	  <input type="file" name="processDataImgFile" />  
									  <input type="button" id="upload" value=" 上 传 " onclick="this.form.submit();"  />
								    </form>  
								</p>
							</td>
							<td style="width: 50xp;"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
						</tr>
						<!-- 7 -->
						<tr>
							<td style="width: 50xp;"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
						</tr>
						<!-- 8 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 9 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 10 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 11 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 12 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 13 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 14 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 15 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 16 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 17 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 18 -->
						<tr>
							<td>&nbsp;</td><td colspan="8">&nbsp;</td>
						</tr>
						<!-- 19 -->
						<tr>
							<td rowspan="4" style="width: 50px;">过程参数</td><td colspan="2">材料牌号及厚度</td><td colspan="2">焊接名称或直径</td><td>气体流量</td><td>电流强度</td><td>保护气体</td><td>钨丝直径</td>
						</tr>
						<!-- 20 -->
						<tr>
							<td colspan="2"><input type="hidden" name="hanjieGuochengCanshu.id"/><input type="text" name="hanjieGuochengCanshu.cailiaoPaihaoHoudu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieGuochengCanshu.hanliaoMingchengZhijing" style="width: 100%;"/></td><td><input type="text" name="hanjieGuochengCanshu.qitiLiuliang" style="width: 100%;"/></td><td><input type="text" name="hanjieGuochengCanshu.dianliuQiangdu" style="width: 100%;"/></td><td><input type="text" name="hanjieGuochengCanshu.baohuQiti" style="width: 100%;"/></td><td><input type="text" name="hanjieGuochengCanshu.wusiZhijing" style="width: 100%;"/></td>
						</tr>
						<!-- 21 -->
						<tr>
							<td  rowspan="2">附注</td><td colspan="5"><input type="text" name="hanjieGuochengCanshu.remark" style="width: 100%;"/></td><td>送丝速度</td><td>电弧电压</td>
						</tr>
						<!-- 22 -->
						<tr>
							<td colspan="5">空白</td><td><input type="text" name="hanjieGuochengCanshu.songsiSudu" style="width: 100%;"/></td><td><input type="text" name="hanjieGuochengCanshu.dianhuDianya" style="width: 100%;"/></td>
						</tr>
						<!-- 23 -->
						<tr>
							<td>序号</td><td colspan="2">检测项目</td><td colspan="2">测量器具</td><td colspan="2">操作者测定频次</td><td colspan="2">巡检测定频次</td>
						</tr>
						<!-- 24 -->
						<tr>
							<td style="width: 50xp;"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 25 -->
						<tr>
							<td style="width: 50xp;"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 50px;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 26 -->
						<tr>
							<td style="width: 50xp;"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 50px;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 27 -->
						<tr>
							<td style="width: 50xp;"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 50px;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 28 -->
						<tr>
							<td>&nbsp;</td><td colspan="2"></td><td colspan="2"></td><td colspan="2"></td><td colspan="2"></td>
						</tr>
						<!-- 29 -->
						<tr>
							<td>&nbsp;</td><td colspan="2"></td><td colspan="2"></td><td colspan="2"></td><td colspan="2"></td>
						</tr>
						<!-- 30 -->
						<tr>
							<td>&nbsp;</td><td colspan="2"></td><td colspan="2"></td><td colspan="2"></td><td colspan="2"></td>
						</tr>
						<!-- 31 -->
						<tr>
							<td>&nbsp;</td><td colspan="2"></td><td colspan="2"></td><td colspan="2"></td><td colspan="2"></td>
						</tr>
						<!-- 32 -->
						<tr>
							
						</tr>
						<!-- 33 -->
						<tr>
							<td rowspan="3">更改</td><td colspan="2">索引号</td><td colspan="2"><input type="text" name="processData.suoyinNumb"/></td><td colspan="2">&nbsp;</td><td colspan="2" ><p style="width: 0px;"></p></td><td colspan="2" ><p style="width: 0px;"></p></td><td colspan="2" ><p style="width: 0px;"></p></td><td colspan="2" ><p style="width: 0px;"></p></td><td colspan="2" ><p style="width: 0px;"></p></td><td colspan="2" ><p style="width: 0px;"></p></td>
							<td colspan="2">编制</td><td>${gongyiGuicheng.bianzhiName}</td><td><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></td><td colspan="3">批准</td><td>${gongyiGuicheng.pizhunName}</td><td><s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></td>
						</tr>
						<!-- 34 -->
						<tr>
							<td colspan="2">更改单号</td><td colspan="2"><input type="text" name="processData.danNumb"/></td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td>
							<td colspan="2">校队</td><td>${gongyiGuicheng.jiaoduiName}</td><td><s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></td><td rowspan="2">会签</td><td colspan="2">生产部加工</td><td>${gongyiGuicheng.jiagongName}</td><td><s:date name="gongyiGuicheng.jiagongDate"  format="yyyy-MM-dd"/></td>
						</tr>
						<!-- 35 -->
						<tr>
							<td colspan="2">签名、日期</td><td colspan="2"><input type="text" name="processData.qianming"/></td><td colspan="2"><input class="Wdate" type="text" name="processData.qianmingDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td>
							<td colspan="2">审核</td><td>${gongyiGuicheng.shenheName}</td><td><s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></td><td colspan="2">技术质保部品质</td><td>${gongyiGuicheng.pinzhiName}</td><td><s:date name="gongyiGuicheng.pinzhiDate"  format="yyyy-MM-dd"/></td>
						</tr>
					</table>
					--%>
					<table border="1" cellspacing="0" cellpadding="0" width="1474" style="border:1px solid #999;border-collapse:collapse;">
					  <tr>
					    <td width="66" height="90" colspan="2" rowspan="2"><img style="width: 100%;height: 100%;" src="<%=basePath%>${companyInfo.shhhjpg}"/></td>
					    <td width="222" colspan="4" rowspan="2" style="font-size: 25px;"><p align="center">焊接图表 </p></td>
					    <td width="150" colspan="3"><p align="center">型别</p></td>
					    <td width="150" colspan="3"><p align="center">件号</p></td>
					    <td width="269" colspan="3"><p align="center">件名</p></td>
					    <td width="169" colspan="4"><p align="center">工序名称</p></td>
					    <td width="136" colspan="6"><p align="center">工序号</p></td>
					    <td width="197" colspan="5"><p align="center">材料</p></td>
					    <td width="38" colspan="2"><p align="center">第</p></td>
					    <td width="39" colspan="2"><p align="center">1</p></td>
					    <td width="39"><p align="center">页</p></td>
					  </tr>
					  <tr>
					    <td width="150" colspan="3"><p align="center">${gongyiGuicheng.xingbie}</p></td>
					    <td width="150" colspan="3"><p align="center">${gongyiGuicheng.jianNumb}</p></td>
					    <td width="269" colspan="3"><p align="center">${gongyiGuicheng.jianName}</p></td>
					    <td width="169" colspan="4"><p align="center">${processData.gongxuName}</p></td>
					    <td width="136" colspan="6"><p align="center">${processData.gongxuNo}</p></td>
					    <td width="197" colspan="5"><p align="center"><input type="text" name="processData.cailiao" style="width: 100%;"/></p></td>
					    <td width="38" colspan="2"><p align="center">共</p></td>
					    <td width="39" colspan="2"><p align="center">1</p></td>
					    <td width="39"><p align="center">页</p></td>
					  </tr>
					  <tr>
					    <td width="143" colspan="4"><p align="center">设备</p></td>
					    <td width="713" colspan="11"><input type="text" name="processData.shebeiName" disabled="disabled" style="width: 100%;"/></td>
					    <td width="50"><p align="center">项目</p></td>
					    <td width="568" colspan="19"><p align="center">作业规范</p></td>
					  </tr>
					  <tr>
					    <td width="143" colspan="4"><p align="center">基准</p></td>
					    <td width="713" colspan="11"><input type="text" name="processData.jizhun" style="width: 100%;"/></td>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id" /><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="143" colspan="4"><p align="center">夹/模具</p></td>
					    <td width="713" colspan="11"><input type="text" name="processData.jiaOrMoju" disabled="disabled" style="width: 100%;"/></td>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="857" colspan="15" rowspan="27"><p><img id="hanjieImg" style="width: 500px;height: 600px;" src="${processData.hanjieImg}"/></p>
								<p>
								    <form action="gongyiGuichengAction!uploadProcessDataAffix.action?affixType=hanjieImg" enctype="multipart/form-data" method="post" target="hidden_iframe">  
								      <div style="position:relative;display:none;">  
								        <input type="hidden" name="processData.id" value="${processData.id}" />  
								        <iframe name="hidden_iframe" id="hidden_iframe"></iframe>  
								      </div>  
							    	  <input type="file" name="processDataImgFile" />  
									  <input type="button" id="upload" value=" 上 传 " onclick="this.form.submit();"  />
								    </form>  
								</p>
						</td>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieZuoyeGuifan.id"/><input type="text" name="hanjieZuoyeGuifan.numb" style="width: 100%;"/></td>
					    <td width="568" colspan="19"><input type="text" name="hanjieZuoyeGuifan.content" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50" rowspan="4"><p align="center"><strong>过<br/>程<br/>参<br/>数 </strong></p></td>
					    <td width="121" colspan="4"><p align="center">材料牌号及厚度厚度</p></td>
					    <td width="132" colspan="4"><p align="center">焊料名称直径</p></td>
					    <td width="85" colspan="3"><p align="center">气体流量</p></td>
					    <td width="76" colspan="2"><p align="center">电流强度</p></td>
					    <td width="76" colspan="4"><p align="center">保护气体</p></td>
					    <td width="78" colspan="2"><p align="center">钨丝直径</p></td>
					  </tr>
					  <tr>
					    <td width="121" colspan="4"><input type="hidden" name="hanjieGuochengCanshu.id"/><input type="text" name="hanjieGuochengCanshu.cailiaoPaihaoHoudu" style="width: 100%;"/></td>
					    <td width="132" colspan="4"><input type="text" name="hanjieGuochengCanshu.hanliaoMingchengZhijing" style="width: 100%;"/></td>
					    <td width="85" colspan="3"><input type="text" name="hanjieGuochengCanshu.qitiLiuliang" style="width: 100%;"/></td>
					    <td width="76" colspan="2"><input type="text" name="hanjieGuochengCanshu.dianliuQiangdu" style="width: 100%;"/></td>
					    <td width="76" colspan="4"><input type="text" name="hanjieGuochengCanshu.baohuQiti" style="width: 100%;"/></td>
					    <td width="78" colspan="2"><input type="text" name="hanjieGuochengCanshu.wusiZhijing" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="36" colspan="2" rowspan="2"><p align="center">附注</p></td>
					    <td width="378" colspan="11"><p align="center"><input type="text" name="hanjieGuochengCanshu.remark" style="width: 100%;"/></p></td>
					    <td width="76" colspan="4"><p align="center">送丝速度</p></td>
					    <td width="78" colspan="2"><p align="center">电弧电压</p></td>
					  </tr>
					  <tr>
					    <td width="378" colspan="11"><p align="center">&nbsp;</p></td>
					    <td width="76" colspan="4"><input type="text" name="hanjieGuochengCanshu.songsiSudu" style="width: 100%;"/></td>
					    <td width="78" colspan="2"><input type="text" name="hanjieGuochengCanshu.dianhuDianya" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><p align="center"><strong>序号 </strong></p></td>
					    <td width="160" colspan="5"><p align="center"><strong>检测项目 </strong></p></td>
					    <td width="150" colspan="5"><p align="center"><strong>测量器具 </strong></p></td>
					    <td width="142" colspan="4"><p align="center"><strong>操作者测定频次 </strong></p></td>
					    <td width="116" colspan="5"><p align="center"><strong>巡检测定频次 </strong></p></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td>
					    <td width="160" colspan="5"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td>
					    <td width="150" colspan="5"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td>
					    <td width="142" colspan="4"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td>
					    <td width="116" colspan="5"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td>
					    <td width="160" colspan="5"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td>
					    <td width="150" colspan="5"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td>
					    <td width="142" colspan="4"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td>
					    <td width="116" colspan="5"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td>
					    <td width="160" colspan="5"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td>
					    <td width="150" colspan="5"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td>
					    <td width="142" colspan="4"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td>
					    <td width="116" colspan="5"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td>
					    <td width="160" colspan="5"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td>
					    <td width="150" colspan="5"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td>
					    <td width="142" colspan="4"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td>
					    <td width="116" colspan="5"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td>
					    <td width="160" colspan="5"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td>
					    <td width="150" colspan="5"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td>
					    <td width="142" colspan="4"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td>
					    <td width="116" colspan="5"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td>
					    <td width="160" colspan="5"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td>
					    <td width="150" colspan="5"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td>
					    <td width="142" colspan="4"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td>
					    <td width="116" colspan="5"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td>
					    <td width="160" colspan="5"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td>
					    <td width="150" colspan="5"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td>
					    <td width="142" colspan="4"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td>
					    <td width="116" colspan="5"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="50"><input type="hidden" name="hanjieJianceXiangmu.id"/><input type="text" name="hanjieJianceXiangmu.numb" style="width: 100%;"/></td>
					    <td width="160" colspan="5"><input type="text" name="hanjieJianceXiangmu.xiangmu" style="width: 100%;"/></td>
					    <td width="150" colspan="5"><input type="text" name="hanjieJianceXiangmu.qiju" style="width: 100%;"/></td>
					    <td width="142" colspan="4"><input type="text" name="hanjieJianceXiangmu.caozuoPinci" style="width: 100%;"/></td>
					    <td width="116" colspan="5"><input type="text" name="hanjieJianceXiangmu.xunjianPinci" style="width: 100%;"/></td>
					  </tr>
					  <tr>
					    <td width="38" rowspan="3"><p align="center"><strong>更改 </strong></p></td>
					    <td width="100" colspan="2"><p align="center"><strong>索引号 </strong></p></td>
					    <td width="96" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="95" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="85"><p align="center">&nbsp;</p></td>
					    <td width="85" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="85"><p align="center">&nbsp;</p></td>
					    <td width="85" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="94"><p align="center">&nbsp;</p></td>
					    <td width="93"><p align="center">&nbsp;</p></td>
					    <td width="80" colspan="2"><p align="center"><strong>编制 </strong></p></td>
					    <td width="91" colspan="3"><p align="center">${gongyiGuicheng.bianzhiName}</p></td>
					    <td width="95" colspan="2"><p align="center"><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></p></td>
					    <td width="174" colspan="6"><p align="center"><strong>批准 </strong></p></td>
					    <td width="91" colspan="3"><p align="center">${gongyiGuicheng.pizhunName}</p></td>
					    <td width="88" colspan="4"><p align="center"><s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></p></td>
					  </tr>
					  <tr>
					    <td width="100" colspan="2"><p align="center"><strong>更改单号 </strong></p></td>
					    <td width="96" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="95" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="85"><p align="center">&nbsp;</p></td>
					    <td width="85" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="85"><p align="center">&nbsp;</p></td>
					    <td width="85" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="94"><p align="center">&nbsp;</p></td>
					    <td width="93"><p align="center">&nbsp;</p></td>
					    <td width="80" colspan="2"><p align="center"><strong>校对 </strong></p></td>
					    <td width="91" colspan="3"><p align="center">${gongyiGuicheng.jiaoduiName}</p></td>
					    <td width="95" colspan="2"><p align="center"><s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></p></td>
					    <td width="28" rowspan="2"><p align="center"><strong>会签 </strong></p></td>
					    <td width="145" colspan="5"><p align="center"><strong>生产部加工 </strong></p></td>
					    <td width="91" colspan="3"><p align="center">${gongyiGuicheng.jiagongName}</p></td>
					    <td width="88" colspan="4"><p align="center"><s:date name="gongyiGuicheng.jiagongDate"  format="yyyy-MM-dd"/></p></td>
					  </tr>
					  <tr>
					    <td width="100" colspan="2"><p align="center"><strong>签名日期 </strong></p></td>
					    <td width="96" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="95" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="85"><p align="center">&nbsp;</p></td>
					    <td width="85" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="85"><p align="center">&nbsp;</p></td>
					    <td width="85" colspan="2"><p align="center">&nbsp;</p></td>
					    <td width="94"><p align="center">&nbsp;</p></td>
					    <td width="93"><p align="center">&nbsp;</p></td>
					    <td width="80" colspan="2"><p align="center"><strong>审核 </strong></p></td>
					    <td width="91" colspan="3"><p align="center">${gongyiGuicheng.shenheName}</p></td>
					    <td width="95" colspan="2"><p align="center"><s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></p></td>
					    <td width="145" colspan="5"><p align="center"><strong>技术质保部品质 </strong></p></td>
					    <td width="91" colspan="3"><p align="center">${gongyiGuicheng.pinzhiName}</p></td>
					    <td width="88" colspan="4"><p align="center"><s:date name="gongyiGuicheng.pinzhiDate"  format="yyyy-MM-dd"/></p></td>
					  </tr>
					</table>
					</div>
				</div>
			</div>
			<br/>
			<div style="width: 794px;text-align: center;margin: 0 auto;">
				<form id="markingForm" method="post"
					style="">
					<input type="hidden" name="gongyiGuichengScore.gongyiGuichengId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" name="gongyiGuichengScore.model" value="gxsmlq"/>
					<input type="hidden" name="gongyiGuichengScore.processDataId" value="${processData.id}"/>
				<!-- 工序说明栏区 -->
				<table id="gxsmlq" style="display: block;">
					<tr>
						<td><span>设备栏：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.shebeiScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.shebeiScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.shebeiScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>工序名称：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="jianNumbScore1" name="gongyiGuichengScore.gongxuNameScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="jianNumbScore2" name="gongyiGuichengScore.gongxuNameScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="jianNumbScore3" name="gongyiGuichengScore.gongxuNameScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>工序号：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="jianNameScore1" name="gongyiGuichengScore.gongxuNumbScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="jianNameScore2" name="gongyiGuichengScore.gongxuNumbScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="jianNameScore3" name="gongyiGuichengScore.gongxuNumbScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>工装：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.toolsScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.toolsScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.toolsScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>页码：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.pageNumbScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.pageNumbScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.pageNumbScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>尺寸标注没有遗漏：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.chicunBiaozhuYilouScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.chicunBiaozhuYilouScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>尺寸标注不会产生观察误导：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.chicunBiaozhuWudaoScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.chicunBiaozhuWudaoScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>尺寸标注符合公司标准：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.chicunBiaozhuBiaozhunScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.chicunBiaozhuBiaozhunScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>图形符合国标制图标准：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.tuxingBiaozhunScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.tuxingBiaozhunScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>轴测图或图片显示示意：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.zhucetuShiyiScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.zhucetuShiyiScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>图片或轴测图表示准确：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.zhucetuBiaozhunScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.zhucetuBiaozhunScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>项目栏：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.xiangmuScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.xiangmuScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.xiangmuScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>作业规范栏：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.zuoyeGuifanScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.zuoyeGuifanScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.zuoyeGuifanScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>技术参数栏：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.jishuCanshuScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.jishuCanshuScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.jishuCanshuScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>索引号：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.suoyinScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.suoyinScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.suoyinScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>更改单号：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.danNumbScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.danNumbScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.danNumbScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>签名：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.qianmingScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.qianmingScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.qianmingScoreGxsmlq" value="-2"/>
						</td>
					</tr>
				</table>
				</form>
				<table style="width: 100%;text-align: center;border: 0px;">
					<tr>
						<td><input type="button" id="saveButton" value="保存" class="input" /><input type="button" id="markingButton" value="打分" class="input"  /><input type="button" id="printButton" value="打印" class="input" /><input type="button" id="finishButton" value="完成工序编辑" class="input" style="width: 120px;"/></td>
					</tr>
				</table>
			</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function(){
	//全局工艺规程状态
	var dengluId='${sessionScope.Users.id}';
	var status='';
	var bianzhiId='';
	var jiaoduiId='';
	var shenheId='';
	var pizhunId='';
	var gygcId=$("#gygcId").val();
	var processDataId=$("#processDataId").val();
	$("#printButton").bind("click",function() {
		window.location="gongyiGuichengAction!getGongyiGuiChengGxsmlqHanjiePage.action?gongyiGuicheng.id="+gygcId+"&processData.gongyiGuichengId="+gygcId+"&processData.id="+processDataId+"&print=print";
	});
	//初始化工序数据
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getProcessDataId.action",
        data:{
			'processData.id': processDataId
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var processData=data.data;
				var cailiao=processData.cailiao;
				var shebeiName=processData.shebeiName;
				var gongzhuangName=processData.gongzhuangName;
				var jizhun=processData.jizhun;
				var jiaOrMoju=processData.jiaOrMoju;
				//var suoyinNumb=processData.suoyinNumb;
				//var danNumb=processData.danNumb;
				//var qianming=processData.qianming;
				//var qianmingDate=processData.qianmingDate==null? new Date(): processData.qianmingDate;
				//qianmingDate=new Date(qianmingDate).format('yyyy-MM-dd');
				//var twoPage=processData.twoPage;
				var $cailiao=$("input[name='processData.cailiao']");
				var $shebeiName=$("input[name='processData.shebeiName']");
				var $jizhun=$("input[name='processData.jizhun']");
				var $jiaOrMoju=$("input[name='processData.jiaOrMoju']");
				//var $suoyinNumb=$("input[name='processData.suoyinNumb']");
				//var $danNumb=$("input[name='processData.danNumb']");
				//var $qianming=$("input[name='processData.qianming']");
				//var $qianmingDate=$("input[name='processData.qianmingDate']");
				//var $twoPage=$("input[name='processData.twoPage']");
				$cailiao.val(cailiao);
				$shebeiName.val(shebeiName);
				$jizhun.val(jizhun);
				$jiaOrMoju.val(gongzhuangName);
				//$suoyinNumb.val(suoyinNumb);
				//$danNumb.val(danNumb);
				//$qianming.val(qianming);
				//$qianmingDate.val(qianmingDate);
				//$twoPage.val(twoPage);
			}
		}
	});
	//初始化焊接作业规范
	
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getHanjieZuoyeGuifanListByProcessDataId.action",
        data:{
			'hanjieZuoyeGuifan.processDataId': processDataId
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				//$("#operationStandardTable tr:gt(0)").remove();
				var $id=$("input[name='hanjieZuoyeGuifan.id']");
				var $numb=$("input[name='hanjieZuoyeGuifan.numb']");
				var $content=$("input[name='hanjieZuoyeGuifan.content']");
				var operationStandardArr=data.data;
				$(operationStandardArr).each(function(i,n){
					//$('<td><input type="hidden" name="operationStandardTable.id" value=""/><input type="text" name="operationStandardTable.numb"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>').appendTo($($id[i]).parent());
					$id.eq(i).val(this.id);
					$numb.eq(i).val(this.numb);
					$content.eq(i).val(this.content);
				});
			}
		}
	});
	
	//初始化检查项目
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getHanjieJianceXiangmuListByProcessDataId.action",
        data:{
			'hanjieJianceXiangmu.processDataId': processDataId
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				//$("#operationStandardTable tr:gt(0)").remove();
				var $idArr=$("input[name='hanjieJianceXiangmu.id']");
				var $numbArr=$("input[name='hanjieJianceXiangmu.numb']");
				var $xiangmuArr=$("input[name='hanjieJianceXiangmu.xiangmu']");
				var $qijuArr=$("input[name='hanjieJianceXiangmu.qiju']");
				var $caozuoPinciArr=$("input[name='hanjieJianceXiangmu.caozuoPinci']");
				var $xunjianPinciArr=$("input[name='hanjieJianceXiangmu.xunjianPinci']");
				var detectionItemArr=data.data;
				$(detectionItemArr).each(function(i,n){
					$($idArr[i]).val(this.id);
					$($numbArr[i]).val(this.numb);
					$($xiangmuArr[i]).val(this.xiangmu);
					$($qijuArr[i]).val(this.qiju);
					$($caozuoPinciArr[i]).val(this.caozuoPinci);
					$($xunjianPinciArr[i]).val(this.xunjianPinci);
				});
			}
		}
	});
	
	//初始化焊接过程参数
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getHanjieGuochengCanshuByProcessDataId.action",
        data:{
			'hanjieGuochengCanshu.processDataId': processDataId
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				data=data.data;
				var id=data.id;
				var cailiaoPaihaoHoudu=data.cailiaoPaihaoHoudu;
				var hanliaoMingchengZhijing=data.hanliaoMingchengZhijing;
				var qitiLiuliang=data.qitiLiuliang;
				var dianliuQiangdu=data.dianliuQiangdu;
				var baohuQiti=data.baohuQiti;
				var wusiZhijing=data.wusiZhijing;
				var songsiSudu=data.songsiSudu;
				var dianhuDianya=data.dianhuDianya;
				var remark=data.remark;
				var $id=$("input[name='hanjieGuochengCanshu.id']");
				var $cailiaoPaihaoHoudu=$("input[name='hanjieGuochengCanshu.cailiaoPaihaoHoudu']");
				var $hanliaoMingchengZhijing=$("input[name='hanjieGuochengCanshu.hanliaoMingchengZhijing']");
				var $qitiLiuliang=$("input[name='hanjieGuochengCanshu.qitiLiuliang']");
				var $dianliuQiangdu=$("input[name='hanjieGuochengCanshu.dianliuQiangdu']");
				var $baohuQiti=$("input[name='hanjieGuochengCanshu.baohuQiti']");
				var $wusiZhijing=$("input[name='hanjieGuochengCanshu.wusiZhijing']");
				var $songsiSudu=$("input[name='hanjieGuochengCanshu.songsiSudu']");
				var $dianhuDianya=$("input[name='hanjieGuochengCanshu.dianhuDianya']");
				var $remark=$("input[name='hanjieGuochengCanshu.remark']");
				$id.val(id);
				$cailiaoPaihaoHoudu.val(cailiaoPaihaoHoudu);
				$hanliaoMingchengZhijing.val(hanliaoMingchengZhijing);
				$qitiLiuliang.val(qitiLiuliang);
				$dianliuQiangdu.val(dianliuQiangdu);
				$baohuQiti.val(baohuQiti);
				$wusiZhijing.val(wusiZhijing);
				$songsiSudu.val(songsiSudu);
				$dianhuDianya.val(dianhuDianya);
				$remark.val(remark);
				
			}
		}
	});
	
	//添加工序数据Data jQuery数组取第一个值 select不适用这种方法
	function getProcessDataParams(){
		var $cailiao=$("input[name='processData.cailiao']");
		//var $shebeiName=$("input[name='processData.shebeiName']");
		var $jizhun=$("input[name='processData.jizhun']");
		//var $jiaOrMoju=$("input[name='processData.jiaOrMoju']");
		var $suoyinNumb=$("input[name='processData.suoyinNumb']");
		var $danNumb=$("input[name='processData.danNumb']");
		var $qianming=$("input[name='processData.qianming']");
		var $qianmingDate=$("input[name='processData.qianmingDate']");
		var $twoPage=$("input[name='processData.twoPage']");
		var processData={
			'id': $("#processDataId").val(),
			'cailiao':$cailiao.val(),
			//'shebeiName':$shebeiName.val(),
			'jizhun':$jizhun.val()
			//'jiaOrMoju':$jiaOrMoju.val(),
			//'suoyinNumb':$suoyinNumb.val(),
			//'danNumb':$danNumb.val(),
			//'qianming':$qianming.val(),
			//'qianmingDate':$qianmingDate.val(),
			//'twoPage':$twoPage.val()
		};
		return JSON.stringify(processData);
	}
	
	//添加焊接作业规范
	function getHanjieZuoyeGuifanParams(){
		var $idArr=$("input[name='hanjieZuoyeGuifan.id']");
		var $numbArr=$("input[name='hanjieZuoyeGuifan.numb']");
		var $contentArr=$("input[name='hanjieZuoyeGuifan.content']");
		var hanjieZuoyeGuifanArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			hanjieZuoyeGuifanArr[i]={	
							'id': $($idArr[i]).val(),
							'numb':$($numbArr[i]).val(),
							'content':$($contentArr[i]).val(),
							'processDataId': processDataId
						  };
		}
		return JSON.stringify(hanjieZuoyeGuifanArr);
	}
	
	//添加焊接检查项目
	function getHanjieJianceXiangmuParams(){
		var $idArr=$("input[name='hanjieJianceXiangmu.id']");
		var $numbArr=$("input[name='hanjieJianceXiangmu.numb']");
		var $xiangmuArr=$("input[name='hanjieJianceXiangmu.xiangmu']");
		var $qijuArr=$("input[name='hanjieJianceXiangmu.qiju']");
		var $caozuoPinciArr=$("input[name='hanjieJianceXiangmu.caozuoPinci']");
		var $xunjianPinciArr=$("input[name='hanjieJianceXiangmu.xunjianPinci']");
		var $processDataIdArr=$("input[name='hanjieJianceXiangmu.processDataId']");
		var hanjieJianceXiangmuArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			hanjieJianceXiangmuArr[i]={	
							'id': $($idArr[i]).val(),
							'numb':$($numbArr[i]).val(),
							'xiangmu':$($xiangmuArr[i]).val(),
							'qiju':$($qijuArr[i]).val(),
							'caozuoPinci':$($caozuoPinciArr[i]).val(),
							'xunjianPinci':$($xunjianPinciArr[i]).val(),
							'processDataId': processDataId
						  };
		}
		return JSON.stringify(hanjieJianceXiangmuArr);
	}
	
	//添加焊接过程参数
	function getHanjieGuochengCanshuParams(){
		var $id=$("input[name='hanjieGuochengCanshu.id']");
		var $cailiaoPaihaoHoudu=$("input[name='hanjieGuochengCanshu.cailiaoPaihaoHoudu']");
		var $hanliaoMingchengZhijing=$("input[name='hanjieGuochengCanshu.hanliaoMingchengZhijing']");
		var $qitiLiuliang=$("input[name='hanjieGuochengCanshu.qitiLiuliang']");
		var $dianliuQiangdu=$("input[name='hanjieGuochengCanshu.dianliuQiangdu']");
		var $baohuQiti=$("input[name='hanjieGuochengCanshu.baohuQiti']");
		var $wusiZhijing=$("input[name='hanjieGuochengCanshu.wusiZhijing']");
		var $songsiSudu=$("input[name='hanjieGuochengCanshu.songsiSudu']");
		var $dianhuDianya=$("input[name='hanjieGuochengCanshu.dianhuDianya']");
		var $remark=$("input[name='hanjieGuochengCanshu.remark']");
		hanjieGuochengCanshu={	
				'id': $id.val(),
				'cailiaoPaihaoHoudu':$cailiaoPaihaoHoudu.val(),
				'hanliaoMingchengZhijing':$hanliaoMingchengZhijing.val(),
				'qitiLiuliang':$qitiLiuliang.val(),
				'dianliuQiangdu':$dianliuQiangdu.val(),
				'baohuQiti':$baohuQiti.val(),
				'wusiZhijing':$wusiZhijing.val(),
				'songsiSudu':$songsiSudu.val(),
				'dianhuDianya':$dianhuDianya.val(),
				'remark':$remark.val(),
				'processDataId':processDataId
			  };
		return JSON.stringify(hanjieGuochengCanshu);
	}
	//********************************保存页面数据******************************************************
	$("#saveButton").bind("click",function(){
		var processDataParams=getProcessDataParams();
		var hanjieZuoyeGuifanParams=getHanjieZuoyeGuifanParams();
		var hanjieJianceXiangmuParams=getHanjieJianceXiangmuParams();
		var hanjieGuochengCanshuParams=getHanjieGuochengCanshuParams();
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "gongyiGuichengAction!updateGongyiGuiChengGxsmlqHanjiePage.action",
	        data:{
				'processData.params': processDataParams,
				'hanjieZuoyeGuifan.params': hanjieZuoyeGuifanParams,
				'hanjieJianceXiangmu.params': hanjieJianceXiangmuParams,
				'hanjieGuochengCanshu.params': hanjieGuochengCanshuParams
			},
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					alert("保存成功");
					window.location.reload();
				}
			}
		});
	});
	//****************************************************打分区*******************************************************
	//$("form table:visible").css('display','none');
	$.ajax({
		type: "get",
		dataType: "json",
        url: "gongyiGuichengScoreAction!getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId.action",
        data:{
			'gongyiGuichengScore.gongyiGuichengId': $("#gygcId").val(),
			'gongyiGuichengScore.model': "gxsmlq",
			'gongyiGuichengScore.processDataId': $('#processDataId').val()
		},
		async: true,
		success: function(data){
			var success=data.success;
			if(success){
				var gongyiGuichengScore=data.data;
				$("input[name='gongyiGuichengScore.shebeiScoreGxsmlq'][value="+gongyiGuichengScore.shebeiScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.gongxuNameScoreGxsmlq'][value="+gongyiGuichengScore.gongxuNameScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.gongxuNumbScoreGxsmlq'][value="+gongyiGuichengScore.gongxuNumbScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.toolsScoreGxsmlq'][value="+gongyiGuichengScore.toolsScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.pageNumbScoreGxsmlq'][value="+gongyiGuichengScore.pageNumbScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.chicunBiaozhuYilouScoreGxsmlq'][value="+gongyiGuichengScore.chicunBiaozhuYilouScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.chicunBiaozhuWudaoScoreGxsmlq'][value="+gongyiGuichengScore.chicunBiaozhuWudaoScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.chicunBiaozhuBiaozhunScoreGxsmlq'][value="+gongyiGuichengScore.chicunBiaozhuBiaozhunScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.tuxingBiaozhunScoreGxsmlq'][value="+gongyiGuichengScore.tuxingBiaozhunScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.zhucetuShiyiScoreGxsmlq'][value="+gongyiGuichengScore.zhucetuShiyiScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.zhucetuBiaozhunScoreGxsmlq'][value="+gongyiGuichengScore.zhucetuBiaozhunScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.xiangmuScoreGxsmlq'][value="+gongyiGuichengScore.xiangmuScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.zuoyeGuifanScoreGxsmlq'][value="+gongyiGuichengScore.zuoyeGuifanScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.jishuCanshuScoreGxsmlq'][value="+gongyiGuichengScore.jishuCanshuScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.suoyinScoreGxsmlq'][value="+gongyiGuichengScore.suoyinScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.danNumbScoreGxsmlq'][value="+gongyiGuichengScore.danNumbScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.qianmingScoreGxsmlq'][value="+gongyiGuichengScore.qianmingScoreGxsmlq+"]").attr("checked",true);
			
				
				
			}
		}
	});
	$('#markingButton').bind('click',function(){
		$.ajax({
			type: "get",
			dataType: "json",
	        url: "gongyiGuichengScoreAction!updateGongyiGuichengScore.action",
	        data:$('#markingForm').serialize(),
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					var gongyiGuichengScore=data.data;
					alert('打分成功!');
				}else{
					alert('打分失败!');
				}
			}
		});
	});
	//*************************************************完成工序编辑****************************************************
	$('#finishButton').bind('click',function(){
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "gongyiGuichengAction!finishProcessData.action",
	        data:{
				'processData.id': $('#processDataId').val()
			},
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					alert("完成工序编辑");
					window.location.reload();
				}
			}
		});
	});
	//****************************************添加权限******************************************************
$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getGongyiGuichengById.action",
        data:{
			'gongyiGuicheng.id': $('#gygcId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var data=data.data;
				
				bianzhiId=data.bianzhiId;
				jiaoduiId=data.jiaoduiId;
				shenheId=data.shenheId;
				pizhunId=data.pizhunId;
				
				status=data.status;
				//$('#button').attr('disabled',"true");添加disabled属性
				//$('#button').removeAttr("disabled"); 移除disabled属性 
				
				//markingForm
				//markingButton
				//saveButton
				//presentButton
				//printButton
				switch(status){
					case '打回':
					case '待编制':
						$("#markingForm input").attr('disabled',true);
						$("#markingButton").attr('disabled',true);
						$("#backButton").attr('disabled',true);
						break;
					case '已编制':
						if(jiaoduiId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						
						break;
					case '已校对':
						if(shenheId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						
						break;
					case '已审核':
						if(pizhunId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						break;
					case '已批准':
						$(":enabled").attr('disabled',true);
						
						$("#printButton").removeAttr("disabled");
						break;
					default :
						
						break;
				}
			}
		}
	});
});
</script>
