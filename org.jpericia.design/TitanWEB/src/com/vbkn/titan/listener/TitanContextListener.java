package com.vbkn.titan.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Valter Bruno Konrad Neto
 */

public class TitanContextListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(TitanContextListener.class);

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			// configura o log4j
			Context env = (Context) new InitialContext().lookup("java:comp/env");
			String baseLog4jProperties = (String) env.lookup("Log4jProperties");
			PropertyConfigurator.configure(baseLog4jProperties.concat("log4j.properties"));
		} catch (NamingException ne) {
			logger.error("Erro ao configurar log4j");
			ne.printStackTrace();
		}
	}
}
