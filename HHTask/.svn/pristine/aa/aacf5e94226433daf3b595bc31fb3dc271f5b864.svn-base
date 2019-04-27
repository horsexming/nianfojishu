<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
var layout_east_groupTree;
$(function() {
	layout_east_groupTree = $('#layout_east_groupTree').tree( {
		url : 'smsGroup_list.action',
		lines : true,
		onClick : function(node) {
			var t = $('#layout_center_tabs');
			if (t.tabs('exists', '发送短信')) {
				t.tabs('select', '发送短信');
				var nameInput = $("#sms_sendInput_from input[name='name']");
				var idInput = $("#sms_sendInput_from input[name='id']");
				if(node.attributes){
					nameInput.val(nameInput.val() + node.text + "<" + node.attributes.phone + ">;");
					idInput.val(idInput.val() + node.id + ",");
				} else {
					var users = $('#layout_east_groupTree').tree('getChildren',node.target);
					for(var i=0;i<users.length;i++){
						nameInput.val(nameInput.val() + users[i].text + "<" + users[i].attributes.phone + ">;");
						idInput.val(idInput.val() + users[i].id + ",");
					}
				}
			}
		}
	});

});
</script>
<div class="easyui-panel" title="通讯录"  data-options="fit:true,border:false">
	<ul id="layout_east_groupTree" style="margin-top: 5px;"></ul>
</div>
