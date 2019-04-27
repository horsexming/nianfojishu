package com.task.action.zhaobiao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.supplier.SupplierCertificationService;
import com.task.Server.zhaobiao.ZhaobiaoServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Category;
import com.task.entity.Users;
import com.task.entity.ZhMetric;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.WaigouOrder;
import com.task.entity.supplier.SupplierCertification;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "unused", "serial"})
public class ZhaobiaoAction<GysmarkIdjiepai> extends ActionSupport {
    private ZhMetric zhMetric;
    private ZhaobiaoServer zhaobiaoServer;
    private ZhUser zhUser;// 供应商
    private ChargebackNotice chargebackNotice;// 扣款单
    private PrepaymentsApplication prepayApp;// 预付款申请单
    private WaigouOrder waigouOrder;// 外购采购订单表
    private File addzhUser;
    private Zhmoban zhmoban;
    private Zhaobiao zhaobiao;
    private DeptMonthBudget deptMonthBudget;
    private ZhaobiaoXi zhaobiaoXi;
    private ZhaobiaoXis zhaobiaoXis;
    private String successMessage;// 成功消息
    private String errorMessage;// 错误消息
    private int id;// id
    private Integer id1;// id1
    private String year;// 年份
    private String pageStatus;// 页面状态
    private String pagename;
    private Zhtoubiao zhtoubiao;
    private Flowdetail flowdetail;// 审批表
    private Huikuang huikuang;
    private Hui_Xi huiXi;
    List<ChargebackNotice> chargebackNoticeList;// 扣款单
    List<PrepaymentsApplication> prepayAppList;// 扣款单

    private String csNumber;

    private Integer hh;
    private String[] kemus;
    private float[] moneys;
    private Integer[] orderId;
    private String gysName;//供应商名称
    private List<WaigouOrder> waigouOrderList;
    private List<PrepaymentsApplicationDetails> prepaymentsApplicationDetailsList;
    private List<PrepaymentsApplicationDetails> prepaymentsApplicationDetailsList1;
    private float zongCount;
    // 上传
    private File t8;
    private String t8Type;
    private String t8FileName;
    //
    private File shenhefujian;
    private String shenhefujianType;
    private String shenhefujianFileName;

    private Float coun;
    /*
     * 测试数据 杨 270039 449 粟 161530 450刘 151233 453
	 */

    // 分页
    private String cpage = "1";
    private String total;
    private String url;
    private String tag;//状态
    private int pageSize = 15;
    //
    private List list;
    private List listyusuan;
    private String pagemoban;
    private HuikuangXiangxi huikuangXiangxi;
    private HuikuangXiangxis huikuangXiangxis;
    private Zhgongxu zhgongxu;
    private Nianlilv nianlilv;
    private ProcardTemplate procardTemplate;
    private GysMarkIdjiepai gysjiepai;
    private List<GysMarkIdjiepai> gysMarkIdjiepais;
    private List<Category> cylist;
    // ----------------------------
    private int[] selected;
    private String addmachineContentType;// 文件类型
    private String addmachineFileName;// 文件名称

    private File[] attachment;
    private String[] attachmentContentType;
    private String[] attachmentFileName;

    //供应商认证
    private SupplierCertification supplierCertification;
    private SupplierCertificationService supplierCertificationService;


    /***
     * 采购补充合同
     */
    public String updateZhtoubiaoY() {
        // Zhtoubiao oldt=zhaobiaoServer.getByIdZhtoubiao(zhtoubiao.getTid());
        // oldt.setQiandingTime(zhtoubiao.getQiandingTime());
        // oldt.setQiTime(zhtoubiao.getQiTime());
        // oldt.setZhongTime(zhtoubiao.getZhongTime());
        // oldt.setJiaohuoTime(zhtoubiao.getJiaohuoTime());
        // oldt.setShuoming(zhtoubiao.getShuoming());
        // oldt.setChengzhong(zhtoubiao.getChengzhong());
        // oldt.setZongjine(zhtoubiao.getZongjine());
        list = zhtoubiao.getZhtoubiaossList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Zhtoubiao oldtt = (Zhtoubiao) list.get(i);

                Zhtoubiao oldt = zhaobiaoServer
                        .getByIdZhtoubiao(oldtt.getTid());
                oldt.setQiandingTime(zhtoubiao.getQiandingTime());
                oldt.setQiTime(zhtoubiao.getQiTime());
                oldt.setZhongTime(zhtoubiao.getZhongTime());
                oldt.setJiaohuoTime(zhtoubiao.getJiaohuoTime());
                oldt.setShuoming(zhtoubiao.getShuoming());
                oldt.setHeji(zhtoubiao.getHeji());
                // ----------------
                oldt.setChengzhong(oldtt.getChengzhong());
                oldt.setZongjine(oldtt.getZongjine());
                // --------------
                zhaobiaoServer.updatetoubiao(oldt);
            }
        }
        errorMessage = "操作成功";
        url = "zhaobiaoAction!shengchenhetong.action?zhtoubiao.tid="
                + zhtoubiao.getTid();
        return ERROR;
    }

    /***
     * 重新评标
     *
     * @return
     */
    public String chongxinpinbiao() {
        zhaobiaoServer.chongxinpingbiao(zhtoubiao.getTid());
        Zhtoubiao oldt = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao.getTid());
        ZhaobiaoXi oldx = zhaobiaoServer.ByIdzhaobiaoXi(oldt.getTkong10());
        Integer[] userId = zhaobiaoServer.listzhongbiaoY(oldx.getT10());
        AlertMessagesServerImpl.addAlertMessages("招标信息", "最新中标信息公布!!!", userId,
                "zhaobiaoAction!listUserZhaobiao.action", true);
        return "pingbiao";
    }

    /*
     * zhaobiaoServer.pingbiao(zhaobiao.getId()); Integer[] userId =
     * zhaobiaoServer.listzhongbiaoY(zhaobiao.getId());
     * AlertMessagesServerImpl.addAlertMessages("招标信息", "最新中标信息公布!!!", userId,
     * "zhaobiaoAction!listUserZhaobiao.action", true);
     */
	/*
	 * 查看节拍
	 */
    public String chakan() {
        gysjiepai = zhaobiaoServer.ByIdgysjiepai(gysjiepai.getId());
        return "chakan";
    }

    /*
     * gys补充节拍内容
     */
    public String buchongjiepai() {
        gysjiepai.setStatus("完成");
        zhaobiaoServer.updategysjiepai(gysjiepai);
        errorMessage = "完成！！！";
        return "buchongjiepai";
    }

    public String tojiepai() {
        gysjiepai = zhaobiaoServer.ByIdgysjiepai(gysjiepai.getId());
        return "tojiepai";
    }

    public String listtianxiejiepai() {
        if (gysjiepai != null) {
            ActionContext.getContext().getSession().put("gysjiepai", gysjiepai);
        } else {
            gysjiepai = (GysMarkIdjiepai) ActionContext.getContext()
                    .getSession().get("gysjiepai");
        }
        Object[] object = zhaobiaoServer.listtianxiejiepai(gysjiepai, Integer
                .parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<GysmarkIdjiepai>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!listtianxiejiepai.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "listtianxiejiepai";
    }

    /*
     * 供应商登录 填写件号节拍列表
     */
    public String listbandingjianhao() {
        Users user = Util.getLoginUser();
        zhUser = zhaobiaoServer.listByIdZhUser(zhUser.getUserid());
        if (procardTemplate != null) {
            ActionContext.getContext().getSession().put("procardTemplate",
                    procardTemplate);
        } else {
            procardTemplate = (ProcardTemplate) ActionContext.getContext()
                    .getSession().get("procardTemplate");
        }
        Object[] object = zhaobiaoServer.listbandingjianhao(procardTemplate,
                Integer.parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<ProcardTemplate>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!listbandingjianhao.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "listbandingjianhao";
    }

    /***
     * 查询供应商
     */
    public void findZhUser() {
        zhUser = zhaobiaoServer.listByIdZhUser(id);
        MKUtil.writeJSON(zhUser);
    }

    /*
     * 绑定外购件
     */
    public String banding() {
        zhaobiaoServer.banding(selected, zhUser.getId(), null);
        errorMessage = "绑定成功!!!";
        url = "zhaobiaoAction!qubangding.action?zhUser.id=" + zhUser.getId();
        return ERROR;
    }

    /*
     * 绑定外购件件号 前去查询所有的外购件
     */
    public String qubangding() {
        listyusuan = zhaobiaoServer.listyibangdingjianhao(zhUser.getId());

        if (procardTemplate != null) {
            ActionContext.getContext().getSession().put("procardTemplate",
                    procardTemplate);
        } else {
            procardTemplate = (ProcardTemplate) ActionContext.getContext()
                    .getSession().get("procardTemplate");
        }
        Object[] object = zhaobiaoServer.listProcardTemplate(zhUser.getId(),
                procardTemplate, Integer.parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<ProcardTemplate>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!qubangding.action?zhUser.id="
                            + zhUser.getId());
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "listProcardTemplate";
    }

    // ---------
	/*
	 * 入库前 打印入库执行单
	 */
    public String ruku() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        list = zhaobiaoServer.ruku(zhaobiao.getId());
        listyusuan = zhaobiaoServer.listxitoubiao(zhaobiao.getId());
        return "ruku";
    }

    // --------------------------------------------
	/*
	 * 中标信息 查看
	 */
    public String listzhongbiaoUsers() {
        if (zhaobiao != null) {
            ActionContext.getContext().getSession().put("zhaobiao", zhaobiao);
        } else {
            zhaobiao = (Zhaobiao) ActionContext.getContext().getSession().get(
                    "zhaobiao");
        }
        Object[] object = zhaobiaoServer.listzhongbiaoUsers(zhaobiao, Integer
                .parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<Zhaobiao>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!listzhongbiaoUsers.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "listzhongbiaoUsers";
    }

    // ---------------------------------------------------------
    // 打印招标详细信息 招商用户查看
    public String zhtoubiaogyschankan() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        list = zhaobiaoServer.listxitoubiao(zhaobiao.getId());
        return "zhtoubiao_gyschankan";
    }

    /***
     * TODO 自动评标
     *
     * @return
     */
    public String pingbiao() {
        zhaobiaoServer.pingbiao(zhaobiao.getId());
        Integer[] userId = zhaobiaoServer.listzhongbiaoY(zhaobiao.getId());
        AlertMessagesServerImpl.addAlertMessages("招标信息", "最新中标信息公布!!!", userId,
                "zhaobiaoAction!listUserZhaobiao.action", true);
        return "pingbiao";
    }

    // 选择年利率
    public String listlilv() {
        if (nianlilv != null) {
            ActionContext.getContext().getSession().put("nianlilv", nianlilv);
        } else {
            nianlilv = (Nianlilv) ActionContext.getContext().getSession().get(
                    "nianlilv");
        }
        Object[] object = zhaobiaoServer.listlilv(nianlilv, Integer
                .parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<Nianlilv>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!listlilv.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "listlilv";
    }

    public String addnianlilv() {
        Nianlilv newNianlilv = zhaobiaoServer.Bynianfenlilv(nianlilv
                .getNianfen());
        if (newNianlilv != null) {
            newNianlilv.setLilv(nianlilv.getLilv());
            zhaobiaoServer.updatenianlilv(newNianlilv);
            errorMessage = "修改成功！";
        } else {
            zhaobiaoServer.addnianlilv(nianlilv);
            errorMessage = "添加成功！";
        }
        return "addnianlilv";
    }

    public String zhaobiaoXilistlilv() {
        if (zhaobiaoXi != null) {
            ActionContext.getContext().getSession().put("zhaobiaoXi",
                    zhaobiaoXi);
        } else {
            zhaobiaoXi = (ZhaobiaoXi) ActionContext.getContext().getSession()
                    .get("zhaobiaoXi");
        }
        Object[] object = zhaobiaoServer.zhaobiaoXilisthesuan(zhaobiao.getId(),
                zhaobiaoXi, Integer.parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<ZhaobiaoXi>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!zhaobiaoXilistlilv.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "zhaobiaoXilistlilv";
    }

    public String lilv() {
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        zhaobiao = zhaobiaoServer.listById(zhaobiaoXi.getT10());
        return "lilv";
    }

    public String updatezhaobiaoXililv() {
        ZhaobiaoXi newZhaobiaoXi = new ZhaobiaoXi();
        newZhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        newZhaobiaoXi.setLilv(zhaobiaoXi.getLilv());
        zhaobiaoServer.updatezhaobiaoXi(newZhaobiaoXi);
        // 判断zhaobaiao是否有没有未选择年利率的
        list = zhaobiaoServer.listBiIdXiliklvnull(newZhaobiaoXi.getT10());
        if (list == null || list.size() <= 0) {
            zhaobiao = zhaobiaoServer.listById(newZhaobiaoXi.getT10());
            zhaobiao.setStatus("H");
            zhaobiaoServer.update(zhaobiao);
        }
        errorMessage = "操作成功！";
        // url="zhaobiaoAction!lilv.action?zhaobiaoXi.id="+newZhaobiaoXi.getId();
        // return ERROR;
        return "updatezhaobiaoXililv";
    }

    public String addzhaobiaoXidahuito() {
        zhaobiaoXi.setT10(zhaobiao.getId());
        Zhmoban newZhmoban = new Zhmoban();
        newZhmoban.setId(zhaobiaoXi.getT1());
        zhaobiaoXi.setZhmoban(newZhmoban);

        DeptMonthBudget newdemo = new DeptMonthBudget();
        newdemo.setId(zhaobiaoXi.getT9());
        zhaobiaoXi.setDeptMonthBudget(newdemo);
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        String[] nianfen = zhaobiao.getMoban().split("-");
        nianlilv = zhaobiaoServer.Bynianfenlilv(nianfen[0]);
        zhaobiaoXi.setLilv(nianlilv.getLilv());
        zhaobiaoXi.setT7("N");
        zhaobiaoServer.addzhaobiaoXi(zhaobiaoXi);

        zhaobiao = zhaobiaoServer.listById(zhaobiaoXi.getT10());
        zhaobiao.setStatus("W");
        zhaobiaoServer.update(zhaobiao);

        errorMessage = "添加成功！";
        return "addzhaobiaoXidahuito";
    }

    public String addzhaobiaoXidahui() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        return "addzhaobiaoXidahui";
    }

    //
    public String updatezhaobiaoXidahui() {
        ZhaobiaoXi newzXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        newzXi.setT1(zhaobiaoXi.getT1());
        newzXi.setT2(zhaobiaoXi.getT2());
        newzXi.setT3(zhaobiaoXi.getT3());
        newzXi.setT5(zhaobiaoXi.getT5());
        newzXi.setT6(zhaobiaoXi.getT6());
        // newzXi.setT9(zhaobiaoXi.getT9());
        zhaobiaoServer.updatezhaobiaoXi(newzXi);

        zhaobiao = zhaobiaoServer.listById(newzXi.getT10());
        zhaobiao.setStatus("W");
        zhaobiaoServer.update(zhaobiao);
        errorMessage = "修改成功！";
        // url="zhaobiaoAction!updatezhaobiaoXijuti.action?zhaobiaoXi.id="+newzXi.getId();
        return "updatezhaobiaoXidahui";
    }

    public String updatezhaobiaoXijuti() {
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        deptMonthBudget = zhaobiaoServer.byIdDeptMonthBudget(zhaobiaoXi
                .getT10());

        // list=zhaobiaoServer.toaddzhaobiao();
        return "updatezhaobiaoXijuti";
    }

    public void listzhmoban() {
        list = zhaobiaoServer.listzhmoban();
        MKUtil.writeJSON(list);
    }

    public String updatezhaobiaoXi() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        list = zhaobiaoServer.listBiIdXi(zhaobiao.getId());
        return "updatezhaobiaoXi";
    }

    public String deletezhaobiaoXi() {
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        zhaobiaoServer.deletezhaobiaoXi(zhaobiaoXi);
        zhaobiao = zhaobiaoServer.listById(zhaobiaoXi.getT10());
        zhaobiao.setStatus("W");
        zhaobiaoServer.update(zhaobiao);
        errorMessage = "删除成功！";
        url = "zhaobiaoAction!updatezhaobiaoXi.action?zhaobiao.id="
                + zhaobiao.getId();
        return ERROR;
    }

    public String hesuanjiage() {
        Zhtoubiao newzZhtoubiao = new Zhtoubiao();
        newzZhtoubiao = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao.getTid());
        newzZhtoubiao.setShijidaohuo(zhtoubiao.getShijidaohuo());
        newzZhtoubiao.setZhouqi(zhtoubiao.getZhouqi());
        newzZhtoubiao.setDaohuoriqi(zhtoubiao.getDaohuoriqi());
        newzZhtoubiao.setHuanhuozhouqi(zhtoubiao.getHuanhuozhouqi());
        newzZhtoubiao.setJiabanfei(zhtoubiao.getJiabanfei());
        // =D4+G8*D4*G6*P4-(H8*D4*H6*P4+I8*D4*I6*P4+J8*D4*J6*P4)+
        // (K4-E4)*D4*L4*P4+
        // "实际到货数量(kg)" "  订单需求数量（kg/件)" "报价（元/kg/件)" "多余货物消耗周期" 天利率
        // D4*P4*N4+
        // 报价（元/kg/件) 天利率 "质量问题换货周期"
        // D4*(M4-F4)*P4+O4/E4
        // 报价（元/kg/件) "实际到货日" "订单需求到货日" 天利率 加班费用(元） "订单需求数量（kg/件)"
        // =D4+G8*D4*G6*P4-(H8*D4*H6*P4+I8*D4*I6*P4+J8*D4*J6*P4)+(K4-E4)*D4*L4*P4+D4*P4*N4+D4*(M4-F4)*P4+O4/E4
        // =D4+G8*D4*G6*P4-(H8*D4*H6*P4+I8*D4*I6*P4+J8*D4*J6*P4)
        float hesuan = Float.valueOf(newzZhtoubiao.getTkong1())
                + newzZhtoubiao.getZhouqikuan()
                * newzZhtoubiao.getShoubikuan()
                * Float.valueOf(newzZhtoubiao.getTkong1())
                * newzZhtoubiao.getLilv()
                / 365F
                - (newzZhtoubiao.getZhouqihuo() * newzZhtoubiao.getShoubihuo()
                * Float.valueOf(newzZhtoubiao.getTkong1())
                * newzZhtoubiao.getLilv() / 365F
                + newzZhtoubiao.getZhouqier()
                * newzZhtoubiao.getErbihuo()
                * Float.valueOf(newzZhtoubiao.getTkong1())
                * newzZhtoubiao.getLilv() / 365F + newzZhtoubiao
                .getMobihuo()
                * newzZhtoubiao.getMobiuo()
                * Float.valueOf(newzZhtoubiao.getTkong1())
                * newzZhtoubiao.getLilv() / 365F)
                + (newzZhtoubiao.getShijidaohuo() - Float
                .parseFloat(newzZhtoubiao.getXuqiushuliang()))
                * newzZhtoubiao.getTkong1()
                * newzZhtoubiao.getZhouqi()
                * newzZhtoubiao.getLilv()
                / 365F
                + newzZhtoubiao.getTkong1()
                * newzZhtoubiao.getLilv()
                / 365F
                * zhtoubiao.getHuanhuozhouqi()
                + newzZhtoubiao.getTkong1()
                * (newzZhtoubiao.getDaohuoriqi() - Float
                .parseFloat(newzZhtoubiao.getTtime()))
                * newzZhtoubiao.getLilv() / 365F + newzZhtoubiao.getJiabanfei()
                / Float.parseFloat(newzZhtoubiao.getXuqiushuliang());

        // --------------
        newzZhtoubiao.setHesuanjiage(Float.parseFloat(String.format("%.2f",
                hesuan)));
        // 结算比例
        newzZhtoubiao.setBili(newzZhtoubiao.getHesuanjiage()
                / Float.valueOf(newzZhtoubiao.getTkong1()));
        zhaobiaoServer.updatezhtoubiao(newzZhtoubiao);
        zhUser = zhaobiaoServer.byzhUserName(newzZhtoubiao.getTname());
        // =Q4/D4
        // if (zhUser.getBili() == null) {
        //
        // float bili=newzZhtoubiao.getHesuanjiage()/ newzZhtoubiao.getTkong1();
        // zhUser.setBili(Float.parseFloat(String.format("%.2f", bili)));
        // zhaobiaoServer.updatezhUser(zhUser);
        // }
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        return "hesuanjiage";
    }

    public String zhtoubiaoByID() {
        zhtoubiao = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao.getTid());
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhtoubiao.getTkong10());

        zhaobiao = zhaobiaoServer.listById(zhaobiaoXi.getT10());
        return "hesuan";
    }

    public String toUpdatehuikuang() {
        huikuang = zhaobiaoServer.BuIdhuikuang(huikuang.getHid());
        return "toUpdatehuikuang";
    }

    public String Updatehuikuang() {
        zhaobiaoServer.Updatehuikuang(huikuang);
        return "Updatehuikuang";
    }

    public String deletehuikuang() {
        zhaobiaoServer.deletehuikuang(huikuang);
        return "deletehuikuang";
    }

    public String zhtoubiaolist() {
        if (zhtoubiao != null) {
            ActionContext.getContext().getSession().put("zhtoubiao", zhtoubiao);
        } else {
            zhtoubiao = (Zhtoubiao) ActionContext.getContext().getSession()
                    .get("zhtoubiao");
        }
        Object[] object = zhaobiaoServer.zhtoubiaolisthesuan(
                zhaobiaoXi.getId(), zhtoubiao, Integer.parseInt(cpage),
                pageSize);

        if (object != null && object.length > 0) {
            list = (List<Zhtoubiao>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!zhtoubiaolisthesuan.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "zhtoubiaolist";
    }

    public String zhaobiaoXilist() {
        if (zhaobiaoXi != null) {
            ActionContext.getContext().getSession().put("zhaobiaoXi",
                    zhaobiaoXi);
        } else {
            zhaobiaoXi = (ZhaobiaoXi) ActionContext.getContext().getSession()
                    .get("zhaobiaoXi");
        }
        Object[] object = zhaobiaoServer.zhaobiaoXilisthesuan(zhaobiao.getId(),
                zhaobiaoXi, Integer.parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<ZhaobiaoXi>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!zhaobiaoXilisthesuan.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "zhaobiaoXilist";
    }

    // 后期成本核算
    public String hesuan() {
        if (zhaobiao != null) {
            ActionContext.getContext().getSession().put("zhaobiao", zhaobiao);
        } else {
            zhaobiao = (Zhaobiao) ActionContext.getContext().getSession().get(
                    "zhaobiao");
        }
        Object[] object = zhaobiaoServer.hesuan(zhaobiao, Integer
                .parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<Zhaobiao>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!hesuan.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "hesuanzhaobiao";
    }

    // 修改招标
    public String toUpdatezhaobiao() {
        zhaobiao = zhaobiaoServer.toUpdatezhaobiao(zhaobiao.getId());
        return "toUpdatezhaobiao";
    }

    //
    public String listgongxu() {
        if (zhgongxu != null) {
            ActionContext.getContext().getSession().put("zhgongxu", zhgongxu);
        } else {
            zhgongxu = (Zhgongxu) ActionContext.getContext().getSession().get(
                    "zhgongxu");
        }
        Object[] object = zhaobiaoServer.listgongxu(zhgongxu, Integer
                .parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<Zhgongxu>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!listgongxu.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "listgongxu";
    }

    public String addZhgongxu() {
        Users user = Util.getLoginUser();
        zhgongxu.setPerson(user.getName());
        if (zhgongxu.getJiage() != null) {
            // 含税假 计算不含税价
            Float buhanshui = null;
            buhanshui = zhgongxu.getJiage()
                    / (1 + (Float.parseFloat(zhgongxu.getKongxian())));
            zhgongxu.setBuhanshui(buhanshui);
        }
        if (zhgongxu.getBuhanshui() != null) {
            // 不含税假 计算含税价
            Float hanshui = null;
            hanshui = zhgongxu.getBuhanshui()
                    * (1 + (Float.parseFloat(zhgongxu.getKongxian())));
            zhgongxu.setJiage(hanshui);
        }

        zhaobiaoServer.addZhgongxu(zhgongxu);
        return "addZhgongxu";
    }

    public String toUpdatezhgongxu() {
        zhgongxu = zhaobiaoServer.ZhgongXuByID(zhgongxu.getId());
        return "toUpdatezhgongxu";
    }

    public String Updatezhgongxu() {
        zhaobiaoServer.Updatezhgongxu(zhgongxu);
        return "Updatezhgongxu";
    }

    public String deletezhgongxu() {
        zhaobiaoServer.deletezhgongxu(zhgongxu);
        return "deletezhgongxu";
    }

    public String updatezhaobiao() {
        zhaobiaoServer.updatezhaobiao(zhaobiao);
        errorMessage = "修改成功！";
        return "updatezhaobiao";
    }

    // 查看审批历史记录
    public String chankanshenpijilu() {
        Object[] object = zhaobiaoServer.chankanshenpijilu(zhaobiao.getId(),
                Integer.parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!chankanshenpijilu.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "chankanshenpijilu";
    }

    // 5.15查看投标记录
    public String chankantoubiaoXi() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        Object[] object = zhaobiaoServer.qupingxuan(zhaobiao.getId(), Integer
                .parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!qupingxuan.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }

        return "chankantoubiaoXi";
    }

    public String chakantoubiaojilu() {
        Object[] object = zhaobiaoServer.chakantoubiaojilu(zhaobiaoXi.getId(),
                Integer.parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!chakantoubiaojilu.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }

        return "chakantoubiaojilu";
    }

    // 回款方式管理
    public String listhuikuanfangshi() {
        Object[] object = zhaobiaoServer.listhuikuanfangshi(Integer
                .parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listhuikuanfangshi.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listhuikuanfangshi";
    }

    public String addhuikuan() {
        zhaobiaoServer.addhuikuan(huikuang);
        return "addhuikuan";
    }

    // 查看金额
    public String chakanjine() {
        list = zhaobiaoServer.chakanjineByX(zhtoubiao.getTid());
        zhtoubiao = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao.getTid());
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhtoubiao.getTkong10());
        return "chakanjine";
    }

    // 去选择回款方式
    public String toxuanzhe() {
        list = zhaobiaoServer.toaddhuikuangxiangxi(zhaobiaoXi.getId());
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        return "toxuanzhe";
    }

    /*
     * TODO确定回款方式
     */
    //
    public String quedinghuikuanfangshi() {
        list = zhaobiaoServer.quedinghuikuanfangshi(zhaobiao.getId());
        if (list == null || list.size() <= 0) {
            Zhaobiao zhaobiao3 = new Zhaobiao();
            zhaobiao3 = zhaobiaoServer.listById(zhaobiao.getId());
            zhaobiao3.setStatus("C");
            zhaobiaoServer.updatezhaobiao(zhaobiao3);
            errorMessage = "回款方式确定成功";

            url = "zhaobiaoAction!listzhaobiao.action";
        } else {
            errorMessage = "还有未确定回款方式的项目~";
            url = "zhaobiaoAction!listzhaobiao.action";
        }
        return ERROR;
    }

    // 添加回款方式
    public String fangshi() {
        zhaobiaoServer.addHuiXi(huiXi);
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(huiXi.getXid());
        return "fangshi";
    }

    // 去选择回款方式
    public String tofangshi() {
        Hui_Xi huiXi1 = new Hui_Xi();
        huiXi1 = zhaobiaoServer.listHuiXiByXid(zhaobiaoXi.getId());
        listhuikuang = zhaobiaoServer.listhuikuang();
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        if (huiXi1 == null) {
            return "tofangshi";
            // return url="";
        } else {
            errorMessage = "已经初步拟定过回款方式     此处执行会对初步拟定过回款方式进行修改！";
            zhaobiaoServer.delHuiXi(huiXi1);
            // listhuikuang=zhaobiaoServer.listhuikuang();
            // zhaobiaoXi=zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
            // url="System/caigou/zhaobiao/tofangshi.jsp";
            url = "zhaobiaoAction!tofangshi.action?zhaobiaoXi.id="
                    + zhaobiaoXi.getId();
        }
        return ERROR;
    }

    // 发布之前选择回款方式
    public String huikuangfangshi() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        list = zhaobiaoServer.listxitoubiao(zhaobiao.getId());
        listAllDept = zhaobiaoServer.listhuikuan();
        // listhuikuang=zhaobiaoServer.listhuikuang();
        return "huikuangfangshi";
    }

    // 打印招标详细信息
    public String dayinzhongbiaoxinxi() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        list = zhaobiaoServer.listxitoubiao(zhaobiao.getId());
        return "dayinzhongbiaoxinxi";
    }

    // 提交回款方式结果
    public String tijiaohuikuangjieguo() {
        list = zhaobiaoServer.tijiaohuikuangjieguo(zhaobiao.getId());
        if (list == null || list.size() <= 0) {
            Zhaobiao zhaobiao3 = new Zhaobiao();
            zhaobiao3 = zhaobiaoServer.listById(zhaobiao.getId());
            zhaobiao3.setStatus("G");
            zhaobiaoServer.updatezhaobiao(zhaobiao3);
            errorMessage = "提交成功";
            url = "zhaobiaoAction!listcaiwu.action";
        } else {
            errorMessage = "还有未选择回款方式的材料~";
            url = "zhaobiaoAction!listcaiwu.action";
        }
        return ERROR;
    }

    // 确定回款方式
    public String quedinghuikuang() {
        // ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ZhaobiaoXi zhaobiaoXi2 = new ZhaobiaoXi();
        zhaobiaoXi2 = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        zhaobiaoXi2.setT11(zhaobiaoXi.getT11());

        // zhaobiaoXi2.setT11(zhaobiaoXi.getT11());
        zhaobiaoServer.updatezhaobiaoXi(zhaobiaoXi2);
		/*
		 * TODO 超出预算的直接忽略
		 */
        // 超出预算的直接忽略
        zhaobiaoServer.listToubiaoId(zhaobiaoXi.getId());

        zhaobiaoXi = zhaobiaoXi2;
        // 自动提交回款方式
        list = zhaobiaoServer.tijiaohuikuangjieguo(zhaobiaoXi.getT10());
        if (list == null || list.size() <= 0) {

            Zhaobiao zhaobiao3 = new Zhaobiao();
            zhaobiao3 = zhaobiaoServer.listById(zhaobiaoXi.getT10());
            zhaobiao3.setStatus("G");
            zhaobiaoServer.updatezhaobiao(zhaobiao3);
        }
        return "quedinghuikuang";
    }

    // 回款方式
    public void listhuikuang() {
        list = zhaobiaoServer.listhuikuang();
        MKUtil.writeJSON(list);
    }

	/*
	 * 财务 查看版聊
	 */

    public String listcaiwutotoubiao() {
        Object[] object = zhaobiaoServer.qupingxuan(zhaobiao.getId(), Integer
                .parseInt(cpage), pageSize);
        listAllDept = zhaobiaoServer.listhuikuang();
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!qupingxuan.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }

        return "listcaiwutotoubiao";
    }

    /*
     *
     * 财务选择回款方式
     */
    public String listcaiwu() {
        Object[] object = zhaobiaoServer.listcaiwu(Integer.parseInt(cpage),
                pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listcaiwu.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }

        return "listcaiwu";
    }

    /*
     * /* 提交至财务 财务选择回款方式
     */
    public String tohuikuang() {
        Zhaobiao zhaobiao3 = new Zhaobiao();
        zhaobiao3 = zhaobiaoServer.listById(zhaobiao.getId());
        zhaobiao3.setStatus("F");
        zhaobiaoServer.updatezhaobiao(zhaobiao3);
        return "tohuikuang";
    }

    /*
     * 添加板料完成后用于提交招标
     */
    public String tijiaozhaobiao() {
        Integer epId;
        String processName = "招标信息发布审核";
        try {
            Zhaobiao zhaobiao2 = zhaobiaoServer.listById(zhaobiao.getId());
            epId = CircuitRunServerImpl.createProcess(processName,
                    Zhaobiao.class, zhaobiao2.getId(), "t2", "id", "招标信息发布审核!",
                    true, null);
            // epId = CircuitRunServerImpl.createProcess(227, Zhaobiao.class,
            // zhaobiao2.getId(), "t2", "id", "招标信息发布审核", true, null);
            if (epId != null && epId > 0) {
                zhaobiao2.setStatus("审核中");
                zhaobiao2.setEpId(epId);
                zhaobiaoServer.updatezhaobiao(zhaobiao2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 提交合同报告 zhaobiao.id
    public String tijiaohetong() {
        List list = (List) zhaobiaoServer.listzhtoubiaoY(zhaobiao.getId());
        for (int i = 0; i < list.size(); i++) {
            // zhtoubiao=(Zhtoubiao) list.get(i);

            System.err.println("-----------------------" + list.get(i));
            Zhtoubiao zhtoubiao2 = new Zhtoubiao();
            zhtoubiao2.setTid(Integer.valueOf(String.valueOf(list.get(i)))
                    .intValue());
            Zhtoubiao zhtoubiao1 = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao2
                    .getTid());
            // 触发审批 12
            Integer epId = 0;
            String processName = "";
            try {
                processName = "合同下发审核";
                // epId = CircuitRunServerImpl.createProcess(processName,
                // Zhtoubiao.class,zhtoubiao1.getTid(), "tkong7",
                // "tid","合同下发审核", true,null);

                // epId = CircuitRunServerImpl.createProcess(225,
                // Zhtoubiao.class,
                // zhtoubiao1.getTid(), "tkong7", "tid", "合同下发审核", true,
                // null);
                if (epId != null && epId > 0) {
                    zhtoubiao1.setTkong7("同意");
                    // zhtoubiao1.setEpId(epId);
                    zhaobiaoServer.updatetoubiao(zhtoubiao1);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        Zhaobiao zhaobiao2 = zhaobiaoServer.listById(zhaobiao.getId());
        zhaobiao2.setStatus("完成");
        zhaobiaoServer.update(zhaobiao2);
        return "tijiaohetong";
    }

    // 生成合同
    public String shengchenhetong() {
        zhtoubiao = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao.getTid());
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhtoubiao.getTkong10());
        zhaobiao = zhaobiaoServer.listById(zhaobiaoXi.getT10());
        zhUser = zhaobiaoServer.byzhUserName(zhtoubiao.getTname());
        // list = zhaobiaoServer.chakanjineByX(zhtoubiao.getTid());
        list = zhaobiaoServer.ByToubiaohetong(zhaobiao.getNumbers(), zhUser
                .getId());
        return "shengchenhetong";
    }

    // 去查看合同
    public String tochakanhetong() {
        list = zhaobiaoServer.tochakanhetong(zhaobiao.getId());
        return "tochakanhetong";
    }

    // 查看明细
    public String mingxi() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        list = zhaobiaoServer.listxitoubiao(zhaobiao.getId());
        listAllDept = zhaobiaoServer.listAllzhongbiao();
        return "mingxi";
    }

    // 项目完成之后提交至上级审核
    public String shenhezhaobiao() {
        Integer epId;
        try {
            Zhaobiao zhaobiao2 = zhaobiaoServer.listById(zhaobiao.getId());
            // epId = CircuitRunServerImpl.createProcess(224, Zhaobiao.class,
            // zhaobiao2.getId(), "status", "id",
            // "zhaobiaoAction!mingxi.action?zhaobiao.id="
            // + zhaobiao.getId(), "中标信息上报审核", true, null);
            // if (epId != null && epId > 0) {
            zhaobiao2.setStatus("完成");
            // zhaobiao2.setEpId(epId);
            zhaobiaoServer.updatezhaobiao(zhaobiao2);
            // }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "shenhezhaobiao";
    }

    // 重新评标
    public String chongxin() {
        zhaobiaoServer.chongxin(flowdetail, zhaobiaoXi.getId(), shenhefujian,
                shenhefujianFileName);

        ZhaobiaoXi zhaobiaoXi1 = new ZhaobiaoXi();
        zhaobiaoXi1 = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        zhaobiaoXi1.setT7("N");
        zhaobiaoServer.updatezhaobiaoXi(zhaobiaoXi1);

        Zhtoubiao zhtoubiao1 = new Zhtoubiao();
        zhtoubiao1 = zhaobiaoServer.ByXiBY(zhaobiaoXi.getId());
        zhtoubiao1.setTkong7("S");
        zhaobiaoServer.updatetoubiao(zhtoubiao1);
        zhaobiaoXi1 = zhaobiaoXi;

        return "chongxin";
    }

    // 显示投标用户
    public void listtoubiaoyonghu() {
        list = zhaobiaoServer.listBymobanName(pagemoban);
        MKUtil.writeJSON(list);
    }

    /***
     * 列表用户
     *
     * @return
     */
    //
    public String listAll() {
        // /---------------------------
        if (zhUser != null) {
            ActionContext.getContext().getSession().put("zhUser", zhUser);
        } else {
            zhUser = (ZhUser) ActionContext.getContext().getSession().get(
                    "zhUser");
        }
        Object[] object = zhaobiaoServer.findAll(zhUser, Integer
                .parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List<ZhUser>) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listAll.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listAll";// zhaobiao/list.jsp
    }

    public String uploadMeetingRecord() {
            if (attachment != null && attachment.length > 0) {
                String filename = MKUtil.saveFile(attachment, attachmentFileName,
                        "MeetingRecord");
                zhUser=zhaobiaoServer.listByIdZhUser(id);
                zhUser.setMeetingRecord(filename);
            }
            errorMessage =zhaobiaoServer.updatezhUser(zhUser);
            if ("true".equals(errorMessage))
                errorMessage="上传成功";
                url = "zhaobiaoAction!listAll.action";
            return "error";
    }

    public String MeetingRecord() {
        if (zhUser == null) {
            zhUser = new ZhUser();
        }
        return "meetingrecord";
    }

    public String serachZhUserNameAndId() throws UnsupportedEncodingException {
        String name = URLDecoder.decode(zhUser.getCmp().trim(), "UTF-8");zhUser.setCmp(name);Object zh[]=zhaobiaoServer.serachZhUserNameAndId(zhUser);
        List list= (List) zh[0];
        class Supplier {
            public String suppliername;
            public int supplierid;
        }

        List<Supplier> suppliers = new ArrayList<Supplier>();
        for(int i=0;i<list.size();i++){
            Supplier supplier=new Supplier();
            Object[] l= (Object[]) list.get(i);
            supplier.supplierid=(Integer) l[0];
            supplier.suppliername=(String) l[1];
            suppliers.add(supplier);
        }
        MKUtil.writeJSON(suppliers);
        return  null;
    }


    // -----------------------------------------
	/*
	 * 招标信息
	 */
    public String listzhaobiao() {
        if (zhaobiao != null) {
            ActionContext.getContext().getSession().put("zhaobiao", zhaobiao);
        } else {
            zhaobiao = (Zhaobiao) ActionContext.getContext().getSession().get(
                    "zhaobiao");
        }
        Object[] object = zhaobiaoServer.listzhaobiao1(zhaobiao, Integer
                .parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List<Zhaobiao>) object[0];
            if (object != null && object.length > 0) {
                list = (List) object[0];
                if (list != null && list.size() > 0) {
                    int count = (Integer) object[1];
                    int pageCount = (count + pageSize - 1) / pageSize;
                    this.setTotal(pageCount + "");
                    this.setUrl("zhaobiaoAction!listzhaobiao.action");
                    errorMessage = null;
                } else {
                    errorMessage = "没有找到你要查询的内容,请检查后重试!";
                }
            }
        }
        return "listzhaobiao";
    }

    public String toaddUser() {
        if("是".equals(zhaobiaoServer.yanzhengys())){
        	errorMessage ="未进行供应商认证,不能添加供应商";
        	return ERROR;
        }else{
	        supplierCertification=supplierCertificationService.findSupplierCetification(id);
	        list = zhaobiaoServer.listUser();
	        // cylist = zhaobiaoServer.findCategoryByType("物料");
	        return "toaddUser";// addUser.jsp
        }
    }

    // 用户详细
    public String listByIdZhUser() {
        zhUser = zhaobiaoServer.listByIdZhUser(zhUser.getId());
        return "listByIdZhUser";// zhaobiao/listbyid.jsp
    }

    public String laheizhUser() {
        ZhUser zhUser2 = zhaobiaoServer.listByIdZhUser(zhUser.getId());
        zhUser2.setBlackliststauts("拉黑");
        zhaobiaoServer.updatezhUser(zhUser2);
        return "laheizhUser";
    }

    public String addUser() {
        // Users user = Util.getLoginUser();
        // zhUser.setUid(zhUser.getUserid().toString());
        errorMessage = zhaobiaoServer.addUser(zhUser);
        if ("添加成功！".equals(errorMessage)) {
            url = "zhaobiaoAction!listAll.action";
        }
        return "error";//zhaobiaoAction!listAll.action
    }

    public void deletezhUser() {
        errorMessage = zhaobiaoServer.deletezhUser(zhUser);
        MKUtil.writeJSON(errorMessage);
//		return ;
    }

    public String toUpdatezhUser() {
        zhUser = zhaobiaoServer.listByIdZhUser(zhUser.getId());
        // cylist = zhaobiaoServer.findCategoryByType("物料");
        return "toUpdatezhUser";
    }

    public String updatezhUser() {
        // ZhUser zhUser1=zhaobiaoServer.listByIdZhUser(zhUser.getId());
        errorMessage = zhaobiaoServer.updatezhUser(zhUser);
        if ("true".equals(errorMessage)) {
            errorMessage = "修改成功";
            url = "zhaobiaoAction!listAll.action";
        }
        return "error";//zhaobiaoAction!listAll.action
    }

    /*
     * 招标模版
     */
    public String listZhmoban() {
        Object[] object = zhaobiaoServer.listmoban(Integer.parseInt(cpage),
                pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listZhmoban.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }

        return "listZhmoban";
    }

    public String toUpdateMoban() {
        zhmoban = zhaobiaoServer.ByIdZhmoban(zhmoban.getId());
        return "toUpdateMoban";
    }

    public String updatezhmoban() {
        zhaobiaoServer.updateZhmoban(zhmoban);
        return "updatezhmoban";
    }

    public String addmoban() {
        zhaobiaoServer.addMoban(zhmoban);
        return "addmoban";
    }

    public String deleteMoban() {
        list = zhaobiaoServer.ByZhmobanId(zhmoban.getId());
        if (list != null && list.size() > 0) {
            errorMessage = "此模版正在使用中不能删除!";
            url = "zhaobiaoAction!listZhmoban.action";
        } else {
            zhaobiaoServer.deleteMoban(zhmoban);

            return "deleteMoban";
        }
        return ERROR;
    }

    public String toaddzhaobiao() {
        // List listDept=zhaobiaoServer.listDept();
        // listyusuan=zhaobiaoServer.listyusuan(deptMonthBudget);
        // list=zhaobiaoServer.toaddzhaobiao();
        return "toaddzhaobiao";
    }

    public String addzhaobiao() {
        Users user = Util.getLoginUser();
        zhaobiao.setFaburen(user.getName());
        zhaobiao.setFabushijian(Util.getDateTime());
        zhaobiao.setStatus("E");
        zhaobiao.setNumbers("SHLD-Y-" + Util.getDateTime("yyyy-MM-dd"));
        zhaobiaoServer.addzhaobiao(zhaobiao);
        errorMessage = "添加成功！";
        return "addzhaobiao";
    }

    /*
     * TODO
     */
    public String listById() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        list = zhaobiaoServer.toaddzhaobiao();
        listyusuan = zhaobiaoServer.listyusuan(deptMonthBudget);
        return "listById";
    }

    /*
     * 添加招标下的
     */
    private String fatherPartNumber = "";

    public String addxi() {
        // 与预算挂钩
        // DeptMonthBudget deptMonthBudget1 =
        // zhaobiaoServer.listByIdDeptMonthBudget(deptMonthBudget.getId());
        // deptMonthBudget1.setMore(deptMonthBudget.getMore());
        // deptMonthBudget1.setBudgetStyle("预算");
        // zhaobiaoServer.updateDeptMonthBudget(deptMonthBudget1);

        // 变招标为待审核 调用审批流程
        if (zhaobiaoXis == null) {
            errorMessage = "招标明细为空";
            return "error";
        }
        if (zhaobiao != null && zhaobiao.getId() != null
                && zhaobiao.getId() > 0) {
            zhaobiaoServer.addxi(zhaobiaoXis, zhaobiao.getId());// 添加明细
            errorMessage = zhaobiaoServer.addShenpi(zhaobiao.getId());
            if ("true".equals(errorMessage))
                return "addxi";
        }
        return "error";
    }

    //
    public void findAllDept1() {
        listyusuan = zhaobiaoServer.findAllDept();
        MKUtil.writeJSON(listyusuan);
    }

    //
    public void listDept() {
        list = zhaobiaoServer.listDept(pageStatus);
        MKUtil.writeJSON(list);
    }

    public void listMoth() {
        list = zhaobiaoServer.listMoth(pageStatus, pagename);
        MKUtil.writeJSON(list);
    }

    /*
     */
    public String listshenhe() {
        Object[] object = zhaobiaoServer.listshenhe(Integer.parseInt(cpage),
                pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listshenhe.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listshenhe";
    }

    public void listXitoubiao() {
        list = zhaobiaoServer.listXitoubiao(zhaobiaoXi.getId());
        List newList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Zhtoubiao zhtoubiao = (Zhtoubiao) list.get(i);
            zhtoubiao.setZhaobiaoXi(null);
            zhtoubiao.setZhUser(null);
            newList.add(zhtoubiao);
        }
        MKUtil.writeJSON(newList);
    }

    private List listmoban;
    private List listAllDept;

    public String shenhe() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        list = zhaobiaoServer.listBiIdXi(zhaobiao.getId());
        // listmoban=zhaobiaoServer.toaddzhaobiao();
        // listAllDept=zhaobiaoServer.listAllDeptMonthBudget();
        // System.err.println(list.size()+"    "+listmoban.size()+"   "+listAllDept.size()+".....................审核记录前查询.................");
        return "shenhe";
    }

    public String addshenhe() {
        Zhaobiao zhaobiaoshenhe = new Zhaobiao();
        zhaobiaoshenhe = zhaobiaoServer.listById(zhaobiao.getId());
        zhaobiaoshenhe.setStatus(zhaobiao.getStatus());
        zhaobiaoshenhe.setT1(zhaobiao.getT1());
        zhaobiaoServer.updatezhaobiao(zhaobiaoshenhe);

        Zhaobiao zhaobiao2 = zhaobiaoServer.listById(zhaobiao.getId());
        Flowdetail flowdetail = new Flowdetail();
        flowdetail.setTitle("招商项目   一级审核");
        flowdetail.setSqdid(zhaobiao2.getId());
        flowdetail.setSqtype("招标审核");
        flowdetail.setSphj(zhaobiao2.getStatus());

        // flowdetail.setSqtype(zhaobiao2.getStatus());
        // flowdetail.setSphj("");
        // flowdetail.setBeginrq(Util.getDateTime());
        Users user = Util.getLoginUser();
        flowdetail.setSpr(user.getName());
        flowdetail.setSpyj(zhaobiao2.getT1());
        flowdetail.setSprq(Util.getDateTime());

        zhaobiaoServer.savaflow(flowdetail);
        return "addshenhe";
    }

    // 查看审批历史记录
    public String chakanflowdetail() {
        list = zhaobiaoServer.chakanflowdetail(zhaobiao.getId());
        return "chakanflowdetail";
    }

    public String deletezhaobiao() {
        zhaobiaoServer.deletezhaobiao(zhaobiao);
        errorMessage = "删除成功";
        url = "zhaobiaoAction!listzhaobiao.action";
        return ERROR;

    }

    /***
     * 发布招标信息
     *
     * @return
     */
    public String fabu() {
        Zhaobiao fabu = new Zhaobiao();
        fabu = zhaobiaoServer.listById(zhaobiao.getId());
        fabu.setStatus("D");
        zhaobiaoServer.update(fabu);
        errorMessage = "发布成功";

        // Integer[] userId = null;
        // list = zhaobiaoServer.listfabuzhongbiao();
        // for (int i = 0; i < list.size(); i++) {
        // userId[i] = Integer.parseInt(list.get(i).toString());
        // }
        // AlertMessagesServerImpl.addAlertMessages("招标信息", "最新招标信息公布!!!",
        // userId,
        // "zhaobiaoAction!listzhongbiao.action", false);

        // 给相对应的类别的用户发布信息
        Integer[] userId = zhaobiaoServer.listfabuxinxi(fabu);
        // Integer[] userId = new Integer[list.size()];
        //
        // for (int i = 0; i < list.size(); i++) {
        // if (list.get(i) != null) {
        // userId[i] = Integer.parseInt(list.get(i).toString());
        // }
        // }

        AlertMessagesServerImpl.addAlertMessages("招标信息", "最新招标信息公布!!!", userId,
                "zhaobiaoAction!listUserZhaobiao.action", true);
        url = "zhaobiaoAction!listzhaobiao.action";
        return ERROR;
    }

    /*
     * TODO 添加板料前
     */
    private List listhuikuang;

    public String xianshizhaoshangyonghu() {
        zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
        listhuikuang = zhaobiaoServer.listhuikuang();
        list = zhaobiaoServer.xianshizhaoshangyonghu(zhaobiao.getId());
        return "xianshizhaoshangyonghu";
    }

    public String jieshu() {
        // Zhaobiao jieshu = new Zhaobiao();
        // jieshu = zhaobiaoServer.listById(zhaobiao.getId());
        // //查看财务是否选择年利率
        // list=zhaobiaoServer.listBiIdXiliklv(jieshu.getId());
        // if (list.size()>0) {
        // errorMessage = "还有未选择年利率的招标项目！";
        // }else {
        // jieshu.setStatus("S");
        // zhaobiaoServer.update(jieshu);
        // }
        //
        // url="zhaobiaoAction!listzhaobiao.action";
        // //return "jieshu";
        // return ERROR;
        Zhaobiao jieshu = new Zhaobiao();
        jieshu = zhaobiaoServer.listById(zhaobiao.getId());
        jieshu.setStatus("S");
        zhaobiaoServer.update(jieshu);
        return "jieshu";
    }

    /*
     * 招标信息（招商用户）
     */
    public String listUserZhaobiao() {
        Object[] object = zhaobiaoServer.listUserZhaobiao(Integer
                .parseInt(cpage), pageSize);

        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listUserZhaobiao.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listUserZhaobiao";
    }

    //
    public String listtotoubiao() {
        Users user = Util.getLoginUser();//
        if ("供应商".equals(user.getDept())) {
            zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
            list = zhaobiaoServer.listtotoubiao(zhaobiao.getId());
            // url="/System/caigou/zhaobiao/listtoubiao.jsp";
            return "listtoubiao";
        } else {
            errorMessage = "次为投标界面.您无法查看！！！";
            url = "zhaobiaoAction!listUserZhaobiao.action";
        }
        return ERROR;
    }

	/*
	 * TODO
	 */

    public String qutoubiao() {
        Users user = Util.getLoginUser();
        Zhtoubiao zhtoubiao1 = zhaobiaoServer.getXiByName(zhaobiaoXi.getId());
        String re = null;
        if (zhtoubiao1 == null) {
            zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
            // list = zhaobiaoServer.toaddhuikuangxiangxi(zhaobiaoXi.getId());
            zhUser = zhaobiaoServer.listByUserIDZhUser(user.getId());
            zhaobiao = zhaobiaoServer.listById(zhaobiaoXi.getT10());
            return "qutoubiao";
        } else {
            errorMessage = "你已参与过次项目的招标！！你可以所投的项目进行修改！！！";
            zhtoubiao = zhaobiaoServer.getXiByName(zhaobiaoXi.getId());

            url = "zhaobiaoAction!toupdatetoubiao.action?zhtoubiao.tid="
                    + zhtoubiao1.getTid();
            return ERROR;
        }
        // zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhaobiaoXi.getId());
        // return "qutoubiao";
    }

    public String addtoubiao() {
        Users user = Util.getLoginUser();
        zhUser = zhaobiaoServer.listByUidZhUser(user.getId());
        zhtoubiao.setShoubikuan(zhtoubiao.getShoubikuan() / 100);
        zhtoubiao.setShoubihuo(zhtoubiao.getShoubihuo() / 100);
        zhtoubiao.setErbihuo(zhtoubiao.getErbihuo() / 100);
        zhtoubiao.setMobihuo(zhtoubiao.getMobihuo() / 100);
        // 前期计算价格
        List listzhongbiao = zhaobiaoServer.listzhongbiaosize(zhUser.getName());
        if (listzhongbiao == null || listzhongbiao.size() <= 0) {
            // 未中标过 评标价格计算
            // =D4+ G8*D4*G6*P4-(H8*D4*H6*P4 +I8*D4*I6*P4+ J8*D4*J6*P4)
            // D4 报价
            // G8*D4*G6*P4 款到后到货周期*报价*首笔比例*天利率
            // H8*D4*H6*P4 到货后周期I* 报价*首笔*天利率
            float qianqihesuan = (Float.valueOf(zhtoubiao.getTkong1())
                    + zhtoubiao.getZhouqikuan() * zhtoubiao.getShoubikuan()
                    * Float.valueOf(zhtoubiao.getTkong1())
                    * zhtoubiao.getLilv() / 365F - (zhtoubiao.getZhouqihuo()
                    * zhtoubiao.getShoubihuo()
                    * Float.valueOf(zhtoubiao.getTkong1())
                    * zhtoubiao.getLilv() / 365F + zhtoubiao.getZhouqier()
                    * zhtoubiao.getErbihuo()
                    * Float.valueOf(zhtoubiao.getTkong1())
                    * zhtoubiao.getLilv() / 365F + zhtoubiao.getMobihuo()
                    * zhtoubiao.getMobiuo()
                    * Float.valueOf(zhtoubiao.getTkong1())
                    * zhtoubiao.getLilv() / 365F));
            // newzZhtoubiao.setTkong2(Float.valueOf(newzZhtoubiao.getTkong1())+newzZhtoubiao.getZhouqikuan()*Float.valueOf(newzZhtoubiao.getTkong1())*newzZhtoubiao.getShoubikuan()*0.072F/365F
            // -newzZhtoubiao.getZhouqihuo()*Float.valueOf(newzZhtoubiao.getTkong1())*newzZhtoubiao.getShoubihuo()
            //
            // );

            zhtoubiao.setTkong2(Float.parseFloat(String.format("%.2f",
                    qianqihesuan)));

        } else {
            float zongbili = 0F;
            for (int j = 0; j < listzhongbiao.size(); j++) {
                Zhtoubiao oldZhtoubiao = (Zhtoubiao) listzhongbiao.get(j);
                zongbili = zongbili + oldZhtoubiao.getBili();
            }
            float bili = zongbili / listzhongbiao.size()
                    * Float.valueOf(zhtoubiao.getTkong1());
            zhtoubiao.setTkong2(Float.parseFloat(String.format("%.2f", bili)));
        }
        zhtoubiao.setTname(zhUser.getName());
        zhtoubiao.setTkong10(zhaobiaoXi.getId());
        zhtoubiao.setTkong7("N");
        zhtoubiao.setTkong8(Util.getDateTime());
        zhtoubiao.setTkong9(user.getName());
        zhtoubiao.setPaihao(zhaobiaoXi.getT6());
        zhtoubiao.setGuige(zhaobiaoXi.getT5());
        zhtoubiao.setZhUserId(zhUser.getId());
        zhaobiaoServer.addtoubiao(zhtoubiao, t8, t8FileName, fatherPartNumber,
                zhaobiaoXi);

        ZhaobiaoXi zhaobiaoXi3 = new ZhaobiaoXi();
        // zhaobiaoServer.addhuikuangxaingxi(zhtoubiao.getTid(), kemus, moneys,
        // huikuangXiangxi.getDanwei());
        return "addtoubiao";
    }

    /*
     * 评选招商
     */
    // 根据id查ZhaobiaoXi 版聊表
    public String qupingxuan() {
        Object[] object = zhaobiaoServer.qupingxuan(zhaobiao.getId(), Integer
                .parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!qupingxuan.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "qupingxuan";
    }

    public String qupingxuanToubiao() {
        Object[] object = zhaobiaoServer.qupingxuanToubiao(zhaobiaoXi.getId(),
                Integer.parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!qupingxuanToubiao.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "qupingxuanToubiao";
    }

    public String pingxuanzhongbiao() {
        // 投标状态为中标
        Zhtoubiao zhtoubiao1 = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao
                .getTid());
        zhtoubiao1.setTkong7("Y");
        zhaobiaoServer.updatetoubiao(zhtoubiao1);
        // 版聊为已经中标
        ZhaobiaoXi zhaobiaoXi1 = zhaobiaoServer.ByIdzhaobiaoXi(zhtoubiao1
                .getTkong10());
        zhaobiaoXi1.setT7("Y");
        zhaobiaoServer.updatezhaobiaoXi(zhaobiaoXi1);

        zhaobiaoXi = zhaobiaoXi1;
		/*
		 * 
		 */

		/*
		 * 给中标商家发信息
		 */
        ZhUser zhUser1 = zhaobiaoServer.byzhUserName(zhtoubiao1.getTname());

        Users users2 = zhaobiaoServer.ByIDUsers(zhUser1.getUserid());
        AlertMessagesServerImpl.addAlertMessages("中标信息",
                "红湖排气系统发布了最新中标信息...恭喜你成为中标商家", "恭喜你成为中标商家", users2.getCode());
        return "pingxuanzhongbiao";
    }

    public String toupdatetoubiao() {
        zhtoubiao = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao.getTid());
        // list = zhaobiaoServer.listByToubiao(zhtoubiao.getTid());
        return "toupdatetoubiao";
    }

    public String updatetoubiao() {
        zhaobiaoServer.updatetoubiao(zhtoubiao);
        // zhaobiaoServer.updatehuikuangXiangxi(huikuangXiangxis);
        zhaobiaoXi = zhaobiaoServer.byToubiaoXI(zhtoubiao.getTid());
        return "updatetoubiao";
    }

    /*
     * 中标信息公布
     */
    public String listzhongbiao() {
        Object[] object = zhaobiaoServer.listzhongbiao(Integer.parseInt(cpage),
                pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listzhongbiao.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listzhongbiao";
    }

    /*
     * 自动评标
     */
    public String listzidong() {
        Object[] object = zhaobiaoServer.listzhaobiao(Integer.parseInt(cpage),
                pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listzhaobiao.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listzidong";
    }

    public String listzidongXi() {
        Object[] object = zhaobiaoServer.qupingxuan1(zhaobiao.getId(), Integer
                .parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!qupingxuan.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listzidongXi";
    }

    public String listzidongpingxuanToubiao() {
        Object[] object = zhaobiaoServer.qupingxuanToubiao(zhaobiaoXi.getId(),
                Integer.parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listzidongpingxuanToubiao.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listzidongpingxuanToubiao";
    }

    // 价格排序
    public String listpaixujiage() {
        Object[] object = zhaobiaoServer.listpaixujiage(zhtoubiao.getTkong10(),
                Integer.parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listpaixujiage.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listzidongpingxuanToubiao";
    }

    // 供货率
    public String listpaixugonghuolv() {
        Object[] object = zhaobiaoServer.listpaixugonghuolv(zhtoubiao
                .getTkong10(), Integer.parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listpaixugonghuolv.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listzidongpingxuanToubiao";
    }

    // 质量 listpaixuzhiliang
    public String listpaixuzhiliang() {
        Object[] object = zhaobiaoServer.listpaixuzhiliang(zhtoubiao
                .getTkong10(), Integer.parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            list = (List) object[0];
            if (list != null && list.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!listpaixuzhiliang.action");
                errorMessage = null;
            } else {
                errorMessage = "没有找到你要查询的内容,请检查后重试!";
            }
        }
        return "listzidongpingxuanToubiao";
    }

    /*
 *
 */
    // 综合排序
    public String listpaixuzonghe() {
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhtoubiao.getTkong10());
        zhtoubiao = zhaobiaoServer.listpaixuzonghe(zhaobiaoXi, zhtoubiao
                .getTkong10());
        errorMessage = "   " + zhtoubiao.getTname() + "  评选为供应商";
        url = "zhaobiaoAction!pingxuan.action?zhtoubiao.tid="
                + zhtoubiao.getTid();
        return ERROR;
    }

    // 评标计算
    // public String pingbiaojisuan() {
    // zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhtoubiao.getTkong10());
    // zhtoubiao = zhaobiaoServer.pingbiaojisuan(zhaobiaoXi, zhtoubiao
    // .getTkong10());
    // errorMessage = "   " + zhtoubiao.getTname() + "  评选为供应商";
    // url = "zhaobiaoAction!pingxuan.action?zhtoubiao.tid="
    // + zhtoubiao.getTid();
    // return ERROR;
    // }
    public String pingbiaojisuan() {
        zhaobiaoXi = zhaobiaoServer.ByIdzhaobiaoXi(zhtoubiao.getTkong10());
        zhtoubiao = zhaobiaoServer.Byhesuanjiage(zhtoubiao.getTkong10());
        errorMessage = "   " + zhtoubiao.getTname() + "  评选为供应商";
        url = "zhaobiaoAction!pingxuan.action?zhtoubiao.tid="
                + zhtoubiao.getTid();
        return ERROR;
    }

    // 评选中标商家
    public String pingxuan() {
        // 投标状态为中标
        Zhtoubiao zhtoubiao1 = zhaobiaoServer.getByIdZhtoubiao(zhtoubiao
                .getTid());
        zhtoubiao1.setTkong7("Y");
        zhaobiaoServer.updatetoubiao(zhtoubiao1);
        // 版聊为已经中标
        ZhaobiaoXi zhaobiaoXi1 = zhaobiaoServer.ByIdzhaobiaoXi(zhtoubiao1
                .getTkong10());
        zhaobiaoXi1.setT7("Y");
        zhaobiaoServer.updatezhaobiaoXi(zhaobiaoXi1);
        zhaobiaoXi = zhaobiaoXi1;

		/*
		 * 给中标商家发信息
		 */
        ZhUser zhUser1 = zhaobiaoServer.byzhUserName(zhtoubiao1.getTname());
        Users users2 = zhaobiaoServer.ByIDUsers(zhUser1.getUserid());
        AlertMessagesServerImpl.addAlertMessages("中标信息",
                "红湖排气系统发布了最新中标信息...恭喜你成为中标商家", "恭喜你成为中标商家", users2.getCode());
        return "pingxuan";
    }

    /*
     * 查看图标信息
     */
    public String chakantoubiao() {
        try {
            zhaobiao = zhaobiaoServer.listById(zhaobiao.getId());
            list = zhaobiaoServer.listxitoubiao(zhaobiao.getId());
            listAllDept = zhaobiaoServer.listAlltoubiao();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "chakantoubiao";
    }

    // 批量导入供应商
    public String addzhUser() {
        errorMessage = zhaobiaoServer.addzhUser(addzhUser);
        if ("true".equals(errorMessage)) {
            return "addUser";
        }
        return ERROR;
    }

    // 导出供应商
    public String exportExcel() {
        zhaobiaoServer.exportExcel(zhUser);
        return ERROR;
    }

    //
    public String XFWgType() {
        zhaobiaoServer.XFWgType();
        errorMessage = "完成!";
        return ERROR;
    }

    /*****************************
     * 扣款单
     ************************************/
    // 分页显示
    // 显示查询内容
    public String showList() {
        if (chargebackNotice != null)
            ActionContext.getContext().getSession().put("chargebackNotice",
                    chargebackNotice);
        else
            chargebackNotice = (ChargebackNotice) ActionContext.getContext()
                    .getSession().get("ChargebackNotice");
        Object[] object = zhaobiaoServer.findChargebackNotice(chargebackNotice,
                Integer.parseInt(cpage), pageSize);
        if (object != null && object.length > 0) {
            chargebackNoticeList = (List<ChargebackNotice>) object[0];
            if (chargebackNoticeList != null && chargebackNoticeList.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!showList.action");
            }
            errorMessage = null;
        } else
            errorMessage = "没有找到你要查询的内容,请检查后重试!";
        return "chargebackNotice_show";
    }

    public String toAdd() {
        csNumber = zhaobiaoServer.findCSNumber();
        return "chargebackNotice_add";
    }

    public void daochukoukuan() {
        zhaobiaoServer.daochukoukuan(chargebackNotice);
    }

    // 添加方法
    public String add() {
        if (chargebackNotice != null) {
            if (attachment != null && attachment.length > 0) {
                String filename = MKUtil.saveFile(attachment, attachmentFileName,
                        "ChargebackNotice");
                chargebackNotice.setFileName(filename);
            }
            errorMessage = zhaobiaoServer.addChargebackNotice(chargebackNotice);
            if ("添加成功！".equals(errorMessage))
                url = "zhaobiaoAction!showList.action";
            return "error";
        }
        errorMessage = "数据为空，添加失败！";
        return "error";
    }

    // 跳转到修改页面的方法
    public String toupdate() {
        if (chargebackNotice != null && chargebackNotice.getId() != null
                && chargebackNotice.getId() > 0) {
            chargebackNotice = zhaobiaoServer
                    .byIdChargebackNotice(chargebackNotice.getId());
            if (chargebackNotice != null)
                return "chargebackNotice_update";
        }
        errorMessage = "数据为空!请检查";
        return "error";

    }

    // 跳转到打印页面的方法
    public String toselect() {
        if (chargebackNotice != null && chargebackNotice.getId() != null
                && chargebackNotice.getId() > 0) {
            chargebackNotice = zhaobiaoServer
                    .byIdChargebackNotice(chargebackNotice.getId());
            if (chargebackNotice != null)
                return "chargebackNotice_select";
        }
        errorMessage = "数据为空!请检查";
        return "error";

    }

    // 修改方法
    public String update() {
        if (attachment != null && attachment.length > 0) {
            String filename = MKUtil.saveFile(attachment, attachmentFileName,
                    "ChargebackNotice");
            chargebackNotice.setFileName(filename);
        }
        errorMessage = zhaobiaoServer.updateChargebackNotice(chargebackNotice);
        if ("修改成功！".equals(errorMessage))
            url = "zhaobiaoAction!showList.action?cpage=" + cpage;
        return "error";
    }

    // 删除方法
    public String delete() {
        if (id1 != null && id1 > 0) {
            errorMessage = zhaobiaoServer.deleteChargebackNotice(id1);
            if ("删除成功！".equals(errorMessage))
                url = "zhaobiaoAction!showList.action?cpage=" + cpage;
            return "error";
        }
        errorMessage = "不存在该对象！删除失败！";
        return "error";
    }

    /***
     * ***************预付款申请
     *******************/
    // 跳转到添加页面的方法
    public String toAddyufu() {
        if (id1 != null && id1 > 0) {
            if (errorMessage == null) {
                waigouOrder = zhaobiaoServer.ByIdWaigouOrder(id1);
                prepayApp = zhaobiaoServer.ByIdpre(waigouOrder);
                Object[] object = zhaobiaoServer.ByIdpreLisyt(waigouOrder);
                if (object != null && object.length > 0) {
                    prepayAppList = (List<PrepaymentsApplication>) object[0];
                    coun = (Float) object[1];
                }
            }
            return "prepayApp_add";
        }
        errorMessage = "数据为空!请检查";
        return "error";

    }

    // 添加
    public String addyufu() {
        errorMessage = zhaobiaoServer.addPrepayApp(prepayApp, id1);
        if ("添加成功！".equals(errorMessage))
            url = "zhaobiaoAction!toAddyufu.action?id1=" + id1
                    + "&errorMessage=" + errorMessage;
        return "error";
    }

    // 跳转到修改页面的方法
    public String toupdateyufu() {
        if (prepayApp != null && prepayApp.getId() != null
                && prepayApp.getId() > 0) {
            prepayApp = zhaobiaoServer.byIdPrepayApp(prepayApp.getId());
            if (prepayApp != null)
                return "prepayApp_update";
        }
        errorMessage = "数据为空!请检查";
        return "error";

    }

    // 跳转到打印页面的方法
    public String toselectyufu() {
        if (prepayApp != null && prepayApp.getId() != null
                && prepayApp.getId() > 0) {
            prepayApp = zhaobiaoServer.byIdPrepayApp(prepayApp.getId());
            if (prepayApp != null)
                return "prepayApp_select";
        }
        errorMessage = "数据为空!请检查";
        return "error";

    }

    // 修改方法
    public String updateyufu() {
        errorMessage = zhaobiaoServer.updatePrepayApp(prepayApp);
        if ("修改成功！".equals(errorMessage))
            url = "zhaobiaoAction!showListYufu.action?cpage=" + cpage;
        return "error";
    }

    // 删除方法
    public String deleteyufu() {
        if (id1 != null && id1 > 0) {
            errorMessage = zhaobiaoServer.deletePrepayApp(id1);
            if ("删除成功！".equals(errorMessage))
                url = "zhaobiaoAction!showListYufu.action?cpage=" + cpage;
            return "error";
        }
        errorMessage = "不存在该对象！删除失败！";
        return "error";
    }

    // 分页显示预付款
    public String showListYufu() {
        if (prepayApp != null){
            ActionContext.getContext().getSession().put(
                    "PrepaymentsApplication", prepayApp);
        }
        else{
            prepayApp = (PrepaymentsApplication) ActionContext.getContext()
                    .getSession().get("PrepaymentsApplication");
        }
        if (tag != null){
        	ActionContext.getContext().getSession().put(
                    "tag", tag);
        }
        Object[] object = zhaobiaoServer.findPrepayApp(prepayApp, Integer
                .parseInt(cpage), pageSize, tag);
        if (object != null && object.length > 0) {
            prepayAppList = (List<PrepaymentsApplication>) object[0];
            if (prepayAppList != null && prepayAppList.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("zhaobiaoAction!showListYufu.action?tag="+tag);
            }
            errorMessage = null;
        } else
            errorMessage = "没有找到你要查询的内容,请检查后重试!";
        return "prepayApp_show";
    }

    /**********************
     * 供应商送货(合格)月度汇总
     ***************************/
    // 根据供应商id得到当年月度送货总数
    public void jsonDelivery() {
        MKUtil.writeJSON(zhaobiaoServer.byidZhUserDelivery(id, year));
    }

    /***
     * 供应商综合分析
     *
     * @return
     */
    public String screenSupplierManagement() {
        zhUser = zhaobiaoServer.listByIdZhUser(zhUser.getId());


        GysMarkIdjiepai gysMarkIdjiepai = new GysMarkIdjiepai();
        gysMarkIdjiepai.setGys(zhUser.getName());
        Object[] object2 = zhaobiaoServer.findSupplierGoods(gysMarkIdjiepai);
        gysMarkIdjiepais = (List<GysMarkIdjiepai>) object2[0];

        return "screenSupplierManagement";
    }

    /**
     * 预付款申请及明细添加页面
     *
     * @return
     */
    public String toAddPrepaymentsApplicationDetail() {
        if (orderId != null && orderId.length > 0) {
            Map<String, Object> map = zhaobiaoServer.toaddxufuDatils(orderId);
            if (map.get("gysName").equals("只能选择同一家供应商进行填写")) {
                errorMessage = (String) map.get("gysName");
                return "error";
            } else {
                prepayApp = (PrepaymentsApplication) map.get("prepayApp");
                prepaymentsApplicationDetailsList = (List<PrepaymentsApplicationDetails>) map.get("prepaymentsApplicationDetailsList");
                prepaymentsApplicationDetailsList1 = (List<PrepaymentsApplicationDetails>) map.get("padList");
                return "prepayDetils_add";
            }

        } else {
            errorMessage = "请选择至少一个订单!";
            return "error";
        }
    }

    /**
     * 添加预付款申请及明细
     *
     * @return
     */
    public String addPrepaymentsApplicationDetail() {
        if (prepayApp != null && prepaymentsApplicationDetailsList != null && prepaymentsApplicationDetailsList.size() > 0) {
            errorMessage = zhaobiaoServer.addPrepaymentsApplicationDetail(prepayApp, prepaymentsApplicationDetailsList);
            url = "zhaobiaoAction!showListYufu.action?tag=findAllself";
        } else {
            errorMessage = "系统异常请重试";
        }
        return "error";
    }

    public void findBeyond() {
        String msg = "";
        List<String> stringList = zhaobiaoServer.findBeyondBili(orderId, coun);
        if (stringList != null && stringList.size() > 0) {
            for (String s : stringList) {
                msg += "  " + s;
            }
        }
        if (!"".equals(msg)) {
            MKUtil.writeJSON(msg + "预付比例超过100%");
        } else {
            MKUtil.writeJSON(null);
        }
    }

    public String findMingxi() {
        if (prepayApp.getId() != null && !"".equals(prepayApp.getId())) {
            Map<String, Object> map = zhaobiaoServer.findMingxi(prepayApp.getId());
            prepayApp = (PrepaymentsApplication) map.get("prepayApp");
            prepaymentsApplicationDetailsList = (List<PrepaymentsApplicationDetails>) map.get("prepaymentsApplicationDetailsList");
        }
        return "prepayDetils_mingxi";

    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ChargebackNotice getChargebackNotice() {
        return chargebackNotice;
    }

    public void setChargebackNotice(ChargebackNotice chargebackNotice) {
        this.chargebackNotice = chargebackNotice;
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public List<ChargebackNotice> getChargebackNoticeList() {
        return chargebackNoticeList;
    }

    public void setChargebackNoticeList(
            List<ChargebackNotice> chargebackNoticeList) {
        this.chargebackNoticeList = chargebackNoticeList;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public ZhaobiaoServer getZhaobiaoServer() {
        return zhaobiaoServer;
    }

    public void setZhaobiaoServer(ZhaobiaoServer zhaobiaoServer) {
        this.zhaobiaoServer = zhaobiaoServer;
    }

    public ZhMetric getZhMetric() {
        return zhMetric;
    }

    public void setZhMetric(ZhMetric zhMetric) {
        this.zhMetric = zhMetric;
    }

    public String getCpage() {
        return cpage;
    }

    public void setCpage(String cpage) {
        this.cpage = cpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ZhUser getZhUser() {
        return zhUser;
    }

    public void setZhUser(ZhUser zhUser) {
        this.zhUser = zhUser;
    }

    public Zhmoban getZhmoban() {
        return zhmoban;
    }

    public void setZhmoban(Zhmoban zhmoban) {
        this.zhmoban = zhmoban;
    }

    public Zhaobiao getZhaobiao() {
        return zhaobiao;
    }

    public void setZhaobiao(Zhaobiao zhaobiao) {
        this.zhaobiao = zhaobiao;
    }

    public DeptMonthBudget getDeptMonthBudget() {
        return deptMonthBudget;
    }

    public void setDeptMonthBudget(DeptMonthBudget deptMonthBudget) {
        this.deptMonthBudget = deptMonthBudget;
    }

    public ZhaobiaoXi getZhaobiaoXi() {
        return zhaobiaoXi;
    }

    public void setZhaobiaoXi(ZhaobiaoXi zhaobiaoXi) {
        this.zhaobiaoXi = zhaobiaoXi;
    }

    public ZhaobiaoXis getZhaobiaoXis() {
        return zhaobiaoXis;
    }

    public void setZhaobiaoXis(ZhaobiaoXis zhaobiaoXis) {
        this.zhaobiaoXis = zhaobiaoXis;
    }

    public List getListyusuan() {
        return listyusuan;
    }

    public void setListyusuan(List listyusuan) {
        this.listyusuan = listyusuan;
    }

    public String getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(String pageStatus) {
        this.pageStatus = pageStatus;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public List getListmoban() {
        return listmoban;
    }

    public void setListmoban(List listmoban) {
        this.listmoban = listmoban;
    }

    public List getListAllDept() {
        return listAllDept;
    }

    public void setListAllDept(List listAllDept) {
        this.listAllDept = listAllDept;
    }

    public Zhtoubiao getZhtoubiao() {
        return zhtoubiao;
    }

    public void setZhtoubiao(Zhtoubiao zhtoubiao) {
        this.zhtoubiao = zhtoubiao;
    }

    public Integer getHh() {
        return hh;
    }

    public void setHh(Integer hh) {
        this.hh = hh;
    }

    public Flowdetail getFlowdetail() {
        return flowdetail;
    }

    public void setFlowdetail(Flowdetail flowdetail) {
        this.flowdetail = flowdetail;
    }

    public File getT8() {
        return t8;
    }

    public void setT8(File t8) {
        this.t8 = t8;
    }

    public String getT8Type() {
        return t8Type;
    }

    public void setT8Type(String t8Type) {
        this.t8Type = t8Type;
    }

    public String getT8FileName() {
        return t8FileName;
    }

    public void setT8FileName(String t8FileName) {
        this.t8FileName = t8FileName;
    }

    public String getPagemoban() {
        return pagemoban;
    }

    public void setPagemoban(String pagemoban) {
        this.pagemoban = pagemoban;
    }

    public String getFatherPartNumber() {
        return fatherPartNumber;
    }

    public void setFatherPartNumber(String fatherPartNumber) {
        this.fatherPartNumber = fatherPartNumber;
    }

    public File getShenhefujian() {
        return shenhefujian;
    }

    public void setShenhefujian(File shenhefujian) {
        this.shenhefujian = shenhefujian;
    }

    public String getShenhefujianType() {
        return shenhefujianType;
    }

    public void setShenhefujianType(String shenhefujianType) {
        this.shenhefujianType = shenhefujianType;
    }

    public String getShenhefujianFileName() {
        return shenhefujianFileName;
    }

    public void setShenhefujianFileName(String shenhefujianFileName) {
        this.shenhefujianFileName = shenhefujianFileName;
    }

    public Huikuang getHuikuang() {
        return huikuang;
    }

    public void setHuikuang(Huikuang huikuang) {
        this.huikuang = huikuang;
    }

    public List getListhuikuang() {
        return listhuikuang;
    }

    public void setListhuikuang(List listhuikuang) {
        this.listhuikuang = listhuikuang;
    }

    public Hui_Xi getHuiXi() {
        return huiXi;
    }

    public void setHuiXi(Hui_Xi huiXi) {
        this.huiXi = huiXi;
    }

    public String[] getKemus() {
        return kemus;
    }

    public void setKemus(String[] kemus) {
        this.kemus = kemus;
    }

    public float[] getMoneys() {
        return moneys;
    }

    public void setMoneys(float[] moneys) {
        this.moneys = moneys;
    }

    public HuikuangXiangxi getHuikuangXiangxi() {
        return huikuangXiangxi;
    }

    public void setHuikuangXiangxi(HuikuangXiangxi huikuangXiangxi) {
        this.huikuangXiangxi = huikuangXiangxi;
    }

    public HuikuangXiangxis getHuikuangXiangxis() {
        return huikuangXiangxis;
    }

    public void setHuikuangXiangxis(HuikuangXiangxis huikuangXiangxis) {
        this.huikuangXiangxis = huikuangXiangxis;
    }

    public Zhgongxu getZhgongxu() {
        return zhgongxu;
    }

    public void setZhgongxu(Zhgongxu zhgongxu) {
        this.zhgongxu = zhgongxu;
    }

    public Nianlilv getNianlilv() {
        return nianlilv;
    }

    public void setNianlilv(Nianlilv nianlilv) {
        this.nianlilv = nianlilv;
    }

    public ProcardTemplate getProcardTemplate() {
        return procardTemplate;
    }

    public void setProcardTemplate(ProcardTemplate procardTemplate) {
        this.procardTemplate = procardTemplate;
    }

    public int[] getSelected() {
        return selected;
    }

    public void setSelected(int[] selected) {
        this.selected = selected;
    }

    public GysMarkIdjiepai getGysjiepai() {
        return gysjiepai;
    }

    public void setGysjiepai(GysMarkIdjiepai gysjiepai) {
        this.gysjiepai = gysjiepai;
    }

    public File getAddzhUser() {
        return addzhUser;
    }

    public void setAddzhUser(File addzhUser) {
        this.addzhUser = addzhUser;
    }

    public String getAddmachineContentType() {
        return addmachineContentType;
    }

    public void setAddmachineContentType(String addmachineContentType) {
        this.addmachineContentType = addmachineContentType;
    }

    public String getAddmachineFileName() {
        return addmachineFileName;
    }

    public void setAddmachineFileName(String addmachineFileName) {
        this.addmachineFileName = addmachineFileName;
    }

    public List<Category> getCylist() {
        return cylist;
    }

    public void setCylist(List<Category> cylist) {
        this.cylist = cylist;
    }

    public String getCsNumber() {
        return csNumber;
    }

    public void setCsNumber(String csNumber) {
        this.csNumber = csNumber;
    }

    public PrepaymentsApplication getPrepayApp() {
        return prepayApp;
    }

    public void setPrepayApp(PrepaymentsApplication prepayApp) {
        this.prepayApp = prepayApp;
    }

    public List<PrepaymentsApplication> getPrepayAppList() {
        return prepayAppList;
    }

    public void setPrepayAppList(List<PrepaymentsApplication> prepayAppList) {
        this.prepayAppList = prepayAppList;
    }

    public WaigouOrder getWaigouOrder() {
        return waigouOrder;
    }

    public void setWaigouOrder(WaigouOrder waigouOrder) {
        this.waigouOrder = waigouOrder;
    }

    public Float getCoun() {
        return coun;
    }

    public void setCoun(Float coun) {
        this.coun = coun;
    }

    public List<GysMarkIdjiepai> getGysMarkIdjiepais() {
        return gysMarkIdjiepais;
    }

    public void setGysMarkIdjiepais(List<GysMarkIdjiepai> gysMarkIdjiepais) {
        this.gysMarkIdjiepais = gysMarkIdjiepais;
    }

    public Integer[] getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer[] orderId) {
        this.orderId = orderId;
    }

    public String getGysName() {
        return gysName;
    }

    public void setGysName(String gysName) {
        this.gysName = gysName;
    }

    public List<WaigouOrder> getWaigouOrderList() {
        return waigouOrderList;
    }

    public void setWaigouOrderList(List<WaigouOrder> waigouOrderList) {
        this.waigouOrderList = waigouOrderList;
    }

    public List<PrepaymentsApplicationDetails> getPrepaymentsApplicationDetailsList() {
        return prepaymentsApplicationDetailsList;
    }

    public void setPrepaymentsApplicationDetailsList(
            List<PrepaymentsApplicationDetails> prepaymentsApplicationDetailsList) {
        this.prepaymentsApplicationDetailsList = prepaymentsApplicationDetailsList;
    }

    public float getZongCount() {
        return zongCount;
    }

    public void setZongCount(float zongCount) {
        this.zongCount = zongCount;
    }

    public List<PrepaymentsApplicationDetails> getPrepaymentsApplicationDetailsList1() {
        return prepaymentsApplicationDetailsList1;
    }

    public void setPrepaymentsApplicationDetailsList1(
            List<PrepaymentsApplicationDetails> prepaymentsApplicationDetailsList1) {
        this.prepaymentsApplicationDetailsList1 = prepaymentsApplicationDetailsList1;
    }


    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    public File[] getAttachment() {
        return attachment;
    }

    public void setAttachment(File[] attachment) {
        this.attachment = attachment;
    }

    public String[] getAttachmentContentType() {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String[] attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    public String[] getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String[] attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public SupplierCertification getSupplierCertification() {
        return supplierCertification;
    }

    public void setSupplierCertification(SupplierCertification supplierCertification) {
        this.supplierCertification = supplierCertification;
    }

    public SupplierCertificationService getSupplierCertificationService() {
        return supplierCertificationService;
    }

    public void setSupplierCertificationService(SupplierCertificationService supplierCertificationService) {
        this.supplierCertificationService = supplierCertificationService;
    }
}
