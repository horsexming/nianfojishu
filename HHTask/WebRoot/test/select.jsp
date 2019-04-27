<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>可输入的select下拉框</title>
	</head>
	<script>

</script>
	<body>
		<select style='width: 150px; z-index: -1' id="aa"
			name="selectHelpCode"
			onkeydown="if(event.keyCode == 8){this.options[0].text = '';}"
			onkeypress="writeSelect(this)">
			<option value=""></option>
			<option value="11">
				11
			</option>
			<option value="22">
				22
			</option>
			<option value="33">
				33
			</option>
		</select>

		<input type="button" value="点我" onclick="ttt();" />

		<script>

function writeSelect(obj) {
	obj.options[0].selected = "select";
	obj.options[0].text = obj.options[0].text
			+ String.fromCharCode(event.keyCode);
	event.returnValue = false;
	return obj.options[0].text;
}

function ttt() {
	var ss = document.getElementById("aa").value;
	var jg = "";

	if (ss == "") {
		var aas = writeSelect(document.getElementById("aa"));
		jg = aas;
	} else {
		jg = document.getElementById("aa").value;
	}

	alert(jg);
}
</script>

	</body>
</html>