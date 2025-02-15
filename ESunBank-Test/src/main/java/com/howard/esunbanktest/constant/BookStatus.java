package com.howard.esunbanktest.constant;

public enum BookStatus {

    INSTOCK(0, "可借閱"),
    Loaned(1, "出借中"),
    SORTING(2, "整理中 ( 包含歸還後未入庫 )"),
    LOST(3, "遺失"),
    BROKEN(4, "損毀"),
    DISCARDED(5, "廢棄");

    private final int statusCode;
    private final String statusDesc;

    BookStatus(int statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
