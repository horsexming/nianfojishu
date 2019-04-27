package com.task.util.invoice;

//import org.apache.http.entity.mime.content.ByteArrayBody;
//import org.apache.http.entity.mime.content.FileBody;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Iterator;
import java.util.Map;


public class HttpHelper {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpHelper.class);

	/**
	 * 上传文件（包括图片）
	 * 
	 * @param url
	 *            请求URL
	 * @param paramsMap
	 *            参数和值
	 * @return
	 */
	public static ResponseContent postEntity(String url,
			Map<String, Object> paramsMap) {
		logger.info("当前POST请求地址:"+url+";参数:"+paramsMap);

//		HttpClientWrapper hw = new HttpClientWrapper();
		ResponseContent ret = null;
//		try {
//			//setParams(url, hw);
//			Iterator<String> iterator = paramsMap.keySet().iterator();
//			while (iterator.hasNext()) {
//				String key = iterator.next();
//				Object value = paramsMap.get(key);
//				if (value instanceof File) {
//					FileBody fileBody = new FileBody((File) value);
//					hw.getContentBodies().add(fileBody);
//				} else if (value instanceof byte[]) {
//					byte[] byteVlue = (byte[]) value;
//					ByteArrayBody byteArrayBody = new ByteArrayBody(byteVlue,
//							key);
//					hw.getContentBodies().add(byteArrayBody);
//				} else {
//					if (value != null && !"".equals(value)) {
//						hw.addNV(key, String.valueOf(value));
//					} else {
//						hw.addNV(key, "");
//					}
//				}
//			}
//			ret = hw.postEntity(url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return ret;
	}

}
