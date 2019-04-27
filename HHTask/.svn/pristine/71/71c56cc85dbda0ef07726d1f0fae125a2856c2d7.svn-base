package com.huawei.openapi.openaipexample.client.http.ec;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import com.task.util.Util;

import ext.huawei.gdp.ecfeeback.bean.ECAFeedBackBean;
import ext.huawei.gdp.ecfeeback.bean.ResponseInfoBean;
import ext.huawei.gdp.ecfeeback.bean.SubmitECFeedBack;
import ext.huawei.gdp.ecfeeback.service.AutoFeedBackService;

public class Test {

	public static void main(String[] args) {
		AutoFeedBackService service = new AutoFeedBackService("Z000TR_PM",
				"ZLL1234.");
		ResponseInfoBean<ECAFeedBackBean> resultBean;
		try {
			resultBean = service.getECFeedBack();
			System.out.println(resultBean.getException());// 获取异常信息
			System.out.println(resultBean.getSuccess());//
			// 判断请求是否成功,true成功,false不成功,不成功getException方法会有输出
			System.out.println(resultBean.getRecords());//
			// 获取请求结果,返回是List,元素是ECAFeedBackBean,是反馈单的相关信息
			List<ECAFeedBackBean> list = resultBean.getRecords();
			for (ECAFeedBackBean ecaFeedBackBean : list) {
				System.out.println(ecaFeedBackBean.getEcnNumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String execute = "";// 执行情况(已执行/不执行/不涉及)
		String refuseReason = "不涉及我司编码";// 未执行原因

		SubmitECFeedBack submit = new SubmitECFeedBack();
		// 设置反馈单内容,根据实际内容设置,这只是样例
		submit.setWorkItemId(121727993495L);// 任务唯一标识符
		submit.setEcType("Item Revise");// EC申请单类型
		submit.setRefuseReason(refuseReason);// 不涉及的时候必须填写原因
		// |NotInvovled(不涉及)]
		if ("MDADocAutoChange".equals(submit.getEcType())) {
			/****
			 * 非整机
			 */
			String mdamakedoc = "是";
			if ("已执行".equals(execute)) {
				submit.setExecute("Executed");// 是否执行EC[Executed(已执行)/NOExecuted(不涉及)]
			} else {
				mdamakedoc = "不涉及";
				submit.setExecute("NOExecuted");// 是否执行EC[Executed(已执行)/NOExecuted(不涉及)]
			}
			/*****
			 * 处理结果:1.是 2.否:原因:xxxx 3.不涉及
			 */
			submit.setMdamakedoc(mdamakedoc);// 工艺文档(含展开图,BOM,QC,SOP,SIP)是否优化完成
			submit.setMdaequip("不涉及");// 工装检具模具是否优化完成
			submit.setMdasupplier("不涉及");// 二级供应商是否同步完成
			submit.setMdarelationNumber("不涉及");// 关联编码是否已排查完成
			submit.setStackComments("不涉及");// 库存(半成品,成品库存)处理情况
		} else {
			String cutInTime = null;
			if ("已执行".equals(execute)) {
				submit.setExecute("NOExecuted");// 是否执行EC[Executed(已执行)/NOExecuted(不执行)/NOTINVOLVED(不涉及)]
				cutInTime = Util.getDateTime("yyyy-MM-dd");
			} else if ("不执行".equals(execute)) {
				submit.setExecute("NOExecuted");// 是否执行EC[Executed(已执行)/NOExecuted(不执行)/NOTINVOLVED(不涉及)]
			} else {
				submit.setExecute("NOTINVOLVED");// 是否执行EC[Executed(已执行)/NOExecuted(不执行)/NOTINVOLVED(不涉及)]
			}
			/****
			 * 整机
			 */
			submit.setMaterialsPurchasedWO("");// 采购材料执行数量或者任务命令
			submit.setSemiProductsWO("");// 半成品执行数量或者任务命令
			submit.setSystemProductsWO("");// 整机执行数量或任务命令
			submit.setCutInTime(cutInTime);// 切入时间(年-月-日,2016-01-20)
			submit.setMdamakedoc(null);// 工艺文档(含展开图,BOM,QC,SOP,SIP)是否优化完成
			submit.setMdaequip(null);// 工装检具模具是否优化完成
			submit.setMdasupplier(null);// 二级供应商是否同步完成
			submit.setMdarelationNumber(null);// 关联编码是否已排查完成
			submit.setStackComments(null);// 库存(半成品,成品库存)处理情况
		}

		ResponseInfoBean<SubmitECFeedBack> resp;
		try {
			resp = service.submitECFeedBack(Arrays.asList(submit));
			if (!resp.getSuccess()) {// 只用处理不成功情况
				String msg = resp.getException();// 获取异常信息
				System.out.println(msg);
				List<SubmitECFeedBack> submitlist = resp.getRecords();// 如果异常了,会将检查结果写到SubmitECFeedBack的checkResult字段
				for (SubmitECFeedBack submit1 : submitlist) {
					System.out.println(submit1.getCheckResult());// 获取检查结果,具体异常字段
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
