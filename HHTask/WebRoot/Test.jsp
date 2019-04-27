<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/javascript/jquery-table2excel-master/dist/jquery.table2excel.js"></script>
		<STYLE type="text/css">
#baifen {
	position: absolute;
	left: 50px;
}

#jindu_div {
	position: relative;
}
.test_name{
	width: 75px;
}
.test_skill{
	width: 120px;
}
<%--.center-vertical {--%>
<%--    position: relative;--%>
<%--    top: 50%;--%>
<%--    transform: translateY(-50%);--%>
<%--    color: transparent;--%>
<%--    text-shadow: #111 0 0 5px;--%>
<%--}--%>
<%--div {--%>
<%--    box-shadow: 0 0 0 6px rgba(0, 0, 0, 0.2), 0 0 0 12px rgba(0, 0, 0, 0.2), 0 0 0 18px rgba(0, 0, 0, 0.2), 0 0 0 24px rgba(0, 0, 0, 0.2);--%>
<%--    height: 200px;--%>
<%--    margin: 50px auto;--%>
<%--    width: 400px--%>
<%--}--%>
</STYLE>

		<title>异常页面</title>
	</head>
	<body style="width: 100%; position: relative; padding-bottom: 20%;" onbeforeunload="body_onUnload()">
		 <style style="display:block" contentEditable>
        	body { color: blue;background: red; }
        </style>
		<CENTER>
			 <canvas id="can" width="400" height="400" style="background: Black"></canvas>
				<%--		<input type=button value="打 印 " onclick="pagePrint('PrintDiv')" class="input" >--%>
				<div >
					<input type="button" value="开始" onclick="aa()"/>
					<input type="button" value="暂停" onclick="test()"/>
				</div>
				<SPAN>
					<fmt:formatNumber value="${2363.4}" pattern="#000.0000"></fmt:formatNumber>
				</SPAN>
<%--				<div id="center-vertical">--%>
<%--					我看一下，文字效果--%>
<%--				</div>--%>
			<div id="test_div" style="width: 200px;">
			
			</div>
			<input type="button" id="btn" value="导出" />
	<table id="example">
        <thead>
            <tr>
                <th>Column 1</th>
                <th>Column 2</th>
                <th>Column 3</th>
                <th>Column 4</th>
                <th>Column 5</th>
            </tr>
        </thead>
        <tbody>
            <tr>
            <td colspan="2">1aaa</td>
            <td>ccc</td>
            <td>ddd</td>
            <td>eee</td>
        </tr>
        <tr style="background-color:#0f0;">
            <td>3aaa</td>
            <td>34bbb</td>
            <td>ccc</td>
            <td>ddd</td>
            <td>修改</td>
        </tr>
        <tr>
            <td>22bbb</td>
            <td>22bbb</td>
            <td>ccc</td>
            <td>ddd</td>
            <td>eee</td>
        </tr>
        <tr>
            <td>5aaa</td>
            <td>55bbb</td>
            <td>ccc</td>
            <td>ddd</td>
            <td>eee</td>
        </tr>
        <tr>
            <td>4aaa</td>
            <td>44bbb</td>
            <td>ccc</td>
            <td>ddd</td>
            <td>eee</td>
        </tr>
        </tbody>
    </table>
		</CENTER>
		</div>
	</BODY>
</HTML>
</div>
<%@include file="/util/foot.jsp"%>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
<%--var _log = console.log;--%>
<%--console.log = function() {--%>
<%--  _log.call(console, '%c' + [].slice.call(arguments).join(' '), 'color:transparent;text-shadow:0 0 2px rgba(0,0,0,.5);');--%>
<%--};--%>
//创建一个类  
    var Person = function () {  
        //属性：姓名，注意要属性名与get和set的名称不能重复否则会报错  
        this._username = 'unknown';  
        this._age = 0;  
    }  
    //在原型中给set和get方法  
    //在原型中get和set方法的名称是一样的，方便调用  
    Person.prototype = {  
        set username(name) {  
            this._username = name;  
        },  
        get username() {  
            return this._username;  
        }  
    }  
  

 var sn = [ 42, 41 ], dz = 43, fx = 1, n, ctx = document.getElementById("can").getContext("2d"),timeout;  
        function draw(t, c) {  
            ctx.fillStyle = c;  
            
            ctx.fillRect(t % 20 * 20 + 1, ~~(t / 20) * 20 + 1, 18, 18);  
        }  
        document.onkeydown = function(e) {  
            fx = sn[1] - sn[0] == (n = [ -1, -20, 1, 20 ][(e || event).keyCode - 37] || fx) ? fx : n
                                                                                            
        };  
        function aa() {  
            sn.unshift(n = sn[0] + fx);//向数组的开头添加一个或更多元素，并返回新的长度。  
            if (sn.indexOf(n, 1) > 0 || n<0||n>399 || fx == 1 && n % 20 == 0 || fx == -1 && n % 20 == 19)  
                return alert("GAME OVER");  
            draw(n, "Lime");  
            if (n == dz) {
                while (sn.indexOf(dz = ~~(Math.random() * 400)) >= 0);  
                draw(dz, "Yellow");  
            } else  
                draw(sn.pop(), "Black");  
              timeout =  setTimeout(arguments.callee, 130);  
        }; 
  function generateRandomAlphaNum(len) {
    var rdmString = "";
    for (; rdmString.length < len; rdmString += Math.random().toString(36).substr(2));
    return rdmString.substr(0, len);
}
  var tems_data = [
			{name:'张三丰',skill:'太极拳',id:1,fatherId:0,rootId:1,belongLayer:1},
			{name:'张翠山',skill:'倚天屠龙功',id:2,fatherId:1,rootId:1,belongLayer:2},
			{name:'莫声谷',skill:'天地同寿',id:3,fatherId:1,rootId:1,belongLayer:2},
			{name:'俞莲舟',skill:'武当梯云纵',id:4,fatherId:1,rootId:1,belongLayer:2},
			{name:'张无忌',skill:'乾坤大挪移',id:5,fatherId:2,rootId:1,belongLayer:3},
			{name:'宋远桥',skill:'武当剑法',id:6,fatherId:1,rootId:1,belongLayer:2},
			{name:'宋青书',skill:'降龙十八掌（缺）',id:7,fatherId:6,rootId:1,belongLayer:3},
		];
var tems=[];
 var data = [
        {
            id:1,
            name :"一级分类：1",
            pid :0,
        },
        {
            id:2,
            name :"二级分类：1",
            pid :1,
        },
        {
            id:3,
            name :"三级分类：3",
            pid :2,
        },
        {
            id:4,
            name :"一级分类：2",
            pid :0,
        },
        {
            id:7,
            name :"f级分类：2",
            pid :4,
        },
        {
            id:10,
            name :"f级分类：2",
            pid :7,
        },
        {
            id:9,
            name :"f级分类：2",
            pid :10,
        },
        {
            id:12,
            name :"f级分类：2",
            pid :9,
        },
        {
            id:15,
            name :"f级分类：2",
            pid :12,
        },
        {
            id:13,
            name :"f级分类：2",
            pid :15,
        },
    ]



    function toTree(data) {
        // 删除 所有 children,以防止多次调用
        data.forEach(function (item) {
            delete item.children;
        });

        // 将数据存储为 以 id 为 KEY 的 map 索引数据列
        var map = {};
        data.forEach(function (item) {
            map[item.id] = item;
        });
//        console.log(map);

        var val = [];
        data.forEach(function (item) {

            // 以当前遍历项，的pid,去map对象中找到索引的id
            var parent = map[item.pid];

            // 好绕啊，如果找到索引，那么说明此项不在顶级当中,那么需要把此项添加到，他对应的父级中
            if (parent) {

                (parent.children || ( parent.children = [] )).push(item);

            } else {
                //如果没有在map中找到对应的索引ID,那么直接把 当前的item添加到 val结果集中，作为顶级
                val.push(item);
            }
        });

        return val;
    }

   var menuArry = [
      { id: 1, name: "办公管理", pid: 0 },
      { id: 2, name: "请假申请", pid: 1 },
      { id: 3, name: "出差申请", pid: 1 },
      { id: 4, name: "请假记录", pid: 2 },
      { id: 5, name: "系统设置", pid: 0 },
      { id: 6, name: "权限管理", pid: 5 },
      { id: 7, name: "用户角色", pid: 6 },
      { id: 8, name: "菜单设置", pid: 6 },
      ];
  
      GetData(0, tems_data)
      $("body").append(menus);
  
  
    //菜单列表html
    var menus = '';
  
    //根据菜单主键id生成菜单列表html
    //id：菜单主键id
    //arry：菜单数组信息
    function GetData(id, arry) {
      var childArry = GetParentArry(id, arry);
      if (childArry.length > 0) {
        menus += '<ul>';
        for (var i in childArry) {
          menus += '<li>' + childArry[i].name;
          GetData(childArry[i].id, arry);
          menus += '</li>';
        }
        menus += '</ul>';
      }
    }
  
    //根据菜单主键id获取下级菜单
    //id：菜单主键id
    //arry：菜单数组信息
    function GetParentArry(id, arry) {
      var newArry = new Array();
      for (var i in arry) {
        if (arry[i].fatherId == id)
          newArry.push(arry[i]);
      }
      return newArry;
    }
$(function(){
	var i =0;
	var str =	daxiezhuanhuan(421373.47,'')
	test()
})
  
$(function(){
        $("#btn").click(function(){
            $("#example").table2excel({
                // 不被导出的表格行的CSS class类
                exclude: ".noExl",
                // 导出的Excel文档的名称，（没看到作用）
                name: "Excel Document Name",
                // Excel文件的名称
                filename: "myExcelTable"
            });
        });
    });
<%-- 		$("#barcode").JsBarcode('Hi!');//or JsBarcode("#barcode", "Hi!");  --%>
<%--  --%>
<%--        $("#canvas").JsBarcode('Hello world!');//or JsBarcode("#barcode", "Hello world!");  --%>
<%--  --%>
<%--        $("#bcode").JsBarcode("I'm bwju!");//or JsBarcode("#barcode", "I'm bwju!");  --%>
</script>



</SCRIPT>
</body>
</html>
