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
		<script type="text/javascript" src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
		</script>
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
					<a
						href="<%=path%>/System/shizhi/skillscore_add.jsp"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改关键备件<br/>
					（add skillscore）
				</h3>
				<form action="machineSparePartAction_update.action" method="post"
					onsubmit="return validate();">
					<table class="table">
					<tr>
							<th align="right">
								关键设备<br/>（machine）
								<input type="hidden" name="machineSparePart.id" value="<s:property value="machineSparePart.id"/>">
							</th>
							<td>
								<SELECT id="keyMachine" name="machineSparePart.machine.id">
								<s:if test="machineSparePartVo.machineId!=null&&machineSparePartVo.machineId!=0">
								<option value="<s:property value="machineSparePartVo.machineId"/>"><s:property value="machineSparePartVo.machineName"/>
								 </option>
								</s:if>
								 <option value="0">请选择关键设备
								 </option>
                                   <s:iterator value="machinelist" id="machine">
                                     <option value="<s:property value='#machine.id'/>">
                                     <s:property value='#machine.name'/>
                                     </option>
                                   </s:iterator>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								编号<br/>（number）
							</th>
							<td>
								<input type="text" name="machineSparePart.number" id="number"  value="<s:property value="machineSparePartVo.number"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								 名称<br/>（name）
							</th>
							<td>
								<input type="text" name="machineSparePart.matetag" id="matetag"  value="<s:property value="machineSparePartVo.matetag"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								安全库存<br/>（safeCount）
							</th>
							<td>
								<input type="text" name="machineSparePart.safeCount" id="safeCount" value="<s:property value="machineSparePartVo.safeCount"/>" onblur="mustBeNumber('safeCount')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								单位<br/>（unit）
							</th>
							<td>
								<input type="text" name="machineSparePart.unit" id="unit" value="<s:property value="machineSparePartVo.unit"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								规格<br/>（format）
							</th>
							<td>
								<input type="text" name="machineSparePart.format" id="format" value="<s:property value="machineSparePartVo.format"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								分类<br/>（parClass）
							</th>
							<td>
								<input type="text" name="machineSparePart.parClass" id="parClass" value="<s:property value="machineSparePartVo.parClass"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								位置<br/>（difficult score）
							</th>
							<td>
								<input type="text" name="machineSparePart.place" id="place" value="<s:property value="machineSparePartVo.place"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								价格<br/>（price）
							</th>
							<td>
								<input type="text" name="machineSparePart.price" id="price" value="<s:property value="machineSparePartVo.price"/>" onblur="mustBeNumber('price')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								 车型<br/>（carModel）
							</th>
							<td>
								<input type="text" name="machineSparePart.carModel" id="carModel" value="<s:property value="machineSparePartVo.carModel"/>"/>
							</td>
						</tr>
						<tr>
						 <td align="right">
										添加时间:
									</td>
									<td>
										<input id="addtime" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',skin:'whyGreen'})"
											name="machineSparePart.addtime"  value="<s:property value="machineSparePartVo.addtime"/>"/>
										<font color="red">*</font>
									</td>
								</tr>
						<tr>
							<th align="right">
								备注<br/>（remake）
							</th>
							<td>
								<input type="text" name="machineSparePart.remake" id="remake" value="<s:property value="machineSparePartVo.remake"/>"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
if($("#keyMachine").val()==null||$("#keyMachine").val()==0){
		alert("请关键设备!");
		return false;
}
if(!validateMsg("编号","number")){
	return false;
}

if(!validateMsg("名称","matetag")){
	return false;
}

if(!validateMsg("单位","unit")){
	return false;
}

if(!validateMsg("规格","format")){
	return false;
}

if(!validateMsg("分类","parClass")){
	return false;
}
return true;
}
function validateMsg(msg,id){
	if($("#"+id).val()==null&&$("#"+id).val()==""){
		alert(msg+"不能为空!");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
