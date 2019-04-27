package com.task.ServerImpl.yw;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

/**
 * @Title: ResponseUtil.java
 * @Package com.task.ServerImpl.yw
 * @Description: 返回响应的信息
 * @author 曾建森
 * @date 2012-11-10 下午05:34:42
 * @version V1.0
 */
public class ResponseUtil {
	/**
	 * @Title: write
	 * @Description: TODO 返回页面响应的弹窗
	 * @param response
	 * @param message
	 * @param target
	 * @param map 
	 * @return void
	 * @throws
	 */
	public static void write(HttpServletResponse response,String message,String target,Map map){
		if(response != null && message != null && target != null && !target.equals("")){
			if(map != null && map.size() > 0){
				StringBuffer param = new StringBuffer("?");
				Set<Map.Entry> entry = map.entrySet();
				for(Map.Entry obj : entry){
					param.append(obj.getKey()).append("=").append(obj.getValue()).append("&");
				}
				target = target + param.toString();
				target = target.substring(0,target.length()-1);
			}
			try {
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print("<script>alert('" + message + "');window.location='"+target+"'</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void write(String msg){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(msg);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeObj(Object obj){
		try {
			String msg = JSON.toJSONString(obj);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(msg);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
