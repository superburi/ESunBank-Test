package com.howard.esunbanktest.dao;

import java.util.Map;

public interface InventoryRepository {

    public Map<String, Object> updateStatus(String isbn, Integer status);

}
