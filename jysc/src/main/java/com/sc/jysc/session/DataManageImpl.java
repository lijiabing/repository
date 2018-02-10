package com.sc.jysc.session;


import java.io.Serializable;

public class DataManageImpl extends DataManage implements DataSet{
    public DataManageImpl(LazySessionCache lazySessionCache) {
        super(lazySessionCache);
    }

    public Object get(Serializable id, String entityName) {
        return this.getSession().get(entityName,id);
    }

    public Serializable save(Object object) {

        return this.getSession().save(object);
    }

    @Override
    public void remove(Object object) {
        this.getSession().delete(object);
    }

    @Override
    public void remove(Serializable id, String var) {
        this.getSession().remove(this.getSession().get(var,id));
    }


}
