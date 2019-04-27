<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<base href="<%=basePath%>">

		<title>文件阅读器</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/javascript.js">
</script>


		<script src="${pageContext.request.contextPath}/pdf/build/pdf.js">
</script>
		<style type="text/css">
#the-canvas {
	border: 1px solid black;
}

body {
	margin: 0px;
}
</style>
	</head>
	<body>
		<div>
			<%--download filename--%>
			<%--			https://stackoverflow.com/questions/24457064/mozilla-pdf-js-how-to-i-specify-the-filename-for-download--%>
			<iframe id="pdfview"
				style="width: 100%; height: 100%; margin: 0px; padding: 0px;"></iframe>
			<%--			<a--%>
			<%--				href="${pageContext.request.contextPath}/pdf/web/viewer.html?file=/HHTask/pdf/web/1.pdf">pdf/web/viewer.html?file=/HHTask/pdf/web/1.pdf</a>--%>

		</div>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/watermarkjs/dist/watermark.min.js">

</script>
		<script type="text/javascript">
var url = "${FilePath}";
var contextpath='${pageContext.request.contextPath}';
var rename = "";
function loadiframe() {
	String.prototype.compare = function(str) {
		//不区分大小写
		if (this.toLowerCase() == str.toLowerCase()) {
			return true;
		} else {
			return false;
		}
	}

	//get rename
	var url4rename = window.location.search.substring(1);
	var searchs = url4rename.split("&");
	var i = 0;
	if (searchs.length > 1) {
		for (i; i < searchs.length;i++) {
			var vals = searchs[i].split("=");
			if (vals[0] == "Rename") {
				rename = searchs[i].split("=");
				rename = rename[1];
			}
		}

	}
	//get rename end
	
	var element = document.getElementById('pdfview');
	var Filetype = url.substring(url.lastIndexOf(".") + 1, url.length);
	
	var pdfsrc="${pageContext.request.contextPath}/pdf/web/viewer.html?file=${pageContext.request.contextPath}";
	//是否为pdf格式
	if (Filetype.compare("pdf")) {
		pdfsrc+= url ;
	} else {
	//获取转换后pdf
		pdfsrc = pdfsrc+"/upload/pdf_view/"+ "${FilePath}" + ".pdf" 
	}
	//rename下载重命名
	if (rename != "") {
		rename = encodeURI(rename);
		pdfsrc+= "&Rename=" + rename;
	}
	if (contextpath != "") {
		pdfsrc+= "&head=1";
	}
	element.src=pdfsrc;

}

loadiframe();
</script>


		<script
			src="${pageContext.request.contextPath}/pdf/build/jquery-latest.js">
</script>
		<script type="text/javascript">

watermark( {
	watermark_txt : "PEBS--${name}--${code}"+"\n"+"${wmark}"
})

function watermark(settings) {
	//默认设置
	var defaultSettings = {
		watermark_txt : "text",
		watermark_x : 20,//水印起始位置x轴坐标
		watermark_y : 20,//水印起始位置Y轴坐标
		watermark_rows : 20,//水印行数
		watermark_cols : 20,//水印列数
		watermark_x_space : 100,//水印x轴间隔
		watermark_y_space : 50,//水印y轴间隔
		watermark_color : '#aaa',//水印字体颜色
		watermark_alpha : 0.7,//水印透明度
		watermark_fontsize : '15px',//水印字体大小
		watermark_font : '微软雅黑',//水印字体
		watermark_width : 210,//水印宽度
		watermark_height : 80,//水印长度
		watermark_angle : 15
	//水印倾斜度数
	};
	//采用配置项替换默认值，作用类似jquery.extend
	if (arguments.length === 1 && typeof arguments[0] === "object") {
		var src = arguments[0] || {};
		for (key in src) {
			if (src[key] && defaultSettings[key]
					&& src[key] === defaultSettings[key])
				continue;
			else if (src[key])
				defaultSettings[key] = src[key];
		}
	}

	var oTemp = document.createDocumentFragment();

	//获取页面最大宽度
	var page_width = Math.max(document.body.scrollWidth,
			document.body.clientWidth);
	var cutWidth = page_width * 0.0150;
	var page_width = page_width - cutWidth;
	//获取页面最大高度
	var page_height = Math.max(document.body.scrollHeight,
			document.body.clientHeight) + 450;
	// var page_height = document.body.scrollHeight+document.body.scrollTop;
	//如果将水印列数设置为0，或水印列数设置过大，超过页面最大宽度，则重新计算水印列数和水印x轴间隔
	if (defaultSettings.watermark_cols == 0
			|| (parseInt(defaultSettings.watermark_x
					+ defaultSettings.watermark_width
					* defaultSettings.watermark_cols
					+ defaultSettings.watermark_x_space
					* (defaultSettings.watermark_cols - 1)) > page_width)) {
		defaultSettings.watermark_cols = parseInt((page_width
				- defaultSettings.watermark_x + defaultSettings.watermark_x_space)
				/ (defaultSettings.watermark_width + defaultSettings.watermark_x_space));
		defaultSettings.watermark_x_space = parseInt((page_width
				- defaultSettings.watermark_x - defaultSettings.watermark_width
				* defaultSettings.watermark_cols)
				/ (defaultSettings.watermark_cols - 1));
	}
	//如果将水印行数设置为0，或水印行数设置过大，超过页面最大长度，则重新计算水印行数和水印y轴间隔
	if (defaultSettings.watermark_rows == 0
			|| (parseInt(defaultSettings.watermark_y
					+ defaultSettings.watermark_height
					* defaultSettings.watermark_rows
					+ defaultSettings.watermark_y_space
					* (defaultSettings.watermark_rows - 1)) > page_height)) {
		defaultSettings.watermark_rows = parseInt((defaultSettings.watermark_y_space
				+ page_height - defaultSettings.watermark_y)
				/ (defaultSettings.watermark_height + defaultSettings.watermark_y_space));
		defaultSettings.watermark_y_space = parseInt(((page_height - defaultSettings.watermark_y) - defaultSettings.watermark_height
				* defaultSettings.watermark_rows)
				/ (defaultSettings.watermark_rows - 1));
	}
	var x;
	var y;
	for ( var i = 0; i < defaultSettings.watermark_rows; i++) {
		y = defaultSettings.watermark_y
				+ (defaultSettings.watermark_y_space + defaultSettings.watermark_height)
				* i;
		for ( var j = 0; j < defaultSettings.watermark_cols; j++) {
			x = defaultSettings.watermark_x
					+ (defaultSettings.watermark_width + defaultSettings.watermark_x_space)
					* j;

			var mask_div = document.createElement('div');
			mask_div.id = 'mask_div' + i + j;
			mask_div.className = 'mask_div';
			mask_div.appendChild(document
					.createTextNode(defaultSettings.watermark_txt));
			//设置水印div倾斜显示
			mask_div.style.webkitTransform = "rotate(-"
					+ defaultSettings.watermark_angle + "deg)";
			mask_div.style.MozTransform = "rotate(-"
					+ defaultSettings.watermark_angle + "deg)";
			mask_div.style.msTransform = "rotate(-"
					+ defaultSettings.watermark_angle + "deg)";
			mask_div.style.OTransform = "rotate(-"
					+ defaultSettings.watermark_angle + "deg)";
			mask_div.style.transform = "rotate(-"
					+ defaultSettings.watermark_angle + "deg)";
			mask_div.style.visibility = "";
			mask_div.style.position = "absolute";
			mask_div.style.left = x + 'px';
			mask_div.style.top = y + 'px';
			mask_div.style.overflow = "hidden";
			mask_div.style.zIndex = "9999";
			mask_div.style.pointerEvents = 'none';//pointer-events:none  让水印不遮挡页面的点击事件
			//mask_div.style.border="solid #eee 1px";
			mask_div.style.opacity = defaultSettings.watermark_alpha;
			mask_div.style.fontSize = defaultSettings.watermark_fontsize;
			mask_div.style.fontFamily = defaultSettings.watermark_font;
			mask_div.style.color = defaultSettings.watermark_color;
			mask_div.style.textAlign = "center";
			mask_div.style.width = defaultSettings.watermark_width + 'px';
			mask_div.style.height = defaultSettings.watermark_height + 'px';
			mask_div.style.display = "block";
			oTemp.appendChild(mask_div);
		}
		;
	}
	;
	document.body.appendChild(oTemp);
}
</script>
</html>
