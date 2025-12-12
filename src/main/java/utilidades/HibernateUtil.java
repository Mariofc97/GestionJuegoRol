package utilidades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;
	
	// Inicializa la SessionFactory si aún no lo está
	public static void crearConexion() {
		try {
			if (sessionFactory == null || sessionFactory.isClosed()) {
				sessionFactory = new Configuration()
						.configure() // Carga hibernate.cfg.xml
						.buildSessionFactory();
				System.out.println("SessionFactory creada y conexión establecida");
			}
		} catch (Throwable ex) {
			System.err.println("Error al crear la SessionFactory: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	// patron singleton, un patron que se crea una sola vez en memoria
	private static Session getSession() {
        SessionFactory sessionFactory = new Configuration()
                .configure() // Carga hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
	}
	
	public static Session getSessionFactory() {
		if (session == null || !session.isOpen()) {
			session = getSession();
		}
		
		return session;
	}
	
//	public static Session getSession() {
//		// Por si acaso alguien llama a getSession() sin haber creado antes la conexión
//		if (sessionFactory == null || sessionFactory.isClosed()) {
//			crearConexion();
//		}
//		return sessionFactory.openSession();
//	}
	
	public static void cerrarSessionFactory() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
			System.out.println("SessionFactory cerrada");
		}
	}
}
