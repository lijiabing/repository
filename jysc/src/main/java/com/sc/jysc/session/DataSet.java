package com.sc.jysc.session;

import java.io.Serializable;

public interface DataSet extends DataManageTransactional{
    Object get(Serializable id, String entityName);
    Serializable save(Object object);
    void remove(Object object);
    void remove(Serializable id,String var);

}
