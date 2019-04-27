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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/echarts/echarts.js">
			</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font style="color: red;font-size: large">${jisungs}</font>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							科目
						</th>
						<th>
							上级科目
						</th>
						<th>
							月份
						</th>
						<th>
							借方-年初金额
						</th>
						<th>
							贷方-年初金额
						</th>
						<th>
							借方-本年累计金额
						</th>
						<th>
							贷方-本年累计金额
						</th>
						<th>
							借方-期初金额
						</th>
						<th>
							贷方-期初金额
						</th>
						<th>
							借方-本期发生金额
						</th>
						<th>
							贷方-本期发生金额
						</th>
						<th>
							 借方-期末金额
						</th>
						<th>
							贷方-期末金额
						</th>
						<th>
							明细
						</th>
					</tr>
					<s:iterator value="ListsubMonths" id="pagesub" status="statussdf">
						<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>${pagesub.name}</td>
								<td>${pagesub.fathrName}</td>
								<td align="right">
									${pagesub.bookKDate}
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.borrowYearBegingMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.lendYearBegingMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.borrowYearSumMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.lendYearSumMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.borrowQichuMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.lendQichuMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.borrowMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.lendMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.borrowJieyuMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td align="right">
									<fmt:formatNumber value="${pagesub.lendJieyuMoney}" pattern="###,###,###.##"></fmt:formatNumber>	
								</td>
								<td>
									<a href="javascript:;" onclick="window.open('CwVouchersAction!findDetailBysubId.action?id=${pagesub.fk_SubBudgetRateId}')">明细</a>
								</td>
							</tr>
					</s:iterator>
				</table>
				<br/>
				<div id="main" style="width: 98%; height: 400px;"></div>
				<br/>
					<iframe  name="workMain" target="workMain"  id="workMainIframe" src=""
						marginwidth="0" marginheight="0" hspace="0" vspace="0"
							frameborder="0" scrolling="yes"
							style="width: 100%; height: 500px; margin: 0px; padding: 0px; display: none;"></iframe>
							
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	var src = "";
	if('${tag}' == 'z55'){
		 src = "WageAction!findAllWage.action?wage.mouth=${months}";
	}else if('${tag}' == 'z11'){
		src = "GoodsStoreAction!findGoodsSum.action?goodsSum.months=${months}";
	}
	if(src!="" && src.length >0){
		$("#workMainIframe").attr('src',src);
		$("#workMainIframe").show();
	}
	
	
})

var UCLXBar,xAxis_data,yAxis_data0,min0,max0;
!function fuzhi(){
// 基于准备好的dom，初始化echarts实例
var name = '${jisungs}';
var names = name.split("=");
name = names[0];
var myChart0 = echarts.init(document.getElementById('main'));
	 myChart0.setOption({
	 backgroundColor: '#c0dcf2',
    title: {
        text: name+'本年趋势图',
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['UCLXBar']
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {
                 show: true
            },
            dataView: {readOnly: false},
            magicType: {type: ['line','bar']},
            restore: {},
            saveAsImage: {show: true}
        }
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: []
    },
    yAxis: {
       type: 'value',
    },
    series: [
        {
            name:name,
            type:'line',
            data:[],
             itemStyle:{
        		normal:{
        			color:'#FF0000',
        			lineStyle:{
        				color:'#FF0000',
        			}
        		}
            }
        },
    ]
});
	
 myChart0.showLoading();    //数据加载完之前先显示一段简单的loading动画
	UCLXBar = new Array();
 xAxis_data =  new Array();
	var jiange = 1;
	var jiange1 = 1;
	$.ajax( {
		type : "POST",
		url : "SubjectBudgetAction!getjisunGS0.action",
		data : {
			tag : '${tag}',
			months:'${months}'
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$.each(data,function(index,obj){
					UCLXBar[index]=Math.round(obj[0]*1000)/1000;
					xAxis_data[index]=obj[1];
				})
				
				
	  myChart0.hideLoading();    //隐藏加载动画
	myChart0.setOption({
        xAxis: {
            data: xAxis_data
        },
       yAxis: {
       boundaryGap:yAxis_data0,
       min:min0,
       max:max0
    },
        series: [{
            // 根据名字对应到相应的系列
            name: name,
            data: UCLXBar,
        	}
        ]
    });
	
	
			}
		}
	})
	
	
myChart0.on('click', function (params) {
    if (params.componentType === 'series') {
        if (params.seriesType === 'line') {
            if (params.dataType === 'edge') {
            }else {
            	window.open("SubjectBudgetAction!getjisunGS.action?tag=${tag}&months="+params.name);
            }
        }
    }

});
	
}();


</SCRIPT>
	</body>
</html>
