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
	<title>快递员信息录入界面</title>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript" src="System/lpanclear/jquery-1.8.3.js"></script>
	</head>
	
	<style type="text/css">
	*{margin:0;padding:0;list-style-type:none;font-family: 楷体;}
	a,img{border:0;}
	img{vertical-align:middle;}
	#fdhxstp{
	transform: rotate(90deg);
	}
	</style>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="ycczsc" style="margin-left:100px; width:750px;height:1400px;position: absolute;background-color:#f8f8ff; font-size:30px; border:10px solid;border-color:#dddddd;display:none;">
           <p style="padding-top:50px;text-align:center; height:200px;font-size:50px;font-weight: bold;">快递员服务协议</p>
           <p style="height:120px; margin:0px;">&nbsp;本协议由您和PEBS签订,您签署本协议视为您同意<br/><br/>同时遵守《PEBS网站服务协议》</p>
           <p style="height:300px;margin:0px;">
                                      一丶服务条款<br/><br/>本网站将可能不时的修改本协议的有关条款，一旦条款内<br/><br/>
                                      容发生变动，本网站将会在相关的页面提示修改内容。如<br/><br/>
                                      果不同意本网站对服务条款所做的修改，用户有权停止使<br/><br/>
                                       用PEBS服务。如果用户继续使用PEBS服务，则视为用户<br/><br/>
                                       接受服务条款的变动。<br/><br/>
           </p>
           <p style="height:300px;margin:0px;">
		           二丶法律管辖<br/><br/>本协议的订立、执行和解释及争议的解决均应适用中国<br/><br/>
		           法律。如双方就本协议内容或其执行发生任何争议，双方<br/><br/>
		           应尽量友好协商解决；协商不成时，任何一方均可向本网<br/><br/>
		           站所在地的人民法院提起诉讼。</p>
           <p style="height:300px;margin:0px;">
		           三丶其他规定<br/><br/>本协议构成双方对本协议之约定事项及其他有关事宜<br/><br/>
		           的完整协议，除本协议规定的之外，未赋予本协议各方。<br/><br/>
		           其他权利如本协议中的任何条款无论因何种原因完全<br/><br/>
		           或部分无效或不具有执行力，本协议的其余条款仍应有<br/><br/>
		           效并且有约束力。</p>
          
        </div>
		<div width=100%; id="gongneng" style="">
			<div width=100% height=100% style="display:block;">
			<form id="cfrom" action="WePayAction_AddCourier.action" method="POST" enctype="multipart/form-data">			
			    <table width=80% style="text-align:center;height:1100px; font-size: 40px;">		
			        <tr>
			            <td width=16%></td>
			            <td colspan="2" style="font-size:46px;">请填写注册信息</td>
			        </tr>	        
			        <tr>
			            <td width=16%></td>
			            <td width=25% style="text-align:left;">姓名:</td>
			            <td><input style="width:370px;height:60px;font-size:36px;" name="courier.couName" type="text" value="" id="kdyname" /></td>
			        </tr>
			        <tr>
			            <td width=16%></td>
			            <td width=25% style="text-align:left;">手机号:</td>
			            <td><input style="width:370px;height:60px;font-size:36px;" name="courier.phoneNumber" type="text" readonly="readonly" value="${phoneNumber}" id="pNumber" /></td>
			        </tr>
			        <tr>
			            <td width=16%></td>
			            <td width=25% style="text-align:left;">身份证号:</td>
			            <td><input style="width:370px;height:60px;font-size:36px;" name="courier.idNumber" type="text" value="" id="idNumber" /></td>
			        </tr>
			        <tr>
			            <td width=16%></td>
			            <td width=25% style="text-align:left;">快递公司:</td>
			            <td>
			                <select name="courier.kdCompany" style="width:370px;height:60px;" id="kdCompany">
			                    <s:iterator id="ccpanys" value="couCpanyList" status="status">
			                        <s:if test="#ccpanys==null">
								    </s:if>		
								    <s:else>
			                            <option value="${ccpanys.id}">${ccpanys.couCpanyName}</option>
			                        </s:else>
			                    </s:iterator>			                    
			                </select>
			            </td>
			        </tr>
			    </table>
			    <table width=80% style="text-align:center;height:350px; font-size: 40px;">
			        <tr>
			            <td width=15%></td>
			            <td><p style="font-size:24px;">请提供您的身份证正面照片或者是工作证正面照片(上传照片请保证清晰可见方便审核,提交后有一段审核期,请耐心等候)</p></td>
			        </tr>
			        <tr>
			            <td width=15%></td>
			            <!-- 上传身份证正反面或者工作证 -->
			            <td colspan="2">
			                <input name="idFront" type=file name="picture" id="picture" accept="image/*"  style=" color:red; font-size:28px;" onchange="javascript:setImagePreview();">  		                    
			                <p style="float:left" id="localImag"><p><img  id="preview" src="images/lpanclear/bg.png" width=-1 height=-1  style="transform: rotate(90deg);display:block;width:280px;height:280px;" /></p></p>
			               
                        </td>                           
			        </tr>
			        <tr>
			            <td width=15%></td>
			            <td style="font-size:26px;"><input type="checkbox" name="xx" value="" id="txfxk"  style="height:30px;width:30px;"/>同意<a href="javascript:void(0)" id="ckxx">PEBS快递柜协议</a>并且愿意承担相应法律责任</td>
			        </tr>
			        <tr>
			            <td width=15%></td>
			            <td style="text-align: center;">
			                <p style="text-align: center;height:100px;"><input style="width:190px;height:65px; font-size:36px;" id="tj" onclick="tjff();" type="button" value="提&nbsp;&nbsp;&nbsp;&nbsp;交"/></p>
			            </td>
			        </tr>
			    </table>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)--> 
		<script type="text/javascript">  
		//显示协议
		 //显示操作手册
	    $("#ckxx").click(function(){	
	    	if($('#ycczsc').is(':hidden')){
	    		$("#ycczsc").show();
	    	}else{
	    		$("#ycczsc").hide();
	    	}	    	
	    })
		$("#ycczsc").click(function(){	
	    	if($('#ycczsc').is(':hidden')){
	    		$("#ycczsc").show();
	    	}else{
	    		$("#ycczsc").hide();
	    	}	    	
	    })
		
		//使用button进行验证后提交from表单		
		function tjff(){
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
			var cName =  $("#kdyname").val();
			var phoneNumber =  $("#phoneNumber").val();
			var idNumber =  $("#idNumber").val();
			var kdCompany =  $("#kdCompany").val();
			var idFront =  $("#picture").val();			
			
			if(cName==""){
				alert("姓名不能为空!!!");			
			}else if(phoneNumber==""){
				alert("手机号不能为空!!!");
			}else if(idNumber==""){
				alert("身份证号不能为空!!!");
			}else if(!idNumber.match(reg)){
				alert("身份证号无效!!!");
			}else if(kdCompany==""){
				alert("所属快递公司不能为空!!!");
			}else if(idFront==""){
				alert("证件照不能为空");
			}else if(!($("#txfxk").attr("checked"))){
				alert("请同意协议");
			}else{
				//alert("可以进行提交from了");
				$("#cfrom").submit();
			}
		}
		
		
		function xxlrdate1(){
			
			if(cName==""){
				alert("姓名不能为空!!!");
				return false;
			}
			if(phoneNumber==""){
				alert("手机号不能为空!!!");
				return false;
			}
			if(idNumber==""){
				alert("身份证号不能为空!!!");
				return false;
			}
			if(reg.test(idNumber)===false){
				alert("身份证号无效!!!");
				return false;
			}
			if(kdCompany==""){
				alert("所属快递公司不能为空!!!");
				return false;
			}
			if(idFront==""){
				alert("证件照不能为空");
				return false;
			}
			return true;
		}
		
		
		
		
		function setImagePreview() {          
	    var docObj=document.getElementById("picture");           
	    var imgObjPreview=document.getElementById("preview");  
	    if(docObj.files && docObj.files[0]){                          
	        //火狐下，直接设img属性                          
	        imgObjPreview.style.display = 'block';                          
	        imgObjPreview.style.width = '280px';                          
	        imgObjPreview.style.height = '280px';                                              
	        //imgObjPreview.src = docObj.files[0].getAsDataURL();  
	        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式          
	        imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);  
	     }else{                          
	         //IE下，使用滤镜                          
	         docObj.select();                          
	         var imgSrc = document.selection.createRange().text;                          
	         var localImagId = document.getElementById("localImag");  
	         //必须设置初始大小                          
	         localImagId.style.width = "280px";                          
	         localImagId.style.height = "280px";  
	        //图片异常的捕捉，防止用户修改后缀来伪造图片  
	        try{                                  
	            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";                                  
	            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;                          
	        }catch(e){                                  
	            alert("您上传的图片格式不正确，请重新选择!");                                  
	            return false;                          
	        }  
	            imgObjPreview.style.display = 'none';                          
	            document.selection.empty();                  
	        }                  
	            return true;          
	        }
				
	    </script>
	</body>
</html>
