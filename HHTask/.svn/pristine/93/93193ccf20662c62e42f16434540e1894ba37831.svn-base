package com.task.util;

/*
 * @(#)SessionListener.java	1.00	2009/02/20
 * CopyRight(C) stephen(zhoujianqiang AT gmail DOT com) 2009-2014, All rights reserved.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.entity.Users;

/**
 * session监听器. <br>
 * 在WEB容器的web.xml中添加本监听器的调用,具体格式如下：(其中的"[","]"分别用" <",">"替换) <br>
 * 
 * <pre>
 * 
 *    [web-app]
 *    [filter]
 *    ...
 *    [/filter]
 *    [filter-mapping]
 *    ...
 *    [/filter-mapping]
 *    ...
 *    [listener][listener-class]com.stephen.filter.SessionListener[/listener-class][/listener]
 *    ...
 *    [servlet]
 *    ...
 *    [/servlet]
 *    ...
 *    [/web-app]
 * 
 * </pre>
 * 
 * 注意在web.xml中配置的位置. <br>
 * 
 * @author stephen
 * @version 1.00
 * @see javax.servlet.http.HttpSessionAttributeListener
 */
public class SessionListener implements HttpSessionAttributeListener {
	/**
	 * 定义监听的session属性名.
	 */
	public final static String LISTENER_NAME = TotalDao.users;

	/**
	 * 定义存储客户登录session的集合.
	 */
	private static List sessions = new ArrayList();
	/**
	 * 定义存储客户登录session的集合.
	 */
	private static List sessionsUUID = new ArrayList();

	/**
	 * 加入session时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	@SuppressWarnings("unchecked")
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		if (LISTENER_NAME.equals(sbe.getName())) {
			Users sbeUsers = (Users) sbe.getValue();
			boolean bool = true;
			HttpSession sessionUUID = sbe.getSession();
			for (int i = 0; i < sessions.size(); i++) {
				Users loginUser = (Users) sessions.get(i);
				// 如果存在同一个帐号再次登录，则清空上次登录的UUID信息
				if ("是".equals(loginUser.getInternal())
						&& sbeUsers.getId().equals(loginUser.getId())) {
					String codeUUID = (String) ActionContext.getContext()
							.getApplication().get(
									sbeUsers.getId() + sbeUsers.getCode());
					sessionsUUID.remove(codeUUID);
					sessions.remove(loginUser);
					bool = true;
				}
			}
			if (bool) {
				// 保存数据
				sessions.add(sbe.getValue());
			}
			// 生成唯一ID
			UUID uuid = UUID.randomUUID();
			sessionsUUID.add(uuid.toString());

			ActionContext.getContext().getApplication().put(
					sbeUsers.getId() + sbeUsers.getCode(), uuid.toString());
			sessionUUID.setAttribute(sbeUsers.getId() + sbeUsers.getCode(),
					uuid.toString());
		}
	}

	/**
	 * session失效时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		if (LISTENER_NAME.equals(sbe.getName())) {
			sessions.remove(sbe.getValue());
		}
	}

	/**
	 * session覆盖时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
	}

	/**
	 * 返回客户登录session的集合.
	 * 
	 * @return
	 */
	public static List getSessions() {
		return sessions;
	}

	/**
	 * 返回客户登录sessionUUID的集合.
	 * 
	 * @return
	 */
	public static List getSessionsUUID() {
		return sessionsUUID;
	}

	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
	}
}
