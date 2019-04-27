<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
 <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js" ></script>
 <title>使用 Jquery 来实现可以输入值的下拉选单</title>


 <script type="text/javascript">
 $(document).ready(function () {
 //动画速度
 var speed = 500;

 //选单的相关处理事件
 $("#divPop div").live("mouseover mouseout click", function (event) {
 if (event.type == "mouseover") {
 //$(this).addClass(‘highlight’);
 $(this)[0].style.backgroundColor = '#E6F5FA';
 }
 if (event.type == "mouseout") {
 //$(this).removeClass("highlight");
 $(this)[0].style.backgroundColor = '#DDFFDD';
 }
 if (event.type == "click") {
 var inID = $("#btnDDL").get(0).getAttribute("inputid");
 //alert($(this).html());
 $("#" + inID).val($(this).html());
 }
 });
 //动态产生下拉选单的选项，後面 要从 array 中读取产生选单
 $("#divPop").append("<div>test1</div>");
 $("#divPop").append("<div>test2</div>");
 $("#divPop").append("<div>test3</div>");
 $("#divPop").append("<div>test4</div>");
 $("#divPop").append("<div>test5</div>");
 $("#divPop").append("<div>test6</div>");
 $("#divPop").append("<div>test7</div>");
 $("#divPop").append("<div>test8</div>");
 $("#divPop").append("<div>test9</div>");

 //绑定事件处理
 $("#btnDDL").click(function (event) {
 //取消事件冒泡
 event.stopPropagation();
 //设置弹出层位置
 var offset = $(event.target).offset();
 //alert($(event.target).width());
 var inID = $(this).get(0).getAttribute("inputid");
 //依據 input 跟 button 寬度來設定 下拉選單的寬度
 $("#divPop")[0].style.width = ($("#" + inID).width() + $(this).width() + 10) + "px";
 //单击空白区域隐藏弹出层
 $(document).click(function (event) { $("#divPop").hide(speed) });
 //设定下拉选单显示的位置
 $("#divPop").css({ top: offset.top + $(event.target).height() + 10 + "px", left: offset.right });
 //切换弹出层的显示状态
 $("#divPop").toggle(speed);
 });
 });

 </script>

 </head>
 <body>
 <div>
 <br /><br /><br />
 <input name="txtKey" type="text" maxlength="30" size="30" id="txtKey" style="Padding:2px;" /><button id="btnDDL" inputid="txtKey" >选择默认提供项目</button>
 </div>

 <!-- 弹出层 -->
 <div id="divPop" style="background-color: #DDFFDD; border: solid 1px #000000; position: absolute; display:none;
 width: 300px; height: auto;">

 </div>
 </body>
 </html>

