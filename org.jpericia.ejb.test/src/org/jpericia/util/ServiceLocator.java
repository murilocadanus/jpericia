package org.jpericia.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;
import org.jpericia.exception.ServiceLocatorException;

/**
 * 
 * @author Marlus Cadanus da Costa
 * Classe que implementa o padrao
 * J2EE Service Locator
 */
public class ServiceLocator {
	
	private static Logger logger = Logger.getLogger(ServiceLocator.class);

	private static ServiceLocator me;
	
	private InitialContext context;
	
	private Map<String, Object> cache = new HashMap<String, Object>();
	
	private Properties propriedadesServiceLocator;

	private ServiceLocator() throws ServiceLocatorException {
		try {
			logger.debug("Inicializando Service Locator");
			context = new InitialContext();
			//Context env = (Context) new InitialContext().lookup("OrganizacaoSessionFacade");
//			String baseServiceLocator = (String) env.lookup("ServiceLocatorProperties");
//			logger.debug("Obtendo propriedades do Service Locator em " + baseServiceLocator);
//			Properties initialContextProps =  PropertiesUtil.getProperties(baseServiceLocator.concat("initialcontext.properties"));
//			context = new InitialContext(initialContextProps);
//			propriedadesServiceLocator = PropertiesUtil.getProperties(baseServiceLocator.concat("servicelocator.properties"));
			logger.debug("Inicializou Service Locator");
		} catch (NamingException ne) {
			logger.error("Causa -> " + ne.getCause());
			throw new ServiceLocatorException("Erro ao obter InitialContext", ne);
		}
//		} catch (PropertiesUtilException pue) {
//			logger.error("Causa -> " + pue.getCause());
//			throw new ServiceLocatorException("Erro ao obter arquivo de propriedades do Service Locator", pue);
//		}
	}

	public synchronized static ServiceLocator getInstance() throws ServiceLocatorException {
		if (me == null) {
			me = new ServiceLocator();
		}
		return me;
	}

	/**
	 * Metodo para lookup remoto
	 * @param clazz
	 * @return
	 * @throws ServiceLocatorException
	 */
	public Object getHome(Class<?> clazz, String className) throws ServiceLocatorException {
		logger.debug("Obtendo stub para " + clazz.getSimpleName());
		Object home = null;
		String interfaceName = clazz.getName();
		String lookupName = "";
		try {
			String remoteClassName = className + "Remote";
			Object objref = context.lookup(className + "/remote");
			home = PortableRemoteObject.narrow(objref, Class.forName(interfaceName));
				
			return home;
		} catch (NamingException ex) {
			logger.error("Causa -> " + ex.getCause());
			throw new ServiceLocatorException("Erro ao obter stub para " + lookupName, ex);
		}catch(ClassNotFoundException cnfe){
			logger.error("Causa -> " + cnfe.getCause());
			throw new ServiceLocatorException("Classe nao existe", cnfe);
		}
	}	
	
/*	public Object getHome(Class<?> clazz) throws ServiceLocatorException {
		logger.debug("Obtendo stub para " + clazz.getSimpleName());
		Object home = null;
		String classSimpleName = clazz.getSimpleName();
		String lookupName = "";
		try {
			lookupName = propriedadesServiceLocator.getProperty(classSimpleName+".JNDIName");
			String remoteClassName = propriedadesServiceLocator.getProperty(classSimpleName+".RemoteInterface");
			if(cache.get(classSimpleName) !=  null){
				home = cache.get(clazz.getSimpleName());
			}
			else{
				Object objref = context.lookup(lookupName);
				home = PortableRemoteObject.narrow(objref, Class.forName(remoteClassName));
				cache.put(classSimpleName, home);
			}
			return home;
		} catch (NamingException ex) {
			logger.error("Causa -> " + ex.getCause());
			throw new ServiceLocatorException("Erro ao obter stub para " + lookupName, ex);
		}catch(ClassNotFoundException cnfe){
			logger.error("Causa -> " + cnfe.getCause());
			throw new ServiceLocatorException("Classe nao existe", cnfe);
		}
	}*/
	
	/**
	 * Metodo para lookup local
	 * @param clazz
	 * @return
	 * @throws ServiceLocatorException
	 */
	public Object getLocalHome(Class<?> clazz) throws ServiceLocatorException {
		logger.debug("Obtendo stub para " + clazz.getSimpleName());
		Object home = null;
		String classSimpleName = clazz.getSimpleName();
		String lookupName = "";
		try {
			lookupName = propriedadesServiceLocator.getProperty(classSimpleName+".JNDIName.local");
			String remoteClassName = propriedadesServiceLocator.getProperty(classSimpleName+".LocalInterface");
			
			Object objref = context.lookup(lookupName);
			home = PortableRemoteObject.narrow(objref, Class.forName(remoteClassName));

			return home;
		} catch (NamingException ex) {
			logger.error("Causa -> " + ex.getCause());
			throw new ServiceLocatorException("Erro ao obter stub para " + lookupName, ex);
		}catch(ClassNotFoundException cnfe){
			logger.error("Causa -> " + cnfe.getCause());
			throw new ServiceLocatorException("Classe nao existe", cnfe);
		}
	}

}
