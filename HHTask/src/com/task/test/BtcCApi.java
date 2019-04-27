package com.task.test;

import java.util.Date;

public class BtcCApi {
	
	
	String access_key="7edff9ae-f32c-4875-9ca7-93bc9ab72aa8";
	String 	secret_key="8c0c2990-536a-4968-83ab-93dd83c96050";
	Long tonce=new Date().getTime();
	String	params="tonce="+tonce+"&accesskey="+access_key+"&requestmethod=post&id=1&method=getAccountInfo&params=";
//		params_hash = hmac.new(secret_key, params, hashlib.sha1).hexdigest();
//		base64string = base64.b64encode(access_key+':'+params_hash)
//		headers = {
//		'Content-type': 'application/json-rpc',
//		'Authorization': 'Basic '+base64string,
//		'Json-Rpc-Tonce': tonce
//		}
//		postdata = '{"method": "getAccountInfo", "params": [], "id": 1}'
//		conn=httplib.HTTPSConnection("btcchina.com")
//		conn.request("POST",'/api_trade_v1.php',postdata,headers)
//		response = conn.getresponse()
//		print "\nresponse: ",response.read()

}
