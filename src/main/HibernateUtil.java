package main;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Nikola Dragić
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory(){
        Configuration config = new Configuration().configure("/config/hibernate.cfg.xml");
        if (sessionFactory == null){
            sessionFactory = config.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build());
        }
        
        return sessionFactory;
    }
    
}
