<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="description" content="" />
<link rel="stylesheet" href="<%=basePath %>css/main.css"/>
<link rel="stylesheet" href="<%=basePath %>css/style.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery1.42.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.SuperSlide.2.1.1.js"></script>
</head>
<body>
<div id="header">
      <h1>快问快答</h1>
      <p></p>    
    </div>
     
    <div id="content">
       <!--left-->
         <div class="left" id="learn">
           <div class="l_content">
              
           <s:iterator value="questionList" id="list" status="pageStatus">   
           <div class="wz">
            <h3>${list.title}</h3>
             <dl>
               <dd>
                 <p class="dd_text_1">${list.detail}</p>
                 <hr/>
                 <s:if test="#list.answer==null">
                 <p align="center">暂时没有回复</p>
                 </s:if>
                 <s:else>
                 <p>最新回答：${list.answer}</p>
                 <p align="right">Repair：${list.answerPerson}--${list.answerTime}</p>
                 </s:else>
               <p class="dd_text_2">
               <span class="left author">${list.questionPerson}</span><span class="left sj">时间:${list.questionTime}</span>
               <span class="left fl">分类:<a href="#">${list.type}</a></span><span class="left yd"><a onclick="toAnswer(${list.id})"title="查看回复">查看回复</a>
               </span>
                <div class="clear"></div>
               </p>
               </dd>
               <div class="clear"></div>
             </dl>
            </div>
       	</s:iterator>
       	<div align="right">
       	第
		<font color="red"><s:property value="cpage" /> </font>/
		<s:property value="total" />
		页
		<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
		styleClass="page" theme="number" />
		</div>
           </div>
         </div>
         </div>
         <div class="clear"></div>
         
   
    <!--content end-->
    <!--footer-->
    <div id="footer">
    </div>
    <script type="text/javascript" src="<%=basePath %>js/nav.js"></script>
 	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function toAnswer(id) {
			window.location.href = "QuestionAction_findAnswer.action?questionId="
				+ id;
		}
		</script>
</body>
</html>



