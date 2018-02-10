package com.sc.jysc.sevice;

import com.sc.jysc.config.DefaultServiceException;
import com.sc.jysc.entity.Sort;
import com.sc.jysc.session.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Date;


/**
 * 分类编辑
 */
@Service
@Transactional(transactionManager = "dataManage")
public class SortEditService {


    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DataSet ds;

    /**
     * 添加分类
     * @param sort
     */
    public Serializable addSort(Sort sort) throws DefaultServiceException {
        sort.setId(null);
        if(sort==null||(sort.getSortName()==null||sort.getSortId()==null)){
            throw new DefaultServiceException("分类名称或分类编号为空!");
        }
        Date date=new Date();
        sort.setCreatetimestamp(date.getTime());
        sort.setModifytimestamp(date.getTime());
        //this.entityManager.persist(sort);
        return ds.save(sort);
    }

    /**
     *删除分类
     */
    public boolean deleteSort(String id){
        try{
            //this.sessionFactory.getCurrentSession().remove(this.sessionFactory.getCurrentSession().get(Sort.class,id));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Object get(String id){
        return ds.get(id,Sort.class.getName());
    }

}
