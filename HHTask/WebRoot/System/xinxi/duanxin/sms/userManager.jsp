<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function(){
		$('#sms_userManager_searchBut').linkbutton({
			iconCls : 'icon-search'
		});
		$('#sms_userManager_resetBut').linkbutton({
			iconCls : 'icon-search'
		});
		
		$('#sms_userManager_searchBut').bind('click', searchFun);
		$('#sms_userManager_resetBut').bind('click',clearFun);

		$("#sms_userManager_addDialog").dialog({
			title: '添加',
			width: 260,  
			height: 175,
			closed: false,
			cache: false,
			modal: true,
			buttons : [ {
				text : '确定',
				handler : function() {
					ajaxAddUser();
				}
			}]
		}).dialog('close');
		
		$("#sms_userManager_editDialog").dialog({
			title: '添加',
			width: 260,  
			height: 175,
			closed: false,
			cache: false,
			modal: true,
			buttons : [ {
				text : '确定',
				handler : function() {
					ajaxEditUser();
				}
			}]
		}).dialog('close');
	
		$('#sms_userManager_datarid').datagrid({
			url:'smsUser_datagrid.action',
			fit:true,
			fitColumns : true,
			border : false,
			pagination : true,
			singleSelect : true,
			loadMsg : "努力加载中.....",
			idField : 'id',
			pageSize : 10,
			pageList : [10,20,30,40,50],
			sortName : 'id',
			sortOrder : 'desc',
			columns : [[{
				field : 'id',
				title : 'ID',
				width : 100,
				hidden :true
			},{
				field : 'name',
				title : '名称',
				width : 100
			},{
				field : 'phone',
				title : '手机号',
				width : 100
			},{
				field : 'group',
				title : '群组',
				width : 100,
				sortable : true
			},
			]],
			toolbar: [{
				iconCls: 'icon-add',
				text : '添加',
				handler: function(){
					ajaxGetGroups();
				}
			},'-',{
				iconCls: 'icon-edit',
				text : '修改',
				handler: function(){
					edit();
				}
			},'-',{
				iconCls: 'icon-remove',
				text : '删除',
				handler: function(){
					var node = $("#sms_userManager_datarid").datagrid('getSelected');
					if(node == null){
						$.messager.alert("提示", "请选择要编辑的记录", "error");
						return ;
					}
		            $.messager.confirm('提示', '你确定要删除此人吗?', function(r){  
		                if (r){
		                    remove(node.id);  
		                }  
		            });
				}
			}]
		});
		
	});
	
	function remove(userId){
		$.ajax({
			type: "POST",
			url : 'smsUser_delete.action',
			data : 'id=' + userId,
			dataType : 'json',
		    success: function(data){
				if(data.success){
					$.messager.show({
						title : '提示',
						msg : data.message
					});
					$("#layout_east_groupTree").tree('reload');
					$('#sms_userManager_datarid').datagrid('load',{});
				}else {
					$.messager.show({
						title : '提示',
						msg : data.message
					});
				}
		    } 
		});
	}
	
	function edit(){
		var node = $("#sms_userManager_datarid").datagrid('getSelected');
		if(node == null){
			$.messager.alert("提示", "请选择要编辑的记录", "error");
			return ;
		}
		$("#sms_userManager_editForm input[name='id']").val(node.id);
		$("#sms_userManager_editForm input[name='name']").val(node.name);
		$("#sms_userManager_editForm input[name='phone']").val(node.phone);
		$("#sms_userManager_editForm_groupSel").empty();
		$.ajax({
			type: "POST",
			url : 'smsGroup_getAll.action',
			dataType : 'json',
		    success: function(data){
				if(data.success){
					for(var i=0; i<data.data.length; i++){
						if(node.groupId == data.data[i].id){
							$("#sms_userManager_editForm_groupSel").append("<option selected='selected' value=" + data.data[i].id +">" + data.data[i].name + "</option>");
						} else {
							$("#sms_userManager_editForm_groupSel").append("<option value=" + data.data[i].id +">" + data.data[i].name + "</option>");
						}
					}
					$('#sms_userManager_editDialog').dialog('open');
				}else {
					$.messager.show({
						title : '提示',
						msg : data.message
					});
				}
		    } 
		});
	}
	
	function ajaxGetGroups(){
		$("#sms_userManager_addForm_groupSel").empty();
		$("#sms_userManager_addForm input").val('');
		$.ajax({
			type: "POST",
			url : 'smsGroup_getAll.action',
			dataType : 'json',
		    success: function(data){
				if(data.success){
					for(var i=0; i<data.data.length; i++){
						$("#sms_userManager_addForm_groupSel").append("<option value=" + data.data[i].id +">" + data.data[i].name + "</option>");
					}
					$('#sms_userManager_addDialog').dialog('open');
					
				}else {
					$.messager.show({
						title : '提示',
						msg : data.message
					});
				}
		    } 
		});
	}
	
	function ajaxAddUser(){
		var phone = $("#sms_userManager_addForm [name='phone']").val();
		var name = $("#sms_userManager_addForm [name='name']").val();
		
		if(!isTelephone(phone)){
			$.messager.show({
				title : '错误:',
				msg : '手机号格式不对.'
			});
			return ;
		}
		if(!isRealName(name)){
			$.messager.show({
				title : '错误:',
				msg : '姓名格式不对.'
			});
			return ;
		}

		$.ajax({
			type: "POST",
			url : 'smsUser_add.action',
			dataType : 'json',
			data: $("#sms_userManager_addForm").serialize(),
		    success: function(data){
				$.messager.show({
					title : '提示',
					msg : data.message
				});
				if(data.success){
					$('#sms_userManager_addDialog').dialog('close');
					$("#layout_east_groupTree").tree('reload');
				}
		    } 
		});
		
	}
	
	function ajaxEditUser(){
		var phone = $("#sms_userManager_editForm [name='phone']").val();
		var name = $("#sms_userManager_editForm [name='name']").val();
		
		if(!isTelephone(phone)){
			$.messager.show({
				title : '错误:',
				msg : '手机号格式不对.'
			});
			return ;
		}
		if(!isRealName(name)){
			$.messager.show({
				title : '错误:',
				msg : '姓名格式不对.'
			});
			return ;
		}
		$.ajax({
			type: "POST",
			url : 'smsUser_edit.action',
			dataType : 'json',
			data: $("#sms_userManager_editForm").serialize(),
		    success: function(data){
				$.messager.show({
					title : '提示',
					msg : data.message
				});
				if(data.success){
					$('#sms_userManager_editDialog').dialog('close');
					$("#layout_east_groupTree").tree('reload');
				}
		    } 
		})
		
	}
	
	function searchFun(){
		$('#sms_userManager_datarid').datagrid('load', serializeObject($("#sms_userManager_form")));
	}
	function clearFun(){
		name : $('#sms_userManager_layout input[name=name]').val('');
		$('#sms_userManager_datarid').datagrid('load',{});
	}
</script>


<div id="sms_userManager_layout" class="easyui-layout"  data-options="fit:true" >
	<div data-options="region:'north',title:'查询条件'" style="height: 130">
		<form id="sms_userManager_form" method="post">
			<table>
				<tr>
					<th align="right" >
						<font style="font-size: 12">用户名：</font>
					</th>
					<td><input name="name"> </td>
				</tr>
				<tr>
					<th align="right" >
						<font style="font-size: 12">手机号：</font>
					</th>
					<td><input name="phone"> </td>
					
				</tr>
				<tr>
					<th align="right" >
						<font style="font-size: 12">所在组：</font>
					</th>
					<td><input name="group"> </td>
					<td rowspan="3">
						<a id="sms_userManager_searchBut">搜索</a>
						<a id="sms_userManager_resetBut" >清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',title:'用户列表'">
		<table id="sms_userManager_datarid"></table>
	</div>
</div>

<div id="sms_userManager_addDialog" >
	<form id="sms_userManager_addForm" method="post">
		<table>
			<tr>
				<th><font style="font-size: 11px;">用户名:</font></th>
				<td><input id="a11" name="name"/></td>
			</tr>
			<tr>
				<th><font style="font-size: 11px;">手机号:</font></th>
				<td><input name="phone"/></td>
			</tr>
			<tr>
				<th><font style="font-size: 11px;">用户组:</font></th>
				<td>
					<select id="sms_userManager_addForm_groupSel" name="groupId" style="width: 153 px">
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="sms_userManager_editDialog" >
	<form id="sms_userManager_editForm" method="post">
		<input type="hidden" name="id">
		<table>
			<tr>
				<th><font style="font-size: 11px;">用户名:</font></th>
				<td><input id="a11" name="name"/></td>
			</tr>
			<tr>
				<th><font style="font-size: 11px;">手机号:</font></th>
				<td><input name="phone"/></td>
			</tr>
			<tr>
				<th><font style="font-size: 11px;">用户组:</font></th>
				<td>
					<select id="sms_userManager_editForm_groupSel" name="groupId" style="width: 153 px">
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="sms_userManager_message"></div>
