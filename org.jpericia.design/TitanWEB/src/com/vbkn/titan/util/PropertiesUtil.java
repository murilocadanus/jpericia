package com.vbkn.titan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.vbkn.titan.exception.PropertiesUtilException;

/**
 * @author Valter
 */
public class PropertiesUtil {
	
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	
	public static Properties getProperties(String path) throws PropertiesUtilException{
		Properties properties = new Properties();
		logger.debug("Carregando arquivo de proprieties " + path);
	    try {
	        properties.load(new FileInputStream(path));
	    } catch (FileNotFoundException fnfe) {
	    	logger.error("Causa -> " + fnfe.getCause());
	    	throw new PropertiesUtilException("Arquivo não encontrado", fnfe);
	    } catch (IOException ioe) {
	    	logger.error("Causa -> " + ioe.getCause());
	    	throw new PropertiesUtilException("Erro de I/O", ioe);
	    }
	    return properties;
	}

}
