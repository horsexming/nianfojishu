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

$(function(){
	var status = parent.status;
	if(status == '编码'){
		$("#name_span").html('图号类别:');
		$("#code_span").html('图号编码:');
		$(".gh_tr").hide();
		$("#gh_button").hide();
	}
	$.ajax( {
		type : "POST",
		url : "CategoryAction_islow.action",
		data : {id:${category.id}},
		dataType : "json",
		success : function(data) {
			if(!data){
				$(".gh_tr").hide();
				$("#gh_button").hide();
			}
		}
	})
})

function chageCategory(status, form,tag) {
	if (status == "update") {
		form.action = "CategoryAction_updatecategory.action?status="+tag;
	} else if (status == "add") {
		form.action = "CategoryAction_add.action?tag="+tag;
	} else if (status == "del") {
		if(confirm('确定要删除该类别和所属的所有类别吗?')){
			form.action = "CategoryAction_delcategory.action";
		}
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
	var Id=$("#Id").val();
	if(Id!=null&&Id>0){
		$("#message").show();
	}
	var msg=$("#hid").val();
	if(msg!=""){
		alert(msg)
		window.parent.location.reload();
	}
	
});

</script>
	</head>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" align="left">
		
		<div>
<%--			<div align="left"--%>
<%--				style="margin-left: 10px; float: left; border: solid 0px red; width: 30%">--%>
<%--				<sd:tree rootNode="root" nodeIdProperty="id"--%>
<%--					nodeTitleProperty="name" childCollectionProperty="childrens"--%>
<%--					showRootGrid="false" showGrid="true" id="deptNumber">--%>
<%--				</sd:tree>--%>
<%--					<ul id="treeDemo" class="ztree"></ul>--%>
<%--			</div>--%>
			<div id="message" style="border: solid 0px #000000; display: none;margin: 50 0 0 50px;">
				<span id="deptName"></span>
				<form action="" method="post">
					<input type="hidden" id="Id" name="id" value="${category.id}">
					<input type="hidden" id="Id" name="category.id" value="${category.id}">
					<input type="hidden" id="type" name="category.type" value="物料">
					<table>
						<tr>
							<th align="right">
								<span id="name_span">物料&nbsp;&nbsp;&nbsp;类别:</span>
							</th>
							<td>
								<input id="dept" type="text" name="category.name" value="${category.name}"
									onfocus="javascript:this.select()" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<span id="code_span">物料&nbsp;&nbsp;&nbsp;编码:</span>
							</th>
							<td>
								<input id="dept" type="text" name="category.code" value="${category.code}"
									onfocus="javascript:this.select()"  />
							</td>
						</tr>
						<tr class="gh_tr" >
							<th align="right">
								<span id="code_span">生产&nbsp;&nbsp;&nbsp;节拍:</span>
							</th>
							<td>
								<input id="dept" type="text" name="category.avgProductionTakt" value="${category.avgProductionTakt}"
									onfocus="javascript:this.select()"  />(秒)
							</td>
						</tr>
						<tr class="gh_tr">
							<th align="right">
								<span id="code_span">配送&nbsp;&nbsp;&nbsp;时长:</span>
							</th>
							<td>
								<input id="dept" type="text" name="category.avgDeliveryTime" value="${category.avgDeliveryTime}"
									onfocus="javascript:this.select()"  />(天)
							</td>
						</tr>
						<tr class="gh_tr">
							<th align="right">
								<span id="code_span">损耗值类型:</span>
							</th>
							<td>
								<s:if test="category.sunhaoType == 1">
									<input type="radio" value="0" name="category.sunhaoType" />百分比
									<input type="radio" value="1" name="category.sunhaoType" checked="checked"/>固定值
								</s:if>
								<s:else>
									<input type="radio" value="0" name="category.sunhaoType" checked="checked"/>百分比
									<input type="radio" value="1" name="category.sunhaoType"/>固定值
								</s:else>
							</td>
						</tr>
						<tr class="gh_tr">
							<th align="right">
								<span id="code_span">损&nbsp;&nbsp;&nbsp;耗&nbsp;&nbsp;&nbsp;值:</span>
							</th>
							<td>
								<input type="text" value="${category.sunhao}" name="category.sunhao" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr class="gh_tr">
							<th align="right">
								<span id="code_span">收货浮动范围:</span>
							</th>
							<td>
								±<input type="text" value="${category.rangeOfReceipt}" name="category.rangeOfReceipt" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr class="gh_tr">
							<th align="right">
								<span id="code_span">质检周期:</span>
							</th>
							<td>
								<input type="text" value="${category.round}" name="category.round" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="right">
								<input type="button" value="修改内容"
									onclick="chageCategory('update',this.form)"
									style="width: 80px; height: 50px;" />
								<input type="button" value="同步更新" id="gh_button"
									onclick="chageCategory('update',this.form,'updatycl')"
									style="width: 80px; height: 50px;" />
								<input type="button" value="添加同层"
									onclick="chageCategory('add',this.form,'tongcheng')"
									style="width: 80px; height: 50px;" />
								<input type="button" value="添加下层"
									onclick="chageCategory('add',this.form,'xiacheng')"
									style="width: 80px; height: 50px;" />
								<input type="button" value="删除"
									onclick="chageCategory('del',this.form)"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div style="clear: both;" align="center">
				<input type="hidden" value="${errorMessage}"  id="hid"/>
				<font color="red"> ${errorMessage}</font>
			</div>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	</body>
</html>
