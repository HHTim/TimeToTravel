package com.tibame.timetotravel.Service;

import com.tibame.timetotravel.Entity.Ann;
import com.tibame.timetotravel.common.PageBean;

import java.util.List;

public interface AdminAnnService {
    void insert(Ann ann);

    String update(Integer id,Ann ann);

    void deleteById(Integer id);
    List<Ann> findByPage(Integer currPage, Integer row);
    List<Ann> findByDateRange(String startDate, String endDate,Integer currPage, Integer limit);

    List<Ann> findByKeyWords(String keyword, Integer currPage, Integer limit);

    List<Ann> findByAllKeyWords(String keyword);

    PageBean<Ann> findAnnPageByRowData(Integer currPage, Integer rows);
    PageBean<Ann> findAnnPageByDateRange(String startDate, String endDate, Integer currPage, Integer rows);
    PageBean<Ann> findAnnPageByKeyWords(String keyword,Integer currPage, Integer rows);
}
