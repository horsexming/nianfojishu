<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
	</head>
	<body>
		<form method="POST" name="uploadfile" id="uploadfileId"
			action="uploadok.jsp" onsubmit="return checkfile();">
			<table id="uploadtable" border="1">
				<tbody>
					<tr>
						<td>
							文件上传：
							<INPUT TYPE="FILE" NAME="FILE0" SIZE="30" id="FILE0">
						</td>
						<td>
							<input type="button" value="继续" onclick="iT();">
						</td>
						<td colspan="5">
							<input type="radio" name="brand_solve_type0" value="0"
								onclick="javascript:document.getElementById('select_brand').disabled='true';"
								checked>
							按号段自动拆分品牌
							<input id="brand_solve_type_id" type="radio"
								name="brand_solve_type0" value="1">
							手选品牌 品牌名称
							<select id="select_brand" name="selected_brand_id0"
								onchange="choose_brand()" disabled=true>
								<option value="">
									-请选择-
								</option>
								<option value="1">
									1
								</option>
								<option value="2">
									2
								</option>
								<option value="3">
									3
								</option>
							</select>
						</td>
					</tr>
					<tr id="uploadtr">
						<td colspan="5">
							是否测试号码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="is_test" value="1">
							测试号码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="is_test" value="0" checked>
							客户号码
					</tr>
					<tr>
						<td>
							<input type="submit" value="上传">

							<input type="button" onclick="checkfile()" value="点击">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<SCRIPT LANGUAGE="JavaScript">
<!--
	var i=0;
   function iT()
	{
		
	   var tb = document.getElementById("uploadtable").tBodies[0];//table里的首个tbody对象，用tbody操作表格
	   var uploadtr = document.getElementById("uploadtr");//新增的tr新增到该行之前
		var len = tb.rows.length;//tb.rows获得所有行，tb.rows.length得到行数。
		i++; //i用于命名新增的行，提交的时候通过getparmete得到value.
		var _tr = document.createElement("tr");//创建新增的节点元素
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		td2.colSpan=4;//第2列列占4格位置
		var file1 = document.createTextNode("文件上传: ");//创造新增的文本
		var file2 = document.createTextNode("按号段自动拆分品牌");
		var file3 = document.createTextNode("手选品牌");
		var file4 = document.createTextNode(" 品牌名称");
		var   bTxt   =   document.createElement("input");
		bTxt.type    =   "file";//设置类型，name，id,size属性
		bTxt.name    =   "FILE"+i;
		bTxt.id    =   "FILE"+i;
		bTxt.size   =   "30";

		var   bTon   =   document.createElement("input");
		bTon.type    =   "button";
		bTon.value    =   "继续";
		bTon.onclick = function (){
			iT();
		}
		var   bTondel   =   document.createElement("input");
		bTondel.type    =   "button";
		bTondel.value    =   "删除";
		bTondel.name    =    i;
		bTondel.onclick = function (){
			var s = event.srcElement;//触发事件的源节点input
			var a_tr = s.parentNode.parentNode;//tr
			var rowindex = _tr.rowIndex;
			var table =  _tr.parentElement;//table
			var tablerows = table.rows;//行数
			for(var k = 0;k < table.rows.length-2;k++){
				if (k > rowindex){
					var kname = k - 1;//删除该行，则下面所有行的id,与name都相应减1
					var brand_solve_type0 = document.getElementById("0brand_solve_type_id"+k) ;
					var brand_solve_type1 = document.getElementById("1brand_solve_type_id"+k) ;
					var selected_brand = document.getElementById("selected_brand_id"+k) ;
					
					brand_solve_type0.name = "brand_solve_type" + kname;
					brand_solve_type1.name = "brand_solve_type" + kname;
					selected_brand.name = "selected_brand_id" + kname;
					bTxt.name    =   "FILE"+kname;
					
					brand_solve_type0.id = "0brand_solve_type_id" + kname;
					brand_solve_type1.id = "1brand_solve_type_id" + kname;
					selected_brand.id = "selected_brand_id" + kname;
					bTxt.id    =   "FILE"+kname;
				}
			}
			i--;
			tb.deleteRow(_tr.rowIndex);
		}
		
		listLength = uploadfile.selected_brand_id0.options.length;
		var   bSelect   =   document.createElement("select");//创建select对象
		bSelect.name    = "selected_brand_id"+i;
		bSelect.id      = "selected_brand_id"+i;
		bSelect.options.add(new Option("-请选择-", ""));
		for(var k=1;k<listLength;k++){//创建的select对象options选项复制第一行的内容
			bSelect.options.add(new Option(uploadfile.selected_brand_id0.options[k].text),uploadfile.selected_brand_id0.options[k].value );
		}
		bSelect.disabled='true';
		
		var   bRadio0   =   document.createElement("input");//创建radio对象
		bRadio0.type    =   "radio";
		bRadio0.name    =    "brand_solve_type"+i;
		bRadio0.id    =    "0brand_solve_type_id"+i;
		bRadio0.value="0";
		//创建radio对象或者check对象时需要将该对象append到form下，否则在ie下checked属性无效.
		document.getElementById('uploadfileId').appendChild(bRadio0); 
		bRadio0.checked=true;
		
		bRadio0.onclick = function (){
			bSelect.disabled='true';
		}
		var   bRadio1   =   document.createElement("input");
		bRadio1.type    =   "radio";
		bRadio1.name    =    "brand_solve_type"+i;
		bRadio1.id    =    "1brand_solve_type_id"+i;
		bRadio1.value="1";
		bRadio1.checked=true;
		bRadio1.onclick = function (){
			bSelect.disabled=false;
		}
					
		tb.insertBefore(_tr,uploadtr);//将新增的行insert到uploadtr(倒数第二行)之前
		_tr.insertBefore(td1,null);
		td1.insertBefore(file1,null);
		td1.insertBefore(bTxt,null);
		_tr.insertBefore(td2,null);
		td2.insertBefore(bTon,null);
		_tr.insertBefore(td3,null);
		td3.insertBefore(bRadio0,null);
		td3.insertBefore(file2,null);
		td3.insertBefore(bRadio1,null);
		td3.insertBefore(file3,null);
		td3.insertBefore(file4,null);
		td3.insertBefore(bSelect,null);
		
		td2.insertBefore(bTondel,null);
	}
//-->
</SCRIPT>
	</body>
</html>