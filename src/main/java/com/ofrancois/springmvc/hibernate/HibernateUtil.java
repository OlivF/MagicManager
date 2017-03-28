package com.ofrancois.springmvc.hibernate;

import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/** 
 * <b>HibernateUtil est la classe pour la gestion des sessions hibernate</b>
 * 
 * @author Olivier F.
 * @version 1.0
 */
public class HibernateUtil {

	/**
	 * La sessionFactory
	 */
	private static final SessionFactory sessionFactory;

	static {
		try {
			// Crée la SessionFactory
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			        .configure( "hibernate.cfg.xml" )
			        .build();

			Metadata metadata = new MetadataSources( standardRegistry )
			        .getMetadataBuilder()
			        .build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();
	   } catch (HibernateException ex) {
		   throw new RuntimeException("Problème de configuration : "
				   							+ ex.getMessage(), ex);
	   }
	}

	public static final ThreadLocal session = new ThreadLocal();

	/**
	 * Récupère la session hibernate
	 * 
	 * @return La session
	 * 
	 * @throws HibernateException
	 */
	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		// Ouvre une nouvelle Session, si ce Thread n'en a aucune
		if (s == null) {
		   s = sessionFactory.openSession();
		   session.set(s);
		}
		return s;
	}

	/**
	 * Ferme la session Hibernate
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
	   Session s = (Session) session.get();
	   session.set(null);
	   if (s != null)
		   s.close();
	}
}
