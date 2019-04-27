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
		table {
			font-size: 14px;
			padding: 0px;
			margin: 0px;
			border-collapse: collapse;
			/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
			border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
			border-width: 1px 0 0 1px;
			
		}
		
		table th,table td {
			border: solid #999;
			border-width: 1 1px 1px 1;
			padding: 2px;
		}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				 <form action="huikuanAction!manageShopping.action" method="post">      
    <table width="900px">
    <tr>
    <td>件号：<s:textfield name="tahkSellSta.hkSellMarkId" size="20"/></td>
    <td>品名：<s:textfield name="tahkSellSta.hkSellGoods" size="20"/></td>
    <td>送货单号：<s:textfield name="tahkSellSta.hkSellSendId" size="20"/></td>
    </tr>
    <tr>
    <td>客户：<s:textfield name="tahkSellSta.hkSellCumpanyName" size="20"/></td>
    <td>开始：<input class="Wdate" type="text" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
    <td>截止：<input class="Wdate" type="text" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
    </tr>
    <tr>
    <td>备注：<s:textfield name="tahkSellSta.hkSellMore" size="20"/></td>
    <td colspan="2"><s:submit value="查找" />&nbsp;<s:reset value="放弃" /></td>    
    </tr>
    </table>
    </form>
  <form action="huikuanAction!selectSta.action?tag=shopp" method="post" target="main" style="margin: 0px">
   <table width="900px" class="table">  
 
   	<tr align="center" bgcolor="#c0dcf2" style="height: 40px; font-weight: bold;">
   	<td>序号</td><td>件号</td><td>品名</td><td>数量</td>
   	<td>送货单号</td><td>客户</td><td>加入任务栏日期</td><td>经办人</td> 
   	<td><input type="checkbox" id="checkAll" onclick="chageAllCheck(this)">
												全选</td>
												<td>操作</td> 
   	</tr>
   	<tr><td colspan="10" align="right">
		<font color="red">共选择 <label id="peopleLabel">
					${count}
				</label> <input type="hidden" id="propleText" name="peopleNum"
					style="width: 20px;" readonly="readonly"> 条送货记录</font>
					<input type="submit" value="开票申请"
				style="width: 60px; height: 40px;" align="top">
			<br>
	</td></tr>
   	 <s:iterator value="listShop" status="se" id="shopp">
    	<s:if test="#se.index%2==1">
		<tr align="center" bgcolor="#e6f3fb"
			onmouseover="chageBgcolor(this)"
			onmouseout="outBgcolor(this,'#e6f3fb')">
	  </s:if>
	  <s:else>
	  <tr align="center" onmouseover="chageBgcolor(this)"
		onmouseout="outBgcolor(this,'')">
      </s:else>
    	<td><s:property value="#se.index+1" /></td> 
    	<td><s:property value="haSellSta.hkSellMarkId" /></td>
    	<td><s:property value="haSellSta.hkShkSellGoods" /></td>
    	<td><s:property value="haSellSta.hkSellCount" /></td>
    	<td><s:property value="haSellSta.hkSellSendId" /></td>
    	<td><s:property value="haSellSta.hkSellCumpanyName" /></td>    	
    	<td><s:property value="shoppingTime" /></td> 
    	<td><s:property value="shoppingUserName" /></td> 	
    	<td>
    	<input type="checkbox" id="sell<s:property value="#se.index"/>"
			name="sellId" value="<s:property value='haSellSta.id'/>" onclick="chageNum(this)">
    	</td>
    	<td><a onClick="return confirm('确定要删除该条记录吗？')" href="huikuanAction!deleteShopping.action?id=<s:property value='id' />">删除</a></td>
    	</tr>
    </s:iterator>
    <tr><td colspan="10" align="right" style="font-weight: bold; padding-right: 40px">
							<input type="checkbox" id="checkAll2"
							onclick="chageAllCheck(this)">
							全选
    	</td></tr>
	<tr><td colspan="10" align="right">
		<font color="red">共选择 <label id="peopleLabel2">
					${count}
				</label> <input type="hidden" id="propleText" name="peopleNum"
					style="width: 20px;" readonly="readonly"> 条送货记录</font>
				<input type="submit" value="开票申请"
				style="width: 60px; height: 40px;" align="top">
			<br>
	</td></tr>
   </table>
  </form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
	</body>
	<script type="text/javascript">
	 var oldObj;
	var oldObj2;
	function chageModule(obj, obj2) {
		if (obj.id != "module1") {
			document.getElementById("module1").className = "tag_1";
			document.getElementById("module1_1").style.display = "none";
		}
		if (oldObj != null) {
			oldObj.className = "tag_1";
			document.getElementById("module1_" + oldObj2).style.display = "none";
		}
	
		obj.className = "tag_2";
		document.getElementById("module1_" + obj2).style.display = "block";
	
		oldObj = obj;
		oldObj2 = obj2;
	}
	function chageBgcolor(obj) {
		obj.style.background = "#c0dcf2";
	}
	function outBgcolor(obj, oldColor) {
		obj.style.background = oldColor;
	}
  
  function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox);
				}
			}
		}
	}
}
	var num = "${count}";
	if (num == "") {
		num = 0;
	}
	function chageNum(obj) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			checkAll2.checked = true;
		}
		num++;
	} else if (num == 0 && check.checked == false) {
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
}
	</script>
</html>
