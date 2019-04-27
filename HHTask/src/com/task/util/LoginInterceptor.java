package com.task.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lc.action.studentAttend.AttendClassAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.qq.weixin.mp.aes.HuidiaoAction;
import com.task.Dao.TotalDao;
import com.task.action.AdminAction;
import com.task.action.BoardAction;
import com.task.action.CompanyVIPAction;
import com.task.action.DownAction;
import com.task.action.EquipmentAction;
import com.task.action.InsRecordAction;
import com.task.action.InsTemplateAction;
import com.task.action.IntelligentDiagnosisAction;
import com.task.action.NoticeAction;
import com.task.action.OrderManagerAction;
import com.task.action.OsRecordAction;
import com.task.action.OsTemplateAction;
import com.task.action.ProdEquipmentAction;
import com.task.action.QexamineAction;
import com.task.action.ScreenAction;
import com.task.action.ScreenContentAction;
import com.task.action.ScreenFilesAction;
import com.task.action.SuspsomAction;
import com.task.action.TaskmanagerAction;
import com.task.action.UserFacialFeaturesAction;
import com.task.action.UsersAction;
import com.task.action.AttendanceTow.AttendanceTowAction;
import com.task.action.android.CustomerAction;
import com.task.action.android.processpush.PushAction;
import com.task.action.barandqr.AirtightLogAction;
import com.task.action.bbs.BoardReviewAction;
import com.task.action.ess.GoodsAction;
import com.task.action.expresscabinet.WePayAction;
import com.task.action.gongyi.GongyiGuichengAction;
import com.task.action.gzbj.MeasuringAction;
import com.task.action.led.LEDAction;
import com.task.action.menjin.AccessAction;
import com.task.action.menjin.VisitAction;
import com.task.action.menjin.visitor.VisitorAction;
import com.task.action.peb.ProductEBAction;
import com.task.action.renshi.InDoorScreenAction;
import com.task.action.sop.FailureStAction;
import com.task.action.sop.ProcardAction;
import com.task.action.sop.ProcardTemplateGyAction;
import com.task.action.sop.ProcessCollectAction;
import com.task.action.sop.qd.LogoStickersAction;
import com.task.action.sop.spc.SpcControlAction;
import com.task.action.sys.CompanyInfoAction;
import com.task.action.zhaobiao.ZhaobiaoAction;
import com.task.entity.Admin;
import com.task.entity.Users;
import com.test.ShortLinkServlet;

/**
 * 登录检查拦截器
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class LoginInterceptor implements Interceptor {

	public void destroy() {

	}

	public void init() {

	}

	// 拦截所有Action 判断用户是否登录
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map paramMap = actionInvocation.getInvocationContext().getParameters();
		if (paramMap != null) {
			Object[] obj = (Object[]) paramMap.get("loginType");
			if (obj != null) {
				String loginType = (String) obj[0];
				if ("8c160649-4333-44db-bfa9-6fdf8992eccf".equals(loginType)) {
					return actionInvocation.invoke();
				}
			}
		}
		Object action = actionInvocation.getAction();
		if (action instanceof UsersAction || action instanceof AdminAction
				|| action instanceof InsTemplateAction
				|| action instanceof BoardReviewAction
				|| action instanceof InsRecordAction
				|| action instanceof OsRecordAction
				|| action instanceof ScreenAction
				|| action instanceof ScreenFilesAction
				|| action instanceof FailureStAction
				|| action instanceof BoardAction
				|| action instanceof GongyiGuichengAction
				|| action instanceof CustomerAction
				|| action instanceof CompanyInfoAction
				|| action instanceof DownAction
				|| action instanceof HuidiaoAction
				|| action instanceof OrderManagerAction
				|| action instanceof QexamineAction
				|| action instanceof ProcardAction
				|| action instanceof PushAction
				|| action instanceof AccessAction
				|| action instanceof SocketServersZWKQ
				|| action instanceof SuspsomAction
				|| action instanceof ProdEquipmentAction
				|| action instanceof AirtightLogAction
				|| action instanceof ProcardTemplateGyAction
				|| action instanceof LEDAction
				|| action instanceof CompanyVIPAction
				|| action instanceof OsTemplateAction
				|| action instanceof AttendClassAction
				|| action instanceof LogoStickersAction
				|| action instanceof NoticeAction
				|| action instanceof ScreenContentAction
				|| action instanceof SpcControlAction
				|| action instanceof UserFacialFeaturesAction
				|| action instanceof IntelligentDiagnosisAction
				|| action instanceof EquipmentAction
				|| action instanceof MeasuringAction
				|| action instanceof TaskmanagerAction
//				|| action instanceof ZhaobiaoAction
				|| action instanceof GoodsAction
				|| action instanceof ProcessCollectAction
				|| action instanceof OsRecordAction
				|| action instanceof ProductEBAction
				|| action instanceof VisitAction
				|| action instanceof VisitorAction
				|| action instanceof InDoorScreenAction
				|| action instanceof ShortLinkServlet
				|| action instanceof WePayAction
				|| action instanceof AttendanceTowAction) {
			return actionInvocation.invoke();
		}

		Map session = actionInvocation.getInvocationContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		Admin admin = (Admin) session.get("admin");
		if (user != null) {
			// 判断本次访问的UsersSession的UUID是否存在
			List list = SessionListener.getSessionsUUID();
			boolean bool = false;
			if (list != null) {
				String codeUUID = (String) ActionContext.getContext()
						.getSession().get(user.getId() + user.getCode());
				for (int i = 0; i < list.size(); i++) {
					String uuid = (String) list.get(i);
					if (codeUUID != null && codeUUID.equals(uuid)) {
						bool = true;
					}
				}
			}
			if (bool) {
				return actionInvocation.invoke();
			} else {
				HttpServletRequest request = ServletActionContext.getRequest();
				request.getSession().invalidate();
				return "UserLogin";
			}
		} else if (admin != null) {
			return actionInvocation.invoke();
		} else {
			String code = "";// 员工号
			String deptNum = "";// 部门编号
			String passwordvalue = "";// 密码
			String loginType = "";// 登录类型
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if ("code".equals(cookie.getName())) {
						code = cookie.getValue();
					} else if ("deptNum".equals(cookie.getName())) {
						deptNum = cookie.getValue();
					} else if ("password".equals(cookie.getName())) {
						passwordvalue = cookie.getValue();
					} else if ("loginType".equals(cookie.getName())) {
						loginType = cookie.getValue();
					}
				}
			}
			if (code.length() > 1) {
				if ("sjLogin.jsp".equals(loginType)) {
					response
							.sendRedirect("UsersAction!jyLogin.action?user.code="
									+ code
									+ "&password.password="
									+ passwordvalue + "&autoLogin=yes");
				} else if ("fxLogin.jsp".equals(loginType)) {
					response
							.sendRedirect("UsersAction!jyLogin2.action?user.code="
									+ code
									+ "&password.password="
									+ passwordvalue + "&autoLogin=yes");
				} else {
					response.sendRedirect("UsersAction!login.action?user.code="
							+ code + "&password.password=" + passwordvalue
							+ "&password.deptNumber=" + deptNum
							+ "&autoLogin=yes");
				}
				return null;
			}
			return "UserLogin";

		}
	}
}