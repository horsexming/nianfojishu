<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	
	$(function(){
		$('#sms_sendInput_sendBtn').linkbutton({
			iconCls : 'icon-emailGo'
		});
		$('#sms_sendInput_clearBtn').linkbutton({
			iconCls : 'icon-emailDelete'
		});
		
		$('#sms_sendInput_sendBtn').bind('click', function(){
			var idParam = $("#sms_sendInput_from [name='id']").val().substring(0, $("#sms_sendInput_from [name='id']").val().length-1);
			var msgParam = $("#sms_sendInput_from [name='msg']").val();
			if(idParam && idParam.length > 0){
				sendMsg(idParam, msgParam);
			}
		});
		$('#sms_sendInput_clearBtn').bind('click',formReset);
	})
	
	function sendMsg(idParam,msg){
		$.ajax({
			type: "POST",
			url : 'sms_send.action',
			dataType : 'json',
			data: 'id=' + idParam + '&' + 'msg=' + msg,
		    success: function(data){
				$.messager.show({
					title : '提示',
					msg : data.message
				});
				if(data.success){
					$("#sms_sendInput_from")[0].reset();
					$("#sms_sendInput_from [name='id']").val('');
				}
		    } 
		});
	}
	
	function formReset(){
		$("#sms_sendInput_from")[0].reset();
		$("#sms_sendInput_from [name='id']").val('');
	}
</script>
<div>
	<p style="margin-left:20px" align="left">
		<a id="sms_sendInput_sendBtn" >发送</a>
		<a id="sms_sendInput_clearBtn" >重输</a>
	</p>
	<form id="sms_sendInput_from" action="">
		<input type="hidden" name="id" />
		<table>
			<tr>
				<th align="right">
					收信人:
				</th>
				<td>
					<input name="name" readonly="readonly" style="width: 300px; background-color:#EFEFEF" title="请在右边通信录选择">
				</td>
			</tr>
			<tr>
				<th align="right">
					内容:
				</th>
				<th>
					<textarea name="msg" rows="10" cols="40"></textarea>
				</th>
			</tr>
		</table>
	</form>
</div>
