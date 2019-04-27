<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sd" uri="/struts-dojo-tags"%>
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
<%--	<s:head theme="xhtml" />--%>
<%--	<sd:head parseContent="true" />--%>
	<%@include file="/util/sonHead.jsp"%>
	<script type="text/javascript">

//tree加载操作
<%--dojo.addOnLoad(function() {--%>
<%--	var t = dojo.widget.byId('deptNumber');--%>
<%--	var s = t.selector;--%>
<%--	dojo.event.connect(s, 'select', 'treeNodeSelected');--%>
<%--	expandObj(t);--%>
<%--});--%>

//展开全部
<%--function expandObj(obj) {--%>
<%--	if (obj) {--%>
<%--		if (obj.children) {--%>
<%--			for ( var i = 0; i < obj.children.length; i++) {--%>
<%--				var childObj = obj.children[i];--%>
<%--				if (childObj) {--%>
<%--					try {--%>
<%--						childObj.expand();--%>
<%--					} catch (e) {--%>
<%--					}--%>
<%--					if (childObj.children) {--%>
<%--						expandObj(childObj);--%>
<%--					}--%>
<%--				}--%>
<%--			}--%>
<%--		}--%>
<%--	}--%>
<%--}--%>



function chageDeptNumber(status, form) {
	if (status == "update") {
		form.action = "DeptNumberAction!updateDeptNumber.action";
	} else if (status == "add") {
		form.action = "DeptNumberAction!addDeptNumber.action";
	} else if (status == "del") {
		form.action = "DeptNumberAction!delDeptNumber.action";
	}
	form.submit();
}

<%--$(function(){--%>
<%--	var deptId=$("#deptId").val();--%>
<%--	if(deptId!=null&&deptId>0){--%>
<%--		$("#message").show();--%>
<%--		--%>
<%--	}--%>
<%--	var msg=$("#hid").val();--%>
<%--	if(msg!=null&&msg!=""){--%>
<%--		alert(msg);--%>
<%--		parent.window.location.reload();--%>
<%--		--%>
<%--	}--%>
<%--});--%>

$(function(){
	var deptId=$("#deptId").val();
	if(deptId!=null&&deptId>0){
		$("#message").show();
	}
	var msg=$("#hid").val();
	if(msg!=""){
		alert(msg)
		window.parent.location.reload();
	}
	
});

function changeIsVisitor(){
	var toggle = $("#toggle").val();
	if(toggle==null || toggle=="" || toggle=="否"){
		$("#toggle").val("是");
	}else{
		$("#toggle").val("否");
	}
}

</script>
<style type="text/css">
	.switch {
	  position: relative;
	  display: inline-block;
	  width: 40px;
	  height: 20px;
	  background-color: rgba(0, 0, 0, 0.25);
	  border-radius: 20px;
	  transition: all 0.3s;
	}
	.switch::after {
	  content: '';
	  position: absolute;
	  width: 18px;
	  height: 18px;
	  border-radius: 18px;
	  background-color: white;
	  top: 1px;
	  left: 1px;
	  transition: all 0.3s;
	}
	input[type='checkbox']:checked + .switch::after {
	  transform: translateX(20px);
	}
	input[type='checkbox']:checked + .switch {
	  background-color: #7983ff;
	}
	.offscreen {
	  position: absolute;
	  left: -9999px;
	}
</style>
	</head>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng"
		style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong"
			style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
			align="left">
			<div style="float: left; width: 50%" align="left">
				
			</div>
			<div style="float: left; width: 48%" align="right">
				<a
					href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
					style="color: #ffffff">查询所有</a>
			</div>
		</div>
		
		<div>
<%--			<div align="left"--%>
<%--				style="margin-left: 10px; float: left; border: solid 0px red; width: 30%">--%>
<%--				<sd:tree rootNode="root" nodeIdProperty="id"--%>
<%--					nodeTitleProperty="name" childCollectionProperty="childrens"--%>
<%--					showRootGrid="false" showGrid="true" id="deptNumber">--%>
<%--				</sd:tree>--%>
<%--					<ul id="treeDemo" class="ztree"></ul>--%>
<%--			</div>--%>
			<div id="message" style="border: solid 0px #000000; display: none;">
				<span id="deptName"></span>
				<form action="" method="post">
					<input type="hidden" id="deptId" name="id" value="${deptNumber.id}">
					<table>
						<tr>
							<th align="right">
								名 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:
							</th>
							<td>
								<input id="dept" type="text" name="deptNumber.dept" value="${deptNumber.dept}"
									onfocus="javascript:this.select()" class="search_shuru1" />
							</td>
						</tr>
						<tr>
							<th align="right">
								部门编号:
							</th>
							<td>
								<input id="deptNumber" type="text" name="deptNumber.deptNumber" value="${deptNumber.deptNumber}"
									class="search_shuru1" title="小部门时填写部门编号!">
							</td>
						</tr>
						<tr>
							<th align="right">
								允许访客:
							</th>
							<td>
								<s:if test="deptNumber.isVisitor==null || deptNumber.isVisitor=='否'">
									<input type="checkbox" id="toggle" class="offscreen" value="否" name="deptNumber.isVisitor"/>
								</s:if>
								<s:else>
									<input type="checkbox" id="toggle" checked="checked" id="visitor"
										class="offscreen"  value="是" name="deptNumber.isVisitor"/>
								</s:else>
								<label for="toggle" class="switch" onclick="changeIsVisitor()"></label>
								<br>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="right">
								<input type="button" value="修改内容"
									onclick="chageDeptNumber('update',this.form)"
									style="width: 80px; height: 50px;" />
								<input type="button" value="添加下层"
									onclick="chageDeptNumber('add',this.form)"
									style="width: 80px; height: 50px;" />
								<input type="button" value="删除"
									onclick="chageDeptNumber('del',this.form)"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div style="clear: both;" align="center">
				<input type="hidden" value="${successMessage}"  id="hid"/>
				<font color="red"> ${successMessage}</font>
			</div>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	</body>
</html>
