/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ecommerce.persistencia;

import java.io.Serializable;
import java.util.List;
import br.com.ecommerce.hibernate.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Marlon
 */
public class GenericDAO{

    public GenericDAO() {}

    protected static Session getSession(){
        return NewHibernateUtil.getInstance().getSession();
    }

    protected void saveOrUpdatePojo(Serializable pojo){
        Session ses = getSession();
        ses.saveOrUpdate(pojo);
        ses.beginTransaction().commit();
        ses.close();
    }

    protected void saveOrUpdatePojoNoCommit(Serializable pojo, Session session){
        session.saveOrUpdate(pojo);
    }
    
    protected void removePojo(Serializable pojo){
        Session ses = getSession();
        ses.delete(pojo);
        ses.beginTransaction().commit();
        ses.close();
    }
    
    protected void removePojoNoCommit(Serializable pojo, Session session){
        session.delete(pojo);
    }
    
    protected void commit(Session session){
        session.beginTransaction().commit();
    }
    
    protected void roolback(Session session){
        session.beginTransaction().rollback();
    }

    protected <T extends Serializable> T getPojo(Class<T> classToSearch, Serializable key){
        Session ses = getSession();
        Serializable toReturn = (Serializable) ses.get(classToSearch, key);
        ses.beginTransaction().commit();
        ses.close();
        return (T) toReturn;
    }

    protected Serializable getPurePojo(String query, Object... params) {
        Session ses = getSession();

        Query qr = ses.createQuery(query);
        for (int i = 1; i <= params.length; i++){
           qr.setParameter(i, params[i-1]);
        }
        Object toReturn = qr.uniqueResult();

        ses.beginTransaction().commit();
        ses.close();

        return (Serializable) toReturn;
    }
    
    protected Serializable getPurePojoSQLQuery(String query, Object... params) {
        Session ses = getSession();

        Query qr = ses.createSQLQuery(query);
        for (int i = 1; i <= params.length; i++){
           qr.setParameter(i, params[i-1]);
        }
        Object toReturn = qr.uniqueResult();

        ses.beginTransaction().commit();
        ses.close();

        return (Serializable) toReturn;
    }

    public <T extends Serializable> List<T> getListPojo(Class<T> classToCast, String query, Object... params) {
        Session ses = getSession();

        Query qr = ses.createQuery(query);
        for(int i = 1; i <= params.length; i++) {
            qr.setParameter(i, params[i-1]);
        }

        List<T> toReturn = qr.list();

        ses.beginTransaction().commit();
        ses.close();

        return toReturn;
    }

}
