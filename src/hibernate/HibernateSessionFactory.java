package hibernate;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactory {

    static String CONFIG_FILE_LOCATION = "/Hibernate.cfg.xml";
    static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    static Configuration configuration = new Configuration();
    static SessionFactory sessionFactory;
    static String configFile = CONFIG_FILE_LOCATION;


    static {
        buildSessionFactory();

    }

    private HibernateSessionFactory() {

    }

    private static void buildSessionFactory() {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }

    //获取Session
    public static Session getCurrentSession() {

        Session session = threadLocal.get();

        //判断Session是否为空，如果为空，将创建一个session，并付给线程变量tLocalsess
        try {
            if (session == null || !session.isOpen()) {
                if (sessionFactory == null) {
                    rbuildSessionFactory();
                } else {
                    session = sessionFactory.openSession();

                }
                session = (sessionFactory != null) ? sessionFactory.openSession() : null;

            }
            threadLocal.set(session);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        return session;
    }

    public static void closeSession() {
        Session session = threadLocal.get();
        threadLocal.set(null);
        if (session != null)
            session.close();
    }

    public static void rbuildSessionFactory() {
        buildSessionFactory();
    }

    public static Configuration getConfiguration() {
        return configuration;
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static void setConfigFile(String configFile) {
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }
}
