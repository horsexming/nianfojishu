<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function(){
		$("#layout_west_tree").tree({
			url: 'System/xinxi/duanxin/layout/tree.json',
			onClick : function(node) {
				if (node.attributes.url) {
					var url = node.attributes.url;
					addTab({
						title : node.text,
						closable : true,
						href : url
					});
				}
			}
		});
	});
</script>
<ul id="layout_west_tree" class="easyui-tree" >
</ul>