package br.com.ecommerce.hibernate;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.ecommerce.classe.Despesa;
import br.com.ecommerce.classe.Item;
import br.com.ecommerce.classe.Usuario;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Marlon
 */
public class NewHibernateUtil{
    private static NewHibernateUtil me;
    private static SessionFactory sessionFactory;

    public NewHibernateUtil() {
            sessionFactory = new AnnotationConfiguration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/Renove")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "postgres")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .setProperty("hibernate.c3p0.acquire_increment", "1")
                .setProperty("hibernate.c3p0.idle_test_period", "100")
                .setProperty("hibernate.c3p0.max_size", "10")
                .setProperty("hibernate.c3p0.max_statements", "0")
                .setProperty("hibernate.c3p0.min_size", "5")
                .setProperty("hibernate.c3p0.timeout", "100")
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Usuario.class)
                .addAnnotatedClass(Despesa.class)
                .buildSessionFactory();
    }
    public static NewHibernateUtil getInstance(){
        if (me == null) {
            me = new NewHibernateUtil();
        }
        return me;
    }
    public Session getSession() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }
}
