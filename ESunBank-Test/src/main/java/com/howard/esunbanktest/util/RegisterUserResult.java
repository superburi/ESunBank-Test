package com.howard.esunbanktest.util;

public enum RegisterUserResult {

    Failed( "手機號碼已存在，請檢查是否已註冊！"),
    Success( "註冊成功！"),
    Default( "未知錯誤");

    private final String returnMsg; // 註冊結果訊息

    RegisterUserResult(String returnMsg) {

        this.returnMsg = returnMsg;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

}
