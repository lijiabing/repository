package com.sc.jysc.session;

import java.io.Serializable;

public interface DataSet extends DataManageTransactional{
    Object get(Serializable id, String entityName);
    Serializable save(Object object);
}
