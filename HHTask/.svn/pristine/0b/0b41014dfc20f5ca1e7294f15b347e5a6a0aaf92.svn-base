package com.task.util.invoice;

public enum ErrorResps {
    SUCCESS(0, "成功", null),
    PARAM_ERROR(10001, "参数错误", null),
    JYM_ERROR(10002, "请输入20位或后6位校验码", null);

    private final BaseResp<Object> resp;

    ErrorResps(int code, String msg, Object data){
        this.resp = new BaseResp<Object>(code, msg, data);
    }

    public BaseResp get(){ return resp; }

}
