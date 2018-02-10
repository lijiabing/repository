package com.sc.jysc.session;

public interface DataManageTransactional {
    void commit();

    void rollback();

    void flush();

    void beginTransaction();
}
