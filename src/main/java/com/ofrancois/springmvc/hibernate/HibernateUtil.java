package com.ofrancois.springmvc.hibernate;

import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	 private static final SessionFactory sessionFactory;

	 static {
	   try {
	   // Cr�e la SessionFactory
	   /*sessionFactory =
	         new Configuration().configure().buildSessionFactory();*/
		   StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			        .configure( "hibernate.cfg.xml" )
			        .build();

			        Metadata metadata = new MetadataSources( standardRegistry )
			        .getMetadataBuilder()
			        .build();
		   sessionFactory = metadata.getSessionFactoryBuilder().build();
		   
		   
	   } catch (HibernateException ex) {
	   throw new RuntimeException("Probl�me de configuration : "
	   + ex.getMessage(), ex);
	   }
	   }

	 public static final ThreadLocal session = new ThreadLocal();

	 public static Session currentSession()
	                throws HibernateException {
	   Session s = (Session) session.get();
	   // Ouvre une nouvelle Session, si ce Thread n'en a aucune
	   if (s == null) {
	   s = sessionFactory.openSession();
	   session.set(s);
	   }
	   return s;
	   }

	 public static void closeSession()
	                throws HibernateException {
	   Session s = (Session) session.get();
	   session.set(null);
	   if (s != null)
	   s.close();
	   }
}
