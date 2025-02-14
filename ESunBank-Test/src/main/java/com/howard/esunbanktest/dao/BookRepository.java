package com.howard.esunbanktest.dao;

import java.util.List;
import java.util.Map;

public interface BookRepository {

    public List<Map<String, Object>> getBooks();

}
