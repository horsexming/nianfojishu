<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
$(function() {
	//alert("服务器异常!");
	$.ajax( {
		url : "NoticeAction!show.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(useradsfa) {
			$("#show").empty();//清空
		var message = "";
		$(useradsfa).each(function(i, n) {
			message += (i + 1) + "、" + n.content + "&nbsp;&nbsp;&nbsp;&nbsp;";
		});
		$("#show").html(message);
	},
	error : function() {
		alert("服务器异常!");
	}
	});
});
</script>
<div style="width: 100%; border: solid 1px #0170b8; margin: 40 0 10 0;"
	align="left">
	<table width="100%" border="0" bgcolor="#f7f7f7"
		style="margin: 0px; padding: 0px; font-weight: bold; border: 0px;">
		<tr>
			<td align="right" width="5%" style="border: 0px;">
				公告:
			</td>
			<td width="100%" style="border: 0px;">
				<marquee direction="left" scrollamount="5" onmouseout="this.start()"
					onmouseover="this.stop()">
					<font color="red" id="show"></font>
				</marquee>

			</td>
		</tr>
	</table>
</div>
