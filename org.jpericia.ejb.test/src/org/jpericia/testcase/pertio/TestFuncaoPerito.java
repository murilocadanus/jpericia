package org.jpericia.testcase.pertio;

import javax.ejb.EJB;

import junit.framework.TestCase;

import org.jpericia.businessdelegate.perito.FuncaoPeritoDelegate;
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.exception.BusinessDelegateException;

public class TestFuncaoPerito extends TestCase 
{
	@EJB
	public void test() 
	{
		try 
		{
			FuncaoPeritoDelegate od = new FuncaoPeritoDelegate();
			
			FuncaoPerito fp = new FuncaoPerito();
			//fp.setCodigo(new Integer(201));
			fp.setFuncao("Médico Legista");
			
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
