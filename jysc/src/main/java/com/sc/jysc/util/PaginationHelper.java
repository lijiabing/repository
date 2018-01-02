package com.sc.jysc.util;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by Jackbing on 2017/12/26.
 */
public class PaginationHelper<T> {

    public CurrentPage<T> fetchPage(String sql, Object[] params, JdbcTemplate jt, RowMapper rowMapper,
                                    int pageNum,int pageSize) throws RuntimeException{
        String sqlcountRows="select count(*) from ("+sql+") as t";

        int countRow=jt.queryForObject(sqlcountRows,params,Integer.class);

        if(pageNum<0||pageSize<1){
            throw new RuntimeException("cannot to fetchpage by pageNum : "+pageNum+", pageSize : "+pageSize);
        }

        if(params==null){
            params=new Object[0];
        }

        CurrentPage<T> currentPage=new CurrentPage<T>(pageNum,pageSize,countRow);
//        if(pageNum*pageSize>=countRow-((pageNum-1)*pageSize)){
//            if(countRow==0){
//                countRow=1;
//            }
//            pageNum=0;
//            pageSize=countRow;
//        }
        sql=sql+" limit ?,?";
        Object[] newParams=new Object[params.length+2];
        System.arraycopy(params,0,newParams,0,params.length);
        newParams[params.length]=pageSize*(pageNum-1);
        newParams[params.length+1]=pageSize;
        List<T> list=jt.query(sql,newParams,rowMapper);
        if(list!=null) {
            currentPage.setPageItems(list);
        }
        return currentPage;
    }
}
