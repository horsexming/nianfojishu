<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
		<style type="text/css">
			.row{
				width: 98%;			
			}
		</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<form action="productEBAction!addSubTeam.action" method="post"  id="myform" autocomplete=off >
				<div class="row clearfix">
					<h2 align="center">
						添加<s:if test="pageStatus==null || pageStatus!='chejian'">分厂</s:if><s:else>车间</s:else>信息
					</h2>
					<div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">名称</span>
	                        <input class="form-control" type="text" name="subTeam.subName" value="${subTeam.subName}">
	                    </div>
	                </div>
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">负责人</span>
	                        <input type="text" class="form-control" name="subTeam.principals" value="${subTeam.principals}">
	                    </div>
	                </div>
	            </div>
	            <div class="row">
					<!--下拉框 -->
	                <div class="form-group col-lg-3">
	                    <div class="input-group">
	                        <span class="input-group-addon">类型</span>
	                        <select class="form-control" name="subTeam.isBanzu">
	                        	<s:iterator value="{'车间','分厂','工序'}" id="type">
	                        		<s:if test="subTeam.isBanzu!=null&& #type==subTeam.isBanzu">
	                        			<option value="${type }" selected="selected">${type }</option>
	                        		</s:if>
	                        		<s:else>
	                        			<option value="${type }">${type }</option>
	                        		</s:else>
	                        	</s:iterator>
	                        </select>
	                    </div>
	                </div>
	            </div>
	            <div class="row clearfix">
	            	<div class="form-group">
						<label for="">
							基础数据信息
						</label>
						<br></br>
						<label for="">
							2015年
						</label>
						<div class="row">
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2015年出勤天数</span>
									<input class="form-control" type="text" name="subTeam.chuqinDays2015" value="${subTeam.chuqinDays2015}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2015年平均人数</span>
									<input class="form-control" type="text" name="subTeam.personCount2015" value="${subTeam.personCount2015}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2015年实际产出(仓)</span>
									<input class="form-control" type="text" name="subTeam.ccCount2015" value="${subTeam.ccCount2015}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2015年实际日人均仓</span>
									<input class="form-control" type="text" name="subTeam.avg2015" value="${subTeam.avg2015}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2015年目标日人均仓</span>
									<input class="form-control" type="text" name="subTeam.mbCount2015" value="${subTeam.mbCount2015}"/>
								</div>
							</div>
						</div>
						
						<br>
						<label for="">
							2016年
						</label>
						<div class="row">
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2016年出勤天数</span>
									<input class="form-control" type="text" name="subTeam.chuqinDays2016" value="${subTeam.chuqinDays2016}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2016年平均人数</span>
									<input class="form-control" type="text" name="subTeam.personCount2016" value="${subTeam.personCount2016}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2016年实际产出(仓)</span>
									<input class="form-control" type="text" name="subTeam.ccCount2016" value="${subTeam.ccCount2016}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2016年实际日人均仓</span>
									<input class="form-control" type="text" name="subTeam.avg2016" value="${subTeam.avg2016}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2016年目标日人均仓</span>
									<input class="form-control" type="text" name="subTeam.mbCount2016" value="${subTeam.mbCount2016}"/>
								</div>
							</div>
						</div>
						
						<br>
						<label for="">
							2017年
						</label>
						<div class="row">
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2017年出勤天数</span>
									<input class="form-control" type="text" name="subTeam.chuqinDays2017" value="${subTeam.chuqinDays2017}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2017年平均人数</span>
									<input class="form-control" type="text" name="subTeam.personCount2017" value="${subTeam.personCount2017}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2017年实际产出(仓)</span>
									<input class="form-control" type="text" name="subTeam.ccCount2017" value="${subTeam.ccCount2017}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2017年实际日人均仓</span>
									<input class="form-control" type="text" name="subTeam.avg2017" value="${subTeam.avg2017}"/>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<div class="input-group">
									<span class="input-group-addon">2017年目标日人均仓</span>
									<input class="form-control" type="text" name="subTeam.mbCount2017" value="${subTeam.mbCount2017}"/>
								</div>
							</div>
						</div>
					</div>
	            </div>
				<div class="row clearfix">
					<div class="form-group">
						<label for="">
							备注
						</label>
						<textarea class="form-control" rows="3"
							name="subTeam.remarks" >${subTeam.remarks}</textarea>
					</div>
				</br>
				<input type="hidden" value="${subTeam.id}" name="subTeam.id" id="subTeamId">
				<s:if test="subTeam.fatherId!=null">
					<input type="hidden" value="${subTeam.fatherId}" name="subTeam.fatherId">
				</s:if>
				<s:else>
					<input type="hidden" value="${id}" name="subTeam.fatherId">
				</s:else>
				<button type="submit" class="btn btn-default" id="addOrUpdate" style="background-color: #fff;">
					添加
				</button>
				<div class="col-md-12 column">
<!-- 					<form method="post" id="fromFile"> -->
<!-- 						<input type="file" name="attachment" id="file" > -->
<!-- 						<input type="button" value="提交" class="input" onclick="submitFile()"> -->
<!-- 					</form> -->
				</div>
			</div>
			</form>
		</div>
	</body>	
<script type="text/javascript">
	$(function(){
		var subTeamId = $("#subTeamId").val();
		if(subTeamId!=null && subTeamId>0){
			$jq("#myform").attr("action","productEBAction!updateSubTeam.action");
			$jq("#addOrUpdate").text("修改");
		}
	})
	
</script>	
	
</html>
