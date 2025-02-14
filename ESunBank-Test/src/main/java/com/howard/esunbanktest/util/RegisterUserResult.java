package com.howard.esunbanktest.util;

public enum RegisterUserResult {

    Failed( -1, "手機號碼已存在，請檢查是否已註冊！"),
    Success( 1, "註冊成功！"),
    Default( 0, "未知錯誤");

    private final int returnCode;
    private final String returnMsg; // 註冊結果訊息

    RegisterUserResult(int returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

}
