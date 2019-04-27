<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--<HTML>--%>
<%--	<HEAD>--%>
<%--		<TITLE>WMI Scripting HTML</TITLE>--%>
<%--		<META http-equiv=Content-Type content="text/html;   charset=gb2312">--%>
<%--		<SCRIPT language=JScript--%>
<%--			event="OnCompleted(hResult,pErrorObject,   pAsyncContext)" for=foo>  --%>
<%--    document.forms[0].txtMACAddr.value=unescape(MACAddr);  --%>
<%--    document.forms[0].txtIPAddr.value=unescape(IPAddr);  --%>
<%--    document.forms[0].txtDNSName.value=unescape(sDNSName);  --%>
<%--    //document.formbar.submit();  --%>
<%--      </SCRIPT>--%>
<%--		<SCRIPT language=JScript--%>
<%--			event=OnObjectReady(objObject,objAsyncContext) for=foo>  --%>
<%--        if(objObject.IPEnabled   !=   null   &&   objObject.IPEnabled   !=   "undefined"   &&   objObject.IPEnabled   ==   true)  --%>
<%--        {  --%>
<%--          if(objObject.MACAddress   !=   null   &&   objObject.MACAddress   !=   "undefined")  --%>
<%--          MACAddr   =   objObject.MACAddress;  --%>
<%--          if(objObject.IPEnabled   &&   objObject.IPAddress(0)   !=   null   &&   objObject.IPAddress(0)   !=   "undefined")  --%>
<%--          IPAddr   =   objObject.IPAddress(0);  --%>
<%--          if(objObject.DNSHostName   !=   null   &&   objObject.DNSHostName   !=   "undefined")  --%>
<%--          sDNSName   =   objObject.DNSHostName;  --%>
<%--          }  --%>
<%--      </SCRIPT>--%>
<%--		<META content="MSHTML   6.00.2800.1106" name=GENERATOR>--%>
<%--	</HEAD>--%>
<%--	<BODY>--%>
<%--		<OBJECT id=locator classid=CLSID:76A64158-CB41-11D1-8B02-00600806D9B6--%>
<%--			VIEWASTEXT></OBJECT>--%>
<%--		<OBJECT id=foo classid=CLSID:75718C9A-F029-11d1-A1AC-00C04FB6C223></OBJECT>--%>
<%--		<SCRIPT language=JScript>  --%>
<%--        var   service   =   locator.ConnectServer();  --%>
<%--        var   MACAddr   ;  --%>
<%--        var   IPAddr   ;  --%>
<%--        var   DomainAddr;  --%>
<%--        var   sDNSName;  --%>
<%--        service.Security_.ImpersonationLevel=3;  --%>
<%--        service.InstancesOfAsync(foo,   'Win32_NetworkAdapterConfiguration');  --%>
<%--        </SCRIPT>--%>
<%----%>
<%--		<FORM id=formfoo name=formbar action=NICPost.asp method=post>--%>
<%--			<INPUT value="" name="txtMACAddr">--%>
<%--			<INPUT value="" name="txtIPAddr">--%>
<%--			<INPUT value="" name="txtDNSName">--%>
<%--		</FORM>--%>
<%--	</BODY>--%>
<%--</HTML>--%>


<!DOCTYPE HTML>
<html>
	<head>
		<title>js</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta content="MSHTML 6.00.2800.1106" name="GENERATOR">
	</head>

	<body>
		<object id=locator classid=CLSID:76A64158-CB41-11D1-8B02-00600806D9B6
			VIEWASTEXT></object>

		<object id=foo classid=CLSID:75718C9A-F029-11d1-A1AC-00C04FB6C223></object>
		<script language="JScript">
var service = locator.ConnectServer();
var MACAddr;
var IPAddr;
var DomainAddr;
var sDNSName;
service.Security_.ImpersonationLevel = 3;
service.InstancesOfAsync(foo, 'Win32_NetworkAdapterConfiguration');
</script>
		<script language="JScript"
			event="OnCompleted(hResult,pErrorObject, pAsyncContext)" for="foo">
document.forms[0].txtMACAddr.value = unescape(MACAddr);
document.all.txtIPAddr.value = unescape(IPAddr);
document.forms[0].txtDNSName.value = unescape(sDNSName);
//document.formbar.submit(); 
</script>
		<script language="JScript"
			event="OnObjectReady(objObject,objAsyncContext)" for="foo">
if (objObject.IPEnabled != null && objObject.IPEnabled != "undefined"
		&& objObject.IPEnabled == true) {
	if (objObject.MACAddress != null && objObject.MACAddress != "undefined") {
		MACAddr = objObject.MACAddress;
	}
	if (objObject.IPEnabled && objObject.IPAddress(0) != null
			&& objObject.IPAddress(0) != "undefined") {
		IPAddr = objObject.IPAddress(0);
	}
	if (objObject.DNSHostName != null && objObject.DNSHostName != "undefined") {
		sDNSName = objObject.DNSHostName;
	}
}
</script>

		<form id="formfoo" name="formbar" action="#" method="post">
			本机MAC地址：<input value="" name="txtMACAddr"><br/>
			本机IP地址：<input value="" name="txtIPAddr"><br/>
			本机计算机名：<input value="" name="txtDNSName">
		</form>

		<script language=javascript>
var wshNetwork = new ActiveXObject("WScript.Network");
<%--域名--%>
document.write("域名= " + wshNetwork.UserDomain + "<br/>");
<%--document.write("域名= " + wshNetwork.MACAddress + "<br/>");--%>
<%--document.write("域名= " + wshNetwork.IPAddress(0) + "<br/>");--%>
<%--计算机名--%>
document.write("计算机名= " + wshNetwork.ComputerName + "<br/>");
<%--登录用户名--%>
document.write("登录用户名 = " + wshNetwork.UserName + "<br/>");
</script>
	</body>
</html>


<%--<!DOCTYPE HTML>--%>
<%--<html>--%>
<%--	<head>--%>
<%--		<title>js</title>--%>
<%--		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
<%--		<meta content="MSHTML 6.00.2800.1106" name="GENERATOR">--%>
<%--	</head>--%>
<%--	<body>--%>
<%--		<script language=javascript>--%>
<%-- var wshNetwork = new ActiveXObject("WScript.Network");--%>
<%-- document.write("ym       = "+ wshNetwork.UserDomain+"<br/>");--%>
<%-- document.write("js   = "+ wshNetwork.ComputerName+"<br/>");--%>
<%-- document.write("users = "+ wshNetwork.UserName+"<br/>");--%>
<%-- </script>--%>
<%--	</body>--%>
<%--</html>--%>

