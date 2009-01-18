package com.vbkn.titan.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Valter Bruno Konrad Neto
 */

public final class PersistenceUtil {
	
	private static final String UNIT_NAME = "titan";
	
	private static EntityManagerFactory FACTORY;
	
	public static final ThreadLocal<EntityManager> SESSION = new ThreadLocal<EntityManager>();
	
	public static EntityManager currentEntityManager() {
		EntityManager manager = (EntityManager) SESSION.get();
        if (manager == null) {	
        	loadInstance();
        	manager = FACTORY.createEntityManager();
            SESSION.set(manager);
        }
        return manager;
    }
	
	public static void closeEntityManager() {
		EntityManager manager = (EntityManager) SESSION.get();
        if (manager != null) {
        	manager.close();
        }
        SESSION.set(null);
    }

	private static synchronized void loadInstance() {
		if (FACTORY == null) {
			FACTORY = Persistence.createEntityManagerFactory(UNIT_NAME);
		}
	}
	

}
