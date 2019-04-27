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
		<input type="checkbox" value="全选" id="playerAll" onchange="changeAll(this)">全选
		<ul class="list-group">
			<s:iterator value="yfUserList" id="data" >
				<s:if test="'项目主管'==#data.usertype">
					<li class="list-group-item">
						<input type='checkbox' disabled='disabled'
							 onchange="changePlayer()"> ${data.userName }
					</li>
				</s:if>
				<s:elseif test="null==data.weight">
					<li class="list-group-item">
						<input type='checkbox' name='canyuren' value="${data.id }"
							onchange="changePlayer()"> ${data.userName }
					</li>
				</s:elseif>
				<s:else>
					<li class="list-group-item">
						<input type='checkbox' name='canyuren' value="${data.id }"
							onchange="changePlayer()"> ${data.userName }
						<input type='text' disabled='disabled' value="${data.weight }" size="5"/>%
					</li>
				</s:else>
			</s:iterator>
		</ul>
	</div>
</div>
<script type="text/javascript">
function changeAll(obj){
	var canyuren = $("input[name='canyuren']");
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
	var checkAll=document.getElementById("playerAll");
	var checkboxs=document.getElementsByName("canyuren");
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
