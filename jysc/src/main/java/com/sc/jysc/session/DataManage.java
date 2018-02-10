package com.sc.jysc.session;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class DataManage  extends AbstractPlatformTransactionManager implements DataManageTransactional{

    private static Logger logger = Logger.getLogger(DataManage.class);
    private SessionFactory sessionFactory=null;
    private Transaction currentTransaction = null;
    private LazySessionCache lazySessionCache;

    public DataManage(LazySessionCache lazySessionCache) {
        this.lazySessionCache = lazySessionCache;
        this.lazyLoad();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void beginTransaction() {
        this.currentTransaction = this.getSession().beginTransaction();
    }

    public void commit() {
        if (this.currentTransaction != null) {
            this.currentTransaction.commit();
        }

    }

    public void rollback() {
        if (this.currentTransaction != null) {
            this.currentTransaction.rollback();
        }

    }

    protected Session getSession() {
        try {
            this.lazyLoad();
        } catch (Exception var2) {
            logger.error("fail to load session factory, reason: " + var2.getMessage(), var2);
            throw new RuntimeException(var2);
        }

        return this.sessionFactory.getCurrentSession();
    }
    public void flush() {
        this.getSession().flush();
    }

    public SessionFactory getSessionFactoryInstance() {
        this.lazyLoad();
        return this.getSessionFactory();
    }

    public void lazyLoad() {
        if (this.sessionFactory == null) {
            this.lazySessionCache.reloadSession(this);
            if (this.sessionFactory == null) {
                throw new RuntimeException("Fail to lazy load " );
            }
        }
        if(this.currentTransaction==null){
            this.currentTransaction=sessionFactory.getCurrentSession().getTransaction();
        }
    }


    @Override
    protected Object doGetTransaction() throws TransactionException {
        return this.getSession().getTransaction();
    }

    @Override
    protected void doBegin(Object o, TransactionDefinition transactionDefinition) throws TransactionException {
        this.beginTransaction();
    }

    @Override
    protected void doCommit(DefaultTransactionStatus defaultTransactionStatus) throws TransactionException {
        this.commit();
    }

    @Override
    protected void doRollback(DefaultTransactionStatus defaultTransactionStatus) throws TransactionException {
        this.rollback();
    }
}
