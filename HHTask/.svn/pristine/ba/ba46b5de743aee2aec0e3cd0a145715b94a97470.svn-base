<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.noStyle{
	 background: none;
	 color: gray;
}
</style>
<div align="center">
<!-- 	<h4> -->
<!-- 		项目成员 -->
<!-- 	</h4> -->
	<div>
		<input type="checkbox" value="全选" id="unerAll" onchange="changeAll(this)">全选
		<ul class="list-group">
			<s:iterator value="erList" id="data" >
				<li class="list-group-item">
					<input type='checkbox' value="${data.addUserId}" name="uner"
						 onchange="changePlayer()"> ${data.addUserName }
					<input type='text' size="5"/>%
				</li>
				
			</s:iterator>
		</ul>
	</div>
</div>
<script type="text/javascript">
function changeAll(obj){
	var canyuren = $("input[name='uner']");
	if(obj.checked){
		for(var i=0;i<canyuren.length;i++){
			canyuren[i].checked=true;
		}
	}else{
		for(var i=0;i<canyuren.length;i++){
			canyuren[i].checked=false;
		}
	}
}

function changePlayer(){
// 	debugger;
	var checkAll=document.getElementById("unerAll");
	var checkboxs=document.getElementsByName("uner");
	var count=0;
	for(var i=0;i<checkboxs.length;i++){
		if(checkboxs[i].checked==false){
			checkAll.checked=false;
			return;
		}else{
			count++;
		}
	}
	if(count==checkboxs.length){
		checkAll.checked=true;
	}
}
</script>
