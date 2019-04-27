package com.task.Server.caiwu;

import com.task.entity.caiwu.AccountCheck;
import com.task.entity.caiwu.receivePayment.ReceiptLog;

import java.util.List;

public interface AccountCheckService {
    List<ReceiptLog> findAllPayment();

    AccountCheck findAccountCheck(Integer id);


    AccountCheck findAccountCheckByReceiptLogId(Integer id);

    /*
        * @author fy
    　　* @date 2018/8/24 10:36
    　　* @Description:上传时检查是否有对账单。更新状态
    　　* @param
    　　* @return
    　　* @throws
    　　*/
    Boolean checkUplodState(Integer id);

    Boolean checkCwVouchersState(Integer id);

    ReceiptLog findReceiptLog(Integer id);

    String AddAccountCheck(AccountCheck accountCheck);

    String SendAlertMessages(AccountCheck accountCheck);


    String DeleteAccountCheck(AccountCheck accountCheck);

    String UpdateAccountCheck(AccountCheck accountCheck);

    Object[] findAllAccountCheck(AccountCheck accountCheck, int pageNo, int pageSize);

    String findFundApplyByAccountCheck(Integer id);

    String findReceiptByAccountCheck(Integer id);

    String findReceiptLogFileByAccountCheck(Integer id);

    String findcwVouchersByAccountCheck(Integer id);
}
