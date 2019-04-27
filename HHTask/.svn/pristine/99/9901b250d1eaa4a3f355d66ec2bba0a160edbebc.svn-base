<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<title>系统语法说明</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<script type="text/javascript" src="javascript/javascript.js">
</script>
		<script language="javascript" type="text/javascript"
			src="javascript/DatePicker/WdatePicker.js">
</script>
		<script type="text/javascript">
document.onkeydown = banBackSpace;
</script>
	</head>

	<body bgcolor="#ffffff" >
		<div style="width: 20000px;">
		<a href="ModuleAction!findModuleByUser.action">${companyInfo.shortName}作业网</a>
		<br />
		<br />
		<b>一、使用下拉框可输入:</b>
		<br />
		1、引用javascript/javascript.js脚本
		<br />
		2、使用方法
		<BR />
		<textarea rows="4" cols="80">
			<select style="width: 275px" onkeypress="writeSelect(this)"
				onkeydown="window.history.forward(1);if(event.keyCode == 8){this.options[0].text ='';}">
		<option value=""></option>
		</select>
			</textarea>
		<br />
		3、在脚本里加上:document.onkeydown = banBackSpace; 可以删除\已输入内容
		<br />
		4、示例:
		<select style="width: 275px" onkeypress="writeSelect(this)"
			onkeydown="window.history.forward(1);if(event.keyCode == 8){this.options[0].text ='';}">
			<option value=""></option>
		</select>
		<hr />
		<b>二、struts2 自带日期控件</b>
		<br />
		1、在网页的head部分加上:
		<br />
		&nbsp;&nbsp;&nbsp;&nbsp;&lt;s:head theme="ajax"/&gt;
		<br />
		&nbsp;&nbsp;&nbsp;&nbsp;&lt;%@taglib prefix="s"
		uri="/struts-tags"%&gt;
		<br />
		注:使用该控件需要删除这句: &lt;base href="&lt;%=basePath%&gt;"&gt;
		<br />
		当不指定value值的时候不要加value="",不然会报java.text.ParseException错误
		<br />
		2、使用方法:
		<br />
		<font color="red"> &lt;s:datetimepicker
			name="template.asstMouth" toggleType="explode" value="today"
			tooltipConfig="10000" displayFormat="yyyy-MM-dd" language="zh" /&gt;</font>
		<br />
		3、示例:
		<hr />
		<b>三、javascript日期控件</b>
		<br />
		1、引用javascript/DatePicker/WdatePicker.js脚本
		<br />
		注:使用该控件需要删除这句: &lt;base href="&lt;%=basePath%&gt;"&gt;
		<br />
		2、使用方法:
		<br />
		<font color="red"> &lt;input class="Wdate" type="text"
			name="wage.mouth"
			onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" /&gt;</font><br/>
		2.1、当天日期后不能选中：WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})<br/>
		2.2、当天日期前不能选中：WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d'})
		
		<br />
		3、示例:
		<input class="Wdate" type="text" name="test"
			onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />

		<hr />
		<b>三、生成下拉列表(默认是查询部门)(Ajax)</b>
		<br />
		1、引用javascript/javascript.js脚本
		<br />
		2、使用方法: 调用
		createDept('selectId','actionUrl');(selectId为下拉框的id,actionUrl为要访问的action地址,actionUrl可加可不加)
		<br />
		&nbsp;&nbsp;&nbsp;&nbsp;不加ActionUrl时默认查询所有部门 如:
		<font color="red">onclick="createDept('dept')"</font>
		<br />
		&nbsp;&nbsp;&nbsp;&nbsp;加ActionUrl时该action的返回值必须是包含"|"的字符串 如:
		<font color="red">onclick="createDept('workLogClass','WorkLogClassAction!findPersonWorkLogClass.action')"</font>
		<br />
		在页面新建一个下拉框 &lt;select id='dept' &gt; &lt;/select&gt;
		<br />
		3、示例:
		<select id="dept" onclick="createDept('dept')">
			<option>
				请选择部门
			</option>
		</select>
		<select id="workLogClass"
			onclick="createDept('workLogClass','WorkLogClassAction!findPersonWorkLogClass.action')">
			<option>
				请选择日志类别
			</option>
		</select>
		<hr />
		<b>四、条件查询 (分页) 返回数组</b>
		<br />
		1、使用方法:
		<br />
		Server层：
		<br />
		<textarea rows="13" cols="80">
	public Object[] findWorkLogByCondition(WorkLog workLog, int pageNo,
			int pageSize) {
		if (workLog == null) {
			workLog = new WorkLog();
		}
		String hql = totalDao.criteriaQueries(workLog, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}</textarea>
		<br />
		Action层：
		<br />
		<textarea rows="13" cols="100">
	public String findWorkLogByCondition() {
		if (workLog != null) {
			ActionContext.getContext().getSession().put("workLog", workLog);
		} else {
			workLog = (WorkLog) ActionContext.getContext().getSession().get(
					"workLog");
		}
		Object[] object = workLogServer.findWorkLogByCondition(workLog, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			workLogList = (List<WorkLog>) object[0];
			if (workLogList != null && workLogList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("WorkLogAction!findWorkLogByCondition");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "addWorkLogSuccess";
	}</textarea>
		<hr />
		<b>五、struts2 iterator标签页面集合显示 、行背景改变、序号</b>
		<br />
		1、引用javascript/javascript.js脚本
		<br />
		2、使用方法:
		<br />
		<textarea rows="13" cols="100">&lt;table class="table">
<tr bgcolor="#c0dcf2" height="50px">
<th align="center">
序号
</th>
<th align="center">
类别
</th>
<th align="center">
标题
</th>
<th align="center">
状态
</th>
<th align="center">
添加时间
</th>
<th align="center">
操作
</th>
</tr>
&lt;s:iterator value="workLogList" id="pageworkLog" status="pageStatus">
&lt;s:if test="#pageStatus.index%2==1">
<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)"
				onmouseout="outBgcolor(this,'#e6f3fb')">
&lt;/s:if>
&lt;s:else>
&lt;tr align="center" onmouseover="chageBgcolor(this)"
onmouseout="outBgcolor(this,'')">
&lt;/s:else>
<td>
&lt;s:property value="#pageStatus.index+1" />
&lt;/td>
<td>
${pageworkLog.workLogClass.name}
</td>
<td>
${pageworkLog.title}
</td>
<td>
${pageworkLog.logStatus}
</td>
<td>
${pageworkLog.addDateTime}
&lt;/td>
&lt;td>
&lt;a href="">明细</a>
&lt;/td>
&lt;/tr>
&lt;/s:iterator>
&lt;tr>
&lt;s:if test="errorMessage==null">
&lt;td colspan="11" align="right">
第<font color="red">&lt;s:property value="cpage" /> </font> /
&lt;s:property value="total" />页&lt;fenye:pages cpage="%{cpage}"
total="%{total}" url="%{url}" styleClass="page" theme="number" />
&lt;/s:if>
&lt;s:else>
&lt;td colspan="11" align="center" style="color: red">
${errorMessage}
&lt;/s:else>
</td>
&lt;/tr>
</table>		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		</textarea>
		<hr />
		<b>六、将文本框变成横线、选中时恢复框体,离开时编程横线</b>
		<br />
		1、引用javascript/javascript.js脚本、css/css.css
		<br />
		2、使用方法:
		<br />
		<textarea rows="8" cols="70">&lt;table width="100%" border="0" style="border-collapse: collapse;">
		<input class="horizontalLine" style="" onfocus="chageClass(this,'')"
				onblur="chageClass(this,'horizontalLine')">
		</textarea>
		<br />
		3、示例
		<input class="horizontalLine" style="" onfocus="chageClass(this,'')"
			onblur="chageClass(this,'horizontalLine')">

		<br />

		<b>七、透明层</b>
		<br />
		<textarea rows="10" cols="100">
&lt;div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		&lt;/div>
		&lt;div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			&lt;div id="closeDiv"
				style="background: url(&lt;%=basePath%>/images/bq_bg2.gif); width: 100%;">
				&lt;table style="width: 100%">
					&lt;tr>
						&lt;td>
							&lt;span id="title">工序信息&lt;/span>
						&lt;/td>
						&lt;td align="right">
							&lt;img alt="" src="&lt;%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						&lt;/td>
					&lt;/tr>
				&lt;/table>
				&lt;div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					&lt;iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 400px; margin: 0px; padding: 0px;">&lt;/iframe>
				&lt;/div>
			&lt;/div>
		&lt;/div>
</textarea>
		<br />
		<b>八、下拉框查询过滤功能</b>
		<br />
				<textarea rows="10" cols="100">1、select上面增加样式class='cxselect'
	&lt;select id='testselect' class='cxselect'>
		&lt;option>&lt;option>
	&lt;/select>2、手动绑定
	$("#testselect").tinyselect();
</textarea>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		</div>
	</body>
</html>
