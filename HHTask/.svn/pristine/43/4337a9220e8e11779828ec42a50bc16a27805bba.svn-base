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
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript">
var fileDivHTML = "";
var count = 0;
function uploadFile(obj) {
	var fileDiv = document.getElementById("fileDiv");
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	var fileDivHTML = "<div id='file" + count + "'>"
	var fileDivHTML1 = "<input type='file' name='attachment' value='0'></div>";
	if (count == 0) {
		fileDivHTML = fileDivHTML
				+ "样&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品:"
				+ fileDivHTML1;
	} else if (count == 1) {
		fileDivHTML = fileDivHTML
				+ "图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;纸:"
				+ fileDivHTML1;
	} else if (count == 2) {
		fileDivHTML = fileDivHTML + "检&nbsp;&nbsp;验&nbsp;&nbsp;报&nbsp;&nbsp;告:"
				+ fileDivHTML1;
	} else if (count == 3) {
		fileDivHTML = fileDivHTML + "材&nbsp;&nbsp;质&nbsp;&nbsp;证&nbsp;&nbsp;明:"
				+ fileDivHTML1;
	} else if (count == 4) {
		fileDivHTML = fileDivHTML + "环&nbsp;&nbsp;保&nbsp;&nbsp;报&nbsp;&nbsp;告:"
				+ fileDivHTML1;
	} else if (count == 5) {
		fileDivHTML = fileDivHTML + "材&nbsp;&nbsp;料&nbsp;&nbsp;性&nbsp;&nbsp;能:"
				+ fileDivHTML1;
	} else if (count == 6) {
		fileDivHTML = fileDivHTML + "盐&nbsp;&nbsp;雾&nbsp;&nbsp;检&nbsp;&nbsp;验:"
				+ fileDivHTML1;
	} else if (count == 7) {
		fileDivHTML = fileDivHTML + "模具认证资料:" + fileDivHTML1;
	} else if (count == 8) {
		fileDivHTML = fileDivHTML + "工&nbsp;&nbsp;艺&nbsp;认&nbsp;证:&nbsp;&nbsp;"
				+ fileDivHTML1;
		$("#fileButton").remove();
	}
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}
</script>
	</head>
	<body>
		<form action="ZhuserOfferAction_addSample.action" method="post"
			enctype="multipart/form-data">
			<table class="table">
				<tr align="center">
					<td colspan="4" style="font-size: 20px;">
						打样确认表
						(${zhuserOffer.cmp}-${zhuserOffer.cperson}-${zhuserOffer.ctel})
					</td>
				</tr>
				<tr>
					<td align="right">
						件号：
					</td>
					<td>
						${zhuserOffer.markId}
					</td>
					<td align="right">
						工序号：
					</td>
					<td>
						${zhuserOffer.processNO}
					</td>
				</tr>
				<tr>
					<td align="right">
						工序名称：
					</td>
					<td>
						${zhuserOffer.processName}
					</td>
					<td align="right">
						报价：
					</td>
					<td>
						${zhuserOffer.taxprice}(税率)
						<hr>
						${zhuserOffer.hsPrice}(含税价)
						<hr>
						${zhuserOffer.bhsPrice}(不含税价)
					</td>
				</tr>
				<tr>
					<td align="right">
						上传附件:
						<br />
						(Upload)
					</td>
					<td colspan="10">
						<div id="fileDiv" style="display: block;">
							<div id="file1">
								图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;纸:
								<input type="file" name="sample.picF">
								<input type="hidden" name="zhuserOffer.id" value="${zhuserOffer.id}">
							</div>
							<div id="file2">
								检&nbsp;&nbsp;验&nbsp;&nbsp;报&nbsp;&nbsp;告:
								<input type="file" name="sample.checkNoteF" >
							</div>
							<div id="file3">
								材&nbsp;&nbsp;质&nbsp;&nbsp;证&nbsp;&nbsp;明:
								<input type="file" name="sample.caiZhiF">
							</div>
							<div id="file4">
								环&nbsp;&nbsp;保&nbsp;&nbsp;报&nbsp;&nbsp;告:
								<input type="file" name="sample.huanBaoF">
							</div>
							<div id="file5">
								材&nbsp;&nbsp;料&nbsp;&nbsp;性&nbsp;&nbsp;能:
								<input type="file" name="sample.caiLiaoF">
							</div>
							<div id="file6">
								盐&nbsp;&nbsp;雾&nbsp;&nbsp;检&nbsp;&nbsp;验:
								<input type="file" name="sample.yanWuF">
							</div>
							<div id="file7">
								模具认证资料:
								<input type="file" name="sample.moJuRenZhenF">
							</div>
							<div id="file8">
								工&nbsp;&nbsp;艺&nbsp;认&nbsp;证:&nbsp;&nbsp;
								<input type="file" name="sample.gongYiF">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					</td>
				</tr>

			</table>
			<div align="center">
				<input class="input" type="submit" />
			</div>
		</form>
	</body>
</html>
