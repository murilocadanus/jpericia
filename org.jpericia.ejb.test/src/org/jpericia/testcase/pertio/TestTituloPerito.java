package org.jpericia.testcase.pertio;

import javax.ejb.EJB;

import junit.framework.TestCase;

import org.jpericia.businessdelegate.perito.TituloPeritoDelegate;
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.exception.BusinessDelegateException;

public class TestTituloPerito extends TestCase 
{
	@EJB
	public void test() 
	{
		try 
		{
			TituloPeritoDelegate od = new TituloPeritoDelegate();
			
			TituloPerito fp = new TituloPerito();
			//fp.setCodigo(new Integer(201));
			fp.setTitulo("Dr.");
			
			od.inserir(fp);
			//od.pesquisar();
			//od.atualizar(fp);
			//od.remover(fp);
		} 
		catch (BusinessDelegateException e) 
		{
			e.printStackTrace();
		}
	}
}
