package com.vbkn.titan.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 * @author Valter Bruno Konrad Neto
 */

public final class DisplayTagUtil {
	
	private static final Logger LOGGER = Logger.getLogger(DisplayTagUtil.class);

	private static final String getStrParameter(String tableId,	String parameterName, HttpServletRequest request) {

		String paramValue = null;

		try {
			ParamEncoder paramEncoder = new ParamEncoder(tableId);
			String encodedParameterName = paramEncoder.encodeParameterName(parameterName);
			paramValue = request.getParameter(encodedParameterName);

		} catch (RuntimeException e) {
			LOGGER.fatal(e);
			throw e;
		}

		return paramValue;

	}

	private static final Integer getIntParameter(String tableId,
			String parameterName, HttpServletRequest request) {

		Integer paramValue = null;
		try {
			String paramStrValue = getStrParameter(tableId, parameterName,
					request);

			if (paramStrValue != null && !paramStrValue.equals("")) {
				paramValue = Integer.valueOf(paramStrValue);
			}
		} catch (RuntimeException e) {
			LOGGER.fatal(e);
			throw e;
		}
		return paramValue;
	}

	public static final Integer getCurrentPage(String tableId, HttpServletRequest request) {
		Integer page = getIntParameter(tableId, TableTagParameters.PARAMETER_PAGE, request);
		if (page == null) {
			page = new Integer(1);
		}
		return page; 
	}

	public static final Integer getCurrentOrder(String tableId,	HttpServletRequest request) {
		Integer order = getIntParameter(tableId, TableTagParameters.PARAMETER_ORDER, request);
		if ( order == null) {
			order = new Integer(1);
		}
		return order;
	}

	public static final String getCurrentSortName(String tableId, HttpServletRequest request) {
		return getStrParameter(tableId,
				TableTagParameters.PARAMETER_SORT, request);
	}

	public static final Integer getCurrentSortIndex(String tableId,	HttpServletRequest request) {
		return getIntParameter(tableId, TableTagParameters.PARAMETER_ORDER,
				request);
	}

}
