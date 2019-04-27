package com.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.UserRoleServer;
import com.task.entity.ModuleFunction;
import com.task.entity.UserRole;
import com.task.entity.Users;
import com.task.util.MKUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SuppressWarnings("serial")
public class UserRoleAction extends ActionSupport {
    private Integer id;
    private List list;
    private UserRole userRole;
    private List<UserRole> userroles;
    private UserRoleServer userroleserver;
    private List<ModuleFunction> moduleFunctions;
    private List<Users> users;
    private String DeptName;
    private String url;

    public String show() {
        userroles = userroleserver.findAllrole();
        // for (Role r : roles) {
        // Set<Users> u=new HashSet<Users>();
        // u.addAll(roleserver.findRoleuser(r.getId()));
        // r.setUsers(u);
        // }
        return "UserRole_listshow";
    }

    public String add() {
        if (userRole.getName() == null) {
            return "error";
        }
        userroleserver.addrole(userRole);
        userroles = userroleserver.findAllrole();
        return "UserRole_listshow";
    }

    public String delete() {
        try {
            userroleserver.deleterole(userRole.getId());
            // MKUtil.writeJSON(true, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            // MKUtil.writeJSON(false, "删除失败：" + e.getMessage(), null);
        }
        userroles = userroleserver.findAllrole();
        return "UserRole_listshow";

    }

    public String editRole() {
        userRole = userroleserver.findbyIdrole(id);
        return "UserRole_update";
    }

    //修改名称简介
    public String updateRole() {
        try {
            userroleserver.updateUserRole(userRole);
            userroles = userroleserver.findAllrole();
            return "UserRole_listshow";
        } catch (Exception e) {
            MKUtil.writeJSON(false, "修改失败：" + e.getMessage(), null);
            return "error";
        }
    }


    //修改绑定内容
    public String update() {
        try {
            userRole.setModuleFunction(new HashSet<ModuleFunction>(
                    moduleFunctions));
            userroleserver.updaterole(userRole);
            MKUtil.writeJSON(true, "修改成功", null);
        } catch (Exception e) {
            MKUtil.writeJSON(false, "修改失败：" + e.getMessage(), null);
            return "error";
        }
        return "UserRole_listshow";
    }

    public String editModuleFunction() {
        userRole = userroleserver.findbyIdrole(userRole.getId());
        // 显示已绑定mf
        // Set<ModuleFunction> mf = new HashSet<ModuleFunction>();
        // moduleFunctions=roleserver.findRolemoduleFunction(role.getId());
        // mf.addAll(moduleFunctions);
        // role.setModuleFunction(mf);

        return "UserRole_editModuleFunction";
    }

    public String edituser() {
        userRole = userroleserver.findbyIdrole(userRole.getId());
        // 显示已绑定users
        // Set<Users> u = new HashSet<Users>();
        // u.addAll(userroleserver.findRoleuser(userRole.getId()));
        // userRole.setUsers(u);
        return "UserRole_edituser";
    }

    // 绑定moduleFunction
    public String addmoduleFunction() {
        try {
            userRole.setModuleFunction(new HashSet<ModuleFunction>(
                    moduleFunctions));
            userroleserver.updaterole(userRole);
            MKUtil.writeJSON(true, "添加成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            MKUtil.writeJSON(false, "添加失败：" + e.getMessage(), null);
        }
        return null;
    }

    // 绑定user
    public String addusers() {
        try {
            if (users == null)
                users = new ArrayList<Users>();
            userRole.setUsers(new HashSet<Users>(users));
            userroleserver.updaterole(userRole);
            MKUtil.writeJSON(true, "添加成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            MKUtil.writeJSON(false, "添加失败：" + e.getMessage(), null);
        }
        return "UserRole_listshow";
    }

    // 显示已绑定模块
    public String findbindingMFbyid() {
        moduleFunctions = userroleserver.findRolemoduleFunction(id);
        List list = new ArrayList<Integer>();
        for (int i = 0; i < moduleFunctions.size(); i++) {
            list.add(moduleFunctions.get(i).getId());
        }
        MKUtil.writeJSON(list);
        return null;
    }

    public String findbindingUserbyid() {
        users = userroleserver.findRoleuser(id);
        List list = new ArrayList<Integer>();
        Users index_user = new Users();
        for (int i = 0; i < users.size(); i++) {
            index_user = users.get(i);
            if ("离职".equals(index_user.getOnWork()) || "内退".equals(index_user.getOnWork()) ||
                    "离职中".equals(index_user.getOnWork()) || "退休".equals(index_user.getOnWork()) ||
                    "内退".equals(index_user.getDept()) || "病休".equals(index_user.getDept())) {
            } else {
                list.add(users.get(i).getId());
            }
        }

        MKUtil.writeJSON(list);
        return null;
    }

    // users Ztree
    public String findAllUsers() {
        Object[] objects = userroleserver.findAllUsers();
        MKUtil.writeJSON(objects);
        return null;
    }

    public String findUsersByDept() {
        Object[] objects = userroleserver.findUsersByDept(DeptName);
        MKUtil.writeJSON(objects);
        return null;

    }

    public List<ModuleFunction> getModuleFunctions() {
        return moduleFunctions;
    }

    public void setModuleFunctions(List<ModuleFunction> moduleFunctions) {
        this.moduleFunctions = moduleFunctions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<UserRole> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRole> userroles) {
        this.userroles = userroles;
    }

    public UserRoleServer getUserroleserver() {
        return userroleserver;
    }

    public void setUserroleserver(UserRoleServer userroleserver) {
        this.userroleserver = userroleserver;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
