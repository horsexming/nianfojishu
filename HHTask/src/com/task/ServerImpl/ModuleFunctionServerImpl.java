package com.task.ServerImpl;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.ModuleFunctionServer;
import com.task.entity.ModuleFunction;
import com.task.entity.Users;
import com.task.entity.system.CompanyInfo;
import com.task.util.HttpRequest;
import com.task.util.HttpResponse;
import com.task.util.Util;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.Boolean;

/**
 * 模块功能Server实现类
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("unchecked")
public class ModuleFunctionServerImpl implements ModuleFunctionServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 查询所有模块功能
	public List findAllMF() {
		String hql = "from ModuleFunction order by sequence_id";
		List list = totalDao.query(hql);
		return list;
	}

	public static void main(String[] args) {
		String translatedStr = "";
		String transRetHtml = "";
		String encodedStr = "中文";
		String googleTransBaseUrl = "http://translate.google.cn/translate_a/t?";
		String googleTransUrl = googleTransBaseUrl;
		googleTransUrl += "&client=" + "t";
		googleTransUrl += "&text=" + encodedStr;
		googleTransUrl += "&hl=" + "zh-CN";
		googleTransUrl += "&sl=" + "zh-CN";
		googleTransUrl += "&tl=" + "en";
		googleTransUrl += "&ie=" + "UTF-8";
		googleTransUrl += "&oe=" + "UTF-8";
		HttpRequest httpRequest = new HttpRequest();
		HttpResponse httpResponse = new HttpResponse();
		try {
			httpResponse = httpRequest.sendHttpPost(googleTransUrl, null);
			String mes = httpResponse.getDataString();
			System.out.println(mes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 查询当前登录用所拥有的所有功能
	 * 
	 * @return
	 */

	public List findUserMF() {
		String hql = "from ModuleFunction  where id in " +
				"(select mf.id from ModuleFunction mf join mf.users u where u.id=? ) ";
		Users user = Util.getLoginUser();
		return totalDao.query(hql, user.getId());
	}

	/***
	 * 根据rootId 查询用户对应的目录功能
	 * 
	 * @param rootId
	 * @return
	 */
	@Override
	public List findUserMFByRootId(Integer rootId) {
		String hql = "from ModuleFunction  where rootId=? " +
				"and id in (select mf.id from ModuleFunction mf join mf.users u where u.id=? and mf.rootId=? ) order by sequence_id";
		Users user = Util.getLoginUser();
		return totalDao.query(hql, rootId, user.getId(), rootId);
	}

	// 通过Id查询模块功能
	public ModuleFunction findMfById(Integer id) {
		if (id != null && id > 0) {
			return (ModuleFunction) totalDao.getObjectById(
					ModuleFunction.class, id);
		}
		return null;
	}
	/***
	 * 通过Id查询用户对应的功能
	 * @param id
	 * @return
	 */
	@Override
	public ModuleFunction findMfByIdAndUser(Integer id) {
		if (id != null && id > 0&&Util.getLoginUser()!=null) {
			String hql = "from ModuleFunction  where " +
					"id in (select mf.id from ModuleFunction mf join mf.users u where u.id=? and mf.id=?  )";
			return (ModuleFunction) totalDao.getObjectByCondition(hql, Util.getLoginUser().getId(), id);
		}
		return null;
	}

	// 通过父类Id查询其所有子类

	public List findSonMfById(Integer id) {
		if (id != null && id > 0) {
			String hql = "from ModuleFunction where fatherId=?";
			return totalDao.query(hql, id);
		}
		return null;
	}

	public void updateRootId(Integer fatherId) {
		ModuleFunction fathermf = findMfById(fatherId);
		String hql = "from ModuleFunction where fatherId=?";
		List<ModuleFunction> list = totalDao.query(hql, fatherId);
		if (list != null) {
			for (ModuleFunction moduleFunction : list) {
				if (fathermf.getBelongLayer() == 1) {
					moduleFunction.setRootId(fathermf.getId());
				} else {
					moduleFunction.setRootId(fathermf.getRootId());
				}
				moduleFunction.setBgColor(fathermf.getBgColor());
				totalDao.update(moduleFunction);

				updateRootId(moduleFunction.getId());
			}
		} else {
			return;
		}
	}

	// 修改模块功能

	public String updateMf(ModuleFunction moduleFunction, Integer id,
			File qxImage, String qxImageFileName, File xkImage,
			String xkImageFileName, File smallImage, String smallImageFileName,
			File mrImage, String mrImageFileName, File bsImage,
			String bsImageFileName) {
		String message = "参数错误!请检查!";
		if (moduleFunction != null && id != null) {
			ModuleFunction oldModuleFunction = findMfById(id);
			if (oldModuleFunction != null) {
				// 检查是否是最后一层
				if (oldModuleFunction.getFunctionLink() == null
						|| oldModuleFunction.getFunctionLink().length() <= 0) {
					if (moduleFunction.getFunctionLink() != null
							&& moduleFunction.getFunctionLink().length() > 0) {
						List sonList = findSonMfById(id);
						if (sonList != null && sonList.size() > 0) {
							message = "该功能下存在 " + sonList.size()
									+ " 个子功能,不能添加功能链接!";
							return message;
						}
					}
				}

				oldModuleFunction.setFunctionIntro(moduleFunction
						.getFunctionIntro());
				oldModuleFunction.setFunctionLink(moduleFunction
						.getFunctionLink());
				oldModuleFunction.setFunctionName(moduleFunction
						.getFunctionName());
				oldModuleFunction.setBgColor(moduleFunction.getBgColor());
				oldModuleFunction.setTargetNewPage(moduleFunction
						.getTargetNewPage());
				oldModuleFunction.setPhoneShow(moduleFunction.getPhoneShow());
				oldModuleFunction.setEnglishName(moduleFunction
						.getEnglishName());

				/**** 上传图标 */
				// 上传qxImage
				if (qxImage != null) {
					String qxImageName = "qx"
							+ Util.getDateTime("yyyyMMddHHmmss")
							+ qxImageFileName
									.substring(
											qxImageFileName.lastIndexOf("."),
											qxImageFileName.length());
					Util
							.UploadFile(qxImage, qxImageFileName, qxImageName,
									"/upload/file/sysImages",
									"D:/WorkSpace/HHTask/WebRoot/upload/file/sysImages");
					oldModuleFunction.setQximageName(qxImageName);
				}
				if (xkImage != null) {
					// 上传xkImage
					String xkImageName = "xk"
							+ Util.getDateTime("yyyyMMddHHmmss")
							+ xkImageFileName
									.substring(
											xkImageFileName.lastIndexOf("."),
											xkImageFileName.length());
					Util
							.UploadFile(xkImage, xkImageFileName, xkImageName,
									"/upload/file/sysImages",
									"D:/WorkSpace/HHTask/WebRoot/upload/file/sysImages");
					oldModuleFunction.setImageName(xkImageName);
				}
				// 上传smallImage
				if (smallImage != null) {
					String smallImageName = "sm"
							+ Util.getDateTime("yyyyMMddHHmmss")
							+ smallImageFileName.substring(smallImageFileName
									.lastIndexOf("."), smallImageFileName
									.length());
					Util
							.UploadFile(smallImage, smallImageFileName,
									smallImageName, "/upload/file/sysImages",
									"D:/WorkSpace/HHTask/WebRoot/upload/file/sysImages");
					oldModuleFunction.setSmallImageName(smallImageName);
				}
				if (mrImage != null) {
					// 上传mrImage
					String mrdhNoColor = "mr"
							+ Util.getDateTime("yyyyMMddHHmmss")
							+ mrImageFileName
									.substring(
											mrImageFileName.lastIndexOf("."),
											mrImageFileName.length());
					Util
							.UploadFile(mrImage, mrImageFileName, mrdhNoColor,
									"/upload/file/sysImages",
									"D:/WorkSpace/HHTask/WebRoot/upload/file/sysImages");
					oldModuleFunction.setDhNoColor(mrdhNoColor);
				}
				if (bsImage != null) {
					// 上传bsImage
					String bsdhHasColor = "bs"
							+ Util.getDateTime("yyyyMMddHHmmss")
							+ bsImageFileName
									.substring(
											bsImageFileName.lastIndexOf("."),
											bsImageFileName.length());
					Util
							.UploadFile(bsImage, bsImageFileName, bsdhHasColor,
									"/upload/file/sysImages",
									"D:/WorkSpace/HHTask/WebRoot/upload/file/sysImages");
					oldModuleFunction.setDhHasColor(bsdhHasColor);
				}
				/**** 上传图标 结束 */
				// 修改模块功能
				boolean bool = totalDao.update(oldModuleFunction);
				if (bool) {
					message = "true";
				} else {
					message = "修改失败,请检查后重试!";
				}
			} else {
				message = "不存在该功能!";
			}
		}
		return message;
	}

	// 删除模块功能

	public String delMf(ModuleFunction moduleFunction) {
		String message = "参数错误!请检查!";
		if (moduleFunction != null) {
			// 检查是否是最后一层
			List sonList = findSonMfById(moduleFunction.getId());
			if (sonList != null && sonList.size() > 0) {
				message = "该功能下存在 " + sonList.size() + " 个子功能,请先删除子功能后再删除该功能!";
				return message;
			}
			// 删除模块功能
			boolean bool = totalDao.delete(moduleFunction);
			if (bool) {
				message = "true";
			}
		}
		return message;
	}

	// 添加模块功能

	public String addMf(ModuleFunction moduleFunction,
			ModuleFunction oldModuleFunction, String pageStatus) {
		String message = "参数错误!请检查!";
		if (moduleFunction != null && oldModuleFunction != null
				&& pageStatus != null && pageStatus.length() > 0) {
			if (moduleFunction.getTimeControl() != null
					&& moduleFunction.getTimeControl().equals("yes")) {
				// 判断开始时间是否大于结束时间
				if (moduleFunction.getStratDateTime().compareTo(
						moduleFunction.getEndDateTime()) > 0) {

					return "开始时间不能大于结束时间!请重新填写!";
				}
			}

			List list = findMfByName(moduleFunction.getFunctionName());// 查询功能名称是否存在
			if (list != null && list.size() > 0) {
				message = "该功能已经存在!请更改功能名称!";
				return message;
			} else {
				if ("lower".equals(pageStatus)) {// 添加下层功能
					// 判断上层的链接是否为空 (不为空则不能添加下层)
					if (oldModuleFunction.getFunctionLink() != null
							&& oldModuleFunction.getFunctionLink().length() > 1) {
						message = "上层" + oldModuleFunction.getFunctionName()
								+ "已经存在功能链接,不能添加下层功能!如需添加下层功能,请删除"
								+ oldModuleFunction.getFunctionName() + "的"
								+ oldModuleFunction.getFunctionLink();
						return message;
					}

					moduleFunction.setFatherId(oldModuleFunction.getId());// 上层Id
					moduleFunction
							.setRootId(oldModuleFunction.getRootId() == null ? oldModuleFunction
									.getId()
									: oldModuleFunction.getRootId());// ROOTId(第一层没有rootID)
					moduleFunction.setBelongLayer(oldModuleFunction
							.getBelongLayer() + 1);// 所属层
				} else if ("same".equals(pageStatus)) {// 添加下层功能
					moduleFunction.setFatherId(oldModuleFunction.getFatherId());// 上层Id
					moduleFunction.setBelongLayer(oldModuleFunction
							.getBelongLayer());// 所属层
					moduleFunction
							.setRootId(oldModuleFunction.getRootId() == null ? oldModuleFunction
									.getId()
									: oldModuleFunction.getRootId());// ROOTId(第一层没有rootID)
				} else if ("sub".equals(pageStatus)) {// 添加子模块
					moduleFunction.setIsSubModule("true");
					moduleFunction.setFatherId(oldModuleFunction.getId());
					moduleFunction.setBelongLayer(oldModuleFunction
							.getBelongLayer());// 所属层
					moduleFunction
							.setRootId(oldModuleFunction.getRootId() == null ? oldModuleFunction
									.getId()
									: oldModuleFunction.getRootId());// ROOTId(第一层没有rootID)

				} else {
					message = "非法字符!请检查!";
					return message;
				}
			}
			boolean bool = totalDao.save(moduleFunction);
			if (bool) {
				message = "true";
			} else {
				message = "修改失败,请检查后重试!";
			}
		}
		return message;
	}

	// 通过功能名称查询功能

	public List findMfByName(String functionName) {
		if (functionName != null && functionName.length() > 0) {
			String hql = "from ModuleFunction where functionName=?";
			return totalDao.query(hql, functionName);
		}
		return null;
	}

	// 绑定用户
	public boolean binDingUsers(ModuleFunction moduleFunction, Integer[] usersId) {
		if (moduleFunction != null) {
			Set<Users> usersSet = new HashSet<Users>();// 用来存储最终要的绑定用户
			Set<Users> moreSet = new HashSet<Users>();// 用来存储相对之前增加的绑定用户
			Set<Users> lessSet = new HashSet<Users>();// 用来存储相对之前减少的绑定用户
			ModuleFunction moduleFunction2 = (ModuleFunction) totalDao
					.getObjectById(ModuleFunction.class, moduleFunction.getId());
			Set<Users> haduserSet = moduleFunction2.getUsers();
			if (usersId != null && usersId.length > 0) {
				for (int i = 0; i < usersId.length; i++) {
					Users user = (Users) totalDao.getObjectById(Users.class,
							usersId[i]);// 查询用户
					if (user != null) {
						usersSet.add(user);
					} else {
						return false;
					}
				}
			}
			Users user = (Users) ActionContext.getContext().getSession().get(
					"adminusers");
			boolean bool = true;
			if (user != null) {
				List<String> deptNames = totalDao
						.query("select deptName from UserDept where userId ="
								+ user.getId());
				if (moduleFunction2 != null) {
					Set<Users> sameSet = new HashSet<Users>();// 用来存储与当前登陆用户可操作部门的用户对象
					if (haduserSet.size() > 0) {
						for (Users u : haduserSet) {
							if (u.getDept() != null
									&& deptNames.contains(u.getDept())) {
								sameSet.add(u);
							}
						}
						haduserSet.removeAll(sameSet);// 剩下的就是与当前登陆用户不可部门的用户对象
					}
					for (Users u1 : sameSet) {
						if (!usersSet.contains(u1)) {// 页面传过来的不包含说明页面新减少了这个对象的绑定
							lessSet.add(u1);
						}
					}
					for (Users u2 : usersSet) {
						if (!sameSet.contains(u2)) {// 原来的的不包含说明页面新增加了这个对象的绑定
							moreSet.add(u2);
						}
					}

				}
				usersSet.addAll(haduserSet);
			} else {
				for (Users u4 : haduserSet) {
					if (!usersSet.contains(u4)) {// 页面传过来的不包含说明页面新减少了这个对象的绑定
						lessSet.add(u4);
					}
				}
				for (Users u5 : usersSet) {
					if (!haduserSet.contains(u5)) {// 原来的的不包含说明页面新增加了这个对象的绑定
						moreSet.add(u5);
					}
				}

			}
			moduleFunction2.setUsers(usersSet);
			bool = totalDao.update(moduleFunction2);
			if (bool) {
				// 查询是否存在下层子功能
				bool = binDingSonUsers(moduleFunction, moreSet, lessSet);
				bool = binDingFatherUsers(moduleFunction, moreSet);
			}

			return bool;
		}
		return false;
	}

	@Override
	public boolean AddbinDingUsers(ModuleFunction moduleFunction,
			Integer[] usersId) {
		if (moduleFunction != null) {
			Set<Users> usersSet = new HashSet<Users>();// 用来存储最终要的绑定用户
			Set<Users> moreSet = new HashSet<Users>();// 用来存储相对之前增加的绑定用户
			Set<Users> lessSet = new HashSet<Users>();// 用来存储相对之前增加的绑定用户
			ModuleFunction moduleFind = (ModuleFunction) totalDao
					.getObjectById(ModuleFunction.class, moduleFunction.getId());
			usersSet = moduleFind.getUsers();
			if (usersId != null && usersId.length > 0) {
				for (int i = 0; i < usersId.length; i++) {
					Users user = (Users) totalDao.getObjectById(Users.class,
							usersId[i]);// 查询用户
					if (user != null) {
						usersSet.add(user);
						moreSet.add(user);
					} else {
						return false;
					}
				}
			}
			moduleFind.setUsers(usersSet);
			Boolean bool = false;
			bool = totalDao.update(moduleFind);
			if (bool) {
				// 查询是否存在下层子功能
				bool = binDingSonUsers(moduleFunction, moreSet, lessSet);
				bool = binDingFatherUsers(moduleFunction, moreSet);
			}
			return bool;
		}
		return false;
	}

	@Override
	public boolean DeletebinDingUsers(ModuleFunction moduleFunction,
			Integer[] usersId) {
		if (moduleFunction != null) {
			Set<Users> usersSet = new HashSet<Users>();// 用来存储最终要的绑定用户
			Set<Users> lessSet = new HashSet<Users>();// 用来存储相对之前减少的绑定用户
			Set<Users> moreSet = new HashSet<Users>();// 用来存储相对之前增加的绑定用户

			ModuleFunction moduleFind = (ModuleFunction) totalDao
					.getObjectById(ModuleFunction.class, moduleFunction.getId());
			usersSet = moduleFind.getUsers();
			if (usersId != null && usersId.length > 0) {
				for (int i = 0; i < usersId.length; i++) {
					Users user = (Users) totalDao.getObjectById(Users.class,
							usersId[i]);// 查询用户
					if (user != null) {
						usersSet.remove(user);
						lessSet.add(user);
					} else {
						return false;
					}
				}
			}
			moduleFind.setUsers(usersSet);
			Boolean bool = false;
			bool = totalDao.update(moduleFind);
			if (bool) {
				// 查询是否存在下层子功能
				bool = binDingSonUsers(moduleFunction, moreSet, lessSet);
				bool = binDingFatherUsers(moduleFunction, moreSet);
			}
			return bool;
		}
		return false;
	}

	// 绑定上层用户

	private boolean binDingFatherUsers(ModuleFunction moduleFunction,
			Set<Users> moreSet) {
		boolean bool = true;
		// 查询是否存在上层功能
		ModuleFunction fatherModuleFunction = findMfById(moduleFunction
				.getFatherId());
		if (fatherModuleFunction != null) {
			if (moreSet.size() > 0) {
				String hql = "from Users where " +
						"id in (select u.id from Users u join u.moduleFunction f  where f.id =?)";
				List oldUsersList = totalDao.query(hql, fatherModuleFunction
						.getId());// 查询已经与该功能绑定的用户
				// 查找不相同用户
				Set<Users> oldUserSet = new HashSet<Users>();
				for (int i = 0; i < oldUsersList.size(); i++) {
					Users oldUser = (Users) oldUsersList.get(i);
					oldUserSet.add(oldUser);
				}
				oldUserSet.addAll(moreSet);
				fatherModuleFunction.setUsers(oldUserSet);
				bool = totalDao.update(fatherModuleFunction);
				binDingFatherUsers(fatherModuleFunction, moreSet);
			}
		}
		return bool;

	}

	// 绑定子层用户

	private boolean binDingSonUsers(ModuleFunction moduleFunction,
			Set<Users> moreSet, Set<Users> lessSet) {
		boolean bool = true;
		// 查询是否存在下层子功能
		List sonMfList = findSonMfById(moduleFunction.getId());

		if (moreSet.size() > 0 || lessSet.size() > 0) {
			for (int i = 0; i < sonMfList.size(); i++) {
				ModuleFunction sonModuleFunction = (ModuleFunction) sonMfList
						.get(i);
				Set<Users> newUsersSet = new HashSet<Users>();
				String hql = "from Users where " +
						"id in (select u.id from Users u join u.moduleFunction f  where f.id =?)";
				List list = totalDao.query(hql, sonModuleFunction.getId());// 查询已经与该功能绑定的用户
				Set<Users> oldUsersSet = new HashSet<Users>();
				if (list.size() > 0) {
					for (Object o : list) {
						oldUsersSet.add((Users) o);
					}
					Set<Users> tolessSet = new HashSet<Users>();
					for (Users u : lessSet) {
						if (oldUsersSet.contains(u)) {// 如果包含上层新减少的User就减去
							tolessSet.add(u);
						}
					}
					oldUsersSet.removeAll(tolessSet);
				}

				oldUsersSet.addAll(moreSet);
				sonModuleFunction.setUsers(oldUsersSet);
				// 绑定
				bool = totalDao.update(sonModuleFunction);

				if (sonModuleFunction != null) {
					bool = bool
							& binDingSonUsers(sonModuleFunction, moreSet,
									lessSet);
				}
			}
		}
		return bool;
	}

	// 查询用户所对应第一层模块

	public List findMfByUser(Users user) {
		if (user != null) {
			String hql = "from ModuleFunction where id in " +
					"(select m.id from ModuleFunction m join m.users u  where u.id =? and m.belongLayer=1) order by sequence_id";
			return totalDao.query(hql, user.getId());
		}
		return null;
	}

	// 通过父类ID查询用户已绑定子层功能

	public List findSonMfById(Users user, Integer fatherId) {
		if (fatherId != null && fatherId > 0) {
			String hql = "from ModuleFunction where id in " +
					"(select m.id from ModuleFunction m join m.users u  where u.id =? and m.fatherId=? ) and (phoneShow is null or phoneShow ='' or phoneShow = 'no') order by sequence_id ";
			return totalDao.query(hql, user.getId(), fatherId);
		}
		return null;
	}

	// 通过父类ID查询其所有功能名称 (页面导航)
	public String findMfNameForNavigation(ModuleFunction moduleFunction,
			String pageStauts) {
		if (pageStauts == null || pageStauts == "null") {
			pageStauts = "";
		}
		String message = "";
		if (moduleFunction != null) {
			String target = "";
			String href = "ModuleFunctionAction!findMfByIdForJump.action?id="
					+ moduleFunction.getId() + "&pageStatus=" + pageStauts;
			if ("3".equals(pageStauts)) {
				target = "onclick='showWork()' target='workMain'";
				if (moduleFunction.getFunctionLink() != null
						&& moduleFunction.getFunctionLink().length() > 0) {
					href = "'" + moduleFunction.getFunctionLink() + "'";
				} else {
					target = "onclick='showWork(" + moduleFunction.getId()
							+ ");' target='workMain'";
					href = "javascript:;";
				}
			}
			message = " <a href=" + href
					+ " class='sop' style='font-weight: bolder' " + target
					+ ">" + moduleFunction.getFunctionName() + "</a>";

			if (moduleFunction.getFatherId() != null
					&& moduleFunction.getFatherId() != 0) {
				// if ("3".equals(pageStauts)) {
				// message = findFatherMfName(moduleFunction, pageStauts);
				// } else
				message = findFatherMfName(moduleFunction, pageStauts)
						+ message;
			}
		}
		return message;
	}

	// 查询上层功能名称
	private String findFatherMfName(ModuleFunction moduleFunction,
			String pageStauts) {
		String message = "";
		ModuleFunction fatherModuleFunction = findMfById(moduleFunction
				.getFatherId());
		if (fatherModuleFunction != null) {
			String target = "";
			String href = "ModuleFunctionAction!findMfByIdForJump.action?id="
					+ fatherModuleFunction.getId() + "&pageStatus="
					+ pageStauts;
			if ("3".equals(pageStauts)) {
				target = "onclick='showWork(" + fatherModuleFunction.getId()
						+ ");' target='workMain'";
				href = "javascript:;";
			}
			message = " <a href='" + href + "' class='sop' " + target + ">"
					+ fatherModuleFunction.getFunctionName() + "</a> >";
			message = findFatherMfName(fatherModuleFunction, pageStauts)
					+ message;
		}
		return message;
	}

	// 搜索功能

	public List searchModuleFunction(ModuleFunction moduleFunction, Users user) {
		if (moduleFunction != null && user != null) {
			String hql = "from ModuleFunction where  id in " +
					"(select mf1.id from ModuleFunction mf1 join mf1.users u where u.id=? and mf1.functionName like ?)";
			return totalDao.query(hql, user.getId(), "%" + moduleFunction.getFunctionName()
					+ "%");
		}
		return null;
	}

	/****
	 * 获得整个网站的配置信息
	 * 
	 * @return
	 */
	@Override
	public CompanyInfo findCompanyInfo() {
		String hql = "from CompanyInfo order by id desc";
		return (CompanyInfo) totalDao.getObjectByCondition(hql);
	}

	/*
	 * 
	 * 自拟定顺序(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.ModuleFunctionServer#updateMf1(com.task.entity.ModuleFunction
	 * , int)
	 */
	@Override
	public boolean updateMf1(ModuleFunction moduleFunction, Integer id) {
		// TODO Auto-generated method stub
		boolean bool = true;
		if (moduleFunction != null && id != null) {
			ModuleFunction oldModuleFunction = findMfById(id);
			oldModuleFunction.setSequence_id(moduleFunction.getSequence_id());
			bool = this.totalDao.update(oldModuleFunction);
		} else {
			bool = false;
		}
		return bool;
	}

	/*
	 * 
	 * 根据编号查询所有子目录(non-Javadoc)
	 * 
	 * @see com.task.Server.ModuleFunctionServer#findMfById1(int)
	 */
	@Override
	public List<ModuleFunction> findMfById1(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from ModuleFunction where fatherId=? order by sequence_id ";
		List list = this.totalDao.query(hql, id);
		return list;
	}

	@Override
	public boolean updateMfById1(Integer[] detailSelect, Integer[] detailSelect1) {
		boolean bool = true;
		if (detailSelect != null && detailSelect1 != null) {
			for (int i = 0; i < detailSelect.length; i++) {
				ModuleFunction oldModuleFunction = findMfById(detailSelect[i]);
				oldModuleFunction.setSequence_id(detailSelect1[i]);
				bool = this.totalDao.update(oldModuleFunction);
			}
		} else {
			bool = false;
		}
		return bool;
	}

	/*
	 * 
	 * 上移(non-Javadoc)id为主键 seid为序列Id
	 * 
	 * @see
	 * com.task.Server.ModuleFunctionServer#updateMfById2(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public void updateMfById2(Integer id, Integer seId) {
		String hql = "from ModuleFunction where fatherId=? and sequence_id<? order by sequence_id desc";
		List list = this.totalDao.query(hql, id, seId);
		ModuleFunction moduleFunction = null;
		Integer id_1;// 标识序列ID
		if (list != null) {
			moduleFunction = (ModuleFunction) list.get(0);// 获得上移的第一条数据
			id_1 = moduleFunction.getSequence_id();// 把当前的序列Id存放在id_1里

			String hql1 = "from ModuleFunction where fatherId=? and sequence_id=?";
			ModuleFunction moduleFunction2 = (ModuleFunction) totalDao
					.getObjectByCondition(hql1, id, seId);
			moduleFunction2.setSequence_id(id_1);// 改变当前序列的值
			moduleFunction.setSequence_id(seId);
			totalDao.update(moduleFunction);// 更新上一条序列Id
			totalDao.update(moduleFunction2);// 更新当前序列Id
		}
	}

	/*
	 * 
	 * 上移(non-Javadoc)id为主键 seid为序列Id
	 * 
	 * @see
	 * com.task.Server.ModuleFunctionServer#updateMfById2(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public void updateMfById3(Integer id, Integer seId) {
		String hql = "from ModuleFunction where fatherId=? and sequence_id>? order by sequence_id";
		List list = this.totalDao.query(hql, id, seId);
		ModuleFunction moduleFunction = null;
		Integer id_1;// 标识序列ID
		if (list != null) {
			moduleFunction = (ModuleFunction) list.get(0);// 获得上移的第一条数据
			id_1 = moduleFunction.getSequence_id();// 把当前的序列Id存放在id_1里

			String hql1 = "from ModuleFunction where fatherId=? and sequence_id=?";
			ModuleFunction moduleFunction2 = (ModuleFunction) totalDao
					.getObjectByCondition(hql1, id, seId);
			moduleFunction2.setSequence_id(id_1);// 改变当前序列的值
			moduleFunction.setSequence_id(seId);
			totalDao.update(moduleFunction);// 更新上一条序列Id
			totalDao.update(moduleFunction2);// 更新当前序列Id
		}
	}

	/*
	 * 
	 * 添加首页(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.ModuleFunctionServer#saveHome(com.task.entity.ModuleFunction
	 * )
	 */
	@Override
	public void saveHome(ModuleFunction moduleFunction) {
		// TODO Auto-generated method stub
		moduleFunction.setBelongLayer(0);
		this.totalDao.save(moduleFunction);
	}

	@Override
	public List findAllMfByUser(Users user) {
		// TODO Auto-generated method stub
		Users user2 = (Users) totalDao.getObjectById(Users.class, user.getId());
		if (user2 != null) {
			Set<ModuleFunction> mfSet = user2.getModuleFunction();
			if (mfSet != null && mfSet.size() > 0) {
				List<ModuleFunction> mfList = new ArrayList<ModuleFunction>();
				for (ModuleFunction mf : mfSet) {

					mfList.add(mf);
				}
				return mfList;
			}

		}
		return null;
	}

	/***
	 * 通过父类id和登录用户获得对应功能列表
	 * 
	 * @param fatherId
	 * @return
	 */
	@Override
	public List findMfByUserAndFId(Integer fatherId) {
		if (fatherId != null) {
			Users user = Util.getLoginUser();
			String hql = "from ModuleFunction where " +
					"id in (select m.id from ModuleFunction m join m.users u  where u.id =? and m.fatherId=?) and fatherId=?  order by sequence_id";
			return totalDao.query(hql, user.getId(), fatherId);
		}
		return null;
	}

	@Override
	public List findSonMfByIdPhone(Integer fatherId) {
		// TODO Auto-generated method stub
		String hql = "from ModuleFunction where " +
				"id in (select m.id from ModuleFunction m join m.users u  where u.id =?  and m.fatherId = ?  and phoneShow = 'yes')  order by sequence_id";
		if (Util.getLoginUser() == null)
			return null;
		return totalDao.query(hql, Util.getLoginUser().getId(), fatherId);
	}

	@Override
	public List findSonMfByIdPhone_one() {
		// TODO Auto-generated method stub
		String hql = "from ModuleFunction where " +
				"id in (select m.id from ModuleFunction m join m.users u where u.id =? and m.phoneShow = 'yes' and (m.functionLink = '' or m.functionLink is null)) order by sequence_id";
		if (Util.getLoginUser() == null)
			return null;
		return totalDao.query(hql, Util.getLoginUser().getId());
	}

	@Override
	public Object[] findMfByUserId(Integer userId, ModuleFunction Mf,
			int pageNo, int pageSize) {
		Users user = (Users) totalDao.get(Users.class, userId);
		String hql_ybd = "from ModuleFunction where id in (select m.id from ModuleFunction m join m.users u  where u.id =?) order by sequence_id";
		String hql_wbd = "from ModuleFunction where id not in (select m.id from ModuleFunction m join m.users u  where u.id ='"
				+ userId + "') order by sequence_id";
		String str = "";
		if (Mf != null && Mf.getFunctionName() != null
				&& Mf.getFunctionName().length() > 0) {
			str = " and functionName like '%" + Mf.getFunctionName() + "%'";
		}
		List<ModuleFunction> ybdMfList = totalDao.query(hql_ybd, userId);
		int count = totalDao.getCount(hql_wbd);
		List<ModuleFunction> wbdMfList = totalDao.findAllByPage(hql_wbd,
				pageNo, pageSize);

		return new Object[] { ybdMfList, wbdMfList, count, user };
	}

	@Override
	public boolean binDingModuleFunction(Integer userId, Integer[] detailSelect) {
		if (userId != null) {
			Users user = (Users) totalDao.get(Users.class, userId);
			Set<ModuleFunction> setmodulefunction = new HashSet<ModuleFunction>();
			if (detailSelect != null && detailSelect.length > 0) {
				for (int i = 0; i < detailSelect.length; i++) {
					ModuleFunction modulefunction = (ModuleFunction) totalDao
							.get(ModuleFunction.class, detailSelect[i]);
					setmodulefunction.add(modulefunction);
				}
				user.setModuleFunction(setmodulefunction);
				return totalDao.update(user);
			}
		}
		return false;
	}

	/**
	 * 
	 * @param fatherId
	 *            userId 原人员 /otheruserId 继承功能人员
	 * 
	 */
	@Override
	public Boolean copyModuleFunction(Integer userId, Integer otheruserId) {
		if (userId != null && otheruserId != null) {
			String hql = "select mf from ModuleFunction mf join mf.users u where u.id=?";
			List<ModuleFunction> CopyFunctions = totalDao.query(hql, userId);
			List<ModuleFunction> hadFunctions = totalDao
					.query(hql, otheruserId);

			Users u2 = (Users) totalDao.getObjectById(Users.class, otheruserId);

			for (ModuleFunction moduleFunction : hadFunctions) {
				Set<Users> hadUsers = new HashSet<Users>();
				hadUsers = moduleFunction.getUsers();
				hadUsers.remove(u2);
				moduleFunction.setUsers(hadUsers);
				totalDao.update(moduleFunction);
			}
			for (ModuleFunction moduleFunction : CopyFunctions) {
				Set<Users> uSet = new HashSet<Users>();
				uSet = moduleFunction.getUsers();
				uSet.add(u2);
				moduleFunction.setUsers(uSet);
				totalDao.update(moduleFunction);
			}
			return true;
		}
		return false;
	}

	// @Override
	// public List<SubModule> findSubModule(Integer id) {
	// String hql = "from SubModule s where s.moduleFunction.id=?";
	// List<SubModule> list = totalDao.query(hql, id);
	// return list;
	// }

	@Override
	public List<ModuleFunction> findSubModule(Integer id) {
		String hql = "from ModuleFunction m where m.isSubModule='true' and m.fatherId=?";
		// String hql =
		// "from ModuleFunction m where m.fatherId=? and id in (select m.id from ModuleFunction m join m.users u  where u.id =? and m.fatherId=? ) and (phoneShow is null or phoneShow ='' or phoneShow = 'no') order by sequence_id ";
		List<ModuleFunction> list = totalDao.query(hql, id);
		return list;
	}

	/***
	 * 查询同层功能
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<ModuleFunction> findLayerModule(Integer id) {
		// String hql =
		// "from ModuleFunction m where m.isSubModule='true' and m.fatherId=?";
		String hql = "from ModuleFunction  where" +
				" id in (select m.id from ModuleFunction m join m.users u  where u.id =? and m.fatherId=? " +
				"and (m.phoneShow is null or m.phoneShow ='' or m.phoneShow = 'no') ) "
				+ "order by sequence_id ";
		if (Util.getLoginUser() == null)
			return null;
		List<ModuleFunction> list = totalDao.query(hql, Util.getLoginUser()
				.getId(), id);
		return list;
	}

	@Override
	public Boolean chang2SubModule(Integer mfid, String fatherMfname) {

		ModuleFunction fatherMF = null;
		// if (fatherMfname == "father") {
		// Integer subFatherid = subModuleFunction.getFatherId();
		// fatherMF = (ModuleFunction)
		// totalDao.getObjectById(ModuleFunction.class, subFatherid);
		// } else {
		List<ModuleFunction> list = totalDao.query(
				"from ModuleFunction where functionName=?", fatherMfname);
		if (list.size() == 1) {
			fatherMF = list.get(0);
			if (fatherMF.getFunctionLink() == null
					|| "".equals(fatherMF.getFunctionLink())) {
				return false;
			}
		}
		// }
		if (fatherMF != null) {
			ModuleFunction subModuleFunction = (ModuleFunction) totalDao
					.getObjectById(ModuleFunction.class, mfid);
			Integer layerInteger = fatherMF.getBelongLayer();
			subModuleFunction.setBelongLayer(layerInteger);
			subModuleFunction.setFatherId(fatherMF.getId());
			subModuleFunction.setRootId(fatherMF.getRootId());
			subModuleFunction.setIsSubModule("true");
			totalDao.update(subModuleFunction);
			return true;
		}
		return false;

	}

	@Override
	public int delModulByUsers(Users user) {
		// 清除后台绑定关系
		if (user != null) {
			String sql = "delete from ta_usersMF where  ta_userId=?";
			int count = totalDao.createQueryUpdate(null, sql, user.getId());
			return count;
		}

		return 0;
	}

	/**
	 * 通过部门id递归获取该部门下所有的下级部门id
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getModuleFunctionIdById(Integer deptId) {
		List<Integer> deptIds = new ArrayList<Integer>();
		List<Integer> list = totalDao
				.query("select id from ModuleFunction where fatherId=" + deptId);
		if (list.size() != 0) {
			List<Integer> ids = list;
			deptIds.addAll(ids);
			for (Integer id : ids) {
				getModuleFunctionIdById(id);
			}
		}
		return deptIds;
	}

	/**
	 * 导出Excel
	 * 
	 * @param name
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.task.Server.ModuleFunctionServer#exportExcel(java.lang.String)
	 */
	public String exportExcel(String name) {
		if (name != null) {
			if ("xiangmuguanli".equals(name)) {
				name = "项目管理";
			} else if ("caigouguanli".equals(name)) {
				name = "采购管理";
			} else if ("shengchanguanli".equals(name)) {
				name = "生产管理";
			} else if ("wuliuxitong".equals(name)) {
				name = "物流系统";
			} else if ("caiwuguanli".equals(name)) {
				name = "财务管理";
			} else if ("renshiguanli".equals(name)) {
				name = "人事管理";
			} else if ("xinxiguanli".equals(name)) {
				name = "信息管理";
			} else if ("yibiaopan".equals(name)) {
				name = "仪表盘";
			}
		}
		Integer id = (Integer) totalDao.getObjectByCondition(
				"select id from ModuleFunction where functionName =?", name);

		// List<Integer> ids = getModuleFunctionIdById(id);
		// StringBuffer buffer = new StringBuffer();
		// for(int i=0;i<ids.size();i++){
		// if(i==0){
		// buffer.append(ids.get(i));
		// }else{
		// buffer.append(","+ids.get(i));
		// }
		// }
		// 查找所有子模块
		List<ModuleFunction> list = totalDao
				.query(
						"from ModuleFunction m where m.id not in(select f.fatherId from ModuleFunction f) and m.rootId=?",
						id);

		// for (ModuleFunction moduleFunction : list) {
		// String hql =
		// "from Users where id in (select u.id from Users u join u.ModuleFunction"
		// +
		// " f  where f.id =? and u.onWork not in ('离职','离职中','内退','退休','病休') and"
		// +
		// " u.dept not in('內退','病休') )"
		// + " order by dept";
		// //List<Users> userList = totalDao.query(hql, moduleFunction.getId());
		// }

		List<String> userAllList = totalDao
				.query("select name from Users where onWork not in "
						+ "('离职','离职中','内退','退休','病休') and dept not in('內退','病休') order by code");

		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(name.getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet(name, 0);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);

			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					10, WritableFont.NO_BOLD);

			// 背景颜色
			WritableCellFormat wcf1 = new WritableCellFormat(font);// 单元格样式
			wcf1.setBackground(Colour.GREEN);
			// sheet.addCell(new Label(1, 2, "测试颜色---自定义#EEA9B8", wcf1));

			String code = null;
			ModuleFunction moduleFunction = null;
			String userName = null;
			Label labelUser = null;
			Label labelCell = null;
			for (int i = 0; i < list.size(); i++) {
				moduleFunction = list.get(i);
				Label label = new Label(i + 1, 0, moduleFunction
						.getFunctionName(), wcf);
				ws.addCell(label);

				for (int j = 1; j <= userAllList.size(); j++) {
					userName = userAllList.get(j - 1);
					if (i == 0) {
						labelUser = new Label(i, j, userName, wcf);
						ws.addCell(labelUser);
						code = (String) totalDao.getObjectByCondition(
								"select u.code from Users u join u.moduleFunction f "
										+ "where f.id=? and u.name = ?",
								moduleFunction.getId(), userName);

						if (code != null) {
							labelCell = new Label(i + 1, j, "", wcf1);

							ws.addCell(labelCell);
						}
					} else {
						code = (String) totalDao.getObjectByCondition(
								"select u.code from Users u join u.moduleFunction f "
										+ "where f.id=? and u.name = ?",
								moduleFunction.getId(), userName);

						if (code != null) {
							labelCell = new Label(i + 1, j, "", wcf1);

							ws.addCell(labelCell);
						}

					}
				}
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "导出成功";
	}
}