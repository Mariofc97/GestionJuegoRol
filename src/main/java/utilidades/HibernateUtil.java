package utilidades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    // Inicializa la SessionFactory si aún no lo está
    public static void crearConexion() {
        try {
            if (sessionFactory == null || sessionFactory.isClosed()) {
                sessionFactory = new Configuration()
                        .configure() // hibernate.cfg.xml
                        .buildSessionFactory();
                System.out.println("SessionFactory creada y conexión establecida");
            }
        } catch (Throwable ex) {
            System.err.println("Error al crear la SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Devuelve la SessionFactory (SINGLETON)
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            crearConexion();
        }
        return sessionFactory;
    }

    // Devuelve una Session nueva (cada llamada abre una)
    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    public static void cerrarSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("SessionFactory cerrada");
        }
    }
}
